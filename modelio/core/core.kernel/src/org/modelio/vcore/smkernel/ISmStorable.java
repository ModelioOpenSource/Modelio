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

/**
 * This interface defines the methods that SmObjectImpl must implement in order
 * to deal the storage.
 * 
 * @author phv
 */
@objid ("007d91be-8893-1f4f-9c13-001ec947cd2a")
public interface ISmStorable {
    /**
     * Set the repository handler.
     * @param storageHandle the repository handler.
     */
    @objid ("006196e4-b2b0-1f4f-9c13-001ec947cd2a")
    void setRepositoryObject(final IRepositoryObject storageHandle);

    /**
     * @return the repository handler.
     */
    @objid ("0061a62a-b2b0-1f4f-9c13-001ec947cd2a")
    IRepositoryObject getRepositoryObject();

}
