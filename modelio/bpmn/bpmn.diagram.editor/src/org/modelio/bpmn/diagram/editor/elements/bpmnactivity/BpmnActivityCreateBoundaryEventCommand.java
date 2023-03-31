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
package org.modelio.bpmn.diagram.editor.elements.bpmnactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command dedicated to the creation of BpmnBoundaryEvent
 */
@objid ("60773e9b-55b6-11e2-877f-002564c97630")
public class BpmnActivityCreateBoundaryEventCommand extends DefaultCreateElementCommand {
    @objid ("60773ea8-55b6-11e2-877f-002564c97630")
    public  BpmnActivityCreateBoundaryEventCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        super(parentElement, parentNode, context, constraint);
    }

    @objid ("6078c541-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.getParentNode().getDiagram();
        
        MObject newElement = this.getContext().getElementToUnmask();
        
        if (newElement == null) {
            newElement = createBoundryEventElement(diagram.getModelManager());
        }
        
        // Show the new element in the diagram (ie create its Gm )
        
        this.mainLinkable = diagram.unmask(this.getParentNode(), newElement, this.getConstraint());
        
    }

    @objid ("6078c544-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final IGmDiagram gmDiagram = getParentNode().getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return true;
    }

    @objid ("8c0f81aa-aa03-49c9-a175-b4383f2ab3e3")
    private MObject createBoundryEventElement(IModelManager modelManager) {
        MObject newElement;
        
        
        // Create the Element...
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        newElement = modelFactory.createElement(this.getContext().getMetaclass());
        
        // Add Port to Task : Relation boundaryEventRef
        assert (getParentElement() instanceof BpmnActivity);
        assert (newElement instanceof BpmnBoundaryEvent);
        
        
        BpmnBoundaryEvent boundaryElement = (BpmnBoundaryEvent) newElement;
        BpmnActivity activity = (BpmnActivity) getParentElement();
        
        if (activity.getContainer() != null) {
            boundaryElement.setContainer(activity.getContainer());
        } else if (activity.getSubProcess() != null) {
            boundaryElement.setSubProcess(activity.getSubProcess());
        }
        
        activity.getBoundaryEventRef().add((BpmnBoundaryEvent) newElement);
        
        // Attach the stereotype if needed.
        if (this.getContext().getStereotype() != null) {
            boundaryElement.getExtension().add(this.getContext().getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(boundaryElement, getContext().getProperties());
        
        // Set default name
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        boundaryElement.setName(elementNamer.getUniqueName(boundaryElement));
        return newElement;
    }

}
