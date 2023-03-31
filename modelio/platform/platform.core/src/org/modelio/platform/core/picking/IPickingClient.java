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
package org.modelio.platform.core.picking;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("008339ca-02e9-106f-bbdd-001ec947cd2a")
public interface IPickingClient {
    @objid ("0083c976-02e9-106f-bbdd-001ec947cd2a")
    boolean hover(MObject target);

    @objid ("0083d9fc-02e9-106f-bbdd-001ec947cd2a")
    boolean pick(MObject target);

    @objid ("007e3e7a-e423-1076-aae0-001ec947cd2a")
    void abort();
}

