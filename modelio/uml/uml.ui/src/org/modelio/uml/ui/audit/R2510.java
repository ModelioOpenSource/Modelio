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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PortChecker checkClassPortConnection
 */
@objid ("453f504f-5bc3-48d0-a66c-90415752eb0c")
public class R2510 extends AbstractUmlRule {
    @objid ("391c4d45-e8bd-46be-931c-f2dc4eea0be6")
    private static final String RULEID = "R2510";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("56de2b30-e887-4a89-a3ff-ea45014ac497")
    private CheckR2510 checkerInstance = null;

    @objid ("fd3b3ac1-79a3-4071-9014-c963005fea1d")
    @Override
    public String getRuleId() {
        return R2510.RULEID;
    }

    @objid ("29a12a69-b0c8-40ff-82ef-f0429264a940")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Link.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("43b6bacc-cb6f-4549-8e8b-0902481a5b3c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9e28544e-e719-4ae4-9e20-9fc356c44240")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e27e0635-224b-43a1-816e-5575f3902630")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2510
     */
    @objid ("bf5547ac-5387-4000-880b-6d44949a56cd")
    public R2510() {
        this.checkerInstance = new CheckR2510(this);
    }

    @objid ("18ddb055-ff0d-47e7-82c6-19eff186fc3a")
    private static class CheckR2510 extends AbstractControl {
        @objid ("644bee05-7077-4a03-a99b-43b39d601e3a")
        public CheckR2510(IRule rule) {
            super(rule);
        }

        @objid ("263b99ab-6916-484b-a500-fc18b5050b03")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Link) {
                diagnostic.addEntry(checkR2510((Link) element));
            } else {
                UmlUi.LOG.warning("R2510: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5c09ff84-c9c3-487b-a3af-8ac0ebfc6a6b")
        private IAuditEntry checkR2510(final Link link) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    link,
                    null);
            
            org.modelio.metamodel.uml.statik.Class foundClass = null;
            
            for (LinkEnd linkEnd : link.getLinkEnd()) {
                Instance instance = linkEnd.getSource() != null ? linkEnd.getSource() : linkEnd.getOpposite().getTarget();
            
                if (instance instanceof Port) {
                    Classifier classifier = ((Port) instance).getInternalOwner();
                    if (classifier instanceof org.modelio.metamodel.uml.statik.Class) {
                        if (foundClass != null) {
            
                            // Rule failed
            
                            auditEntry.setSeverity(this.rule.getSeverity());
                            List<Object> linkedObjects = new ArrayList<>();
                            linkedObjects.add(instance);
                            linkedObjects.add(foundClass);
                            linkedObjects.add(classifier);
                            auditEntry.setLinkedInfos(linkedObjects);
            
                        } else {
                            foundClass = (org.modelio.metamodel.uml.statik.Class) classifier;
                        }
                    }
                }
            }
            return auditEntry;
        }

    }

}
