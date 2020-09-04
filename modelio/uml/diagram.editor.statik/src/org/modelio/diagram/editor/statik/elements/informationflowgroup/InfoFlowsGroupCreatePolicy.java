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

package org.modelio.diagram.editor.statik.elements.informationflowgroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.group.DefaultGroupLayoutEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultNodeNonResizableEditPolicy;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow creation an {@link InformationFlow information flow} model element on this edit part.
 * <p>
 * Also forbid labels to be dragged.
 * <p>
 * The created information flow must be realized by the related element, and must have as source and target the source and the
 * target of the represented link.
 * 
 * @author cmarin
 */
@objid ("816e87b2-1dec-11e2-8cad-001ec947c8cc")
public class InfoFlowsGroupCreatePolicy extends DefaultGroupLayoutEditPolicy {
    @objid ("8170e9e3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean canHandle(MClass metaclass) {
        return metaclass.getJavaInterface() == InformationFlow.class;
    }

    @objid ("8170e9ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createAddCommand(final EditPart child, final EditPart after) {
        return null;
    }

    /**
     * Forbid move of inner labels
     */
    @objid ("8170e9fb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        DefaultNodeNonResizableEditPolicy p = new DefaultNodeNonResizableEditPolicy();
        p.setDragAllowed(false);
        return p;
    }

    @objid ("8170ea07-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getHost().getModel() instanceof GmCompositeNode) {
            final GmCompositeNode hostModel = (GmCompositeNode) getHost().getModel();
        
            final CompoundCommand command = new CompoundCommand();
            for (Object editPartObj : request.getEditParts()) {
                final EditPart editPart = (EditPart) editPartObj;
                if (editPart.getModel() instanceof GmModel) {
                    final GmModel gmModel = (GmModel) editPart.getModel();
                    final MClass metaclassToClone = gmModel.getRelatedMClass();
                    if (canHandle(metaclassToClone)) {
                        command.add(new CreateInformationFlowCommand(hostModel, new ModelioCreationContext(metaclassToClone,
                                null, null), Integer.valueOf(-1)));
        
                    }
                }
            }
            return command.unwrap();
        }
        return null;
    }

    @objid ("8170ea12-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(final CreateRequest req) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(req);
        if (ctx == null) {
            return null;
        }
        
        final Class<? extends MObject> metaclassToCreate = ctx.getMetaclass().getJavaInterface();
        
        if (metaclassToCreate != InformationFlow.class) {
            return null;
        }
        
        final GmCompositeNode gmGroup = (GmCompositeNode) getHost().getModel();
        return new CreateInformationFlowCommand(gmGroup, ctx, Integer.valueOf(-1));
    }

}
