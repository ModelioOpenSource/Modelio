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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TemplateParameterSubstitutionChecker checkTemplateParameterOwner
 */
@objid ("9cfb80ec-3235-4738-850c-49afd3e47f2b")
public class R2740 extends AbstractUmlRule {
    @objid ("d345df40-51bc-464a-bc74-86243a23efbf")
    private static final String RULEID = "R2740";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("eb6488ab-490b-4bf0-8a9d-13f2005389cf")
    private CheckR2740 checkerInstance = null;

    @objid ("8e670de9-e2da-43c6-95d4-0ddabacd9170")
    @Override
    public String getRuleId() {
        return R2740.RULEID;
    }

    @objid ("5a15c981-3b58-474c-8e24-87b4aa96b43c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(TemplateBinding.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameterSubstitution.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7804c2af-f7e3-4bff-b6c2-6100f191d28e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("88f9cfee-970f-45eb-a084-d0c512eba396")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a3658f71-32bb-4bff-9520-a01c7d316bcc")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2740
     */
    @objid ("d686dfb3-01d6-4d6d-8619-208ea8b3977d")
    public R2740() {
        this.checkerInstance = new CheckR2740(this);
    }

    @objid ("199dd876-714e-48e8-b3c9-ad26361d58ad")
    private static class CheckR2740 extends AbstractControl {
        @objid ("1d43e001-04a4-40b0-9b81-339836c8a17b")
        public CheckR2740(IRule rule) {
            super(rule);
        }

        @objid ("0ea19944-5e46-4d4c-bca9-ae10c0847724")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateBinding) {
                diagnostic.addEntry(checkR2740((TemplateBinding) element));
            } else if (element instanceof TemplateParameterSubstitution) {
                TemplateBinding tb = ((TemplateParameterSubstitution) element).getOwner();
                if (tb != null) {
                    diagnostic.addEntry(checkR2740(tb));
                }
            }
            return diagnostic;
        }

        @objid ("2bcb698d-dcc8-41b7-91a8-9ebf1df05cbc")
        private IAuditEntry checkR2740(final TemplateBinding tBinding) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    tBinding,
                    null);
            
            NameSpace instanciated = tBinding.getInstanciatedTemplate();
            
            for (TemplateParameterSubstitution tps : tBinding.getTemplateSubstitution()) {
                ModelElement owner = tps.getOwnerTemplateParameter();
                if (owner != null && !owner.equals(instanciated)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(tBinding);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
