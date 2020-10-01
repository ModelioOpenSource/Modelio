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

package org.modelio.uml.statikdiagram.editor.elements.templatecontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allows creation of template parameters.
 * 
 * @author cmarin
 */
@objid ("36df3eba-55b7-11e2-877f-002564c97630")
public class CreateTemplateParameterEditPolicy extends AbstractEditPolicy {
    @objid ("36df3ebc-55b7-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (REQ_CREATE.equals(request.getType()) && canHandle((CreateRequest) request)) {
            return getHost();
        } else {
            return null;
        }
    }

    @objid ("36df3ec2-55b7-11e2-877f-002564c97630")
    private boolean canHandle(final CreateRequest request) {
        if (request.getNewObjectType() instanceof String) {
            final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
            final MObject elementToUnmask = ctx.getElementToUnmask();
        
            if (elementToUnmask instanceof TemplateParameter) {
                return true;
            } else if (elementToUnmask == null) {
                MClass metaclassToCreate = ctx.getMetaclass();
                String depName = ctx.getDependencyName();
                Class<? extends MObject> interfaceToCreate = metaclassToCreate.getJavaInterface();
                if (TemplateParameter.class.isAssignableFrom(interfaceToCreate)) {
                    final MObject hostElement = getHostElement();
                    final MClass hostMetaclass = hostElement.getMClass();
        
                    return hostMetaclass.getMetamodel().getMExpert().canCompose(hostMetaclass, metaclassToCreate, depName);
                }
            }
        }
        return false;
    }

    @objid ("36df3ec8-55b7-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            return getCreateCommand((CreateRequest) request);
        } else {
            return null;
        }
    }

    @objid ("36e0c55b-55b7-11e2-877f-002564c97630")
    protected Command getCreateCommand(final CreateRequest request) {
        final MObject hostElement = getHostElement();
        final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        
        final MObject elementToUnmask = ctx.getElementToUnmask();
        if (elementToUnmask instanceof TemplateParameter) {
            return new Command() {
                // Nothing to redefine
            };
        } else if (elementToUnmask == null && hostElement != null) {
            final MClass metaclassToCreate =ctx.getMetaclass();
            String depName = ctx.getDependencyName();
            
            if (TemplateParameter.class.isAssignableFrom(metaclassToCreate.getJavaInterface())) {
                final boolean returnCommand = metaclassToCreate.getMetamodel().getMExpert().canCompose(hostElement.getMClass(), metaclassToCreate, depName);
        
                if (returnCommand) {
                    return new CreateTemplateParameterCommand(getHostCompositeNode(), ctx);
                }
            }
        }
        return null;
    }

    /**
     * @return the {@link GmCompositeNode} model of the host edit part.
     */
    @objid ("36e0c561-55b7-11e2-877f-002564c97630")
    protected GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    /**
     * @return the element represented.
     */
    @objid ("36e0c568-55b7-11e2-877f-002564c97630")
    protected MObject getHostElement() {
        return getHostCompositeNode().getRelatedElement();
    }

    /**
     * Command that creates a template parameter without unmasking it.
     * 
     * @author cmarin
     */
    @objid ("36e0c56f-55b7-11e2-877f-002564c97630")
    private static class CreateTemplateParameterCommand extends Command {
        @objid ("36e0c575-55b7-11e2-877f-002564c97630")
        private MObject parentElement;

        @objid ("a7fc9bac-55c2-11e2-9337-002564c97630")
        private ModelioCreationContext context;

        @objid ("a7fc9bad-55c2-11e2-9337-002564c97630")
        private GmCompositeNode parentNode;

        @objid ("36e0c57b-55b7-11e2-877f-002564c97630")
        public CreateTemplateParameterCommand(final GmCompositeNode parentNode, final ModelioCreationContext context) {
            this.parentNode = parentNode;
            this.parentElement = parentNode.getRelatedElement();
            this.context = context;
        }

        @objid ("36e0c585-55b7-11e2-877f-002564c97630")
        @Override
        public void execute() {
            final IGmDiagram diagram = this.parentNode.getDiagram();
            
            TemplateParameter newElement = (TemplateParameter) this.context.getElementToUnmask();
            
            if (newElement == null) {
                IModelManager modelManager = diagram.getModelManager();
                // Create the Element...
                final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
                newElement = modelFactory.createTemplateParameter();
            
                if (this.parentElement instanceof NameSpace) {
                    newElement.setParameterized((NameSpace) this.parentElement);
                } else {
                    newElement.setParameterizedOperation((Operation) this.parentElement);
                }
            
                // Attach the stereotype if needed.
                if (this.context.getStereotype() != null) {
                    ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
                }
            
                // Set default name
                newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
            
                // Configure element
                IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
                elementConfigurer.configure(newElement, this.context.getProperties());
            }
        }

    }

}
