/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InformationFlowChecker checkConveysInformation warning
 */
@objid ("a1ca05b7-8092-4923-a2bc-ba6f676b6b55")
public class R1740 extends AbstractUmlRule {
    @objid ("16139a53-2b9e-4213-ab86-efb674c74507")
    private static final String RULEID = "R1740";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("67ef0da0-4369-4676-bde7-f028fe98d2c5")
    private CheckR1740 checkerInstance = null;

    @objid ("ce2c0ef8-d21a-4c40-b788-9e14f67709eb")
    @Override
    public String getRuleId() {
        return R1740.RULEID;
    }

    @objid ("682f329e-9fe6-4550-964e-22c9408347bb")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InformationFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b25dad0e-62b3-4b74-8ca1-275e353bf3c5")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6911ef9a-70b8-4abb-8f5f-8cd99d11b10e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("11f87dc0-58f9-440d-8b1d-580579d2be4f")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1740
     */
    @objid ("db67d397-a913-400c-a1c2-882dca16cee1")
    public R1740() {
        this.checkerInstance = new CheckR1740(this);
    }

    @objid ("1f13ab69-00a3-4d3e-b707-feaa24fbc5d3")
    private static class CheckR1740 extends AbstractControl {
        @objid ("7bfc071b-3a0b-4703-b13e-ceb4b28b7df9")
        public CheckR1740(IRule rule) {
            super(rule);
        }

        @objid ("689b913e-e195-4701-b5ef-448ce99e0c29")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InformationFlow) {
                diagnostic.addEntry(checkR1740((InformationFlow) element));
            } else {
                UmlUi.LOG.warning("R1740: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("83bae946-8d0c-4f97-b778-1ca3396dd052")
        private IAuditEntry checkR1740(final InformationFlow infoFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, infoFlow, null);
            
            if (infoFlow.getConveyed().isEmpty()) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(infoFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
