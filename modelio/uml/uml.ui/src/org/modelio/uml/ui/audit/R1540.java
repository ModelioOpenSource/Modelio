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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: BindableInstanceChecker checkTypeCycles error
 */
@objid ("fb530f6c-0f98-4ed4-a1f4-cbc4d80b07fa")
public class R1540 extends AbstractUmlRule {
    @objid ("4fcc0541-df2b-4c92-a630-0450f9a6221c")
    private static final String RULEID = "R1540";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("cc8a0709-b62c-4228-8f45-0deb67dce474")
    private CheckR1540 checkerInstance = null;

    @objid ("5994066b-3851-437c-bdd9-76c9586e26bc")
    @Override
    public String getRuleId() {
        return R1540.RULEID;
    }

    @objid ("6ee401cf-d013-4f2b-84b9-8ec3f3623d59")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("04e5fcff-b45a-4365-b082-688e406ba6ba")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7f6c3bab-c20f-488e-a55d-fe8bd9aef039")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("84cbb402-9a0c-4fec-acdb-1326c513aa8c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1540
     */
    @objid ("f5b70a59-82d9-445d-82ec-874582e23020")
    public R1540() {
        this.checkerInstance = new CheckR1540(this);
    }

    @objid ("71cb2b86-18cb-4b2f-995a-44880b22b7e8")
    private static class CheckR1540 extends AbstractControl {
        @objid ("43b1db6d-ed21-4c70-993b-ab2c8d0e29ae")
        public CheckR1540(IRule rule) {
            super(rule);
        }

        @objid ("e3fd740a-686a-47c0-93de-bcca05455bcd")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof BindableInstance) {
                diagnostic.addEntry(checkR1540((BindableInstance) element));
            }
            return diagnostic;
        }

        @objid ("cc62cf62-ff0b-4c39-8beb-082643419b60")
        private IAuditEntry checkR1540(final BindableInstance bindableInstance) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    bindableInstance,
                    null);
            
            List<BindableInstance> cycleInstances = new ArrayList<>();
            
            BindableInstance currentInstance = bindableInstance;
            boolean hasCycle = false;
            
            while (currentInstance != null && !hasCycle) {
                ModelElement repFeature = null;
                if ((repFeature = currentInstance.getRepresentedFeature()) != null) {
                    if (repFeature instanceof BindableInstance) {
                        if (repFeature.equals(bindableInstance)) {
                            hasCycle = true;
                        } else {
                            currentInstance = (BindableInstance) repFeature;
                        }
                    } else {
                        currentInstance = null;
                    }
                } else {
                    currentInstance = null;
                }
            }
            
            if (hasCycle) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(bindableInstance);
                for (BindableInstance bi : cycleInstances) {
                    linkedObjects.add(bi);
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
