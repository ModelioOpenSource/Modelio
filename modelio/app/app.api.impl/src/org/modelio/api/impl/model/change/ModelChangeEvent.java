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
package org.modelio.api.impl.model.change;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IElementDeletedEvent;
import org.modelio.api.modelio.model.event.IElementMovedEvent;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class is used to access the list of changes that occured in a transaction.
 * <p><p>
 * When a transaction is commited, the list of model changes is sent to the
 * class that have subscribed to the modeling session as model listeners through the use
 * of the following method : {@link IModelingSession#addListener(IModelChangeListener)}.
 * <p><p>
 * When the model is changed, the session warn all the listeners of the difference between the
 * old model and the new one. The list of changes is the following:<p>
 * <ul>
 * <li>Created elements</li>
 * <li>Deleted elements</li>
 * <li>Updated elements</li>
 * <li>Moved elements</li>
 * </ul>
 * <p><p>
 * Any element localized in the created element list or deleted element list cannot be get using
 * another list (updated or moved element list).
 */
@objid ("147fb361-9a6d-46bd-b8ec-a0e6b5282de9")
public class ModelChangeEvent implements IModelChangeEvent {
    @objid ("88dbd1c2-4aff-4a5e-b918-e443ced690f6")
    private org.modelio.vcore.session.api.model.change.IModelChangeEvent coreModelChangeEvent;

    /**
     * @exclude
     */
    @objid ("24d81aa0-a625-4f4e-8f7b-21b989e1a1d8")
    public  ModelChangeEvent(org.modelio.vcore.session.api.model.change.IModelChangeEvent coreModelChangeEvent) {
        this.coreModelChangeEvent = coreModelChangeEvent;
    }

    /**
     * Used to get the list of created elements.
     * <p><p>
     * Only the top created elements are listed here. For example if a package P1 has been created
     * and in the same way, a class C1 has been created and added to the package, only the package P1
     * will be given through the use of this returned list.
     * @return The list of created elements
     */
    @objid ("f787607f-5d38-45b4-b83d-2462e759047d")
    @Override
    public Set<MObject> getCreationEvents() {
        return this.coreModelChangeEvent.getCreationEvents();
    }

    /**
     * Used to get the list of updated elements.
     * <p><p>
     * An element is considered as updated only if it was not created or deleted during
     * the transaction.
     * @return The updated elements
     */
    @objid ("c0bca7e6-03af-43e3-a033-af48d9d67f1e")
    @Override
    public Set<MObject> getUpdateEvents() {
        return this.coreModelChangeEvent.getUpdateEvents();
    }

    /**
     * Used to get the list of moved elements.
     * <p><p>
     * An element is considered as moved only if it was not created or deleted during
     * the transaction.
     * <p><p>
     * The {@link ModelMoveEvent} class is used to determine more precise information.
     * @return A list of move events
     */
    @objid ("e2384ef3-5e44-4375-a483-49609889bbaf")
    @Override
    public List<IElementMovedEvent> getMoveEvents() {
        List<IElementMovedEvent> elementMovedEvents = new ArrayList<>();
        
        for (org.modelio.vcore.session.api.model.change.IElementMovedEvent coreElementMovedEvent : this.coreModelChangeEvent.getMoveEvents()) {
            elementMovedEvents.add(new ModelMoveEvent(coreElementMovedEvent));
        }
        return elementMovedEvents;
    }

    /**
     * Used to get the list of deleted elements.
     * <p><p>
     * Only the top deleted elements are listed here. For example if a package P1, which contained a
     * class C1, has been deleted, only the package P1
     * will be given through the use of this returned list.
     * @return The list of deleted elements
     */
    @objid ("be5de62b-f578-4caa-8932-73da72a927d9")
    @Override
    public List<IElementDeletedEvent> getDeleteEvents() {
        List<IElementDeletedEvent> elementDeletedEvents = new ArrayList<>();
        
        for (org.modelio.vcore.session.api.model.change.IElementDeletedEvent coreElementDeletedEvents : this.coreModelChangeEvent.getDeleteEvents()) {
            elementDeletedEvents.add(new ModelDeleteEvent(coreElementDeletedEvents));
        }
        return elementDeletedEvents;
    }

}
