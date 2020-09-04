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
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR140
 */
@objid ("5b412514-29f2-4e9e-ba49-6757dd0ae7f1")
public class R2390 extends AbstractUmlRule {
    @objid ("9f9679a5-4503-47f2-b743-ddf3d81ab227")
    private static final String RULEID = "R2390";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("117484da-1894-4337-8159-35d8650320ba")
    private CheckR2390 checkerInstance = null;

    @objid ("983bbbac-f991-4312-b876-c810caa9be8c")
    @Override
    public String getRuleId() {
        return R2390.RULEID;
    }

    @objid ("fe5f5443-a0a6-4af6-b33b-4541b7e9d5f0")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This cover the case of moving or deleting a Parameter, which triggers an UPDATE on the old parent.
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
        // This cover the case of [creating|moving] a Parameter [under|to] a new parent.
        // It also covers the case of renaming a Parameter.
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5a2541e9-081f-40b0-a589-e33711f58496")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("35e62d2e-53cc-4d1a-9adf-dbec4dc6a8ca")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("69c66f20-b8dc-4d30-a43a-42521d87c16a")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2390
     */
    @objid ("3aef88a6-81b9-4ba2-bd78-de6fbae2d7ca")
    public R2390() {
        this.checkerInstance = new CheckR2390(this);
    }

    @objid ("5244c8c0-d91b-417d-ac17-5d198c8fd6d4")
    private static class CheckR2390 extends AbstractControl {
        @objid ("04ebfa05-9199-4d8b-94d3-a387bb8b7afb")
        public CheckR2390(IRule rule) {
            super(rule);
        }

        @objid ("5a105974-c060-46ae-9ebc-e9a998975b64")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2390((Operation) element));
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                MObject owner = parameter.getCompositionOwner();
                if (owner instanceof Operation) {
                    diagnostic.addEntry(checkR2390((Operation) owner));
                }
            } else {
                UmlUi.LOG.warning("R2390: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("38482d64-193c-43ac-856b-a7fb4378cbaf")
        private IAuditEntry checkR2390(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            boolean failed = false;
            if (operation.isStereotyped("ModelerModule", "create") && operation.getReturn() != null) {
                // Rule failed
                failed = true;
            }
            
            if (failed) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
