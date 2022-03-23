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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61072 error
 */
@objid ("ded8d80a-9b57-468e-9f4b-3c5ffe6ace84")
public class R1400 extends AbstractUmlRule {
    @objid ("9326b114-27f6-40cd-bc92-eea0e8e28945")
    private static final String RULEID = "R1400";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("dbc4c2eb-8c76-4dfe-92bd-fa082e282790")
    private CheckR1400 checkerInstance = null;

    @objid ("c69d761b-cb01-40f1-9f48-346d25333a83")
    @Override
    public String getRuleId() {
        return R1400.RULEID;
    }

    @objid ("5ba01a76-23d3-4012-b2a2-756fb0c820be")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ActivityParameterNode.MQNAME, this,
                AuditTrigger.CREATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2deda4b6-fb89-4396-9879-b8919c2acf2b")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a7eebb29-082d-48f2-a4ac-7585a46a4fa5")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("70fe2f95-9df4-4a72-808c-f1fb1d93dfc3")
    @Override
    public IControl getUpdateControl(MObject element) {
        return null;
    }

    /**
     * Default constructor for R1400
     */
    @objid ("98c0f03f-2012-4881-af72-fa0a3719c98c")
    public  R1400() {
        this.checkerInstance = new CheckR1400(this);
    }

    @objid ("ab4a23d3-5b67-43de-b561-c59c70ffbde5")
    private static class CheckR1400 extends AbstractControl {
        @objid ("5447a411-f09e-4655-b826-d7094d5f85f8")
        public  CheckR1400(IRule rule) {
            super(rule);
        }

        @objid ("99a36692-82ca-47f3-83ae-6236d4082648")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityParameterNode) {
                diagnostic
                        .addEntry(checkR1400((ActivityParameterNode) element));
            } else {
                UmlUi.LOG.warning("R1400: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("4e387094-7437-4f90-b983-e96e05bba236")
        private IAuditEntry checkR1400(ActivityParameterNode node) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, node, null);
            
            MObject owner = node.getCompositionOwner();
            
            if (owner instanceof Activity) {
                return auditEntry;
            }
            
            // Rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(node);
            linkedObjects.add(owner);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

    }

}
