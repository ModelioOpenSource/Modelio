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
package org.modelio.diagram.elements.core.figures.anchors;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.geometry.Direction;

/**
 * Anchor on one of {@link #getFace()} of a polygon at a {@link #getRank()}/{@link #getTotalOnFace()} fraction of the face length.
 * <p>
 * The reference point and the anchor location computation is delegated to a {@link IFixedAnchorLocator} {@link #locator}.
 */
@objid ("dc699008-39b9-42a7-bcd1-754216ff22c8")
public class FixedAnchor extends AbstractConnectionAnchor implements IOrientedAnchor, IAnchorHandleProvider {
    /**
     * Anchor feedback figures radius.
     */
    @objid ("01ffb530-d794-41f0-8574-c038704789bd")
    public static final int ANCHOR_RADIUS = 5;

    
    @mdl.prop
    @objid ("932b78fd-d298-4902-85d4-8b6822dd34b0")
    private int face;

    @mdl.propgetter
    public int getFace() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.face;
    }

    
    @mdl.prop
    @objid ("1da0255f-c2fa-444d-82b5-2019fb62ecea")
    private int rank;

    @mdl.propgetter
    public int getRank() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.rank;
    }

    
    @mdl.prop
    @objid ("717ade79-c362-4076-9258-fc965c3877b6")
    private int totalOnFace;

    @mdl.propgetter
    public int getTotalOnFace() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.totalOnFace;
    }

    @objid ("cfc7bda3-ddc9-4dd0-9cfe-410fee71ef6a")
    private IFixedAnchorLocator locator;

    /**
     * Constructor
     * @param owner a rectangular figure
     * @param face the face number
     * @param rank the anchor number on the face
     * @param count the number of possible anchors on the face
     * @param locator the algorithm to position the anchor
     */
    @objid ("1c8216e7-b5ec-4133-a4a3-01ecdb9ee5a7")
    public  FixedAnchor(final IFigure owner, final int face, final int rank, final int count, IFixedAnchorLocator locator) {
        super(Objects.requireNonNull(owner));
        assert rank >= 0 && rank <= count;
        
        this.locator = locator;
        
        this.face = face;
        this.rank = rank;
        this.totalOnFace = count;
        
    }

    /**
     * Copy constructor
     * @param other the anchor to copy
     */
    @objid ("08156730-2227-42d7-bc5a-9b9d4b975657")
    public  FixedAnchor(FixedAnchor other) {
        this(other.getOwner(), other.getFace(), other.getRank(), other.getTotalOnFace(), other.getLocator());
    }

    @objid ("abea34a7-e08d-4bf8-a2c9-a07e8213bd0e")
    @Override
    public void ancestorMoved(IFigure figure) {
        super.ancestorMoved(figure);
        this.locator.onFigureMoved(figure);
        
    }

    @objid ("0050068a-9e18-4c19-9fe3-8229ac549af6")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FixedAnchor other = (FixedAnchor) obj;
        return isSame(other);
    }

    /**
     * @return a debug friendly face name for {@link #getFace()}.
     */
    @objid ("cc9d34dd-2b66-42d8-9620-cefbbbfcf847")
    public String getFaceName() {
        return this.locator.getFaceName(this);
    }

    /**
     * Always return the reference point.
     */
    @objid ("b538a73b-fe6b-4751-9313-e8c418fb98f0")
    @Override
    public Point getLocation(final Point reference) {
        return this.locator.getLocation(this, reference);
    }

    /**
     * @return Algorithm the anchor delegates to to compute its position.
     */
    @objid ("162ad2f4-0319-443f-a620-fcc47e9c2458")
    public IFixedAnchorLocator getLocator() {
        return this.locator;
    }

    @objid ("0c4f1728-b585-4a60-80c7-34a6410a32bc")
    @Override
    public Point getReferencePoint() {
        return this.locator.getReferencePoint(this);
    }

    @objid ("8adc2eb2-0d66-4f40-a0f8-917d5479c757")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.face;
        result = prime * result + this.rank;
        result = prime * result + this.totalOnFace;
        result = prime * result + getOwner().hashCode();
        return result;
    }

    @objid ("2da0c94c-b578-4db2-a636-1b5c52fc4dbc")
    public boolean isSame(FixedAnchor other) {
        return other.getFace() == getFace() &&
                other.getRank()==getRank() &&
                other.getTotalOnFace()==getTotalOnFace() &&
                other.getOwner()==getOwner();
        
    }

    @objid ("97d5b33c-e28f-4c5d-aacc-96335d334d8c")
    @Override
    public Direction getDirection() {
        return this.locator.getDirection(this);
    }

    @objid ("590a5dda-c7b6-44da-81bd-df89d7e12a6c")
    @Override
    public void setOwner(IFigure owner) {
        super.setOwner(Objects.requireNonNull(owner));
    }

    @objid ("f03489e5-5a00-4673-aa16-e5b65de4341b")
    @Override
    public String toString() {
        return String.format("%s [face=%s, rank=%d/%d]", getClass().getSimpleName(), getFaceName(), getRank(), getTotalOnFace());
    }

    @objid ("d16765f0-a7fe-4134-bddf-56b06897be09")
    @Override
    public IFigure createAnchorHandleFigure(ConnectionAnchor anchor) {
        return this.locator.createAnchorHandleFigure(this);
    }

    /**
     * Overwrite all fields with new values.
     * <p>
     * <h2>Warning</h2>
     * To be used only for temporary anchors involved in small computations.
     * @param owner a rectangular figure
     * @param newFace the face number
     * @param newRank the anchor number on the face
     * @param count the number of possible anchors on the face
     * @param newLocator the algorithm to position the anchor
     */
    @objid ("28b2df86-e5a2-4b8b-b5e7-97b145cb2b16")
    public void overwrite(final IFigure owner, final int newFace, final int newRank, final int count, IFixedAnchorLocator newLocator) {
        assert newRank >= 0 && newRank <= count;
        setOwner(owner);
        
        this.locator = newLocator;
        
        this.face = newFace;
        this.rank = newRank;
        this.totalOnFace = count;
        
    }

}
