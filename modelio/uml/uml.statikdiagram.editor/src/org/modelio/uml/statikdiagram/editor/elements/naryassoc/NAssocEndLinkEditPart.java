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
package org.modelio.uml.statikdiagram.editor.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.DefaultCreateInfoFlowOnLinkEditPolicy;

/**
 * Edit part for {@link GmNAssocEndLink}.
 * 
 * @author cmarin
 */
@objid ("35c98921-55b7-11e2-877f-002564c97630")
public class NAssocEndLinkEditPart extends LinkEditPart {
    @objid ("35c98925-55b7-11e2-877f-002564c97630")
    private boolean showNavigability = true;

    @objid ("180a69bf-cd59-4dba-8675-f3bd4af690c4")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    @objid ("09df86ca-2aba-4bdd-acd4-3659ac8b1c0c")
    private static final PointList AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0 });

    @objid ("9b4c6b55-74e6-4c1f-af3a-ef0713dbeffd")
    private static final PointList NAVIG_AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0,
                -3, -1, -2, 0, -3, 1, -2, 0 });

    @objid ("35c9892c-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        // recreate arrows if the display navigability changes.
        GmNAssocEndLink gmModel = (GmNAssocEndLink) getModel();
        
        boolean showArrows = gmModel.getDisplayedStyle().getProperty(NAssocStructuredStyleKeys.SHOWNAVIGABILITY);
        if (showArrows != this.showNavigability) {
            this.showNavigability = showArrows;
            createDecorations((PolylineConnection) aFigure);
        }
        
        refreshDecorationsFromStyle((LinkFigure) aFigure, style);
        
    }

    @objid ("35c98933-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        LinkFigure connection = (LinkFigure) getFigure();
        
        // Target side navigability & aggregation
        createDecorations(connection);
        
        refreshDecorationsFromStyle(connection, getModelStyle());
        
    }

    @objid ("35c98936-55b7-11e2-877f-002564c97630")
    private void createDecorations(PolylineConnection connection) {
        RotatableDecoration deco;
        
        deco = createDecoration(false);
        connection.setTargetDecoration(deco);
        
    }

    @objid ("35c9893a-55b7-11e2-877f-002564c97630")
    private RotatableDecoration createDecoration(boolean withArrow) {
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

    @objid ("35cb0f9d-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationsFromStyle(LinkFigure connection, IStyle style) {
        GmNAssocEndLink model = (GmNAssocEndLink) getModel();
        
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
            decoration.setBackgroundColor(fillColor);
        
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

    @objid ("35cb0fa5-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy("CreateInfoFlow", new DefaultCreateInfoFlowOnLinkEditPolicy());
        removeEditPolicy("rake");
        
    }

}
