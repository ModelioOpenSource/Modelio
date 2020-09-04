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

package org.modelio.vcore.session.impl.transactions.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.session.impl.transactions.smAction.AppendDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.CreateElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.DeleteElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.EraseDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.MoveDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.SetAttributeAction;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00d00074-0000-0893-0000-000000000000")
class ModelChangeActionVisitor implements IActionVisitor {
    @objid ("00d00074-0000-08db-0000-000000000000")
    private ModelChangeEvent event;

    @objid ("01f419b4-0000-0beb-0000-000000000000")
    private StatusChangeEvent statusEvent;

    @objid ("00d00074-0000-08b7-0000-000000000000")
    public ModelChangeActionVisitor(ModelChangeEvent event, StatusChangeEvent statusEvent) {
        this.event = event;
        this.statusEvent = statusEvent;
    }

    @objid ("00d00074-0000-089b-0000-000000000000")
    @Override
    public void visitTransaction(final Transaction tr) {
        // simply visit the actions of the transaction
        for (IAction a : tr.getActions()) {
            a.accept(this);
        }
    }

    @objid ("00d00074-0000-089f-0000-000000000000")
    @Override
    public void visitCreateElementAction(final CreateElementAction action) {
        SmObjectImpl created = action.getRefered();
        
        // assert(! refered.isTobeDeleted());
        
        // A created element cannot have already been created, thereby
        // permitting the new object to be stored without test
        this.event.createdElements.add(created);
    }

    @objid ("00d00074-0000-08a3-0000-000000000000")
    @Override
    public void visitDeleteElementAction(final DeleteElementAction action) {
        SmObjectImpl deleted = action.getRefered();
        
        // Get the old parent that can be found in the erase list
        MObject oldParent = this.event.erasedElements.get(deleted);
        if (oldParent != null) {
            this.event.deletedElements.put(deleted, oldParent);
            // Remove the now useless erase event
            this.event.erasedElements.remove(deleted);
        } else {
            if (deleted.getMClass().areOrphansAllowed()) {
                this.event.deletedRootElements.add(deleted);
            }
        }
    }

    @objid ("00d00074-0000-08a7-0000-000000000000")
    @Override
    public void visitSetAttributeAction(final SetAttributeAction action) {
        SmObjectImpl refered = action.getRefered();
        
        // Forget created then deleted elements
        if (refered.isDeleted()) {
            return;
        }
        
        // If the element is already in the list of created elements, it has not
        // to be added
        if (this.event.createdElements.contains(refered)) {
            return;
        }
        
        if (action.getAtt() == refered.getClassOf().statusAtt()) {
            // populate the status change event
            long oldStatus = (long) action.getOldValue();
            long newStatus = (Long) refered.getAttVal(action.getAtt());
        
            this.statusEvent.add(refered, oldStatus, newStatus);
        
        } else {
            // If the element is already in the list of updated elements, it has
            // not to be added
            if (! this.event.updatedElements.contains(refered) ) {
                // If the element already is in the list of created elements, it
                // has not to be added
                if (! this.event.createdElements.contains(refered)) {
                    this.event.updatedElements.add(refered);
                }
            }
        }
    }

    @objid ("00d00074-0000-08ab-0000-000000000000")
    @Override
    public void visitEraseDependencyAction(final EraseDependencyAction action) {
        SmObjectImpl owner = action.getRefered();
        SmObjectImpl value = action.getRef();
        //SmDependency dep = action.getDep();
        
        // Semantic: 'value' has been removed from 'dep' on the object 'owner'
        
        // Only composition dependencies are managed
        if (isComponentDependency(action.getDep()) && value != null) {
            // If the element is in the created element list, that means it has
            // not to be declared as moved
            // because it is already declared as created.
            if (this.event.createdElements.contains(value)) {
                return;
            }
        
            // If the element already is in the erase relation, that means the
            // element has been moved
            // under another element (in the same transaction)..., which has not
            // to be managed because
            // the main point is the old parent of the moved element that
            // exactly corresponds to the
            // first erase dependency
            if (this.event.erasedElements.get(value) != null) {
                return;
            }
        
            // If the element has been removed then re-added to the same owner, consider
            // that the dep was reordered.
            if (value.isValid() && value.getCompositionOwner() == owner) {
                // for performance issues, do not use 'indexOf'
                // int newIndex = (action.smDep.isMultiple())? ((SmMultipleDependency) action.smDep).getValueList(owner.getData()).indexOf(value) - 1 :-1;
                // if (newIndex != action.index)
                this.event.updatedElements.add(owner);
                return;
            } else {
                // If the relation did not exist, it has to be created : the key
                // corresponds to the
                // child element (that has been erased) and the value
                // corresponds
                // to the old/current owner
                // of this element
                this.event.erasedElements.put(value, owner);
        
                // It can be a move, so the movedElements list is filled with
                // the old owner.
                this.event.movedElements.put(value, owner);
            }
        } else if (action.getDep().isPartOf() && owner != null) {
            this.event.updatedElements.add(owner);
        }
    }

    @objid ("00d00074-0000-08af-0000-000000000000")
    @Override
    public void visitAppendDependencyAction(final AppendDependencyAction action) {
        SmObjectImpl owner = action.getRefered();
        SmObjectImpl value = action.getRef();
        SmDependency dep = action.getDep();
        
        // Semantic: 'value' has been appended to 'dep' on the object 'owner'
        
        // Forget operations on deleted elements
        if (owner == null || value == null || value.isDeleted() || owner.isDeleted()) {
            return;
        }
        
        if (dep.isPartOf()) {
        
            // If the element is already in the list of created elements, it has
            // not to be added
            if (this.event.createdElements.contains(owner)) {
                return;
            }
        
            this.event.updatedElements.add(owner);
        }
    }

    @objid ("00d00074-0000-08b3-0000-000000000000")
    @Override
    public void visitMoveDependencyAction(final MoveDependencyAction theMoveDependencyAction) {
        SmObjectImpl refered = theMoveDependencyAction.getRefered();
        
        // Forget operations on deleted elements
        if (refered.isDeleted()) {
            return;
        }
        
        // If the element is already in the list of created elements, it has not
        // to be added
        if (this.event.createdElements.contains(refered)) {
            return;
        }
        
        // If the element already is in the list of updated elements, it has not
        // to be added
        if (this.event.updatedElements.contains(refered)) {
            // If the element already is in the list of created eleemnts, it has
            // not to be added
            if (this.event.createdElements.contains(refered)) {
                this.event.updatedElements.add(refered);
            }
        }
    }

    @objid ("01f42120-0000-1ee0-0000-000000000000")
    private static boolean isComponentDependency(SmDependency dep) {
        return dep.isComponent();
    }

}
