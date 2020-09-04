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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.model.ModelWalker;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PortChecker checkImplementation
 */
@objid ("1464f065-f0dd-4540-a14b-82306a0ac86d")
public class R2540 extends AbstractUmlRule {
    @objid ("f3db4f11-3545-4883-a1e5-6a3caffe4328")
    private static final String RULEID = "R2540";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("21964384-ddcb-41c9-9d30-38d5abe8e8ca")
    private CheckR2540 checkerInstance;

    @objid ("07b4a784-e4f2-4940-b31b-64f40f4a00ac")
    @Override
    public String getRuleId() {
        return R2540.RULEID;
    }

    @objid ("29bb2314-47a0-4cd9-9088-989c76e74817")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ProvidedInterface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InterfaceRealization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("19ee5657-01ef-4c0a-9c12-d97ebae1cc9a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9767ba52-b650-45d0-b805-b60d07a5a48d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a12706ef-2f78-4dbf-a530-6cab4204e4bd")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2540
     */
    @objid ("1fbf8316-c1fb-424f-bbe0-1a7cf14619b1")
    public R2540() {
        this.checkerInstance = new CheckR2540(this);
    }

    @objid ("83e9ef32-1af1-471f-b165-6268ff36a56c")
    private static class CheckR2540 extends AbstractControl {
        @objid ("e93890a9-a9e7-4092-8abd-a6b90960d9fa")
        private final ModelWalker<NameSpace> implementedInterfacesGetter;

        @objid ("fae95803-c38e-4c83-9642-a5639cf63a13")
        private final ModelWalker<NameSpace> classAndSubClassesGetter;

        @objid ("671d451a-5219-461e-9db4-65b22fd4c41f")
        public CheckR2540(IRule rule) {
            super(rule);
            
            this.implementedInterfacesGetter = new ModelWalker<NameSpace>()
                    .withCompositeTransition(NameSpace::getRealized, InterfaceRealization::getImplemented)
                    .withCompositeTransition(NameSpace::getParent, Generalization::getSuperType)
                    .withFilter(Interface.class::isInstance);
            
            this.classAndSubClassesGetter = new ModelWalker<NameSpace>()
                    .withCompositeTransition(NameSpace::getSpecialization, Generalization::getSubType)
                    .withSourcesIncluded(true)
                    ;
        }

        @objid ("ebb02ee0-c278-4d63-9d73-b97c34841252")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Port) {
                diagnostic.addEntry(checkR2540((Port) element));
            } else if (element instanceof ProvidedInterface) {
                diagnostic.addEntry(checkR2540(((ProvidedInterface) element).getProviding()));
            } else if (element instanceof Generalization) {
                NameSpace ns = ((Generalization) element).getSubType();
                for (NameSpace nn : this.classAndSubClassesGetter.from(ns).getTraversed()) {
                    for (Port port : nn.getRepresenting(Port.class)) {
                        diagnostic.addEntry(checkR2540(port));
                    }
                }
            } else if (element instanceof InterfaceRealization) {
                NameSpace ns = ((InterfaceRealization) element).getImplementer();
                //if (ns instanceof Class) {
                for (NameSpace nn : this.classAndSubClassesGetter.from(ns).getTraversed()) {
                    for (Port port : nn.getRepresenting(Port.class)) {
                        diagnostic.addEntry(checkR2540(port));
                    }
                }
                //}
            } else if (element instanceof Class) {
                for (Port port : ((Class) element).getRepresenting(Port.class)) {
                    diagnostic.addEntry(checkR2540(port));
                }
            } else {
                UmlUi.LOG.warning("R2540: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c6afd558-1f6d-4003-a5bf-fc48bcf4ce5a")
        private IAuditEntry checkR2540(final Port port) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, port, null);
            
            NameSpace ns = port.getBase();
            if (ns == null) {
                return auditEntry;
            }
            
            List<Interface> interfaces = new ArrayList<>();
            
            // Fetches all .Interfaces provided by the port
            for (ProvidedInterface pi : port.getProvided()) {
                interfaces.addAll(pi.getProvidedElement());
            }
            
            if (interfaces.isEmpty()) {
                // No Provided Interfaces found
                return auditEntry;
            }
            
            interfaces.removeAll(
                    this.implementedInterfacesGetter
                    .from(ns)
                    .getTraversed());
            
            if (!interfaces.isEmpty()) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity()); 
                ArrayList<Object> linkedObjects = new ArrayList<>(3 + interfaces.size());
                linkedObjects.add(port);
                linkedObjects.add(ns);
                linkedObjects.add(interfaces.size());
                linkedObjects.addAll(interfaces);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
