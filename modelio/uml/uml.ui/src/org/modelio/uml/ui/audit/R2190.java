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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameSpaceChecker checkGeneralizationUnicity error
 */
@objid ("f0e386c4-1274-4b36-b690-f71b16a5528a")
public class R2190 extends AbstractUmlRule {
    @objid ("ad14ffe4-180d-4556-904c-f5bb78f4b38b")
    private static final String RULEID = "R2190";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("b9eb42a2-6630-4bc1-b8b2-1acdc6408d99")
    private CheckR2190 checkerInstance = null;

    @objid ("97e8e9fa-aa85-441e-84ad-4b3c80048a44")
    @Override
    public String getRuleId() {
        return R2190.RULEID;
    }

    @objid ("fb0fe3a7-b1f3-4a9e-9434-df6e46642c06")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(NameSpace.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fa4dd7dd-bae0-4bad-add5-ab9ff70f6e76")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("231c8998-75cf-460a-8fc8-c8469feda007")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("14bafdb5-59f1-4730-965e-13c6bcc5c9a5")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2190
     */
    @objid ("1d1a3728-6df2-46a6-913a-43fcecb69a8d")
    public R2190() {
        this.checkerInstance = new CheckR2190(this);
    }

    @objid ("7a5f11fe-7dba-47cb-b0ff-9e13c4328606")
    private static class CheckR2190 extends AbstractControl {
        @objid ("8ae6db59-6a28-4c87-b915-3c3355990980")
        public CheckR2190(IRule rule) {
            super(rule);
        }

        @objid ("4fc83469-a37b-494a-95ea-3ff11452a8f6")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                diagnostic.addEntry(checkR2190((NameSpace) element));
            }
            if (element instanceof Generalization) {
                diagnostic.addEntry(checkR2190(((Generalization) element).getSubType()));
            } else {
                UmlUi.LOG.warning("R2190: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("01fd5df4-c674-4e8a-bc07-b046676784f5")
        private IAuditEntry checkR2190(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            List<NameSpace> nameSpaceNames = new ArrayList<>();
            
            for (Generalization gen : nameSpace.getParent()) {
                NameSpace ns = gen.getSuperType();
                if (nameSpaceNames.contains(ns)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(nameSpace);
                    linkedObjects.add(ns);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    nameSpaceNames.add(ns);
                }
            }
            return auditEntry;
        }

    }

}
