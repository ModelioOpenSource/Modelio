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
package org.modelio.model.browser.view.handlers.create;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.IModelioService;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific handler for diagram creation, opening it at the end of the creation.
 */
@objid ("3690b569-5bec-11e2-90ce-002564c97630")
public class CreateDiagramHandler extends CreateCmsElementHandler {
    @objid ("afe34d29-c6ec-44b1-a847-374b01b41eaa")
    @Inject
    protected IModelioEventService eventService;

    @objid ("23677180-e499-4f56-a51a-60db63144f07")
    @Override
    protected void postCommit(final MPart part, final MObject element, IMModelServices mmServices) {
        if (element instanceof AbstractDiagram) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    CreateDiagramHandler.this.eventService.postAsyncEvent(new IModelioService() {
                        @Override
                        public String getName() {
                            return "openEditor : AbstractDiagram";
                        }
                    }, ModelioEvent.EDIT_ELEMENT, element);
                }
            });
        }
        
    }

}
