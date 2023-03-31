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
@objid ("7feead19-1dec-11e2-8cad-001ec947c8cc")
public class DefaultReconnectSourceCommand extends Command {
    @objid ("c2fee6d9-267d-494b-b2e3-52e55acf58ca")
    private boolean gmModelOnly;

    @objid ("7feead1d-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink gmLink;

    @objid ("7feead1f-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkable newSrcNode;

    @objid ("7feead21-1dec-11e2-8cad-001ec947c8cc")
    private Object anchorModel;

    /**
     * Create the command.
     * @param gmLink The link to move.
     * @param dest The new source node.
     * @param gmModelOnly if <code>true</code>, only the Gm model changes after executing the command.
     */
    @objid ("7feead22-1dec-11e2-8cad-001ec947c8cc")
    public  DefaultReconnectSourceCommand(GmLink gmLink, IGmLinkable dest, Boolean gmModelOnly) {
        this.gmLink = gmLink;
        this.newSrcNode = dest;
        this.gmModelOnly = Boolean.TRUE.equals(gmModelOnly);
        
    }

    @objid ("7feead27-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // The diagram must be modifiable
        if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
            return false;
        }
        
        // If the source changes, The old and new source and the link elements must be modifiable
        final IGmLinkable oldSrcNode = this.gmLink.getFrom();
        if (oldSrcNode == null) {
            return true;
        }
        if (this.newSrcNode.getRepresentedRef().equals(oldSrcNode.getRepresentedRef())) {
            return true;
        } else {
            MObject link = this.gmLink.getRelatedElement();
            MObject newSrc = this.newSrcNode.getRelatedElement();
            MExpert expert = link.getMClass().getMetamodel().getMExpert();
            MObject modelTarget = expert.getTarget(link);
            if (Objects.equals(modelTarget, newSrc)) {
                // Model already has the 'new' target, no need to check modifiability
                return true;
            } else {
                if (link instanceof ModelElement) {
                    IMdaExpert mdaExpert = this.gmLink.getDiagram().getModelManager().getMdaExpert();
                    for (Stereotype linkStereotype : ((ModelElement) link).getExtension()) {
                        if (!mdaExpert.canSource(linkStereotype, link, newSrc)) {
                            return false;
                        }
                    }
                }
            }
        
            // The old and new source and the link elements must be modifiable
            return isModifableElement(oldSrcNode) && isModifableElement(this.newSrcNode) && isModifableElement(this.gmLink) && expert.canSource(link, newSrc);
        }
        
    }

    @objid ("7feead2b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        updateLinkSource();
        
        if (this.anchorModel != null) {
            GmPath newPath = new GmPath(this.gmLink.getPath());
            newPath.setSourceAnchor(this.anchorModel);
            newPath.setSourceRake(null); // unrake from source
            this.gmLink.setLayoutData(newPath);
        }
        
    }

    /**
     * Set the model of the source anchor of the link.
     * @param anchorModel the model of the source anchor of the link
     */
    @objid ("7ff10f4b-1dec-11e2-8cad-001ec947c8cc")
    public void setAnchorModel(final Object anchorModel) {
        this.anchorModel = anchorModel;
    }

    @objid ("7ff10f50-1dec-11e2-8cad-001ec947c8cc")
    protected void updateLinkSource() {
        final IGmLinkable oldSourceNode = this.gmLink.getFrom();
        if (oldSourceNode != this.newSrcNode) {
            final MObject link = this.gmLink.getRelatedElement();
            final MObject newSource = this.newSrcNode.getRelatedElement();
            final MExpert expert = link.getMClass().getMetamodel().getMExpert();
        
            if (oldSourceNode != null) {
                final MObject oldSource = oldSourceNode.getRelatedElement();
                if (!newSource.equals(oldSource)) {
                    updateObModel(expert, link, oldSource, newSource);
                }
        
                // Update gm model
                oldSourceNode.removeStartingLink(this.gmLink);
            } else {
                updateObModel(expert, link, null, newSource);
            }
        
            // Update gm model
            this.newSrcNode.addStartingLink(this.gmLink);
        }
        
    }

    @objid ("41313a5a-e597-4ef7-9169-678a9d074478")
    private void updateObModel(final MExpert expert, final MObject link, final MObject oldSource, final MObject newSource) {
        if (!this.gmModelOnly) {
            expert.setSource(link, oldSource, newSource);
        }
        
    }

    @objid ("7ff10f52-1dec-11e2-8cad-001ec947c8cc")
    private boolean isModifableElement(final IGmLinkable model) {
        MObject relatedElement = model.getRelatedElement();
        if (relatedElement != null && !relatedElement.isShell() && !relatedElement.isDeleted()) {
            return relatedElement.getStatus().isModifiable();
        }
        return true;
    }

}
