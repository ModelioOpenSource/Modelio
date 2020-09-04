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

package org.modelio.diagram.editor.statik.elements.informationflowlink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.extensions.FractionalConnectionLocator;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Edit part for {@link GmInformationFlowLink}.
 * <p>
 * A binding link is always dashed.
 * 
 * @author cmarin
 */
@objid ("35094653-55b7-11e2-877f-002564c97630")
public class InformationFlowLinkEditPart extends LinkEditPart {
    @objid ("a55cea89-55c2-11e2-9337-002564c97630")
    private DefaultPolygonDecoration deco;

    @objid ("3509465a-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        // Set style independent properties
        connection.setLineStyle(SWT.LINE_DASH);
        
        // Navigability arrow toward target
        final DefaultPolylineDecoration arrow = new DefaultPolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(9, 4);
        arrow.setOpaque(false);
        arrow.setBackgroundColor(null);
        arrow.setFill(false);
        connection.setTargetDecoration(arrow);
        
        // Set style dependent properties
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("3509465f-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        final GmInformationFlowLink gmModel = (GmInformationFlowLink) getModel();
        
        PolylineConnection connection = (PolylineConnection) aFigure;
        if (style.getBoolean(StyleKey.getInstance("INFOFLOWLINK_SHOWFLOWARROW"))) {
            showFlowArrow(connection);
        } else {
            hideFlowArrow(connection);
        }
        
        if (gmModel.getHeader() != null) {
            gmModel.getHeader().setShowLabel(style.getBoolean(gmModel.getStyleKey(MetaKey.SHOWLABEL)));
        }
        
        if (this.deco != null) {
            if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null)
                this.deco.setFillColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
        }
    }

    @objid ("35094668-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("1ac0d31e-9146-4f4f-9a76-64e16825d496")
    private void showFlowArrow(final PolylineConnection connection) {
        if (this.deco != null)
            hideFlowArrow(connection);
        
        this.deco = new DefaultPolygonDecoration();
        this.deco.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        this.deco.setScale(12, 10);
        this.deco.setOpaque(true);
        this.deco.setFill(true);
        
        FractionalConnectionLocator locator = new FractionalConnectionLocator(connection, 0.66, true);
        connection.add(this.deco, locator);
    }

    @objid ("d4a7fe11-317d-45a3-84b1-954f3770bb3c")
    private void hideFlowArrow(final PolylineConnection connection) {
        if (this.deco != null) {
            connection.remove(this.deco);
            this.deco = null;
        }
    }

}
