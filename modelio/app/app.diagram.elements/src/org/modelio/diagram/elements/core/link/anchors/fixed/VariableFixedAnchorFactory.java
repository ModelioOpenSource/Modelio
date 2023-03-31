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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;

/**
 * Default Factory of all possible FixedAnchors for a rectangular figure.
 */
@objid ("9f810fa7-a44a-4938-8434-2e5717313187")
@Deprecated
class VariableFixedAnchorFactory extends FixedConnectionAnchorFactory {
    @objid ("124e39ae-2683-42eb-b6a4-fab9984e0e80")
    private static final int DIST_BETWEEN_ANCHORS = 30;

    @objid ("4ad19efc-1ae5-4803-a688-b5377a86823b")
    protected final int margin;

    @objid ("e01bfd78-f8d6-4443-ac0e-34a8eb1a96a0")
    @Override
    protected ComputedState createComputedState(IFigure aNodeFigure) {
        return new MarginDependentComputedState(this.margin);
    }

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     */
    @objid ("872c9d8a-7c43-4057-b9f7-93011c51e9ff")
    public  VariableFixedAnchorFactory(String algoId) {
        this(algoId, DIST_BETWEEN_ANCHORS);
    }

    /**
     * C'tor.
     * @param margin the minimum distance to keep between anchors.
     */
    @objid ("67cd7a75-19a4-483a-9a22-1ef6b2cfac35")
    public  VariableFixedAnchorFactory(String algoId, int margin) {
        super(algoId);
        this.margin = margin;
        
    }

    @objid ("ea46f57a-8098-4e72-a16d-55119e4c61e7")
    @Override
    protected IFixedAnchorLocator createAnchorLocator(String algoId, IFigure aNodeFigure) {
        return new VariableFixedAnchorLocator(algoId, this::onFigureMoved, this::getAnchorCounts);
    }

    @objid ("357d6f8f-a54f-422d-93e6-12641a147227")
    private Dimension getAnchorCounts(IFigure aNodeFigure) {
        return getState(aNodeFigure).anchorsCount;
    }

    @objid ("b236a26f-640c-43d1-9f2f-ce687cee40f4")
    protected static class MarginDependentComputedState extends ComputedState {
        @objid ("15368918-103b-4159-b7c0-9adfda8841e4")
        protected final int margin;

        @objid ("3b9299c3-6666-4c97-a0f9-d942f3f41c46")
        protected Dimension previousFigSize = new Dimension();

        @objid ("0135ffb4-2f51-4619-8591-e717c9c023b7")
        public  MarginDependentComputedState(int margin) {
            this.margin = margin;
        }

        @objid ("c9e84574-f271-4ccc-bada-62fe4a2c3e44")
        @Override
        protected boolean update(IFigure aNodeFig) {
            Dimension figSize = aNodeFig.getSize();
            
            boolean sizeChanged = !Objects.equals(this.previousFigSize, figSize);
            
            if (sizeChanged) {
                // compute how many time the label fit on horizontal and vertical faces
                int nHorizontal = Math.max(1, figSize.width / this.margin - 0);
                int nVertical = Math.max(1, figSize.height / this.margin - 0);
            
                // ensure count is odd
                if (nHorizontal % 2 == 0) {
                    nHorizontal++;
                }
                if (nVertical % 2 == 0) {
                    nVertical++;
                }
            
                this.anchorsCount.setSize(nHorizontal, nVertical);
                this.previousFigSize = figSize;
            }
            
            // Call inherited behavior
            boolean ret = super.update(aNodeFig);
            return ret && !figSize.isEmpty();
        }

    }

    /**
     * {@link FixedNodeAnchorLocator} that reacts to node resize.
     * <p>
     * When the node is resized the number of available fixed anchors is different.
     * <p>
     * Convert the given anchor from its base number of anchors per face to the current available anchors number.
     * The returned anchor location may then move to match the new fixed anchor locations.
     * 
     * @author cmarin
     */
    @objid ("79ada7c5-cd55-45d8-a161-725ddc401bd9")
    public static class VariableFixedAnchorLocator extends FixedNodeAnchorLocator {
        /**
         * a function that take a node figure and return the number of anchors.
         */
        @objid ("d924165e-508e-4df9-a892-cd3ca75d3eb9")
        private final Function<IFigure, Dimension> anchorCountGetter;

        /**
         * @param factory the anchor factory
         * @param anchorCountGetter a function that take a node figure and return the number of anchors.
         */
        @objid ("d81d678c-8ae9-4cd2-bff7-49eb64be1f3d")
        public  VariableFixedAnchorLocator(String algo, Consumer<IFigure> figureMoveListener, Function<IFigure, Dimension> anchorCountGetter) {
            super(algo, figureMoveListener);
            this.anchorCountGetter = anchorCountGetter;
            
        }

        @objid ("a13dbdbe-d2b2-4418-a0cf-9e26e928c624")
        @Override
        public Point getReferencePoint(final FixedAnchor anchor) {
            FixedAnchor otherAnchor = getCorrectedAnchor(anchor);
            return super.getReferencePoint(otherAnchor);
        }

        /**
         * When the node is resized the number of available fixed anchors is different.
         * <p>
         * Convert the given anchor from its base number of anchors per face to the current available anchors number. The returned anchor location may then move to match the new fixed anchor locations.
         * @param anchor a fixed anchor
         * @return an anchor on the same face with the right rank.
         */
        @objid ("b2fab4bc-e4f3-4c66-a658-a1e8d0cf5b6c")
        protected FixedAnchor getCorrectedAnchor(final FixedAnchor anchor) {
            Dimension anchorCounts = this.anchorCountGetter.apply(anchor.getOwner());
            
            int newAnchorCount;
            switch (anchor.getFace()) {
            case FacesConstants.FACE_EAST:
            case FacesConstants.FACE_WEST:
                newAnchorCount = anchorCounts.height;
                break;
            case FacesConstants.FACE_NORTH:
            case FacesConstants.FACE_SOUTH:
            default:
                newAnchorCount = anchorCounts.width;
            }
            
            if (newAnchorCount==0 || anchor.getTotalOnFace() == newAnchorCount) {
                // No change, fast exit
                return anchor;
            }
            
            // Compute new anchor
            int newRank = Math.round((float) (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1) * (newAnchorCount + 1)) - 1;
            newRank = Math.min(newRank, newAnchorCount - 1);
            newRank = Math.max(newRank, 0);
            return new FixedAnchor(
                    anchor.getOwner(),
                    anchor.getFace(),
                    newRank,
                    newAnchorCount,
                    anchor.getLocator());
            
        }

    }

}
