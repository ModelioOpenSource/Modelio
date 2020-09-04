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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameSpaceChecker checkGeneralizationUse error
 */
@objid ("8c0a26db-961f-43e7-81e1-ea783541cf30")
public class R2200 extends AbstractUmlRule {
    @objid ("dc9b2cd0-1e3a-46cb-88d0-f74ab33c39b2")
    private static final String RULEID = "R2200";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8fa1340c-d688-418d-b7f8-ab5d1e1522d3")
    private CheckR2200 checkerInstance = null;

    @objid ("2c7609ff-bc52-4b6b-99e2-ac00d5a9ee81")
    @Override
    public String getRuleId() {
        return R2200.RULEID;
    }

    @objid ("c3befcaa-e155-4c6b-a6ae-c8bff9f3eccc")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
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
        
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(ElementImport.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e11d5d87-369c-4725-b937-daff51b0a406")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3a28c9a3-d902-4465-9ac4-35f646fa1ade")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("57eeef09-c7ed-4b4b-90b5-7586b44db095")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2200
     */
    @objid ("8f36db19-48aa-4436-b1fc-cb9cc9ddc660")
    public R2200() {
        this.checkerInstance = new CheckR2200(this);
    }

    @objid ("f394b0ca-84ad-4e79-944d-734a0560843a")
    private static class CheckR2200 extends AbstractControl {
        @objid ("2a03e052-e838-4985-abff-c0ecb15f7d73")
        public CheckR2200(IRule rule) {
            super(rule);
        }

        @objid ("efb5c67e-e9c7-478d-9753-68e79a1d1572")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                diagnostic.addEntry(checkR2200((NameSpace) element));
            } else if (element instanceof Generalization) {
                diagnostic.addEntry(checkR2200(((Generalization) element).getSubType()));
            } else if (element instanceof ElementImport) {
                NameSpace ns = ((ElementImport) element).getImportingNameSpace();
                if (ns != null) {
                    diagnostic.addEntry(checkR2200(ns));
                }
            }
            return diagnostic;
        }

        @objid ("250ec573-6312-464e-89c1-94fa2d02ba3e")
        private IAuditEntry checkR2200(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            for (Generalization gen : nameSpace.getParent()) {
                NameSpace superNS = gen.getSuperType();
                for (ElementImport ei : superNS.getImporting()) {
                    NameSpace ns = ei.getImportingNameSpace();
                    if (ns != null && ns.equals(nameSpace)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(nameSpace);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

    }

}
