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
package org.modelio.diagram.editor.handlers.unmask.ui;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("32ab6786-52b2-477e-afe0-d75b36e03b28")
public class LinkedElementData {
    @objid ("da546ffc-842d-4b90-9ca1-f94e421d71c7")
    private Boolean isSelected;

    @objid ("413f8e91-5c68-47e0-ab45-a1f2ddff8208")
    private MObject link;

    @objid ("ff242510-7b26-4864-ab45-1f2161c28bb2")
    private List<MObject> linkedElement = new ArrayList<>();

    @objid ("103d5ca9-e820-43b9-ade6-ddfa72eae0e3")
    public  LinkedElementData(MObject link, MObject linkedElement) {
        this.link = link;
        this.linkedElement.add(linkedElement);
        this.isSelected = true;
        
    }

    @objid ("48337831-c208-4537-80b3-fe18614a53e4")
    public  LinkedElementData(MObject link, List<MObject> linkedElement) {
        this.link = link;
        this.linkedElement = linkedElement;
        this.isSelected = true;
        
    }

    @objid ("44d5bfbf-990b-4858-bc35-e5b89d3d3adb")
    public MObject getLink() {
        return link;
    }

    @objid ("a0f19342-9ad7-4e55-9a92-2d78e29f3267")
    public void setLink(MObject link) {
        this.link = link;
    }

    @objid ("22618f97-ff53-4634-9755-7b98831f4e25")
    public MObject getFirstLinkedElement() {
        return linkedElement.get(0);
    }

    @objid ("6033caa8-bfda-43af-bbd0-13fe3b6c3ff4")
    public List<MObject> getLinkedElement() {
        return linkedElement;
    }

    @objid ("21d5bca4-829b-4054-a964-23d3c0e001b3")
    public Boolean getIsSelected() {
        return isSelected;
    }

    @objid ("ea940f8d-4d47-40c8-8947-415f570f3988")
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
