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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkInitialState
 */
@objid ("f858e354-a974-4b5c-9357-759801f58e96")
public class R2590 extends AbstractUmlRule {
    @objid ("d1bef651-5822-40d9-b86b-bcc40fb145da")
    private static final String RULEID = "R2590";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("bf92fc2f-1633-4105-904a-5322596892b0")
    private CheckR2590 checkerInstance = null;

    @objid ("8c6bfb76-88f7-42e4-8268-0ddb9ca79dca")
    @Override
    public String getRuleId() {
        return R2590.RULEID;
    }

    @objid ("7c6dabfa-f3b3-44b4-8e19-9a23fde61f86")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Region.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InitialPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9b8a57ae-eb2b-44b8-93df-5e17f9fb994a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f8fe81f8-32ce-468d-af99-f2eff02e75f3")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("009dcf97-5f03-42c1-b58a-977aabd75b1e")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2590
     */
    @objid ("833875e1-b5a0-40e6-8229-18c069314879")
    public R2590() {
        this.checkerInstance = new CheckR2590(this);
    }

    @objid ("23c21227-96c8-40f2-bfec-57aaa39133f7")
    private static class CheckR2590 extends AbstractControl {
        @objid ("1baa3241-692f-4019-8662-61e32e557978")
        public CheckR2590(IRule rule) {
            super(rule);
        }

        @objid ("0b3ec9ac-3456-4f74-a0d2-0939d837147e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InitialPseudoState) {
                InitialPseudoState initialState = (InitialPseudoState) element;
                Region region = initialState.getParent();
                if (region != null) {
                    diagnostic.addEntry(checkR2590(region));
                }
            } else if (element instanceof Region) {
                diagnostic.addEntry(checkR2590((Region) element));
            } else {
                UmlUi.LOG.warning("R2590: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("6d86b30f-4c54-4499-ada2-52a7fa62d28c")
        private IAuditEntry checkR2590(Region region) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, region, null);
            
            List<InitialPseudoState> foundInistailStates = new ArrayList<>();
            
            for (InitialPseudoState foundInitialState : region.getSub(InitialPseudoState.class)) {
                foundInistailStates.add(foundInitialState);
            }
            
            if (foundInistailStates.size() > 1) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(region);
                linkedObjects.addAll(foundInistailStates);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
