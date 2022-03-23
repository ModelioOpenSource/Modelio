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
package org.modelio.uml.usecasediagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
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

@objid ("45132462-170a-463e-a48b-d68570c73c5e")
public class UseCaseDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("2a73816d-0b0a-4221-a6b8-d8a9ed8e103b")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("67cf3777-e43b-4f4a-9dda-3e0d4c1143c1")
    public  UseCaseDiagramTemplate() {
        super();
    }

    @objid ("407049f6-eac5-4254-8e66-5bb6fff0a5db")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("bb75ed43-b852-42ea-aae5-333d562dbbe0")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        AbstractDiagram diagram = modelFactory.createUseCaseDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("64d908e9-3481-4ebe-9d37-3136001bc5ee")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("155034c3-4dc1-41d4-b493-ce8250f5b941")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("949447b1-d6ce-4255-b9e2-874dbe54b5d3")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("118c6a70-aeef-44eb-8f11-34be04fa6326")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    @objid ("2dbd5400-896c-4138-84f8-96f9c89162be")
    private AbstractDiagram smartCreateForStateMachine(IStandardModelFactory modelFactory, StateMachine parentStateMachine) {
        return createStateMachineDiagram(modelFactory, parentStateMachine);
    }

    @objid ("1afe7d25-4c6f-4203-b5a5-298896dd7416")
    private AbstractDiagram smartCreateForOperation(IStandardModelFactory modelFactory, Operation parentOperation) {
        StateMachine stateMachine = modelFactory.createStateMachine();
        stateMachine.setOwnerOperation(parentOperation);
        stateMachine.setName(this.mmServices.getElementNamer().getUniqueName(stateMachine));
        return createStateMachineDiagram(modelFactory, stateMachine);
    }

    @objid ("35d98b0c-c2a7-4221-b720-8a217e9174b1")
    private AbstractDiagram smartCreateForNameSpace(IStandardModelFactory modelFactory, NameSpace parentNameSpace) {
        StateMachine stateMachine = modelFactory.createStateMachine();
        stateMachine.setOwner(parentNameSpace);
        stateMachine.setName(this.mmServices.getElementNamer().getUniqueName(stateMachine));
        return createStateMachineDiagram(modelFactory, stateMachine);
    }

    @objid ("f20916fd-410d-4bc3-9dfc-2dadadf1d304")
    private StateMachineDiagram createStateMachineDiagram(final IStandardModelFactory modelFactory, StateMachine parentStateMachine) {
        StateMachineDiagram diagram = modelFactory.createStateMachineDiagram();
        diagram.setOrigin(parentStateMachine);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

}
