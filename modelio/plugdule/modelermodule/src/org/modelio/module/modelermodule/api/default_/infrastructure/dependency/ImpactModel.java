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
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << impact_model >> stereotype.
 * <p>Stereotype description:
 * <br/><i>impact_model dependencies are used to associate one or several ImpactModel instances to an ImpactDiagram. The associated models are used to filter the impact links to be displayed in the diagram.</i></p>
 */
@objid ("570c43d0-4d3e-4aa8-89b0-108c1cd48ed9")
public class ImpactModel {
    @objid ("fa255037-0e23-4ede-83cd-683f1dcae415")
    public static final String STEREOTYPE_NAME = "impact_model";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("925f7baa-6432-4771-8297-7ce596ee9584")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactModel proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_model >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("910a7abf-8250-4b85-a48b-d4bcdbe24b33")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_model >> then instantiate a {@link ImpactModel} proxy.
     * 
     * @return a {@link ImpactModel} proxy on the created {@link Dependency}.
     */
    @objid ("7f3bd2d7-1736-4222-8177-639ed27a411a")
    public static ImpactModel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME);
        return ImpactModel.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactModel} proxy or <i>null</i>.
     */
    @objid ("92a43b97-5aa4-4af8-910f-ba1140f1e92e")
    public static ImpactModel instantiate(Dependency obj) {
        return ImpactModel.canInstantiate(obj) ? new ImpactModel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactModel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7aef0de3-03aa-4277-aefb-0b6cbbe7cc72")
    public static ImpactModel safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactModel.canInstantiate(obj))
        	return new ImpactModel(obj);
        else
        	throw new IllegalArgumentException("ImpactModel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8669e320-a8b9-4d30-b8d1-918ce978f929")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ImpactModel other = (ImpactModel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("b0f9b1bd-8613-4757-94e9-d9d231bd0fe1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("226ec4e3-d0f8-43c2-920b-58876149506f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c3617a90-8b75-46d5-96f1-beaa9a102ec1")
    protected  ImpactModel(Dependency elt) {
        this.elt = elt;
    }

    @objid ("2f2f1126-cb55-4153-a6f5-b83673c9034b")
    public static final class MdaTypes {
        @objid ("267b1ec1-40e9-4c7c-b7ba-ca3eaa8acc6b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e8990596-aa41-42a5-857f-c79fab2edccf")
        private static Stereotype MDAASSOCDEP;

        @objid ("0be994bc-31b7-485a-bd09-fba333e9a287")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6fb5f400-0bb4-486d-a12c-35ccda9ac548")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "946458b2-daf1-44b8-887b-12a0d9e5c2f6");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }

	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
