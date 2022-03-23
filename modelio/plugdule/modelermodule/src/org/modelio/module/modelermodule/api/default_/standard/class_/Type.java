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
    @objid ("d84fa021-8e3d-4067-8c65-1c90853832bc")
    public static final String STEREOTYPE_NAME = "type";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("c64bd60f-4915-4f07-b477-8a94e5305ce9")
    protected final Class elt;

    /**
     * Tells whether a {@link Type proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << type >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("29652067-926a-4631-8e36-cadb5ef960d4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << type >> then instantiate a {@link Type} proxy.
     * 
     * @return a {@link Type} proxy on the created {@link Class}.
     */
    @objid ("9b1f4a1d-01c1-49cc-8fad-8638d310cb39")
    public static Type create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Class");
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
    @objid ("e22480f1-7865-45fe-a23f-b36cf0ee4962")
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
    @objid ("51b3ba11-35d8-47a3-b71a-be03d0486a85")
    public static Type safeInstantiate(Class obj) throws IllegalArgumentException {
        if (Type.canInstantiate(obj))
        	return new Type(obj);
        else
        	throw new IllegalArgumentException("Type: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b0408c66-e0ce-4f87-aa28-3a39db247da6")
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
    @objid ("ba4dc2e7-d10c-43d3-b8b8-699ccd8411a6")
    public Class getElement() {
        return this.elt;
    }

    @objid ("3bb86785-d61f-4c71-bff7-f81b9a3f04f0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c9e1cdc3-a775-4574-8b6e-8447ae548479")
    protected  Type(Class elt) {
        this.elt = elt;
    }

    @objid ("efed0986-aaef-411c-afca-a6659ff2ccf9")
    public static final class MdaTypes {
        @objid ("d782275d-2f54-4875-98ed-40fdef45c5d7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5c2487c3-72ad-4ae6-87a1-02ef5cde66be")
        private static Stereotype MDAASSOCDEP;

        @objid ("08db89f9-5dea-4b6f-986e-ad9d974a0532")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7b585cc1-89ce-44db-ab1c-deaae817f8d5")
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
