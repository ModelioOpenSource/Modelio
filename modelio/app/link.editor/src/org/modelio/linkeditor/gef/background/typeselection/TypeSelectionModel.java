/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.linkeditor.gef.background.typeselection;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1b9865ad-5e33-11e2-b81d-002564c97630")
public class TypeSelectionModel {
    /**
     * A list of all the types that are possible to create.
     */
    @objid ("76c0201f-f4b4-48ee-8231-3e38e963df1f")
    private Set<LinkTypeDescriptor> types;

    @objid ("becab0b9-3e04-4d03-aef9-6945036102b4")
    private LinkTypeDescriptor selectedType;

    @objid ("1b9865b3-5e33-11e2-b81d-002564c97630")
    public TypeSelectionModel(final MObject centerElement, final MObject[] droppedElements, boolean isFrom, Set<LinkTypeDescriptor> candidates) {
        this.types = candidates;
    }

    @objid ("1b9865c5-5e33-11e2-b81d-002564c97630")
    public LinkTypeDescriptor getSelectedType() {
        return this.selectedType;
    }

    @objid ("1b9865c9-5e33-11e2-b81d-002564c97630")
    public void setSelectedType(final LinkTypeDescriptor selectedType) {
        this.selectedType = selectedType;
    }

    @objid ("072384e5-63e3-4735-881a-247a7a336022")
    public boolean isEmpty() {
        return this.types.isEmpty();
    }

    @objid ("71c4ce14-9c2e-458e-9fde-5e688c1a5540")
    public Set<LinkTypeDescriptor> getTypes() {
        return this.types;
    }

}
