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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupEditPart;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupLayout;

/**
 * Specialised edit part that handles correctly the creation and re-ordering of InteractionOperands
 * 
 * @author fpoyer
 */
@objid ("d8cfa7ee-55b6-11e2-877f-002564c97630")
public class InteractionOperandContainerEditPart extends ResizableGroupEditPart {
    @objid ("d8cfa7f2-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Override layout policy to handle ObModel in addition to simply handling the graphical properties.
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new InteractionOperandContainerLayoutEditPolicy());
    }

    @objid ("d8cfa7f5-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure fig = new InteractionOperandContainerFigure();
        fig.setOpaque(false);
        // Define properties not specific to style.
        ResizableGroupLayout layoutManager = new ResizableGroupLayout();
        layoutManager.setStretchMinorAxis(true);
        fig.setLayoutManager(layoutManager);
        
        // Define properties specific to style
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

}
