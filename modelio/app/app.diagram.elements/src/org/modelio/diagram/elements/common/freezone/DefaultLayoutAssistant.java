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
package org.modelio.diagram.elements.common.freezone;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Helper that moves edit parts to avoid intersections after a move or resize.
 * <p>
 * This helper uses and needs absolute coordinates only.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("38e7d2b9-d5c2-42c5-b567-cc08162828ec")
class DefaultLayoutAssistant implements ILayoutAssistant {
    @objid ("a71fd36b-5c32-4fa2-9691-03625bda38c5")
    private boolean isDirty = true;

    /**
     * If true all other nodes will keep same distance.
     * If false a minimal distance will be kept.
     */
    @objid ("32d83ac1-f9ed-4649-a7bc-e207f6c07c19")
    private boolean keepSameDistance = false;

    /**
     * the minimum distance between nodes.
     */
    @objid ("ef90b734-df85-43a4-ba0f-e54b0d3cfe57")
    private double minDist;

    /**
     * If true collision will be avoided with connection bend points too.
     */
    @objid ("2d46de15-dcd2-4dbc-a883-ae8f7e5b890d")
    private boolean avoidBendPoints = true;

    /**
     * The first moved edit part, to have an access to the edit part viewer.
     */
    @objid ("90e42fb4-b155-4b84-9006-844632fe35db")
    private GraphicalEditPart any;

    @objid ("919f33bb-1e47-4d05-a3bf-bf9fe9cac386")
    private Collection<BoundChange> changes = new ArrayList<>();

    /**
     * All movable connections with their points in absolute coordinates.
     */
    @objid ("d0c485c1-15cb-424d-8bcb-8dbcd688d823")
    private Map<ConnectionEditPart, PointList> movableConnections = new HashMap<>();

    /**
     * All moveable nodes with their initial bounds in absolute coordinates.
     */
    @objid ("d389bb1f-e694-4c16-a3cf-ce478e0a37fb")
    private Map<GraphicalEditPart, Rectangle> movableNodes = new HashMap<>();

    /**
     * Nodes initially moved, sources of new intersections.
     */
    @objid ("9ed460f6-be50-429c-a49a-fef1467c7912")
    private Set<GraphicalEditPart> movedNodes = new HashSet<>();

    /**
     * All connection bendpoints to move
     */
    @objid ("e4e7ccdd-4d04-4e26-842f-bfc720348d9d")
    private Map<BpRef, BpMove> pushBendPointMoves = new HashMap<>();

    @objid ("d4042931-3f6e-4d2e-a3ed-20f4ff040823")
    private Collection<BendpointRequest> pushBendPointRequests;

    @objid ("ebb3f65b-0cf4-4b55-ab8e-cc7afdb6842a")
    private CompoundCommand pushExecCommand;

    /**
     * All nodes to push to avoid intersections.
     */
    @objid ("0cf3225d-e0e7-4e36-ba95-8bb3a5af0cf8")
    private Map<GraphicalEditPart, Move> pushNodeMoves = new HashMap<>();

    @objid ("c4974d60-700f-434c-9e8d-19890a292a0a")
    private Collection<ChangeBoundsRequest> pushNodeRequests;

    /**
     * Record a node bounds change and compute all changes to do to avoid new intersections.
     * @param node the moved edit part
     * @param oldBounds the old node bounds in absolute coordinates.
     * @param newBounds the new node bounds in absolute coordinates.
     */
    @objid ("12e79ba3-69b3-4ded-8605-47f975d8008c")
    @Override
    public void addBoundsChange(GraphicalEditPart node, PrecisionRectangle oldBounds, PrecisionRectangle newBounds) {
        this.changes.add(new BoundChange(node, oldBounds, newBounds));
        
        this.movedNodes.add(node);
        this.movableNodes.remove(node);
        //this.movableConnections.keySet().removeAll(node.getSourceConnections());
        //this.movableConnections.keySet().removeAll(node.getTargetConnections());
        
        Deque<GraphicalEditPart> clist = new ArrayDeque<>(node.getChildren());
        while (! clist.isEmpty()) {
            GraphicalEditPart c = clist.poll();
        
            this.movedNodes.add(c);
            this.movableNodes.remove(c);
            //this.movableConnections.keySet().removeAll(c.getSourceConnections());
            //this.movableConnections.keySet().removeAll(c.getTargetConnections());
            clist.addAll(c.getChildren());
        }
        
        this.isDirty = true;
        
    }

    @objid ("dd4931f6-16b2-4ecd-aedd-62d81601125d")
    @Override
    public void addCommand(Command cmd) {
        if (this.pushExecCommand == null) {
            this.pushExecCommand = new ExecuteCommand();
        }
        this.pushExecCommand.add(cmd);
        
    }

    /**
     * Add an involved child edit part
     * @param part the edit part
     * @param bounds the edit part bounds in absolute coords
     */
    @objid ("ed1b8f51-a13e-43be-ae63-b27e8b4f6394")
    public void addMovableNode(GraphicalEditPart part, Rectangle bounds) {
        this.movableNodes.put(part, bounds);
        
        if (this.any == null) {
            // first node : add all diagram connections
            this.any = part;
        
            // add all diagram connections
            Map<?, ?> epMap = getVisualPartMap();
            IFigure clayer = LayerManager.Helper.find(this.any).getLayer(LayerConstants.CONNECTION_LAYER);
            for (Object o : clayer.getChildren()) {
                if (o instanceof Connection) {
                    Connection connection = (Connection) o;
        
                    PointList pts = connection.getPoints().getCopy();
                    connection.translateToAbsolute(pts);
        
                    ConnectionEditPart cp = (ConnectionEditPart) epMap.get(connection);
                    if (cp != null) {
                        this.movableConnections.put(cp, pts);
                    } else {
                        DiagramElements.LOG.warning("DefaultLayoutAssistant: <%s> {%s} owned by <%s> has no edit part.", connection, connection.getPoints(), connection.getParent());
                    }
        
                }
            }
        }
        
    }

    @objid ("c31b8834-9f8a-4309-95a1-5d427629c9cf")
    @Override
    public Command createExecuteCommand() {
        if (this.pushExecCommand == null || this.pushExecCommand.isEmpty()) {
            return null;
        } else {
            return this.pushExecCommand;
        }
        
    }

    /**
     * Get the requests to apply to avoid connection intersections with changed nodes.
     * @return the bend point requests to apply.
     */
    @objid ("7573ab46-ab1a-4803-8efd-a4a02016d25c")
    @Override
    public Collection<BendpointRequest> getBendPointRequests() {
        compute();
        
        if (this.pushBendPointRequests == null) {
            this.pushBendPointRequests = new ArrayList<>();
            for (Entry<BpRef, BpMove> entry : this.pushBendPointMoves.entrySet()) {
                BendpointRequest moveBpRequest = createMoveBpRequest(entry.getKey(), entry.getValue());
                if (moveBpRequest != null) {
                    this.pushBendPointRequests.add(moveBpRequest);
                }
            }
        }
        return this.pushBendPointRequests;
    }

    /**
     * Get the requests to apply to avoid node intersections.
     * @return the requests to apply.
     */
    @objid ("1c391f20-8a37-4c01-9bf1-95c7e0ff25e7")
    @Override
    public Collection<ChangeBoundsRequest> getNodeRequests() {
        compute();
        
        if (this.pushNodeRequests == null) {
            Collection<ChangeBoundsRequest> newReqs = new ArrayList<>();
        
            for (Entry<GraphicalEditPart, Move> entry : this.pushNodeMoves.entrySet()) {
                GraphicalEditPart part = entry.getKey();
                Move move = entry.getValue();
        
                double dx = move.dx();
                double dy = move.dy();
        
                if (dx !=0 || dy != 0) {
                    newReqs.add(createMoveRequest(part, dx, dy));
                }
            }
        
            this.pushNodeRequests = newReqs;
        }
        return this.pushNodeRequests;
    }

    /**
     * Set the minimum distance between nodes.
     * @param d the minimum distance between nodes.
     */
    @objid ("ddae834f-9d01-48d3-8c1d-a6371d671639")
    public void setMinDist(double d) {
        this.minDist = d;
    }

    @objid ("29097ef9-6512-485f-af9c-01ad2bc60efd")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        if (this.pushNodeMoves.isEmpty() && this.pushBendPointMoves.isEmpty()) {
            s.append(getClass().getSimpleName());
            s.append("[]");
        } else {
            s.append(getClass().getSimpleName());
            s.append("[\n");
        
            for (Entry<GraphicalEditPart, Move> entry : this.pushNodeMoves.entrySet()) {
                s.append("  ");
                s.append(entry.getKey().getModel());
                s.append(" pushed ");
                s.append(entry.getValue());
                s.append("\n");
            }
        
            for (Entry<BpRef, BpMove> entry : this.pushBendPointMoves.entrySet()) {
                BpRef bpRef = entry.getKey();
                BpMove move = entry.getValue();
        
                s.append("  ");
                dump(bpRef.conn, s);
                s.append("[");
                s.append(bpRef.index);
                s.append("] bend point pushed ");
                s.append(move.toString());
                s.append("\n");
            }
        
            /*s.append(" Moved nodes=");
            for (GraphicalEditPart ep : this.movedNodes) {
                s.append("   - ");
                s.append(ep.getModel());
                s.append("\n");
            }*/
        
            s.append("]");
        }
        return s.toString();
    }

    @objid ("bd59ff05-ecbf-4274-8d67-4d1781d1c54d")
    static StringBuilder dump(ConnectionEditPart ep, StringBuilder s) {
        Object msrc = ep.getSource().getAdapter(MObject.class);
        Object mtarget = ep.getTarget().getAdapter(MObject.class);
        
        s.append(ep.getModel());
        s.append("(");
        s.append(msrc);
        s.append(" --> ");
        s.append(mtarget);
        s.append(")");
        return s;
    }

    @objid ("457045d5-c7dc-4fad-86fd-0df5afca5eb9")
    protected Map<?, ?> getVisualPartMap() {
        Map<?,?> visualPartMap = this.any.getViewer().getVisualPartMap();
        return visualPartMap;
    }

    /**
     * Tells whether a connection is to exclude.
     * <p>
     * A connection is to exclude if it is initially moved or joins to initially moved nodes.
     * @param cp a connection edit part.
     * @return whether the connection is to exclude.
     */
    @objid ("aa70a70c-3239-4d7f-9bc5-c93c41041274")
    protected boolean isToExclude(ConnectionEditPart cp) {
        if (this.movedNodes.contains(cp)) {
            return true;
        } else {
            // test both extremities
            return this.movedNodes.contains(cp.getTarget()) && this.movedNodes.contains(cp.getSource());
        }
        
    }

    /**
     * Remove connections to exclude.
     */
    @objid ("38e7dc20-a606-470e-8e49-9ba3366a0b46")
    private void cleanupConnections() {
        for (Iterator<Entry<ConnectionEditPart, PointList>> it = this.movableConnections.entrySet()
                .iterator(); it.hasNext();) {
            Entry<ConnectionEditPart, PointList> entry = it.next();
            ConnectionEditPart cp = entry.getKey();
        
            if (isToExclude(cp)) {
                //DiagramElements.LOG.debug("  DefaultLayoutAssistant: removing connection %s", dump(cp, new StringBuilder()));
                it.remove();
            }
        }
        
    }

    @objid ("2112f19b-b2fa-49a6-b0bc-dccc40048925")
    private void compute() {
        if (this.isDirty) {
            this.pushBendPointRequests = null;
            this.pushNodeRequests = null;
            this.pushNodeMoves.clear();
            this.pushBendPointMoves.clear();
        
            if (this.pushExecCommand != null) {
                // invalidate and drop last command
                this.pushExecCommand.getCommands().clear();
                this.pushExecCommand = null;
            }
        
            cleanupConnections();
        
            for (BoundChange c : this.changes) {
                computeBoundsChange(c.node, c.oldBounds, c.newBounds);
            }
        
            this.isDirty = false;
        }
        
    }

    @objid ("77f0dc66-ade0-441e-a90c-e870ee1c3123")
    private void computeBoundsChange(GraphicalEditPart changedNode, PrecisionRectangle oldBounds, PrecisionRectangle newBounds) {
        final Point moveDelta = newBounds.getLocation().translate(oldBounds.getLocation().negate());
        final Dimension sizeDelta = newBounds.getSize().shrink(oldBounds.getSize());
        final boolean isMove = sizeDelta.equals(0, 0);
        
        Rectangle newRect = newBounds.getCopy(); // new bounds expanded by minimum distance
        newRect.expand(this.minDist, this.minDist);
        
        Move refMove = new Move(); // how much each "node" borders moved
        
        if (moveDelta.x < 0) {
            refMove.leftMove = - moveDelta.x;
        }
        
        if (moveDelta.y < 0) {
            refMove.topMove = - moveDelta.y;
        }
        
        refMove.rightMove = sizeDelta.width() + moveDelta.x();
        refMove.bottomMove = sizeDelta.height() + moveDelta.y();
        
        
        Move neededPush = null; //
        
        Collection<GraphicalEditPart> existingIntersections = new ArrayList<>();
        Collection<Connection> existingConnIntersections = new ArrayList<>();
        
        if (this.keepSameDistance) {
            neededPush = refMove;
            neededPush.leftFrom = oldBounds.x;
            neededPush.topFrom = oldBounds.y;
            neededPush.rightFrom = oldBounds.right();
            neededPush.bottomFrom = oldBounds.bottom();
            neededPush.bottomCause = changedNode;
            neededPush.leftCause = changedNode;
            neededPush.rightCause = changedNode;
            neededPush.topCause = changedNode;
        } else {
            neededPush = new Move();
        
            // Compute intersection zone of the moved/resized element.
            // Keep same aspect ratio as the initial bounds because the aspect ratio
            // is needed to determine the push direction (horizontal or vertical)
            Rectangle intersectZone = getExpandedSameRatio(newBounds, this.minDist);
        
            for (Entry<GraphicalEditPart, Rectangle> entry : this.movableNodes.entrySet()) {
                Rectangle childRect = entry.getValue();
                if (childRect.intersects(oldBounds)) {
                    existingIntersections.add(entry.getKey());
                } else if (childRect.intersects(newRect)) {
                    // intersection found : move needed
                    Dimension pushDirection;
                    if (isMove) {
                        pushDirection = computeMovePushDirection(intersectZone, childRect);
                    } else {
                        pushDirection = computeResizePushDirection(oldBounds, childRect);
                    }
                    computePush(changedNode, childRect, newBounds, pushDirection, neededPush);
                }
            }
        
            // Compute bendpoints move
            if (this.avoidBendPoints) {
                Point bendPointLoc = new Point();
                Rectangle bendPointBounds = new Rectangle();
                for (Entry<ConnectionEditPart, PointList> e : this.movableConnections.entrySet()) {
                    PointList pts = e.getValue();
                    if (pts.intersects(newRect)) {
                        // Intersection found : bend points move needed
                        // Find the bend points to move, skip first and last points
                        for (int i=1, nb= pts.size(); i< nb-1; i++) {
                            pts.getPoint(bendPointLoc, i);
                            bendPointBounds.setLocation(bendPointLoc.x, bendPointLoc.y);
        
                            if (! oldBounds.contains(bendPointLoc)) {
                                ConnectionEditPart connEp = e.getKey();
                                //Object srcModel = connEp.getSource().getAdapter(MObject.class);
                                //Object targetModel = connEp.getTarget().getAdapter(MObject.class);
                                //DiagramElements.LOG.debug("    intersections: %s (%s -> %s) [%d] intersects with %s", connEp.getModel(), srcModel, targetModel, i, node);
                                Dimension pushDirection;
                                if (isMove) {
                                    pushDirection = computeMovePushDirection(intersectZone, bendPointBounds);
                                } else {
                                    pushDirection = computeResizePushDirection(oldBounds, bendPointBounds);
                                }
        
                                computePush(connEp, bendPointBounds, newBounds, pushDirection, neededPush);
                            }
                        }
                    }
                }
            }
        }
        
        if (! neededPush.isEmpty()) {
            // Some graphic elements need to be pushed.
            // Apply the computed push move to all
        
            // Bounding boxes of all edit part parents
            Rectangle parentsBounds = computeParentBounds();
        
            // Compute node push moves
            for (Entry<GraphicalEditPart, Rectangle> entry : this.movableNodes.entrySet()) {
                GraphicalEditPart child = entry.getKey();
                Rectangle childRect = entry.getValue();
                // Exclude nodes that already intersected moved ones.
                if (! existingIntersections.contains(child)) {
                    // Test whether this node must be pushed and in which direction
                    Move pushMove = neededPush.getFiltered(childRect);
                    if (! pushMove.isEmpty()) {
                        getMove(child).setMax(pushMove);
                    }
                }
            }
        
            // Compute bendpoints push moves
            Point bendPointLoc = new Point();
            Rectangle bendPointBounds = new Rectangle(); // zero sized rectangle to convert points to rectangle
            for (Entry<ConnectionEditPart, PointList> e : this.movableConnections.entrySet()) {
                ConnectionEditPart cp = e.getKey();
                Connection c = (Connection) cp.getFigure();
                PointList pts = e.getValue();
                // Exclude connections that:
                // - already intersected
                // - and those that are not contained in moved elements parent figures.
                if (! existingConnIntersections.contains(c) && parentsBounds.contains(pts.getBounds())) {
                    // find bendpoints to move, exclude extremities.
                    for (int i=1, nb= pts.size()-1; i< nb; i++) {
                        // Get bend point 'i' location
                        pts.getPoint(bendPointLoc, i);
        
                        if (! oldBounds.contains(bendPointLoc)) {
                            // convert Point to rectangle
                            bendPointBounds.setLocation(bendPointLoc);
        
                            // compute whether this bend point must be pushed
                            Move pushMove = neededPush.getFiltered(bendPointBounds);
        
                            if (! pushMove.isEmpty()) {
                                // apply move
                                BpMove bpMove = getBpMove(cp, i, bendPointLoc);
                                bpMove.setMax(pushMove);
                            }
                        }
                    }
                }
            }
        }
        
    }

    /**
     * Compute the direction in which childRect must be pushed to avoid intersection with newBounds.
     * @param newBounds a rectangle
     * @param childRect the rectangle that intersects with the first.
     * @return the direction in which childRect must be pushed to avoid intersection with newBounds.
     */
    @objid ("24ee86db-03a3-4bf6-a6e2-1ce573280871")
    private Dimension computeMovePushDirection(Rectangle newBounds, Rectangle childRect) {
        if (newBounds.contains(childRect)) {
            return computePushDirection(childRect.getCenter(), newBounds);
        } else if (childRect.contains(newBounds)) {
            return computePushDirection(newBounds.getCenter(), childRect).negate();
        } else {
            // Look at the intersection between moved node and the node to push.
            // Push the node according to the intersection location and size.
            Rectangle intersection = newBounds.getIntersection(childRect);
            Dimension pushDirection = computePushDirectionFromIntersection(childRect, intersection);
        
            return pushDirection;
        }
        
    }

    /**
     * Compute bounding box of all movable edit part parent edit parts.
     * @return the global bounding box
     */
    @objid ("4b24cf4e-3dd5-4be9-94a8-1ba9408cc381")
    private Rectangle computeParentBounds() {
        Rectangle parentsBounds = null;
        ArrayList<GraphicalEditPart> all = new ArrayList<> (this.movableNodes.keySet());
        all.addAll(this.movedNodes);
        
        // Bounding boxes of all edit part parents
        for (GraphicalEditPart ep : all) {
            IFigure parentFigure = ((GraphicalEditPart) ep.getParent()).getFigure();
            Rectangle parentBounds = parentFigure.getBounds().getCopy();
            parentFigure.translateToAbsolute(parentBounds);
            if (parentsBounds == null) {
                parentsBounds = parentBounds;
            } else {
                parentsBounds.union(parentBounds);
            }
        }
        
        if (parentsBounds == null) {
            parentsBounds = new Rectangle();
        }
        return parentsBounds;
    }

    /**
     * Compute the move needed to keep the 'tpPush' rectangle at least at {@link #minDist} from 'ref' rectangle.
     * @param cause the reference edit part that causes the move
     * @param toPush the rectangle to move to keep minimal distance
     * @param ref the reference new rectangle (the one that was moved/resized)
     * @param oldVector old vector from ref before move/resize to rect to push
     */
    @objid ("38b40e18-f33a-4399-9546-c77535444f5f")
    private void computePush(GraphicalEditPart cause, Rectangle toPush, Rectangle ref, Dimension oldVector, Move pushMove) {
        if (oldVector.width() > 0) {
            // toPush was on right, we have :
            // ref.right() + this.minDist <= toPush.x() + ret.width
            double dx = this.minDist + ref.right() - toPush.x();
            if (dx >0) {
                pushMove.setMaxRight(dx, toPush.x(), cause);
            }
        } else if (oldVector.width() < 0) {
            // toPush was on left, we have :
            // toPush.right() + this.minDist + ret.width = ref.x()
            double dx = ref.x() - this.minDist - toPush.right();
            if (dx < 0) {
                pushMove.setMaxLeft(-dx, toPush.right(), cause);
            }
        }
        
        if (oldVector.height() > 0) {
            // toPush was on bottom, we have:
            // ref.bottom() + this.minDist = toPush.y() + ret.h()
            double dy = this.minDist - toPush.y() + ref.bottom();
            if (dy > 0) {
                pushMove.setMaxBottom(dy, toPush.y(), cause);
            }
        } else if (oldVector.height() < 0) {
            // toPush was on top, we have:
            // toPush.bottom() + this.minDist + ret.h() = ref.y()
            double dy = ref.y() - this.minDist - toPush.bottom();
            if (dy < 0) {
                pushMove.setMaxTop(-dy, toPush.bottom(), cause);
            }
        }
        
    }

    /**
     * Compute the orthogonal direction in which the point must be moved to escape the rectangle.
     * @param toMove the point to move
     * @param rect the rectangle to escape
     * @return the shortest orthogonal direction
     */
    @objid ("0c7e4614-90ab-417f-b2d2-4102a964d4b3")
    private Dimension computePushDirection(Point toMove, Rectangle rect) {
        Dimension pushDirection = new Dimension();
        switch (GeomUtils.getDirection(toMove, rect)) {
        case EAST:
            pushDirection.setSize(1, 0);
            break;
        case NONE:
            break;
        case NORTH:
            pushDirection.setSize(0, -1);
            break;
        case SOUTH:
            pushDirection.setSize(0, 1);
            break;
        case WEST:
            pushDirection.setSize(-1, 0);
        
            break;
        }
        return pushDirection;
    }

    /**
     * Compute the direction in which childRect must be pushed to avoid the given intersection rectangle.
     * @param childRect a rectangle
     * @param intersection the intersection with the first rectangle
     * @return the direction in which childRect must be pushed to avoid the intersection.
     */
    @objid ("be120aca-9f36-412c-b39b-8c970797ed55")
    private Dimension computePushDirectionFromIntersection(Rectangle childRect, Rectangle intersection) {
        // Look at the intersection between moved node and the node to push.
        // Push the node according to the intersection location and size.
        Dimension pushDirection = new Dimension();
        
        if (intersection.x == childRect.x) {
            // intersection on child left border : push child toward right
            pushDirection.width = 1;
        }
        if (intersection.right() == childRect.right()) {
            // right border : toward left
            // This will set 'width' to zero if both left and right borders are in the intersection.
            pushDirection.width -= 1;
        }
        
        if (intersection.y == childRect.y) {
            // top border : toward bottom
            pushDirection.height = 1;
        }
        if (intersection.bottom() == childRect.bottom()) {
            // bottom border : toward top
            // This will set 'height' to zero if both top and bottom borders are in the intersection.
            pushDirection.height -= 1;
        }
        
        // Choose only one direction from the intersection size
        if (pushDirection.height != 0 && pushDirection.width != 0) {
            // Choose the smallest intersection length as direction
            if (intersection.height >= intersection.width) {
                // Intersection taller : push only horizontally
                pushDirection.height = 0;
            } else {
                // Intersection wider : push only vertically
                pushDirection.width = 0;
            }
        }
        return pushDirection;
    }

    @objid ("2d4dbb89-aa37-48a1-a17a-da3728252abb")
    private Dimension computeResizePushDirection(Rectangle oldBounds, Rectangle childRect) {
        // it's a resize : the direction is from the old bounds to the child
        return getDistanceVector(oldBounds, childRect);
    }

    @objid ("8c66497e-2e46-43f1-8624-14b19e31e531")
    private BendpointRequest createMoveBpRequest(BpRef ref, BpMove move) {
        double dx = move.dx();
        double dy = move.dy();
        
        if (dx !=0 || dy != 0) {
            BendpointRequest req = new BendpointRequest();
            req.setType(RequestConstants.REQ_MOVE_BENDPOINT);
            req.setLocation(move.origPoint.getCopy());
            req.getLocation().translate(move.dx(), move.dy());
            req.setSource(ref.conn);
            req.setIndex(ref.index - 1);
        
            return req;
        }
        return null;
    }

    @objid ("ada5deae-2e39-4667-86f4-c7d61cb5e6af")
    private ChangeBoundsRequest createMoveRequest(GraphicalEditPart key, double dx, double dy) {
        ChangeBoundsRequest r = new ChangeBoundsRequest(RequestConstants.REQ_MOVE_CHILDREN);
        r.setMoveDelta(new PrecisionPoint(dx, dy));
        r.setEditParts(key);
        return r;
    }

    @objid ("b6a7b703-730e-4eab-bccf-9a0355babf3d")
    private BpMove getBpMove(ConnectionEditPart c, int bendPointIndex, Point bpOrigLocation) {
        BpRef ref = new BpRef(c, bendPointIndex);
        BpMove move = this.pushBendPointMoves.get(ref);
        if (move == null) {
            move = new BpMove();
            move.origPoint = new PrecisionPoint(bpOrigLocation);
            this.pushBendPointMoves.put(ref, move);
        }
        return move;
    }

    /**
     * The the orthogonal distance between 2 rectangle nearest borders/corners.
     * <p>
     * If width is positive, 'to' is on right of 'from'. If negative 'to' is on left'.
     * If null intersection is possible.
     * <p>
     * Same thing for height.
     * <p>
     * If both width and height are 0, the rectangles intersect.
     * @param from a rectangle
     * @param to another rectangle
     * @return the orthogonal distance vector between the 2 rectangles borders.
     */
    @objid ("e9e02c4f-9c29-4ed0-ae11-ce5db62fbb46")
    private static Dimension getDistanceVector(Rectangle from, Rectangle to) {
        int dx ;
        int dy;
        
        if (from.right() < to.x()) {
            // from is left from to
            dx = to.x() - from.right();
        } else if (to.right() < from.x()) {
            // from is right to 'to'
            dx = to.right() - from.x() ;
        } else {
            // intersection
            dx = 0;
        }
        
        if (from.bottom() < to.y()) {
            // from is above to
            dy = to.y() - from.bottom();
        } else if (to.bottom() < from.y()) {
            // from is below to : dy negative
            dy = to.bottom() - from.y() ;
        } else {
            dy = 0;
        }
        return new Dimension(dx, dy);
    }

    @objid ("f6b165e4-d2ec-4ce1-b1bc-f4995569d162")
    private Move getMove(GraphicalEditPart ep) {
        Move move = this.pushNodeMoves.get(ep);
        if (move == null) {
            move = new Move();
            this.pushNodeMoves.put(ep, move);
        }
        return move;
    }

    @objid ("a0593c9b-746f-4910-8f34-0ef3a6411797")
    private void resetAnswer() {
        // Empty
    }

    /**
     * Return a copy of this Rectangle expanded on horizontal and vertical sides with at least the given value
     * while keeping the width/height ratio.
     * @param toExpand the rectangle to expand
     * @param minValue the minimum expand
     * @return the expanded rectangle.
     */
    @objid ("3289cb8c-f644-4cf4-9316-f38b42a4c3c8")
    private static Rectangle getExpandedSameRatio(Rectangle toExpand, double minValue) {
        Rectangle r = toExpand.getCopy();
        double scale;
        double dw;
        double dh;
        if (r.width() >= r.height()) {
            // h * scale = h + minValue
            scale = (minValue ) / (r.preciseHeight());
            dh = minValue;
            dw = r.preciseWidth() * scale ;
        } else {
            // w * scale = w + minValue
            scale = (minValue ) / (r.preciseWidth());
            dw = minValue;
            dh = r.preciseHeight() * scale ;
        }
        r.expand(dw, dh);
        return r;
    }

    /**
     * @return If true collision will be avoided with connection bend points too.
     */
    @objid ("5b7cca19-f265-40c9-a383-2b030a404902")
    public boolean avoidBendPoints() {
        return this.avoidBendPoints;
    }

    /**
     * If true collision will be avoided with connection bend points too.
     * @param avoidBendPoints true to avoid collision with bend points.
     */
    @objid ("fe6d2b03-50f5-4835-beea-ada01d4f748e")
    public void setAvoidBendPoints(boolean avoidBendPoints) {
        this.avoidBendPoints = avoidBendPoints;
    }

    /**
     * @return true to keep same distance between all nodes
     */
    @objid ("bc7f3d88-bb5b-4957-ba84-9b5d9c5b5afe")
    public boolean keepSameDistance() {
        return this.keepSameDistance;
    }

    /**
     * If true all nodes will keep same distance.
     * If false a minimal distance will be kept.
     * @param keepSameDistance true to keep same distance between all nodes
     */
    @objid ("bcd7434c-005e-453f-9559-ca1369be680e")
    public void setKeepSameDistance(boolean keepSameDistance) {
        this.keepSameDistance = keepSameDistance;
    }

    @objid ("f95535a8-3b44-4fc9-9a90-ffb72b36589c")
    private static class BoundChange {
        @objid ("f724d4d1-ffde-4d90-9fa0-38f2106d5b55")
        GraphicalEditPart node;

        @objid ("b15aedb1-fdcd-4e4f-bfb0-1fe8059d4711")
        PrecisionRectangle oldBounds;

        @objid ("443061c7-1bcc-451f-9087-ff0b340b3d15")
        PrecisionRectangle newBounds;

        @objid ("d43da022-efd1-4409-8a13-1a3ec86add29")
        public  BoundChange(GraphicalEditPart node, PrecisionRectangle oldBounds, PrecisionRectangle newBounds) {
            super();
            this.node = node;
            this.oldBounds = oldBounds;
            this.newBounds = newBounds;
            
        }

    }

    /**
     * Bend point move.
     */
    @objid ("4d3f245b-0ab8-4e07-a8fb-ca1f2d18a0f3")
    private static class BpMove extends Move {
        /**
         * Original bend point location in absolute coordinates.
         */
        @objid ("d8f2fe06-897e-46ee-afcb-7f01da78643e")
        PrecisionPoint origPoint;

        @objid ("0e57a6ef-5a90-4ea4-b326-f938283dbcc1")
        public  BpMove() {
            super();
        }

    }

    /**
     * Connection bend point reference.
     */
    @objid ("6837e5ed-fe42-426a-8852-381f6b2db434")
    private static class BpRef {
        @objid ("686089ec-9779-410c-8d92-d3425fd90200")
        int index;

        @objid ("3d39c56b-0605-4390-90db-b80349a8405b")
        ConnectionEditPart conn;

        @objid ("2af45e82-cebf-4159-80c0-9f47843c096b")
        public  BpRef(ConnectionEditPart conn, int index) {
            super();
            this.conn = conn;
            this.index = index;
            
        }

        @objid ("22c422f1-bb1a-4533-b95f-0e3e367a2d36")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.conn == null) ? 0 : this.conn.hashCode());
            result = prime * result + this.index;
            return result;
        }

        @objid ("9b75b9c0-1458-4f06-b62d-756a7dcc2e10")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            BpRef other = (BpRef) obj;
            if (this.conn == null) {
                if (other.conn != null) {
                    return false;
                }
            } else if (!this.conn.equals(other.conn)) {
                return false;
            }
            if (this.index != other.index) {
                return false;
            }
            return true;
        }

    }

    /**
     * Compound command that can be executed even if empty.
     */
    @objid ("afced67c-286d-43ee-8553-b82f504c8983")
    protected class ExecuteCommand extends CompoundCommand {
        @objid ("d83c6ffa-b885-4670-ba23-c71128a2734f")
        @Override
        public void execute() {
            if (! getCommands().isEmpty()) {
                DiagramElements.LOG.debug(" Running %s", this);
                super.execute();
            }
            
        }

        @objid ("fe6ffd35-f073-4e84-932d-e41e646e8675")
        @Override
        public boolean canExecute() {
            if (getCommands().isEmpty()) {
                return true;
            } else {
                return super.canExecute();
            }
            
        }

        @objid ("617c6630-1726-4d99-ae96-8408eefc818c")
        @Override
        public String toString() {
            if (getCommands().isEmpty()) {
                return "Empty compound command";
            } else {
                return getDebugLabel();
            }
            
        }

        @objid ("c69156ab-0e26-438c-a46d-6488b10a250d")
        @Override
        public String getDebugLabel() {
            if (getCommands().isEmpty()) {
                return "disabled intersections removal.";
            } else {
                return DefaultLayoutAssistant.this.toString();
            }
            
        }

    }

    /**
     * Records distance for the 4 orthogonal directions.
     */
    @objid ("ef2c8a18-2f28-4bc3-b352-ec193a21ccba")
    private static class Move {
        @objid ("d1428fda-b161-4a59-b9c1-287750de5db4")
        double leftMove;

        @objid ("8ed7cc1b-f413-43a0-87dd-15cd74206a1d")
        double rightMove;

        @objid ("f8d6883f-63bb-4c46-be48-2ae7617a3309")
        double topMove;

        @objid ("60e8c3a8-ed3a-4c31-839a-d0d4b6eeed44")
        double bottomMove;

        @objid ("3b133e08-4e99-46f0-be84-3b15f11d7746")
        double leftFrom;

        @objid ("1a94489f-7c83-45cc-adc9-74fb079e32a1")
        double rightFrom;

        @objid ("6fb916ac-c2d0-4c2c-ab0e-e5668999aaa6")
        double topFrom;

        @objid ("57efa0e9-8c2b-43d4-b09c-74676d06ab4d")
        double bottomFrom;

        @objid ("14a2429f-7370-49dd-a3e9-dc06f1c32f05")
        GraphicalEditPart leftCause;

        @objid ("26bad93b-5a35-4998-9860-8d7c4ebe6aaa")
        GraphicalEditPart rightCause;

        @objid ("a601412e-9162-476b-9734-93e9755e2b13")
        GraphicalEditPart topCause;

        @objid ("f50571f1-8851-439a-98e0-25b67983d449")
        GraphicalEditPart bottomCause;

        @objid ("3a535426-d53b-412e-bbf3-6e38df98590f")
        public  Move() {
            super();
        }

        @objid ("e0138470-136a-403c-be9e-69af44d227ac")
        public void setMax(Move refMove) {
            if (refMove.leftMove != 0 ) {
                setMaxLeft(refMove.leftMove, refMove.leftFrom, refMove.leftCause);
            }
            if (refMove.rightMove != 0) {
                setMaxRight(refMove.rightMove, refMove.rightFrom, refMove.rightCause);
            }
            if (refMove.topMove != 0 ) {
                setMaxTop(refMove.topMove, refMove.topFrom, refMove.topCause);
            }
            if (refMove.bottomMove != 0 ) {
                setMaxBottom(refMove.bottomMove, refMove.bottomFrom, refMove.bottomCause);
            }
            
        }

        @objid ("a1d70d66-8ce7-4699-ac97-1b66d43d0d27")
        public  Move(Move other) {
            this.leftMove = other.leftMove;
            this.rightMove = other.rightMove;
            this.topMove = other.topMove;
            this.bottomMove = other.bottomMove;
            this.leftFrom = other.leftFrom;
            this.rightFrom = other.rightFrom;
            this.topFrom = other.topFrom;
            this.bottomFrom = other.bottomFrom;
            this.leftCause = other.leftCause;
            this.rightCause = other.rightCause;
            this.topCause = other.topCause;
            this.bottomCause = other.bottomCause;
            
        }

        @objid ("4601ea1d-9e64-463b-9171-8370171796da")
        public boolean isEmpty() {
            return this.leftMove==0 && this.rightMove==0 && this.topMove==0 && this.bottomMove==0;
        }

        @objid ("2e4b6cb9-6aef-435a-96f5-374591af6f15")
        public void setMaxLeft(double delta, double leftFrom, GraphicalEditPart leftCause) {
            double max = getMax(this.leftMove, delta);
            if (max == delta) {
                this.leftMove = max;
                this.leftFrom = leftFrom;
                this.leftCause = leftCause;
            }
            
        }

        @objid ("5b5e961b-164e-4c40-80bf-58bd93ca2307")
        public void setMaxRight(double delta, double rightFrom, GraphicalEditPart rightCause) {
            double max = getMax(this.rightMove, delta);
            if (max == delta) {
                this.rightMove = max;
                this.rightFrom = rightFrom;
                this.rightCause = rightCause;
            }
            
        }

        @objid ("72e71df3-2a99-42e5-abe5-02f0ca279d47")
        public void setMaxTop(double delta, double topFrom, GraphicalEditPart topCause) {
            double max = getMax(this.topMove, delta);
            if (max == delta) {
                this.topMove = max;
                this.topFrom = topFrom;
                this.topCause = topCause;
            }
            
        }

        @objid ("4e48ad3d-c737-4cf3-9bba-3304a981f264")
        public void setMaxBottom(double delta, double bottomFrom, GraphicalEditPart bottomCause) {
            double max = getMax(this.bottomMove, delta);
            if (max == delta) {
                this.bottomMove = max;
                this.bottomFrom = bottomFrom;
                this.bottomCause = bottomCause;
            }
            
        }

        @objid ("7562531f-7970-4c41-98a2-332e10ecacd9")
        public double dx() {
            return getMax(-this.leftMove, this.rightMove);
        }

        @objid ("42dfa821-1c03-4843-9893-0bd170564195")
        public double dy() {
            return getMax(-this.topMove, this.bottomMove);
        }

        /**
         * Get the same move with fields that does not match the given vector directions reset.
         * @param oldDist old vector from ref to push.
         * @return a filtered copy
         */
        @objid ("c1b74d8a-fcc2-4e52-9240-d7f651f36747")
        public Move getFiltered(Dimension oldDist) {
            Move ret = new Move(this);
            ret.filter(oldDist);
            return ret;
        }

        /**
         * Reset fields that do not match the given vector directions.
         * @param oldDist old vector from ref to push.
         */
        @objid ("e450ae8f-8684-4a76-a6ee-d5a46d08858b")
        public void filter(Dimension oldDist) {
            if ( oldDist.width() < 0) {
                this.rightMove = 0;
            } else {
                this.leftMove = 0;
            }
            
            if (oldDist.height() < 0) {
                this.bottomMove = 0;
            } else  {
                this.topMove = 0;
            }
            
        }

        @objid ("b698bbb1-c598-4c7c-85e6-212408f652ab")
        private static double getMax(double a, double b) {
            if (Math.abs(a) < Math.abs(b)) {
                return b;
            } else {
                return a;
            }
            
        }

        /**
         * Get the same move with fields that does not match the target rectangle position reset.
         * @param toPush the rectangle to apply this move.
         * @return a filtered copy
         */
        @objid ("60fe84a9-a276-48bd-9432-ae6f32650c7c")
        public Move getFiltered(Rectangle toPush) {
            Move ret = new Move(this);
            ret.filter(toPush);
            return ret;
        }

        /**
         * Reset fields that don't apply to the given rectangle to push.
         * @param target the rectangle to push.
         */
        @objid ("634b79bc-f6a9-4342-b950-52dde0bc4077")
        public void filter(Rectangle target) {
            if ( target.right() > this.leftFrom) {
                this.leftMove = 0;
            }
            
            if (target.x() < this.rightFrom) {
                this.rightMove = 0;
            }
            
            if ( target.bottom() > this.topFrom) {
                this.topMove = 0;
            }
            
            if (target.y() < this.bottomFrom) {
                this.bottomMove = 0;
            }
            
        }

        @objid ("da2e11af-9008-4319-8b24-1682cd0888df")
        @Override
        public String toString() {
            if (this.leftMove==0 && this.rightMove==0 && this.topMove==0 && this.bottomMove == 0) {
                return "Empty move";
            } else {
                StringBuilder s = new StringBuilder();
                if (this.leftMove!=0) {
                    s.append(this.leftMove);
                    s.append("px toward left by ");
                    s.append(causeToString(this.leftCause));
                }
                if (this.rightMove!=0) {
                    if (s.length() > 0) {
                        s.append(", ");
                    }
                    s.append(this.rightMove);
                    s.append("px toward right by ");
                    s.append(causeToString(this.rightCause));
                }
                if (this.topMove!=0) {
                    if (s.length() > 0) {
                        s.append(", ");
                    }
                    s.append(this.topMove);
                    s.append("px toward top by ");
                    s.append(causeToString(this.topCause));
                }
                if (this.bottomMove != 0) {
                    if (s.length() > 0) {
                        s.append(", ");
                    }
                    s.append(this.bottomMove);
                    s.append("px toward bottom by ");
                    s.append(causeToString(this.bottomCause));
                }
            
                return s.toString();
            }
            
        }

        @objid ("c457abda-1fb3-46dc-9d99-336f67ae63cc")
        private static final String causeToString(GraphicalEditPart cause) {
            if (cause == null) {
                return "<none>";
            } else {
                return String.valueOf(cause.getModel());
            }
            
        }

    }

}
