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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TemplateBindingChecker checkExtraSubstitutions
 */
@objid ("da7dd8ab-e3df-46b7-9816-07d99c98d170")
public class R2700 extends AbstractUmlRule {
    @objid ("035d5e62-821b-4a44-8f66-23fb0577d0a0")
    private static final String RULEID = "R2700";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f2009af2-37e0-47a5-a3b6-73acee13c5a8")
    private CheckR2700 checkerInstance = null;

    @objid ("76f11ff9-77b0-40fd-b3ee-93676faf8d2e")
    @Override
    public String getRuleId() {
        return R2700.RULEID;
    }

    @objid ("78cf98a3-645d-4788-ab4c-3bf8dd76753d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(TemplateBinding.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("708139dc-80cf-4ae5-9c0d-31b4c5a2da1a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ebc58cf9-fe53-4894-90d4-84bb9990ca1b")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c3b7a574-5892-4672-97b5-6f458d304a9c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2700
     */
    @objid ("34aa1ba6-7eb7-4424-bc33-142af6a7d3e3")
    public R2700() {
        this.checkerInstance = new CheckR2700(this);
    }

    @objid ("ad5e3853-96dc-42ca-ac2d-b8e9eb351849")
    private static class CheckR2700 extends AbstractControl {
        @objid ("1934b72a-ba39-4288-8e68-646c35291b05")
        public CheckR2700(IRule rule) {
            super(rule);
        }

        @objid ("691691f6-a3fe-408b-817d-18f111e363da")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateBinding) {
                diagnostic.addEntry(checkR2700((TemplateBinding) element));
            } else {
                UmlUi.LOG.warning("R2700: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("ab7da84b-3145-43f8-9333-cc02f6f3565d")
        private IAuditEntry checkR2700(final TemplateBinding tBinding) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    tBinding,
                    null);
            
            NameSpace instanciatedNs = tBinding.getInstanciatedTemplate();
            Operation instanciatedOp = tBinding.getInstanciatedTemplateOperation();
            
            List<TemplateParameter> params = new ArrayList<>();
            
            if (instanciatedNs != null) {
                params.addAll(instanciatedNs.getTemplate());
            } else if (instanciatedOp != null) {
                params.addAll(instanciatedOp.getTemplate());
            }
            
            List<TemplateParameter> matchedParams = new ArrayList<>();
            
            for (TemplateParameterSubstitution tps : tBinding.getParameterSubstitution()) {
                TemplateParameter param = tps.getFormalParameter();
                if (param != null && params.contains(param)) {
                    if (matchedParams.contains(param)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(tBinding);
                        linkedObjects.add(param);
                        auditEntry.setLinkedInfos(linkedObjects);
                    } else {
                        matchedParams.add(param);
                    }
                }
            }
            return auditEntry;
        }

    }

}
