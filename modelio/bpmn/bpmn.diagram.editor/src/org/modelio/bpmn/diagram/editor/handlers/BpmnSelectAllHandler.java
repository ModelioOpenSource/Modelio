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

package org.modelio.bpmn.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.modelio.bpmn.diagram.editor.elements.workflow.WorkflowEditPart;
import org.modelio.diagram.editor.handlers.SelectAllHandler;

/**
 * Bpmn-specific implementation of the {@link SelectAllHandler}, taking workflows into account.
 */
@objid ("00b30bd7-1b72-486b-b073-d46209d95f31")
public class BpmnSelectAllHandler extends SelectAllHandler {
    @objid ("9211191c-35ce-4ff7-bd62-37933c3602e9")
    @Override
    protected List<EditPart> getSelectableEditParts(GraphicalViewer viewer) {
        List<EditPart> selectableChildren = new ArrayList<>();
        for (Object child : viewer.getContents().getChildren()) {
            if (child instanceof WorkflowEditPart) {
                WorkflowEditPart workflowPart = (WorkflowEditPart) child;
                for (Object workflowChild : workflowPart.getChildren()) {
                    EditPart childPart = (EditPart) workflowChild;
                    if (childPart.isSelectable()) {
                        selectableChildren.add(childPart);
                    }
                }
            } else if (child instanceof EditPart) {
                EditPart childPart = (EditPart) child;
                if (childPart.isSelectable()) {
                    selectableChildren.add(childPart);
                }
            }
        }
        return selectableChildren;
    }

}
