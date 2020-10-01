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

package org.modelio.uml.sequencediagram.editor.contributor;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;

@objid ("76e553df-a941-4947-a4a0-de0fa93a896c")
public class SequenceDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("18b4d8e5-164e-4438-a10f-680a25b8f6a8")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("533582a7-b421-49dd-85af-ee959697d4ad")
    public SequenceDiagramTemplate() {
        super();
    }

    @objid ("a4c78bcd-3494-485c-8f39-50321d5581ba")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("25450166-c2dd-4c0c-8a6d-948d5b8b3fdf")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        AbstractDiagram diagram = null;
        
        if (base instanceof Interaction) {
            diagram = smartCreateForInteraction(modelFactory, (Interaction) base);
        } else if ((base instanceof Classifier) && !(base instanceof UseCase)) {
            diagram = smartCreateForClassifier(modelFactory, (Classifier) base);
        } else if (base instanceof Operation) {
            diagram = smartCreateForOperation(modelFactory, (Operation) base);
        } else if (base instanceof NameSpace) {
            diagram = smartCreateForNameSpace(modelFactory, (NameSpace) base);
        }
        return diagram;
    }

    @objid ("c644e148-dbe7-4d63-bc4d-c776d283026c")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("cdf1878b-6309-47b0-973c-11898268d7c9")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("49c635fa-847d-448e-ba65-68c091317480")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("f848795f-797e-4c11-a9bc-13f9e18f8198")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    @objid ("669167ba-6a57-4ea3-b82e-31b120f900f4")
    private AbstractDiagram smartCreateForInteraction(IStandardModelFactory modelFactory, Interaction base) {
        ensureLocalCollaboration(modelFactory, base);
        return createSequenceDiagram(modelFactory, base);
    }

    @objid ("68d8046c-5f55-49d5-b1fb-609c914599fe")
    private AbstractDiagram smartCreateForClassifier(IStandardModelFactory modelFactory, Classifier parentClassifier) {
        Interaction interaction = modelFactory.createInteraction();
        interaction.setOwner(parentClassifier);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, interaction);
        
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
        return diagram;
    }

    @objid ("ccf835e8-c3b9-4b5c-8683-436bbfc9bb27")
    private AbstractDiagram smartCreateForOperation(IStandardModelFactory modelFactory, Operation parentOperation) {
        Interaction interaction = modelFactory.createInteraction();
        interaction.setOwnerOperation(parentOperation);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, interaction);
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
        return diagram;
    }

    @objid ("30fe66ee-9bef-414a-816b-79b3e186ddc2")
    private AbstractDiagram smartCreateForNameSpace(IStandardModelFactory modelFactory, NameSpace parentNameSpace) {
        Interaction interaction = modelFactory.createInteraction();
        interaction.setOwner(parentNameSpace);
        interaction.setName(this.mmServices.getElementNamer().getUniqueName(interaction));
        
        // ensure 'locals' Collaboration
        Collaboration locals = ensureLocalCollaboration(modelFactory, interaction);
        
        // create the sequence diagram
        SequenceDiagram diagram = createSequenceDiagram(modelFactory, interaction);
        return diagram;
    }

    @objid ("0c7dd2b5-b42b-4c5e-bd11-20f60e0eefd7")
    private SequenceDiagram createSequenceDiagram(final IStandardModelFactory modelFactory, Interaction interaction) {
        SequenceDiagram diagram = modelFactory.createSequenceDiagram();
        diagram.setOrigin(interaction);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("c7f6967f-bb54-4271-936e-c2892b8d903b")
    private Collaboration ensureLocalCollaboration(final IStandardModelFactory modelFactory, final Interaction interaction) {
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

}
