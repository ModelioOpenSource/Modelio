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
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseChecker checkDependencyUnicity
 */
@objid ("dd1db6fc-4416-473b-b3b7-67a094f36bcd")
public class R2860 extends AbstractUmlRule {
    @objid ("da42ef47-063f-40c3-a09c-2c0bf5187a6a")
    private static final String RULEID = "R2860";

    @objid ("14151850-ff9e-46eb-9e56-cef7596f6d80")
    private static final String UseCaseDependencyIncludeKind = "include";

    @objid ("0a86fae2-df18-462d-84a5-6f360c711e0d")
    private static final String UseCaseDependencyExtendKind = "extend";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("89f8f166-2132-425a-b3cc-1288ba104e59")
    private CheckR2860 checkerInstance = null;

    @objid ("43b1d433-5fa3-4233-98dc-86176bc372bf")
    @Override
    public String getRuleId() {
        return R2860.RULEID;
    }

    @objid ("c37b488c-62de-4cc9-b1bf-c6fc11c33450")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCaseDependency.MQNAME + R2860.UseCaseDependencyIncludeKind, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(UseCaseDependency.MQNAME + R2860.UseCaseDependencyExtendKind, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("50564c8c-15e7-4bd6-bff8-e061549f2f30")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1d7b68cf-cac0-4bcb-a051-5a032d7c57f5")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("aa633606-d4cf-47b1-a1d4-fb6cb8bcf339")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2860
     */
    @objid ("181f7f74-14b5-4445-b11b-cb55eba3c301")
    public R2860() {
        this.checkerInstance = new CheckR2860(this);
    }

    @objid ("840e604b-7c35-4b3b-b291-0342c74d84be")
    private static class CheckR2860 extends AbstractControl {
        @objid ("870c05eb-dd4e-4c9d-a5ae-94cc149dd7e9")
        public CheckR2860(IRule rule) {
            super(rule);
        }

        @objid ("c8044fa6-7468-48a9-94fc-c304da4e465a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCase) {
                diagnostic.addEntry(checkR2860((UseCase) element));
            } else if (element instanceof UseCaseDependency) {
                diagnostic.addEntries(checkR2860((UseCaseDependency) element));
            }
            return diagnostic;
        }

        @objid ("9a97d8a5-f56d-4449-9591-89808500b95a")
        private IAuditEntry checkR2860(UseCase useCase) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, useCase, null);
            
            List<UseCaseDependency> targets = useCase.getUsed();
            
            List<UseCase> useCases = new ArrayList<>();
            List<UseCase> duplicateUseCases = new ArrayList<>();
            
            for (UseCaseDependency dependency : targets) {
                UseCase uc = dependency.getTarget();
                if (useCases.contains(uc)) {
                    duplicateUseCases.add(uc);
                } else {
                    useCases.add(uc);
                }
            }
            
            if (!duplicateUseCases.isEmpty()) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(useCase);
                linkedObjects.addAll(duplicateUseCases);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("4ab24526-a310-4958-a49e-a47656212067")
        private List<IAuditEntry> checkR2860(UseCaseDependency dependency) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            auditEntries.add(checkR2860(dependency.getOrigin()));
            return auditEntries;
        }

    }

}
