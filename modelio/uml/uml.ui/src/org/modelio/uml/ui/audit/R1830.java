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

import java.util.Arrays;
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
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InteractionModelChecker checkR61104 error
 */
@objid ("85a86fd8-a9d8-43f6-b904-b6317943f8e4")
public class R1830 extends AbstractUmlRule {
    @objid ("b31546a4-9e59-4a19-b093-7c1b89b279fe")
    private static final String RULEID = "R1830";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f876acb4-b791-4bdd-adda-9d3a9713b95a")
    private CheckR1830 checkerInstance = null;

    @objid ("5ea2140e-dfc7-42b0-b1f2-73489595c94c")
    @Override
    public String getRuleId() {
        return R1830.RULEID;
    }

    @objid ("4a203614-0b8f-43f0-ad45-dc4e26ae5e8a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(PartDecomposition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(Message.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("73ab4443-b9c8-42ef-9315-d7efe9aee9e5")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a5e7ac58-37b6-46cb-9adb-bee7196ce8ea")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ea7518bd-fbde-4111-80eb-432492476e1b")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1830
     */
    @objid ("c8722529-eb42-4b2c-8e5d-61513b1143a7")
    public R1830() {
        this.checkerInstance = new CheckR1830(this);
    }

    @objid ("02c14873-95a9-4400-ab46-c3b8b88d9e7b")
    private static class CheckR1830 extends AbstractControl {
        @objid ("f0e667f4-6550-4ed9-bbfb-f0a402703465")
        public CheckR1830(IRule rule) {
            super(rule);
        }

        @objid ("4b8f1652-df05-4c70-a319-b92ba32d8f14")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Message) {
                Message message = (Message) element;
                MessageSort sort = message.getSortOfMessage();
                if (sort.equals(MessageSort.CREATEMESSAGE) ||
                        sort.equals(MessageSort.DESTROYMESSAGE)) {
                    List<Lifeline> lifelines = message.getReceiveEvent().getCovered();
                    for (Lifeline lifeline : lifelines) {
                        PartDecomposition partDecomposition = lifeline.getDecomposedAs();
                        if (partDecomposition != null) {
                            diagnostic.addEntry(checkR1830(partDecomposition));
                        }
                    }
            
                }
            } else if (element instanceof PartDecomposition) {
                diagnostic.addEntry(checkR1830((PartDecomposition) element));
            }
            return diagnostic;
        }

        @objid ("361adfbd-7500-43b7-9bad-c8c32f782dd7")
        private IAuditEntry checkR1830(final PartDecomposition partDecomposition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    partDecomposition,
                    null);
            
            for (MessageEnd messageEnd : partDecomposition.getDecomposed()
                    .getCoveredBy(MessageEnd.class)) {
                Message receivedMessage = messageEnd.getReceivedMessage();
                if (receivedMessage != null) {
                    MessageSort sort = receivedMessage.getSortOfMessage();
                    if (sort.equals(MessageSort.CREATEMESSAGE) ||
                            sort.equals(MessageSort.DESTROYMESSAGE)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        auditEntry.setLinkedInfos(Arrays.asList(partDecomposition, receivedMessage));
                    }
                }
            }
            return auditEntry;
        }

    }

}
