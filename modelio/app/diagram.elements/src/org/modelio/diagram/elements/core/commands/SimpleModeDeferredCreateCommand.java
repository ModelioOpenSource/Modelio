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

package org.modelio.diagram.elements.core.commands;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that switches the representation mode to
 * {@link org.modelio.diagram.styles.core.StyleKey.RepresentationMode#STRUCTURED STRUCTURED},
 * then defers a {@link CreateRequest} to a child edit part of the sender.
 * <p>
 * The actual edit part is found by calling {@link GmCompositeNode#getCompositeFor(Class)} on the sender, then looking for its edit
 * part.
 * 
 * @author cmarin
 */
@objid ("7f47c869-1dec-11e2-8cad-001ec947c8cc")
public class SimpleModeDeferredCreateCommand extends Command {
    @objid ("7f47c871-1dec-11e2-8cad-001ec947c8cc")
    private Map<?,?> editPartRegistry;

    @objid ("7f47c870-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode gmComposite;

    @objid ("67109058-1e83-11e2-8cad-001ec947c8cc")
    private CreateRequest req;

    /**
     * Create a deferred command.
     * @param req The creation request.
     * @param sender The edit part sending the request
     */
    @objid ("7f47c875-1dec-11e2-8cad-001ec947c8cc")
    public SimpleModeDeferredCreateCommand(CreateRequest req, EditPart sender) {
        this.req = req;
        this.gmComposite = (GmCompositeNode) sender.getModel();
        this.editPartRegistry = sender.getViewer().getEditPartRegistry();
    }

    @objid ("7f47c87e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final GmCompositeNode gmTarget = getTargetNode();
        if (gmTarget != null) {
            final ModelioCreationContext ctx = ModelioCreationContext.fromRequest(this.req);
            return new DefaultCreateElementCommand(gmTarget, ctx, this.req.getLocation()).canExecute();
        } else {
            return false;
        }
    }

    @objid ("7f47c882-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final Command cmd = getCommand();
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
            final GmCompositeNode gmTarget = getTargetNode();
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
        
            autoSizeNode(p);
        
            for (Object sub : p.getChildren()) {
                autoSizeNode((GraphicalEditPart) sub);
            }
        }
    }

    /**
     * Build and return the deferred command.
     * @return the built command or null if it couldn't be built.
     */
    @objid ("7f47c885-1dec-11e2-8cad-001ec947c8cc")
    private Command getCommand() {
        final GmCompositeNode gmTarget = getTargetNode();
        
        if (gmTarget == null) {
            return null;
        }
        
        boolean wasVisible = gmTarget.isVisible();
        if (!wasVisible) {
            gmTarget.setVisible(true);
        }
        
        final GraphicalEditPart p = (GraphicalEditPart) this.editPartRegistry.get(gmTarget);
        if (p != null) {
            EditPart targetEditPart = p.getTargetEditPart(this.req);
            if (targetEditPart != null) {
                if (!wasVisible) {
                    // First layout figures to compute correct coordinates
                    p.getFigure().getUpdateManager().performValidation();
                }
        
                return targetEditPart.getCommand(this.req);
            }
        }
        return null;
    }

    /**
     * Get the composite node in which the element will be really unmasked.
     * @return the target node.
     * @throws java.lang.IllegalArgumentException if the metaclass name of the element to create is invalid
     */
    @objid ("7f47c88c-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode getTargetNode() throws IllegalArgumentException {
        ModelioCreationContext ctx = ModelioCreationContext.fromRequest(this.req);
        final Class<? extends MObject> metaclass = ctx.getMetaclass().getJavaInterface();
        
        final GmCompositeNode gmTarget = this.gmComposite.getCompositeFor(metaclass);
        return gmTarget;
    }

    @objid ("7f47c891-1dec-11e2-8cad-001ec947c8cc")
    private void autoSizeNode(final EditPart newEditPart) {
        // Look for an edit part in the parent hierarchy that understands resize requests.
        final ChangeBoundsRequest reqSize = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        reqSize.setEditParts(newEditPart);
        reqSize.setSizeDelta(new Dimension(-1, -1));
        
        EditPart editPart = newEditPart;
        while (editPart != null && !editPart.understandsRequest(reqSize)) {
            editPart = editPart.getParent();
            reqSize.setEditParts(newEditPart);
        }
        
        if (editPart != null) {
            final GraphicalEditPart graphicEditPart = (GraphicalEditPart) editPart;
        
            // Force layout so that child figures on Port container have valid bounds needed by
            // XYLayoutEditPolicy.getConstraintFor(ChangeBoundsRequest , GraphicalEditPart ) .
            graphicEditPart.refresh();
            graphicEditPart.getFigure().getUpdateManager().performValidation();
        
            // Run fit to content to the found edit part.
            new FitToMinSizeCommand(graphicEditPart).execute();
        }
    }

}
