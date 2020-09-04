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

package org.modelio.diagram.editor.statik.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that should create generalizations or interface realizations depending on the source and destination
 * node.
 */
@objid ("363d807a-55b7-11e2-877f-002564c97630")
public class SmartGeneralizationEditPolicy extends DefaultCreateLinkEditPolicy {
    @objid ("363d807e-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCompleteCommand(final CreateConnectionRequest request) {
        // Only handle model element creation requests.
        if (!(request.getNewObject() instanceof ModelioLinkCreationContext)) {
            return null;
        }
        ModelioLinkCreationContext context = (ModelioLinkCreationContext) request.getNewObject();
        if ("true".equals(context.getProperties().get("smart"))) {
            DefaultCreateLinkCommand startCommand = (DefaultCreateLinkCommand) request.getStartCommand();
        
            GmNodeModel targetNodeModel = (GmNodeModel) getHost().getModel();
            startCommand.setTarget(targetNodeModel);
            startCommand.setPath(createPathModel(request));
        
            MObject sourceElement = ((GmNodeModel) request.getSourceEditPart().getModel()).getRelatedElement();
            MObject targetElement = targetNodeModel.getRelatedElement();
        
            MMetamodel mm = sourceElement.getMClass().getMetamodel();
        
            if (sourceElement == targetElement) {
                // No self inheritance allowed
                return null;
            } else if ((sourceElement instanceof Interface) && !(targetElement instanceof Interface)) {
                // An interface can derive from an interface only.
                return null;
            } else if (!(sourceElement instanceof Interface) && (targetElement instanceof Interface)) {
                // Create an interface realization
                ModelioLinkCreationContext newContext = new ModelioLinkCreationContext(mm.getMClass(InterfaceRealization.class),
                                                                                       context.getStereotype());
                newContext.setProperties(context.getProperties());
                startCommand.setContext(newContext);
            } else {
                // Create a generalization
                ModelioLinkCreationContext newContext = new ModelioLinkCreationContext(mm.getMClass(Generalization.class),
                                                                                       context.getStereotype());
                newContext.setProperties(context.getProperties());
                startCommand.setContext(newContext);
            }
        
            return startCommand;
        } else {
            return super.getConnectionCompleteCommand(request);
        }
    }

}
