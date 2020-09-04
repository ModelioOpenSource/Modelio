/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InteractionModelChecker checkR61068 warning
 */
@objid ("dfbfd356-31c6-454a-a130-58abef8178a4")
public class R1810 extends AbstractUmlRule {
    @objid ("e91aef67-13e0-47b2-b87f-2c0dae178ed2")
    private static final String RULEID = "R1810";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("87ab2dd7-981e-49a8-9c46-778bc41a74f0")
    private CheckR1810 checkerInstance = null;

    @objid ("f7500e53-c8ca-46b3-a76c-6e66b60eac01")
    @Override
    public String getRuleId() {
        return R1810.RULEID;
    }

    @objid ("a4b7c9ae-0cfc-4de6-aa89-244669de0bbb")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Gate.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InteractionUse.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("13fac7c6-c9ee-4841-a381-4ba03037f976")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6b590e85-4a18-4e41-b549-efa44cc12f81")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5e7f9323-6f39-420f-9376-13353fc7039e")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1810
     */
    @objid ("16842687-60c4-4fc0-9938-026297e4c1ec")
    public R1810() {
        this.checkerInstance = new CheckR1810(this);
    }

    @objid ("59771e41-dc1a-43a9-a6d8-4233978e887b")
    private static class CheckR1810 extends AbstractControl {
        @objid ("0020f339-ecd0-4e69-bbf5-69a10203c2dd")
        public CheckR1810(IRule rule) {
            super(rule);
        }

        @objid ("4fecf84f-5177-4d73-bab4-b6569e6ab8da")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Gate) {
                diagnostic.addEntry(checkR1810((Gate) element));
            } else if (element instanceof InteractionUse) {
                for (Gate gate : ((InteractionUse) element).getActualGate()) {
                    diagnostic.addEntry(checkR1810(gate));
                }
            } else {
                UmlUi.LOG.warning("R1810: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("b36f01b4-9a08-40bd-a1f6-3c4ced938708")
        private IAuditEntry checkR1810(final Gate gate) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    gate,
                    null);
            
            Gate formal = gate.getFormal();
            InteractionUse interactionUse = gate.getOwnerUse();
            
            if (formal != null && interactionUse != null) {
                Interaction interaction = interactionUse.getRefersTo();
                if (interaction != null && !interaction.equals(formal.getOwnerInteraction())) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(gate);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
