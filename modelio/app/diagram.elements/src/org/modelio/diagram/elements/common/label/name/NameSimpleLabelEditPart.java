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

package org.modelio.diagram.elements.common.label.name;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * EditPart for {@link GmNameSimpleLabel}.
 * <p>
 * Creates a {@link Label} as figure.
 * 
 * @author cmarin
 */
@objid ("7eaa6d2b-1dec-11e2-8cad-001ec947c8cc")
public class NameSimpleLabelEditPart extends AbstractNodeEditPart {
    /**
     * Default constructor for deserialization.
     */
    @objid ("7eaa6d2d-1dec-11e2-8cad-001ec947c8cc")
    public NameSimpleLabelEditPart() {
        super();
    }

    /**
     * Initialize the edit part.
     * 
     * @param gmLabel The label model
     */
    @objid ("7eaa6d30-1dec-11e2-8cad-001ec947c8cc")
    public NameSimpleLabelEditPart(GmNameSimpleLabel gmLabel) {
        super();
        setModel(gmLabel);
    }

    @objid ("7eaa6d34-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else {
            super.propertyChange(evt);
        }
    }

    @objid ("7eaa6d38-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        // Nothing to create
    }

    /**
     * Creates a {@link Label}.
     */
    @objid ("7eaa6d3b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final GmNameSimpleLabel model = (GmNameSimpleLabel) getModel();
        
        final Label f = new Label(model.getLabel());
        
        // Set style independent properties
        f.setLabelAlignment(PositionConstants.LEFT);
        f.setOpaque(false);
        f.setMinimumSize(new Dimension(20, 20));
        
        // Set style dependent properties
        refreshFromStyle(f, model.getDisplayedStyle());
        return f;
    }

    @objid ("7eaa6d43-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final GmNameSimpleLabel model = (GmNameSimpleLabel) getModel();
        
        aFigure.setForegroundColor(style.getColor(model.getStyleKey(MetaKey.TEXTCOLOR)));
        aFigure.setFont(style.getFont(model.getStyleKey(MetaKey.FONT)));
    }

    @objid ("7eaa6d4a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmNameSimpleLabel model = (GmNameSimpleLabel) getModel();
        final Label labelFigure = (Label) getFigure();
        
        // Layout data
        if (model.getLayoutData() != null) {
            labelFigure.getParent().setConstraint(labelFigure, model.getLayoutData());
        }
        
        // Label text
        labelFigure.setText(model.getLabel());
    }

}
