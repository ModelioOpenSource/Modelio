/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.uml.ui.browser.handlers;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.model.browser.view.handlers.create.CreateCmsElementHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("89659379-c9d8-11e1-b479-001ec947c8cc")
public class CreateStateMachineHandler extends CreateCmsElementHandler {
    @objid ("a197d766-79d1-483f-b33e-65854ed282dc")
    @Inject
     IModelioEventService eventService;

    @objid ("4e57e2b6-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        // Create the Top Region and a default State diagram
        StateMachine stateMachine = (StateMachine) createdElement;
        
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        Region topRegion = modelFactory.createRegion();
        stateMachine.setTop(topRegion);
        
        StateMachineDiagram diagram = modelFactory.createStateMachineDiagram();
        stateMachine.getProduct().add(diagram);
        diagram.setName(mmServices.getElementNamer().getUniqueName(diagram));
    }

    @objid ("4e57e2ba-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        final StateMachineDiagram param = ((StateMachine) element).getProduct(StateMachineDiagram.class).get(0);
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                CreateStateMachineHandler.this.eventService.postAsyncEvent(new IModelioService() {
                    @Override
                    public String getName() {
                        return "openEditor : AbstractDiagram";
                    }
                }, ModelioEvent.EDIT_ELEMENT, param);
            }
        });
    }

}
