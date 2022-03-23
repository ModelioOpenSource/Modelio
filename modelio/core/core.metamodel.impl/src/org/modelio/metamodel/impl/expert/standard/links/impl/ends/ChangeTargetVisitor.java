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

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
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
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
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
 * Visitor to change model link target.
 * <p>
 * Usage :
 * <li>set {@link #newTarget}
 * <li>set {@link #oldTarget}
 * <li>Call {@link MObject#accept(org.modelio.vcore.smkernel.mapi.MVisitor)}.
 */
@objid ("9c1d2f2b-1682-4b77-a039-86ee78d143e9")
public class ChangeTargetVisitor extends DefaultModelVisitor {
    /**
     * New target
     */
    @objid ("5112e9fc-f215-4d9d-815d-3c99c918bcd2")
    public MObject newTarget;

    /**
     * Old target
     */
    @objid ("e4583e1b-c6ea-4fbf-a6ce-2040e89aac54")
    public MObject oldTarget;

    /**
     * Constructor
     */
    @objid ("d1302ed9-a61a-4317-9946-29e8e5948925")
    public  ChangeTargetVisitor() {
        this(new InfraVisitor());
    }

    @objid ("5f03ee71-7b28-4ae4-9568-fb8a19f5a0a2")
    @Override
    public Object visitActivityEdge(ActivityEdge theActivityEdge) {
        theActivityEdge.setTarget((ActivityNode) this.newTarget);
        return null;
    }

    @objid ("826276c4-5255-4edf-8d86-c281f9aed6e6")
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

    @objid ("410de8f6-d373-4390-8382-cc8f9ff68007")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        AssociationEnd opposite = theAssociationEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theAssociationEnd);
            opposite = session.getModel().getGenericFactory().create(AssociationEnd.class, theAssociationEnd);
            theAssociationEnd.setOpposite(opposite);
            opposite.setOpposite(theAssociationEnd);
        
            // The association is incomplete, force the opposite to be navigable by setting a temporary wrong target.
            opposite.setTarget((Classifier) this.newTarget);
        }
        
        Association association = theAssociationEnd.getAssociation();
        if (association == null) {
            ICoreSession session = CoreSession.getSession(theAssociationEnd);
            association = session.getModel().getGenericFactory().create(Association.class, theAssociationEnd);
        }
        
        // Make sure the association is referenced by both ends
        theAssociationEnd.setAssociation(association);
        opposite.setAssociation(association);
        
        if (theAssociationEnd.isNavigable()) {
            theAssociationEnd.setTarget((Classifier) this.newTarget, true);
        } else {
            theAssociationEnd.getOpposite().setSource((Classifier) this.newTarget, true);
        }
        return null;
    }

    @objid ("fb23814d-37c6-49d7-a9f0-c36d89558a55")
    @Override
    public Object visitBinding(Binding theBinding) {
        theBinding.setRepresentedFeature((UmlModelElement) this.newTarget);
        return null;
    }

    @objid ("bc6cda1c-7603-4e27-bba2-dacc3429e716")
    @Override
    public Object visitBpmnDataAssociation(final BpmnDataAssociation theBpmnDataAssociation) {
        // Empty all 'target' relationships
        theBpmnDataAssociation.setEndingActivity(null);
        theBpmnDataAssociation.setStartingEvent(null);
        theBpmnDataAssociation.setTargetRef(null);
        
        // Fill appropriate relationship
        if (this.newTarget instanceof BpmnActivity) {
            theBpmnDataAssociation.setEndingActivity((BpmnActivity) this.newTarget);
        } else if (this.newTarget instanceof BpmnThrowEvent) {
            theBpmnDataAssociation.setStartingEvent((BpmnThrowEvent) this.newTarget);
        } else if (this.newTarget instanceof BpmnItemAwareElement) {
            theBpmnDataAssociation.setTargetRef((BpmnItemAwareElement) this.newTarget);
        }
        return null;
    }

    @objid ("0e2eb7ec-c7c3-4322-90c1-b8d550038fb4")
    @Override
    public Object visitBpmnMessageFlow(final BpmnMessageFlow theBpmnMessageFlow) {
        if (this.newTarget instanceof BpmnParticipant
                || this.newTarget instanceof BpmnProcess
                || this.newTarget instanceof BpmnEvent
                || this.newTarget instanceof BpmnActivity
                || this.newTarget instanceof BpmnLane
                || this.newTarget instanceof BpmnTask) {
            theBpmnMessageFlow.setTargetRef((BpmnBaseElement) this.newTarget);
        }
        return null;
    }

    @objid ("ddc3b4c6-7dea-4db0-bbb4-131ba5d6b3b1")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        if (this.newTarget instanceof BpmnFlowNode) {
            theBpmnSequenceFlow.setTargetRef((BpmnFlowNode) this.newTarget);
        }
        return null;
    }

    @objid ("c32fa4d6-50de-4fb4-a112-ab4e602fb1d3")
    @Override
    public Object visitClassAssociation(final ClassAssociation theClassAssociation) {
        theClassAssociation.setClassPart((Class) this.newTarget);
        return null;
    }

    @objid ("25ef46aa-e230-434b-b53c-be66a079df57")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel theChannel) {
        theChannel.setEnd((CommunicationNode) this.newTarget);
        return null;
    }

    @objid ("58e9bf8e-99aa-4369-808c-16b729ca88e6")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        obj.setAbstraction((Component) this.newTarget);
        return null;
    }

    @objid ("23cd8636-52e4-4c38-b209-3bf3483bcd13")
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

    @objid ("3901b594-be9c-4c16-b0fe-f59d4837672d")
    @Override
    public Object visitConnectorEnd(ConnectorEnd theConnectorEnd) {
        ConnectorEnd opposite = (ConnectorEnd) theConnectorEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theConnectorEnd);
            opposite = session.getModel().getGenericFactory().create(ConnectorEnd.class, theConnectorEnd);
            theConnectorEnd.setOpposite(opposite);
            opposite.setOpposite(theConnectorEnd);
        
            // The connector is incomplete, force the opposite to be navigable by setting a temporary wrong target.
            opposite.setTarget((Instance) this.newTarget);
        }
        
        Connector connector = (Connector) theConnectorEnd.getLink();
        if (connector == null) {
            ICoreSession session = CoreSession.getSession(theConnectorEnd);
            connector = session.getModel().getGenericFactory().create(Connector.class, theConnectorEnd);
        }
        
        // Make sure the connector is referenced by both ends
        theConnectorEnd.setLink(connector);
        opposite.setLink(connector);
        
        if (theConnectorEnd.isNavigable()) {
            theConnectorEnd.setTarget((Instance) this.newTarget, true);
        } else {
            theConnectorEnd.getOpposite().setSource((Instance) this.newTarget, true);
        }
        return null;
    }

    @objid ("4d8d8034-aa9f-4e52-a177-391b4cedfd30")
    @Override
    public Object visitDataFlow(DataFlow theDataFlow) {
        theDataFlow.setDestination((NameSpace) this.newTarget);
        return null;
    }

    @objid ("6009d320-b18c-40b9-a7b7-10762dcb3d5c")
    @Override
    public Object visitElementImport(ElementImport theElementImport) {
        theElementImport.setImportedElement((NameSpace) this.newTarget);
        return null;
    }

    @objid ("587cf4d1-ae66-471f-817e-1162024d0b34")
    @Override
    public Object visitExceptionHandler(final ExceptionHandler theExceptionHandler) {
        theExceptionHandler.setExceptionInput((InputPin) this.newTarget);
        return null;
    }

    @objid ("efb1020c-8920-4dd2-a270-cda3409d00a8")
    @Override
    public Object visitGeneralization(Generalization theGeneralization) {
        theGeneralization.setSuperType((NameSpace) this.newTarget);
        return null;
    }

    @objid ("0a4c141a-a19a-4635-a78f-057b1ebbf495")
    @Override
    public Object visitInformationFlow(InformationFlow theInformationFlow) {
        theInformationFlow.getInformationTarget().clear();
        
        theInformationFlow.getInformationTarget().add((UmlModelElement) this.newTarget);
        return null;
    }

    @objid ("77c91030-091f-4950-9b67-fc98acf2f715")
    @Override
    public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
        theInterfaceRealization.setImplemented((Interface) this.newTarget);
        return null;
    }

    @objid ("f127b0b7-f78a-400e-94bd-9a36089d6f4a")
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

    @objid ("cfd75ab0-26bb-46b5-a662-a277343c4660")
    @Override
    public Object visitLinkEnd(LinkEnd theLinkEnd) {
        LinkEnd opposite = theLinkEnd.getOpposite();
        if (opposite == null) {
            ICoreSession session = CoreSession.getSession(theLinkEnd);
            opposite = session.getModel().getGenericFactory().create(LinkEnd.class, theLinkEnd);
            theLinkEnd.setOpposite(opposite);
            opposite.setOpposite(theLinkEnd);
        
            // The link is incomplete, force the opposite to be navigable by setting a temporary wrong target.
            opposite.setTarget((Instance) this.newTarget);
        }
        
        Link link = theLinkEnd.getLink();
        if (link == null) {
            ICoreSession session = CoreSession.getSession(theLinkEnd);
            link = session.getModel().getGenericFactory().create(Link.class, theLinkEnd);
        }
        
        // Make sure the link is referenced by both ends
        theLinkEnd.setLink(link);
        opposite.setLink(link);
        
        if (theLinkEnd.isNavigable()) {
            theLinkEnd.setTarget((Instance) this.newTarget, true);
        } else {
            theLinkEnd.getOpposite().setSource((Instance) this.newTarget, true);
        }
        return null;
    }

    @objid ("a7a61918-7ac7-4b39-b1d8-bb30ffaf3cb8")
    @Override
    public Object visitManifestation(Manifestation theManifestation) {
        theManifestation.setUtilizedElement((UmlModelElement) this.newTarget);
        return null;
    }

    @objid ("73dd2e9d-9f6b-489d-b2e0-144d0f4aabd2")
    @Override
    public Object visitMessage(Message theMessage) {
        theMessage.setReceiveEvent((MessageEnd) this.newTarget);
        return null;
    }

    @objid ("6fe02b4b-fcf8-4d9f-92d2-ff02a37cbdda")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd theNaryAssociationEnd) {
        theNaryAssociationEnd.setNaryAssociation((NaryAssociation) this.newTarget);
        return null;
    }

    @objid ("0445a030-76b5-421b-a54c-f7d035c64188")
    @Override
    public Object visitNaryLinkEnd(NaryLinkEnd theNaryLinkEnd) {
        theNaryLinkEnd.setNaryLink((NaryLink) this.newTarget);
        return null;
    }

    @objid ("9f5c8b8a-66e3-4a38-83d7-f6538d677445")
    @Override
    public Object visitPackageImport(PackageImport thePackageImport) {
        thePackageImport.setImportedPackage((Package) this.newTarget);
        return null;
    }

    @objid ("a0ace94c-6973-4e2a-8998-d3edd2e0d008")
    @Override
    public Object visitPackageMerge(PackageMerge thePackageMerge) {
        thePackageMerge.setMergedPackage((Package) this.newTarget);
        return null;
    }

    @objid ("702d4776-b716-4110-8a89-4eeef9b32386")
    @Override
    public Object visitProvidedInterface(final ProvidedInterface theProvidedInterface) {
        if (this.newTarget == null) {
            setTarget(theProvidedInterface, null);
            return null;
        }
        if (this.newTarget instanceof NaryConnectorEnd) {
            setTarget(theProvidedInterface, (NaryConnectorEnd) this.newTarget);
            return null;
        } else {
            // Not supported
            throw new IllegalArgumentException(this.newTarget + " is not a supported target for " + theProvidedInterface + ". Use a NaryConnectorEnd.");
        }
        
    }

    @objid ("d2e1a0ce-174f-47e0-9327-a7805a99c274")
    @Override
    public Object visitRaisedException(RaisedException theRaisedException) {
        theRaisedException.setThrownType((Classifier) this.newTarget);
        return null;
    }

    @objid ("1e272b9d-2f3b-46b5-959e-a8dd15b9c10d")
    @Override
    public Object visitRequiredInterface(final RequiredInterface theRequiredInterface) {
        if (this.newTarget == null) {
            setTarget(theRequiredInterface, null);
            return null;
        }
        if (this.newTarget instanceof NaryConnectorEnd) {
            setTarget(theRequiredInterface, (NaryConnectorEnd) this.newTarget);
            return null;
        } else {
            // Not supported
            throw new IllegalArgumentException(this.newTarget + " is not a supported target for " + theRequiredInterface + ". Use a NaryConnectorEnd.");
        }
        
    }

    @objid ("d8cbaa07-ae04-45c7-9633-3d9997e4302b")
    @Override
    public Object visitSubstitution(Substitution obj) {
        obj.setContract((Classifier) this.newTarget);
        return null;
    }

    @objid ("090e108f-5843-4a1d-bbc4-cc7a2f00d509")
    @Override
    public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
        if (this.newTarget instanceof NameSpace) {
            theTemplateBinding.setInstanciatedTemplate((NameSpace) this.newTarget);
        } else if (this.newTarget instanceof Operation) {
            theTemplateBinding.setInstanciatedTemplateOperation((Operation) this.newTarget);
        } else {
            throw new ClassCastException();
        }
        return null;
    }

    @objid ("76800565-f403-447d-9a23-e0d97d474850")
    @Override
    public Object visitTransition(Transition theTransition) {
        theTransition.setTarget((StateVertex) this.newTarget);
        return null;
    }

    @objid ("5de5c109-f2c0-4559-a3f7-25738942572d")
    @Override
    public Object visitUmlModelElement(UmlModelElement theModelElement) {
        throw new IllegalArgumentException(theModelElement + " is not a link element.");
    }

    @objid ("4c224b98-3fce-4c2b-9b23-94941ce9cf4b")
    @Override
    public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
        theUseCaseDependency.setTarget((UseCase) this.newTarget);
        return null;
    }

    @objid ("9c745908-af67-45c2-835b-1036e2c3e174")
    private void removeTarget(final RequiredInterface el) {
        for (LinkEnd end : new ArrayList<>(el.getProvider())) {
            final Link l = end.getLink();
            if (l != null) {
                l.delete();
            } else {
                end.delete();
            }
        }
        
        for (NaryLinkEnd end : new ArrayList<>(el.getNaryProvider())) {
            final NaryLink l = end.getNaryLink();
            if (l != null && l.getNaryLinkEnd().size() <= 2) {
                l.delete();
            } else {
                end.delete();
            }
        }
        
    }

    @objid ("2c2b3a5a-d058-441d-a149-05f871190334")
    private void removeTarget(final ProvidedInterface el) {
        for (LinkEnd end : new ArrayList<>(el.getConsumer())) {
            final Link l = end.getLink();
            if (l != null) {
                l.delete();
            } else {
                end.delete();
            }
        }
        
        for (NaryLinkEnd end : new ArrayList<>(el.getNaryConsumer())) {
            final NaryLink l = end.getNaryLink();
            if (l != null && l.getNaryLinkEnd().size() <= 2) {
                l.delete();
            } else {
                end.delete();
            }
        }
        
    }

    @objid ("4d6ec699-7ae6-48fa-82ce-42141801f054")
    private void setTarget(final RequiredInterface el, final NaryConnectorEnd target) {
        if (el.getNaryProvider().contains(target)) {
            return;
        }
        
        removeTarget(el);
        
        if (target != null) {
            el.getNaryProvider().add(target);
        }
        
    }

    @objid ("03589f43-a243-45db-adb1-25ad9920a016")
    private void setTarget(final ProvidedInterface el, final NaryConnectorEnd target) {
        if (el.getNaryConsumer().contains(target)) {
            return;
        }
        
        removeTarget(el);
        
        if (target != null) {
            el.getNaryConsumer().add(target);
        }
        
    }

    @objid ("978c124e-bce6-4df8-966c-eb871adc26ec")
    private  ChangeTargetVisitor(InfraVisitor infraVisitor) {
        super(infraVisitor);
        infraVisitor.csv = this;
        
    }

    @objid ("940afd42-7995-472b-9d12-6fcda6bf1dcc")
    private static class InfraVisitor extends DefaultInfrastructureVisitor {
        @objid ("f9dffff3-61e4-499c-9395-762b4e34f83b")
        private ChangeTargetVisitor csv;

        @objid ("36e1f608-a27c-43ea-b1bb-36fd0bf35378")
        @Override
        public Object visitDependency(Dependency theDependency) {
            theDependency.setDependsOn((ModelElement) this.csv.newTarget);
            return null;
        }

    }

}
