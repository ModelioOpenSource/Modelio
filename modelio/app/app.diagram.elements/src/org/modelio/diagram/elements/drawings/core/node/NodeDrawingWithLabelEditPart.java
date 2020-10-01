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

package org.modelio.diagram.elements.drawings.core.node;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.elements.drawings.core.VAlign;
import org.modelio.diagram.elements.drawings.text.TextFigure;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Edit part for a node that can have an editable label.
 */
@objid ("f7e3fc12-4eb1-4c2a-9f90-0433bf6b278f")
public abstract class NodeDrawingWithLabelEditPart extends NodeDrawingEditPart {
    @objid ("467723ef-8a05-45f2-8af4-54abca1eddf9")
    private TextFigure textLabel;

    @objid ("3676b09b-8530-46f2-a7a9-cfb67dadc7ce")
    @Override
    public void performRequest(Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            TextFigure label = getLabelFigure(getFigure());
            final CellEditorLocator locator = 
                    new EditorLocatorForLabelFigure(
                            label, 
                            str -> label.setContents(str))
                    .setFontGetter(() -> label.getFont());
        
            new TextDirectEditManager(
                    this, 
                    locator, 
                    (HAlign) getModel().getDisplayedStyle().getProperty(getTextAlignKey()), 
                    getModel().getLabel())
            .setMultiline(true)
            .setWrap(true)
            .show();
        
        } else {
            super.performRequest(req);
        }
    }

    @objid ("f157f253-6e93-4603-bce7-d9656ca6e496")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName())
        {
        case IGmObject.PROPERTY_LABEL:
            refreshVisuals();
            break;
        default:
            super.propertyChange(evt);
        }
    }

    @objid ("48bf082e-81a1-4252-9b62-120fe3b576b0")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeLabelDirectEditPolicy());
    }

    /**
     * Creates and ad the label figure to the edit part figure
     * 
     * @param mainFigure the edit part figure.
     */
    @objid ("ed72b732-79ed-4dda-a6d3-794a82a82fbd")
    protected void createLabelFigure(IFigure mainFigure) {
        // enforce the main figure layout
        mainFigure.setLayoutManager(new GridLayout(1,false));
        
        this.textLabel = new TextFigure();
        this.textLabel.setMinimumSize(new Dimension(20,20));
        //this.textLabel.setContents("A");
        
        GridData constraint = new GridData();
        constraint.horizontalAlignment = GridData.CENTER;
        constraint.verticalAlignment = GridData.CENTER;
        constraint.grabExcessHorizontalSpace = true;
        constraint.grabExcessVerticalSpace = true;
        
        mainFigure.add(this.textLabel, constraint);
    }

    /**
     * Get the label figure horizontal alignment style key.
     * 
     * @return the horizontal alignment style key.
     */
    @objid ("f1a435f3-4e2e-4dab-aa8b-fff29f6ca2e9")
    protected abstract StyleKey getHAlignKey();

    /**
     * Get the label figure.
     * <p>
     * Creates and add the label figure if it does not exist yet.
     * 
     * @param mainFigure the main figure
     * @return the label figure.
     */
    @objid ("bf80ef6f-41b7-40bd-9b64-ad005db24268")
    protected TextFigure getLabelFigure(IFigure mainFigure) {
        if (this.textLabel == null) {
            createLabelFigure(mainFigure);
        }
        return this.textLabel;
    }

    /**
     * Get the style key used to set the text alignment inside the label figure.
     * 
     * @return the text horizontal alignment style key.
     */
    @objid ("314a9911-9ec8-49e7-8e66-a56c450d2299")
    protected abstract StyleKey getTextAlignKey();

    /**
     * Get the label figure vertical alignment style key.
     * 
     * @return the vertical alignment style key.
     */
    @objid ("65955dde-19b2-4c70-b6d7-427baf8f48b2")
    protected abstract StyleKey getVAlignKey();

    /**
     * Overridden to refresh the label figure
     */
    @objid ("f6682b83-d42e-4448-8f62-c3c78a63aae5")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        refreshLabelFromStyle(aFigure, style);
    }

    /**
     * Refresh the label figure from the model style.
     * 
     * @param mainFigure the main figure
     * @param style the model style
     */
    @objid ("13999ea7-6808-4b1c-b503-5b29ae024cc8")
    protected void refreshLabelFromStyle(IFigure mainFigure, IStyle style) {
        GridData constraint = new GridData();
        constraint.horizontalAlignment = ((HAlign) style.getProperty(getHAlignKey())).toSwt();
        constraint.verticalAlignment = ((VAlign) style.getProperty(getVAlignKey())).toSwt();
        constraint.grabExcessHorizontalSpace = true;
        constraint.grabExcessVerticalSpace = true;
        
        
        final TextFigure labelFigure = getLabelFigure(mainFigure);
        mainFigure.setConstraint(labelFigure, constraint);
        
        final Font fontProperty = (Font) style.getProperty(getModel().getStyleKey(MetaKey.FONT));
        
        labelFigure.setTextColor((Color) style.getProperty(getModel().getStyleKey(MetaKey.TEXTCOLOR)));
        labelFigure.setTextFont(fontProperty);
        labelFigure.setHorizontalAligment((HAlign) style.getProperty(getTextAlignKey()));
    }

    @objid ("730116c0-dbce-4ead-a4c7-d6af0dd6505b")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        final IFigure fig = getFigure();
        final GmNodeDrawing model = getModel();
        
        final TextFigure labelFigure = getLabelFigure(fig);
        labelFigure.setContents(model.getLabel());
        labelFigure.revalidate();
    }

    /**
     * Override to add the label figure.
     */
    @objid ("5e23073a-c57f-4d94-a77d-8efa5e601a26")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        
        // Ensure the label exists
        getLabelFigure(figure);
    }

}
