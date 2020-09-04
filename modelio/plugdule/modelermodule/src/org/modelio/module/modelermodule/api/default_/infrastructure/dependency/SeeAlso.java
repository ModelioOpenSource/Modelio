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
 * Proxy class to handle a {@link Dependency} with << see_also >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dab2a566-5d9e-4d14-a078-3daa13429dd7")
public class SeeAlso {
    @objid ("6222df6f-033d-48b5-8dc3-d8a2a52bce71")
    public static final String STEREOTYPE_NAME = "see_also";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5879a4bf-ec5b-4d25-98f9-9cf43cd226da")
    protected final Dependency elt;

    /**
     * Tells whether a {@link SeeAlso proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << see_also >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0881a6b5-c0b1-4fcd-a85a-6548b35517be")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << see_also >> then instantiate a {@link SeeAlso} proxy.
     * 
     * @return a {@link SeeAlso} proxy on the created {@link Dependency}.
     */
    @objid ("ee89959f-4530-46e4-bec1-52c78e90f742")
    public static SeeAlso create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME);
        return SeeAlso.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link SeeAlso} proxy or <i>null</i>.
     */
    @objid ("439fdd5d-81eb-41c1-b94c-f59636d330da")
    public static SeeAlso instantiate(Dependency obj) {
        return SeeAlso.canInstantiate(obj) ? new SeeAlso(obj) : null;
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link SeeAlso} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b05362b5-3344-4807-9af7-2d3ef7dc9f67")
    public static SeeAlso safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (SeeAlso.canInstantiate(obj))
        	return new SeeAlso(obj);
        else
        	throw new IllegalArgumentException("SeeAlso: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a1fc4cae-c2f8-4bb2-837c-4fb09cd68cab")
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
        SeeAlso other = (SeeAlso) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a07f56ae-5395-4972-8582-5acd9d9cdfb5")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e311a3db-b0c7-4426-91c8-92ed7d1e9fec")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8595e554-dabc-4e13-a742-5bfd4e4dc1a7")
    protected SeeAlso(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5b47eb06-542a-4878-a3f2-5e7de42d4e66")
    public static final class MdaTypes {
        @objid ("ccc54bf7-97b3-43a5-9b69-c24a61f786fa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("21089474-ede5-44fc-b228-b50eebb61b25")
        private static Stereotype MDAASSOCDEP;

        @objid ("9d820f35-62e5-4c84-8e54-6bd7ab463912")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c32e2ad6-4fa0-44b1-82c9-42c3eaa76529")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0ac7e50f-50c6-4eb6-9107-3d9df92a2b75");
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
