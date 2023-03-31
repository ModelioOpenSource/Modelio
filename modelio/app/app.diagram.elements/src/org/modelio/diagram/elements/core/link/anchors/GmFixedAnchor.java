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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents a anchor anchored on a fixed place on a face of the figure.
 */
@objid ("9633d707-fe1f-4772-bf32-27fd286dc17a")
public class GmFixedAnchor implements IPersistent {
    @objid ("291c90aa-7ff2-41a6-8d2c-ce78db513b99")
    private static final int MAJOR_VERSION = 0;

    
    @mdl.prop
    @objid ("797a5c39-f9c7-4164-bae4-7afdfabe09a0")
    private int face;

    @mdl.propgetter
    public int getFace() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.face;
    }

    @mdl.propsetter
    public void setFace(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.face = value;
    }

    
    @mdl.prop
    @objid ("1d547d04-264d-465d-9cc4-3466702174f9")
    private int rank;

    @mdl.propgetter
    public int getRank() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.rank;
    }

    @mdl.propsetter
    public void setRank(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.rank = value;
    }

    
    @mdl.prop
    @objid ("28a15e11-1435-45c9-bfdc-5633c87e60e5")
    private int totalOnFace;

    @mdl.propgetter
    public int getTotalOnFace() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.totalOnFace;
    }

    @mdl.propsetter
    public void setTotalOnFace(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.totalOnFace = value;
    }

    /**
     * the {@link org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator IFixedAnchorLocator} implementation ID.
     */
    
    @mdl.prop
    @objid ("0f72a073-d82c-414e-ab96-66ad36524dca")
    private String locator;

    @mdl.propgetter
    public String getLocator() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.locator;
    }

    @mdl.propsetter
    public void setLocator(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.locator = value;
    }

    @objid ("b5f3558f-febf-4610-b35d-51a866e9d8d1")
    @Override
    public void read(IDiagramReader in) {
        this.face = (int) in.readProperty("face");
        this.rank = (int) in.readProperty("rank");
        this.totalOnFace = (int) in.readProperty("total");
        this.locator = (String) in.readProperty("locator");
        
    }

    @objid ("78b617f6-43f7-4038-bc1b-e30383bf1df8")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("face", this.face);
        out.writeProperty("rank", this.rank);
        out.writeProperty("total", this.totalOnFace);
        out.writeProperty("locator", this.locator);
        
    }

    @objid ("83ae7286-1aec-4043-ae65-e570a7771fca")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * Constructor.
     * @param locator the {@link org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator IFixedAnchorLocator} implementation ID.
     * @param face the face number
     * @param rank the anchor number on the face
     * @param totalOnFace the number of possible anchors on the face
     */
    @objid ("ce360404-c1e5-48ed-aa1d-445919cd60b9")
    public  GmFixedAnchor(final String locator, final int face, final int rank, final int totalOnFace) {
        this.face = face;
        this.rank = rank;
        this.totalOnFace = totalOnFace;
        this.locator = locator;
        
    }

    @objid ("cf990936-14a9-4f87-9cbf-eaf76869c56e")
    public  GmFixedAnchor() {
        
    }

    @objid ("dfc6f26d-94d8-4857-8b64-28ec3b4d817b")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("4e4fbec6-373f-4e54-8dcc-895412f3dfe7")
    @Override
    public String toString() {
        return String.format("%s [face=%s, rank=%d / %d]", getClass().getSimpleName(), this.face, this.rank, this.totalOnFace);
    }

    @objid ("e8c019c3-8341-4b4d-8272-e2665dca00ec")
    @Override
    public boolean equals(Object obj) {
        // Automatically generated method.Please delete this comment before entering specific code.
        
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        
        GmFixedAnchor other = (GmFixedAnchor)obj;
        if (this.face != other.face) return false;
        if (this.rank != other.rank) return false;
        if (this.totalOnFace != other.totalOnFace) return false;
        return true;
    }

    @objid ("79c0e9d8-0d94-498b-9aa4-a8293aceeb1d")
    @Override
    public int hashCode() {
        // Automatically generated method.Please delete this comment before entering specific code.
        int result = super.hashCode();
        result = 31 * result + this.face;
        result = 31 * result + this.rank;
        result = 31 * result + this.totalOnFace;
        return result;
    }

}
