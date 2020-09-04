/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vcore.model.impl;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementConfiguratorService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;

/**
 * Implementation of {@link IElementConfigurator}.
 * <p>
 * Delegates to the matching metamodel service.
 */
@objid ("dc130502-e9e1-41d8-9e62-ee45aae6a134")
public class ElementConfigurator implements IElementConfiguratorService {
    @objid ("c6620633-bb10-49c2-9922-edcdfbc02db4")
    private final MetamodelExtensionPoint<IElementConfigurator> metamodelExtensionPoint;

    @objid ("a3750744-301a-442f-ba16-fbd45ca5fc67")
    public ElementConfigurator() {
        this.metamodelExtensionPoint = new MetamodelExtensionPoint<>();
    }

    @objid ("5a68a67f-18fc-44e5-b55d-961827a29617")
    @Override
    public void configure(MObject element, Map<String, Object> properties) {
        IElementConfigurator service = this.metamodelExtensionPoint.getService(element.getMClass().getOrigin());
        if (service != null) {
            service.configure(element, properties);
        }
    }

    @objid ("0dfc1ab2-1673-4227-b9df-e19e831930e9")
    @Override
    public MetamodelExtensionPoint<IElementConfigurator> getMetamodelExtensionPoint() {
        return this.metamodelExtensionPoint;
    }

}
