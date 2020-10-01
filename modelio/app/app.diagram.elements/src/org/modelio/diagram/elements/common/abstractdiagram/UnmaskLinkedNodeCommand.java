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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command that will unmask a note and the link between it and the annoted model element.
 * 
 * @author fpoyer
 */
@objid ("7e24e9ab-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskLinkedNodeCommand extends Command {
    @objid ("7e24e9af-1dec-11e2-8cad-001ec947c8cc")
    private final MObject theElement;

    @objid ("7e24e9b1-1dec-11e2-8cad-001ec947c8cc")
    private final Object constraint;

    @objid ("7e24e9b3-1dec-11e2-8cad-001ec947c8cc")
    private final IGmDiagram diagram;

    @objid ("7e24e9b5-1dec-11e2-8cad-001ec947c8cc")
    private final AbstractDiagramEditPart host;

    @objid ("fdfff5a2-fdf5-4d65-9dff-5baf4579e744")
    private final Point dropLocation;

    /**
     * C'tor.
     * 
     * @param theElement the linked node element to unmask.
     * @param host the edit part of the diagram in which to unmask it.
     * @param initialLayoutData the initial layout data for the node part
     * @param dropLocation the drop location retrieved from the request.
     */
    @objid ("7e274bcb-1dec-11e2-8cad-001ec947c8cc")
    public UnmaskLinkedNodeCommand(final MObject theElement, final AbstractDiagramEditPart host, final Object initialLayoutData, final Point dropLocation) {
        this.theElement = theElement;
        this.host = host;
        this.diagram = (IGmDiagram) host.getModel();
        this.constraint = initialLayoutData;
        this.dropLocation = dropLocation;
    }

    @objid ("7e274bd8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // the diagram must be modifiable
        return MTools.getAuthTool().canModify(this.host.getModel().getRelatedElement());
    }

    @objid ("7e274bdd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // unmask the node part
        GmNodeModel targetModel = this.diagram.unmaskAsChild(this.theElement, this.constraint);
        
        // Unmask the link
        IGmLink link = this.diagram.unmaskLink(this.theElement);
        
        // Create a connection creation request
        final CreateConnectionRequest req = new CreateConnectionRequest();
        req.setLocation(this.dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioLinkCreationContext(this.theElement));
        req.setType(RequestConstants.REQ_CONNECTION_START);
        
        // Look for edit part of source element... If none found, unmask it.
        MObject srcElement = link.getFromElement();
        EditPart sourceEditPart = getEditPartFor(srcElement, req);
        if (sourceEditPart == null) {
            unmaskElement(srcElement, this.dropLocation.getTranslated(-50, 0));
            sourceEditPart = getEditPartFor(srcElement, req);
            if (sourceEditPart == null) {
                // Failed to get subject element, revert what was done until now and abort.
                targetModel.delete();
                link.delete();
                return;
            }
        }
        IGmLinkable sourceModel = (IGmLinkable) sourceEditPart.getModel();
        
        // Attach the link to the source and target nodes
        targetModel.addEndingLink(link);
        sourceModel.addStartingLink(link);
    }

    @objid ("7e274be0-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getEditPartFor(final MObject element, final CreateConnectionRequest req) {
        // Search all gm related the element
        Collection<GmModel> models = this.diagram.getAllGMRelatedTo(new MRef(element));
        // This boolean will be used to note that the searched End was found
        // unmasked at least once.
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) this.host.getViewer().getEditPartRegistry().get(model);
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

    @objid ("7e274bec-1dec-11e2-8cad-001ec947c8cc")
    private void unmaskElement(final MObject element, final Point location) {
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { element });
        dropRequest.setLocation(location);
        EditPart targetEditPart = this.host.getTargetEditPart(dropRequest);
        Command command = targetEditPart.getCommand(dropRequest);
        if (command != null && command.canExecute()) {
            command.execute();
        }
    }

}
