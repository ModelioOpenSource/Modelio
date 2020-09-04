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

package org.modelio.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Handler that will select everything possible in the active diagram.
 */
@objid ("65c78d6d-33f7-11e2-95fe-001ec947c8cc")
public class SelectAllHandler {
    @objid ("65c78d6f-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named (IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof AbstractDiagramEditor)) {
            return null;
        }
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        GraphicalViewer viewer = editor.getGraphicalViewer();
        if (viewer != null) {
            viewer.setSelection(new StructuredSelection(getSelectableEditParts(viewer)));
        }
        return null;
    }

    /**
     * Retrieves edit parts which can be selected on the diagram background.
     * 
     * @param viewer from which the edit parts are to be retrieved
     * @return list of selectable EditParts
     */
    @objid ("65c78d76-33f7-11e2-95fe-001ec947c8cc")
    protected List<EditPart> getSelectableEditParts(final GraphicalViewer viewer) {
        List<EditPart> selectableChildren = new ArrayList<>();
        List<?> children = viewer.getContents().getChildren();
        for (Object child : children) {
            if (child instanceof EditPart) {
                EditPart childPart = (EditPart) child;
                if (childPart.isSelectable() == true) {
                    selectableChildren.add(childPart);
                }
            }
        }
        return selectableChildren;
    }

}
