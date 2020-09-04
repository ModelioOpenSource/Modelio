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

package org.modelio.vcore.session.api.model.change;

import java.util.Collection;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Status change events
 */
@objid ("0037fbf4-d48e-1f3b-aafd-001ec947cd2a")
public interface IStatusChangeEvent {
    /**
     * Test whether the status change event is empty.
     * <p>
     * An empty status change event does not need to be fired.
     * @return <code>true</code> if the status change event is empty.
     */
    @objid ("3b8e46bc-9a87-4db3-ac49-3fe516e8b650")
    boolean isEmpty();

    /**
     * Get the element whose access rights changed.
     * @return element whose access rights changed.
     */
    @objid ("9efb3445-9228-43b8-a6af-b83d653931e6")
    Collection<SmObjectImpl> getAccessChanged();

    /**
     * @return elements whose CMS state changed.
     */
    @objid ("91b34534-a81b-49da-9cb6-e4e5b6a4fbb1")
    Collection<SmObjectImpl> getCmsStatusChanged();

    /**
     * @return elements whose audit state changed.
     */
    @objid ("bd6966ba-f22c-44e4-a2a8-e08cddd0d8cc")
    Collection<SmObjectImpl> getAuditStatusChanged();

    /**
     * @return elements whose shell state changed.
     */
    @objid ("397edf46-3a25-424f-951b-f03293680844")
    Collection<SmObjectImpl> getShellStateChanged();

    /**
     * Get the elements whose status changed.
     * <p>
     * Returns a map with as key the changed element and as value the old status.
     * @return the changed elements map.
     */
    @objid ("dec1336f-da18-41dc-a2d3-5b538d676e98")
    Map<SmObjectImpl, Long> getStatusChanged();

    /**
     * Get the origin of this status change event
     * @return the status change event cause.
     */
    @objid ("15129f5d-ba62-4b0c-85c6-e3f046b9a9df")
    ChangeCause getCause();

}
