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

package org.modelio.diagram.elements.core.link;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that moves the source of a GmLink to another location on the same node or another one.
 * 
 * @author cmarin
 */
@objid ("7ff10f5a-1dec-11e2-8cad-001ec947c8cc")
public class DefaultReconnectTargetCommand extends Command {
    @objid ("7ff10f5e-1dec-11e2-8cad-001ec947c8cc")
    private Object anchorModel;

    @objid ("7ff10f5f-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink gmLink;

    @objid ("7ff10f61-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkable newTargetNode;

    /**
     * Create the command.
     * 
     * @param gmLink The link to move.
     * @param newTarget The new target node.
     */
    @objid ("7ff10f63-1dec-11e2-8cad-001ec947c8cc")
    public DefaultReconnectTargetCommand(GmLink gmLink, IGmLinkable newTarget) {
        this.gmLink = gmLink;
        this.newTargetNode = newTarget;
    }

    @objid ("7ff10f68-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // The diagram must be modifiable
        if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
            return false;
        }
        
        // If the target changes, the source and the link elements must be modifiable
        IGmLinkable oldTargetNode = this.gmLink.getTo();
        if (oldTargetNode == null) {
            return true;
        }
        
        if (this.newTargetNode.getRepresentedRef().equals(oldTargetNode.getRepresentedRef())) {
            return true;
        } else {
            MObject link = this.gmLink.getRelatedElement();
            MObject newDest = this.newTargetNode.getRelatedElement();
            MExpert expert = link.getMClass().getMetamodel().getMExpert();
            MObject modelTarget = expert.getTarget(link);
            if (Objects.equals(modelTarget, newDest)) {
                // Model already has the 'new' target, no need to check modifiability
                return true;
            } else {
                if (link instanceof ModelElement) {
                    IMdaExpert mdaExpert = this.gmLink.getDiagram().getModelManager().getMdaExpert();
                    for (Stereotype linkStereotype : ((ModelElement) link).getExtension()) {
                        if (!mdaExpert.canTarget(linkStereotype, link, newDest)) {
                            return false;
                        }
                    }
                }
        
                // The old target and the link elements must be modifiable
                return isModifableElement(oldTargetNode) && isModifableElement(this.gmLink) && expert.canTarget(link, newDest);
            }
        }
    }

    @objid ("7ff10f6c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        updateLinkTarget();
        
        if (this.anchorModel != null) {
            this.gmLink.getPath().setTargetAnchor(this.anchorModel);
            this.gmLink.setLayoutData(new GmPath(this.gmLink.getPath()));
        }
    }

    /**
     * Set the model of the target anchor of the link.
     * 
     * @param anchorModel the model of the target anchor of the link
     */
    @objid ("7ff10f6f-1dec-11e2-8cad-001ec947c8cc")
    public void setAnchorModel(final Object anchorModel) {
        this.anchorModel = anchorModel;
    }

    @objid ("7ff10f74-1dec-11e2-8cad-001ec947c8cc")
    protected void updateLinkTarget() {
        final IGmLinkable oldTargetNode = this.gmLink.getTo();
        if (oldTargetNode != this.newTargetNode) {
            final MObject link = this.gmLink.getRelatedElement();
            final MObject newDest = this.newTargetNode.getRelatedElement();
            final MExpert expert = link.getMClass().getMetamodel().getMExpert();
        
            if (oldTargetNode != null) {
                final MObject oldDest = oldTargetNode.getRelatedElement();
                if (!newDest.equals(oldDest)) {
                    // Update Ob model
                    expert.setTarget(link, oldDest, newDest);
                }
                oldTargetNode.removeEndingLink(this.gmLink);
            } else {
                expert.setTarget(link, null, newDest);
            }
        
            // Update gm model
            this.newTargetNode.addEndingLink(this.gmLink);
        }
    }

    @objid ("7ff10f76-1dec-11e2-8cad-001ec947c8cc")
    private boolean isModifableElement(final IGmLinkable model) {
        MObject relatedElement = model.getRelatedElement();
        if (relatedElement != null && !relatedElement.isShell() && !relatedElement.isDeleted()) {
            return relatedElement.getStatus().isModifiable();
        }
        return true;
    }

}
