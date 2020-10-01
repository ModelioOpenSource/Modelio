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

package org.modelio.diagram.editor.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.tools.ClonePropertiesSelectionTool;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.platform.model.ui.swt.SelectionHelper;

@objid ("65c52afc-33f7-11e2-95fe-001ec947c8cc")
public class RestyleHandler {
    @objid ("fd176a18-1cd5-4cfe-8f31-808ec4586654")
    private static GraphicalEditPart s;

    @objid ("65c52afd-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_PART) final MPart part, @Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if ((part.getObject() instanceof AbstractDiagramEditor)) {
            AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
            GraphicalViewer viewer = editor.getGraphicalViewer();
            if (viewer != null) {
                // Configure the edit domain
                // Set the active and default tool
                    viewer.getEditDomain().setActiveTool(new ClonePropertiesSelectionTool((GraphicalEditPart) s));
            }
        }
    }

    @objid ("ee240366-c24a-487c-803a-e8763efec320")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if (SelectionHelper.size(selection) == 1) {
            GraphicalEditPart editPart = SelectionHelper.getFirst(selection, GraphicalEditPart.class);
            if (! (editPart instanceof AbstractDiagramEditPart)) { 
                s = editPart;
            }
        } 
        if (s!=null && !s.isActive())
            s = null;
        return s != null;
    }

}
