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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.model.AuditRuleModel;

/**
 * Handler that disables the selected rule.
 */
@objid ("ce3c6a8a-510e-4cbd-a08e-edf436c7e072")
public class DisableRuleHandlerAction extends Action {
    @objid ("b715079c-f533-42ea-bc52-2da8cebdc68c")
    private IAuditService auditService;

    @objid ("8ac0434d-f677-41c6-9b81-be78961b689a")
    private Tree tree;

    @objid ("f1f6d2a2-284c-4663-8b25-13d40883ae5c")
    public DisableRuleHandlerAction(IAuditService auditService, Tree tree) {
        this.auditService = auditService;
        this.tree = tree;
        
        setText(Audit.I18N.getString("Audit.CheckerView.Contextual.Disable"));
        setImageDescriptor(Audit.getImageDescriptor("icons/suspended.png"));
    }

    @objid ("3a226a03-0c35-4f96-be56-947ec080afc5")
    @Override
    public void run() {
        TreeItem[] item = this.tree.getSelection();
        Object obj = item[0].getData();
        
        String ruleId = null;
        if (obj instanceof IAuditEntry) {
            ruleId = ((IAuditEntry) obj).getRuleId();
        } else if (obj instanceof AuditRuleModel) {
            ruleId = ((AuditRuleModel) obj).rule;
        }
        
        if (ruleId != null) {
        
            AuditConfigurationModel prefModel = this.auditService.getConfigurationModel();
            AuditRule rulePref = prefModel.get(ruleId);
        
            if (rulePref != null) {
                rulePref.setEnabled(false);
                this.auditService.apply(prefModel);
                this.tree.redraw();
            }
        }
    }

    @objid ("51359115-7eaf-414c-872e-61d61c6ff303")
    @Override
    public boolean isEnabled() {
        TreeItem[] item = this.tree.getSelection();
        Object obj = item[0].getData();
        return obj instanceof IAuditEntry || obj instanceof AuditRuleModel;
    }

}
