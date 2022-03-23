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
package org.modelio.platform.model.ui.nattable.parts.data.element.single;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.ISingleNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Defines an {@link INatValue} wrapping a single {@link MObject} instance.
 */
@objid ("eea0b76d-3dec-47c7-8b0c-9abe1dc30f82")
public interface IElementNatValue extends ISingleNatValue {
    /**
     * Get the list of all accepted (and proposed) metaclasses for this cell.
     * Add metaclasses to this list to complete it.
     * @return the current allowed metaclasses.
     */
    @objid ("2707d374-160a-4594-85af-85733df07d3e")
    Collection<Class<? extends MObject>> getAllowedClasses();

    /**
     * Get the filter that will be used to accept (and propose) elements for this cell.
     * @return the current filter. Might be <code>null</code>.
     */
    @objid ("ae893f52-e99f-4133-9b75-baec3a67d5e6")
    IMObjectFilter getElementFilter();

    @objid ("4fd0a974-392a-49ab-a4de-009e423d3138")
    @Override
    MObject getValue();

    /**
     * Set a filter that will be used to accept (and propose) elements for this cell.
     * @param elementFilter the new filter. Might be <code>null</code>.
     */
    @objid ("3aa2bf4f-352d-4b41-9b11-010561ec3e2c")
    void setElementFilter(IMObjectFilter elementFilter);

}
