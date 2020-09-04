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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command for unmasking a required/provided interface in a diagram.
 */
@objid ("7e274bfb-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskLinkToVoidCommand extends Command {
    @objid ("7e274c02-1dec-11e2-8cad-001ec947c8cc")
    private AbstractDiagramEditPart host;

    @objid ("7e274c03-1dec-11e2-8cad-001ec947c8cc")
    private MObject linkElement;

    @objid ("7e274c04-1dec-11e2-8cad-001ec947c8cc")
    private MObject fromElement;

    @objid ("7e274c05-1dec-11e2-8cad-001ec947c8cc")
    private MObject toElement;

    @objid ("25b5fa40-1efc-439a-a992-285cd944cbd9")
    private Point dropLocation;

    /**
     * C'tor.
     * @param link the link element to unmask.
     * @param from the source element
     * @param to the destination element (should usually be the diagram)
     * @param host the edit part that was asked to handle the unmasking.
     * @param dropLocation the drop location.
     */
    @objid ("7e274c06-1dec-11e2-8cad-001ec947c8cc")
    public UnmaskLinkToVoidCommand(final MObject link, final MObject from, final MObject to, final AbstractDiagramEditPart host, final Point dropLocation) {
        this.linkElement = link;
        this.fromElement = from;
        this.toElement = to;
        this.dropLocation = dropLocation;
        this.host = host;
    }

    @objid ("7e29ae23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (this.fromElement == null || this.toElement == null) {
            return false;
        }
        
        // the diagram must be modifiable
        return MTools.getAuthTool().canModify(((IGmDiagram) this.host.getModel()).getRelatedElement());
    }

    @objid ("7e29ae28-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // Simulate creation of the link:
        // 1 - start creation of a connection
        final CreateConnectionRequest req = new CreateConnectionRequest();
        req.setLocation(this.dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioCreationContext(this.linkElement));
        req.setType(LinkToVoidConstants.REQ_LINKTOVOID_START);
        
        // Look for edit part of from element... If none found, unmask it.
        EditPart sourceEditPart = getEditPartFor(this.fromElement, req);
        boolean sourceMissing = false;
        if (sourceEditPart == null) {
            sourceMissing = true;
            unmaskElement(this.fromElement, this.dropLocation.getTranslated(-50, 0));
            sourceEditPart = getEditPartFor(this.fromElement, req);
            if (sourceEditPart == null) {
                return;
            }
        }
        
        req.setSourceEditPart(sourceEditPart);
        req.setTargetEditPart(sourceEditPart);
        req.setLocation(getAbsoluteFigureCenter(sourceEditPart));
        
        req.setStartCommand(sourceEditPart.getCommand(req));
        
        // 2 - end the creation of the connection
        req.setType(LinkToVoidConstants.REQ_LINKTOVOID_END);
        
        // Look for edit part of to element... if none found, unmask it.
        EditPart targetEditPart = getEditPartFor(this.toElement, req);
        if (targetEditPart == null) {
            Point loc = this.dropLocation;
            if (sourceMissing) {
                loc = this.dropLocation.getTranslated(50, 0);
            }
            unmaskElement(this.toElement, loc);
            targetEditPart = getEditPartFor(this.toElement, req);
            if (targetEditPart == null) {
                return;
            }
        }
        req.setTargetEditPart(targetEditPart);
        
        req.setLocation(this.dropLocation);
        // req.setLocation(getAbsoluteFigureCenter(targetEditPart));
        
        Command connectionCreationCommand = targetEditPart.getCommand(req);
        if (connectionCreationCommand != null && connectionCreationCommand.canExecute()) {
            connectionCreationCommand.execute();
        }
    }

    @objid ("7e29ae2b-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getEditPartFor(final MObject element, final CreateConnectionRequest req) {
        // Search all gm related the element
        Collection<GmModel> models = ((IGmDiagram) getHost().getModel()).getAllGMRelatedTo(new MRef(element));
        // This boolean will be used to note that the searched End was found
        // unmasked at least once.
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(model);
            if (editPart != null) {
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(req);
                if (targetEditPart != null) {
                    return targetEditPart;
                }
            }
        }
        return null;
    }

    @objid ("7e29ae37-1dec-11e2-8cad-001ec947c8cc")
    private AbstractDiagramEditPart getHost() {
        return this.host;
    }

    /**
     * @param element the element to unmask
     * @param location the drop location
     */
    @objid ("7e29ae3b-1dec-11e2-8cad-001ec947c8cc")
    private void unmaskElement(final MObject element, final Point location) {
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { element });
        dropRequest.setLocation(location);
        
        EditPart targetEditPart = getHost().getTargetEditPart(dropRequest);
        Command command = targetEditPart.getCommand(dropRequest);
        if (command != null && command.canExecute()) {
            command.execute();
        }
    }

    /**
     * Get the center of te figure bounds in absolute coordinates.
     * @param editPart a figure edit part
     * @return the center of the figure.
     */
    @objid ("7e29ae44-1dec-11e2-8cad-001ec947c8cc")
    private Point getAbsoluteFigureCenter(final EditPart editPart) {
        IFigure fig = ((AbstractGraphicalEditPart) editPart).getFigure();
        fig.getUpdateManager().performValidation();
        // fig.getParent().validate();
        Point ret = fig.getBounds().getCenter();
        fig.translateToAbsolute(ret);
        return ret;
    }

}
