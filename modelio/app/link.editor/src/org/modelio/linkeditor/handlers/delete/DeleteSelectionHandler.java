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

package org.modelio.linkeditor.handlers.delete;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.edge.EdgeEditPart;
import org.modelio.linkeditor.gef.node.NodeEditPart;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.linkeditor.view.ILinkEditorView;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for Delete Selection command.
 */
@objid ("1b451592-5e33-11e2-b81d-002564c97630")
public class DeleteSelectionHandler {
    @objid ("1b451594-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection, IProjectService projectService) {
        List<MObject> selectedElements = getSelection(selection);
        
        ICoreSession transactionManager = projectService.getSession();
        boolean validElementFound = false;
        try (ITransaction t = transactionManager.getTransactionSupport().createTransaction("delete selected elements")) {
            for (MObject el : selectedElements) {
                if (el.isValid()) {
                    el.delete();
                    validElementFound = true;
                }
            }
            if (validElementFound) {
                t.commit();
            } else {
                t.rollback();
            }
        }
        return null;
    }

    @objid ("1b45159b-5e33-11e2-b81d-002564c97630")
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

    @objid ("1b4515a1-5e33-11e2-b81d-002564c97630")
    @CanExecute
    public boolean canExecute(MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        // Part must be in edition mode
        if (part.getObject() instanceof ILinkEditorView) {
            final ILinkEditorView ILinkEditorView = (ILinkEditorView) part.getObject();
            if (!ILinkEditorView.getLinkEditor().isEditMode()) {
                return false;
            }
        }
        
        // Selection must be OK, ie all selected element must be modifiable
        List<MObject> selection = getSelection(iSelection);
        for (MObject selectedElement : selection) {
            if (selectedElement instanceof ImpactLink || !selectedElement.isModifiable()) {
                return false;
            }
        }
        
        // Selection must not be empty
        return selection.size() > 0;
    }

}
