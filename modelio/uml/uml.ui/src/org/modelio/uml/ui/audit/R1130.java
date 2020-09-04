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
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61031 error
 */
@objid ("ebdb9c14-d11b-4e18-a009-8f557fe62dfe")
public class R1130 extends AbstractUmlRule {
    @objid ("86999977-7987-4e7b-b056-48a26a61cb9b")
    private static final String RULEID = "R1130";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("4fe3033f-9beb-4b3b-868e-04f8e0335cc7")
    private CheckR1130 checkerInstance = null;

    @objid ("67d68396-e9a5-41c5-b199-d1d0a1c4e564")
    @Override
    public String getRuleId() {
        return R1130.RULEID;
    }

    @objid ("2b369460-6307-47c1-9f18-890a631a6a34")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(BehaviorParameter.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8b1a0d50-8df8-4f2b-bab4-0a0837ee6d30")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8f95daa2-af2d-4941-8dc1-50cfcd41feb6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c9cba5dd-4597-440f-ab8b-975ed29a6354")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1130
     */
    @objid ("5cbfd3d0-05ea-4a3b-88b2-8595e4a15cd3")
    public R1130() {
        this.checkerInstance = new CheckR1130(this);
    }

    @objid ("ea006856-9570-4a63-ae64-30b29a936efb")
    private static class CheckR1130 extends AbstractControl {
        @objid ("5a2d572c-f1d7-47d1-aa1a-a379b19af1f7")
        public CheckR1130(IRule rule) {
            super(rule);
        }

        @objid ("af348a93-f0be-43a6-bd77-175b6b68c65c")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Pin) {
                diagnostic.addEntry(checkR1130((Pin) element));
            } else if (element instanceof Parameter) {
                diagnostic.addEntries(checkR1130((Parameter) element));
            } else {
                UmlUi.LOG.warning("R1130: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5266230c-fbff-47fa-97cf-a7ba3c4fa560")
        private IAuditEntry checkR1130(Pin pin) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    pin,
                    null);
            
            if (!(pin.getCompositionOwner() instanceof CallAction)) {
                return auditEntry;
            }
            
            Parameter parameter = pin.getMatched();
            
            if (parameter == null) {
                return auditEntry;
            }
            
            boolean sameType = false;
            
            if (parameter.getType() != null) {
                if (parameter.getType().equals(pin.getType())) {
                    sameType = true;
                }
            } else if (pin.getType() == null) {
                sameType = true;
            }
            
            if (sameType) {
                if (parameter.getMultiplicityMax().equals(pin.getUpperBound())) {
                    return auditEntry;
                }
            }
            
            // At this point the rule failed
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(pin);
            linkedObjects.add(parameter);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        @objid ("33561554-b77a-458a-80ee-a6634340833c")
        private List<IAuditEntry> checkR1130(Parameter parameter) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (parameter instanceof BehaviorParameter) {
                for (CallBehaviorAction callBehaviorAction : ((BehaviorParameter) parameter).getOwner()
                        .getCaller()) {
                    for (InputPin pin : callBehaviorAction.getInput()) {
                        Parameter pinParameter = pin.getMatched();
                        if (pinParameter != null && pinParameter.equals(parameter)) {
                            auditEntries.add(checkR1130(pin));
                        }
                    }
                    for (OutputPin pin : callBehaviorAction.getOutput()) {
                        Parameter pinParameter = pin.getMatched();
                        if (pinParameter != null && pinParameter.equals(parameter)) {
                            auditEntries.add(checkR1130(pin));
                        }
                    }
                }
            } else {
                Operation operation = parameter.getComposed();
                if (operation == null) {
                    operation = parameter.getReturned();
                }
                if (operation != null) {
                    for (CallOperationAction callOperationAction : operation.getCallingAction()) {
                        for (InputPin pin : callOperationAction.getInput()) {
                            Parameter pinParameter = pin.getMatched();
                            if (pinParameter != null && pinParameter.equals(parameter)) {
                                auditEntries.add(checkR1130(pin));
                            }
                        }
                        for (OutputPin pin : callOperationAction.getOutput()) {
                            Parameter pinParameter = pin.getMatched();
                            if (pinParameter != null && pinParameter.equals(parameter)) {
                                auditEntries.add(checkR1130(pin));
                            }
                        }
                    }
                }
            }
            return auditEntries;
        }

    }

}
