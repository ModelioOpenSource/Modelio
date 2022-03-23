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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;

/**
 * This class is passed to Audit configuration point contributors to modify a rule in the audit configuration. Modifications are merged with the current rule configuration, see {@link #reconfigureRule(String, Boolean, AuditSeverity)} to the merging
 * details.
 */
@objid ("a2acba17-40bb-4e7f-a783-02c76e24e459")
public class AuditReconfiguration {
    @objid ("34f78c5f-290c-46c2-9d79-7fce236f6236")
    private Map<String, AuditSeverity> severities = new HashMap<>();

    @objid ("dcdc6fe8-05ab-419d-aba0-0d123d9e553f")
    private Map<String, Boolean> states = new HashMap<>();

    @objid ("c09911dc-638a-4a97-827c-91265fe484a9")
    private List<AuditRule> addedRules = new ArrayList<>();

    /**
     * Reconfigure a rule applying a 'merge' of the audit rule current configuration and the given values for enable state and severity. <br/>
     * Merge rules:
     * <ul>
     * <li>if 'severity' is <code>null</code>, no change is applied to the rule current severity</li>
     * <li>if 'severity' is higher than current rule severity, it is applied otherwise is is ignored</li>
     * <li>if 'enabled' is <code>null</code>, no change is applied to the rule current enable state</li>
     * <li>the enable state is set to MAX(current state, 'enabled') where enabled is > disabled</li>
     * </ul>
     * @param ruleId - the rule identifier, mandatory, not null
     * @param enabled - the enabled/disabled state to set. If null the parameter is ignored (state not modified)
     * @param severity - the severity to set - if null the parameter is ignored (severity not modified)
     */
    @objid ("6a74364c-8e6b-4c39-a7c3-e35f85d4ba33")
    public void reconfigureRule(String ruleId, Boolean enabled, AuditSeverity severity) {
        if (severity != null) {
            AuditSeverity o = this.severities.get(ruleId);
            if (o == null || severity.compareTo(o) > 0)
                this.severities.put(ruleId, severity);
        }
        
        if (enabled != null) {
            Boolean o = this.states.get(ruleId);
            if (o == null || enabled)
                this.states.put(ruleId, enabled);
        }
        
    }

    /**
     * Add a new rule.
     * @param rule the rule to add. The new rule id must not already exist.
     */
    @objid ("c5a4aad1-c813-47df-8431-699a76f08585")
    public void add(AuditRule rule) {
        this.addedRules.add(rule);
    }

    /**
     * Return the added rules
     * @return
     */
    @objid ("c70127e1-1b38-4107-b426-6582ebfb793c")
    public List<AuditRule> getAddedRules() {
        return this.addedRules;
    }

    /**
     * Return the reconfigured enable states
     * @return
     */
    @objid ("b56fc306-fb43-46db-8bda-309a216c6090")
    public Map<String, Boolean> getStates() {
        return this.states;
    }

    /**
     * return the reconfigured severities
     * @return
     */
    @objid ("3db6b023-816a-44e7-9399-c28375b30ea7")
    public Map<String, AuditSeverity> getSeverities() {
        return this.severities;
    }

}
