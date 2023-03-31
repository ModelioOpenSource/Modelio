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
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.wrapped.WrappedFixedAnchorLocator;

/**
 * {@link WrappedFixedAnchorLocator} that reacts to anchor count changes to realign the anchor.
 * <p>
 * Example: When the node is resized the number of available fixed anchors is different.
 * <p>
 * Convert the {@link FixedAnchor} from its base number of anchors per face to the current available anchors number.
 * The returned anchor location may then move to match the new fixed anchor locations.
 * 
 * @author cmarin
 */
@objid ("10881977-c910-4d57-b0fa-167e0fcf36f6")
public class VariableWrappedAnchorLocator extends WrappedFixedAnchorLocator {
    /**
     * a function that take a node figure and return the number of anchors.
     */
    @objid ("2ceee3fd-b8ff-43da-af98-d39940e99e88")
    private final Consumer<Dimension> anchorCountGetter;

    @objid ("cece1acb-9ae1-47eb-87ca-53344c4fafd5")
    private static final Dimension TMP = new Dimension();

    /**
     * Corrected FixedAnchor.
     * <p>
     * It is a single shared instance used in temporary computations. Avoids useless allocations.
     */
    @objid ("3c1f3d3b-5b42-448d-82f3-bd53720223d0")
    private static FixedAnchor temporary;

    /**
     * @param delegate the locator to wrap
     * @param anchorCountGetter a function that take a node figure and return the number of anchors.
     */
    @objid ("87d1a646-79b3-401b-ba52-3df0148992ba")
    public  VariableWrappedAnchorLocator(IFixedAnchorLocator delegate, Consumer<Dimension> anchorCountGetter) {
        super(delegate);
        this.anchorCountGetter = anchorCountGetter;
        
    }

    @objid ("d1664721-2f24-462f-933d-28002704d59f")
    @Override
    public Point getReferencePoint(final FixedAnchor anchor) {
        FixedAnchor otherAnchor = getCorrectedAnchor(anchor);
        return super.getReferencePoint(otherAnchor);
    }

    @objid ("1e5f085c-e99e-4594-b4aa-74476860b835")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        FixedAnchor otherAnchor = getCorrectedAnchor(anchor);
        return super.getLocation(otherAnchor, reference);
    }

    /**
     * When the node is resized the number of available fixed anchors is different.
     * <p>
     * Convert the given anchor from its base number of anchors per face to the current available anchors number. The returned anchor location may then move to match the new fixed anchor locations.
     * @param anchor a fixed anchor
     * @return an anchor on the same face with the right rank.
     */
    @objid ("c43bf097-80d6-4d5a-8e52-dc7e1c3da304")
    protected FixedAnchor getCorrectedAnchor(final FixedAnchor anchor) {
        Dimension anchorCounts = TMP;
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
        
        if (temporary == null)
            temporary = new FixedAnchor(anchor);
        
        // Compute new anchor
        int newRank = Math.round((float) (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1) * (newAnchorCount + 1)) - 1;
        newRank = Math.min(newRank, newAnchorCount - 1);
        newRank = Math.max(newRank, 0);
        temporary.overwrite(
                anchor.getOwner(),
                anchor.getFace(),
                newRank,
                newAnchorCount,
                anchor.getLocator());
        return temporary;
    }

}
