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
package org.modelio.diagram.elements.drawings.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that moves the source of a GmLink to another location on the same node or another one.
 * 
 * @author cmarin
 */
@objid ("023b3a8d-b54a-4ed6-8c75-82d4f8628294")
public class ReconnectDrawingSourceCommand extends Command {
    @objid ("51418d55-1412-4ea3-bda0-4af548f8bfe8")
    private final IGmDrawingLink gmLink;

    @objid ("3d14030e-1b06-4a8b-977b-4716dc1f661d")
    private final IGmDrawingLinkable newSrcNode;

    @objid ("8cec468c-fe43-49bf-b63f-1f0624184a0c")
    private Object anchorModel;

    /**
     * Create the command.
     * @param gmLink The link to move.
     * @param dest The new source node.
     */
    @objid ("9c16a3ea-eea8-49d6-9800-a41eceece13b")
    public  ReconnectDrawingSourceCommand(IGmDrawingLink gmLink, IGmDrawingLinkable dest) {
        this.gmLink = gmLink;
        this.newSrcNode = dest;
        
    }

    @objid ("06805a91-7527-4493-b953-ce6b23f95aeb")
    @Override
    public boolean canExecute() {
        // The diagram must be modifiable
        return (MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement()));
    }

    @objid ("d2007e43-a741-4692-be08-bacf70a66c58")
    @Override
    public void execute() {
        updateLinkSource();
        
        if (this.anchorModel != null) {
            GmPath newPath = new GmPath(this.gmLink.getPath());
            newPath.setSourceAnchor(this.anchorModel);
            this.gmLink.setLayoutData(newPath);
        }
        
    }

    /**
     * Set the model of the source anchor of the link.
     * @param anchorModel the model of the source anchor of the link
     */
    @objid ("c7e5c626-b9f0-4293-9f51-610762dd67a3")
    public void setAnchorModel(final Object anchorModel) {
        this.anchorModel = anchorModel;
    }

    @objid ("1899e337-917b-453b-9531-200040d96279")
    protected void updateLinkSource() {
        final IGmDrawingLinkable oldSourceNode = this.gmLink.getFrom();
        if (oldSourceNode != this.newSrcNode) {
            if (oldSourceNode != null) {
        
                // Update gm model
                oldSourceNode.removeStartingDrawingLink(this.gmLink);
            }
            this.newSrcNode.addStartingDrawingLink(this.gmLink);
        }
        
    }

    @objid ("351bb5f5-8f42-4149-b3d7-817ef0e82a32")
    private boolean isModifableElement(final IGmLinkable model) {
        MObject relatedElement = model.getRelatedElement();
        if (relatedElement != null && !relatedElement.isShell() && !relatedElement.isDeleted()) {
            return relatedElement.getStatus().isModifiable();
        }
        return true;
    }

}
