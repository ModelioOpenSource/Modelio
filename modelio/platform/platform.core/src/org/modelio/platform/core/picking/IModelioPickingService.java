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
import org.modelio.platform.core.IModelioService;

@objid ("005b93fc-0355-106f-bbdd-001ec947cd2a")
public interface IModelioPickingService extends IModelioService {
    @objid ("005baf04-0355-106f-bbdd-001ec947cd2a")
    IPickingSession startPicking(IPickingClient client);

    @objid ("005bbbc0-0355-106f-bbdd-001ec947cd2a")
    void stopPicking(IPickingSession session);
}

