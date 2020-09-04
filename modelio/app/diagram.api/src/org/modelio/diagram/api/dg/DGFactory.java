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

package org.modelio.diagram.api.dg;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.app.core.metamodel.MetamodelExtensionPoint;
import org.modelio.diagram.api.dg.common.DiagramDrawingLayerDG;
import org.modelio.diagram.api.dg.drawings.common.DiagramDrawingLinkDG;
import org.modelio.diagram.api.dg.drawings.common.DiagramDrawingNodeDG;
import org.modelio.diagram.api.services.DiagramGraphic;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.elements.drawings.ellipse.GmEllipseDrawing;
import org.modelio.diagram.elements.drawings.line.GmLineDrawing;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleDrawing;
import org.modelio.diagram.elements.drawings.text.GmTextDrawing;

/**
 * Diagram graphic factory.
 */
@objid ("82b36ce4-3670-43aa-b785-f84474746a27")
public class DGFactory implements IDGFactory {
    @objid ("a0a3e139-9cea-4d05-a6cb-d7279b595815")
    private static final String EXTENSION_POINT_ID = "org.modelio.app.metamodel.diagram.api.factory";

    @objid ("12fe75ad-ab5a-4ad8-b8c7-2dbad24eb70d")
    private static final DGFactory INSTANCE = new DGFactory();

    @objid ("34a13d47-6eb3-4f71-a823-a391aa91d5c2")
    private static final MetamodelExtensionPoint<IDGFactory> extensionPoint = new MetamodelExtensionPoint<>(EXTENSION_POINT_ID);

    @objid ("dbe693bf-71c0-4df6-ad31-7324ef842dd5")
    private DGFactory() {
    }

    /**
     * @return the singleton instance.
     */
    @objid ("a6b65b4c-8083-4347-b1d9-d40d1a81dd50")
    public static DGFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a DiagramGraphic for the given model. Can be either a DiagramNode or a DiagramLink.
     * @param diagramHandle a handle to the diagram
     * @param gmModel the model.
     * @return a {@link DiagramGraphic}
     */
    @objid ("ee0d0909-6811-4561-9079-4d28986cdd1a")
    public IDiagramGraphic getDiagramGraphic(IDiagramHandle diagramHandle, IGmObject gmModel) {
        if (gmModel instanceof GmNodeModel) {
            return this.getDiagramNode(diagramHandle, (GmNodeModel) gmModel);
        }
        
        if (gmModel instanceof IGmLink) {
            return this.getDiagramLink(diagramHandle, (IGmLink) gmModel);
        }
        
        if (gmModel instanceof IGmDrawing) {
            return getDiagramDrawingGraphic(diagramHandle, (IGmDrawing) gmModel);
        }
        return null;
    }

    /**
     * Return a list of DiagramGraphics for each given model. Can be mixed {@link DiagramNode}s and {@link DiagramLink}s
     * @param diagramHandle a handle to the diagram
     * @param models the models
     * @return a list of {@link DiagramGraphic}
     */
    @objid ("01817c25-cee2-42ca-b74c-c3510518726e")
    public List<IDiagramGraphic> getDiagramGraphics(IDiagramHandle diagramHandle, Iterable<? extends IGmObject> models) {
        List<IDiagramGraphic> list = new ArrayList<>();
        for (IGmObject gm : models) {
            IDiagramGraphic diagramGraphic = this.getDiagramGraphic(diagramHandle, gm);
            if (diagramGraphic != null) {
                list.add(diagramGraphic);
            }
        }
        return list;
    }

/* (non-Javadoc)
     * @see org.modelio.diagram.api.dg.IDGFactory#getDiagramLink(org.modelio.diagram.api.services.IDiagramHandle, org.modelio.diagram.elements.core.model.IGmLink)
     */
    @objid ("9ece0dd2-718f-486e-aa45-34e751fd9203")
    @Override
    public IDiagramLink getDiagramLink(IDiagramHandle diagramHandle, IGmLink gmLink) {
        IDiagramLink ret = null;
        for (IDGFactory factory : extensionPoint.getAll()) {
            ret = factory.getDiagramLink(diagramHandle, gmLink);
            if (ret != null) {
                return ret;
            }
        }
        return ret;
    }

/* (non-Javadoc)
     * @see org.modelio.diagram.api.dg.IDGFactory#getDiagramNode(org.modelio.diagram.api.services.IDiagramHandle, org.modelio.diagram.elements.core.node.GmNodeModel)
     */
    @objid ("0c99b06f-a207-4b03-b535-a1f176b81570")
    @Override
    public IDiagramNode getDiagramNode(IDiagramHandle diagramHandle, GmNodeModel gmNodeModel) {
        if (!gmNodeModel.isVisible()) {
            return null;
        }
        
        IDiagramNode ret = null;
        for (IDGFactory factory : extensionPoint.getAll()) {
            ret = factory.getDiagramNode(diagramHandle, gmNodeModel);
            if (ret != null) {
                return ret;
            }
        }
        return ret;
    }

    /**
     * Return a list of DiagramNode for each given model.
     * @param diagramHandle a handle to the diagram
     * @param models the models
     * @return a list of {@link DiagramNode}
     */
    @objid ("91972eb5-e7d8-4f2c-8234-d681ee18cc04")
    public List<IDiagramNode> getDiagramNodes(IDiagramHandle diagramHandle, Iterable<? extends IGmNode> models) {
        List<IDiagramNode> list = new ArrayList<>();
        for (IGmNode gm : models) {
            IDiagramNode diagramNode = this.getDiagramNode(diagramHandle, (GmNodeModel) gm);
            if (diagramNode != null) {
                list.add(diagramNode);
            }
        }
        return list;
    }

    /**
     * Returns a DiagramGraphic for the given model. Can be either a DiagramNode or a DiagramLink.
     * @param diagramHandle a handle to the diagram
     * @param gmModel the model.
     * @return a {@link DiagramGraphic}
     */
    @objid ("3ca91567-1b18-4cdf-96bc-9967012ecaa8")
    public IDiagramGraphic getDiagramDrawingGraphic(IDiagramHandle diagramHandle, IGmDrawing gmModel) {
        // Drawing links
        if (gmModel instanceof GmLineDrawing) {
            return getDiagramLink(diagramHandle, (IGmDrawingLink) gmModel);
        }
        
        // Drawing nodes
        if (gmModel instanceof GmEllipseDrawing) {
            return new DiagramDrawingNodeDG((DiagramHandle) diagramHandle, (IGmNodeDrawing) gmModel);
        }
        
        if (gmModel instanceof GmRectangleDrawing) {
            return new DiagramDrawingNodeDG((DiagramHandle) diagramHandle, (IGmNodeDrawing) gmModel);
        }
        
        if (gmModel instanceof GmTextDrawing) {
            return new DiagramDrawingNodeDG((DiagramHandle) diagramHandle, (IGmNodeDrawing) gmModel);
        }
        
        if (gmModel instanceof IGmDrawingLayer) {
            return new DiagramDrawingLayerDG((DiagramHandle) diagramHandle, (IGmDrawingLayer) gmModel);
        }
        return null;
    }

    /**
     * Return a list of {@link IDiagramLayer} for each given model.
     * @param diagramHandle a handle to the diagram
     * @param models the models
     * @return a list of {@link IDiagramLayer}
     */
    @objid ("44fd8b16-ce0a-44ba-a7e7-6b4d240a9cb2")
    public List<IDiagramLayer> getDiagramLayers(IDiagramHandle diagramHandle, List<IGmDrawingLayer> models) {
        List<IDiagramLayer> list = new ArrayList<>();
        for (IGmDrawingLayer gm : models) {
            IDiagramLayer diagramNode = this.getDiagramLayer(diagramHandle, gm);
            if (diagramNode != null) {
                list.add(diagramNode);
            }
        }
        return list;
    }

    /**
     * Returns a {@link IDiagramLayer} for the given model.
     * @param diagramHandle the diagram in which the model is shown.
     * @param gm the model.
     * @return a {@link IDiagramLayer}
     */
    @objid ("c5f5db05-572a-49e3-b7fc-c5de750a8245")
    public IDiagramLayer getDiagramLayer(IDiagramHandle diagramHandle, IGmDrawingLayer gm) {
        return new DiagramDrawingLayerDG((DiagramHandle) diagramHandle, gm);
    }

    /**
     * Returns a {@link IDiagramLink} for the given drawing link model.
     * @param diagramHandle the diagram in which the model is shown.
     * @param gmLinkModel the drawing link model.
     * @return a {@link IDiagramLink}
     */
    @objid ("4cc1c832-4eba-44bb-839d-833a7fc978a6")
    public IDiagramLink getDiagramLink(IDiagramHandle diagramHandle, IGmDrawingLink gmLinkModel) {
        if (gmLinkModel instanceof GmLineDrawing) {
            return new DiagramDrawingLinkDG((DiagramHandle) diagramHandle, gmLinkModel);
        }
        return null;
    }

}
