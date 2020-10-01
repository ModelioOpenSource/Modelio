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

package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Reparent command that is specific to Execution. The ownership change might be graphic only (if not changing lifeline). Also the "times" of starting and ending links are updated.
 * 
 * @author fpoyer
 */
@objid ("d8f1397e-55b6-11e2-877f-002564c97630")
public class ReparentExecutionSpecificationCommand extends Command {
    @objid ("d8f13986-55b6-11e2-877f-002564c97630")
    private int finishTime;

    @objid ("d8f13987-55b6-11e2-877f-002564c97630")
    private int startTime;

    /**
     * The child that is being reparented.
     */
    @objid ("d8f13984-55b6-11e2-877f-002564c97630")
    private GmExecutionSpecification reparentedChild;

    /**
     * The new parent of the reparented child.
     */
    @objid ("50131d69-55c2-11e2-9337-002564c97630")
    private GmCompositeNode newParentNode;

    /**
     * Default C'tor.
     * 
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param startTime the new "time" of the ExecutionOccurenceSpecification that starts the execution.
     * @param finishTime the new "time" of the ExecutionOccurenceSpecification that finishes the execution.
     */
    @objid ("d8f13988-55b6-11e2-877f-002564c97630")
    public ReparentExecutionSpecificationCommand(GmCompositeNode newParent, GmExecutionSpecification reparentedChild, final int startTime, final int finishTime) {
        super();
        this.newParentNode = newParent;
        this.reparentedChild = reparentedChild;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    @objid ("d8f13993-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final ExecutionSpecification childElement = this.reparentedChild.getRelatedElement();
        
        if (childElement == null || !childElement.getStatus().isModifiable()) {
            return false;
        }
        ExecutionOccurenceSpecification start = childElement.getStart();
        if (start == null || !start.getStatus().isModifiable()) {
            return false;
        }
        ExecutionOccurenceSpecification finish = childElement.getStart();
        if (finish == null || !finish.getStatus().isModifiable()) {
            return false;
        }
        
        MObject oldParent = getOldLifeline();
        MObject newParent = getNewLifeline();
        if (newParent == null) {
            return false;
        }
        
        MMetamodel mm = childElement.getMClass().getMetamodel();
        
        boolean sameParentInObModel = newParent.equals(oldParent);
        return sameParentInObModel
                        || (oldParent.getStatus().isModifiable() && newParent.getStatus().isModifiable() && mm.getMExpert().canCompose(
                                newParent, childElement, ((SmObjectImpl) childElement).getCompositionRelation().dep.getName()));
    }

    @objid ("d8f13998-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        ExecutionSpecification childElement = this.reparentedChild.getRelatedElement();
        MObject oldParentElement = getOldLifeline();
        String oldParentDep = "CoveredBy";
        MObject newParentElement = getNewLifeline();
        boolean sameParentInObModel = newParentElement.equals(childElement.getCompositionOwner());
        if (!sameParentInObModel) {
            // orphan the underlying {@link MObject element} from its previous
            // {@link MObject#getCompositionOwner() composition owner},
            MDependency mDep = oldParentElement.getMClass().getDependency(oldParentDep);
            List<MObject> children = oldParentElement.mGet(mDep);
            children.remove(childElement);
            children.remove(childElement.getStart());
            children.remove(childElement.getFinish());
        }
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmCompositeNode oldParent = (GmCompositeNode) this.reparentedChild.getParent();
        oldParent.removeChild(this.reparentedChild);
        // Find and remove the gm corresponding to the ends
        final GmNodeModel startGm = oldParent.getChild(new MRef(childElement.getStart()));
        if (startGm != null) {
            oldParent.removeChild(startGm);
        }
        final GmNodeModel finishGm = oldParent.getChild(new MRef(childElement.getFinish()));
        if (finishGm != null) {
            oldParent.removeChild(finishGm);
        }
        if (!sameParentInObModel) {
            MMetamodel mm = childElement.getMClass().getMetamodel();
        
            // attach the underlying {@link MObject element} to its new {@link
            // MObject#getCompositionOwner() composition owner},
            try {
                MDependency oldDep = newParentElement.getMClass().getDependency(oldParentDep);
                List<MObject> children = newParentElement.mGet(oldDep);
                children.add(childElement);
                children.add(childElement.getStart());
                children.add(childElement.getFinish());
            } catch (@SuppressWarnings ("unused") Exception e) {
                // Maybe new parent is not using the same dependency for composition
                // Try to find a fitting dependency
                MDependency defaultCompositionDep = mm.getMExpert().getDefaultCompositionDep(newParentElement, childElement);
                List<MObject> children = newParentElement.mGet(defaultCompositionDep);
                children.add(childElement);
                children.add(childElement.getStart());
                children.add(childElement.getFinish());
            }
        }
        // and finally attach the {@link GmNodeModel node} to its new {@link
        // GmCompositeNode container}.
        this.newParentNode.addChild(this.reparentedChild);
        if (startGm != null) {
            this.newParentNode.addChild(startGm);
        }
        if (finishGm != null) {
            this.newParentNode.addChild(finishGm);
        }
        // Modifying the Ob model, the rest will follow...
        ExecutionSpecification execution = this.reparentedChild.getRelatedElement();
        execution.getStart().setLineNumber(this.startTime);
        execution.setLineNumber(this.startTime);
        execution.getFinish().setLineNumber(this.finishTime);
    }

    @objid ("d8f1399b-55b6-11e2-877f-002564c97630")
    private MObject getNewLifeline() {
        MObject newParentNodeElement = this.newParentNode.getRelatedElement();
        if (newParentNodeElement instanceof Lifeline) {
            return newParentNodeElement;
        } else if (newParentNodeElement instanceof ExecutionSpecification) {
            return ((ExecutionSpecification) newParentNodeElement).getCovered().get(0);
        } else {
            return null;
        }
    }

    @objid ("d8f139a0-55b6-11e2-877f-002564c97630")
    private MObject getOldLifeline() {
        MObject oldParentNodeElement = this.reparentedChild.getParent().getRelatedElement();
        if (oldParentNodeElement instanceof Lifeline) {
            return oldParentNodeElement;
        } else if (oldParentNodeElement instanceof ExecutionSpecification) {
            return ((ExecutionSpecification) oldParentNodeElement).getCovered().get(0);
        } else {
            return null;
        }
    }

}
