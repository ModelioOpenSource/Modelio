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

package org.modelio.vstore.jdbm.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;

@objid ("9d22b800-cbfd-467b-905b-89a5fd82f362")
public interface ILoadHelper {
    @objid ("40608254-95af-4c35-9dd5-7f58e0174257")
    SmObjectImpl getLocalRef(SmClass cls, String uuid, IModelLoader loader) throws DuplicateObjectException;

    @objid ("6cf63e09-6ddf-4587-a553-49ab66156f56")
    SmClass getClass(String clsid);

}
