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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.model.AuditRuleModel;

/**
 * Handler that disables the selected rule.
 */
@objid ("165729ae-f257-4ed1-a18a-5dca5cc66271")
public class DisableRuleHandler extends AbstractAuditEntryHandler {
    @objid ("7608b621-3aba-4427-9e69-f3ac31da1c70")
    @Execute
    void execute(EModelService modelService, MApplication application, IAuditService auditService) {
        Object obj = getSelectedAuditEntry(modelService, application);
        String ruleId = null;
        if (obj instanceof IAuditEntry) {
            ruleId = ((IAuditEntry) obj).getRuleId();
        } else if (obj instanceof AuditRuleModel) {
            ruleId = ((AuditRuleModel) obj).rule;
        }
        
        if (ruleId != null) {
        
            AuditConfigurationModel prefModel = auditService.getConfigurationModel();
            AuditRule rulePref = prefModel.get(ruleId);
        
            if (rulePref != null) {
                rulePref.setEnabled(false);
                auditService.apply(prefModel);
            }
        }
    }

    @objid ("03852f54-f97f-446f-8051-0e6dafa22eac")
    @CanExecute
    boolean isEnabled(EModelService modelService, MApplication application) {
        Object obj = getSelectedAuditEntry(modelService, application);
        return obj instanceof IAuditEntry || obj instanceof AuditRuleModel;
    }

}
