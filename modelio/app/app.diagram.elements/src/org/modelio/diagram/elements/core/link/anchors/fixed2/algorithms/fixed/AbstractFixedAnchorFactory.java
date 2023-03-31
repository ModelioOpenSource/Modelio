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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

@objid ("0d876d0e-2753-45f8-b9f2-6b52f42afa76")
public abstract class AbstractFixedAnchorFactory implements IFigureAnchorsFactory {
    @objid ("0168a047-cfb0-48e4-b136-c9dceea6f7f0")
    private final String algorithmId;

    @objid ("742c297d-e80c-483c-acd6-614cf456b69f")
    public  AbstractFixedAnchorFactory(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    @objid ("1263c82c-3941-4955-ad6e-5d9b0c0933fe")
    @Override
    public ConnectionAnchor createFromModel(GmFixedAnchor gmAnchor) {
        return new FixedAnchor(
                getNodeFigure(),
                gmAnchor.getFace(),
                gmAnchor.getRank(),
                gmAnchor.getTotalOnFace(),
                getLocator());
        
    }

    @objid ("4a000e4c-4e43-42cb-9bca-6eeded7de705")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(ConnectionRouterId routerId, Integer face) {
        IFigure newNodeFigure = getNodeFigure();
        IFixedAnchorLocator locator = getLocator();
        Dimension anchorsCount = getAnchorCount(new Dimension());
        
        Collection<ConnectionAnchor> anchors = new ArrayList<>(anchorsCount.width + anchorsCount.height);
        for (int i = 0; i < anchorsCount.width; i++) {
            anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_NORTH, i, anchorsCount.width, locator));
            anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_SOUTH, i, anchorsCount.width, locator));
        }
        
        for (int i = 0; i < anchorsCount.height; i++) {
            anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_EAST, i, anchorsCount.height, locator));
            anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_WEST, i, anchorsCount.height, locator));
        }
        return anchors;
    }

    @objid ("7349b036-a9d5-4170-96da-44464b7b083f")
    protected abstract IFixedAnchorLocator getLocator();

    @objid ("30b5e5a4-6b8f-461a-b7a2-f98d1f030722")
    protected abstract IFigure getNodeFigure();

    @objid ("01e82d58-e89f-4e36-9d16-7cc38ad158c1")
    protected abstract GraphicalEditPart getNodeEdtPart();

    /**
     * Fills the given Dimension with the anchor count.
     * <p>
     * Method designed like with to minimize allocations in a safe way.
     * @param out the Dimension to fill with the anchor count.
     * @return the passed dimension, for convenience.
     */
    @objid ("7076f13c-fb74-407d-9226-00e77a0f8249")
    public final Dimension getAnchorCount(Dimension out) {
        fillAnchorCount(out);
        return out;
    }

    /**
     * Fills the given Dimension with the anchor count.
     * <p>
     * Method designed like with to minimize allocations in a safe way.
     * @see #getAnchorCount(Dimension)
     * @param out the Dimension to fill with the anchor count.
     */
    @objid ("581692bc-a3b3-4867-91c7-7d5541326d10")
    public abstract void fillAnchorCount(Dimension out);

    @objid ("ad7b0ed0-c805-4db5-a4a8-d327357735a6")
    @Override
    public String getAlgorithmId() {
        return this.algorithmId;
    }

}
