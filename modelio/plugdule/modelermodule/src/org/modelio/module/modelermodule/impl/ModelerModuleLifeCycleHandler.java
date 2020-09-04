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

package org.modelio.module.modelermodule.impl;

import java.io.File;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.mc.IModelComponentDescriptor;
import org.modelio.api.modelio.mc.IModelComponentService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.lifecycle.DefaultModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vbasic.version.Version;

/**
 * Implementation of the {@link IModuleLifeCycleHandler} interface.
 */
@objid ("d8754ec4-6010-405d-8f31-6b1a17caf233")
public class ModelerModuleLifeCycleHandler extends DefaultModuleLifeCycleHandler {
    @objid ("4943d7e3-2748-45e4-b002-f447f0a5fd81")
    private static final String LATEST_PREDEFINED_TYPES_VERSION = "3.9.00";

    /**
     * Constructor.
     */
    @objid ("fc86ffb4-5c1c-4812-b07f-11d2c091daf4")
    public ModelerModuleLifeCycleHandler(final ModelerModuleModule module) {
        super(module);
    }

    @objid ("5cb5bab7-0298-45e2-a24f-a4a5cdd75957")
    @Override
    public boolean start() throws ModuleException {
        // Remove the metamodelVersion
        final Version version = this.module.getVersion();
        final String fullVersion = version.toString();
        final String message = "Modelio/" + this.module.getName() + " " + fullVersion + " - Copyright 2008-2019 Modeliosoft";
        
        reinitializeProxies();
        
        // Get the Modelio log service
        final ILogService logService = this.module.getModuleContext().getLogService();
        
        // Print copyright
        logService.info(message);
        
        // Deploy predefined types
        checkPredefinedTypes();
        return super.start();
    }

    @objid ("33098d1a-a41b-4008-bd00-ec2c71a8a231")
    private void checkPredefinedTypes() {
        final IModelComponentService modelComponentService = this.module.getModuleContext().getModelioServices().getModelComponentService();
        
        // Check if the predefined type project exists...
        // FIXME hack, use modelComponentService to identify predefined types and handle the upgrade case...
        Artifact ramc = this.module.getModuleContext().getModelingSession().findElementById(Artifact.class, "4bb461ab-1cb8-4e47-ab98-5ab266bd55cb");
        if (ramc != null && !ramc.isStereotyped("ModelerModule", "ModelComponentArchive")) {
            modelComponentService.removeModelComponent(new IModelComponentDescriptor() {
        
                @Override
                public String getVersion() {
                    return null;
                }
        
                @Override
                public String getName() {
                    return "PredefinedTypes";
                }
            });
            ramc = null;
        }
        // end hack
        
        if (ramc == null) {
            deployModelComponent(modelComponentService);
            return;
        }
        
        // Upgrade 3.0.00 -> 3.0.01
        final PropertyType floatType = this.module.getModuleContext().getModelingSession().findElementById(PropertyType.class, "bc36b99e-8470-4aee-ba2a-5012c0a17525");
        if (floatType == null) {
            deployModelComponent(modelComponentService);
            return;
        }
        
        // Upgrade < Latest PredefinedTypes version
        for (final IModelComponentDescriptor mc : modelComponentService.getModelComponents()) {
            if (mc.getName().equals("PredefinedTypes") && new Version(mc.getVersion()).isOlderThan(new Version(ModelerModuleLifeCycleHandler.LATEST_PREDEFINED_TYPES_VERSION))) {
                deployModelComponent(modelComponentService);
            }
            return;
        }
    }

    @objid ("7adbc176-d89d-40dc-a8d5-792e88faf73f")
    public void deployModelComponent(final IModelComponentService modelComponentService) {
        final File predefinedTypesRamc = new File(this.module.getModuleContext().getConfiguration().getModuleResourcesPath().toFile(), "res/PredefinedTypes.ramc");
        if (predefinedTypesRamc.exists()) {
            modelComponentService.deployModelComponent(predefinedTypesRamc, null);
        }
    }

    @objid ("f840a3f0-4c7d-4413-a514-e678bf24cd77")
    @Override
    public void stop() throws ModuleException {
        super.stop();
    }

    @objid ("9fadef4c-2127-4cf8-9ad0-dc01eb3d7a7f")
    public static boolean install(final String modelioPath, final String mdaPath) throws ModuleException {
        return DefaultModuleLifeCycleHandler.install(modelioPath, mdaPath);
    }

    @objid ("55d66db8-4baa-4abf-b815-f543f02bd09e")
    @Override
    public boolean select() throws ModuleException {
        return super.select();
    }

    @objid ("facd4a81-dc01-4208-bc7d-b02f9b0586cc")
    @Override
    public void unselect() throws ModuleException {
        super.unselect();
    }

    @objid ("3acdcb2e-0366-4030-b2fa-769c1be260ca")
    @Override
    public void upgrade(final Version oldVersion, final Map<String, String> oldParameters) throws ModuleException {
        super.upgrade(oldVersion, oldParameters);
    }

    /**
     * Reload all MDA types references.
     * <p>
     * Note : do not modify by hand the code, it is generated from a macro in the operation.
     */
    @objid ("2ff807c0-6442-4e79-9d5e-64f4ba6cc178")
    private void reinitializeProxies() {
        // Code generated from Jython macro in the 'reinitializeProxies'{6ab169ab-0411-428f-b0f2-b89b4d4146f3} Standard.Operation operation.
        final IModuleContext moduleContext = this.module.getModuleContext();
        org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Flow.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactModel.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactRoot.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.ImpactSubroot.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Manifestation.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.SeeAlso.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.dependency.Trace.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition.JyMatrix.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.note.ExternalDocument.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.infrastructure.profile.Methodology.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.actor.Primary.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.actor.Secondary.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.actor.System.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Directory.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Document.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Executable.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.File.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Library.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Mail.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.artifact.Url.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.association.Implicit.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.class_.DesignPattern.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.class_.ImplementationClass.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.class_.Type.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.classifier.Metaclass.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.classifier.Process.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.classifier.Utility.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.component.ModelComponent.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Complete.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Destroyed.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Disjoin.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Incomplete.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Invariant.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.New.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Ordered.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Overlapping.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Postcondition.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Precondition.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Subset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Transient.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.constraint.Xor.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Access.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Catch.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Create.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Friend.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Import.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Instantiate.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.elementimport.Throw.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.generalization.Implementation.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.interaction.Scenario.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.operation.Create.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.operation.Destroy.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.Facade.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.FrameWork.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.Metamodel.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.Stub.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.Subsystem.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.System.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.package_.TopLevel.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.signal.Exception.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.umlmodelelement.ModelComponentElementAlias.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Extend.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.default_.standard.usecasedependency.Include.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Antonym.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Assigned.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Context.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Derive.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Guarantee.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Homonym.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Implement.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOf.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Measure.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluence.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Part.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluence.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refers.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Refine.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Related.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Satisfy.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Synonym.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.Verify.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.BusinessRulePropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.DictionaryPropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.GoalPropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RequirementPropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.RiskPropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition.TestPropertyset.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.DictionaryDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.BusinessRuleDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RequirementDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.GoalDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.RiskDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.analyst.standard.staticdiagram.TestDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Abstraction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ClassifierReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ComponentRealization.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndCreationDataReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDataReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2EndDestructionDataReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ExceptionTypeReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2InstanceValue.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Signal.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.activity.UML2Interaction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2Body.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.activitynode.UML2SetUp.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.association.UML2CommunicationPath.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.association.UML2Extension.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.associationend.UML2ExtensionEnd.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.attribute.UML2PropertyType.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.class_.UML2StereotypeProperty.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.event.UML2CreationEvent.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.event.UML2DestructionEvent.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.event.UML2ExecutionEvent.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.generalization.UML2GeneralizationSet.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Argument.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Collection.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Exception.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2First.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InputValue.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2InsertAt.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2LoopVariableInput.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Node.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Object.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2RemoveAt.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ReplyValue.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Request.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Second.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Target.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2Value.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.instancenode.UML2Variable.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.objectflow.UML2ExceptionHandler.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddStructuralFeatureValueAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearVariableAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RaiseExceptionAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadExtentAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadIsClassifierObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadStructuralFeatureAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReclassifyObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveStructuralFeatureAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartClassifierBehaviorAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2TestIdentityAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.operation.UML2Reception.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.operation.UML2TemplateSignature.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2BodyOutput.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Decider.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.outputpin.UML2Result.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ExpansionNode.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.pin.UML2ReturnInformation.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction.UML2BroadcastSignalAction.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.staticdiagram.UML2InteractionOverviewDiagram.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2ExpansionRegion.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode.UML2SequenceNode.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.templateprofile.standard.package_.Pattern.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternRoot.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement.PatternParameter.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.mda.infrastructure.dependency.MDAAssocDep.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.AbstractMethodologicalLink.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Allocated.MdaTypes.init(moduleContext);
        org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State.MdaTypes.init(moduleContext);
    }

}
