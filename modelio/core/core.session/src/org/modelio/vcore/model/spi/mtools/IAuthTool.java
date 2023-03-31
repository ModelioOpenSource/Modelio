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
package org.modelio.vcore.model.spi.mtools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Helper interface to test whether some model operations are allowed.
 */
@objid ("0080cdfc-d2e8-1097-bcec-001ec947cd2a")
public interface IAuthTool {
    /**
     * Tells whether an element of the given metaclass can be created inside the given parent element.
     * @param parentElement the owner element
     * @param metaclass the new element metaclass
     * @return true if a new object can be created
     */
    @objid ("0053502a-d281-1098-bcec-001ec947cd2a")
    default boolean canAdd(final MObject parentElement, final String metaclass) {
        return canAdd(parentElement, parentElement.getMClass().getMetamodel().getMClass(metaclass));
    }

    /**
     * Tells whether an element of the given metaclass can be created inside the given parent element.
     * @param parentElement the owner element
     * @param metaclass the new element metaclass
     * @return true if a new object can be created
     * @since Modelio 3.4
     */
    @objid ("ef3eb4fd-febd-4514-8f2a-240bc6c98d4b")
    boolean canAdd(final MObject parentElement, final MClass metaclass);

    /**
     * Tells whether an element of the given metaclass can be created inside the given parent element.
     * @param parentElement the owner element
     * @param metaclass the new element metaclass java interface
     * @return true if a new object can be created
     * @since 3.8
     */
    @objid ("d95f989f-fca6-4256-a24f-73c4a1267f0c")
    default boolean canAdd(final MObject parentElement, final Class<? extends MObject> metaclass) {
        return canAdd(parentElement, parentElement.getMClass().getMetamodel().getMClass(metaclass));
    }

    @objid ("f1865eab-2984-11e2-8460-002564c97630")
    boolean canAddTo(MObject child, MObject parent);

    @objid ("00539ca6-d281-1098-bcec-001ec947cd2a")
    boolean canCreateLink(Class<? extends MObject> toCreate, MObject srcElement, MObject targetEl);

    @objid ("0053c960-d281-1098-bcec-001ec947cd2a")
    boolean canCreateLinkFrom(Class<? extends MObject> toCreate, MObject srcElement);

    @objid ("00537dfc-d281-1098-bcec-001ec947cd2a")
    boolean canModify(final MObject el);

    @objid ("f1865eb0-2984-11e2-8460-002564c97630")
    boolean canRemoveFrom(MObject child, MObject parent);
}

