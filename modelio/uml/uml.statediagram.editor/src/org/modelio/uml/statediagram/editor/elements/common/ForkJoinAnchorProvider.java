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
package org.modelio.uml.statediagram.editor.elements.common;

import java.util.Collection;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.adefault.DefaultAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.FixedNodeAnchorProvider2;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.uml.statediagram.editor.elements.ForkJoinOrientation;

/**
 * Anchor provider for <b>Fork</b> and <b>Join</b> nodes, displaying anchors:
 * <ul>
 * <li>closer than the default {@link IFixedNodeAnchorProvider}.</li>
 * <li>only vertically or horizontally, according to the node's orientation.</li>
 * </ul>
 */
@objid ("8bbdb991-da14-4c80-9b8f-02a43bfb11a5")
public class ForkJoinAnchorProvider extends FixedNodeAnchorProvider2 {
    @objid ("3fe9f601-b78f-40fd-83ce-125344107b7b")
    private static final int DIST_BETWEEN_ANCHORS = 15;

    @objid ("811f7724-09c3-4098-aab3-c601b5adc0ba")
    private Supplier<ForkJoinOrientation> orientation;

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     */
    @objid ("ba27e0db-63af-4cd7-9854-e9fcee20aff0")
    public  ForkJoinAnchorProvider(Supplier<ForkJoinOrientation> orientation) {
        super(new ForkJoinAnchorFactory(DIST_BETWEEN_ANCHORS, orientation));
        this.orientation = orientation;
        
    }

    @objid ("a9da6f06-adff-480c-8cd0-58c5a056e0be")
    protected static class ForkJoinAnchorFactory extends DefaultAnchorFactory {
        @objid ("e4b04767-dcc1-4139-a72e-3158cd627674")
        private final Supplier<ForkJoinOrientation> orientationSupplier;

        @objid ("7d42acd3-b0d3-447c-a08b-88cf5ca18d4e")
        public  ForkJoinAnchorFactory(int margin, Supplier<ForkJoinOrientation> orientationSupplier) {
            super();
            this.orientationSupplier = orientationSupplier;
            
        }

        @objid ("4936bb6b-f518-4a62-b195-a1a73c03922a")
        @Override
        protected void createAllAnchors(Integer faceFilter, Dimension anchorsCount, Collection<ConnectionAnchor> anchors) {
            if (this.orientationSupplier.get() == ForkJoinOrientation.HORIZONTAL) {
                createFaceAnchors(anchors, FacesConstants.FACE_NORTH, faceFilter, anchorsCount.width);
                createFaceAnchors(anchors, FacesConstants.FACE_SOUTH, faceFilter, anchorsCount.width);
            } else {
                createFaceAnchors(anchors, FacesConstants.FACE_EAST, faceFilter, anchorsCount.height);
                createFaceAnchors(anchors, FacesConstants.FACE_WEST, faceFilter, anchorsCount.height);
            }
            
        }

        @objid ("4b74d0c6-497b-4408-8960-e82377e876a9")
        @Override
        protected void updateGridAnchorCount(Dimension out, Dimension gridSize) {
            super.updateGridAnchorCount(out, gridSize);
            
            if (this.orientationSupplier.get() == ForkJoinOrientation.HORIZONTAL) {
                out.height = 0;
            } else {
                out.width = 0;
            }
            
        }

    }

}
