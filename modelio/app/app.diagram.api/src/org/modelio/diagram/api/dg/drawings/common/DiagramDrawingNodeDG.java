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
package org.modelio.diagram.api.dg.drawings.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramDrawingNode;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.DiagramDrawingLayerDG;
import org.modelio.diagram.api.services.DiagramAbstractNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a drawing node.
 */
@objid ("79091040-ea8c-435b-afe4-edcf9e82ef45")
public class DiagramDrawingNodeDG extends DiagramAbstractNode implements IDiagramDrawingNode {
    @objid ("a8a0926c-ae0c-4d88-b643-f122a82b5fb8")
    private IGmNodeDrawing gmNode;

    @objid ("73736d6a-8e36-48f2-9471-219c2e00ecf9")
    @Override
    public List<IDiagramNode> getNodes() {
        return Collections.emptyList();
    }

    @objid ("de1368cc-6b98-49b6-b43b-82975fee32b2")
    @Override
    public IDiagramGraphic getParent() {
        final IGmDrawingLinkable from = this.gmNode.getLayer();
        IDiagramGraphic ret = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, from);
        return ret;
    }

    @objid ("4b285bc2-1a5f-4cef-9d21-0cc56c10b5bb")
    @Override
    public int getRepresentationMode() {
        return 0;
    }

    @objid ("5d28e1de-49bf-4391-a52b-d4037493cd49")
    @Override
    public void setRepresentationMode(int value) {
        // ignore
    }

    @objid ("87d14d60-8cb4-4a54-8fb9-2e470328f136")
    @Override
    public MObject getElement() {
        return null;
    }

    @objid ("7c2005f4-d9d1-41ec-a1d3-4ed1ece8f87c")
    @Override
    public List<IDiagramLink> getFromLinks() {
        final List<IDiagramLink> links = new ArrayList<>();
        for (final IGmDrawingLink gmLink : this.gmNode.getStartingDrawingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("f01bb82c-9c59-4e93-988a-d470b9a5336c")
    @Override
    public String getName() {
        return this.gmNode.getLabel();
    }

    @objid ("b783bf0f-a91c-43fb-bc7b-342353713ee4")
    @Override
    public List<IDiagramLink> getToLinks() {
        final List<IDiagramLink> links = new ArrayList<>();
        for (final IGmDrawingLink gmLink : this.gmNode.getEndingDrawingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("4934c832-44a6-485e-8b53-e7180b68c3a3")
    @Override
    public IGmObject getModel() {
        return this.gmNode;
    }

    /**
     * Creates a drawing node.
     * @param diagramHandle the diagram handle
     * @param gmNode the node model
     */
    @objid ("d888129b-e36a-43d0-9280-3c5b4f8c49c6")
    public  DiagramDrawingNodeDG(DiagramHandle diagramHandle, IGmNodeDrawing gmNode) {
        super(diagramHandle);
        this.gmNode = gmNode;
        
    }

    /**
     * Set the node label.
     * @param label the node label.
     */
    @objid ("9bbb8e56-eedd-4eea-acbd-6f1593894c35")
    @Override
    public void setLabel(String label) {
        this.gmNode.setLabel(label);
    }

    @objid ("a9a2d03c-d891-4520-a637-a06d097cc0ab")
    @Override
    public IDiagramLayer getLayer() {
        return DGFactory.getInstance().getDiagramLayer(this.diagramHandle, this.gmNode.getLayer());
    }

    @objid ("607f051f-c39a-428d-a660-17000d5e588f")
    @Override
    public void moveToLayer(IDiagramLayer newLayer) throws IllegalArgumentException {
        if (! (newLayer instanceof DiagramDrawingLayerDG)) {
            throw new IllegalArgumentException(" new layer must be a drawing layer.");
        }
        
        DiagramDrawingLayerDG castedLayer = (DiagramDrawingLayerDG) newLayer;
        IGmDrawingLayer gmNewLayer = (IGmDrawingLayer) castedLayer.getModel();
        
        this.gmNode.getLayer().removeChild(this.gmNode);
        gmNewLayer.addChild(this.gmNode);
        
    }

    @objid ("1b5300cb-681b-4b29-a302-10fd47aa4ebd")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        return Collections.emptyList();
    }

    @objid ("3c60d54e-34a8-4fef-b0f2-ab97f24a68f3")
    @Override
    public String getLabel() {
        return this.gmNode.getLabel();
    }

}
