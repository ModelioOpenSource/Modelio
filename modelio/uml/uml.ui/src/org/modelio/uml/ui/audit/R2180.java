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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameSpaceChecker checkGeneralizationCycles error
 */
@objid ("3e1f22a2-1c7e-4dc2-b009-da19b3e4db95")
public class R2180 extends AbstractUmlRule {
    @objid ("d68c0409-09bb-4f7c-abb1-4e4f32ece13b")
    private static final String RULEID = "R2180";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("5522eb40-56bf-4165-b60d-c19d7b64927d")
    private CheckR2180 checkerInstance = null;

    @objid ("b81a58f6-a44c-4709-af19-f7f05529b810")
    @Override
    public String getRuleId() {
        return R2180.RULEID;
    }

    @objid ("d137e5ad-c719-4709-bab6-bed75dea69a5")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        
        // Namespaces
        plan.registerRule(Package.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.UPDATE);
        
        // Namespaces.Classifiers
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("143bb6ef-22bf-433e-b400-f9c62e951bc9")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c937ffb8-2781-4941-8080-1fe7db14be5d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("aa177d96-d760-4458-8137-dafee859df17")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2180
     */
    @objid ("d907a3da-1502-4fea-a51d-6c6c3f07ba95")
    public R2180() {
        this.checkerInstance = new CheckR2180(this);
    }

    @objid ("d288d867-ff68-49f5-a617-fd655f67df19")
    private static class CheckR2180 extends AbstractControl {
        @objid ("17c81356-dd56-4cbb-bc5e-f622df410190")
        public CheckR2180(IRule rule) {
            super(rule);
        }

        @objid ("1d286d18-42c8-4d47-b708-5f6dc0558fdc")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Generalization) {
                diagnostic.addEntry(checkR2180(((Generalization) element).getSubType()));
            } else if (element instanceof NameSpace) {
            
                // If a NameSpace was updated, potentially a Generalization was deleted and therefore cycles could have been broken.
                // In this regard, we need to check all the sub generalisation paths in case a cycle rule was triggered on a NameSpace before, and check it again.
            
                List<NameSpace> nameSpaces = findImpactedNameSpaces((NameSpace) element,
                        new ArrayList<NameSpace>());
                for (NameSpace ns : nameSpaces) {
                    diagnostic.addEntry(checkR2180(ns));
                }
            } else {
                UmlUi.LOG.warning("R2180: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * This checks a single NameSpace to find if it is part of a inheritance cycle.
         * 
         * @param nameSpace The NameSpace to check.
         * @return The audit result.
         */
        @objid ("991735fd-4743-472b-bcda-45c3f1b4ac8a")
        private IAuditEntry checkR2180(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            List<NameSpace> cycle = new ArrayList<>();
            if (findCycle(nameSpace, cycle)) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                ArrayList<Object> linkedObjects = new ArrayList<>(cycle);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("d6c456ee-40c2-456e-8cca-d6d5ad971b79")
        private List<NameSpace> findImpactedNameSpaces(final NameSpace nameSpace, final List<NameSpace> foundNameSpaces) {
            if (!foundNameSpaces.contains(nameSpace)) {
                foundNameSpaces.add(nameSpace);
                for (Generalization gen : nameSpace.getSpecialization()) {
                    findImpactedNameSpaces(gen.getSubType(), foundNameSpaces);
                }
            }
            return foundNameSpaces;
        }

        /**
         * This method search for a cycle in the super types inheritance graph.
         * 
         * @param nameSpace The NameSpace to start from.
         * @param foundNameSpaces A list of found NameSpaces to avoid cycling.
         * @return True is a cycle was found, false otherwise.
         */
        @objid ("283f5ea4-acc3-4e22-adbd-927d51e4688f")
        private boolean findCycle(final NameSpace nameSpace, final List<NameSpace> foundNameSpaces) {
            if (foundNameSpaces.contains(nameSpace)) {
                return true;
            } else {
                foundNameSpaces.add(nameSpace);
            }
            
            for (Generalization gen : nameSpace.getParent()) {
                List<NameSpace> save = new ArrayList<>(foundNameSpaces);
                if (findCycle(gen.getSuperType(), save)) {
                    foundNameSpaces.clear();
                    foundNameSpaces.addAll(save);
                    return true;
                }
            }
            return false;
        }

    }

}
