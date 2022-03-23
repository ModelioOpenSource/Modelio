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
package org.modelio.uml.objectdiagram.editor.elements.objectdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Free zone layout edit policy that when asked to create an {@link Instance} creates:
 * <ul>
 * <li>a {@link BindableInstance} under a {@link Classifier} or an {@link Instance}.
 * <li>an {@link Instance} under a package or anything else.
 * </ul>
 */
@objid ("ba601904-ec52-4ad8-8a8e-81204f06b04e")
public class ObjectDiagramEditLayoutPolicy extends DiagramEditLayoutPolicy {
    @objid ("641c504b-e905-4be2-b742-871ec61aabbd")
    @Override
    protected Command getCreateCommand(CreateRequest req) {
        return super.getCreateCommand(getModifiedRequest(req));
    }

    @objid ("c72c39d7-215c-4d62-ab33-dd15ea3d6326")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            return super.getTargetEditPart(getModifiedRequest((CreateRequest) request));
        } else {
            return super.getTargetEditPart(request);
        }
        
    }

    @objid ("a0cfad4c-aa85-40ea-8109-2e10b7263aec")
    private CreateRequest getModifiedRequest(CreateRequest req) {
        ModelioCreationContext context = ModelioCreationContext.fromRequest(req);
        
        if ("true".equals(context.getProperties().get("smart"))) {
            final MObject targetElement = getHostElement();
        
            if (targetElement instanceof Instance ||
                    targetElement instanceof Classifier ||
                    targetElement instanceof Collaboration) {
                MMetamodel mm = targetElement.getMClass().getMetamodel();
        
                // Ask to create a BindableInstance
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
