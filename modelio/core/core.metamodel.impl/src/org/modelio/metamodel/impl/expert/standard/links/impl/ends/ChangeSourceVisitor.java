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

package org.modelio.metamodel.impl.expert.standard.links.impl.ends;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
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
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Visitor to change model link source.
 * <p>
 * Usage :
 * <li>set {@link #newSource}
 * <li>set {@link #oldSource}
 * <li>Call {@link MObject#accept(org.modelio.vcore.smkernel.mapi.MVisitor)}.
 */
@objid ("06685287-c3e1-4ca1-ba5e-95d8edc1a228")
public class ChangeSourceVisitor extends DefaultModelVisitor {
    /**
     * New source
     */
    @objid ("c5efa740-2b9a-4194-aeec-fd2c9e44d324")
    public MObject newSource;

    /**
     * Old source
     */
    @objid ("0abe3490-f497-47fd-85a7-146b284eb366")
    public MObject oldSource;

    /**
     * Constructor
     */
    @objid ("49084747-4e0c-4e3e-a229-8635449688eb")
    public ChangeSourceVisitor() {
        this(new InfraVisitor());
    }

    @objid ("4cfee264-fa99-4a20-aae9-dd57a98994e8")
    @Override
    public Object visitActivityEdge(ActivityEdge theActivityEdge) {
        theActivityEdge.setSource((ActivityNode) this.newSource);
        return null;
    }

    @objid ("a75fbe1a-0531-420d-bd1e-618fb6c24fe8")
    @Override
    public Object visitAssociation(Association theAssociation) {
        EList<AssociationEnd> ends = theAssociation.getEnd();
        if (ends.isEmpty()) {
            ICoreSession session = CoreSession.getSession(theAssociation);
            AssociationEnd associationEnd = session.getModel().getGenericFactory().create(AssociationEnd.class, theAssociation);
            associationEnd.setAssociation(theAssociation);
            return visitAssociationEnd(associationEnd);
        } else {
            return visitAssociationEnd(ends.get(0));
        }
    }

    @objid ("99495ef0-bd9a-4849-a91b-bec1f1194a54")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        AssociationEnd opposite = theAssociationEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theAssociationEnd);
            opposite = session.getModel().getGenericFactory().create(AssociationEnd.class, theAssociationEnd);
            theAssociationEnd.setOpposite(opposite);
            opposite.setOpposite(theAssociationEnd);
        
            // The association is incomplete, force the current end to be navigable by setting a temporary wrong target.
            theAssociationEnd.setTarget((Classifier) this.newSource);
        }
        
        Association association = theAssociationEnd.getAssociation();
        if (association == null) {
            ICoreSession session = CoreSession.getSession(theAssociationEnd);
            association = session.getModel().getGenericFactory().create(Association.class, theAssociationEnd);
        }
        
        // Make sure the association is referenced by both ends
        theAssociationEnd.setAssociation(association);
        opposite.setAssociation(association);
        
        if (opposite.isNavigable()) {
            opposite.setTarget((Classifier) this.newSource, true);
        } else {
            theAssociationEnd.setSource((Classifier) this.newSource, true);
        }
        return null;
    }

    @objid ("a46341b8-ede8-47c7-83da-e2caa83d057b")
    @Override
    public Object visitBinding(Binding theBinding) {
        if (this.newSource instanceof BindableInstance) {
            theBinding.setRole((BindableInstance) this.newSource);
        } else if (this.newSource instanceof NaryConnector) {
            theBinding.setConnectorRole((NaryConnector) this.newSource);
        } else if (this.newSource instanceof Connector) {
            theBinding.setConnectorEndRole((ConnectorEnd) this.newSource);
        } else if (this.newSource instanceof ConnectorEnd) {
            theBinding.setConnectorEndRole((ConnectorEnd) this.newSource);
        } else {
            throw new IllegalArgumentException(this.newSource + " cannot be the source of " + theBinding);
        }
        return null;
    }

    @objid ("64650012-3394-4f70-93c6-95299e1599f9")
    @Override
    public Object visitBpmnDataAssociation(final BpmnDataAssociation theBpmnDataAssociation) {
        // Empty all 'source' relationships
        theBpmnDataAssociation.getSourceRef().clear();
        theBpmnDataAssociation.setEndingEvent(null);
        theBpmnDataAssociation.setStartingActivity(null);
        
        // Fill appropriate relationship
        if (this.newSource instanceof BpmnActivity) {
            theBpmnDataAssociation.setStartingActivity((BpmnActivity) this.newSource);
        } else if (this.newSource instanceof BpmnCatchEvent) {
            theBpmnDataAssociation.setEndingEvent((BpmnCatchEvent) this.newSource);
        } else if (this.newSource instanceof BpmnItemAwareElement) {
            theBpmnDataAssociation.getSourceRef().add((BpmnItemAwareElement) this.newSource);
        }
        return null;
    }

    @objid ("a8e6ea55-4867-4333-a688-eade90a1598c")
    @Override
    public Object visitBpmnMessageFlow(final BpmnMessageFlow theBpmnMessageFlow) {
        if (this.newSource instanceof BpmnParticipant
                || this.newSource instanceof BpmnProcess
                || this.newSource instanceof BpmnEvent
                || this.newSource instanceof BpmnActivity
                || this.newSource instanceof BpmnLane
                || this.newSource instanceof BpmnTask) {
            theBpmnMessageFlow.setSourceRef((BpmnBaseElement) this.newSource);
        }
        return null;
    }

    @objid ("0a418f10-3008-49cf-bf43-3081a1462bef")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        if (this.newSource instanceof BpmnFlowNode) {
            theBpmnSequenceFlow.setSourceRef((BpmnFlowNode) this.newSource);
        }
        return null;
    }

    @objid ("2d4f4604-1c90-482a-868f-2433f4cba51e")
    @Override
    public Object visitClassAssociation(final ClassAssociation theClassAssociation) {
        theClassAssociation.setNaryAssociationPart((NaryAssociation) this.newSource);
        return null;
    }

    @objid ("6d8044ff-5225-4b77-91de-b0dddd18eed2")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel theChannel) {
        theChannel.setStart((CommunicationNode) this.newSource);
        return null;
    }

    @objid ("8e4e5c11-74d5-4290-a5f4-9fea6a090f5a")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        obj.setRealizingClassifier((Classifier) this.newSource);
        return null;
    }

    @objid ("d1dcfbd6-11bf-4903-a471-cbf9ffb4d4ea")
    @Override
    public Object visitConnector(Connector theConnector) {
        EList<LinkEnd> ends = theConnector.getLinkEnd();
        if (ends.isEmpty()) {
            ICoreSession session = CoreSession.getSession(theConnector);
            ConnectorEnd connectorEnd = session.getModel().getGenericFactory().create(ConnectorEnd.class, theConnector);
            connectorEnd.setLink(theConnector);
            return visitConnectorEnd(connectorEnd);
        } else {
            return visitConnectorEnd((ConnectorEnd) ends.get(0));
        }
    }

    @objid ("2fa32e86-4737-41dc-ad3b-7b8876d6fce4")
    @Override
    public Object visitConnectorEnd(ConnectorEnd theConnectorEnd) {
        ConnectorEnd opposite = (ConnectorEnd) theConnectorEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theConnectorEnd);
            opposite = session.getModel().getGenericFactory().create(ConnectorEnd.class, theConnectorEnd);
            theConnectorEnd.setOpposite(opposite);
            opposite.setOpposite(theConnectorEnd);
        
            // The connector is incomplete, force the current end to be navigable by setting a temporary wrong target.
            theConnectorEnd.setTarget((Instance) this.newSource);
        }
        
        Connector connector = (Connector) theConnectorEnd.getLink();
        if (connector == null) {
            ICoreSession session = CoreSession.getSession(theConnectorEnd);
            connector = session.getModel().getGenericFactory().create(Connector.class, theConnectorEnd);
        }
        
        // Make sure the connector is referenced by both ends
        theConnectorEnd.setLink(connector);
        opposite.setLink(connector);
        
        if (theConnectorEnd.getOpposite().isNavigable()) {
            theConnectorEnd.getOpposite().setTarget((Instance) this.newSource, true);
        } else {
            theConnectorEnd.setSource((Instance) this.newSource, true);
        }
        return null;
    }

    @objid ("de7c6701-4719-4252-8691-d641c4d71f23")
    @Override
    public Object visitDataFlow(DataFlow theDataFlow) {
        theDataFlow.setOwner((NameSpace) this.newSource);
        return null;
    }

    @objid ("21f422bc-3ed4-497d-8fb3-761950f100e7")
    @Override
    public Object visitElementImport(ElementImport theElementImport) {
        if (this.newSource instanceof NameSpace) {
            theElementImport.setImportingNameSpace((NameSpace) this.newSource);
        } else if (this.newSource instanceof Operation) {
            theElementImport.setImportingOperation((Operation) this.newSource);
        } else {
            throw new IllegalArgumentException(this.newSource + " cannot be the source of " + theElementImport);
        }
        return null;
    }

    @objid ("174c2e09-d388-44a2-8dcd-77ed15601421")
    @Override
    public Object visitExceptionHandler(final ExceptionHandler theExceptionHandler) {
        theExceptionHandler.setProtectedNode((ActivityAction) this.newSource);
        return null;
    }

    @objid ("ad6218dd-c9b4-4f72-8e27-d2e9f94a474e")
    @Override
    public Object visitGeneralization(Generalization theGeneralization) {
        theGeneralization.setSubType((NameSpace) this.newSource);
        return null;
    }

    @objid ("ca254881-413a-4ae1-b645-b1eb7113f2a7")
    @Override
    public Object visitInformationFlow(InformationFlow theInformationFlow) {
        theInformationFlow.getInformationSource().clear();
        
        theInformationFlow.getInformationSource().add((UmlModelElement) this.newSource);
        return null;
    }

    @objid ("755f32f2-f86c-4644-952e-0aad76470140")
    @Override
    public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
        theInterfaceRealization.setImplementer((NameSpace) this.newSource);
        return null;
    }

    @objid ("3f4199b1-4a9d-43b9-b734-ba63b048b89a")
    @Override
    public Object visitLink(Link theLink) {
        EList<LinkEnd> ends = theLink.getLinkEnd();
        if (ends.isEmpty()) {
            ICoreSession session = CoreSession.getSession(theLink);
            LinkEnd linkEnd = session.getModel().getGenericFactory().create(LinkEnd.class, theLink);
            linkEnd.setLink(theLink);
            return visitLinkEnd(linkEnd);
        } else {
            return visitLinkEnd(ends.get(0));
        }
    }

    @objid ("bc5f01c5-5c89-4158-bf9c-6933270293a0")
    @Override
    public Object visitLinkEnd(LinkEnd theLinkEnd) {
        LinkEnd opposite = theLinkEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theLinkEnd);
            opposite = session.getModel().getGenericFactory().create(LinkEnd.class, theLinkEnd);
            theLinkEnd.setOpposite(opposite);
            opposite.setOpposite(theLinkEnd);
        
            // The link is incomplete, force the current end to be navigable by setting a temporary wrong target.
            theLinkEnd.setTarget((Instance) this.newSource);
        }
        
        Link link = theLinkEnd.getLink();
        if (link == null) {
            ICoreSession session = CoreSession.getSession(theLinkEnd);
            link = session.getModel().getGenericFactory().create(Link.class, theLinkEnd);
        }
        
        // Make sure the link is referenced by both ends
        theLinkEnd.setLink(link);
        opposite.setLink(link);
        
        if (theLinkEnd.getOpposite().isNavigable()) {
            theLinkEnd.getOpposite().setTarget((Instance) this.newSource, true);
        } else {
            theLinkEnd.setSource((Instance) this.newSource, true);
        }
        return null;
    }

    @objid ("dc56a80b-c532-4cd8-a1d1-6b5527b0291f")
    @Override
    public Object visitManifestation(Manifestation theManifestation) {
        theManifestation.setOwner((Artifact) this.newSource);
        return null;
    }

    @objid ("38917b30-c905-48c7-be86-2437e90f0b72")
    @Override
    public Object visitMessage(Message theMessage) {
        theMessage.setSendEvent((MessageEnd) this.newSource);
        return null;
    }

    @objid ("9c6a0d93-4528-48fa-a6ad-97432d8208dd")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd theNaryAssociationEnd) {
        theNaryAssociationEnd.setOwner((Classifier) this.newSource);
        return null;
    }

    @objid ("143e4f00-2375-42c8-a43e-636772350b2e")
    @Override
    public Object visitNaryLinkEnd(NaryLinkEnd theNaryLinkEnd) {
        theNaryLinkEnd.setSource((Instance) this.newSource);
        return null;
    }

    @objid ("019d4a05-b051-4c5f-a214-57c6de7d4968")
    @Override
    public Object visitPackageImport(PackageImport thePackageImport) {
        if (this.newSource instanceof NameSpace) {
            thePackageImport.setImportingNameSpace((NameSpace) this.newSource);
        } else if (this.newSource instanceof Operation) {
            thePackageImport.setImportingOperation((Operation) this.newSource);
        } else {
            throw new ClassCastException();
        }
        return null;
    }

    @objid ("31bcded4-e1ee-49a0-9114-2b09feceff0c")
    @Override
    public Object visitPackageMerge(PackageMerge thePackageMerge) {
        thePackageMerge.setReceivingPackage((Package) this.newSource);
        return null;
    }

    @objid ("3c093e9e-7723-478a-9144-17fd037f8552")
    @Override
    public Object visitProvidedInterface(final ProvidedInterface theProvidedInterface) {
        theProvidedInterface.setProviding((Port) this.newSource);
        return null;
    }

    @objid ("6eb4c397-74f8-4572-84f5-3bcb66f4e3aa")
    @Override
    public Object visitRaisedException(RaisedException theRaisedException) {
        theRaisedException.setThrower((Operation) this.newSource);
        return null;
    }

    @objid ("b705c6c3-cace-428f-aa39-173eb5b70fae")
    @Override
    public Object visitRequiredInterface(final RequiredInterface theRequiredInterface) {
        theRequiredInterface.setRequiring((Port) this.newSource);
        return null;
    }

    @objid ("81cbb196-27fc-438b-b031-9b21196d6e7e")
    @Override
    public Object visitSubstitution(Substitution obj) {
        obj.setSubstitutingClassifier((Classifier) this.newSource);
        return null;
    }

    @objid ("42d1c4ad-bdeb-4191-885e-28437b731e62")
    @Override
    public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
        if (theTemplateBinding instanceof NameSpace) {
            theTemplateBinding.setBoundElement((NameSpace) this.newSource);
        } else if (theTemplateBinding instanceof Operation) {
            theTemplateBinding.setBoundOperation((Operation) this.newSource);
        } else {
            throw new ClassCastException();
        }
        return null;
    }

    @objid ("340fec5d-164f-47eb-a895-2928a7466137")
    @Override
    public Object visitTransition(Transition theTransition) {
        theTransition.setSource((StateVertex) this.newSource);
        return null;
    }

    @objid ("49a851bb-1766-410d-9570-cd049da49c30")
    @Override
    public Object visitUmlModelElement(UmlModelElement theModelElement) {
        throw new IllegalArgumentException(theModelElement + " is not a link element.");
    }

    @objid ("d6cd8f7e-563b-489e-b2c1-96c5dfbb58ca")
    @Override
    public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
        theUseCaseDependency.setOrigin((UseCase) this.newSource);
        return null;
    }

    @objid ("c8396bcf-eca2-40e1-be5e-1d6cabe34ee4")
    private ChangeSourceVisitor(InfraVisitor infraVisitor) {
        super(infraVisitor);
        infraVisitor.csv = this;
    }

    @objid ("6bac39c0-866a-49dc-867e-eb50689a167e")
    private static class InfraVisitor extends DefaultInfrastructureVisitor {
        @objid ("bcf3084f-bc7c-4d32-a385-ee14a7d8b334")
        private ChangeSourceVisitor csv;

        @objid ("7b2fa9f5-f41b-4001-87e1-b6848f796f60")
        public InfraVisitor() {
        }

        @objid ("100478f0-c09d-4274-bf4a-eb6c15b027e3")
        @Override
        public Object visitDependency(Dependency theDependency) {
            theDependency.setImpacted((ModelElement) this.csv.newSource);
            return null;
        }

    }

}
