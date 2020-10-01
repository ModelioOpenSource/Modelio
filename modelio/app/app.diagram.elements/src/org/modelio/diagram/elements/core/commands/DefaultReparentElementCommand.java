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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} reparent <code>command</code> that:
 * <ul>
 * <li>orphan the underlying {@link MObject element} from its previous {@link MObject#getCompositionOwner() composition owner},</li>
 * <li>orphan the {@link GmNodeModel node} from its previous {@link GmCompositeNode container},</li>
 * <li>attach the underlying {@link MObject element} to its new {@link MObject#getCompositionOwner() composition owner},</li>
 * <li>and finally attach the {@link GmNodeModel node} to its new {@link GmCompositeNode container}.</li>
 * </ul>
 * <br>
 * This <code>command</code> is ONLY meant to be used if both containers are {@link GmCompositeNode}
 * 
 * @author fpoyer
 * @see org.modelio.diagram.elements.core.node.GmCompositeNode
 */
@objid ("7f397a62-1dec-11e2-8cad-001ec947c8cc")
public class DefaultReparentElementCommand extends Command {
    /**
     * The new layout data of the reparented child.
     */
    @objid ("7f397a66-1dec-11e2-8cad-001ec947c8cc")
    private Object newLayoutData;

    /**
     * The new parent of the reparented child.
     */
    @objid ("7f397a68-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode newParent;

    /**
     * The new parent element (might be different from the element of the new parent node).
     */
    @objid ("7f397a6a-1dec-11e2-8cad-001ec947c8cc")
    private MObject newParentElement;

    /**
     * The child that is being reparented.
     */
    @objid ("7f397a6c-1dec-11e2-8cad-001ec947c8cc")
    private GmNodeModel reparentedChild;

    /**
     * Default C'tor.
     * 
     * @param newParentElement the MObject that will be the new parent of the element represented by the reparented node.
     * @param newParent the composite node that will be the new parent of the reparented node.
     * @param reparentedChild the reparented node.
     * @param newLayoutData the new layout data of the reparented node.
     */
    @objid ("7f397a6e-1dec-11e2-8cad-001ec947c8cc")
    public DefaultReparentElementCommand(MObject newParentElement, GmCompositeNode newParent, GmNodeModel reparentedChild, Object newLayoutData) {
        super();
        this.newParentElement = newParentElement;
        this.newParent = newParent;
        this.reparentedChild = reparentedChild;
        this.newLayoutData = newLayoutData;
    }

    @objid ("7f3bdc95-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final MObject childElement = this.reparentedChild.getRelatedElement();
        
        if (this.newParentElement == null || childElement == null) {
            return false;
        }
        
        if (this.newParentElement.equals(childElement)) {
            return false;
        }
        
        // The target parent composition path must not contain the moved element
        // 'i' is to shield against existing composition graph cycle.
        MObject p = this.newParentElement;
        for (int i = 0; p != null && i <= 10000; i++, p = p.getCompositionOwner()) {
            if (p.equals(childElement) || i == 10000) {
                return false;
            }
        }
        
        // The diagram must be valid and modifiable.
        IGmDiagram diagram = this.reparentedChild.getDiagram();
        if (diagram == null || !MTools.getAuthTool().canModify(diagram.getRelatedElement())) {
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
        final MExpert expert = childElement.getMClass().getMetamodel().getMExpert();
        return expert.canCompose(this.newParentElement, childElement, null);
    }

    @objid ("7f3bdc9a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // orphan the underlying {@link MObject element} from its previous
        // {@link MObject#getCompositionOwner() composition owner},
        final MObject childElement = this.reparentedChild.getRelatedElement();
        assert (childElement != null) : "cannot reparent: child element is null";
        final MObject oldParentElement = childElement.getCompositionOwner();
        
        MDependency oldParentDependency = null;
        for (MDependency dep : oldParentElement.getMClass().getDependencies(true)) {
            if (oldParentElement.mGet(dep).contains(childElement)) {
                oldParentDependency = dep;
                break;
            }
        }
        oldParentElement.mGet(oldParentDependency).remove(childElement);
        
        // orphan the {@link GmNodeModel node} from its previous {@link
        // GmCompositeNode container},
        final GmModel oldParentModel = this.reparentedChild.getParent();
        assert (oldParentModel instanceof GmCompositeNode) : "This command should only be used if both old parent and new parent are instances of GmCompositeNode!";
        final GmCompositeNode oldParent = (GmCompositeNode) oldParentModel;
        oldParent.removeChild(this.reparentedChild);
        
        // attach the underlying {@link MObject element} to its new {@link
        // MObject#getCompositionOwner() composition owner},
        try {
            this.newParentElement.mGet(oldParentDependency).add(childElement);
        } catch (@SuppressWarnings ("unused") Exception e) {
            // Maybe new parent is not using the same dependency for composition
            // Try to find a fitting dependency
            final MExpert expert = childElement.getMClass().getMetamodel().getMExpert();
            MDependency newParentDep = expert.getDefaultCompositionDep(this.newParentElement, childElement);
            if (newParentDep != null) {
                this.newParentElement.mGet(newParentDep).add(childElement);
            }
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
        
            this.newParent.getDiagram().unmask(this.newParent, this.reparentedChild.getRelatedElement(), this.newLayoutData);
        
            // Delete the now unused child
            this.reparentedChild.delete();
        }
    }

}
