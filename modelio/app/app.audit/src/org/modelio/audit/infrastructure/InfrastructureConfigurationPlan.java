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
package org.modelio.audit.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;

@objid ("2ee82cca-313a-4ab9-90ea-0ebf06370639")
public class InfrastructureConfigurationPlan implements IAuditConfigurationPlan {
    @objid ("70f45b4a-690f-4e2f-8b8b-73b11e9fb573")
    private List<AuditCategory> rootCategories;

    @objid ("6ac30efe-48e4-44dc-8d77-f1c1b00e1998")
    public  InfrastructureConfigurationPlan(List<AuditCategory> rootCategories) {
        this.rootCategories = rootCategories;
    }

    @objid ("c9bbefdc-03d0-4d2a-81dd-540b096dd2ea")
    @Override
    public List<AuditCategory> getRootCategories() {
        return this.rootCategories;
    }

    @objid ("8d926754-3567-40ff-9c33-487b020a09c6")
    @Override
    public String getLabel(AuditCategory category) {
        return Audit.I18N.getString(IAuditConfigurationPlan.CATEGORY_PREFIX + category.getId() + ".label");
    }

    @objid ("7782df19-ea15-42fc-9322-95171e5e698e")
    @Override
    public String getMessage(String ruleId) {
        return Audit.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + ruleId + ".message");
    }

    @objid ("788ce96c-3195-45da-bfc0-c552b4af3029")
    @Override
    public String getDescription(AuditRule rule) {
        return Audit.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + rule.getId() + ".description");
    }

    @objid ("73fa0246-f843-4d5b-9d47-4cdc9dd27aca")
    @Override
    public String getLabel(AuditRule element) {
        return element.getId();
    }

    @objid ("1e3890e4-4e07-40ef-b875-c1cc90fd5fd5")
    @Override
    public Image getImage(AuditCategory category) {
        return null;
    }

}
