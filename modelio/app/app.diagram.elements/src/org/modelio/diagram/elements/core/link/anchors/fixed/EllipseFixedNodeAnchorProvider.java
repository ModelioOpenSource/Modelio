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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.RoutingModeGetter;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

@objid ("b1737289-ad11-4020-a4fa-128f54142cbb")
public class EllipseFixedNodeAnchorProvider extends FixedNodeAnchorProvider {
    @objid ("f71f77af-7dae-43d7-b0cd-d36507854e04")
    public  EllipseFixedNodeAnchorProvider() {
        super(1,1);
    }

    @objid ("e6bcd1d0-7c2a-4999-a357-c8bdcc9341fe")
    protected ComputedEllipseState getEllipseState(IFigure aNodeFigure) {
        return (ComputedEllipseState) super.getState(aNodeFigure);
    }

    @objid ("11210683-6d4a-4592-ba77-7f047d150b43")
    @Override
    protected ComputedState createComputedState(IFigure aNodeFigure) {
        return new ComputedEllipseState();
    }

    @objid ("0dc95ba9-0b4a-4e78-96bc-edf4add1821a")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(IFigure node, ConnectionRouterId routerId, Integer face) {
        if (routerId==ConnectionRouterId.BENDPOINT || routerId==ConnectionRouterId.DIRECT)
            return Collections.singleton(getEllipseState(node).ellipseAnchor);
        else
            return super.getAllAnchors(node, routerId, face);
        
    }

    @objid ("dacf182e-80bf-4c84-acaa-3f07ef4bfd30")
    @Override
    public ConnectionAnchor getNearest(IFigure node, Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSource) {
        if (routerId==ConnectionRouterId.BENDPOINT || routerId==ConnectionRouterId.DIRECT)
            return getEllipseState(node).ellipseAnchor;
        else
            return super.getNearest(node, absPoint, routerId, face, isSource);
        
    }

    @objid ("7f105126-6d48-46cc-b239-47db6d87bab5")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        switch (RoutingModeGetter.fromEditPart(connEditpart)) {
        case BENDPOINT:
        case DIRECT:
            return getEllipseState(nodeEditPart.getFigure()).ellipseAnchor;
        case ORTHOGONAL:
        default:
            return super.getSourceConnectionAnchor(nodeEditPart, connEditpart);
        }
        
    }

    @objid ("02d0c31f-bc29-4a5b-bca9-de1e15979932")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        switch (RoutingModeGetter.fromEditPart(connEditpart)) {
        case BENDPOINT:
        case DIRECT:
            return getEllipseState(nodeEditPart.getFigure()).ellipseAnchor;
        case ORTHOGONAL:
        default:
            return super.getTargetConnectionAnchor(nodeEditPart, connEditpart);
        }
        
    }

    @objid ("1ac45edb-a304-4746-a741-9baacb083eb3")
    @Override
    protected ConnectionAnchor getAnchorForCreateRequest(GraphicalEditPart nodeEditPart, CreateRequest request, boolean source) throws IllegalArgumentException {
        switch (RoutingModeGetter.fromRequest(request)) {
        case BENDPOINT:
        case DIRECT:
            return getEllipseState(nodeEditPart.getFigure()).ellipseAnchor;
        case ORTHOGONAL:
        default:
            return super.getAnchorForCreateRequest(nodeEditPart, request, source);
        }
        
    }

    @objid ("5b79960d-b716-4649-8bcf-7428c7d276cd")
    @Override
    protected ConnectionAnchor getAnchorForReconnectRequest(GraphicalEditPart nodeEditPart, ReconnectRequest request, boolean source) throws IllegalArgumentException {
        switch (RoutingModeGetter.fromReconnectRequest(request)) {
        case BENDPOINT:
        case DIRECT:
            return getEllipseState(nodeEditPart.getFigure()).ellipseAnchor;
        case ORTHOGONAL:
        default:
            return super.getAnchorForReconnectRequest(nodeEditPart, request, source);
        }
        
    }

    @objid ("0bdcd91f-5a87-4276-a3b5-c2cea0eb912a")
    protected static class ComputedEllipseState extends ComputedState {
        @objid ("19e8c604-9f41-40be-afb0-36dd3525f612")
        protected EllipseAnchor ellipseAnchor;

        @objid ("bc209028-54d4-4feb-a257-34cc8dbf85da")
        @Override
        protected boolean update(IFigure newNodeFigure) {
            if (this.ellipseAnchor==null || this.ellipseAnchor.getOwner() != newNodeFigure) {
                this.ellipseAnchor = new EllipseAnchor(newNodeFigure);
            }
            return super.update(newNodeFigure);
        }

    }

}
