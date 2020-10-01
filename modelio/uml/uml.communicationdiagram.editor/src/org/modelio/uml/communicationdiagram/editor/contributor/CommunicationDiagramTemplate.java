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

package org.modelio.uml.communicationdiagram.editor.contributor;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;

@objid ("7d6db804-a1f6-4c89-934d-53e018f61173")
public class CommunicationDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("d9156bcf-3859-46e8-9a6c-9151c04078f6")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("69e8342a-6ed2-468a-92cb-c81bcfba1e61")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("8f59fc54-ad07-40ca-b77e-f45ed8764b32")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        CommunicationDiagram diagram = null;
        
        if (base instanceof CommunicationInteraction) {
            diagram = smartCreateForCommunicationInteraction(modelFactory, (CommunicationInteraction) base);
        } else if (base instanceof Operation) {
            diagram = smartCreateForOperation(modelFactory, (Operation) base);
        } else if (base instanceof Classifier && !(base instanceof UseCase)) {
            diagram = smartCreateForClassifier(modelFactory, (Classifier) base);
        } else if (base instanceof NameSpace) {
            diagram = smartCreateForNameSpace(modelFactory, (NameSpace) base);
        }
        return diagram;
    }

    /**
     * Create a Communication diagram under 'interaction' and sets its default name.
     * 
     * @return the created Communication diagram or null in case of problems.
     */
    @objid ("9b0b5ed4-b556-4ccc-b533-1dd92babc462")
    private CommunicationDiagram createCommunicationDiagram(IStandardModelFactory modelFactory, final CommunicationInteraction interaction) {
        // Create the Communication diagram
        CommunicationDiagram diagram = modelFactory.createCommunicationDiagram();
        interaction.getProduct().add(diagram);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("1a78a7e1-b329-463a-8065-e38cabdc0995")
    private CommunicationDiagram smartCreateForCommunicationInteraction(IStandardModelFactory modelFactory, CommunicationInteraction parentInteraction) {
        ensureLocalCollaboration(modelFactory, parentInteraction);
        return createCommunicationDiagram(modelFactory, parentInteraction);
    }

    @objid ("9e5aa144-c6aa-4ca9-bc54-6b134c341d01")
    private CommunicationDiagram smartCreateForOperation(IStandardModelFactory modelFactory, Operation parentOperation) {
        CommunicationInteraction interaction = modelFactory.createCommunicationInteraction();
        interaction.setOwnerOperation(parentOperation);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // Ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // Create the communication diagram
        CommunicationDiagram diagram = createCommunicationDiagram(modelFactory, interaction);
        
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
        return diagram;
    }

    @objid ("6944ee8e-77c0-4bca-a5b0-f5dbcf1945b6")
    private CommunicationDiagram smartCreateForClassifier(IStandardModelFactory modelFactory, Classifier parentClassifier) {
        CommunicationInteraction interaction = modelFactory.createCommunicationInteraction();
        interaction.setOwner(parentClassifier);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // Ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // Create the communication diagram
        CommunicationDiagram diagram = createCommunicationDiagram(modelFactory, interaction);
        
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
        return diagram;
    }

    @objid ("847e6142-8166-4934-9183-c23ba7b31de3")
    private CommunicationDiagram smartCreateForNameSpace(IStandardModelFactory modelFactory, NameSpace parent) {
        CommunicationInteraction interaction = modelFactory.createCommunicationInteraction();
        interaction.setOwner(parent);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // Ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // Create the communication diagram
        CommunicationDiagram diagram = createCommunicationDiagram(modelFactory, interaction);
        return diagram;
    }

    @objid ("6d4e3ce6-2d39-43a4-8fce-4481abc8a01e")
    @Override
    public AbstractDiagram getExistingView(ModelElement main) {
        // Not supported concept
        return null;
    }

    @objid ("829fbe79-e54a-456f-ab08-cc17c1eda45c")
    @Override
    public void updateView(AbstractDiagram existingDiagram) {
        // Not supported concept
    }

    @objid ("174ee548-2db9-4af8-a451-785a5bf53e42")
    @Override
    public ModelElement resolveOrigin(ModelElement main) {
        return main;
    }

    @objid ("371a1fe3-8a43-4af6-8bea-55e2f8b2da85")
    @Override
    public ModelElement getMainElement(AbstractDiagram diagram) {
        return diagram.getOrigin();
    }

    @objid ("06d95807-3424-47af-9991-58657c4f24f2")
    private Collaboration ensureLocalCollaboration(final IStandardModelFactory modelFactory, final CommunicationInteraction interaction) {
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

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("fc50f9e1-b49f-4d00-957e-84271987c998")
    public CommunicationDiagramTemplate() {
        super();
    }

}
