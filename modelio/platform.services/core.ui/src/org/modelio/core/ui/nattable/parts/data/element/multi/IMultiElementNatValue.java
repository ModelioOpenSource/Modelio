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

package org.modelio.core.ui.nattable.parts.data.element.multi;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.IMultiNatValue;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Defines an {@link INatValue} wrapping a collection of {@link MObject} instances.
 */
@objid ("24374717-c391-4717-82d6-b6a46c762490")
public interface IMultiElementNatValue extends IMultiNatValue {
    /**
     * Get the list of all accepted (and proposed) metaclasses for this cell.
     * Add metaclasses to this list to complete it.
     * 
     * @return the current allowed metaclasses.
     */
    @objid ("9433555e-6255-4ca5-9745-3f9b208026f5")
    Collection<Class<? extends MObject>> getAllowedClasses();

    /**
     * Get the filter that will be used to accept (and propose) elements for this cell.
     * 
     * @return the current filter. Might be <code>null</code>.
     */
    @objid ("1d7e18f8-84b8-4fb7-a19b-ed17e817186f")
    IMObjectFilter getElementFilter();

    @objid ("5d4e9233-4bff-4877-bb20-f9be42cb8781")
    @Override
    Collection<MObject> getValue();

    /**
     * Set a filter that will be used to accept (and propose) elements for this cell.
     * 
     * @param elementFilter the new filter. Might be <code>null</code>.
     */
    @objid ("79c2e589-f852-4316-89b9-513d6642f430")
    void setElementFilter(IMObjectFilter elementFilter);

}
