/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.lifeline.body;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.GmExecutionOccurenceSpecification;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("d935e3e2-55b6-11e2-877f-002564c97630")
public class ReparentBlueSquareCommand extends Command {
    @objid ("d9376a3f-55b6-11e2-877f-002564c97630")
    private int newTime;

    /**
     * The new parent of the reparented child.
     */
    @objid ("4fa3b9d3-55c2-11e2-9337-002564c97630")
    private GmCompositeNode newParentNode;

    /**
     * The child that is being reparented.
     */
    @objid ("d633a79b-bbad-452d-9be0-6d7898528d02")
    private GmExecutionOccurenceSpecification reparentedChild;

    /**
     * Default C'tor.
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param newTime the new "time" of the ExecutionOccurenceSpecification.
     */
    @objid ("d9376a40-55b6-11e2-877f-002564c97630")
    public ReparentBlueSquareCommand(GmCompositeNode newParent, GmExecutionOccurenceSpecification reparentedChild, final int newTime) {
        super();
        this.newParentNode = newParent;
        this.reparentedChild = reparentedChild;
        this.newTime = newTime;
    }

    @objid ("d9376a49-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final ExecutionOccurenceSpecification childElement = (ExecutionOccurenceSpecification) this.reparentedChild
                .getRelatedElement();
        
        if (childElement == null || !childElement.getStatus().isModifiable()) {
            return false;
        }
        ExecutionSpecification start = childElement.getStarted();
        if (start != null && !start.getStatus().isModifiable()) {
            return false;
        }
        ExecutionSpecification finish = childElement.getFinished();
        if (finish != null && !finish.getStatus().isModifiable()) {
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
                                newParent, childElement, "CoveredBy"));
    }

    @objid ("d9376a4e-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        ExecutionOccurenceSpecification childElement = (ExecutionOccurenceSpecification) this.reparentedChild.getRelatedElement();
        MObject oldParentElement = getOldLifeline();
        String oldParentDep = "CoveredBy";
        MObject newParentElement = getNewLifeline();
        boolean sameParentInObModel = newParentElement.equals(childElement.getCompositionOwner());
        if (!sameParentInObModel) {
            // orphan the underlying {@link MObject element} from its previous
            // {@link Element#getCompositionOwner() composition owner},
        
            MDependency mDep = oldParentElement.getMClass().getDependency(oldParentDep);
            List<MObject> children = oldParentElement.mGet(mDep);
        
            children.remove(childElement);
            if (childElement.getStarted() != null) {
                children.remove(childElement.getStarted());
                children.remove(childElement.getStarted().getFinish());
            }
            if (childElement.getFinished() != null) {
                children.remove(childElement.getFinished());
                children.remove(childElement.getFinished().getStart());
            }
        }
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmCompositeNode oldParent = (GmCompositeNode) this.reparentedChild.getParent();
        oldParent.removeChild(this.reparentedChild);
        // Find and remove the gm corresponding to the ends
        GmNodeModel startedGm = null;
        GmNodeModel startedFinishGm = null;
        if (childElement.getStarted() != null) {
            startedGm = oldParent.getChild(new MRef(childElement.getStarted()));
            if (startedGm != null) {
                oldParent.removeChild(startedGm);
            }
            startedFinishGm = oldParent.getChild(new MRef(childElement.getStarted().getFinish()));
            if (startedFinishGm != null) {
                oldParent.removeChild(startedFinishGm);
            }
        }
        
        GmNodeModel finishedGm = null;
        GmNodeModel finishedStartGm = null;
        if (childElement.getFinished() != null) {
            finishedGm = oldParent.getChild(new MRef(childElement.getFinished()));
            if (finishedGm != null) {
                oldParent.removeChild(finishedGm);
            }
            finishedStartGm = oldParent.getChild(new MRef(childElement.getFinished().getStart()));
            if (finishedStartGm != null) {
                oldParent.removeChild(finishedStartGm);
            }
        
        }
        if (!sameParentInObModel) {
            // attach the underlying {@link MObject element} to its new {@link
            // Element#getCompositionOwner() composition owner},
        
            try {
                MDependency mDep = newParentElement.getMClass().getDependency(oldParentDep);
                List<MObject> children = oldParentElement.mGet(mDep);
                children.add(childElement);
                if (childElement.getStarted() != null) {
                    children.add(childElement.getStarted());
                    children.add(childElement.getStarted().getFinish());
                }
                if (childElement.getFinished() != null) {
                    children.add(childElement.getFinished());
                    children.add(childElement.getFinished().getStart());
                }
            } catch (@SuppressWarnings ("unused") Exception e) {
                // Maybe new parent is not using the same dependency for composition
                // Try to find a fitting dependency
                MMetamodel mm = newParentElement.getMClass().getMetamodel();
                MDependency defaultCompositionDep = mm.getMExpert().getDefaultCompositionDep(newParentElement, childElement);
                List<MObject> children = newParentElement.mGet(defaultCompositionDep);
                children.add(childElement);
                if (childElement.getStarted() != null) {
                    children.add(childElement.getStarted());
                    children.add(childElement.getStarted().getFinish());
                }
                if (childElement.getFinished() != null) {
                    children.add(childElement.getFinished());
                    children.add(childElement.getFinished().getStart());
                }
            }
        }
        // and finally attach the {@link GmNodeModel node} to its new {@link
        // GmCompositeNode container}.
        this.newParentNode.addChild(this.reparentedChild);
        if (startedGm != null) {
            this.newParentNode.addChild(startedGm);
            this.newParentNode.addChild(startedFinishGm);
        }
        if (finishedGm != null) {
            this.newParentNode.addChild(finishedGm);
            this.newParentNode.addChild(finishedStartGm);
        }
        // Modifying the Ob model, the rest will follow...
        int delta = this.newTime - childElement.getLineNumber();
        childElement.setLineNumber(this.newTime);
        if (childElement.getStarted() != null) {
            int tmp = childElement.getStarted().getLineNumber() + delta;
            childElement.getStarted().setLineNumber(tmp);
            tmp = childElement.getStarted().getFinish().getLineNumber() + delta;
            childElement.getStarted().getFinish().setLineNumber(tmp);
        }
        if (childElement.getFinished() != null) {
            int tmp = childElement.getFinished().getLineNumber() + delta;
            childElement.getFinished().setLineNumber(tmp);
            tmp = childElement.getFinished().getStart().getLineNumber() + delta;
            childElement.getFinished().getStart().setLineNumber(tmp);
        }
    }

    @objid ("d9376a51-55b6-11e2-877f-002564c97630")
    private MObject getNewLifeline() {
        MObject newParentNodeElement = this.newParentNode.getRelatedElement();
        if (newParentNodeElement instanceof Lifeline) {
            return newParentNodeElement;
        } else if (newParentNodeElement instanceof ExecutionOccurenceSpecification) {
            return ((ExecutionOccurenceSpecification) newParentNodeElement).getCovered().get(0);
        } else {
            return null;
        }
    }

    @objid ("d9376a56-55b6-11e2-877f-002564c97630")
    private MObject getOldLifeline() {
        MObject oldParentNodeElement = this.reparentedChild.getParent().getRelatedElement();
        if (oldParentNodeElement instanceof Lifeline) {
            return oldParentNodeElement;
        } else if (oldParentNodeElement instanceof ExecutionOccurenceSpecification) {
            return ((ExecutionOccurenceSpecification) oldParentNodeElement).getCovered().get(0);
        } else {
            return null;
        }
    }

}
