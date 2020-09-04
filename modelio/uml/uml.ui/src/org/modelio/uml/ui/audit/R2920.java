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
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseDependencyChecker checkIncludeHasNoExtensionPoint
 */
@objid ("529a90c0-5b45-480f-b29a-12496d73f343")
public class R2920 extends AbstractUmlRule {
    @objid ("0626cf79-bebe-40e9-abba-c25ccb7c1663")
    private static final String RULEID = "R2920";

    @objid ("70b75054-4fbd-4262-b587-50db31df4cbf")
    private static final String UseCaseDependencyExtendKind = "extend";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("54a19408-8d58-4a9f-89be-471012fbcc24")
    private CheckR2920 checkerInstance = null;

    @objid ("de9fc677-6535-4689-9d75-62ec0b835105")
    @Override
    public String getRuleId() {
        return R2920.RULEID;
    }

    @objid ("5bce90dc-e2f5-4d6e-952a-5b1f7f04752d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCaseDependency.MQNAME + R2920.UseCaseDependencyExtendKind, this,
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("be6069de-ba77-4564-a78f-f7a69b507f8e")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9c383bb5-9da7-4052-ba73-e98cff343f99")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("02582a8a-09ac-4bf7-ab63-6d0e122d19c4")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2920
     */
    @objid ("f5b3c5fd-87f2-4576-8c0f-0788fc0506bc")
    public R2920() {
        this.checkerInstance = new CheckR2920(this);
    }

    @objid ("8a6cf577-ea5f-4364-a67c-9c817f3806d5")
    private static class CheckR2920 extends AbstractControl {
        @objid ("c3112333-c294-4893-82b8-dd2033ed10ad")
        public CheckR2920(IRule rule) {
            super(rule);
        }

        @objid ("daf00ba3-f3bf-4eab-af09-5335ffbdab43")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCaseDependency) {
                diagnostic.addEntry(checkR2920((UseCaseDependency) element));
            } else {
                UmlUi.LOG.warning("R2920: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("10c02fcd-a271-4fdb-b7be-ea94d84f63ca")
        private IAuditEntry checkR2920(UseCaseDependency dependency) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, dependency, null);
            
            List<ExtensionPoint> extensionPoints = dependency.getExtensionLocation();
            
            if (extensionPoints.isEmpty()) {
                return auditEntry;
            }
            
            boolean failed = false;
            if (!dependency.isStereotyped("ModelerModule", R2920.UseCaseDependencyExtendKind)) {
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
