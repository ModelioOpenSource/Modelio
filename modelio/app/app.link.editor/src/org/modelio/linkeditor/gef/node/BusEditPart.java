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
package org.modelio.linkeditor.gef.node;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.panel.model.EdgeBus;

/**
 * Edit part for the EdgeBus model.
 * 
 * @author fpoyer
 */
@objid ("1badd1e6-5e33-11e2-b81d-002564c97630")
public class BusEditPart extends AbstractGraphicalEditPart implements org.eclipse.gef.NodeEditPart {
    @objid ("1badd1ec-5e33-11e2-b81d-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new ToSideBusAnchor(getFigure(), isVerticalLayout());
    }

    @objid ("1badd1f7-5e33-11e2-b81d-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        return new FromSideBusAnchor(getFigure(), isVerticalLayout());
    }

    @objid ("1badd202-5e33-11e2-b81d-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new ToSideBusAnchor(getFigure(), isVerticalLayout());
    }

    @objid ("1badd20d-5e33-11e2-b81d-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new FromSideBusAnchor(getFigure(), isVerticalLayout());
    }

    @objid ("1badd218-5e33-11e2-b81d-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure rectangleFigure = new Figure();
        rectangleFigure.setOpaque(true);
        rectangleFigure.setBackgroundColor(ColorConstants.lightGray);
        return rectangleFigure;
    }

    @objid ("1badd21f-5e33-11e2-b81d-002564c97630")
    @Override
    protected void createEditPolicies() {
        // No policies to create
    }

    @objid ("1badd222-5e33-11e2-b81d-002564c97630")
    @Override
    public List<?> getModelSourceConnections() {
        return getModel().outgoing;
    }

    @objid ("1badd229-5e33-11e2-b81d-002564c97630")
    @Override
    public List<?> getModelTargetConnections() {
        return getModel().incoming;
    }

    @objid ("1badd230-5e33-11e2-b81d-002564c97630")
    @Override
    public EdgeBus getModel() {
        return (EdgeBus) super.getModel();
    }

    @objid ("1badd235-5e33-11e2-b81d-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("b80cafcf-1cef-43e9-b5d5-2aebbaa04550")
    private boolean isVerticalLayout() {
        BackgroundEditPart ep = (BackgroundEditPart) this.getRoot().getContents();
        boolean vertical = ep.isVerticalLayout();
        return vertical;
    }

}
