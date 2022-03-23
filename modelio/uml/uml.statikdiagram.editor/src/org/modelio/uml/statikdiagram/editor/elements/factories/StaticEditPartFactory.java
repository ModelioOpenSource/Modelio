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
package org.modelio.uml.statikdiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.common.groupitem.GroupItemEditPart;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupEditPart;
import org.modelio.diagram.elements.core.model.factory.GenericUserImageModeEditPartFactory;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.dependency.DependencyEditPart;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.uml.statikdiagram.editor.elements.abstraction.GmAbstraction;
import org.modelio.uml.statikdiagram.editor.elements.activity.ActivityEditPart;
import org.modelio.uml.statikdiagram.editor.elements.activity.GmActivity;
import org.modelio.uml.statikdiagram.editor.elements.activity.GmActivityPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.association.AssociationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.association.GmAssociation;
import org.modelio.uml.statikdiagram.editor.elements.association.GmRoleNameLabel;
import org.modelio.uml.statikdiagram.editor.elements.association.RoleNameEditPart;
import org.modelio.uml.statikdiagram.editor.elements.associationclass.ClassAssociationLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.associationclass.GmClassAssociationLink;
import org.modelio.uml.statikdiagram.editor.elements.assocqualifier.GmQualifierGroup;
import org.modelio.uml.statikdiagram.editor.elements.assocqualifier.QualifierGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.attribute.AttributeEditPart;
import org.modelio.uml.statikdiagram.editor.elements.attribute.GmAttribute;
import org.modelio.uml.statikdiagram.editor.elements.attributegroup.AttributeGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.attributegroup.GmAttributeGroup;
import org.modelio.uml.statikdiagram.editor.elements.bindinglink.BindingLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.bindinglink.GmBindingLink;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.BpmnBehaviorEditPart;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.GmBpmnBehavior;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.GmBpmnBehaviorPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.BpmnCollaborationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.GmBpmnCollaboration;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.GmBpmnCollaborationPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.BpmnProcessEditPart;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.GmBpmnProcess;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.GmBpmnProcessPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.BpmnSharedDefinitionsEditPart;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.GmBpmnSharedDefinitions;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.GmBpmnSharedDefinitionsPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.classifier.ClassifierEditPart;
import org.modelio.uml.statikdiagram.editor.elements.classifier.ClassifierSimpleEditPart;
import org.modelio.uml.statikdiagram.editor.elements.classifier.GmClassifierResizableGroup;
import org.modelio.uml.statikdiagram.editor.elements.classifier.ImageClassifierEditPart;
import org.modelio.uml.statikdiagram.editor.elements.clazz.GmClass;
import org.modelio.uml.statikdiagram.editor.elements.clazz.GmClassPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.collab.CollaborationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.collab.GmCollaboration;
import org.modelio.uml.statikdiagram.editor.elements.collab.GmCollaborationPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseEditPart;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.GmCollaborationUse;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.GmCollaborationUsePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.collabuselink.CollabUseLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.collabuselink.GmCollabUseLink;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.CommunicationInteractionEditPart;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.GmCommunicationInteraction;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.GmCommunicationInteractionPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.component.GmComponent;
import org.modelio.uml.statikdiagram.editor.elements.component.GmComponentPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.componentRealization.GmComponentRealization;
import org.modelio.uml.statikdiagram.editor.elements.connector.GmConnectorLink;
import org.modelio.uml.statikdiagram.editor.elements.constraint.ConstraintBodyEditPart;
import org.modelio.uml.statikdiagram.editor.elements.constraint.ConstraintBodyLabelEditPart;
import org.modelio.uml.statikdiagram.editor.elements.constraint.ConstraintLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.constraint.GmConstraintBody;
import org.modelio.uml.statikdiagram.editor.elements.constraint.GmConstraintBodyLabel;
import org.modelio.uml.statikdiagram.editor.elements.constraint.GmConstraintLink;
import org.modelio.uml.statikdiagram.editor.elements.datatype.GmDataType;
import org.modelio.uml.statikdiagram.editor.elements.datatype.GmDataTypePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.elementRealization.ElementRealizationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.elementRealization.GmElementRealization;
import org.modelio.uml.statikdiagram.editor.elements.elementimport.ElementImportEditPart;
import org.modelio.uml.statikdiagram.editor.elements.elementimport.GmElementImport;
import org.modelio.uml.statikdiagram.editor.elements.enumeration.GmEnum;
import org.modelio.uml.statikdiagram.editor.elements.enumeration.GmEnumPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.enumliteral.EnumLitteralGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.enumliteral.GmEnumLitteral;
import org.modelio.uml.statikdiagram.editor.elements.enumliteral.GmEnumLitteralGroup;
import org.modelio.uml.statikdiagram.editor.elements.generalization.GeneralizationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.generalization.GmGeneralization;
import org.modelio.uml.statikdiagram.editor.elements.imagenamespacelabel.GmImageNameSpaceLabel;
import org.modelio.uml.statikdiagram.editor.elements.informationconveyed.ConveyedClassifiersGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.informationconveyed.GmConveyedClassifierLabel;
import org.modelio.uml.statikdiagram.editor.elements.informationconveyed.GmConveyedClassifiersGroup;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.GmInformationFlowArrow;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.GmInformationFlowLabel;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.InfoFlowsGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.InformationArrowEditPart;
import org.modelio.uml.statikdiagram.editor.elements.informationflowlink.GmInformationFlowLink;
import org.modelio.uml.statikdiagram.editor.elements.informationflowlink.InformationFlowLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.informationitem.GmInformationItem;
import org.modelio.uml.statikdiagram.editor.elements.informationitem.GmInformationItemPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.innerclass.GmInnerClass;
import org.modelio.uml.statikdiagram.editor.elements.innerclass.InnerClassEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstance;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstanceLabel;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstancePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.instance.ImageInstanceEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instance.InstanceEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instance.InstanceSimpleEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instanceheader.GmInstanceHeader;
import org.modelio.uml.statikdiagram.editor.elements.instanceheader.InstanceHeaderEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instanceinternalstructure.GmInstanceInternalStructure;
import org.modelio.uml.statikdiagram.editor.elements.instanceinternalstructure.GmInstanceInternalStructureGroup;
import org.modelio.uml.statikdiagram.editor.elements.instanceinternalstructure.GmInstanceInternalStructureZone;
import org.modelio.uml.statikdiagram.editor.elements.instanceinternalstructure.InstanceInternalStructureGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instanceinternalstructure.InstanceInternalStructureZoneEditPart;
import org.modelio.uml.statikdiagram.editor.elements.instancelink.GmInstanceLink;
import org.modelio.uml.statikdiagram.editor.elements.instancelink.GmLinkRoleCardinalityLabel;
import org.modelio.uml.statikdiagram.editor.elements.instancelink.GmLinkRoleNameLabel;
import org.modelio.uml.statikdiagram.editor.elements.instancelink.InstanceLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.interaction.GmInteraction;
import org.modelio.uml.statikdiagram.editor.elements.interaction.GmInteractionPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.interaction.InteractionEditPart;
import org.modelio.uml.statikdiagram.editor.elements.interfaze.GmInterface;
import org.modelio.uml.statikdiagram.editor.elements.interfaze.GmInterfacePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.interfaze.InterfaceEditPart;
import org.modelio.uml.statikdiagram.editor.elements.interfaze.SimpleInterfaceEditPart;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.GmInternalStructure;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.GmInternalStructureGroup;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.GmInternalStructureZone;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.InternalStructureEditPart;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.InternalStructureGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.InternalStructureZoneEditPart;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.NamespaceHeaderEditPart;
import org.modelio.uml.statikdiagram.editor.elements.namespacelabel.GmNameSpaceLabel;
import org.modelio.uml.statikdiagram.editor.elements.namespacelabel.NamespaceLabelEditPart;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.CompositionLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.GmCompositionLink;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.GmNAssocEndLink;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.GmNAssocNode;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.GmNAssocPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.GmNaryRoleCardinalityLabel;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.GmNaryRoleNameLabel;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.NAssocEndLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.NAssocNodeEditPart;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.NaryRoleNameEditPart;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.GmNConnectorEndLink;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.GmNConnectorNode;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.GmNConnectorPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNLinkEndLink;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNLinkNode;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNLinkPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNaryLinkRoleCardinalityLabel;
import org.modelio.uml.statikdiagram.editor.elements.narylink.NLinkEndLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.narylink.NLinkNodeEditPart;
import org.modelio.uml.statikdiagram.editor.elements.operation.GmOperation;
import org.modelio.uml.statikdiagram.editor.elements.operation.OperationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.operationgroup.GmOperationGroup;
import org.modelio.uml.statikdiagram.editor.elements.operationgroup.OperationGroupEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packageimport.GmPackageImport;
import org.modelio.uml.statikdiagram.editor.elements.packageimport.PackageImportEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packagemerge.GmPackageMerge;
import org.modelio.uml.statikdiagram.editor.elements.packagemerge.PackageMergeEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackage;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackageBody;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackagePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.packaze.PackageBodyEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packaze.PackageEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packaze.PackageImageEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packaze.PackagePortContainerEditPart;
import org.modelio.uml.statikdiagram.editor.elements.packaze.PackageSimpleEditPart;
import org.modelio.uml.statikdiagram.editor.elements.ports.GmPort;
import org.modelio.uml.statikdiagram.editor.elements.ports.GmPortPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.ports.ImagePortEditPart;
import org.modelio.uml.statikdiagram.editor.elements.ports.PortEditPart;
import org.modelio.uml.statikdiagram.editor.elements.ports.PortPrimaryNodeEditPart;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.ProvidedInterfaceLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.raisedexception.GmRaisedException;
import org.modelio.uml.statikdiagram.editor.elements.raisedexception.RaisedExceptionEditPart;
import org.modelio.uml.statikdiagram.editor.elements.realization.GmInterfaceRealization;
import org.modelio.uml.statikdiagram.editor.elements.realization.InterfaceRealizationEditPart;
import org.modelio.uml.statikdiagram.editor.elements.requiredinterface.GmLollipopConnection;
import org.modelio.uml.statikdiagram.editor.elements.requiredinterface.GmRequiredInterfaceLink;
import org.modelio.uml.statikdiagram.editor.elements.requiredinterface.LollipopConnectionEditPart;
import org.modelio.uml.statikdiagram.editor.elements.requiredinterface.RequiredInterfaceLinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.rolecardinalitylabel.GmRoleCardinalityLabel;
import org.modelio.uml.statikdiagram.editor.elements.signal.GmSignal;
import org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalPrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.slot.GmSlot;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.GmStateMachine;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.GmStateMachinePrimaryNode;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.StateMachineEditPart;
import org.modelio.uml.statikdiagram.editor.elements.staticdiagram.GmStaticDiagram;
import org.modelio.uml.statikdiagram.editor.elements.staticdiagram.StaticDiagramEditPart;
import org.modelio.uml.statikdiagram.editor.elements.substitution.GmSubstitution;
import org.modelio.uml.statikdiagram.editor.elements.templatebinding.GmTemplateBinding;
import org.modelio.uml.statikdiagram.editor.elements.templatebinding.TemplateBindingEditPart;
import org.modelio.uml.statikdiagram.editor.elements.templatecontainer.TemplateContainerEditPart;
import org.modelio.uml.statikdiagram.editor.elements.templateparameter.GmTemplateSignature;
import org.modelio.uml.statikdiagram.editor.elements.templateparameter.TemplateSignatureEditPart;

/**
 * Edit part factory for the static diagrams.
 */
@objid ("36b792a2-55b7-11e2-877f-002564c97630")
public class StaticEditPartFactory implements EditPartFactory {
    @objid ("36b792a6-55b7-11e2-877f-002564c97630")
    private final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    @objid ("36b792a7-55b7-11e2-877f-002564c97630")
    private final SimpleModeEditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    @objid ("36b792a8-55b7-11e2-877f-002564c97630")
    private final ImageModeEditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    @objid ("14af03ed-b102-4770-8479-daa108e2aef9")
    private final EditPartFactory userImageModeEditPartFactory = new GenericUserImageModeEditPartFactory(this.imageModeEditPartFactory);

    @objid ("36b792b0-55b7-11e2-877f-002564c97630")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case USER_IMAGE:
                editPart = this.userImageModeEditPartFactory.createEditPart(context, model);
                break;
            case SIMPLE:
                editPart = this.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            case IMAGE:
                editPart = this.imageModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null; // generically supported by standard factory
            }
        
            return editPart;
        } else {
            // Link models are always in structured mode.
            editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
            return editPart;
        }
        
    }

    /**
     * Edit part factory for nodes in structured mode.
     */
    @objid ("36b792b6-55b7-11e2-877f-002564c97630")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("36b792b8-55b7-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmStaticDiagram.class) {
                editPart = new StaticDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Information flow
            if (model.getClass() == GmInformationFlowLabel.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInfoFlowsGroup.class) {
                editPart = new InfoFlowsGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInformationFlowArrow.class) {
                editPart = new InformationArrowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Abstraction
            if (model.getClass() == GmAbstraction.class) {
                editPart = new DependencyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // ElementRealization
            if (model.getClass() == GmElementRealization.class) {
                editPart = new ElementRealizationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Constraint
            if (model instanceof GmConstraintBody) {
                editPart = new ConstraintBodyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmConstraintBodyLabel) {
                editPart = new ConstraintBodyLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmConstraintLink) {
                editPart = new ConstraintLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Class & co
            if (model.getClass() == GmNamespaceHeader.class) {
                editPart = new NamespaceHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInnerClass.class) {
                editPart = new InnerClassEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmClass.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmClassPrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmClassifierResizableGroup.class) {
                editPart = new ResizableGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNameSpaceLabel.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmImageNameSpaceLabel.class) {
                editPart = new NamespaceLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Class association
            if (model.getClass() == GmClassAssociationLink.class) {
                editPart = new ClassAssociationLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Component
            if (model.getClass() == GmComponent.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmComponentPrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Signal
            if (model.getClass() == GmSignal.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSignalPrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Operation
            if (model.getClass() == GmOperationGroup.class) {
                editPart = new OperationGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmOperation.class) {
                editPart = new OperationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Attribute
            if (model.getClass() == GmAttributeGroup.class) {
                editPart = new AttributeGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAttribute.class) {
                editPart = new AttributeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Port
            if (model.getClass() == GmPort.class) {
                editPart = new PortEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPortPrimaryNode.class) {
                editPart = new PortPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Association
            if (model.getClass() == GmAssociation.class) {
                editPart = new AssociationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmRoleNameLabel.class) {
                editPart = new RoleNameEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmLinkRoleNameLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNaryRoleNameLabel.class) {
                editPart = new NaryRoleNameEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmRoleCardinalityLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNaryRoleCardinalityLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Qualifier
            if (model.getClass() == GmQualifierGroup.class) {
                editPart = new QualifierGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary association
            if (model.getClass() == GmNAssocEndLink.class) {
                editPart = new NAssocEndLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNAssocNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNAssocPrimaryNode.class) {
                editPart = new NAssocNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary link
            if (model.getClass() == GmNLinkEndLink.class) {
                editPart = new NLinkEndLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNLinkNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNLinkPrimaryNode.class) {
                editPart = new NLinkNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary connector
            if (model.getClass() == GmNConnectorEndLink.class) {
                editPart = new NLinkEndLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNConnectorNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNConnectorPrimaryNode.class) {
                editPart = new NAssocNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // MObject import
            if (model.getClass() == GmElementImport.class) {
                editPart = new ElementImportEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Collaboration & co
            if (model.getClass() == GmCollaboration.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCollaborationPrimaryNode.class) {
                editPart = new CollaborationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCollaborationUse.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCollaborationUsePrimaryNode.class) {
                editPart = new CollaborationUseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Instance
            if (model.getClass() == GmInstance.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstancePrimaryNode.class) {
                editPart = new InstanceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstanceHeader.class) {
                editPart = new InstanceHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstanceInternalStructureGroup.class) {
                editPart = new InstanceInternalStructureGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstanceInternalStructureZone.class) {
                editPart = new InstanceInternalStructureZoneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInternalStructure.class) {
                editPart = new InternalStructureEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstanceInternalStructure.class) {
                editPart = new InternalStructureEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInternalStructureGroup.class) {
                editPart = new InternalStructureGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInternalStructureZone.class) {
                editPart = new InternalStructureZoneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInstanceLabel.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Link
            if (model.getClass() == GmInstanceLink.class) {
                editPart = new InstanceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmLinkRoleCardinalityLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNaryLinkRoleCardinalityLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Connector
            if (model.getClass() == GmConnectorLink.class) {
                editPart = new InstanceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Interface
            if (model.getClass() == GmInterface.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInterfacePrimaryNode.class) {
                editPart = new InterfaceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Slot : AttributeLink
            if (model.getClass() == GmSlot.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Generalization
            if (model.getClass() == GmGeneralization.class) {
                editPart = new GeneralizationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Interface realization
            if (model.getClass() == GmInterfaceRealization.class) {
                editPart = new InterfaceRealizationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Component Realization
            if (model.getClass() == GmComponentRealization.class) {
                editPart = new InterfaceRealizationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Substitution
            if (model.getClass() == GmSubstitution.class) {
                editPart = new DependencyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Package
            if (model.getClass() == GmPackage.class) {
                RepresentationMode realMode = ((GmPackage) model).getMainNodeRepresentationMode();
                switch (realMode) {
                case SIMPLE:
                    editPart = new PackagePortContainerEditPart();
                    break;
                case IMAGE:
                case STRUCTURED:
                default:
                    editPart = new PortContainerEditPart();
                }
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPackagePrimaryNode.class) {
                editPart = new PackageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPackageBody.class) {
                editPart = new PackageBodyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCompositionLink.class) {
                editPart = new CompositionLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Enumeration
            if (model.getClass() == GmEnum.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEnumPrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEnumLitteral.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEnumLitteralGroup.class) {
                editPart = new EnumLitteralGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Datatype
            if (model.getClass() == GmDataType.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDataTypePrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Binding (collaboration use)
            if (model.getClass() == GmBindingLink.class) {
                editPart = new BindingLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Collaboration use
            if (model.getClass() == GmCollabUseLink.class) {
                editPart = new CollabUseLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Package import
            if (model.getClass() == GmPackageImport.class) {
                editPart = new PackageImportEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Package merge
            if (model.getClass() == GmPackageMerge.class) {
                editPart = new PackageMergeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            // Raised exception
            if (model.getClass() == GmRaisedException.class) {
                editPart = new RaisedExceptionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Information flow
            if (model.getClass() == GmInformationFlowLink.class) {
                editPart = new InformationFlowLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConveyedClassifiersGroup.class) {
                editPart = new ConveyedClassifiersGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConveyedClassifierLabel.class) {
                editPart = new GroupItemEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Information item
            if (model.getClass() == GmInformationItem.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInformationItemPrimaryNode.class) {
                editPart = new ClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Provided interface
            if (model.getClass() == GmProvidedInterfaceLink.class) {
                editPart = new ProvidedInterfaceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Required interface
            if (model.getClass() == GmRequiredInterfaceLink.class) {
                editPart = new RequiredInterfaceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Template parameters
            if (model.getClass() == GmTemplateSignature.class) {
                editPart = new TemplateSignatureEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Template binding
            if (model.getClass() == GmTemplateBinding.class) {
                editPart = new TemplateBindingEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Activity
            if (model.getClass() == GmActivity.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityPrimaryNode.class) {
                editPart = new ActivityEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // BpmnProcess
            if (model.getClass() == GmBpmnProcess.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmBpmnProcessPrimaryNode.class) {
                editPart = new BpmnProcessEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // BpmnCollaboration
            if (model.getClass() == GmBpmnCollaboration.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmBpmnCollaborationPrimaryNode.class) {
                editPart = new BpmnCollaborationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // BpmnSharedDefinitions
            if (model.getClass() == GmBpmnSharedDefinitions.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmBpmnSharedDefinitionsPrimaryNode.class) {
                editPart = new BpmnSharedDefinitionsEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Interaction
            if (model.getClass() == GmInteraction.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInteractionPrimaryNode.class) {
                editPart = new InteractionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // BpmnBehavior
            if (model.getClass() == GmBpmnBehavior.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmBpmnBehaviorPrimaryNode.class) {
                editPart = new BpmnBehaviorEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Interaction
            if (model.getClass() == GmCommunicationInteraction.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationInteractionPrimaryNode.class) {
                editPart = new CommunicationInteractionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // StateMachine
            if (model.getClass() == GmStateMachine.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStateMachinePrimaryNode.class) {
                editPart = new StateMachineEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

    /**
     * Edit part factory for nodes in simple mode.
     */
    @objid ("36b9191a-55b7-11e2-877f-002564c97630")
    private static final class SimpleModeEditPartFactory implements EditPartFactory {
        @objid ("36b9191c-55b7-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart;
            
            // Class & co
            if (model.getClass() == GmNamespaceHeader.class) {
                editPart = new NamespaceHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Class
            if (model.getClass() == GmClassPrimaryNode.class) {
                editPart = new ClassifierSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Enum
            if (model.getClass() == GmEnumPrimaryNode.class) {
                editPart = new ClassifierSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Signal
            if (model.getClass() == GmSignalPrimaryNode.class) {
                editPart = new ClassifierSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Interface
            if (model.getClass() == GmInterface.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInterfacePrimaryNode.class) {
                editPart = new SimpleInterfaceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Instance
            if (model.getClass() == GmInstancePrimaryNode.class) {
                editPart = new InstanceSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Provided interface
            if (model.getClass() == GmProvidedInterfaceLink.class) {
                editPart = new ProvidedInterfaceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Required interface
            if (model.getClass() == GmRequiredInterfaceLink.class) {
                editPart = new RequiredInterfaceLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Required/provided lollipop connection.
            if (model.getClass() == GmLollipopConnection.class) {
                editPart = new LollipopConnectionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Component
            if (model.getClass() == GmComponent.class) {
                editPart = new TemplateContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmComponentPrimaryNode.class) {
                editPart = new ClassifierSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary association
            if (model.getClass() == GmNAssocNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNAssocPrimaryNode.class) {
                editPart = new NAssocNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary link
            if (model.getClass() == GmNLinkNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNLinkPrimaryNode.class) {
                editPart = new NLinkNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // N-ary connector
            if (model.getClass() == GmNConnectorNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmNConnectorPrimaryNode.class) {
                editPart = new NAssocNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Port
            if (model.getClass() == GmPort.class) {
                editPart = new PortEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPortPrimaryNode.class) {
                editPart = new PortPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Package
            if (model.getClass() == GmPackagePrimaryNode.class) {
                editPart = new PackageSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Not handled
            return null;
        }

    }

    /**
     * Edit part factory for nodes in image mode.
     */
    @objid ("36b91923-55b7-11e2-877f-002564c97630")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("36b91925-55b7-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart;
            
            // Port
            if (model.getClass() == GmPort.class) {
                editPart = new PortEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmPortPrimaryNode.class) {
                editPart = new ImagePortEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Classifier
            if (model.getClass() == GmClassPrimaryNode.class ||
                    model.getClass() == GmDataTypePrimaryNode.class ||
                    model.getClass() == GmEnumPrimaryNode.class ||
                    model.getClass() == GmComponentPrimaryNode.class ||
                    model.getClass() == GmInterfacePrimaryNode.class ||
                    model.getClass() == GmSignalPrimaryNode.class) {
                editPart = new ImageClassifierEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Instance
            if (model.getClass() == GmInstancePrimaryNode.class) {
                editPart = new ImageInstanceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Package
            if (model.getClass() == GmPackagePrimaryNode.class) {
                // specific edit part for primary node that reparent content of body if switched to simple mode.
                editPart = new PackageImageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Not handled
            return null;
        }

    }

}
