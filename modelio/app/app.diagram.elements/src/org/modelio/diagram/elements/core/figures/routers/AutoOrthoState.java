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
package org.modelio.diagram.elements.core.figures.routers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.modelio.diagram.elements.core.figures.anchors.IOrientedAnchor;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;

@objid ("6f8e0b62-ea89-454e-890d-62abeffe0ae2")
class AutoOrthoState {
    @objid ("aa577217-7fe3-44fa-a974-a20e4c890c11")
    Direction sourceAnchorDir = Direction.NONE;

    @objid ("3451bb0d-0114-4c26-bfd8-63b98680a49d")
    Direction targetAnchorDir = Direction.NONE;

    @objid ("272ec7c2-3732-4a8b-8502-03ddc97c7e69")
    ConnectionAnchor sourceAnchor;

    @objid ("f2cd96dc-5b87-4ecd-bd9c-ed08c80fd8f6")
    ConnectionAnchor targetAnchor;

    @objid ("0b5138aa-d5dc-47a2-bc07-b5a998a62c8a")
    Connection connection;

    @objid ("fe797235-85e7-4355-91a9-2d00f3d90f76")
    final AnchorBounds anchorBounds = new AnchorBounds();

    @objid ("8e0c7302-0640-4c15-85e0-aedeab4abbe4")
    List<MPoint> allPoints;

    @objid ("da62829c-fb6f-43be-98e7-ea42149b88fb")
    private static final org.modelio.diagram.elements.core.figures.geometry.Direction.Pair curDirs = new Direction.Pair();

    @objid ("33ab09fb-5ea8-411f-a033-541f24906f82")
    private static final org.modelio.diagram.elements.core.figures.geometry.Direction.Pair nextDirs = new Direction.Pair();

    /**
     * Temporary point used to avoid Point allocations.
     */
    @objid ("bd9bf616-9c39-4859-a6b5-db68ac740fbf")
    private static final MPrecisionPoint A_POINT = new MPrecisionPoint();

    @objid ("23c0b3c6-9037-4068-920c-d09663bac2d8")
    public AutoOrthoState init(Connection aconnection) {
        return init (aconnection ,
                (List<MPoint>) aconnection.getRoutingConstraint(),
                aconnection.getSourceAnchor(),
                aconnection.getTargetAnchor());
        
    }

    @objid ("136ed585-1169-4707-a8a6-db2dbf8f5353")
    public AutoOrthoState init(Connection aconnection, List<MPoint> initialConstraint, final ConnectionAnchor asourceAnchor, final ConnectionAnchor atargetAnchor) {
        this.connection = aconnection;
        this.sourceAnchor = asourceAnchor;
        this.targetAnchor = atargetAnchor;
        this.allPoints = null;
        
        refreshAnchorBounds();
        computeInitialRoute(initialConstraint);
        refreshAnchorDirections();
        return this;
    }

    @objid ("952dad94-b7cc-4fdb-9340-90340f1a6379")
    public AutoOrthoState refreshAnchorBounds() {
        if (this.allPoints== null)
            return refreshAnchorBoundsSimple();
        else
            return refreshAnchorBoundsComplex();
        
    }

    @objid ("3f6b9b1b-01e6-4850-9129-a334dff0fe59")
    public AutoOrthoState refreshAnchorBoundsSimple() {
        this.anchorBounds.fromAnchors(this.sourceAnchor, this.targetAnchor)
                .expand(1)
                .toRelative(this.connection);
        return this;
    }

    @objid ("7540f710-8669-43cc-a29c-4f9a4d05559c")
    public AutoOrthoState refreshAnchorBoundsComplex() {
        int lastIndex = this.allPoints.size() - 1;
        Point srcRefPoint;
        Point targetRefPoint;
        
        if (lastIndex == 1) {
            // No bend point : use opposite anchor reference point
            srcRefPoint = this.targetAnchor.getReferencePoint();
            targetRefPoint = this.sourceAnchor.getReferencePoint();
        } else {
            srcRefPoint = new Point().setLocation(this.allPoints.get(1).getLocation());
            targetRefPoint = new Point().setLocation(this.allPoints.get(lastIndex - 1).getLocation());
            this.connection.translateToAbsolute(targetRefPoint);
            this.connection.translateToAbsolute(srcRefPoint);
        }
        
        this.anchorBounds.fromAnchors(this.sourceAnchor, srcRefPoint, this.targetAnchor, targetRefPoint)
                .expand(1)
                .toRelative(this.connection);
        return this;
    }

    @objid ("bc72377c-fbf5-466a-bec4-385eb3608d5d")
    public List<MPoint> computeInitialRoute(List<MPoint> origBendpoints) {
        if (origBendpoints == null) {
            origBendpoints = Collections.emptyList();
        }
        
        this.allPoints = new ArrayList<>(origBendpoints.size() + 2);
        
        // Let's assume the first point is the source anchor reference point (This may be modified later).
        A_POINT.setLocation(this.sourceAnchor.getLocation(this.sourceAnchor.getReferencePoint()));
        this.connection.translateToRelative(A_POINT);
        this.allPoints.add(new MPoint(A_POINT, true));
        
        // Now assume the given allPoints are good (we'll fix them later if needed)
        for (MPoint origBendpoint : origBendpoints) {
            this.allPoints.add(new MPoint(origBendpoint.getLocation(), origBendpoint.isFixed()));
        }
        
        // End with the target anchor reference point
        A_POINT.setLocation(this.targetAnchor.getLocation(this.targetAnchor.getReferencePoint()));
        this.connection.translateToRelative(A_POINT);
        this.allPoints.add(new MPoint(A_POINT, true));
        
        this.anchorBounds.trimContainedPoints(this.allPoints.subList(1, this.allPoints.size() - 1), false);
        
        // Compute source and target anchor reference points the same way as BendPointConnectionRouter.
        int lastIndex = this.allPoints.size() - 1;
        Point srcRefPoint = new Point();
        Point targetRefPoint = new Point();
        
        if (lastIndex == 1) {
            // No bend point : use opposite anchor reference point
            srcRefPoint.setLocation(this.targetAnchor.getReferencePoint());
            targetRefPoint.setLocation(this.sourceAnchor.getReferencePoint());
        } else {
            srcRefPoint.setLocation(this.allPoints.get(1).getLocation());
            targetRefPoint.setLocation(this.allPoints.get(lastIndex - 1).getLocation());
            this.connection.translateToAbsolute(targetRefPoint);
            this.connection.translateToAbsolute(srcRefPoint);
        }
        
        // Now compute the actual location of the source anchor.
        A_POINT.setLocation(this.sourceAnchor.getLocation(srcRefPoint));
        this.connection.translateToRelative(A_POINT);
        // Use that value in the list, instead of the reference point.
        this.allPoints.set(0, new MPoint(A_POINT, true));
        
        // Now compute the actual location of the target anchor.
        A_POINT.setLocation(this.targetAnchor.getLocation(targetRefPoint));
        this.connection.translateToRelative(A_POINT);
        // Use that value in the list, instead of the reference point.
        this.allPoints.set(lastIndex, new MPoint(A_POINT, true));
        return this.allPoints;
    }

    @objid ("e5c62770-74ab-4d84-b41c-e1233155c328")
    public void refreshAnchorDirections() {
        this.sourceAnchorDir = IOrientedAnchor.getAnchorDirection(this.sourceAnchor, this.allPoints.get(0), this.anchorBounds.source);
        this.targetAnchorDir = IOrientedAnchor.getAnchorDirection(this.targetAnchor, this.allPoints.get(this.allPoints.size() - 1), this.anchorBounds.target);
        if (this.sourceAnchorDir == Direction.NONE) {
            this.sourceAnchorDir = Direction.getMajor(this.allPoints.get(0), this.allPoints.get(1));
        }
        if (this.targetAnchorDir == Direction.NONE) {
            this.targetAnchorDir = guessBestDirectionFromPreviousSegments( this.allPoints.size() - 1);
        }
        
    }

    /**
     * Guess a best "target direction" to pass to router algorithm from the previous segments, to minimize bendings at "wrong" place.
     * @param index the point index. Must be >=1 .
     * @return the best direction
     */
    @objid ("b96c1ac1-b07b-46c4-9fef-17ce85e3baae")
    public Direction guessBestDirectionFromPreviousSegments(int index) {
        return guessBestDirectionFromPreviousSegments(this.sourceAnchorDir, index);
    }

    /**
     * Guess a best "target direction" to pass to router algorithm from the previous segments, to minimize bendings at "wrong" place.
     * @param sourceAnchorDirection a previous segment direction to use if index is < 2.
     * @param index the point index. Must be >=1 .
     * @return the best direction
     */
    @objid ("cb33506b-6035-40c4-823a-349bfa672921")
    public Direction guessBestDirectionFromPreviousSegments(Direction sourceAnchorDirection, int index) {
        Point curLocation = this.allPoints.get(index);
        Point prevLocation = this.allPoints.get(index - 1);
        Direction previousSegDir;
        if (index > 2) {
            // get direction of the previous segment
            Point prevPrevLocation = this.allPoints.get(index - 2);
            previousSegDir = Direction.getMajor(prevPrevLocation, prevLocation);
            if (previousSegDir == Direction.NONE) {
                previousSegDir = Direction.NORTH;
            }
        } else {
            // Only one segment
            previousSegDir = sourceAnchorDirection;
        }
        
        curDirs.init(curLocation, prevLocation);
        if (curDirs.isOverlap()) {
            // Both points are equal, return previous opposite direction to avoid loops
            return previousSegDir.opposite();
        } else if (curDirs.isOrthogonal()) {
            // Both points are aligned, use major direction to avoid micro-bendings
            return curDirs.major();
        } else if (previousSegDir == curDirs.major().opposite()) {
            // source and target in same direction
            if (true && index + 1 < this.allPoints.size()) {
                // Not last segment: look at the next segment
                MPoint nextLoc = this.allPoints.get(index + 1);
                nextDirs.init(nextLoc, curLocation);
                if (nextDirs.isOverlap()) {
                    // curLocation and nextLoc overlap
                    return curDirs.minor();
                } else if (nextDirs.isOrthogonal()) {
                    // next segment is orthogonal, return current perpendicular direction
                    return curDirs.perpendicularOf(nextDirs);
                } else {
                    // next segment goes globally same way
                    return nextDirs.parallelOf(curDirs);
                }
            }
            // use the minor direction
            // to bend at the "end" instead of in the middle of the segment.
            return curDirs.minor();
        } else {
            // source and target in different directions
        
            if (true && index + 1 < this.allPoints.size()) {
                // Not last segment: look at the next segment
                MPoint nextLoc = this.allPoints.get(index + 1);
                nextDirs.init(nextLoc, curLocation);
                if (nextDirs.isOverlap()) {
                    // curLocation and nextLoc overlap
                    return curDirs.minor();
                } else if (nextDirs.isOrthogonal()) {
                    // next segment is orthogonal, return current perpendicular direction
                    return curDirs.perpendicularOf(nextDirs);
                } else {
                    // next segment goes globally same way
                    // return nextDirs.parallelOf(curDirs);
                }
            }
        
            return curDirs.major();
        }
        
    }

    /**
     * Try to simplify the connection by asking the target anchor whether it can align to the before last bend point
     * @param stopIndex the smallest index where to stop simplification
     */
    @objid ("9ffd21e6-8098-4fce-8664-225baf1129db")
    public void simplifyEndBendPoints(int stopIndex) {
        int size = this.allPoints.size();
        PrecisionPoint piAbs = new PrecisionPoint();
        PrecisionPoint newTargetLoc = new PrecisionPoint();
        
        int i = size - 3;
        while (i > stopIndex) {
            MPoint piRel = this.allPoints.get(i); // the bend point to align to
            MPoint pNext = this.allPoints.get(i + 1); // the candidate for removal
            if ( pNext.isFixed()) {
                return;
            }
        
            piAbs.setLocation(piRel);
            this.connection.translateToAbsolute(piAbs);
        
            newTargetLoc.setLocation(this.targetAnchor.getLocation(piAbs));
            this.connection.translateToRelative(newTargetLoc);
        
            if (Direction.getOrtho(newTargetLoc, piRel) != this.targetAnchorDir) {
                // the anchor does not align to next point, or it changes the segment direction
                return;
            }
        
            // the target anchor accept to align to this bend point i,
            // Record new target location
            MPoint newTarget = this.allPoints.get(size - 1);
            newTarget.setLocation(newTargetLoc);
        
            // Remove the now useless next bend point : i+1
            this.allPoints.remove(i + 1);
            i--;
            size--;
        
        }
        
    }

    /**
     * Try to simplify the connection by asking the source anchor whether it can align to the second bend point
     * @param aLastIndex the last index to simplify
     */
    @objid ("381b5c7d-9296-4081-8597-1be015115e25")
    public void simplifyStartBendPoints(int aLastIndex) {
        PrecisionPoint piAbs = new PrecisionPoint();
        PrecisionPoint newSourceLoc = new PrecisionPoint();
        
        int i = 2;
        int lastIndex = aLastIndex;
        while (i < lastIndex) {
            MPoint piRel = this.allPoints.get(i); // The bendpoint to align to
            MPoint pPrev = this.allPoints.get(i - 1); // The candidate for removal
        
            if (pPrev.isFixed()) {
                return;
            }
        
            piAbs.setLocation(piRel);
            this.connection.translateToAbsolute(piAbs);
        
            newSourceLoc.setLocation(this.sourceAnchor.getLocation(piAbs));
            this.connection.translateToRelative(newSourceLoc);
        
            if (Direction.getOrtho(newSourceLoc, piRel) != this.sourceAnchorDir) {
                // the anchor does not align to next point, or it changes the segment direction
                return;
            }
        
            // the source anchor accept to align to this bend point, remove the previous
            MPoint newSource = this.allPoints.get(0);
            newSource.setLocation(newSourceLoc);
        
            this.allPoints.remove(i - 1);
            lastIndex--;
        }
        
    }

}
