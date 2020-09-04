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

@objid ("01f42120-0000-0142-0000-000000000000")
class UndoModelChangeActionVisitor implements IActionVisitor {
    @objid ("01f42120-0000-0193-0000-000000000000")
    private ModelChangeEvent event;

    @objid ("01f42120-0000-01ab-0000-000000000000")
    private StatusChangeEvent statusEvent;

    @objid ("01f42120-0000-014a-0000-000000000000")
    public UndoModelChangeActionVisitor(ModelChangeEvent event, StatusChangeEvent statusEvent) {
        this.event = event;
        this.statusEvent = statusEvent;
    }

    @objid ("01f42120-0000-0154-0000-000000000000")
    @Override
    public void visitTransaction(final Transaction tr) {
        // simply visit the actions of the transaction
        for (IAction a : tr.getActions()) {
            a.accept(this);
        }
    }

    @objid ("01f42120-0000-015d-0000-000000000000")
    @Override
    public void visitCreateElementAction(final CreateElementAction action) {
        // note: in 'undo' operations, creating an object is always applied to a
        // previously deleted object
        // therefore let's call the refered object 'deleted'
        SmObjectImpl deleted = action.getRefered();
        
        // Get the old parent that can be found in the erase list
        MObject oldParent = this.event.erasedElements.get(deleted);
        if (oldParent != null) {
            this.event.deletedElements.put(deleted, oldParent);
            // Remove the now useless erase event
            this.event.erasedElements.remove(deleted);
        }
        // // Get the old parent that can be found in the erase list
        // std::map<SmObjectImpl, SmObjectImpl>::iterator found =
        // event.erasedElements.find(refered);
        // if ( found != event.erasedElements.end() )
        // {
        // event.deletedElements [refered] = found.second;
        // // Remove the now useless erase event
        // event.erasedElements.erase(found);
        // }
    }

    @objid ("01f42120-0000-0166-0000-000000000000")
    @Override
    public void visitDeleteElementAction(final DeleteElementAction action) {
        // note: in 'undo' operations, deleting an object is always applied to a
        // previously created object
        // therefore let's call the refered object 'created'
        SmObjectImpl created = action.getRefered();
        
        // A created element cannot have already been created, thereby
        // permitting the new object
        // to be stored without test
        this.event.createdElements.add(created);
    }

    @objid ("01f42120-0000-016f-0000-000000000000")
    @Override
    public void visitSetAttributeAction(final SetAttributeAction action) {
        SmObjectImpl refered = action.getRefered();
        
        // Forget created then deleted elements
        if (refered.isDeleted())
            return;
        
        // If the element is already in the list of created elements, it has not
        // to be added
        if (this.event.createdElements.contains(refered))
            return;
        
        if (action.getAtt() == refered.getClassOf().statusAtt()) {
            // populate the status change event
            long oldStatus = (long) action.getOldValue();
            long newStatus = refered.getData().getStatus();
        
            this.statusEvent.add(refered, oldStatus, newStatus);
        
        } else {
            // If the element is already in the list of updated elements, it has
            // not to be added
            if (this.event.updatedElements.contains(refered) == false) {
                // If the element already is in the list of created elements, it
                // has not to be added
                if (this.event.createdElements.contains(refered) == false)
                    this.event.updatedElements.add(refered);
            }
        }
    }

    @objid ("01f42120-0000-0178-0000-000000000000")
    @Override
    public void visitEraseDependencyAction(final EraseDependencyAction theEraseDependencyAction) {
        SmObjectImpl ref = theEraseDependencyAction.getRef();
        SmObjectImpl refered = theEraseDependencyAction.getRefered();
        SmDependency dep = theEraseDependencyAction.getDep();
        
        // Forget operations on deleted elements
        if (refered == null || ref.isDeleted() || refered.isDeleted())
            return;
        
        if (dep.isPartOf() ) {
            // If the element is already in the list of created elements, it has
            // not to be added
            if (this.event.createdElements.contains(refered))
                return;
        
            if (this.event.updatedElements.contains(refered) == false) {
                this.event.updatedElements.add(refered);
            }
        }
    }

    @objid ("01f42120-0000-0181-0000-000000000000")
    @Override
    public void visitAppendDependencyAction(final AppendDependencyAction theAppendDependencyAction) {
        SmObjectImpl target = theAppendDependencyAction.getRef();
        SmObjectImpl src = theAppendDependencyAction.getRefered();
        SmDependency dep = theAppendDependencyAction.getDep();
        
        if (target == null || src == null)
            return;
        
        // Only composition dependencies are managed
        if (isComponentDependency(dep)) {
        
            // If the element is in the created element list, that means it has
            // not to be declared as moved
            // because it is already declared as created.
            if (this.event.createdElements.contains(target))
                return;
        
            // If the element already is in the erase relation, that means the
            // element has been moved
            // under another element (in the same transaction)..., which has not
            // to be managed because
            // the main point is the old parent of the moved element that
            // exactly corresponds to the
            // first erase dependency
            if (this.event.erasedElements.containsKey(target))
                return;
        
            // If the element has been removed then readded to the same owner,
            // it is a false move.
            if (target.isValid() && target.getCompositionOwner() == src)
                return;
        
            if ( target.isDeleted()) {
                this.event.deletedElements.put(target, src);
            } else {
                // If the relation did not exist, it has to be created : the key
                // corresponds to the
                // child element (that has been moved) and the value corresponds
                // to the old/current owner
                // of this element
                this.event.erasedElements.put(target, src);
        
                // It can be a move, so the movedElements list is filled with
                // the old owner.
                this.event.movedElements.put(target, src);
            }
        } else if (dep.isPartOf()) {
            if (this.event.updatedElements.contains(src) == false) {
                this.event.updatedElements.add(src);
            }
        }
    }

    @objid ("01f42120-0000-018a-0000-000000000000")
    @Override
    public void visitMoveDependencyAction(final MoveDependencyAction theMoveDependencyAction) {
        SmObjectImpl refered = theMoveDependencyAction.getRefered();
        
        // Forget operations on deleted elements
        if (refered.isDeleted())
            return;
        
        // If the element is already in the list of created elements, it has not
        // to be added
        if (this.event.createdElements.contains(refered))
            return;
        
        // If the element already is in the list of updated elements, it has not
        // to be added
        if (this.event.updatedElements.contains(refered) == false) {
            // If the element already is in the list of created elements, it has
            // not to be added
            if (this.event.createdElements.contains(refered) == false)
                this.event.updatedElements.add(refered);
        }
    }

    @objid ("01f42120-0000-2182-0000-000000000000")
    private static boolean isComponentDependency(SmDependency dep) {
        return dep.isComponent(); // TODO fix it
    }

}
