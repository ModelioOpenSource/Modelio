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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR139
 */
@objid ("9a39bc16-e0fe-4005-8f86-b760de2a1e43")
public class R2380 extends AbstractUmlRule {
    @objid ("58f24854-edb0-4fb5-b0b5-1113063214c5")
    private static final String RULEID = "R2380";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("fbe5f955-ef7a-4b45-ac96-6daef6460ca6")
    private CheckR2380 checkerInstance = null;

    @objid ("e4d5e988-10e6-4770-a85d-901e4bac7112")
    @Override
    public String getRuleId() {
        return R2380.RULEID;
    }

    @objid ("0f69b65b-027d-4e37-8809-a844734bd26c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("99e028d8-e063-40a2-980b-1fa0d21153c8")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("219e9b5b-dfcb-4697-9a38-9bba6d17de85")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6d90c05d-44ad-48a3-9555-4931fac62851")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2380
     */
    @objid ("cb78f44b-923f-4129-b676-157c8e6e5ef6")
    public R2380() {
        this.checkerInstance = new CheckR2380(this);
    }

    @objid ("c7a3aea8-a375-4a52-bdfa-0b08d9ee526a")
    private static class CheckR2380 extends AbstractControl {
        @objid ("ef7c2570-0a5f-486c-a634-44a199377752")
        public CheckR2380(IRule rule) {
            super(rule);
        }

        @objid ("65087d4d-06a9-4334-9c77-e968c63c27ac")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2380((Operation) element));
            }
            return diagnostic;
        }

        @objid ("d91f600a-91ce-4f08-a585-8eb91731ab88")
        private IAuditEntry checkR2380(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, operation, null);
            
            if (operation.isIsAbstract()) {
            
                Operation redefined = operation.getRedefines();
            
                if (redefined != null && !redefined.isIsAbstract()) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(operation);
                    linkedObjects.add(redefined);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
