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
package org.modelio.platform.core.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.IModelioService;

@objid ("0044936e-cc35-1ff2-a7f4-001ec947cd2a")
public class ModelioEventService implements IModelioEventService {
    @objid ("003b4ae8-441a-105b-aa42-001ec947cd2a")
    private final IEclipseContext context;

    @objid ("00499ed6-cc35-1ff2-a7f4-001ec947cd2a")
    public  ModelioEventService(final IEclipseContext context) {
        this.context = context;
    }

    @objid ("0082871e-acc2-103b-a520-001ec947cd2a")
    @Override
    public void postAsyncEvent(final IModelioService emitter, final ModelioEvent topic, final Object data) {
        postAsyncEvent(emitter, topic.topic(), data);
    }

    @objid ("0023cdbe-a9cf-106e-bbdd-001ec947cd2a")
    @Override
    public void postSyncEvent(final IModelioService emitter, final ModelioEvent topic, final Object data) {
        postSyncEvent(emitter, topic.topic(), data);
    }

    @objid ("4d13dc78-6a73-4569-a173-4ac02e610e71")
    public void postAsyncEvent(final IModelioService emitter, final String topic, final Object data) {
        IEventBroker eventBroker = this.context.get(IEventBroker.class);
        if (eventBroker != null) {
            eventBroker.post(topic, data);
        }
        
    }

    @objid ("d4f8289c-2601-4526-8d4b-6a60bfb962c1")
    public void postSyncEvent(final IModelioService emitter, final String topic, final Object data) {
        IEventBroker eventBroker = this.context.get(IEventBroker.class);
        if (eventBroker != null) {
           eventBroker.send(topic, data);
        }
        
    }

}
