/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.session.impl.transactions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.debug.ThreadDumper;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeHandler;
import org.modelio.vcore.session.api.model.change.IPersistentViewModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.transactions.ConcurrentTransactionException;
import org.modelio.vcore.session.api.transactions.EndTransactionBadIdException;
import org.modelio.vcore.session.api.transactions.EndTransactionNoActiveTransactionException;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionClosureHandler;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.session.api.transactions.ITransactionValidator;
import org.modelio.vcore.session.api.transactions.RedoNoUndoneTransactionException;
import org.modelio.vcore.session.api.transactions.TransactionCreationException;
import org.modelio.vcore.session.api.transactions.TransactionForbiddenException;
import org.modelio.vcore.session.api.transactions.UndoActiveTransactionException;
import org.modelio.vcore.session.api.transactions.UndoNoDoneTransactionException;
import org.modelio.vcore.session.impl.transactions.events.EventFactory;
import org.modelio.vcore.session.impl.transactions.events.ModelChangeSupport;
import org.modelio.vcore.session.impl.transactions.smAction.AddActionNoActiveTransactionException;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.IActionManager;
import org.modelio.vcore.session.plugin.VCoreSession;

/**
 * This class manages transactions stacks ("undone", "active" and "done").
 * 
 * It allows to open and close a transaction, as well as activate the undo/redo mechanism.
 * It is also possible to rollback a transaction and empty the transaction stacks (usually after saving the model).
 */
@objid ("006f848e-0d1e-1f20-85a5-001ec947cd2a")
@SuppressWarnings("resource")
public class TransactionManager implements IActionManager, ITransactionSupport {
    @objid ("006ede80-0d1e-1f20-85a5-001ec947cd2a")
    private boolean actionsRecorded = true;

    /**
     * Keeps in a thread safe way without using locks whether {@link #activeTransactions} contains elements.
     */
    @objid ("c9d7a43f-d84f-4b1c-988a-e54b92923a53")
    private volatile boolean hasOpenTransaction;

    @objid ("006ee100-0d1e-1f20-85a5-001ec947cd2a")
    private volatile boolean transactionsForbidden = false;

    @objid ("006edd4a-0d1e-1f20-85a5-001ec947cd2a")
    private final ActionHandle actionHandle;

    /**
     * Stack implementation based on {@link Deque}.
     */
    @objid ("006ec5ee-0d1e-1f20-85a5-001ec947cd2a")
    private final Deque<Transaction> activeTransactions = new ArrayDeque<>();

    @objid ("7da7c6ec-1c43-11e2-8eb9-001ec947ccaf")
    private final ModelChangeSupport changeSupport;

    @objid ("006ecaee-0d1e-1f20-85a5-001ec947cd2a")
    private final Deque<Transaction> doneTransactions = new ArrayDeque<>();

    /**
     * Synchronization support for the transaction manager transactions stacks.
     * <p>
     * Use this class instead using 'synchronized' on TransactionManager methods.
     */
    @objid ("ead0b194-99c9-4e07-b4e3-756ab21f1407")
    private final SyncSupport sync = new SyncSupport();

    @objid ("de8e47bc-dcdb-432f-a266-75af9fc76297")
    private ITransactionClosureHandler transactionClosureHandler;

    /**
     * Lock that prevent many threads to open concurrent transactions
     */
    @objid ("6ebac3ae-99a8-41f9-85b6-627a2c2b350d")
    private final ReentrantLock transactionOwnerLock = new ReentrantLock();

    @objid ("00458bf2-8400-1033-9188-001ec947cd2a")
    private ITransactionValidator transactionValidator;

    @objid ("006ecbe8-0d1e-1f20-85a5-001ec947cd2a")
    private final Deque<Transaction> undoneTransactions = new ArrayDeque<>();

    /**
     * Constructor for the transaction manager.
     * @param changeSupport the model change support.
     */
    @objid ("006ec4b8-0d1e-1f20-85a5-001ec947cd2a")
    public TransactionManager(ModelChangeSupport changeSupport) {
        this.actionHandle = new ActionHandle(this);
        this.changeSupport = changeSupport;
    }

    /**
     * Add an action to the active transaction. The 'undone' stack is also emptied. Action creation is triggered by methods on
     * 'semantic' objects such as "set_", "append_" and "erase_").
     * @throws org.modelio.vcore.session.impl.transactions.smAction.AddActionNoActiveTransactionException when no active transaction exists.
     */
    @objid ("006ecce2-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void addAction(final IAction action) throws AddActionNoActiveTransactionException {
        if (this.actionsRecorded) {
        
            if (!this.activeTransactions.isEmpty()) {
                // Empty the 'undone' pile
                this.undoneTransactions.clear();
        
                Transaction currentTransaction = this.activeTransactions.peek();
                currentTransaction.addAction(action);
            } else {
                String msg = VCoreSession.getMessage("NoActiveTransactionException", action);
                throw new AddActionNoActiveTransactionException(msg);
            }
        }
    }

    /**
     * Check transaction recording is enabled.
     * @return <code>true</code> only if transaction recording is enabled.
     */
    @objid ("006ecf3a-0d1e-1f20-85a5-001ec947cd2a")
    public boolean areActionRecorded() {
        return this.actionsRecorded;
    }

    @objid ("910ca9fd-137d-4748-8a2b-f057fa668828")
    @Override
    public void asyncExec(Runnable runnable) {
        this.sync.asyncExec(runnable);
    }

    /**
     * Commit a transaction.
     * <p>
     * If the active stack is empty, we are closing and committing an 'undo' block,
     * therefore let stack the closed transaction in
     * the "undo" stack (only if it is 'undoable' otherwise just delete it).
     * <p>
     * @param toCommit the transaction to commit.
     * @throws org.modelio.vcore.session.api.transactions.EndTransactionBadIdException if the transaction being committed is not the currently active one (Sequence error)
     * @throws org.modelio.vcore.session.api.transactions.EndTransactionNoActiveTransactionException if there is no currently active transaction.
     */
    @objid ("006ed0d4-0d1e-1f20-85a5-001ec947cd2a")
    public void commit(final Transaction toCommit) throws EndTransactionBadIdException, EndTransactionNoActiveTransactionException {
        this.sync.lock();
        
        try {
            // commit Transaction when recording is off does nothing
            if (!this.actionsRecorded) {
                return;
            }
        
            // commit when no active transaction => error
            if (this.activeTransactions.isEmpty()) {
                String msg = VCoreSession.getMessage("NoActiveTransactionException", toCommit.getName());
        
                throw new EndTransactionNoActiveTransactionException(msg);
            }
        
            // if committing a transaction which is not the current top => Error
            if (this.activeTransactions.peek() != toCommit) {
                String msg = VCoreSession.getMessage("EndTransactionBadIdException", toCommit.getName(), this.activeTransactions.peek().getName());
                throw new EndTransactionBadIdException(msg);
            }
        
        
            // Empty transactions are worth no effort
            // If it is an empty inner transaction, discard it.
            if (toCommit.isEmpty()) {
                this.activeTransactions.pop();
                Transaction owner = this.activeTransactions.peek();
                if (owner != null) {
                    owner.forgetLastAction();
                }
                this.transactionOwnerLock.unlock();
                return;
            }
        
            // At this stage we are committing a non empty transaction.
            // If the transaction is session level,
            boolean isSession = (this.activeTransactions.size() == 1);
            if (isSession) {
                // Manage the session handlers for top level transactions
                EventFactory evFact = EventFactory.createCommitEvent(toCommit);
        
                // Notify model change handlers.
                // They may modify the model, create new transactions and
                // throw exceptions. In the last case the transaction will be rollbacked by the
                // try with resources that the caller MUST use.
                fireModelChangeHandlers(toCommit, evFact);
        
                // Check it using the transaction validator. (ModelShield)
                if (this.transactionValidator != null) {
                    this.transactionValidator.validate(toCommit);
                    // the validator will throw an exception if the core audit fails.
                    // the transaction will be rollbacked by the try with resources
                    // that the caller MUST use.
                }
        
                // Call the transaction model closure handler who is responsible for adjusting some model elements based on the transaction contents
                if (this.transactionClosureHandler != null) {
                    this.transactionClosureHandler.commit(toCommit);
                    evFact.updateCommitEvent(toCommit);
                }
        
                // Notify persistent view model change listeners.
                // They may modify the model, but should make only non structural modifications.
                // They should not create new transactions.
                // They may throw exceptions. In this case the transaction will be rollbacked by the
                // try-with-resources that the caller MUST have used to open the transaction.
                firePersistentViewModelChangeListeners(evFact.getEvent());
        
                // Pop the top active session
                this.activeTransactions.pop();
        
                // Put non empty undoable sessions in the undo/redo stack,
                // and discards the others.
                if (toCommit.isUndoable()) {
                    this.doneTransactions.push(toCommit);
                }
        
                // Notify model change listeners.
                fireChangeListeners(evFact);
        
            } else {
                // Just pop the top active transaction
                this.activeTransactions.pop();
            }
        
            this.transactionOwnerLock.unlock();
        } finally {
            try {
                updateHasCurrentTransaction();
            } finally {
                this.sync.unlock();
            }
        }
    }

    @objid ("006ed048-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public ITransaction createTransaction(final String idName) throws ConcurrentTransactionException, TransactionCreationException, TransactionForbiddenException {
        return createTransaction(idName, 2, TimeUnit.SECONDS);
    }

    @objid ("458edfa6-5353-4a23-97ce-fb91e36b827e")
    @Override
    public ITransaction createTransaction(final String trName, long timeout, TimeUnit unit) throws ConcurrentTransactionException, TransactionCreationException, TransactionForbiddenException {
        tryGetSyncLock(trName, timeout, unit);
        
        try {
            Transaction newTransaction = null;
        
            if (this.transactionsForbidden) {
                String message = VCoreSession.getMessage("TransactionForbiddenException");
                throw new TransactionForbiddenException(message);
        
            } else if (this.actionsRecorded) {
                newTransaction = new Transaction(trName, this);
        
                this.sync.fastUnlock(); // Avoid potential dead locks because we takes 2 locks.
                try {
                    // Check the transaction is created in the same thread as the parent one,
                    // Forbids other threads to create transaction as long as this one will be open.
                    tryGetLock(this.transactionOwnerLock, trName, timeout, unit);
                } finally {
                    tryGetSyncLock(trName, timeout, unit);
                }
        
                // Make the new transaction a child of the currently active one
                if (!this.activeTransactions.isEmpty()) {
                    Transaction lastTransaction = this.activeTransactions.peek();
                    lastTransaction.addAction(newTransaction);
                }
        
                // Make the new transaction the currently active one
                this.activeTransactions.push(newTransaction);
        
            } else {
                // do nothing
            }
            return newTransaction;
        } finally {
            updateHasCurrentTransaction();
            if (this.sync.isHeldByCurrentThread()) {
                this.sync.unlock();
            }
        }
    }

    /**
     * @return the action handle created by this transaction manager.
     */
    @objid ("006ed26e-0d1e-1f20-85a5-001ec947cd2a")
    public ActionHandle getActionHandle() {
        return this.actionHandle;
    }

    /**
     * Get the currently opened transaction.
     * @return a transaction. Might be <code>null</code>.
     */
    @objid ("006ed318-0d1e-1f20-85a5-001ec947cd2a")
    public Transaction getCurrentTransaction() {
        return this.activeTransactions.peek();
    }

    /**
     * Returns the name of the first transaction available for a 'redo' or <code>null</code> if none.
     * @return the name of the transaction.
     */
    @objid ("006ed3ae-0d1e-1f20-85a5-001ec947cd2a")
    public String getRedoTransactionName() {
        return (this.undoneTransactions.peek() != null) ? this.undoneTransactions.peek().getName() : null;
    }

    /**
     * Returns the name of the first transaction available for a 'undo' or <code>null</code> if none.
     * @return the name of the transaction.
     */
    @objid ("006ed4f8-0d1e-1f20-85a5-001ec947cd2a")
    public String getUndoTransactionName() {
        if (hasCurrentTransaction()) {
            Transaction currentTransaction = this.activeTransactions.peek();
            if (currentTransaction.isLastActionATransaction()) {
                return currentTransaction.getLastTransactionName();
            }
        } else if (!this.doneTransactions.isEmpty()) {
            return this.doneTransactions.peek().getName();
        }
        return null;
    }

    /**
     * Return <code>true</code> if there is a transaction currently active.
     * @return whether or not a transaction is active.
     */
    @objid ("006ed5a2-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean hasCurrentTransaction() {
        return this.hasOpenTransaction;
    }

    /**
     * Return <code>true</code> if a 'redo' transaction is available.
     * @return whether or not the 'redo' is active.
     */
    @objid ("006ed64c-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean hasRedo() {
        return (!this.undoneTransactions.isEmpty());
    }

    /**
     * Returns <code>true</code> if an undo is currently possible.
     * <p>
     * Conditions: <ul>
     * <li>the active stack is not empty</li>
     * <li>the last recorder action is a closed transaction (no pending opened transaction) or the active transaction stack is empty
     * and the undo stack is not empty.</li>
     * </ul>
     * @return <code>true</code> if 'undo' is possible.
     */
    @objid ("006ed6e2-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean hasUndo() {
        return (!this.doneTransactions.isEmpty());
    }

    @objid ("006ed778-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean isEnabled() {
        return this.actionsRecorded;
    }

    /**
     * Run a 'Redo' on the top transaction of the 'undone' stack.
     * <p>
     * Remove it from the 'undone' stack.
     * @throws org.modelio.vcore.session.api.transactions.RedoNoUndoneTransactionException if the undone stack is empty, or a transaction is in progress.
     */
    @objid ("006ed80e-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() throws RedoNoUndoneTransactionException {
        this.sync.lock();
        
        try {
            if (!this.actionsRecorded) {
                return;
            }
        
            if (this.undoneTransactions.isEmpty()) {
                String message = VCoreSession.getMessage("RedoNoUndoneTransactionException");
                throw new RedoNoUndoneTransactionException(message);
            }
        
            if (!this.activeTransactions.isEmpty()) {
                String message = VCoreSession.getMessage("RedoWhileInTransactionException");
                throw new RedoNoUndoneTransactionException(message);
            }
        
            Transaction undoneTransaction = this.undoneTransactions.pop();
        
            Log.trace("Redo '"+ undoneTransaction.getName()+"'");
        
            undoneTransaction.redo();
            this.doneTransactions.push(undoneTransaction);
        
            // Notify transaction listeners
            //fireRedoTransaction(undoneTransaction);
        
            // Fire model change listeners
            EventFactory event = EventFactory.createRedoEvent(undoneTransaction);
            fireChangeListeners(event);
        } finally {
            this.sync.unlock();
        }
    }

    /**
     * Empty the undo/redo stacks.
     */
    @objid ("006eda5c-0d1e-1f20-85a5-001ec947cd2a")
    public void reset() {
        this.sync.lock();
        try {
            if (!this.activeTransactions.isEmpty()) {
                this.activeTransactions.getLast().clearAllSimpleActions();
            }
            this.undoneTransactions.clear();
            this.doneTransactions.clear();
            updateHasCurrentTransaction();
        } finally {
            this.sync.unlock();
        }
    }

    /**
     * Rollback the currently active transaction.
     * <p>
     * The actions of the currently active transaction are undo and the transaction removed from he active stack
     * @param toRollback whether or not this transaction can be undone.
     * @throws org.modelio.vcore.session.api.transactions.EndTransactionBadIdException the transaction to rollback is not the currently active one
     * @throws org.modelio.vcore.session.api.transactions.EndTransactionNoActiveTransactionException if there is no active transaction
     */
    @objid ("006edaf2-0d1e-1f20-85a5-001ec947cd2a")
    public void rollback(final Transaction toRollback) throws EndTransactionBadIdException, EndTransactionNoActiveTransactionException {
        this.sync.lock();
        try {
            if (!this.actionsRecorded) {
                return;
            }
        
            if (this.activeTransactions.isEmpty()) {
                String message = VCoreSession.getMessage("RollbackTransactionNoActiveTransaction",toRollback.getName());
                throw new EndTransactionNoActiveTransactionException(message);
            }
        
            if (this.activeTransactions.peek() != toRollback) {
                String message = VCoreSession.getMessage("RollbackTransactionWrongTransaction",toRollback.getName(), this.activeTransactions.peek().getName());
                throw new EndTransactionBadIdException(message);
            }
        
            // undo the current transaction
            Transaction rollbackTransaction = this.activeTransactions.pop();
            rollbackTransaction.undo(true);
        
            // Notify model change listeners
            //fireAbortTransaction(rollbackTransaction, this.activeTransactions.isEmpty());
        
            // Si la liste des transaction courantes n'est pas vide on a affaire
            // a une transaction imbriquee.
            // il faut donc enlever de la liste des actions la derniere action cree.
            if (!this.activeTransactions.isEmpty()) {
                this.activeTransactions.peek().forgetLastAction();
            }
        
            this.transactionOwnerLock.unlock();
        } finally {
            try {
                updateHasCurrentTransaction();
            } finally {
                this.sync.unlock();
            }
        }
    }

    /**
     * Disable/Enable transaction recording.
     * @param value the new status of transaction recording.
     * @return the old status of transaction recording.
     */
    @objid ("006edc1e-0d1e-1f20-85a5-001ec947cd2a")
    public boolean setActionRecorded(final boolean value) {
        this.sync.lock();
        try {
            boolean oldVal = this.actionsRecorded;
            this.actionsRecorded = value;
            return (oldVal);
        } finally {
            this.sync.unlock();
        }
    }

    @objid ("976fd806-aef4-427c-8603-fa1cfd0ba507")
    @Override
    public void setClosureHandler(ITransactionClosureHandler transactionClosureHandler) {
        this.transactionClosureHandler = transactionClosureHandler;
    }

    /**
     * Set a {@link ITransactionValidator} to check transaction contents before committing.
     * @param value the transaction validator.
     */
    @objid ("009408cc-841a-1033-9188-001ec947cd2a")
    @Override
    public void setTransactionValidator(final ITransactionValidator value) {
        this.sync.lock();
        try {
            this.transactionValidator = value;
        } finally {
            this.sync.unlock();
        }
    }

    /**
     * Undo the transaction available for undo if some.
     * <p>
     * If there is an active transaction, undo its last transaction. If there is no active transaction, undo the top transaction of
     * the undo stack if some.
     * <p>
     * The undone transaction is stacked on the redo stack.
     * @throws org.modelio.vcore.session.api.transactions.UndoNoDoneTransactionException if there is no transaction to undo.
     * @throws org.modelio.vcore.session.api.transactions.UndoActiveTransactionException if no active transaction exists.
     */
    @objid ("006edcb4-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo() throws UndoActiveTransactionException, UndoNoDoneTransactionException {
        this.sync.lock();
        try {
            if (this.actionsRecorded) {
                if (!this.activeTransactions.isEmpty()) {
                    String message = VCoreSession.getMessage("UndoActiveTransactionException");
                    throw new UndoActiveTransactionException(message);
                }
        
                if (this.doneTransactions.isEmpty()) {
                    String message = VCoreSession.getMessage("UndoNoDoneTransactionException");
                    throw new UndoNoDoneTransactionException(message);
                }
        
                Transaction doneTransaction = this.doneTransactions.pop();
        
                Log.trace("Undo '"+ doneTransaction.getName()+"'");
        
                doneTransaction.undo(false);
                this.undoneTransactions.push(doneTransaction);
        
                // Notify transaction listeners
                //fireUndoTransaction(doneTransaction);
        
                // Fire model change listeners (forbid transaction creation)
                EventFactory event = EventFactory.createUndoEvent(doneTransaction);
                fireChangeListeners(event);
            }
        } finally {
            this.sync.unlock();
        }
    }

    @objid ("c70a5ac5-aea4-4781-8ef2-1d3dc346c415")
    void setTransactionsForbidden(boolean transactionsForbidden) {
        this.transactionsForbidden = transactionsForbidden;
    }

    /**
     * Fires model and status change listeners.
     * <p>
     * Transactions are forbidden for model change listeners, ie model change listeners are not allowed to modify the model
     * @param evFact the factory to use to create the model change events.
     */
    @objid ("00938c12-702b-1f21-85a5-001ec947cd2a")
    private void fireChangeListeners(final EventFactory evFact) {
        setTransactionsForbidden(true);
        try {
            this.changeSupport.fireModelChangeListeners(evFact.getEvent());
            
            IStatusChangeEvent statusEvent = evFact.getStatusEvent();
            if (! statusEvent.isEmpty()) {
                this.changeSupport.fireStatusChangeListeners(statusEvent);
            }
        } finally {
            setTransactionsForbidden(false);
        }
    }

    /**
     * Fires model change handlers. Handler can modify the model
     */
    @objid ("ab39bf97-32ef-4356-b448-78912919ed53")
    private void fireModelChangeHandlers(Transaction toCommit, EventFactory evFact) {
        for (IModelChangeHandler it : this.changeSupport.getModelChangeHandlers()) {
            it.handleModelChange(evFact.getEvent());
            evFact.updateCommitEvent(toCommit);
        }
    }

    /**
     * Fires model change handlers. Handler can modify the model
     */
    @objid ("6a8c3a63-b124-413a-a9d3-dec1a0331db0")
    private void firePersistentViewModelChangeListeners(IModelChangeEvent event) {
        for (IPersistentViewModelChangeListener it : this.changeSupport.getPersistentViewChangeListeners()) {
            it.updateView(event);
        }
    }

    /**
     * Compute the return value of {@link #hasCurrentTransaction()}.
     * <p>
     * Must be called each time {@link #activeTransactions} is modified.
     * Call to this method and {@link #activeTransactions} modification have to be atomic together.
     */
    @objid ("f763f310-fd97-4c28-9c97-5c966084c96b")
    private void updateHasCurrentTransaction() {
        this.hasOpenTransaction = !this.activeTransactions.isEmpty();
    }

    @objid ("f57b2d26-f1a3-4fdb-9d0c-41f353f0c1a0")
    private void tryGetLock(Lock lock, final String trName, long timeout, TimeUnit unit) throws ConcurrentTransactionException, TransactionCreationException {
        Transaction lastTransaction = this.activeTransactions.peek();
        try {
            if (!lock.tryLock(timeout, unit)) {
                if (lastTransaction != null) {
                    throw new ConcurrentTransactionException(trName, lastTransaction, lastTransaction.getCreatorThread(), lastTransaction.getCreationTrace(), timeout, unit);
                } else {
                    // This case should not occur
                    String msg = VCoreSession.getMessage("TransactionManager.locked.noTransaction", 
                            trName, 
                            timeout, 
                            unit,
                            lock);
        
                    throw new ThreadDumper()
                    .getDeadLocks()
                    .addAsSupressed(new TransactionCreationException(msg));
                }
            }
        } catch (InterruptedException e) {
            TransactionCreationException e2;
            if (lastTransaction != null) {
                e2 =  new ConcurrentTransactionException(trName, lastTransaction, lastTransaction.getCreatorThread(), lastTransaction.getCreationTrace(), timeout, unit);
            } else {
                // This case should not occur
                String msg = VCoreSession.getMessage("TransactionManager.locked.noTransaction.interrupted", 
                        trName, 
                        timeout, 
                        unit,
                        lock);
            
                e2 = new ThreadDumper()
                .getDeadLocks()
                .addAsSupressed(new TransactionCreationException(msg));
            }
        
            e2.initCause(e);
            
            throw e2;
        }
    }

    @objid ("123cbcc0-0652-4756-816a-e6fb737f0e22")
    private void tryGetSyncLock(final String trName, long timeout, TimeUnit unit) throws ConcurrentTransactionException, IllegalStateException, TransactionCreationException {
        try {
            Transaction lastTransaction = this.activeTransactions.peek();
            if (!this.sync.tryLock(timeout, unit)) {
                if (lastTransaction != null) {
                    throw new ConcurrentTransactionException(trName, lastTransaction, lastTransaction.getCreatorThread(), lastTransaction.getCreationTrace(), timeout, unit);
                } else {
                    String msg = VCoreSession.getMessage("TransactionManager.locked.global", 
                            trName, 
                            timeout, 
                            unit,
                            this.sync);
                    
                    throw new ThreadDumper()
                    .getDeadLocks()
                    .addAsSupressed(new TransactionCreationException(msg));
                }
            }
        } catch (InterruptedException e) {
            String msg = VCoreSession.getMessage("TransactionManager.locked.global.interrupted", 
                    trName, 
                    timeout, 
                    unit,
                    this.sync);
            throw new TransactionCreationException(msg, e);
        }
    }

    @objid ("d030a11c-7ae0-49cc-8597-04de91f6f6f0")
    private void logAllThreads() {
        StringBuilder s = new StringBuilder();
        s.append("Thread dump:\n");
        for (Entry<Thread, StackTraceElement[]>  entry : Thread.getAllStackTraces().entrySet()) {
            s.append(entry.getKey().toString());
            s.append(":\n");
            for (StackTraceElement st : entry.getValue()) {
                s.append("   at ");
                s.append(st.toString());
                s.append("\n");
            }
            s.append("\n");
        }
        Log.error(s.toString());
    }

    /**
     * Synchronization support for the transaction manager.
     * <p>
     * Use this class instead using 'synchronized' on TransactionManager methods:
     * <pre>
     * this.sync.lock();
     * 
     * try {
     * ...
     * } finally {
     * this.sync.unlock();
     * }
     * </pre>
     * 
     * This class has the same functionality as {@link Lock} but also
     * maintains a list of differed actions to run when no transaction is open anymore.
     */
    @objid ("3efa82ff-1b5b-4e2e-874f-2c9dede1ed60")
    private class SyncSupport extends ReentrantLock {
        @objid ("0d57ff2a-ac57-4298-89e2-570c4e7de4be")
        private static final long serialVersionUID = 1L;

        @objid ("9f9d172f-8c1b-45b8-8f5e-a3b81bcc8a24")
        private final Queue<Runnable> deffered = new ConcurrentLinkedQueue<>();

        @objid ("b693f00b-414e-4405-bea6-09e7d7f1c062")
        public SyncSupport() {
            // nothing
        }

        /**
         * Extends inherited behavior by running differed actions if no transaction is open anymore.
         */
        @objid ("4dd5e331-ff96-428c-9a14-569dd8896cfd")
        @Override
        public void unlock() {
            assert isHeldByCurrentThread() : this;
            
            if (!hasCurrentTransaction()) {
                runDefferedActions();
            }
            
            super.unlock();
        }

        @objid ("40d409a4-753f-46c4-b797-f849c84bfe3d")
        private void runDefferedActions() {
            setTransactionsForbidden(true);
            
            Runnable r = this.deffered.poll();
            while (r != null) {
                try {
                    r.run();
                } catch (RuntimeException e) {
                    Log.warning(e);
                }
                r = this.deffered.poll();
            }
            
            setTransactionsForbidden(false);
        }

        /**
         * Execute the given runnable as soon as no transaction is open.
         * <p>
         * No transaction will be open until the runnable has finished execution.
         * The given runnable should execute as quickly as possible in order to not clock Modelio.
         * @param runnable a runnable
         */
        @objid ("d8bfc9a8-5e52-4c6b-9f14-3eec54eb6ebf")
        public void asyncExec(Runnable runnable) {
            if (super.tryLock()) {
                try {
                    if (! hasCurrentTransaction()) {
                        // Run the action now and return
                        runnable.run();
                        return;
                    }
                } finally {
                    super.unlock();
                }
            }
            
            // If we reach this statement, add the action to the deferred ones
            this.deffered.add(runnable);
        }

        /**
         * Same as {@link #unlock()} but does not run deferred actions.
         * <p>
         * Directly calls super {@link ReentrantLock#unlock()}.
         */
        @objid ("2818d732-69a0-4839-a7df-4771eaaf2191")
        public void fastUnlock() {
            assert isHeldByCurrentThread() : this;
            super.unlock();
        }

    }

}
