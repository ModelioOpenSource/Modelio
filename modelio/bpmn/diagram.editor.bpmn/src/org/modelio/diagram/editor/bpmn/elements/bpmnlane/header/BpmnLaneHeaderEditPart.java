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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.policies.MethodologicalLinkUpdateDropEditPolicy;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.figures.rotated.RotatedFigureContainer;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;

/**
 * Specialization of the {@link ModelElementHeaderEditPart} that allows selection while delegating the actual selection feedback to the EditPart that contains it.
 */
@objid ("611f175a-55b6-11e2-877f-002564c97630")
public class BpmnLaneHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("611f175e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("611f1763-55b6-11e2-877f-002564c97630")
    @Override
    public void setFocus(boolean value) {
        // Actually delegate focus to parent
        if (getParent() != null) {
            getParent().setFocus(value);
        }
    }

    @objid ("611f1767-55b6-11e2-877f-002564c97630")
    @Override
    public DragTracker getDragTracker(Request request) {
        // Actually delegate selection to parent
        if (getParent() != null) {
            return getParent().getDragTracker(request);
        }
        // else
        return super.getDragTracker(request);
    }

    @objid ("611f176d-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        if (isHorizontalLaneOrientation()) {
            return new RotatedFigureContainer(super.createFigure(), 90);
        } else {
            return super.createFigure();
        }
    }

    @objid ("611f1778-55b6-11e2-877f-002564c97630")
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

    @objid ("2503af85-8468-4d72-a2f8-6c98e4ff5f57")
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

    /**
     * @return whether lanes should be displayed horizontally or vertically.
     */
    @objid ("025e9be7-0e05-4b24-8604-79792ce41074")
    private boolean isHorizontalLaneOrientation() {
        return getModel().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
    }

    @objid ("94c89ec2-8702-4432-890d-c56a3b033356")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(ModelElementDropRequest.TYPE, new MethodologicalLinkUpdateDropEditPolicy(PartitionElement.MdaTypes.STEREOTYPE_ELT));
    }

}
