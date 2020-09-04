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

package org.modelio.diagram.editor.statik.elements.requiredinterface;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.common.linktovoid.CreateLinkToVoidCommand;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Command that creates a provided interface connected to a required interface or the reverse.
 * 
 * @author cmarin
 */
@objid ("366fdb47-55b7-11e2-877f-002564c97630")
public class CreateConnectedConnectionCommand extends Command {
    @objid ("6386ae90-5bd5-11e2-9e33-00137282c51b")
    private EditPart requiredInterfacePart;

    @objid ("0ee3af5f-e4e0-465c-a348-09947ff2ec3a")
    private Point location;

    @objid ("5a97b6de-4328-4da0-acf6-63534e6273f6")
    private CreateConnectionRequest request;

    /**
     * Initialize the command.
     * @param request the creation request.
     * @param targetPart The target required interface link edit part
     * @param location The join location
     */
    @objid ("366fdb4c-55b7-11e2-877f-002564c97630")
    public CreateConnectedConnectionCommand(final CreateConnectionRequest request, final EditPart targetPart, final Point location) {
        this.request = request;
        this.requiredInterfacePart = targetPart;
        this.location = location;
    }

    @objid ("367161b9-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Request to create a provided interface
        EditPart diagramEditPart = this.requiredInterfacePart.getViewer().getContents();
        CreateConnectionRequest r1 = getRequestCopy();
        r1.setTargetEditPart(diagramEditPart);
        CreateLinkToVoidCommand c1 = (CreateLinkToVoidCommand) diagramEditPart.getCommand(r1);
        
        c1.execute();
        
        // Get the created element and edit part
        final MObject createdProvidedInterface = c1.getCreatedElement();
        final ConnectionEditPart provEditPart = (ConnectionEditPart) getEditPartFor(createdProvidedInterface);
        
        // Request to connect the provided to the required.
        ReconnectRequest req = new ReconnectRequest(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET);
        req.setConnectionEditPart(provEditPart);
        req.setTargetEditPart(this.requiredInterfacePart);
        req.setLocation(this.location);
        
        Command c2 = this.requiredInterfacePart.getCommand(req);
        if (c2 != null && c2.canExecute()) {
            c2.execute();
        }
    }

    @objid ("367161bc-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        // Request to create a provided interface
        EditPart diagramEditPart = this.requiredInterfacePart.getViewer().getContents();
        CreateConnectionRequest r1 = getRequestCopy();
        r1.setTargetEditPart(diagramEditPart);
        CreateLinkToVoidCommand c1 = (CreateLinkToVoidCommand) diagramEditPart.getCommand(r1);
        
        if (c1 == null || !c1.canExecute()) {
            return false;
        }
        
        // Check the target part is modifiable.
        GmModel tt = (GmModel) this.requiredInterfacePart.getModel();
        return MTools.getAuthTool().canModify(tt.getRelatedElement());
    }

    /**
     * @return a copy of the request.
     */
    @objid ("367161c1-55b7-11e2-877f-002564c97630")
    private CreateConnectionRequest getRequestCopy() {
        if (this.request instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest orig = (CreateBendedConnectionRequest) this.request;
            CreateBendedConnectionRequest ret = new CreateBendedConnectionRequest();
            ret.setExtendedData(orig.getExtendedData());
            ret.setFactory((CreationFactory) orig.getNewObject());
            ret.setLocation(orig.getLocation());
            ret.setSize(orig.getSize());
            ret.setSourceEditPart(orig.getSourceEditPart());
            ret.setStartCommand(orig.getStartCommand());
            ret.setTargetEditPart(orig.getTargetEditPart());
            ret.setType(orig.getType());
            ret.getData().setRoutingMode(orig.getData().getRoutingMode());
            ret.getData().setSrcPoint(orig.getData().getSrcPoint());
            for (Point p : orig.getData().getPath()) {
                ret.getData().getPath().add(p);
            }
        
            return ret;
        } else {
            CreateConnectionRequest ret = new CreateConnectionRequest();
            ret.setExtendedData(this.request.getExtendedData());
            ret.setFactory((CreationFactory) this.request.getNewObject());
            ret.setLocation(this.request.getLocation());
            ret.setSize(this.request.getSize());
            ret.setSourceEditPart(this.request.getSourceEditPart());
            ret.setStartCommand(this.request.getStartCommand());
            ret.setTargetEditPart(this.request.getTargetEditPart());
            ret.setType(this.request.getType());
            return ret;
        }
    }

    @objid ("367161c5-55b7-11e2-877f-002564c97630")
    private EditPart getEditPartFor(final MObject element) {
        // Search all gm related the element
        IGmObject gmModel = (IGmObject) this.requiredInterfacePart.getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        Collection<GmModel> models = gmDiagram.getAllGMRelatedTo(new MRef(element));
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) this.requiredInterfacePart.getViewer()
                                                                     .getEditPartRegistry()
                                                                     .get(model);
            if (editPart != null) {
                return editPart;
            }
        }
        return null;
    }

}
