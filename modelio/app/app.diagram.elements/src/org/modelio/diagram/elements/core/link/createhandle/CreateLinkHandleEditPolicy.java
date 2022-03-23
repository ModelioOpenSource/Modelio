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
package org.modelio.diagram.elements.core.link.createhandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.link.extensions.FractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.tools.BendedConnectionAndNodeCreationTool;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that add a "create link" drag handle on the middle of the node figure.
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
    private boolean dragInProgress;

    /**
     * The role this edit policy should be installed with.
     */
    @objid ("0453b015-fa99-4bcf-8da3-01fc71f037be")
    public static final Object ROLE = CreateLinkHandleEditPolicy.class;

    @objid ("ccd80552-06cb-409e-ab76-d20c311c3b69")
    protected final GraphicalEditPart connSource;

    @objid ("0ba2f441-ad39-4ac1-9c51-f92ee734de79")
    private MouseMotionListener mouseMotionListener;

    /**
     * Hold in static var the currently displayed create link handles policy.
     */
    @objid ("a0027512-98e4-4f82-be2b-151901d9d11c")
    private static CreateLinkHandleEditPolicy currentDisplayed;

    /**
     * Trigger to Remove create handles 1 second after the mouse moves out of the node, unless the mouse comes back before.
     */
    @objid ("275c1ee3-79f0-4a3b-a01f-8ee587f3366c")
    private CompletableFuture<Void> removeHandlesFuture;

    @objid ("2dab6c0a-bc01-4577-8cc5-70bfcdafafb8")
    private static IFixedConnectionAnchorFactory defaultAnchorProvider = new VariableFixedAnchorProvider();

    /**
     * @param connsource the created connections source edit part.
     */
    @objid ("e012ca7c-2702-4d0a-9760-3884b03420be")
    public  CreateLinkHandleEditPolicy(GraphicalEditPart connsource) {
        this.connSource = connsource;
    }

    @objid ("19de9917-9808-46de-9173-ef92f8943706")
    @Override
    public void activate() {
        this.dragInProgress = false;
        
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
            this.dragInProgress = false;
        }
        
    }

    @objid ("2e30ab5d-20e9-4cfb-9a24-0795a5e05c84")
    @Override
    public void eraseTargetFeedback(Request request) {
        // listen for REQ_SELECTION instead of REQ_SELECTION_HOVER.
        // in the other case the handle disappear when beginning dragging.
        if (RequestConstants.REQ_SELECTION.equals(request.getType()) && this.handles != null) {
            // done by mouse listener
            //removeSelectionHandles();
        
            // Remove create handles 1 second after getting out of node, unless it comes back
            if (this.removeHandlesFuture==null || this.removeHandlesFuture.isDone()) {
                CompletableFuture<Void> f = new CompletableFuture<>();
                Display.getCurrent().timerExec(500, () -> triggerRemoveHandles(f));
                this.removeHandlesFuture = f;
            }
        }
        
    }

    @objid ("1dff8e2f-2a01-4025-8998-c3f936a81ed1")
    private void triggerRemoveHandles(CompletableFuture<Void> future) {
        if (future.isCancelled())
            return;
        
        if (getHost().isActive()) {
            removeSelectionHandles();
        }
        
        future.complete(null);
        
    }

    @objid ("fc24a5df-99fa-43e5-b530-69239435cce5")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_SELECTION_HOVER.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("c69c40c5-16b4-425f-a1b6-39596a239b27")
    @Override
    public void showSourceFeedback(Request request) {
        Object type = request.getType();
        if (RequestConstants.REQ_MOVE.equals(type)
                || RequestConstants.REQ_MOVE_CHILDREN.equals(type)) {
            if (!this.dragInProgress) {
                this.dragInProgress = true;
                hideSelection();
            }
        }
        
    }

    @objid ("7f839032-f4d1-4dee-a528-81e2fc9b0200")
    @Override
    public void showTargetFeedback(Request request) {
        int hostSelected = getHost().getSelected();
        if (!this.dragInProgress && hostSelected==0) {
            Object reqType = request.getType();
            if (RequestConstants.REQ_SELECTION_HOVER.equals(reqType)) {
                if (this.handles == null && isHandleToDisplay()) {
                    addSelectionHandles();
                }
            } else if (RequestConstants.REQ_SELECTION.equals(reqType)) {
                if(this.removeHandlesFuture != null && !this.removeHandlesFuture.isDone()) {
                    this.removeHandlesFuture.cancel(false);
                    this.removeHandlesFuture = null;
                }
            }
        }
        
    }

    /**
     * Redefined to ensure only one node display create link anchors,
     * and to register created handles in the visual parts registry.
     */
    @objid ("c49df2b8-667c-4b3c-af90-d506609da581")
    @Override
    protected void addSelectionHandles() {
        // Call inherited behavior
        super.addSelectionHandles();
        
        // Without this the handle disappear when the mouse hovers it.
        if (this.handles != null) {
            setCurrentDisplayed(this);
            for (Object handle : this.handles) {
                getHost().getViewer().getVisualPartMap().put(handle, getHost());
            }
        }
        
    }

    @objid ("9029ec39-b717-4f05-9186-b1cf163353b1")
    private static void setCurrentDisplayed(CreateLinkHandleEditPolicy newDisplayed) {
        if (CreateLinkHandleEditPolicy.currentDisplayed == newDisplayed)
            return;
        
        if (CreateLinkHandleEditPolicy.currentDisplayed != null) {
            // Only one node may display create link anchors
            CreateLinkHandleEditPolicy.currentDisplayed.removeSelectionHandles();
        }
        
        CreateLinkHandleEditPolicy.currentDisplayed = newDisplayed;
        
    }

    @objid ("ba94eafa-834c-4030-957c-89a419af8a58")
    @Override
    protected List createSelectionHandles() {
        IFigure srcFigure = this.connSource.getFigure();
        if (this.connSource instanceof ConnectionEditPart) {
            // source is a connection, return one handle at 2/3 of the connection and fast exit.
            return Arrays.asList(new CreateHandle(
                    this.connSource,
                    new FractionalConnectionLocator((Connection) srcFigure, 0.6, false)));
        }
        
        IFixedConnectionAnchorFactory anchorFactory = this.connSource.getAdapter(IFixedConnectionAnchorFactory.class);
        if (anchorFactory == null)
            anchorFactory = defaultAnchorProvider;
        
        List<CreateHandle> ret = new ArrayList<>();
        for (ConnectionAnchor anchor : anchorFactory.getAllAnchors(srcFigure, ConnectionRouterId.ORTHOGONAL, null)) {
            Locator loc =  (IFigure target) -> {
                Point p = anchor.getReferencePoint();
                if (true) {
                    // move the handle ANCHOR_RADIUS px farther from the figure to avoid overriding resize handles
                    Rectangle srcBounds = srcFigure.getBounds().getCopy();
                    srcFigure.translateToAbsolute(srcBounds);
        
                    p = p.getCopy();
                    GeomUtils.translate(p, GeomUtils.getDirection(p, srcBounds), IFixedConnectionAnchorFactory.ANCHOR_RADIUS);
                }
                Rectangle r = new Rectangle(p.x, p.y,1,1);
                target.translateToRelative(r);
                r.expand(IFixedConnectionAnchorFactory.ANCHOR_RADIUS, IFixedConnectionAnchorFactory.ANCHOR_RADIUS);
                r.expand(target.getInsets());
                target.setBounds(r);
            };
            CreateHandle handle = new CreateHandle(this.connSource, loc);
            ret.add(handle);
        }
        return ret;
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

    @objid ("124093e9-d42b-453d-b745-adfb04afa6e5")
    @Override
    protected void removeSelectionHandles() {
        if (this.handles != null) {
            Map<?,EditPart> visualPartMap = getHost().getViewer().getVisualPartMap();
            for (Object h : this.handles) {
                visualPartMap.remove(h);
            }
        }
        
        super.removeSelectionHandles();
        
        this.removeHandlesFuture = null;
        
    }

    @objid ("9164bc3e-0d08-4f11-8665-543b54fa5241")
    @Override
    protected void showSelection() {
        if (false) {
            // Don't show drag handle immediately, do it only on "hover".
            // Don't call: super.showSelection();
        
            // REQ_SELECTION_HOVER requests are not received anymore when the node is selected,
            // children nodes get it instead. We need our own hover listener.
            if (isHandleToDisplay()) {
                getHostFigure().addMouseMotionListener(getMouseMotionListener());
            }
        } else {
            // remove "create link" handles once selected to avoid overriding resize anchors.
            removeSelectionHandles();
        }
        
    }

    /**
     * Lazy deferred instantiation of the mouse motion listener
     * @return the mouse motion listener
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
    protected static class CreateHandle extends AbstractHandle {
        @objid ("f86c7f45-75ce-43e6-9210-b51636f2ba74")
        private static final UniversalLabelProvider labelProvider = new UniversalLabelProvider();

        @objid ("b01cb947-3ffc-4801-b782-180bac0ce931")
        public  CreateHandle(GraphicalEditPart owner, Locator locator) {
            super(owner, locator);
            setLayoutManager(new StackLayout());
            
            // Make a circle figure
            Ellipse child = AnchorFigureFactory.createHandleFigure(owner);
            
            add(child);
            
            setCursor(SharedCursors.CURSOR_PLUG);
            
            // Setup handle tooltip
            IGmModelRelated model = (IGmModelRelated) owner.getModel();
            
            String text = DiagramElements.I18N.getMessage("CreateLinkHandle.tooltip", Optional.ofNullable(model)
                    .map(IGmModelRelated::getRelatedElement)
                    .map(el -> labelProvider.getText(el))
                    .orElse(""));
            
            setToolTip(new Label(text));
            
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
            BendedConnectionAndNodeCreationDragTracker tool2 = new BendedConnectionAndNodeCreationDragTracker(getOwner());
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
        private static final class BendedConnectionAndNodeCreationDragTracker extends BendedConnectionAndNodeCreationTool implements DragTracker {
            @objid ("7868727a-d343-4ddd-a701-f5f1a66c3486")
            private final EditPart owner;

            @objid ("38f7787a-2c9c-4d60-a40d-da54aad29d1a")
            public  BendedConnectionAndNodeCreationDragTracker(EditPart owner) {
                this.owner = owner;
            }

            @objid ("9154ff9e-81b1-4246-9a9e-dbee046b289e")
            @Override
            protected boolean updateTargetUnderMouse() {
                if (isInState(AbstractTool.STATE_INITIAL)) {
                    // Force the source to be the handle owner
                
                    EditPart newTarget = this.owner.getTargetEditPart(getTargetRequest());
                    if (getTargetEditPart() != newTarget) {
                        return updateTargetEditPart(newTarget, RequestConstants.REQ_CONNECTION_START);
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

}
