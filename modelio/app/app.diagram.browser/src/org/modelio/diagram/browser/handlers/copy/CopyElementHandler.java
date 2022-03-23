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
package org.modelio.diagram.browser.handlers.copy;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.platform.model.ui.swt.copy.PasteElementObject;
import org.modelio.platform.model.ui.swt.copy.PasteElementObject.PasteType;
import org.modelio.platform.model.ui.swt.copy.PasteElementTransfer;
import org.modelio.platform.model.ui.swt.copy.TransferItem;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Copies selected elements into the clipboard.
 */
@objid ("b1ebe066-54c7-11e2-ae63-002564c97630")
public class CopyElementHandler {
    @objid ("b1ebe068-54c7-11e2-ae63-002564c97630")
    boolean ctrlFlag;

    @objid ("b1ee41c3-54c7-11e2-ae63-002564c97630")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements is not empty.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("b1ee41c5-54c7-11e2-ae63-002564c97630")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Copy the currently selected elements.
     * @param selection the current modelio selection.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("b1ee41ce-54c7-11e2-ae63-002564c97630")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, Display currentDisplay) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return;
        }
        
        PasteElementObject toCopy = new PasteElementObject(PasteType.COPY);
        
        for (MObject element : selectedElements) {
            toCopy.addTransferedItems(new TransferItem(element, element.getCompositionOwner()));
        }
        
        Clipboard clipboard= new Clipboard(currentDisplay);
        clipboard.setContents(new Object[] { toCopy }, new Transfer[] { PasteElementTransfer.getInstance() });
        
    }

    @objid ("b1ee41d6-54c7-11e2-ae63-002564c97630")
    private static List<MObject> getSelectedElements(final Object selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof DiagramSet) {
            selectedElements.add((MObject) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof DiagramSet) {
                    selectedElements.add((MObject) element);
                }
            }
        }
        return selectedElements;
    }

}
