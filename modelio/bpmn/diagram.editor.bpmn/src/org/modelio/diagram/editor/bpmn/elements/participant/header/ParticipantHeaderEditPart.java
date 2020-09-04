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

package org.modelio.diagram.editor.bpmn.elements.participant.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.modelio.diagram.editor.bpmn.elements.participant.BpmnParticipantElementDropEditPolicy;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.figures.rotated.RotatedFigureContainer;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.drawings.core.HAlign;

/**
 * Specialization of the {@link ModelElementHeaderEditPart} that allows selection while delegating the actual selection feedback to the PartitionEditPart that contains it.
 * 
 * @author fpoyer
 */
@objid ("0f193ce3-7541-46ba-be0e-122363f262dd")
public class ParticipantHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("21ad9d0a-5612-454a-b985-bf4d26b34537")
    @Override
    public DragTracker getDragTracker(Request request) {
        // Actually delegate selection to parent
        if (getParent() != null) {
            return getParent().getDragTracker(request);
        }
        // else
        return super.getDragTracker(request);
    }

    @objid ("0c7c8561-6201-4bf3-8b20-8d3353671892")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("17de70c1-3816-4380-ab3c-2947bffd99f4")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            LabelumFigure label = getMainLabelFigure();
            IEditableText editableText = getModel().getEditableText();
            if (editableText == null) {
                return;
            }
        
            final CellEditorLocator cellEditorLocator = new CellEditorLocator() {
                @Override
                public void relocate(CellEditor cellEditor) {
        
                    Rectangle relRect = label.getBounds().getCopy();
        
                    Rectangle absRect = relRect.getCopy();
                    label.translateToAbsolute(absRect);
        
                    Point controlPrefSize = cellEditor.getControl().computeSize(-1, -1);
                    int controlW = Math.max(relRect.width, Math.max(absRect.width, controlPrefSize.x));
                    int controlH = relRect.height;
        
                    cellEditor.getControl().setFont(label.getTextFont());
                    cellEditor.getControl().setBounds(absRect.x,
                            absRect.y + (absRect.height / 2) - controlH / 2,
                            controlW,
                            controlH);
                }
        
            };
        
            new TextDirectEditManager(
                    this,
                    cellEditorLocator,
                    HAlign.Left,
                    editableText.getText())
                            .show();
        } else {
            super.performRequest(req);
        }
    }

    @objid ("e89d3d60-702a-49c6-9924-2dca1f7b4a0b")
    @Override
    public void setFocus(boolean value) {
        // Actually delegate focus to parent (PartitionContainer)
        if (getParent() != null) {
            getParent().setFocus(value);
        }
    }

    @objid ("215bd57a-47fa-4928-9726-2962843c64e8")
    @Override
    protected IFigure createFigure() {
        if (getModel().isHorizontal()) {
            return super.createFigure();
        } else {
            return new RotatedFigureContainer(super.createFigure(), 90);
        }
    }

    @objid ("46aee48f-072c-4799-b28f-01236c596cf6")
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

    @objid ("89a7df3c-864c-46ff-bb0e-47d3da80b9a4")
    @Override
    public GmBpmnParticipantHeader getModel() {
        return (GmBpmnParticipantHeader) super.getModel();
    }

    @objid ("3290422e-6296-4b75-8341-610826e97e8a")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnParticipantElementDropEditPolicy());
    }

}
