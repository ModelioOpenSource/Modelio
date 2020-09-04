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
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command that will unmask a note and the link between it and the annoted model element.
 * 
 * @author fpoyer
 */
@objid ("7e29ae57-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskNoteCommand extends Command {
    @objid ("7e29ae5b-1dec-11e2-8cad-001ec947c8cc")
    private final Note theNote;

    @objid ("7e29ae5d-1dec-11e2-8cad-001ec947c8cc")
    private final Object constraint;

    @objid ("7e29ae5f-1dec-11e2-8cad-001ec947c8cc")
    private final IGmDiagram diagram;

    @objid ("7e2c107e-1dec-11e2-8cad-001ec947c8cc")
    private final AbstractDiagramEditPart host;

    @objid ("14b62e62-73b5-4040-ba6b-d709cb6b06e4")
    private final Point dropLocation;

    /**
     * C'tor.
     * @param theNote the note to unmask.
     * @param host the edit part of the diagram in which to unmask it.
     * @param initialLayoutData the initial layout data for the node part
     * @param dropLocation the drop location retreived from the request.
     */
    @objid ("7e2c1084-1dec-11e2-8cad-001ec947c8cc")
    public UnmaskNoteCommand(final Note theNote, final AbstractDiagramEditPart host, final Object initialLayoutData, final Point dropLocation) {
        this.theNote = theNote;
        this.host = host;
        this.diagram = (IGmDiagram) host.getModel();
        this.constraint = initialLayoutData;
        this.dropLocation = dropLocation;
    }

    @objid ("7e2c1091-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // unmask the node part
        GmNodeModel targetModel = this.diagram.unmaskAsChild(this.theNote, this.constraint);
        
        ModelElement subject = this.theNote.getSubject();
        if(!(subject instanceof AbstractDiagram) && !(subject instanceof BpmnProcess)){
            // Simulate creation of the link:
            // 1 - start creation of a connection
            final CreateConnectionRequest req = new CreateConnectionRequest();
            req.setLocation(this.dropLocation);
            req.setSize(new Dimension(-1, -1));
            req.setFactory(new ModelioLinkCreationContext(this.theNote));
            req.setType(RequestConstants.REQ_CONNECTION_START);
        
            // Look for edit part of subject element... If none found, unmask it.
            EditPart sourceEditPart = getEditPartFor(subject, req);
            if (sourceEditPart == null) {
                unmaskElement(subject, this.dropLocation.getTranslated(-50, 0));
                sourceEditPart = getEditPartFor(subject, req);
                if (sourceEditPart == null) {
                    // Failed to get subject element, revert what was done until now and abort.
                    targetModel.delete();
                    return;
                }
            }
            IGmLinkable sourceModel = (IGmLinkable) sourceEditPart.getModel();
            // Unmask the link
            IGmLink link = this.diagram.unmaskLink(this.theNote);
            targetModel.addEndingLink(link);
            sourceModel.addStartingLink(link);
        }
    }

    @objid ("7e2c1094-1dec-11e2-8cad-001ec947c8cc")
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

    @objid ("7e2c10a0-1dec-11e2-8cad-001ec947c8cc")
    private void unmaskElement(final MObject element, final Point location) {
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { element });
        dropRequest.setLocation(location);
        dropRequest.setSmart(false);
        EditPart targetEditPart = this.host.getTargetEditPart(dropRequest);
        Command command = targetEditPart.getCommand(dropRequest);
        if (command != null && command.canExecute()) {
            command.execute();
        }
    }

    @objid ("7e2c10a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // the diagram must be modifiable
        return MTools.getAuthTool().canModify(((IGmDiagram) this.host.getModel()).getRelatedElement());
    }

}
