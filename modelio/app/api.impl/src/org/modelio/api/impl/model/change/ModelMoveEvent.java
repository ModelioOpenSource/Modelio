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

package org.modelio.api.impl.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.event.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This event is used to define a model move.
 * <p><p>
 * A moved element is defined by the following information:
 * <ul>
 * <li>The moved element ({@link #getMovedElement()})</li>
 * <li>The old parent element ({@link #getOldParent()})</li>
 * <li>The new parent element ({@link #getNewParent()})</li>
 * </ul>
 * <p><p>
 */
@objid ("5dae9808-23c5-475f-b3be-71acb7c2b354")
public class ModelMoveEvent implements IElementMovedEvent {
    @objid ("869077c3-39b4-4cb0-8203-dcd3f08398c0")
    private org.modelio.vcore.session.api.model.change.IElementMovedEvent coreElementMovedEvent;

    /**
     * @exclude
     */
    @objid ("c610b986-cb0a-48cb-9cb7-52556329abae")
    public ModelMoveEvent(org.modelio.vcore.session.api.model.change.IElementMovedEvent coreElementMovedEvent) {
        this.coreElementMovedEvent = coreElementMovedEvent;
    }

    /**
     * @exclude
     */
    @objid ("a225ead1-0302-4400-80f2-7c3ec5e977a9")
    @Override
    public String toString() {
        return this.coreElementMovedEvent.toString();
    }

    /**
     * Used to get the moved element.
     * @return Returns the moved element
     */
    @objid ("885f46c6-27ee-40a7-ad75-e2eaf872da63")
    @Override
    public MObject getMovedElement() {
        return this.coreElementMovedEvent.getMovedElement();
    }

    /**
     * Used to get the new moved element parent.
     * <p><p>
     * The parent corresponds to the element accessed through the use of the
     * {@link MObject#getCompositionOwner()} method.
     * @return Returns the new parent of the moved element
     */
    @objid ("aedd91e6-4131-4840-aa86-72d9611842cd")
    @Override
    public MObject getNewParent() {
        return this.coreElementMovedEvent.getNewParent();
    }

    /**
     * Used to get the old moved element parent.
     * <p><p>
     * The parent corresponds to the element accessed through the use of the
     * {@link MObject#getCompositionOwner()} method.
     * @return Returns the old parent of the moved element
     */
    @objid ("e1f5b700-99a1-4b92-9281-6c0b20d2669a")
    @Override
    public MObject getOldParent() {
        return this.coreElementMovedEvent.getOldParent();
    }

}
