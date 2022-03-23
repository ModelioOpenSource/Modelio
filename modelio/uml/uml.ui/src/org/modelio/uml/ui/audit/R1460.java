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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkR35 error
 */
@objid ("42f63de2-0b9c-47be-948d-13779536fdd5")
public class R1460 extends AbstractUmlRule {
    @objid ("c8bad7e5-676c-4d78-8a7b-b3421207aebf")
    private static final String RULEID = "R1460";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("20630715-0c25-4a1f-a575-d5cab2872c23")
    private CheckR1460 checkerInstance = null;

    @objid ("9c66d418-81f3-48b9-adf0-9b70239919b7")
    @Override
    public String getRuleId() {
        return R1460.RULEID;
    }

    @objid ("34979ff4-0da5-4f01-ba67-18d2d4e54cd6")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        
        // IClassifier
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(org.modelio.metamodel.uml.statik.Class.MQNAME, this, AuditTrigger.UPDATE);
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
    @objid ("9201e929-4ded-4fc1-8338-38aef85f8778")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("242c40f2-3647-4db3-8360-4d12e35447cc")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a393c1f2-3a7f-450a-b6af-006b2cf426ac")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1460
     */
    @objid ("9f20581f-1500-4999-b968-523db9bcddd5")
    public  R1460() {
        this.checkerInstance = new CheckR1460(this);
    }

    @objid ("044674cb-8b74-4ca8-8a0d-563634e68e3c")
    private static class CheckR1460 extends AbstractControl {
        @objid ("4ab1d644-6af0-428e-94fa-a2d63dbcb568")
        public  CheckR1460(IRule rule) {
            super(rule);
        }

        @objid ("9d8deb24-b618-49a2-95ef-78845c7cd865")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd) {
                diagnostic.addEntry(checkR1460((AssociationEnd) element));
            } else if (element instanceof Classifier) {
                for (AssociationEnd assocEnd : ((Classifier) element).getOwnedEnd()) {
                    diagnostic.addEntry(checkR1460(assocEnd));
                    diagnostic.addEntry(checkR1460(assocEnd.getOpposite()));
                }
            } else {
                UmlUi.LOG.warning("R1460: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("d31379d6-4e75-49e3-82e4-a29798f9bbaf")
        private IAuditEntry checkR1460(final AssociationEnd assocEnd) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, assocEnd, null);
            
            if (assocEnd.getVisibility().equals(VisibilityMode.PUBLIC) && assocEnd.getSource() != null
                    && assocEnd.getSource().getVisibility().equals(VisibilityMode.PUBLIC)) {
            
                AssociationEnd oppositeEnd = assocEnd.getOpposite();
                Classifier opositeOwner = oppositeEnd.getSource() != null ? oppositeEnd.getSource() : oppositeEnd.getOpposite()
                        .getTarget();
            
                VisibilityMode visibility = opositeOwner.getVisibility();
            
                if (visibility.equals(VisibilityMode.PRIVATE) || visibility.equals(VisibilityMode.PROTECTED)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(assocEnd);
                    linkedObjects.add(assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget());
                    linkedObjects.add(oppositeEnd.getSource() != null ? oppositeEnd.getSource() : oppositeEnd.getOpposite()
                            .getTarget());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
