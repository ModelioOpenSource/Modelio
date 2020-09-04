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
 * Rule implementation origin: PortChecker checkDelegateRequired
 */
@objid ("1481bf99-17d5-4ae2-acbe-4d122a3c223e")
public class R2530 extends AbstractUmlRule {
    @objid ("7f17d70a-6073-46f3-9bfd-92f7198343e6")
    private static final String RULEID = "R2530";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("3e8c2692-da8d-4022-81e7-1fb2109b2525")
    private CheckR2530 checkerInstance = null;

    @objid ("4e295481-c4ad-43d6-8aad-e19934fad242")
    @Override
    public String getRuleId() {
        return R2530.RULEID;
    }

    @objid ("620855f3-0631-40e0-83f5-afd60e2a0cd5")
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
    @objid ("732016d2-ebad-49af-831e-b151668f72ed")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("43a4674e-ae79-4239-aa46-c14dd48dbc0f")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("df19325b-f0c9-4839-9a65-234cb53b3a1f")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2530
     */
    @objid ("c87ffabf-9f46-41ec-ac1f-0fb4e7ec56f5")
    public R2530() {
        this.checkerInstance = new CheckR2530(this);
    }

    @objid ("ec2e4cf1-d894-4e41-a563-f0d384f8bb6a")
    private static class CheckR2530 extends AbstractControl {
        @objid ("9142e987-f4f9-42c2-9285-037c4eb02ad4")
        public CheckR2530(IRule rule) {
            super(rule);
        }

        @objid ("5a23191f-d925-41ef-9e77-a745b5efa682")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Link) {
                for (LinkEnd le : ((Link) element).getLinkEnd()) {
                    Instance instance = le.getSource() != null ? le.getSource() : le.getOpposite().getTarget();
                    if (instance != null && instance instanceof Port) {
                        diagnostic.addEntry(checkR2530((Port) instance));
                    }
                }
            } else if (element instanceof Port) {
                diagnostic.addEntry(checkR2530((Port) element));
            } else {
                UmlUi.LOG.warning("R2530: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("64f788f2-d690-43a9-9c11-a59704f5dda7")
        private IAuditEntry checkR2530(final Port port) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, port, null);
            
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
