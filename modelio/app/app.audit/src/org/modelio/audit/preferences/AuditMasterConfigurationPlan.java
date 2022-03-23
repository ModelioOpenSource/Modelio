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
package org.modelio.audit.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;

/**
 * This audit configuration plan delegates its work to sub plans.
 */
@objid ("d5a58b71-ac94-468d-aecb-7988127740ec")
public class AuditMasterConfigurationPlan implements IAuditConfigurationPlan {
    @objid ("5d382430-6a90-4694-a7a9-5b6622811106")
    private List<IAuditConfigurationPlan> subPlans = new ArrayList<>();

    /**
     * Constructs a new audit plan, initializing the sub plans.
     * @param subPlans all available sub plans.
     */
    @objid ("8329e632-5437-4b44-b224-1fef69d84cc3")
    public  AuditMasterConfigurationPlan(List<IAuditConfigurationPlan> subPlans) {
        super();
        this.subPlans.addAll(subPlans);
        
    }

    @objid ("b6c25f3b-2a5a-414e-a39e-1822e0456dd5")
    @Override
    public String getLabel(AuditRule element) {
        return element.getId();
    }

    @objid ("716f3f9c-895c-41e3-acaf-58ad19d64b03")
    @Override
    public List<AuditCategory> getRootCategories() {
        return this.subPlans.stream()
                        .map(p -> p.getRootCategories())
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        
    }

    @objid ("3567dc74-0579-4f5a-9454-7cdc06873a8c")
    @Override
    public String getLabel(AuditCategory category) {
        String categoryId = category.getId();
        for (IAuditConfigurationPlan plan : this.subPlans) {
            if (isCategoryInPlan(categoryId, plan)) {
                return plan.getLabel(category);
            }
        }
        
        // No label found, return the id as it is
        return categoryId;
    }

    @objid ("edb5e760-81ab-4d83-add0-e21c39d5f25c")
    @Override
    public String getDescription(AuditRule rule) {
        String ruleId = rule.getId();
        for (IAuditConfigurationPlan plan : this.subPlans) {
            if (isRuleInPlan(ruleId, plan)) {
                return plan.getDescription(rule);
            }
        }
        
        // No description found, return the id as it is
        return ruleId;
    }

    @objid ("5decffa6-344e-4c38-8703-aef4c04b7cd4")
    @Override
    public String getMessage(String ruleId) {
        for (IAuditConfigurationPlan plan : this.subPlans) {
            if (isRuleInPlan(ruleId, plan)) {
                return plan.getMessage(ruleId);
            }
        }
        
        // No description found, return the id as it is
        return ruleId;
    }

    @objid ("f48344fa-882d-4b62-8d83-0a99dd1966eb")
    private boolean isCategoryInPlan(String categoryId, IAuditConfigurationPlan plan) {
        return plan.getRootCategories().stream().anyMatch(e -> e.getId().equals(categoryId));
    }

    @objid ("c205514b-5541-4cbc-800d-69fc7d0c31aa")
    private boolean isRuleInPlan(String ruleId, IAuditConfigurationPlan plan) {
        return plan.getRootCategories().stream().map(c -> c.getRules()).flatMap(l -> l.stream()).anyMatch(e -> e.getId().equals(ruleId));
    }

    @objid ("ccea47a7-fbe2-4088-a818-f771a3295320")
    @Override
    public Image getImage(AuditCategory category) {
        String categoryId = category.getId();
        for (IAuditConfigurationPlan plan : this.subPlans) {
            if (isCategoryInPlan(categoryId, plan)) {
                return plan.getImage(category);
            }
        }
        return null;
    }

}
