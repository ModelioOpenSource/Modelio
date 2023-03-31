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
    @objid ("e2f05b2a-a689-485e-bd8b-ec4aabe00d95")
    public static final String STEREOTYPE_NAME = "see_also";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("969ad058-acfa-4652-b3b4-51186109bf21")
    protected final Dependency elt;

    /**
     * Tells whether a {@link SeeAlso proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << see_also >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e30b7203-0b17-4007-ab8b-25a18c6dae50")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << see_also >> then instantiate a {@link SeeAlso} proxy.
     * 
     * @return a {@link SeeAlso} proxy on the created {@link Dependency}.
     */
    @objid ("0d145454-5f47-43ca-9271-1f7bdc0e556b")
    public static SeeAlso create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME);
        return SeeAlso.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link SeeAlso} proxy or <i>null</i>.
     */
    @objid ("c2b89324-c598-46ec-b5f5-820dd5bd7fbd")
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
    @objid ("645575fc-b956-4c91-a13a-ff16a5699a99")
    public static SeeAlso safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (SeeAlso.canInstantiate(obj))
        	return new SeeAlso(obj);
        else
        	throw new IllegalArgumentException("SeeAlso: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c74584ea-dce3-4665-a8ff-4978598c1f43")
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
    @objid ("8647a9f3-b1cb-44a3-915d-ad1fd462fe2d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8a950df7-a5c0-48a0-99e4-e0164282c421")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("22e42335-a460-4290-9594-e83052a59259")
    protected  SeeAlso(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5b47eb06-542a-4878-a3f2-5e7de42d4e66")
    public static final class MdaTypes {
        @objid ("e2ff1938-c24c-4c0a-8f33-c861a866e224")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("64e278e5-072e-4039-a97a-d040a29a4ae4")
        private static Stereotype MDAASSOCDEP;

        @objid ("a66d10fc-0c98-4d8d-ba58-a88ca2231346")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("88310779-2814-440d-b5e6-f627e72d24df")
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
