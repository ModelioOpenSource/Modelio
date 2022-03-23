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
package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices.IRouterDependentEditPolicyFactory;
import org.modelio.diagram.elements.core.link.ortho.OrthoBendpointEditPolicy;
import org.modelio.diagram.elements.core.link.rake.RakeLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultBendpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultConnectionEndpointEditPolicy;

/**
 * Default implementation of {@link IRouterDependentEditPolicyFactory}.
 * <p>
 * May be subclassed.
 * 
 * @since 5.0.2
 */
@objid ("9c54ce38-cf8c-46b0-ae08-4bd2e3eac6be")
public class SelectionHandlesEditPolicyFactory implements IRouterDependentEditPolicyFactory {
    @objid ("ee89fa1a-1606-415f-8749-8e1b962abb68")
    @Override
    public EditPolicy createBendPointsPolicy(RoutingMode mode) {
        if (mode.rake) {
            return new RakeLinkEditPolicy();
        } else {
            switch (mode.routingStyle) {
            case DIRECT:
                return null;
            case BENDPOINT:
                return new DefaultBendpointEditPolicy();
            case ORTHOGONAL:
                return new OrthoBendpointEditPolicy();
            default:
                throw new IllegalStateException(mode + " routing mode not supported");
            }
        }
        
    }

    @objid ("2c578d82-5e8a-4a5e-8562-be73555b9277")
    @Override
    public EditPolicy createEndPointsPolicy(RoutingMode mode) {
        return new DefaultConnectionEndpointEditPolicy();
    }

}
