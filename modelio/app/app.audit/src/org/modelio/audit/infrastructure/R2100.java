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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkPropertyEnumerationLiteralElements
 */
@objid ("6646e6fd-aa1c-48e5-81ae-1bc2f774a252")
public class R2100 extends AbstractInfrastructureRule {
    @objid ("eef30a3d-87e2-472e-977f-9428f8cb73c8")
    private static final String RULEID = "R2100";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("3d59e1f2-0979-4a97-b328-e4840bd5cf17")
    private CheckR2100 checkerInstance = null;

    @objid ("9b2f52df-7c71-4e42-bd04-659db94ec694")
    @Override
    public String getRuleId() {
        return R2100.RULEID;
    }

    @objid ("4773052b-c042-4250-a80d-b6c8e8c54551")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(EnumeratedPropertyType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyEnumerationLitteral.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("42b99955-ec6e-4496-a79b-061f2baa29ae")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("be85dfc8-74b4-42c1-8274-9c9b6f661bf1")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("39692fb4-7ccf-491b-b7c3-9d73abf46cf3")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2100
     */
    @objid ("48a83cd4-5560-48a5-9eeb-bbcef6cb24bf")
    public R2100() {
        this.checkerInstance = new CheckR2100(this);
    }

    @objid ("7f0c7ac4-f99f-4d56-b882-46826dc88e8c")
    private static class CheckR2100 extends AbstractControl {
        @objid ("69e8b42f-9e94-4f68-9499-ab07315d96ea")
        public CheckR2100(IRule rule) {
            super(rule);
        }

        @objid ("30a152f4-b973-4363-a1aa-3dc1148a3b38")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof EnumeratedPropertyType) {
                diagnostic.addEntry(checkR2100((EnumeratedPropertyType) element));
            } else if (element instanceof PropertyEnumerationLitteral) {
                EnumeratedPropertyType owner = ((PropertyEnumerationLitteral) element).getOwner();
                if (owner != null) {
                    diagnostic.addEntry(checkR2100(owner));
                }
            } else {
                Audit.LOG.warning("R2100: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("15c7535d-a6d7-4c15-9474-3d2594906bfd")
        private IAuditEntry checkR2100(final EnumeratedPropertyType enumPropType) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    enumPropType,
                    null);
            
            Map<String, List<PropertyEnumerationLitteral>> duplicates = new HashMap<>();
            
            for (PropertyEnumerationLitteral p : enumPropType.getLitteral()) {
                String name = p.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyEnumerationLitteral>());
                }
                duplicates.get(p.getName()).add(p);
            }
            
            for (Entry<String, List<PropertyEnumerationLitteral>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(enumPropType);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
