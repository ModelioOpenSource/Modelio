/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InteractionModelChecker checkR61063 error
 */
@objid ("4aa32c12-55e8-4055-8d52-a6edce364dbb")
public class R1800 extends AbstractUmlRule {
    @objid ("2b3cc460-c813-4257-88e2-8833ff7d4026")
    private static final String RULEID = "R1800";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ee9ab1eb-af78-4f41-9c27-8f18331432c8")
    private CheckR1800 checkerInstance = null;

    @objid ("a21c7deb-4ac7-4b90-8001-2785b043d57f")
    @Override
    public String getRuleId() {
        return R1800.RULEID;
    }

    @objid ("170ade23-7900-4cb9-9a65-f8e981aa6101")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(CombinedFragment.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d5a3e602-b2ed-4e6c-b2b0-c78d97ef6cce")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a0a7463d-0a46-46cc-8890-03e201bfbe58")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0a45dc72-9d40-47fb-897c-f5d7542d5973")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1800
     */
    @objid ("afa9b61f-9074-4fda-96cb-c0436f358a70")
    public R1800() {
        this.checkerInstance = new CheckR1800(this);
    }

    @objid ("5b3a99d0-c619-46b6-a76c-807f89d755a0")
    private static class CheckR1800 extends AbstractControl {
        @objid ("7d2316d8-c571-43fd-a9ef-c2835e83d7e5")
        public CheckR1800(IRule rule) {
            super(rule);
        }

        @objid ("d642fc41-6aac-4757-a165-763155487fd5")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof CombinedFragment) {
                diagnostic.addEntry(checkR1800((CombinedFragment) element));
            } else {
                UmlUi.LOG.warning("R1800: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f21237d4-7825-4aed-80ba-8f6b4f86f6e3")
        private IAuditEntry checkR1800(final CombinedFragment combinedFragment) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, combinedFragment, null);
            
            InteractionOperator operator = combinedFragment.getOperator();
            if ((operator.equals(InteractionOperator.OPTOP) || operator.equals(InteractionOperator.LOOPOP) || operator.equals(InteractionOperator.BREAKOP) || operator.equals(InteractionOperator.NEGOP)) && combinedFragment.getOperand().size() > 1) {
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(combinedFragment);
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
