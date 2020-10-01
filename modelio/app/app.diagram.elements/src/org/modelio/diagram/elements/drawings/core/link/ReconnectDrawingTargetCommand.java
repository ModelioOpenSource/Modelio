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
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that moves the source of a GmLink to another location on the same node or another one.
 * 
 * @author cmarin
 */
@objid ("fdf53c2f-1794-4105-845c-b76d8ec25820")
public class ReconnectDrawingTargetCommand extends Command {
    @objid ("a14584cd-f9fd-45f0-8d68-ee357dc429b3")
    private Object anchorModel;

    @objid ("89fc259b-db33-4c60-8f2f-bedf4e946ae7")
    private final IGmDrawingLink gmLink;

    @objid ("8c5c331e-8ee9-4379-ae1e-ec951215670b")
    private final IGmDrawingLinkable newTargetNode;

    /**
     * Create the command.
     * 
     * @param gmLink The link to move.
     * @param newTarget The new target node.
     */
    @objid ("d2153680-f28b-4260-9362-ed7229ba8dc3")
    public ReconnectDrawingTargetCommand(IGmDrawingLink gmLink, IGmDrawingLinkable newTarget) {
        this.gmLink = gmLink;
        this.newTargetNode = newTarget;
    }

    @objid ("7d8b6200-69d0-42dc-8a47-b10f166e3906")
    @Override
    public boolean canExecute() {
        // The diagram must be modifiable
        return (MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement()));
    }

    @objid ("f4e20bd4-c13a-4893-9e78-e7b09d5870a4")
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
    @objid ("0acf1b13-0348-410c-aa22-44f670ba1857")
    public void setAnchorModel(final Object anchorModel) {
        this.anchorModel = anchorModel;
    }

    @objid ("a65fd41b-b2a2-4683-9d24-85c3cda4b2a9")
    protected void updateLinkTarget() {
        final IGmDrawingLinkable oldTargetNode = this.gmLink.getTo();
        if (oldTargetNode != this.newTargetNode) {
            // Update gm model
            this.newTargetNode.addEndingDrawingLink(this.gmLink);
        }
    }

}
