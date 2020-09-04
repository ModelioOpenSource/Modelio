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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.throwcatch;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Helper class to manage the visual feedback of the 'insert throw/catch nodes in flow' interaction.
 */
@objid ("7ba2b2de-cea0-4e10-a604-f216751f427b")
class InsertThrowCatchFeedback {
    @objid ("3a1dec2b-980a-420c-857e-1275cf623ba6")
    private static final int FEEDBACK_NODE_WIDTH = 60;

    @objid ("b77dadee-0cfb-4706-b661-397853773437")
    private final IFigure feedbackLayer;

    @objid ("b014f681-4481-4c8e-a33a-590865c70b7e")
    private Shape insertedNodeFeedback;

    @objid ("75da5d90-afb9-485f-84f4-da9b7c8e32a8")
    private Polyline flowInFeedback;

    @objid ("0e0769fe-679c-4546-8e2f-68a4b4aee61d")
    private Polyline flowOutFeedback;

    @objid ("665bb658-6aba-4875-bccc-291d089f3b2c")
    private ZoomManager zoomManager;

    @objid ("922e76f7-1038-4acc-8cb1-81f6c605b27e")
    public InsertThrowCatchFeedback(IFigure feedbackLayer, ZoomManager zoomManager) {
        this.feedbackLayer = feedbackLayer;
        this.zoomManager = zoomManager;
    }

    /**
     * Shows the creation feedback.
     * 
     * @param flowFigure the sequence flow to insert the node in.
     * @param mouseLocation the current mouse location.
     */
    @objid ("2f215aaa-2561-45a4-ad0e-f2f2c2168394")
    public void show(IFigure flowFigure, Point mouseLocation) {
        // If any, hide previous feedback
        if (this.insertedNodeFeedback != null) {
            hide();
        }
        
        // Make sure the figure is a proper link
        if (!(flowFigure instanceof AbstractPointListShape)) {
            return;
        }
        
        if (this.insertedNodeFeedback == null) {
            // location is mouse coord here
            Point location = mouseLocation.getCopy();
            this.feedbackLayer.translateToRelative(location);
        
            // Draw the feedback elements in absolute coordinates first
            this.insertedNodeFeedback = getNodeFigure();
            Rectangle nodeBounds = this.insertedNodeFeedback.getBounds();
            this.insertedNodeFeedback.setForegroundColor(ColorConstants.blue);
            this.insertedNodeFeedback.setBackgroundColor(ColorConstants.blue);
            this.insertedNodeFeedback.setLineWidth(1);
            this.insertedNodeFeedback.setAlpha(128);
            this.insertedNodeFeedback
                    .setBounds(new Rectangle(location.x - nodeBounds.width / 2, location.y - nodeBounds.height / 2, nodeBounds.width, nodeBounds.height));
            this.insertedNodeFeedback.setVisible(true);
            this.insertedNodeFeedback.setOpaque(true);
        
            this.flowInFeedback = new Polyline();
            this.flowInFeedback.setForegroundColor(ColorConstants.blue);
            this.flowInFeedback.setLineWidth(2);
            this.flowInFeedback.setLineStyle(SWT.LINE_DASH);
            this.flowInFeedback.setAlpha(128);
        
            Point flowInStartPoint = ((AbstractPointListShape) flowFigure).getStart().getCopy();
            flowFigure.translateToAbsolute(flowInStartPoint);
            this.feedbackLayer.translateToRelative(flowInStartPoint);
        
            Point flowInEndPoint = GeomUtils.getLineIntersection(nodeBounds.getCenter(), flowInStartPoint, nodeBounds);
            if (flowInEndPoint == null) {
                // Fallback, just in case GeomUtils fails
                flowInEndPoint = flowInStartPoint.getCopy();
            }
        
            this.flowInFeedback.setStart(flowInStartPoint);
            this.flowInFeedback.setEnd(flowInEndPoint);
        
            this.flowOutFeedback = new Polyline();
            this.flowOutFeedback.setForegroundColor(ColorConstants.blue);
            this.flowOutFeedback.setLineWidth(2);
            this.flowOutFeedback.setLineStyle(SWT.LINE_DASH);
            this.flowOutFeedback.setAlpha(128);
        
            Point flowOutEndPoint = ((AbstractPointListShape) flowFigure).getEnd().getCopy();
            flowFigure.translateToAbsolute(flowOutEndPoint);
            this.feedbackLayer.translateToRelative(flowOutEndPoint);
        
            Point flowOutStartPoint = GeomUtils.getLineIntersection(nodeBounds.getCenter(), flowOutEndPoint, nodeBounds);
            if (flowOutStartPoint == null) {
                // Fallback, just in case GeomUtils fails
                flowOutStartPoint = flowOutEndPoint.getCopy();
            }
        
            this.flowOutFeedback.setStart(flowOutStartPoint);
            this.flowOutFeedback.setEnd(flowOutEndPoint);
        
        }
        this.feedbackLayer.add(this.flowInFeedback);
        this.feedbackLayer.add(this.flowOutFeedback);
        this.feedbackLayer.add(this.insertedNodeFeedback);
    }

    /**
     * Hide the creation feedback.
     */
    @objid ("48a279a9-87c4-4d8e-8417-5a5b9d20b041")
    public void hide() {
        if (this.flowInFeedback != null) {
            this.feedbackLayer.remove(this.flowInFeedback);
            this.flowInFeedback = null;
        }
        if (this.insertedNodeFeedback != null) {
            this.feedbackLayer.remove(this.insertedNodeFeedback);
            this.insertedNodeFeedback = null;
        }
        if (this.flowOutFeedback != null) {
            this.feedbackLayer.remove(this.flowOutFeedback);
            this.flowOutFeedback = null;
        }
    }

    @objid ("d6545742-bc90-4e34-a501-edf62e7109ce")
    private Shape getNodeFigure() {
        Dimension nodeSize = new Dimension(InsertThrowCatchFeedback.FEEDBACK_NODE_WIDTH / 2, InsertThrowCatchFeedback.FEEDBACK_NODE_WIDTH / 2);
        nodeSize.scale(this.zoomManager.getZoom()); // take zoom into account
        
        Ellipse r = new Ellipse();
        r.setSize(nodeSize);
        return r;
    }

}
