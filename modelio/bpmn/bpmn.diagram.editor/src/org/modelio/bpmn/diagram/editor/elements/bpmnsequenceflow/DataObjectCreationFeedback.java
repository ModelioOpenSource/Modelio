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

package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.swt.SWT;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.BpmnDataFigure;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Helper class to manage the visual feedback of the 'create data object on flow' interaction.
 * <p>
 * Draws a {@link BpmnDataFigure} linked to the selected sequence flow in its center.
 * </p>
 */
@objid ("14ca992e-f697-4546-926a-a67e80c4ec8a")
class DataObjectCreationFeedback {
    @objid ("d4dca260-0ae7-4620-a33c-979868d7b0dc")
    private static final int FEEDBACK_NODE_WIDTH = 40;

    @objid ("7b09e5ef-1178-42b4-808e-db4baf6a3b07")
    private static final int FEEDBACK_NODE_HEIGHT = 55;

    @objid ("61848f45-0c5d-4743-8855-a35b00d10cad")
    private static final int FEEDBACK_LINK_LENGHT = 24;

    @objid ("74d740dd-dc6e-438f-b297-e96a4a31634e")
    private final IFigure feedbackLayer;

    @objid ("5aa38a52-0106-4b4b-9087-53d6dba456e1")
    private BpmnDataFigure nodeFeedback;

    @objid ("28e340bb-4a7d-41d0-a56d-111ad06acf9a")
    private Polyline linkFeedback;

    @objid ("80e84a57-1021-4fec-8c1e-4ac32d7236eb")
    private ZoomManager zoomManager;

    @objid ("5a354aea-8bcb-48a7-9b03-7d67e67b7ae0")
    public DataObjectCreationFeedback(IFigure feedbackLayer, ZoomManager zoomManager) {
        this.feedbackLayer = feedbackLayer;
        this.zoomManager = zoomManager;
    }

    /**
     * Shows the creation feedback.
     * 
     * @param flowFigure the sequence flow to create the data object on.
     * @param mouseLocation the current mouse location.
     */
    @objid ("7b3b40b5-11fe-40be-8029-ba0b7324b16f")
    public void show(IFigure flowFigure, Point mouseLocation) {
        // If any, hide previous feedback
        if (this.nodeFeedback != null) {
            hide();
        }
        
        // Make sure the figure is a proper link
        if (!(flowFigure instanceof AbstractPointListShape)) {
            return;
        }
        
        if (this.nodeFeedback == null) {
            // Get the flow's center point
            Point flowCenter = LineCoordinatesHelper.getCenterPoint((AbstractPointListShape) flowFigure);
            flowFigure.translateToAbsolute(flowCenter);
            this.feedbackLayer.translateToRelative(flowCenter);
        
            // Node feedback size is 40 * 55, with the zoom taken into account
            Dimension nodeSize = new Dimension(DataObjectCreationFeedback.FEEDBACK_NODE_WIDTH, DataObjectCreationFeedback.FEEDBACK_NODE_HEIGHT);
            nodeSize.scale(this.zoomManager.getZoom());
        
            // Node feedback is horizontally centered with the link, and vertically offset 24 px downwards, with the zoom taken into account
            Point nodePosition = new Point(flowCenter.x - nodeSize.width / 2, flowCenter.y + ((int) (Math.floor(DataObjectCreationFeedback.FEEDBACK_LINK_LENGHT * this.zoomManager.getZoom()))));
        
            // Draw the node feedback
            Rectangle nodeBounds = new Rectangle(nodePosition, nodeSize);
            this.nodeFeedback = new BpmnDataFigure();
            this.nodeFeedback.setSize(nodeSize);
            this.nodeFeedback.setForegroundColor(ColorConstants.blue);
            this.nodeFeedback.setBackgroundColor(ColorConstants.blue);
            this.nodeFeedback.setLineWidth(1);
            this.nodeFeedback.setFillAlpha(128);
            this.nodeFeedback.setBounds(nodeBounds);
            this.nodeFeedback.setVisible(true);
            this.nodeFeedback.setOpaque(true);
        
            // Link feedback goes from the flow's center to the node's upper side.
            Point flowInStartPoint = flowCenter.getCopy();
            Point flowInEndPoint = GeomUtils.getLineIntersection(nodeBounds.getCenter(), flowInStartPoint, nodeBounds);
            if (flowInEndPoint == null) {
                // Fallback, just in case GeomUtils fails
                flowInEndPoint = flowInStartPoint.getCopy();
            }
        
            // Draw the link feedback
            this.linkFeedback = new Polyline();
            this.linkFeedback.setForegroundColor(ColorConstants.blue);
            this.linkFeedback.setLineWidth(2);
            this.linkFeedback.setLineStyle(SWT.LINE_DASH);
            this.linkFeedback.setAlpha(128);
            this.linkFeedback.setStart(flowInStartPoint);
            this.linkFeedback.setEnd(flowInEndPoint);
        }
        
        this.feedbackLayer.add(this.nodeFeedback);
        this.feedbackLayer.add(this.linkFeedback);
    }

    /**
     * Hide the creation feedback.
     */
    @objid ("3371e0fb-dd91-4d5e-9584-ec01d213d360")
    public void hide() {
        if (this.linkFeedback != null) {
            this.feedbackLayer.remove(this.linkFeedback);
            this.linkFeedback = null;
        }
        if (this.nodeFeedback != null) {
            this.feedbackLayer.remove(this.nodeFeedback);
            this.nodeFeedback = null;
        }
    }

    @objid ("34a4820b-483a-4af8-8d1d-e43c88e3aa56")
    private static class LineCoordinatesHelper {
        /**
         * Preallocated local variable used in getCenterPoint()
         */
        @objid ("c11324e5-a020-46d1-b680-16103baba1c0")
        private static final Point P1 = new Point();

        /**
         * Preallocated local variable used in getCenterPoint()
         */
        @objid ("72c32fa8-bae2-41c7-b04e-e80f68d90d7b")
        private static final Point P2 = new Point();

        /**
         * Compute the center point of a line figure.
         * 
         * @param flowFigure a flow figure.
         * @return a point.
         */
        @objid ("c7c65d2a-e67f-46fe-9379-0c9fde3333a2")
        public static Point getCenterPoint(AbstractPointListShape flowFigure) {
            final PointList points = flowFigure.getPoints();
            final Point center = new Point();
            final long theLength = length(points);
            
            long remainingLength = Math.round(0.5 * theLength);
            
            final int n = points.size() - 1;
            for (int i = 0; i < n; i++) {
                points.getPoint(LineCoordinatesHelper.P1, i);
                points.getPoint(LineCoordinatesHelper.P2, i + 1);
            
                final long nextLength = Math.round(LineCoordinatesHelper.P2.getDistance(LineCoordinatesHelper.P1));
            
                if (nextLength >= remainingLength) {
                    pointOn(remainingLength, LineCoordinatesHelper.P1, LineCoordinatesHelper.P2, center);
                    return center;
                } else {
                    remainingLength -= nextLength;
                }
            }
            
            throw new IllegalStateException("Failed to compute location");
        }

        /**
         * Gets the point on the line segment at the given distance away from the key point.
         * 
         * @param theDistance <code>long</code> distance along the line
         * @param start start of the segment
         * @param end end of the segment
         * @param ptResult <code>Point</code> where the resulting calculating value is stored.
         * @return <code>boolean</code> <code>true</code> if point can be calculated, <code>false</code> otherwise.
         */
        @objid ("63164e9f-b13c-4b12-bb43-118c0374214e")
        private static boolean pointOn(final long theDistance, final Point start, final Point end, final Point ptResult) {
            float m, dx_float;
            int dx, dy, startX = 0, startY = 0, otherX = 0, otherY = 0;
            
            // Set the point to offset from and the other point used to determine
            // which direction dx and dy should be applied to get a point on the
            // line.
            
            startX = start.x;
            startY = start.y;
            otherX = end.x;
            otherY = end.y;
            
            m = slope(start, end); // get the slope of this line
            
            // Find dx and dy - the delta x and y to get from the endpoint to the
            // point on the line at the specified distance away.
            // The following is based on solving 2 equations with 2 unknowns:
            // dy/dx = m (m is slope of line)
            // dy^2 + dx^2 = dist^2
            //
            final double d_squared = (float) theDistance * (float) theDistance;
            final double m_squared = m * m;
            
            // Add .5 so result is rounded to nearest integer when cast
            dx_float = (float) Math.sqrt(d_squared / (m_squared + 1.0));
            dx = (int) (dx_float + 0.5);
            dy = (int) (Math.sqrt(d_squared * m_squared / (m_squared + 1.0)) + 0.5);
            
            /* negative distance means we want point off the line */
            if (theDistance < 0) {
                dx = -dx;
                dy = -dy;
            }
            
            ptResult.x = ((startX > otherX) ? startX - dx : startX + dx);
            ptResult.y = ((startY > otherY) ? startY - dy : startY + dy);
            boolean in_line;
            if (startX > otherX) {
                in_line = ptResult.x >= otherX;
            } else {
                in_line = ptResult.x <= otherX;
            }
            if (in_line) {
                if (startY > otherY) {
                    in_line = ptResult.y >= otherY;
                } else {
                    in_line = ptResult.y <= otherY;
                }
            }
            return in_line;
        }

        /**
         * Calculates the slope of the line segment (y=ax+b)
         * 
         * @param start start of segment
         * @param end end of segment
         * @return <code>float</code> the slope of the segment. If the slope is not defined such as when the line segment is
         * vertical, then the constant <code>BIGSLOPE</code> is returned to avoid divide by zero errors.
         */
        @objid ("a7f15b1c-a46d-4780-ac56-97638d97b0ac")
        private static final float slope(final Point start, final Point end) {
            if (end.x == start.x) {
                return 9999;
            }
            return (float) (end.y - start.y) / (float) (end.x - start.x);
        }

        /**
         * Get the length of the given point list.
         * 
         * @param points The point list
         * @return the length
         */
        @objid ("33742002-9057-483a-aa94-cd9f09a1c54d")
        private static final long length(final PointList points) {
            long ret = 0;
            final int n = points.size() - 1;
            for (int i = 0; i < n; i++) {
                points.getPoint(LineCoordinatesHelper.P1, i);
                points.getPoint(LineCoordinatesHelper.P2, i + 1);
            
                ret += Math.round(LineCoordinatesHelper.P2.getDistance(LineCoordinatesHelper.P1));
            }
            return ret;
        }

    }

}
