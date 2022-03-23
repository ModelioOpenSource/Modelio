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
package org.modelio.diagram.elements.core.commands;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;
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
@objid ("7f34b591-1dec-11e2-8cad-001ec947c8cc")
public class DefaultCloneElementCommand extends Command {
    @objid ("7f34b595-1dec-11e2-8cad-001ec947c8cc")
    private MObject elementToClone;

    @objid ("7f34b596-1dec-11e2-8cad-001ec947c8cc")
    private Object constraint;

    @objid ("7f34b597-1dec-11e2-8cad-001ec947c8cc")
    private MObject parentElement;

    @objid ("7f34b598-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode parentNode;

    /**
     * Creates a node creation command.
     * @param parentNode The parent node
     * @param parentElement the parent element
     * @param elementToClone MObject to clone
     * @param constraint The initial constraint of the created node.
     */
    @objid ("7f34b599-1dec-11e2-8cad-001ec947c8cc")
    public  DefaultCloneElementCommand(GmCompositeNode parentNode, final MObject parentElement, final MObject elementToClone, Object constraint) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.elementToClone = elementToClone;
        this.constraint = constraint;
        
    }

    @objid ("7f34b5a2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        MObject newElement = MTools.getModelTool().cloneElement(this.elementToClone);
        List<MObject> elements = new ArrayList<>(1);
        elements.add(newElement);
        MTools.getModelTool().moveElements(elements, this.parentElement, null);
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newElement, this.constraint);
        
    }

    /**
     * Get the initial layout constraint.
     * @return the initial layout constraint.
     */
    @objid ("7f34b5a5-1dec-11e2-8cad-001ec947c8cc")
    protected Object getConstraint() {
        return this.constraint;
    }

    /**
     * Get the parent model element.
     * @return the parent model element.
     */
    @objid ("7f34b5aa-1dec-11e2-8cad-001ec947c8cc")
    protected MObject getParentElement() {
        return this.parentElement;
    }

    /**
     * Get the parent graphic node.
     * @return the parent graphic node.
     */
    @objid ("7f34b5af-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getParentNode() {
        return this.parentNode;
    }

    @objid ("7f34b5b4-1dec-11e2-8cad-001ec947c8cc")
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
        
        // Ask metamodel experts
        MExpert expert = this.parentElement.getMClass().getMetamodel().getMExpert();
        return expert.canCompose(this.parentElement, this.elementToClone, null);
    }

    /**
     * Get the element to clone.
     * @return the element to clone.
     */
    @objid ("7f34b5b9-1dec-11e2-8cad-001ec947c8cc")
    public MObject getElementToClone() {
        return this.elementToClone;
    }

}
