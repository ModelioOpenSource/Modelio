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

package org.modelio.diagram.elements.core.commands;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Command that defers a {@link GroupRequest} to a child edit part of the sender.
 * <p>
 * The actual edit part is found by calling {@link GmCompositeNode#getCompositeFor(Class)} for all involved GmModel,
 * then looking for their edit part.
 * 
 * @author cmarin
 */
@objid ("7f3e3ef0-1dec-11e2-8cad-001ec947c8cc")
public class DeferredGroupCommand extends Command {
    @objid ("7f3e3ef8-1dec-11e2-8cad-001ec947c8cc")
    private Map<?,?> editPartRegistry;

    @objid ("7f3e3ef7-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode gmComposite;

    @objid ("e098a421-421a-4d5d-9601-b96162beb35b")
    private GroupRequest req;

    /**
     * Create a deferred command.
     * 
     * @param req The creation request.
     * @param sender The edit part sending the request
     */
    @objid ("7f3e3efc-1dec-11e2-8cad-001ec947c8cc")
    public DeferredGroupCommand(GroupRequest req, EditPart sender) {
        this.req = req;
        this.gmComposite = (GmCompositeNode) sender.getModel();
        this.editPartRegistry = sender.getViewer().getEditPartRegistry();
    }

    @objid ("7f3e3f05-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return getGmTarget() != null;
    }

    @objid ("7f3e3f0a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final GmCompositeNode gmTarget = getGmTarget();
        
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
        
                Command cmd = targetEditPart.getCommand(this.req);
                if (cmd != null) {
                    cmd.execute();
                }
            }
        }
    }

    /**
     * Get the node model where all the request must be handled or <tt>null</tt> if the request cannot be executed in a
     * single node (the selection is not homogeneous).
     * 
     * @return the node model where the request must be handled.
     */
    @objid ("7f3e3f0d-1dec-11e2-8cad-001ec947c8cc")
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

}
