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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InstanceChecker checkR92
 */
@objid ("aabc95ca-5f57-45fc-b9cc-bf21d300e91d")
public class R1790 extends AbstractUmlRule {
    @objid ("3eb57286-1c25-4150-a26e-acae1b900aa2")
    private static final String RULEID = "R1790";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("6b024b79-ecf3-43be-b9ca-b8fbb55615fc")
    private CheckR1790 checkerInstance = null;

    @objid ("bba25a46-bced-43a1-8264-005bc8882a39")
    @Override
    public String getRuleId() {
        return R1790.RULEID;
    }

    @objid ("59ef6de7-7193-4b36-9cda-fca1ca5d0a32")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Instance.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7b94f76e-4168-412f-a648-286bbd9083f7")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("771c69f2-741e-4cbb-a7b1-d18116fe5529")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b5740d72-8556-495c-8c4e-9c0da7298d76")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1790
     */
    @objid ("d53a7422-3930-43e1-8c3f-51eed7a7a554")
    public  R1790() {
        this.checkerInstance = new CheckR1790(this);
    }

    @objid ("3cad6c79-c4db-4785-a131-9e9a9635b983")
    private static class CheckR1790 extends AbstractControl {
        @objid ("d47f489a-838d-4980-af14-a69849625530")
        public  CheckR1790(IRule rule) {
            super(rule);
        }

        @objid ("8d98f1b5-7445-44e1-8166-a4b1f0c0f4a2")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Instance) {
                diagnostic.addEntry(checkR1790((Instance) element));
            } else {
                UmlUi.LOG.warning("R1790: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("6733ef0a-3721-45c6-b476-b07d4bc24331")
        private IAuditEntry checkR1790(final Instance instance) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, instance, null);
            
            if (instance.getName().isEmpty() && instance.getBase() == null) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(instance);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
