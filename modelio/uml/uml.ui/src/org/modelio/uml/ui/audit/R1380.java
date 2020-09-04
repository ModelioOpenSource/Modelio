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
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61061 error
 */
@objid ("ee2b39f9-3cd7-4f5a-95ed-fdfdde10780f")
public class R1380 extends AbstractUmlRule {
    @objid ("a8ca68db-431c-47b2-bc42-f2ef8bf33ab5")
    private static final String RULEID = "R1380";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("aecaeb50-a4de-4a01-bbcd-c23ced188073")
    private CheckR1380 checkerInstance = null;

    @objid ("ce05f846-4dcb-46f0-a10c-efaa4e40a4c7")
    @Override
    public String getRuleId() {
        return R1380.RULEID;
    }

    @objid ("0ccd4689-ca78-45b5-b14a-cb71de1f4f6a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(SendSignalAction.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c88d7a75-5a3d-4cc2-b1d2-376c0d9ec1cc")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("33987128-4c89-40f9-879e-e912238fd7bb")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6b506b7f-f868-48e3-9f30-772f90dbca67")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1380
     */
    @objid ("414dfef5-e2c9-4d2b-afc9-ee74eece9930")
    public R1380() {
        this.checkerInstance = new CheckR1380(this);
    }

    @objid ("48d980c2-7f06-417c-a045-91b149db3e01")
    private static class CheckR1380 extends AbstractControl {
        @objid ("e205c44b-e9ce-4752-8ee0-a12e34e9b3b7")
        public CheckR1380(IRule rule) {
            super(rule);
        }

        @objid ("dcb72545-e09c-4008-b9a5-73f79f588677")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof SendSignalAction) {
                diagnostic.addEntry(checkR1380((SendSignalAction) element));
            } else if (element instanceof Signal) {
                diagnostic.addEntries(checkR1380((Signal) element));
            } else if (element instanceof InputPin) {
                IAuditEntry auditEntry = checkR1380((InputPin) element);
                if (auditEntry != null) {
                    diagnostic.addEntry(auditEntry);
                }
            } else if (element instanceof Attribute) {
                diagnostic.addEntries(checkR1380((Attribute) element));
            } else {
                UmlUi.LOG.warning("R1380: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f145876e-0fe4-459a-a91e-46fc920acb62")
        private IAuditEntry checkR1380(SendSignalAction action) {
            // Related UML constraint on SendSignalAction:
            // The number and order of argument pins must be the same as the
            // number and order of attributes in the signal.
            // The type, ordering, and multiplicity of an argument pin must be the same as the corresponding attribute of the signal.
            
            // In Modelio, there is no way to differentiate 'arguments pins' from normal InputPins on the action. We consider all InputPin are 'argument pins'.
            
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, action, null);
            
            Signal signal = null;
            
            if ((signal = action.getSent()) == null) {
                return auditEntry;
            }
            
            // We create a list of the Signal's Attributes and of the
            // SendSignalAction's InputPins
            
            List<Attribute> attributes = new ArrayList<>(signal.getOwnedAttribute());
            List<InputPin> inputPins = new ArrayList<>(action.getInput());
            
            // Each time an Attribute is matched to an InputPin, both element
            // are removed from their respective list.
            
            for (Attribute attribute : signal.getOwnedAttribute()) {
                for (InputPin input : action.getInput()) {
                    Attribute pinRepresentedAttribute = input.getRepresentedAttribute();
                    if (attribute.equals(pinRepresentedAttribute)
                            && attribute.getType().equals(input.getType())
            
                            // Cannot check ordering because you can't change ordering on a Pin in Modelio
                            // && (input.getOrdering().equals(ObObjectNodeOrderingKindEnum.ORDERED) && attribute.isOrdered())
            
                            // Since Modelio Pins don't have a LowerBound value, minimum multiplicity cannot be checked.
                            // && input.getLowerBound().equals(attribute.getMultiplicityMin())
            
                            && input.getUpperBound().equals(attribute.getMultiplicityMax())) {
                        attributes.remove(attribute);
                        inputPins.remove(input);
                        break;
                    }
                }
            }
            
            if (!attributes.isEmpty() || !inputPins.isEmpty()) {
            
                // The rule failed since one of the list is not empty, either
                // an Attribute or an InputPin is not matched.
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(action);
                linkedObjects.add(signal);
                if (!attributes.isEmpty()) {
                    for (Attribute attribute : attributes) {
                        linkedObjects.add(attribute);
                    }
                }
                if (!inputPins.isEmpty()) {
                    for (InputPin inputPin : inputPins) {
                        linkedObjects.add(inputPin);
                    }
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        /**
         * If an attribute was created or moved under a Signal, we need to check the rule on the Signal.
         * 
         * @param attribute The created or moved attribute.
         * @return A list of audit entry for each SendSignalAction concerned by the attribute.
         */
        @objid ("513790f1-adc2-4688-9c75-1e0cbc4f7728")
        private List<IAuditEntry> checkR1380(Attribute attribute) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            MObject owner = attribute.getCompositionOwner();
            if (owner instanceof Signal) {
                auditEntries.addAll(checkR1380((Signal) owner));
            }
            return auditEntries;
        }

        /**
         * If an InputPin was created or moved, and if it now belongs to a SendSignalAction, we need to check the rule on the SendSignalAction. If an InputPin was updated and belongs to a SendSignalAction, it's represented attribute potentially changed and we need to check the rule on the SendSignalAction.
         * 
         * @param pin The InputPin that was either created, moved of updated.
         * @return An audit entry for the SendSignalAction if the InputPin belongs to one, null otherwise.
         */
        @objid ("c819ab4e-3dae-47dd-9832-7f3f57ba95fb")
        private IAuditEntry checkR1380(InputPin pin) {
            MObject owner = pin.getCompositionOwner();
            if (owner instanceof SendSignalAction) {
                return checkR1380((SendSignalAction) owner);
            }
            return null;
        }

        /**
         * If a Signal is modified, Attributes were potentially added or removed, and we need to check the rule on any SendSignalAction which is a sender of the Signal.
         * 
         * @param signal The Signal that was updated.
         * @return A list of audit entry for any concerned SendSignalAction.
         */
        @objid ("db913a8d-9788-427e-8d55-d17db35991c3")
        private List<IAuditEntry> checkR1380(Signal signal) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            for (SendSignalAction action : signal.getSender()) {
                auditEntries.add(checkR1380(action));
            }
            return auditEntries;
        }

    }

}
