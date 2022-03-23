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
package org.modelio.diagram.elements.common.text;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * EditPart for {@link GmElementText} ..
 * <p>
 * Creates a {@link MultilineTextFigure} as figure.
 * 
 * @author cmarin
 */
@objid ("7f2b2c33-1dec-11e2-8cad-001ec947c8cc")
public class ElementTextEditPart extends AbstractNodeEditPart {
    /**
     * Creates the edit part.
     */
    @objid ("7f2b2c35-1dec-11e2-8cad-001ec947c8cc")
    public  ElementTextEditPart() {
        super();
    }

    @objid ("7f2b2c5e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Redefined to handle direct edition of the text.
     * @see RequestConstants#REQ_DIRECT_EDIT
     */
    @objid ("7f2b2c38-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
        
            IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText == null) {
                return;
            }
        
            final MultilineTextFigure label = (MultilineTextFigure) getFigure();
        
            final CellEditorLocator cellEditorLocator = new EditorLocatorForLabelFigure(
                    label, 
                    (String s) -> label.setText(s));
        
            TextDirectEditManager manager = new TextDirectEditManager(
                    this, 
                    cellEditorLocator, 
                    HAlign.Left, 
                    editableText.getText())
                    .setMultiline(true)
                    .setWrap(true);
        
            manager.show();
        } else {
            super.performRequest(req);
        }
        
    }

    /**
     * Added the handling of LABEL property change events: updates the visual and requests a resize to preferred size if
     * available.
     */
    @objid ("7f2b2c3f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            // Take note of the current size.
            final IFigure aFigure = getFigure();
            final Dimension currentSize = aFigure.getSize();
            aFigure.translateToAbsolute(currentSize);
        
            refreshVisuals();
        
            // If preferred size is not the same as current size, check if it is
            // possible to resize this figure to its preferred size.
            final Dimension updatedPrefSize = aFigure.getPreferredSize().getCopy();
            aFigure.translateToAbsolute(updatedPrefSize);
            if (!currentSize.isEmpty() && !updatedPrefSize.equals(currentSize)) {
                final ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(REQ_RESIZE);
                changeBoundsRequest.setEditParts(this);
                changeBoundsRequest.setSizeDelta(updatedPrefSize.getShrinked(currentSize));
        
                final Command resizeCommand = getCommand(changeBoundsRequest);
                if (resizeCommand != null && resizeCommand.canExecute()) {
                    resizeCommand.execute();
                }
            }
        } else {
            super.propertyChange(evt);
        }
        
    }

    @objid ("7f2b2c44-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        //super.createEditPolicies();
        
        if (((GmModel) getModel()).getEditableText() != null) {
            installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
        }
        
    }

    /**
     * Creates a {@link MultilineTextFigure}.
     */
    @objid ("7f2b2c47-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final GmElementText model = (GmElementText) getModel();
        
        final MultilineTextFigure f = new MultilineTextFigure(model.getText());
        
        // Set style independent properties
        f.setTextAlignment(PositionConstants.LEFT);
        f.setOpaque(false);
        
        // Set style dependent properties
        refreshFromStyle(f, model.getDisplayedStyle());
        return f;
    }

    @objid ("7f2b2c4f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final GmElementText model = (GmElementText) getModel();
        
        aFigure.setForegroundColor(style.getColor(model.getStyleKey(MetaKey.TEXTCOLOR)));
        aFigure.setFont(style.getFont(model.getStyleKey(MetaKey.FONT)));
        
        updateVisibility(aFigure);
        
    }

    @objid ("7f2b2c56-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmElementText model = (GmElementText) getModel();
        final MultilineTextFigure labelFigure = (MultilineTextFigure) getFigure();
        
        labelFigure.setText(model.getText());
        
        final Object layoutData = model.getLayoutData();
        if (layoutData != null) {
            labelFigure.getParent().setConstraint(labelFigure, layoutData);
        }
        
    }

    @objid ("7f2b2c59-1dec-11e2-8cad-001ec947c8cc")
    private void updateVisibility(IFigure aFigure) {
        final boolean visible = ((GmElementText) getModel()).isVisible();
        if (visible) {
            aFigure.setVisible(true);
        } else {
            aFigure.setVisible(false);
        }
        
    }

}
