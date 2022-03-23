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
package org.modelio.diagram.elements.core.link.ortho;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;

/**
 * Synchronize the routing constraint from the points computed by  {@link AutoOrthogonalRouter}.
 * 
 * @since 5.0.2
 */
@objid ("a9ff012e-7843-4812-ace0-d177e2e90c39")
public class AutoOrthogonalRouterSynchronizeConstraintCommand extends Command {
    @objid ("50c55f66-9e46-423b-9386-be2349b29c2d")
    private ConnectionEditPart connectionEP;

    @objid ("6f70ca85-3b3c-4d38-9848-f12385fb3906")
    public  AutoOrthogonalRouterSynchronizeConstraintCommand(final ConnectionEditPart connectionEP) {
        this.connectionEP = connectionEP;
    }

    @objid ("4bfe59c2-e063-46d3-90f2-3386f0f112fc")
    @Override
    public void execute() {
        AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                .setCleanupManualPoints(true)
                .setIgnoreAutomaticPoints(false);
        
        Connection c = (Connection) this.connectionEP.getFigure();
        Object initConstraint = c.getRoutingConstraint();
        
        @SuppressWarnings ("unchecked")
        List<MPoint> newConstraint = router.computeMPointRoute(c, (List<MPoint>) initConstraint);
        
        // remove first and last points that are anchors
        if (!newConstraint.isEmpty())
            newConstraint.remove(0);
        
        if (!newConstraint.isEmpty())
            newConstraint.remove(newConstraint.size()-1);
        
        // Fast exit if no change
        if (newConstraint.equals(initConstraint))
            return;
        
        
        IGmLinkObject model = (IGmLinkObject) this.connectionEP.getModel();
        IGmPath path = new GmPath(model.getPath());
        
        path.setPathData(newConstraint);
        model.setLayoutData(path);
        
    }

}
