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
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63013 error
 */
@objid ("ecad8e6f-ad7c-46f8-8943-353f7b791f6a")
public class R2830 extends AbstractUmlRule {
    @objid ("5cb60d27-2538-4414-84a7-4276323a51b1")
    private static final String RULEID = "R2830";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("20a06c10-37e2-4274-a75e-d568edd101e4")
    private CheckR2830 checkerInstance = null;

    @objid ("4af48a98-3fcb-474a-b401-648f36aa6b5e")
    @Override
    public String getRuleId() {
        return R2830.RULEID;
    }

    @objid ("de456df0-97ba-4bb0-b7b0-92de7186a483")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(FinalState.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f46a2049-d083-4558-adc5-01106e5d8d42")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6ac51763-c1c7-40e9-a7ec-e6acfd45cf99")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ee6405d7-ea71-4c1d-960b-16758384192f")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2830
     */
    @objid ("008d71cd-231e-4f3b-b8d2-973c809c6fdb")
    public R2830() {
        this.checkerInstance = new CheckR2830(this);
    }

    @objid ("205a15ce-e81c-4a52-b43c-f978e674b13f")
    private static class CheckR2830 extends AbstractControl {
        @objid ("c6c90ada-35d6-430c-ba91-87b0b8ac385b")
        public CheckR2830(IRule rule) {
            super(rule);
        }

        @objid ("7ac8e2af-12ec-4d35-a963-33a1b0624b7d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2830((Transition) element));
            } else if (element instanceof FinalState) {
                diagnostic.addEntries(checkR2830((FinalState) element));
            } else {
                UmlUi.LOG.warning("R2830: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c125e333-c5ca-45c3-83f3-2799a49aa491")
        private IAuditEntry checkR2830(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            StateVertex source = transition.getSource();
            
            if (source instanceof FinalState) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(transition);
                linkedObjects.add(source);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("1ccca7bd-f9a4-47d2-b565-071427485155")
        private List<IAuditEntry> checkR2830(final FinalState state) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (Transition transition : state.getOutGoing()) {
                auditEntries.add(checkR2830(transition));
            }
            return auditEntries;
        }

    }

}
