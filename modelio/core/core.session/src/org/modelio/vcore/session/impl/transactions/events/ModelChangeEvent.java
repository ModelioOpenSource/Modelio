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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IElementDeletedEvent;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("00d00074-0000-085f-0000-000000000000")
class ModelChangeEvent implements IModelChangeEvent {
    @objid ("c7a56091-7730-4098-9c32-60ebe23d34cb")
    ChangeCause cause;

    @objid ("003cb1b2-ca41-1f3c-aafd-001ec947cd2a")
    Set<MObject> createdElements = new HashSet<>();

    @objid ("001e8390-47fe-1f3d-aafd-001ec947cd2a")
    private List<IElementDeletedEvent> deleteEvents = null;

    @objid ("00357c3a-ca6e-1f3c-aafd-001ec947cd2a")
    Map<MObject, MObject> deletedElements = new HashMap<>();

    @objid ("293a5e79-5c02-4033-b74f-bf808d892dc3")
    Set<MObject> deletedRootElements = new HashSet<>();

    @objid ("0035beca-ca6e-1f3c-aafd-001ec947cd2a")
    Map<MObject, MObject> erasedElements = new HashMap<>();

    @objid ("001e64c8-47fe-1f3d-aafd-001ec947cd2a")
    private List<IElementMovedEvent> moveEvents = null;

    @objid ("0035d3c4-ca6e-1f3c-aafd-001ec947cd2a")
    Map<MObject, MObject> movedElements = new HashMap<>();

    @objid ("003596ac-ca6e-1f3c-aafd-001ec947cd2a")
    Set<MObject> updatedElements = new HashSet<>();

    /**
     * Get the origin of this model change event
     * @return the model change event cause.
     */
    @objid ("fd08e6f2-d258-4e5a-b4f6-a068f92f49cd")
    @Override
    public ChangeCause getCause() {
        return this.cause;
    }

    /**
     * Get a resume of model object creations.
     * <p>
     * When whole composition trees are created the list only contains the root and CMS node elements .
     * @return the created elements.
     */
    @objid ("003d7bec-ca41-1f3c-aafd-001ec947cd2a")
    @Override
    public Set<MObject> getCreationEvents() {
        return this.createdElements;
    }

    /**
     * Get a resume of model object deletions.
     * <p>
     * When whole composition trees are deleted the list only contains the root and CMS node elements .
     * @return model objects deletions
     */
    @objid ("003e0eb8-ca41-1f3c-aafd-001ec947cd2a")
    @Override
    public List<IElementDeletedEvent> getDeleteEvents() {
        if (this.deleteEvents == null) {
            this.deleteEvents = new ArrayList<>(this.deletedElements.size());
            for (Entry<MObject, MObject> entry : this.deletedElements.entrySet()) {
                this.deleteEvents.add(new ElementDeletedEvent(entry.getKey(), entry.getValue()));
            }
        }
        return this.deleteEvents;
    }

    /**
     * Get all model moves.
     * @return all model moves.
     */
    @objid ("003ddea2-ca41-1f3c-aafd-001ec947cd2a")
    @Override
    public List<IElementMovedEvent> getMoveEvents() {
        if (this.moveEvents == null) {
            this.moveEvents = new ArrayList<>(this.movedElements.size());
            for (Entry<MObject, MObject> entry : this.movedElements.entrySet()) {
                this.moveEvents.add(new ElementMovedEvent(entry.getKey(), entry.getKey().getCompositionOwner(), entry
                        .getValue()));
            }
        }
        return this.moveEvents;
    }

    /**
     * Get all deleted composition root model objects.
     * <p>
     * This list contains all composition tree root that were deleted.
     * @return all deleted composition roots.
     */
    @objid ("35ae0406-b6f4-4c28-a6fd-66a0a885fd55")
    @Override
    public Set<MObject> getRootDeletionEvents() {
        return this.deletedRootElements;
    }

    /**
     * Get all modified model objects.
     * <p>
     * This list does not contain the composition owner of created elements, unless other
     * modification was made on the owner.
     * @return all modified model objects.
     */
    @objid ("003dae64-ca41-1f3c-aafd-001ec947cd2a")
    @Override
    public Set<MObject> getUpdateEvents() {
        return this.updatedElements;
    }

    /**
     * Test whether the model change event is empty.
     * <p>
     * An empty model change events does not need to be fired.
     * @return <code>true</code> if the model change event is empty.
     */
    @objid ("6e9bc8dd-aabe-4050-8784-c6e832542201")
    @Override
    public boolean isEmpty() {
        return this.updatedElements.isEmpty() && 
                        this.createdElements.isEmpty() && 
                        this.deletedElements.isEmpty() &&
                        this.movedElements.isEmpty() &&
                        this.deletedRootElements.isEmpty();
    }

    @objid ("0020d7d0-47fe-1f3d-aafd-001ec947cd2a")
    static class ElementDeletedEvent implements IElementDeletedEvent {
        @objid ("0020e900-47fe-1f3d-aafd-001ec947cd2a")
        private MObject moved;

        @objid ("0020f026-47fe-1f3d-aafd-001ec947cd2a")
        private MObject oldParent;

        @objid ("0020f5d0-47fe-1f3d-aafd-001ec947cd2a")
        public  ElementDeletedEvent(final MObject moved, final MObject oldParent) {
            this.moved = moved;
            this.oldParent = oldParent;
            
        }

        @objid ("002110ba-47fe-1f3d-aafd-001ec947cd2a")
        @Override
        public MObject getDeletedElement() {
            return this.moved;
        }

        @objid ("00213324-47fe-1f3d-aafd-001ec947cd2a")
        @Override
        public MObject getOldParent() {
            return this.oldParent;
        }

    }

    @objid ("001f2dcc-47fe-1f3d-aafd-001ec947cd2a")
    static class ElementMovedEvent implements IElementMovedEvent {
        @objid ("001fb8e6-47fe-1f3d-aafd-001ec947cd2a")
        private MObject moved;

        @objid ("001fc1e2-47fe-1f3d-aafd-001ec947cd2a")
        private MObject newParent;

        @objid ("001fc868-47fe-1f3d-aafd-001ec947cd2a")
        private MObject oldParent;

        @objid ("001fcf98-47fe-1f3d-aafd-001ec947cd2a")
        public  ElementMovedEvent(final MObject mObject, final MObject mObject2, final MObject mObject3) {
            this.moved = mObject;
            this.newParent = mObject2;
            this.oldParent = mObject3;
            
        }

        @objid ("00206dea-47fe-1f3d-aafd-001ec947cd2a")
        @Override
        public MObject getMovedElement() {
            return this.moved;
        }

        @objid ("002091da-47fe-1f3d-aafd-001ec947cd2a")
        @Override
        public MObject getNewParent() {
            return this.newParent;
        }

        @objid ("0020b480-47fe-1f3d-aafd-001ec947cd2a")
        @Override
        public MObject getOldParent() {
            return this.oldParent;
        }

    }

}
