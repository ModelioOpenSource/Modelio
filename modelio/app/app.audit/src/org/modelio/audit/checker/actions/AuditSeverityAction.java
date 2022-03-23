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
package org.modelio.audit.checker.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.model.AuditRuleModel;

@objid ("895c56a5-7da7-4cf6-8690-8d0dbbe74676")
public class AuditSeverityAction extends Action {
    @objid ("8c27deb9-d6d6-4af9-9e42-79fc75b71fb2")
    private String mode;

    @objid ("32b9e1f4-bf0b-45af-a2ba-4b9cf7bdffa7")
    private Tree tree;

    @objid ("4c5e52e9-98eb-4e97-8370-7741aa51aebd")
    private IAuditService auditService;

    @objid ("785f6054-079e-4545-b551-68b2cffb173c")
    public  AuditSeverityAction(String mode, IAuditService auditService, Tree tree) {
        this.mode = mode;
        this.auditService = auditService;
        this.tree = tree;
        
        if ("AuditAdvice".equals(mode)) {
            setText(Audit.I18N.getString("Audit.CheckerView.Contextual.AuditAdvice"));
            setImageDescriptor(Audit.getImageDescriptor("icons/advice.png"));
        } else if ("AuditWarning".equals(mode)) {
            setText(Audit.I18N.getString("Audit.CheckerView.Contextual.AuditWarning"));
            setImageDescriptor(Audit.getImageDescriptor("icons/warning.png"));
        } else if ("AuditError".equals(mode)) {
            setText(Audit.I18N.getString("Audit.CheckerView.Contextual.AuditError"));
            setImageDescriptor(Audit.getImageDescriptor("icons/error.png"));
        }
        
    }

    @objid ("73b16764-0ffe-4827-b952-315baf6ccbfb")
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
        
        if (ruleId != null && this.mode != null) {
            AuditConfigurationModel prefModel = this.auditService.getConfigurationModel();
            AuditRule rulePref = prefModel.get(ruleId);
            if (rulePref != null) {
                if ("AuditAdvice".equals(this.mode)) {
                    rulePref.setSeverity(AuditSeverity.AuditAdvice);
                } else if ("AuditWarning".equals(this.mode)) {
                    rulePref.setSeverity(AuditSeverity.AuditWarning);
                } else if ("AuditError".equals(this.mode)) {
                    rulePref.setSeverity(AuditSeverity.AuditError);
                }
                this.auditService.apply(prefModel);
                this.tree.redraw();
            }
        }
        
    }

    @objid ("a05f7a47-4e4b-4436-83ab-d2b8fe83cf31")
    @Override
    public boolean isEnabled() {
        TreeItem[] item = this.tree.getSelection();
        Object obj = item[0].getData();
        org.modelio.audit.service.AuditSeverity severity = null;
        if (obj instanceof IAuditEntry) {
            severity = ((IAuditEntry) obj).getSeverity();
        } else if (obj instanceof AuditRuleModel) {
            severity = ((AuditRuleModel) obj).severity;
        }
        
        if (severity != null && this.mode != null) {
            if (!severity.name().equals(this.mode)) {
                return true;
            }
        }
        return false;
    }

}
