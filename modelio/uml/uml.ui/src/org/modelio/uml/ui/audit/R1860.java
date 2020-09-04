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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InterfaceChecker checkFeaturesVisibility
 */
@objid ("879c86b5-f86c-479c-9079-5161f135314d")
public class R1860 extends AbstractUmlRule {
    @objid ("8310cb5c-33df-4092-83c9-f629563bd68e")
    private static final String RULEID = "R1860";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("a0a3d369-4712-4a82-9abc-a905f69db332")
    private CheckR1860 checkerInstance = null;

    @objid ("55034925-27c0-4fc8-97b8-522a81e4615c")
    @Override
    public String getRuleId() {
        return R1860.RULEID;
    }

    @objid ("3aa06248-76b4-4ecc-9114-f6724bae39f3")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This cover the case of moving or deleting a Feature, which triggers an UPDATE on the old parent.
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        
        // IFeature
        // This cover the case of moving a Feature under a new parent.
        // This also cover the case of updating a Feature visibility.
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8e9649eb-2443-4d66-8275-741ed95483dd")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("093666b6-a955-49b6-8d6a-ba41e5fb8fc9")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9d8cb927-8cfc-453b-b3f5-5c35cc613fd0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1860
     */
    @objid ("d9b3f3e4-4d86-461c-8640-4b19e6002a25")
    public R1860() {
        this.checkerInstance = new CheckR1860(this);
    }

    @objid ("2db6bacd-ae82-4c95-9e2d-d78a3ea65eae")
    private static class CheckR1860 extends AbstractControl {
        @objid ("8e02dbf1-c631-40d4-b74e-1668ebfcb675")
        public CheckR1860(IRule rule) {
            super(rule);
        }

        @objid ("250e525e-814c-43a1-bcff-024f1d8723e5")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Interface) {
                diagnostic.addEntry(checkR1860((Interface) element));
            } else if (element instanceof Attribute ||
                    element instanceof Operation) {
                MObject owner = ((Feature) element).getCompositionOwner();
                if (owner instanceof Interface) {
                    diagnostic.addEntry(checkR1860((Interface) owner));
                }
            } else if (element instanceof AssociationEnd) {
                AssociationEnd assocEnd = (AssociationEnd) element;
                MObject owner = assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget();
                if (owner instanceof Interface) {
                    diagnostic.addEntry(checkR1860((Interface) owner));
                }
            } else {
                UmlUi.LOG.warning("R1860: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("5425a319-cbf3-4d2d-898d-0f4394f143f7")
        private IAuditEntry checkR1860(final Interface interfaze) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    interfaze,
                    null);
            
            List<Feature> nonPublicFeatures = new ArrayList<>();
            
            for (Attribute feature : interfaze.getOwnedAttribute()) {
                if (!feature.getVisibility().equals(VisibilityMode.PUBLIC) && !feature.getVisibility().equals(VisibilityMode.VISIBILITYUNDEFINED)) {
                    nonPublicFeatures.add(feature);
                }
            }
            
            for (Operation feature : interfaze.getOwnedOperation()) {
                if (!feature.getVisibility().equals(VisibilityMode.PUBLIC) && !feature.getVisibility().equals(VisibilityMode.VISIBILITYUNDEFINED)) {
                    nonPublicFeatures.add(feature);
                }
            }
            
            for (AssociationEnd feature : interfaze.getOwnedEnd()) {
                if (!feature.getVisibility().equals(VisibilityMode.PUBLIC) && !feature.getVisibility().equals(VisibilityMode.VISIBILITYUNDEFINED)) {
                    nonPublicFeatures.add(feature);
                }
            }
            
            if (!nonPublicFeatures.isEmpty()) {
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(interfaze);
                for (Feature feature : nonPublicFeatures) {
                    linkedObjects.add(feature);
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
