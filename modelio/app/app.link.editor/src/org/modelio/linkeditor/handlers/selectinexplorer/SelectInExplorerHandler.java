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
package org.modelio.linkeditor.handlers.selectinexplorer;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.edge.EdgeEditPart;
import org.modelio.linkeditor.gef.node.NodeEditPart;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler class for Select In Explorer command.
 */
@objid ("1b5a81e9-5e33-11e2-b81d-002564c97630")
public class SelectInExplorerHandler {
    @objid ("1b5a81eb-5e33-11e2-b81d-002564c97630")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection, IModelioNavigationService navigationService) {
        navigationService.fireNavigate(getSelection(iSelection));
    }

    @objid ("1b5a81f2-5e33-11e2-b81d-002564c97630")
    protected List<MObject> getSelection(ISelection selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof IStructuredSelection) {
            List<?> selectedObjects = ((IStructuredSelection) selection).toList();
            for (Object selectedObject : selectedObjects) {
                if (selectedObject instanceof EdgeEditPart) {
                    Edge edge = (Edge) ((EdgeEditPart) selectedObject).getModel();
                    if (edge.data != null && edge.data instanceof MObject) {
                        selectedElements.add((MObject) edge.data);
                    }
                } else if (selectedObject instanceof NodeEditPart) {
                    GraphNode node = ((NodeEditPart) selectedObject).getModel();
                    if (node.getData() != null) {
                        selectedElements.add(node.getData());
                    }
                } else if (selectedObject instanceof BackgroundEditPart) {
                    GraphNode node = ((BackgroundEditPart) selectedObject).getModel().getCenter();
                    if (node.getData() != null) {
                        selectedElements.add(node.getData());
                    }
                }
            }
        }
        return selectedElements;
    }

    @objid ("1b5a81f8-5e33-11e2-b81d-002564c97630")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        List<MObject> listSelection = getSelection(iSelection);
        for (MObject selectedElement : listSelection) {
            if (selectedElement instanceof ImpactLink) {
                return false;
            }
        }
        return listSelection.size() > 0;
    }

}
