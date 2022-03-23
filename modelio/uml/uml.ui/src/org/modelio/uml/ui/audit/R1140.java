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
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61032 error
 */
@objid ("1bf680dd-f287-4a22-b1ba-8390bd5026a0")
public class R1140 extends AbstractUmlRule {
    @objid ("7d06f4af-850a-4fea-985e-9df31cb83727")
    private static final String RULEID = "R1140";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("2db8f420-c2fb-4fe4-9ddb-fa0c7b8ebfb2")
    private CheckR1140 checkerInstance = null;

    @objid ("168d5e29-30fb-42f0-8927-983e0fe702d5")
    @Override
    public String getRuleId() {
        return R1140.RULEID;
    }

    @objid ("c6a9c5c7-7ec1-4746-8868-0de2c139e8b5")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(CallOperationAction.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(CallOperationAction.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
        // Pin
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.CREATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("68c3014f-d907-4b91-8852-63a3211684c8")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f9b5713e-d774-46ae-a0e0-48fd8d9333d2")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f4c91c72-470e-44b3-8913-a4911c418621")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1140
     */
    @objid ("d4284122-d10a-4d0b-952c-e259aa33a4e9")
    public  R1140() {
        this.checkerInstance = new CheckR1140(this);
    }

    @objid ("9acc077e-f1ed-4eed-9fbd-165eb2d69af3")
    private static class CheckR1140 extends AbstractControl {
        @objid ("91c7e60c-087a-4255-b58e-74986ec7e413")
        public  CheckR1140(IRule rule) {
            super(rule);
        }

        @objid ("4b3b4797-6f07-42d5-881d-a8df51ed069a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof CallOperationAction) {
                diagnostic.addEntry(checkR1140((CallOperationAction) element));
            } else if (element instanceof Pin) {
                IAuditEntry auditEntrey = checkR1140((Pin) element);
                if (auditEntrey != null) {
                    diagnostic.addEntry(auditEntrey);
                }
            } else if (element instanceof Operation) {
                diagnostic.addEntries(checkR1140((Operation) element));
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                Operation operation = null;
                if ((operation = parameter.getComposed()) != null || (operation = parameter.getReturned()) != null) {
                    diagnostic.addEntries(checkR1140(operation));
                }
            } else {
                UmlUi.LOG.warning("R1140: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * Checks if all the pins of a CallOperationAction are matched to all the BehaviorParameters of the called Operation.
         * @param callBehaviorAction The CallOperationAction to check.
         * @return An audit entry.
         */
        @objid ("b6a350bf-c2c8-42b7-bf92-1d05fc228b72")
        private IAuditEntry checkR1140(CallOperationAction callOperationAction) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, callOperationAction, null);
            
            if (callOperationAction.getCalled() == null) {
                return auditEntry;
            }
            
            Operation operation = callOperationAction.getCalled();
            
            // We create a list of parameters, input pins and output pins, and
            // each time we will match a parameter to a pin, we removed the
            // corresponding element from the lists.
            
            List<Parameter> parameters = new ArrayList<>(operation.getIO());
            List<InputPin> inputPins = new ArrayList<>(callOperationAction.getInput());
            List<OutputPin> outputPins = new ArrayList<>(callOperationAction.getOutput());
            
            for (Parameter parameter : operation.getIO()) {
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
            
            Parameter returnParameter = operation.getReturn();
            if (returnParameter != null) {
                for (OutputPin outputPin : callOperationAction.getOutput()) {
                    if (returnParameter.equals(outputPin.getMatched())) {
                        parameters.remove(returnParameter);
                        outputPins.remove(outputPin);
                        break;
                    }
                }
            }
            
            if (!parameters.isEmpty() || !inputPins.isEmpty() || !outputPins.isEmpty()) {
                // The rule failed since one of the list wasn't empty, so either
                // a parameter or a pin wasn't matched.
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(callOperationAction);
                linkedObjects.add(operation);
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
         * A Pin was either created, moved or updated, we need to check if this Pin belong to a CallOperationAction and check it if it does.
         * @param pin The Pin to check.
         * @return An audit entry.
         */
        @objid ("a7601a3a-e0ba-4954-9881-3bee0dc6c391")
        private IAuditEntry checkR1140(Pin pin) {
            if (pin.getCompositionOwner() instanceof CallOperationAction) {
                return checkR1140((CallOperationAction) pin.getCompositionOwner());
            }
            return null;
        }

        /**
         * An Operation was updated, we need to check if it has registered callers and check the rule on these CallOperationAction.
         * @param behavior The Operation to check.
         * @return A list of audit entries.
         */
        @objid ("aedc07fb-c475-4cd3-a912-e250f1924fd4")
        private List<IAuditEntry> checkR1140(Operation operation) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (CallOperationAction callOperationAction : operation.getCallingAction()) {
                auditEntries.add(checkR1140(callOperationAction));
            }
            return auditEntries;
        }

    }

}
