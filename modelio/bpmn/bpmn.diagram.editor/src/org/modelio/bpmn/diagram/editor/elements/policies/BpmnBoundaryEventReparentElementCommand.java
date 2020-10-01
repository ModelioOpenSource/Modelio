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

package org.modelio.bpmn.diagram.editor.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6217cf3a-55b6-11e2-877f-002564c97630")
public class BpmnBoundaryEventReparentElementCommand extends Command {
    /**
     * The new parent element (might be different from the element of the new parent node).
     */
    @objid ("621955dc-55b6-11e2-877f-002564c97630")
    private MObject newParentElement;

    /**
     * The new layout data of the reparented child.
     */
    @objid ("6217cf3b-55b6-11e2-877f-002564c97630")
    private Object newLayoutData;

    /**
     * The new parent of the reparented child.
     */
    @objid ("72a022c9-55c1-11e2-9337-002564c97630")
    private GmCompositeNode newParent;

    /**
     * The child that is being reparented.
     */
    @objid ("72a022cb-55c1-11e2-9337-002564c97630")
    private GmNodeModel reparentedChild;

    @objid ("621955e0-55b6-11e2-877f-002564c97630")
    public BpmnBoundaryEventReparentElementCommand(final MObject newParentElement, final GmCompositeNode newParent, final GmNodeModel reparentedChild, final Object newLayoutData) {
        super();
        this.newParentElement = newParentElement;
        this.newParent = newParent;
        this.reparentedChild = reparentedChild;
        this.newLayoutData = newLayoutData;
    }

    @objid ("621955f0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        if (this.newParentElement == null || childElement == null)
            return false;
        if (!this.newParentElement.getStatus().isModifiable() ||
                !childElement.getStatus().isModifiable())
            return false;
        return true;
    }

    @objid ("621955f5-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        BpmnBoundaryEvent childElement = (BpmnBoundaryEvent) this.reparentedChild.getRelatedElement();
        MObject newParentElem = this.newParentElement;
        
        // orphan the underlying {@link MObject element} from its previous
        // {@link MObject#getCompositionOwner() composition owner},
        assert (childElement != null) : "cannot reparent: child element is null";
        
        BpmnActivity activity = childElement.getAttachedToRef();
        if (activity != null) {
            activity.getBoundaryEventRef().remove(childElement);
        }
        
        BpmnSubProcess subprocess = childElement.getSubProcess();
        if (subprocess != null) {
            subprocess.getFlowElement().remove(childElement);
        }
        
        BpmnProcess process = childElement.getContainer();
        if (process != null) {
            process.getFlowElement().remove(childElement);
        }
        
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmModel oldParentModel = this.reparentedChild.getParent();
        assert (oldParentModel instanceof GmCompositeNode) : "This command should only be used if both old parent and new parent are instances of GmCompositeNode!";
        final GmCompositeNode oldParent = (GmCompositeNode) oldParentModel;
        oldParent.removeChild(this.reparentedChild);
        
        childElement.setAttachedToRef((BpmnActivity) newParentElem);
        newParentElem = getOwnerProcess(newParentElem);
        
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

    @objid ("621955f8-55b6-11e2-877f-002564c97630")
    private MObject getOwnerProcess(final MObject childElement) {
        if (childElement instanceof BpmnProcess || childElement instanceof BpmnSubProcess) {
            return childElement;
        }
        return getOwnerProcess(childElement.getCompositionOwner());
    }

}
