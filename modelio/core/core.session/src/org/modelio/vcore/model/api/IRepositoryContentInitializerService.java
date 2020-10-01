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

package org.modelio.vcore.model.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;

@objid ("c3710c5c-759f-4da8-b34f-35fd2c38007b")
public interface IRepositoryContentInitializerService extends IRepositoryContentInitializer {
    /**
     * Get the metamodel extensions point.
     * <p>
     * Use it to register metamodel extensions to this service.
     * 
     * @return the metamodel extensions point.
     */
    @objid ("6b480d6f-4f85-4124-9396-922946d3c60b")
    MetamodelExtensionPoint<IRepositoryContentInitializer> getMetamodelExtensionPoint();

}
