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
package org.modelio.uml.statikdiagram.editor.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.uml.statikdiagram.editor.elements.abstraction.GmAbstraction;
import org.modelio.uml.statikdiagram.editor.elements.activity.GmActivityImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.activity.GmActivitySimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.activity.GmActivityStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.association.GmAssocStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.associationclass.ClassAssociationLinkStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.attribute.GmAttributeStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bindinglink.BindingLinkStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.GmBpmnBehaviorImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.GmBpmnBehaviorSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior.GmBpmnBehaviorStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.GmBpmnCollaborationImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.GmBpmnCollaborationSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration.GmBpmnCollaborationStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.GmBpmnProcessImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.GmBpmnProcessSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnprocess.GmBpmnProcessStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.GmBpmnSharedDefinitionsImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.GmBpmnSharedDefinitionsSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition.GmBpmnSharedDefinitionsStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.clazz.GmClassStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collab.CollaborationStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuselink.CollabUseLinkStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.GmCommunicationInteractionImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.GmCommunicationInteractionSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.communicationinteraction.GmCommunicationInteractionStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.component.ComponentStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.componentRealization.GmComponentRealization;
import org.modelio.uml.statikdiagram.editor.elements.connector.ConnectorLinkStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.constraint.GmConstraintStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.datatype.DataTypeStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.elementRealization.GmElementRealization;
import org.modelio.uml.statikdiagram.editor.elements.elementimport.GmElementImportStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.enumeration.EnumStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.factories.StaticEditPartFactory;
import org.modelio.uml.statikdiagram.editor.elements.factories.StaticGmLinkFactory;
import org.modelio.uml.statikdiagram.editor.elements.factories.StaticGmNodeFactory;
import org.modelio.uml.statikdiagram.editor.elements.generalization.GmGeneralizationStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.informationflowlink.InformationFlowLinkStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.informationitem.InformationItemStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstanceStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.instancelink.InstanceLinkStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.interaction.GmInteractionImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.interaction.GmInteractionSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.interaction.GmInteractionStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.interfaze.InterfaceStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.NAssocStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.NConnectorStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.narylink.NLinkStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.operation.GmOperationStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.packageimport.PackageImportStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.packagemerge.PackageMergeStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackageStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.ports.GmPortStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.ProvidedInterfaceStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.raisedexception.RaisedExceptionStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.realization.GmInterfaceRealizationStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.requiredinterface.RequiredInterfaceStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.GmStateMachineImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.GmStateMachineSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.statemachine.GmStateMachineStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.staticdiagram.GmStaticDiagramStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.substitution.GmSubstitution;
import org.modelio.uml.statikdiagram.editor.elements.templatebinding.TemplateBindingStructuredStyleKeys;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link StaticDiagram} and {@link ClassDiagram} diagrams.
 */
@objid ("d8540744-5a76-11e2-9e33-00137282c51b")
public class StaticProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("65828377-5bd5-11e2-9e33-00137282c51b")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(ClassDiagram.MNAME, null, new ClassDiagramConfigurer());
        
        configurerRegistry.registerDiagramConfigurer(StaticDiagram.MNAME, null, new StaticDiagramConfigurer());
        
    }

    @objid ("6582837c-5bd5-11e2-9e33-00137282c51b")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(ClassDiagram.MNAME, new StaticGmNodeFactory(), new StaticGmLinkFactory(), new StaticEditPartFactory());
        
        factoryRegistry.registerDiagramFactories(StaticDiagram.MNAME, new StaticGmNodeFactory(), new StaticGmLinkFactory(), new StaticEditPartFactory());
        
    }

    @objid ("6584e5ca-5bd5-11e2-9e33-00137282c51b")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Declare the StyleKey providers
        // ------------------------------
        
        // Abstraction
        factoryStyle.declareProvider(GmAbstraction.styleKeyProvider);
        
        // Association
        factoryStyle.declareProvider(GmAssocStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmAssocStructuredStyleKeys.InfoFlows.class);
        factoryStyle.declareProvider(NAssocStructuredStyleKeys.class);
        factoryStyle.declareProvider(NAssocStructuredStyleKeys.InfoFlows.class);
        
        // Binding
        factoryStyle.declareProvider(BindingLinkStyleKeys.class);
        
        // Class
        factoryStyle.declareProvider(GmClassStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmClassStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(GmClassStructuredStyleKeys.Operation);
        factoryStyle.declareProvider(GmClassStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(GmClassStructuredStyleKeys.InternalStructure);
        
        // Class association
        factoryStyle.declareProvider(ClassAssociationLinkStyleKeys.class);
        
        // Collaboration
        factoryStyle.declareProvider(CollaborationStructuredStyleKeys.class);
        factoryStyle.declareProvider(CollaborationStructuredStyleKeys.Inner.class);
        factoryStyle.declareProvider(CollaborationStructuredStyleKeys.InternalStructure.class);
        
        // CollaborationUse
        factoryStyle.declareProvider(CollaborationUseStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(CollabUseLinkStructuredStyleKeys.class);
        factoryStyle.declareProvider(CollabUseLinkStructuredStyleKeys.BindingsGroup.class);
        
        // Component
        factoryStyle.declareProvider(ComponentStructuredStyleKeys.class);
        factoryStyle.declareProvider(ComponentStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(ComponentStructuredStyleKeys.Operation);
        factoryStyle.declareProvider(ComponentStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(ComponentStructuredStyleKeys.InternalStructure);
        
        // Component realization
        factoryStyle.declareProvider(GmComponentRealization.styleKeyProvider);
        
        // Connector
        factoryStyle.declareProvider(ConnectorLinkStructuredStyleKeys.class);
        factoryStyle.declareProvider(ConnectorLinkStructuredStyleKeys.InfoFlows.class);
        factoryStyle.declareProvider(NConnectorStructuredStyleKeys.class);
        factoryStyle.declareProvider(NConnectorStructuredStyleKeys.InfoFlows.class);
        
        // Constraint
        factoryStyle.declareProvider(GmConstraintStyleKeys.class);
        
        // Datatype
        factoryStyle.declareProvider(DataTypeStructuredStyleKeys.class);
        factoryStyle.declareProvider(DataTypeStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(DataTypeStructuredStyleKeys.InternalStructure);
        factoryStyle.declareProvider(DataTypeStructuredStyleKeys.Operation);
        
        // Diagram
        factoryStyle.declareProvider(GmStaticDiagramStyleKeys.class);
        
        // ElementRealization
        factoryStyle.declareProvider(GmElementRealization.styleKeyProvider);
        
        // Enumeration
        factoryStyle.declareProvider(EnumStructuredStyleKeys.class);
        factoryStyle.declareProvider(EnumStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(EnumStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(EnumStructuredStyleKeys.Litteral.class);
        factoryStyle.declareProvider(EnumStructuredStyleKeys.Operation);
        
        // MObject import
        factoryStyle.declareProvider(GmElementImportStructuredStyleKeys.class);
        
        // Package import
        factoryStyle.declareProvider(PackageImportStyleKeys.class);
        
        // Generalization
        factoryStyle.declareProvider(GmGeneralizationStyleKeys.class);
        
        // Information flow
        factoryStyle.declareProvider(InformationFlowLinkStyleKeys.class);
        factoryStyle.declareProvider(InformationFlowLinkStyleKeys.Items.class);
        
        // Information item
        factoryStyle.declareProvider(InformationItemStructuredStyleKeys.class);
        // factory.declareProvider(InformationItemStructuredStyleKeys.Represented.class);
        
        // Instance
        factoryStyle.declareProvider(GmInstanceStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmInstanceStructuredStyleKeys.Slot.class);
        factoryStyle.declareProvider(GmInstanceStructuredStyleKeys.InternalStructure.class);
        
        // Interface
        factoryStyle.declareProvider(InterfaceStructuredStyleKeys.class);
        factoryStyle.declareProvider(InterfaceStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(InterfaceStructuredStyleKeys.Operation);
        factoryStyle.declareProvider(InterfaceStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(InterfaceStructuredStyleKeys.InternalStructure);
        
        // InterfaceRealization
        factoryStyle.declareProvider(GmInterfaceRealizationStyleKeys.class);
        
        // Link
        factoryStyle.declareProvider(InstanceLinkStructuredStyleKeys.class);
        factoryStyle.declareProvider(InstanceLinkStructuredStyleKeys.InfoFlows.class);
        
        factoryStyle.declareProvider(NLinkStructuredStyleKeys.class);
        factoryStyle.declareProvider(NLinkStructuredStyleKeys.InfoFlows.class);
        
        // Package
        factoryStyle.declareProvider(GmPackageStructuredStyleKeys.class);
        
        // Package merge
        factoryStyle.declareProvider(PackageMergeStyleKeys.class);
        
        // Port
        factoryStyle.declareProvider(GmPortStructuredStyleKeys.class);
        
        // Provided interface
        factoryStyle.declareProvider(ProvidedInterfaceStyleKeys.class);
        
        // Raised exception
        factoryStyle.declareProvider(RaisedExceptionStyleKeys.class);
        
        // Required interface
        factoryStyle.declareProvider(RequiredInterfaceStyleKeys.class);
        
        // Signal
        factoryStyle.declareProvider(GmSignalStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmSignalStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(GmSignalStructuredStyleKeys.Operation);
        factoryStyle.declareProvider(GmSignalStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(GmSignalStructuredStyleKeys.InternalStructure);
        
        // Substitution
        factoryStyle.declareProvider(GmSubstitution.styleKeyProvider);
        
        // Template binding
        factoryStyle.declareProvider(TemplateBindingStructuredStyleKeys.class);
        
        // Activity
        factoryStyle.declareProvider(GmActivityStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmActivityImageStyleKeys.class);
        factoryStyle.declareProvider(GmActivitySimpleStyleKeys.class);
        
        // BpmnBehavior
        factoryStyle.declareProvider(GmBpmnBehaviorStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnBehaviorImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnBehaviorSimpleStyleKeys.class);
        
        // BpmnCollaboration
        factoryStyle.declareProvider(GmBpmnCollaborationStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnCollaborationImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnCollaborationSimpleStyleKeys.class);
        
        // BpmnProcess
        factoryStyle.declareProvider(GmBpmnProcessStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnProcessImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnProcessSimpleStyleKeys.class);
        
        // BpmnSharedDefinitions
        factoryStyle.declareProvider(GmBpmnSharedDefinitionsStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnSharedDefinitionsImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnSharedDefinitionsSimpleStyleKeys.class);
        
        // Interaction
        factoryStyle.declareProvider(GmInteractionStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmInteractionImageStyleKeys.class);
        factoryStyle.declareProvider(GmInteractionSimpleStyleKeys.class);
        
        // CommunicationInteraction
        factoryStyle.declareProvider(GmCommunicationInteractionStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmCommunicationInteractionImageStyleKeys.class);
        factoryStyle.declareProvider(GmCommunicationInteractionSimpleStyleKeys.class);
        
        // StateMachine
        factoryStyle.declareProvider(GmStateMachineStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmStateMachineImageStyleKeys.class);
        factoryStyle.declareProvider(GmStateMachineSimpleStyleKeys.class);
        
        // Attribute
        factoryStyle.declareProvider(GmAttributeStyleKeys.class);
        
        // Operation
        factoryStyle.declareProvider(GmOperationStyleKeys.class);
        
    }

    @objid ("6584e5cc-5bd5-11e2-9e33-00137282c51b")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values. Do it only here, after key providers registration.
        BundleContext bundle = DiagramEditorStatik.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        StyleLoader loader = new StyleLoader();
        loader.load(url);
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
        
    }

}
