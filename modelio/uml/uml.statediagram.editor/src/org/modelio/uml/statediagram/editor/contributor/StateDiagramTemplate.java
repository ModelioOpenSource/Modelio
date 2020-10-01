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

package org.modelio.uml.statediagram.editor.contributor;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;

@objid ("aafb0299-42aa-485f-ac90-ec36a971b37e")
public class StateDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("5f1bbb3a-91c3-4f19-b926-a273cce4696b")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("47cff0d8-cd3d-4694-bd64-bd1a43e8f1bb")
    public StateDiagramTemplate() {
        super();
    }

    @objid ("116469f1-17c0-435c-b3c7-c5799b0eb315")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("aa651c38-443a-4414-81d9-78e1856f0b81")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        AbstractDiagram diagram = null;
        
        if (base instanceof StateMachine) {
            diagram = smartCreateForStateMachine(modelFactory, (StateMachine) base);
        } else if (base instanceof Operation) {
            diagram = smartCreateForOperation(modelFactory, (Operation) base);
        } else {
            diagram = smartCreateForNameSpace(modelFactory, (NameSpace) base);
        }
        return diagram;
    }

    @objid ("1b1e634a-ce7f-4c67-991d-8346df53aed8")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("db41e654-6fdf-47d9-aca6-02cb06202f54")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("5e1e85e3-a918-45cf-b775-d2b8b21fc62d")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("057f1a0f-9bfd-42a1-aa22-4d3837df0a42")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    @objid ("f5dc8cbf-1309-4400-9a17-68715e11fe36")
    private AbstractDiagram smartCreateForStateMachine(IStandardModelFactory modelFactory, StateMachine parentStateMachine) {
        return createStateMachineDiagram(modelFactory, parentStateMachine);
    }

    @objid ("81ef7e00-9678-4abf-8616-50e0fae711f0")
    private AbstractDiagram smartCreateForOperation(IStandardModelFactory modelFactory, Operation parentOperation) {
        StateMachine stateMachine = modelFactory.createStateMachine();
        stateMachine.setOwnerOperation(parentOperation);
        stateMachine.setName(this.mmServices.getElementNamer().getUniqueName(stateMachine));
        return createStateMachineDiagram(modelFactory, stateMachine);
    }

    @objid ("418964f7-5f69-4689-bc0a-e83233be1719")
    private AbstractDiagram smartCreateForNameSpace(IStandardModelFactory modelFactory, NameSpace parentNameSpace) {
        StateMachine stateMachine = modelFactory.createStateMachine();
        stateMachine.setOwner(parentNameSpace);
        stateMachine.setName(this.mmServices.getElementNamer().getUniqueName(stateMachine));
        return createStateMachineDiagram(modelFactory, stateMachine);
    }

    @objid ("9038b1b5-3b1e-4305-ba50-44819fab3c88")
    private StateMachineDiagram createStateMachineDiagram(final IStandardModelFactory modelFactory, StateMachine parentStateMachine) {
        StateMachineDiagram diagram = modelFactory.createStateMachineDiagram();
        diagram.setOrigin(parentStateMachine);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

}
