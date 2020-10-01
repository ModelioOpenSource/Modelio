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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.AbstractMetaclassException;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Factory found on the {@link SmClass} used to create {@link ISmObjectData} and {@link SmObjectImpl}.
 */
@objid ("0043e8a6-fd1a-1f27-a7da-001ec947cd2a")
public interface ISmObjectFactory {
    /**
     * Create the data object
     * 
     * @return a data object
     */
    @objid ("0048e95a-fd1a-1f27-a7da-001ec947cd2a")
    ISmObjectData createData() throws AbstractMetaclassException;

    /**
     * Create the implementation object.
     * 
     * @return the implementation object.
     */
    @objid ("0048f2ce-fd1a-1f27-a7da-001ec947cd2a")
    SmObjectImpl createImpl() throws AbstractMetaclassException;

}
