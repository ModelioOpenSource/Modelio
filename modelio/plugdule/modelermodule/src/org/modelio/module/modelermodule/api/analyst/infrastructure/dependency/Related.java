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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << related >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5ac2e06b-dda4-459c-acdb-e71b88f113b8")
public class Related {
    @objid ("a948c719-e628-4ec6-907c-8ff4b989ad48")
    public static final String STEREOTYPE_NAME = "related";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f58dc3e6-b527-4c98-af68-268c4d4a91ef")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Related proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d4fd54f2-9753-4880-b6cf-f663e8e93def")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related >> then instantiate a {@link Related} proxy.
     * 
     * @return a {@link Related} proxy on the created {@link Dependency}.
     */
    @objid ("141b511a-05c9-495a-bc1b-582ce4f2d5e0")
    public static Related create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME);
        return Related.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Related} proxy or <i>null</i>.
     */
    @objid ("092999b9-8a76-49f9-8488-553d997d6d97")
    public static Related instantiate(Dependency obj) {
        return Related.canInstantiate(obj) ? new Related(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Related} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("74183a04-fcbf-4377-a81c-54037729f1f5")
    public static Related safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Related.canInstantiate(obj))
        	return new Related(obj);
        else
        	throw new IllegalArgumentException("Related: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a9bdd74d-ecd9-4b07-84d3-020d3c054d31")
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
        Related other = (Related) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ee9bd088-6078-40e8-a6bc-50baed6b1ec0")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e6780728-1f15-42ea-9eb3-8a66d22166c0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("20a13376-9b91-4e0b-997d-d026e126d2b5")
    protected  Related(Dependency elt) {
        this.elt = elt;
    }

    @objid ("20b74997-f3b4-4b98-8673-17a6d2577e56")
    public static final class MdaTypes {
        @objid ("806f9e7e-b875-4aaf-99aa-7a97346b3317")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3aad3059-e163-42d0-9501-1e3ad372a177")
        private static Stereotype MDAASSOCDEP;

        @objid ("bcdf6b16-8bc6-4d21-88ba-2e927054966b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4389e76f-7d17-4225-8165-f293a36628d2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-023d-0000-000000000000");
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
