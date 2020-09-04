/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.state.elements.region;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.freezone.FreeZoneLayout;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for a Region.
 * 
 * @author fpoyer
 */
@objid ("f56b91c4-55b6-11e2-877f-002564c97630")
public class RegionEditPart extends FreeZoneEditPart {
    @objid ("f56b91c8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    @objid ("f56b91cd-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        GradientFigure fig = new GradientFigure();
        final FreeZoneLayout layout = new FreeZoneLayout();
        fig.setLayoutManager(layout);
        
        // Style independent properties
        fig.setOpaque(true);
        fig.setBorder(new MarginBorder(3, 2, 3, 2));
        MinimumSizeLayout.apply(fig, 50, 50);
        
        
        // Set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("f56b91d2-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("f56b91d9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        GmAbstractObject model = getModel();
        IFigure fig = getFigure();
        fig.getParent().setConstraint(fig, model.getLayoutData());
    }

    @objid ("f56b91dc-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Unlike most nodes, the region is not meant to be masked: un-install the default masking policy.
        installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
    }

}
