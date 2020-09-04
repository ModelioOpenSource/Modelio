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
@objid ("806844f6-1dec-11e2-8cad-001ec947c8cc")
public class RakeLinkOnSourceCommand extends Command {
    @objid ("806844fa-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink toConnect;

    @objid ("806844fc-1dec-11e2-8cad-001ec947c8cc")
    private final GmLink rakeLink;

    @objid ("80684502-1dec-11e2-8cad-001ec947c8cc")
    private Object gmSourceAnchor;

    @objid ("d7c87fdc-e66e-49da-9e65-ad75d843b17e")
    private final Point rakeLocation;

    /**
     * Initializes the command.
     * @param toConnect The link to connect to the rake.
     * @param rakeLink The rake link. This link may already be in rake mode or not.
     * @param loc The rake merge location. Ignored if the main link is already in rake mode.
     * @param gmSourceAnchor the new source anchor model.
     */
    @objid ("80684503-1dec-11e2-8cad-001ec947c8cc")
    public RakeLinkOnSourceCommand(GmLink toConnect, GmLink rakeLink, Point loc, final Object gmSourceAnchor) {
        this.rakeLink = rakeLink;
        this.toConnect = toConnect;
        this.rakeLocation = loc;
        this.gmSourceAnchor = gmSourceAnchor;
    }

    @objid ("8068450d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        updateLinkSource();
        if (this.rakeLink.getPath().getSourceRake() == null) {
            setRakeMode();
        }
        
        // Make toConnect and rakeLink share the same path data.
        GmPath newConnectPath = new GmPath(this.toConnect.getPath());
        newConnectPath.setPathData(this.rakeLink.getPath().getPathData());
        newConnectPath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        newConnectPath.setSourceAnchor(this.rakeLink.getPath().getSourceAnchor());
        newConnectPath.setSourceRake(this.rakeLink.getPath().getSourceRake());
        
        this.toConnect.setLayoutData(newConnectPath);
    }

    @objid ("80684510-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (this.toConnect.getStyleKey(MetaKey.CONNECTIONROUTER) != null &&
                (this.rakeLink.getStyleKey(MetaKey.CONNECTIONROUTER) != null) &&
                (this.toConnect.getClass() == this.rakeLink.getClass())) {
            return MTools.getAuthTool().canModify(this.toConnect.getDiagram().getRelatedElement());
        }
        return false;
    }

    @objid ("80684515-1dec-11e2-8cad-001ec947c8cc")
    private void setRakeMode() {
        // Create rake
        GmLinkRake sourceRake = new GmLinkRake();
        sourceRake.setSharedAnchor(this.gmSourceAnchor);
        
        // Create rake constraint
        final RakeConstraint rakeData = new RakeConstraint();
        rakeData.setSourceRakeAnchor(new XYAnchor(this.rakeLocation));
        
        final GmPath path = new GmPath(this.rakeLink.getPath());
        path.setPathData(rakeData);
        path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        path.setSourceAnchor(this.gmSourceAnchor);
        path.setSourceRake(sourceRake);
        this.rakeLink.setLayoutData(path);
    }

    @objid ("80684517-1dec-11e2-8cad-001ec947c8cc")
    protected void updateLinkSource() {
        final MObject link = this.toConnect.getRelatedElement();
        final MObject newSource = this.rakeLink.getFromElement();
        final IGmLinkable oldSourceNode = this.toConnect.getFrom();
        final MExpert expert = link.getMClass().getMetamodel().getMExpert();
        
        if (oldSourceNode != null) {
            final MObject oldSource = oldSourceNode.getRelatedElement();
            if (!newSource.equals(oldSource)) {
                // Update Ob model
                expert.setSource(link, oldSource, newSource);
            }
        
            // Update gm model
            oldSourceNode.removeStartingLink(this.toConnect);
        } else {
            expert.setSource(link, null, newSource);
        }
        this.rakeLink.getFrom().addStartingLink(this.toConnect);
    }

}
