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

package org.modelio.uml.ui.audit;

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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61028 error
 */
@objid ("cbaaf983-c32a-4564-849f-915b6b22fb4d")
public class R1100 extends AbstractUmlRule {
    @objid ("26684057-d86f-428e-b4c9-8c114e343c61")
    private static final String RULEID = "R1100";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ade0047d-11ae-4a7c-b0a8-68281c161db5")
    private CheckR1100 checkerInstance = null;

    @objid ("0f18b040-0c22-4772-aa89-0b8a9ebb8658")
    @Override
    public String getRuleId() {
        return R1100.RULEID;
    }

    @objid ("1d3aa5d1-8e73-4dd0-8b08-5383bfb85bfb")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ActivityPartition.MQNAME, this,
                AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(BindableInstance.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dd1f8d4a-2b5c-4400-bd58-3df6907d266d")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("70ce3461-c989-4def-8b1f-423c2bc0ea15")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b8419ffe-1b87-415c-a9ad-076c79705769")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1100
     */
    @objid ("fbcbc014-65f7-495a-bf8d-f29234280433")
    public R1100() {
        this.checkerInstance = new CheckR1100(this);
    }

    @objid ("be0bc58e-0394-4447-8984-fae67ed09eba")
    private static class CheckR1100 extends AbstractControl {
        @objid ("9a41b10c-650a-44e6-ad32-3a5031e30d92")
        public CheckR1100(IRule rule) {
            super(rule);
        }

        @objid ("598542d7-666e-4642-9dd7-8191dff78298")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityPartition) {
                diagnostic
                        .addEntries(checkAllPartitions((ActivityPartition) element));
            } else if (element instanceof BindableInstance) {
                diagnostic.addEntries(checkR1100((BindableInstance) element));
            } else {
                UmlUi.LOG.warning("R1100: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * UML2.3, ActivityPartition, Constraints [4]
         * 
         * @param partition The partition to check.
         * @return The audit entry result.
         */
        @objid ("d0a56d0b-cb7a-405a-bdd9-62ec63ca2109")
        private IAuditEntry checkR1100(ActivityPartition partition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, partition, null);
            
            ModelElement element = partition.getRepresented();
            ActivityPartition superPartition = partition.getSuperPartition();
            
            // If the partition does not represent a part, the rule does not
            // apply
            if (element == null || !(element instanceof BindableInstance)) {
                return auditEntry;
            }
            
            // If the partition does not have a super partition, the rule does
            // not apply
            if (superPartition == null) {
                return auditEntry;
            }
            
            ModelElement superElement = superPartition.getRepresented();
            BindableInstance represented = (BindableInstance) element;
            Classifier representedClassifier = represented.getInternalOwner();
            
            // The super partition must represent the same classifier, or an
            // instance typed by this classifier
            if (superElement != null) {
                if (superElement instanceof Classifier
                        && representedClassifier
                                .equals(superElement)) {
                    return auditEntry;
                }
                if (superElement instanceof BindableInstance
                        && representedClassifier
                                .equals(((BindableInstance) superElement)
                                        .getBase())) {
                    return auditEntry;
                }
            }
            
            // At this point the test failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(representedClassifier);
            linkedObjects.add(represented);
            linkedObjects.add(partition);
            linkedObjects.add(superPartition);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        /**
         * When a partition is updated or moved, we need to check if it satisfies the rule, but in the case it is updated, we also need to check if its sub partitions, if any, satisfy the rule or not.
         * 
         * @param partition The updated partition.
         * @return A list of audit entry for each concerned partition.
         */
        @objid ("62e68d41-05ea-450d-9a93-b2bdecf4c314")
        private List<IAuditEntry> checkAllPartitions(ActivityPartition partition) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            // Checking itself
            auditEntries.add(checkR1100(partition));
            
            // Checking its sub partitions if any
            for (ActivityPartition subPartition : partition.getSubPartition()) {
                auditEntries.add(checkR1100(subPartition));
            }
            return auditEntries;
        }

        @objid ("d56349fb-fb7c-45ca-a06f-50ff9cc5e1c7")
        private List<IAuditEntry> checkR1100(BindableInstance part) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityPartition partition : part.getRepresentingPartition()) {
                auditEntries.addAll(checkAllPartitions(partition));
            }
            return auditEntries;
        }

    }

}
