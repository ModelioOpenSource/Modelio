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

@objid ("000851e2-c2b7-1f3b-aafd-001ec947cd2a")
public interface IElementDeletedEvent {
    @objid ("00465e10-d283-1f3b-aafd-001ec947cd2a")
    @Override
    String toString();

    @objid ("004684e4-d283-1f3b-aafd-001ec947cd2a")
    MObject getDeletedElement();

    @objid ("002a9d9c-d402-1f3b-aafd-001ec947cd2a")
    MObject getOldParent();

}
