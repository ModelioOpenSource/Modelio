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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.ChangeBoundsFeedbackMap;

/**
 * {@link ResizeTracker} redefined to not loose precision on floating point resize request.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("86fda749-29e5-47ec-8735-41053c88942d")
public class DefaultResizeTracker extends ResizeTracker {
    /**
     * Constructs a resize tracker that resizes in the specified direction. The direction is specified using {@link PositionConstants#NORTH}, {@link PositionConstants#NORTH_EAST}, etc.
     * @param owner of the resize handle which returned this tracker
     * @param direction the direction
     */
    @objid ("bbf31388-0ddf-41df-a41c-bf500fe3662e")
    public  DefaultResizeTracker(GraphicalEditPart owner, int direction) {
        super(owner, direction);
    }

    @objid ("9b5cc0fe-8c39-42d5-bffc-cdd703187a6a")
    @Override
    protected ChangeBoundsRequest getSourceRequest() {
        return (ChangeBoundsRequest) super.getSourceRequest();
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
     * Returns a new List of editparts that this tool is operating on.
     * <p>
     * This method is called once during {@link #getOperationSet()}, and its result is cached.
     * <P>
     * Redefined so that Edit parts that are not {@link IGmObject#isUserEditable() "user editable"} are filtered out.
     * @return a list of edit parts being operated on
     */
    @objid ("88094cde-a0a3-466f-b022-b4c215f587b0")
    @Override
    protected List createOperationSet() {
        List<EditPart> ret = super.createOperationSet();
        
        ret.removeIf(ep -> !(ep instanceof GraphicalEditPart && ((IGmObject) ep.getModel()).isUserEditable()));
        return ret;
    }

    @objid ("6269b4a2-0a2c-43b1-b499-0e4be9b6c0e0")
    @Override
    protected Command getCommand() {
        CompoundCommand command = new CompoundCommand();
        command.add(super.getCommand());
        return command.unwrap();
    }

    /**
     * Request that holds node feedback figures so that link policies may update draw feedback from node feedback figures.
     * 
     * @author chm
     * @since 5.0.2
     */
    @objid ("92eb89dc-358b-4c1f-9aea-5d900b0fe48a")
    public static class FeedbackChangeBoundsRequest extends Request {
        /**
         * The request type.
         */
        @objid ("c1c17a14-d383-4935-92e1-1e15e47f71bc")
        public static final String REQ_TYPE = "Display link feedback on node feedback";

        @objid ("5b41f384-9255-404f-8494-0b1f0fe1898a")
        private final ChangeBoundsRequest linkedRequest;

        @objid ("214ff933-bd0f-4757-860b-220c791521b5")
        private final ChangeBoundsFeedbackMap feedbackFigures;

        @objid ("9d3c3b76-de28-47b4-8b0a-01598f695bd9")
        public  FeedbackChangeBoundsRequest(ChangeBoundsRequest linkedRequest, ChangeBoundsFeedbackMap feedbackFigures) {
            super(REQ_TYPE);
            this.linkedRequest = linkedRequest;
            this.feedbackFigures = feedbackFigures;
            
        }

        /**
         * @return the original request
         */
        @objid ("76759591-ac0d-4c9c-af38-c3a072e5c67a")
        public ChangeBoundsRequest getLinkedRequest() {
            return this.linkedRequest;
        }

        /**
         * @param ep an edit part
         * @return the figure to use as feedback
         */
        @objid ("d3590a2b-ff2b-42f8-8d42-2b768064f29a")
        public IFigure getFeedbackFigure(GraphicalEditPart ep) {
            GraphicalEditPart current = ep;
            if (!(current instanceof PortContainerEditPart)) {
                current = (GraphicalEditPart) current.getParent();
            }
            return this.feedbackFigures.getOrDefault(current, current.getFigure());
        }

    }

}
