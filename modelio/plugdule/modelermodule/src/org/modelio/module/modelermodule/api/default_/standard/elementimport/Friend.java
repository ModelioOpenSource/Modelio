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
 * Proxy class to handle a {@link ElementImport} with << friend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("46045e22-e2ca-4093-b989-b9b9bfb21781")
public class Friend {
    @objid ("ed91938f-845b-4dba-a6e2-864dff61ab53")
    public static final String STEREOTYPE_NAME = "friend";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("0bcab6b4-6f8d-4042-879b-d287a2106f7b")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Friend proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << friend >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0c1841c5-04e3-4779-871e-8f737d83b259")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << friend >> then instantiate a {@link Friend} proxy.
     * 
     * @return a {@link Friend} proxy on the created {@link ElementImport}.
     */
    @objid ("765b42a1-5988-4743-9da0-46d6cfecea99")
    public static Friend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME);
        return Friend.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Friend} proxy or <i>null</i>.
     */
    @objid ("d9ff38f5-1003-4598-a4dc-4917516ef738")
    public static Friend instantiate(ElementImport obj) {
        return Friend.canInstantiate(obj) ? new Friend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Friend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("74714321-01d9-4139-9299-6df3556d09c7")
    public static Friend safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Friend.canInstantiate(obj))
        	return new Friend(obj);
        else
        	throw new IllegalArgumentException("Friend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0b3b10c2-555b-485f-9069-716ed468eaca")
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
        Friend other = (Friend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("2c110107-9e71-42a4-8548-e200295cb9a7")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("7b3ffc2a-2ab1-4ea4-b7fd-a92ec874baf6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eb9a9041-3699-4f7a-ace9-172b6d5d445f")
    protected Friend(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("e4135690-595b-43d4-b1e7-11c556195a6c")
    public static final class MdaTypes {
        @objid ("535fc4ff-9050-42ec-baf7-ba232a5b2f21")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e06447ad-9ca3-4579-8363-6c67aa13d6fc")
        private static Stereotype MDAASSOCDEP;

        @objid ("b2258dec-9e81-4875-b085-34035fb8ba21")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7fa37790-acca-494a-a254-67ffa7441cbd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ca-0000-000000000000");
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
