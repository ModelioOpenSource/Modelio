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

package org.modelio.diagram.editor.bpmn.elements.workflow;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.editor.bpmn.elements.diagrams.BpmnDiagramUnmaskHelper;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneSetContainerLayout;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramLayout;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayer2;
import org.modelio.diagram.elements.core.figures.freeform.IExtentFilter;
import org.modelio.diagram.elements.core.figures.freeform.IFreeformFigure2;
import org.modelio.diagram.elements.core.link.linknode.AbstractCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;

@objid ("9546f148-fd9b-4449-becb-2404eea19209")
public class WorkflowEditPart extends FreeZoneEditPart {
    @objid ("8d55ec4f-381c-4ccf-898d-28fcbb8c53c0")
    private LayoutMode currentLayoutMode;

    /**
     * Please choose here between Peste and Cholera for layout of embedded workflow containing lanes.
     * <p>
     * <li>true : everything outside the lane will be cropped out
     * <li>false : if something is on the left or top of the lane, the lane won't take the whole place in order to not break connection bend points layout.
     */
    @objid ("f064ce57-a9a6-4d7a-9090-0c70e6e9d2aa")
    private static boolean CROP_OUTSIDE_LANES = true;

    /**
     * A temporary point to avoid allocations of hundred of Points. Used only by {@link #createAnchor(Point)}
     */
    @objid ("5a2b9443-db60-4558-a8d2-35662397bd3a")
    private static final Point tmpAnchor = new Point();

    @objid ("ab2e12f7-9c6b-417f-a76f-581d402dd59e")
    @Override
    protected IFigure createFigure() {
        IFigure fig;
        fig = new WorkflowFreeformLayer(this::calculateLayoutMode);
        fig.setLayoutManager(new AbstractDiagramLayout());
        
        // The figure must be kept transparent
        fig.setOpaque(false);
        fig.setBackgroundColor(null);
        return fig;
    }

    @objid ("7a9bfd79-ae35-46bf-a648-fbf3d58c5c64")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new WorkflowLayoutEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new WorkflowDropEditPolicy());
        
        // Policy to create Link+Node for sequence flow and data association (allow to create a node as target of a new link, user can choose the kind of node)
        installEditPolicy(AbstractCreateLinkChooseNodeEditPolicy.ROLE, new BpmnCreateLinkChooseNodeEditPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END, new LinkedNodeFinishCreationEditPolicy());
    }

    @objid ("abbe1225-e3df-4924-bdd2-0df671c65e23")
    @Override
    public GmWorkflow getModel() {
        return (GmWorkflow) super.getModel();
    }

    @objid ("b141d5a4-6b89-49be-8423-1ef15ac1299a")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("0baa9454-1734-4992-951a-1ab742472e47")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_CHILDREN)) {
            refreshLayout();
        } else if (evt.getPropertyName().equals(GmModel.PROP_REFRESH_FROM_OBMODEL)) {
            getUnmaksHelper().unmaskAllWorkflowElements();
        }
    }

    @objid ("953dfc3c-dae3-48a5-9435-fe548386c39e")
    @Override
    public void refresh() {
        super.refresh();
        refreshLayout();
    }

    @objid ("a1219fc8-f450-4507-8d8d-2f69a69852b4")
    private void refreshLayout() {
        WorkflowFreeformLayer fig = (WorkflowFreeformLayer) getFigure();
        
        LayoutManager layoutManager = fig.getLayoutManager();
        LayoutMode newLayoutMode = calculateLayoutMode();
        if (layoutManager == null || this.currentLayoutMode != newLayoutMode) {
            this.currentLayoutMode = newLayoutMode;
            switch (newLayoutMode) {
            case EMBEDDED_EDITABLE:
            case EMBEDDED_READ_ONLY:
                updateLayoutManager(fig, layoutManager, new AbstractDiagramLayout());
                fig.setBorder(new MarginBorder(5));
                fig.setExtentFilter(null);
                unhackParentFigures();
                break;
            case EMBEDDED_LANE_CONTAINER:
                updateLayoutManager(fig, layoutManager, new EmbeddedLaneSetContainerLayout());
                fig.setBorder(null);
                if (WorkflowEditPart.CROP_OUTSIDE_LANES) {
                    fig.setExtentFilter(WorkflowEditPart::isLaneSet);
                    hackParentFigures();
                }
                break;
            case ROOT:
                updateLayoutManager(fig, layoutManager, new AbstractDiagramLayout());
                fig.setBorder(null);
                fig.setExtentFilter(null);
                unhackParentFigures();
                break;
            default:
                break;
            }
        }
    }

    @objid ("e3a3765e-86ec-441b-837c-7260847f9c0c")
    private LayoutMode calculateLayoutMode() {
        GmWorkflow gm = getModel();
        if (!gm.isEmbedded()) {
            return LayoutMode.ROOT;
        } else if (gm.isEmbeddedWithLanes()) {
            return LayoutMode.EMBEDDED_LANE_CONTAINER;
        } else if (gm.isUserEditable()) {
            return LayoutMode.EMBEDDED_EDITABLE;
        } else {
            return LayoutMode.EMBEDDED_READ_ONLY;
        }
    }

    @objid ("74e07ab5-49d2-4f2d-8af2-5c506574a397")
    private BpmnDiagramUnmaskHelper getUnmaksHelper() {
        return new BpmnDiagramUnmaskHelper(getModel().getDiagram(), getViewer());
    }

    @objid ("3c4ce65a-6b25-462a-ad7d-201c775494d4")
    private void refreshChildrenLayoutData() {
        getChildren().forEach(o -> {
            GraphicalEditPart childEp = (GraphicalEditPart) o;
            Object ld = ((IGmObject) childEp.getModel()).getLayoutData();
            setLayoutConstraint(childEp, childEp.getFigure(), ld);
        });
    }

    /**
     * Create a XY anchor with the layer coordinates
     * 
     * @param absPoint an absolute point.
     * @return a XY anchor for the layer figure
     */
    @objid ("82b3d30b-a4ee-4c15-a44e-696ada69f327")
    private ConnectionAnchor createAnchor(final Point absPoint) {
        WorkflowEditPart.tmpAnchor.setLocation(absPoint);
        getFigure().translateToRelative(WorkflowEditPart.tmpAnchor);
        PointAnchor pointAnchor = new PointAnchor(getFigure(), WorkflowEditPart.tmpAnchor);
        return pointAnchor;
    }

    @objid ("608326ba-9198-4b42-bc39-1c566f8f7276")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

    @objid ("3f77f7cb-05ff-467f-b96c-c5a65c2269ef")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

    @objid ("a3f21ef6-a8dc-4151-8882-7a9b7238cb73")
    private void updateLayoutManager(IFigure fig, LayoutManager oldLayoutManager, LayoutManager newLayoutManager) {
        if (oldLayoutManager != null && oldLayoutManager.getClass() == newLayoutManager.getClass()) {
            return;
        }
        
        fig.setLayoutManager(newLayoutManager);
        if (newLayoutManager instanceof XYLayout) {
            refreshChildrenLayoutData();
        }
    }

    @objid ("2ac255b3-f9c1-49ca-aa50-19d3fd6eeda2")
    private void hackParentFigures() {
        IFigure fig = getFigure();
        IFigure parent = fig.getParent();
        while (parent instanceof IFreeformFigure2) {
            final IFigure ffig = fig;
            ((IFreeformFigure2) parent).setExtentFilter(f -> f == ffig);
            fig = parent;
            parent = parent.getParent();
        }
    }

    @objid ("ee8d7def-e0cb-4075-af6a-69dd54d29a34")
    private void unhackParentFigures() {
        if (WorkflowEditPart.CROP_OUTSIDE_LANES) {
            IFigure parent = getFigure().getParent();
            while (parent instanceof IFreeformFigure2) {
                ((IFreeformFigure2) parent).setExtentFilter(IExtentFilter.NONE);
                parent = parent.getParent();
            }
        }
    }

    @objid ("2d22b174-7d3d-4876-9128-4a181691f4a5")
    private static boolean isLaneSet(IFigure f) {
        return (ChainedLayout.getRootLayout(f) instanceof BpmnLaneSetContainerLayout);
    }

    /**
     * {@link FreeformLayer2} that is transparent but that respond to mouse clicks.
     * 
     * Has special behavior when the workflow is embedded and only contain lanes.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("07051deb-7fc0-4149-83b2-4ceb6c52459b")
    public static class WorkflowFreeformLayer extends FreeformLayer2 {
        @objid ("99e567c9-5edd-4882-8d74-1cf27f81f07c")
        private Supplier<LayoutMode> layoutMode;

        /**
         * Copy of Figure.findFigureAt(...) code to act as an opaque figure.
         */
        @objid ("709b1107-53ee-4cdd-9da8-a7e7a127a175")
        @Override
        public IFigure findFigureAt(int x, int y, TreeSearch search) {
            // Copy of Figure.findFigureAt(...) code to act as an opaque figure
            if (!containsPoint(x, y)) {
                return null;
            }
            if (search.prune(this)) {
                return null;
            }
            IFigure child = findDescendantAtExcluding(x, y, search);
            if (child != null) {
                return child;
            }
            if (search.accept(this)) {
                return this;
            }
            return null;
        }

        /**
         * Act as opaque figure.
         */
        @objid ("79fde57d-d8e5-4afb-861a-87fd345ed2bc")
        @Override
        public boolean containsPoint(int x, int y) {
            return getBounds().contains(x, y);
        }

        @objid ("03622dc2-33fa-45c2-ac08-5aeeebb3a5dd")
        public WorkflowFreeformLayer(Supplier<LayoutMode> layoutMode) {
            this.layoutMode = layoutMode;
        }

        /**
         * Uses the preferred size instead of bounds when the workflow is embedded and contains lanes.
         */
        @objid ("6bd50cc6-7b96-4005-8e47-931cdfcb0067")
        @Override
        public Rectangle getFreeformExtent() {
            Rectangle b = super.getFreeformExtent().getCopy();
            switch (this.layoutMode.get()) {
            case ROOT:
            case EMBEDDED_EDITABLE:
                // ensure bounds starts at least at 0,0
                if (!b.isEmpty()) {
                    Point orig = new Point();
                    translateToParent(orig);
                    b.union(orig);
                }
                return b;
            case EMBEDDED_LANE_CONTAINER:
                // Set size as preferred size
                Dimension preferredSize = getMinimumSize().getCopy();
                translateToParent(preferredSize);
                b.setSize(preferredSize);
                return b;
            case EMBEDDED_READ_ONLY:
            default:
                return b;
            
            }
        }

    }

    @objid ("7d1b533b-bf3c-4ed2-9b96-171fafe0de3e")
    private static final class EmbeddedLaneSetContainerLayout extends AbstractDiagramLayout {
        @objid ("b1d225c3-414b-4298-af85-ecfb106809a9")
        @Override
        public void layout(IFigure parent) {
            super.layout(parent);
            
            Point offset = getOrigin(parent);
            Rectangle parentBounds = parent.getBounds();
            List<IFigure> lchildren = parent.getChildren();
            for (IFigure f : lchildren) {
                if (isLaneSet(f)) {
                    Rectangle bounds = getConstraint(f);
                    if (bounds == null) {
                        continue;
                    }
            
                    // With this lanes take all space unless there is something else on top or left.
                    // With this notes and bend points outside are still coherent.
                    bounds = bounds.getTranslated(offset);
                    bounds.width = parentBounds.width();
                    bounds.height = parentBounds.height();
                    f.setBounds(bounds);
                }
            }
        }

        /**
         * Use only lanesets to compute preferred size, ignore other figures.
         * @see StackLayout#calculatePreferredSize(IFigure, int, int)
         */
        @objid ("4c025c8f-e068-44cd-9f35-32df73e0d84b")
        @Override
        protected Dimension calculatePreferredSize(final IFigure figure, final int wHint, final int hHint) {
            final Rectangle lanesetrect = new Rectangle();
            final Rectangle nonlanerect = new Rectangle();
            final Rectangle childRect = new Rectangle();
            final List<IFigure> children = figure.getChildren();
            for (IFigure child : children) {
                final Rectangle childConstraint = (Rectangle) this.constraints.get(child);
                boolean childIsLaneSet = isLaneSet(child);
                if (childConstraint == null) {
                    continue;
                }
                final Rectangle mergeRect = childIsLaneSet ? lanesetrect : nonlanerect;
            
                childRect.setBounds(childConstraint);
                if (childIsLaneSet) {
                    childRect.setSize(child.getPreferredSize(-1, -1));
                } else if (childRect.width == -1 || childRect.height == -1) {
                    Dimension prefSize = child.getPreferredSize(childRect.width, childRect.height);
                    if (childRect.width == -1) {
                        childRect.width = prefSize.width;
                    }
                    if (childRect.height == -1) {
                        childRect.height = prefSize.height;
                    }
                }
            
                if (mergeRect.isEmpty()) {
                    mergeRect.setBounds(childRect);
                } else {
                    mergeRect.union(childRect);
                }
            }
            
            final Rectangle rect;
            if (WorkflowEditPart.CROP_OUTSIDE_LANES) {
                rect = lanesetrect;
            } else {
                rect = lanesetrect.getUnion(nonlanerect);
            }
            
            Insets insets = figure.getInsets();
            return new Dimension(rect.width + insets.getWidth(), rect.height
                    + insets.getHeight()).union(getBorderPreferredSize(figure));
        }

    }

    @objid ("a6e46c90-5923-4848-bd5e-cc1e1f1aec55")
    protected enum LayoutMode {
        ROOT,
        EMBEDDED_LANE_CONTAINER,
        EMBEDDED_EDITABLE,
        EMBEDDED_READ_ONLY;
    }

}
