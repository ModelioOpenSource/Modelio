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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PackageChecker checkGeneralization
 */
@objid ("896dad95-abde-40ae-8a70-5ab2c1a11fd2")
public class R2450 extends AbstractUmlRule {
    @objid ("625bc377-e895-41a5-8168-0811d7308c6b")
    private static final String RULEID = "R2450";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("1ea94f99-e8cb-44aa-abea-f71306eb3619")
    private CheckR2450 checkerInstance = null;

    @objid ("66eaa3c8-0a70-4bc6-b222-1d404df047f0")
    @Override
    public String getRuleId() {
        return R2450.RULEID;
    }

    @objid ("3920315c-57af-48d2-b272-6357d4cc2303")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Package.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("567bde09-a3bd-4d76-9c85-4e3db38c2323")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5f94b7f9-ba18-4253-b36b-65a17be2eda5")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("09240cdc-1588-4302-bd6d-c6c021861c13")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2450
     */
    @objid ("e0220d91-d806-4e8d-8fc8-c1fa7ecd3184")
    public R2450() {
        this.checkerInstance = new CheckR2450(this);
    }

    @objid ("7231739c-4beb-4c00-a86a-01593ee41da5")
    private static class CheckR2450 extends AbstractControl {
        @objid ("b8f15fd2-fe90-4a22-89c1-f61dd4d35c1c")
        public CheckR2450(IRule rule) {
            super(rule);
        }

        @objid ("1383f35f-42ba-4382-917e-c3dfa0d2c166")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Package) {
                diagnostic.addEntry(checkR2450((Package) element));
            } else if (element instanceof Generalization) {
                Generalization gen = (Generalization) element;
                NameSpace subNS = gen.getSubType();
                NameSpace superNS = gen.getSuperType();
                if (subNS instanceof Package) {
                    diagnostic.addEntry(checkR2450((Package) subNS));
                }
                if (superNS instanceof Package) {
                    diagnostic.addEntry(checkR2450((Package) superNS));
                }
            } else {
                UmlUi.LOG.warning("R2450: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("304c17fd-76fc-480d-b651-928a197310cb")
        private IAuditEntry checkR2450(final Package pkg) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, pkg, null);
            
            if (!pkg.getParent().isEmpty() || !pkg.getSpecialization().isEmpty()) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(pkg);
                linkedObjects.addAll(pkg.getParent());
                linkedObjects.addAll(pkg.getSpecialization());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
