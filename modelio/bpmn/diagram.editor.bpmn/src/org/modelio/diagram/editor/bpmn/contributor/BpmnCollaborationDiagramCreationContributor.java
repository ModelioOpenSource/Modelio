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

package org.modelio.diagram.editor.bpmn.contributor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.bpmn.editor.BpmnGmDiagramCreator;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for Bpmn Collaboration diagrams.
 */
@objid ("0886a6cd-6515-4fa1-a72b-ac54a5168ae7")
public class BpmnCollaborationDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("c1e18e6b-7a58-441c-bb5f-39d6c59416fb")
    @Inject
    @Optional
    private IMModelServices mmServices;

    @objid ("97488104-3a83-468a-a3a3-897a1aff4528")
    @Inject
    @Optional
    private IModuleContext moduleContext;

    @objid ("15e9901f-1655-4f97-9006-5fccf00b38b2")
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("7c8360e4-5168-4b1a-b2d8-f89d34965e8e")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        final IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnCollaborationDiagram diagram = null;
        
        final BpmnCollaboration collaboration;
        
        if (diagramContext instanceof BpmnCollaboration) {
            collaboration = (BpmnCollaboration) diagramContext;
        } else {
            collaboration = modelFactory.createBpmnCollaboration();
            if (diagramContext instanceof NameSpace) {
                collaboration.setOwner((NameSpace) diagramContext);
            } else if (diagramContext instanceof Operation) {
                collaboration.setOwnerOperation((Operation) diagramContext);
            } else if (diagramContext instanceof BpmnProcess) {
                collaboration.setDefinedProcess((BpmnProcess) diagramContext);
            }
            setElementDefaultName(collaboration);
        }
        
        diagram = createBpmnCollaborationDiagram(modelFactory, collaboration, diagramName);
        
        if (diagram != null) {
            putNoteContent(diagram, diagramDescription);
        
            if (diagramContext instanceof BpmnProcess) {
                BpmnProcess process = (BpmnProcess) diagramContext;
        
                // Create a participant
                BpmnParticipant participant = modelFactory.createBpmnParticipant();
                participant.setName(process.getName());
                participant.setProcess(process);
                participant.setContainer(collaboration);
        
                // Unmask it
                IGmDiagram input = new BpmnGmDiagramCreator().createDiagram(new ModelManager(this.eclipseContext), diagram);
                if (input != null) {
                    // Make the diagram visible at GM level.
                    input.setVisible(true);
        
                    // Load from the persistence.
                    input.getPersister().load();
        
                    input.unmaskAsChild(participant, null);
        
                    input.getPersister().save(true);
                    input.dispose();
                }
            }
        }
        return diagram;
    }

    @objid ("1a983dac-2c3e-4216-84d0-879b54b470c9")
    @Override
    public String getDetails() {
        return DiagramEditorBpmn.I18N.getString("Contributor.BpmnCollaborationDiagram.Details");
    }

    @objid ("302fd5e5-deb2-496e-972d-3a7cecf1996f")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(BpmnCollaborationDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("bd2c5f75-ee2a-49cb-a05e-7a201266c2b6")
    @Override
    public String getInformation() {
        return DiagramEditorBpmn.I18N.getString("Contributor.BpmnCollaborationDiagram.Information");
    }

    @objid ("48bcd774-e841-4c84-bdfe-fde9537aae9d")
    @Override
    public String getLabel() {
        return DiagramEditorBpmn.I18N.getString("Contributor.BpmnCollaborationDiagram.Name");
    }

    @objid ("5bc66617-6536-4389-9a66-a22ce2dc13db")
    private BpmnCollaborationDiagram createBpmnCollaborationDiagram(final IStandardModelFactory modelFactory, final BpmnCollaboration collaboration, final String diagramName) {
        BpmnCollaborationDiagram diagram = modelFactory.createBpmnCollaborationDiagram();
        diagram.setOrigin(collaboration);
        if (diagramName.equals(getLabel())) {
            setElementDefaultName(diagram);
        } else {
            diagram.setName(diagramName);
        }
        return diagram;
    }

    @objid ("d692792a-5c24-4354-b6e3-b28e89b3b709")
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
            allowedScopes.add(new ElementScope(metamodel.getMClass(BpmnCollaboration.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("64b1ccb6-f2f9-45a2-a5b4-e7b98111eb03")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorBpmn.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/bpmncollaborationdiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("530bb99f-1004-468a-b570-f626078f079e")
    protected final void putNoteContent(ModelElement element, String content) {
        try {
            element.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("b5f9412d-7fa5-45d9-9697-15bdd8b7a17d")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("3723c393-b1da-46fc-9c2c-b9e1526a505a")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("026b0ec2-0b00-41e4-8c59-ce928c3cae0b")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("da5ab396-79b9-4cb3-8f8a-42cfdadc7ea0")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        if (!MTools.getAuthTool().canAdd(owner, BpmnCollaborationDiagram.MQNAME)) {
            return false;
        }
        if (owner instanceof BpmnProcess) {
            // Only one definitional BpmnCollaboration is allowed under a BpmnProcess
            BpmnProcess proc = (BpmnProcess) owner;
            return proc.getDefinitionalCollaboration() == null;
        }
        return true;
    }

    @objid ("6636e197-7af9-4c56-ac03-79d3a2326ebe")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(BpmnCollaborationDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
