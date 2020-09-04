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

package org.modelio.diagram.editor.statik.elements.constraint;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;
import org.modelio.diagram.elements.common.edition.MultilineTextCellEditor;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Specialization of the {@link ModelElementHeaderEditPart} providing a {@link MultilineTextCellEditor} instead of a {@link TextCellEditor}.
 */
@objid ("76297c0d-6e41-4105-8244-c1d046bb357f")
public class ConstraintBodyLabelEditPart extends ModelElementHeaderEditPart {
    @objid ("e31b2857-c01f-4e53-98e4-d988461ba78f")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            // Redefined to be multiline
            GmModelElementHeader gm = (GmModelElementHeader) getModel();
            if (gm.getRelatedElement() == null
                    || gm.getRelatedElement().isShell()
                    || gm.getRelatedElement().isDeleted()
                    || !gm.getRelatedElement().getStatus().isModifiable()) {
                return;
            }
        
            final IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText != null) {
                IHeaderFigure headerFigure = getHeaderFigure(getFigure());
        
                TextDirectEditManager
                        .forLabelum(
                                this,
                                headerFigure.getMainLabelFigure(),
                                editableText.getText(),
                                !headerFigure.isWrapped())
                        .setMultiline(true)
                        .show();
            }
        } else {
            super.performRequest(req);
        }
    }

    @objid ("b69b320c-0ee2-4f20-9e05-28db8c86d862")
    @Override
    protected IFigure createFigure() {
        final WrappedHeaderFigure fig = (WrappedHeaderFigure) super.createFigure();
        updateAlignment(fig, ((GmModel) getModel()).getDisplayedStyle());
        return fig;
    }

    @objid ("18ccb615-545b-4b3d-bd91-348fd1b3260a")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        final WrappedHeaderFigure fig = (WrappedHeaderFigure) this.figure;
        if (fig != null) {
            updateAlignment(fig, style);
        }
    }

    @objid ("76a5fe82-95e1-48d7-9d7e-04b9ba750d42")
    protected void updateAlignment(final WrappedHeaderFigure fig, IStyle style) {
        // Alignment
        HAlign align = style.getProperty(GmConstraintStyleKeys.ALIGNMENT);
        switch (align) {
        case Left:
            fig.setMainLabelAlignement(PositionConstants.LEFT);
            break;
        case Right:
            fig.setMainLabelAlignement(PositionConstants.RIGHT);
            break;
        default:
        case Center:
            fig.setMainLabelAlignement(PositionConstants.CENTER);
            break;
        
        }
    }

    @objid ("4dd8e197-5db2-4730-8dcc-ae4686b5f844")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            // When name has changed force resize of the constraint node if needed.
            if (this.getParent().getModel() != null) {
                final GmNodeModel gmNodeModel = (GmNodeModel) this.getParent().getModel();
                Object layoutData = gmNodeModel.getLayoutData();
                if (layoutData != null) {
                    String mainLabelFigureText = this.getMainLabelFigure().getText();
                    String modelString = ((GmConstraintBodyLabel) this.getModel()).getMainLabel();
                    if (!mainLabelFigureText.equals(modelString)) {
                        Rectangle layoutDataRect = (Rectangle) layoutData;
                        if (layoutDataRect.width == -1) {
                            // We are creating the graphic object
                            layoutData = new Rectangle(layoutDataRect.x, layoutDataRect.y, 100, -1);
                            gmNodeModel.setLayoutData(layoutData);
                        } else {
                            // If the width of the text is greater than the container then we force to resize the height of the container by setting the height to -1
                            layoutData = new Rectangle(layoutDataRect.x, layoutDataRect.y, layoutDataRect.width, -1);
                            gmNodeModel.setLayoutData(layoutData);
                        }
                    }
        
                }
            }
        }
        
        super.propertyChange(evt);
    }

}
