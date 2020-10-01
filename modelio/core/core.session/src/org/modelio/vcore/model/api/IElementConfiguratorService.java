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
 * Service to configure newly created (or not) model elements
 * depending on their initialized state and configuration properties.
 * <p>
 * For example, can create analyst property tables for analyst elements.
 */
@objid ("45683a8b-7e9f-472f-b44a-c9c4e561ff2f")
public interface IElementConfiguratorService extends IElementConfigurator {
    /**
     * Get the metamodel extensions point.
     * <p>
     * Use it to register metamodel extensions to this service.
     * 
     * @return the metamodel extensions point.
     */
    @objid ("564eac89-c172-46c9-9a6c-7f13df106278")
    MetamodelExtensionPoint<IElementConfigurator> getMetamodelExtensionPoint();

}
