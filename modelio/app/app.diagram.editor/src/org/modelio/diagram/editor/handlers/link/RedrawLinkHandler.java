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
package org.modelio.diagram.editor.handlers.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler for the "Redraw link" contextual command.
 * <p>
 * Simply sets the current tool to {@link RedrawConnectionTool}, a custom tool with a custom made creation factory.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("65c2c8df-33f7-11e2-95fe-001ec947c8cc")
public class RedrawLinkHandler extends AbstractLinkHandler {
    /**
     * Execute the command. Execute the command.
     * @param selection the current diagram selection.
     */
    @objid ("65c2c8e1-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // First get the current selection
        LinkEditPart primarySelection = null;
        for (LinkEditPart editPart : SelectionHelper.toList(selection, LinkEditPart.class)) {
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                primarySelection = editPart;
                break;
            }
        }
        
        if (primarySelection != null) {
            // Now instantiate the tool.
            EditDomain editDomain = primarySelection.getViewer().getEditDomain();
            RedrawConnectionTool redrawTool = new RedrawConnectionTool(primarySelection);
            // Set the tool as the active one.
            editDomain.setActiveTool(redrawTool);
        }
        
    }

}
