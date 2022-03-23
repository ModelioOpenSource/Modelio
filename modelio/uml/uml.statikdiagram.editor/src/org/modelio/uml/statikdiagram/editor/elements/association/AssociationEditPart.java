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
package org.modelio.uml.statikdiagram.editor.elements.association;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.decoration.CompositeDecoration;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.uml.statikdiagram.editor.elements.assocqualifier.GmQualifierGroup;

/**
 * Edit part for {@link GmAssociation}.
 * 
 * @author cmarin
 */
@objid ("33e140fa-55b7-11e2-877f-002564c97630")
public class AssociationEditPart extends LinkEditPart {
    @objid ("33e140fe-55b7-11e2-877f-002564c97630")
    private boolean showNavigability = true;

    @objid ("0130767b-f604-4b7a-b595-f5939e8f94ec")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    @objid ("c705e494-686d-42e3-afc1-fea7291074e8")
    private static final PointList AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0 });

    @objid ("fd05239c-7ba4-4763-9285-a121c9fa7002")
    private static final PointList NAVIG_AGGREG_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0,
                -3, -1, -2, 0, -3, 1, -2, 0 });

    @objid ("33e2c79a-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        // recreate arrows if the display navigability changes.
        GmAssociation gmModel = (GmAssociation) getModel();
        
        boolean showArrows = gmModel.getDisplayedStyle().getProperty(GmAssocStructuredStyleKeys.SHOWNAVIGABILITY);
        if (showArrows != this.showNavigability) {
            this.showNavigability = showArrows;
            createDecorations((LinkFigure) aFigure, gmModel);
        }
        
        refreshDecorationsFromStyle((LinkFigure) aFigure, style);
        
    }

    @objid ("33e2c7a1-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        LinkFigure connection = (LinkFigure) getFigure();
        GmAssociation model = (GmAssociation) getModel();
        
        // Target side navigability & aggregation
        createDecorations(connection, model);
        
        refreshDecorationsFromStyle(connection, getModelStyle());
        
    }

    @objid ("33e2c7a4-55b7-11e2-877f-002564c97630")
    private void createDecorations(LinkFigure connection, GmAssociation model) {
        RotatableDecoration deco;
        
        deco = createDecoration(model.getToAggregation(), this.showNavigability && model.isToNavigable());
        if (connection.getTargetDecoration() instanceof CompositeDecoration)
            ((CompositeDecoration) connection.getTargetDecoration()).setDecoration(deco);
        else
            connection.setTargetDecoration(deco);
        
        deco = createDecoration(model.getFromAggregation(), this.showNavigability && model.isFromNavigable());
        
        if (connection.getSourceDecoration() instanceof CompositeDecoration)
            ((CompositeDecoration) connection.getSourceDecoration()).setDecoration(deco);
        else
            connection.setSourceDecoration(deco);
        
    }

    @objid ("33e2c7aa-55b7-11e2-877f-002564c97630")
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

    @objid ("33e2c7b2-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationsFromStyle(LinkFigure connection, IStyle style) {
        GmAssociation model = (GmAssociation) getModel();
        
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
            if (model.getFromAggregation() == AggregationKind.KINDISCOMPOSITION) {
                decoration.setBackgroundColor(connection.getLineColor());
            } else {
                decoration.setBackgroundColor(fillColor);
            }
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(linePattern);
            pennable.setLineWidth(lineWidth);
        }
        
        // Target decoration
        decoration = connection.getTargetDecoration();
        if (decoration != null) {
            if (model.getToAggregation() == AggregationKind.KINDISCOMPOSITION) {
                decoration.setBackgroundColor(connection.getLineColor());
            } else {
                decoration.setBackgroundColor(fillColor);
            }
        
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLinePattern(linePattern);
            pennable.setLineWidth(lineWidth);
        }
        
    }

    @objid ("33e2c7ba-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        installEditPolicy("CreateInfoFlow", new CreateInfoFlowEditPolicy());
        installEditPolicy("qualifiers", new CreateQualifierEditPolicy());
        
        super.createEditPolicies();
        
    }

    @objid ("33e2c7bd-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        Object childModel = childEditPart.getModel();
        if (childModel instanceof GmQualifierGroup) {
            GmAssociation gmAssoc = (GmAssociation) getModel();
            GmQualifierGroup gmQual = (GmQualifierGroup) childModel;
            if (gmQual.getRepresentedRef().equals(gmAssoc.getRepresentedRoleRef())) {
                // qualifier of the role ==> source side
                setSourceQualifier(childEditPart);
            } else {
                // target side
                setTargetQualifier(childEditPart);
            }
        } else {
            super.addChildVisual(childEditPart, index);
        }
        
    }

    @objid ("33e2c7c4-55b7-11e2-877f-002564c97630")
    private void setSourceQualifier(final EditPart childEditPart) {
        LinkFigure connection = (LinkFigure) getFigure();
        IFigure childFigure = ((AbstractGraphicalEditPart) childEditPart).getFigure();
        RotatableDecoration oldDecoration = connection.getSourceDecoration();
        
        if (oldDecoration instanceof CompositeDecoration) {
            ((CompositeDecoration) oldDecoration).setQualifier(childFigure);
        } else {
            CompositeDecoration compoDeco = new CompositeDecoration();
            connection.setSourceDecoration(compoDeco);
            compoDeco.setQualifier(childFigure);
            if (oldDecoration != null)
                compoDeco.setDecoration(oldDecoration);
        }
        
    }

    @objid ("33e2c7c8-55b7-11e2-877f-002564c97630")
    private void setTargetQualifier(final EditPart childEditPart) {
        LinkFigure connection = (LinkFigure) getFigure();
        IFigure childFigure = ((AbstractGraphicalEditPart) childEditPart).getFigure();
        RotatableDecoration oldDecoration = connection.getTargetDecoration();
        
        if (oldDecoration instanceof CompositeDecoration) {
            ((CompositeDecoration) oldDecoration).setQualifier(childFigure);
        } else {
            CompositeDecoration compoDeco = new CompositeDecoration();
            connection.setTargetDecoration(compoDeco);
            compoDeco.setQualifier(childFigure);
            if (oldDecoration != null)
                compoDeco.setDecoration(oldDecoration);
        }
        
    }

    @objid ("33e2c7cc-55b7-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(final EditPart childEditPart) {
        Object childModel = childEditPart.getModel();
        if (childModel instanceof GmQualifierGroup) {
            GmAssociation gmAssoc = (GmAssociation) getModel();
            GmQualifierGroup gmQual = (GmQualifierGroup) childModel;
            if (gmQual.getRepresentedRef().equals(gmAssoc.getRepresentedRoleRef())) {
                // qualifier of the role ==> source side
                removeSourceQualifier();
            } else {
                // target side
                removeTargetQualifier();
            }
        } else {
            super.removeChildVisual(childEditPart);
        }
        
    }

    @objid ("33e2c7d1-55b7-11e2-877f-002564c97630")
    private void removeSourceQualifier() {
        LinkFigure connection = (LinkFigure) getFigure();
        RotatableDecoration oldDecoration = connection.getSourceDecoration();
        
        if (oldDecoration instanceof CompositeDecoration) {
            connection.setSourceDecoration(((CompositeDecoration) oldDecoration).getDecoration());
        } else {
            //throw new IllegalStateException("There is no source composite decoration to remove the figure from.");
        }
        
    }

    @objid ("33e2c7d3-55b7-11e2-877f-002564c97630")
    private void removeTargetQualifier() {
        LinkFigure connection = (LinkFigure) getFigure();
        RotatableDecoration oldDecoration = connection.getTargetDecoration();
        
        if (oldDecoration instanceof CompositeDecoration) {
            connection.setTargetDecoration(((CompositeDecoration) oldDecoration).getDecoration());
        } else {
            //throw new IllegalStateException("There is no target composite decoration to remove the figure from.");
        }
        
    }

}
