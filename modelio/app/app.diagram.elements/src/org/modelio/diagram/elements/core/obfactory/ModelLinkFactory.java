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

package org.modelio.diagram.elements.core.obfactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Factory that creates link Elements between a source MObject and a target Element.
 */
@objid ("80a8a474-1dec-11e2-8cad-001ec947c8cc")
public class ModelLinkFactory implements IModelLinkFactory {
    @objid ("80a8a476-1dec-11e2-8cad-001ec947c8cc")
    private IMModelServices modelServices;

    /**
     * Create the link element factory.
     * 
     * @param modelServices A model factory this factory will use.
     */
    @objid ("80a8a477-1dec-11e2-8cad-001ec947c8cc")
    public ModelLinkFactory(IMModelServices modelServices) {
        this.modelServices = modelServices;
    }

    /**
     * Create a link model element from the given source to the destination.
     * 
     * @param metaclass The metaclass of the link to create.
     * @param source The source element
     * @param target The destination element
     * @return The created link element
     * @throws java.lang.IllegalArgumentException if the asked metaclass is not a link metaclass
     * @throws java.lang.ClassCastException if one of the source or destination element is a bad class for the link.
     */
    @objid ("80ab06b6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject createLink(MClass metaclass, MObject source, MObject target) throws IllegalArgumentException, ClassCastException {
        IModelFactoryService modelFactory = this.modelServices.getModelFactory();
        final MObject ret = modelFactory.createElement(metaclass);
        if (ret.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
            return (MObject) ret.accept(new ImplUmlVisitor(modelFactory.getFactory(IStandardModelFactory.class), source, target, null));
        } else {
            return (MObject) ret.accept(new ImplInfraVisitor(source, target));
        }
    }

    /**
     * Create a link model element owned by the given owner, from the given source to the destination.
     * <p>
     * This method is intended to be used to create links which have a source, a destination and an owner.
     * 
     * @param metaclass The metaclass of the link to create.
     * @param source The source element
     * @param target The destination element
     * @param owner The owner of the link.
     * @return The created link element
     * @throws java.lang.IllegalArgumentException if the asked metaclass is not a link metaclass
     * @throws java.lang.ClassCastException if one of the source, destination or owner element is a bad class for the link.
     */
    @objid ("80ab06bf-1dec-11e2-8cad-001ec947c8cc")
    public MObject createLink(final String metaclass, final MObject source, final MObject target, final MObject owner) throws IllegalArgumentException, ClassCastException {
        IModelFactoryService modelFactory = this.modelServices.getModelFactory();
        final MObject ret = modelFactory.createElement(metaclass);
        return (MObject) ret.accept(new ImplUmlVisitor(modelFactory.getFactory(IStandardModelFactory.class), source, target, owner));
    }

    /**
     * Private visitor based implementation.
     */
    @objid ("80ab06cc-1dec-11e2-8cad-001ec947c8cc")
    private static class ImplUmlVisitor extends DefaultModelVisitor {
        @objid ("80ab06d1-1dec-11e2-8cad-001ec947c8cc")
        private IStandardModelFactory modelFactory;

        @objid ("80ab06d2-1dec-11e2-8cad-001ec947c8cc")
        private MObject owner;

        @objid ("80ab06cf-1dec-11e2-8cad-001ec947c8cc")
        private MObject source;

        @objid ("80ab06d0-1dec-11e2-8cad-001ec947c8cc")
        private MObject target;

        @objid ("80ab06d3-1dec-11e2-8cad-001ec947c8cc")
        public ImplUmlVisitor(final IStandardModelFactory modelFactory, MObject source, MObject target, final MObject owner) {
            super(new ImplInfraVisitor(source, target));
            this.source = source;
            this.target = target;
            this.owner = owner;
            this.modelFactory = modelFactory;
        }

        @objid ("80ab06db-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitActivityEdge(ActivityEdge theEdge) {
            theEdge.setSource((ActivityNode) this.source);
            theEdge.setTarget((ActivityNode) this.target);
            return theEdge;
        }

        @objid ("80ab06e1-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitAssociationEnd(AssociationEnd sourceEnd) {
            final AssociationEnd targetEnd = this.modelFactory.createAssociationEnd();
            sourceEnd.setOpposite(targetEnd);
            targetEnd.setOpposite(sourceEnd);
            
            sourceEnd.setSource((Classifier) this.source);
            sourceEnd.setTarget((Classifier) this.target);
            
            final Association assoc = this.modelFactory.createAssociation();
            sourceEnd.setAssociation(assoc);
            targetEnd.setAssociation(assoc);
            return sourceEnd;
        }

        @objid ("80ab06e7-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitBinding(Binding theBinding) {
            return new BindingLinkFactory().createBinding(theBinding, this.source, this.target, this.owner);
        }

        @objid ("80ab06ed-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitBpmnDataAssociation(final BpmnDataAssociation theDataAssociation) {
            // Source
            if (this.source instanceof BpmnItemAwareElement) {
                theDataAssociation.getSourceRef().add((BpmnItemAwareElement) this.source);
            } else if (this.source instanceof BpmnActivity) {
                theDataAssociation.setStartingActivity((BpmnActivity) this.source);
            } else if (this.source instanceof BpmnCatchEvent) {
                theDataAssociation.setEndingEvent((BpmnCatchEvent) this.source);
            }
            
            // Target
            if (this.target instanceof BpmnItemAwareElement) {
                theDataAssociation.setTargetRef((BpmnItemAwareElement) this.target);
            } else if (this.target instanceof BpmnActivity) {
                theDataAssociation.setEndingActivity((BpmnActivity) this.target);
            } else if (this.target instanceof BpmnThrowEvent) {
                theDataAssociation.setStartingEvent((BpmnThrowEvent) this.target);
            }
            return theDataAssociation;
        }

        @objid ("80ab06f4-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitBpmnMessageFlow(BpmnMessageFlow theMessageeFlow) {
            MObject parent = this.source.getCompositionOwner();
            while (parent != null && !(parent instanceof BpmnCollaboration)) {
                parent = parent.getCompositionOwner();
            }
            
            if (parent != null) {
                BpmnCollaboration collaboration = (BpmnCollaboration) parent;
                theMessageeFlow.setCollaboration(collaboration);
            
                theMessageeFlow.setSourceRef((BpmnBaseElement) this.source);
                theMessageeFlow.setTargetRef((BpmnBaseElement) this.target);
            }
            return theMessageeFlow;
        }

        @objid ("80ab06fa-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitBpmnSequenceFlow(BpmnSequenceFlow theSequenceFlow) {
            if (this.source.getCompositionOwner() instanceof BpmnProcess) {
                theSequenceFlow.setContainer((BpmnProcess) this.source.getCompositionOwner());
            
            } else if (this.source.getCompositionOwner() instanceof BpmnSubProcess) {
                theSequenceFlow.setSubProcess((BpmnSubProcess) this.source.getCompositionOwner());
            }
            
            theSequenceFlow.setSourceRef((BpmnFlowNode) this.source);
            theSequenceFlow.setTargetRef((BpmnFlowNode) this.target);
            return theSequenceFlow;
        }

        @objid ("80ab0700-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitClassAssociation(final ClassAssociation theAssoc) {
            if (this.source instanceof Class) {
                if (this.target instanceof AssociationEnd) {
                    theAssoc.setAssociationPart(((AssociationEnd) this.target).getAssociation());
                } else if (this.target instanceof NaryAssociation) {
                    theAssoc.setNaryAssociationPart((NaryAssociation) this.target);
                }
                theAssoc.setClassPart((Class) this.source);
            } else {
                if (this.source instanceof AssociationEnd) {
                    theAssoc.setAssociationPart(((AssociationEnd) this.source).getAssociation());
                } else if (this.source instanceof NaryAssociation) {
                    theAssoc.setNaryAssociationPart((NaryAssociation) this.source);
                }
                theAssoc.setClassPart((Class) this.target);
            }
            return theAssoc;
        }

        @objid ("80ad690f-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitCollaborationUse(final CollaborationUse theCollaborationUse) {
            if (this.source instanceof NameSpace) {
                theCollaborationUse.setNRepresented((NameSpace) this.source);
            } else {
                theCollaborationUse.setORepresented((Operation) this.source);
            }
            
            theCollaborationUse.setType((Collaboration) this.target);
            return theCollaborationUse;
        }

        @objid ("1cebfa92-27d7-4f35-a388-bb9ce3227aa3")
        @Override
        public Object visitCommunicationChannel(CommunicationChannel theCommunicationChannel) {
            theCommunicationChannel.setStart((CommunicationNode) this.source);
            theCommunicationChannel.setEnd((CommunicationNode) this.target);
            return theCommunicationChannel;
        }

        @objid ("afc03ae7-f1a2-4247-a934-1b8250058bf1")
        @Override
        public Object visitComponentRealization(ComponentRealization obj) {
            // In the model a ComponentRealization is from a Classifier to a Component, but owned by the Component.
            // In the diagram it is drawn as an interface realization from the Classifier to the Component.
            // This link is not in the intuitive side so test for both sides.
            if (this.target instanceof Component) {
                // diagram way
                obj.setAbstraction((Component) this.target);
                obj.setRealizingClassifier((Classifier) this.source);
            } else if (this.source instanceof Component) {
                // Ownership way
                obj.setAbstraction((Component) this.source);
                obj.setRealizingClassifier((Classifier) this.target);
            } else {
                throw new IllegalArgumentException(obj.getMClass().getName() + " link creation from " + this.source + " to " + this.target + " is illegal.");
            }
            return obj;
        }

        @objid ("80ad6916-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitConnectorEnd(final ConnectorEnd sourceEnd) {
            final ConnectorEnd targetEnd = this.modelFactory.createConnectorEnd();
            sourceEnd.setOpposite(targetEnd);
            targetEnd.setOpposite(sourceEnd);
            
            sourceEnd.setSource((BindableInstance) this.source, true);
            sourceEnd.setTarget((BindableInstance) this.target, true);
            
            Connector connector = this.modelFactory.createConnector();
            sourceEnd.setLink(connector);
            targetEnd.setLink(connector);
            return sourceEnd;
        }

        @objid ("80ad6928-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitElementImport(ElementImport theElementImport) {
            theElementImport.setImportedElement((NameSpace) this.target);
            
            if (this.source instanceof NameSpace) {
                theElementImport.setImportingNameSpace((NameSpace) this.source);
            } else {
                theElementImport.setImportingOperation((Operation) this.source);
            }
            return theElementImport;
        }

        @objid ("80ad692e-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitExceptionHandler(ExceptionHandler theExceptionHandler) {
            theExceptionHandler.setProtectedNode((ActivityAction) this.source);
            theExceptionHandler.setExceptionInput((InputPin) this.target);
            return theExceptionHandler;
        }

        @objid ("80ad6934-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitGeneralization(Generalization theGeneralization) {
            theGeneralization.setSuperType((NameSpace) this.target);
            theGeneralization.setSubType((NameSpace) this.source);
            return theGeneralization;
        }

        @objid ("80ad693a-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitInformationFlow(final InformationFlow theFlow) {
            theFlow.getInformationSource().add((UmlModelElement) this.source);
            theFlow.getInformationTarget().add((UmlModelElement) this.target);
            theFlow.setOwner(getCommonNameSpace(this.source, this.target));
            return theFlow;
        }

        @objid ("80ad6941-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
            theInterfaceRealization.setImplemented((Interface) this.target);
            theInterfaceRealization.setImplementer((NameSpace) this.source);
            return theInterfaceRealization;
        }

        @objid ("80ad6947-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitLinkEnd(LinkEnd sourceEnd) {
            final LinkEnd targetEnd = this.modelFactory.createLinkEnd();
            
            sourceEnd.setOpposite(targetEnd);
            targetEnd.setOpposite(sourceEnd);
            
            sourceEnd.setSource((Instance) this.source, true);
            sourceEnd.setTarget((Instance) this.target, true);
            
            Link link = this.modelFactory.createLink();
            sourceEnd.setLink(link);
            targetEnd.setLink(link);
            return sourceEnd;
        }

        @objid ("80ad694d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitManifestation(Manifestation theManifestation) {
            theManifestation.setOwner((Artifact) this.source);
            theManifestation.setUtilizedElement((UmlModelElement) this.target);
            return theManifestation;
        }

        @objid ("80ad6953-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitPackageImport(PackageImport thePackageImport) {
            thePackageImport.setImportedPackage((Package) this.target);
            
            if (this.source instanceof NameSpace) {
                thePackageImport.setImportingNameSpace((NameSpace) this.source);
            } else {
                thePackageImport.setImportingOperation((Operation) this.source);
            }
            return thePackageImport;
        }

        @objid ("80afcb69-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitPackageMerge(PackageMerge thePackageMerge) {
            thePackageMerge.setMergedPackage((Package) this.target);
            thePackageMerge.setReceivingPackage((Package) this.source);
            return thePackageMerge;
        }

        @objid ("80afcb6f-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitRaisedException(final RaisedException theRaisedException) {
            theRaisedException.setThrower((Operation) this.source);
            theRaisedException.setThrownType((Classifier) this.target);
            return theRaisedException;
        }

        @objid ("48add215-d984-44e8-a237-8fe798f4b011")
        @Override
        public Object visitSubstitution(Substitution obj) {
            obj.setContract((Classifier) this.target);
            obj.setSubstitutingClassifier((Classifier) this.source);
            return obj;
        }

        @objid ("80afcb76-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitTemplateBinding(final TemplateBinding theTemplateBinding) {
            if (this.source instanceof NameSpace) {
                theTemplateBinding.setBoundElement((NameSpace) this.source);
            } else {
                theTemplateBinding.setBoundOperation((Operation) this.source);
            }
            
            if (this.target instanceof NameSpace) {
                theTemplateBinding.setInstanciatedTemplate((NameSpace) this.target);
            } else {
                theTemplateBinding.setInstanciatedTemplateOperation((Operation) this.target);
            }
            return theTemplateBinding;
        }

        @objid ("80afcb7d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitTransition(Transition theTransition) {
            theTransition.setTarget((StateVertex) this.target);
            theTransition.setSource((StateVertex) this.source);
            return theTransition;
        }

        @objid ("80afcb83-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
            theUseCaseDependency.setOrigin((UseCase) this.source);
            theUseCaseDependency.setTarget((UseCase) this.target);
            return theUseCaseDependency;
        }

        /**
         * Get the name space owning both model elements
         * 
         * @param aSource a model element
         * @param aTarget another model element
         * @return the common namespace
         * @throws java.lang.IllegalArgumentException if the 2 elements have no common namespace
         */
        @objid ("80afcb89-1dec-11e2-8cad-001ec947c8cc")
        private NameSpace getCommonNameSpace(final MObject aSource, final MObject aTarget) throws IllegalArgumentException {
            final ArrayList<MObject> l1 = new ArrayList<>(20);
            final ArrayList<MObject> l2 = new ArrayList<>(20);
            
            MObject el = aSource;
            while (el != null) {
                l1.add(el);
                el = el.getCompositionOwner();
            }
            
            el = aTarget;
            while (el != null) {
                l2.add(el);
                el = el.getCompositionOwner();
            }
            
            Collections.reverse(l1);
            Collections.reverse(l2);
            
            NameSpace ret = null;
            int i = 0;
            final int max = Math.min(l1.size(), l2.size());
            do {
                el = l1.get(i);
                if (el instanceof NameSpace) {
                    if (el.equals(l2.get(i))) {
                        ret = (NameSpace) el;
                    } else if (ret != null) {
                        return ret;
                    } else {
                        // No common namespace, return the last one
                        return (NameSpace) el;
                    }
                } else if (ret != null) {
                    return ret;
                }
                i++;
            
            } while (i < max);
            
            // Reaching this point means aSource == aTarget
            if (ret != null) {
                return ret;
            }
            
            // Should never reach this point.
            throw new IllegalArgumentException("No common namespace between " + aSource + " and " + aTarget);
        }

    }

    /**
     * Inner factory specialized for {@link Binding} links.
     * <p>
     * Guesses the collaboration use if not given. Supports:
     * <ul>
     * <li>Binding Link from CollaborationUse to the represented feature
     * <li>Binding link from the represented feature to the role
     * </ul>
     * 
     * @author cmarin
     */
    @objid ("80afcb91-1dec-11e2-8cad-001ec947c8cc")
    private static class BindingLinkFactory {
        /**
         * Constructor.
         */
        @objid ("80afcb94-1dec-11e2-8cad-001ec947c8cc")
        public BindingLinkFactory() {
        }

        /**
         * Initialize the binding from the given source and destination.
         * <p>
         * Guesses the collaboration use if not given. Supports:
         * <ul>
         * <li>Binding Link from CollaborationUse to the represented feature
         * <li>Binding link from the represented feature to the role
         * </ul>
         * 
         * @param theBinding The binding to initialize
         * @param source The binding source
         * @param target The binding target
         * @param owner The owner of the binding link
         * @return the initialized binding link.
         * @throws java.lang.IllegalArgumentException if the given arguments don't allow creation of a binding link.
         */
        @objid ("80afcb97-1dec-11e2-8cad-001ec947c8cc")
        public Object createBinding(final Binding theBinding, final MObject source, final MObject target, final MObject owner) throws IllegalArgumentException {
            if (source instanceof CollaborationUse) {
                // Link from CollaborationUse to the represented feature
                theBinding.setOwner((CollaborationUse) source);
                theBinding.setRepresentedFeature((UmlModelElement) target);
            } else {
                // Binding link from the represented feature to the role.
            
                final CollaborationUse collabUse;
                if (owner != null) {
                    collabUse = (CollaborationUse) owner;
                } else {
                    collabUse = getCollabUse(source, target);
                }
            
                if (target instanceof BindableInstance) {
                    theBinding.setRepresentedFeature((UmlModelElement) source);
                    theBinding.setRole((BindableInstance) target);
                    theBinding.setOwner(collabUse);
                } else if (target instanceof ConnectorEnd) {
                    theBinding.setRepresentedFeature((UmlModelElement) source);
                    theBinding.setConnectorEndRole((ConnectorEnd) target);
                    theBinding.setOwner(collabUse);
                }
            }
            return theBinding;
        }

        /**
         * Guess the collaboration use from the represented feature and the role.
         * 
         * @param feature the represented feature
         * @param role the role
         * @return the found collaboration use
         * @throws java.lang.IllegalArgumentException if no matching collaboration use was found
         */
        @objid ("80afcba4-1dec-11e2-8cad-001ec947c8cc")
        private CollaborationUse getCollabUse(final MObject feature, final MObject role) throws IllegalArgumentException {
            final Collaboration collab = getCollaborationOf(role);
            final Collection<CollaborationUse> uses = getCollabUsesOf(feature);
            
            for (CollaborationUse u : uses) {
                if (collab.equals(u.getType())) {
                    return u;
                }
            }
            
            throw new IllegalArgumentException("No matching collaboration use found.");
        }

        /**
         * Get the collaboration uses accessible from the given represented feature.
         * 
         * @param feature a represented feature
         * @return accessible collaboration uses
         * @throws java.lang.IllegalArgumentException if no collaboration uses were found
         */
        @objid ("80afcbac-1dec-11e2-8cad-001ec947c8cc")
        private Collection<CollaborationUse> getCollabUsesOf(final MObject feature) throws IllegalArgumentException {
            MObject container = feature.getCompositionOwner();
            while (container != null) {
                if (container instanceof NameSpace) {
                    return ((NameSpace) container).getOwnedCollaborationUse();
                } else if (container instanceof Operation) {
                    return ((Operation) container).getOwnedCollaborationUse();
                } else {
                    container = container.getCompositionOwner();
                }
            }
            throw new IllegalArgumentException("No collaboration use found for " + feature + " represented feature.");
        }

        /**
         * Get the collaboration that owns directly or indirectly the given role.
         * 
         * @param role a collaboration role
         * @return The collaboration owning the role.
         * @throws java.lang.IllegalArgumentException If the given role is not owned by a collaboration.
         */
        @objid ("80afcbb4-1dec-11e2-8cad-001ec947c8cc")
        private Collaboration getCollaborationOf(final MObject role) throws IllegalArgumentException {
            MObject container = role.getCompositionOwner();
            while (container != null) {
                if (container instanceof Collaboration) {
                    return (Collaboration) container;
                } else if (container instanceof NameSpace) {
                    container = null;
                } else {
                    container = container.getCompositionOwner();
                }
            }
            
            throw new IllegalArgumentException(role + " is not in a collaboration");
        }

    }

    @objid ("9384c621-8db3-4bc2-a419-a2019a22631f")
    private static class ImplInfraVisitor extends DefaultInfrastructureVisitor {
        @objid ("5139f4da-9963-4fb0-b45f-d8f597c8bd6d")
        private MObject source;

        @objid ("979d1698-8c2e-4e8c-ab2c-f789b82f95f2")
        private MObject target;

        @objid ("f59dfc08-4527-4f74-adfb-695e3951640b")
        public ImplInfraVisitor(MObject source, MObject target) {
            this.source = source;
            this.target = target;
        }

        @objid ("80ad691d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitDependency(Dependency theDependency) {
            theDependency.setDependsOn((ModelElement) this.target);
            theDependency.setImpacted((ModelElement) this.source);
            return theDependency;
        }

        @objid ("80ad6923-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitElement(Element theElement) {
            MClass metaclass = theElement.getMClass();
            MExpert mExpert = metaclass.getMetamodel().getMExpert();
            if (mExpert.isLink(metaclass)) {
                mExpert.setSource(theElement, null, this.source);
                mExpert.setTarget(theElement, null, this.target);
                return theElement;
            } else {
                throw new IllegalArgumentException(metaclass.getName() + " link creation is illegal.");
            }
        }

    }

}
