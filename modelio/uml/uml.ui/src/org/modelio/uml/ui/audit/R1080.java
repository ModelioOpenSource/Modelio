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
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61026 error
 */
@objid ("1739be6d-c163-4b1d-a7eb-9273f77a07d2")
public class R1080 extends AbstractUmlRule {
    @objid ("2e76c4bb-85e0-41b0-ae92-c0bb457f2988")
    private static final String RULEID = "R1080";

    /**
     * The checker unique instance for move behaviours.
     */
    @objid ("6fed0888-d240-457d-8e9e-32a669a2ecbe")
    private CheckR1080Move moveCheckerInstance = null;

    /**
     * The checker unique instance for update behaviours.
     */
    @objid ("44e468aa-ffb0-420b-a715-c24844895772")
    private CheckR1080Update updateCheckerInstance = null;

    @objid ("4d3ae5e7-715e-4941-b3c1-a7dde3565e2c")
    @Override
    public String getRuleId() {
        return R1080.RULEID;
    }

    @objid ("afdcd3ff-df86-499b-bc2c-213645d64432")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Activity
        plan.registerRule(ActivityPartition.MQNAME, this,
                AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Activity.MQNAME, this, AuditTrigger.UPDATE);
        
        // BindableInstance
        plan.registerRule(Port.MQNAME, this, AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2d1d859c-4488-480c-a8ca-89108bcf7478")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2288c883-573c-4f48-94eb-f8e4a5b40d4b")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.moveCheckerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("501b1c99-4b4c-43e3-aa7a-4636986208e7")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.updateCheckerInstance;
    }

    /**
     * Default constructor for R1080
     */
    @objid ("fec8b4c8-95f5-413d-b513-bfdea44c06ff")
    public  R1080() {
        this.moveCheckerInstance = new CheckR1080Move(this);
        this.updateCheckerInstance = new CheckR1080Update(this);
        
    }

    /**
     * This checker is instantiated for a move event on a ActivityPartition or on a Port. In this case, we only need to check the homogeneity of the siblings partitions.
     */
    @objid ("3617f70a-9876-4e32-8642-c786018216c3")
    private static class CheckR1080Move extends AbstractControl {
        @objid ("f0409146-ef7f-4ba0-ae9b-cf4b1fb8b723")
        public  CheckR1080Move(IRule rule) {
            super(rule);
        }

        @objid ("b5ef1c6c-80b3-4a6a-8ae6-a97b1966133b")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Port) {
                diagnostic.addEntries(checkR1080((Port) element));
            } else if (element instanceof ActivityPartition) {
                diagnostic.addEntry(checkR1080((ActivityPartition) element));
            } else {
                UmlUi.LOG.warning("R1080: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3de4e97c-d205-44f8-8fc2-b6eeaa9f8e7e")
        private IAuditEntry checkR1080(ActivityPartition partition) {
            AuditEntry auditEntry;
            
            if (partition.getSuperPartition() != null) {
                auditEntry = new AuditEntry(this.rule.getRuleId(),
                        AuditSeverity.AuditSuccess, partition
                                .getSuperPartition(),
                        null);
            } else {
                auditEntry = new AuditEntry(this.rule.getRuleId(),
                        AuditSeverity.AuditSuccess, partition.getInActivity(),
                        null);
            }
            
            if (!needsCheck(partition)) {
                return auditEntry;
            }
            
            if (!checkSiblings(partition)) {
            
                // Rule failed on siblings
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                if (partition.getSuperPartition() != null) {
                    linkedObjects.add(partition.getSuperPartition());
                } else {
                    linkedObjects.add((partition.getInActivity()));
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        /**
         * Check the homogeneity of the siblings of a partition.
         * @param partition The partition to check
         * @return True is the partition's siblings partitions are homogeneous, false otherwise
         */
        @objid ("fef5d4f5-aaef-4981-8a22-ab83ef3fb05d")
        public boolean checkSiblings(ActivityPartition partition) {
            if (partition.getSuperPartition() != null) {
                return checkChildren(partition.getSuperPartition());
            } else {
                return checkChildren(partition.getInActivity());
            }
            
        }

        /**
         * Check the homogeneity of the children partitions of a partition
         * @param partition The partition to check
         * @return True is the activity's children are homogeneous or if there are no children, false otherwise
         */
        @objid ("0dbcb6e3-4ef7-4aca-bbdb-a50256ec988a")
        public boolean checkChildren(ActivityPartition partition) {
            List<ActivityPartition> childrenPartitions = partition.getSubPartition();
            if (childrenPartitions != null) {
                return checkPartitionsHomogeneity(childrenPartitions);
            } else {
                return true;
            }
            
        }

        /**
         * Check the homogeneity of the children partitions of an activity
         * @param activity The activity to check
         * @return True is the activity's children are homogeneous or if there are no children, false otherwise
         */
        @objid ("fe8859a2-f4cf-4f83-8537-aab3e72f3d4a")
        public boolean checkChildren(Activity activity) {
            List<ActivityPartition> childrenPartitions = new ArrayList<>();
            for (ActivityPartition partition : activity
                    .getOwnedGroup(ActivityPartition.class)) {
                childrenPartitions.add(partition);
            }
            if (!childrenPartitions.isEmpty()) {
                return checkPartitionsHomogeneity(childrenPartitions);
            } else {
                return true;
            }
            
        }

        /**
         * Check if a list of partitions are homogeneous (that they represent parts of the same Classifier)
         * @param partitions partitions of the same level
         * @return true if partitions are homogeneous , false otherwise
         */
        @objid ("08d6c179-524b-4bcd-88b1-6b3eb051b5d8")
        private boolean checkPartitionsHomogeneity(List<ActivityPartition> partitions) {
            Classifier homogeneousClassifier = null;
            
            for (ActivityPartition partition : partitions) {
                if (needsCheck(partition)) {
            
                    Classifier classifier = ((BindableInstance) partition
                            .getRepresented()).getInternalOwner();
            
                    if (homogeneousClassifier == null) {
                        homogeneousClassifier = classifier;
                    } else if (!homogeneousClassifier.equals(classifier)) {
                        return false;
                    }
                }
            }
            return true;
        }

        /**
         * Check if a partition needs to be checked by the rule (if it's not external and if it's represented by a part.
         * @param partition The partition to check.
         * @return True if the partition needs to be checked, else otherwise.
         */
        @objid ("294be3e8-5cc2-4887-950a-10a5391e2a21")
        private boolean needsCheck(ActivityPartition partition) {
            UmlModelElement element = null;
            return (!partition.isIsExternal() && (element = partition
                                                                                .getRepresented()) != null)
                                                                                && element instanceof BindableInstance ? true : false;
        }

        /**
         * Checks is the Classifier is represented in any partition, and check the rule on these partitions.
         * @param classifier The classifier to check
         * @return A list of audit entries for each partition that was checked
         */
        @objid ("c3f22d64-0274-48dc-95e2-b5f54c3a8fb3")
        private List<IAuditEntry> checkR1080(Port port) {
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (((UmlModelElement) port).getRepresentingPartition() != null) {
                for (ActivityPartition partition : ((UmlModelElement) port)
                        .getRepresentingPartition()) {
            
                    AuditEntry auditEntry;
            
                    if (partition.getSuperPartition() != null) {
                        auditEntry = new AuditEntry(this.rule.getRuleId(),
                                AuditSeverity.AuditSuccess, partition
                                        .getSuperPartition(),
                                null);
                    } else {
                        auditEntry = new AuditEntry(this.rule.getRuleId(),
                                AuditSeverity.AuditSuccess, partition
                                        .getInActivity(),
                                null);
                    }
            
                    if (!checkSiblings(partition)) {
            
                        // Rule failed on siblings
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        if (partition.getSuperPartition() != null) {
                            linkedObjects.add(partition.getSuperPartition());
                        } else {
                            linkedObjects.add((partition.getInActivity()));
                        }
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
            
                    auditEntries.add(auditEntry);
                }
            }
            return auditEntries;
        }

    }

    /**
     * This checker is instantiated either for an update on an ActivityPartityon or an Activity.
     */
    @objid ("f3983126-97f1-4de9-8895-cee253c61b2f")
    private static class CheckR1080Update extends CheckR1080Move {
        @objid ("9874ace5-fcc5-41a8-bcee-463429d9d198")
        public  CheckR1080Update(IRule rule) {
            super(rule);
        }

        @objid ("087a9a60-d4e4-4e88-a1d8-47628a7ebe84")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityPartition) {
                diagnostic
                        .addEntries(checkR1080((ActivityPartition) element));
            } else if (element instanceof Activity) {
                diagnostic.addEntry(checkR1080((Activity) element));
            } else {
                UmlUi.LOG.warning("R1080: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5fce2e10-5973-428c-a8b1-8ebc6c4fa02a")
        private IAuditEntry checkR1080(Activity activity) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, activity, null);
            
            if (!super.checkChildren(activity)) {
            
                // Rule failed on children
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(activity);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        /**
         * In this case we need to check the siblings partition of our updated partition, and the children partitions in case the partition was updated after a moved partition event.
         * @param partition The partition to check
         * @return The result of the audit
         */
        @objid ("9a132dc0-3b83-4942-b9f8-2cc05455281a")
        private List<IAuditEntry> checkR1080(ActivityPartition partition) {
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            AuditEntry auditEntry;
            
            // Applying rule on super partition or activity
            
            if (partition.getSuperPartition() != null) {
                auditEntry = new AuditEntry(this.rule.getRuleId(),
                        AuditSeverity.AuditSuccess, partition
                                .getSuperPartition(),
                        null);
            } else {
                auditEntry = new AuditEntry(this.rule.getRuleId(),
                        AuditSeverity.AuditSuccess, partition.getInActivity(),
                        null);
            }
            
            if (!super.checkSiblings(partition)) {
            
                // Rule failed on siblings
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                if (partition.getSuperPartition() != null) {
                    linkedObjects.add(partition.getSuperPartition());
                } else {
                    linkedObjects.add((partition.getInActivity()));
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            
            auditEntries.add(auditEntry);
            
            // Applying rule on self
            
            auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, partition, null);
            
            if (!super.checkChildren(partition)) {
            
                // Rule failed on children
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(partition);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            
            auditEntries.add(auditEntry);
            return auditEntries;
        }

    }

}
