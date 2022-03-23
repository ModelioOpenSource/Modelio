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
@objid ("bcfebe9e-b6b6-4c1e-a0c9-7e13c61b7f56")
public interface ILinkRouteBuilder {
    /**
     * Request to anchor the connection source point at a given location.
     * <p>
     * The real anchor location may be different from the requested one.
     * @param loc the point location
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("20166f0f-e8b4-426c-b2a6-90bc147fd78d")
    Target setSourceAnchor(Point loc, boolean sliding);

    /**
     * Request to anchor the connection source point on the middle of a given face.
     * <p>
     * The real anchor location may be different from the requested one.
     * @param face the desired false
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("4f89246e-3707-44a6-93ef-e3497e0c72b4")
    Target setSourceAnchor(LinkAnchorFace face, boolean sliding);

    /**
     * Request to anchor the connection source point on the nearst face toward target node.
     * @param sliding true to request a sliding anchor, false to request a fixed anchor.
     * @return the route builder to use for next steps.
     */
    @objid ("4e1df7ce-77c7-4224-8642-26dea8cf5089")
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
    @objid ("81f84ef7-ce4c-4407-8ecc-ac596b69ad9f")
    Target setSourceAnchor(LinkAnchorFace face, int numerator, int denominator, boolean sliding);

    /**
     * ILinkRouteBuilder usage example
     */
    @objid ("bfd01853-7af3-4726-bd48-c94b13162013")
    public static class Example {
        @objid ("baf33c59-4441-4558-b7fb-e3c0c20a9f66")
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
    @objid ("15a5b6f1-7c4d-408a-9492-8970630a414d")
    public interface Target {
        /**
         * Request to anchor the connection target point at a given location.
         * <p>
         * The real anchor location may be different from the requested one.
         * @param loc the point location
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("fe86f54a-b8dd-4ea2-a633-e99390d462aa")
        BendPoints setTargetAnchor(Point loc, boolean sliding);

        /**
         * Request to anchor the connection target point on the middle of a given face.
         * <p>
         * The real anchor location may be different from the requested one.
         * @param face the desired false
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("fd9cd659-8fb9-421b-b743-0acc5393fe44")
        BendPoints setTargetAnchor(LinkAnchorFace face, boolean sliding);

        /**
         * Request to anchor the connection target point on the nearest face toward source node.
         * @param sliding true to request a sliding anchor, false to request a fixed anchor.
         * @return the route builder to use for next steps.
         */
        @objid ("dede60bc-6d1b-494e-9761-6243023e0e55")
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
        @objid ("8583541d-feae-4be6-a502-c0632af5888d")
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
    @objid ("2422902a-fbad-408b-8ed2-3a5f857079b8")
    public interface BendPoints {
        /**
         * Get a direct access to all points of the connection.
         * <p>
         * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
         * It does not modify the connection from which this path was built.
         * The path must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
         * @return the points in coordinates relative to the diagram.
         */
        @objid ("3fccddeb-df34-4a2d-868e-10577918367e")
        List<ILinkPoint> getAllPoints();

        /**
         * Create a new bend point.
         * @param loc the point location
         * @return the route builder to use for next steps.
         */
        @objid ("5f6f32bd-1f44-49b6-8823-59de2cbc17ba")
        BendPoints addBendPoint(Point loc);

        /**
         * Create a new fixed bend point.
         * @param loc the point location
         * @return the route builder to use for next steps.
         */
        @objid ("b3b1ef65-0d37-42a8-9151-a179e30918f9")
        BendPoints addFixedPoint(Point loc);

        /**
         * Add one horizontal segment with y aligned with previous point.
         * @param nextBendPointX the bend point X position.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("f7104a75-2fbb-4d43-9d10-46ad965fee25")
        BendPoints addHorizontalSegment(int nextBendPointX, boolean fixed);

        /**
         * Add one vertical segment with x aligned with previous point.
         * @param nextBendPointY the bend point Y position.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("f2cd4e84-a588-4611-bdda-706610cd2323")
        BendPoints addVerticalSegment(int nextBendPointY, boolean fixed);

        /**
         * Add 2 segment : one horizontal with y aligned with previous point,
         * one vertical with x aligned with target anchor.
         * <p>
         * You should call {@link #apply()} after this method.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("5701c89c-addf-4968-a5c9-407564f4a906")
        BendPoints finishHorizontalThenVertical(boolean fixed);

        /**
         * Add 2 segment : one vertical with x aligned with previous point,
         * one horizontal with y aligned with target anchor.
         * <p>
         * You should call {@link #apply()} after this method.
         * @param fixed true to make the bend point is fixed.
         * @return this route instance
         */
        @objid ("8130ad9a-4f95-4237-9f1b-f7ad0e65c604")
        BendPoints finishVerticalThenHorizontal(boolean fixed);

        /**
         * Build the route and apply it to the connection it was created from.
         */
        @objid ("63bb559e-3719-4918-9e59-b26070dcf575")
        void apply();

        /**
         * Add many bend points at once.
         * @param points the bend points locations.
         * @return this route instance
         */
        @objid ("cc6acd8a-0e01-49b3-ac3e-ccf4780d5e95")
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
        @objid ("25345fc8-eccc-4e43-b88d-370b56c1ed63")
        default BendPoints addFixedPoints(Collection<Point> points) {
            for (Point p : points) {
                addFixedPoint(p);
            }
            return this;
        }

    }

}
