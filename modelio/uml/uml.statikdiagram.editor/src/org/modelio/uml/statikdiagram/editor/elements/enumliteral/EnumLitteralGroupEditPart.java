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
package org.modelio.uml.statikdiagram.editor.elements.enumliteral;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * EditPart for {@link GmEnumGroup}.
 * <p>
 * All behavior is currently inherited from {@link GroupEditPart}.
 * </p>
 */
@objid ("cc0b42bc-3ac3-4b16-876e-79d13669e4f6")
public class EnumLitteralGroupEditPart extends GroupEditPart {
    @objid ("137b6e91-c491-43bd-aee0-3e006a23160d")
    @Override
    protected void refreshVisuals() {
        IGmObject model = getModel();
        if (model.getLayoutData() != null) {
            getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        }
        
    }

    @objid ("968d578d-30c5-4690-a59e-9133b86968a0")
    @Override
    public boolean isSelectable() {
        // Already selected, return true
        if (getViewer().getSelectedEditParts().contains(this)) {
            return true;
        }
        
        // Allow selection only if the composition parent was already selected
        EditPart parent = getParent();
        while (parent != null) {
            if (parent.isSelectable()) {
                return (parent.getSelected() != EditPart.SELECTED_NONE);
            }
            parent = parent.getParent();
        }
        return false;
    }

    @objid ("820fe4fc-04f9-4350-9cb1-9c80a21364fa")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
        final GmModel gmModel = getModel();
        TLBRBorder border = new TLBRBorder(true, false, false, false);
        if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null) {
            border.setColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
        }
        if (gmModel.getStyleKey(MetaKey.LINEWIDTH) != null) {
            border.setWidth(style.getInteger(gmModel.getStyleKey(MetaKey.LINEWIDTH)));
        }
        aFigure.setBorder(border);
        
    }

}
