/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.vcore.session.impl.transactions.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model change event factory.
 * <p>
 * Standard Usage:<ul>
 * <li> Call one of the create static method such as {@link #createCommitEvent(Transaction)},
 * {@link #createUndoEvent(Transaction)}, {@link #createRedoEvent(Transaction)}.
 * <li> Use the {@link #getEvent()} and {@link #getStatusEvent()} to get the built events.
 * </ul>
 * <p>
 * After a call to {@link #createCommitEvent(Transaction)}, you can call {@link #updateCommitEvent(Transaction)}
 * if the transaction is updated.
 * <p>
 * Usage for custom events:<ul>
 * <li> Call {@link #createEvent(ChangeCause)}.
 * <li> Use {@link #process(IAction)} to process your actions.
 * <li> Call {@link #postProcess()} once all actions are processed.
 * <li> Use the {@link #getEvent()} and {@link #getStatusEvent()} to get the built events.
 * </ul>
 */
@objid ("004e8cac-ca22-1f3c-aafd-001ec947cd2a")
public class EventFactory {
    /**
     * index of the last processed action in the transaction
     */
    @objid ("37e53587-ba41-4cc5-9d86-7ca3d938af73")
    private int lastTrIndex;

    @objid ("7d70f147-1c43-11e2-8eb9-001ec947ccaf")
    private ModelChangeEvent event = new ModelChangeEvent();

    @objid ("7d70f148-1c43-11e2-8eb9-001ec947ccaf")
    private StatusChangeEvent statusEvent = new StatusChangeEvent();

    @objid ("7d70f149-1c43-11e2-8eb9-001ec947ccaf")
    private IActionVisitor visitor;

    /**
     * Create ready to use commit events.
     * 
     * @param tr a transaction
     * @return a factory with events ready to use.
     */
    @objid ("004e980a-ca22-1f3c-aafd-001ec947cd2a")
    public static EventFactory createCommitEvent(final Transaction tr) {
        EventFactory f = new EventFactory(ChangeCause.TRANSACTION);
        
        // Create the visitor that will visit all the action hierarchy
        f.visitor = new ModelChangeActionVisitor(f.event, f.statusEvent);
        
        // Accept the visitor on the transaction to define the event structure
        tr.accept(f.visitor);
        f.lastTrIndex = tr.getActions().size() - 1; 
        
        // Call the post process
        f.postProcess();
        return f;
    }

    /**
     * Create a change event factory for an undone transaction.
     * 
     * @param tr the undone transaction
     * @return the change event factory.
     */
    @objid ("004edbf8-ca22-1f3c-aafd-001ec947cd2a")
    public static EventFactory createUndoEvent(final Transaction tr) {
        EventFactory f = new EventFactory(ChangeCause.UNDO);
        
        // Create the visitor that will visit all the action hierarchy
        f.visitor = new UndoModelChangeActionVisitor(f.event, f.statusEvent);
        
        // Accept the visitor on the transaction to define the event structure
        tr.accept(f.visitor);
        
        // Call the post process
        f.postProcess();
        return f;
    }

    /**
     * Create a change event factory for an redone transaction.
     * 
     * @param tr the redone transaction
     * @return the change event factory.
     */
    @objid ("004efab6-ca22-1f3c-aafd-001ec947cd2a")
    public static EventFactory createRedoEvent(final Transaction tr) {
        // create a new event to be filled
        EventFactory f = new EventFactory(ChangeCause.REDO);
        
        // Create the visitor that will visit all the action hierarchy
        f.visitor = new ModelChangeActionVisitor(f.event, f.statusEvent);
        
        // Accept the visitor on the transaction to define the event structure
        tr.accept (f.visitor);
        
        // Call the post process
        f.postProcess();
        return f;
    }

    /**
     * Post process the already processed actions.
     * <p>
     * To be called after having processed all model actions and
     * before calling {@link #getEvent()} and {@link #getStatusEvent()}.
     */
    @objid ("004f19c4-ca22-1f3c-aafd-001ec947cd2a")
    public void postProcess() {
        // HashMap<MObject, MObject>::iterator it;
        HashSet<MObject> rightCreations = new HashSet<>(this.event.createdElements.size());
        HashMap<MObject, MObject> rightDestructions = new HashMap<>(this.event.deletedElements.size());
        HashSet<MObject> rightUpdates = new HashSet<>(this.event.updatedElements.size());
        HashMap<MObject, MObject> rightMoves = new HashMap<>(this.event.movedElements.size());
        
        // 1. Created objects
        for (MObject obj : this.event.createdElements) {
            // If the parent of the element is already defined in the list, just
            // remove it from the list
            if (!this.event.createdElements.contains(obj.getCompositionOwner())) {
                // Only elements that are not destroyed are managed
                if (!obj.isDeleted()) {
                    rightCreations.add(obj);
                }
            }
        }
        
        // 2. Then manage the update list
        for (MObject obj : this.event.updatedElements) {
            // Only elements that are not destroyed are managed
            if (!obj.isDeleted()) {
                rightUpdates.add(obj);
            }
        }
        
        // 3. Then manage the move list
        for (Entry<MObject, MObject> entry : this.event.movedElements.entrySet()) {
            // Only elements that are not destroyed are managed
            if (!entry.getKey().isDeleted()) {
                rightMoves.put(entry.getKey(), entry.getValue());
            }
        }
        
        // 4. Then manage the delete list
        for (Entry<MObject, MObject> entry : this.event.deletedElements.entrySet()) {
            // If the element has been created and deleted, ignore it from both
            // all lists.
            MObject deletedEl = entry.getKey();
            if (! this.event.createdElements.contains(deletedEl)) {
                // If the parent of the element is already defined in the list,
                // just remove it from the list
        
                MObject deletedParent = entry.getValue();
                if (!deletedParent.isDeleted() || this.event.deletedRootElements.contains(deletedParent)) {
                    rightDestructions.put(deletedEl, deletedParent);
                }
            }
        
            this.statusEvent.remove(deletedEl);
        
        }
        
        
        this.event.createdElements = rightCreations;
        this.event.updatedElements = rightUpdates;
        this.event.movedElements = rightMoves;
        this.event.deletedElements = rightDestructions;
    }

    /**
     * Private constructor.
     */
    @objid ("7d70f14a-1c43-11e2-8eb9-001ec947ccaf")
    private EventFactory(ChangeCause cause) {
        // create a new event to be filled
        this.event = new ModelChangeEvent();
        this.statusEvent = new StatusChangeEvent();
        this.event.cause = cause;
        this.statusEvent.cause = cause;
    }

    /**
     * Create an empty model change event.
     * <p>
     * Call {@link #process(IAction)} to fill the events and
     * {@link #postProcess()} when finished.
     * 
     * @param cause The cause of the model change event. {@link ChangeCause#UNDO} is not allowed.
     * @return a ready factory.
     */
    @objid ("7d73539c-1c43-11e2-8eb9-001ec947ccaf")
    public static EventFactory createEvent(ChangeCause cause) {
        assert (cause != ChangeCause.UNDO) : cause;
        
        // create a new event to be filled
        EventFactory f = new EventFactory(cause);
        
        // Create the visitor that will visit all the action hierarchy
        f.visitor = new ModelChangeActionVisitor(f.event, f.statusEvent);
        return f;
    }

    /**
     * Process an action.
     * 
     * @param a an action.
     */
    @objid ("7d7353a1-1c43-11e2-8eb9-001ec947ccaf")
    public void process(IAction a) {
        a.accept(this.visitor);
    }

    /**
     * @return the built model change event.
     */
    @objid ("7d75b5f0-1c43-11e2-8eb9-001ec947ccaf")
    public IModelChangeEvent getEvent() {
        return this.event;
    }

    /**
     * @return the built status change event.
     */
    @objid ("7d75b5f4-1c43-11e2-8eb9-001ec947ccaf")
    public IStatusChangeEvent getStatusEvent() {
        return this.statusEvent;
    }

    /**
     * Test whether the change and status event are both empty.
     * 
     * @return <code>true</code> if no model nor status change occurred.
     */
    @objid ("ec573269-4fe7-4ca5-b3c2-56287dec8823")
    public boolean isEmpty() {
        return this.event.isEmpty() && this.statusEvent.isEmpty();
    }

    @objid ("8ebe9eda-b49c-4310-af51-5cdf575594ad")
    void processStatusChange(SmObjectImpl obj, long oldStatus, long newStatus) {
        this.statusEvent.add(obj, oldStatus, newStatus);
    }

    /**
     * Update the commit event.
     * <p>
     * To be called when the transaction had new actions after having called {@link #createCommitEvent(Transaction)}.
     * 
     * @param tr the updated transaction
     */
    @objid ("15b75b37-5057-47fa-92e9-54187c60471f")
    public void updateCommitEvent(final Transaction tr) {
        final List<IAction> actions = tr.getActions();
        int s=actions.size();
        
        if (this.lastTrIndex < s-1) {
            for (; this.lastTrIndex<s; ++this.lastTrIndex) {
                actions.get(this.lastTrIndex).accept(this.visitor);
            }
        
            // Call the post process
            postProcess();
        }
    }

}
