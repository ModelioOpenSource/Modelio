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

package org.modelio.vcore.session.api.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("003ca5fa-c2c4-1f3b-aafd-001ec947cd2a")
public interface IElementMovedEvent {
    @objid ("0029d2cc-d402-1f3b-aafd-001ec947cd2a")
    @Override
    String toString();

    @objid ("0029ea64-d402-1f3b-aafd-001ec947cd2a")
    MObject getMovedElement();

    @objid ("0029f202-d402-1f3b-aafd-001ec947cd2a")
    MObject getNewParent();

    @objid ("0029f9c8-d402-1f3b-aafd-001ec947cd2a")
    MObject getOldParent();

}
