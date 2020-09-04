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

package org.modelio.diagram.editor.usecase.elements.system;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.vcore.model.api.MTools;

/**
 * UseCase-specific command resizing the {@link GmSystem} when a {@link UseCase} is moved or grows.
 */
@objid ("5e54de87-55b7-11e2-877f-002564c97630")
class SystemResizeCommand extends Command {
    @objid ("8daff820-fe17-4617-8bc9-97cb65c225ff")
    private GraphicalEditPart freeZoneEditPart;

    @objid ("dc3b98ea-903a-4fe6-ae6e-cd1d0c3597e8")
    private GraphicalEditPart childEditPart;

    @objid ("aa9b6a33-b701-4a83-a797-773e22af1bbb")
    private ChangeBoundsRequest request;

    @objid ("5e54de8c-55b7-11e2-877f-002564c97630")
    SystemResizeCommand(final GraphicalEditPart freeZoneEditPart, final GraphicalEditPart childEditPart, final ChangeBoundsRequest request) {
        this.freeZoneEditPart = freeZoneEditPart;
        this.childEditPart = childEditPart;
        this.request = request;
    }

    @objid ("5e54de94-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Resize the free zone
        Rectangle newRect = this.freeZoneEditPart.getFigure().getBounds().getCopy();
        Rectangle orig = newRect.getCopy();
        Rectangle useCaseBounds = this.childEditPart.getFigure().getBounds();
        
        newRect.union(this.request.getTransformedRectangle(useCaseBounds));
        
        Point moveDelta = newRect.getLocation().getTranslated(orig.getLocation().getNegated());
        Dimension sizeDelta = newRect.getSize().getShrinked(orig.getSize());
        
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        req.setSizeDelta(sizeDelta);
        req.setMoveDelta(moveDelta);
        req.setEditParts(this.freeZoneEditPart.getParent());
        
        Command resizeParentCommand = this.freeZoneEditPart.getParent().getCommand(req);
        if (resizeParentCommand != null && resizeParentCommand.canExecute()) {
            resizeParentCommand.execute();
        
            GmNodeModel childModel = (GmNodeModel) this.childEditPart.getModel();
            if (childModel.getLayoutData() instanceof Rectangle) {
                childModel.setLayoutData(((Rectangle) childModel.getLayoutData()).getTranslated(moveDelta));
            }
        }
    }

    @objid ("5e54de97-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(((GmSystemFreeZone) this.freeZoneEditPart.getModel()).getDiagram().getRelatedElement());
    }

}
