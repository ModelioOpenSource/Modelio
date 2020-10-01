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
package org.modelio.module.modelermodule.api.default_.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << type >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81f548a1-1daa-4886-a9f2-8c71e8080095")
public class Type {
    @objid ("181a8a14-8b8e-4248-970c-a5d8e9092ca0")
    public static final String STEREOTYPE_NAME = "type";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("37d1a55d-aeff-4ca0-b7e1-cb19e95608ec")
    protected final Class elt;

    /**
     * Tells whether a {@link Type proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << type >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e41242ed-dc63-4036-a779-34232282b2c2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << type >> then instantiate a {@link Type} proxy.
     * 
     * @return a {@link Type} proxy on the created {@link Class}.
     */
    @objid ("770bdf18-240a-437d-a8d2-dc26b63137f4")
    public static Type create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME);
        return Type.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link Type} proxy from a {@link Class} stereotyped << type >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link Type} proxy or <i>null</i>.
     */
    @objid ("b6c3d007-41bc-4fa0-ac90-03d2ce8fdcd0")
    public static Type instantiate(Class obj) {
        return Type.canInstantiate(obj) ? new Type(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Type} proxy from a {@link Class} stereotyped << type >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link Type} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ac1d49a7-f736-4aa8-82ac-628384b5b4b4")
    public static Type safeInstantiate(Class obj) throws IllegalArgumentException {
        if (Type.canInstantiate(obj))
        	return new Type(obj);
        else
        	throw new IllegalArgumentException("Type: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3218b720-9e39-4e4a-84aa-5cb2181e355b")
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
        Type other = (Type) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("2dbf7c96-786b-4e03-89b6-cfd726a98b2d")
    public Class getElement() {
        return this.elt;
    }

    @objid ("b4deb01c-d8bc-4909-b774-da636e5796c5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8e8044c1-bc5b-4f3a-aa7a-b3fe8d014483")
    protected Type(Class elt) {
        this.elt = elt;
    }

    @objid ("efed0986-aaef-411c-afca-a6659ff2ccf9")
    public static final class MdaTypes {
        @objid ("75d4bbdd-185b-43dc-b776-0026066881f8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("abe5a965-2519-4f5c-b260-07701e3ccfcd")
        private static Stereotype MDAASSOCDEP;

        @objid ("302dcf6b-c639-4862-baed-60308cbcf06c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8e87d7b8-bb3c-43e4-9f61-c60f1c9d5f45")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ba-0000-000000000000");
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
