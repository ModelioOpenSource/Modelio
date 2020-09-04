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

package org.modelio.diagram.editor.statik.elements.instanceinternalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.group.DefaultGroupLayoutEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Group layout edit policy that when asked to create an {@link Instance} creates:
 * <ul>
 * <li>a {@link BindableInstance} under a {@link Classifier} or an {@link Instance}.
 * <li>an {@link Instance} under a package or anything else.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("3555918c-55b7-11e2-877f-002564c97630")
public class InstanceGroupLayoutEditPolicy extends DefaultGroupLayoutEditPolicy {
    @objid ("35559190-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest req) {
        return super.getCreateCommand(getModifiedRequest(req));
    }

    @objid ("35559196-55b7-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            return super.getTargetEditPart(getModifiedRequest((CreateRequest) request));
        } else {
            return super.getTargetEditPart(request);
        }
    }

    @objid ("3555919b-55b7-11e2-877f-002564c97630")
    private CreateRequest getModifiedRequest(CreateRequest req) {
        ModelioCreationContext context = ModelioCreationContext.lookRequest(req);
        
        if (context != null && "true".equals(context.getProperties().get("smart"))) {
        
            final AbstractNodeEditPart targetEditPart = (AbstractNodeEditPart) getHost();
            final MObject targetElement = targetEditPart.getModel().getRelatedElement();
        
            if (targetElement instanceof Instance || targetElement instanceof Classifier) {
                // Ask to create a BindableInstance
                MMetamodel mm = targetElement.getMClass().getMetamodel();
                ModelioCreationContext newContext = new ModelioCreationContext(mm.getMClass(BindableInstance.class),
                        context.getDependency(),
                        context.getStereotype());
        
                newContext.setProperties(context.getProperties());
        
                final CreateRequest newreq = new CreateRequest();
                newreq.setExtendedData(req.getExtendedData());
                newreq.setFactory(newContext);
                newreq.setLocation(req.getLocation());
                newreq.setSize(req.getSize());
        
                return newreq;
            }
        }
        return req;
    }

}
