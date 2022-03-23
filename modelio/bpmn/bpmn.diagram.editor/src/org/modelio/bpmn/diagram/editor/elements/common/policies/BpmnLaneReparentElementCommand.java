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
package org.modelio.bpmn.diagram.editor.elements.common.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Bpmn-specific command reparenting BPMN Lanes, slightly different from the {@link DefaultReparentElementCommand}.
 */
@objid ("621c632e-55b6-11e2-877f-002564c97630")
public class BpmnLaneReparentElementCommand extends Command {
    /**
     * The new parent element (might be different from the element of the new parent node).
     */
    @objid ("621c6339-55b6-11e2-877f-002564c97630")
    private MObject newParentElement;

    /**
     * The new layout data of the reparented child.
     */
    @objid ("621c632f-55b6-11e2-877f-002564c97630")
    private Object newLayoutData;

    /**
     * The new parent of the reparented child.
     */
    @objid ("72a63d49-55c1-11e2-9337-002564c97630")
    private GmCompositeNode newParent;

    /**
     * The child that is being reparented.
     */
    @objid ("72a63d4b-55c1-11e2-9337-002564c97630")
    private GmNodeModel reparentedChild;

    /**
     * Default C'tor.
     * @param newParentElement the MObject that will be the new parent of the element represented by the reparented node.
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param newLayoutData the new layout data of the reparented node.
     */
    @objid ("621c633d-55b6-11e2-877f-002564c97630")
    public  BpmnLaneReparentElementCommand(final MObject newParentElement, final GmCompositeNode newParent, final GmNodeModel reparentedChild, final Object newLayoutData) {
        super();
        this.newParentElement = newParentElement;
        this.newParent = newParent;
        this.reparentedChild = reparentedChild;
        this.newLayoutData = newLayoutData;
        
    }

    @objid ("621c634d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        if (this.newParentElement == null || childElement == null) {
            return false;
        }
        if (!this.newParentElement.getStatus().isModifiable() ||
                !childElement.getStatus().isModifiable()) {
            return false;
        }
        return true;
    }

    @objid ("621c6352-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        BpmnLane childElement = (BpmnLane) this.reparentedChild.getRelatedElement();
        MObject newParentElem = this.newParentElement;
        
        // orphan the underlying {@link MObject element} from its previous
        // {@link MObject#getCompositionOwner() composition owner},
        assert (childElement != null) : "cannot reparent: child element is null";
        
        if (newParentElem.equals(childElement.getLaneSet().getCompositionOwner())) {
            return;
        }
        
        BpmnLaneSet laneset = childElement.getLaneSet();
        if (laneset != null) {
            laneset.getLane().remove(childElement);
            if (laneset.getLane().isEmpty()) {
                laneset.delete();
            }
        }
        
        final IGmDiagram diagram = this.newParent.getDiagram();
        final IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // orphan the {@link GmNodeModel node} from its previous {@link GmCompositeNode container},
        final GmModel oldParentModel = this.reparentedChild.getParent();
        assert (oldParentModel instanceof GmCompositeNode) : "This command should only be used if both old parent and new parent are instances of GmCompositeNode!";
        final GmCompositeNode oldParent = (GmCompositeNode) oldParentModel;
        oldParent.removeChild(this.reparentedChild);
        
        if (newParentElem instanceof BpmnLane) {
            BpmnLane parentLane = (BpmnLane) newParentElem;
            if (parentLane.getChildLaneSet() != null) {
                newParentElem = parentLane.getChildLaneSet();
        
                this.newParent = (GmCompositeNode) diagram.getExistingModelFor(newParentElem);
            } else {
                BpmnLaneSet newElement = modelFactory.createBpmnLaneSet();
                parentLane.setChildLaneSet(newElement);
        
                // Set default name
                newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
                // Unmask new lane set
                this.newParent = (GmCompositeNode) diagram.unmask(this.newParent, newElement, this.newLayoutData);
                newParentElem = newElement;
        
            }
        } else if (newParentElem instanceof BpmnProcess) {
            BpmnProcess process = (BpmnProcess) newParentElem;
            if (process.getLaneSet() != null) {
                newParentElem = process.getLaneSet();
                this.newParent = (GmCompositeNode) diagram.getExistingModelFor(newParentElem);
            } else {
                BpmnLaneSet newElement = modelFactory.createBpmnLaneSet();
                process.setLaneSet(newElement);
        
                // Set default name
                newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
                // Unmask new lane set
                this.newParent = (GmCompositeNode) diagram.unmask(this.newParent, newElement, this.newLayoutData);
                newParentElem = newElement;
            }
        }
        
        // Some additional initializing steps might be needed.
        if (newParentElem instanceof BpmnLaneSet) {
            childElement.setLaneSet((BpmnLaneSet) newParentElem);
        }
        
        if (this.newParent.canContain(this.reparentedChild.getClass())) {
            // and finally attach the {@link GmNodeModel node} to its new {@link
            // GmCompositeNode container}.
            this.newParent.addChild(this.reparentedChild);
        } else {
            // The new parent does not support the node.
            // Ask the diagram to create a new node.
            if (this.newLayoutData instanceof Rectangle) {
                // reset the rectangle dimensions
                final Rectangle r = (Rectangle) this.newLayoutData;
                this.newLayoutData = new Rectangle(r.x, r.y, -1, -1);
            }
        
            this.newParent.getDiagram().unmask(this.newParent, this.reparentedChild.getRelatedElement(), this.newLayoutData);
        
            // Delete the now unused child
            this.reparentedChild.delete();
        }
        
        super.execute();
        
    }

}
