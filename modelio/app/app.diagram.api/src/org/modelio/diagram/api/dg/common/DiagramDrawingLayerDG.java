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
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramDrawingsLayer;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramAbstractNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a diagram drawings layer.
 */
@objid ("dc49271b-8156-4b0c-8b46-c026cf4c589d")
public class DiagramDrawingLayerDG extends DiagramAbstractNode implements IDiagramDrawingsLayer {
    @objid ("f2cbefdc-a9a4-4803-9a04-13acb93ddcf8")
    private IGmDrawingLayer gm;

    /**
     * C'tor
     * 
     * @param diagramHandle the diagram handle
     * @param gm the graphic model
     */
    @objid ("7b402cfd-4d0e-4b82-baf0-f9f8a9f8a621")
    public DiagramDrawingLayerDG(DiagramHandle diagramHandle, IGmDrawingLayer gm) {
        super(diagramHandle);
        this.gm = gm;
    }

    @objid ("95c2f64f-3e3f-446e-8985-4e88a9f1ec39")
    @Override
    public List<IDiagramNode> getNodes() {
        return getDrawingNodes();
    }

    @objid ("ff638b0d-74d3-4953-8839-ba7b38d54be6")
    @Override
    public List<IDiagramLink> getLinks() {
        return getDrawingLinks();
    }

    @objid ("02777edd-59a0-4801-ab50-1917c0c6e0eb")
    @Override
    public List<IDiagramNode> getDrawingNodes() {
        final List<IDiagramNode> nodes = new ArrayList<>();
        for (final IGmNodeDrawing gmLink : this.gm.getNodes()) {
            IDiagramGraphic diagramLink = DGFactory.getInstance().getDiagramDrawingGraphic(this.diagramHandle, gmLink);
            if (diagramLink instanceof IDiagramNode) {
                nodes.add((IDiagramNode) diagramLink);
            }
        }
        return nodes;
    }

    @objid ("cf0efb03-0758-4c35-ab00-7a66cb9782ab")
    @Override
    public List<IDiagramLink> getDrawingLinks() {
        // Currently all drawing links have the layer as source and destination
        final List<IDiagramLink> links = new ArrayList<>();
        for (final IGmDrawingLink gmLink : this.gm.getStartingDrawingLinks()) {
            IDiagramLink diagramLink = DGFactory.getInstance().getDiagramLink(this.diagramHandle, gmLink);
            if (diagramLink != null) {
                links.add(diagramLink);
            }
        }
        return links;
    }

    @objid ("386580fc-70c3-42f8-9f3f-fddfa0243b4f")
    @Override
    public IDiagramGraphic getParent() {
        return this.diagramHandle.getDiagramNode();
    }

    @objid ("b2999f4e-0d90-4719-83d3-1fae1a42251c")
    @Override
    public int getRepresentationMode() {
        return 0;
    }

    @objid ("6c78e707-854b-4ca1-82ba-ba2ff230d703")
    @Override
    public void setRepresentationMode(int value) {
        // ignore
    }

    @objid ("11b647c0-ab49-4bb2-8ee0-8564c134b792")
    @Override
    public MObject getElement() {
        return this.gm.getRepresentedElement();
    }

    @objid ("239481c9-6be2-46db-b1a9-6e24a471cc10")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("d795b6e9-e354-41c1-94be-4360aea7d2c6")
    @Override
    public String getName() {
        final IGmDiagram diagram = this.gm.getDiagram();
        
        if (this.gm == diagram.getBackgroundDrawingLayer()) {
            return IDiagramDrawingsLayer.BACKGROUND;
        }
        
        final List<IGmDrawingLayer> layers = diagram.getDrawingLayers();
        final int indexOf = layers.indexOf(this.gm);
        if (!layers.isEmpty() && indexOf == layers.size() - 1) {
            return IDiagramDrawingsLayer.TOP;
        }
        return String.valueOf(indexOf);
    }

    @objid ("61d33712-fd1b-4438-b530-2537ad48d4ef")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

    @objid ("f5b9e076-a6ea-4724-87a5-2dc859a2716f")
    @Override
    public IGmObject getModel() {
        return this.gm;
    }

    /**
     * Layers don't belong to a layer.
     */
    @objid ("d3b5bbab-ce44-4840-a468-3c906ab77d59")
    @Override
    public IDiagramLayer getLayer() {
        return null;
    }

    @objid ("1c26e6bf-5104-4b27-9cd1-c466bfcbefa3")
    @Override
    public void moveToLayer(IDiagramLayer newLayer) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Layers cannot be moved.");
    }

    @objid ("0d96a695-af35-4d15-91fc-2ec86b568f8f")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        switch (role) {
        case INNER:
            return getNodes();
        default:
            return Collections.emptyList();
        }
    }

}
