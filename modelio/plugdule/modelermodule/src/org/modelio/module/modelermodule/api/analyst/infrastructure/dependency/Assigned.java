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
 * Proxy class to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f6b5b83-851b-4d23-b980-2de82874fa5b")
public class Assigned {
    @objid ("13570875-ab15-450c-b380-a8a5ac62a19e")
    public static final String STEREOTYPE_NAME = "assigned";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d1578b15-5159-4c0c-ad32-95cef0d8f5eb")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Assigned proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << assigned >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("cd2cbb43-fdfe-4a6e-8797-befc6d7aa1cf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << assigned >> then instantiate a {@link Assigned} proxy.
     * 
     * @return a {@link Assigned} proxy on the created {@link Dependency}.
     */
    @objid ("fa462e58-aa91-4a4b-84a8-5381b4e37392")
    public static Assigned create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME);
        return Assigned.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Assigned} proxy or <i>null</i>.
     */
    @objid ("22e8ae31-2b78-49ed-a13a-aaea0b41f5cf")
    public static Assigned instantiate(Dependency obj) {
        return Assigned.canInstantiate(obj) ? new Assigned(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Assigned} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ce76316d-6a85-4e9f-8c57-b0066d84b7ab")
    public static Assigned safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Assigned.canInstantiate(obj))
        	return new Assigned(obj);
        else
        	throw new IllegalArgumentException("Assigned: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8f190ca3-b828-477b-af43-7ce215e3751b")
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
        Assigned other = (Assigned) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("eb7f97e9-8787-4025-9600-965104a1c3bd")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8cef298d-038b-46ef-acd7-3f4fed547d35")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("752cf08f-9bb6-4218-b6a7-3b802687aef6")
    protected  Assigned(Dependency elt) {
        this.elt = elt;
    }

    @objid ("52e42aa6-442a-4a00-a707-08c1820fc9cd")
    public static final class MdaTypes {
        @objid ("eb546959-d44c-452d-9a53-6034753c0218")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9ff879ca-3ffa-4513-a54d-65f0eafbddc0")
        private static Stereotype MDAASSOCDEP;

        @objid ("d7a07a72-1946-418a-a013-89ac005a476d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0254bd52-74e2-4e3f-951c-ca65cda71fd0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-025b-0000-000000000000");
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
