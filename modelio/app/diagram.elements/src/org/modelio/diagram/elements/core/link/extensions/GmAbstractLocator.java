/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * Abstract base implementation for {@link IGmLocator} that stores size constraints.
 * @author cmarin
 */
@objid ("c8cf569d-370f-43d9-bf3f-7b5c4410d604")
public abstract class GmAbstractLocator implements IGmLocator {
    @objid ("b59227cd-41e4-426d-80d9-06d6bf2832fe")
    private int heightConstraint = -1;

    @objid ("323f91cd-2c6a-4713-924f-2d622c7fc779")
    private int widthConstraint = -1;

    /**
     * Copy constructor.
     * @param source the object to copy.
     */
    @objid ("5051907a-8432-413d-a6ed-953bed1eceff")
    public GmAbstractLocator(GmAbstractLocator source) {
        this.heightConstraint = source.heightConstraint;
        this.widthConstraint = source.widthConstraint;
    }

    /**
     * Default constructor.
     */
    @objid ("ab80f7e1-2210-4f0f-976d-b94f41902850")
    public GmAbstractLocator() {
        super();
    }

    @objid ("e502b236-dab6-4335-a222-778d49c6bd27")
    @Override
    public void read(IDiagramReader in) {
        Integer i;
        i = (Integer) in.readProperty("width");
        this.widthConstraint = i != null ? i : -1;
        
        i = (Integer) in.readProperty("height");
        this.heightConstraint = i != null ? i : -1;
    }

    @objid ("8ad6f4b7-e4f8-40ca-b1f5-344d71548e58")
    @Override
    public void write(IDiagramWriter out) {
        if (this.widthConstraint != -1) {
            out.writeProperty("width", this.widthConstraint);
        }
        
        if (this.heightConstraint != -1) {
            out.writeProperty("height", this.heightConstraint);
        }
    }

    @objid ("c043f9a6-7841-441a-ba3f-0f5a6584bd89")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("d073839d-a510-400d-9571-16905ec64aba")
    @Override
    public int getWidthConstraint() {
        return this.widthConstraint;
    }

    @objid ("0b5a17a2-2e15-4c26-b936-dbf1a747dc10")
    @Override
    public int getHeightConstraint() {
        return this.heightConstraint;
    }

    @objid ("5a7b4fab-1a68-4650-b39b-067c3318f82e")
    @Override
    public void setWidthConstraint(int val) {
        this.widthConstraint = val;
    }

    @objid ("e0b128fb-4e07-45db-a3ec-dadc4d515b5e")
    @Override
    public void setHeightConstraint(int val) {
        this.heightConstraint = val;
    }

}
