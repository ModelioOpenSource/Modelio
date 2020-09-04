/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: GeneralizationChecker checkGeneralizationMetaclass error
 */
@objid ("d59723ff-a327-4df8-9c1a-a4c96b4948c0")
public class R1730 extends AbstractUmlRule {
    @objid ("49a6275a-36df-43bc-b0dc-eb2173f7f099")
    private static final String RULEID = "R1730";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("29aa364e-e7ff-40ad-bd93-eb661f374ab8")
    private CheckR1730 checkerInstance = null;

    @objid ("69c8538d-7624-4921-a2e4-81e08b702324")
    @Override
    public String getRuleId() {
        return R1730.RULEID;
    }

    @objid ("fd8053b3-aff6-4ff0-8f06-7343c3aadb1d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c608abf5-3c44-4917-b3f9-b436d515dfba")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b1e39bf1-42a2-46e9-9798-ce08dd330503")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6127f304-1190-4b37-82bb-8322d0e40df4")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1730
     */
    @objid ("c4def138-13b5-48aa-85b5-ebd67ca0c4a6")
    public R1730() {
        this.checkerInstance = new CheckR1730(this);
    }

    @objid ("261dfca4-4040-492b-93ef-024befe6a1c6")
    private static class CheckR1730 extends AbstractControl {
        @objid ("fa116cff-3667-4ba2-94b5-e27496b3e5e6")
        public CheckR1730(IRule rule) {
            super(rule);
        }

        @objid ("f0d3c370-0ff2-4036-b7b0-4f4a5af09e94")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Generalization) {
                diagnostic.addEntry(checkR1730((Generalization) element));
            }
            return diagnostic;
        }

        @objid ("0b5f48cc-83e0-4c6a-9201-c01153e5c38d")
        private IAuditEntry checkR1730(final Generalization generalization) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    generalization,
                    null);
            
            NameSpace superNS = generalization.getSuperType();
            NameSpace subNS = generalization.getSubType();
            
            if (!((subNS instanceof Signal && superNS instanceof Class) || subNS.getMClass().getName()
                    .equals(superNS.getMClass().getName()))) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(generalization);
                linkedObjects.add(subNS);
                linkedObjects.add(superNS);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
