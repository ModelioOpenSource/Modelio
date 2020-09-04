/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.link.ortho;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.vcore.model.api.MTools;

/**
 * A command that synchronize connection model path data from the Connection figure bend points.
 */
@objid ("80421f55-1dec-11e2-8cad-001ec947c8cc")
public class TranslateBendpointsCommand extends Command {
    @objid ("80421f5f-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkObject model;

    @objid ("e13e9d0b-11f3-4a1f-9b9f-3a7d47f3c13c")
    private final Connection connection;

    @objid ("bf0617f8-0274-4d69-801d-b7b712f04257")
    private final List<Point> newPathData;

    /**
     * @param connectionEP the connection edit part to synchronize
     */
    @objid ("80421f60-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    public TranslateBendpointsCommand(final ConnectionEditPart connectionEP) {
        this.model = (IGmLinkObject) connectionEP.getModel();
        this.connection = (Connection) connectionEP.getFigure();
        List<Bendpoint> routingConstraint = (List<Bendpoint>) this.connection.getRoutingConstraint();
        this.newPathData = BendPointUtils.draw2dConstraintToModelConstraint(routingConstraint);
    }

    @objid ("80421f69-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        ConnectionAnchor currentSourceAnchor = this.connection.getSourceAnchor();
        ConnectionAnchor currentTargetAnchor = this.connection.getTargetAnchor();
        
        if (currentSourceAnchor==null || currentTargetAnchor==null) {
            // figure dead
            return;
        }
        
        GmPath lpath = new GmPath(this.model.getPath());
        lpath.setPathData(this.newPathData);
        this.model.setLayoutData(lpath);
    }

    @objid ("6f91d6ac-e6ee-48ac-b190-bd903ee65d29")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.model.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

}
