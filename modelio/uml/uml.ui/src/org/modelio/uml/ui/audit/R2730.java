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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TemplateBindingChecker checkSubstitutionOrder
 */
@objid ("81e5f9c2-3b0f-4cad-9d58-a8d5056831b0")
public class R2730 extends AbstractUmlRule {
    @objid ("2f5b8a38-dabd-45e0-9c84-79def37fa71d")
    private static final String RULEID = "R2730";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f1c84afc-5a8c-441b-a4e6-77133b3d0194")
    private CheckR2730 checkerInstance = null;

    @objid ("bb71dd31-97eb-4131-8b28-c29e3dabf517")
    @Override
    public String getRuleId() {
        return R2730.RULEID;
    }

    @objid ("42024335-1e8e-4e0d-8813-f83d88d1adef")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(TemplateBinding.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.CREATE);
        
        // Namespace
        plan.registerRule(Package.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.UPDATE);
        
        // Namespace.Classifier
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d0e224b0-2d8b-4ae3-9b24-9db86aaaaf74")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9fc87eaa-12e8-4aee-bffe-5da0f999be57")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4b962b10-d227-4005-a2fa-47afc6b1b949")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2730
     */
    @objid ("83f384c3-1b43-418f-93a1-a362ffde38b4")
    public  R2730() {
        this.checkerInstance = new CheckR2730(this);
    }

    @objid ("5960ef93-0176-4c3c-9c52-79892ed6146f")
    private static class CheckR2730 extends AbstractControl {
        @objid ("50151a4f-2189-4768-a33f-ad84efe636d0")
        public  CheckR2730(IRule rule) {
            super(rule);
        }

        @objid ("c9ceaf68-2db5-4641-8e2a-9310b7a75d55")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateParameter) {
                TemplateParameter parameter = (TemplateParameter) element;
                NameSpace ns = parameter.getParameterized();
                Operation op = parameter.getParameterizedOperation();
                if (ns != null) {
                    for (TemplateBinding binding : ns.getInstanciatingBinding()) {
                        diagnostic.addEntry(checkR2730(binding));
                    }
                } else if (op != null) {
                    for (TemplateBinding binding : op.getInstanciatingBinding()) {
                        diagnostic.addEntry(checkR2730(binding));
                    }
                }
            } else if (element instanceof NameSpace) {
                for (TemplateBinding binding : ((NameSpace) element).getInstanciatingBinding()) {
                    diagnostic.addEntry(checkR2730(binding));
                }
            } else if (element instanceof Operation) {
                for (TemplateBinding binding : ((Operation) element).getInstanciatingBinding()) {
                    diagnostic.addEntry(checkR2730(binding));
                }
            } else if (element instanceof TemplateBinding) {
                diagnostic.addEntry(checkR2730((TemplateBinding) element));
            } else {
                UmlUi.LOG.warning("R2730: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c3f44a28-9ab9-4990-b685-83007228c21e")
        private IAuditEntry checkR2730(final TemplateBinding tBinding) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    tBinding,
                    null);
            
            NameSpace instanciatedNs = tBinding.getInstanciatedTemplate();
            Operation instanciatedOp = tBinding.getInstanciatedTemplateOperation();
            
            List<TemplateParameter> params = new ArrayList<>();
            
            if (instanciatedNs != null) {
                params.addAll(instanciatedNs.getTemplate());
            } else if (instanciatedOp != null) {
                params.addAll(instanciatedOp.getTemplate());
            }
            
            List<TemplateParameterSubstitution> substitutions = tBinding.getParameterSubstitution();
            
            boolean ruleFailed = false;
            
            // Checks that the number of substitutions is not superior to the number of parameters.
            if ((substitutions.size() > params.size())) {
                ruleFailed = true;
            } else {
            
                int index = 0;
            
                for (TemplateParameterSubstitution tps : substitutions) {
                    boolean found = false;
                    while (!found) {
                        TemplateParameter param = params.get(index);
                        if (param.getName().equals(tps.getName())) {
                            // Found, ok next
                            found = true;
                            index++;
                        } else if (!param.getDefaultValue().isEmpty()) {
                            // Its optional, ok next
                            index++;
                        } else {
                            // Not found, and it was not optional
                            ruleFailed = true;
                            break;
                        }
                    }
                }
            
                // When we get out of the loop, we checked if all substitutions were matched...
                // Now we check if there is any Parameter that was not matched
                if (!ruleFailed) {
                    for (int i = index; i < params.size(); i++) {
                        // If the Parameter has no default value, it should have been matched earlier
                        if (params.get(i).getDefaultValue().isEmpty()) {
                            ruleFailed = true;
                        }
                    }
                }
            }
            
            if (ruleFailed) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(tBinding);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
