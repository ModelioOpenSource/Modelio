/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.ShapedBorderedFigure;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.ui.CoreColorRegistry;

/**
 * Specialization of the {@link ElementLabelEditPart} to provide a specialized figure (with the side decoration).
 * 
 * @author fpoyer
 */
@objid ("d8d12e96-55b6-11e2-877f-002564c97630")
public class OperatorEditPart extends ElementLabelEditPart {
    @objid ("d8d2b50c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d8d2b504-55b6-11e2-877f-002564c97630")
    @Override
    public void performRequest(final Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            // not editable
        
        } else {
            super.performRequest(req);
        }
    }

    @objid ("d8d12e9a-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        GmElementLabel model = (GmElementLabel) getModel();
        ToolbarLayout tl = new ToolbarLayout(true);
        
        Figure mainFig = new GradientFigure();
        mainFig.setLayoutManager(tl);
        mainFig.setOpaque(true);
        
        ShapedBorderedFigure operatorFig = new ShapedBorderedFigure(new OperatorShaper());
        operatorFig.setLayoutManager(new StackLayout());
        
        LabelumFigure labelFigure = new LabelumFigure(model.getLabel());
        labelFigure.setLabelAlignment(PositionConstants.LEFT);
        labelFigure.setOpaque(false);
        operatorFig.add(labelFigure);
        
        mainFig.add(operatorFig);
        
        refreshFromStyle(mainFig, model.getDisplayedStyle());
        return mainFig;
    }

    @objid ("d8d2b4fb-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        final GmModel gmModel = getModel();
        // Refresh the main figure
        
        // Set pen properties where applicable
        if (aFigure instanceof IPenOptionsSupport) {
            applyPenOptions((IPenOptionsSupport) aFigure, style, gmModel);
        }
        
        // Set brush properties where applicable
        ShapedBorderedFigure opFigure = (ShapedBorderedFigure) getOperatorFigure(aFigure);
        StyleKey bgStyleKey = gmModel.getStyleKey(MetaKey.FILLCOLOR);
        if (aFigure instanceof IBrushOptionsSupport) {
            final IBrushOptionsSupport brush = (IBrushOptionsSupport) aFigure;
        
            if (bgStyleKey != null) {
                brush.setFillColor(style.getColor(bgStyleKey));
            }
        
            if (gmModel.getStyleKey(MetaKey.FILLMODE) != null) {
                switch ((FillMode) style.getProperty(gmModel.getStyleKey(MetaKey.FILLMODE))) {
                case GRADIENT:
                    brush.setUseGradient(true);
                    break;
                case SOLID:
                    brush.setUseGradient(false);
                    break;
                case TRANSPARENT:
                    brush.setFillColor(null);
                    break;
                }
            }
        }
        
        // Now also refresh the label specifically: use a darker shade of the fill color for background, and use all pen options.
        
        // Passed figure is 'fig' (see createFigure method) => fetch fig2 in fig and then label in fig2.
        opFigure.setUseGradient(false);
        if (bgStyleKey != null) {
            opFigure.setFillColor(CoreColorRegistry.getDerivedColor(style.getColor(bgStyleKey), 0.6f));
        }
        applyPenOptions(opFigure, style, gmModel);
        
        // Set pen properties where applicable
        IFigure labelFigure = getLabelFigure(aFigure);
        if (labelFigure instanceof IPenOptionsSupport) {
            final IPenOptionsSupport pen = (IPenOptionsSupport) labelFigure;
            applyPenOptions(pen, style, gmModel);
        }
    }

    @objid ("d8d2b509-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmElementLabel model = (GmElementLabel) getModel();
        // Update label.
        getLabelFigure(getFigure()).setText(model.getLabel());
    }

    @objid ("4a2b540c-7f3d-442b-8d63-b5c7bb72be36")
    @Override
    protected LabelumFigure getLabelFigure(IFigure aFigure) {
        return (LabelumFigure) getOperatorFigure(aFigure).getChildren().get(0);
    }

    @objid ("81052a4e-7ed6-4655-8fb5-b80d78c45760")
    private IFigure getOperatorFigure(IFigure mainFig) {
        return (IFigure) mainFig.getChildren().get(0);
    }

    @objid ("1b891ec5-de2d-4046-9ff1-2018ba8061aa")
    protected static void applyPenOptions(final IPenOptionsSupport pen, final IStyle style, final GmModel gmModel) {
        StyleKey styleKey = gmModel.getStyleKey(MetaKey.FONT);
        if (styleKey != null) {
            pen.setTextFont(style.getFont(styleKey));
        }
        
        styleKey = gmModel.getStyleKey(MetaKey.TEXTCOLOR);
        if (styleKey != null) {
            pen.setTextColor(style.getColor(styleKey));
        }
        
        styleKey = gmModel.getStyleKey(MetaKey.LINECOLOR);
        if (styleKey != null) {
            pen.setLineColor(style.getColor(styleKey));
        }
        
        styleKey = gmModel.getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey != null) {
            pen.setLineWidth(style.getInteger(styleKey));
        }
        
        styleKey = gmModel.getStyleKey(MetaKey.LINEPATTERN);
        if (styleKey != null) {
            LinePattern linePattern = style.getProperty(styleKey);
            pen.setLinePattern(linePattern);
        }
    }

}
