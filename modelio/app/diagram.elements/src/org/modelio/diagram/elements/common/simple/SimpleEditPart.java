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

package org.modelio.diagram.elements.common.simple;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.core.policies.SimpleModeDeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Universal editpart for "simple" mode of any ModelElement.
 * <p>
 * It provides a simple {@link RectangularFigure} figure with a {@link BorderLayout#CENTER centered} child figure.
 */
@objid ("7f240511-1dec-11e2-8cad-001ec947c8cc")
public class SimpleEditPart extends AbstractNodeEditPart {
    @objid ("101bff78-8601-485e-8f5a-53cb8a98a6c9")
    public static final int MIN_HEIGHT = 50;

    @objid ("8623149d-ad60-46cd-b047-e3a38da49b8a")
    public static final int MIN_WIDTH = 100;

    @objid ("7f240513-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // This edit part is way too generic to afford being smart: on any property change event, refresh all!
        refresh();
        refreshFromStyle(getFigure(), getModelStyle());
    }

    @objid ("7f240517-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final RectangularFigure aFigure = new RectangularFigure();
        aFigure.setLayoutManager(new BorderLayout());
        aFigure.setOpaque(true);
        
        // set style independent properties
        MinimumSizeLayout.apply(aFigure, SimpleEditPart.MIN_WIDTH, SimpleEditPart.MIN_HEIGHT);
        
        // set style dependent properties
        refreshFromStyle(aFigure, getModelStyle());
        
        // return the figure
        return aFigure;
    }

    @objid ("7f24051e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("7f240525-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeDeferringCreateNodePolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("7f240528-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmNodeModel model = getModel();
        final IFigure aFigure = getFigure();
        
        IFigure parent = aFigure.getParent();
        if (parent != null) {
            parent.setConstraint(aFigure, model.getLayoutData());
        }
    }

    /**
     * SimpleEditPart has no model children.
     */
    @objid ("7f24052b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<Object> getModelChildren() {
        for (Object o : super.getModelChildren()) {
            if (o instanceof GmModelElementHeader) {
                return Collections.singletonList(o);
            }
        }
        return Collections.emptyList();
    }

    @objid ("3edee248-bdd7-496a-bd6d-ef74ea15a225")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure childFig = ((AbstractGraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(childFig, BorderLayout.CENTER);
    }

}
