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

package org.modelio.uml.statikdiagram.editor.elements.association;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodeOnLinkEditPolicy;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.uml.statikdiagram.editor.elements.assocqualifier.GmQualifierGroup;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Allow creation of information flow on the link.
 * <p>
 * Defers the creation to the nearest {@link GmQualifierGroup}.
 * 
 * @author cmarin
 */
@objid ("33e8e22c-55b7-11e2-877f-002564c97630")
public class CreateQualifierEditPolicy extends DeferringCreateNodeOnLinkEditPolicy {
    /**
     * Redefined to return the {@link GmQualifierGroup} for the nearest association role from the mouse.
     * 
     * @param gmLink The association model
     * @param location The mouse location
     * @return The nearest {@link GmQualifierGroup} from the mouse.
     */
    @objid ("33e8e230-55b7-11e2-877f-002564c97630")
    @Override
    protected GmCompositeNode getExtensionFor(final GmLink gmLink, final Class<? extends MObject> metaclass, final Point location) {
        // The element must be an attribute
        if (!Attribute.class.isAssignableFrom(metaclass))
            return null;
        
        Connection fig = (Connection) getHostFigure();
        GmAssociation gmAssoc = (GmAssociation) gmLink;
        
        PointList points = fig.getPoints();
        Point begin = points.getFirstPoint();
        Point last = points.getLastPoint();
        
        int d1 = Math.abs(begin.getDistance2(location));
        int d2 = Math.abs(last.getDistance2(location));
        
        AssociationEnd role;
        if (d1 <= d2) {
            // source side
            role = gmAssoc.getRepresentedRole();
        } else {
            // target side
            role = gmAssoc.getOppositeRole();
        }
        
        GmCompositeNode gmTargetChild = null;
        for (GmNodeModel n : gmLink.getExtensions()) {
            if (n instanceof GmQualifierGroup && n.getRelatedElement().equals(role)) {
                gmTargetChild = (GmCompositeNode) n;
                break;
            }
        }
        return gmTargetChild;
    }

}
