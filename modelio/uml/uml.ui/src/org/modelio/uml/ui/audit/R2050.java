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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkHasName
 */
@objid ("4d526c94-0f71-4c36-b99b-fd7a5d73969e")
public class R2050 extends AbstractUmlRule {
    @objid ("6bd4e262-388b-4ba7-a639-c19e733ab2a6")
    private static final String RULEID = "R2050";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("d87c8c5f-7956-4add-9f46-a05d7eabca4e")
    private CheckR2050 checkerInstance = null;

    @objid ("4886a87f-16c2-4147-91c7-e70857c9bba5")
    @Override
    public String getRuleId() {
        return R2050.RULEID;
    }

    @objid ("98abcefd-434d-4652-84c2-1862f25b7745")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Artifact.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Attribute.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(AttributeLink.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(org.modelio.metamodel.uml.statik.Class.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(CollaborationUse.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(EnumerationLiteral.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(EnumeratedPropertyType.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Event.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Link.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Node.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(NoteType.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Operation.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(org.modelio.metamodel.uml.statik.Package.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Parameter.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Project.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyDefinition.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyEnumerationLitteral.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyTableDefinition.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(PropertyType.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(StateMachine.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Stereotype.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TagType.MNAME, this, AuditTrigger.UPDATE);
        
        // Diagram.Behavior
        plan.registerRule(ActivityDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(BpmnProcessCollaborationDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(BpmnSubProcessDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(CommunicationDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(SequenceDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(StateMachineDiagram.MNAME, this, AuditTrigger.UPDATE);
        
        // Diagram.Static
        plan.registerRule(ClassDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DeploymentDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ObjectDiagram.MNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCaseDiagram.MNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("aa093b53-ac5a-44e5-bcec-a29baaf43724")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7b6feccb-99b6-4eab-a8e7-7edc2c7221ae")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d66dcd34-033e-4552-85ef-f2e13cc07e7c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2050
     */
    @objid ("5984f1e3-b743-4be8-a913-890a77cd8443")
    public R2050() {
        this.checkerInstance = new CheckR2050(this);
    }

    @objid ("c10a5bba-6986-474d-bb36-b758d7e82916")
    static class CheckR2050 extends AbstractControl {
        @objid ("573bf6bf-04a3-4670-b2c0-a48961a5e03a")
        public CheckR2050(IRule rule) {
            super(rule);
        }

        @objid ("415fb997-6eda-44c5-be3a-b87a1a7ef3b7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            diagnostic.addEntry(checkR2050(element));
            return diagnostic;
        }

        @objid ("a0b5dcab-04b0-4977-91b4-d5e9b5f1f671")
        private IAuditEntry checkR2050(MObject element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    element,
                    null);
            
            // Case to ignore return parameters which don't have a name.
            if (element instanceof Parameter && ((Parameter) element).getReturned() != null) {
                return auditEntry;
            }
            
            if (element.getName().equals("")) {
                auditEntry.setSeverity(this.rule.getSeverity());
                ArrayList<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(element.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
