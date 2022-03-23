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
import org.modelio.diagram.elements.core.link.ortho.AutoOrthoBendpointEditPolicy;
import org.modelio.diagram.elements.core.link.ortho.AutoOrthoConnEndPointEditPolicy;
import org.modelio.diagram.elements.core.link.rake.RakeLinkEditPolicy;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * New orthogonal router edit policies factory.
 * 
 * @since 5.0.2
 */
@objid ("c29866fd-c0ac-4954-a318-7ee560459a3a")
public class AutoOrthogonalRouterSelectionHandlesEditPolicyFactory extends SelectionHandlesEditPolicyFactory {
    @objid ("97f040ae-ef23-4379-865a-34002fe2acc3")
    @Override
    public EditPolicy createBendPointsPolicy(RoutingMode mode) {
        if (mode.rake) {
            return new RakeLinkEditPolicy();
        } else {
            switch (mode.routingStyle) {
            case ORTHOGONAL:
                return new AutoOrthoBendpointEditPolicy();
            case DIRECT:
            case BENDPOINT:
            default:
                return super.createBendPointsPolicy(mode);
            }
        }
        
    }

    @objid ("cb9aa869-8a52-46ca-8ee6-df0a26351402")
    @Override
    public EditPolicy createEndPointsPolicy(RoutingMode mode) {
        if (mode.routingStyle == ConnectionRouterId.ORTHOGONAL && !mode.rake) {
            return new AutoOrthoConnEndPointEditPolicy();
        }
        return super.createEndPointsPolicy(mode);
    }

}
