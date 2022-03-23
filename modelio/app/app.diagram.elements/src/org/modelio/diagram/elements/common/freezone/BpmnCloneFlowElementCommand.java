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
package org.modelio.diagram.elements.common.freezone;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("bcfab4ce-eb90-435a-a6b9-bbc3edb37cb8")
public class BpmnCloneFlowElementCommand extends Command {
    @objid ("9c8f37aa-ca45-4041-bb61-1037459ff83c")
    private BpmnFlowElement elementToClone;

    @objid ("cfc0fd22-1ad9-4672-b6cc-03c798aeb697")
    private Object constraint;

    @objid ("e6ff50a7-8504-4be8-940c-5829a51b3a44")
    private BpmnLane parentElement;

    @objid ("31d9bb48-ee9f-469b-9cc0-2741687022ed")
    private GmCompositeNode parentNode;

    /**
     * Creates a node creation command.
     * @param parentNode The parent node
     * @param parentElement the parent element
     * @param elementToClone MObject to clone
     * @param constraint The initial constraint of the created node.
     */
    @objid ("00508182-20f5-4cf7-b9c8-e6980f9b899a")
    public  BpmnCloneFlowElementCommand(GmCompositeNode parentNode, final BpmnLane parentElement, final BpmnFlowElement elementToClone, Object constraint) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.elementToClone = elementToClone;
        this.constraint = constraint;
        
    }

    @objid ("78f160e1-d3f6-46e3-bed6-72974e4bc046")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        BpmnFlowElement newElement = (BpmnFlowElement) MTools.getModelTool().cloneElement(this.elementToClone);
        
        MObject owner = findBaseOwner(this.parentElement.getLaneSet());
        
        if (owner instanceof BpmnProcess) {
            newElement.setContainer((BpmnProcess) owner);
        } else if (owner instanceof BpmnSubProcess) {
            newElement.setSubProcess((BpmnSubProcess) owner);
        }
        
        newElement.getLane().add(this.parentElement);
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newElement, this.constraint);
        
    }

    @objid ("8c0421b7-04d4-4ab3-a419-68063e201c0e")
    private MObject findBaseOwner(BpmnLaneSet element) {
        if (element.getProcess() != null) {
            return element.getProcess();
        } else if (element.getSubProcess() != null) {
            return element.getSubProcess();
        } else {
            return findBaseOwner(element.getParentLane().getLaneSet());
        }
        
    }

    /**
     * Get the initial layout constraint.
     * @return the initial layout constraint.
     */
    @objid ("f751e9fb-64d9-4910-8b63-81c5e4d29f71")
    protected Object getConstraint() {
        return this.constraint;
    }

    /**
     * Get the parent model element.
     * @return the parent model element.
     */
    @objid ("35913867-a02a-4bf3-82d5-f2335d698e88")
    protected MObject getParentElement() {
        return this.parentElement;
    }

    /**
     * Get the parent graphic node.
     * @return the parent graphic node.
     */
    @objid ("7aefc36a-a236-47f3-b46f-67c9d38cecfc")
    protected GmCompositeNode getParentNode() {
        return this.parentNode;
    }

    @objid ("8044821a-8a41-4c15-94aa-f353380a061b")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.parentNode.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        // The parent element must be modifiable or
        // both must be CMS nodes.
        if (!MTools.getAuthTool().canAdd(this.parentElement, this.elementToClone.getMClass())) {
            return false;
        }
        return true;
    }

    /**
     * Get the element to clone.
     * @return the element to clone.
     */
    @objid ("3ef3e879-d431-484e-8fd8-38dc596771e5")
    public MObject getElementToClone() {
        return this.elementToClone;
    }

}
