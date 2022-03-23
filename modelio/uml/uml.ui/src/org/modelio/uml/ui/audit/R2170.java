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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
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
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameSpaceChecker checkBehaviorName
 */
@objid ("a96b4e0a-3b0b-42d1-a011-5b5a16573947")
public class R2170 extends AbstractUmlRule {
    @objid ("5856f938-9fce-4602-bc44-0d9263742276")
    private static final String RULEID = "R2170";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8fe1932f-13dd-4aa1-a7ab-4a4e183e2863")
    private CheckR2170 checkerInstance = null;

    @objid ("624fbdfc-83ad-4dcc-999e-4da3a580ff3f")
    @Override
    public String getRuleId() {
        return R2170.RULEID;
    }

    @objid ("f5a2b0e3-3347-46de-aba6-e3610d1967c2")
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
        
        // Behavior
        plan.registerRule(Activity.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.MOVE |
                AuditTrigger.CREATE);
        plan.registerRule(CommunicationInteraction.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.MOVE |
                AuditTrigger.CREATE);
        plan.registerRule(Interaction.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.MOVE |
                AuditTrigger.CREATE);
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.MOVE |
                AuditTrigger.CREATE);
        plan.registerRule(OpaqueBehavior.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.MOVE |
                AuditTrigger.CREATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5123253f-7d32-4cd7-a6c3-49b57e3360ee")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fabaf562-e244-4a07-b634-e930613d5b90")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f8244200-1217-426b-81c5-70fb5075edf6")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2170
     */
    @objid ("8c05e1c6-f704-4e3c-977d-a4e886fd9863")
    public  R2170() {
        this.checkerInstance = new CheckR2170(this);
    }

    @objid ("e27780bb-0eca-41a6-8bbe-6fc3d36478f2")
    private static class CheckR2170 extends AbstractControl {
        @objid ("55b4e3a6-67e0-4229-af9c-d52340572976")
        public  CheckR2170(IRule rule) {
            super(rule);
        }

        @objid ("e54abb3b-1551-4590-9b40-7baf1d333d7a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                // A NameSpace was potentially updated after an element moved in, so we need to check the rule on this NameSpace.
                diagnostic.addEntry(checkR2170((NameSpace) element));
            } else if (element instanceof Behavior) {
                // An Instance element was updated (potentially its name).
                // If it's owned by another NameSpace, we need to check the rule is still valid on this NameSpace.
                MObject owner = element.getCompositionOwner();
                if (owner instanceof NameSpace) {
                    diagnostic.addEntry(checkR2170((NameSpace) owner));
                }
            } else {
                UmlUi.LOG.warning("R1780: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3551dc03-c9c2-448e-8bd9-2a05986c1570")
        private IAuditEntry checkR2170(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            Map<String, List<Behavior>> duplicates = new HashMap<>();
            
            for (Behavior be : nameSpace.getOwnedBehavior()) {
                String name = be.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<Behavior>());
                }
                duplicates.get(be.getName()).add(be);
            }
            
            for (Entry<String, List<Behavior>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(nameSpace);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
