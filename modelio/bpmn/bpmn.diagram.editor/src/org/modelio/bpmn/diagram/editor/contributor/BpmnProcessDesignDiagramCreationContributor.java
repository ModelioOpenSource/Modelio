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
package org.modelio.bpmn.diagram.editor.contributor;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Creation contributor for Bpmn Process Design diagrams.
 */
@objid ("a60fa965-d978-4db7-b4c8-cbb919b85655")
public class BpmnProcessDesignDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("25eb0df9-75cd-4632-919a-d139de205a0b")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("a3f50d75-392d-4958-8a02-b8d69de60e2d")
    @Inject
    @Optional
    private ModelViewTemplateManager diagramCreationService;

    @objid ("2b3a7c84-580d-473c-9561-a3583611f400")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        final BpmnProcess process;
        if (diagramContext instanceof BpmnProcess) {
            // Creating a Process Design Diagram under an existing process
            process = (BpmnProcess) diagramContext;
        } else {
            // Creating a Process Design Diagram without an existing process => create a BPMNProcess first is applicable
            process = createBpmnProcess(diagramContext);
        }
        
        // Got a valid process !
        IModelViewTemplate<AbstractDiagram> creator = this.diagramCreationService.get(getModelViewTemplateId());
        AbstractDiagram diagram = creator.createView(process);
        if (!diagramName.equals(getLabel())) {
            diagram.setName(diagramName);
        }
        diagram.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", diagramDescription);
        return diagram;
    }

    @objid ("22e07d86-318c-421b-843a-eae830c9c128")
    @Override
    public Image getIconImage() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(BpmnProcessDesignDiagram.class));
        } else {
            return null;
        }
        
    }

    @objid ("e4fc1453-12bb-47c9-8510-27d5a6e32265")
    private BpmnProcessDesignDiagram createBpmnProcessDesignDiagram(final IStandardModelFactory modelFactory, final BpmnProcess owner, final String diagramName) {
        BpmnProcessDesignDiagram diagram = modelFactory.createBpmnProcessDesignDiagram();
        diagram.setOrigin(owner);
        if (diagramName.equals(getLabel())) {
            setElementDefaultName(diagram);
        } else {
            diagram.setName(diagramName);
        }
        return diagram;
    }

    /**
     * Create a BPMNProcess under <code>diagramContext</code> if possible.
     * @param modelFactory the model factory
     * @param diagramContext the parent candidate for the process to create
     * @return <code>null</code> if creation was not possible
     */
    @objid ("c22b191c-ba36-4964-8484-fd905846e8e4")
    private BpmnProcess createBpmnProcess(final ModelElement diagramContext) {
        final IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnProcess process = null;
        if (diagramContext instanceof NameSpace) {
            process = modelFactory.createBpmnProcess();
            process.setOwner((NameSpace) diagramContext);
            setElementDefaultName(process);
            populateProcess(modelFactory, process);
        } else if (diagramContext instanceof Operation) {
            process = modelFactory.createBpmnProcess();
            process.setOwnerOperation((Operation) diagramContext);
            setElementDefaultName(process);
            populateProcess(modelFactory, process);
        }
        return process;
    }

    @objid ("3d7b7e0c-c6c1-40e6-a60f-b5c547bddccd")
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
            allowedScopes.add(new ElementScope(metamodel.getMClass(BpmnProcess.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("f5420b6e-9e31-439e-b01f-75dd1f6b0d87")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("45273a10-563a-4613-94fd-6f97418767bb")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
        
    }

    @objid ("d517a978-9654-44c2-b524-20ad6611028d")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("abe5c9d6-7d67-475c-a9ce-893acf2583ef")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, BpmnProcessDesignDiagram.MQNAME);
    }

    @objid ("47d6267b-3f8d-4255-a3ea-1eaa53770afa")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(BpmnProcessDesignDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

    /**
     * Create an initial diagram contents:
     * <ul>
     * <li>A {@link BpmnStartEvent}</li>
     * <li>A {@link BpmnEndEvent}</li>
     * <li>A {@link BpmnTask}</li>
     * <li>A {@link BpmnSequenceFlow} from the start event to the task.</li>
     * <li>A {@link BpmnSequenceFlow} from the task to the end event.</li>
     * </ul>
     */
    @objid ("f4c1d908-58be-4fe2-87b9-966a8c3ebcd9")
    private void populateProcess(IStandardModelFactory modelFactory, BpmnProcess process) {
        IElementNamer namer = this.mmServices.getElementNamer();
        
        // Create a Start event
        BpmnStartEvent startEvent = modelFactory.createBpmnStartEvent();
        startEvent.setContainer(process);
        startEvent.setName(namer.getUniqueName(startEvent));
        
        // Create an End event
        BpmnEndEvent endEvent = modelFactory.createBpmnEndEvent();
        endEvent.setContainer(process);
        endEvent.setName(namer.getUniqueName(endEvent));
        
        // Create a dumb task
        BpmnTask task = modelFactory.createBpmnTask();
        task.setContainer(process);
        task.setName(namer.getUniqueName(task));
        
        // Create a flow between start and task
        BpmnSequenceFlow flow1 = modelFactory.createBpmnSequenceFlow();
        flow1.setSourceRef(startEvent);
        flow1.setTargetRef(task);
        flow1.setContainer(process);
        
        // Create a flow between task and end
        BpmnSequenceFlow flow2 = modelFactory.createBpmnSequenceFlow();
        flow2.setSourceRef(task);
        flow2.setTargetRef(endEvent);
        flow2.setContainer(process);
        
    }

    @objid ("6bbb4a2e-d02d-4b6d-88f1-a7e16ec881c3")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
