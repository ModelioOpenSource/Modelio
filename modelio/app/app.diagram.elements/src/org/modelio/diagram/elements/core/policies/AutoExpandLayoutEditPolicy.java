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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * The host will try to expands itself to fits its preferred size when a child node is added, removed or changed.
 * <p>
 * To be used on edit parts that are not meant to have a layout policy but that should resize when one of their child
 * edit part wants to resize.
 * <p>
 * This policy should be installed with the {@link EditPolicy#LAYOUT_ROLE} role.
 * <p>
 * <h2>Working:</h2>
 * This policy computes the new container size by asking its figure layout manager for the
 * new preferred size, after having forced the resized figure preferred size to conform
 * the resize request.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("5946c95a-d098-4aa5-a78c-b356d97bb9b5")
public class AutoExpandLayoutEditPolicy extends LayoutEditPolicy {
    @objid ("f4fa798c-f0cd-4704-9b1f-903cdb3c68ce")
    @Override
    public Command getCommand(final Request request) {
        final Object reqType = request.getType();
        
        if (REQ_RESIZE_CHILDREN.equals(reqType) ||
                REQ_MOVE_CHILDREN.equals(reqType)) {
            return getExpandContainerCommand((ChangeBoundsRequest) request);
        }
        return super.getCommand(request);
    }

    @objid ("de50f9a9-0302-461f-8bb8-5c9eba21c31f")
    @Override
    public EditPart getTargetEditPart(Request request) {
        //return super.getTargetEditPart(request);
        return null;
    }

    @objid ("ba2ed10d-a308-49db-a6b5-f2aeca50c566")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return new ChildPolicy();
    }

    @objid ("7b5c6e54-57bd-49d5-8cd8-1d144b62e324")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }

    @objid ("a63e880d-551f-463b-bb5d-63c37b9a95bb")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
        return null;
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     * <p>
     * Redefined to auto expand on child edit part addition.
     * 
     * @return EditPartListener
     */
    @objid ("6c826906-5479-409c-b908-6691f75a1d03")
    @Override
    protected EditPartListener createListener() {
        return new EditPartListener.Stub() {
                                            @Override
                                            public void childAdded(EditPart child, int index) {
                                                onChildAdded(child);
                                            }
                                        };
    }

    /**
     * @param request a REQ_RESIZE_CHILDREN request
     * @return the container resize command.
     */
    @objid ("781313f7-b4e8-44cd-8241-bde48920db05")
    protected Command getExpandContainerCommand(ChangeBoundsRequest request) {
        return AutoExpandHelper.getExpandContainerCommand(request, getHost(), getLayoutContainer());
    }

    /**
     * Called by an edit part listener when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * 
     * @param child the added edit part
     */
    @objid ("06887af0-3393-49bd-a38c-337e4b067da7")
    protected void onChildAdded(EditPart child) {
        // Standard behavior inherited from LayoutEditPolicy
        decorateChild(child);
        
        // The child figure has just been added but not yet layouted, force layout now to avoid strange effects.
        getHostFigure().getUpdateManager().performValidation();
        
        ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
        request.setEditParts(child);
        
        Command cmd = getExpandContainerCommand(request);
        
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        } else {
            DiagramElements.LOG.debug("AutoExpandLayoutEditPolicy.onChildAdded(%s) : unable to expand <%s>. Command = <%s>",
                    child.toString(), getHost().toString(), cmd);
        }
    }

    /**
     * Simple layout satellite policy that forwards resize requests to the parent.
     * <p>
     * Inspired from {@link ResizableEditPolicy}.
     * 
     * @author cmarin
     */
    @objid ("bc94d412-bd08-4fe8-afa0-9cf5b99189bd")
    protected static class ChildPolicy extends GraphicalEditPolicy {
        @objid ("9674b828-99e4-4826-a5b1-5f13755da2f0")
        @Override
        public Command getCommand(Request request) {
            if (understandsRequest(request)) {
                return getResizeCommand((ChangeBoundsRequest) request);
            }
            return null;
        }

        @objid ("681a397f-672c-4934-a2ac-19b9006379b5")
        @Override
        public EditPart getTargetEditPart(Request request) {
            if (understandsRequest(request)) {
                return getHost();
            } else {
                return null;
            }
        }

        /**
         * Returns the command contribution for the given resize request. By
         * default, the request is re-dispatched to the host's parent as a
         * {@link org.eclipse.gef.RequestConstants#REQ_RESIZE_CHILDREN}. The
         * parent's edit policies determine how to perform the resize based on the
         * layout manager in use.
         * 
         * @param request the resize request
         * @return the command contribution obtained from the parent
         */
        @objid ("544662a3-7124-496c-8377-2f5c3ef76e1e")
        protected Command getResizeCommand(ChangeBoundsRequest request) {
            ChangeBoundsRequest req = getRequestForParent(request);
            return getHost().getParent().getCommand(req);
        }

        @objid ("c460272f-c30d-4ccf-a933-e65b6f3e06b8")
        @Override
        public boolean understandsRequest(Request request) {
            return REQ_RESIZE.equals(request.getType());
        }

        /**
         * Generates the request to dispatch to the parent edit part.
         * <p>
         * By default, the request is re-dispatched to the host's parent as a
         * {@link org.eclipse.gef.RequestConstants#REQ_RESIZE_CHILDREN}. The
         * parent's edit policies determine how to perform the resize based on the
         * layout manager in use.
         * 
         * @param request the resize request
         * @return the request to send to the parent edit part.
         */
        @objid ("7cf8dc78-554a-461f-b60a-b1456dc749ad")
        protected ChangeBoundsRequest getRequestForParent(ChangeBoundsRequest request) {
            ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
            req.setEditParts(getHost());
            req.setCenteredResize(request.isCenteredResize());
            req.setConstrainedMove(request.isConstrainedMove());
            req.setConstrainedResize(request.isConstrainedResize());
            req.setSnapToEnabled(request.isSnapToEnabled());
            req.setMoveDelta(request.getMoveDelta());
            req.setSizeDelta(request.getSizeDelta());
            req.setLocation(request.getLocation());
            req.setExtendedData(request.getExtendedData());
            req.setResizeDirection(request.getResizeDirection());
            return req;
        }

    }

    /**
     * Drag feedback policy for nodes that don't have policy
     * inherited from {@link org.eclipse.gef.editpolicies.NonResizableEditPolicy}.
     */
    @objid ("78b95f95-c7dd-419c-9b56-0a70c7b7b3c7")
    protected static class ChildDragSourceFeedbackPolicy extends GraphicalEditPolicy {
        @objid ("ebbbbacb-feb0-4d75-b2df-e7e25598f1a2")
        private IFigure feedback;

        /**
         * Returns the bounds of the host's figure by reference to be used to
         * calculate the initial location of the feedback. The returned Rectangle
         * should not be modified. Uses handle bounds if available.
         * 
         * @return the host figure's bounding Rectangle
         */
        @objid ("faf00065-d8eb-468c-a3cc-72bc2cc1eb8e")
        protected Rectangle getInitialFeedbackBounds() {
            if (((GraphicalEditPart) getHost()).getFigure() instanceof HandleBounds) {
                return ((HandleBounds) ((GraphicalEditPart) getHost()).getFigure())
                        .getHandleBounds();
            }
            return ((GraphicalEditPart) getHost()).getFigure().getBounds();
        }

        /**
         * Creates the figure used for feedback.
         * 
         * @return the new feedback figure
         */
        @objid ("9f69c019-09f1-4e10-b173-1d8a9d752f15")
        protected IFigure createDragSourceFeedbackFigure() {
            // Use a ghost rectangle for feedback
            RectangleFigure r = new RectangleFigure();
            FigureUtilities.makeGhostShape(r);
            r.setLineStyle(Graphics.LINE_DOT);
            r.setForegroundColor(ColorConstants.white);
            r.setBounds(getInitialFeedbackBounds());
            r.validate();
            addFeedback(r);
            return r;
        }

        /**
         * Calls other methods as appropriate.
         * @see org.eclipse.gef.EditPolicy#showSourceFeedback(org.eclipse.gef.Request)
         */
        @objid ("c8321a1c-8eda-45a9-9baa-d0f000bd5e50")
        @Override
        public void showSourceFeedback(Request request) {
            if ((REQ_MOVE.equals(request.getType()) && isDragAllowed())
                    || REQ_RESIZE.equals(request.getType())
                    || REQ_ADD.equals(request.getType())
                    || REQ_CLONE.equals(request.getType())) {
                showChangeBoundsFeedback((ChangeBoundsRequest) request);
            }
        }

        @objid ("c100a9bd-4fab-4b18-8e29-6f79243faba5")
        protected boolean isDragAllowed() {
            return true;
        }

        /**
         * Shows or updates feedback for a change bounds request.
         * 
         * @param request the request
         */
        @objid ("2dba5f45-d615-4417-958f-8dc7c39b5782")
        protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
            IFigure fbFig = getDragSourceFeedbackFigure();
            
            PrecisionRectangle rect = new PrecisionRectangle(
                    getInitialFeedbackBounds().getCopy());
            getHostFigure().translateToAbsolute(rect);
            rect.translate(request.getMoveDelta());
            rect.resize(request.getSizeDelta());
            
            fbFig.translateToRelative(rect);
            fbFig.setBounds(rect);
            fbFig.validate();
        }

        /**
         * Lazily creates and returns the feedback figure used during drags.
         * 
         * @return the feedback figure
         */
        @objid ("a1fe372b-ae34-4f22-8e30-4c3645dbe433")
        protected IFigure getDragSourceFeedbackFigure() {
            if (this.feedback == null) {
                this.feedback = createDragSourceFeedbackFigure();
            }
            return this.feedback;
        }

        @objid ("b6cfb1e8-f138-48be-b7a1-1391b827eef1")
        @Override
        public void eraseSourceFeedback(Request request) {
            if ((REQ_MOVE.equals(request.getType()) && isDragAllowed())
                    || REQ_RESIZE.equals(request.getType())
                    || REQ_CLONE.equals(request.getType())
                    || REQ_ADD.equals(request.getType())) {
                eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
            }
        }

        /**
         * Erases drag feedback. This method called whenever an erase feedback
         * request is received of the appropriate type.
         * 
         * @param request the request
         */
        @objid ("6ed19af0-39c5-43cf-9f51-648fdc0aceb9")
        protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
            if (this.feedback != null) {
                removeFeedback(this.feedback);
            }
            this.feedback = null;
        }

    }

}
