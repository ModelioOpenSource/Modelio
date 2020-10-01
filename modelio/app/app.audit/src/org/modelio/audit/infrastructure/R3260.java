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

package org.modelio.audit.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * R3260 Severity : tip Description : Identify shell objects
 */
@objid ("d5714951-5ae3-448c-8af6-45037a7f2a35")
public class R3260 extends AbstractInfrastructureRule {
    @objid ("461394ec-4d42-4b45-8131-211079d28238")
    private static final String RULEID = "R3260";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f578ccf2-cd4f-402b-a77e-0a2ad4580318")
    private CheckR3260 checkerInstance = null;

    @objid ("a60daa15-2ff4-47ef-b191-13996e7c2af4")
    @Override
    public void autoRegister(final InfrastructureAuditPlan plan) {
        plan.registerRuleWithoutTrigger(this);
    }

    @objid ("e7531c89-b40a-469b-91df-b5632b9d5223")
    @Override
    public String getRuleId() {
        return R3260.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("50b56b9e-f419-489f-961a-dbc4534cabdd")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9b8dd034-0fbc-4f2f-b2b9-a4a13c98b247")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1627c44b-ffed-47d9-b892-7dff10a82044")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("ec11c911-9386-4229-8ff4-961afdc68a84")
    public R3260() {
        this.checkerInstance = new CheckR3260(this);
    }

    /**
     * Actual checker for R3260: Checks that the element is not a shell object
     */
    @objid ("c417db58-748b-4a92-8e45-6f50a844117c")
    private static class CheckR3260 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("d73162ec-61a7-4325-bbcf-14256db7a5fd")
        public CheckR3260(final IRule rule) {
            super(rule);
        }

        @objid ("98670904-cf05-4dc2-b397-d1a1383a2cbb")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            diagnostic.addEntry(checkR3260(element));
            return diagnostic;
        }

        @objid ("9cf49595-a17d-402f-b401-c3151ccff583")
        private IAuditEntry checkR3260(final MObject element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    element,
                    null);
            
            if (element.isShell() == true) {
                auditEntry.setSeverity(this.rule.getSeverity());
                ArrayList<Object> linkedObjects = new ArrayList<>(3);
                linkedObjects.add(element);
            
                // Add all elements referencing this shell one.
                for (MDependency dep : element.getMClass().getDependencies(true)) {
                    SmDependency d = (SmDependency) dep;
                    if (!d.isComposition() && !d.isSharedComposition() && !d.isPartOf()) {
                        // Skip MObject API to avoid trigger loading of all objects using this one.
                        linkedObjects.addAll(d.getValueAsCollection(((SmObjectImpl) element).getData()));
                    }
                }
            
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
