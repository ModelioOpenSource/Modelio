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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkPropertyElements
 */
@objid ("a97dd817-3e3a-4ba2-8cb3-067a7984d36b")
public class R2080 extends AbstractInfrastructureRule {
    @objid ("6b324ffd-bb2e-44d8-8fbc-9d0f6870d663")
    private static final String RULEID = "R2080";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("44fb416c-c367-4fdf-a640-83ae19983545")
    private CheckR2080 checkerInstance = null;

    @objid ("0ad734f1-6f70-4c12-915a-3524b13cf2f4")
    @Override
    public String getRuleId() {
        return R2080.RULEID;
    }

    @objid ("c3c42b86-7b34-4162-aea8-4e9cd8e881cf")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(PropertyTableDefinition.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyDefinition.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("37d52d20-fc14-4684-b777-d1011f3b5fa3")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("72b6a75c-d4ad-4139-9503-96fe581b69ef")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b43819a7-076b-4cb2-9ba7-1dcd5cee4dcd")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2080
     */
    @objid ("d45ee96f-25d1-4669-a617-abdb5ff37b5c")
    public R2080() {
        this.checkerInstance = new CheckR2080(this);
    }

    @objid ("23119e39-d8ff-4850-bbff-8cc37b040d26")
    private static class CheckR2080 extends AbstractControl {
        @objid ("74d01a87-715f-4ae2-9a41-649915bed157")
        public CheckR2080(IRule rule) {
            super(rule);
        }

        @objid ("5541ad7a-bba8-45fe-952a-f9ed073fc279")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof PropertyTableDefinition) {
                diagnostic.addEntry(checkR2080((PropertyTableDefinition) element));
            } else if (element instanceof PropertyDefinition) {
                PropertyTableDefinition owner = ((PropertyDefinition) element).getOwner();
                if (owner != null) {
                    diagnostic.addEntry(checkR2080(owner));
                }
            } else {
                Audit.LOG.warning("R2080: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("e6ec5c0d-7e22-4886-a74b-f8869def67f3")
        private IAuditEntry checkR2080(final PropertyTableDefinition PropertyTable) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    PropertyTable,
                    null);
            
            Map<String, List<PropertyDefinition>> duplicates = new HashMap<>();
            
            for (PropertyDefinition p : PropertyTable.getOwned()) {
                String name = p.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyDefinition>());
                }
                duplicates.get(p.getName()).add(p);
            }
            
            for (Entry<String, List<PropertyDefinition>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(PropertyTable);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
