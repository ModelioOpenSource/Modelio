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

package org.modelio.diagram.api.services;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramElementsLayer;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class represents a DiagramGraphic of the 'node' kind.
 */
@objid ("3d9759b1-b676-4e1e-8066-194c595dd2ba")
public abstract class DiagramNode extends DiagramAbstractNode {
    @objid ("d7cd13d8-faea-4e5e-a14a-553d86a50eb7")
    protected GmNodeModel gmNode;

    /**
     * Creates a diagram node.
     * @param diagramHandle The diagram manipulation class.
     * @param gmNode The gm node represented by this class.
     */
    @objid ("0bb46c19-8466-4020-80ff-cec60109d0cc")
    public DiagramNode(DiagramHandle diagramHandle, GmNodeModel gmNode) {
        super(diagramHandle);
        this.gmNode = gmNode;
    }

    /**
     * @return the parent node.
     */
    @objid ("54307cb4-b2db-45be-bb80-ff7c000e666f")
    @Override
    public IDiagramGraphic getParent() {
        GmModel gmParent = this.gmNode.getParent();
        IDiagramGraphic parent = null;
        
        while (gmParent != null) {
            parent = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, gmParent);
            if (parent != null) {
                return parent;
            }
            gmParent = gmParent.getParent();
        }
        return null;
    }

    /**
     * Return the list of children nodes of this node.
     * @return A list of nodes in any case, possibly an empty one. Never returns null
     */
    @objid ("fb5a6042-61b0-450e-807c-3c504b6ce9bc")
    @Override
    public abstract List<IDiagramNode> getNodes();

    /**
     * Return the links that are starting (ie outgoing links) from this node.
     * @return A list of links in any case, possibly an empty one. Never returns null
     */
    @objid ("c1bcd859-bb76-4e23-aaff-3a45911c3bd0")
    @Override
    public List<IDiagramLink> getFromLinks() {
        final List<IDiagramLink> links = new ArrayList<>();
        for (final IGmLink gmLink : this.gmNode.getStartingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    /**
     * Return the links that are ending (ie incoming links) at this node.
     * @return A list of links in any case, possibly an empty one. Never returns null
     */
    @objid ("94b4c755-4b5a-4c22-89e6-9e9902878ba8")
    @Override
    public List<IDiagramLink> getToLinks() {
        final List<IDiagramLink> links = new ArrayList<>();
        for (final IGmLink gmLink : this.gmNode.getEndingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("3a546530-9f29-4f72-bfc8-4d3c495506ca")
    @Override
    public String toString() {
        Rectangle bounds = getBounds();
        if (bounds == null) {
            bounds = new Rectangle(0, 0, -1, -1);
        }
        return String.format("%s '%s' [%d,%d, %d,%d]",
                                             this.getClass().getSimpleName(),
                                             getName(),
                                             bounds.x,
                                             bounds.y,
                                             bounds.width,
                                             bounds.height);
    }

    /**
     * Return the name of this node. In most cases the name is the displayed label but this can however vary for
     * different nodes. No uniqueness of names across a diagram should be assumed.
     * @return the node name
     */
    @objid ("db4ec55a-b6a2-4bec-a0e5-43dc7c46500c")
    @Override
    public String getName() {
        return this.gmNode.getRelatedElement() != null ? this.gmNode.getRelatedElement().getName()
                                : this.gmNode.getGhostLabel();
    }

    @objid ("a3d4abae-52f1-4175-9a98-e0ebd2956e93")
    @Override
    public MObject getElement() {
        return this.gmNode.getRelatedElement();
    }

    @objid ("723741ab-451d-4d32-a4fe-6af2c1eeaef9")
    @Override
    public void mask() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(this.gmNode);
        
        final GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
        deleteReq.setEditParts(editPart);
        
        final Command cmd = editPart.getCommand(deleteReq);
        if (cmd != null && cmd.canExecute()) {
            editPart.getViewer().getEditDomain().getCommandStack().execute(cmd);
        }
    }

    @objid ("cd51bb0c-3dee-4bf1-8568-515c123da777")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gmNode == null) ? 0 : this.gmNode.hashCode());
        return result;
    }

    @objid ("50b3bc4e-4c25-4be1-adbf-86f8529ef6f5")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DiagramNode)) {
            return false;
        }
        final DiagramNode other = (DiagramNode) obj;
        if (this.gmNode == null) {
            if (other.gmNode != null) {
                return false;
            }
        } else if (!this.gmNode.equals(other.gmNode)) {
            return false;
        }
        return true;
    }

    @objid ("110eb53c-199d-42fe-bfe9-81fee3b94e35")
    @Override
    public int getRepresentationMode() {
        final StyleKey styleKey = this.gmNode.getStyleKey(MetaKey.REPMODE);
        if (styleKey == null) {
            return 0;
        }
        final RepresentationMode pattern = this.gmNode.getDisplayedStyle().getProperty(styleKey);
        
        switch (pattern) {
            case SIMPLE:
                return 0;
            case STRUCTURED:
                return 1;
            case IMAGE:
                return 2;
            default:
                return 1;
        }
    }

    @objid ("71a368b8-ffe0-40fa-8495-34a55d8226ed")
    @Override
    public void setRepresentationMode(final int value) {
        final StyleKey styleKey = this.gmNode.getStyleKey(MetaKey.REPMODE);
        if (styleKey == null) {
            return;
        }
        RepresentationMode pattern;
        
        switch (value) {
            case 0:
                pattern = RepresentationMode.SIMPLE;
                break;
            case 1:
                pattern = RepresentationMode.STRUCTURED;
                break;
            case 2:
                pattern = RepresentationMode.IMAGE;
                break;
            default:
                pattern = RepresentationMode.STRUCTURED;
        }
        
        this.gmNode.getDisplayedStyle().setProperty(styleKey, pattern);
    }

    @objid ("7dbf42c9-487d-4a39-b15f-dedccc1a13eb")
    @Override
    public IGmObject getModel() {
        return this.gmNode;
    }

    @objid ("0481e233-28e8-474f-81ca-7f09aaab4290")
    @Override
    public IDiagramLayer getLayer() {
        // All element nodes currently belong to the main layer
        return this.diagramHandle.getDiagramNode().getElementsLayer(IDiagramElementsLayer.MAIN);
    }

    @objid ("cfadeb12-8bca-4b02-a599-37a670758920")
    @Override
    public void moveToLayer(IDiagramLayer newLayer) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Model element nodes belong to the elements layer.");
    }

}
