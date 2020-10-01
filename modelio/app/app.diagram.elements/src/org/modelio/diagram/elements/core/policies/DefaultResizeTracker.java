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

package org.modelio.diagram.elements.core.policies;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * {@link ResizeTracker} redefined to not loose precision on floating point resize request.
 * @author cma
 * @since 3.7
 */
@objid ("86fda749-29e5-47ec-8735-41053c88942d")
public class DefaultResizeTracker extends ResizeTracker {
    /**
     * Constructs a resize tracker that resizes in the specified direction. The
     * direction is specified using {@link PositionConstants#NORTH}, {@link PositionConstants#NORTH_EAST}, etc.
     * 
     * @param owner of the resize handle which returned this tracker
     * @param direction the direction
     */
    @objid ("bbf31388-0ddf-41df-a41c-bf500fe3662e")
    public DefaultResizeTracker(GraphicalEditPart owner, int direction) {
        super(owner, direction);
    }

    @objid ("60f9ec6a-a06b-4f6e-bc27-a8d57f76617e")
    @Override
    protected void enforceConstraintsForResize(ChangeBoundsRequest changeBoundsRequest) {
        // Adjust request, so that minimum and maximum size constraints are respected
        GraphicalEditPart owner = getOwner();
        if (owner != null) {
            IFigure ownerFig = owner.getFigure();
            PrecisionRectangle originalConstraint = new PrecisionRectangle(ownerFig.getBounds());
            ownerFig.translateToAbsolute(originalConstraint);
            PrecisionRectangle manipulatedConstraint = new PrecisionRectangle(
                    changeBoundsRequest.getTransformedRectangle(originalConstraint));
        
            ownerFig.translateToRelative(manipulatedConstraint);
        
            // validate constraint (maximum and minimum size are regarded to be
            // 'normalized', i.e. relative to this figure's bounds coordinates).
            Dimension minimumSizeFor = getMinimumSizeFor(changeBoundsRequest);
            manipulatedConstraint.setPreciseSize(
                    Math.max(manipulatedConstraint.preciseWidth(), minimumSizeFor.preciseWidth()),
                    Math.max(manipulatedConstraint.preciseHeight(), minimumSizeFor.preciseHeight()));
        
            Dimension maximumSizeFor = getMaximumSizeFor(changeBoundsRequest);
            manipulatedConstraint.setPreciseSize(
                    Math.min(manipulatedConstraint.preciseWidth(), maximumSizeFor.preciseWidth()),
                    Math.min(manipulatedConstraint.preciseHeight(), maximumSizeFor.preciseHeight()));
        
            // translate back to absolute
            ownerFig.translateToAbsolute(manipulatedConstraint);
        
            // This computes the delta as a Dimension if the size delta was a Dimension, as a PrecisionDimension if the size delta was a PrecisionDimension.
            Dimension newSizeDelta = changeBoundsRequest.getSizeDelta().getCopy().setSize(0, 0)
                    .expand(manipulatedConstraint.preciseWidth(), manipulatedConstraint.preciseHeight())
                    .shrink(originalConstraint.preciseWidth(), originalConstraint.preciseHeight());
        
            changeBoundsRequest.setSizeDelta(newSizeDelta);
        }
    }

    /**
     * Returns a new List of editparts that this tool is operating on. This
     * method is called once during {@link #getOperationSet()}, and its result
     * is cached.
     * <P>
     * Edit parts that are not "user editable" are always filtered.
     * 
     * @return a list of editparts being operated on
     */
    @objid ("88094cde-a0a3-466f-b022-b4c215f587b0")
    @Override
    protected List createOperationSet() {
        List<?> ret = super.createOperationSet();
        return ret.stream()
                        .filter(ep -> ep instanceof GraphicalEditPart)
                        .map(ep -> (GraphicalEditPart) ep)
                        .filter(ep -> ((IGmObject) ep.getModel()).isUserEditable())
                        .collect(Collectors.toList());
    }

}
