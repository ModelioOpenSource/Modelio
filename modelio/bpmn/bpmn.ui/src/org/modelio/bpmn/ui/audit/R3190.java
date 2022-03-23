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
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3190 Severity : error Description : A DataAssociation cannot target a DataInput or have a DataOutput as its source.
 */
@objid ("f75b49e4-0017-4f68-9f3d-87379a5645c3")
public class R3190 extends AbstractBpmnRule {
    @objid ("cf5abfeb-fac6-44ce-b587-6668e6c38ef2")
    private static final String RULEID = "R3190";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f96d48ba-ac2c-4889-869c-76fcc77fcaa8")
    private CheckR3190 checkerInstance = null;

    @objid ("ddb5ef6b-a150-4b5d-851c-b57f722f0842")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnDataAssociation.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    @objid ("09ee2398-5e67-4ece-8cc8-b6b1bb0193b3")
    @Override
    public String getRuleId() {
        return R3190.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("94a6baf4-5624-4bed-8cfc-15ee757bc528")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6be4063e-c9ec-4aaf-b0b0-0fa0814db00b")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a2dbca07-2dc3-4b90-bed3-48c38aadad45")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3190
     */
    @objid ("c4e9a535-7b3b-4dbf-92c3-e9a0974c97c1")
    public  R3190() {
        this.checkerInstance = new CheckR3190(this);
    }

    /**
     * Actual checker for R3190: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("ec4cc0c2-6750-4c52-829d-9bc841f1eaee")
    private static class CheckR3190 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("a0592a66-b45c-4108-899b-db4c6c6c61c8")
        public  CheckR3190(final IRule rule) {
            super(rule);
        }

        @objid ("71dca967-afad-448b-a2bd-af20ac64c04b")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnDataAssociation) {
                diagnostic.addEntry(checkR3190((BpmnDataAssociation) element));
            } else {
                BpmnUi.LOG.warning("R3190: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("d4c2f57d-aaa8-4663-99dd-bcc7693109c3")
        private IAuditEntry checkR3190(final BpmnDataAssociation dataAssoc) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    dataAssoc,
                    null);
            
            if (getFromElement(dataAssoc) instanceof BpmnDataOutput || getToElement(dataAssoc) instanceof BpmnDataInput) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(dataAssoc);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("2c37b5dd-92ca-44e2-a249-31adbaa37c17")
        private MObject getFromElement(BpmnDataAssociation dataAssoc) {
            if (dataAssoc.getEndingEvent() != null) {
                return dataAssoc.getEndingEvent();
            } else if (dataAssoc.getStartingActivity() != null) {
                return dataAssoc.getStartingActivity();
            } else if (dataAssoc.getSourceRef().size() > 0) {
                return dataAssoc.getSourceRef().get(0);
            } else if (dataAssoc.getTargetRef() != null) {
                return dataAssoc.getTargetRef();
            }
            return null;
        }

        @objid ("94b19620-84bd-4903-a7bd-2375e33e37e2")
        private MObject getToElement(BpmnDataAssociation dataAssoc) {
            if (dataAssoc.getStartingEvent() != null) {
                return dataAssoc.getStartingEvent();
            } else if (dataAssoc.getEndingActivity() != null) {
                return dataAssoc.getEndingActivity();
            } else if (dataAssoc.getTargetRef() != null) {
                return dataAssoc.getTargetRef();
            } else if (dataAssoc.getSourceRef().size() > 0) {
                return dataAssoc.getSourceRef().get(0);
            }
            return null;
        }

    }

}
