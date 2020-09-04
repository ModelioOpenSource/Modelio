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

package org.modelio.app.project.conf.dialog.modules.list;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.module.GModule;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.mda.infra.service.IRTModule;

/**
 * ModuleStateEditingSupport provides EditingSupport implementation for the module state.
 */
@objid ("7293c7bd-733a-47fd-a394-4f73a9d83dbb")
class ModuleStateEditingSupport extends EditingSupport {
    @objid ("98792df1-1f93-4a79-9f0e-083db1b2d719")
    private TableViewer viewer;

    @objid ("d9c34ff3-f361-4a3f-b09f-42f890e8d67a")
    private IModuleManagementService moduleService;

    /**
     * Initialize the ModuleStateEditingSupport.
     * @param applicationContext the Eclipse 4 context
     * 
     * @param viewer The style viewer.
     */
    @objid ("38e961e8-a7e8-4620-9765-90e7c55f0303")
    public ModuleStateEditingSupport(TableViewer viewer, IModuleManagementService moduleService) {
        super(viewer);
        this.viewer = viewer;
        this.moduleService = moduleService;
    }

    @objid ("8a4d7dba-f5a7-4448-ae51-70e543d42707")
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @objid ("0e56d507-4272-4adb-b59b-4aacc51889e1")
    @Override
    protected CellEditor getCellEditor(Object element) {
        return new CheckboxCellEditor(this.viewer.getTable());
    }

    @objid ("8e8d346e-b214-46a8-a617-a42fae275a0d")
    @Override
    protected Object getValue(Object element) {
        if (element instanceof GModule) {
            IRTModule iModule = this.moduleService.getIRTModule((GModule) element);
            if (iModule != null && iModule.getState() == ModuleRuntimeState.Started) {
                return true;
            }
        }
        return false;
    }

    @objid ("6eb2865e-95eb-44e5-8b1c-0caf0610a979")
    @Override
    protected void setValue(Object element, Object value) {
        if (element instanceof GModule) {
            GModule gModule = (GModule) element;
        
            try {
                if ((Boolean) value) {
                    AppProjectConfExt.LOG.info("Starting module " + gModule.getName()); //$NON-NLS-1$
                    this.moduleService.activateModule(gModule);
                } else {
                    AppProjectConfExt.LOG.info("Stopping module " + gModule.getName()); //$NON-NLS-1$
                    this.moduleService.deactivateModule(gModule);
                }
            } catch (ModuleException e) {
                AppProjectConfExt.LOG.error(e);
                String title = AppProjectConfExt.I18N.getMessage("ModuleStateEditingSupport.startFail.title", gModule.getName(), e.getLocalizedMessage());
                String message = AppProjectConfExt.I18N.getMessage("ModuleStateEditingSupport.startFail.msg", gModule.getName(), e.getLocalizedMessage());
                MessageDialog.openError(this.viewer.getControl().getShell(), title, message);
            }
        }
        
        this.viewer.setSelection(new StructuredSelection(element));
    }

}
