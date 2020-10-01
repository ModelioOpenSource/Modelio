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

package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.obfactory.IModelLinkFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that creates a relationship element between the 2 node model elements represented by the given EditPart.
 */
@objid ("7fe523ca-1dec-11e2-8cad-001ec947c8cc")
public class DefaultCreateLinkCommand extends Command implements ICreationCommand<IGmLink> {
    @objid ("7fe523ce-1dec-11e2-8cad-001ec947c8cc")
    private IGmPath path;

    @objid ("7fe523cf-1dec-11e2-8cad-001ec947c8cc")
    protected ModelioLinkCreationContext context;

    @objid ("7fe523d0-1dec-11e2-8cad-001ec947c8cc")
    protected IGmLinkable sourceNode;

    @objid ("7fe785e1-1dec-11e2-8cad-001ec947c8cc")
    protected IGmLinkable targetNode;

    @objid ("950c5069-140a-445c-ae73-654929ff3d50")
    private IGmLink unmaskedLink;

    /**
     * Command constructor
     * 
     * @param context Informations on the model element to create and or unmask.
     */
    @objid ("7fe785e2-1dec-11e2-8cad-001ec947c8cc")
    public DefaultCreateLinkCommand(ModelioLinkCreationContext context) {
        this.context = context;
        this.unmaskedLink = null;
    }

    @objid ("7fe785e6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.sourceNode.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        // If it is an actual creation (and not a simple unmasking).
        if (this.context.getElementToUnmask() == null) {
            MObject srcElement = this.sourceNode.getRelatedElement();
            MClass toCreateMetaclass = this.context.getMetaclass();
            Class<? extends MObject> toCreateInterface = toCreateMetaclass.getJavaInterface();
            Stereotype toCreateStereotype = this.context.getStereotype();
            IMdaExpert mdaExpert = this.sourceNode.getDiagram().getModelManager().getMdaExpert();
        
            if (this.targetNode == null) {
                // The creation experts must allow starting the link
                if (!mdaExpert.canSource(toCreateStereotype, toCreateMetaclass, srcElement.getMClass())) {
                    return false;
                }
        
                // The access right expert must allow the command
                if (!MTools.getAuthTool().canCreateLinkFrom(toCreateInterface, srcElement)) {
                    return false;
                }
        
            } else {
                // The creation experts must allow the link
                final MObject targetEl = this.targetNode.getRelatedElement();
                if (targetEl == null || targetEl.isShell() || targetEl.isDeleted()) {
                    return false;
                }
                if (!mdaExpert.canLink(toCreateStereotype, toCreateMetaclass, srcElement, targetEl)) {
                    return false;
                }
        
                // The access right expert must allow the command
                if (!MTools.getAuthTool().canCreateLink(toCreateInterface, srcElement, targetEl)) {
                    return false;
                }
            }
        }
        
        // All conditions are fulfilled
        return true;
    }

    @objid ("7fe785eb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        MObject linkElement = this.context.getElementToUnmask();
        if (linkElement == null) {
            linkElement = createElement();
        }
        
        // Unmask the link
        final IGmDiagram gmDiagram = getCommonDiagram(); 
        this.unmaskedLink = unmaskElement(gmDiagram, linkElement);
    }

    /**
     * Sets the context
     * 
     * @param newContext the link creation context.
     */
    @objid ("7fe785ee-1dec-11e2-8cad-001ec947c8cc")
    public void setContext(ModelioLinkCreationContext newContext) {
        this.context = newContext;
    }

    /**
     * Set the link source.
     * 
     * @param sourceNode the link source.
     */
    @objid ("7fe785f2-1dec-11e2-8cad-001ec947c8cc")
    public void setSource(final IGmLinkable sourceNode) {
        this.sourceNode = sourceNode;
    }

    /**
     * Set the link destination.
     * 
     * @param targetNode the link destination.
     */
    @objid ("7fe785f7-1dec-11e2-8cad-001ec947c8cc")
    public void setTarget(final IGmLinkable targetNode) {
        this.targetNode = targetNode;
    }

    /**
     * Set the path of the link.
     * 
     * @param path the link path.
     */
    @objid ("7fe785fc-1dec-11e2-8cad-001ec947c8cc")
    public void setPath(final IGmPath path) {
        this.path = path;
    }

    /**
     * Unmask the link element.
     * <p>
     * May be redefined to do more work.
     * 
     * @param gmDiagram the diagram where the element must be unmasked
     * @param linkElement the link element to unmask
     * @return The unmasked link graphic model.
     */
    @objid ("7fe78601-1dec-11e2-8cad-001ec947c8cc")
    protected IGmLink unmaskElement(final IGmDiagram gmDiagram, final MObject linkElement) {
        return gmDiagram.unmaskLink(linkElement, this.sourceNode, this.targetNode, this.path);
    }

    /**
     * Create the model element specified by the context.
     * <p>
     * May be redefined to do more work.
     * 
     * @return The created link model element.
     */
    @objid ("7fe7860a-1dec-11e2-8cad-001ec947c8cc")
    protected MObject createElement() {
        final IGmDiagram gmDiagram = this.sourceNode.getDiagram();
        MObject linkElement;
        // Create the link model element
        final IModelManager modelManager = gmDiagram.getModelManager();
        final IModelLinkFactory modelFactory = modelManager.getModelLinkFactory();
        final MObject srcElement = this.sourceNode.getRelatedElement();
        final MObject targetElement = this.targetNode.getRelatedElement();
        linkElement = modelFactory.createLink(this.context.getMetaclass(), srcElement, targetElement);
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null && linkElement instanceof ModelElement) {
            ((ModelElement) linkElement).getExtension().add(this.context.getStereotype());
        }
        
        // Configure element
        IMModelServices modelServices = modelManager.getModelServices();
        modelServices.getElementConfigurer().configure(linkElement, this.context.getProperties());
        
        // Set default name
        linkElement.setName(modelServices.getElementNamer().getUniqueName(linkElement));
        return linkElement;
    }

    @objid ("3fe05a08-d19f-4a1a-88c6-30d5754dddec")
    @Override
    public IGmLink getCreatedGraphicModel() {
        return this.unmaskedLink;
    }

    /**
     * Get the diagram in which the link must be unmasked.
     * <p>
     * If the source and target node are in different diagrams, the link is unmasked in the
     * diagram embedding the source diagram and the target one.
     * 
     * @return the diagram in which the link must be unmasked.
     * @since 3.7
     */
    @objid ("8cd686a6-8704-43ec-afa6-32edd98f5d06")
    protected final IGmDiagram getCommonDiagram() {
        return IGmDiagram.getCommonDiagramOwner(this.sourceNode.getDiagram(), this.targetNode.getDiagram());
    }

}
