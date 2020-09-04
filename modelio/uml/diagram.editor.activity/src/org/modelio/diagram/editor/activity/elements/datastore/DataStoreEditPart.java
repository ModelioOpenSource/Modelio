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

package org.modelio.diagram.editor.activity.elements.datastore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.activity.elements.policies.CreateFlowEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an DataStore Node.
 * 
 * @author fpoyer
 */
@objid ("2a24625a-55b6-11e2-877f-002564c97630")
public class DataStoreEditPart extends AbstractNodeEditPart {
    @objid ("2a24625e-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        final GmAbstractObject gmAbstractObject = (GmAbstractObject) childEditPart.getModel();
        
        if (index == 0 && gmAbstractObject.getLayoutData() == null) {
            gmAbstractObject.setLayoutData(BorderLayout.TOP);
        } else if (index == 1 && gmAbstractObject.getLayoutData() == null) {
            gmAbstractObject.setLayoutData(BorderLayout.CENTER);
        }
        
        getFigure().add(child, gmAbstractObject.getLayoutData(), index);
    }

    @objid ("2a246263-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("2a246266-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        RectangularFigure fig = new RectangularFigure();
        
        fig.setLayoutManager(new BorderLayout());
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 80, 45);
        fig.setOpaque(true);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("2a24626b-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("2a25e8fe-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IFigure fig = getFigure();
        GmAbstractObject dataStoreModel = getModel();
        fig.getParent().setConstraint(fig, dataStoreModel.getLayoutData());
    }

    @objid ("2a25e902-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
