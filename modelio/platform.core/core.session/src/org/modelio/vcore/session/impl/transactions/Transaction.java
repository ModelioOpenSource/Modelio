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

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;

/**
 * Les transactions sont identifiees, cet identifiant permet de donner ?
 * l'utilisateur une information precisse sur la sequence d'actions annulees ou
 * rejouees, il permet aussi lors de la fermeture de la transaction de verifier
 * si la transaction fermee est bien la derni?re ouverte.
 * 
 * Toutes les actions et transactions crees pendant une transactions lui
 * appartiennent.
 */
@objid ("006e7a3a-0d1e-1f20-85a5-001ec947cd2a")
public class Transaction implements IAction, ITransaction {
    @objid ("006d56fa-0d1e-1f20-85a5-001ec947cd2a")
    private String name;

    @objid ("006d57d6-0d1e-1f20-85a5-001ec947cd2a")
    private boolean undoable = true;

    @objid ("60d15188-babd-11e1-9fd3-001ec947ccaf")
    private boolean closed = false;

    /**
     * Stack implementation based on {@link Deque}<br>
     */
    @objid ("00888754-702b-1f21-85a5-001ec947cd2a")
    private List<IAction> actions = new ArrayList<>();

    @objid ("60d15189-babd-11e1-9fd3-001ec947ccaf")
    private TransactionManager manager;

    @objid ("54978554-accc-4726-a6c0-36272ab0c62a")
    private Thread creatorThread;

    @objid ("793998af-1387-4741-8966-64b0b2ce578f")
    private Throwable creationTrace;

    /**
     * Constructeur d'une transaction.
     */
    @objid ("006d4e3a-0d1e-1f20-85a5-001ec947cd2a")
    Transaction(final String name, final TransactionManager manager) {
        this.name = name;
        this.undoable = true;
        this.manager = manager;
        this.creatorThread = Thread.currentThread();
        this.creationTrace = new Throwable(name+" transaction created.");
    }

    @objid ("006d4f70-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void accept(IActionVisitor v) {
        v.visitTransaction(this);
    }

    /**
     * Ajout d'une action dans la transaction
     * @param action the action to add
     */
    @objid ("006d5010-0d1e-1f20-85a5-001ec947cd2a")
    public void addAction(final IAction action) {
        this.actions.add(action);
    }

    /**
     * Permet recursivement de:<ul>
     * <li> positionner toutes les transactions contenue a not undoable,
     * <li> detruire toutes les actions simples.
     * </ul>
     * Cette methode est appellee par le {@link TransactionManager#reset() reset()}  de TransactionManager.
     */
    @objid ("006d50a6-0d1e-1f20-85a5-001ec947cd2a")
    void clearAllSimpleActions() {
        List<IAction> tmp = new ArrayList<>(this.actions);
        
        disableUndo();
        
        this.actions.clear();
        
        for (IAction action : tmp) {
            if (action.isTransaction()) {
                @SuppressWarnings("resource")
                Transaction sub = (Transaction) action;
                sub.clearAllSimpleActions();
                this.actions.add(sub);
            }
        }
    }

    /**
     * Get the stack trace recorded when the transaction was created.
     * @return the transaction creation stack trace.
     */
    @objid ("c5ab1fcb-83cc-40d0-8d9e-6dc4e8ab5926")
    Throwable getCreationTrace() {
        return this.creationTrace;
    }

    /**
     * @return the thread where this transaction was created.
     */
    @objid ("477ee363-7b20-4e2b-985e-4296dbf2c091")
    public Thread getCreatorThread() {
        return this.creatorThread;
    }

    /**
     * Si la derniere action est une transaction, renvoie son nom. Sinon renvoie "".
     */
    @objid ("006d51d2-0d1e-1f20-85a5-001ec947cd2a")
    String getLastTransactionName() {
        if (isLastActionATransaction()) {
            return  ((Transaction) this.actions.get(this.actions.size()-1)).getName();
        }
        return "";
    }

    @objid ("006d58c6-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Permet de savoir si la transaction est vide, c'est a dire ne contient aucune
     * action.
     * @return <i>true</i> si la transaction est vide.
     */
    @objid ("006d531c-0d1e-1f20-85a5-001ec947cd2a")
    public boolean isEmpty() {
        return this.actions.isEmpty();
    }

    /**
     * Verifie si la derniere action est une transaction.
     * @return <i>true</i> si la derniere action est une transaction.
     */
    @objid ("006d53c6-0d1e-1f20-85a5-001ec947cd2a")
    public boolean isLastActionATransaction() {
        if (!this.actions.isEmpty()) {
            return this.actions.get(this.actions.size()-1).isTransaction();
        }
        return false;
    }

    @objid ("006d5466-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public boolean isTransaction() {
        return true;
    }

    /**
     * @return <i>true</i> if the transaction can be undone.
     */
    @objid ("006d5970-0d1e-1f20-85a5-001ec947cd2a")
    public boolean isUndoable() {
        return this.undoable;
    }

    /**
     * Lance la methode redo sur toutes les methodes de la transaction
     */
    @objid ("006d5538-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void redo() {
        for (IAction action : this.actions) {
            action.redo();
        }
    }

    /**
     * Permet de positinner toutes les transactions contenue dans la transaction
     * ? notUndoable. Cette methode est appellee par le reset.
     */
    @objid ("006d55ce-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void disableUndo() {
        this.undoable = false;
        
        // Appelle la methode redo sur toutes les actions de la transaction en
        // commencant par fin de la liste
        for (IAction action : this.actions) {
            action.disableUndo();
        }
    }

    /**
     * Lance la methode undo sur toutes les actions de la transaction si la
     * transaction est "undoable"
     */
    @objid ("006d5664-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void undo(final boolean rollback) {
        // Appelle la methode undo sur toutes les actions de la transaction
        // en commencant par fin de la liste
        if (this.undoable)
        {
            for (int i = this.actions.size()-1; i>=0; --i) {
                this.actions.get(i).undo(rollback);
            }
        }
    }

    /**
     * Forget the last registered action.
     * @return the removed action.
     */
    @objid ("008ac6cc-702b-1f21-85a5-001ec947cd2a")
    IAction forgetLastAction() {
        return this.actions.remove(this.actions.size()-1);
    }

    /**
     * Please do not modify the returned list.
     * @return the transaction actions.
     */
    @objid ("008f242e-f11f-1f3c-aafd-001ec947cd2a")
    public List<IAction> getActions() {
        return this.actions;
    }

    @objid ("60d15191-babd-11e1-9fd3-001ec947ccaf")
    @Override
    public void commit() {
        this.manager.commit(this);
        this.closed = true;
    }

    @objid ("60d15194-babd-11e1-9fd3-001ec947ccaf")
    @Override
    public void rollback() {
        this.manager.rollback(this);
        this.closed = true;
    }

    @objid ("60d15197-babd-11e1-9fd3-001ec947ccaf")
    @Override
    public void close() throws RuntimeException {
        if (! this.closed) {
            Log.warning(new Throwable("Transaction '"+getName()+"' not committed, auto-rollbacking."));
            rollback();
        }
    }

}
