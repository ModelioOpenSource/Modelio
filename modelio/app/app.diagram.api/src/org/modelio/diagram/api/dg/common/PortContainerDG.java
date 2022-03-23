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
package org.modelio.diagram.api.dg.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class is a helper class to implement DiagramNode based on a PortContainer. Its role is to hide the PortContainer
 * satellite/primary figure details.<br>
 * As most nodes supporting pins or ports are based on a PortContainer, their DiagramNode specialized class can
 * therefore be easily derived from this class.
 */
@objid ("ad7748eb-de3e-4ae0-a799-55387547e181")
public abstract class PortContainerDG extends DiagramNode {
    /**
     * Creates a PortContainerDG.
     * @param diagramHandle The diagram manipulation class.
     * @param gmNode The gm node represented by this class.
     */
    @objid ("5812cc18-3b98-45fa-9392-c30a15ae9f48")
    public  PortContainerDG(DiagramHandle diagramHandle, final GmNodeModel gmNode) {
        super(diagramHandle, gmNode);
    }

    @objid ("30924931-7ae4-4a60-94cd-39f3927d3d04")
    @Override
    public final List<IDiagramNode> getNodes() {
        ArrayList<IDiagramNode> nodes = new ArrayList<>();
        
        List<IDiagramNode> children = this.getPrimaryChildrenNodes();
        if (children != null) {
            nodes.addAll(children);
        }
        
        GmPortContainer pc = (GmPortContainer) this.gmNode;
        for (GmNodeModel gm : pc.getVisibleChildren()) {
            if (pc.isPort(gm) || pc.isSatellite(gm)) {
                IDiagramNode diagramNode = DGFactory.getInstance().getDiagramNode(this.diagramHandle, gm);
                if (diagramNode != null) {
                    nodes.add(diagramNode);
                }
            }
        }
        return nodes;
    }

    @objid ("b4f5227c-c958-4659-97d9-fca9498c53a9")
    @Override
    public List<IDiagramLink> getFromLinks() {
        GmNodeModel mainNode = getGmNode().getMainNode();
        List<IDiagramLink> links = new ArrayList<>();
        for (IGmLink gmLink : mainNode.getStartingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("b492f0ad-d9ae-4fb8-9b0a-f775d1c195c1")
    @Override
    public List<IDiagramLink> getToLinks() {
        GmNodeModel mainNode = getGmNode().getMainNode();
        List<IDiagramLink> links = new ArrayList<>();
        for (IGmLink gmLink : mainNode.getEndingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("63cbb9e7-1f50-4aca-bba6-88d25895595e")
    protected final GmNodeModel getPrimaryNode() {
        return getGmNode().getMainNode();
    }

    /**
     * Get the diagram nodes of other model elements represented in this diagram node.
     * <p>
     * E.g for a model class, returns the represented attributes, operations, instances
     * and inner classes.
     * @return the represented elements diagram nodes in this node.
     */
    @objid ("c928f5d8-5493-4f17-9df3-aac9cad80df6")
    protected abstract List<IDiagramNode> getPrimaryChildrenNodes();

    @objid ("3042f9d0-efb6-4567-a59f-abda28651d58")
    @Override
    protected Dimension getMinimumSize() {
        // For PortContainers, we must take the main node minimum size to avoid side effects during the setSize
        GmPortContainer gpc = (GmPortContainer)this.gmNode;
        
        GmNodeModel mainNode = gpc.getMainNode();
        if (mainNode != null) {
            GraphicalEditPart mainNodeEditPart = this.diagramHandle.getEditPart(mainNode);
        
            if (mainNodeEditPart != null) {
                IFigure mainFig = mainNodeEditPart.getFigure();
                return mainFig.getMinimumSize();
            }
        }
        // Default behavior, should not happen...
        return super.getMinimumSize();
    }

    @objid ("891af5ff-ec08-4ff2-941b-4dbea3ff5616")
    @Override
    public Rectangle getOverallBounds() {
        final GraphicalEditPart p = this.diagramHandle.getEditPart(getModel());
        if (p == null) {
            return null;
        }
        IFigure figure = p.getFigure();
        Rectangle bounds = figure.getBounds().getCopy();
        figure.translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * Get the satellite nodes.
     * @return the satellite nodes.
     */
    @objid ("23b3b18b-9c0f-48ba-a5e6-a04911b9de02")
    protected Collection<IDiagramNode> getSatelliteNodes() {
        GmPortContainer portCont = getGmNode();
        ArrayList<IDiagramNode> ret = new ArrayList<>();
        for (GmNodeModel c : portCont.getVisibleChildren()) {
            if (portCont.isSatellite(c)) {
                ret.add(DGFactory.getInstance().getDiagramNode(this.diagramHandle, c));
            }
        }
        return ret;
    }

    /**
     * Get the child nodes layouted as port.
     * @return the port nodes.
     */
    @objid ("50573743-95c2-4c14-8051-6de0609b49dd")
    protected Collection<IDiagramNode> getPortNodes() {
        GmPortContainer portCont = getGmNode();
        ArrayList<IDiagramNode> ret = new ArrayList<>();
        for (GmNodeModel c : portCont.getVisibleChildren()) {
            if (portCont.isPort(c)) {
                ret.add(DGFactory.getInstance().getDiagramNode(this.diagramHandle, c));
            }
        }
        return ret;
    }

    @objid ("c5502d04-5e32-4698-a227-7b2da7006bc5")
    protected IDiagramNode getMainSatelliteLabel() {
        GmPortContainer portCont = getGmNode();
        for (GmNodeModel c : portCont.getVisibleChildren()) {
            if (portCont.isMainSatelliteLabel(c)) {
                return DGFactory.getInstance().getDiagramNode(this.diagramHandle, c);
            }
        }
        return null;
    }

    @objid ("4ec63182-3961-49aa-a66b-54327af59a49")
    private GmPortContainer getGmNode() {
        return (GmPortContainer) this.gmNode;
    }

    @objid ("9fa72d6b-311a-402d-9496-205ee9a780e9")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        switch (role) {
        case INNER:
            return getPrimaryChildrenNodes();
        case LABEL:
            IDiagramNode node = getMainSatelliteLabel();
            return node != null ? Collections.singletonList(node) : Collections.emptyList();
        case PORT:
            return getPortNodes();
        case SATELLITE:
            return getSatelliteNodes();
        default:
            throw new IllegalArgumentException(String.valueOf(role));
        }
        
    }

}
