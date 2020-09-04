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

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.ghostnode.GhostNodeEditPart;

@objid ("0fa65063-68c9-4930-9453-9b0c43bf79df")
public class RenameElementHandler {
    @objid ("b661731e-4a14-4746-8b8e-cdc139e5819c")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        return SelectionHelper.toStream(selection, GraphicalEditPart.class)
                        .filter(ep -> !(ep instanceof AbstractDiagramEditPart) && !(ep instanceof GhostNodeEditPart))
                        .count() == 1;
    }

    @objid ("e912a852-5540-4312-96b8-c9b5acd33d41")
    @Execute
    public Object execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selected = SelectionHelper.toStream(selection, GraphicalEditPart.class)
                .filter(ep -> !(ep instanceof AbstractDiagramEditPart) && !(ep instanceof GhostNodeEditPart))
                .collect(Collectors.toList());
        edit(selected);
        return null;
    }

    @objid ("49928eb4-cc92-4a9c-9506-89b3fbf66c42")
    void edit(List<GraphicalEditPart> selected) {
        if (selected.isEmpty()) {
            return;
        }
        
        GroupRequest editReq = new GroupRequest(RequestConstants.REQ_DIRECT_EDIT);
        editReq.setEditParts(selected);
        
        // Perform request on the selected edit part
        selected.get(0).performRequest(editReq);
    }

}
