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

package org.modelio.platform.core.activation;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("00302b70-3967-11e2-a430-001ec947c8cc")
public class ActivationService implements IActivationService {
    @objid ("59a7fa8e-3a22-11e2-a430-001ec947c8cc")
    @Inject
    private IModelioEventService eventService;

    @objid ("59aa5cd5-3a22-11e2-a430-001ec947c8cc")
    @Override
    public String getName() {
        return "ModelioActivationService";
    }

    @objid ("59aa5cda-3a22-11e2-a430-001ec947c8cc")
    @Override
    public void activateMObject(MObject objectToActivate) {
        this.eventService.postAsyncEvent(this, ModelioEvent.EDIT_ELEMENT, objectToActivate);
    }

    @objid ("b8f5ea4b-2e94-4f5b-8e56-703ddea953bf")
    @Override
    public void editProperties(MObject objectToActivate) {
        Display.getDefault().asyncExec(new Runnable() {
        
            @Override
            public void run() {
                ActivationService.this.eventService.postAsyncEvent(ActivationService.this, ModelioEvent.EDIT_PROPERTIES, objectToActivate);
            }
        });
    }

}
