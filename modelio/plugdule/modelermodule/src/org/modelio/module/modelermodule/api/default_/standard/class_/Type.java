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
    @objid ("009111aa-e546-423d-bb4e-b335b7b0a354")
    public static final String STEREOTYPE_NAME = "type";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("d2f79043-54b0-445c-98e4-5ad1ecbf7279")
    protected final Class elt;

    /**
     * Tells whether a {@link Type proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << type >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6b5693c5-54cd-4e90-b8c6-fb09fc23b165")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << type >> then instantiate a {@link Type} proxy.
     * 
     * @return a {@link Type} proxy on the created {@link Class}.
     */
    @objid ("78e788c3-f7df-4dab-abc9-337adba23be2")
    public static Type create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME);
        return Type.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link Type} proxy from a {@link Class} stereotyped << type >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link Type} proxy or <i>null</i>.
     */
    @objid ("b4727a71-ca96-4b36-8c10-3c72168e0446")
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
    @objid ("d97de65a-a1e9-4c2d-9832-48945f678aec")
    public static Type safeInstantiate(Class obj) throws IllegalArgumentException {
        if (Type.canInstantiate(obj))
        	return new Type(obj);
        else
        	throw new IllegalArgumentException("Type: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("23345be7-dd3c-447b-ad11-6746dee930b3")
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
    @objid ("57fe5a55-3a08-4e80-a563-120c24231198")
    public Class getElement() {
        return this.elt;
    }

    @objid ("5eddcca8-1dbb-4d1b-944d-0f801a7e4855")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("24973267-5953-43a3-832e-a0954bd7b623")
    protected Type(Class elt) {
        this.elt = elt;
    }

    @objid ("efed0986-aaef-411c-afca-a6659ff2ccf9")
    public static final class MdaTypes {
        @objid ("4e870ac1-a636-43fb-9fa8-67420119740b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5feed6bf-13e8-4ae2-bece-1bdf0d38a4be")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6930bdc-e199-4584-961b-cd1cc5ea3e6b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("44bc029d-1db9-4524-a66f-8778bdcd4a55")
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
