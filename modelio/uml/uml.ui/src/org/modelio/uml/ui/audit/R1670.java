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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EnumerationChecker checkNameUnicity
 */
@objid ("47ecdece-149e-47c0-b544-a4f663502c0f")
public class R1670 extends AbstractUmlRule {
    @objid ("ada360fa-13f2-4290-b436-7e37e48c80a0")
    private static final String RULEID = "R1670";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("e4843bb9-b865-43e8-8938-f5b85b3b372c")
    private CheckR1670 checkerInstance = null;

    @objid ("09fca4f1-6bdd-4e6e-99d8-5b9d6779b64b")
    @Override
    public String getRuleId() {
        return R1670.RULEID;
    }

    @objid ("b30f53dc-96f7-4a88-9914-a0095cbeafd6")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(EnumerationLiteral.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0c74b35c-4d6d-499c-b0dc-9e146d984f98")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f0dfd589-6d90-4c8b-8012-cac1c6d30902")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("166bf226-c624-44b1-a1b1-8b472f85d283")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1670
     */
    @objid ("ab28944a-71c9-4cde-9cbd-c4f00350a768")
    public R1670() {
        this.checkerInstance = new CheckR1670(this);
    }

    @objid ("9525dbd5-49e9-43c5-8381-4047c0a9c4ba")
    private static class CheckR1670 extends AbstractControl {
        @objid ("28e4b4bb-36ff-4892-9a15-42eef652f9e5")
        public CheckR1670(IRule rule) {
            super(rule);
        }

        @objid ("bc4bf203-d484-4428-9f4c-58b73666243e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Enumeration) {
                diagnostic.addEntry(checkR1670((Enumeration) element));
            } else if (element instanceof EnumerationLiteral) {
                diagnostic.addEntry(checkR1670(((EnumerationLiteral) element).getValuated()));
            } else {
                UmlUi.LOG.warning("R1670: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f440f2e2-6431-4d6a-aeb9-2bfb8843f13f")
        private IAuditEntry checkR1670(final Enumeration enumeration) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    enumeration,
                    null);
            
            List<String> enumLitNames = new ArrayList<>();
            
            for (EnumerationLiteral enumLit : enumeration.getValue()) {
                String enumLitName = enumLit.getName();
                if (enumLitNames.contains(enumLitName)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(enumeration);
                    linkedObjects.add(enumLitName);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    enumLitNames.add(enumLitName);
                }
            }
            return auditEntry;
        }

    }

}
