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
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InstanceChecker checkInstanceAttributesName
 */
@objid ("170445e1-700c-464f-8265-eaad18b8bd45")
public class R1750 extends AbstractUmlRule {
    @objid ("502b5862-93cf-4051-9cff-96a2538b32bd")
    private static final String RULEID = "R1750";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("4953b2bf-6e44-45ba-88ba-62ae19d2ec4a")
    private CheckR1750 checkerInstance = null;

    @objid ("8ffffa5c-f446-4f0f-85ed-71c788124f5d")
    @Override
    public String getRuleId() {
        return R1750.RULEID;
    }

    @objid ("0371aa52-dc26-4db1-b4fe-90ebee03abe2")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Instance.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(AttributeLink.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("74ed7cc5-0229-4f92-a856-7949b9ce2c98")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("58fcecc5-52ee-4f15-a717-0777c546b87c")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c0e93a4e-078f-4e18-9e0c-749afe9bf505")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1750
     */
    @objid ("df7d652e-672f-4786-81e7-b8c52aa9fb74")
    public  R1750() {
        this.checkerInstance = new CheckR1750(this);
    }

    @objid ("386c8db1-4f09-4744-b066-247821a97ba3")
    private static class CheckR1750 extends AbstractControl {
        @objid ("193998b4-4298-4aa7-b5ec-66dc919897a9")
        public  CheckR1750(IRule rule) {
            super(rule);
        }

        @objid ("0c7e54d6-74a8-42c2-9b5f-5dae33c86ee6")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Instance) {
                diagnostic.addEntry(checkR1750((Instance) element));
            } else if (element instanceof AttributeLink) {
                diagnostic.addEntry(checkR1750(((AttributeLink) element).getAttributed()));
            } else {
                UmlUi.LOG.warning("R1750: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("aac67dfb-729c-4eb4-b6a7-0ce084bbcdae")
        private IAuditEntry checkR1750(final Instance instance) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, instance, null);
            
            List<String> attLinkNames = new ArrayList<>();
            
            for (AttributeLink attLink : instance.getSlot()) {
                String attLinkName = attLink.getName();
                if (attLinkNames.contains(attLinkName)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(instance);
                    linkedObjects.add(attLinkName);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    attLinkNames.add(attLinkName);
                }
            }
            return auditEntry;
        }

    }

}
