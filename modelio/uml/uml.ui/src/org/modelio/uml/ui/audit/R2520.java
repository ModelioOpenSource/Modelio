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
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PortChecker checkDelegateProvided
 */
@objid ("1c0fd6bd-0c89-4a6d-9fce-efeb5a843524")
public class R2520 extends AbstractUmlRule {
    @objid ("775b33bc-a46e-4701-9501-2a41d65d60bf")
    private static final String RULEID = "R2520";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("1a9dfce7-591e-40da-bc91-2b07d33ac149")
    private CheckR2520 checkerInstance = null;

    @objid ("1ef5a9f3-ed0f-4095-99c6-946e591d0fca")
    @Override
    public String getRuleId() {
        return R2520.RULEID;
    }

    @objid ("3cd198b3-a366-4994-8fa3-1694d2ebb554")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Link.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c6cd438d-d3ee-4b16-a885-3be061bb0706")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e5571d97-b24c-4f72-87fe-fa571c366272")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1c9ddc5e-2c64-4c9d-ae53-a5c9bda0b1b0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2520
     */
    @objid ("dea5d723-af70-4e95-8451-ce420450aa14")
    public R2520() {
        this.checkerInstance = new CheckR2520(this);
    }

    @objid ("f6504cdb-f26a-440e-912c-b99240dfbacf")
    private static class CheckR2520 extends AbstractControl {
        @objid ("b750df82-886c-414e-ba4a-8021325cd40f")
        public CheckR2520(IRule rule) {
            super(rule);
        }

        @objid ("6fff748f-6539-4316-968f-e0b2f251c077")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Link) {
                for (LinkEnd linkEnd : ((Link) element).getLinkEnd()) {
                    Instance instance = linkEnd.getSource() != null ? linkEnd.getSource() : linkEnd.getOpposite().getTarget();
            
                    if (instance != null && instance instanceof Port) {
                        diagnostic.addEntry(checkR2520((Port) instance));
                    }
                }
            } else if (element instanceof Port) {
                diagnostic.addEntry(checkR2520((Port) element));
            } else {
                UmlUi.LOG.warning("R2520: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5c2394e0-808c-4cbc-ae33-feb7b1ca30f0")
        private IAuditEntry checkR2520(final Port port) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    port,
                    null);
            
            for (LinkEnd ce : port.getOwnedEnd()) {
            
                LinkEnd oposite = ce.getOpposite();
                Instance instance = oposite.getSource() != null ? oposite.getSource() : oposite.getOpposite().getTarget();
            
                if (instance instanceof BindableInstance) {
                    if (port.getProvided().isEmpty()) {
                        // Rule failed
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(port);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

    }

}
