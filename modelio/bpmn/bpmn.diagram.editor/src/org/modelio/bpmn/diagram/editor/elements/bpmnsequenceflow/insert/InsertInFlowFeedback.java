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
package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.insert;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.swt.SWT;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.insert.InsertInFlowEditPolicy.BpmnNodeType;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Helper class to manage the visual feedback of the 'insert node in flow' interaction.
 */
@objid ("3e7c9948-9401-451e-bb40-32a92bc19224")
class InsertInFlowFeedback {
    @objid ("a1da135c-d25e-4fd0-ba8d-9fdc56ea63f4")
    private final IFigure feedbackLayer;

    @objid ("bd02ca60-f9ab-47e0-9288-a746ffd3db41")
    private Shape insertedNodeFeedback;

    @objid ("582868ff-784b-45ff-84be-8b4bff3dead3")
    private Polyline flowInFeedback;

    @objid ("e581f2ce-d72f-4c71-a00d-44c76c62bb48")
    private Polyline flowOutFeedback;

    @objid ("9148f96b-6372-4a34-ac2e-48bd2eb9eed7")
    private ZoomManager zoomManager;

    @objid ("020eb172-dc30-4e18-8f7a-83e9ce63252c")
    public  InsertInFlowFeedback(IFigure feedbackLayer, ZoomManager zoomManager) {
        this.feedbackLayer = feedbackLayer;
        this.zoomManager = zoomManager;
        
    }

    /**
     * Shows the creation feedback.
     * @param flowFigure the sequence flow to insert the node in.
     * @param insertLocation the place to use use as a center for the new node.
     */
    @objid ("feaa4601-3908-48ce-a8af-1a3f0a771063")
    public void show(IFigure flowFigure, Point insertLocation, BpmnNodeType type) {
        // Make sure the figure is a proper link
        if (!(flowFigure instanceof AbstractPointListShape)) {
            return;
        }
        
        // If any, hide previous feedback
        if (this.insertedNodeFeedback != null) {
            hide();
        }
        
        if (this.insertedNodeFeedback == null) {
            Point location = insertLocation.getCopy();
            this.feedbackLayer.translateToRelative(location);
        
            // Draw the feedback elements in absolute coordinates first
            this.insertedNodeFeedback = getNodeFigure(flowFigure, type);
            Rectangle nodeBounds = this.insertedNodeFeedback.getBounds();
            this.insertedNodeFeedback.setForegroundColor(ColorConstants.blue);
            this.insertedNodeFeedback.setBackgroundColor(ColorConstants.blue);
            this.insertedNodeFeedback.setLineWidth(1);
            this.insertedNodeFeedback.setAlpha(128);
            this.insertedNodeFeedback.setBounds(new Rectangle(location.x - nodeBounds.width / 2, location.y - nodeBounds.height / 2, nodeBounds.width, nodeBounds.height));
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
    @objid ("811d1d6d-02d2-43a1-bab2-32978a073b42")
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

    @objid ("f52710f1-c06c-4898-82b6-5fba80b83b14")
    private void scaleToRelative(IFigure flowFigure, Translatable t) {
        flowFigure.translateToAbsolute(t);
        this.feedbackLayer.translateToRelative(t);
        
    }

    @objid ("3b08f38e-0da7-4fd9-815c-405ec3aaa050")
    private Shape getNodeFigure(IFigure flowFigure, BpmnNodeType type) {
        Dimension nodeSize;
        switch (type) {
        case ACTIVITY:
            nodeSize = new Dimension(type.width, type.height);
            scaleToRelative(flowFigure, nodeSize);
            //nodeSize.scale(this.zoomManager.getZoom()); // take zoom into account
        
            RoundedRectangle rr = new RoundedRectangle();
            rr.setSize(nodeSize);
            return rr;
        case GATEWAY:
            int a;
            if (false) {
                a = type.width;
                a = (int) Math.floor(a * this.zoomManager.getZoom()); // take zoom into account
            } else {
                nodeSize = new Dimension(type.width, type.width);
                scaleToRelative(flowFigure, nodeSize);
                a = nodeSize.width;
            }
        
            PolygonShape diamond = new PolygonShape();
            diamond.setStart(new Point(a, a / 2));
            diamond.addPoint(new Point(a / 2, a));
            diamond.addPoint(new Point(0, a / 2));
            diamond.addPoint(new Point(a / 2, 0));
            diamond.addPoint(new Point(a, a / 2));
            diamond.setEnd(new Point(a, a / 2));
            diamond.setSize(a, a);
            return diamond;
        case EVENT:
            nodeSize = new Dimension(type.width, type.height);
            if (false) {
                nodeSize.scale(this.zoomManager.getZoom()); // take zoom into account
            } else {
                scaleToRelative(flowFigure, nodeSize);
            }
        
            Ellipse circle = new Ellipse();
            circle.setSize(nodeSize);
            return circle;
        default:
            nodeSize = new Dimension(type.width, type.height);
            if (false) {
                nodeSize.scale(this.zoomManager.getZoom()); // take zoom into account
            } else {
                scaleToRelative(flowFigure, nodeSize);
            }
        
            RectangleFigure r = new RectangleFigure();
            r.setSize(nodeSize);
            return r;
        
        }
        
    }

}
