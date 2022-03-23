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
package org.modelio.bpmn.ui.audit;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3270 Severity : warning Description : If a BpmnItemAwareElement has a type GeneralClass, then its State must also be part of that GeneralClass.
 */
@objid ("6b7bee21-3b18-4ae5-a3bc-6a2e1525c3ba")
public class R3270 extends AbstractBpmnRule {
    @objid ("be708ecb-a280-4121-afc3-268a22333f08")
    private static final String RULEID = "R3270";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("22212ba8-50cd-4170-a377-c549dca5f5bf")
    private CheckR3270 checkerInstance = null;

    @objid ("7f628786-08c3-44fa-a0e4-aa3b2a01eba2")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnDataInput.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataObject.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataOutput.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataStore.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
    }

    @objid ("34f38764-91a7-49df-87ec-a28a9a2c4e26")
    @Override
    public String getRuleId() {
        return R3270.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("56267b4c-bf6f-4f9c-a050-2bb8b4c8dde0")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b83ca576-a843-4cb8-8847-4ae317f163b8")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0a28a446-89b4-4da8-a676-afbc77abfdf7")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("88028892-b290-4185-ad5c-6347de8232e0")
    public  R3270() {
        this.checkerInstance = new CheckR3270(this);
    }

    /**
     * Actual checker for R3270: Checks that the type and inState relationships are coherent.
     */
    @objid ("e2b77914-c549-4ffd-ac1f-da753744bdca")
    private static class CheckR3270 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("80b5c743-a3d7-45ef-bd3a-1f0fc9e6404f")
        public  CheckR3270(final IRule rule) {
            super(rule);
        }

        @objid ("946ff1cd-1118-4475-a1b0-15d0e6ba57d5")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnItemAwareElement) {
                diagnostic.addEntry(checkR3270((BpmnItemAwareElement) element));
            }
            return diagnostic;
        }

        @objid ("c668ddf1-d5f7-4468-a8d3-d2ed276e3d9a")
        private IAuditEntry checkR3270(final BpmnItemAwareElement element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    element,
                    null);
            
            final ModelElement type = Represents.getTarget(element);
            final ModelElement state = org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.getTarget(element);
            if (type != null && state != null) {
                if (!isOwning(state, type)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(element);
                    linkedObjects.add(state);
                    linkedObjects.add(type);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("d5b4ca00-f0c9-4ebf-b635-8d4a767493d5")
        private boolean isOwning(ModelElement state, ModelElement type) {
            MObject parent = state;
            while (parent != null && !parent.equals(type)) {
                parent = parent.getCompositionOwner();
            }
            return parent != null && parent.equals(type);
        }

    }

}
