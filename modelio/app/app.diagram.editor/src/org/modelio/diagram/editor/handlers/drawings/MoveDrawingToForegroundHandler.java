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
package org.modelio.diagram.editor.handlers.drawings;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.MoveToLayerCommand;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler that moves free drawing to the foreground layer.
 */
@objid ("bab2ea51-519b-448e-8f77-4e4332693f7f")
public class MoveDrawingToForegroundHandler {
    /**
     * Execute the handler
     * @param selection the Eclipse selection
     */
    @objid ("b15f1f77-3502-4c80-a56a-0bd7e5646332")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        MoveToLayerCommand cmd = createGefCommand(selection);
        
        if (cmd != null && cmd.canExecute()) {
            CommandStack cmdStack = SelectionHelper.getFirst(selection, GraphicalEditPart.class).getViewer().getEditDomain().getCommandStack();
            cmdStack.execute(cmd);
        }
        
    }

    @objid ("67d99f48-12c5-4434-b9fc-646b836af90e")
    private MoveToLayerCommand createGefCommand(ISelection selection) {
        List<IGmDrawing> selected = SelectionHelper.toList(selection, IGmDrawing.class);
        if (selected.isEmpty()) {
            return null;
        }
        
        MoveToLayerCommand cmd = new MoveToLayerCommand();
        
        cmd.addElements(selected);
        final List<IGmDrawingLayer> drawingLayers = selected.get(0).getDiagram().getDrawingLayers();
        IGmDrawingLayer fgl = drawingLayers.get(drawingLayers.size() - 1);
        
        cmd.setTarget(fgl);
        return cmd;
    }

    /**
     * @param selection the Eclipse selection
     * @return <code>true</code> to  enable the command, <code>false</code> to gray it.
     */
    @objid ("f0f959e9-63de-49a9-9415-53b9b26836bc")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        MoveToLayerCommand cmd = createGefCommand(selection);
        return  (cmd != null && cmd.canExecute());
    }

}
