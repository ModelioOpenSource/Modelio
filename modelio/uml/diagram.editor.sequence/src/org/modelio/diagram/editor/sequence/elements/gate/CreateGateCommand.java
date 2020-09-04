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

package org.modelio.diagram.editor.sequence.elements.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
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
@objid ("d8f2c01a-55b6-11e2-877f-002564c97630")
public class CreateGateCommand extends Command {
    @objid ("d8f2c026-55b6-11e2-877f-002564c97630")
    private int time;

    @objid ("4fb1756a-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("4fb1756b-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("f7b2453b-7173-46ec-9744-2b45f1ff683b")
    private Object constraint;

    @objid ("c80fa21c-17a0-4117-b083-1d4db51509c8")
    private Interaction parentElement;

    /**
     * Creates a node creation command.
     * 
     * @param parentElement The parent Interaction of the Gate to create
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     * @param time the time of the gate.
     */
    @objid ("d8f2c027-55b6-11e2-877f-002564c97630")
    public CreateGateCommand(Interaction parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint, final int time) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.context = context;
        this.constraint = constraint;
        this.time = time;
    }

    @objid ("d8f2c036-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        Gate newElement = (Gate) this.context.getElementToUnmask();
        
        if (newElement == null) {
            IModelManager modelManager = diagram.getModelManager();
            // Create the Element...
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = modelFactory.createGate();
            // Use the correct dependency to "attach" the gate to the interaction use.
            // TODO: check String effectiveDependency = "FormalGate";
            final MDependency effectiveDependency = newElement.getMClass().getMetamodel().getMExpert()
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
            newElement.setEnclosingInteraction(this.parentElement);
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
    @objid ("d8f2c039-55b6-11e2-877f-002564c97630")
    protected Object getConstraint() {
        return this.constraint;
    }

    /**
     * Get the creation context (parent element, parent dependency, stereotype).
     * 
     * @return the creation context.
     */
    @objid ("d8f2c03e-55b6-11e2-877f-002564c97630")
    protected ModelioCreationContext getContext() {
        return this.context;
    }

    /**
     * Get the parent model element.
     * 
     * @return the parent model element.
     */
    @objid ("d8f2c045-55b6-11e2-877f-002564c97630")
    protected MObject getParentElement() {
        return this.parentElement;
    }

    /**
     * Get the parent graphic node.
     * 
     * @return the parent graphic node.
     */
    @objid ("d8f446bf-55b6-11e2-877f-002564c97630")
    protected GmCompositeNode getParentNode() {
        return this.parentNode;
    }

}
