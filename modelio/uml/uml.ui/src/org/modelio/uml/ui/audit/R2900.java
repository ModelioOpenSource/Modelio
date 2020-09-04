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
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseDependencyChecker checkExtendsHasExtensionPoint
 */
@objid ("9c37133e-008d-4a08-94c2-3ae018ed4907")
public class R2900 extends AbstractUmlRule {
    @objid ("1fe04b75-d138-4726-8ce8-898435a8d3eb")
    private static final String RULEID = "R2900";

    @objid ("79262ca9-7b3a-4fc0-9903-1def12c75b51")
    private static final String UseCaseDependencyExtendKind = "extend";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("06bbce9e-445c-4844-a3cc-8ec2d66accd9")
    private CheckR2900 checkerInstance = null;

    @objid ("fb1bd292-ee84-41a9-9722-d2ab7a5e99c8")
    @Override
    public String getRuleId() {
        return R2900.RULEID;
    }

    @objid ("c1d48baf-021d-4d47-8e59-cac3aa44e69e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCaseDependency.MQNAME + R2900.UseCaseDependencyExtendKind, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b3ebb38c-825a-47b9-9acb-f3e48cdc0a1e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("505b7a76-2867-46e2-beef-9e1e401652f1")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0b6bbc57-6b6e-444a-9790-b590ef601abf")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2900
     */
    @objid ("63538e18-5bfb-411d-b09e-408f2fd48cfb")
    public R2900() {
        this.checkerInstance = new CheckR2900(this);
    }

    @objid ("f822fe04-88f9-423e-88ea-47ee1dc39703")
    private static class CheckR2900 extends AbstractControl {
        @objid ("f2cd50da-4801-44f0-a1b3-04f24efe1ab1")
        public CheckR2900(IRule rule) {
            super(rule);
        }

        @objid ("585fd635-6689-4c97-908c-4779b59fd43c")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCaseDependency) {
                diagnostic.addEntry(checkR2900((UseCaseDependency) element));
            } else {
                UmlUi.LOG.warning("R2900: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("4e65d14a-7232-42a3-a533-0cf48413ea2f")
        private IAuditEntry checkR2900(UseCaseDependency dependency) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, dependency, null);
            
            boolean failed = false;
            if (dependency.isStereotyped("ModelerModule", R2900.UseCaseDependencyExtendKind) && dependency.getExtensionLocation().isEmpty()) {
                // Rule failed
                failed = true;
            }
            
            if (failed) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(dependency.getOrigin());
                linkedObjects.add(dependency.getTarget());
                linkedObjects.add(dependency);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
