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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.variable;

import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.FixedNodeAnchorLocator;

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
@objid ("f711253d-911e-4365-8cc9-7651d71068d0")
public class VariableFixedAnchorLocator extends FixedNodeAnchorLocator {
    /**
     * a function that take a node figure and return the number of anchors.
     */
    @objid ("68c8f602-d9bf-4d4a-b231-2fd038577528")
    private final Consumer<Dimension> anchorCountGetter;

    @objid ("f7354cc9-4899-4b68-96ae-b7d0ac9b3ae6")
    private static final Dimension anchorCountValue = new Dimension();

    /**
     * @param algo the algorithm id serialized in the FixedAnchor
     * @param anchorCountGetter a function that take a node figure and return the number of anchors.
     */
    @objid ("a7bbd7dd-782d-4d61-b300-fcd5817e4ae9")
    public  VariableFixedAnchorLocator(String algo, Consumer<Dimension> anchorCountGetter) {
        super(algo);
        this.anchorCountGetter = anchorCountGetter;
        
    }

    @objid ("b271733c-51c9-42cc-9d70-94f29d25ec0d")
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
    @objid ("f681169e-1b81-4197-9a40-910d71e9cf56")
    protected FixedAnchor getCorrectedAnchor(final FixedAnchor anchor) {
        Dimension anchorCounts = anchorCountValue;
        
        this.anchorCountGetter.accept(anchorCounts);
        
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
