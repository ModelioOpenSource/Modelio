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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AttributeLinkChecker checkAttributeOrigin
 */
@objid ("86ec3765-c28e-4232-9853-cb9fe829854b")
public class R1490 extends AbstractUmlRule {
    @objid ("96f63905-0bfb-4f96-9919-cae03e269b24")
    private static final String RULEID = "R1490";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("52072899-9ece-466a-bcf1-e678c77c19b5")
    private CheckR1490 checkerInstance = null;

    @objid ("9a1c49dc-a5df-4a7b-9fad-547fdbda1e5a")
    @Override
    public String getRuleId() {
        return R1490.RULEID;
    }

    @objid ("441ec619-4f68-4f5f-94b1-a7525e2ba7b2")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // For updates of the base value
        plan.registerRule(Instance.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(AttributeLink.MQNAME, this, AuditTrigger.UPDATE);
        
        // For move (old parent) and delete of generalisations
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        
        // For creation and move (new parent) of generalisations
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c004ff23-9ccc-429f-b150-d2e292cdf5a0")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f9ccc0db-35e8-4fa2-b5d8-54d625a444b2")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("32bdb427-4f3d-4912-a951-24c5d2f91b97")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1490
     */
    @objid ("04862f9f-b83b-49bd-9315-73e5c7a13c64")
    public R1490() {
        this.checkerInstance = new CheckR1490(this);
    }

    @objid ("28c22b15-9af0-4a9f-a39e-ed764b69396e")
    private static class CheckR1490 extends AbstractControl {
        @objid ("b444dd9d-c765-42b4-8923-b50ee6c36a60")
        public CheckR1490(IRule rule) {
            super(rule);
        }

        @objid ("e3957dd5-7d93-4b0d-81b4-9bb69a079192")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Instance) {
                for (AttributeLink attLink : ((Instance) element).getSlot()) {
                    diagnostic.addEntry(checkR1490(attLink));
                }
            } else if (element instanceof AttributeLink) {
                diagnostic.addEntry(checkR1490((AttributeLink) element));
            } else if (element instanceof Class) {
                for (Attribute attribute : ((Class) element).getOwnedAttribute()) {
                    for (AttributeLink attLink : attribute.getOccurence()) {
                        diagnostic.addEntry(checkR1490(attLink));
                    }
                }
            } else if (element instanceof Generalization) {
                NameSpace ns = ((Generalization) element).getSuperType();
                if (ns instanceof Classifier) {
                    for (Attribute attribute : ((Classifier) ns).getOwnedAttribute(Attribute.class)) {
                        for (AttributeLink attLink : attribute.getOccurence()) {
                            diagnostic.addEntry(checkR1490(attLink));
                        }
                    }
                }
            } else {
                UmlUi.LOG.warning("R1490: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("269d3780-5d9b-428d-a9a5-2de0118c07fe")
        private IAuditEntry checkR1490(final AttributeLink attLink) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    attLink,
                    null);
            
            Attribute att = attLink.getBase();
            NameSpace ns = attLink.getAttributed().getBase();
            
            if (att != null && ns != null && ns instanceof Classifier) {
                Classifier classifier = (Classifier) ns;
                List<Classifier> superClasses = new ArrayList<>();
                findAllSuperClasses(classifier, superClasses);
                if (!findAttribute(att, superClasses)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(attLink);
                    linkedObjects.add(att);
                    linkedObjects.add(classifier);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("a790f74d-ba7d-4fe9-8df2-f9c5cb469bdb")
        private boolean findAttribute(final Attribute att, final List<Classifier> superClasses) {
            for (Classifier clazz : superClasses) {
                for (Attribute attribute : clazz.getOwnedAttribute(Attribute.class)) {
                    if (att.equals(attribute)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @objid ("521c2d21-62d0-4b80-9819-f0b3dc0ace80")
        private void findAllSuperClasses(final Classifier classifier, final List<Classifier> superClasses) {
            if (superClasses.contains(classifier)) {
                return;
            } else {
                superClasses.add(classifier);
                for (Generalization gen : classifier.getParent()) {
                    NameSpace ns = gen.getSuperType();
                    if (ns instanceof Classifier) {
                        findAllSuperClasses((Classifier) ns, superClasses);
                    }
                }
            }
        }

    }

}
