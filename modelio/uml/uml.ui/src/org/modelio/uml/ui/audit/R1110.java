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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61029 error
 */
@objid ("954bc510-5054-495b-ae91-5ce2ee5b56d7")
public class R1110 extends AbstractUmlRule {
    @objid ("ef1799e9-fd37-4a2c-bf34-686e779873a5")
    private static final String RULEID = "R1110";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("44a02e28-f345-4652-90ae-c2de0bdcea19")
    private CheckR1110 checkerInstance = null;

    @objid ("f3b103f8-052c-4b55-b95f-4ad1d9f7b8c5")
    @Override
    public String getRuleId() {
        return R1110.RULEID;
    }

    @objid ("c766abcd-fcaa-4c2f-af7f-2ec66d55e2e3")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(CallBehaviorAction.MQNAME, this,
                AuditTrigger.UPDATE);
        
        // IBehaviour
        plan.registerRule(Activity.MQNAME, this, AuditTrigger.UPDATE);
        
        // The following Behaviour can't have Parameters in Modelio!
        plan.registerRule(BehaviorParameter.MQNAME, this,
                AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
        // Pin
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.UPDATE
                | AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE
                | AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dbf6fc02-5727-48ca-9a01-f45b0f752819")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("02614abb-236d-4d05-a109-2c71dc9b6234")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a7fd367d-e851-4936-8be9-31f1bc1c9c7c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1110
     */
    @objid ("08417c97-7b22-4423-bf7b-f17ee9ffa1d4")
    public R1110() {
        this.checkerInstance = new CheckR1110(this);
    }

    @objid ("725ca2c9-c061-4091-8500-f3ac8ec1f8c7")
    private static class CheckR1110 extends AbstractControl {
        @objid ("12962898-0496-4273-b4d1-82808650f015")
        public CheckR1110(IRule rule) {
            super(rule);
        }

        @objid ("3ad694d5-6192-411f-8b8f-fcb9ea66332a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof CallBehaviorAction) {
                diagnostic
                        .addEntry(checkR1110((CallBehaviorAction) element));
            } else if (element instanceof Pin) {
                IAuditEntry entry = checkR1110((Pin) element);
                if (entry != null) {
                    diagnostic.addEntry(entry);
                }
            } else if (element instanceof Behavior) {
                diagnostic.addEntries(checkR1110((Behavior) element));
            } else if (element instanceof BehaviorParameter) {
                diagnostic
                        .addEntries(checkR1110((BehaviorParameter) element));
            } else {
                UmlUi.LOG.warning("R1110: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * UML 2.3, CallBehaviorAction, Constraints [1] & [2]
         * 
         * @param callBehaviorAction The CallBehaviorAction to check.
         * @return An audit entry.
         */
        @objid ("0bad0ead-a68f-4aef-9541-dc6d1ff78432")
        private IAuditEntry checkR1110(CallBehaviorAction callBehaviorAction) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, callBehaviorAction, null);
            
            if (callBehaviorAction.getCalled() == null) {
                return auditEntry;
            }
            
            Behavior behavior = callBehaviorAction.getCalled();
            
            // We create a list of parameters, input pins and output pins, and
            // each time we will match a parameter to a pin, we removed the
            // corresponding element from the lists.
            
            List<BehaviorParameter> parameters = new ArrayList<>(
                    behavior.getParameter());
            List<InputPin> inputPins = new ArrayList<>(
                    callBehaviorAction.getInput());
            List<OutputPin> outputPins = new ArrayList<>(
                    callBehaviorAction.getOutput());
            
            for (BehaviorParameter parameter : behavior.getParameter()) {
                if (parameter.getParameterPassing() == PassingMode.IN) {
                    for (InputPin inputPin : inputPins) {
                        if (parameter.equals(inputPin.getMatched())) {
                            parameters.remove(parameter);
                            inputPins.remove(inputPin);
                            break;
                        }
                    }
                } else if (parameter.getParameterPassing() == PassingMode.INOUT) {
            
                    boolean found = false;
            
                    for (InputPin inputPin : inputPins) {
                        if (parameter.equals(inputPin.getMatched())) {
                            inputPins.remove(inputPin);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        found = false;
                        for (OutputPin outputPin : outputPins) {
                            if (parameter.equals(outputPin.getMatched())) {
                                outputPins.remove(outputPin);
                                parameters.remove(parameter);
                                break;
                            }
                        }
                    }
                } else if (parameter.getParameterPassing() == PassingMode.OUT) {
                    for (OutputPin outputPin : outputPins) {
                        if (parameter.equals(outputPin.getMatched())) {
                            parameters.remove(parameter);
                            outputPins.remove(outputPin);
                            break;
                        }
                    }
                }
            }
            
            if (!parameters.isEmpty() || !inputPins.isEmpty()
                    || !outputPins.isEmpty()) {
            
                // The rule failed since one of the list wasn't empty, so either
                // a parameter or a pin wasn't matched.
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(callBehaviorAction);
                linkedObjects.add(behavior);
                if (!parameters.isEmpty()) {
                    for (Parameter parameter : parameters) {
                        linkedObjects.add(parameter);
                    }
                }
                if (!inputPins.isEmpty()) {
                    for (InputPin inputPin : inputPins) {
                        linkedObjects.add(inputPin);
                    }
                }
                if (!outputPins.isEmpty()) {
                    for (OutputPin outputPin : outputPins) {
                        linkedObjects.add(outputPin);
                    }
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        /**
         * A Pin was either created, moved or updated, we need to check if this Pin belong to a CallBehaviorAction and check it if it does.
         * 
         * @param pin The Pin to check.
         * @return An audit entry.
         */
        @objid ("4675d109-1041-45a0-9aba-86cc247150a2")
        private IAuditEntry checkR1110(Pin pin) {
            if (pin.getCompositionOwner() instanceof CallBehaviorAction) {
                return checkR1110((CallBehaviorAction) pin
                        .getCompositionOwner());
            }
            return null;
        }

        /**
         * A BehaviorParameter was either created, moved, or updated, wee need to check the Behavior it belongs to.
         * 
         * @param parameter The parameter to check.
         * @return A list of audit entries.
         */
        @objid ("93256ab6-3c77-48ad-b7d3-7054fa95c186")
        private List<IAuditEntry> checkR1110(BehaviorParameter parameter) {
            return checkR1110(parameter.getOwner());
        }

        /**
         * A Behavior was updated, we need to check if it has registered callers and check the rule on these CallBehaviorAction.
         * 
         * @param behavior The Behavior to check.
         * @return A list of audit entries.
         */
        @objid ("3d024766-1941-4380-b224-e8eb358708ee")
        private List<IAuditEntry> checkR1110(Behavior behavior) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (CallBehaviorAction callBehaviorAction : behavior.getCaller()) {
                auditEntries
                        .add(checkR1110(callBehaviorAction));
            }
            return auditEntries;
        }

    }

}
