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

package org.modelio.diagram.editor.statik.elements.narylink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.DefaultCreateInfoFlowOnLinkEditPolicy;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.metamodel.uml.statik.AggregationKind;

/**
 * Edit part for {@link GmNLinkEndLink}.
 * 
 * @author cmarin
 */
@objid ("35eca191-55b7-11e2-877f-002564c97630")
public class NLinkEndLinkEditPart extends LinkEditPart {
    @objid ("35eca195-55b7-11e2-877f-002564c97630")
    private boolean showNavigability = true;

    @objid ("21f8062f-6723-4eb3-ae93-f595308fba16")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    @objid ("9819a78b-822b-4cc4-a4fa-f2ad91380e82")
    private static final PointList AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0 });

    @objid ("ac242789-10a0-4d64-8edd-d9d977629e77")
    private static final PointList NAVIG_AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0,
            -3, -1, -2, 0, -3, 1, -2, 0 });

    @objid ("35eca19c-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        // recreate arrows if the display navigability changes.
        GmNLinkEndLink gmModel = (GmNLinkEndLink) getModel();
        
        boolean showArrows = gmModel.getDisplayedStyle().getProperty(NLinkStructuredStyleKeys.SHOWNAVIGABILITY);
        if (showArrows != this.showNavigability) {
            this.showNavigability = showArrows;
            createDecorations((PolylineConnection) aFigure, gmModel);
        }
        
        refreshDecorationsFromStyle((LinkFigure) aFigure, style);
    }

    @objid ("35ee27fc-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        LinkFigure connection = (LinkFigure) getFigure();
        GmNLinkEndLink model = (GmNLinkEndLink) getModel();
        
        // Target side navigability & aggregation
        createDecorations(connection, model);
        
        refreshDecorationsFromStyle(connection, getModelStyle());
    }

    @objid ("35ee27ff-55b7-11e2-877f-002564c97630")
    private void createDecorations(PolylineConnection connection, GmNLinkEndLink model) {
        RotatableDecoration deco;
        
        deco = createDecoration(AggregationKind.KINDISASSOCIATION, this.showNavigability && model.isToNavigable());
        connection.setTargetDecoration(deco);
    }

    @objid ("35ee2803-55b7-11e2-877f-002564c97630")
    private RotatableDecoration createDecoration(AggregationKind toAggregation, boolean withArrow) {
        switch (toAggregation) {
            case KINDISASSOCIATION: {
                if (withArrow) {
                    DefaultPolylineDecoration deco = new DefaultPolylineDecoration();
                    deco.setTemplate(TRIANGLE_TIP);
                    deco.setScale(9, 4);
                    deco.setOpaque(false);
                    deco.setBackgroundColor(null);
                    deco.setFill(false);
                    return deco;
                } else {
                    return null;
                }
            }
            case KINDISAGGREGATION:
            case KINDISCOMPOSITION:
                DefaultPolygonDecoration deco = new DefaultPolygonDecoration();
                if (withArrow)
                    deco.setTemplate(NAVIG_AGGREG_TIP);
                else
                    deco.setTemplate(AGGREG_TIP);
                deco.setScale(9, 4);
                deco.setOpaque(true);
                deco.setFill(true);
                return deco;
        }
        return null;
    }

    @objid ("35ee280b-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationsFromStyle(LinkFigure connection, IStyle style) {
        GmNLinkEndLink model = (GmNLinkEndLink) getModel();
        
        // Get style values
        Color fillColor = null;
        int lineWidth = 1;
        LinePattern linePattern = LinePattern.LINE_SOLID;
        
        if (model.getStyleKey(MetaKey.FILLCOLOR) != null)
            fillColor = (style.getColor(model.getStyleKey(MetaKey.FILLCOLOR)));
        if (model.getStyleKey(MetaKey.LINEWIDTH) != null)
            lineWidth = (style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH)));
        if (model.getStyleKey(MetaKey.LINEPATTERN) != null)
            linePattern = (style.getProperty(model.getStyleKey(MetaKey.LINEPATTERN)));
        
        // Source decoration
        RotatableDecoration decoration = connection.getSourceDecoration();
        if (decoration != null) {
            //if (model.getFromAggregation() == AggregationKind.KIND_IS_COMPOSITION) {
            //    decoration.setBackgroundColor(connection.getLineColor());
            //} else {
            decoration.setBackgroundColor(fillColor);
            //}
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(linePattern);
            pennable.setLineWidth(lineWidth);
        }
        
        decoration = connection.getTargetDecoration();
        if (decoration != null) {
            //if (model.getFromAggregation() == AggregationKind.KIND_IS_COMPOSITION) {
            //    decoration.setBackgroundColor(connection.getLineColor());
            //} else {
            decoration.setBackgroundColor(fillColor);
            //}
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(linePattern);
            pennable.setLineWidth(lineWidth);
        }
    }

    @objid ("35ee2813-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy("CreateInfoFlow", new DefaultCreateInfoFlowOnLinkEditPolicy());
        removeEditPolicy("rake");
    }

}
