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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << implicit >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a2291f1c-9e40-4062-b1f6-c4ef88ee02eb")
public class Implicit {
    @objid ("414dde20-d664-4428-92c3-a2fb888f75a7")
    public static final String STEREOTYPE_NAME = "implicit";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("3d3fc8a3-30f4-4610-9359-0644d9ba7f3f")
    protected final Association elt;

    /**
     * Tells whether a {@link Implicit proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << implicit >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("666a2966-04de-4dfe-82cf-83611c8fb520")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << implicit >> then instantiate a {@link Implicit} proxy.
     * 
     * @return a {@link Implicit} proxy on the created {@link Association}.
     */
    @objid ("dbc7d7ee-d7f0-4629-bd09-be09fe82b5a1")
    public static Implicit create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME);
        return Implicit.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link Implicit} proxy or <i>null</i>.
     */
    @objid ("41ae691a-ce40-434a-8b35-b5d51e9f3544")
    public static Implicit instantiate(Association obj) {
        return Implicit.canInstantiate(obj) ? new Implicit(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link Implicit} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f001d5ab-9ec4-45e2-b948-d4489424c955")
    public static Implicit safeInstantiate(Association obj) throws IllegalArgumentException {
        if (Implicit.canInstantiate(obj))
        	return new Implicit(obj);
        else
        	throw new IllegalArgumentException("Implicit: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f8c06323-3fe2-49e2-ac5c-5730ecf9e419")
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
        Implicit other = (Implicit) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("d0fefc0e-26b2-4cdd-8f08-7f4904cf59c9")
    public Association getElement() {
        return this.elt;
    }

    @objid ("0d1399dc-edba-44c4-a99a-818115bf4678")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bc083cc8-c8c0-4a39-b9a9-e95d4345982a")
    protected Implicit(Association elt) {
        this.elt = elt;
    }

    @objid ("6e6fdf9c-fd6f-4767-bf30-7a823cf4cd32")
    public static final class MdaTypes {
        @objid ("967687cc-3a03-4eaa-a58e-883bc67e796c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1695873f-d714-4ba8-acfc-472f4ed8392f")
        private static Stereotype MDAASSOCDEP;

        @objid ("5974e2f7-a651-42c8-a18a-2b8e51eecd38")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4184b7dd-0244-4971-ba3f-a68b5ef10495")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01b8-0000-000000000000");
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
