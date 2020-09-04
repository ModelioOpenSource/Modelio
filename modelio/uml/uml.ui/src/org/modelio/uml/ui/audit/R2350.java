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
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR135
 */
@objid ("34b8e3c2-ec96-4f03-bbd6-6a8ab3494b99")
public class R2350 extends AbstractUmlRule {
    @objid ("1731122e-68ed-4e84-80b7-c56759b09569")
    private static final String RULEID = "R2350";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("682c4356-a9d3-4403-a4a5-cb4e0965137d")
    private CheckR2350 checkerInstance = null;

    @objid ("3fd848de-6de2-410b-9641-b685fad59db0")
    @Override
    public String getRuleId() {
        return R2350.RULEID;
    }

    @objid ("57aeef00-65ea-48ab-9a1f-90c76a150fad")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fb704f4d-4bab-49bf-9809-a55f62c24e09")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("99008838-fdff-454f-ac29-26507bdd7209")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("49dba983-088b-449f-aed8-e46e39825588")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2350
     */
    @objid ("58340c49-0db9-457a-98e6-42de72094a24")
    public R2350() {
        this.checkerInstance = new CheckR2350(this);
    }

    @objid ("a2e00828-af48-476e-8cc9-c8c18fa0a003")
    private static class CheckR2350 extends AbstractControl {
        @objid ("1ba8aa89-a659-41ed-8012-97e8bff74a49")
        public CheckR2350(IRule rule) {
            super(rule);
        }

        @objid ("5ae9db45-53d6-4f2d-8d6d-ca1530e5784e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                // An Operation was modified
                // We need to check it, but also any operation that was redefined by it
                Operation operation = (Operation) element;
                diagnostic.addEntry(checkR2350(operation));
                Operation redefined = operation.getRedefines();
                if (redefined != null) {
                    diagnostic.addEntry(checkR2350(redefined));
                }
            } else {
                UmlUi.LOG.warning("R2350: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("fddfb04c-f6db-4008-b3be-b3c842e86afc")
        private IAuditEntry checkR2350(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            if (operation.getVisibility().equals(VisibilityMode.PRIVATE) && !operation.getRedefinition().isEmpty()) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
