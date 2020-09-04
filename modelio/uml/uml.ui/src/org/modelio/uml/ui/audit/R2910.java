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
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UseCaseDependencyChecker checkExtensionPointOwner
 */
@objid ("7d59a794-6755-42cc-b8d4-117c827eea91")
public class R2910 extends AbstractUmlRule {
    @objid ("a5f2d97c-0aa3-4c96-b71b-8b92f3b60f19")
    private static final String RULEID = "R2910";

    @objid ("5517f4de-3fad-4093-a1ab-509c293cf355")
    private static final String UseCaseDependencyExtendKind = "extend";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8476d19b-30f1-4bf8-9488-67c3503ff156")
    private CheckR2910 checkerInstance = null;

    @objid ("8fc3ecba-86e3-4a48-957d-3cac3d016a8a")
    @Override
    public String getRuleId() {
        return R2910.RULEID;
    }

    @objid ("71e81190-e081-4b12-93b5-3084f9de6e08")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(UseCaseDependency.MQNAME + R2910.UseCaseDependencyExtendKind, this,
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fd21b718-3f77-41a5-a194-c6becddfd88d")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("29274422-8ba3-48c1-86be-153d4f163ef9")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("145d332a-6832-4fc1-81f5-28cb5c5eb135")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2910
     */
    @objid ("6512e017-983c-4a54-826e-ab536759ca78")
    public R2910() {
        this.checkerInstance = new CheckR2910(this);
    }

    @objid ("ce9d8f79-cdf4-4ce5-a04b-209d6e4f1212")
    private static class CheckR2910 extends AbstractControl {
        @objid ("a3fbd88f-2665-4631-9638-42e8f59d71da")
        public CheckR2910(IRule rule) {
            super(rule);
        }

        @objid ("1f5b7ad7-6b1f-44f1-8690-d2716ca080c1")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof UseCaseDependency) {
                diagnostic.addEntry(checkR2910((UseCaseDependency) element));
            } else {
                UmlUi.LOG.warning("R2910: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("11c8be3a-8962-465e-8af2-cf89b09304f0")
        private IAuditEntry checkR2910(UseCaseDependency dependency) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, dependency, null);
            if (dependency.isStereotyped("ModelerModule", R2910.UseCaseDependencyExtendKind)) {
            
                List<ExtensionPoint> extensionPoints = new ArrayList<>(dependency.getExtensionLocation());
            
                if (!extensionPoints.isEmpty()) {
            
                    UseCase target = dependency.getTarget();
                    List<ExtensionPoint> ownedExtensions = new ArrayList<>(target.getOwnedExtension());
            
                    for (ExtensionPoint extensionPoint : ownedExtensions) {
                        if (extensionPoints.contains(extensionPoint)) {
                            extensionPoints.remove(extensionPoint);
                        }
                    }
            
                    if (!extensionPoints.isEmpty()) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(dependency.getOrigin());
                        linkedObjects.add(dependency.getTarget());
                        linkedObjects.add(dependency);
                        linkedObjects.addAll(extensionPoints);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

    }

}
