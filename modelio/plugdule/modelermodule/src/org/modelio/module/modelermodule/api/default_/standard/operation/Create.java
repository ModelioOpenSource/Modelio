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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << create >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("73b5dac7-a5b3-4178-b056-93aaa0e2ed41")
public class Create {
    @objid ("340ead05-ad40-4bcc-ae74-cf0579728cfa")
    public static final String STEREOTYPE_NAME = "create";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("e51ba6c5-951f-4206-8e26-6db8b2230416")
    protected final Operation elt;

    /**
     * Tells whether a {@link Create proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << create >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("008c7629-0509-4d6e-8de2-ce22017f9f9d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << create >> then instantiate a {@link Create} proxy.
     * 
     * @return a {@link Create} proxy on the created {@link Operation}.
     */
    @objid ("5acf4bad-5c4f-4da0-b67a-ed05ef5a5cbd")
    public static Create create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME);
        return Create.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link Operation} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Create} proxy or <i>null</i>.
     */
    @objid ("68e53490-3876-475f-a771-f834a10ffc1b")
    public static Create instantiate(Operation obj) {
        return Create.canInstantiate(obj) ? new Create(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link Operation} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link Create} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6c424082-1a65-4b2b-ab29-9a0d991f46ee")
    public static Create safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Create.canInstantiate(obj))
        	return new Create(obj);
        else
        	throw new IllegalArgumentException("Create: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5604fb52-c4f6-4296-b015-5a86a2638bc3")
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
        Create other = (Create) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("db705819-4c59-4743-a76c-c183ae81aa98")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("893300d0-1f04-4996-8e2f-c1ff861e53f8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a7cc4075-1bfc-43f8-8352-e2c739d11ad4")
    protected Create(Operation elt) {
        this.elt = elt;
    }

    @objid ("98b0bd6f-4676-4bac-aa4f-c8a2015d89b0")
    public static final class MdaTypes {
        @objid ("1ac69e3d-de1c-4e55-9661-aa1870df9c91")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d72ac412-d664-43e1-9e38-7151303d0e05")
        private static Stereotype MDAASSOCDEP;

        @objid ("1f4226c9-098f-48b1-a7c1-15e3af2cfe16")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fc6c4a20-55f7-4aeb-bb6a-e60dd95c1f18")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0204-0000-000000000000");
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
