/* 
 * Copyright 2013-2020 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.AntonymExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.AssignedExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.ContextExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.DeriveExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.GuaranteeExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.HomonymExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.ImplementExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOfExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MeasureExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluenceExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PartExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluenceExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.RefersExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.RelatedExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.SatisfyExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.SynonymExpert;
import org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.VerifyExpert;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagramExpert;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Implementation of the ModuleComponent interface. <br>
 * All Modelio java modules should inherit from this class.
 */
@objid ("8ff9775c-98fb-4728-946c-288ec1566bef")
public class ModelerModuleModule extends AbstractJavaModule {
    @objid ("538be819-2f1b-441a-a8ce-6c029ff16650")
    private ModelerModulePeerModule peerModule = null;

    @objid ("7185d303-5812-483e-8e9a-fce83c00cf73")
    private ModelerModuleLifeCycleHandler session = null;

    @objid ("98fd1551-c27d-4f1f-83b3-b1bc5f0a716f")
    private static ModelerModuleModule instance;

    @objid ("4b8fe495-814a-4c2b-adfe-48e1be300f9e")
    @Override
    public ModelerModulePeerModule getPeerModule() {
        return this.peerModule;
    }

    /**
     * Builds a new module.
     * <p>
     * <p>
     * This constructor must not be called by the user. It is automatically invoked by Modelio when the module is installed, selected or started.
     * @param moduleContext the module's context.
     */
    @objid ("e8160f77-9187-43c5-ab4f-b3c7116a48de")
    public  ModelerModuleModule(IModuleContext moduleContext) {
        super(moduleContext);
        this.session = new ModelerModuleLifeCycleHandler(this);
        this.peerModule = new ModelerModulePeerModule(this, moduleContext.getPeerConfiguration());
        
        ModelerModuleModule.instance = this;
        
    }

    @objid ("2338e2a2-5259-44d3-9ec3-2a384e9ab1ac")
    @Override
    public String getModuleImagePath() {
        return "/res/icon/modeler_module.png";
    }

    @objid ("fd7d0d97-5580-4c70-827f-6be9e5956852")
    @Override
    public IModelComponentContributor getModelComponentContributor(final IModelComponent mc) {
        return new ModelComponentRamcContributor(this);
    }

    /**
     * @return the only instance of ModelerModule.
     */
    @objid ("46fef5ea-30c3-4c2c-b5c2-2c692a771334")
    public static ModelerModuleModule getInstance() {
        return ModelerModuleModule.instance;
    }

    @objid ("b899b5e7-9ee2-4a12-babe-7571ef06ac61")
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
        return this.session;
    }

    @objid ("b8775758-0558-4204-9103-7aff458bd6b6")
    @Override
    public IMdaExpert getMdaExpert(Stereotype st) {
        // Generated code, please do not remove it
        IMdaExpert expert = getGeneratedMdaExpert(st);
        if (expert != null) {
            return expert;
        } // End generated code
        
        String baseClassName = st.getBaseClassName();
        if (baseClassName.equals(Dependency.MQNAME) || baseClassName.equals(Dependency.MNAME)) {
            if (isAnalystMetamodelPresent()) {
                // Analyst Dependency experts
                switch (st.getName()) {
                case IModelerModuleStereotypes.plusINFLUENCE:
                    return new PlusInfluenceExpert();
                case IModelerModuleStereotypes.moinsINFLUENCE:
                    return new MinusInfluenceExpert();
                case IModelerModuleStereotypes.ANTONYM:
                    return new AntonymExpert();
                case IModelerModuleStereotypes.ASSIGNED:
                    return new AssignedExpert();
                case IModelerModuleStereotypes.CONTEXT:
                    return new ContextExpert();
                case IModelerModuleStereotypes.DERIVE:
                    return new DeriveExpert();
                case IModelerModuleStereotypes.GUARANTEE:
                    return new GuaranteeExpert();
                case IModelerModuleStereotypes.HOMONYM:
                    return new HomonymExpert();
                case IModelerModuleStereotypes.IMPLEMENT:
                    return new ImplementExpert();
                case IModelerModuleStereotypes.KIND_OF:
                    return new KindOfExpert();
                case IModelerModuleStereotypes.MEASURE:
                    return new MeasureExpert();
                case IModelerModuleStereotypes.PART:
                    return new PartExpert();
                case IModelerModuleStereotypes.REFERS:
                    return new RefersExpert();
                case IModelerModuleStereotypes.RELATED:
                    return new RelatedExpert();
                case IModelerModuleStereotypes.SATISFY:
                    return new SatisfyExpert();
                case IModelerModuleStereotypes.SYNONYM:
                    return new SynonymExpert();
                case IModelerModuleStereotypes.VERIFY:
                    return new VerifyExpert();
                default:
                    break;
                }
            }
        
            // Regular Dependency experts
            switch (st.getName()) {
            case IModelerModuleStereotypes.RELATED_DIAGRAM:
                return new RelatedDiagramExpert();
            default:
                break;
            }
        }
        return null;
    }

    /**
     * @return <code>true</code> if the <b>Analyst</b> metamodel fragment is installed in the project.
     */
    @objid ("d87e9616-a773-487d-a840-83b0f0d92e84")
    private boolean isAnalystMetamodelPresent() {
        MClass mClass = getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass("Analyst.Requirement");
        return mClass != null && !mClass.isFake();
    }

    /**
     * Generated expert looking for a MDA expert in the generated MDA API.
     * @param st a stereotype owned by the current module.
     * @return a MDA expert belonging to the MDA API or <code>null</code>.
     */
    @objid ("de8b875d-0802-4ecb-862f-ba2ec7d10f33")
    private IMdaExpert getGeneratedMdaExpert(Stereotype st) {
        switch (st.getUuid()) {
            case "2961d57b-5120-11de-bbaf-00218648fa3d": return new org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagramExpert();
            case "01ec12fc-0000-0247-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PlusInfluenceExpert();
            case "01ec12fc-0000-024c-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MinusInfluenceExpert();
            case "01ec12fc-0000-0233-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.AntonymExpert();
            case "01ec12fc-0000-025b-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.AssignedExpert();
            case "01ec12fc-0000-0242-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.ContextExpert();
            case "01ec12fc-0000-021a-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.DeriveExpert();
            case "01ec12fc-0000-0251-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.GuaranteeExpert();
            case "01ec12fc-0000-0238-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.HomonymExpert();
            case "01ec12fc-0000-0260-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.ImplementExpert();
            case "01ec1228-0000-12f8-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.KindOfExpert();
            case "01ec12fc-0000-0256-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.MeasureExpert();
            case "01ec12fc-0000-00b7-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.PartExpert();
            case "01ec12fc-0000-0265-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.RefersExpert();
            case "01ec12fc-0000-023d-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.RelatedExpert();
            case "01ec12fc-0000-0224-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.SatisfyExpert();
            case "01ec12fc-0000-022e-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.SynonymExpert();
            case "01ec12fc-0000-0229-0000-000000000000": return new org.modelio.module.modelermodule.api.analyst.infrastructure.dependency.VerifyExpert();
            case "e5076ee8-b071-4433-a25d-4d8cdddead0a": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.AllocatedExpert();
            case "c3862c6c-5983-4d1a-b0e2-58dd2685eda0": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.CalledExpert();
            case "143b4e00-fe2e-44d0-9c64-5a95e385ec5a": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.EventExpert();
            case "5de33d2a-ed28-439c-aa09-d11bf1a6d878": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElementExpert();
            case "616b72d4-1d47-49e1-a381-2e6ecfea637c": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.ProcessExpert();
            case "3b4dc351-ccaa-47b8-af57-8434f8e0e5f5": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.ReferenceExpert();
            case "f5d2927d-46d6-4d87-9cf2-adb4a47ca929": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.RepresentsExpert();
            case "c2d2a1ec-2c29-453c-a79c-19e4f2d27f13": return new org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.StateExpert();
            default: return null;
        }
        
    }

}
