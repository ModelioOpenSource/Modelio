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

package org.modelio.platform.core.navigate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link IModelioNavigationService} implementation.
 * <p>
 * This class self instantiates in the context at demand with {@link Creatable}.
 */
@objid ("005d684e-a738-10ac-8258-001ec947cd2a")
@Creatable
public class ModelioNavigationService implements IModelioNavigationService {
    @objid ("005d7604-a738-10ac-8258-001ec947cd2a")
    @Inject
    private IModelioEventService eventService;

    @objid ("005d787a-a738-10ac-8258-001ec947cd2a")
    @Override
    public String getName() {
        return "ModelioNavigateService";
    }

    @objid ("005bba6c-a86b-10ac-8258-001ec947cd2a")
    @Override
    public void fireNavigate(MObject data) {
        if (data == null) {
            this.eventService.postAsyncEvent(this, ModelioEvent.NAVIGATE_ELEMENT, Collections.emptyList());
        } else {
            this.eventService.postAsyncEvent(this, ModelioEvent.NAVIGATE_ELEMENT, Arrays.asList(data));
        }
    }

    @objid ("005bd8da-a86b-10ac-8258-001ec947cd2a")
    @Override
    public void fireNavigate(List<MObject> data) {
        if (data == null) {
            this.eventService.postAsyncEvent(this, ModelioEvent.NAVIGATE_ELEMENT, Collections.emptyList());
        } else {
            this.eventService.postAsyncEvent(this, ModelioEvent.NAVIGATE_ELEMENT, data);
        }
    }

}
