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
package org.modelio.audit.preferences.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.extension.IAuditConfigurationPlan;

/**
 * Audit configuration model.
 * <p>
 * Manage a set of AuditRule objects into categories.
 */
@objid ("cdb4ddee-3343-4c5e-a946-b75d7831e450")
public class AuditConfigurationModel {
    @objid ("e8cca3d0-bdef-4228-ac8f-5f71e7fe6971")
    private final Map<String, AuditRule> rulesById;

    @objid ("6f28f969-1157-4cb3-8f6f-cb0c004f709d")
    private final IAuditConfigurationPlan auditConfigurationPlan;

    /**
     * Creates an audit model.
     */
    @objid ("9a09fa7e-3563-4daa-9a6f-cca8bc956e57")
    public  AuditConfigurationModel(IAuditConfigurationPlan auditConfigurationPlan) {
        super();
        
        this.rulesById = new HashMap<>();
        this.auditConfigurationPlan = auditConfigurationPlan;
        addRules(auditConfigurationPlan);
        
    }

    /**
     * @param rulePref the rulePref to add
     */
    @objid ("4720dd93-0e5a-49b4-82b7-0dd9648ebafc")
    public void add(AuditRule rulePref) {
        this.rulesById.putIfAbsent(rulePref.getId(), rulePref);
    }

    /**
     * Get known categories.
     */
    @objid ("9b534d19-86a7-49fc-9ccb-84e55dbaf9c5")
    public Collection<AuditCategory> getCategories() {
        return this.auditConfigurationPlan.getRootCategories();
    }

    /**
     * Get all registered audit rules.
     * @return audit rules
     */
    @objid ("bd3cb4ed-cee1-418a-b89f-1dbb1d952f79")
    public Collection<AuditRule> getRules() {
        return this.rulesById.values();
    }

    /**
     * Find a rule from its identifier.
     * @param ruleId a rule identifier.
     * @return the found rule or <code>null</code>.
     */
    @objid ("33a1d265-c00f-4d65-a68c-4979b588f990")
    public AuditRule get(String ruleId) {
        return this.rulesById.get(ruleId);
    }

    /**
     * Creates a deep copy of the given audit model.
     * <p>
     * All rules are copied to this model.
     * @param anotherConfig the audit model to copy.
     */
    @objid ("dd5d2e1b-aac7-4165-bd88-c9d53d488d71")
    public  AuditConfigurationModel(AuditConfigurationModel anotherConfig) {
        super();
        this.rulesById = new HashMap<>();
        this.auditConfigurationPlan = anotherConfig.auditConfigurationPlan;
        
        // Create new AuditRules from the list in the given configuration
        for (AuditRule originalRule : anotherConfig.getRules()) {
            AuditRule newRule = new AuditRule(originalRule);
            add(newRule);
        }
        
        // Create new AuditCategories from the list in the given configuration
        for (AuditCategory originalCategory : anotherConfig.getCategories()) {
            AuditCategory newCategory = new AuditCategory(originalCategory.getId());
            addRules(newCategory);
        
            for (AuditRule originalRule : originalCategory.getRules()) {
                AuditRule newRule = this.rulesById.get(originalRule.getId());
                if (newRule != null) {
                    newCategory.add(newRule);
                }
            }
        }
        
    }

    @objid ("d40ba60e-201c-409b-a620-82d4255fdb08")
    private void addRules(IAuditConfigurationPlan configurationPlan) {
        for (AuditCategory rootCategory : configurationPlan.getRootCategories()) {
            addRules(rootCategory);
        }
        
    }

    @objid ("8ac5e701-6ed1-4abe-ac9e-0cf95a1b7ad1")
    private void addRules(AuditCategory category) {
        for (AuditRule rule : category.getRules()) {
            this.rulesById.put(rule.getId(), rule);
        }
        
        // for (AuditCategory subCategory : category.getSubCategories()) {
        // addRules(subCategory);
        // }
        
    }

    @objid ("6de7108c-8e8e-4d7a-b167-7abba02e4ffe")
    public IAuditConfigurationPlan getAuditConfigurationPlan() {
        return this.auditConfigurationPlan;
    }

}
