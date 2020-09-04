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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Edit part for {@link GmBpmnMessageFlow}.
 */
@objid ("616b629a-55b6-11e2-877f-002564c97630")
public class BpmnMessageFlowEditPart extends LinkEditPart {
    @objid ("616b629e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        connection.setTargetDecoration(new SolidArrowDecoration());
        connection.setSourceDecoration(new SolidCircleDeco());
        
        // Make sure the arrow has appropriate style
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("616ce945-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedMessageFlowStartEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy(true));
    }

    @objid ("448261e7-a7e6-4c2e-94b8-f350adc4127a")
    @Override
    public GmBpmnMessageFlow getModel() {
        return (GmBpmnMessageFlow) super.getModel();
    }

    /**
     * Solid triangle with a white background, scaling when the line width changes.
     */
    @objid ("6aa49318-d450-473c-98c6-ec4767634bc5")
    public static class SolidArrowDecoration extends DefaultPolygonDecoration {
        @objid ("17684db0-022a-450f-9bab-6d166ce659e2")
        public SolidArrowDecoration() {
            super();
            setTemplate(PolygonDecoration.TRIANGLE_TIP);
            setOpaque(true);
            setFill(true);
            setBackgroundColor(ColorConstants.white);
        }

        @objid ("da0565a5-ad43-460b-b9ea-7284001079a0")
        @Override
        public void setLinePattern(LinePattern lineStyle) {
            super.setLinePattern(LinePattern.LINE_SOLID);
        }

        @objid ("ff7e5bf8-4c9e-4b2a-b9d4-47c57d4f35d8")
        @Override
        public void setLineWidth(int w) {
            super.setLineWidth(w);
            setScale(8 + w, 5 + w);
        }

    }

    /**
     * Solid circle with a white background, scaling when the line width changes.
     */
    @objid ("3044af17-912a-49ae-8a30-6f24a3f73560")
    public static class SolidCircleDeco extends EllipseFigure implements RotatableDecoration {
        @objid ("a7b25014-d5e8-4f8e-8eda-1c8ce2911649")
        public SolidCircleDeco() {
            super();
            setOpaque(true);
            setBackgroundColor(ColorConstants.white);
        }

        @objid ("8af8c16b-7193-41ce-9873-359806c6373f")
        @Override
        public void setReferencePoint(final Point p) {
            // Do nothing
        }

        @objid ("0756cdcf-4c25-4ec0-96ec-44b154b13697")
        @Override
        public void setLocation(final Point p) {
            Dimension dim = getBounds().getSize().scale(0.5);
            super.setLocation(new Point(p.x - dim.width, p.y - dim.height));
        }

        @objid ("ab473777-8859-40f6-aba9-f0f08cb42018")
        @Override
        public void setLinePattern(LinePattern lineStyle) {
            super.setLinePattern(LinePattern.LINE_SOLID);
        }

        @objid ("c808e0dd-c484-4f8e-bf60-d5885e930dbe")
        @Override
        public void setLineWidth(int lineWidth) {
            super.setLineWidth(lineWidth);
            int radius = Math.max(11, lineWidth * 4 + 1);
            setSize(radius, radius);
        }

    }

}
