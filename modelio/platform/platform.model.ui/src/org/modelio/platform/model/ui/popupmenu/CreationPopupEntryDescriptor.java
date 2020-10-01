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

package org.modelio.platform.model.ui.popupmenu;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class is a descriptor representing a creation command.
 * <p>Consists of a metaclass (mandatory), a command id (mandatory), a stereotype (optional) and a parameter list (might be empty).
 */
@objid ("be907af8-628f-4309-add9-d38b4517d774")
class CreationPopupEntryDescriptor implements IPopupEntryDescriptor {
    @objid ("ea6b88fc-9775-44f7-a317-d29156fd3679")
    public String sourceMetaclass;

    @objid ("4559b81c-d2a2-4840-84b5-86688aac5524")
    public String sourceStereotype;

    @objid ("61daeb8b-0ac2-4ba2-b0ce-bfa8717512e2")
    public String commandId;

    @objid ("e02d430b-834b-4c4e-b8a8-aa12c169ecaa")
    public Properties parameters = new Properties();

}
