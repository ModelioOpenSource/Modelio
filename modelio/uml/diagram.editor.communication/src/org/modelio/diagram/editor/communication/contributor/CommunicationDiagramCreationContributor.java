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

package org.modelio.diagram.editor.communication.contributor;

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
import org.modelio.diagram.editor.communication.plugin.DiagramEditorCommunication;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
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
 * Creation contributor for Communication diagrams.
 */
@objid ("ec6aac3b-c346-4034-862d-103eaffc4942")
public class CommunicationDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("98114443-4f84-48b1-9867-8b9854b87a9b")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("1e2da3b1-fa12-4035-b1fb-680327d14f75")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        CommunicationDiagram diagram = null;
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        // Unless the parent element is already a CommunicationInteraction, create the CommunicationInteraction:
        CommunicationInteraction interaction = null;
        Collaboration locals = null;
        if (diagramContext instanceof CommunicationInteraction) {
            interaction = (CommunicationInteraction) diagramContext;
            if (interaction.getOwnedCollaboration().size() > 0) {
                locals = interaction.getOwnedCollaboration().get(0);
            } else {
                checkLocalCollaboration(modelFactory, interaction);
            }
        } else if (diagramContext instanceof Operation) {
            // create a CommunicationInteraction and its 'locals' collaboration
            interaction = modelFactory.createCommunicationInteraction();
            locals = checkLocalCollaboration(modelFactory, interaction);
            interaction.setOwnerOperation((Operation) diagramContext);
            setElementDefaultName(interaction);
        } else {
            // create a CommunicationInteraction and its 'locals' collaboration
            interaction = modelFactory.createCommunicationInteraction();
            locals = checkLocalCollaboration(modelFactory, interaction);
            interaction.setOwner((NameSpace) diagramContext);
            setElementDefaultName(interaction);
        }
        
        // Create the diagram, depending on parentElement, carry out the "smart" creation job
        if ((diagramContext instanceof Classifier) && !(diagramContext instanceof UseCase)) {
            diagram = smartCreateForClassifier(modelFactory, interaction, locals, (Classifier) diagramContext, diagramName);
        } else if (diagramContext instanceof Operation) {
            diagram = smartCreateForOperation(modelFactory, interaction, locals, (Operation) diagramContext, diagramName);
        } else {
            diagram = smartCreateForNameSpace(modelFactory, interaction, diagramName);
        }
        
        if (diagram != null) {
            if (diagramName.equals(getLabel())) {
                setElementDefaultName(diagram);
            } else {
                diagram.setName(diagramName);
            }
            putNoteContent(diagram, "description", diagramDescription);
        }
        return diagram;
    }

    @objid ("3732317d-09f0-4543-8b21-53e5256fa5f2")
    @Override
    public String getDetails() {
        return DiagramEditorCommunication.I18N.getString("CreationWizard.Communication.Details");
    }

    @objid ("8912795d-02d1-4ef4-a24e-7cd80bc23796")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(CommunicationDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("36fde341-81e9-4d17-b21a-ec2881896f96")
    @Override
    public String getInformation() {
        return DiagramEditorCommunication.I18N.getString("CreationWizard.Communication.Information");
    }

    @objid ("a9f72cb6-0c85-4dfd-a99e-048a090c1f6e")
    @Override
    public String getLabel() {
        return DiagramEditorCommunication.I18N.getString("CreationWizard.Communication.Name");
    }

    @objid ("b24b7556-09bb-4983-a495-8883f15f100b")
    private Collaboration checkLocalCollaboration(final IStandardModelFactory modelFactory, final CommunicationInteraction interaction) {
        Collaboration locals = null;
        // Look for an existing local Collaboration
        for (Collaboration collab : interaction.getOwnedCollaboration()) {
            locals = collab;
            break;
        }
        
        if (locals == null) {
            // Create the local Collaboration
            locals = modelFactory.createCollaboration();
            interaction.getOwnedCollaboration().add(locals);
            locals.setName("locals");
        }
        return locals;
    }

    @objid ("dfd1d1ff-98b9-47b7-bf05-e7dfbeb642d2")
    private CommunicationDiagram createCommunicationDiagram(IStandardModelFactory modelFactory, final CommunicationInteraction diagramContext, final String diagramName) {
        // Create the Communication diagram
        CommunicationDiagram diagram = modelFactory.createCommunicationDiagram(diagramName, diagramContext, null);
        diagramContext.getProduct().add(diagram);
        return diagram;
    }

    /**
     * Creating a Communication diagram under a classifier will:
     * <ul>
     * <li>create a 'locals' collaboration under the classifier</li>
     * </ul>
     * @param locals
     * @param diagramName
     */
    @objid ("78bd2121-f188-4725-8083-884b1783098e")
    private CommunicationDiagram smartCreateForClassifier(final IStandardModelFactory modelFactory, final CommunicationInteraction interaction, final Collaboration locals, final Classifier parentClassifier, final String diagramName) {
        // Update model with effective context
        CommunicationInteraction diagramContext = interaction;
        
        // create the communication diagram
        CommunicationDiagram diagram = createCommunicationDiagram(modelFactory, diagramContext, diagramName);
        if (diagram != null) {
            // Create this pointer, create the instance:
            BindableInstance instance = modelFactory.createBindableInstance();
            if (instance != null) {
                locals.getDeclared().add(instance);
                instance.setName("this");
                instance.setBase(parentClassifier);
        
                // Create the corresponding CommunicationNode:
                CommunicationNode node = modelFactory.createCommunicationNode();
                if (node != null) {
                    interaction.getOwned().add(node);
                    node.setName("this");
                    node.setRepresented(instance);
                }
            }
        }
        return diagram;
    }

    @objid ("0d172706-a65a-4ca6-a8a5-dc3ae79a1b90")
    private CommunicationDiagram smartCreateForNameSpace(final IStandardModelFactory modelFactory, final CommunicationInteraction interaction, final String diagramName) {
        // Update model with effective context
        CommunicationInteraction diagramContext = interaction;
        
        // create the communication diagram
        return createCommunicationDiagram(modelFactory, diagramContext, diagramName);
    }

    @objid ("61c2c4e7-3138-4a39-8539-2c47c875d31e")
    private CommunicationDiagram smartCreateForOperation(final IStandardModelFactory modelFactory, final CommunicationInteraction interaction, final Collaboration locals, final Operation parentOperation, final String diagramName) {
        // Update model with effective context
        CommunicationInteraction diagramContext = interaction;
        
        // create the communication diagram
        CommunicationDiagram diagram = createCommunicationDiagram(modelFactory, diagramContext, diagramName);
        if (diagram != null) {
        
            // Create the 'this' instance:
            BindableInstance instance = modelFactory.createBindableInstance();
        
            if (instance != null) {
                locals.getDeclared().add(instance);
                instance.setName("this");
                instance.setBase(parentOperation.getOwner());
        
                // Create the corresponding CommunicationNode:
                CommunicationNode node = modelFactory.createCommunicationNode();
                if (node != null) {
                    interaction.getOwned().add(node);
                    node.setName("this");
                    node.setRepresented(instance);
                }
            }
        }
        return diagram;
    }

    @objid ("cb0ed8cd-2e4f-4a60-826a-b5a25941d00c")
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
            allowedScopes.add(new ElementScope(metamodel.getMClass(CommunicationInteraction.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("3ed464a7-aa1a-4f3f-bb06-b070ad61a936")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorCommunication.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/communicationdiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("0029929f-a81b-48d6-b561-00437e4403c6")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("5b9b8fcd-c4c9-4b86-a3bf-51ecda4e26f5")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("7da856cd-ee9e-49ca-bea1-ed893954529a")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("403d5d9d-d82b-4de6-9646-45ce23518cd5")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("6aa8160b-6747-4012-9be1-76762d2306bc")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, CommunicationDiagram.MQNAME);
    }

    @objid ("468f9450-227c-4fa3-8c6c-0a0b1674623f")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(CommunicationDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
