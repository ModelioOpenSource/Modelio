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
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkEnumeratedPropertyTypeElements
 */
@objid ("e3a277f9-8f05-4531-bfa3-3c34af3dfe9b")
public class R2030 extends AbstractInfrastructureRule {
    @objid ("9e785790-eb06-4586-b1f5-7f78f4ee7019")
    private static final String RULEID = "R2030";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("477f53b3-638f-46c9-945a-9bae22c5601c")
    private CheckR2030 checkerInstance = null;

    @objid ("370d74a6-1d32-4200-b922-73b79c4f1661")
    @Override
    public String getRuleId() {
        return R2030.RULEID;
    }

    @objid ("52e06640-feaa-45fc-97f9-d88b3cb291ba")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(ModuleComponent.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Profile.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(EnumeratedPropertyType.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b1aabb2a-adfb-4a90-a83b-614f5dd3825e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9f80408b-0da4-423b-a83c-3695c3d2c36a")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e839f283-df4e-4e1a-9015-af2ae13c8706")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2030
     */
    @objid ("787d9f57-3416-40fa-a36d-3e48fcff2208")
    public R2030() {
        this.checkerInstance = new CheckR2030(this);
    }

    @objid ("01ead237-ed3a-4903-9225-8d02d0dcd42b")
    private static class CheckR2030 extends AbstractControl {
        @objid ("68105ad7-ce7b-4212-8acb-96728a03c8d5")
        public CheckR2030(IRule rule) {
            super(rule);
        }

        @objid ("7bc3bc91-4142-4d01-bee3-0c0d2f3be787")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ModuleComponent) {
                diagnostic.addEntry(checkR2030((ModuleComponent) element));
            } else if (element instanceof Profile) {
                diagnostic.addEntry(checkR2030((Profile) element));
            } else if (element instanceof EnumeratedPropertyType) {
                Profile owner = ((EnumeratedPropertyType) element).getAnalystOwner();
                if (owner != null) {
                    diagnostic.addEntry(checkR2030(owner));
                }
            } else {
                Audit.LOG.warning("R2030: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("b199f77c-dc60-40e4-9159-ee0370033497")
        private IAuditEntry checkR2030(final ModuleComponent propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<EnumeratedPropertyType>> duplicates = new HashMap<>();
            
            for (EnumeratedPropertyType ep : propertyContainer.getDefinedPropertyType(EnumeratedPropertyType.class)) {
                String name = ep.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<EnumeratedPropertyType>());
                }
                duplicates.get(ep.getName()).add(ep);
            }
            
            for (Entry<String, List<EnumeratedPropertyType>> entry : duplicates.entrySet()) {
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

        @objid ("7e6ed126-df57-45a0-b4c1-faca48b00642")
        private IAuditEntry checkR2030(final Profile propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<EnumeratedPropertyType>> duplicates = new HashMap<>();
            
            for (EnumeratedPropertyType ep : propertyContainer.getDefinedType(EnumeratedPropertyType.class)) {
                String name = ep.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<EnumeratedPropertyType>());
                }
                duplicates.get(ep.getName()).add(ep);
            }
            
            for (Entry<String, List<EnumeratedPropertyType>> entry : duplicates.entrySet()) {
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
