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

package org.modelio.diagram.elements.core.commands;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Command that that switches the representation mode to
 * {@link org.modelio.diagram.styles.core.StyleKey.RepresentationMode#STRUCTURED STRUCTURED},
 * then defers a {@link GroupRequest} to a child edit part of the sender.
 * <p>
 * The actual edit part is found by calling {@link GmCompositeNode#getCompositeFor(Class)} for all involved GmModel, then looking
 * for their edit part.
 * 
 * @author cmarin
 */
@objid ("7f47c89f-1dec-11e2-8cad-001ec947c8cc")
public class SimpleModeDeferredGroupCommand extends Command {
    @objid ("7f47c8a7-1dec-11e2-8cad-001ec947c8cc")
    private Map<?,?> editPartRegistry;

    @objid ("7f47c8a6-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode gmComposite;

    @objid ("6712f2b1-1e83-11e2-8cad-001ec947c8cc")
    private GroupRequest req;

    /**
     * Create a deferred command.
     * 
     * @param req The creation request.
     * @param sender The edit part sending the request
     */
    @objid ("7f47c8ab-1dec-11e2-8cad-001ec947c8cc")
    public SimpleModeDeferredGroupCommand(GroupRequest req, EditPart sender) {
        this.req = req;
        this.gmComposite = (GmCompositeNode) sender.getModel();
        this.editPartRegistry = sender.getViewer().getEditPartRegistry();
    }

    @objid ("7f4a2ab3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (!MTools.getAuthTool().canModify(this.gmComposite.getDiagram().getRelatedElement())) {
            return false;
        }
        
        GmCompositeNode target = getGmTarget();
        if (target == null) {
            return false;
        }
        
        final MObject parentEl = target.getRelatedElement();
        if (parentEl == null) {
            return false;
        }
        
        for (Object o : this.req.getEditParts()) {
            final EditPart part = (EditPart) o;
            final GmModel model = (GmModel) part.getModel();
            final MObject childEl = model.getRelatedElement();
        
            if (childEl == null || !MTools.getAuthTool().canAdd(parentEl, childEl.getMClass())) {
                return false;
            }
        }
        return true;
    }

    @objid ("7f4a2ab8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final Command cmd = getCommand();
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
            final GmCompositeNode gmTarget = getGmTarget();
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
            autoSizeNode(p);
        }
    }

    /**
     * Get the node model where all the request must be handled or <tt>null</tt> if the request cannot be executed in a single node
     * (the selection is not homogeneous).
     * 
     * @return the node model where the request must be handled.
     */
    @objid ("7f4a2abb-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode getGmTarget() {
        final SmMetamodel mm = this.gmComposite.getDiagram().getModelManager().getModelingSession().getMetamodel();
        
        GmCompositeNode gmTarget = null;
        
        for (Object o : this.req.getEditParts()) {
            final EditPart part = (EditPart) o;
            final GmModel model = (GmModel) part.getModel();
            final String metaclassName = model.getRepresentedRef().mc;
            final Class<? extends MObject> metaclass = mm.getMClass(metaclassName).getJavaInterface();
        
            final GmCompositeNode cont = this.gmComposite.getCompositeFor(metaclass);
        
            if (cont == null) {
                return null;
            }
        
            if (gmTarget == null) {
                gmTarget = cont;
            } else if (gmTarget != cont) {
                return null;
            }
        }
        return gmTarget;
    }

    @objid ("7f4a2ac0-1dec-11e2-8cad-001ec947c8cc")
    private Command getCommand() {
        final GmCompositeNode gmTarget = getGmTarget();
        
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

    @objid ("7f4a2ac6-1dec-11e2-8cad-001ec947c8cc")
    private void autoSizeNode(final EditPart newEditPart) {
        // Look for an edit part in the parent hierarchy that understands resize requests.
        final ChangeBoundsRequest resizeReq = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        resizeReq.setEditParts(newEditPart);
        resizeReq.setSizeDelta(new Dimension(-1, -1));
        
        EditPart editPart = newEditPart;
        while (editPart != null && !editPart.understandsRequest(resizeReq)) {
            editPart = editPart.getParent();
            resizeReq.setEditParts(newEditPart);
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
