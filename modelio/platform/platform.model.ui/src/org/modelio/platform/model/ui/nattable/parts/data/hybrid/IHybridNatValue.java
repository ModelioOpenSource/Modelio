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
package org.modelio.platform.model.ui.nattable.parts.data.hybrid;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.ISingleNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Defines an {@link INatValue} wrapping a single {@link MObject} or {@link String} instance.
 */
@objid ("1cc8ec50-f208-4998-ae31-c2b5ccbe4881")
public interface IHybridNatValue extends ISingleNatValue {
    /**
     * @return whether or not a {@link String} instance is a valid value to be set.
     */
    @objid ("1fe7200c-aa68-4574-bcdb-196739219cb3")
    boolean acceptStringValue();

    /**
     * Get the list of all accepted (and proposed) metaclasses for this cell.
     * Add metaclasses to this list to complete it.
     * @return the current allowed metaclasses.
     */
    @objid ("2ab62522-3acb-46b6-9418-0b80a7c62f6e")
    Collection<Class<? extends MObject>> getAllowedClasses();

    /**
     * Get the filter that will be used to accept (and propose) elements for this cell.
     * @return the current filter. Might be <code>null</code>.
     */
    @objid ("38db3ee9-da5e-4b72-b3c6-facd29767b7f")
    IMObjectFilter getElementFilter();

    /**
     * Set a filter that will be used to accept (and propose) elements for this cell.
     * @param elementFilter the new filter. Might be <code>null</code>.
     */
    @objid ("190df862-21bf-44eb-b004-b727df26b137")
    void setElementFilter(IMObjectFilter elementFilter);
}

