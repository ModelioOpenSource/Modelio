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

package org.modelio.diagram.elements.common.label.modelelement;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Manages a {@link GmModelElementLabel}.
 * <ul>
 * <li>The the main label text may be edited.
 * <li>This label may be moved and resized
 * <li>This label may be hidden
 * <li>This label asks its container to fit to content when the label text changes
 * </ul>
 */
@objid ("7e9c1f3b-1dec-11e2-8cad-001ec947c8cc")
public class ModelElementLabelEditPart extends ModelElementHeaderEditPart {
    /**
     * Default constructor.
     */
    @objid ("f2010d61-e9ca-4498-840d-3aae045f440d")
    public ModelElementLabelEditPart() {
        super();
    }

    /**
     * The edit part is selectable only if non empty or its parent is already selected.
     */
    @objid ("13f51a8d-2c84-4819-882a-f2e6f8d55a94")
    @Override
    public boolean isSelectable() {
        // Already selected, return true
        if (getViewer().getSelectedEditParts().contains(this)) {
            return true;
        }
        
        // Not empty label, allow selection
        final boolean hasLabel = !getMainLabelFigure().getText().isEmpty();
        if (hasLabel) {
            return true;
        }
        
        // Allow selection when the parent link is already selected
        EditPart parent = getParent();
        while (parent != null) {
            if (parent.isSelectable()) {
                return (parent.getSelected() != EditPart.SELECTED_NONE);
            }
            parent = parent.getParent();
        }
        return false;
    }

    /**
     * Added the handling of {@link IGmObject#PROPERTY_LABEL} property change events: <ul>
     * <li>updates the visual
     * <li>and requests a resize to preferred size if available.
     * </ul>
     */
    @objid ("d01b1c06-d504-426f-9a94-665d39b90a1f")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        
            // TODO delete if not needed
            // // If preferred size if not the same as current size, check if it is
            // // possible to resize this figure to its preferred size.
            // tryFitToPreferredSize();
        } else {
            super.propertyChange(evt);
        }
    }

    @objid ("7e9e816b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure fig, IStyle style) {
        updateVisibility(fig);
        
        if (fig.isVisible()) {
            super.refreshFromStyle(fig, style);
        }
    }

    @objid ("520e19ab-bdcd-4da1-b9c8-fc863c351236")
    protected void updateVisibility(IFigure aFigure) {
        final boolean visible = ((GmModelElementHeader) getModel()).isVisible();
        if (visible) {
            aFigure.setVisible(true);
        } else {
            aFigure.setVisible(false);
        }
    }

}
