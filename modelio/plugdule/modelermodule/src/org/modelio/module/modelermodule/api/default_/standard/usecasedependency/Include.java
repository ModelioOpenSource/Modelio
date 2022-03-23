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
package org.modelio.module.modelermodule.api.default_.standard.usecasedependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
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
 * Proxy class to handle a {@link UseCaseDependency} with << include >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("09cedb92-5f5a-40aa-a318-488613b0c1ef")
public class Include {
    @objid ("5d1ac765-9c6f-4854-ac51-dfca542b602d")
    public static final String STEREOTYPE_NAME = "include";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("85f9b8e7-fcbb-4d18-8ee7-456aabe2f46d")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Include proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << include >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("530bdc18-3959-4faf-bb32-e5d35917cfc7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << include >> then instantiate a {@link Include} proxy.
     * 
     * @return a {@link Include} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("d5eea2e4-daab-474d-957c-e546f57b0534")
    public static Include create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME);
        return Include.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Include} proxy or <i>null</i>.
     */
    @objid ("a96778b5-2af3-4e72-a4f8-c0c5c724ab2b")
    public static Include instantiate(UseCaseDependency obj) {
        return Include.canInstantiate(obj) ? new Include(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Include} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8d32e3fc-5054-46ae-86f7-ac670e826f04")
    public static Include safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Include.canInstantiate(obj))
        	return new Include(obj);
        else
        	throw new IllegalArgumentException("Include: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("921c4af7-2d61-43f8-82ba-65d4b19ffe2d")
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
        Include other = (Include) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("f552ecf8-e6e4-43ec-81b7-1b7e189f410c")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("40f90c1e-8068-4a5a-ad06-4671fe4374d5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("45ce1648-7761-4c61-8ece-629b914a2bfa")
    protected  Include(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("9d68d730-b7bb-41e7-a89a-ad9aad3c697c")
    public static final class MdaTypes {
        @objid ("3fa00e52-c47c-4eae-97c4-f8573e30d82a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("62209487-bf5d-42d9-ba4e-0d5e8c10361e")
        private static Stereotype MDAASSOCDEP;

        @objid ("e01b83b0-e7bf-42c6-b7d8-873819f9b4cb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7e7ff2a7-b937-49fa-8173-04bf48bfa474")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c49-0000-000000000000");
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
