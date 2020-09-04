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
package org.modelio.module.modelermodule.api.default_.standard.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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
 * Proxy class to handle a {@link Signal} with << exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d36d02b-ca62-4e9d-ac9f-5616c922fdd2")
public class Exception {
    @objid ("e6240165-6be5-4506-a860-58c8e5ee0d11")
    public static final String STEREOTYPE_NAME = "exception";

    /**
     * The underlying {@link Signal} represented by this proxy, never null.
     */
    @objid ("84ef82f1-bf74-4f6f-8c69-4145e89335a7")
    protected final Signal elt;

    /**
     * Tells whether a {@link Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link Signal} stereotyped << exception >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("81de404e-b660-4373-b0db-ce3b3add1afa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Signal) && ((Signal) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Signal} stereotyped << exception >> then instantiate a {@link Exception} proxy.
     * 
     * @return a {@link Exception} proxy on the created {@link Signal}.
     */
    @objid ("8ccf2076-4379-46ed-97e6-a4986b4ea51f")
    public static Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Signal");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME);
        return Exception.instantiate((Signal)e);
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Signal
     * @return a {@link Exception} proxy or <i>null</i>.
     */
    @objid ("19e39d9f-32e2-455f-8c7f-755b45e7caf3")
    public static Exception instantiate(Signal obj) {
        return Exception.canInstantiate(obj) ? new Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Signal}
     * @return a {@link Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("07c82414-654c-49ed-9c61-0f8af4967f08")
    public static Exception safeInstantiate(Signal obj) throws IllegalArgumentException {
        if (Exception.canInstantiate(obj))
        	return new Exception(obj);
        else
        	throw new IllegalArgumentException("Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c637ea5b-a409-45ad-8dfb-2bd7295614e2")
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
        Exception other = (Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Signal}. 
     * @return the Signal represented by this proxy, never null.
     */
    @objid ("274e31a6-1e3d-489d-b2ce-bff16ddbbd54")
    public Signal getElement() {
        return this.elt;
    }

    @objid ("0d1b4df8-571d-46e9-8a59-de1c43e86607")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4321ad4d-f139-4d7a-9521-8634a9b8a534")
    protected Exception(Signal elt) {
        this.elt = elt;
    }

    @objid ("9d616c88-ee7e-4e3e-a81d-c779a5fefc14")
    public static final class MdaTypes {
        @objid ("9fb5929f-ddcd-4d25-844a-301327fea512")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7568959d-dd00-47cc-848b-7a7fae24e6b5")
        private static Stereotype MDAASSOCDEP;

        @objid ("d0d38c37-6f81-4a65-9071-f8bc38925683")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f3f99985-0719-4324-ab3a-80662a7e14f6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d1-0000-000000000000");
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
