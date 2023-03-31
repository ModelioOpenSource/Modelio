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
package org.modelio.diagram.elements.core.link.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPartViewer;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.NodeAnchor;
import org.modelio.diagram.elements.core.figures.anchors.RaySlidableAnchor;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.anchors.GmRaySlidableAnchor;
import org.modelio.diagram.elements.core.link.ortho.ChangeLinkRoutingConstraintCommand;
import org.modelio.diagram.elements.core.link.ortho.edit.AxisAccessor;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionEditor;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionView;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * With Modelio 5.0.2, the orthogonal router changed, making it necessary to adapt the layout data before using the new router.
 * <p>
 * This class compares the point list gotten from {@link OrthogonalRouter5_0} and {@link AutoOrthogonalRouter}, and updates the {@link GmPath} of the {@link GmLink} if needed to keep the same looks for old orthogonal links.
 * </p>
 */
@objid ("477f3df8-c7db-4950-aa85-8c689fd7272e")
public final class OrthogonalPathFixer5_0_2 extends AbstractPathFixer {
    @objid ("0417bf72-7738-4af8-8b65-413c377cf2de")
    private final IGmPath origGmPath;

    /**
     * C'tor
     * @param gmLink the link being fixed.
     */
    @objid ("c18db4f5-e57c-4fe1-951e-15d9ef63c42f")
    public  OrthogonalPathFixer5_0_2(GmLink gmLink) {
        super(gmLink);
        // the port container autoresize policies triggers NodeLayoutConnectionEditPolicies
        // that completely layout the link, so we need to backup the GmPath now and
        // restore it before migration.
        this.origGmPath = new GmPath(this.gmLink.getPath());
        
    }

    @objid ("1f10eb65-2e61-4291-a011-592138260ae6")
    @Override
    public void run(EditPartViewer viewer) {
        if (!this.gmLink.isValid()) {
            return;
        }
        
        ConnectionEditPart linkEditPart = (ConnectionEditPart) viewer.getEditPartRegistry().get(this.gmLink);
        if (linkEditPart == null) {
            return;
        }
        
        if (!(linkEditPart.getFigure() instanceof Connection)) {
            DiagramElements.LOG.warning("Broken link: %s in %s", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
            DiagramElements.LOG.warning("  a link figure should be a Connection.");
        }
        
        // Loading issue workaround
        if (linkEditPart.getSource() == null || linkEditPart.getTarget() == null) {
            // Force a resolution of the source/target of the link
            this.gmLink.getFrom();
            this.gmLink.getTo();
        }
        
        if (linkEditPart.getSource() == null || linkEditPart.getTarget() == null) {
            DiagramElements.LOG.warning("Broken link: %s in %s", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
            DiagramElements.LOG.warning("  source: %s for %s", linkEditPart.getSource(), this.gmLink.getFrom());
            DiagramElements.LOG.warning("  target: %s for %s", linkEditPart.getTarget(), this.gmLink.getTo());
            return;
        }
        
        Connection connection = (Connection) linkEditPart.getFigure();
        
        if (!(connection.getConnectionRouter() instanceof BendpointConnectionRouter)) {
            return;
        }
        
        // Restore the saved model path
        // this.gmLink.getLayoutData() is an already modified version of 'this.origGmPath'
        IGmPath curLayoutData = (IGmPath) this.gmLink.getLayoutData();
        this.gmLink.setLayoutData(this.origGmPath);
        
        restoreOriginalAnchors(curLayoutData, connection);
        
        // Triggers figures validation so the connection points match the constraint & anchors
        connection.getUpdateManager().performValidation();
        
        // Try to compute migrated GmPath
        IGmPath newGmPath = computeMigratedPathModel(linkEditPart, connection);
        
        if (applyAndValidateGmPath(newGmPath, connection, false)) {
            tryImprovelink(linkEditPart);
            return;
        }
        
        // Unable to migrate connection, rerouting it from scratch
        DiagramElements.LOG.warning("Unable to migrate %s in %s, rerouting it from scratch.", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
        newGmPath = reRouteConnection(linkEditPart, connection);
        
        if (applyAndValidateGmPath(newGmPath, connection, true)) {
            return;
        }
        
        // no fix possible: restore previous layout data
        DiagramElements.LOG.warning("Unable to reroute %s in %s, restoring intermediate layout data: %s.", this.gmLink, this.gmLink.getDiagram().getRelatedElement(), curLayoutData);
        this.gmLink.setLayoutData(curLayoutData);
        
    }

    /**
     * Try to improve connection by anchoring them at fixed anchors.
     * @param editPart the connection edit part to improve
     */
    @objid ("dbf7497d-8c23-46b5-acfa-08ff3ca49bd1")
    private void tryImprovelink(ConnectionEditPart editPart) {
        new ConnectionPathImprover(editPart).tryImprovelink();
    }

    /**
     * With new anchor providers some {@link RaySlidableAnchor} are changed to {@link NodeAnchor} with the same offset.
     * <p>
     * But the two implementations with same offset don't give the same result at all: RaySlidableAnchors depend on the passed reference point, NodeAnchor always use the offset.
     * <p>
     * Restore RaySlidableAnchors on the Connection so that computation look like same in 5.0.
     * @param curLayoutData the current path,
     */
    @objid ("cdac6608-8057-4045-9db4-7e88263772c6")
    private void restoreOriginalAnchors(IGmPath curLayoutData, Connection connection) {
        Object origSourceAnchor = this.origGmPath.getSourceAnchor();
        if (origSourceAnchor != null && origSourceAnchor.getClass() == GmRaySlidableAnchor.class
                && connection.getSourceAnchor().getClass() == NodeAnchor.class) {
            GmRaySlidableAnchor gmOrigAnchor = (GmRaySlidableAnchor) origSourceAnchor;
            connection.setSourceAnchor(new RaySlidableAnchor(
                    connection.getSourceAnchor().getOwner(),
                    gmOrigAnchor.getDifference()));
        }
        
        Object origTargetAnchor = this.origGmPath.getTargetAnchor();
        if (origTargetAnchor != null && origTargetAnchor.getClass() == GmRaySlidableAnchor.class
                && connection.getTargetAnchor().getClass() == NodeAnchor.class) {
            GmRaySlidableAnchor gmOrigAnchor = (GmRaySlidableAnchor) origTargetAnchor;
            connection.setTargetAnchor(new RaySlidableAnchor(
                    connection.getTargetAnchor().getOwner(),
                    gmOrigAnchor.getDifference()));
        }
        
    }

    /**
     * Apply the GmPath and check it makes an orthogonal connection.
     * @param newGmPath the path model to apply
     * @param connection the connection figure to validate
     * @param dumpOnFail if true, dump infos in the log on failure
     * @return true if the connection is orthogonal
     */
    @objid ("5777ab70-439d-4666-9560-974cfe49914a")
    private boolean applyAndValidateGmPath(IGmPath newGmPath, Connection connection, boolean dumpOnFail) {
        if (newGmPath == null) {
            return false;
        }
        
        // Apply GmPath to model & edit parts
        this.gmLink.setLayoutData(newGmPath);
        
        // Fast check
        final boolean extremesOrthogonal = areExtremesOrthogonal(connection);
        
        if (dumpOnFail && !extremesOrthogonal) {
            // this will dump to log debug stuff
            assertLinkOrthogonal(connection);
        }
        
        if (extremesOrthogonal) {
            // debug mode check
            assert assertLinkOrthogonal(connection);
        }
        return extremesOrthogonal;
    }

    /**
     * check first and last segments are orthogonal.
     * @param connection a connection
     * @return true only when first and last segments are orthogonal.
     */
    @objid ("415e2717-d1e7-4f97-b6fb-a5449623908d")
    private static boolean areExtremesOrthogonal(Connection connection) {
        // Triggers figures validation so the connection points match the constraint & anchors
        connection.getUpdateManager().performValidation();
        
        Point p1 = connection.getPoints().getFirstPoint();
        Point p2 = connection.getPoints().getLastPoint();
        Point p3 = connection.getPoints().getPoint(1);
        Point p4 = connection.getPoints().getPoint(connection.getPoints().size() - 2);
        if (p1.x() != p3.x() && p1.y() != p3.y()) {
            return false;
        } else if (p2.x() != p4.x() && p2.y() != p4.y()) {
            return false;
        }
        return true;
    }

    @objid ("f2c445ac-321f-4566-a501-1cf924008956")
    private static boolean assertLinkOrthogonal(Connection connection) {
        // Triggers figures validation so the connection points match the constraint & anchors
        connection.getUpdateManager().performValidation();
        
        Point p1 = connection.getPoints().getFirstPoint();
        Point p2 = connection.getPoints().getLastPoint();
        Point p3 = connection.getPoints().getPoint(1);
        Point p4 = connection.getPoints().getPoint(connection.getPoints().size() - 2);
        if (p1.x() != p3.x() && p1.y() != p3.y()) {
            // fix failed: restore previous layout data
            DiagramElements.LOG.error("First segment not orthogonal: %s", ConnectionView.Validator.validate(new ConnectionView().init(connection)));
        } else if (p2.x() != p4.x() && p2.y() != p4.y()) {
            // fix failed: restore previous layout data
            DiagramElements.LOG.error("Last segment not orthogonal: %s", ConnectionView.Validator.validate(new ConnectionView().init(connection)));
        }
        return true;
    }

    /**
     * Compares the point list gotten from {@link AutoOrthogonalRouter} and {@link OrthogonalRouter5_0}.
     * @param linkEditPart the connection edit part
     * @param connection the connection being routed.
     * @return a list of points when a layout fix is needed, <code>null</code> otherwise.
     */
    @objid ("b37548ad-1587-4593-b6be-9f642cb40bd3")
    private IGmPath computeMigratedPathModel(ConnectionEditPart linkEditPart, Connection connection) {
        GmPath newPath = new GmPath(this.origGmPath);
        
        @SuppressWarnings ("unchecked")
        List<MPoint> origPathData = (List<MPoint>) this.origGmPath.getPathData();
        
        AutoOrthogonalRouter newRouter = new AutoOrthogonalRouter()
                .setCleanupManualPoints(false)
                .setSimplifyEnds(false);
        List<MPoint> newPointList = newRouter.computeMPointRoute(connection, origPathData);
        PointList oldPointList = new OrthogonalRouter5_0().computePointList(connection, origPathData);
        
        if (oldPointList.size() < 2 || newPointList.size() < 2) {
            DiagramElements.LOG.warning("Invalid number of points on %s in %s, rerouting the connection completely.", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
            return null;
        }
        
        // Keep point list unchanged when anchors are not properly initialized (aka both equals to (0, 0))
        if (newPointList.get(0).equals(0, 0) && newPointList.get(newPointList.size() - 1).equals(0, 0)) {
            DiagramElements.LOG.warning("Both anchors are zero on %s in %s, rerouting the connection completely.", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
            return null;
        }
        
        // Remove first and last points, handled by anchors
        Point oldFirstPoint = oldPointList.getPoint(new PrecisionPoint(), 0);
        oldPointList.removePoint(oldPointList.size() - 1);
        newPointList.remove(newPointList.size() - 1);
        oldPointList.removePoint(0);
        newPointList.remove(0);
        
        List<Point> newPathData = null;
        
        // Check at least one bendpoint is different
        
        if (oldPointList.size() == 0 || newPointList.isEmpty()) {
            // The connection was or can be one segment.
            // No points here
            newPathData = new ArrayList<>(0);
        } else if (isContentDifferent(newPointList, oldPointList)) {
            // Set old router points as bend points
            newPathData = new ArrayList<>(oldPointList.size());
            for (int i = 0; i < oldPointList.size(); i++) {
                // Assume new points are automatic
                newPathData.add(new MPoint(oldPointList.getPoint(i), false));
            }
        } else {
            // Set new router points as bend points
            newPathData = new ArrayList<>(newPointList.size());
            for (int i = 0; i < newPointList.size(); i++) {
                newPathData.add(newPointList.get(i).getCopy().setFixed(false));
            }
        }
        
        newPath.setPathData(newPathData);
        ConnectionEditor editor = new ConnectionEditor().init(linkEditPart);
        editor.getView().getState().setConstraint(newPathData);
        if (!alignAnchors(editor, connection, newPath, oldFirstPoint)) {
            DiagramElements.LOG.warning("Failed aligning anchors of %s in %s, rerouting the connection completely.", this.gmLink, this.gmLink.getDiagram().getRelatedElement());
            return null;
        }
        
        // No fix needed or possible
        return newPath;
    }

    @objid ("54e51d67-86ee-4bee-b1dd-ceb57b21cb53")
    private boolean alignAnchors(ConnectionEditor editor, Connection connection, IGmPath newPath, Point oldFirstPoint) {
        List<MPoint> mPoints = editor.getView().getState().getMPoints();
        if (mPoints.isEmpty()) {
            // No points here, make sure the anchors are aligned
            return alignDirectConnAnchors(editor, connection, newPath, oldFirstPoint);
        } else {
            // align anchors with first and last bend points
            if (!alignAnchor(editor, connection, newPath, mPoints.get(0), true)) {
                return false;
            }
            if (!alignAnchor(editor, connection, newPath, mPoints.get(mPoints.size() - 1), false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * At last resort reroute completely the connection so that it is at least orthogonal.
     * @param linkEditPart the connection edit part
     * @param connection the connection
     * @return the computed GmPath, null if failed
     */
    @objid ("1be6a7a0-8356-4609-93e7-dd82a0928dc9")
    private IGmPath reRouteConnection(ConnectionEditPart linkEditPart, Connection connection) {
        // restore old GmPath
        this.gmLink.setLayoutData(this.origGmPath);
        
        // Allocate new path model
        IGmPath newPath = new GmPath(this.origGmPath);
        
        @SuppressWarnings ("unchecked")
        List<MPoint> origPathData = (List<MPoint>) this.origGmPath.getPathData();
        
        // create ortho router with all permissions
        AutoOrthogonalRouter newRouter = new AutoOrthogonalRouter()
                .setCleanupManualPoints(true)
                .setRerouteWrongSectionFromPreviousManualPoint(true)
                .setSimplifyEnds(false);
        
        List<MPoint> newPointList = newRouter.computeMPointRoute(connection, origPathData);
        if (newPointList.size() < 2) {
            DiagramElements.LOG.warning("unable to reroute the connection %s in %s: Invalid number of points %d", this.gmLink, this.gmLink.getDiagram().getRelatedElement(), newPointList.size());
            return null;
        }
        
        MPoint oldFirstPoint = newPointList.get(0);
        newPointList.remove(newPointList.size() - 1);
        newPointList.remove(0);
        
        // Set new router points as bend points
        newPath.setPathData(newPointList);
        
        ConnectionEditor editor = new ConnectionEditor().init(linkEditPart);
        editor.getView().getState().setConstraint(newPointList);
        
        alignAnchors(editor, connection, newPath, oldFirstPoint);
        return newPath;
    }

    @objid ("eb17b27a-0099-41ad-a1a3-cbe6148763ca")
    private Orientation guessOrientation(Connection connection) {
        ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
        ConnectionAnchor targetAnchor = connection.getTargetAnchor();
        
        Point sourceAnchorRef = sourceAnchor.getReferencePoint();
        Point targetAnchorRef = targetAnchor.getReferencePoint();
        
        Point sourceLocation = sourceAnchor.getLocation(sourceAnchorRef);
        Point targetLocation = targetAnchor.getLocation(targetAnchorRef);
        
        Point targetLocation2 = targetAnchor.getLocation(sourceAnchorRef);
        Point sourceLocation2 = sourceAnchor.getLocation(targetAnchorRef);
        
        Orientation ret = guessOrientation(sourceLocation, targetLocation);
        if (ret == Orientation.NONE) {
            ret = guessOrientation(sourceLocation, targetLocation2);
        }
        if (ret == Orientation.NONE) {
            ret = guessOrientation(sourceLocation2, targetLocation);
        }
        if (ret == Orientation.NONE) {
            ret = guessOrientation(sourceLocation2, targetLocation2);
        }
        
        if (ret != Orientation.NONE) {
            return ret;
        }
        
        Rectangle srcNodeBounds = sourceAnchor.getOwner().getBounds().getCopy();
        sourceAnchor.getOwner().translateToAbsolute(srcNodeBounds);
        return GeomUtils.getDirection(targetLocation, srcNodeBounds).orientation();
    }

    @objid ("dafbd2f1-6455-44fd-b8cb-23795c2bc37d")
    private static Orientation guessOrientation(Point p1, Point p2) {
        final int TOLERANCE = 3;
        
        final int dx = p2.x() - p1.x();
        final int dy = p2.y() - p1.y();
        if (dx < dy && Math.abs(dx) < TOLERANCE) {
            return Orientation.VERTICAL;
        } else if (dy < dx && Math.abs(dy) < TOLERANCE) {
            return Orientation.HORIZONTAL;
        } else {
            return Orientation.NONE;
        }
        
    }

    /**
     * Align anchors of a no bendpoint connection if they are not already.
     * @param path the link's path to edit
     * @param alignRef the point to align to in coordinates relative to the connection
     */
    @objid ("ad1164c3-2ce4-4284-b0c8-652378061bae")
    private boolean alignDirectConnAnchors(ConnectionEditor editor, Connection connection, IGmPath path, Point alignRef) {
        ConnectionState backup = new ConnectionState().init(editor.getView().getState());
        Point absAlignRef = alignRef.getCopy();
        connection.translateToAbsolute(absAlignRef);
        
        Point p1 = editor.getView().getSourceLocation(new MPoint(), true);
        Point p2 = editor.getView().getTargetLocation(new MPoint(), true);
        Orientation orientation = guessOrientation(connection);
        
        if (alignDirectConnAnchors2(editor, orientation, path, absAlignRef)) {
            return true;
        }
        
        editor.getView().getState().init(backup);
        if (alignDirectConnAnchors2(editor, orientation, path, p1)) {
            return true;
        }
        
        editor.getView().getState().init(backup);
        if (alignDirectConnAnchors2(editor, orientation, path, p2)) {
            return true;
        }
        
        // No way to align anchors with previous points
        // Try to translate by 1 pixel.
        AxisAccessor acc = AxisAccessor.forOrientation(orientation).across;
        int coord = acc.getCoord(absAlignRef);
        
        editor.getView().getState().init(backup);
        Point newRef = new Point(absAlignRef);
        acc.setCoord(newRef, coord + 1);
        if (alignDirectConnAnchors2(editor, orientation, path, newRef)) {
            return true;
        }
        
        editor.getView().getState().init(backup);
        acc.setCoord(newRef, coord - 1);
        if (alignDirectConnAnchors2(editor, orientation, path, newRef)) {
            return true;
        }
        return false;
    }

    /**
     * Align anchors of a no bendpoint connection if they are not already.
     * @param path the link's path to edit
     * @param absAlignRef the point to align to in absolute coordinates
     */
    @objid ("afd00c57-6217-4ee7-b8e3-407b6badbeca")
    private boolean alignDirectConnAnchors2(ConnectionEditor editor, Orientation orientation, IGmPath path, Point absAlignRef) {
        MPrecisionPoint p1 = editor.getView().getSourceLocation(new MPrecisionPoint(), true);
        MPrecisionPoint p2 = editor.getView().getTargetLocation(new MPrecisionPoint(), true);
        switch (orientation) {
        case HORIZONTAL:
            p1.setPreciseY(absAlignRef.preciseY());
            p2.setPreciseY(absAlignRef.preciseY());
            break;
        case VERTICAL:
            p1.setPreciseX(absAlignRef.preciseX());
            p2.setPreciseX(absAlignRef.preciseX());
            break;
        default:
            return false;
        }
        
        editor.getView().getState().setSourceAnchor(editor.requestSourceAnchor()
                .withLocation(p1)
                .withSliding(true)
                .requestAnchor());
        
        editor.getView().getState().setTargetAnchor(editor.requestTargetAnchor()
                .withLocation(p2)
                .withSliding(true)
                .requestAnchor());
        
        editor.fixWithRouter(true);
        
        if (editor.getView().cardPoints() == 2) {
            applySourceAnchorToGmPath(editor, path);
            applyTargetAnchorToGmPath(editor, path);
        
            return true;
        } else {
            return false;
        }
        
    }

    @objid ("e2754db3-56f0-40b0-9420-be5091f7ef7a")
    private static void applySourceAnchorToGmPath(ConnectionEditor editor, IGmPath gmpath) {
        IAnchorModelProvider sourceEp = (IAnchorModelProvider) editor.getConnectionEditPart().getSource();
        ConnectionState connectionState = editor.getView().getState();
        Object sourceAnchorModel = sourceEp.createAnchorModel(connectionState.getSourceAnchor());
        gmpath.setSourceAnchor(sourceAnchorModel);
        
    }

    @objid ("aae85ea6-ef50-4605-8436-c226423d7c31")
    private static void applyTargetAnchorToGmPath(ConnectionEditor editor, IGmPath gmpath) {
        IAnchorModelProvider nodeEp = (IAnchorModelProvider) editor.getConnectionEditPart().getTarget();
        ConnectionState connectionState = editor.getView().getState();
        Object anchorModel = nodeEp.createAnchorModel(connectionState.getTargetAnchor());
        gmpath.setTargetAnchor(anchorModel);
        
    }

    /**
     * Build a new anchor that aligns to 'alignRef', and modifies the given path model.
     * @param editor an initialized ConnectionEditor to use for computations, free to modify.
     * @param connection the edited Connection
     * @param path the path model to edit
     * @param alignRef a connection point, in coordinates relative to the connection
     * @param source true for source anchor, false for target anchor
     * @return true if anchor is aligned else false.
     */
    @objid ("c3dc6bb1-796a-4b7d-bce6-bdb7b354a50f")
    private boolean alignAnchor(ConnectionEditor editor, Connection connection, IGmPath path, Point alignRef, boolean source) {
        // Compute alignment point absolute
        Point absAlign = new PrecisionPoint(alignRef);
        connection.translateToAbsolute(absAlign);
        
        // Compute previous anchor location
        ConnectionAnchor anchor;
        Point absAnchorLoc;
        Point relAnchorLoc;
        
        if (source) {
            anchor = connection.getSourceAnchor();
            absAnchorLoc = anchor.getLocation(absAlign);
        } else {
            anchor = connection.getTargetAnchor();
            absAnchorLoc = anchor.getLocation(absAlign);
        }
        
        relAnchorLoc = absAnchorLoc.getCopy();
        connection.translateToRelative(relAnchorLoc);
        
        // Compute ref point for new anchor request : align absAlign to the anchor location
        AxisAccessor acc = AxisAccessor.forOrientation(Direction.getMajor(absAnchorLoc, absAlign).orientation()).along;
        Point reqLocation = absAlign.getCopy();
        acc.setCoord(reqLocation, absAnchorLoc);
        
        // Request a new anchor
        ConnectionAnchor newAnchor;
        if (source) {
            newAnchor = editor.requestSourceAnchor().withLocation(reqLocation).withSliding(true).requestAnchor();
        } else {
            newAnchor = editor.requestTargetAnchor().withLocation(reqLocation).withSliding(true).requestAnchor();
        }
        
        // compute new anchor location, absolute and relative
        Point newAbsAnchorLoc = newAnchor.getLocation(absAlign);
        Point newRelAnchorLoc = newAbsAnchorLoc.getCopy();
        connection.translateToRelative(newRelAnchorLoc);
        
        // the new anchor is OK if it aligns either on absolute or relative coordinates
        // Note: it is much better when it aligns in relative coordinates.
        boolean ok = newRelAnchorLoc.x() == alignRef.x() || newRelAnchorLoc.y() == alignRef.y();
        
        if (ok || newAbsAnchorLoc.x() == absAlign.x() || newAbsAnchorLoc.y() == absAlign.y()) {
            // Aligns: apply changes
            if (source) {
                editor.getView().getState().setSourceAnchor(newAnchor);
                applySourceAnchorToGmPath(editor, path);
            } else {
                editor.getView().getState().setTargetAnchor(newAnchor);
                applyTargetAnchorToGmPath(editor, path);
            }
            return true;
        }
        
        // Impossible to align anchors
        if (DiagramElements.LOG.isDebugEnabled()) {
            DiagramElements.LOG.debug("Failed align %s", editor.getConnectionEditPart());
            DiagramElements.LOG.debug("  %s to: %s abs, %s relative:", source ? "source" : "target", absAlign, alignRef);
            DiagramElements.LOG.debug(" - old anchor=%s -> %s%n - tried anchor=%s -> %s abs, %s rel", anchor, absAnchorLoc, relAnchorLoc);
            DiagramElements.LOG.debug(" - tried anchor=%s -> %s abs, %s rel", newAnchor, newAbsAnchorLoc, newRelAnchorLoc);
        }
        return false;
    }

    /**
     * Routes {@link Connection}s through a <code>List</code> of {@link Bendpoint Bendpoints} that make an orthogonal path.
     * <p>
     * The route constraint is modified to be made orthogonal.
     * </p>
     */
    @objid ("f30533cb-97f8-4bc4-8bb0-7b4c539a1486")
    private static class OrthogonalRouter5_0 {
        /**
         * Temporary point used to avoid Point allocations.
         */
        @objid ("1e0b29b0-1231-4ce8-b3fa-dbb51b4e90f3")
        private static final PrecisionPoint A_POINT = new PrecisionPoint();

        @objid ("4d69a289-2e6b-4222-8a16-f54c891f59bc")
        private static final AnchorBounds anchorBounds = new AnchorBounds();

        /**
         * @param allPoints point list to clean unnecessary bend points from.
         */
        @objid ("9ea5a101-619f-48e2-942e-6e4e1ebf93a9")
        private void cleanup(final List<Bendpoint> allPoints) {
            boolean pointsRemoved = false;
            // Finish by removing unnecessary points:
            // 1: overlapping points.
            List<Integer> indexesToRemove = new ArrayList<>();
            for (int i = 1; i < allPoints.size() - 2; ++i) {
                Point p1 = allPoints.get(i).getLocation();
                Point p2 = allPoints.get(i + 1).getLocation();
            
                if (p1.getDistance(p2) < 1) {
                    indexesToRemove.add(i);
                }
            }
            for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
                allPoints.remove(indexesToRemove.get(i).intValue());
                pointsRemoved = true;
            }
            
            // 2: allPoints not bending
            indexesToRemove.clear();
            for (int i = 1; i < allPoints.size() - 1; ++i) {
                Point p1 = allPoints.get(i - 1).getLocation();
                Point p2 = allPoints.get(i + 1).getLocation();
                if (p1.x == p2.x || p1.y == p2.y) {
                    indexesToRemove.add(i);
                }
            }
            for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
                allPoints.remove(indexesToRemove.get(i).intValue());
                pointsRemoved = true;
            }
            
            if (pointsRemoved) {
                // Some points were removed, try cleaning the new point list again
                cleanup(allPoints);
            }
            
        }

        @objid ("16b5197c-2ae4-4cf0-8da9-438f2f75725c")
        private void fixSeveralBendpointsLink(final List<Bendpoint> bendpoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
            // If there are at least 2 intermediary bend points, fix them to get orthogonal segments
            Point fixedPoint = bendpoints.get(1).getLocation();
            Point nextPoint = bendpoints.get(2).getLocation();
            Orientation nextSegmentOrientation = Orientation.NONE;
            if (fixedPoint.x == nextPoint.x) {
                nextSegmentOrientation = Orientation.VERTICAL;
            } else if (fixedPoint.y == nextPoint.y) {
                nextSegmentOrientation = Orientation.HORIZONTAL;
            } else {
                assert false : String.format("Impossible to determine orientation of start segment [%s ; %s], invalid bendpoints list:%n %s!",
                        fixedPoint, nextPoint, bendpoints);
            }
            if (sourceAnchorOrientation == Direction.NONE) {
                if (nextSegmentOrientation == Orientation.VERTICAL) {
                    // next segment is vertical, so first was horizontal
                    fixedPoint.y = sourceLocation.y;
                } else if (nextSegmentOrientation == Orientation.HORIZONTAL) {
                    // next segment is horizontal so first is vertical
                    fixedPoint.x = sourceLocation.x;
                }
            } else if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                // First segment is vertical: align the X coordinates
                // check that we don't need an additional bend point (next segment must be horizontal) first
                if (nextSegmentOrientation != Orientation.HORIZONTAL) {
                    // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                    bendpoints.add(1, null);
                    fixedPoint = new Point(fixedPoint);
                }
                fixedPoint.x = sourceLocation.x;
            } else {
                // First segment is horizontal: align the Y coordinates
                // check that we don't need an additional bend point (next segment must be vertical) first
                if (nextSegmentOrientation != Orientation.VERTICAL) {
                    // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                    bendpoints.add(1, null);
                    fixedPoint = new Point(fixedPoint);
                }
                fixedPoint.y = sourceLocation.y;
            }
            AbsoluteBendpoint fixedBendpoint = new MPoint(fixedPoint, false);
            bendpoints.set(1, fixedBendpoint);
            
            int lastBendpointIndex = bendpoints.size() - 2;
            fixedPoint = bendpoints.get(lastBendpointIndex).getLocation();
            nextPoint = bendpoints.get(lastBendpointIndex - 1).getLocation();
            Orientation previousSegmentOrientation = Orientation.NONE;
            if (fixedPoint.x == nextPoint.x) {
                previousSegmentOrientation = Orientation.VERTICAL;
            } else if (fixedPoint.y == nextPoint.y) {
                previousSegmentOrientation = Orientation.HORIZONTAL;
            } else {
                assert false : "impossible to determine orientation of last segment, something is wrong with the provided list of bendpoints!";
            }
            if (targetAnchorOrientation == Direction.NONE) {
                // Target anchor is not oriented, deduct orientation from previous segment if possible.
                if (previousSegmentOrientation == Orientation.VERTICAL) {
                    // previous segment is vertical, so first was horizontal
                    fixedPoint.y = targetLocation.y;
                } else if (previousSegmentOrientation == Orientation.HORIZONTAL) {
                    // previous segment is horizontal so first is vertical
                    fixedPoint.x = targetLocation.x;
                }
            } else if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                // Last segment is vertical: align the X coordinates
                // Check that we don't need an additional bend point (previous segment must be horizontal) first
                if (previousSegmentOrientation != Orientation.HORIZONTAL) {
                    // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                    ++lastBendpointIndex;
                    bendpoints.add(lastBendpointIndex, null);
                    fixedPoint = new Point(fixedPoint);
                }
                fixedPoint.x = targetLocation.x;
            } else {
                // Last segment is horizontal: align the Y coordinates
                // Check that we don't need an additional bend point (previous segment must be vertical) first
                if (previousSegmentOrientation != Orientation.VERTICAL) {
                    // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                    ++lastBendpointIndex;
                    bendpoints.add(lastBendpointIndex, null);
                    fixedPoint = new Point(fixedPoint);
                }
                fixedPoint.y = targetLocation.y;
            }
            fixedBendpoint = new MPoint(fixedPoint, false);
            bendpoints.set(lastBendpointIndex, fixedBendpoint);
            
        }

        @objid ("439c9f44-f939-4958-b923-dee91ccf64e8")
        private void fixNoBendpointsLink(final List<Bendpoint> allPoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
            // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
            final PrecisionPoint point = OrthogonalRouter5_0.A_POINT;
            
            if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                    if (sourceLocation.x != targetLocation.x) {
                        // No luck: not aligned, we need 2 additional bend points.
                        point.setLocation(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                        allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                        point.setLocation(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                        allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                    }
                    // else: good luck: both anchors are aligned, nothing to do!
                } else {
                    // We need an additional bend point.
                    point.setLocation(sourceLocation.x, targetLocation.y);
                    allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                }
            } else {
                if (targetAnchorOrientation == Direction.NONE) {
                    // Not oriented target anchor: we might need an additional bend point.
                    if (sourceLocation.y != targetLocation.y) {
                        // No luck, anchors are not aligned, we need a bend point.
                        point.setLocation(targetLocation.x, sourceLocation.y);
                        allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                    }
                    // else: good luck, both anchors are aligned, nothing to do!
                } else if (targetAnchorOrientation == Direction.SOUTH ||
                        targetAnchorOrientation == Direction.NORTH) {
                    // We need an additional bend point
                    point.setLocation(targetLocation.x, sourceLocation.y);
                    allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                } else {
                    if (sourceLocation.y != targetLocation.y) {
                        // No luck: not aligned, we need 2 additional bend points.
                        point.setLocation((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y);
                        allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                        point.setLocation((sourceLocation.x + targetLocation.x) / 2, targetLocation.y);
                        allPoints.add(allPoints.size() - 1, new MPoint(point, false));
                    }
                    // else: good luck: both anchors are aligned, nothing to do!
                }
            }
            
        }

        @objid ("9a6919d7-1514-44ba-8482-251a66ce8746")
        private List<Bendpoint> computeInitialBendpointsList(final Connection connection, final ConnectionAnchor sourceAnchor, final ConnectionAnchor targetAnchor, List<MPoint> pathData) {
            List<MPoint> origBendpoints = pathData;
            if (origBendpoints == null) {
                origBendpoints = Collections.emptyList();
            }
            final List<Bendpoint> allPoints = new ArrayList<>();
            final PrecisionPoint tmp = OrthogonalRouter5_0.A_POINT;
            
            // Let's assume the first point is the source anchor reference point (This may be modified later).
            tmp.setLocation(sourceAnchor.getReferencePoint());
            connection.translateToRelative(tmp);
            allPoints.add(new MPoint(tmp, false));
            // Now assume the given allPoints are good (we'll fix them later if needed)
            for (Bendpoint bendpoint : origBendpoints) {
                allPoints.add(new MPoint(bendpoint.getLocation(), false));
            }
            // End with the target anchor reference point
            tmp.setLocation(targetAnchor.getReferencePoint());
            connection.translateToRelative(tmp);
            allPoints.add(new MPoint(tmp, false));
            
            if (!origBendpoints.isEmpty()) {
                anchorBounds.fromConnectionAbs(connection)
                        .expand(1)
                        .toRelative(connection)
                        .trimContainedBendPoints(allPoints.subList(1, allPoints.size() - 1), false);
            }
            
            // Now compute the actual location of the source anchor, based on the next bendpoint (might be the target anchor reference point).
            tmp.setLocation(allPoints.get(1).getLocation());
            connection.translateToAbsolute(tmp);
            tmp.setLocation(sourceAnchor.getLocation(tmp));
            connection.translateToRelative(tmp);
            // Use that value in the list, instead of the reference point.
            allPoints.set(0, new MPoint(tmp, false));
            
            // Now compute the actual location of the target anchor, based on the previous bendpoint (might be the source anchor location point).
            int index = allPoints.size() - 1;
            tmp.setLocation(allPoints.get(index - 1).getLocation());
            connection.translateToAbsolute(tmp);
            tmp.setLocation(targetAnchor.getLocation(tmp));
            connection.translateToRelative(tmp);
            // Use that value in the list, instead of the reference point.
            allPoints.set(index, new MPoint(tmp, false));
            return allPoints;
        }

        @objid ("08ce1b45-70fe-4ac3-939d-fad7a35bfb9b")
        private void fixLastBendpointLink(final List<Bendpoint> allPoints, final Point targetLocation, final Direction targetAnchorOrientation) {
            Point previousLocation = allPoints.get(allPoints.size() - 3).getLocation();
            Point lastLocation = allPoints.get(allPoints.size() - 2).getLocation();
            
            Direction direction;
            if (previousLocation.x == lastLocation.x) {
                // HORIZONTAL
                direction = lastLocation.x - targetLocation.x >= 0 ? Direction.NORTH : Direction.SOUTH;
            } else if (previousLocation.y == lastLocation.y) {
                // VERTICAL
                direction = lastLocation.y - targetLocation.y >= 0 ? Direction.WEST : Direction.EAST;
            } else {
                // impossible to determine a direction, something is temporarily inconsistent with the provided list of allPoints, will be dealt with later
                direction = Direction.NONE;
            }
            fixNoBendpointsLink(allPoints, lastLocation, targetLocation, direction, targetAnchorOrientation);
            
        }

        /**
         * Compute a list of points to use when routing the connection.
         * @param connection an orthogonal connection.
         * @param pathData initial bendpoints.
         * @return a List of Points
         */
        @objid ("f5fcf303-1e3a-4d6d-80c5-f2cb5aa1e9da")
        public PointList computePointList(Connection connection, List<MPoint> pathData) {
            final ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
            final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
            
            final List<Bendpoint> allPoints = computeInitialBendpointsList(connection, sourceAnchor, targetAnchor, pathData);
            
            // Source and target locations are now fixed, we are not allowed to move them anymore.
            Point sourceLocation = allPoints.get(0).getLocation();
            Point targetLocation = allPoints.get(allPoints.size() - 1).getLocation();
            
            // Now the tricky part: fix the first and last bend points to form an orthogonal path.
            anchorBounds.fromConnectionAbs(connection).expand(1).toRelative(connection);
            
            Direction sourceAnchorOrientation = GeomUtils.getDirection(sourceLocation, anchorBounds.source);
            Direction targetAnchorOrientation = GeomUtils.getDirection(targetLocation, anchorBounds.target);
            
            if (allPoints.size() == 2) {
                fixNoBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
            } else if (allPoints.size() > 2) {
                fixLastBendpointLink(allPoints, targetLocation, targetAnchorOrientation);
                fixSeveralBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
            }
            
            // Cleanup of useless points.
            cleanup(allPoints);
            
            // Build a new point list
            final PointList points = new PointList(allPoints.size());
            for (int i = 0; i < allPoints.size(); i++) {
                Bendpoint bp = allPoints.get(i);
                points.addPoint(bp.getLocation());
            }
            return points;
        }

    }

    /**
     * Service that tries to improve auto orthogonal connections by moving anchors to nearest FixedAnchor but not more than {@link #setMaxDiffPercent(double)}% of the node face length.
     * 
     * @author cma
     * @since 5.1.0
     */
    @objid ("099001c1-c764-421f-b7c9-3b926869bc87")
    static class ConnectionPathImprover {
        @objid ("39cb99b3-2934-492d-99d2-ca77e91f1c61")
        private final int oldPointcount;

        @objid ("d7b09292-5cca-4af7-8a5b-611ed24a16df")
        private double maxDiffPercent = 0.17;

        @objid ("6f95a39e-5841-4501-bf22-162447edd228")
        private final ConnectionEditor editor;

        @objid ("a0fb8c48-9736-4564-85e0-c7ac059ff13c")
        private final ConnectionView origView;

        @objid ("f29a652b-4d3b-4e9f-bdad-e90bfb5a0221")
        private final MPoint origSrcAbsLoc;

        @objid ("933e018e-e6dc-49e0-8088-e2c00bed8bb3")
        private final MPoint origTargetAbsLoc;

        @objid ("aa0355cd-9b26-40d1-bdf6-f46811198619")
        private final List<MPoint> origPointList;

        @objid ("dcbff8a9-97f3-465d-95c3-d8ba4d95547d")
        public  ConnectionPathImprover(ConnectionEditPart editPart) {
            this.editor = new ConnectionEditor().init(editPart);
            ConnectionView view = this.editor.getView();
            this.origView = new ConnectionView().init(view.getConnection());
            
            this.oldPointcount = view.cardPoints();
            this.origPointList = this.origView.toMPointList(true);
            this.origSrcAbsLoc = this.origPointList.get(0);
            this.origTargetAbsLoc = this.origPointList.get(this.origPointList.size() - 1);
            
        }

        @objid ("4b8f58e1-cb59-4b42-b118-b3f4f7a1a2d5")
        public ConnectionPathImprover setMaxDiffPercent(double maxDiffPercent) {
            this.maxDiffPercent = maxDiffPercent;
            return this;
        }

        /**
         * Try to improve connection by anchoring them at fixed anchors.
         */
        @objid ("7667640f-709d-40f4-b23e-217f2b6e9aee")
        public void tryImprovelink() {
            ConnectionView view = this.editor.getView();
            ConnectionAnchor oldSourceAnchor = view.getState().getSourceAnchor();
            ConnectionAnchor oldTargetAnchor = view.getState().getTargetAnchor();
            
            ConnectionAnchor newSourceAnchor = this.editor.requestSourceAnchor()
                    .withSliding(false)
                    .withLocation(this.origSrcAbsLoc)
                    .requestAnchor();
            
            ConnectionAnchor newTargetAnchor = this.editor.requestTargetAnchor()
                    .withSliding(false)
                    .withLocation(this.origTargetAbsLoc)
                    .requestAnchor();
            
            // If same anchors returned fast exit
            if (Objects.equals(oldSourceAnchor, newSourceAnchor) && Objects.equals(oldTargetAnchor, newTargetAnchor)) {
                return;
            }
            
            // Check at least one anchor is fixed
            if (!(newSourceAnchor instanceof FixedAnchor || newTargetAnchor instanceof FixedAnchor)) {
                return;
            }
            
            // change both anchors at once
            view.getState().setSourceAnchor(newSourceAnchor);
            view.getState().setTargetAnchor(newTargetAnchor);
            tryImprovedLink();
            
        }

        @objid ("abf951aa-5e03-45a8-86c0-f186f279af00")
        private boolean tryImprovedLink() {
            ConnectionView view = this.editor.getView();
            if (!view.isValidPath()) {
                this.editor.fixWithRouter(true);
            }
            
            MPoint newSrcAbsLoc = view.getSourceLocation(new MPoint(), true);
            MPoint newTargetAbsLoc = view.getTargetLocation(new MPoint(), true);
            int newPointcount = view.cardPoints();
            
            if (view.isValidPath()
                    && newPointcount <= this.oldPointcount) {
                double srcDiff = getBorderFractionDiff(this.origSrcAbsLoc, newSrcAbsLoc, view.getAnchorBounds().source);
                double targetDiff = getBorderFractionDiff(this.origTargetAbsLoc, newTargetAbsLoc, view.getAnchorBounds().target);
                if (srcDiff < this.maxDiffPercent && targetDiff < this.maxDiffPercent) {
                    // New path is better than old, apply it
                    DiagramElements.LOG.debug("Optimizing %s connection path :", this.editor.getConnectionEditPart());
                    DiagramElements.LOG.debug("     from : %s", this.origPointList);
                    DiagramElements.LOG.debug("     to : %s", view.toMPointList(true));
                    new ChangeLinkRoutingConstraintCommand(this.editor.getConnectionEditPart(), view.getState()).execute();
                    return true;
                } else if (DiagramElements.LOG.isDebugEnabled()) {
                    DiagramElements.LOG.debug("Won't optimize %s connection path :", this.editor.getConnectionEditPart());
                    DiagramElements.LOG.debug("     from : %s", this.origPointList);
                    DiagramElements.LOG.debug("     to : %s", view.toMPointList(true));
                    DiagramElements.LOG.debug("     because  src diff=%f%% , target diff=%f%% :", srcDiff * 100.0, targetDiff * 100);
                }
            } else if (DiagramElements.LOG.isDebugEnabled()) {
                DiagramElements.LOG.debug("Failed optimize %s connection path :", this.editor.getConnectionEditPart());
                DiagramElements.LOG.debug("     from: %s", this.origPointList);
                DiagramElements.LOG.debug("     to%s: %s ", view.isValidPath() ? "" : " INVALID ", view.toMPointList(true));
            }
            return false;
        }

        @objid ("e906fb7c-d871-4fb7-bbd7-252a6d9ee611")
        private static double getBorderFractionDiff(Point p1, Point p2, Rectangle r) {
            switch (GeomUtils.getDirection(p2, r)) {
            case EAST:
            case WEST:
                return Math.abs(p2.preciseY() - p1.preciseY()) / r.preciseHeight();
            case NORTH:
            case SOUTH:
                return Math.abs(p2.preciseX() - p1.preciseX()) / r.preciseWidth();
            case NONE:
            default:
                return 0;
            }
            
        }

    }

}
