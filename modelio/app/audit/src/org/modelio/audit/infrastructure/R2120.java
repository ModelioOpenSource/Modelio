/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkPropertyTableDefinitionElements
 */
@objid ("9ada56c9-b518-42db-ab41-823dc8ea44ca")
public class R2120 extends AbstractInfrastructureRule {
    @objid ("f0307054-5f70-4c92-b94e-5892fcfbb8f1")
    private static final String RULEID = "R2120";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("a0ae9eb2-322d-49f1-8183-7094650ed1df")
    private CheckR2120 checkerInstance = null;

    @objid ("a1830029-ccfe-4f03-936e-aec64e3e0016")
    @Override
    public String getRuleId() {
        return R2120.RULEID;
    }

    @objid ("571ad08c-c17e-49e9-bde8-7eb6bbc34de4")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(ModuleComponent.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Profile.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyTableDefinition.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7b1731a2-2da2-4dbd-97a7-910df962e04c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0de638f7-0779-4400-8510-de443facc7f7")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f7333451-dc98-4c50-b61e-e4f78b482f5c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2120
     */
    @objid ("120f7f7c-39f2-473e-8d33-cf0d0d78f280")
    public R2120() {
        this.checkerInstance = new CheckR2120(this);
    }

    @objid ("30595585-6ba7-4c05-b546-39b2b5d09ab3")
    private static class CheckR2120 extends AbstractControl {
        @objid ("ac91abe8-baba-4ec5-b40b-f55762156c38")
        public CheckR2120(IRule rule) {
            super(rule);
        }

        @objid ("33e4c1d8-5bcd-46ed-aea4-d765e0ec6c25")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ModuleComponent) {
                diagnostic.addEntry(checkR2120((ModuleComponent) element));
            } else if (element instanceof Profile) {
                diagnostic.addEntry(checkR2120((Profile) element));
            } else {
                Audit.LOG.warning("R2120: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("a0aeed49-b027-47d3-a3b0-8ddc1c9841c1")
        private IAuditEntry checkR2120(final Profile propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<PropertyTable>> duplicates = new HashMap<>();
            
            for (PropertyTable ps : propertyContainer.getProperties()) {
                String name = ps.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyTable>());
                }
                duplicates.get(ps.getName()).add(ps);
            }
            
            for (Entry<String, List<PropertyTable>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
                    // Rule failed
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(propertyContainer);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("79f6a304-45e7-4570-9c73-452fca6b5e7c")
        private IAuditEntry checkR2120(final ModuleComponent propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<PropertyTable>> duplicates = new HashMap<>();
            
            for (PropertyTable ps : propertyContainer.getProperties()) {
                String name = ps.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyTable>());
                }
                duplicates.get(ps.getName()).add(ps);
            }
            
            for (Entry<String, List<PropertyTable>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(propertyContainer);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
