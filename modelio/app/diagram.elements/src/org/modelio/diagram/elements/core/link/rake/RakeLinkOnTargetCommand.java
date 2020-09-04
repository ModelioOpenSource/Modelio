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

package org.modelio.diagram.elements.core.link.rake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmLinkRake;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that add a link to an existing rake or create a rake from 2 links.
 * <p>
 * The rake branches merge on the target side at the given location.
 * 
 * @author cmarin
 */
@objid ("806aa737-1dec-11e2-8cad-001ec947c8cc")
public class RakeLinkOnTargetCommand extends Command {
    @objid ("806aa73b-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink toConnect;

    @objid ("806aa73d-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink rakeLink;

    @objid ("806aa743-1dec-11e2-8cad-001ec947c8cc")
    private Object gmTargetAnchor;

    @objid ("fc95b0e0-3b53-454d-af5a-82ab47760500")
    private final Point rakeLocation;

    /**
     * Initializes the command.
     * @param toConnect The link to connect to the rake.
     * @param rakeLink The rake link. This link may already be in rake mode or not.
     * @param loc The rake merge location. Ignored if the main link is already in rake mode.
     * @param gmTargetAnchor The new target anchor model.
     */
    @objid ("806aa744-1dec-11e2-8cad-001ec947c8cc")
    public RakeLinkOnTargetCommand(GmLink toConnect, GmLink rakeLink, Point loc, final Object gmTargetAnchor) {
        this.rakeLink = rakeLink;
        this.toConnect = toConnect;
        this.rakeLocation = loc;
        this.gmTargetAnchor = gmTargetAnchor;
    }

    @objid ("806aa74e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        updateLinkTarget();
        
        if (this.rakeLink.getPath().getTargetRake() == null) {
            setRakeMode();
        }
        
        // Make toConnect and rakeLink share the same path data and target anchor.
        GmPath newConnectPath = new GmPath(this.toConnect.getPath());
        newConnectPath.setPathData(this.rakeLink.getPath().getPathData());
        newConnectPath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        newConnectPath.setTargetAnchor(this.rakeLink.getPath().getTargetAnchor());
        newConnectPath.setTargetRake(this.rakeLink.getPath().getTargetRake());
        
        this.toConnect.setLayoutData(newConnectPath);
    }

    @objid ("806aa751-1dec-11e2-8cad-001ec947c8cc")
    private void setRakeMode() {
        // Create rake
        GmLinkRake targetRake = new GmLinkRake();
        targetRake.setSharedAnchor(this.gmTargetAnchor);
        
        // Create rake constraint
        RakeConstraint rakeData = new RakeConstraint();
        rakeData.setTargetRakeAnchor(new XYAnchor(this.rakeLocation));
        
        GmPath path = new GmPath(this.rakeLink.getPath());
        path.setPathData(rakeData);
        path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        path.setTargetAnchor(this.gmTargetAnchor);
        path.setTargetRake(targetRake);
        
        this.rakeLink.setLayoutData(path);
    }

    @objid ("806aa753-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (this.toConnect.getStyleKey(MetaKey.CONNECTIONROUTER) != null &&
                (this.rakeLink.getStyleKey(MetaKey.CONNECTIONROUTER) != null) &&
                (this.toConnect.getClass() == this.rakeLink.getClass())) {
            return MTools.getAuthTool().canModify(this.toConnect.getDiagram().getRelatedElement());
        }
        return false;
    }

    @objid ("806aa758-1dec-11e2-8cad-001ec947c8cc")
    protected void updateLinkTarget() {
        final MObject link = this.toConnect.getRelatedElement();
        final MObject newDest = this.rakeLink.getToElement();
        final IGmLinkable oldTargetNode = this.toConnect.getTo();
        final MExpert expert = link.getMClass().getMetamodel().getMExpert();
        
        if (oldTargetNode != null) {
            final MObject oldDest = oldTargetNode.getRelatedElement();
            if (!newDest.equals(oldDest)) {
                // Update Ob model
                expert.setTarget(link, oldDest, newDest);
            }
            oldTargetNode.removeEndingLink(this.toConnect);
        } else {
            expert.setTarget(link, null, newDest);
        }
        
        // Update gm model
        this.rakeLink.getTo().addEndingLink(this.toConnect);
    }

}
