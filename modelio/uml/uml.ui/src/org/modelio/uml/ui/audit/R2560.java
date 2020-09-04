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
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PortChecker checkIsBehaviorOneProvided
 */
@objid ("aa2fe1e2-a248-4c89-9976-cd76d1b1e855")
public class R2560 extends AbstractUmlRule {
    @objid ("7e7cde8d-5ecc-49dd-9ee5-76c122c8cf8d")
    private static final String RULEID = "R2560";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("cf9df359-d255-4e31-8871-151400b5458f")
    private CheckR2560 checkerInstance = null;

    @objid ("8e545530-c7fe-42eb-bf45-4f78ef281ed1")
    @Override
    public String getRuleId() {
        return R2560.RULEID;
    }

    @objid ("9c11b524-dc10-4ad9-8297-51616c9c5a46")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ProvidedInterface.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("99812f28-e925-4268-8b83-b9fe58148d3d")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5bbee564-e6e6-4c66-b03f-6faecd8c97bc")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2aa49147-4aba-4d46-a3f7-b02f817a2229")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2560
     */
    @objid ("817d5f59-ab06-4cfb-8afc-c814ae7ebab2")
    public R2560() {
        this.checkerInstance = new CheckR2560(this);
    }

    @objid ("63c95b27-0715-400c-9cb7-a45caca0b9f6")
    private static class CheckR2560 extends AbstractControl {
        @objid ("de0b8aa7-52b2-455d-8c09-50dba7eb238c")
        public CheckR2560(IRule rule) {
            super(rule);
        }

        @objid ("fbec6588-736b-48cc-b193-3f589b7a25fd")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Port) {
                diagnostic.addEntry(checkR2560((Port) element));
            } else if (element instanceof ProvidedInterface) {
                diagnostic.addEntry(checkR2560(((ProvidedInterface) element).getProviding()));
            } else {
                UmlUi.LOG.warning("R2560: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("0f8fd4ab-c855-4334-ad7d-32981a86a9e1")
        private IAuditEntry checkR2560(final Port port) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, port, null);
            
            if (port.isIsBehavior()) {
            
                for (ProvidedInterface pi : port.getProvided()) {
                    if (!pi.getProvidedElement().isEmpty()) {
                        return auditEntry;
                    }
                }
            
                // At this point the rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(port);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
