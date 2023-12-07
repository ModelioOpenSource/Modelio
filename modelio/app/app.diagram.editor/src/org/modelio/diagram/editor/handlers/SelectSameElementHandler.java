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
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Handler for the "select same element in diagram" command.
 */
@objid ("c35fa5cb-632b-4b4a-a23e-81e7cb529f1b")
public class SelectSameElementHandler {
    @objid ("9e89d9dc-b532-4bff-8afa-c1b8f94679f2")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection iSelection, @Named (IServiceConstants.ACTIVE_PART) final MPart part) {
        if (part.getObject() instanceof AbstractDiagramEditor) {
        
            Set<MClass> metaclasses = getSelectionMetaclasses(iSelection);
        
            AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
            GraphicalViewer viewer = editor.getGraphicalViewer();
            if (viewer != null) {
                viewer.setSelection(new StructuredSelection(getSelectableEditParts(viewer, metaclasses)));
            }
        }
        
    }

    @objid ("8cb9210f-1ba0-4dcb-8068-186ad19987ed")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        return SelectionHelper.toStream(iSelection, EditPart.class)
        .anyMatch(o -> getRelatedMClass(o) != null);
        
    }

    @objid ("1a0b39d0-f418-4c1c-bd3a-b7e3c505d7a9")
    public Set<MClass> getSelectionMetaclasses(ISelection iSelection) {
        if (true) {
            return SelectionHelper.toStream(iSelection, EditPart.class)
                    .map(o -> getRelatedMClass(o))
                    .filter(mc -> mc != null)
                    .collect(Collectors.toSet());
        } else {
        
            Set<MClass> selectedElements = new HashSet<>();
        
            if (iSelection instanceof IStructuredSelection) {
                List<?> selectionAsList = ((IStructuredSelection) iSelection).toList();
                for (Object selectedObject : selectionAsList) {
                    if (selectedObject instanceof GraphicalEditPart) {
                        MClass cls = getRelatedMClass((GraphicalEditPart) selectedObject);
                        selectedElements.add(cls);
                    }
                }
            }
            return selectedElements;
        }
        
    }

    @objid ("46caeb4e-8f6f-4d5f-95b0-ee1148f70e69")
    private MClass getRelatedMClass(EditPart gpart) {
        Object model = gpart.getModel();
        if (! (model instanceof IGmModelRelated))
            return null;
        return ((IGmModelRelated) model).getRelatedMClass();
    }

    @objid ("50b22754-a40f-4b48-b385-8e7bce34fb9b")
    protected List<EditPart> getSelectableEditParts(final GraphicalViewer viewer, Set<MClass> metaclass) {
        List<EditPart> selectableEps = new ArrayList<>(viewer.getEditPartRegistry().size() / 2);
        
        if (true) {
            forAllChildren(viewer.getContents(), anEditPart -> {
                if (anEditPart.isSelectable()
                        && anEditPart.getModel() instanceof GmModel
                        && metaclass.contains(getRelatedMClass(anEditPart))
                        && !selectableEps.contains(anEditPart.getParent())
                        && !selectableEps.contains(anEditPart))
                    selectableEps.add(anEditPart);
            });
        } else {
            List<?> children = viewer.getContents().getChildren();
            for (Object child : children) {
                for (EditPart anEditPart : getAllChildren(child)) {
                    if (anEditPart.isSelectable() && anEditPart.getModel() instanceof GmModel && metaclass.contains(getRelatedMClass(anEditPart))) {
                        if(!selectableEps.contains(anEditPart.getParent()) && !selectableEps.contains(anEditPart))
                            selectableEps.add(anEditPart);
                    }
                }
            }
        }
        return selectableEps;
    }

    @objid ("6f21f43d-6d4e-4f9e-9754-49bc88e9f2d2")
    @Deprecated
    private List<EditPart> getAllChildren(Object parent) {
        List<EditPart> results = new ArrayList<>();
        if (parent instanceof EditPart) {
            EditPart parentEditPart = (EditPart) parent;
            results.add(parentEditPart);
        
            for (Object child : parentEditPart.getChildren()) {
                results.addAll(getAllChildren(child));
            }
        
            if (parent instanceof NodeEditPart) {
                for (Object child : ((NodeEditPart) parent).getSourceConnections()) {
                    results.addAll(getAllChildren(child));
                }
        
                for (Object child : ((NodeEditPart) parent).getTargetConnections()) {
                    results.addAll(getAllChildren(child));
                }
            }
        }
        return results;
    }

    @objid ("dcd91b65-f05e-42a6-96b1-c37b182634c3")
    private void forAllChildren(Object parent, Consumer<EditPart> action) {
        if (! (parent instanceof EditPart))
            return ;
        
        EditPart parentEditPart = (EditPart) parent;
        action.accept(parentEditPart);
        
        for (Object child : parentEditPart.getChildren()) {
            forAllChildren(child, action);
        }
        
        if (parent instanceof NodeEditPart) {
            for (Object child : ((NodeEditPart) parent).getSourceConnections()) {
                forAllChildren(child, action);
            }
        
            for (Object child : ((NodeEditPart) parent).getTargetConnections()) {
                forAllChildren(child, action);
            }
        }
        return ;
    }

}
