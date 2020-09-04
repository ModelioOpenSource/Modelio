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

package org.modelio.diagram.editor.handlers.unmask;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class aggregates several sets of links, computed from a root element.
 */
@objid ("65edb2f5-33f7-11e2-95fe-001ec947c8cc")
public class LinkPositionSet {
    @objid ("048cac77-1917-4e33-87cc-bb4fe18bc98c")
    private final LinkFinderVisitor visitor;

    /**
     * Constructor building the position set from a root element.
     * 
     * @param theRoot The element to compute the links from.
     * @param unmaskStructuringLink Whether or not the computed links must be structural.
     * @param unmaskedElements Each computed link must start or target an element from this list. A <code>null</code>value indicates no restrictions: all links related to theRoot are accepted.
     */
    @objid ("65f0154d-33f7-11e2-95fe-001ec947c8cc")
    public LinkPositionSet(final MObject theRoot, final boolean unmaskStructuringLink, final Set<MObject> unmaskedElements) {
        this.visitor = new LinkFinderVisitor(unmaskStructuringLink, unmaskedElements);
        theRoot.accept(this.visitor);
    }

    @objid ("65f01558-33f7-11e2-95fe-001ec947c8cc")
    public Set<MObject> getBottomLinks() {
        return this.visitor.bottomLinks;
    }

    @objid ("65f0155e-33f7-11e2-95fe-001ec947c8cc")
    public Set<MObject> getLeftLinks() {
        return this.visitor.leftLinks;
    }

    @objid ("65f01564-33f7-11e2-95fe-001ec947c8cc")
    public Set<MObject> getRightLinks() {
        return this.visitor.rightLinks;
    }

    @objid ("65f0156a-33f7-11e2-95fe-001ec947c8cc")
    public Set<MObject> getTopLinks() {
        return this.visitor.topLinks;
    }

    @objid ("65f01570-33f7-11e2-95fe-001ec947c8cc")
    private static class LinkFinderVisitor extends DefaultModelVisitor {
        @objid ("65f01573-33f7-11e2-95fe-001ec947c8cc")
        private boolean unmaskStructuringLink;

        @objid ("65f01574-33f7-11e2-95fe-001ec947c8cc")
        private Set<MObject> unmaskedElements;

        @objid ("65edb2f7-33f7-11e2-95fe-001ec947c8cc")
        protected Set<MObject> leftLinks = new HashSet<>();

        @objid ("65f01544-33f7-11e2-95fe-001ec947c8cc")
        protected Set<MObject> rightLinks = new HashSet<>();

        @objid ("65f01547-33f7-11e2-95fe-001ec947c8cc")
        protected Set<MObject> topLinks = new HashSet<>();

        @objid ("65f0154a-33f7-11e2-95fe-001ec947c8cc")
        protected Set<MObject> bottomLinks = new HashSet<>();

        @objid ("65f01577-33f7-11e2-95fe-001ec947c8cc")
        public LinkFinderVisitor(final boolean unmaskStructuringLink, final Set<MObject> unmaskedElements) {
            super();
            this.infrastructureVisitor = new DefaultInfrastructureVisitor() {
                @objid ("66379be2-33f7-11e2-95fe-001ec947c8cc")
                @Override
                public Object visitModelElement(final ModelElement child) {
                    if (!unmaskStructuringLink) {
                        for (Dependency i : child.getDependsOnDependency()) {
                            ModelElement target = i.getDependsOn();
                            addRight(i, target);
                        }
                    }
                    return super.visitModelElement(child);
                }
            };
            this.unmaskStructuringLink = unmaskStructuringLink;
            this.unmaskedElements = unmaskedElements;
        }

        @objid ("65f0157f-33f7-11e2-95fe-001ec947c8cc")
        private void addLeft(final MObject elt, final MObject target) {
            if (target != null) {
                if (this.unmaskedElements == null || this.unmaskedElements.contains(target)) {
                    this.leftLinks.add(elt);
                }
            }
        }

        @objid ("65f01585-33f7-11e2-95fe-001ec947c8cc")
        protected void addRight(final MObject elt, final MObject target) {
            if (target != null) {
                if (this.unmaskedElements == null || this.unmaskedElements.contains(target)) {
                    this.rightLinks.add(elt);
                }
            }
        }

        @objid ("65f0158b-33f7-11e2-95fe-001ec947c8cc")
        private void addTop(final MObject elt, final MObject target) {
            if (target != null) {
                if (this.unmaskedElements == null || this.unmaskedElements.contains(target)) {
                    this.topLinks.add(elt);
                }
            }
        }

        @objid ("65f01591-33f7-11e2-95fe-001ec947c8cc")
        private void addBottom(final MObject elt, final MObject target) {
            if (target != null) {
                if (this.unmaskedElements == null || this.unmaskedElements.contains(target)) {
                    this.bottomLinks.add(elt);
                }
            }
        }

        @objid ("65f277e5-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitActivityAction(final ActivityAction child) {
            if (this.unmaskStructuringLink) {
                for (ExceptionHandler i : child.getHandler()) {
                    addRight(i, i);
                }
            }
            return super.visitActivityAction(child);
        }

        @objid ("65f4da15-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitActivityNode(final ActivityNode child) {
            if (this.unmaskStructuringLink) {
                for (ActivityEdge i : child.getOutgoing()) {
                    addRight(i, i.getTarget());
                }
            }
            return super.visitActivityNode(child);
        }

        @objid ("65f4da47-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitArtifact(final Artifact child) {
            if (this.unmaskStructuringLink) {
                for (Manifestation i : child.getUtilized()) {
                    addRight(i, i.getUtilizedElement());
                }
            }
            return super.visitArtifact(child);
        }

        @objid ("65f99ed9-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitBpmnBaseElement(final BpmnBaseElement child) {
            if (this.unmaskStructuringLink) {
                for (BpmnMessageFlow i : child.getOutgoingFlow()) {
                    addRight(i, i.getTargetRef());
                }
            
                for (BpmnMessageFlow i : child.getIncomingFlow()) {
                    addLeft(i, i.getSourceRef());
                }
            
            }
            return super.visitBpmnBaseElement(child);
        }

        @objid ("65fc011f-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitBpmnCatchEvent(final BpmnCatchEvent child) {
            if (this.unmaskStructuringLink) {
                for (BpmnDataAssociation i : child.getDataOutputAssociation()) {
                    addRight(i, i.getTargetRef());
                }
            }
            return super.visitBpmnCatchEvent(child);
        }

        @objid ("65fc0129-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitBpmnCollaboration(final BpmnCollaboration child) {
            if (this.unmaskStructuringLink) {
                for (BpmnMessageFlow i : child.getMessageFlow()) {
                    addRight(i, i.getTargetRef());
                }
            }
            return super.visitBpmnCollaboration(child);
        }

        @objid ("66032817-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitBpmnFlowNode(final BpmnFlowNode child) {
            if (this.unmaskStructuringLink) {
                for (BpmnSequenceFlow i : child.getOutgoing()) {
                    addRight(i, i.getTargetRef());
                }
            
                for (BpmnSequenceFlow i : child.getIncoming()) {
                    addLeft(i, i.getSourceRef());
                }
            }
            return super.visitBpmnFlowNode(child);
        }

        @objid ("6611762f-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitBpmnThrowEvent(final BpmnThrowEvent child) {
            if (this.unmaskStructuringLink) {
                for (BpmnDataAssociation i : child.getDataInputAssociation()) {
                    addRight(i, i.getTargetRef());
                }
            }
            return super.visitBpmnThrowEvent(child);
        }

        @objid ("6613d8c2-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitClassifier(final Classifier child) {
            if (this.unmaskStructuringLink) {
                for (AssociationEnd i : child.getTargetingEnd()) {
                    addLeft(i, i.getTarget());
                }
            
                for (AssociationEnd i : child.getOwnedEnd()) {
                    addRight(i, i.getTarget());
                }
            
                for (NaryAssociationEnd i : child.getOwnedNaryEnd()) {
                    addRight(i, i.getOwner());
                }
            
                for (ComponentRealization i : child.getRealizedComponent()) {
                    addTop(i, i.getAbstraction());
                }
            } else {
                for (Substitution i : child.getSubstitued()) {
                    addRight(i, i.getContract());
                }
            }
            return super.visitClassifier(child);
        }

        @objid ("66163af8-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitCollaborationUse(final CollaborationUse child) {
            if (this.unmaskStructuringLink) {
                for (Binding i : child.getRoleBinding()) {
                    addRight(i, i);
                }
            }
            return super.visitCollaborationUse(child);
        }

        @objid ("66189d50-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitCommunicationNode(final CommunicationNode child) {
            if (this.unmaskStructuringLink) {
                for (CommunicationChannel i : child.getStarted()) {
                    addRight(i, i.getEnd());
                }
            
                for (CommunicationChannel i : child.getEnded()) {
                    addLeft(i, i.getStart());
                }
            }
            return super.visitCommunicationNode(child);
        }

        @objid ("662e1267-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitInstance(final Instance child) {
            if (this.unmaskStructuringLink) {
                for (LinkEnd i : child.getOwnedEnd()) {
                    if (i.isNavigable()) {
                        addRight(i, i.getTarget());
                    } else {
                        addLeft(i, i.getTarget());
                    }
                }
                for (NaryLinkEnd i : child.getOwnedNaryEnd()) {
                    addRight(i, i.getSource());
                }
            }
            return super.visitInstance(child);
        }

        @objid ("663074ca-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitInterface(final Interface child) {
            if (this.unmaskStructuringLink) {
                for (InterfaceRealization i : child.getImplementedLink()) {
                    addTop(i, i.getImplementer());
                }
            }
            return super.visitInterface(child);
        }

        @objid ("6639fe28-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitNameSpace(final NameSpace child) {
            if (this.unmaskStructuringLink) {
                for (Generalization i : child.getParent()) {
                    addTop(i, i.getSuperType());
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (Generalization i : child.getSpecialization()) {
                    addBottom(i, i.getSubType());
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (InterfaceRealization i : child.getRealized()) {
                    addTop(i, i.getImplemented());
                }
            }
            
            if (!this.unmaskStructuringLink) {
                for (DataFlow i : child.getOwnedDataFlow()) {
                    addRight(i, i.getDestination());
                }
            }
            
            if (!this.unmaskStructuringLink) {
                for (PackageImport i : child.getOwnedPackageImport()) {
                    addRight(i, i.getImportingNameSpace());
                    addRight(i, i.getImportingOperation());
                }
            }
            
            if (!this.unmaskStructuringLink) {
                for (ElementImport i : child.getOwnedImport()) {
                    addRight(i, i.getImportedElement());
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (TemplateBinding i : child.getTemplateInstanciation()) {
                    addRight(i, i.getInstanciatedTemplate());
                    addRight(i, i.getInstanciatedTemplateOperation());
                }
            }
            return super.visitNameSpace(child);
        }

        @objid ("663ec2e6-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitOperation(final Operation child) {
            if (!this.unmaskStructuringLink) {
                for (ElementImport i : child.getOwnedImport()) {
                    addRight(i, i.getImportedElement());
                }
            }
            
            if (!this.unmaskStructuringLink) {
                for (PackageImport i : child.getOwnedPackageImport()) {
                    addRight(i, i.getImportedPackage());
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (RaisedException i : child.getThrown()) {
                    addRight(i, i.getThrownType());
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (TemplateBinding i : child.getTemplateInstanciation()) {
                    addRight(i, i.getInstanciatedTemplate());
                    addRight(i, i.getInstanciatedTemplateOperation());
                }
            }
            return super.visitOperation(child);
        }

        @objid ("663ec2fa-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitPackage(final Package child) {
            if (!this.unmaskStructuringLink) {
                for (PackageMerge i : child.getMerge()) {
                    addRight(i, i.getMergedPackage());
                }
            }
            return super.visitPackage(child);
        }

        @objid ("6641255d-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitPort(final Port child) {
            if (this.unmaskStructuringLink) {
                for (ProvidedInterface i : child.getProvided()) {
                    addRight(i, i);
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (RequiredInterface i : child.getRequired()) {
                    addRight(i, i);
                }
            }
            
            if (this.unmaskStructuringLink) {
                for (LinkEnd i : child.getTargetingEnd()) {
                    addLeft(i, i.getTarget());
                }
            
                for (LinkEnd i : child.getOwnedEnd()) {
                    addRight(i, i.getTarget());
                }
            }
            return super.visitPort(child);
        }

        @objid ("664d1120-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Object visitStateVertex(final StateVertex child) {
            if (this.unmaskStructuringLink) {
                for (Transition i : child.getOutGoing()) {
                    if (!(i instanceof InternalTransition)) {
                        addRight(i, i.getTarget());
                    }
                }
            }
            return super.visitStateVertex(child);
        }

        @objid ("99e10a98-5ff4-4012-b53a-ad467a9e30af")
        @Override
        public Object visitUmlModelElement(final UmlModelElement child) {
            if (!this.unmaskStructuringLink) {
                for (InformationFlow i : child.getSentInfo()) {
                    // Ignore info flow with 0 or n > 1 targets
                    if (i.getInformationTarget().size() == 1) {
                        addRight(i, i.getInformationTarget().get(0));
                    }
                }
            }
            return super.visitUmlModelElement(child);
        }

        @objid ("6fcb805c-e121-4176-b37b-a8ce16320e0c")
        @Override
        public Object visitBpmnActivity(final BpmnActivity child) {
            if (this.unmaskStructuringLink) {
                for (BpmnDataAssociation i : child.getDataInputAssociation()) {
                    addRight(i, i.getTargetRef());
                }
            }
            return super.visitBpmnActivity(child);
        }

        @objid ("0ccdb728-be01-4719-9774-e8698644eb08")
        @Override
        public Object visitComponent(final Component child) {
            if (this.unmaskStructuringLink) {
                for (ComponentRealization i : child.getRealization()) {
                    addTop(i, i.getRealizingClassifier());
                }
            }
            return super.visitComponent(child);
        }

    }

}
