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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AttributeLinkChecker checkName
 */
@objid ("fd19546b-23f9-4dd0-8f69-70fa5d7a48f8")
public class R1500 extends AbstractUmlRule {
    @objid ("ad44e501-d3c2-4687-8566-6e95ff714c78")
    private static final String RULEID = "R1500";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("b4e9a0c9-f70a-43ad-964a-3927ae9a20c8")
    private CheckR1500 checkerInstance = null;

    @objid ("f60c83d9-d027-496d-933e-e95e7513871c")
    @Override
    public String getRuleId() {
        return R1500.RULEID;
    }

    @objid ("2cd435b4-002b-47dc-b3b4-a75d0e630bfa")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(AttributeLink.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4165cdd6-bce0-47dc-8804-151e9e458b5e")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0b4923c3-bce7-49cd-9c38-913fad650718")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c1b6b528-5953-4173-a227-03408b91c4e9")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1500
     */
    @objid ("ea36caf6-a8f4-42b5-afcb-20d0a6aac9f9")
    public  R1500() {
        this.checkerInstance = new CheckR1500(this);
    }

    @objid ("d8c22001-bbc4-44a2-a62f-d0578652b262")
    private static class CheckR1500 extends AbstractControl {
        @objid ("4d4e3d16-de78-4745-86e1-cd06d242f310")
        public  CheckR1500(IRule rule) {
            super(rule);
        }

        @objid ("706d68aa-2d36-4274-a2d8-14b36cb13719")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AttributeLink) {
                diagnostic.addEntry(checkR1500((AttributeLink) element));
            } else if (element instanceof Attribute) {
                for (AttributeLink attLink : ((Attribute) element).getOccurence()) {
                    diagnostic.addEntry(checkR1500(attLink));
                }
            } else {
                UmlUi.LOG.warning("R1500: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("2b4f090e-e3fd-4ce2-9ac5-6bd934844394")
        private IAuditEntry checkR1500(final AttributeLink attLink) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    attLink,
                    null);
            
            Attribute att = attLink.getBase();
            
            if (att != null) {
            
                String attLinkName = attLink.getName();
                String attName = att.getName();
            
                if (!attLinkName.equals(attName)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(attLink);
                    linkedObjects.add(att);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
