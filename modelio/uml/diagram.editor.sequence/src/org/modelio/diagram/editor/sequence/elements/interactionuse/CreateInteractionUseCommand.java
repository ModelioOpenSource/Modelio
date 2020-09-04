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

package org.modelio.diagram.editor.sequence.elements.interactionuse;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.editor.sequence.elements.lifeline.GmLifeline;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MDependency;

/**
 * Creation command specific to InteractionUse. It handles the covered lifelines.
 * 
 * @author fpoyer
 */
@objid ("d90e3765-55b6-11e2-877f-002564c97630")
public class CreateInteractionUseCommand extends Command {
    @objid ("d90fbdfe-55b6-11e2-877f-002564c97630")
    private static final int DEFAULT_SIZE = 70;

    @objid ("d90fbdfa-55b6-11e2-877f-002564c97630")
    private List<GmLifeline> coveredLifelines;

    @objid ("d90fbdfd-55b6-11e2-877f-002564c97630")
    private GmSequenceDiagram diagram;

    @objid ("50287a29-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("28eb5bfa-bda7-45c7-b4e4-dcd2d75d15bb")
    private Rectangle constraint;

    /**
     * Creates a node creation command.
     * @param diagram The sequence diagram in which the interaction use is to be created.
     * @param context Details on the MObject and/or the node to create.
     * @param constraint The initial constraint of the created node.
     * @param coveredLifelines The list of lifelines covered at creation time. May be empty but not <code>null</code>!
     */
    @objid ("d90fbe00-55b6-11e2-877f-002564c97630")
    public CreateInteractionUseCommand(final GmSequenceDiagram diagram, final ModelioCreationContext context, final Rectangle constraint, final List<GmLifeline> coveredLifelines) {
        this.diagram = diagram;
        this.context = context;
        this.constraint = constraint;
        this.coveredLifelines = coveredLifelines;
    }

    @objid ("d90fbe0f-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        InteractionUse newElement = (InteractionUse) this.context.getElementToUnmask();
        
        if (newElement == null) {
            ModelElement parentElement = ((AbstractDiagram) this.diagram.getRelatedElement()).getOrigin();
            // Create the Element...
            IModelManager modelManager = this.diagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = (InteractionUse) modelFactory.createElement(this.context.getMetaclass());
        
            // The new element must be attached to its parent using the composition dependency
            // provided by the context.
            // If the context provides a null dependency, use the default dependency recommended by the metamodel
        
            final MDependency effectiveDependency = modelManager.getMetamodel().getMExpert()
                    .getDefaultCompositionDep(parentElement, newElement);
            if (effectiveDependency == null) {
                StringBuilder msg = new StringBuilder();
                msg.append("Cannot find a composition dependency to attach ");
                msg.append(newElement.toString());
                msg.append(" to ");
                msg.append(parentElement.toString());
                throw new IllegalStateException(msg.toString());
            }
        
            parentElement.mGet(effectiveDependency).add(newElement);
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
            }
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            elementConfigurer.configure(newElement, this.context.getProperties());
        
            // Set default name
            newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
            // Now add the covered lifelines
            for (GmLifeline gmLifeline : this.coveredLifelines) {
                Lifeline lifeline = gmLifeline.getRelatedElement();
                if (lifeline != null && lifeline.isValid()) {
                    newElement.getCovered().add(lifeline);
                }
            }
        
            // And set the line numbers
            newElement.setLineNumber(this.constraint.y);
            if (this.constraint.bottom() > this.constraint.y) {
                newElement.setEndLineNumber(this.constraint.bottom());
            } else {
                newElement.setEndLineNumber(this.constraint.y + CreateInteractionUseCommand.DEFAULT_SIZE);
            }
        
        }
        
        // Show the new element in the diagram (ie create its Gm )
        this.diagram.unmask(this.diagram, newElement, this.constraint);
    }

}
