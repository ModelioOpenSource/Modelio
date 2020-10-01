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

package org.modelio.uml.communicationdiagram.editor.elements.communicationmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Edit part for {@link GmCommunicationSentMessageArrow} and {@link GmCommunicationInvertedMessageArrow}.
 * <p>
 * Creates a filled arrow on the connection owning it. The arrow is filled with the line color.
 * 
 * @author cmarin
 */
@objid ("7a3a6615-55b6-11e2-877f-002564c97630")
public class CommunicationMessageArrowEditPart extends AbstractNodeEditPart {
    @objid ("d353868a-36e5-446e-bbe9-c46dcb73a74c")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { 1, 1, 0, 0, 1, -1, 0, 0, 2, 0 });

    /**
     * The arrow is not selectable.
     */
    @objid ("7a3bec9d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    @objid ("7a3beca3-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final DefaultPolylineDecoration deco = new DefaultPolylineDecoration();
        
        deco.setTemplate(TRIANGLE_TIP);
        deco.setScale(7, 6);
        deco.setOpaque(true);
        deco.setLineWidth(5);
        deco.setFill(false);
        
        refreshFromStyle(deco, getModelStyle());
        return deco;
    }

    @objid ("7a3beca8-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
    }

    @objid ("7a3becb1-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
    }

}
