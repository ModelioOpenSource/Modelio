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
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkDeepHistoryState
 */
@objid ("8e2aef93-d79a-42f4-a159-640dd0981538")
public class R2580 extends AbstractUmlRule {
    @objid ("f7e9c386-bf87-4a3d-806d-bcb1c86da5e7")
    private static final String RULEID = "R2580";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("9511ea34-4337-4a65-a35e-03663204273c")
    private CheckR2580 checkerInstance = null;

    @objid ("b649c621-786d-4531-a290-e91359657aea")
    @Override
    public String getRuleId() {
        return R2580.RULEID;
    }

    @objid ("da539106-00b8-4ce1-b2c2-40daed02af2d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Region.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DeepHistoryPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b8f261f5-8e78-4594-be57-425a09db3514")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9824d3a2-a12a-43e2-9eb1-62f2cc0c2fcd")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("20ce3e69-b484-4ae5-8d5f-d22262160b65")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2580
     */
    @objid ("3697ac04-b7b3-4d0e-9b1f-1348a48bbae0")
    public R2580() {
        this.checkerInstance = new CheckR2580(this);
    }

    @objid ("a3d46c4a-81e7-4902-9de7-6b812f523dfe")
    private static class CheckR2580 extends AbstractControl {
        @objid ("2a7f51cb-e633-41b0-a5dc-7c65e558df57")
        public CheckR2580(IRule rule) {
            super(rule);
        }

        @objid ("b2de1a04-4518-4677-bb5e-692cb3da4a55")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Region) {
                diagnostic.addEntry(checkR2580((Region) element));
            } else if (element instanceof DeepHistoryPseudoState) {
                diagnostic.addEntry(checkR2580(((DeepHistoryPseudoState) element).getParent()));
            } else {
                UmlUi.LOG.warning("R2580: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5222cbf5-05a3-4325-96ea-2f00105c481e")
        private IAuditEntry checkR2580(Region region) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, region, null);
            
            List<DeepHistoryPseudoState> foundDhps = new ArrayList<>();
            
            for (DeepHistoryPseudoState dhps : region.getSub(DeepHistoryPseudoState.class)) {
                foundDhps.add(dhps);
            }
            
            if (foundDhps.size() > 1) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(region);
                linkedObjects.addAll(foundDhps);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
