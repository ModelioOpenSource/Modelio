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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: .InterfaceRealizationChecker checkUnicity
 */
@objid ("60f4ba75-7941-415b-837b-57440cf75146")
public class R1870 extends AbstractUmlRule {
    @objid ("bc92a229-6d38-481d-a73f-eb7b96d819ec")
    private static final String RULEID = "R1870";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("83ab1034-a9bd-41ab-bd66-afecdda0f519")
    private CheckR1870 checkerInstance = null;

    @objid ("d8e0b401-199c-4b58-8b64-6761ea4bab0b")
    @Override
    public String getRuleId() {
        return R1870.RULEID;
    }

    @objid ("e1423fc9-b7a7-4635-ad16-f426687c701a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InterfaceRealization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c2d16b62-b4a6-4edd-8a1c-01143385d7be")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b375435b-1e50-4a9d-959c-872b26251caf")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f2e6ff4a-f778-4229-afa0-1433f1142685")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1870
     */
    @objid ("38357750-dab9-4ff4-8aed-94361f454890")
    public R1870() {
        this.checkerInstance = new CheckR1870(this);
    }

    @objid ("51ee1a02-e4d0-4fed-961e-1f45bd718f6c")
    private static class CheckR1870 extends AbstractControl {
        @objid ("c77d345c-bbc0-4548-9daf-aff94ed8dd08")
        public CheckR1870(IRule rule) {
            super(rule);
        }

        @objid ("8b5efdea-62c8-49b0-8478-5cc5c5af4157")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                diagnostic.addEntry(checkR1870((NameSpace) element));
            } else if (element instanceof InterfaceRealization) {
                diagnostic.addEntry(checkR1870(((InterfaceRealization) element).getImplementer()));
            } else {
                UmlUi.LOG.warning("R1870: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("37457ba7-c019-46e1-97a7-a5b85fcd330d")
        private IAuditEntry checkR1870(final NameSpace ns) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    ns,
                    null);
            
            List<Interface> interfaces = new ArrayList<>();
            
            for (InterfaceRealization ir : ns.getRealized()) {
                Interface interfaze = ir.getImplemented();
                if (interfaces.contains(interfaze)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(interfaze);
                    linkedObjects.add(ns);
                    auditEntry.setLinkedInfos(linkedObjects);
                } else {
                    interfaces.add(interfaze);
                }
            }
            return auditEntry;
        }

    }

}
