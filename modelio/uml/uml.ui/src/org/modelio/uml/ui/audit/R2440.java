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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OwnershipChecker checkOperation
 */
@objid ("49a53e79-fde4-4c15-8b71-53e1a42d93a3")
public class R2440 extends AbstractUmlRule {
    @objid ("a59be066-a189-48f1-a840-133488eb0147")
    private static final String RULEID = "R2440";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8a98e17b-bcfd-486f-9628-ba8b394a4752")
    private CheckR2440 checkerInstance = null;

    @objid ("71714690-6c23-47ae-b58d-5390a4080129")
    @Override
    public String getRuleId() {
        return R2440.RULEID;
    }

    @objid ("65222ddf-ca57-4f2d-a3ce-d5b72832e2fd")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("05263a6b-5826-4174-94df-83d162d7a52c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6223ebe0-3030-4805-bf7b-1e8bb3744ebe")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9c41c4a5-f4ab-485f-95f7-eea0dc1a4da0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2440
     */
    @objid ("ec4f9232-da7f-4d67-ac28-8aa0752295ad")
    public R2440() {
        this.checkerInstance = new CheckR2440(this);
    }

    @objid ("e900f5dd-7f69-4d3e-a186-82f8317c8c49")
    private static class CheckR2440 extends AbstractControl {
        @objid ("ed6e91f5-b37f-43a0-8391-7865f9fd50dc")
        public CheckR2440(IRule rule) {
            super(rule);
        }

        @objid ("e655b909-0df7-4cbd-9800-f0cba05913f9")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Enumeration) {
                diagnostic.addEntry(checkR2440((Enumeration) element));
            } else if (element instanceof Operation) {
                Classifier owner = ((Operation) element).getOwner();
                if (owner != null && owner instanceof Enumeration) {
                    diagnostic.addEntry(checkR2440((Enumeration) owner));
                }
            } else {
                UmlUi.LOG.warning("R2440: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("37ed08de-4eec-4319-84ad-370c8b15c33e")
        private IAuditEntry checkR2440(final Enumeration enumeration) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, enumeration, null);
            
            for (ModelTree mt : enumeration.getOwnedElement()) {
                if (mt instanceof Operation) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(enumeration);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
