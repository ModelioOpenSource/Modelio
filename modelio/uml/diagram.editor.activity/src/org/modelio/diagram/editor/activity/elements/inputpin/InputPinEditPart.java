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

package org.modelio.diagram.editor.activity.elements.inputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.activity.elements.figures.InputPinFigure;
import org.modelio.diagram.editor.activity.elements.policies.CreateFlowEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.common.portcontainer.PortContainerFigure;
import org.modelio.diagram.elements.common.portcontainer.PortContainerLayout;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an {@link GmInputPinPrimaryNode} Node.
 * 
 * @author fpoyer
 */
@objid ("2ab0c3d9-55b6-11e2-877f-002564c97630")
public class InputPinEditPart extends AbstractNodeEditPart {
    /**
     * Creates the Figure to be used as this part's main visuals
     */
    @objid ("2ab0c3dd-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        InputPinFigure fig = new InputPinFigure();
        
        // set style independent properties
        fig.setOpaque(true);
        fig.setSize(10, 10);
        fig.setMinimumSize(new Dimension(10, 10));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("2ab0c3e3-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        refreshOrientation();
    }

    @objid ("2ab0c3e6-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Do not show the smart link creation handle in pins which are too small to display them properly.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("2ab0c3e9-55b6-11e2-877f-002564c97630")
    private void refreshOrientation() {
        if (getFigure().getParent().getParent() instanceof PortContainerFigure) {
            PortContainerFigure portContainerFigure = (PortContainerFigure) getFigure()
                    .getParent()
                    .getParent();
            // we are sure that this is a port and have a portandsatellitelayout
            PortContainerLayout portLayout = portContainerFigure.getPortContainerLayout();
            Border border = portLayout.getReferenceBorder(getFigure().getParent());
            if (border != null) {
                // we are sure that this is an inputpin figure
                InputPinFigure fig = (InputPinFigure) getFigure();
                fig.setOrientation(border);
            }
        }
    }

    @objid ("2ab0c3eb-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof InputPinFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        } else {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("2ab0c3f2-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
