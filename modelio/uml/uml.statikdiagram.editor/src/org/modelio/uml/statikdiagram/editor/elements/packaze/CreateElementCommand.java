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

package org.modelio.uml.statikdiagram.editor.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.GmCompositionLink;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("36175aec-55b7-11e2-877f-002564c97630")
public class CreateElementCommand extends Command {
    @objid ("36175af2-55b7-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("36175af1-55b7-11e2-877f-002564c97630")
    private Object constraint;

    @objid ("a72d16aa-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("a72d16ab-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    /**
     * Creates a node creation command.
     * 
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("36175af8-55b7-11e2-877f-002564c97630")
    public CreateElementCommand(GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        this.parentNode = parentNode;
        this.parentElement = parentNode.getRelatedElement();
        this.context = context;
        this.constraint = constraint;
    }

    /**
     * Creates a node creation command.
     * 
     * @param parentElement The parent MObject of the MObject to create
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("36175b02-55b7-11e2-877f-002564c97630")
    public CreateElementCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.context = context;
        this.constraint = constraint;
    }

    @objid ("36175b0f-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        MObject newElement = this.context.getElementToUnmask();
        
        if (newElement == null) {
            IModelManager modelManager = diagram.getModelManager();
            // Create the MObject...
            final IModelFactory modelFactory = modelManager.getModelFactory();
            MClass mclass = this.context.getMetaclass();
            newElement = modelFactory.createElement(mclass);
            MExpert mExpert = mclass.getMetamodel().getMExpert();
        
            // The new element must be attached to its parent using the composition dependency
            // provided by the context.
            // If the context provides a null dependency, use the default dependency recommended by the metamodel
            MDependency effectiveDependency = this.context.getDependency();
            if (effectiveDependency == null) {
                effectiveDependency = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
            }
        
            // ... and attach it to its parent.
            try {
                this.parentElement.mGet(effectiveDependency).add(newElement);
            } catch (@SuppressWarnings("unused") Exception e) {
                // The dependency indicated in the context cannot be used: try
                // to find a valid one!
                MDependency compositionDep = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
                if (compositionDep != null) {
                    this.parentElement.mGet(compositionDep).add(newElement);
                } else {
                    newElement.delete();
                    return;
                }
            }
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null && newElement instanceof ModelElement) {
                ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
            }
        
            // Set default name
            newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            elementConfigurer.configure(newElement, this.context.getProperties());
        }
        
        // Show the new element in the diagram (ie create its Gm )
        GmCompositeNode actualParentNode = this.parentNode.getParentNode();
        GmNodeModel gmModel = diagram.unmask(actualParentNode, newElement, this.constraint);
        
        // Remove child and add it again, so that its current role is correctly used.
        actualParentNode.removeChild(gmModel);
        gmModel.setRoleInComposition(GmPackage.BODY_CONTENT_AS_SATELLITE);
        gmModel.setLayoutData(this.constraint);
        actualParentNode.addChild(gmModel);
        
        GmCompositionLink link = new GmCompositionLink(diagram,
                this.parentNode.getRepresentedRef());
        this.parentNode.addStartingLink(link);
        gmModel.addEndingLink(link);
    }

    /**
     * Get the creation context (parent element, parent dependency, stereotype).
     * 
     * @return the creation context.
     */
    @objid ("36175b12-55b7-11e2-877f-002564c97630")
    protected ModelioCreationContext getContext() {
        return this.context;
    }

    /**
     * Get the initial layout constraint.
     * 
     * @return the initial layout constraint.
     */
    @objid ("3618e17a-55b7-11e2-877f-002564c97630")
    protected Object getConstraint() {
        return this.constraint;
    }

    /**
     * Get the parent model element.
     * 
     * @return the parent model element.
     */
    @objid ("3618e17f-55b7-11e2-877f-002564c97630")
    protected MObject getParentElement() {
        return this.parentElement;
    }

    /**
     * Get the parent graphic node.
     * 
     * @return the parent graphic node.
     */
    @objid ("3618e186-55b7-11e2-877f-002564c97630")
    protected GmCompositeNode getParentNode() {
        return this.parentNode;
    }

    @objid ("3618e18d-55b7-11e2-877f-002564c97630")
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
            if (!MTools.getAuthTool().canAdd(this.parentElement, this.context.getMetaclass())) {
                return false;
            }
        
            // Ask metamodel experts
            final MClass toCreate = this.context.getMetaclass();
            return toCreate.getMetamodel().getMExpert().canCompose(this.parentElement, toCreate, this.context.getDependencyName());
        
        }
        return true;
    }

}
