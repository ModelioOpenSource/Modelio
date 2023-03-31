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
package org.modelio.diagram.elements.core.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("1f3d21e6-7baf-47b5-8697-485f38d01d8a")
public class ElementPlaceolderData {
    @objid ("ebfee21f-e35f-4813-943a-c6f9199dedc6")
    private String name = "";

    @objid ("98027345-99d9-4bff-8dde-2f39a72ededb")
    private String description = "";

    @objid ("d47deff8-95a9-408e-a85b-46fd71086281")
    private MClass metaclass;

    @objid ("69e3e077-aeb4-4bc3-b09c-9dd1e46782c7")
    public String getName() {
        return name;
    }

    @objid ("03dfa0d0-2a57-4a8c-9172-c1bc507b470a")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("f577348b-fc37-4e99-8ca8-769e5ce8adf2")
    public String getDescription() {
        return description;
    }

    @objid ("f845970f-1b6e-462c-b5a0-f56f628a8e1e")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("ab1ffa9c-a443-42a2-b73e-ce0f66e34086")
    public MClass getMetaclass() {
        return metaclass;
    }

    @objid ("f891d59e-d0f4-475f-8d52-bb26edc7090e")
    public void setMetaclass(MClass metaclass) {
        this.metaclass = metaclass;
    }

}
