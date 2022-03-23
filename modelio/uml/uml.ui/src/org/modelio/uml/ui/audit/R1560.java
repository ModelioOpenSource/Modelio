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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassChecker checkActive
 */
@objid ("59a780fa-8b0e-49c2-8297-36faeae7146c")
public class R1560 extends AbstractUmlRule {
    @objid ("088f923c-5de0-4e62-a0fb-bf96d7d7c643")
    private static final String RULEID = "R1560";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("329d01e7-5170-46a2-8e0f-0eaa84a4d49f")
    private CheckR1560 checkerInstance = null;

    @objid ("fe2c94eb-c269-4efb-b966-0308eb475c7f")
    @Override
    public String getRuleId() {
        return R1560.RULEID;
    }

    @objid ("3681d84f-5009-4042-8373-dd80076a259c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f8786749-8879-448e-ab39-b29ee765a1f4")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ba5e9d44-bc82-4c61-adad-55ffcf7860bc")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("936406c7-c9a7-4f14-ba4c-eff4a209ffa9")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1560
     */
    @objid ("7d2e3373-79c3-4cf2-81d4-129da3537ee9")
    public  R1560() {
        this.checkerInstance = new CheckR1560(this);
    }

    @objid ("0f5834e3-67a9-41b4-b94b-2534377792ed")
    private static class CheckR1560 extends AbstractControl {
        @objid ("698fda96-a39e-4416-8a7f-d5e86be0c2b6")
        public  CheckR1560(IRule rule) {
            super(rule);
        }

        @objid ("b5f45862-60bc-4e68-84e1-c5b3237a64d7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            // A class was modified, potentially its isActiove value.
            // We need to check the rule on the class itself (which will check all sub classes)
            // But we also need to check it super class if any.
            if (element instanceof Class) {
                Class clazz = (Class) element;
                diagnostic.addEntry(checkR1560(clazz));
                for (Generalization gen : clazz.getParent()) {
                    NameSpace ns = gen.getSuperType();
                    if (ns instanceof Class) {
                        diagnostic.addEntry(checkR1560((Class) ns));
                    }
                }
            } else if (element instanceof Generalization) {
                NameSpace ns = ((Generalization) element).getSuperType();
                if (ns instanceof Class) {
                    diagnostic.addEntry(checkR1560((Class) ns));
                }
            } else {
                UmlUi.LOG.warning("R1560: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f176bd34-ebcd-4ac9-9d2b-d810f3b41cf0")
        private IAuditEntry checkR1560(final Class clazz) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    clazz,
                    null);
            
            if (clazz.isIsActive()) {
                for (Generalization gen : clazz.getSpecialization()) {
                    NameSpace ns = gen.getSubType();
                    if (ns instanceof Class) {
                        if (!((Class) ns).isIsActive()) {
            
                            // Rule failed
            
                            auditEntry.setSeverity(this.rule.getSeverity());
                            List<Object> linkedObjects = new ArrayList<>();
                            linkedObjects.add(clazz);
                            linkedObjects.add(ns);
                            auditEntry.setLinkedInfos(linkedObjects);
                        }
                    }
                }
            }
            return auditEntry;
        }

    }

}
