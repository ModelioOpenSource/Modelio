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

package org.modelio.model.browser.view.handlers.copy;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Pastes the elements from the clipboard.
 */
@objid ("976755fd-439f-11e2-b513-002564c97630")
public class CloneElementHandler {
    @objid ("9b86a23e-439f-11e2-b513-002564c97630")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selection contains only one modifiable element.
     * 
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("9b8b361e-439f-11e2-b513-002564c97630")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must have at least an element
        List<MObject> toClone = CloneElementHandler.getSelectedElements(selection);
        // No elements to paste
        if (toClone.isEmpty()) {
            return false;
        }
        MObject dest = CloneElementHandler.getPasteTarget(toClone);
        if (dest == null) {
            return false;
        }
        
        // Check the elements to clone can be added to dest
        for (MObject element : toClone) {
            if (!MTools.getAuthTool().canAdd(dest, element.getMClass())) {
                return false;
            }
            if (!CloneElementHandler.canBeParentOf(dest, element)) {
                return false;
            }
        
            if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                if (dest instanceof Operation && parameter.getReturned() != null) {
                    Operation targetOperation = (Operation) dest;
                    if (targetOperation.getReturn() != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @objid ("9b8b3627-439f-11e2-b513-002564c97630")
    private static MObject getPasteTarget(List<MObject> toClone) {
        MObject ret = null;
        for (MObject obj : toClone) {
            // All elements to clone must have the same parent
            MObject compositionOwner = obj.getCompositionOwner();
            if (ret != null && ret != compositionOwner) {
                return null;
            } else {
                ret = compositionOwner;
            }
        }
        return ret;
    }

    @objid ("9b8b362e-439f-11e2-b513-002564c97630")
    private static List<MObject> getSelectedElements(final Object selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof MObject) {
            selectedElements.add((MObject) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof MObject) {
                    selectedElements.add((MObject) element);
                } else if (element instanceof IAdaptable) {
                    final MObject adapter = ((IAdaptable) element).getAdapter(MObject.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

    /**
     * Cut the currently selected elements.
     * 
     * @param selection the current modelio selection.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("9b8b3636-439f-11e2-b513-002564c97630")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, Display currentDisplay) {
        ICoreSession session = this.projectService.getSession();
        
        // Sanity checks
        if (session == null) {
            return;
        }
        
        final List<MObject> pastedElements = CloneElementHandler.getSelectedElements(selection);
        MObject targetElement = CloneElementHandler.getPasteTarget(pastedElements);
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Clone")) {
            List<MObject> copyResult = new ArrayList<>();
        
            copyResult.addAll(MTools.getModelTool().copyElements(pastedElements, targetElement));
        
            transaction.commit();
        } catch (Exception e) {
            // Should catch InvalidModelManipulationException to display a popup box, but it
            // is not a RuntimeException.
            CloneElementHandler.reportException(e);
        }
    }

    @objid ("9b8cbcc4-439f-11e2-b513-002564c97630")
    static void reportException(Exception e) {
        // Show an error box
        String title = BrowserViewActivator.I18N.getMessage("CannotPasteClipboard");
        
        MessageDialog.openError(null, title, e.getLocalizedMessage());
        
        BrowserViewActivator.LOG.error(e);
    }

    /**
     * Tells whether 'child' can be owned by 'parent'.
     * 
     * @param owner The future parent element
     * @param composed a child element
     * @return true only if parent can contain the child.
     */
    @objid ("9b8cbcc7-439f-11e2-b513-002564c97630")
    private static boolean canBeParentOf(final MObject owner, final MObject composed) {
        return owner.getMClass().getMetamodel().getMExpert().canCompose(owner, composed, null);
    }

}
