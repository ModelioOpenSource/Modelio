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
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ModelElementChecker checkMetaRelations
 */
@objid ("5a284dd0-d95d-4137-99f4-7e41fc37c99f")
public class R1970 extends AbstractUmlRule {
    @objid ("ded524a7-c8f1-4f83-9e46-1fc0d06a9ad6")
    private static final String RULEID = "R1970";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("0398cd5e-cb03-40e8-b05e-db0670c9c780")
    private CheckR1970 checkerInstance = null;

    @objid ("e72f8308-509a-4314-8d7d-4f32e072ef04")
    @Override
    public String getRuleId() {
        return R1970.RULEID;
    }

    @objid ("7e8d960c-728e-4048-8037-bd4b92e950a9")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(TemplateParameterSubstitution.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7e9ff855-cd75-461d-936d-edef0148504e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3180323b-d16c-4264-9d75-2e9c136d4aee")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("abaaf3c9-a394-4af6-9c95-34fd963cc39a")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1970
     */
    @objid ("5686f879-63b6-4170-aa7e-2b4725d43d00")
    public R1970() {
        this.checkerInstance = new CheckR1970(this);
    }

    @objid ("8a69ca11-bedc-4224-b8e2-1e78f34be54b")
    private static class CheckR1970 extends AbstractControl {
        @objid ("1d5e3b6b-6002-4352-b1a1-60c98885f5d8")
        public CheckR1970(IRule rule) {
            super(rule);
        }

        @objid ("5ed2a32d-2277-40b3-966c-42def3ede545")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateParameterSubstitution) {
                diagnostic.addEntry(checkR1970((TemplateParameterSubstitution) element));
            } else {
                UmlUi.LOG.warning("R1970: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("2f639380-bf6d-4060-9bca-6dceb761304f")
        private IAuditEntry checkR1970(final TemplateParameterSubstitution tps) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, tps, null);
            
            if (tps.getFormalParameter() == null) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(tps);
                linkedObjects.add(tps.getOwner());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
