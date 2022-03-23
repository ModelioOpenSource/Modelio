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
 * Service that gives a default name to a model element.
 * 
 * @author phv
 */
@objid ("e4773a52-8c23-4b37-9cb3-d8a482eb7ba9")
public interface IElementNamerService extends IElementNamer {
    /**
     * Get the metamodel extensions point.
     * <p>
     * Use it to register metamodel extensions to this service.
     * @return the metamodel extensions point.
     */
    @objid ("a959e243-6474-40f2-b062-535ab878e5c0")
    MetamodelExtensionPoint<IElementNamer> getMetamodelExtensionPoint();

}
