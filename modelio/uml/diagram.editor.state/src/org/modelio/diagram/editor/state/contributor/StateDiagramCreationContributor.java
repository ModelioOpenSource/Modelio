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

package org.modelio.diagram.editor.state.contributor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.state.plugin.DiagramEditorState;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for StateMachine diagrams.
 */
@objid ("bd03b437-0b6b-4f0b-9ff2-9a8dd3ea9afb")
public class StateDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("4d225f6e-95a1-410f-ac20-36548fd09ae8")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("93b631d9-ff29-4bb6-8a9d-2b16a0c7c6ae")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StateMachineDiagram stateDiagram = null;
        
        // Create the StateMachine:
        final StateMachine stateMachine;
        
        if (diagramContext instanceof StateMachine) {
            stateMachine = (StateMachine) diagramContext;
        } else if (diagramContext instanceof Operation) {
            stateMachine = modelFactory.createStateMachine();
            stateMachine.setOwnerOperation((Operation) diagramContext);
            setElementDefaultName(stateMachine);
        } else {
            stateMachine = modelFactory.createStateMachine();
            stateMachine.setOwner((NameSpace) diagramContext);
            setElementDefaultName(stateMachine);
        }
        
        // Create the state machine diagram:
        stateDiagram = createStateDiagram(modelFactory, diagramName, stateMachine);
        
        if (stateDiagram != null) {
            if (diagramName.equals(getLabel())) {
                setElementDefaultName(stateDiagram);
            } else {
                stateDiagram.setName(diagramName);
            }
            putNoteContent(stateDiagram, "description", diagramDescription);
        }
        return stateDiagram;
    }

    @objid ("310f05f7-23f8-4f33-9bfe-e9588d502594")
    @Override
    public String getDetails() {
        return DiagramEditorState.I18N.getString("CreationWizard.State.Details");
    }

    @objid ("4a80ece4-a985-446a-8575-7b7ef371f0bd")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(StateMachineDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("c1317223-1c92-4a40-ae67-ca28708f43ac")
    @Override
    public String getInformation() {
        return DiagramEditorState.I18N.getString("CreationWizard.State.Information");
    }

    @objid ("fad7354e-f54c-43c0-bada-c45087202f82")
    @Override
    public String getLabel() {
        return DiagramEditorState.I18N.getString("CreationWizard.State.Name");
    }

    @objid ("57d59360-cdae-428a-832a-7e5d32f56a52")
    private StateMachineDiagram createStateDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        // Create the Sequence diagram
        StateMachineDiagram stateDiagram = modelFactory.createStateMachineDiagram(diagramName, diagramContext, null);
        return stateDiagram;
    }

    @objid ("8b564a44-c64c-4947-8f52-616f6d9e6974")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interface.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Signal.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Actor.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(UseCase.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Operation.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(StateMachine.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("0d4a0862-9404-4bab-8ab0-b7fe6cb821c8")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorState.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/statediagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("872474c4-6b94-4a37-bbfb-9698dac8adf0")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("fa5fdd90-a833-4c12-b91d-5dcc525d2783")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("3fe84fc1-5175-433f-b48f-2f5db132c2d9")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("c4194aca-b767-46fc-b19f-6abec8bec80f")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("207ba25b-d057-42ac-9857-c8c85c922d0b")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, StateMachineDiagram.MQNAME);
    }

    @objid ("9ae917de-09d9-4a6d-9506-9b37566242ba")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(StateMachineDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
