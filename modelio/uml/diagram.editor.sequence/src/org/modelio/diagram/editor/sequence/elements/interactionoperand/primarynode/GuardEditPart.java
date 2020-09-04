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

package org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Edit part for {@link GmGuardLabel}.
 */
@objid ("d90b2a52-55b6-11e2-877f-002564c97630")
public class GuardEditPart extends ElementLabelEditPart {
    @objid ("d90b2a5b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure mainFig = new Figure();
        mainFig.setOpaque(false);
        mainFig.setLayoutManager(new BorderLayout());
        
        IFigure label = super.createFigure();
        label.setOpaque(true);
        label.setBackgroundColor(ColorConstants.white);
        mainFig.add(label, BorderLayout.LEFT, 0);
        
        // transparent figure that fills remaining space
        Figure fillerFig = new Figure();
        fillerFig.setOpaque(false);
        fillerFig.setLayoutManager(new BorderLayout());
        mainFig.add(fillerFig, BorderLayout.CENTER, 1);
        return mainFig;
    }

    @objid ("d90cb0ba-55b6-11e2-877f-002564c97630")
    @Override
    public void performRequest(final Request req) {
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
        } else {
            super.performRequest(req);
        }
    }

    @objid ("d90cb0bf-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        final GmElementLabel model = (GmElementLabel) getModel();
        
        aFigure.setForegroundColor(style.getColor(model.getStyleKey(MetaKey.TEXTCOLOR)));
        aFigure.setFont(style.getFont(model.getStyleKey(MetaKey.FONT)));
        
        LabelumFigure label = getLabelFigure(aFigure);
        
        StyleKey textColorStyleKey = model.getStyleKey(MetaKey.TEXTCOLOR);
        if (textColorStyleKey != null) {
            label.setForegroundColor(style.getColor(textColorStyleKey));
        }
        
        StyleKey fontStyleKey = model.getStyleKey(MetaKey.FONT);
        if (fontStyleKey != null) {
            label.setFont(style.getFont(fontStyleKey));
        }
    }

    @objid ("d90cb0c8-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmElementLabel model = (GmElementLabel) getModel();
        final IFigure fig = getFigure();
        
        getLabelFigure(fig).setText(model.getLabel());
        
        fig.getParent().setConstraint(fig, model.getLayoutData());
    }

    @objid ("60d0aec2-e519-47e9-9feb-fbc9c161801a")
    @Override
    protected LabelumFigure getLabelFigure(IFigure mainFig) {
        if (mainFig instanceof LabelumFigure) {
            return (LabelumFigure) mainFig;
        } else {
            return (LabelumFigure) mainFig.getChildren().get(0);
        }
    }

}
