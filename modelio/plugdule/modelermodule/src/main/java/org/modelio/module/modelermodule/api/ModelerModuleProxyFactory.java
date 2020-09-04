/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.IDefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.IDefaultModelVisitor;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;

/**
 * Factory that instantiates the right proxy class for a model element stereotyped by a 'ModelerModule' module stereotype.
 * <h2>Module description:</h2>
 * <br/><i></i></p>
 * <h2>Supported stereotypes:</h2>
 * <ul><li><< ExternalDocument >> on a {@link org.modelio.metamodel.uml.infrastructure.Note}: instantiates a {@link ExternalDocument}
 * <li><< ModelComponent >> on a {@link org.modelio.metamodel.uml.statik.Component}: instantiates a {@link ModelComponent}
 * <li><< ModelComponentArchive >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link ModelComponentArchive}
 * <li><< ModelComponentElementAlias >> on a {@link org.modelio.metamodel.uml.infrastructure.UmlModelElement}: instantiates a {@link ModelComponentElementAlias}
 * <li><< access >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Access}
 * <li><< catch >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Catch}
 * <li><< complete >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Complete}
 * <li><< create >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Create}
 * <li><< create >> on a {@link org.modelio.metamodel.uml.statik.Operation}: instantiates a {@link Create}
 * <li><< design pattern >> on a {@link org.modelio.metamodel.uml.statik.Class}: instantiates a {@link DesignPattern}
 * <li><< destroy >> on a {@link org.modelio.metamodel.uml.statik.Operation}: instantiates a {@link Destroy}
 * <li><< destroyed >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Destroyed}
 * <li><< disjoin >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Disjoin}
 * <li><< exception >> on a {@link org.modelio.metamodel.uml.behavior.commonBehaviors.Signal}: instantiates a {@link Exception}
 * <li><< executable >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Executable}
 * <li><< extend >> on a {@link org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency}: instantiates a {@link Extend}
 * <li><< facade >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link Facade}
 * <li><< flow >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Flow}
 * <li><< frameWork >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link FrameWork}
 * <li><< friend >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Friend}
 * <li><< impact_root >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link ImpactRoot}
 * <li><< impact_subroot >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link ImpactSubroot}
 * <li><< implementation >> on a {@link org.modelio.metamodel.uml.statik.Generalization}: instantiates a {@link Implementation}
 * <li><< implementationClass >> on a {@link org.modelio.metamodel.uml.statik.Class}: instantiates a {@link ImplementationClass}
 * <li><< implicit >> on a {@link org.modelio.metamodel.uml.statik.Association}: instantiates a {@link Implicit}
 * <li><< import >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Import}
 * <li><< include >> on a {@link org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency}: instantiates a {@link Include}
 * <li><< incomplete >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Incomplete}
 * <li><< instantiate >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Instantiate}
 * <li><< invariant >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Invariant}
 * <li><< library >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Library}
 * <li><< manifestation >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Manifestation}
 * <li><< metaclass >> on a {@link org.modelio.metamodel.uml.statik.Classifier}: instantiates a {@link Metaclass}
 * <li><< metamodel >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link Metamodel}
 * <li><< new >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link New}
 * <li><< ordered >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Ordered}
 * <li><< overlapping >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Overlapping}
 * <li><< postcondition >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Postcondition}
 * <li><< precondition >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Precondition}
 * <li><< primary >> on a {@link org.modelio.metamodel.uml.behavior.usecaseModel.Actor}: instantiates a {@link Primary}
 * <li><< process >> on a {@link org.modelio.metamodel.uml.statik.Classifier}: instantiates a {@link Process}
 * <li><< scenario >> on a {@link org.modelio.metamodel.uml.behavior.interactionModel.Interaction}: instantiates a {@link Scenario}
 * <li><< secondary >> on a {@link org.modelio.metamodel.uml.behavior.usecaseModel.Actor}: instantiates a {@link Secondary}
 * <li><< stub >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link Stub}
 * <li><< subset >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Subset}
 * <li><< subsystem >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link Subsystem}
 * <li><< system >> on a {@link org.modelio.metamodel.uml.behavior.usecaseModel.Actor}: instantiates a {@link System}
 * <li><< system >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link System}
 * <li><< throw >> on a {@link org.modelio.metamodel.uml.statik.ElementImport}: instantiates a {@link Throw}
 * <li><< topLevel >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link TopLevel}
 * <li><< trace >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Trace}
 * <li><< transient >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Transient}
 * <li><< type >> on a {@link org.modelio.metamodel.uml.statik.Class}: instantiates a {@link Type}
 * <li><< utility >> on a {@link org.modelio.metamodel.uml.statik.Classifier}: instantiates a {@link Utility}
 * <li><< xor >> on a {@link org.modelio.metamodel.uml.infrastructure.Constraint}: instantiates a {@link Xor}
 * <li><< related_diagram >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link RelatedDiagram}
 * <li><< AutoDiagram >> on a {@link org.modelio.metamodel.diagrams.AbstractDiagram}: instantiates a {@link AutoDiagram}
 * <li><< directory >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Directory}
 * <li><< file >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link File}
 * <li><< url >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Url}
 * <li><< mail >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Mail}
 * <li><< see_also >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link SeeAlso}
 * <li><< document >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link Document}
 * <li><< JyMatrix >> on a {@link org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition}: instantiates a {@link JyMatrix}
 * <li><< impact_model >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link ImpactModel}
 * <li><< Methodology >> on a {@link org.modelio.metamodel.uml.infrastructure.Profile}: instantiates a {@link Methodology}
 * <li><< dictionary_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link DictionaryPropertyset}
 * <li><< dictionary_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link DictionaryDiagram}
 * <li><< business_rule_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link BusinessRuleDiagram}
 * <li><< business_rule_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link BusinessRulePropertyset}
 * <li><< requirement_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link RequirementPropertyset}
 * <li><< requirement_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link RequirementDiagram}
 * <li><< goal_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link GoalPropertyset}
 * <li><< goal_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link GoalDiagram}
 * <li><< kpi_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link KpiDiagram}
 * <li><< kpi_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link KpiPropertyset}
 * <li><< risk_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link RiskPropertyset}
 * <li><< risk_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link RiskDiagram}
 * <li><< test_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link TestPropertyset}
 * <li><< test_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link TestDiagram}
 * <li><< part >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Part}
 * <li><< derive >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Derive}
 * <li><< satisfy >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Satisfy}
 * <li><< verify >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Verify}
 * <li><< refers >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Refers}
 * <li><< refine >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Refine}
 * <li><< related >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Related}
 * <li><< implement >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Implement}
 * <li><< +influence >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Influence}
 * <li><< -influence >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Influence}
 * <li><< guarantee >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Guarantee}
 * <li><< homonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Homonym}
 * <li><< measure >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Measure}
 * <li><< antonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Antonym}
 * <li><< assigned >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Assigned}
 * <li><< synonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Synonym}
 * <li><< context >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Context}
 * <li><< kind-of >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link KindOf}
 * <li><< UML2Abstraction >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2Abstraction}
 * <li><< UML2ActionInputPin >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2ActionInputPin}
 * <li><< UML2AddStructuralFeatureValueAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2AddStructuralFeatureValueAction}
 * <li><< UML2AddVariableValueAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2AddVariableValueAction}
 * <li><< UML2Argument >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Argument}
 * <li><< UML2AssociationReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2AssociationReference}
 * <li><< UML2Body >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.ActivityNode}: instantiates a {@link UML2Body}
 * <li><< UML2BodyOutput >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OutputPin}: instantiates a {@link UML2BodyOutput}
 * <li><< UML2BroadcastSignalAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction}: instantiates a {@link UML2BroadcastSignalAction}
 * <li><< UML2ClassifierReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2ClassifierReference}
 * <li><< UML2ClassifierTemplateParameter >> on a {@link org.modelio.metamodel.uml.statik.TemplateParameter}: instantiates a {@link UML2ClassifierTemplateParameter}
 * <li><< UML2ClearAssociationAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ClearAssociationAction}
 * <li><< UML2ClearStructuralFeatureAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ClearStructuralFeatureAction}
 * <li><< UML2ClearVariableAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ClearVariableAction}
 * <li><< UML2Collection >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Collection}
 * <li><< UML2CommunicationPath >> on a {@link org.modelio.metamodel.uml.statik.Association}: instantiates a {@link UML2CommunicationPath}
 * <li><< UML2ComponentRealization >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2ComponentRealization}
 * <li><< UML2ConnectableElementTemplateParameter >> on a {@link org.modelio.metamodel.uml.statik.TemplateParameter}: instantiates a {@link UML2ConnectableElementTemplateParameter}
 * <li><< UML2CreateLinkAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2CreateLinkAction}
 * <li><< UML2CreateLinkObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2CreateLinkObjectAction}
 * <li><< UML2CreateObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2CreateObjectAction}
 * <li><< UML2CreationEvent >> on a {@link org.modelio.metamodel.uml.behavior.commonBehaviors.Event}: instantiates a {@link UML2CreationEvent}
 * <li><< UML2Decider >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OutputPin}: instantiates a {@link UML2Decider}
 * <li><< UML2Deployment >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2Deployment}
 * <li><< UML2DeploymentSpecification >> on a {@link org.modelio.metamodel.uml.statik.Artifact}: instantiates a {@link UML2DeploymentSpecification}
 * <li><< UML2DestroyLinkAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2DestroyLinkAction}
 * <li><< UML2DestroyObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2DestroyObjectAction}
 * <li><< UML2DestructionEvent >> on a {@link org.modelio.metamodel.uml.behavior.commonBehaviors.Event}: instantiates a {@link UML2DestructionEvent}
 * <li><< UML2Device >> on a {@link org.modelio.metamodel.uml.statik.Node}: instantiates a {@link UML2Device}
 * <li><< UML2EndCreationDataReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2EndCreationDataReference}
 * <li><< UML2EndData_Reference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2EndDataReference}
 * <li><< UML2EndDestructionDataReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2EndDestructionDataReference}
 * <li><< UML2Exception >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Exception}
 * <li><< UML2ExceptionHandler >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow}: instantiates a {@link UML2ExceptionHandler}
 * <li><< UML2ExceptionTypeReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2ExceptionTypeReference}
 * <li><< UML2ExecutionEnvironment >> on a {@link org.modelio.metamodel.uml.statik.Node}: instantiates a {@link UML2ExecutionEnvironment}
 * <li><< UML2ExecutionEvent >> on a {@link org.modelio.metamodel.uml.behavior.commonBehaviors.Event}: instantiates a {@link UML2ExecutionEvent}
 * <li><< UML2ExpansionNode >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.Pin}: instantiates a {@link UML2ExpansionNode}
 * <li><< UML2ExpansionRegion >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode}: instantiates a {@link UML2ExpansionRegion}
 * <li><< UML2Extension >> on a {@link org.modelio.metamodel.uml.statik.Association}: instantiates a {@link UML2Extension}
 * <li><< UML2ExtensionEnd >> on a {@link org.modelio.metamodel.uml.statik.AssociationEnd}: instantiates a {@link UML2ExtensionEnd}
 * <li><< UML2First >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2First}
 * <li><< UML2GeneralizationSet >> on a {@link org.modelio.metamodel.uml.statik.Generalization}: instantiates a {@link UML2GeneralizationSet}
 * <li><< UML2InputValue >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2InputValue}
 * <li><< UML2InsertAt >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2InsertAt}
 * <li><< UML2InstanceValue >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2InstanceValue}
 * <li><< UML2Interaction  >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.Activity}: instantiates a {@link UML2Interaction}
 * <li><< UML2InteractionOverviewDiagram  >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link UML2InteractionOverviewDiagram}
 * <li><< UML2LoopVariableInput >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2LoopVariableInput}
 * <li><< UML2MethodReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2MethodReference}
 * <li><< UML2Node >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Node}
 * <li><< UML2Object >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Object}
 * <li><< UML2OperationTemplateParameter >> on a {@link org.modelio.metamodel.uml.statik.Parameter}: instantiates a {@link UML2OperationTemplateParameter}
 * <li><< UML2PropertyType >> on a {@link org.modelio.metamodel.uml.statik.Attribute}: instantiates a {@link UML2PropertyType}
 * <li><< UML2ProtocolConformance >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2ProtocolConformance}
 * <li><< UML2RaiseExceptionAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2RaiseExceptionAction}
 * <li><< UML2ReadExtentAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadExtentAction}
 * <li><< UML2ReadIsClassifierObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadIsClassifierObjectAction}
 * <li><< UML2ReadLinkAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadLinkAction}
 * <li><< UML2ReadLinkObjectEndAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadLinkObjectEndAction}
 * <li><< UML2ReadLinkObjectEndQualifierAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadLinkObjectEndQualifierAction}
 * <li><< UML2ReadSelfAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadSelfAction}
 * <li><< UML2ReadStructuralFeatureAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadStructuralFeatureAction}
 * <li><< UML2ReadVariableAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReadVariableAction}
 * <li><< UML2Reception >> on a {@link org.modelio.metamodel.uml.statik.Operation}: instantiates a {@link UML2Reception}
 * <li><< UML2ReclassifyObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReclassifyObjectAction}
 * <li><< UML2RedefinableTemplateSignature >> on a {@link org.modelio.metamodel.uml.statik.Operation}: instantiates a {@link UML2RedefinableTemplateSignature}
 * <li><< UML2ReduceAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReduceAction}
 * <li><< UML2RemoveAt >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2RemoveAt}
 * <li><< UML2RemoveStructuralFeatureAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2RemoveStructuralFeatureAction}
 * <li><< UML2RemoveVariableValueAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2RemoveVariableValueAction}
 * <li><< UML2ReplyAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ReplyAction}
 * <li><< UML2ReplyValue >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2ReplyValue}
 * <li><< UML2Request >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Request}
 * <li><< UML2Result >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OutputPin}: instantiates a {@link UML2Result}
 * <li><< UML2ReturnInformation >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.Pin}: instantiates a {@link UML2ReturnInformation}
 * <li><< UML2Second >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Second}
 * <li><< UML2SendObjectAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2SendObjectAction}
 * <li><< UML2SequenceNode >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode}: instantiates a {@link UML2SequenceNode}
 * <li><< UML2SetUp >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.ActivityNode}: instantiates a {@link UML2SetUp}
 * <li><< UML2Signal >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2Signal}
 * <li><< UML2StartClassifierBehaviorAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2StartClassifierBehaviorAction}
 * <li><< UML2StartObjectBehaviorAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2StartObjectBehaviorAction}
 * <li><< UML2StereotypeProperty >> on a {@link org.modelio.metamodel.uml.statik.Class}: instantiates a {@link UML2StereotypeProperty}
 * <li><< UML2StructuralFeatureReference >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link UML2StructuralFeatureReference}
 * <li><< UML2Target >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Target}
 * <li><< UML2TemplateSignature >> on a {@link org.modelio.metamodel.uml.statik.Operation}: instantiates a {@link UML2TemplateSignature}
 * <li><< UML2TestIdentityAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2TestIdentityAction}
 * <li><< UML2UnmarshallAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2UnmarshallAction}
 * <li><< UML2Value >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InputPin}: instantiates a {@link UML2Value}
 * <li><< UML2ValueSpecificationAction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction}: instantiates a {@link UML2ValueSpecificationAction}
 * <li><< UML2Variable >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.InstanceNode}: instantiates a {@link UML2Variable}
 * <li><< Pattern >> on a {@link org.modelio.metamodel.uml.statik.Package}: instantiates a {@link Pattern}
 * <li><< PatternRoot >> on a {@link org.modelio.metamodel.uml.infrastructure.UmlModelElement}: instantiates a {@link PatternRoot}
 * <li><< PatternParameter >> on a {@link org.modelio.metamodel.uml.infrastructure.UmlModelElement}: instantiates a {@link PatternParameter}
 * <li><< MDAAssocDep >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link MDAAssocDep}
 * <li><< Allocated >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Allocated}
 * <li><< Called >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Called}
 * <li><< Event >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Event}
 * <li><< PartitionElement >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link PartitionElement}
 * <li><< Process >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Process}
 * <li><< Reference >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Reference}
 * <li><< Represents >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link Represents}
 * <li><< State >> on a {@link org.modelio.metamodel.uml.infrastructure.MethodologicalLink}: instantiates a {@link State}
 * </ul>
 */
@objid ("9727a3e0-3939-4e5f-b6ab-b5e301fb587a")
public class ModelerModuleProxyFactory {
    @objid ("6afaa7a6-c9de-4c8f-9759-a82636e7f6e0")
    private static final InstantiateVisitor instantiateVisitor = new InstantiateVisitor();

    /**
     * Instantiates the right proxy class the given element.
     * <br/>The model element must be stereotyped by a 'ModelerModule' module stereotype.
     * <br/>In the other case the method will return <i>null</i>.
     * @param e A model element
     * @return the right proxy or <i>null</i>.
     */
    @objid ("89837ac1-5778-424c-bf72-3d52b7efc6a4")
    public static final Object instantiate(ModelElement e) {
        for (Stereotype s : e.getExtension()) {
              if (s.getModule().getName().equals(IModelerModulePeerModule.MODULE_NAME)) {
                  return instantiate(e, s.getName());
              }
        }
        return null;
    }

    /**
     * Instantiates the right proxy class the given element with a stereotype name.
     * The stereotype must be one of the 'ModelerModule' module stereotypes.
     * In the other case the method will return <i>null</i>.
     * @param e A model element.
     * @param stName A stereotype name.
     * @return the right proxy or <i>null</i>.
     */
    @objid ("860bb65f-e58d-414a-98f5-726f9808834f")
    public static final Object instantiate(Element e, String stName) {
        instantiateVisitor.setStereotype(stName);
        return e.accept(instantiateVisitor);
    }

    @objid ("9ed2b7ba-8da7-45b9-8039-da375ff664c8")
    private static class InstantiateVisitor implements IDefaultInfrastructureVisitor, IDefaultModelVisitor {
        @objid ("21d41720-0516-4034-a42c-40ad3984880e")
        private String stName;

        @objid ("b8314af0-b48f-44d5-9b9a-114248241775")
        public final void setStereotype(String stName) {
            this.stName = stName;
        }

        @objid ("9ffaab07-8512-4248-b933-c85f32f2ee6e")
        @Override
        public final Object visitAbstractDiagram(AbstractDiagram obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitAbstractDiagram(obj);
        }

        @objid ("9b1398e7-c929-4cec-9d26-a83cc89054f6")
        @Override
        public final Object visitAssociation(Association obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.association.Implicit.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.association.Implicit.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitAssociation(obj);
        }

        @objid ("1f3a14fa-b422-4c7e-b894-1ae0480d70cc")
        @Override
        public final Object visitDependency(Dependency obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Manifestation.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Manifestation.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Trace.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Trace.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactModel.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactModel.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Part.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Part.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Context.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Context.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference.instantiate(obj);
                case org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitDependency(obj);
        }

        @objid ("3699679e-f6e2-4c02-b369-c10098e0b6a6")
        @Override
        public final Object visitComponent(Component obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.component.ModelComponent.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.component.ModelComponent.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitComponent(obj);
        }

        @objid ("8c869d54-c173-4374-af89-99bab1368e5e")
        @Override
        public final Object visitProfile(Profile obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitProfile(obj);
        }

        @objid ("c787495c-eda0-4457-98b1-4c9b2bc6eb2f")
        @Override
        public final Object visitMatrixDefinition(MatrixDefinition obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitMatrixDefinition(obj);
        }

        @objid ("d02e9738-8d0c-4dec-be8b-bea63aa607ee")
        @Override
        public final Object visitClassifier(Classifier obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.classifier.Process.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.classifier.Process.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.classifier.Utility.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.classifier.Utility.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitClassifier(obj);
        }

        @objid ("1a76c6df-f8ac-4dad-a234-e43aa607a41e")
        @Override
        public final Object visitGeneralization(Generalization obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.generalization.Implementation.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.generalization.Implementation.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitGeneralization(obj);
        }

        @objid ("c5898eed-0221-4c32-a088-d7c373df495a")
        @Override
        public final Object visitClass(Class obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.class_.Type.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.class_.Type.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitClass(obj);
        }

        @objid ("833c7606-3ebe-4c53-81d4-28ddad3c92de")
        @Override
        public final Object visitOperation(Operation obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.operation.Create.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.operation.Create.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.operation.Destroy.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.operation.Destroy.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitOperation(obj);
        }

        @objid ("d5d93d87-b0d5-492d-b702-984d079bd082")
        @Override
        public final Object visitUmlModelElement(UmlModelElement obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias.instantiate(obj);
                case org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot.instantiate(obj);
                case org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitUmlModelElement(obj);
        }

        @objid ("5481bf1d-ed7e-4f6b-a099-5b9ad133d46b")
        @Override
        public final Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.KpiPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.KpiPropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitPropertyTableDefinition(obj);
        }

        @objid ("fa2a553e-8f44-4d00-995c-76e5e053b58a")
        @Override
        public final Object visitObjectFlow(ObjectFlow obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitObjectFlow(obj);
        }

        @objid ("34ff3de0-75e4-467a-835b-e500cd66e010")
        @Override
        public final Object visitEvent(Event obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitEvent(obj);
        }

        @objid ("b467a121-4a50-49b7-96fd-4e9e0057f224")
        @Override
        public final Object visitActivityNode(ActivityNode obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitActivityNode(obj);
        }

        @objid ("852c4f37-be64-46b0-8f42-c332fd1a4836")
        @Override
        public final Object visitArtifact(Artifact obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Executable.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Executable.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Library.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Library.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Directory.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Directory.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.File.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.File.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Url.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Url.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Mail.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Mail.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.artifact.Document.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.artifact.Document.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitArtifact(obj);
        }

        @objid ("77b422fc-4402-421a-bf7b-d25634d2e1bc")
        @Override
        public final Object visitAssociationEnd(AssociationEnd obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitAssociationEnd(obj);
        }

        @objid ("17e5c481-3caa-472a-b58b-0cb71056aee8")
        @Override
        public final Object visitInstanceNode(InstanceNode obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitInstanceNode(obj);
        }

        @objid ("2e16b900-0397-40ba-b8bf-77d1ad195a44")
        @Override
        public final Object visitAttribute(Attribute obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitAttribute(obj);
        }

        @objid ("4076d913-1cca-4ad9-b98f-b3a4b8c2b059")
        @Override
        public final Object visitTemplateParameter(TemplateParameter obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitTemplateParameter(obj);
        }

        @objid ("56cc88ab-9f36-43aa-8165-65f070c569f9")
        @Override
        public final Object visitNode(Node obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitNode(obj);
        }

        @objid ("2a327d69-9c52-4e20-9a5a-0f7a1d09af4e")
        @Override
        public final Object visitConstraint(Constraint obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Complete.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Complete.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.New.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.New.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Subset.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Subset.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Transient.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Transient.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.constraint.Xor.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.constraint.Xor.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitConstraint(obj);
        }

        @objid ("6a884384-607e-460b-960b-672170a30e24")
        @Override
        public final Object visitElementImport(ElementImport obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Access.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Access.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Create.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Create.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Import.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Import.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitElementImport(obj);
        }

        @objid ("17f61807-6b60-4bcd-b320-bd919c5eb24a")
        @Override
        public final Object visitPin(Pin obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitPin(obj);
        }

        @objid ("727006ef-56f4-4de7-8aec-baedb0475205")
        @Override
        public final Object visitOutputPin(OutputPin obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitOutputPin(obj);
        }

        @objid ("6fee9853-fa47-41f8-83e0-44936662f310")
        @Override
        public final Object visitNote(Note obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitNote(obj);
        }

        @objid ("583302f4-6072-4206-a763-64575a384bd7")
        @Override
        public final Object visitActivity(Activity obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitActivity(obj);
        }

        @objid ("005b3228-686b-49ee-b9e2-0ab409f42b97")
        @Override
        public final Object visitSendSignalAction(SendSignalAction obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitSendSignalAction(obj);
        }

        @objid ("936ef6f3-e502-4104-8409-08683ac71c24")
        @Override
        public final Object visitStaticDiagram(StaticDiagram obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.KpiDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.KpiDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitStaticDiagram(obj);
        }

        @objid ("8ae53ce1-faf2-4f81-b331-fd3966006eb2")
        @Override
        public final Object visitPackage(Package obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.package_.Facade.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.Facade.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.Metamodel.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.Metamodel.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.Stub.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.Stub.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.System.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.System.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel.instantiate(obj);
                case org.modelio.module.modelermodule.api.templateprofile.standard.package_.Pattern.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.templateprofile.standard.package_.Pattern.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitPackage(obj);
        }

        @objid ("bd0310c7-a518-441e-a56e-7aad751fa467")
        @Override
        public final Object visitSignal(Signal obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.signal.Exception.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.signal.Exception.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitSignal(obj);
        }

        @objid ("5855c9cf-2972-42a1-b6bf-f23b3a8ab0e7")
        @Override
        public final Object visitInputPin(InputPin obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitInputPin(obj);
        }

        @objid ("b6ab8a6f-bfcb-4377-9bc3-96b7ab006bc3")
        @Override
        public final Object visitMethodologicalLink(MethodologicalLink obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents.instantiate(obj);
                case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.instantiate(obj);
                default:
                    break;
            }
            return IDefaultInfrastructureVisitor.super.visitMethodologicalLink(obj);
        }

        @objid ("ea35fabd-f55f-4a20-aa47-8232b76ea8fe")
        @Override
        public final Object visitUseCaseDependency(UseCaseDependency obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitUseCaseDependency(obj);
        }

        @objid ("4cb0de27-6a18-4585-a5ee-51374ca34c22")
        @Override
        public final Object visitInteraction(Interaction obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitInteraction(obj);
        }

        @objid ("a83e04bf-a347-4835-88d8-0b2e3e5daf4e")
        @Override
        public final Object visitActor(Actor obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.default_.standard.actor.Primary.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.actor.Primary.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.actor.Secondary.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.actor.Secondary.instantiate(obj);
                case org.modelio.module.modelermodule.api.default_.standard.actor.System.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.default_.standard.actor.System.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitActor(obj);
        }

        @objid ("9d2e8bcf-e618-405c-9ca3-af925ef7de70")
        @Override
        public final Object visitOpaqueAction(OpaqueAction obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitOpaqueAction(obj);
        }

        @objid ("7739ba3c-d165-4dc0-9c5e-47184842ab5b")
        @Override
        public final Object visitParameter(Parameter obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitParameter(obj);
        }

        @objid ("3ef694ae-6900-4609-83d3-a0371cb625fa")
        @Override
        public final Object visitStructuredActivityNode(StructuredActivityNode obj) {
            switch (this.stName) {
                case org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion.instantiate(obj);
                case org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode.STEREOTYPE_NAME: return org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode.instantiate(obj);
                default:
                    break;
            }
            return IDefaultModelVisitor.super.visitStructuredActivityNode(obj);
        }

        /**
         * Get the visitor to delegate to when a {@link IInfrastructureVisitor} is needed.
         * <p>If null is returned the caller will return null.
         * @return the {@link IInfrastructureVisitor} visitor or <i>null</i>.
         */
        @objid ("5fd58998-44b6-4b53-84f7-acc277918a04")
        @Override
        public IInfrastructureVisitor getInfrastructureVisitor() {
            return this;
        }

    }

}
