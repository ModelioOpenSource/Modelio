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

package org.modelio.diagram.editor.sequence.elements.stateinvariant;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Reparent command that is specific to StateInvariant. The ownership change might be graphic only (if not changing lifeline). Also the "times" of starting and ending links are updated.
 * 
 * @author fpoyer
 */
@objid ("d99a98f3-55b6-11e2-877f-002564c97630")
public class ReparentStateInvariantCommand extends Command {
    @objid ("d99a98fb-55b6-11e2-877f-002564c97630")
    private int finishTime;

    @objid ("d99a98fc-55b6-11e2-877f-002564c97630")
    private int startTime;

    /**
     * The child that is being reparented.
     */
    @objid ("d99a98f9-55b6-11e2-877f-002564c97630")
    private GmStateInvariant reparentedChild;

    /**
     * The new parent of the reparented child.
     */
    @objid ("50703196-55c2-11e2-9337-002564c97630")
    private GmCompositeNode newParentNode;

    /**
     * Default C'tor.
     * 
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param startTime the new "time" of the StateInvariant.
     * @param finishTime the new "end time" of the StateInvariant
     */
    @objid ("d99a98fd-55b6-11e2-877f-002564c97630")
    public ReparentStateInvariantCommand(GmCompositeNode newParent, GmStateInvariant reparentedChild, final int startTime, final int finishTime) {
        super();
        this.newParentNode = newParent;
        this.reparentedChild = reparentedChild;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    @objid ("d99a9908-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        
        if (childElement == null || !childElement.getStatus().isModifiable()) {
            return false;
        }
        
        MObject oldParent = this.reparentedChild.getParent().getRelatedElement();
        MObject newParent = this.newParentNode.getRelatedElement();
        if (newParent == null) {
            return false;
        }
        
        MMetamodel mm = newParent.getMClass().getMetamodel();
        boolean sameParentInObModel = newParent.equals(oldParent);
        return sameParentInObModel
                        || (newParent.getStatus().isModifiable() && mm.getMExpert().canCompose(newParent, childElement,
                                ((SmObjectImpl) childElement).getCompositionRelation().dep.getName()));
    }

    @objid ("d99a990d-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        StateInvariant childElement = this.reparentedChild.getRelatedElement();
        MObject oldParentElement = this.reparentedChild.getParent().getRelatedElement();
        String oldParentDep = "CoveredBy";
        MObject newParentElement = this.newParentNode.getRelatedElement();
        boolean sameParentInObModel = newParentElement.equals(childElement.getCompositionOwner());
        if (!sameParentInObModel) {
            // orphan the underlying {@link MObject element} from its previous
            // {@link Element#getCompositionOwner() composition owner},
            MDependency oldDep = oldParentElement.getMClass().getDependency(oldParentDep);
            List<MObject> children = oldParentElement.mGet(oldDep);
            children.remove(childElement);
        }
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmCompositeNode oldParent = (GmCompositeNode) this.reparentedChild.getParent();
        oldParent.removeChild(this.reparentedChild);
        if (!sameParentInObModel) {
            // attach the underlying {@link MObject element} to its new {@link
            // Element#getCompositionOwner() composition owner},
        
            try {
                MDependency oldDep = newParentElement.getMClass().getDependency(oldParentDep);
                List<MObject> children = newParentElement.mGet(oldDep);
                children.add(childElement);
            } catch (@SuppressWarnings ("unused") Exception e) {
                // Maybe new parent is not using the same dependency for composition
                // Try to find a fitting dependency
                MMetamodel mm = newParentElement.getMClass().getMetamodel();
                MDependency defaultCompositionDep = mm.getMExpert().getDefaultCompositionDep(newParentElement, childElement);
                List<MObject> children = newParentElement.mGet(defaultCompositionDep);
                children.add(childElement);
            }
        }
        // and finally attach the {@link GmNodeModel node} to its new {@link
        // GmCompositeNode container}.
        this.newParentNode.addChild(this.reparentedChild);
        // Modifying the Ob model, the rest will follow...
        StateInvariant stateInvariant = this.reparentedChild.getRelatedElement();
        stateInvariant.setLineNumber(this.startTime);
        stateInvariant.setEndLineNumber(this.finishTime);
    }

}
