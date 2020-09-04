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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationEditPolicy;
import org.modelio.metamodel.uml.infrastructure.Constraint;

/**
 * This policy handles the last click during the creation of a constraint that defines the place of the "body" of a
 * constraint.
 * 
 * @author fpoyer
 */
@objid ("7e084d68-1dec-11e2-8cad-001ec947c8cc")
public class ConstraintFinalizationEditPolicy extends MultiPointCreationEditPolicy {
    /**
     * C'tor.
     */
    @objid ("7e084d6a-1dec-11e2-8cad-001ec947c8cc")
    public ConstraintFinalizationEditPolicy() {
        super(false);
    }

    @objid ("7e084d6d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointAdditionalCommand(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("7e084d76-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointFinalCommand(final CreateMultiPointRequest request) {
        // Filter out requests we don't handle
        if (!isCreationOf(request, Constraint.class))
            return null;
        
        // At least one node must already be selected
        if (request.getAcceptedEditParts().isEmpty())
            return null;
        
        ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) request.getNewObject();
        List<GmModel> sourceModels = new ArrayList<>(request.getAcceptedEditParts().size());
        for (EditPart acceptedEditPart : request.getAcceptedEditParts()) {
            sourceModels.add((GmModel) acceptedEditPart.getModel());
        }
        Point.SINGLETON.setLocation(request.getLocation());
        getHostFigure().translateToRelative(Point.SINGLETON);
        Rectangle requestRect = new Rectangle(Point.SINGLETON, new Dimension(-1, -1));
        return new CreateConstraintCommand(sourceModels,
                                                                                           (GmCompositeNode) getHost().getModel(),
                                                                                           ctx,
                                                                                           requestRect);
    }

    @objid ("7e084d7f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointFirstCommand(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("7e0aaf98-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getTargetEditPartAdditional(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("7e0aafa1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getTargetEditPartFirst(final CreateMultiPointRequest request) {
        // Only accept final request
        return null;
    }

    @objid ("7e0aafaa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getTargetEditPartLast(final CreateMultiPointRequest request) {
        // Only accept Constraint so far.
        if (isCreationOf(request, Constraint.class)) {
            // At least one node must already be selected
            if (request.getAcceptedEditParts().isEmpty())
                return null;
        
            return super.getTargetEditPartLast(request);
        } else {
            return null;
        }
    }

    @objid ("7e0d11ef-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(final CreateMultiPointRequest request) {
        // do NOT use the default highlight for diagram background, this is ugly!
        // TODO: define a better highlight
    }

}
