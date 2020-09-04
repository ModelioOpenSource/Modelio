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
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61027 error
 */
@objid ("6b754608-52fe-463c-9bf1-1f0f32c73ba3")
public class R1090 extends AbstractUmlRule {
    @objid ("631c6121-0b09-4b9a-a414-f8effa81cc65")
    private static final String RULEID = "R1090";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("26bb18ac-6d1f-4aa3-a16c-36b4bdffd783")
    private CheckR1090 checkerInstance = null;

    @objid ("0d3c020f-d948-4a10-900a-1b581bd137c6")
    @Override
    public String getRuleId() {
        return R1090.RULEID;
    }

    @objid ("90d9103f-7ef2-4cb6-8540-765bb09ea75d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Activity
        plan.registerRule(ActivityPartition.MQNAME, this,
                AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
        // Classifier
        plan.registerRule(InformationItem.MQNAME, this,
                AuditTrigger.MOVE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(TemplateParameter.MQNAME, this,
                AuditTrigger.MOVE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(ModuleComponent.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("372cf15f-6c25-4cdb-8024-abe5d3b449f8")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("91364a4c-61a2-4b08-b131-2d92b914888b")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("142b5c53-7355-4412-99ab-a75c8233d9b7")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1090
     */
    @objid ("81c0e61b-9e6b-4be4-8200-c209366a9624")
    public R1090() {
        this.checkerInstance = new CheckR1090(this);
    }

    @objid ("077bccd0-618b-46e1-aacf-093f54ce132a")
    private static class CheckR1090 extends AbstractControl {
        @objid ("4ac3a25b-32e7-405c-8d43-48003938e3c7")
        public CheckR1090(IRule rule) {
            super(rule);
        }

        @objid ("734b5226-52b9-45a1-bfe4-7b6d5e7455cc")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityPartition) {
                diagnostic.addEntries(checkAllPartitions((ActivityPartition) element));
            } else if (element instanceof Classifier) {
                diagnostic.addEntries(checkR1090((Classifier) element));
            } else {
                UmlUi.LOG.warning("R1090: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * UML2.3, ActivityPartition, Constraints [3]
         * 
         * @param partition The partition to check.
         * @return The audit entry result.
         */
        @objid ("8af812c8-4edb-45e1-bb2d-910abedbf9b1")
        private IAuditEntry checkR1090(ActivityPartition partition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, partition, null);
            
            // If the partition is external, or if it's not represented by a
            // classifier or if it does not have a super partition, the rule
            // does not apply
            if (partition.isIsExternal()
                    || !(partition.getRepresented() instanceof Classifier)
                    || partition.getSuperPartition() == null) {
                return auditEntry;
            }
            
            if (partition.getSuperPartition().getRepresented() instanceof Classifier) {
            
                Classifier superClassifier = (Classifier) partition
                        .getSuperPartition().getRepresented();
                Classifier classifier = (Classifier) partition
                        .getRepresented();
            
                // Checking if the partition represented classifier is
                // nested in the super partition represented classifier
                if (superClassifier.getOwnedElement().contains(classifier)) {
                    return auditEntry;
                } else {
                    for (AssociationEnd assoc : superClassifier.getOwnedEnd()) {
                        if (assoc.getAggregation().equals(AggregationKind.KINDISCOMPOSITION)) {
                            if (classifier.equals(assoc.getTarget())) {
                                return auditEntry;
                            }
                        }
                    }
                }
            }
            
            // At this point the rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(partition.getSuperPartition());
            linkedObjects.add(partition);
            linkedObjects.add(partition.getRepresented());
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        /**
         * If a Classifier was updated, wee need to check if it is represented in any Partition. If it is, we check the rule on this partition.
         * 
         * @param classifier The updated Classifier.
         * @return A list of audit entry for each partition that was checked.
         */
        @objid ("a640c3f1-4b38-4f0c-8bd2-a9612feef064")
        private List<IAuditEntry> checkR1090(Classifier classifier) {
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityPartition partition : ((UmlModelElement) classifier)
                    .getRepresentingPartition()) {
                auditEntries.add(checkR1090(partition));
            }
            return auditEntries;
        }

        /**
         * When a partition is updated or moved, we need to check if it satisfies the rule, but in the case it is updated, we also need to check if its sub partitions, if any, satisfy the rule or not.
         * 
         * @param partition The updated partition.
         * @return A list of audit entry for each concerned partition.
         */
        @objid ("fb68f115-0e6f-4335-90a2-485d87e7c9e0")
        private List<IAuditEntry> checkAllPartitions(ActivityPartition partition) {
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            // Checking itself
            auditEntries.add(checkR1090(partition));
            
            // Checking its sub partitions if any
            for (ActivityPartition subPartition : partition.getSubPartition()) {
                auditEntries.add(checkR1090(subPartition));
            }
            return auditEntries;
        }

    }

}
