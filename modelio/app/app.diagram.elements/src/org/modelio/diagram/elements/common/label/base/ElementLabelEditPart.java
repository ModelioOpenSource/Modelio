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

package org.modelio.diagram.elements.common.label.base;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.figures.labelum.NativeTextLayouter;
import org.modelio.diagram.elements.core.figures.labelum.NoBreakTextLayouter;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * EditPart for {@link GmElementLabel} around connection links or inside GmGroups..
 * <p>
 * Creates a {@link LabelumFigure} as figure.
 */
@objid ("7e90337a-1dec-11e2-8cad-001ec947c8cc")
public class ElementLabelEditPart extends AbstractNodeEditPart {
    /**
     * Creates the label edit part.
     */
    @objid ("7e90337c-1dec-11e2-8cad-001ec947c8cc")
    public ElementLabelEditPart() {
        super();
    }

    @objid ("7e903383-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
        
            IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText == null) {
                return;
            }
        
            final LabelumFigure label = getLabelFigure(getFigure());
            final CellEditorLocator cellEditorLocator = new EditorLocatorForLabelFigure(
                    label,
                    (String s) -> label.setText(s))
                            .setAutoExpand(true);
        
            TextDirectEditManager manager = new TextDirectEditManager(
                    this,
                    cellEditorLocator,
                    HAlign.Left,
                    editableText.getText())
                            .setMultiline(false)
                            .setWrap(false);
        
            manager.show();
        
        }
        super.performRequest(req);
    }

    /**
     * Added the handling of {@link IGmObject#PROPERTY_LABEL} property change events:
     * <ul>
     * <li>updates the visual
     * <li>and requests a resize to preferred size if available.
     * </ul>
     */
    @objid ("7e903389-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        
            // If preferred size if not the same as current size, check if it is
            // possible to resize this figure to its preferred size.
            tryFitToPreferredSize();
        } else {
            super.propertyChange(evt);
        }
    }

    @objid ("7e92959d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        if (((GmModel) getModel()).getEditableText() != null) {
            installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
        }
    }

    /**
     * Creates a {@link LabelumFigure}.
     */
    @objid ("7e9295a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final GmElementLabel model = (GmElementLabel) getModel();
        
        final LabelumFigure f = new LabelumFigure(model.getLabel());
        
        // Set style independent properties
        f.setLabelAlignment(PositionConstants.LEFT);
        f.setOpaque(false);
        if (model.isWrapped()) {
            f.setTextLayouter(NativeTextLayouter.INSTANCE);
        } else {
            f.setTextLayouter(NoBreakTextLayouter.INSTANCE);
        }
        
        // Set style dependent properties
        refreshFromStyle(f, model.getDisplayedStyle());
        return f;
    }

    @objid ("7e9295a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final GmElementLabel model = (GmElementLabel) getModel();
        LabelumFigure fig = getLabelFigure(aFigure);
        
        StyleKey textColorStyleKey = model.getStyleKey(MetaKey.TEXTCOLOR);
        if (textColorStyleKey != null) {
        
            fig.setTextColor(style.getColor(textColorStyleKey));
        }
        
        StyleKey fontStyleKey = model.getStyleKey(MetaKey.FONT);
        if (fontStyleKey != null) {
            fig.setFont(style.getFont(fontStyleKey));
        }
        
        updateVisibility(aFigure);
    }

    @objid ("7e9295af-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmElementLabel model = (GmElementLabel) getModel();
        final LabelumFigure labelFigure = getLabelFigure(getFigure());
        labelFigure.setText(model.getLabel());
        labelFigure.getParent().setConstraint(labelFigure, model.getLayoutData());
    }

    @objid ("7e9295b2-1dec-11e2-8cad-001ec947c8cc")
    protected void updateVisibility(IFigure aFigure) {
        final boolean visible = getModel().isVisible();
        if (visible) {
            aFigure.setVisible(true);
        } else {
            aFigure.setVisible(false);
        }
    }

    /**
     * If preferred size if not the same as current size, ask if it is
     * possible to resize this figure to its preferred size.
     */
    @objid ("fab5af59-5531-4ae7-b4da-ed144f0bc0d4")
    private void tryFitToPreferredSize() {
        final IFigure fig = getFigure();
        // Get the current size.
        Dimension currentSize = new PrecisionDimension(fig.getSize());
        
        // ask for preferred size in within current width
        Dimension updatedPrefSize = new PrecisionDimension(fig.getPreferredSize(fig.getSize().width(), -1));
        
        if (!currentSize.equals(0, 0) && !updatedPrefSize.equals(currentSize)) {
            fig.translateToAbsolute(currentSize);
            fig.translateToAbsolute(updatedPrefSize);
        
            ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            changeBoundsRequest.setEditParts(this);
            changeBoundsRequest.setSizeDelta(updatedPrefSize.getShrinked(currentSize));
        
            Command resizeCommand = getCommand(changeBoundsRequest);
            if (resizeCommand != null && resizeCommand.canExecute()) {
                resizeCommand.execute();
            }
        }
    }

    /**
     * Get the {@link LabelumFigure} from the main figure.
     * <p>
     * To be redefined if {@link #createFigure()} is redefined and returns a composite figure.
     * 
     * @param mainFig the main figure, usually {@link #getFigure()}
     * @return the {@link LabelumFigure}
     */
    @objid ("2ef4a1eb-e9ed-4eeb-8c2e-5802aeaa706d")
    protected LabelumFigure getLabelFigure(IFigure mainFig) {
        return (LabelumFigure) mainFig;
    }

}
