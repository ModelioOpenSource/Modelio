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
 * Proxy class to handle a {@link ElementImport} with << access >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b374025a-7e25-4fab-adc2-20468c9497a7")
public class Access {
    @objid ("504c66ae-f037-4cdf-9c4d-69944aeff8cf")
    public static final String STEREOTYPE_NAME = "access";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("0ad650ee-c661-4a19-bb89-753dd9ada90c")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Access proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << access >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4e18dc28-92d9-4dbf-ae4b-0a07015856e3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << access >> then instantiate a {@link Access} proxy.
     * 
     * @return a {@link Access} proxy on the created {@link ElementImport}.
     */
    @objid ("1bb78da8-5af4-4549-a810-8ced8069b5bd")
    public static Access create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME);
        return Access.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Access} proxy or <i>null</i>.
     */
    @objid ("d7436d73-232d-4058-9c07-67d3975ee3a8")
    public static Access instantiate(ElementImport obj) {
        return Access.canInstantiate(obj) ? new Access(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Access} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6411d253-e36f-47cf-97f9-97c9cb2a96ba")
    public static Access safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Access.canInstantiate(obj))
        	return new Access(obj);
        else
        	throw new IllegalArgumentException("Access: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("522feb73-8972-4fb1-9a1c-a940ed88e06e")
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
        Access other = (Access) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("043b88a6-353f-4eb6-a239-16b57bc79f01")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("7b362e47-812b-458b-b678-5bd41ee98722")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("5552fa6d-2239-4d5e-bcd8-83b36dced994")
    protected  Access(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("f1f67b2c-6db9-48c7-8cfe-811ff7873676")
    public static final class MdaTypes {
        @objid ("471e9851-7f9e-43da-9384-a7923d3d45e9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a1445656-1981-4f02-856f-17af986b9f76")
        private static Stereotype MDAASSOCDEP;

        @objid ("7dddf2a2-d0bb-4fd3-b87c-eaaaf1dfff14")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("144c2a52-fca6-4c62-934e-a56b295f9ab9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01cc-0000-000000000000");
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
