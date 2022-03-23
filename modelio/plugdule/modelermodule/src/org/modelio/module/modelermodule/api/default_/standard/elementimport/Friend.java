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
    @objid ("a17fdda2-d673-4975-8711-915ad74a3075")
    public static final String STEREOTYPE_NAME = "friend";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("163f2160-e21a-4607-b89f-c91d2e06bde1")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Friend proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << friend >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b84cebc9-ee93-4b17-a403-71b86f775d46")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << friend >> then instantiate a {@link Friend} proxy.
     * 
     * @return a {@link Friend} proxy on the created {@link ElementImport}.
     */
    @objid ("60ce3b2c-ae8d-4cb2-ada9-29f2a4d6c154")
    public static Friend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME);
        return Friend.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Friend} proxy or <i>null</i>.
     */
    @objid ("9c2adb67-5e76-4a6e-86c9-b6d45b6a8464")
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
    @objid ("1699da1f-6ef0-4d57-b4a1-06e305d91082")
    public static Friend safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Friend.canInstantiate(obj))
        	return new Friend(obj);
        else
        	throw new IllegalArgumentException("Friend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7ee9882f-065b-4789-9d0b-627b92dabc0f")
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
    @objid ("46778060-f48d-46e7-9a41-aaea7dd1f8dc")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("c9689dd1-4431-4547-9a73-da937dfe769d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("bacefbcc-5c0d-42a3-adca-df495ae8c486")
    protected  Friend(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("e4135690-595b-43d4-b1e7-11c556195a6c")
    public static final class MdaTypes {
        @objid ("a9ab2c5b-0bb7-4e28-aad7-60161c235931")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("010fbbbd-8481-4ee0-a7e5-01737b86d7e8")
        private static Stereotype MDAASSOCDEP;

        @objid ("a988fec0-4cd9-40be-b507-ad874f9c8caf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8f0edf40-1aeb-4f2c-b6a1-5ce63f4dc14e")
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
