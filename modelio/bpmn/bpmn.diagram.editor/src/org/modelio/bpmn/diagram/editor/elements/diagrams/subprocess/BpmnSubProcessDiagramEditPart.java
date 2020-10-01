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

package org.modelio.bpmn.diagram.editor.elements.diagrams.subprocess;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.swt.graphics.Color;
import org.modelio.bpmn.diagram.editor.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramFigure;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;
import org.modelio.diagram.elements.core.policies.CreateLinkIntermediateEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart (== controller in the GEF model) for BPMN diagram background.
 */
@objid ("61fc57fa-55b6-11e2-877f-002564c97630")
public class BpmnSubProcessDiagramEditPart extends AbstractDiagramEditPart {
    @objid ("61fc57fe-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new BpmnSubProcessDiagramFigure();
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        
        // Set style independent properties
        
        // Set style dependent properties
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    @objid ("61fc5803-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoExpandLayoutEditPolicy());
        
        // To redirect to drawing layers
        installEditPolicy(AbstractDiagramEditPart.DRAWINGLAYER_ROLE, new AbstractDiagramEditPart.AskDrawingLayerEditPolicy());
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
        
        // Policy to add bend points to connections being created
        installEditPolicy(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT, new CreateLinkIntermediateEditPolicy());
    }

    @objid ("61fc580d-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        AbstractDiagramFigure diagramFigure = (AbstractDiagramFigure) aFigure;
        
        boolean viewGrid = style.getBoolean(GmBpmnDiagramStyleKeys.VIEWGRID);
        boolean snapToGrid = style.getBoolean(GmBpmnDiagramStyleKeys.SNAPTOGRID);
        boolean snapToGeometry = style.getBoolean(GmBpmnDiagramStyleKeys.SNAPTOGEOMETRY);
        Color gridColor = style.getColor(GmBpmnDiagramStyleKeys.GRIDCOLOR);
        int gridAlpha = style.getInteger(GmBpmnDiagramStyleKeys.GRIDALPHA);
        int gridSpacing = style.getInteger(GmBpmnDiagramStyleKeys.GRIDSPACING);
        
        Color fillColor = style.getColor(GmBpmnDiagramStyleKeys.FILLCOLOR);
        String fillImage = style.getProperty(GmBpmnDiagramStyleKeys.FILLIMAGE);
        int fillAlpha = style.getInteger(GmBpmnDiagramStyleKeys.FILLALPHA);
        
        // Process the page size property
        // TODO: in the future this parsing might become the responsability of the property view,
        // ie the property view would propose a 'Dimension' editor returning the proper 'in pixel' dimension value...
        String pageSize = (String) style.getProperty(GmBpmnDiagramStyleKeys.PAGE_SIZE);
        Dimension pixelPageSize = null;
        if (pageSize != null && !pageSize.isEmpty()) {
            PrecisionDimension inchPageSize = parsePageSize(pageSize);
            if (inchPageSize != null) {
                pixelPageSize = convertToPixel(inchPageSize);
            }
        }
        
        //
        EditPartViewer v = getRoot().getViewer();
        v.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, Boolean.valueOf(viewGrid));
        v.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.valueOf(snapToGrid));
        v.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(gridSpacing, gridSpacing));
        v.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.valueOf(snapToGeometry));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_GRID_COLOR, gridColor);
        v.setProperty(AbstractDiagramEditPart.PROPERTY_GRID_ALPHA, Integer.valueOf(gridAlpha));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_COLOR, fillColor);
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_IMAGE, resolvePath(fillImage));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_ALPHA, Integer.valueOf(fillAlpha));
        diagramFigure.showPageBoundaries(style.getBoolean(GmBpmnDiagramStyleKeys.SHOW_PAGES));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_TILE_SIZE, pixelPageSize);
        diagramFigure.setPageBoundaries(pixelPageSize);
    }

    @objid ("b0340e85-b470-4025-8b61-ab6d0da3bb9d")
    @Override
    public GmBpmnSubProcessDiagram getModel() {
        return (GmBpmnSubProcessDiagram) super.getModel();
    }

}
