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
package org.modelio.uml.sequencediagram.editor.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d969c4f2-55b6-11e2-877f-002564c97630")
public enum MessageType {
    @objid ("d969c4f3-55b6-11e2-877f-002564c97630")
    SimpleSynchronous,
    @objid ("d969c4f4-55b6-11e2-877f-002564c97630")
    SimpleAsynchronous,
    @objid ("d969c4f5-55b6-11e2-877f-002564c97630")
    Reply,
    @objid ("d969c4f6-55b6-11e2-877f-002564c97630")
    ToSelf,
    @objid ("d969c4f7-55b6-11e2-877f-002564c97630")
    InnerExecutionSynchronous,
    @objid ("d969c4f8-55b6-11e2-877f-002564c97630")
    InnerExecutionAsynchronous,
    @objid ("d969c4f9-55b6-11e2-877f-002564c97630")
    InnerExecutionToSelf,
    @objid ("d969c4fa-55b6-11e2-877f-002564c97630")
    Lost,
    @objid ("d969c4fb-55b6-11e2-877f-002564c97630")
    Found,
    @objid ("d969c4fc-55b6-11e2-877f-002564c97630")
    Creation,
    @objid ("d969c4fd-55b6-11e2-877f-002564c97630")
    Destruction;

}
