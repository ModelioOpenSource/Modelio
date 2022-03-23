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
package org.modelio.audit.infrastructure;

import java.util.Arrays;
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
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TaggedValueChecker checkParamNumber
 */
@objid ("dbf73b20-5f83-427f-a60a-ea75ffb2e498")
public class R2680 extends AbstractInfrastructureRule {
    @objid ("1557bbb1-caeb-4376-bbaa-7bed692eb0d2")
    private static final String RULEID = "R2680";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("885641ba-0088-40a5-86d7-7e379598562d")
    private CheckR2680 checkerInstance = null;

    @objid ("d912ea67-c0aa-49da-be6e-e7d2d7599cb0")
    @Override
    public String getRuleId() {
        return R2680.RULEID;
    }

    @objid ("39a00975-77fd-4bc4-a17d-cb9fab72f8a1")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(TaggedValue.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(TagParameter.MQNAME, this, AuditTrigger.CREATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f48d40e8-97a2-42ec-9a56-70470b385067")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d454dce2-3f69-48a9-b587-c4e2a07b46ec")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("13af1a8d-b7f7-496b-8829-0afaf36f2d9d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2680
     */
    @objid ("1d7706bb-6c61-4fe7-86e8-77158e9af014")
    public  R2680() {
        this.checkerInstance = new CheckR2680(this);
    }

    @objid ("7b6dadd5-1813-4174-8796-ca4ca9c02f3e")
    private static class CheckR2680 extends AbstractControl {
        @objid ("315880b7-da21-45cf-9b8c-3aeb28222953")
        public  CheckR2680(IRule rule) {
            super(rule);
        }

        @objid ("6be4657b-f0c9-4a54-9017-63b17bf39831")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TaggedValue) {
                diagnostic.addEntry(checkR2680((TaggedValue) element));
            } else if (element instanceof TagParameter) {
                TaggedValue tv = ((TagParameter) element).getAnnoted();
                if (tv != null) {
                    diagnostic.addEntry(checkR2680(tv));
                }
            }
            return diagnostic;
        }

        @objid ("d6d186e7-e652-42e9-a07a-390673881db3")
        private IAuditEntry checkR2680(final TaggedValue taggedValue) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    taggedValue,
                    null);
            
            TagType tt = taggedValue.getDefinition();
            
            try {
                int paramNumber = Integer.parseInt(tt.getParamNumber());
            
                if (taggedValue.getActual().size() != paramNumber) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    auditEntry.setLinkedInfos(Arrays.asList(
                            taggedValue,
                            taggedValue.getActual().size(),
                            paramNumber,
                            tt));
                }
            } catch (@SuppressWarnings("unused") NumberFormatException e) {
                // Nothing
            }
            return auditEntry;
        }

    }

}
