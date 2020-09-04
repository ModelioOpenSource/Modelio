/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.audit.checker.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.dialog.auditEntry.AuditEntryDialog;
import org.modelio.audit.view.model.AuditElementModel;

@objid ("c5960dc3-ded7-4893-baf2-4fac1156738c")
public class ShowDetailsAction extends Action {
    @objid ("d5b4c833-a2b2-4d40-b2f5-b50bc69aaf48")
    private IProjectService projectService;

    @objid ("1833a222-1f41-44ba-b282-3f315d9a92fb")
    private IModelioNavigationService navigationService;

    @objid ("f1b7c876-f584-4641-9180-547d3c245169")
    private TreeViewer tree;

    @objid ("ac57151d-4012-45bb-bb83-b43001dc1e65")
    private IAuditConfigurationPlan configurationPlan;

    @objid ("23068af3-abd9-40a7-aa21-5caff3464983")
    public ShowDetailsAction(IAuditService auditService, IProjectService projectService, IModelioNavigationService navigationService, TreeViewer tree) {
        this.tree = tree;
        this.projectService = projectService;
        this.navigationService = navigationService;
        this.configurationPlan = auditService.getConfigurationModel().getAuditConfigurationPlan();
        setText(Audit.I18N.getString("Audit.CheckerView.Contextual.Show"));
        setImageDescriptor(Audit.getImageDescriptor("icons/details.png"));
    }

    @objid ("90077217-793a-4321-b441-66d9543bd403")
    @Override
    public void run() {
        TreeItem[] item = this.tree.getTree().getSelection();
        IAuditEntry entry = (IAuditEntry) item[0].getData();
        AuditEntryDialog dialog = new AuditEntryDialog(Display.getCurrent().getActiveShell(), entry, this.projectService.getSession(), this.navigationService, this.configurationPlan);
        dialog.open();
    }

    @objid ("2fb22aad-076a-4bed-ba34-49e00a66d91c")
    @Override
    public boolean isEnabled() {
        TreeItem[] item = this.tree.getTree().getSelection();
        Object obj = item[0].getData();
        return obj instanceof IAuditEntry || obj instanceof AuditElementModel;
    }

}
