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
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseChecker checkIncludeCycles error
 */
@objid ("7a22e542-7dcc-4c21-9169-295f82620fe3")
public class R2880 extends AbstractUmlRule {
    @objid ("659e8643-b821-4389-85d3-328744c35c83")
    private static final String RULEID = "R2880";

    @objid ("7a0e11bc-c973-4296-a1d6-6f01a93b5fe1")
    private static final String UseCaseDependencyIncludeKind = "include";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("61ba162c-348c-4de5-a4d9-338e33724d6b")
    private CheckR2880 checkerInstance = null;

    @objid ("dbcb3d6e-6d65-4cdc-8e51-7b1e1190d18b")
    @Override
    public String getRuleId() {
        return R2880.RULEID;
    }

    @objid ("b7e03820-e635-4b3a-9a21-16af921ad4db")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCaseDependency.MQNAME + R2880.UseCaseDependencyIncludeKind, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7ff25d12-3f22-4e6d-8307-2ce76ccf8a81")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("daefa069-0132-4413-b245-2594e3219480")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("60a51fea-756b-4423-b37e-f33ef24f0f28")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2880
     */
    @objid ("a8fad3c2-2ebb-4a30-ac80-f2acb1ec683e")
    public R2880() {
        this.checkerInstance = new CheckR2880(this);
    }

    @objid ("64cf018d-8ad7-4136-a270-e651207cb073")
    private static class CheckR2880 extends AbstractControl {
        @objid ("ffcbc8e3-7eb2-4c0a-91c8-428f8f6e37fe")
        public CheckR2880(IRule rule) {
            super(rule);
        }

        @objid ("0815a343-8b8b-4def-9911-3bf5c0b402b2")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCase) {
                diagnostic.addEntry(checkR2880((UseCase) element));
            } else if (element instanceof UseCaseDependency) {
                UseCaseDependency dependency = (UseCaseDependency) element;
                if (dependency.isStereotyped("ModelerModule", R2880.UseCaseDependencyIncludeKind)) {
                    diagnostic.addEntry(checkR2880(dependency.getTarget()));
                }
            } else {
                UmlUi.LOG.warning("R2880: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("94822135-5a9f-4580-9d73-22998f06059a")
        private IAuditEntry checkR2880(UseCase useCase) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, useCase, null);
            
            if (!checkCycle(useCase, new ArrayList<UseCase>())) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(useCase);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("0206704d-ecca-4c33-b3d3-17482adb9349")
        private boolean checkCycle(UseCase useCase, List<UseCase> foundUseCases) {
            if (foundUseCases.contains(useCase)) {
                return false;
            } else {
                foundUseCases.add(useCase);
            }
            
            List<UseCase> targets = new ArrayList<>();
            for (UseCaseDependency dep : useCase.getUsed()) {
                if (dep.isStereotyped("ModelerModule", R2880.UseCaseDependencyIncludeKind)) {
                    UseCase depTarget = dep.getTarget();
                    if (!targets.contains(depTarget)) {
                        targets.add(depTarget);
                    }
                }
            }
            for (UseCase target : targets) {
                if (!checkCycle(target, foundUseCases)) {
                    return false;
                }
            }
            return true;
        }

    }

}
