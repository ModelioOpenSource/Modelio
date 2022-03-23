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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkShallowHistoryState
 */
@objid ("2e8a19e8-b22e-4ef8-b1e8-564a1d0ede69")
public class R2630 extends AbstractUmlRule {
    @objid ("54d7a8f3-b3d7-448d-a230-72e1bf40a7f6")
    private static final String RULEID = "R2630";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("206d209e-65a8-454c-8957-41ca7b29e361")
    private CheckR2630 checkerInstance = null;

    @objid ("9da1abe8-e0c3-4bbb-adee-e83661e814bb")
    @Override
    public String getRuleId() {
        return R2630.RULEID;
    }

    @objid ("86d0e660-5577-4934-a053-3fdb417472d8")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Region.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ShallowHistoryPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dd88f0ee-cbd1-4d4c-995a-9feeb803d432")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5889ccc8-2424-4340-8855-93533a38b3a8")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0db05142-0e1e-4b48-86b3-05f9786b1140")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2630
     */
    @objid ("caf1fb2b-9cf2-417e-8a06-167a40159572")
    public  R2630() {
        this.checkerInstance = new CheckR2630(this);
    }

    @objid ("a95a9005-82ee-4a45-beca-f88f60415be2")
    private static class CheckR2630 extends AbstractControl {
        @objid ("a3ed273f-eef4-4ec9-a47d-1abd671427af")
        public  CheckR2630(IRule rule) {
            super(rule);
        }

        @objid ("0b29444e-960c-401a-a845-cb551790eb0b")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Region) {
                diagnostic.addEntry(checkR2630((Region) element));
            } else if (element instanceof ShallowHistoryPseudoState) {
                diagnostic.addEntry(checkR2630(((ShallowHistoryPseudoState) element).getParent()));
            } else {
                UmlUi.LOG.warning("R2630: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f6fa4973-85c3-49d5-895c-d1df053d0b04")
        private IAuditEntry checkR2630(Region region) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, region, null);
            
            List<ShallowHistoryPseudoState> foundShps = new ArrayList<>();
            
            for (ShallowHistoryPseudoState shps : region.getSub(ShallowHistoryPseudoState.class)) {
                foundShps.add(shps);
            }
            
            if (foundShps.size() > 1) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(region);
                linkedObjects.addAll(foundShps);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
