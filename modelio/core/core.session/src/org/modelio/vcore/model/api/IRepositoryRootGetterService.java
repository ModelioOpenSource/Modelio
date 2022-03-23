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

/**
 * Service that get composition roots of a repository.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("22b5a636-b2dc-43e8-9d18-d23a50b76332")
public interface IRepositoryRootGetterService extends IRepositoryRootGetter {
    /**
     * Get the metamodel extensions point.
     * <p>
     * Use it to register metamodel extensions to this service.
     * @return the metamodel extensions point.
     */
    @objid ("1d41e838-3cfc-44c9-9782-d40ae9154157")
    MetamodelExtensionPoint<IRepositoryRootGetter> getMetamodelExtensionPoint();

}
