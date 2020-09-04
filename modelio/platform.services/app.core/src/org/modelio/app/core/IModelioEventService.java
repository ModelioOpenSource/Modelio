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

package org.modelio.app.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.core.events.ModelioEvent;

@objid ("00449eae-cc35-1ff2-a7f4-001ec947cd2a")
public interface IModelioEventService {
    @objid ("007d0988-acc2-103b-a520-001ec947cd2a")
    void postSyncEvent(IModelioService emitter, final ModelioEvent topic, final Object data);

    @objid ("000f3124-a9ae-106e-bbdd-001ec947cd2a")
    void postAsyncEvent(IModelioService emitter, final ModelioEvent topic, final Object data);

}
