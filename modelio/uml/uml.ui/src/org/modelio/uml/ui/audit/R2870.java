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
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseChecker checkExtendCycles error
 */
@objid ("6566f5d9-2a83-4029-a911-d2661a463589")
public class R2870 extends AbstractUmlRule {
    @objid ("a4343b48-1baf-4627-a2b0-e469ed57868a")
    private static final String RULEID = "R2870";

    @objid ("e51f0df7-aade-4bb4-bcf2-be4ee16bf949")
    private static final String UseCaseDependencyExtendKind = "extend";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f3f82533-4ac3-495e-adc7-af3fec8c3529")
    private CheckR2870 checkerInstance = null;

    @objid ("854f65c4-72dd-4b7b-b075-a8ff062efa05")
    @Override
    public String getRuleId() {
        return R2870.RULEID;
    }

    @objid ("912c2a8c-4736-4cb6-bdce-20f7e03fa00f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCaseDependency.MQNAME + R2870.UseCaseDependencyExtendKind, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7f0803af-23eb-4329-a64b-774dc600826a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c9d08438-bda4-46b1-a22b-a549d0584ef4")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1fb67a88-5686-42d4-b22a-0f55cfb37017")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2870
     */
    @objid ("4c152130-01c4-4f0b-aba6-49cbf39b6573")
    public  R2870() {
        this.checkerInstance = new CheckR2870(this);
    }

    @objid ("dbe8bb9d-d4a2-4647-b674-335394ee40e1")
    private static class CheckR2870 extends AbstractControl {
        @objid ("1b7f6cc6-e5d7-4173-828a-092f381ba0f7")
        public  CheckR2870(IRule rule) {
            super(rule);
        }

        @objid ("b107802d-15a5-45ac-bf84-078a76bdf032")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCase) {
                diagnostic.addEntry(checkR2870((UseCase) element));
            } else if (element instanceof UseCaseDependency) {
                UseCaseDependency dependency = (UseCaseDependency) element;
                if (dependency.isStereotyped("ModelerModule", R2870.UseCaseDependencyExtendKind)) {
                    diagnostic.addEntry(checkR2870(dependency.getTarget()));
                }
            } else {
                UmlUi.LOG.warning("R2870: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("ecac86fe-6473-41cb-b8d3-c737ec37a2fd")
        private IAuditEntry checkR2870(UseCase useCase) {
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

        @objid ("50ec9d33-3e14-46f6-8c78-a3dd651ec656")
        private boolean checkCycle(UseCase useCase, List<UseCase> foundUseCases) {
            if (foundUseCases.contains(useCase)) {
                return false;
            } else {
                foundUseCases.add(useCase);
            }
            
            for (UseCaseDependency dep : useCase.getUsed()) {
                if (dep.isStereotyped("ModelerModule", R2870.UseCaseDependencyExtendKind)) {
                    if (!checkCycle(dep.getTarget(), foundUseCases)) {
                        return false;
                    }
                }
            }
            return true;
        }

    }

}
