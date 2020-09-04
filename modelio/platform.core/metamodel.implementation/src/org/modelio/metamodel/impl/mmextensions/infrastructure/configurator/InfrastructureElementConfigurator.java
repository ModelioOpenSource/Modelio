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

package org.modelio.metamodel.impl.mmextensions.infrastructure.configurator;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementConfigurator}.
 */
@objid ("9d3d15e2-4aa6-4b1b-9f60-d2d37a23b605")
public class InfrastructureElementConfigurator implements IElementConfigurator {
    /**
     * Property name for {@link #configure(IModelFactory, MObject, Map)}
     * to configure activity nodes and activity parameters.
     */
    @objid ("33b2b2e1-832b-4e0f-921f-3ba059358624")
    public static final Object COMPLETE = "complete";

    @objid ("abd9c0c5-a05d-49af-a6c7-da6eb9978eff")
    @Override
    public void configure(MObject element, Map<String, Object> properties) {
        /*ICoreSession session = CoreSession.getSession(element);
        ElementConfiguratorVisitor visitor = new ElementConfiguratorVisitor(
                ModelFactory.getFactory(session),
                session.getModel(),
                properties);
        
        element.accept(visitor);*/
    }

}
