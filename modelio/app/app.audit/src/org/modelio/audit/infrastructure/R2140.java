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
import java.util.Map;
import java.util.Map.Entry;
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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkPropertyTypeElements
 */
@objid ("136c91ef-9bc8-4aba-8f24-561565b6b979")
public class R2140 extends AbstractInfrastructureRule {
    @objid ("04dac3aa-6c0a-4008-843d-a944d067b771")
    private static final String RULEID = "R2140";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c53ea4d9-40aa-4977-953c-f5411efc82a4")
    private CheckR2140 checkerInstance = null;

    @objid ("70ed1de5-e9b4-4e09-82f3-728240d1e057")
    @Override
    public String getRuleId() {
        return R2140.RULEID;
    }

    @objid ("30463ddb-9590-418b-bdc4-ce3514055bc3")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(ModuleComponent.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Profile.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyType.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0deb381c-bedf-4bd7-b006-a89dd2aea1d4")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fac65c27-3036-4b1d-ac4e-883b4f9cfa14")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7e7be58a-9f5d-4f4d-bbc1-3526089a4c57")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2140
     */
    @objid ("828cfa60-8cfa-449e-8f49-72ae1350cadf")
    public  R2140() {
        this.checkerInstance = new CheckR2140(this);
    }

    @objid ("61381293-9c7a-4201-921f-5f9127255942")
    private static class CheckR2140 extends AbstractControl {
        @objid ("4b197f74-05b7-433d-bb3d-040a00cf27ce")
        public  CheckR2140(IRule rule) {
            super(rule);
        }

        @objid ("d89b6a03-077c-4a8c-86b5-29435d48cb42")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ModuleComponent) {
                diagnostic.addEntry(checkR2140((ModuleComponent) element));
            } else if (element instanceof Profile) {
                diagnostic.addEntry(checkR2140((Profile) element));
            } else if (element instanceof PropertyType) {
                Profile owner = ((PropertyType) element).getAnalystOwner();
                if (owner != null) {
                    diagnostic.addEntry(checkR2140(owner));
                }
            } else {
                Audit.LOG.warning("R2140: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5f3fe329-68b6-4285-bf89-979af9f18154")
        private IAuditEntry checkR2140(final ModuleComponent propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<PropertyType>> duplicates = new HashMap<>();
            
            for (PropertyType pt : propertyContainer.getDefinedPropertyType(PropertyType.class)) {
                String name = pt.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyType>());
                }
                duplicates.get(pt.getName()).add(pt);
            }
            
            for (Entry<String, List<PropertyType>> entry : duplicates.entrySet()) {
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

        @objid ("12718b82-ee10-4fca-9dde-cd5a2000f9d9")
        private IAuditEntry checkR2140(final Profile propertyContainer) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    propertyContainer,
                    null);
            
            Map<String, List<PropertyType>> duplicates = new HashMap<>();
            
            for (PropertyType pt : propertyContainer.getDefinedType(PropertyType.class)) {
                String name = pt.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<PropertyType>());
                }
                duplicates.get(pt.getName()).add(pt);
            }
            
            for (Entry<String, List<PropertyType>> entry : duplicates.entrySet()) {
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
