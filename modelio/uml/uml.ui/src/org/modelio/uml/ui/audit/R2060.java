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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkNameSpaceOwnedElements error
 */
@objid ("2de78d46-31b9-4518-bfe9-461d71055e60")
public class R2060 extends AbstractUmlRule {
    @objid ("3b3190ea-dfc2-4476-b7fd-c0bd800d9e0f")
    private static final String RULEID = "R2060";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("9488733a-b3ac-485b-a09d-a88374c80671")
    private CheckR2060 checkerInstance = null;

    @objid ("88ce654b-ef7e-4a25-916f-77fedf1932f4")
    @Override
    public String getRuleId() {
        return R2060.RULEID;
    }

    @objid ("c4c4f793-69f5-4ced-a176-6ad01b21924e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Namespaces
        plan.registerRule(Package.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        
        // Namespaces.Classifiers
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("981edddd-a7cb-4ae8-8e44-cfb1b23282ef")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5c23f0d1-4e9b-4d10-b27a-99e836338f58")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("03ef6e3e-5c48-478e-995c-2ddef3ec3742")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2060
     */
    @objid ("8e4e63b4-86be-4c5a-bdb8-8e316dff193d")
    public  R2060() {
        this.checkerInstance = new CheckR2060(this);
    }

    @objid ("50035a57-7fe9-497b-805f-993817100504")
    private static class CheckR2060 extends AbstractControl {
        @objid ("20ee36eb-22ab-4db3-8b89-dda859d9e6d6")
        public  CheckR2060(IRule rule) {
            super(rule);
        }

        @objid ("06385463-11fb-4bdc-afa1-9d1cf3630d83")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                // A NameSpace element was updated (potentially its name).
                // If it's owned by another NameSpace, we need to check the rule is still valid on this NameSpace.
                MObject owner = element.getCompositionOwner();
                if (owner instanceof NameSpace) {
                    diagnostic.addEntry(checkR2060((NameSpace) owner));
                }
                // We also need to check this NameSpace itself in case it was updated from a move.
                diagnostic.addEntry(checkR2060((NameSpace) element));
            } else {
                UmlUi.LOG.warning("R2060: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("60f7d85a-0c50-41b4-9f79-6d5da1ea2f04")
        private IAuditEntry checkR2060(final NameSpace nameSpace) {
            List<Object> linkedObjects = new ArrayList<>();
            IAuditEntry entry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, nameSpace, linkedObjects);
            
            Map<String, List<NameSpace>> duplicates = new HashMap<>();
            
            for (NameSpace me : nameSpace.getOwnedElement(NameSpace.class)) {
                String name = me.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<NameSpace>());
                }
                duplicates.get(me.getName()).add(me);
            }
            
            boolean dupFound = false;
            for (Entry<String, List<NameSpace>> e : duplicates.entrySet()) {
                if (e.getValue().size() > 1) {
                    dupFound = true;
            
                    // Rule failed
                    linkedObjects.addAll(e.getValue());
                }
            }
            
            if (dupFound) {
                linkedObjects.add(0, nameSpace);
                entry.setSeverity(this.rule.getSeverity());
            }
            return entry;
        }

    }

}
