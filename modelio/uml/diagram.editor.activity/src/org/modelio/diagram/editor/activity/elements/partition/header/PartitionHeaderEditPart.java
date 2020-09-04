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

package org.modelio.diagram.editor.activity.elements.partition.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.figures.rotated.RotatedFigureContainer;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.drawings.core.HAlign;

/**
 * Specialisation of the ModelElementHeaderEditPart that allows selection while delegating the actual selection feedback to the PartitionEditPart that contains it.
 * 
 * @author fpoyer
 */
@objid ("2b096b29-55b6-11e2-877f-002564c97630")
public class PartitionHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("2b09923b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final GmPartitionHeader gm = (GmPartitionHeader) getModel();
        if (gm.isVertical()) {
            return super.createFigure();
        } else {
            // Create an horizontal version of the HeaderFigure
            return new RotatedFigureContainer(super.createFigure(), 90);
        }
    }

    @objid ("2b09e059-55b6-11e2-877f-002564c97630")
    @Override
    public void performRequest(Request req) {
        final GmPartitionHeader gm = (GmPartitionHeader) getModel();
        if (true && req.getType() == RequestConstants.REQ_DIRECT_EDIT && !gm.isVertical()) {
            // Horizontal partition : rotate the editor
            final CellEditorLocator cellEditorLocator = new CellEditorLocator() {
                @Override
                public void relocate(CellEditor cellEditor) {
                    LabelumFigure label = getMainLabelFigure();
        
                    final Rectangle relRect = label.getBounds();
                    final Rectangle absRect = relRect.getCopy();
                    label.translateToAbsolute(absRect);
        
                    Point prefSize = cellEditor.getControl().computeSize(-1, -1);
                    absRect.union(absRect.x, absRect.y, prefSize.x, prefSize.y);
        
                    cellEditor.getControl().setFont(label.getTextFont());
                    cellEditor.getControl().setBounds(absRect.x,
                            absRect.y + (absRect.height / 2) - relRect.height / 2,
                            Math.max(relRect.width, absRect.width),
                            relRect.height);
        
                }
            };
        
            IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText != null) {
                new TextDirectEditManager(this, cellEditorLocator, HAlign.Left, editableText.getText()).show();
            }
        } else {
            super.performRequest(req);
        }
    }

    @objid ("29560b9e-1d75-4806-878d-3213bcec408d")
    @Override
    protected IHeaderFigure getHeaderFigure(IFigure aFigure) {
        if (aFigure instanceof IHeaderFigure) {
            return (IHeaderFigure) aFigure;
        } else if (aFigure.getChildren().isEmpty()) {
            return null;
        } else {
            return getHeaderFigure((IFigure) aFigure.getChildren().get(0));
        }
    }

}
