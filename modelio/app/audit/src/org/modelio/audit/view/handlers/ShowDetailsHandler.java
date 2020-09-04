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

package org.modelio.audit.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.dialog.auditEntry.AuditEntryDialog;

@objid ("3e6edef3-20f0-41b9-b0fd-228f63622f96")
public class ShowDetailsHandler extends AbstractAuditEntryHandler {
    @objid ("b5e4c66b-793b-41b1-855e-aa7f4275f650")
    @Execute
    public void execute(IProjectService projectService, EModelService modelService, MApplication application, IModelioNavigationService navigationService, IAuditService auditService) {
        IAuditEntry entry = (IAuditEntry) getSelectedAuditEntry(modelService, application);
        if (entry != null) {
            AuditEntryDialog dialog = new AuditEntryDialog(Display.getCurrent().getActiveShell(), entry, projectService.getSession(), navigationService, auditService.getConfigurationModel().getAuditConfigurationPlan());
            dialog.open();
        }
    }

    @objid ("c1513c14-a279-4e00-b589-f8f5a20df052")
    @CanExecute
    public boolean isEnabled(EModelService modelService, MApplication application) {
        return getSelectedAuditEntry(modelService, application) instanceof IAuditEntry;
    }

}
