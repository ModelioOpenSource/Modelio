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

package org.modelio.metamodel.impl.expert.standard.links.impl.ends;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
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
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Visitor to get the source of a model link.
 */
@objid ("e26f9554-13d3-4b63-9ab7-5203664a1ca2")
public class GetSourceVisitor extends DefaultModelVisitor {
    /**
     * Constructor
     */
    @objid ("5971b6f6-8c09-4667-a999-39fb59f16ac8")
    public GetSourceVisitor() {
        super(new DefaultInfrastructureVisitor() {
            @Override
            public Object visitDependency(Dependency theDependency) {
                return theDependency.getImpacted();
            }
        });
    }

    @objid ("b156ccf1-398e-4a20-9ff1-0273576a5ddf")
    @Override
    public Object visitActivityEdge(ActivityEdge theActivityEdge) {
        return theActivityEdge.getSource();
    }

    @objid ("4bcfdcd3-c197-426c-bd29-d1dc0c5b0f2e")
    @Override
    public Object visitAssociation(Association theAssociation) {
        EList<AssociationEnd> ends = theAssociation.getEnd();
        if (ends.isEmpty()) {
            return null;
        } else {
            return visitAssociationEnd(ends.get(0));
        }
    }

    @objid ("c518cd1b-9d65-4c16-b940-459d28db1777")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        if (theAssociationEnd.isNavigable()) {
            return theAssociationEnd.getSource();
        } else {
            return theAssociationEnd.getOpposite().getTarget();
        }
    }

    @objid ("98c1c3e9-bc60-4cbd-abaa-f194cb2d514b")
    @Override
    public Object visitBinding(Binding theBinding) {
        MObject src = theBinding.getRole();
        if (src == null) {
            src = theBinding.getConnectorRole();
        }
        if (src == null) {
            src = theBinding.getConnectorEndRole();
        }
        return src;
    }

    @objid ("dbc8e742-ec2a-4666-882c-3cd801d510c0")
    @Override
    public Object visitBpmnDataAssociation(final BpmnDataAssociation theBpmnDataAssociation) {
        if (theBpmnDataAssociation.getStartingActivity() != null) {
            return theBpmnDataAssociation.getStartingActivity();
        } else if (theBpmnDataAssociation.getEndingEvent() != null) {
            return theBpmnDataAssociation.getEndingEvent();
        } else if (theBpmnDataAssociation.getSourceRef().size() > 0) {
            return theBpmnDataAssociation.getSourceRef().get(0);
        } else {
            return null;
        }
    }

    @objid ("ba8541d5-3d84-42ba-bb81-bebf05276b5f")
    @Override
    public Object visitBpmnMessageFlow(final BpmnMessageFlow theBpmnMessageFlow) {
        return theBpmnMessageFlow.getSourceRef();
    }

    @objid ("b02e3b44-fcb3-42c6-b5ed-0b2495d5d48e")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        return theBpmnSequenceFlow.getSourceRef();
    }

    @objid ("c9798f24-a6b4-49fc-87b4-ed40304ab6cd")
    @Override
    public Object visitClassAssociation(final ClassAssociation theClassAssociation) {
        return theClassAssociation.getAssociationPart();
    }

    @objid ("b9e43ee7-75fd-4536-9799-bf458cd7977c")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel theChannel) {
        return theChannel.getStart();
    }

    @objid ("bd795558-545a-4842-876a-601a0a1882f8")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        return obj.getRealizingClassifier();
    }

    @objid ("57f5b5be-4074-477c-9834-34c12140d5be")
    @Override
    public Object visitConnector(Connector theConnector) {
        EList<LinkEnd> ends = theConnector.getLinkEnd();
        if (ends.isEmpty()) {
            return null;
        } else {
            return visitConnectorEnd((ConnectorEnd) ends.get(0));
        }
    }

    @objid ("0bc5758a-6107-443f-85df-62836f6ef6ad")
    @Override
    public Object visitControlFlow(ControlFlow theControlFlow) {
        return visitActivityEdge(theControlFlow);
    }

    @objid ("d66bd7d4-3a4b-4fb7-adf2-13960d02c5d4")
    @Override
    public Object visitDataFlow(DataFlow theDataFlow) {
        return theDataFlow.getOwner();
    }

    @objid ("7ecd1892-db6d-43f8-8a13-15a805c01478")
    @Override
    public Object visitElementImport(ElementImport theElementImport) {
        ModelElement ret = theElementImport.getImportingNameSpace();
        if (ret == null) {
            ret = theElementImport.getImportingOperation();
        }
        return ret;
    }

    @objid ("899cec75-f0bc-48a9-8ec4-0572d5032bc0")
    @Override
    public Object visitExceptionHandler(final ExceptionHandler theExceptionHandler) {
        return theExceptionHandler.getProtectedNode();
    }

    @objid ("925fefd2-c5ad-4d04-a36b-97ce9fbf5e96")
    @Override
    public Object visitGeneralization(Generalization theGeneralization) {
        return theGeneralization.getSubType();
    }

    @objid ("f70f2464-8ee1-4934-a9cf-71c233272475")
    @Override
    public Object visitInformationFlow(InformationFlow theInformationFlow) {
        final List<UmlModelElement> ret = theInformationFlow.getInformationSource();
        if (ret.isEmpty()) {
            return null;
        } else if (ret.size() == 1) {
            return ret.get(0);
        } else {
            throw new UnsupportedOperationException(theInformationFlow + " has " + ret.size() + " sources.");
        }
    }

    @objid ("2fef2ca6-c8a6-4fc9-acba-237f581b6c30")
    @Override
    public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
        return theInterfaceRealization.getImplementer();
    }

    @objid ("231c76be-dc80-41a2-9797-40d2e6a40336")
    @Override
    public Object visitLink(Link theLink) {
        EList<LinkEnd> ends = theLink.getLinkEnd();
        if (ends.isEmpty()) {
            return null;
        } else {
            return visitLinkEnd(ends.get(0));
        }
    }

    @objid ("fb672aa8-ea60-43b9-a3de-392b066012cd")
    @Override
    public Object visitLinkEnd(LinkEnd theLinkEnd) {
        if (theLinkEnd.isNavigable()) {
            return theLinkEnd.getSource();
        } else {
            return theLinkEnd.getOpposite().getTarget();
        }
    }

    @objid ("88f642fe-5fe4-4caf-b4a7-c3ab0b4eef60")
    @Override
    public Object visitManifestation(Manifestation theManifestation) {
        return theManifestation.getOwner();
    }

    @objid ("b3ac5535-2674-43dd-aacb-8d26e64a5574")
    @Override
    public Object visitMessage(Message theMessage) {
        return theMessage.getSendEvent();
    }

    @objid ("a764e1ac-c5fc-4cb5-afed-f975d3e47e4f")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd theNaryAssociationEnd) {
        return theNaryAssociationEnd.getNaryAssociation();
    }

    @objid ("3bf08911-c6b9-4146-9f0d-f9b3b1548c4c")
    @Override
    public Object visitNaryLinkEnd(NaryLinkEnd theNaryLinkEnd) {
        return theNaryLinkEnd.getNaryLink();
    }

    @objid ("d958a3e5-c5b5-46b1-8326-e4238f524fba")
    @Override
    public Object visitPackageImport(PackageImport thePackageImport) {
        ModelElement ret = thePackageImport.getImportingNameSpace();
        if (ret == null) {
            ret = thePackageImport.getImportingOperation();
        }
        return ret;
    }

    @objid ("2c471979-f550-4019-bda0-fa6167bb23ed")
    @Override
    public Object visitPackageMerge(PackageMerge thePackageMerge) {
        return thePackageMerge.getReceivingPackage();
    }

    @objid ("c72b56c0-284b-418f-a5ec-92b5280c26c5")
    @Override
    public Object visitRaisedException(RaisedException theRaisedException) {
        return theRaisedException.getThrower();
    }

    @objid ("4c066204-09b7-4c4e-8ac9-7c4ed264da02")
    @Override
    public Object visitSubstitution(Substitution obj) {
        return obj.getSubstitutingClassifier();
    }

    @objid ("b5dc9187-1899-40bd-b0b0-aba28a123f7d")
    @Override
    public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
        ModelElement ret = theTemplateBinding.getBoundElement();
        if (ret == null) {
            ret = theTemplateBinding.getBoundOperation();
        }
        return ret;
    }

    @objid ("a8e09677-ebd8-4099-b6ad-6b4b1ec950f1")
    @Override
    public Object visitTransition(Transition theTransition) {
        return theTransition.getSource();
    }

    @objid ("a4110281-c09b-49ce-aed8-b675e8aa74e4")
    @Override
    public Object visitUseCaseDependency(final UseCaseDependency theUseCaseDependency) {
        return theUseCaseDependency.getOrigin();
    }

}
