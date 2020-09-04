/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("d911449a-55b6-11e2-877f-002564c97630")
public class CreateGateOnInteractionUseCommand extends Command {
    @objid ("d91144a6-55b6-11e2-877f-002564c97630")
    private int time;

    @objid ("50287a2a-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("50287a2b-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("5701cfcb-b056-460a-af52-56e4929623a9")
    private Object constraint;

    @objid ("1936f6d8-8e22-4843-8b2d-846be43ef7c3")
    private InteractionUse parentElement;

    /**
     * Creates a node creation command.
     * 
     * @param parentElement The parent InteractionUse of the Gate to create
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     * @param time the time of the gate.
     */
    @objid ("d91144a7-55b6-11e2-877f-002564c97630")
    public CreateGateOnInteractionUseCommand(InteractionUse parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint, final int time) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.context = context;
        this.constraint = constraint;
        this.time = time;
    }

    @objid ("d91144b6-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        Gate newElement = (Gate) this.context.getElementToUnmask();
        
        if (newElement == null) {
            // Create the Element...
            IModelManager modelManager = diagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = modelFactory.createGate();
        
            final MDependency effectiveDependency = modelManager.getMetamodel().getMExpert()
                    .getDefaultCompositionDep(this.parentElement, newElement);
        
            if (effectiveDependency == null) {
                StringBuilder msg = new StringBuilder();
                msg.append("Cannot find a composition dependency to attach ");
                msg.append(newElement.toString());
                msg.append(" to ");
                msg.append(this.parentElement.toString());
                throw new IllegalStateException(msg.toString());
            }
        
            this.parentElement.mGet(effectiveDependency).add(newElement);
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
            }
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            elementConfigurer.configure(newElement, getContext().getProperties());
        
            // Specific steps:
            newElement.setEnclosingInteraction(this.parentElement.getEnclosingInteraction());
            newElement.setLineNumber(this.time);
        
        }
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newElement, this.constraint);
    }

    /**
     * Get the initial layout constraint.
     * 
     * @return the initial layout constraint.
     */
    @objid ("d91144b9-55b6-11e2-877f-002564c97630")
    protected Object getConstraint() {
        return this.constraint;
    }

    /**
     * Get the creation context (parent element, parent dependency, stereotype).
     * 
     * @return the creation context.
     */
    @objid ("d91144be-55b6-11e2-877f-002564c97630")
    protected ModelioCreationContext getContext() {
        return this.context;
    }

    /**
     * Get the parent model element.
     * 
     * @return the parent model element.
     */
    @objid ("d91144c5-55b6-11e2-877f-002564c97630")
    protected MObject getParentElement() {
        return this.parentElement;
    }

    /**
     * Get the parent graphic node.
     * 
     * @return the parent graphic node.
     */
    @objid ("d91144cc-55b6-11e2-877f-002564c97630")
    protected GmCompositeNode getParentNode() {
        return this.parentNode;
    }

}
