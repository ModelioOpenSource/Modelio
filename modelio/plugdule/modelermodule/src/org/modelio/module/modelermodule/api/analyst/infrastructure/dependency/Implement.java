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
 * Proxy class to handle a {@link Dependency} with << implement >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("bd3859e6-6a4f-4b1b-82d2-b97c4db33bd4")
public class Implement {
    @objid ("0bd26221-fc1b-4b67-bc5b-c88fd791f6db")
    public static final String STEREOTYPE_NAME = "implement";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c6ab6223-fe83-454e-b3a5-1a3e6160810b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Implement proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << implement >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("962d0e17-9521-41a1-80a8-5dd9834c5ef1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << implement >> then instantiate a {@link Implement} proxy.
     * 
     * @return a {@link Implement} proxy on the created {@link Dependency}.
     */
    @objid ("e7889d0b-932c-470b-893b-d1a8047439f6")
    public static Implement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME);
        return Implement.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Implement} proxy or <i>null</i>.
     */
    @objid ("d2321062-4094-4a12-8bc2-5b1424f7ca66")
    public static Implement instantiate(Dependency obj) {
        return Implement.canInstantiate(obj) ? new Implement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Implement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7c4569b8-3186-4c87-ac7e-0c9c4f81dfc3")
    public static Implement safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Implement.canInstantiate(obj))
        	return new Implement(obj);
        else
        	throw new IllegalArgumentException("Implement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("db004df0-aef4-4320-a13c-6a54425811a7")
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
        Implement other = (Implement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("be294e59-4166-47ad-af7f-c5a937cd5028")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("1abd283e-5c17-41fa-b20f-712dfbfb4338")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("94d26741-6bfd-42a0-834e-7751224fce02")
    protected  Implement(Dependency elt) {
        this.elt = elt;
    }

    @objid ("062103b4-cf74-41a4-8db5-49367f2eaa96")
    public static final class MdaTypes {
        @objid ("cf5441bb-2bd7-4258-8eda-ba93a5630a8c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("21192546-08e6-416d-8666-1c6c96b7e214")
        private static Stereotype MDAASSOCDEP;

        @objid ("20e7c6df-f1b3-4e13-883d-fa492c30e913")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("57e6bd74-a33d-4c18-a316-0204723bbfce")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0260-0000-000000000000");
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
