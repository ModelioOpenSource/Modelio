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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.plugin.ToolRegistry;

@objid ("65c78d93-33f7-11e2-95fe-001ec947c8cc")
public class SwitchToolHandler {
    @objid ("65c78d94-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named("toolId") String toolId, ToolRegistry toolregistry, @Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        ToolEntry toolEntry = toolregistry.getTool(toolId);
        Tool newTool = toolEntry.createTool();
        
        if (! (part.getObject() instanceof AbstractDiagramEditor)) {
            return;
        }
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        // Switch the tool
        editor.getRootEditPart().getViewer().getEditDomain().setActiveTool(newTool);
        
        // Force refresh of the tool state by simulating a mouse move event
        Point coordinates = Display.getCurrent().map(null,
                                                     Display.getCurrent().getCursorControl(),
                                                     Display.getCurrent().getCursorLocation());
        
        Event e = new Event();
        e.stateMask = SWT.None;
        e.x = coordinates.x;
        e.y = coordinates.y;
        e.widget = Display.getCurrent().getCursorControl();
        newTool.mouseMove(new MouseEvent(e), editor.getRootEditPart().getViewer());
        
    }

}
