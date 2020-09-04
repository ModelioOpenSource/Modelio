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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TemplateBindingChecker checkOriginDestination
 */
@objid ("3ef1e47d-6a41-44a6-b23b-fff2b52185ee")
public class R2720 extends AbstractUmlRule {
    @objid ("a2e6a9c9-2283-4917-9e69-c356e2b78fe5")
    private static final String RULEID = "R2720";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c5ad7af6-973a-43c8-9d75-367f02cabaa0")
    private CheckR2720 checkerInstance = null;

    @objid ("970f4534-2636-4595-8b34-dd9f9c705cdb")
    @Override
    public String getRuleId() {
        return R2720.RULEID;
    }

    @objid ("c2e3a19b-27d8-4c2f-a159-791f1edf4aa1")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(TemplateBinding.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d5eae07c-11ca-44ba-8eab-24503fe28a41")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("09127509-5abc-4b18-a2b9-221ba821ecaf")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5ad48963-ddd3-4331-b0c3-79bdd7ff5463")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2720
     */
    @objid ("3841cd37-f455-42cf-aa7b-90c9f5c18a12")
    public R2720() {
        this.checkerInstance = new CheckR2720(this);
    }

    @objid ("5df1b1c3-0ba5-4f24-988a-971466b59e3f")
    private static class CheckR2720 extends AbstractControl {
        @objid ("dfa153fa-c702-46f2-8a32-2b81885d12ea")
        public CheckR2720(IRule rule) {
            super(rule);
        }

        @objid ("c000441f-42c7-40c9-94cb-64b22f5029e7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateBinding) {
                diagnostic.addEntry(checkR2720((TemplateBinding) element));
            } else {
                UmlUi.LOG.warning("R2720: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("60a34aed-e67a-40a3-81ec-411a3576931e")
        private IAuditEntry checkR2720(final TemplateBinding tBinding) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, tBinding, null);
            
            NameSpace nsOrigin = tBinding.getBoundElement();
            Operation opOrigin = tBinding.getBoundOperation();
            
            NameSpace nsTarget = tBinding.getInstanciatedTemplate();
            Operation opTarget = tBinding.getInstanciatedTemplateOperation();
            
            if (opOrigin != null && opTarget != null) {
                return auditEntry;
            }
            
            if (nsOrigin != null && nsTarget != null && (nsOrigin.getMClass().equals(nsTarget.getMClass())
                    || nsOrigin.getMClass().getQualifiedName().equals(DataType.MQNAME) && nsTarget.getMClass().getQualifiedName().equals(Class.MQNAME)
                    || nsTarget.getMClass().getQualifiedName().equals(DataType.MQNAME) && nsOrigin.getMClass().getQualifiedName().equals(Class.MQNAME))) {
                return auditEntry;
            }
            
            // At this point the rule failed miserably
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(tBinding);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

    }

}
