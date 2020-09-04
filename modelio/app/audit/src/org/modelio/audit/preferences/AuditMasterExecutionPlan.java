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

package org.modelio.audit.preferences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.engine.core.IRule;

/**
 * This audit execution plan delegates its work to sub plans.
 */
@objid ("6c0b5510-231d-4939-9c54-5140e1b0efd6")
public class AuditMasterExecutionPlan implements IAuditExecutionPlan {
    @objid ("30732e7a-9bb9-47f7-9f18-e7785e80d033")
    private List<IAuditExecutionPlan> subPlans = new ArrayList<>();

    /**
     * Constructs a new audit plan, initializing the sub plans.
     * 
     * @param subPlans all available sub plans.
     */
    @objid ("cd8df505-d9df-423d-989c-c839e1a2c01a")
    public AuditMasterExecutionPlan(List<IAuditExecutionPlan> subPlans) {
        super();
        this.subPlans.addAll(subPlans);
    }

    @objid ("a3a13651-dfe4-4193-817e-8efdb80dad02")
    @Override
    public List<IRule> getRules(String metaclass, int trigger) {
        return this.subPlans.stream()
                        .map(p -> p.getRules(metaclass, trigger))
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
    }

    @objid ("4862d137-5bfb-4347-91e9-531217fd51ca")
    @Override
    public IRule getRuleById(String ruleId) {
        for (IAuditExecutionPlan plan : this.subPlans) {
            IRule rule = plan.getRuleById(ruleId);
            if (rule != null) {
                return rule;
            }
        }
        return null;
    }

    @objid ("ee89e967-50b9-4ee8-95d0-93067b0664e9")
    @Override
    public Collection<IRule> getAllRules() {
        return this.subPlans.stream()
                        .map(p -> p.getAllRules())
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
    }

    @objid ("2a92188b-2d8a-41c9-8282-2f20cc2a03a6")
    @Override
    public void disableRule(IRule rule) {
        String ruleId = rule.getRuleId();
        for (IAuditExecutionPlan plan : this.subPlans) {
            if (plan.getAllRules().stream().anyMatch(e -> e.getRuleId().equals(ruleId))) {
                plan.disableRule(rule);
            }
        }
    }

}
