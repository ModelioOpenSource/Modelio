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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.adefault;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGrid;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.FixedNodeAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.shaped.ShapedFigureAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.snaptogrid.SnapToGridAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsAbstractFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Default anchor factory.
 * <p>
 * Produces<ul>
 * <li> anchors that align on the grid
 * <li> one anchor at each middle of the figure
 * </ul>
 * @author cmarin
 */
@objid ("6991aa8f-2029-4ac0-9e45-306e7a57111c")
public class DefaultAnchorFactory implements IFigureAnchorsFactory, IFigureAnchorsAbstractFactory {
    @objid ("6e133008-17e6-43e5-91a6-b9ab7a993a6d")
    private GraphicalEditPart curEditPart;

    @objid ("64842cd7-02df-4c6a-ac80-4a5939e8ac41")
    private IFigure curFigure;

    @objid ("808ddbe4-1f11-4fa0-a51c-aaf5aeb73ed6")
    private IFixedAnchorLocator gridLocator;

    @objid ("07bf1c02-38e6-4079-9e8b-f32223dbc8cd")
    private IFixedAnchorLocator middleLocator;

    @objid ("664b7548-f2ab-4dc3-9f43-3dfedb2a9b14")
    private final Dimension anchorCount = new Dimension();

    @objid ("bb385eb1-4946-4a70-828c-b661a029c6b7")
    private final Rectangle curFigBounds = new Rectangle();

    @objid ("18785a94-d881-43c5-831c-7b0998953280")
    private final Dimension curViewerGridSpacing = new Dimension();

    @objid ("b36421c9-4800-430c-8cc5-c2b2e2b7df08")
    private Collection<ConnectionAnchor> allAnchors;

    @objid ("4043224d-3465-414a-a2af-4a191efd0758")
    private static final IFixedAnchorLocator MIDDLE_LOCATOR = new FixedNodeAnchorLocator("middle");

    @objid ("de8e4c0f-2607-4003-acef-406532e64e04")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart newEditPart, IFigure newFigure) {
        Rectangle newFigBounds = newFigure.getBounds();
        Dimension newViewerGridSpacing = getViewerGridSpacing(newEditPart);
        
        // If no change fast exit
        if (this.curEditPart == newEditPart
                && this.curFigure == newFigure
                && newFigBounds.equals(this.curFigBounds)
                && this.curViewerGridSpacing.equals(newViewerGridSpacing))
            return this;
        
        // Recompute internal state
        this.curEditPart = newEditPart;
        this.curFigure = newFigure;
        this.curFigBounds.setBounds(newFigBounds);
        this.curViewerGridSpacing.setSize(newViewerGridSpacing);
        Dimension gridSize = getGridAnchorSize(new Dimension());
        SnapToGrid snapper = new Snapper(newEditPart, gridSize) ;
        
        updateGridAnchorCount(this.anchorCount, gridSize);
        
        // Recreate figure dependent locators
        this.gridLocator = new SnapToGridAnchorLocator(new FixedNodeAnchorLocator("grid"), snapper);
        this.middleLocator = MIDDLE_LOCATOR;
        
        if (false && newFigure instanceof ShapedFigure) {
            this.gridLocator   = new ShapedFigureAnchorLocator(this.gridLocator);
            this.middleLocator = new ShapedFigureAnchorLocator(this.middleLocator);
        }
        return this;
    }

    @objid ("b84f1cba-a528-4000-8ba5-2fb384620dea")
    @Override
    public ConnectionAnchor createFromModel(GmFixedAnchor gmAnchor) {
        IFixedAnchorLocator loc = ("middle".equals(gmAnchor.getLocator())) ? this.middleLocator : this.gridLocator;
        return new FixedAnchor(
                getNodeFigure(),
                gmAnchor.getFace(),
                gmAnchor.getRank(),
                gmAnchor.getTotalOnFace(),
                loc);
        
    }

    @objid ("3a98776e-5729-46af-a684-359f66312b7f")
    protected IFigure getNodeFigure() {
        return this.curFigure;
    }

    @objid ("43065816-200a-4f3f-9232-75adb18eb8b2")
    protected GraphicalEditPart getNodeEdtPart() {
        return this.curEditPart;
    }

    @objid ("ad67a447-0968-4bb3-aa7c-1004c4dd84f1")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(ConnectionRouterId routerId, Integer faceFilter) {
        // TODO keep in cache generated anchors
        IFigure newNodeFigure = getNodeFigure();
        Dimension anchorsCount = this.anchorCount;
        
        Collection<ConnectionAnchor> anchors = new ArrayList<>(anchorsCount.width*2 + anchorsCount.height*2 + 4);
        
        createAllAnchors(faceFilter, anchorsCount, anchors);
        return anchors;
    }

    @objid ("1e3af11b-73d1-4bf2-bd55-21454f8977db")
    protected void createAllAnchors(Integer faceFilter, Dimension anchorsCount, Collection<ConnectionAnchor> anchors) {
        createFaceAnchors(anchors, FacesConstants.FACE_NORTH, faceFilter, anchorsCount.width);
        createFaceAnchors(anchors, FacesConstants.FACE_SOUTH, faceFilter, anchorsCount.width);
        createFaceAnchors(anchors, FacesConstants.FACE_EAST, faceFilter, anchorsCount.height);
        createFaceAnchors(anchors, FacesConstants.FACE_WEST, faceFilter, anchorsCount.height);
        
    }

    @objid ("7744fefb-2860-4b2e-a28a-7005da5250c5")
    protected void createFaceAnchors(Collection<ConnectionAnchor> anchors, int faceId, Integer faceFilter, int anchorsCount) {
        if (faceFilter != null && faceFilter != faceId)
            return;
        
        IFigure newNodeFigure = getNodeFigure();
        
        if (true) {
        
            // Create middle anchor
            FixedAnchor northMiddle = new FixedAnchor(newNodeFigure, faceId, 0, 1, this.middleLocator);
            anchors.add(northMiddle);
        
            // Compute middle anchor location in figure coordinates
            //Point anchorRef = new Point();
            Point northRef = new Point(northMiddle.getReferencePoint());
            newNodeFigure.translateToRelative(northRef);
        
            // Create grid related anchors
            for (int i = 0; i < anchorsCount; i++) {
                FixedAnchor a = new FixedAnchor(newNodeFigure, faceId, i, anchorsCount, this.gridLocator);
        
                // add the anchor only if it is far enough from the middle anchor
                /*
                anchorRef.setLocation(a.getReferencePoint());
                newNodeFigure.translateToRelative(anchorRef);
                if (anchorRef.getDistance(northRef) >= 15)*/
                    anchors.add(a);
        
            }
        } else {
            // Create grid related anchors only
            for (int i = 0; i < anchorsCount; i++) {
                FixedAnchor a = new FixedAnchor(newNodeFigure, faceId, i, anchorsCount, this.gridLocator);
                anchors.add(a);
            }
        }
        
    }

    @objid ("06ae7f51-40df-4b91-8061-d53ef83b8d51")
    @Override
    public String getAlgorithmId() {
        throw new UnsupportedOperationException();
    }

    /**
     * Compute the anchor count due to the grid and put the result in the passed Dimension.
     * @param out the variable to fill
     */
    @objid ("d424ce95-e7e8-438e-a474-479d5763781b")
    protected void updateGridAnchorCount(Dimension out, Dimension gridSize) {
        Rectangle figSize = this.curFigBounds.getCopy();
        
        // compute how many time the 'margin' fit on horizontal and vertical faces
        int nHorizontal = Math.max(1, figSize.width / gridSize.width - 0);
        int nVertical = Math.max(1, figSize.height / gridSize.height - 0);
        
        out.setSize(nHorizontal, nVertical);
        
    }

    /**
     * Compute the anchor grid size to use
     * @param out the grid size to fill.
     * @return the passed grid size for convenience.
     */
    @objid ("deb4200f-6360-4605-af67-c0a311dfde83")
    protected Dimension getGridAnchorSize(Dimension out) {
        out.setSize(this.curViewerGridSpacing);
        
        while (out.width < 15) {
            out.width = out.width * 2;
        };
        
        while (out.width >= 40 ) {
            out.width = out.width / 2;
        }
        
        // We use only grids with same width and height
        out.height = out.width;
        return out;
    }

    /**
     * Get the grid spacing as configured in viewer properties.
     * @param newEditPart an edit part
     * @return the official grid spacing
     */
    @objid ("ad0da0c5-502f-4096-9f8f-26b7cf427da9")
    protected static final Dimension getViewerGridSpacing(GraphicalEditPart newEditPart) {
        return (Dimension) newEditPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_SPACING);
    }

    /**
     * {@link SnapToGrid} redefined to use our own grid size.
     */
    @objid ("59af7a69-1a95-482e-9bbf-be5500a448a4")
    private static class Snapper extends SnapToGrid {
        @objid ("c2088d9e-da17-4f52-aa4a-2f26f5f64d62")
        public  Snapper(GraphicalEditPart container, Dimension gridSize) {
            super(container);
            this.gridX = gridSize.width;
            this.gridY = gridSize.width;
            
        }

    }

}
