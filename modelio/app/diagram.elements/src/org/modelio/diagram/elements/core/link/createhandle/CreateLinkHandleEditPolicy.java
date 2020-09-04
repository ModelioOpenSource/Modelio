/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.link.createhandle;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.diagram.elements.core.link.extensions.FractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.tools.BendedConnectionAndNodeCreationTool;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that add a "create link" drag handle on the middle of the node figure.
 * <p>
 * The "create link" handle is handled by {@link PopupMenuCreateLinkEditPolicy} , displaying a popup menu displaying all allowed links between the source and this node.
 * <p>
 * This edit policy should be installed with the {@link #ROLE} role.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("2b58a3f1-f71f-43a3-aaa8-a8eaf2572ada")
public class CreateLinkHandleEditPolicy extends SelectionHandlesEditPolicy {
    /**
     * Don't display the smart link handle when the edit part is being moved
     */
    @objid ("15c2326e-e49e-48f4-a0e7-06d07e46516c")
    private boolean moveInProgress;

    /**
     * The role this edit policy should be installed with.
     */
    @objid ("0453b015-fa99-4bcf-8da3-01fc71f037be")
    public static final Object ROLE = CreateLinkHandleEditPolicy.class;

    @objid ("ccd80552-06cb-409e-ab76-d20c311c3b69")
    private final GraphicalEditPart connSource;

    /**
     * Request used to update {@link NodeSourceAnchorLocator} location.
     */
    @objid ("cbaf2488-f0ee-4bbf-9fce-2f74952ecf83")
    private CreateConnectionRequest feedBackReq;

    @objid ("a918a051-e09e-4df1-a851-8b45590a6c9c")
    private Locator handleLocator;

    @objid ("0ba2f441-ad39-4ac1-9c51-f92ee734de79")
    private MouseMotionListener mouseMotionListener;

    /**
     * @param connsource the created connections source edit part.
     */
    @objid ("e012ca7c-2702-4d0a-9760-3884b03420be")
    public CreateLinkHandleEditPolicy(GraphicalEditPart connsource) {
        this.connSource = connsource;
        if (connsource instanceof ConnectionEditPart) {
            this.handleLocator = new FractionalConnectionLocator((Connection) connsource.getFigure(), 0.6, false);
        } else if (connsource instanceof NodeEditPart) {
            NodeEditPart nep = (NodeEditPart) connsource;
            this.feedBackReq = new CreateConnectionRequest();
            this.feedBackReq.setType(RequestConstants.REQ_CONNECTION_START);
            this.feedBackReq.setSourceEditPart(connsource);
            this.feedBackReq.setLocation(connsource.getFigure().getBounds().getCenter());
            connsource.getFigure().translateToAbsolute(this.feedBackReq.getLocation());
            this.handleLocator = new NodeSourceAnchorLocator(nep, this.feedBackReq);
        } else {
            this.handleLocator = new RelativeLocator(connsource.getFigure(), 0.5, 0.5);
        
        }
    }

    @objid ("19de9917-9808-46de-9173-ef92f8943706")
    @Override
    public void activate() {
        this.moveInProgress = false;
        
        super.activate();
    }

    @objid ("82cbfd28-d190-42cf-8d99-afd033f5d5a2")
    @Override
    public void deactivate() {
        // remove selection handles that have been added by showTargetFeedBack(...)
        removeSelectionHandles();
        super.deactivate();
    }

    @objid ("30d50ce5-352b-44ae-8ef3-f258768178b1")
    @Override
    public void eraseSourceFeedback(Request request) {
        Object type = request.getType();
        if (RequestConstants.REQ_MOVE.equals(type)
                || RequestConstants.REQ_MOVE_CHILDREN.equals(type)) {
            this.moveInProgress = false;
        }
    }

    @objid ("2e30ab5d-20e9-4cfb-9a24-0795a5e05c84")
    @Override
    public void eraseTargetFeedback(Request request) {
        // listen for REQ_SELECTION instead of REQ_SELECTION_HOVER.
        // in the other case the handle disappear when beginning dragging.
        if (RequestConstants.REQ_SELECTION.equals(request.getType())) {
            removeSelectionHandles();
        }
    }

    @objid ("fc24a5df-99fa-43e5-b530-69239435cce5")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_SELECTION_HOVER.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    /**
     * Set the create link handle locator.
     * 
     * @param handleLocator the create link handle locator.
     * @return this instance.
     */
    @objid ("55a773be-cc4b-4b28-bc6d-ce77b151ce56")
    public CreateLinkHandleEditPolicy setHandleLocator(Locator handleLocator) {
        this.handleLocator = handleLocator;
        return this;
    }

    @objid ("c69c40c5-16b4-425f-a1b6-39596a239b27")
    @Override
    public void showSourceFeedback(Request request) {
        Object type = request.getType();
        if (RequestConstants.REQ_MOVE.equals(type)
                || RequestConstants.REQ_MOVE_CHILDREN.equals(type)) {
            if (!this.moveInProgress) {
                this.moveInProgress = true;
                hideSelection();
            }
        }
    }

    @objid ("7f839032-f4d1-4dee-a528-81e2fc9b0200")
    @Override
    public void showTargetFeedback(Request request) {
        if (!this.moveInProgress) {
            Object reqType = request.getType();
            if (RequestConstants.REQ_SELECTION_HOVER.equals(reqType)) {
                if (this.handles == null && isHandleToDisplay()) {
                    addSelectionHandles();
                }
                refreshHandleLocation(request);
            }
        }
    }

    @objid ("ba94eafa-834c-4030-957c-89a419af8a58")
    @Override
    protected List createSelectionHandles() {
        CreateHandle handle = new CreateHandle(this.connSource, this.handleLocator);
        
        // Without this the handle disappear when the mouse hovers it.
        getHost().getViewer().getVisualPartMap().put(handle, getHost());
        return Collections.singletonList(handle);
    }

    @objid ("c97bf344-ca13-4efd-a1e4-e7f1ed220320")
    @Override
    protected void hideSelection() {
        super.hideSelection();
        if (this.mouseMotionListener != null) {
            getHostFigure().removeMouseMotionListener(this.mouseMotionListener);
            this.mouseMotionListener = null;
        }
    }

    @objid ("e5c11333-d76c-4997-8d54-f3f9ad835538")
    protected boolean isHandleToDisplay() {
        IGmModelRelated model = (IGmModelRelated) getHost().getModel();
        MObject relatedElement = model.getRelatedElement();
        if (model.isUserEditable() && relatedElement != null && relatedElement.isModifiable()) {
            StyleKey propertyKey = model.getDiagram().getStyleKey(MetaKey.SHOWSMARTLINKHANDLE);
            if (propertyKey != null) {
                return model.getDisplayedStyle().getBoolean(propertyKey);
            }
        }
        return false;
    }

    @objid ("c4ae565d-2334-4799-836e-4cc07a6ae84d")
    protected void refreshHandleLocation(Request request) {
        if (this.feedBackReq != null && this.handles != null) {
            this.feedBackReq.setLocation(((org.eclipse.gef.requests.LocationRequest) request).getLocation());
            for (Object object : this.handles) {
                AbstractHandle h = (AbstractHandle) object;
                h.revalidate();
            }
        }
    }

    @objid ("124093e9-d42b-453d-b745-adfb04afa6e5")
    @Override
    protected void removeSelectionHandles() {
        if (this.handles != null) {
            for (Object h : this.handles) {
                getHost().getViewer().getVisualPartMap().remove(h);
            }
        }
        
        super.removeSelectionHandles();
    }

    @objid ("9164bc3e-0d08-4f11-8665-543b54fa5241")
    @Override
    protected void showSelection() {
        // Don't show drag handle immediately, do it only on "hover".
        // Don't call: super.showSelection();
        
        // REQ_SELECTION_HOVER are not received anymore when the node is selected,
        // children nodes get it instead. We need our own hover listener.
        if (isHandleToDisplay()) {
            getHostFigure().addMouseMotionListener(getMouseMotionListener());
        }
    }

    /**
     * Lazy deferred instantiation of the mouse motion listener
     * @return
     */
    @objid ("4d5be243-f4ac-41ab-9dea-5a68d94abb08")
    private MouseMotionListener getMouseMotionListener() {
        if (this.mouseMotionListener == null) {
            this.mouseMotionListener = new MouseMotionListener.Stub() {
                @Override
                public void mouseHover(MouseEvent me) {
                    if (CreateLinkHandleEditPolicy.this.handles == null) {
                        addSelectionHandles();
                    }
                }
        
                @Override
                public void mouseExited(MouseEvent me) {
                    removeSelectionHandles();
                }
            };
        }
        return this.mouseMotionListener;
    }

    /**
     * Drag handle that creates connections.
     */
    @objid ("f0779a3b-6456-4f5c-b211-5b89b448853d")
    private static class CreateHandle extends AbstractHandle {
        @objid ("b01cb947-3ffc-4801-b782-180bac0ce931")
        public CreateHandle(GraphicalEditPart owner, Locator locator) {
            super(owner, locator);
            setLayoutManager(new StackLayout());
            
            Figure child = new ImageFigure((Image) owner
                    .getViewer()
                    .getResourceManager()
                    .get(ImageDescriptor.createFromFile(getClass(), "/icons/link-arrow.png")));
            add(child);
            
            setCursor(SharedCursors.CURSOR_PLUG);
            
            IGmModelRelated model = (IGmModelRelated) owner.getModel();
            UniversalLabelProvider labelProvider = new UniversalLabelProvider();
            
            String text = DiagramElements.I18N.getMessage("CreateLinkHandle.tooltip", Optional.ofNullable(model)
                    .map(m -> m.getRelatedElement())
                    .map(el -> labelProvider.getText(el))
                    .orElse(""));
            
            setToolTip(new Label(text));
            labelProvider.dispose();
        }

        /**
         * SelectionTool does not like Handle with child figures so prevent search to go to children and return itself.
         */
        @objid ("37cc9bb8-0dbb-4990-a958-b0ec4bbbca69")
        @Override
        protected IFigure findDescendantAtExcluding(int x, int y, TreeSearch search) {
            // SelectionTool does not like Handle with child figures so prevent search to go to children and return itself
            return null;
        }

        @objid ("5b0941ab-0501-4f46-afb8-b95d29802ae8")
        @Override
        protected DragTracker createDragTracker() {
            // tracker to create connection by clicking
            BendedConnectionAndNodeCreationDragTracker tool2 = new BendedConnectionAndNodeCreationDragTracker();
            tool2.setFactory(new SimpleFactory(UserChoiceLinkCreationFactory.class));
            return tool2;
        }

        /**
         * {@link BendedConnectionAndNodeCreationTool} as a drag tracker.
         * 
         * @author cma
         * @since 3.7
         */
        @objid ("4b8eca77-59c5-405e-997c-f3322043babc")
        private final class BendedConnectionAndNodeCreationDragTracker extends BendedConnectionAndNodeCreationTool implements DragTracker {
            @objid ("9154ff9e-81b1-4246-9a9e-dbee046b289e")
            @Override
            protected boolean updateTargetUnderMouse() {
                if (isInState(AbstractTool.STATE_INITIAL)) {
                    // Force the source to be the handle owner
                    EditPart owner = getOwner().getTargetEditPart(getTargetRequest());
                    if (getTargetEditPart() != owner) {
                        return updateTargetEditPart(owner, RequestConstants.REQ_CONNECTION_START);
                    }
                    return false;
                } else {
                    return super.updateTargetUnderMouse();
                }
            }

            @objid ("6590218c-cf22-495e-9ae7-8c372c3015c0")
            @Override
            protected boolean handleButtonDown(int button) {
                if (isInState(AbstractTool.STATE_INITIAL)) {
                    EditPartViewer v = getCurrentViewer();
                    getDomain().setActiveTool(this);
                    setViewer(v);
                    setUnloadWhenFinished(true);
                }
                return super.handleButtonDown(button);
            }

        }

    }

    /**
     * Locator that calls {@link NodeEditPart#getSourceConnectionAnchor(Request)} to relocate the figure, using its preferred size.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("60e5c055-aafb-49ff-8065-2e37ea6cef7f")
    protected static final class NodeSourceAnchorLocator implements Locator {
        @objid ("33e47ef6-8a60-488e-b8aa-dc00558d16a0")
        private final NodeEditPart nep;

        @objid ("82b1fb09-f14b-4685-a87f-27d0da3c55b3")
        private final CreateConnectionRequest request;

        @objid ("8e955509-f56e-4a51-a61c-be6d7a2df127")
        public NodeSourceAnchorLocator(NodeEditPart nep, CreateConnectionRequest feedBackReq) {
            this.nep = nep;
            this.request = feedBackReq;
        }

        @objid ("e9a66abc-ce2b-4f8f-9327-f57d1eb3d80a")
        @Override
        public void relocate(IFigure target) {
            if (this.request.getLocation() == null) {
                return;
            }
            ConnectionAnchor anchor = this.nep.getSourceConnectionAnchor(this.request);
            Point loc = anchor.getLocation(this.request.getLocation());
            target.translateToRelative(loc);
            
            Rectangle b = new Rectangle(loc, target.getPreferredSize());
            b.translate(-b.width() / 2, -b.height() / 2);
            
            target.setBounds(b);
        }

    }

}
