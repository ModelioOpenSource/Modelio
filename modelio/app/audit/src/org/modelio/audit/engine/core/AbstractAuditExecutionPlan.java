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

package org.modelio.audit.engine.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IPath;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;

/**
 * Audit plan organizing audit rules by trigger.
 */
@objid ("57dbb255-402a-48ed-88d9-52ff3070d2d8")
public abstract class AbstractAuditExecutionPlan implements IAuditExecutionPlan {
    @objid ("5ad73eda-25dd-4926-aa57-f98615f66006")
    private Map<String, IRule> rulesWithoutTrigger = new HashMap<>();

    @objid ("bd67947c-1649-4906-9a0d-720a333c3453")
    private Map<String, List<IRule>> createRules = new HashMap<>();

    @objid ("1b9207ac-510c-47bf-b10f-86c9026f311a")
    private Map<String, List<IRule>> updateRules = new HashMap<>();

    @objid ("39b4a4af-448c-4c36-85c7-f36ae6c3fc60")
    private Map<String, List<IRule>> moveRules = new HashMap<>();

    @objid ("6aeae885-b79d-410a-a684-4efbe3572059")
    private Map<String, List<IRule>> deleteRules = new HashMap<>();

    @objid ("398ebddc-3f03-435d-969d-a6d964cb363a")
    public AbstractAuditExecutionPlan(List<AuditCategory> rootCategories) {
        super();
        for (AuditCategory category : rootCategories) {
            initRules(category);
        }
    }

    @objid ("3dfba011-abf2-42cd-9f7f-5f529022b36f")
    private void initRules(Collection<AuditRule> rules) {
        for (AuditRule rule : rules) {
            initRule(rule);
        }
    }

    @objid ("2dc14f58-0c62-44b1-8fd7-0ddac33534ed")
    protected abstract void initRule(AuditRule rule);

    @objid ("4bd1c9cc-fbf4-423b-9064-005674d26e8f")
    public final void registerRule(String metaclass, IRule rule, int triggers) {
        if (AuditTrigger.isCreate(triggers)) {
            if (this.createRules.get(metaclass) == null) {
                this.createRules.put(metaclass, new ArrayList<>());
            }
            this.createRules.get(metaclass).add(rule);
        }
        
        if (AuditTrigger.isUpdate(triggers)) {
            if (this.updateRules.get(metaclass) == null) {
                this.updateRules.put(metaclass, new ArrayList<>());
            }
            this.updateRules.get(metaclass).add(rule);
        }
        if (AuditTrigger.isMove(triggers)) {
            if (this.moveRules.get(metaclass) == null) {
                this.moveRules.put(metaclass, new ArrayList<>());
            }
            this.moveRules.get(metaclass).add(rule);
        }
        if (AuditTrigger.isDelete(triggers)) {
            if (this.deleteRules.get(metaclass) == null) {
                this.deleteRules.put(metaclass, new ArrayList<>());
            }
            this.deleteRules.get(metaclass).add(rule);
        }
    }

    @objid ("24780ae0-a3db-4308-bd05-dd713f0f7d9d")
    @Override
    public final List<IRule> getRules(String metaclass, int trigger) {
        List<IRule> results = null;
        
        if (AuditTrigger.isCreate(trigger)) {
            results = this.createRules.get(metaclass);
        } else if (AuditTrigger.isUpdate(trigger)) {
            results = this.updateRules.get(metaclass);
        } else if (AuditTrigger.isMove(trigger)) {
            results = this.moveRules.get(metaclass);
        } else if (AuditTrigger.isDelete(trigger)) {
            results = this.deleteRules.get(metaclass);
        }
        
        if (results != null) {
            return results;
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("a045ef61-3e12-404d-be10-ff191beb0c86")
    private Map<String, List<IRule>> getRules(int trigger) {
        Map<String, List<IRule>> results = null;
        
        if (AuditTrigger.isCreate(trigger)) {
            results = this.createRules;
        } else if (AuditTrigger.isUpdate(trigger)) {
            results = this.updateRules;
        } else if (AuditTrigger.isMove(trigger)) {
            results = this.moveRules;
        } else if (AuditTrigger.isDelete(trigger)) {
            results = this.deleteRules;
        }
        
        if (results != null) {
            return results;
        } else {
            return Collections.emptyMap();
        }
    }

    @objid ("1bd250a1-bf28-447a-933c-05c24fd5332e")
    protected final void dump(PrintStream out) {
        // dump plan
        out.println("-- AUDIT PLAN Composition --");
        out.println("ON CREATE");
        for (Entry<String, List<IRule>> entry : this.getRules(AuditTrigger.CREATE).entrySet()) {
            out.print(" - " + entry.getKey() + " = ");
            for (IRule rule : entry.getValue()) {
                out.print(rule.getRuleId() + " ");
            }
            out.println();
        }
        out.println("ON UPDATE");
        for (Entry<String, List<IRule>> entry : this.getRules(AuditTrigger.UPDATE).entrySet()) {
            out.print(" - " + entry.getKey() + " = ");
            for (IRule rule : entry.getValue()) {
                out.print(rule.getRuleId() + " ");
            }
            out.println();
        }
        
        out.println("ON MOVE");
        for (Entry<String, List<IRule>> entry : this.getRules(AuditTrigger.MOVE).entrySet()) {
            out.print(" - " + entry.getKey() + " = ");
            for (IRule rule : entry.getValue()) {
                out.print(rule.getRuleId() + " ");
            }
            out.println();
        }
        out.println("-- --");
    }

    @objid ("91f177a8-b1d1-425e-b515-f0f41f2666db")
    protected final void dump(IPath iPath) {
        PrintStream fout;
        try {
            fout = new PrintStream(new FileOutputStream(iPath.toFile()));
            dump(fout);
            fout.close();
        } catch (FileNotFoundException e) {
            Audit.LOG.debug(e);
        }
    }

    @objid ("b0c47dc7-5f5c-471d-884d-2f5ebb316deb")
    public final void registerRuleWithoutTrigger(IRule rule) {
        this.rulesWithoutTrigger.put(rule.getRuleId(), rule);
    }

    @objid ("b75ab513-8b72-43c2-8c76-b84f422e81b7")
    @Override
    public final IRule getRuleById(String ruleId) {
        IRule aRule = this.rulesWithoutTrigger.get(ruleId);
        if (aRule != null) {
            return aRule;
        }
        
        for (List<IRule> ruleList : getRules(AuditTrigger.CREATE).values()) {
            for (IRule rule : ruleList) {
                if (rule.getRuleId().equals(ruleId)) {
                    return rule;
                }
            }
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.MOVE).values()) {
            for (IRule rule : ruleList) {
                if (rule.getRuleId().equals(ruleId)) {
                    return rule;
                }
            }
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.UPDATE).values()) {
            for (IRule rule : ruleList) {
                if (rule.getRuleId().equals(ruleId)) {
                    return rule;
                }
            }
        }
        return null;
    }

    @objid ("6c4d167c-0e06-4a0d-a127-30de22eaa5de")
    @Override
    public final Collection<IRule> getAllRules() {
        // Collect all the rules id
        Set<IRule> allRules = new HashSet<>();
        for (List<IRule> ruleList : getRules(AuditTrigger.CREATE).values()) {
            allRules.addAll(ruleList);
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.MOVE).values()) {
            allRules.addAll(ruleList);
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.UPDATE).values()) {
            allRules.addAll(ruleList);
        }
        return allRules;
    }

    @objid ("324dda1e-fe03-4520-8f96-80188755a956")
    private void initRules(AuditCategory category) {
        initRules(category.getRules());
        
        // for (AuditCategory subCategory : category.getSubCategories()) {
        // initRules(subCategory);
        // }
    }

    @objid ("692f71ff-e0ff-46d3-82b0-48d4c0b862e7")
    @Override
    public final void disableRule(IRule rule) {
        for (List<IRule> ruleList : getRules(AuditTrigger.CREATE).values()) {
            ruleList.remove(rule);
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.MOVE).values()) {
            ruleList.remove(rule);
        }
        for (List<IRule> ruleList : getRules(AuditTrigger.UPDATE).values()) {
            ruleList.remove(rule);
        }
        this.rulesWithoutTrigger.remove(rule.getRuleId());
    }

}
