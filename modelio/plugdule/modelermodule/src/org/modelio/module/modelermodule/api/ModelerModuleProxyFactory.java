/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.KpiPropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset;
import org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.KpiDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram;
import org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso;
import org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix;
import org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument;
import org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology;
import org.modelio.module.modelermodule.api.default_.standard.actor.Primary;
import org.modelio.module.modelermodule.api.default_.standard.actor.Secondary;
import org.modelio.module.modelermodule.api.default_.standard.artifact.Directory;
import org.modelio.module.modelermodule.api.default_.standard.artifact.Mail;
import org.modelio.module.modelermodule.api.default_.standard.artifact.Url;
import org.modelio.module.modelermodule.api.default_.standard.association.Implicit;
import org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern;
import org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass;
import org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Complete;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant;
import org.modelio.module.modelermodule.api.default_.standard.constraint.New;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition;
import org.modelio.module.modelermodule.api.default_.standard.constraint.Xor;
import org.modelio.module.modelermodule.api.default_.standard.elementimport.Access;
import org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch;
import org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend;
import org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate;
import org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw;
import org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario;
import org.modelio.module.modelermodule.api.default_.standard.operation.Destroy;
import org.modelio.module.modelermodule.api.default_.standard.package_.Facade;
import org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork;
import org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem;
import org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel;
import org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias;
import org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend;
import org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include;
import org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter;
import org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference;
import org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction;
import org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body;
import org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp;
import org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification;
import org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath;
import org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension;
import org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd;
import org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType;
import org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty;
import org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent;
import org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent;
import org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent;
import org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target;
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value;
import org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable;
import org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device;
import org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment;
import org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction;
import org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception;
import org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature;
import org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature;
import org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput;
import org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider;
import org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result;
import org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter;
import org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode;
import org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation;
import org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction;
import org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram;
import org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion;
import org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode;
import org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter;
import org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter;

/**
 * Factory that instantiates the right proxy class for a model element stereotyped by a 'ModelerModule' module stereotype.
 * <h2>Module description:</h2>
 * <br/>
 * <i>Standard Modelio UML Modeler extensions.</i>
 * </p>
 * <h2>Supported stereotypes:</h2>
 * <ul>
 * <li><< ExternalDocument >> on a {@link org.modelio.metamodel.uml.infrastructure.Note}: instantiates a {@link ExternalDocument}
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
 * <li><< +influence >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link PlusInfluence}
 * <li><< -influence >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link MinusInfluence}
 * <li><< antonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Antonym}
 * <li><< assigned >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Assigned}
 * <li><< business_rule_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link BusinessRuleDiagram}
 * <li><< business_rule_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link BusinessRulePropertyset}
 * <li><< context >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Context}
 * <li><< derive >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Derive}
 * <li><< dictionary_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link DictionaryDiagram}
 * <li><< dictionary_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link DictionaryPropertyset}
 * <li><< goal_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link GoalDiagram}
 * <li><< goal_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link GoalPropertyset}
 * <li><< guarantee >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Guarantee}
 * <li><< homonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Homonym}
 * <li><< implement >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Implement}
 * <li><< kind-of >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link KindOf}
 * <li><< kpi_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link KpiDiagram}
 * <li><< kpi_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link KpiPropertyset}
 * <li><< measure >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Measure}
 * <li><< part >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Part}
 * <li><< refers >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Refers}
 * <li><< refine >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Refine}
 * <li><< related >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Related}
 * <li><< requirement_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link RequirementDiagram}
 * <li><< requirement_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link RequirementPropertyset}
 * <li><< risk_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link RiskDiagram}
 * <li><< risk_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link RiskPropertyset}
 * <li><< satisfy >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Satisfy}
 * <li><< synonym >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Synonym}
 * <li><< test_diagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link TestDiagram}
 * <li><< test_propertyset >> on a {@link org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition}: instantiates a {@link TestPropertyset}
 * <li><< verify >> on a {@link org.modelio.metamodel.uml.infrastructure.Dependency}: instantiates a {@link Verify}
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
 * <li><< UML2Interaction >> on a {@link org.modelio.metamodel.uml.behavior.activityModel.Activity}: instantiates a {@link UML2Interaction}
 * <li><< UML2InteractionOverviewDiagram >> on a {@link org.modelio.metamodel.diagrams.StaticDiagram}: instantiates a {@link UML2InteractionOverviewDiagram}
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
    @objid ("85488ae4-db24-46f6-ae2b-4b6cd938213a")
    private static final InstantiateVisitor instantiateVisitor = new InstantiateVisitor();

    /**
     * Instantiates the right proxy class the given element.
     * <br/>
     * The model element must be stereotyped by a 'ModelerModule' module stereotype.
     * <br/>
     * In the other case the method will return <i>null</i>.
     * 
     * @param e A model element
     * @return the right proxy or <i>null</i>.
     */
    @objid ("4d4809f8-da01-41c6-9a4c-a6335426066d")
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
     * 
     * @param e A model element.
     * @param stName A stereotype name.
     * @return the right proxy or <i>null</i>.
     */
    @objid ("84e2ce82-531d-46cb-a686-29d0fba2ec94")
    public static final Object instantiate(Element e, String stName) {
        ModelerModuleProxyFactory.instantiateVisitor.setStereotype(stName);
        return e.accept(ModelerModuleProxyFactory.instantiateVisitor);
    }

    @objid ("7e9f615e-2feb-4405-82f6-abcd7b93992a")
    private static class InstantiateVisitor implements IDefaultModelVisitor, IDefaultInfrastructureVisitor {
        @objid ("fee3210c-7abb-4162-bc8d-fe5f839461c5")
        private String stName;

        /**
         * Get the visitor to delegate to when a {@link IInfrastructureVisitor} is needed.
         * <p>
         * If null is returned the caller will return null.
         * 
         * @return the {@link IInfrastructureVisitor} visitor or <i>null</i>.
         */
        @objid ("515cb55a-848b-4044-9472-2cd4b6b29be5")
        @Override
        public IInfrastructureVisitor getInfrastructureVisitor() {
            return this;
        }

        @objid ("f92dd148-162a-4b76-b8b0-df96bc16cb50")
        public final void setStereotype(String stName) {
            this.stName = stName;
        }

        @objid ("a02cac1b-56e3-4279-a6c8-a3694d78af22")
        @Override
        public final Object visitAbstractDiagram(AbstractDiagram obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitAbstractDiagram(obj);
        }

        @objid ("1296b1b4-d139-4068-9c27-6f126a2e3f2e")
        @Override
        public final Object visitActivity(Activity obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitActivity(obj);
        }

        @objid ("33cda18e-ec87-445e-a55f-9cf101746061")
        @Override
        public final Object visitActivityNode(ActivityNode obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitActivityNode(obj);
        }

        @objid ("c9abf92b-a10b-4bbe-bab6-4639ee57066c")
        @Override
        public final Object visitActor(Actor obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.actor.Primary.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.actor.Primary.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.actor.Secondary.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.actor.Secondary.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.actor.System.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.actor.System.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitActor(obj);
        }

        @objid ("c0905cb7-45ce-49d2-ae35-c1517051c8cb")
        @Override
        public final Object visitArtifact(Artifact obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Executable.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Executable.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Library.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Library.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Directory.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Directory.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.File.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.File.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Url.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Url.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Mail.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Mail.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.artifact.Document.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.artifact.Document.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitArtifact(obj);
        }

        @objid ("3fd4d5fd-e9a0-4065-9acb-d6d4090ea98d")
        @Override
        public final Object visitAssociation(Association obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.association.Implicit.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.association.Implicit.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAssociation(obj);
        }

        @objid ("b8c07763-31ec-4573-90bc-0a135987af92")
        @Override
        public final Object visitAssociationEnd(AssociationEnd obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAssociationEnd(obj);
        }

        @objid ("492f613f-a48a-4630-96ae-b047f866df44")
        @Override
        public final Object visitAttribute(Attribute obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAttribute(obj);
        }

        @objid ("bd2e483b-2f6b-4c7c-a318-2a12fabf7afc")
        @Override
        public final Object visitClass(Class obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.class_.Type.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.class_.Type.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitClass(obj);
        }

        @objid ("b18eeea2-ab22-495a-97f4-273d882de8a6")
        @Override
        public final Object visitClassifier(Classifier obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.classifier.Process.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.classifier.Process.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.classifier.Utility.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.classifier.Utility.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitClassifier(obj);
        }

        @objid ("feab984b-18f3-4bcc-a22d-fe17c65243ad")
        @Override
        public final Object visitComponent(Component obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.component.ModelComponent.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.component.ModelComponent.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitComponent(obj);
        }

        @objid ("32a82d8c-9648-4f7c-a643-d56041f80f8d")
        @Override
        public final Object visitConstraint(Constraint obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Complete.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Complete.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.New.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.New.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Subset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Subset.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Transient.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Transient.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.constraint.Xor.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.constraint.Xor.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitConstraint(obj);
        }

        @objid ("5bfbf947-a4f1-4460-9d8d-a6ddec818ad9")
        @Override
        public final Object visitDependency(Dependency obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Manifestation.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Manifestation.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Trace.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Trace.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactModel.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactModel.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Context.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Context.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Part.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Part.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference.instantiate(obj);
            case org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitDependency(obj);
        }

        @objid ("edf412c5-dcb9-4eec-9eda-c0f357e57cc4")
        @Override
        public final Object visitElementImport(ElementImport obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Access.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Access.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Create.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Create.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Import.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Import.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitElementImport(obj);
        }

        @objid ("83e0faa0-8b40-4b4f-8338-d7a1abf1e3b1")
        @Override
        public final Object visitEvent(Event obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitEvent(obj);
        }

        @objid ("529dac09-7c24-4e63-a50f-05182bc6f729")
        @Override
        public final Object visitGeneralization(Generalization obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.generalization.Implementation.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.generalization.Implementation.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitGeneralization(obj);
        }

        @objid ("fc4871b7-079d-4862-9e32-b22daa5e7f91")
        @Override
        public final Object visitInputPin(InputPin obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitInputPin(obj);
        }

        @objid ("35ae23a7-e041-4681-a83b-b59167665c9a")
        @Override
        public final Object visitInstanceNode(InstanceNode obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitInstanceNode(obj);
        }

        @objid ("567cd3f8-68a8-4332-be8b-bacc7b5c48ab")
        @Override
        public final Object visitInteraction(Interaction obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitInteraction(obj);
        }

        @objid ("889ea07b-8cca-4e23-a182-11362742aef2")
        @Override
        public final Object visitMatrixDefinition(MatrixDefinition obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitMatrixDefinition(obj);
        }

        @objid ("57cc14a1-0801-455f-8db8-3210841a0fe8")
        @Override
        public final Object visitMethodologicalLink(MethodologicalLink obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents.instantiate(obj);
            case org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitMethodologicalLink(obj);
        }

        @objid ("1e08771c-5d08-4f3c-afde-c76c702a51e4")
        @Override
        public final Object visitNode(Node obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitNode(obj);
        }

        @objid ("89725a15-3c60-430c-a9a3-d7ef4d2c601e")
        @Override
        public final Object visitNote(Note obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitNote(obj);
        }

        @objid ("db97986c-c445-4638-839d-06278bc9c837")
        @Override
        public final Object visitObjectFlow(ObjectFlow obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitObjectFlow(obj);
        }

        @objid ("f098b6f8-b070-4f58-aa1a-66ba8d312e4b")
        @Override
        public final Object visitOpaqueAction(OpaqueAction obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitOpaqueAction(obj);
        }

        @objid ("c13ce4ad-ffcf-4652-a575-df1c96d05797")
        @Override
        public final Object visitOperation(Operation obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.operation.Create.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.operation.Create.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.operation.Destroy.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.operation.Destroy.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitOperation(obj);
        }

        @objid ("9f536162-07b6-41a9-b1a2-2c4ec58660c7")
        @Override
        public final Object visitOutputPin(OutputPin obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitOutputPin(obj);
        }

        @objid ("2f25f7b0-518d-4390-b3c6-de750449b111")
        @Override
        public final Object visitPackage(Package obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.package_.Facade.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.Facade.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.Metamodel.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.Metamodel.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.Stub.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.Stub.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.System.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.System.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel.instantiate(obj);
            case org.modelio.module.modelermodule.api.templateprofile.standard.package_.Pattern.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.templateprofile.standard.package_.Pattern.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitPackage(obj);
        }

        @objid ("ab488c84-c0ab-4bcb-8229-4d8229504d18")
        @Override
        public final Object visitParameter(Parameter obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitParameter(obj);
        }

        @objid ("4a3c6b68-3c01-497c-8ced-e33ce9969241")
        @Override
        public final Object visitPin(Pin obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitPin(obj);
        }

        @objid ("c05fe034-fae2-42cc-94fe-9635ae9a49c3")
        @Override
        public final Object visitProfile(Profile obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitProfile(obj);
        }

        @objid ("501462fd-0398-44f8-8731-8960692f7911")
        @Override
        public final Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.KpiPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.KpiPropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset.instantiate(obj);
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitPropertyTableDefinition(obj);
        }

        @objid ("60f3dbbe-fa43-49ee-bc7b-a47c7e5a973d")
        @Override
        public final Object visitSendSignalAction(SendSignalAction obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitSendSignalAction(obj);
        }

        @objid ("52ca3a3e-2b0a-4f79-849a-0ea7b186704a")
        @Override
        public final Object visitSignal(Signal obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.signal.Exception.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.signal.Exception.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitSignal(obj);
        }

        @objid ("25db447c-e4aa-4866-a16e-ee36583a53c6")
        @Override
        public final Object visitStaticDiagram(StaticDiagram obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.KpiDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.KpiDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitStaticDiagram(obj);
        }

        @objid ("f64dddd0-f5ec-4064-bdae-42776a253d4c")
        @Override
        public final Object visitStructuredActivityNode(StructuredActivityNode obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitStructuredActivityNode(obj);
        }

        @objid ("a2936aa7-3d71-4de5-940d-5506bbb09e5d")
        @Override
        public final Object visitTemplateParameter(TemplateParameter obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter.instantiate(obj);
            case org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitTemplateParameter(obj);
        }

        @objid ("fa70754b-d361-43f1-8bd2-5b6a065d7d56")
        @Override
        public final Object visitUmlModelElement(UmlModelElement obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias.instantiate(obj);
            case org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot.instantiate(obj);
            case org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitUmlModelElement(obj);
        }

        @objid ("5eafd229-5a03-4879-ac72-e51e87a6a068")
        @Override
        public final Object visitUseCaseDependency(UseCaseDependency obj) {
            switch (this.stName) {
            case org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend.instantiate(obj);
            case org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include.STEREOTYPE_NAME:
                return org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include.instantiate(obj);
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitUseCaseDependency(obj);
        }

    }

}
