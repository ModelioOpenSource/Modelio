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
package org.modelio.platform.model.ui.nattable.parts.data.element.multirow;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.ISingleNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Defines an {@link INatValue} wrapping a single {@link MObject} instance among a list of {@link MObject}.
 * <p>
 * At edition, the complete value list is modified instead of the single element only.
 * </p>
 */
@objid ("30eb81a2-55ad-4588-a7a1-0fd2ea42d568")
public interface IMultiRowElementNatValue extends ISingleNatValue {
    /**
     * Get the list of all accepted (and proposed) metaclasses for this cell.
     * Add metaclasses to this list to complete it.
     * @return the current allowed metaclasses.
     */
    @objid ("a5bb2566-01c3-4f8f-8f48-00b97725dd30")
    Collection<Class<? extends MObject>> getAllowedClasses();

    /**
     * Get the filter that will be used to accept (and propose) elements for this cell.
     * @return the current filter. Might be <code>null</code>.
     */
    @objid ("23b879cb-efc3-43be-9a8a-ab9969c76794")
    IMObjectFilter getElementFilter();

    /**
     * Get the "current" element to display. Must be part of the {@link #getAllValues()} list.
     */
    @objid ("0c21173f-76ca-40d9-ad05-9759b68f13e0")
    @Override
    MObject getValue();

    /**
     * Set a filter that will be used to accept (and propose) elements for this cell.
     * @param elementFilter the new filter. Might be <code>null</code>.
     */
    @objid ("6968dd64-c1c0-43f2-bd22-89d4c388379d")
    void setElementFilter(IMObjectFilter elementFilter);

    /**
     * Get a list of elements {@link #getValue()} belongs to.
     */
    @objid ("cfa43563-10cd-46df-a11b-4f769ca5841b")
    List<MObject> getAllValues();

    /**
     * @return a complementary suffix to be added at NatTable tag computing.
     */
    @objid ("22b75262-f0b8-4bdf-acb8-8889d7d099ad")
    String getTagSuffix();

}
