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
package org.modelio.uml.statikdiagram.editor.elements.naryassoc;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationEditPolicy;
import org.modelio.metamodel.uml.statik.NaryAssociation;

/**
 * This policy handles the last click during the creation of a n-ary association that defines the place of the "diamond"
 * of an association.
 * 
 * @author cmarin
 */
@objid ("35cb0fb3-55b7-11e2-877f-002564c97630")
public class NAssocFinalizationEditPolicy extends MultiPointCreationEditPolicy {
    /**
     * C'tor.
     */
    @objid ("35cb0fb7-55b7-11e2-877f-002564c97630")
    public  NAssocFinalizationEditPolicy() {
        super(false);
    }

    @objid ("35cb0fba-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointAdditionalCommand(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("35cb0fc3-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointFinalCommand(final CreateMultiPointRequest request) {
        if (isCreationOf(request, NaryAssociation.class)) {
            final ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) request.getNewObject();
        
            final List<IGmLinkable> sourceModels = new ArrayList<>(request.getAcceptedEditParts()
                                                                                     .size());
            for (EditPart acceptedEditPart : request.getAcceptedEditParts()) {
                sourceModels.add((IGmLinkable) acceptedEditPart.getModel());
            }
        
            final Point loc = request.getLocation().getCopy();
            getHostFigure().translateToRelative(loc);
            final Rectangle requestRect = new Rectangle(loc.x, loc.y, -1, -1);
            return new CreateNAssocCommand(getHost(),
                                           sourceModels,
                                           (GmCompositeNode) getHost().getModel(),
                                           ctx,
                                           requestRect);
        } else {
            return null;
        }
        
    }

    @objid ("35cb0fcb-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointFirstCommand(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("35cb0fd4-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getTargetEditPartAdditional(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("35cb0fdd-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getTargetEditPartFirst(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("35cc963a-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getTargetEditPartLast(final CreateMultiPointRequest request) {
        // Only accept Association so far.
        if (isCreationOf(request, NaryAssociation.class)) {
            return super.getTargetEditPartLast(request);
        } else {
            return null;
        }
        
    }

    @objid ("35cc9642-55b7-11e2-877f-002564c97630")
    @Override
    protected void showTargetConnectionFeedback(final CreateMultiPointRequest request) {
        // do NOT use the default highlight for diagram background, this is ugly!
        // TODO: define a better highlight
        
    }

}
