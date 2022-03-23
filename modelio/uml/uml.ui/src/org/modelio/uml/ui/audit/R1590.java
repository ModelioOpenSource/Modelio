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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassChecker checkNoPrimitiveClassAssociations warning
 */
@objid ("a5777f70-a032-4ab0-961e-15cc280cc36d")
public class R1590 extends AbstractUmlRule {
    @objid ("b38fc5e6-6191-47b7-86f9-e3b586ee570e")
    private static final String RULEID = "R1590";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("c569f5ea-8e9f-4363-8d1b-a8726c917f0b")
    private CheckR1590 checkerInstance = null;

    @objid ("4f23dfec-95d7-4f51-8d5e-b358c26fdcae")
    @Override
    public String getRuleId() {
        return R1590.RULEID;
    }

    @objid ("76acdb55-38ad-4836-bcba-1c7593c94ace")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // IGeneralClass
        plan.registerRule(org.modelio.metamodel.uml.statik.Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fa97715f-fc6e-4842-a34d-81f129e77220")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("aa84c281-fad0-4228-9127-ed084f229a08")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("76e4b31c-3ac2-4437-9d1a-088ad341910d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1590
     */
    @objid ("64537b38-8d15-418c-bef0-bb8462079879")
    public  R1590() {
        this.checkerInstance = new CheckR1590(this);
    }

    @objid ("1ac258e2-6afe-4f03-8b80-00f6fbe50755")
    private static class CheckR1590 extends AbstractControl {
        @objid ("19656121-b6fb-4763-b5a4-0804410a7123")
        public  CheckR1590(IRule rule) {
            super(rule);
        }

        @objid ("fe2467d9-7e88-42ba-8842-bdceeda483c9")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof GeneralClass) {
                diagnostic.addEntry(checkR1590((GeneralClass) element));
            } else if (element instanceof Association) {
                for (AssociationEnd assocEnd : ((Association) element).getEnd()) {
            
                    Classifier classifier = assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget();
            
                    if (classifier instanceof GeneralClass) {
                        diagnostic.addEntry(checkR1590((GeneralClass) classifier));
                    }
                }
            } else if (element instanceof AssociationEnd) {
                AssociationEnd assocEnd = (AssociationEnd) element;
                Classifier classifier = assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget();
                if (classifier instanceof GeneralClass) {
                    diagnostic.addEntry(checkR1590((GeneralClass) classifier));
                }
            } else {
                UmlUi.LOG.warning("R1590: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("4172c2ec-924d-4392-8780-bd0ea1db9db0")
        private IAuditEntry checkR1590(final GeneralClass clazz) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, clazz, null);
            
            if (clazz.isIsElementary() && !clazz.getOwnedEnd().isEmpty()) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(clazz);
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
