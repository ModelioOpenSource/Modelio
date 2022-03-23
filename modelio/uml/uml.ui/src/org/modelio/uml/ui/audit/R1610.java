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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassChecker checkPrimitiveStateMachine
 */
@objid ("99ae8223-d8fe-4c12-be13-732c4b7f2d85")
public class R1610 extends AbstractUmlRule {
    @objid ("c685b814-3151-484d-b5e7-5bc30dff3cbd")
    private static final String RULEID = "R1610";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("acc941ce-6ed7-4a8f-b43c-f7f5798915f5")
    private CheckR1610 checkerInstance = null;

    @objid ("6b145b1f-7c99-4767-8295-26af52d8c43e")
    @Override
    public String getRuleId() {
        return R1610.RULEID;
    }

    @objid ("330c9fac-5429-4954-8af0-43659dbd13a8")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Class.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d0c24fde-6e47-4a19-aed8-ed43d3c39cf4")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("86a0e895-6923-427a-9021-7c3f3b650e1b")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e41bdbd4-eda3-4827-9cc6-f9a657d0c4b3")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1610
     */
    @objid ("791d0c76-832f-4977-97dd-516751729203")
    public  R1610() {
        this.checkerInstance = new CheckR1610(this);
    }

    @objid ("51b1908b-0f13-4c6f-a97e-c1cc8118ae66")
    private static class CheckR1610 extends AbstractControl {
        @objid ("99e60ba0-5655-46ab-aebc-8f0176f7bf2d")
        public  CheckR1610(IRule rule) {
            super(rule);
        }

        @objid ("ab904de7-b5a4-407d-b60e-a0ab00ed1f21")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Class) {
                diagnostic.addEntry(checkR1610((Class) element));
            } else if (element instanceof StateMachine) {
                StateMachine stateMAchine = (StateMachine) element;
                MObject owner = stateMAchine.getCompositionOwner();
                if (owner instanceof Class) {
                    diagnostic.addEntry(checkR1610((Class) owner));
                }
            } else {
                UmlUi.LOG.warning("R1610: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("744f49aa-85c9-41d2-9e3c-ea455499dc7f")
        private IAuditEntry checkR1610(final Class clazz) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    clazz,
                    null);
            
            if (clazz.isIsElementary()) {
                if (!clazz.getOwnedBehavior(StateMachine.class).isEmpty()) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(clazz);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
