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
 * Rule implementation origin: OperationChecker checkParameterNames
 */
@objid ("46c169b4-131f-4943-8557-a3db7195bd94")
public class R2330 extends AbstractUmlRule {
    @objid ("6b98fd81-77b4-424f-b394-34838030bd47")
    private static final String RULEID = "R2330";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f00011bc-07d3-418e-b788-03c3ffa5b776")
    private CheckR2330 checkerInstance = null;

    @objid ("da4bd948-80c9-4cdb-b3b1-cb1c3f805435")
    @Override
    public String getRuleId() {
        return R2330.RULEID;
    }

    @objid ("524d5e2f-ae40-4148-b733-4270fd46e3a7")
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
    @objid ("06d11ff2-1e26-4099-80d2-8fe3bedd9793")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f5ead652-d052-4fb7-bbde-6c30fbf4adde")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ada72e86-eb4c-4c7d-9ab5-cafa6d30fe37")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2330
     */
    @objid ("64c775af-934a-43a0-a83f-76a01128d215")
    public R2330() {
        this.checkerInstance = new CheckR2330(this);
    }

    @objid ("2d0fa287-5001-472e-a865-5740728e865a")
    private static class CheckR2330 extends AbstractControl {
        @objid ("700588ab-fc86-4e0f-9361-794869ed9f72")
        public CheckR2330(IRule rule) {
            super(rule);
        }

        @objid ("0997ba2c-5098-452c-af90-7af00c5b50c7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2330((Operation) element));
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                MObject owner = parameter.getCompositionOwner();
                if (owner instanceof Operation) {
                    diagnostic.addEntry(checkR2330((Operation) owner));
                }
            } else {
                UmlUi.LOG.warning("R2330: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("9685e561-2c29-4439-8367-17240e7c75a3")
        private IAuditEntry checkR2330(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            if (operation.getIO().size() > 1) {
            
                List<String> paremeterNames = new ArrayList<>();
            
                for (Parameter parameter : operation.getIO()) {
                    String name = parameter.getName();
            
                    if (paremeterNames.contains(name)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(operation);
                        linkedObjects.add(name);
                        auditEntry.setLinkedInfos(linkedObjects);
                    } else {
                        paremeterNames.add(name);
                    }
                }
            }
            return auditEntry;
        }

    }

}
