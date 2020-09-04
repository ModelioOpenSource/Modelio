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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

@objid ("d972eca7-55b6-11e2-877f-002564c97630")
class CreatePartDecompositionCommand extends Command {
    @objid ("4fc23e4b-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("4fc23e4c-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("de466347-a399-46b2-a796-f9462df39e95")
    private Object constraint;

    @objid ("8fbef5b0-72d1-4f7c-bed4-f646f2e2f8bc")
    private Interaction interaction;

    /**
     * Creates a node creation command.
     * @param interaction The parent MObject of the MObject to create
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("d972ecb2-55b6-11e2-877f-002564c97630")
    public CreatePartDecompositionCommand(final Interaction interaction, final GmCompositeNode parentNode, final ModelioCreationContext context, final Object constraint) {
        this.parentNode = parentNode;
        this.interaction = interaction;
        this.context = context;
        this.constraint = constraint;
    }

    @objid ("d972ecc3-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        // get ModelFactory
        final IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the Lifeline...
        Lifeline lifeline = modelFactory.createLifeline();
        
        // ... and attach it to the IInteraction.
        this.interaction.getOwnedLine().add(lifeline);
        
        // Now create the actual IPartDecomposition and attach it to the lifeline
        PartDecomposition partDecomposition = modelFactory.createPartDecomposition();
        lifeline.setDecomposedAs(partDecomposition);
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            partDecomposition.getExtension().add(this.context.getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(lifeline, this.context.getProperties());
        elementConfigurer.configure(partDecomposition, this.context.getProperties());
        
        // Show the new lifeline in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, lifeline, this.constraint);
    }

    @objid ("d972ecc6-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.parentNode.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        // If it is an actual creation (and not a simple unmasking).
        if (this.context.getElementToUnmask() == null) {
            // The parent element must be modifiable or
            // both must be CMS nodes.
            if (!MTools.getAuthTool().canAdd(this.interaction, Lifeline.MQNAME)) {
                return false;
            }
        
            // Ask metamodel experts
            MMetamodel mm = gmDiagram.getModelManager().getMetamodel();
            return mm.getMExpert().canCompose(this.interaction, mm.getMClass(Lifeline.class), null);
        
        }
        return true;
    }

}
