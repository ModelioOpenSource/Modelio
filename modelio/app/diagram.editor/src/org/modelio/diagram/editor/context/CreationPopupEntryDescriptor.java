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

package org.modelio.diagram.editor.context;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class is a descriptor representing a creation command.
 * <p>Consists of a metaclass (mandatory), a command id (mandatory), a stereotype (optional) and a parameter list (might be empty).
 */
@objid ("1547b912-7a27-40b1-9b9f-f3a0232e0b85")
class CreationPopupEntryDescriptor {
    @objid ("48c0a90d-947f-41e2-80e3-ea8c55df0aea")
    public String sourceMetaclass;

    @objid ("2f9a6a23-1d4c-4b37-a577-fce9aa467c85")
    public String sourceStereotype;

    @objid ("d300722b-7bbe-45b2-a3eb-14a3104eb21b")
    public String commandId;

    @objid ("63272cfb-cc80-4c6c-8a93-7b5f4b61d1e9")
    public Properties parameters = new Properties();

}
