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
package org.modelio.linkeditor.gef.background;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration;
import org.modelio.linkeditor.panel.model.BackgroundModel;
import org.modelio.linkeditor.panel.model.EdgeBus;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.platform.ui.UIColor;

/**
 * Edit part of the background of the Link Editor.
 */
@objid ("1b855a97-5e33-11e2-b81d-002564c97630")
public class BackgroundEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {
    @objid ("1b855a9b-5e33-11e2-b81d-002564c97630")
    private static int HORIZONTAL_LAYOUT_RANK_BASE;

    @objid ("1b855a9c-5e33-11e2-b81d-002564c97630")
    private static int HORIZONTAL_LAYOUT_OFFSET_SPACING;

    @objid ("1b87bbf5-5e33-11e2-b81d-002564c97630")
    private static int VERTICAL_LAYOUT_RANK_BASE;

    @objid ("1b87bbf6-5e33-11e2-b81d-002564c97630")
    private static int VERTICAL_LAYOUT_OFFSET_SPACING;

    @objid ("eee42bd7-acfd-40c8-9fd0-cd645872d925")
    public static final String LAYOUT_ORIENTATION = "LAYOUT_ORIENTATION";

    @objid ("5bf29281-f048-428c-acdb-28c0f7bd97b9")
    public static final String EDIT_MODE = "EDIT_MODE";

    @objid ("ee8aded0-6e2a-413f-b3ef-9c38b7d5772f")
    public static final String VIEWERPROP_SWITCH_EDIT_MODE = "switchEditModeRunnable";

    @objid ("53d7c013-11e3-43ab-87c5-be0d5aa5fb6a")
    private final IEclipseContext context;

    @objid ("1b87bbf7-5e33-11e2-b81d-002564c97630")
    @Override
    public void activate() {
        super.activate();
        getModel().addPropertyChangeListener(this);
        getViewer().addPropertyChangeListener(this);
        
    }

    @objid ("1b87bbfa-5e33-11e2-b81d-002564c97630")
    @Override
    public void deactivate() {
        getModel().removePropertyChangeListener(this);
        getViewer().removePropertyChangeListener(this);
        super.deactivate();
        
    }

    @objid ("1b87bbfd-5e33-11e2-b81d-002564c97630")
    @Override
    public BackgroundModel getModel() {
        return (BackgroundModel) super.getModel();
    }

    @objid ("1b87bc02-5e33-11e2-b81d-002564c97630")
    @Override
    public void performRequest(final Request req) {
        if (RequestConstants.REQ_OPEN.equals(req.getType())) {
        
            ((Runnable) getViewer().getProperty(VIEWERPROP_SWITCH_EDIT_MODE)).run();
        
            getModel().fireContentChanged();
        } else {
            super.performRequest(req);
        }
        
    }

    @objid ("1b87bc09-5e33-11e2-b81d-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // handle model property change (CONTENT => central node changed or updated: re-apply layout).
        if (evt.getPropertyName().equals(BackgroundModel.CONTENT)) {
            // Compute the layout offsets based on the nodes size for horizontal layout.
            BackgroundEditPart.HORIZONTAL_LAYOUT_RANK_BASE = GraphNode.WIDTH * 75 / 100;
            BackgroundEditPart.HORIZONTAL_LAYOUT_OFFSET_SPACING = GraphNode.HEIGHT * 175 / 100;
        
            // Compute the layout offsets based on the nodes size for vertical layout.
            BackgroundEditPart.VERTICAL_LAYOUT_RANK_BASE = -GraphNode.HEIGHT * 125 / 100;
            BackgroundEditPart.VERTICAL_LAYOUT_OFFSET_SPACING = GraphNode.WIDTH * 125 / 100;
        
            refreshChildren();
            refreshVisuals();
        }
        
        if (evt.getPropertyName().equals(EDIT_MODE)) {
            refreshVisuals();
        }
        
    }

    @objid ("1b87bc0e-5e33-11e2-b81d-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        Node model = (Node) childEditPart.getModel();
        getContentPane().add(child, new Rectangle(model.x, model.y, model.width, model.height), index);
        
    }

    @objid ("1b87bc17-5e33-11e2-b81d-002564c97630")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DropEditPolicy());
    }

    @objid ("1b87bc1a-5e33-11e2-b81d-002564c97630")
    @Override
    protected IFigure createFigure() {
        FreeformLayer freeformLayer = new FreeformLayer();
        freeformLayer.setLayoutManager(new FreeformLayout());
        freeformLayer.setOpaque(true);
        Color swtColor = isEditMode() ? UIColor.TEXT_WRITABLE_BG : UIColor.POSTIT_YELLOW;
        freeformLayer.setBackgroundColor(swtColor);
        return freeformLayer;
    }

    @objid ("1b87bc21-5e33-11e2-b81d-002564c97630")
    @Override
    protected List<?> getModelChildren() {
        BackgroundModel directedGraph = getModel();
        boolean vertical = isVerticalLayout();
        
        Node center = directedGraph.getCenter();
        if (center != null) {
            // "blank" layout
            int offsetLeft = layout(center, 0, 0, vertical, true);
            int offsetRight = layout(center, 0, 0, vertical, false);
            // Now use the computed offsets to align both sides of the tree.
            int offset = Math.max(offsetLeft, offsetRight);
            layout(center, 0, (offset - offsetLeft) / 2, vertical, true);
            layout(center, 0, (offset - offsetRight) / 2, vertical, false);
            // Make sure all buses span as much as needed
            fixBusesSize(directedGraph, vertical);
            // And finally center the whole tree in the Control.
            shiftAll(directedGraph);
        }
        return directedGraph.nodes;
    }

    @objid ("1b87bc28-5e33-11e2-b81d-002564c97630")
    private void fixBusesSize(final BackgroundModel directedGraph, final boolean vertical) {
        for (Object node : directedGraph.nodes) {
            if (node instanceof EdgeBus) {
                EdgeBus edgeBus = (EdgeBus) node;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
        
                for (Object incomingEdge : edgeBus.incoming) {
                    Node source = ((Edge) incomingEdge).source;
                    if (vertical) {
                        int dx = source.x + source.width / 2;
                        min = Math.min(min, dx - source.width / 8);
                        max = Math.max(max, dx + source.width / 8);
                    } else {
                        int dy = source.y + source.height / 2;
                        min = Math.min(min, dy - source.height / 4);
                        max = Math.max(max, dy + source.height / 4);
                    }
                }
        
                for (Object outgoingEdge : edgeBus.outgoing) {
                    Node target = ((Edge) outgoingEdge).target;
                    if (vertical) {
                        int dx = target.x + target.width / 2;
                        min = Math.min(min, dx - target.width / 8);
                        max = Math.max(max, dx + target.width / 8);
                    } else {
                        int dy = target.y + target.height / 2;
                        min = Math.min(min, dy - target.height / 4);
                        max = Math.max(max, dy + target.height / 4);
                    }
                }
        
                if (vertical) {
                    edgeBus.x = min;
                    edgeBus.width = max - min;
                } else {
                    edgeBus.y = min;
                    edgeBus.height = max - min;
                }
            }
        }
        
    }

    /**
     * Get the size of the control showing the viewer .
     * <p>
     * Scroll bars are deduced from the view size.
     * @return the visible view size.
     */
    @objid ("1b87bc2e-5e33-11e2-b81d-002564c97630")
    private Point getViewAreaSize() {
        final Point controlSize = new Point(0, 0);
        // this has to be done in the display thread to avoid
        // InvalidThreadAccessException.
        getViewer().getControl().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                final Control control = BackgroundEditPart.this.getViewer().getControl();
                Point p = control.getSize();
                controlSize.x = p.x;
                controlSize.y = p.y;
        
                if (control instanceof Scrollable) {
                    Scrollable c = (Scrollable) control;
                    ScrollBar b = c.getHorizontalBar();
                    if (b != null /* && b.isVisible() */) {
                        controlSize.y -= b.getSize().y;
                    }
                    b = c.getVerticalBar();
                    if (b != null /* && b.isVisible() */) {
                        controlSize.x -= b.getSize().x;
                    }
                }
            }
        });
        return controlSize;
    }

    /**
     * Layout a node, ie set its position.
     * @param goLeft
     * - the direction of the graph navigation.
     * @param node - the node to layout
     * @param rank - the tree depth coordinate where to layout the node.<br/>
     * For vertical layout rank is the Y coordinate, for horizontal layout rank is the X coordinate.
     * @param offset - the coordinate, measured on the axis that is orthogonal to the rank axis, where to layout the node.<br/>
     * For vertical layout the offset is the X coordinate, for horizontal layout offset is the Y coordinate.
     * @param vertical - the layout orientation, ie the orientation of the edges : vertical => vertical edges
     * @return the new offset coordinate after layout
     */
    @objid ("1b87bc33-5e33-11e2-b81d-002564c97630")
    private int layout(final Node node, final int rank, final int offset, final boolean vertical, final boolean direct) {
        int rankSpacing = vertical ? BackgroundEditPart.VERTICAL_LAYOUT_RANK_BASE : BackgroundEditPart.HORIZONTAL_LAYOUT_RANK_BASE;
        int offsetSpacing = (vertical ? BackgroundEditPart.VERTICAL_LAYOUT_OFFSET_SPACING : BackgroundEditPart.HORIZONTAL_LAYOUT_OFFSET_SPACING);
        
        int newOffset = offset;
        
        EdgeList edges = direct ? node.outgoing : node.incoming;
        
        // Start by layouting children
        for (Object edgeObj : edges) {
            Edge edge = (Edge) edgeObj;
            Node nextNode = direct ? edge.target : edge.source;
        
            int rankIncrement;
            if (node instanceof EdgeBus) {
                // after a edgebus 2 rank spacing units
                rankIncrement = rankSpacing * 2;
            } else if (nextNode instanceof EdgeBus) {
                // before a edgebus 1 rank spacing units
                rankIncrement = (rankSpacing);
            } else {
                // otherwise 2 rank spacing units
                rankIncrement = rankSpacing * 2;
            }
        
            int nextRank = direct ? rank + rankIncrement : rank - rankIncrement;
        
            newOffset = offsetSpacing + layout(nextNode, nextRank, newOffset, vertical, direct);
        }
        
        if (edges.size() > 0) {
            newOffset -= offsetSpacing;
        }
        
        // Now layout node
        if (vertical) {
            // vertical layout
            node.x = offset + ((newOffset - offset) / 2);
            node.y = (rank /* * RANK_GAP */) - (node.height / 2);
        } else {
            // horizontal layout
            node.x = (rank /* * RANK_GAP */) - (node.width / 2);
            node.y = offset + ((newOffset - offset) / 2);
        }
        return newOffset;
    }

    /**
     * Align the center node on the center of the view or one of its border depending on the edges existence.
     * @param directedGraph the model graph
     */
    @objid ("1b87bc43-5e33-11e2-b81d-002564c97630")
    private void shiftAll(final BackgroundModel directedGraph) {
        // Center the center node in the view.
        // Get the size of the control showing the viewer (this has to be done
        // in the display thread to avoid InvalidThreadAccessException).
        final Point controlSize = getViewAreaSize();
        
        if (controlSize.x <= 0 || controlSize.y <= 0) {
            // FIXME We were called too early, SWT not yet ready
            return;
        }
        
        final GraphNode centerNode = directedGraph.getCenter();
        
        // Compute the vector to align the nodes.
        int xDelta;
        int yDelta;
        xDelta = (controlSize.x / 2) - (centerNode.x + (centerNode.width / 2));
        yDelta = (controlSize.y / 2) - (centerNode.y + (centerNode.height / 2));
        
        // Move all nodes
        for (Object nodeObj : directedGraph.nodes) {
            Node node = (Node) nodeObj;
            node.x += xDelta;
            node.y += yDelta;
        }
        
    }

    @objid ("e5f57cc7-5efd-11e2-a8be-00137282c51b")
    public  BackgroundEditPart(IEclipseContext context) {
        this.context = context;
    }

    @objid ("4446ecd0-d6fc-4e04-bf3e-1e60e77b6d3a")
    public boolean isEditMode() {
        return Boolean.TRUE.equals(getViewer().getProperty(EDIT_MODE) );
    }

    @objid ("e5a04ce9-9a4e-4443-9847-cdc6d4c077bc")
    public boolean isVerticalLayout() {
        return getViewer().getProperty(LAYOUT_ORIENTATION) == ILinkEditorConfiguration.Orientation.Vertical;
    }

    @objid ("10984215-e0d6-4a30-8656-d53de3a08206")
    public IEclipseContext getContext() {
        return this.context;
    }

    @objid ("9b6c62a8-c83a-465c-963e-fcf29acd6528")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        Color swtColor = isEditMode() ? UIColor.TEXT_WRITABLE_BG : UIColor.POSTIT_YELLOW;
        getFigure().setBackgroundColor(swtColor);
        
    }

}
