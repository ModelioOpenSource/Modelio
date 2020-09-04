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

package org.modelio.diagram.editor.sequence.contributor;

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
import org.modelio.diagram.editor.sequence.plugin.DiagramEditorSequence;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
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
 * Creation contributor for Sequence diagrams.
 */
@objid ("e5024b84-27e2-4e4a-9bac-c1e6d91383b2")
public class SequenceDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("1e8c3c35-b1a4-4c7c-a9c4-bb82c13816a0")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("7d6d59b5-03bf-4790-b567-38a4e85f1a05")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        SequenceDiagram diagram = null;
        
        // Unless the parent element is already an Interaction, create the Interaction:
        Interaction interaction = null;
        if (diagramContext instanceof Interaction) {
            interaction = (Interaction) diagramContext;
            diagram = smartCreateForNameSpace(modelFactory, interaction, diagramName);
        } else {
            interaction = modelFactory.createInteraction();
            // Create the diagram, depending on parentElement, carry out the "smart" creation job
            if ((diagramContext instanceof Classifier) && !(diagramContext instanceof UseCase)) {
                interaction.setOwner((Classifier) diagramContext);
                setElementDefaultName(interaction);
                diagram = smartCreateForClassifier(modelFactory, interaction, (Classifier) diagramContext, diagramName);
            } else if (diagramContext instanceof Operation) {
                interaction.setOwnerOperation((Operation) diagramContext);
                setElementDefaultName(interaction);
                diagram = smartCreateForOperation(modelFactory, interaction, (Operation) diagramContext, diagramName);
            } else {
                interaction.setOwner((NameSpace) diagramContext);
                setElementDefaultName(interaction);
                diagram = smartCreateForNameSpace(modelFactory, interaction, diagramName);
            }
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

    @objid ("e3370d99-2554-48bf-8e25-71ff1b8d5669")
    @Override
    public String getDetails() {
        return DiagramEditorSequence.I18N.getString("CreationWizard.Sequence.Details");
    }

    @objid ("50f86233-4b73-4334-8bf3-33c5d6764f64")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(SequenceDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("a6f44df1-3ee8-4709-9117-2550730bb040")
    @Override
    public String getInformation() {
        return DiagramEditorSequence.I18N.getString("CreationWizard.Sequence.Information");
    }

    @objid ("3942ebb7-5323-4fbe-9c2b-8867413b8102")
    @Override
    public String getLabel() {
        return DiagramEditorSequence.I18N.getString("CreationWizard.Sequence.Name");
    }

    @objid ("6bab8f96-4257-41d6-ab13-7c5dbc9514c3")
    private Collaboration checkLocalCollaboration(final IStandardModelFactory modelFactory, final Interaction interaction) {
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

    @objid ("af372e0d-3878-4260-82d6-88bf89ef04ce")
    private SequenceDiagram createSequenceDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        SequenceDiagram diagram = modelFactory.createSequenceDiagram();
        diagram.setName(diagramName);
        diagram.setOrigin(diagramContext);
        return diagram;
    }

    @objid ("465d548e-be4c-4def-b9d8-c75bbe8303e2")
    private SequenceDiagram smartCreateForClassifier(final IStandardModelFactory modelFactory, final Interaction interaction, final Classifier parentClassifier, final String diagramName) {
        // Update model with effective context
        Interaction diagramContext = interaction;
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, diagramName, diagramContext);
        if (diagram != null) {
            // Create the locals Collaboration
            Collaboration locals = checkLocalCollaboration(modelFactory, interaction);
            if (locals != null) {
                // Create this pointer, create the instance:
                BindableInstance instance = modelFactory.createBindableInstance();
                if (instance != null) {
                    locals.getDeclared().add(instance);
                    instance.setName("this");
                    instance.setBase(parentClassifier);
        
                    // Create the corresponding InstanceNode:
                    Lifeline lifeline = modelFactory.createLifeline();
                    if (lifeline != null) {
                        interaction.getOwnedLine().add(lifeline);
                        lifeline.setName("this");
                        lifeline.setRepresented(instance);
                    }
                }
            }
        }
        return diagram;
    }

    @objid ("3857bce1-4ec0-4d24-a96d-cfc900dd5b70")
    private SequenceDiagram smartCreateForNameSpace(final IStandardModelFactory modelFactory, final Interaction interaction, final String diagramName) {
        // Update model with effective context
        Interaction diagramContext = interaction;
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, diagramName, diagramContext);
        if (diagram != null) {
            // Create the locals Collaboration:
            checkLocalCollaboration(modelFactory, interaction);
        }
        return diagram;
    }

    @objid ("53bd7024-c12a-4860-98d2-8ae30276dbad")
    private SequenceDiagram smartCreateForOperation(final IStandardModelFactory modelFactory, final Interaction interaction, final Operation parentOperation, final String diagramName) {
        // Update model with effective context
        Interaction diagramContext = interaction;
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, diagramName, diagramContext);
        if (diagram != null) {
        
            // Create the locals Collaboration:
            Collaboration locals = checkLocalCollaboration(modelFactory, interaction);
            if (locals != null) {
                // Create the 'this' instance:
                BindableInstance instance = modelFactory.createBindableInstance();
        
                if (instance != null) {
                    locals.getDeclared().add(instance);
                    instance.setName("this");
                    instance.setBase(parentOperation.getOwner());
        
                    // Create the corresponding InstanceNode:
                    Lifeline lifeline = modelFactory.createLifeline();
                    if (lifeline != null) {
                        interaction.getOwnedLine().add(lifeline);
                        lifeline.setName("this");
                        lifeline.setRepresented(instance);
                    }
                }
            }
        }
        return diagram;
    }

    @objid ("77ff93e3-6ecc-47cd-804a-d640b982c7fc")
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
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interaction.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("6b5088c7-c88d-44a3-ae14-f1b3f232a572")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorSequence.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/sequencediagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("b80beecb-da7a-4b51-b6b2-3bee105c04f9")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("0d758f29-8747-498c-a335-dd033f7d825b")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("682b34f1-935b-464e-b845-622394dad0c3")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("b151155b-00c1-4d5c-abca-0bb19d484163")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("5406bf1d-0df5-479d-ab4a-23f9b850ddea")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, SequenceDiagram.MQNAME);
    }

    @objid ("a9d03917-024c-4a5a-9e37-ff0b64986772")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(SequenceDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
