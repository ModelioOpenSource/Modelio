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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * New Rule: Message and CommunicationMessage cannot have both Signal and Operation properties defined.
 */
@objid ("018275b4-0267-421b-ac3b-9cd32b77831e")
public class R2930 extends AbstractUmlRule {
    @objid ("1cd32fa8-1442-4c13-83ea-840b414b9537")
    private static final String RULEID = "R2930";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("0380b4c5-884e-4eca-a9db-59a336746da3")
    private CheckR2930 checkerInstance = null;

    @objid ("d34d3924-76cd-4c75-acfc-dcd7f7d043ca")
    @Override
    public String getRuleId() {
        return R2930.RULEID;
    }

    @objid ("c51cacb7-a4d7-422c-b0a0-0a1d6d945f85")
    @Override
    public void autoRegister(final UmlAuditPlan plan) {
        plan.registerRule(Message.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(CommunicationMessage.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("843a19ec-39a4-4112-bc07-1297b58a3d99")
    @Override
    public IControl getCreationControl(final MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("34306688-f9ce-4bce-85fe-2c6de0f856e0")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e7c97a85-4534-43d6-9232-7e094ae24f63")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2930
     */
    @objid ("957c0745-1eea-40cc-878d-b63ede86aabc")
    public R2930() {
        this.checkerInstance = new CheckR2930(this);
    }

    @objid ("0b335309-c1ac-4696-b242-062e53aeba8b")
    private static class CheckR2930 extends AbstractControl {
        @objid ("1c8e9c82-ad19-4690-978d-370f74e96dd9")
        public CheckR2930(final IRule rule) {
            super(rule);
        }

        @objid ("dac3c30a-69ed-4b9c-b2d7-4ccd62e3d873")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof Message) {
                diagnostic.addEntry(checkR2930((ModelElement) element));
            } else if (element instanceof CommunicationMessage) {
                diagnostic.addEntry(checkR2930((ModelElement) element));
            } else {
                UmlUi.LOG.warning("R2930: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("e5bfcd46-a651-46b3-9e6d-a923fd6d72b8")
        private IAuditEntry checkR2930(final ModelElement message) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    message,
                    null);
            
            Operation operation = null;
            Signal signal = null;
            
            if (message instanceof CommunicationMessage) {
                CommunicationMessage msg = (CommunicationMessage) message;
                operation = msg.getInvoked();
                signal = msg.getSignalSignature();
            } else if (message instanceof Message) {
                Message msg = (Message) message;
                operation = msg.getInvoked();
                signal = msg.getSignalSignature();
            }
            
            if (signal != null && operation != null) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(message);
                linkedObjects.add(message.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
