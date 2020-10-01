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
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that defers a {@link CreateRequest} to a child edit part of the sender.
 * <p>
 * The actual edit part is found by calling {@link GmCompositeNode#getCompositeFor(Class)} on the sender, then looking
 * for its edit part.
 * 
 * @author cmarin
 */
@objid ("7f3bdcc4-1dec-11e2-8cad-001ec947c8cc")
public class DeferredCreateCommand extends Command {
    @objid ("7f3bdccc-1dec-11e2-8cad-001ec947c8cc")
    private Map<?,?> editPartRegistry;

    @objid ("7f3bdccb-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode gmCompositeNode;

    @objid ("7c3dc469-9863-446c-b9c8-0a346f67dcea")
    private CreateRequest req;

    /**
     * Create a deferred command.
     * 
     * @param req The creation request.
     * @param sender The edit part sending the request
     */
    @objid ("7f3bdcd0-1dec-11e2-8cad-001ec947c8cc")
    public DeferredCreateCommand(CreateRequest req, EditPart sender) {
        this.req = req;
        this.gmCompositeNode = (GmCompositeNode) sender.getModel();
        this.editPartRegistry = sender.getViewer().getEditPartRegistry();
    }

    @objid ("7f3bdcd9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final GmCompositeNode gmTarget = getTargetNode();
        return gmTarget != null && MTools.getAuthTool().canModify(gmTarget.getRelatedElement()) && MTools.getAuthTool().canModify(gmTarget.getDiagram().getRelatedElement());
    }

    @objid ("7f3bdcde-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final GmCompositeNode gmTarget = getTargetNode();
        
        if (gmTarget == null) {
            return;
        }
        
        if (gmTarget == this.gmCompositeNode)
        {
            return; // Avoid infinite recursion
        }
        
        boolean targetVisible = gmTarget.isVisible();
        if (!targetVisible) {
            gmTarget.setVisible(true);
        }
        
        final GraphicalEditPart p = (GraphicalEditPart) this.editPartRegistry.get(gmTarget);
        if (p != null) {
            final EditPart targetEditPart = p.getTargetEditPart(this.req);
            if (targetEditPart != null) {
                if (!targetVisible) {
                    // First layout figures to compute correct coordinates
                    p.getFigure().getUpdateManager().performValidation();
                }
        
                targetEditPart.getCommand(this.req).execute();
            }
        }
    }

    /**
     * Get the composite node on which the new node must be created.
     * 
     * @return the child node
     * @throws java.lang.IllegalArgumentException if the metaclass name does not exist.
     */
    @objid ("7f3bdce1-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getTargetNode() throws IllegalArgumentException {
        //final String metaclassName = (String) this.req.getNewObjectType();
        ModelioCreationContext ctx = (ModelioCreationContext) this.req.getNewObject();
        final Class<? extends MObject> metaclass = ctx.getMetaclass().getJavaInterface();
        
        final GmCompositeNode gmTarget = getCompositeNode().getCompositeFor(metaclass);
        return gmTarget;
    }

    /**
     * Get the parent composite node.
     * 
     * @return the main node.
     */
    @objid ("7f3bdce6-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getCompositeNode() {
        return this.gmCompositeNode;
    }

}
