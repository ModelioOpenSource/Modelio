/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.abstraction.GmAbstractionHeader;
import org.modelio.diagram.editor.statik.elements.activity.GmActivity;
import org.modelio.diagram.editor.statik.elements.attribute.GmAttribute;
import org.modelio.diagram.editor.statik.elements.binding.GmBindingLabel;
import org.modelio.diagram.editor.statik.elements.bpmncollaboration.GmBpmnCollaboration;
import org.modelio.diagram.editor.statik.elements.bpmnprocess.GmBpmnProcess;
import org.modelio.diagram.editor.statik.elements.bpmnsharedefinition.GmBpmnSharedDefinitions;
import org.modelio.diagram.editor.statik.elements.clazz.GmClass;
import org.modelio.diagram.editor.statik.elements.collab.GmCollaboration;
import org.modelio.diagram.editor.statik.elements.collabuse.GmCollaborationUse;
import org.modelio.diagram.editor.statik.elements.collabuse.GmCollaborationUseFlatLabel;
import org.modelio.diagram.editor.statik.elements.communicationinteraction.GmCommunicationInteraction;
import org.modelio.diagram.editor.statik.elements.component.GmComponent;
import org.modelio.diagram.editor.statik.elements.constraint.GmConstraintBody;
import org.modelio.diagram.editor.statik.elements.constraint.GmConstraintBodyLabel;
import org.modelio.diagram.editor.statik.elements.datatype.GmDataType;
import org.modelio.diagram.editor.statik.elements.elementRealization.GmElementRealizationHeader;
import org.modelio.diagram.editor.statik.elements.enumeration.GmEnum;
import org.modelio.diagram.editor.statik.elements.enumliteral.GmEnumLitteral;
import org.modelio.diagram.editor.statik.elements.informationconveyed.GmConveyedClassifierLabel;
import org.modelio.diagram.editor.statik.elements.informationconveyed.GmConveyedClassifiersGroup;
import org.modelio.diagram.editor.statik.elements.informationconveyed.GmConveyedInformationItemLabel;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInformationFlowArrow;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInformationFlowLabel;
import org.modelio.diagram.editor.statik.elements.informationitem.GmInformationItem;
import org.modelio.diagram.editor.statik.elements.informationitem.GmInformationItemLabel;
import org.modelio.diagram.editor.statik.elements.instance.GmInstance;
import org.modelio.diagram.editor.statik.elements.instance.GmInstanceLabel;
import org.modelio.diagram.editor.statik.elements.interaction.GmInteraction;
import org.modelio.diagram.editor.statik.elements.interfaze.GmInterface;
import org.modelio.diagram.editor.statik.elements.namespacelabel.GmNameSpaceLabel;
import org.modelio.diagram.editor.statik.elements.naryassoc.GmNAssocNode;
import org.modelio.diagram.editor.statik.elements.naryconnector.GmNConnectorNode;
import org.modelio.diagram.editor.statik.elements.narylink.GmNLinkNode;
import org.modelio.diagram.editor.statik.elements.operation.GmOperation;
import org.modelio.diagram.editor.statik.elements.packaze.GmPackage;
import org.modelio.diagram.editor.statik.elements.ports.GmPort;
import org.modelio.diagram.editor.statik.elements.requiredinterface.GmLollipopConnection;
import org.modelio.diagram.editor.statik.elements.signal.GmSignal;
import org.modelio.diagram.editor.statik.elements.slot.GmSlot;
import org.modelio.diagram.editor.statik.elements.statemachine.GmStateMachine;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Static diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes static diagram specific elements</li>
 * </ul>
 */
@objid ("36bdad3b-55b7-11e2-877f-002564c97630")
public class StaticGmNodeFactory implements IGmNodeFactory {
    @objid ("36bdad44-55b7-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        GmNodeModel child = null;
        
        if (parent instanceof GmConveyedClassifiersGroup) {
            // Conveyed classifier group factory
            child = createInformationItemGroupNode(diagram, newElement, initialLayoutData);
        } else if (parent instanceof GmGroup) {
            // Use the group element factory visitor
            final GroupElementFactoryVisitor v = new GroupElementFactoryVisitor(diagram, initialLayoutData);
        
            child = (GmNodeModel) newElement.accept(v);
        } else {
            // Use the node factory visitor
            final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, initialLayoutData);
        
            child = (GmNodeModel) newElement.accept(v);
        }
        
        if (child != null) {
            parent.addChild(child);
        }
        return child;
    }

    @objid ("36bf33b9-55b7-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.elements")) {
                // Deal with a few GMs from diagram.elements that were moved into diagram.static
                switch (namespace) {
                case "org.modelio.diagram.elements.umlcommon.abstraction.GmAbstractionHeader":
                    return GmAbstractionHeader.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.constraint.GmConstraintBody":
                    return GmConstraintBody.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.constraint.GmConstraintBodyLabel":
                    return GmConstraintBodyLabel.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.informationflowgroup.GmInfoFlowsGroup":
                    return GmInfoFlowsGroup.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.informationflowgroup.GmInformationFlowArrow":
                    return GmInformationFlowArrow.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.informationflowgroup.GmInformationFlowLabel":
                    return GmInformationFlowLabel.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.elementRealization.GmElementRealizationHeader":
                    return GmElementRealizationHeader.class;
                default:
                    return null;
                }
            } else if (namespace.startsWith("org.modelio.diagram.editor.statik")) {
                Class<?> clazz = Class.forName(namespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("36bf33c0-55b7-11e2-877f-002564c97630")
    private GmNodeModel createInformationItemGroupNode(final IGmDiagram diagram, final MObject newElement, final Object initialLayoutData) {
        GmNodeModel node = null;
        if (newElement instanceof InformationItem) {
            node = new GmConveyedInformationItemLabel(diagram,
                    (InformationItem) newElement,
                    new MRef(newElement));
            node.setLayoutData(initialLayoutData);
        } else if (newElement instanceof Classifier) {
            node = new GmConveyedClassifierLabel(diagram, (Classifier) newElement, new MRef(newElement));
            node.setLayoutData(initialLayoutData);
        }
        return node;
    }

    @objid ("751d0088-f432-484d-b17f-96d56e9fe92f")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            Class<?> clazz = Class.forName(classNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("64e667b5-6f90-40fa-89b7-831878b2afef")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            Class<?> clazz = Class.forName(enumNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    /**
     * Factory visitor that creates instances to put into {@link GmGroup}.
     * 
     * @author cmarin
     */
    @objid ("36c54e1a-55b7-11e2-877f-002564c97630")
    private class GroupElementFactoryVisitor extends DefaultModelVisitor {
        @objid ("a7de1729-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("36c54e1e-55b7-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("36c54e22-55b7-11e2-877f-002564c97630")
        public GroupElementFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("36c6d4e5-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitActivity(final Activity theItem) {
            GmActivity node = new GmActivity(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c54e30-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitAttribute(Attribute theAttribute) {
            final GmAttribute node = new GmAttribute(this.diagram, theAttribute, new MRef(theAttribute));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c54e38-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitAttributeLink(AttributeLink theAttributeLink) {
            final GmSlot node = new GmSlot(this.diagram, theAttributeLink, new MRef(theAttributeLink));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c6d4d4-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitBinding(Binding theBinding) {
            final GmBindingLabel node = new GmBindingLabel(this.diagram, theBinding, new MRef(theBinding));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c54e40-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitClass(org.modelio.metamodel.uml.statik.Class theClass) {
            final GmNameSpaceLabel node = new GmNameSpaceLabel(this.diagram, theClass, new MRef(theClass));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c6d4c4-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
            final GmCollaborationUseFlatLabel node = new GmCollaborationUseFlatLabel(this.diagram,
                    theCollaborationUse,
                    new MRef(theCollaborationUse));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c85b66-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCommunicationInteraction(final CommunicationInteraction theItem) {
            GmCommunicationInteraction node = new GmCommunicationInteraction(this.diagram,
                    theItem,
                    new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c54e51-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitEnumerationLiteral(final EnumerationLiteral theLiteral) {
            final GmEnumLitteral node = new GmEnumLitteral(this.diagram, theLiteral, new MRef(theLiteral));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("8105a06f-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitInformationFlow(final InformationFlow theInformationFlow) {
            GmInformationFlowLabel node = new GmInformationFlowLabel(this.diagram, theInformationFlow, new MRef(theInformationFlow));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c6d4dc-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInformationItem(final InformationItem theItem) {
            GmInformationItemLabel node = new GmInformationItemLabel(this.diagram,
                    theItem,
                    new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c54e5a-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInstance(Instance theInstance) {
            final GmInstanceLabel node = new GmInstanceLabel(this.diagram,
                    theInstance,
                    new MRef(theInstance));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c85b5d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInteraction(final Interaction theItem) {
            GmInteraction node = new GmInteraction(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        /**
         * Use {@link GmNameSpaceLabel} for any {@link NameSpace namespace} .
         */
        @objid ("36c54e48-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitNameSpace(NameSpace theNs) {
            final GmNameSpaceLabel node = new GmNameSpaceLabel(this.diagram, theNs, new MRef(theNs));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c6d4bc-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitOperation(Operation theOperation) {
            final GmOperation node = new GmOperation(this.diagram, theOperation, new MRef(theOperation));
            return node;
        }

        @objid ("36c6d4cc-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitPackage(Package thePackage) {
            final GmNameSpaceLabel node = new GmNameSpaceLabel(this.diagram,
                    thePackage,
                    new MRef(thePackage));
            node.setLayoutData(this.initialLayoutData);
            node.setShowMetaclassIcon(true);
            return node;
        }

        @objid ("36c85b6f-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitStateMachine(final StateMachine theItem) {
            GmStateMachine node = new GmStateMachine(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("36bf33d0-55b7-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("a7d4ef69-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("36bf33d4-55b7-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("36bf33d8-55b7-11e2-877f-002564c97630")
        public NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

/*
         * @objid ("36c24109-55b7-11e2-877f-002564c97630")
         * 
         * @Override public Object visitAssociation(final Association theAssoc) { if (theAssoc.Connection().size() > 2) { // N-Ary association GmNAssocNode node = new GmNAssocNode(this.diagram, theAssoc, new MRef(theAssoc));
         * node.setLayoutData(this.initialLayoutData); return node; } return null; }
         * 
         * @objid ("36c24112-55b7-11e2-877f-002564c97630")
         * 
         * @Override public Object visitLink(final ILink theAssoc) { if (theAssoc.cardConnection() > 2) { // N-Ary association GmNodeModel node = new GmNLinkNode(this.diagram, theAssoc, new MRef(theAssoc)); node.setLayoutData(this.initialLayoutData);
         * return node; } return null; }
         * 
         * @objid ("36c3c77a-55b7-11e2-877f-002564c97630")
         * 
         * @Override public Object visitConnector(final Connector theAssoc) { if (theAssoc.cardConnection() > 2) { // N-Ary association GmNodeModel node = new GmNConnectorNode(this.diagram, theAssoc, new MRef(theAssoc));
         * node.setLayoutData(this.initialLayoutData); return node; } return null; }
         */
        @objid ("36c3c783-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitActivity(final Activity theItem) {
            GmActivity node = new GmActivity(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36bf33de-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitClass(org.modelio.metamodel.uml.statik.Class theClass) {
            final GmClass node = new GmClass(this.diagram, theClass, new MRef(theClass));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba3d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCollaboration(Collaboration theCollaboration) {
            final GmCollaboration node = new GmCollaboration(this.diagram,
                    theCollaboration,
                    new MRef(theCollaboration));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba45-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
            final GmCollaborationUse node = new GmCollaborationUse(this.diagram,
                    theCollaborationUse,
                    new MRef(theCollaborationUse));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c3c7a7-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCommunicationInteraction(final CommunicationInteraction theItem) {
            GmCommunicationInteraction node = new GmCommunicationInteraction(this.diagram,
                    theItem,
                    new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c240f7-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitComponent(final Component theComponent) {
            final GmComponent node = new GmComponent(this.diagram, theComponent, new MRef(theComponent));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("8105a04d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            final GmNodeModel node = new GmConstraintBody(this.diagram, theConstraint, new MRef(theConstraint));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba4d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitDataType(DataType theDataType) {
            final GmDataType node = new GmDataType(this.diagram, theDataType, new MRef(theDataType));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba5d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitEnumeration(Enumeration theEnumeration) {
            final GmEnum node = new GmEnum(this.diagram, theEnumeration, new MRef(theEnumeration));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba65-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitEnumerationLiteral(EnumerationLiteral theEnumerationLiteral) {
            final GmEnumLitteral node = new GmEnumLitteral(this.diagram,
                    theEnumerationLiteral,
                    new MRef(theEnumerationLiteral));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c240ee-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInformationItem(final InformationItem theItem) {
            GmNodeModel node = new GmInformationItem(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba6d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInstance(Instance theInstance) {
            final GmInstance node = new GmInstance(this.diagram, theInstance, new MRef(theInstance));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c3c79e-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInteraction(final Interaction theItem) {
            GmInteraction node = new GmInteraction(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba75-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInterface(Interface theInterface) {
            final GmInterface node = new GmInterface(this.diagram, theInterface, new MRef(theInterface));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("c654c913-bbbe-42d6-a8a3-8705c046d4f3")
        @Override
        public Object visitNaryAssociation(NaryAssociation theNaryAssociation) {
            GmNAssocNode node = new GmNAssocNode(this.diagram, theNaryAssociation, new MRef(theNaryAssociation));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("4f8b9a84-619c-4260-8c1b-5a6d0e28aa8e")
        @Override
        public Object visitNaryConnector(NaryConnector theNaryConnector) {
            boolean isLollipopConnection = true;
            for (NaryLinkEnd end : theNaryConnector.getNaryLinkEnd()) {
                if (end.getProvider() == null && end.getConsumer() == null) {
                    isLollipopConnection = false;
                    break;
                }
            }
            if (isLollipopConnection) {
                GmLollipopConnection node = new GmLollipopConnection(this.diagram, theNaryConnector);
                node.setLayoutData(this.initialLayoutData);
                return node;
            } else {
                GmNConnectorNode node = new GmNConnectorNode(this.diagram, theNaryConnector, new MRef(theNaryConnector));
                node.setLayoutData(this.initialLayoutData);
                return node;
            }
        }

        @objid ("52f4b320-7cff-4509-9ae7-d7e0db9b3fb6")
        @Override
        public Object visitNaryLink(NaryLink theNaryLink) {
            GmNLinkNode node = new GmNLinkNode(this.diagram, theNaryLink, new MRef(theNaryLink));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c0ba7d-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitPackage(Package thePackage) {
            final GmPackage packaze = new GmPackage(this.diagram, thePackage, new MRef(thePackage));
            packaze.setLayoutData(this.initialLayoutData);
            return packaze;
        }

        @objid ("36c240de-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitPort(Port thePort) {
            final GmPort node = new GmPort(this.diagram, thePort, new MRef(thePort));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c240e6-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitSignal(Signal theSignal) {
            final GmSignal node = new GmSignal(this.diagram, theSignal, new MRef(theSignal));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c3c7b0-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitStateMachine(final StateMachine theItem) {
            GmStateMachine node = new GmStateMachine(this.diagram, theItem, new MRef(theItem));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("36c6d4f7-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitBpmnProcess(final BpmnProcess theProcess) {
            GmBpmnProcess node = new GmBpmnProcess(this.diagram, theProcess, new MRef(theProcess));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("165674dc-f721-4f17-ac5c-4e9e5bb08241")
        @Override
        public Object visitBpmnCollaboration(final BpmnCollaboration theCollaboration) {
            GmBpmnCollaboration node = new GmBpmnCollaboration(this.diagram, theCollaboration, new MRef(theCollaboration));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("5bb686f5-e84d-4457-97de-4cc46c327e98")
        @Override
        public Object visitBpmnSharedDefinitions(final BpmnSharedDefinitions theSharedDefinitions) {
            GmBpmnSharedDefinitions node = new GmBpmnSharedDefinitions(this.diagram, theSharedDefinitions, new MRef(theSharedDefinitions));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

}
