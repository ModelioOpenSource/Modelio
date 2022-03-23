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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << system >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5298c9af-64af-4240-bfa9-183dea9dc89a")
public class System {
    @objid ("ec8f8c27-b6e4-4006-8049-70649e4b9e86")
    public static final String STEREOTYPE_NAME = "system";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("ec78f556-5ccb-4dd3-be11-faf86621a03f")
    protected final Package elt;

    /**
     * Tells whether a {@link System proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << system >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0248ee1e-eb23-415c-b665-c1ed153a2aa2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << system >> then instantiate a {@link System} proxy.
     * 
     * @return a {@link System} proxy on the created {@link Package}.
     */
    @objid ("1902001b-19b3-4c33-816c-09b5e5de0004")
    public static System create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME);
        return System.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Package} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link System} proxy or <i>null</i>.
     */
    @objid ("5dd7348b-40c8-4c9c-8bda-c59e2e4bdc45")
    public static System instantiate(Package obj) {
        return System.canInstantiate(obj) ? new System(obj) : null;
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Package} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link System} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d94e19b7-81b4-4c55-b141-5a6dbe924486")
    public static System safeInstantiate(Package obj) throws IllegalArgumentException {
        if (System.canInstantiate(obj))
        	return new System(obj);
        else
        	throw new IllegalArgumentException("System: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b900cfa1-22df-4f3f-92f7-f0dd0e420fa7")
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
        System other = (System) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("b3c15e18-1237-4146-acb3-f5da76807c4b")
    public Package getElement() {
        return this.elt;
    }

    @objid ("e9669ad6-b8a1-4d78-8a3a-955b72d7a53d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("bd41ab5f-c3a0-40ee-bc89-e120cca1f545")
    protected  System(Package elt) {
        this.elt = elt;
    }

    @objid ("39a9edd8-5492-438f-b516-01a2b30620b0")
    public static final class MdaTypes {
        @objid ("6c5d7dcf-20ed-43ea-a288-077fd276a3a7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0c1ba55c-0e13-4955-9459-36e721d27783")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6f494a5-93de-4f60-86ef-2b78d4393f56")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2967778c-d568-4c60-81be-14a9495840fe")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "007006dc-0000-0137-0000-000000000000");
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
