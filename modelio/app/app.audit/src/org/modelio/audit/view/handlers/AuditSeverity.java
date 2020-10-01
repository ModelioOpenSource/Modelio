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

package org.modelio.audit.view.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.model.AuditRuleModel;

@objid ("10ab9e79-c58a-4ffa-adad-aed15acd874b")
public class AuditSeverity extends AbstractAuditEntryHandler {
    @objid ("6ba91151-b8e5-4e00-91c0-3b3e10b0bc9a")
    @Execute
    void execute(EModelService modelService, MApplication application, @Optional @Named("mode") final String mode, IAuditService auditService) {
        Object obj = getSelectedAuditEntry(modelService, application);
        String ruleId = null;
        if (obj instanceof IAuditEntry) {
            ruleId = ((IAuditEntry) obj).getRuleId();
        } else if (obj instanceof AuditRuleModel) {
            ruleId = ((AuditRuleModel) obj).rule;
        }
        
        if (ruleId != null && mode != null) {
            AuditConfigurationModel prefModel = auditService.getConfigurationModel();
            AuditRule rulePref = prefModel.get(ruleId);
            if (rulePref != null) {
                if ("AuditAdvice".equals(mode)) {
                    rulePref.setSeverity(org.modelio.audit.service.AuditSeverity.AuditAdvice);
                } else if ("AuditWarning".equals(mode)) {
                    rulePref.setSeverity(org.modelio.audit.service.AuditSeverity.AuditWarning);
                } else if ("AuditError".equals(mode)) {
                    rulePref.setSeverity(org.modelio.audit.service.AuditSeverity.AuditError);
                }
                auditService.apply(prefModel);
            }
        }
    }

    @objid ("3fe4d19e-0cff-4e68-b207-161f88af8c93")
    @CanExecute
    boolean isEnabled(EModelService modelService, MApplication application, @Optional @Named("mode") final String mode) {
        Object obj = getSelectedAuditEntry(modelService, application);
        org.modelio.audit.service.AuditSeverity severity = null;
        if (obj instanceof IAuditEntry) {
            severity = ((IAuditEntry) obj).getSeverity();
        } else if (obj instanceof AuditRuleModel) {
            severity = ((AuditRuleModel) obj).severity;
        }
        
        if (severity != null && mode != null) {
            if (!severity.name().equals(mode)) {
                return true;
            }
        }
        return false;
    }

}
