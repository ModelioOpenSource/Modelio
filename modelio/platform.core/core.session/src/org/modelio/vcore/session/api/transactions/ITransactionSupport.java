/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.api.transactions;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface defining Modelio transaction support.
 * <p>
 * The main method is {@link #createTransaction(String)} that creates a model transaction.
 * Typical usage is:
 * <pre><code>
 * try (ITransaction t = session.createTransaction("do something")) {
 * // code
 * ...
 * 
 * // Commit transaction
 * t.commit();
 * }
 * </code></pre>
 */
@objid ("0041edc6-5572-10c8-842f-001ec947cd2a")
public interface ITransactionSupport {
    /**
     * Create a new model transaction.
     * <p>
     * A transaction <b>must</b> be used in a  <b><i>try-with-resource</i></b> statement,
     * typically the following way:
     * <pre><code>
     * try (ITransaction t = session.createTransaction("do something")) {
     * // code
     * ...
     * 
     * // Commit transaction
     * t.commit();
     * }
     * </code></pre>
     * <p>
     * Only one thread can use a transaction at a time. If another thread already runs a transaction,
     * this thread will wait 2 seconds for the other transaction to finish.
     * If the other transaction is still running after the given time, a {@link ConcurrentTransactionException} will be thrown.
     * <p>
     * If there is a currently active transaction the new transaction is a child of this active transaction and the newly created
     * transaction becomes the currently active transaction. The 'undone' transaction stack is emptied.
     * 
     * @param transactionName a user friendly transaction name. It may be displayed in the GUI.
     * @return the transaction.
     * @throws org.modelio.vcore.session.api.transactions.TransactionForbiddenException if creating a transaction at this state is invalid. The message will give reason of denial.
     * It typically indicates a transaction cannot be created by a {@link ITransactionListener transaction listener} or a model change listener
     * @throws org.modelio.vcore.session.api.transactions.ConcurrentTransactionException if a concurrent transaction is still running
     * after having waited 2 seconds.
     */
    @objid ("0019bc2a-575b-10c8-842f-001ec947cd2a")
    ITransaction createTransaction(final String transactionName) throws ConcurrentTransactionException, TransactionForbiddenException;

    /**
     * Create a new Transaction, waits for the specified time if a concurrent transaction is already running.
     * <p>
     * A transaction <b>must</b> be used in a <b><i>try-with-resource</i></b> statement,
     * typically the following way:
     * <pre><code>
     * try (ITransaction t = session.createTransaction("do something", 2, TimeUnit.SECONDS)) {
     * // code
     * ...
     * 
     * // Commit transaction
     * t.commit();
     * }
     * </code></pre>
     * <p>
     * Only one thread can use a transaction at a time. If another thread already runs a transaction,
     * this thread will wait the specified time for the other transaction to finish.
     * If the other transaction is still running after the given time, a {@link ConcurrentTransactionException} will be thrown.
     * <p>
     * If there is a currently active transaction the new transaction is a child of this active transaction and the newly created
     * transaction becomes the currently active transaction. The 'undone' transaction stack is emptied.
     * 
     * @param transactionName a user friendly transaction name. It may be displayed in the GUI.
     * @param timeout the time to wait for the lock
     * @param unit the time unit of the timeout argument
     * @return the created transaction.
     * @throws org.modelio.vcore.session.api.transactions.TransactionForbiddenException if creating a transaction at this state is invalid. The message will give reason of denial.
     * It typically indicates a transaction cannot be created by a {@link ITransactionListener transaction listener} or a model change listener.
     * @throws org.modelio.vcore.session.api.transactions.ConcurrentTransactionException if a concurrent transaction is still running after having waited.
     */
    @objid ("d7dfb2a4-ed06-47f6-9cd8-372a3081ebb0")
    ITransaction createTransaction(final String transactionName, long timeout, TimeUnit unit) throws ConcurrentTransactionException, TransactionForbiddenException;

    /**
     * Set a {@link ITransactionValidator} to check transaction contents before commiting.
     * 
     * @param value the transaction validator.
     */
    @objid ("001ba4b8-575b-10c8-842f-001ec947cd2a")
    void setTransactionValidator(final ITransactionValidator value);

    /**
     * Set the {@link ITransactionClosureHandler} that updates namespace uses before commiting.
     * 
     * @param transactionClosureHandler the closure handler.
     */
    @objid ("29e7c01f-7e51-4b75-b995-590f142f29e4")
    void setClosureHandler(ITransactionClosureHandler transactionClosureHandler);

    /**
     * Return true if there is a transaction currently active.
     * 
     * @return whether or not a transaction is active.
     */
    @objid ("001bc524-575b-10c8-842f-001ec947cd2a")
    boolean hasCurrentTransaction();

    /**
     * Return true if a 'redo' transaction is available.
     * 
     * @return whether or not the 'redo' is active.
     */
    @objid ("001bd64a-575b-10c8-842f-001ec947cd2a")
    boolean hasRedo();

    /**
     * Returns true if an undo is currently possible. Conditions:
     * <ul>
     * <li>the active stack is not empty</li>
     * <li>the last recorder action is a closed transaction (no pending opened transaction) or the active transaction stack is empty
     * and the undo stack is not empty.</li>
     * </ul>
     * 
     * @return <code>true</code> if 'undo' is possible.
     */
    @objid ("001be77a-575b-10c8-842f-001ec947cd2a")
    boolean hasUndo();

    /**
     * Undo the transaction available for undo if some.
     * 
     * If there is an active transaction, undo its last transaction. If there is no active transaction, undo the top transaction of
     * the undo stack if some.
     * 
     * The undone transaction is stacked on the redo stack.
     * 
     * @throws org.modelio.vcore.session.api.transactions.UndoNoDoneTransactionException if there is no transaction to undo.
     * @throws org.modelio.vcore.session.api.transactions.UndoActiveTransactionException if no active transaction exists.
     */
    @objid ("001bf8be-575b-10c8-842f-001ec947cd2a")
    void undo() throws UndoActiveTransactionException, UndoNoDoneTransactionException;

    /**
     * Run a 'Redo' on the top transaction of the 'undone' stack. Remove it from the 'undone' stack.
     * 
     * @throws org.modelio.vcore.session.api.transactions.RedoNoUndoneTransactionException if the undone stack is empty, or a transaction is in progress.
     */
    @objid ("2b18539f-21dd-11e2-afc3-001ec947c8cc")
    void redo() throws RedoNoUndoneTransactionException;

//    * Add a transaction listener, triggered when a transaction is committed,
//    * rollbacked, undone and redone.
//    * @param listener the transaction listener to register.
//@objid ("8b7bdfe7-b374-4d46-a236-87d7bec438f8")
//void addTransactionListener(ITransactionListener listener);
//   Unregister a transaction listener.
//     @param aListener the listener to unregister.
//@objid ("dc41d515-f618-44bd-81df-879989272172")
//void removeTransactionListener(ITransactionListener aListener);
    /**
     * Execute the given runnable as soon as no transaction is open and the transaction manager is not busy:
     * <ul>
     * <li>If no transaction is currently open and the transaction manager is idle,
     * execute the given runnable <b>immediately in the current thread</b>.
     * <li>In the other case, <b>schedule</b> the runnable to be executed when the top transaction is
     * committed or rollbacked, in <b>the thread that closes the transaction</b>.
     * </ul>
     * No transaction will be open until the runnable has finished execution.
     * The given runnable should execute as quickly as possible in order to not block Modelio.
     * 
     * @param runnable a runnable to execute when no transaction is running.
     */
    @objid ("960fd012-9aa4-4f55-b048-d3d51d133c42")
    void asyncExec(Runnable runnable);

}
