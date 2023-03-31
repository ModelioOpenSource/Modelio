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

import java.text.MessageFormat;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Bpmn-specific command reparenting BPMN elements in Lanes, slightly different from the {@link DefaultReparentElementCommand}.
 * <p>
 * The default reparent command can't be used because the Lane -> FlowElement relation is not a composition dep.
 * Moreover a check is done to avoid breaking the model.
 * </p>
 */
@objid ("621adca3-55b6-11e2-877f-002564c97630")
public class BpmnFlowElementReparentElementCommand extends Command {
    /**
     * The new parent element (might be different from the element of the new parent node).
     */
    @objid ("621adcae-55b6-11e2-877f-002564c97630")
    private MObject newParentElement;

    /**
     * The new layout data of the reparented child.
     */
    @objid ("621adca4-55b6-11e2-877f-002564c97630")
    private Object newLayoutData;

    /**
     * The new parent of the reparented child.
     */
    @objid ("72a4b6a9-55c1-11e2-9337-002564c97630")
    private GmCompositeNode newParent;

    /**
     * The child that is being reparented.
     */
    @objid ("72a4b6ab-55c1-11e2-9337-002564c97630")
    private GmNodeModel reparentedChild;

    /**
     * Default C'tor.
     * @param newParentElement the MObject that will be the new parent of the element represented by the reparented node.
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param newLayoutData the new layout data of the reparented node.
     */
    @objid ("621adcb2-55b6-11e2-877f-002564c97630")
    public  BpmnFlowElementReparentElementCommand(final MObject newParentElement, final GmCompositeNode newParent, final GmNodeModel reparentedChild, final Object newLayoutData) {
        super();
        this.newParentElement = newParentElement;
        this.newParent = newParent;
        this.reparentedChild = reparentedChild;
        this.newLayoutData = newLayoutData;
        
    }

    @objid ("621adcc2-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        if (this.newParentElement == null || childElement == null || childElement instanceof BpmnSequenceFlow) {
            return false;
        }
        if (!this.newParentElement.getStatus().isModifiable() || !childElement.getStatus().isModifiable()) {
            setLabel(this.newParentElement + " is not editable.");
            return false;
        }
        if (this.newParentElement instanceof BpmnProcess) {
            BpmnLaneSet laneSet = ((BpmnProcess) this.newParentElement).getLaneSet();
            if (laneSet != null && !laneSet.getLane().isEmpty()) {
                // Forbid node reparenting in a process having lanes.
                return false;
            }
        }
        
        if (childElement instanceof BpmnFlowNode) {
            BpmnFlowNode node = (BpmnFlowNode) childElement;
            for (BpmnSequenceFlow flow : node.getIncoming()) {
                BpmnFlowNode srcNode = flow.getSourceRef();
                if (!Objects.equals(getBpmnFlowContainer(this.newParentElement), getBpmnFlowContainer(srcNode.getCompositionOwner()))) {
                    // sequence flows must relate 2 nodes in the same process.
                    setLabel("Sequence flows must relate 2 nodes in the same process.");
                    return false;
                }
            }
            for (BpmnSequenceFlow flow : node.getOutgoing()) {
                BpmnFlowNode targetNode = flow.getTargetRef();
                if (!Objects.equals(getBpmnFlowContainer(this.newParentElement), getBpmnFlowContainer(targetNode.getCompositionOwner()))) {
                    // sequence flows must relate 2 nodes in the same process.
                    setLabel("Sequence flows must relate 2 nodes in the same process.");
                    return false;
                }
            }
        }
        
        setLabel(MessageFormat.format("Move {0} under {1}", childElement, this.newParentElement));
        return true;
    }

    @objid ("621adcc7-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        BpmnFlowElement childElement = (BpmnFlowElement) this.reparentedChild.getRelatedElement();
        MObject parent = this.reparentedChild.getParent().getRelatedElement();
        MObject newParentElem = this.newParentElement;
        
        // orphan the underlying {@link MObject element} from its previous
        // {@link MObject#getCompositionOwner() composition owner},
        assert (childElement != null) : "cannot reparent: child element is null";
        
        if (parent instanceof BpmnLane) {
            childElement.getLane().remove(parent);
        }
        
        BpmnProcess ownerprocess = childElement.getContainer();
        if (ownerprocess != null) {
            ownerprocess.getFlowElement().remove(childElement);
        }
        
        BpmnSubProcess ownersubprocess = childElement.getSubProcess();
        if (ownersubprocess != null) {
            ownersubprocess.getFlowElement().remove(childElement);
        }
        
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmModel oldParentModel = this.reparentedChild.getParent();
        assert (oldParentModel instanceof GmCompositeNode) : "This command should only be used if both old parent and new parent are instances of GmCompositeNode!";
        final GmCompositeNode oldParent = (GmCompositeNode) oldParentModel;
        oldParent.removeChild(this.reparentedChild);
        
        if (newParentElem instanceof BpmnLane) {
            childElement.getLane().add((BpmnLane) newParentElem);
            newParentElem = getBpmnFlowContainer(newParentElem);
        }
        
        if (newParentElem instanceof BpmnProcess) {
            childElement.setContainer((BpmnProcess) newParentElem);
        }
        
        if (newParentElem instanceof BpmnSubProcess) {
            childElement.setSubProcess((BpmnSubProcess) newParentElem);
        }
        
        this.reparentedChild.setLayoutData(this.newLayoutData);
        
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
        
            this.newParent.getDiagram().unmask(this.newParent,
                    this.reparentedChild.getRelatedElement(),
                    this.newLayoutData);
        
            // Delete the now unused child
            this.reparentedChild.delete();
        }
        
        super.execute();
        
    }

    @objid ("621c631b-55b6-11e2-877f-002564c97630")
    private MObject getBpmnFlowContainer(final MObject el) {
        if (el instanceof BpmnProcess || el instanceof BpmnSubProcess) {
            return el;
        }
        return getBpmnFlowContainer(el.getCompositionOwner());
    }

}
