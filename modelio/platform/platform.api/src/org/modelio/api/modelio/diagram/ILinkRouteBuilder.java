/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.modelio.diagram;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;

/**
 * Interface to build easily a connection route from scratch.
 * <p>
 * Contrary to {@link ILinkRoute} this service takes into account
 * the real anchor location to build orthogonal segments.
 * <h2>Usage sample</h2>
 * <pre><code>
 * IDiagramLink link = ...
 * link.buildRoute()
 * .setSourceAnchor(LinkAnchorFace.EAST, true)
 * .setTargetAnchor(LinkAnchorFace.WEST, true)
 * .addHorizontalSegment(200, false)
 * .finishVerticalThenHorizontal(false)
 * .apply();
 * </code></pre>
 * 
 * @since 5.1.0
 */
@objid ("3f3ae2f5-9ccd-41fd-a8ca-cc0845bb53f6")
public interface ILinkRouteBuilder {
    /**
     * Request to anchor the connection source point at a given location.
     * <p>
     * The real anchor location may be different from the requested one.
     * @param loc the point location
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("d8c022f6-03c0-4056-84cf-25acbc1b24d9")
    Target setSourceAnchor(Point loc, boolean sliding);

    /**
     * Request to anchor the connection source point on the middle of a given face.
     * <p>
     * The real anchor location may be different from the requested one.
     * @param face the desired false
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("b82ce465-676a-4b4e-a078-2089596efa20")
    Target setSourceAnchor(LinkAnchorFace face, boolean sliding);

    /**
     * Request to anchor the connection source point on the nearst face toward target node.
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("f4beb015-e7fa-4cc7-b351-e27e6d3395fb")
    Target setSourceAnchorFaceTarget(boolean sliding);

    /**
     * Request to anchor the connection source point on at a fraction of a given face.
     * <p>
     * Depending on the face the fraction starts on the left or from the top.
     * <p>
     * The real anchor location may be different from the requested one.
     * @param face the desired false
     * @param numerator the fraction numerator. 0 means top or left, 'denominator' means bottom or right.
     * @param denominator the fraction denominator.
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("591d79bd-c855-4c89-9f46-4310398c53cf")
    Target setSourceAnchor(LinkAnchorFace face, int numerator, int denominator, boolean sliding);

    /**
     * ILinkRouteBuilder usage example
     */
    @objid ("c507d05b-7bbb-4327-aa70-1196bd85aa63")
    static class Example {
        @objid ("534cb61f-36c3-45a3-beef-97c3560c3efe")
        void test(ILinkRouteBuilder ex) {
            ex.setSourceAnchorFaceTarget(true)
            .setTargetAnchor(LinkAnchorFace.WEST, true)
            .addHorizontalSegment(200, false)
            .finishVerticalThenHorizontal(false)
            .apply();
            
        }

    }

    /**
     * {@link ILinkRouteBuilder} at target anchor state.
     * <p>
     * Choose a target anchor to get further.
     * 
     * @since 5.1.0
     */
    @objid ("82ddead6-fd80-4d0b-96e9-7c4c3755910d")
    interface Target {
        /**
         * Request to anchor the connection target point at a given location.
         * <p>
         * The real anchor location may be different from the requested one.
         * @param loc the point location
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("492227fd-8e6b-48f4-9556-90ef9b08685e")
        BendPoints setTargetAnchor(Point loc, boolean sliding);

        /**
         * Request to anchor the connection target point on the middle of a given face.
         * <p>
         * The real anchor location may be different from the requested one.
         * @param face the desired false
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("791b9ad8-be3b-4272-8cd4-b93f2ee8cde7")
        BendPoints setTargetAnchor(LinkAnchorFace face, boolean sliding);

        /**
         * Request to anchor the connection target point on the nearest face toward source node.
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("e47b71c4-1603-4568-a505-04ca53921957")
        BendPoints setTargetAnchorFaceSource(boolean sliding);

        /**
         * Request to anchor the connection target point on at a fraction of a given face.
         * <p>
         * Depending on the face the fraction starts on the left or from the top.
         * <p>
         * The real anchor location may be different from the requested one.
         * @param face the desired false
         * @param numerator the fraction numerator. 0 means top or left, 'denominator' means bottom or right.
         * @param denominator the fraction denominator.
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("82be7418-07a4-4eee-9768-1bbd740676a6")
        BendPoints setTargetAnchor(LinkAnchorFace face, int numerator, int denominator, boolean sliding);
}
    

    /**
     * {@link ILinkRouteBuilder} at bend points state.
     * <p>
     * User may add bend points or fixed bend points using the helper methods.
     * <p>
     * Finish and apply the route with {@link #apply()}.
     * 
     * @since 5.1.0
     */
    @objid ("3c003dfc-a481-40c8-b132-fb7ab76fb956")
    interface BendPoints {
        /**
         * Get a direct access to all points of the connection.
         * <p>
         * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
         * It does not modify the connection from which this path was built.
         * The path must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
         * @return the points in coordinates relative to the diagram.
         */
        @objid ("89f0f506-a09e-4f4f-89cc-5acbc20dfe35")
        List<ILinkPoint> getAllPoints();

        /**
         * Create a new bend point.
         * @param loc the point location
         * @return the route builder to use for next steps.
         */
        @objid ("22624724-4434-4964-b3e1-6d0c4aa4f372")
        BendPoints addBendPoint(Point loc);

        /**
         * Create a new fixed bend point.
         * @param loc the point location
         * @return the route builder to use for next steps.
         */
        @objid ("5aeb4c4f-2088-4d68-a20f-bbac98f2afd6")
        BendPoints addFixedPoint(Point loc);

        /**
         * Add one horizontal segment with y aligned with previous point.
         * @param nextBendPointX the bend point X position.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("7c16675d-68ff-4876-9d43-90a656bc39a2")
        BendPoints addHorizontalSegment(int nextBendPointX, boolean fixed);

        /**
         * Add one vertical segment with x aligned with previous point.
         * @param nextBendPointY the bend point Y position.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("395204f8-5ed3-47ed-95c8-8f1cd3c54062")
        BendPoints addVerticalSegment(int nextBendPointY, boolean fixed);

        /**
         * Add 2 segment : one horizontal with y aligned with previous point,
         * one vertical with x aligned with target anchor.
         * <p>
         * You should call {@link #apply()} after this method.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("d5400929-4f09-4a75-a989-4c81f84fdbec")
        BendPoints finishHorizontalThenVertical(boolean fixed);

        /**
         * Add 2 segment : one vertical with x aligned with previous point,
         * one horizontal with y aligned with target anchor.
         * <p>
         * You should call {@link #apply()} after this method.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("d9766ca1-d023-402c-8f6c-1f0db91f606f")
        BendPoints finishVerticalThenHorizontal(boolean fixed);

        /**
         * Build the route and apply it to the connection it was created from.
         */
        @objid ("8b084a43-661f-4be7-beb8-a7d52e6a6dad")
        void apply();

        /**
         * Add many bend points at once.
         * @param points the bend points locations.
         * @return this route instance
         */
        @objid ("ca60fcd0-5196-4bf7-8455-16479b2f8175")
        default BendPoints addBendPoints(Collection<Point> points) {
            for (Point p : points) {
                addBendPoint(p);
            }
            return this;
        }

        /**
         * Add many fixed bend points at once.
         * @param points the bend points locations.
         * @return this route instance
         */
        @objid ("140bb61e-9bef-4b80-aa9a-4a6758fb2820")
        default BendPoints addFixedPoints(Collection<Point> points) {
            for (Point p : points) {
                addFixedPoint(p);
            }
            return this;
        }
}
    
}

