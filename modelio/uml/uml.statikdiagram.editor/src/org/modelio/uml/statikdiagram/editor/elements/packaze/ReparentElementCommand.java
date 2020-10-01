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

package org.modelio.uml.statikdiagram.editor.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.GmCompositionLink;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} reparent <code>command</code> that:
 * <ul>
 * <li>orphan the underlying {@link MObject element} from its previous {@link MObject#getCompositionOwner()
 * composition owner},</li>
 * <li>orphan the {@link GmNodeModel node} from its previous {@link GmCompositeNode container},</li>
 * <li>attach the underlying {@link MObject element} to its new {@link MObject#getCompositionOwner() composition
 * owner},</li>
 * <li>and finally attach the {@link GmNodeModel node} to its new {@link GmCompositeNode container}.</li>
 * </ul>
 * <br>
 * This <code>command</code> is ONLY meant to be used if both containers are {@link GmCompositeNode}
 * 
 * @author fpoyer
 * @see MObject#getCompositionOwner()
 * @see MExpert
 */
@objid ("362b3138-55b7-11e2-877f-002564c97630")
public class ReparentElementCommand extends Command {
    /**
     * The new parent element (might be different from the element of the new parent node).
     */
    @objid ("362cb79c-55b7-11e2-877f-002564c97630")
    private MObject newParentElement;

    /**
     * The new layout data of the reparented child.
     */
    @objid ("362b313a-55b7-11e2-877f-002564c97630")
    private Object newLayoutData;

    /**
     * The new parent of the reparented child.
     */
    @objid ("a73ddf89-55c2-11e2-9337-002564c97630")
    private GmCompositeNode newParent;

    /**
     * The child that is being reparented.
     */
    @objid ("a73ddf8b-55c2-11e2-9337-002564c97630")
    private GmNodeModel reparentedChild;

    /**
     * Default C'tor.
     * 
     * @param newParentElement the MObject that will be the new parent of the element represented by the reparented node.
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param newLayoutData the new layout data of the reparented node.
     */
    @objid ("362cb7a4-55b7-11e2-877f-002564c97630")
    public ReparentElementCommand(MObject newParentElement, GmCompositeNode newParent, GmNodeModel reparentedChild, Object newLayoutData) {
        super();
        this.newParentElement = newParentElement;
        this.newParent = newParent;
        this.reparentedChild = reparentedChild;
        this.newLayoutData = newLayoutData;
    }

    @objid ("362cb7b1-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        
        if (this.newParentElement == null || childElement == null) {
            return false;
        }
        
        if (this.newParentElement.equals(childElement)) {
            return false;
        }
        
        // The diagram must be valid and modifiable.
        if (!MTools.getAuthTool().canModify(this.reparentedChild.getDiagram().getRelatedElement())) {
            return false;
        }
        
        // The moved element must be modifiable.
        if (!childElement.getStatus().isModifiable()) {
            return false;
        }
        
        // The old and new parent elements must be modifiable or
        // both must be CMS nodes.
        if (!MTools.getAuthTool().canAdd(this.newParentElement, childElement.getMClass())) {
            return false;
        }
        
        if (!MTools.getAuthTool().canAdd(childElement.getCompositionOwner(), childElement.getMClass())) {
            return false;
        }
        
        // Ask metamodel experts
        return getMmExpert().canCompose(this.newParentElement, childElement, null);
    }

    @objid ("362cb7b6-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // orphan the underlying {@link MObject element} from its previous
        // {@link Element#getCompositionOwner() composition owner},
        final MObject childElement = this.reparentedChild.getRelatedElement();
        assert (childElement != null) : "cannot reparent: child element is null";
        
        SmObjectImpl smObject = (SmObjectImpl) childElement;
        SmDepVal compositionDepVal = smObject.getCompositionRelation();
        if (compositionDepVal.value != null) {
            smObject.appendDepVal(compositionDepVal.dep, null);
        }
        
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmModel oldParentModel = this.reparentedChild.getParent();
        assert (oldParentModel instanceof GmCompositeNode) : "This command should only be used if both old parent and new parent are instances of GmCompositeNode!";
        final GmCompositeNode oldParent = (GmCompositeNode) oldParentModel;
        oldParent.removeChild(this.reparentedChild);
        
        // attach the underlying {@link MObject element} to its new {@link
        // Element#getCompositionOwner() composition owner},
        try {
            smObject.appendDepVal(compositionDepVal.dep, (SmObjectImpl) this.newParentElement);
        } catch (@SuppressWarnings("unused") Exception e) {
            // Maybe new parent is not using the same dependency for composition
            // Try to find a fitting dependency
            MDependency newParentDep = getMmExpert().getDefaultCompositionDep(this.newParentElement, childElement);
            this.newParentElement.mGet(newParentDep).add(childElement);
        }
        
        this.reparentedChild.setLayoutData(this.newLayoutData);
        
        if (this.newParent.canContain(this.reparentedChild.getClass())) {
            // and finally attach the {@link GmNodeModel node} to its new {@link
            // GmCompositeNode container}.
            this.reparentedChild.setRoleInComposition(GmPackage.BODY_CONTENT_AS_SATELLITE);
            this.newParent.addChild(this.reparentedChild);
            GmCompositionLink link = new GmCompositionLink(this.newParent.getDiagram(),
                    this.newParent.getRepresentedRef());
            this.newParent.addStartingLink(link);
            this.reparentedChild.addEndingLink(link);
        } else {
            // The new parent does not support the node.
            // Ask the diagram to create a new node.
            if (this.newLayoutData instanceof Rectangle) {
                // reset the rectangle dimensions
                final Rectangle r = (Rectangle) this.newLayoutData;
                this.newLayoutData = new Rectangle(r.x, r.y, -1, -1);
            }
        
            GmNodeModel newChild = this.newParent.getDiagram()
                    .unmask(this.newParent,
                            this.reparentedChild.getRelatedElement(),
                            this.newLayoutData);
        
            // Remove child and add it again, so that its current role is correctly used.
            this.newParent.removeChild(newChild);
            newChild.setRoleInComposition(GmPackage.BODY_CONTENT_AS_SATELLITE);
            newChild.setLayoutData(this.newLayoutData);
            this.newParent.addChild(newChild);
        
            GmCompositionLink link = new GmCompositionLink(this.newParent.getDiagram(),
                    this.newParent.getRepresentedRef());
            this.newParent.addStartingLink(link);
            newChild.addEndingLink(link);
            // Delete the now unused child
            this.reparentedChild.delete();
        }
    }

    @objid ("d4cf6a38-b9b5-41fc-a82d-0dfe84228583")
    private MExpert getMmExpert() {
        return this.newParentElement.getMClass().getMetamodel().getMExpert();
    }

}
