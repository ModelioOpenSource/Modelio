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

@objid ("bd2f49cc-f7a0-482c-95f9-5b68c4967de0")
class AutoOrthoState {
    @objid ("6ad9f034-9c0c-46a2-a42a-602934fd97a6")
    Direction sourceAnchorDir = Direction.NONE;

    @objid ("6fa631a9-0485-4018-beb7-eb85267a91f6")
    Direction targetAnchorDir = Direction.NONE;

    @objid ("bf1579a5-7997-4899-8650-d038b6db0eac")
    ConnectionAnchor sourceAnchor;

    @objid ("0a59b7ca-ef46-40b2-97a9-fa0df6e4a2b3")
    ConnectionAnchor targetAnchor;

    @objid ("9b4224c6-deaa-4afc-a9da-2ba72487d5d1")
    Connection connection;

    @objid ("11a7b396-20d5-40a5-8110-67eb8d703d28")
    final AnchorBounds anchorBounds = new AnchorBounds();

    @objid ("b71ec802-34eb-4bc5-a3a4-d7ba3c53d97a")
    List<MPoint> allPoints;

    @objid ("0835b5e4-811f-4e34-8c5d-efdbba752b11")
    private static final org.modelio.diagram.elements.core.figures.geometry.Direction.Pair curDirs = new Direction.Pair();

    @objid ("6512f337-f634-431a-8dd3-2f9a20a685ca")
    private static final org.modelio.diagram.elements.core.figures.geometry.Direction.Pair nextDirs = new Direction.Pair();

    /**
     * Temporary point used to avoid Point allocations.
     */
    @objid ("28729f71-55e2-40ad-a948-8b3f47a2c093")
    private static final MPrecisionPoint A_POINT = new MPrecisionPoint();

    @objid ("8b05d574-f2ac-46f4-905c-878c6cb939bf")
    public AutoOrthoState init(Connection aconnection) {
        return init (aconnection ,
                (List<MPoint>) aconnection.getRoutingConstraint(),
                aconnection.getSourceAnchor(),
                aconnection.getTargetAnchor());
        
    }

    @objid ("621a45af-ee7c-4ff6-8256-4e442a518455")
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

    @objid ("18329e5e-1e00-4c89-8091-11ade0c7e7a5")
    public AutoOrthoState refreshAnchorBounds() {
        if (this.allPoints== null)
            return refreshAnchorBoundsSimple();
        else
            return refreshAnchorBoundsComplex();
        
    }

    @objid ("74d7b4ab-ac9d-486d-b5b2-fac691fe58a8")
    public AutoOrthoState refreshAnchorBoundsSimple() {
        this.anchorBounds.fromAnchors(this.sourceAnchor, this.targetAnchor)
                .expand(1)
                .toRelative(this.connection);
        return this;
    }

    @objid ("8c3e764d-7f33-49b0-a2af-736f66b88dc6")
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

    @objid ("43c40fc4-262e-4674-937c-cd12534a00d9")
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
        PrecisionPoint srcRefPoint = new PrecisionPoint();
        PrecisionPoint targetRefPoint = new PrecisionPoint();
        
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

    @objid ("b782432e-8b44-43a7-9d4a-77f88cb73af8")
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
    @objid ("bdb05a7a-b1eb-4f50-86c5-d36608410369")
    public Direction guessBestDirectionFromPreviousSegments(int index) {
        return guessBestDirectionFromPreviousSegments(this.sourceAnchorDir, index);
    }

    /**
     * Guess a best "target direction" to pass to router algorithm from the previous segments, to minimize bendings at "wrong" place.
     * @param sourceAnchorDirection a previous segment direction to use if index is < 2.
     * @param index the point index. Must be >=1 .
     * @return the best direction
     */
    @objid ("51193d7a-f6c4-4b3d-a807-4b18fd422afa")
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
    @objid ("b909d487-334c-4790-8431-6ea914ae57b3")
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
    @objid ("b4d5cf81-36fd-4056-b5b0-49db4068ce4f")
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
