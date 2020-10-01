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
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << create >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1ace0953-e65f-4688-9309-8b3b6ed39c7c")
public class Create {
    @objid ("894db26e-3fbe-4924-b087-5d98b1e0b072")
    public static final String STEREOTYPE_NAME = "create";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("1fc5ba09-a100-4c5a-9cbb-5dbf2a1e7689")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Create proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << create >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0f99ca6f-c5d5-4607-bcf6-48b9a2a0d900")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << create >> then instantiate a {@link Create} proxy.
     * 
     * @return a {@link Create} proxy on the created {@link ElementImport}.
     */
    @objid ("0e44cf1c-05a6-4fe7-9c4d-f708ede6d671")
    public static Create create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME);
        return Create.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Create} proxy or <i>null</i>.
     */
    @objid ("56d6701a-d534-48b3-8b49-e834a3e5c932")
    public static Create instantiate(ElementImport obj) {
        return Create.canInstantiate(obj) ? new Create(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Create} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("75164e13-3bd0-4a7e-8505-01f735cc7e54")
    public static Create safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Create.canInstantiate(obj))
        	return new Create(obj);
        else
        	throw new IllegalArgumentException("Create: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("21bb2a2f-7a7b-4148-8103-6a37363441e9")
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
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("196a3f41-0d2f-4fb4-a139-7e8ff94d9a01")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("9de788ba-381f-4b81-a481-1e8b1b267bd0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7f9ae851-c35b-443b-8472-a4ec9d0a37e8")
    protected Create(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("97dedb37-d9c0-46d3-8bc3-639e2cfc8c0e")
    public static final class MdaTypes {
        @objid ("33d8335b-a141-4ff9-925a-adb69d07d719")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2769295f-e796-4359-99aa-2d68975e3ff6")
        private static Stereotype MDAASSOCDEP;

        @objid ("236342c6-213f-48e6-9540-5698ccefb83c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("69425d28-076e-45c8-ba82-fd62c11d675d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0202-0000-000000000000");
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
