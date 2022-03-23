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
package org.modelio.script.macro;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.script.macro.catalogdialog.CatalogDialog;
import org.modelio.script.plugin.Script;
import org.modelio.script.view.ScriptView;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("00470a54-6505-105c-84ef-001ec947cd2a")
public class OpenCatalogHandler {
    @objid ("cb0bf984-2de0-4329-a417-4960921cfd58")
    @Inject
    private EPartService partService;

    @objid ("00472958-6505-105c-84ef-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, IMacroService macroService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        CatalogDialog dlg = new CatalogDialog(shell, macroService, getSelectedElement(selection));
        
        
        MPart part = this.partService.findPart(ScriptView.PARTID);
        if (part != null && part.getContext() == null) {
            // Create script view if it is not created yet in order to run the
            // script
            if (!this.partService.isPartVisible(part)) {
                this.partService.showPart(part, PartState.CREATE);
            }
        }
        dlg.open();
        Script.LOG.debug("OpenCatalogHandler.execute()");
        
    }

    @objid ("00475c16-6505-105c-84ef-001ec947cd2a")
    @CanExecute
    public boolean canExecute() {
        return true;
    }

    @objid ("8232d28a-4f50-4424-a70f-1e268b711789")
    private List<MObject> getSelectedElement(IStructuredSelection selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection != null) {
            Object[] elements = selection.toArray();
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

}
