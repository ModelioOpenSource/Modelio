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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;

/**
 * Default Factory of all possible FixedAnchors for a rectangular figure.
 */
@objid ("236b07fb-4e92-4624-ab05-ef933ca26651")
public class VariableFixedAnchorProvider extends FixedNodeAnchorProvider {
    @objid ("68ea9d2c-450f-4806-a03a-7ca6151b6425")
    private static final int DIST_BETWEEN_ANCHORS = 30;

    @objid ("1a3eb8f8-a260-4164-95c9-dcb3f907644d")
    protected final int margin;

    @objid ("00ea2a5b-c657-4292-a91d-63825c7c8710")
    @Override
    protected ComputedState createComputedState(IFigure aNodeFigure) {
        return new MarginDependentComputedState(this.margin);
    }

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     */
    @objid ("52b9bf69-cbb4-4193-a7c0-052a5c82ebe6")
    public  VariableFixedAnchorProvider() {
        this(DIST_BETWEEN_ANCHORS);
    }

    /**
     * C'tor.
     * @param margin the minimum distance to keep between anchors.
     */
    @objid ("a0ca0ca8-ce97-483d-80fd-16f6f2d128d4")
    public  VariableFixedAnchorProvider(int margin) {
        this.margin = margin;
    }

    @objid ("f0236d6b-2531-47d3-a78c-d77eb573cbc9")
    @Override
    public ConnectionAnchor createFromModel(IFigure nodeFig, GmFixedAnchor gmLinkAnchor) {
        return new FixedAnchor(nodeFig,
                gmLinkAnchor.getFace(),
                gmLinkAnchor.getRank(),
                gmLinkAnchor.getTotalOnFace(),
                getLocator(nodeFig));
        
    }

    @objid ("e1d84a9d-44cc-45c0-a1f6-000078863ab4")
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
    @objid ("e772585d-e766-45d5-b84d-febc53b68899")
    protected FixedAnchor getCorrectedAnchor(final FixedAnchor anchor) {
        ComputedState state = getState(anchor.getOwner());
        
        int newAnchorCount;
        switch (anchor.getFace()) {
        case FACE_EAST:
        case FACE_WEST:
            newAnchorCount = state.anchorsCount.height;
            break;
        case FACE_NORTH:
        case FACE_SOUTH:
        default:
            newAnchorCount = state.anchorsCount.width;
        }
        
        if (newAnchorCount==0 || anchor.getTotalOnFace() == newAnchorCount) {
            return anchor;
        }
        
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

    @objid ("e6811d6c-2241-49bd-b7cb-c1de831a30e8")
    protected static class MarginDependentComputedState extends ComputedState {
        @objid ("163fe6d8-fb39-4b85-9f64-fe75c3fc70e1")
        protected final int margin;

        @objid ("6b9301c4-2ed8-4deb-b6c1-d6c9ed4146ca")
        protected Dimension previousFigSize = new Dimension();

        @objid ("a11b4f46-c42f-4384-b83b-86bea11179cb")
        public  MarginDependentComputedState(int margin) {
            this.margin = margin;
        }

        @objid ("e6da8cd9-fada-4eea-aeba-8343c66f45fa")
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
                    nHorizontal--;
                }
                if (nVertical % 2 == 0) {
                    nVertical--;
                }
            
                this.anchorsCount.setSize(nHorizontal, nVertical);
                this.previousFigSize = figSize;
            }
            
            // Call inherited behavior
            boolean ret = super.update(aNodeFig);
            return ret && !figSize.isEmpty();
        }

    }

}
