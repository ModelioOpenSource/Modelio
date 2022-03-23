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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.uml.sequencediagram.editor.elements.interactionuse.gate.CreateGateOnInteractionUseCommand;

/**
 * Specialisation to handle the specific aspect of Gate creation.
 * 
 * @author fpoyer
 */
@objid ("d9220d81-55b6-11e2-877f-002564c97630")
public class InteractionUseEditPolicy extends PortContainerEditPolicy {
    @objid ("d9220d85-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        InteractionUse hostElement = (InteractionUse) getHostElement();
        if (hostElement != null) {
            ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
            if (ctx.getElementToUnmask() == null && Gate.class == ctx.getJavaClass()) {
                Object requestConstraint = getConstraintFor(request);
                Point tmp = Point.SINGLETON;
                tmp.setLocation(request.getLocation());
                getHostFigure().translateToRelative(tmp);
        
                return new CreateGateOnInteractionUseCommand(hostElement,
                        getHostCompositeNode(),
                        ctx,
                        requestConstraint,
                        tmp.y);
            }
        }
        return super.getCreateCommand(request);
    }

    @objid ("d9220d8c-55b6-11e2-877f-002564c97630")
    @Override
    protected Dimension getMinimumSizeFor(final GraphicalEditPart child) {
        // XXX hack to work around very unsual case where primarynode of InteractionUse is less than 8 px high (usually: when reading an pre-2.0 model).
        return new Dimension(8, 0);
    }

}
