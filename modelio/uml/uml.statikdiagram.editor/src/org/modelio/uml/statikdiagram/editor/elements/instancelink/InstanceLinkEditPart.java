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

package org.modelio.uml.statikdiagram.editor.elements.instancelink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;
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
 * Edit part for {@link GmInstanceLink}.
 * 
 * @author cmarin
 */
@objid ("35604002-55b7-11e2-877f-002564c97630")
public class InstanceLinkEditPart extends LinkEditPart {
    @objid ("35604006-55b7-11e2-877f-002564c97630")
    private boolean showNavigability = true;

    /**
     * Triangle to the right
     */
    @objid ("f73654f7-d7e5-4399-959a-23931d94d27b")
    private static PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    /**
     * Aggregation/composition diamond
     */
    @objid ("8884f864-a7f5-4bac-99b2-610c0bacd774")
    private static PointList AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0 });

    /**
     * Aggregation/composition diamond with triangle
     */
    @objid ("898bc4dd-d72e-49b4-95b7-83d7a8542327")
    private static PointList NAVIG_AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0, -3, -1,
            -2, 0, -3, 1, -2, 0 });

    /**
     * Default constructor.
     */
    @objid ("3560400d-55b7-11e2-877f-002564c97630")
    public InstanceLinkEditPart() {
        super();
    }

    @objid ("35604010-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        // recreate arrows if the display navigability changes.
        GmInstanceLink gmModel = (GmInstanceLink) getModel();
        
        boolean showArrows = gmModel.getDisplayedStyle().getProperty(InstanceLinkStructuredStyleKeys.SHOWNAVIGABILITY);
        if (showArrows != this.showNavigability) {
            this.showNavigability = showArrows;
            createDecorations((PolylineConnection) aFigure, gmModel);
        }
        
        refreshDecorationsFromStyle((LinkFigure) aFigure,
                                    style,
                                    AggregationKind.KINDISASSOCIATION,
                                    AggregationKind.KINDISASSOCIATION);
    }

    @objid ("35604017-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        LinkFigure connection = (LinkFigure) getFigure();
        GmInstanceLink model = (GmInstanceLink) getModel();
        
        // Target side navigability & aggregation
        createDecorations(connection, model);
        
        refreshDecorationsFromStyle(connection,
                                    getModelStyle(),
                                    AggregationKind.KINDISASSOCIATION,
                                    AggregationKind.KINDISASSOCIATION);
    }

    /**
     * Recreate the source and target decoration arrows and/or diamonds.
     * 
     * @param connection The connection figure
     * @param model The GmAssociation
     */
    @objid ("3560401a-55b7-11e2-877f-002564c97630")
    private void createDecorations(PolylineConnection connection, GmInstanceLink model) {
        RotatableDecoration deco;
        
        deco = createDecoration(AggregationKind.KINDISASSOCIATION,
                                this.showNavigability && model.isToNavigable());
        connection.setTargetDecoration(deco);
        
        deco = createDecoration(AggregationKind.KINDISASSOCIATION,
                                this.showNavigability && model.isFromNavigable());
        connection.setSourceDecoration(deco);
    }

    /**
     * Create the connection decoration from the given parameters.
     * 
     * @param toAggregation The aggregation mode
     * @param withArrow Whether a navigability arrow must be displayed.
     * @return
     */
    @objid ("3561c67b-55b7-11e2-877f-002564c97630")
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

    /**
     * Refresh decorations from the style.
     * 
     * @param connection The connection figure.
     * @param style The style to use.
     */
    @objid ("3561c684-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationsFromStyle(LinkFigure connection, IStyle style, AggregationKind fromAggregation, AggregationKind toAggregation) {
        GmInstanceLink model = (GmInstanceLink) getModel();
        
        // Get style values
        Color fillColor = null;
        int lineWidth = 1;
        LinePattern lineStyle = LinePattern.LINE_SOLID;
        
        if (model.getStyleKey(MetaKey.FILLCOLOR) != null)
            fillColor = (style.getColor(model.getStyleKey(MetaKey.FILLCOLOR)));
        if (model.getStyleKey(MetaKey.LINEWIDTH) != null)
            lineWidth = (style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH)));
        if (model.getStyleKey(MetaKey.LINEPATTERN) != null)
            lineStyle = (style.getProperty(model.getStyleKey(MetaKey.LINEPATTERN)));
        
        // Source decoration
        RotatableDecoration decoration = connection.getSourceDecoration();
        if (decoration != null) {
            if (fromAggregation == AggregationKind.KINDISCOMPOSITION) {
                decoration.setBackgroundColor(connection.getLineColor());
            } else {
                decoration.setBackgroundColor(fillColor);
            }
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(lineStyle);
            pennable.setLineWidth(lineWidth);
        }
        
        // Target decoration
        decoration = connection.getTargetDecoration();
        if (decoration != null) {
            if (toAggregation == AggregationKind.KINDISCOMPOSITION) {
                decoration.setBackgroundColor(connection.getLineColor());
            } else {
                decoration.setBackgroundColor(fillColor);
            }
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(lineStyle);
            pennable.setLineWidth(lineWidth);
        }
    }

    @objid ("3561c693-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        // Allow creation of information flows on the link
        installEditPolicy("CreateInfoFlow", new CreateInfoFlowEditPolicy());
        
        super.createEditPolicies();
    }

}
