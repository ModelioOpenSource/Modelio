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

package org.modelio.diagram.editor.activity.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that should create control flow or object flows depending on the source and destination node.
 * 
 * TODO : does it work? It seems to always create control flows.
 * 
 * @author fpoyer
 */
@objid ("2b3efa1a-55b6-11e2-877f-002564c97630")
public class CreateFlowEditPolicy extends DefaultCreateLinkEditPolicy {
    @objid ("2b3efa1e-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        
        if (context != null && "true".equals(context.getProperties().get("smart"))) {
            DefaultCreateLinkCommand startCommand = (DefaultCreateLinkCommand) req.getStartCommand();
        
            GmNodeModel targetNodeModel = (GmNodeModel) getHost().getModel();
            startCommand.setTarget(targetNodeModel);
            if (req instanceof CreateBendedConnectionRequest) {
                startCommand.setPath(createPathModel(req));
        
            }
        
            MObject sourceElement = ((GmNodeModel) req.getSourceEditPart().getModel()).getRelatedElement();
            MObject targetElement = targetNodeModel.getRelatedElement();
        
            // Fix the context in any case
            if (sourceElement != null && (sourceElement instanceof ControlNode || sourceElement instanceof ActivityAction)
                    && targetElement != null && (targetElement instanceof ControlNode || targetElement instanceof ActivityAction)) {
                // use a modified context
                MMetamodel mm = targetElement.getMClass().getMetamodel();
                ModelioLinkCreationContext newContext = new ModelioLinkCreationContext(mm.getMClass(ControlFlow.class), context.getStereotype());
                newContext.setProperties(context.getProperties());
                startCommand.setContext(newContext);
            } else {
                // reuse the initial context (IMPORTANT)
                startCommand.setContext(context);
            }
        
            return startCommand;
        } else {
            return super.getConnectionCompleteCommand(req);
        }
    }

}
