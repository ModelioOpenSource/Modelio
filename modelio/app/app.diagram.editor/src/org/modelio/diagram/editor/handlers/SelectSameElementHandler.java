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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the "select same element in diagram" command.
 */
@objid ("c35fa5cb-632b-4b4a-a23e-81e7cb529f1b")
public class SelectSameElementHandler {
    @objid ("9e89d9dc-b532-4bff-8afa-c1b8f94679f2")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection iSelection, @Named (IServiceConstants.ACTIVE_PART) final MPart part) {
        if (part.getObject() instanceof AbstractDiagramEditor) {
        
            Set<MClass> metaclass = getSelectionMetaclass(iSelection);
        
            AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
            GraphicalViewer viewer = editor.getGraphicalViewer();
            if (viewer != null) {
                viewer.setSelection(new StructuredSelection(getSelectableEditParts(viewer, metaclass)));
            }
        }
        
    }

    @objid ("8cb9210f-1ba0-4dcb-8068-186ad19987ed")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        return !getSelectionMetaclass(iSelection).isEmpty();
    }

    @objid ("1a0b39d0-f418-4c1c-bd3a-b7e3c505d7a9")
    public Set<MClass> getSelectionMetaclass(ISelection iSelection) {
        Set<MClass> selectedElements = new HashSet<>();
        if (iSelection instanceof IStructuredSelection) {
            List<?> selectionAsList = ((IStructuredSelection) iSelection).toList();
            for (Object selectedObject : selectionAsList) {
                if (selectedObject instanceof GraphicalEditPart) {
                    MObject obj = getRelatedMObject((GraphicalEditPart) selectedObject);
                    selectedElements.add(obj.getMClass());
                }
            }
        }
        return selectedElements;
    }

    @objid ("4d570d91-2a08-43ae-baa4-596c6de3d8b9")
    private MObject getRelatedMObject(EditPart gpart) {
        return ((GmModel) gpart.getModel()).getRelatedElement();
    }

    @objid ("50b22754-a40f-4b48-b385-8e7bce34fb9b")
    protected List<EditPart> getSelectableEditParts(final GraphicalViewer viewer, Set<MClass> metaclass) {
        List<EditPart> selectableChildren = new ArrayList<>();
        
        List<?> children = viewer.getContents().getChildren();
        for (Object child : children) {
            if (child instanceof EditPart) {
                EditPart childPart = (EditPart) child;
                for (EditPart allEditPart : getChildren(childPart)) {
                    if (allEditPart.isSelectable() && allEditPart.getModel() instanceof GmModel && metaclass.contains(getRelatedMObject(allEditPart).getMClass())) {
                       if(!selectableChildren.contains(allEditPart.getParent()) && !selectableChildren.contains(allEditPart))
                        selectableChildren.add(allEditPart);
                    }
                }
            }
        }
        return selectableChildren;
    }

    @objid ("6f21f43d-6d4e-4f9e-9754-49bc88e9f2d2")
    private List<EditPart> getChildren(Object parent) {
        List<EditPart> results = new ArrayList<>();
        if (parent instanceof EditPart) {
            results.add((EditPart) parent);
        
            for (Object children : ((EditPart) parent).getChildren()) {
                results.addAll(getChildren(children));
            }
        
            if (parent instanceof NodeEditPart) {
                for (Object children : ((NodeEditPart) parent).getSourceConnections()) {
                    results.addAll(getChildren(children));
                }
        
                for (Object children : ((NodeEditPart) parent).getTargetConnections()) {
                    results.addAll(getChildren(children));
                }
            }
        }
        return results;
    }

}
