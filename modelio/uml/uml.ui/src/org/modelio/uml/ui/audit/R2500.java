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
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ParameterChecker checkOutDefaultValue
 */
@objid ("bb49592b-dcdb-475d-aa18-37dcf9d639d3")
public class R2500 extends AbstractUmlRule {
    @objid ("a7edb4f9-2030-4802-be30-d1c547d2ce68")
    private static final String RULEID = "R2500";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8ef1f87a-1942-4060-8ae9-10671397a12d")
    private CheckR2500 checkerInstance = null;

    @objid ("0521249c-8602-4aa4-9ab1-241cf7c6773c")
    @Override
    public String getRuleId() {
        return R2500.RULEID;
    }

    @objid ("47b06b90-3b0b-4dd6-b13b-609ee06a1dfa")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7d0abd7e-9783-4949-bfe2-6abc6f35cd29")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ecb628ee-840b-4ca2-808e-64aacc175a35")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b5eac732-b344-411c-a6c6-c1c8236399aa")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2500
     */
    @objid ("5c0f6402-8066-4fc5-966d-529b211b8a27")
    public R2500() {
        this.checkerInstance = new CheckR2500(this);
    }

    @objid ("6affc494-ad64-44b6-a9db-f6f5a97f86d4")
    private static class CheckR2500 extends AbstractControl {
        @objid ("84aede48-9475-467c-ab38-d44df310b9bf")
        public CheckR2500(IRule rule) {
            super(rule);
        }

        @objid ("095b0bfb-74a6-427a-9949-3f30b361ebd7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Parameter) {
                diagnostic.addEntry(checkR2500((Parameter) element));
            } else {
                UmlUi.LOG.warning("R2500: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("127ee52e-9e36-45b2-87c4-4b9d2ce7d894")
        private IAuditEntry checkR2500(final Parameter parameter) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    parameter,
                    null);
            
            if (parameter.getParameterPassing().equals(PassingMode.OUT) && !parameter.getDefaultValue().isEmpty()) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(parameter);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
