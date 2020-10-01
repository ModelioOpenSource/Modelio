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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << secondary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5214e11f-81b6-4d62-9a5c-3032b96258c1")
public class Secondary {
    @objid ("6179ce47-9183-478b-96dd-68174da3e06a")
    public static final String STEREOTYPE_NAME = "secondary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("252c0006-7ed5-40b0-9333-95dd67bf5d54")
    protected final Actor elt;

    /**
     * Tells whether a {@link Secondary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << secondary >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("aa4e4883-f346-48ef-859b-e830c8f0841c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << secondary >> then instantiate a {@link Secondary} proxy.
     * 
     * @return a {@link Secondary} proxy on the created {@link Actor}.
     */
    @objid ("e826e417-4ecf-4154-9c3f-3355c7e0a849")
    public static Secondary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME);
        return Secondary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Secondary} proxy or <i>null</i>.
     */
    @objid ("41425c67-02c9-4788-969e-9f64e4ec703b")
    public static Secondary instantiate(Actor obj) {
        return Secondary.canInstantiate(obj) ? new Secondary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Secondary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6ae236d9-c343-4816-8f9d-d5894c3d42a4")
    public static Secondary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Secondary.canInstantiate(obj))
        	return new Secondary(obj);
        else
        	throw new IllegalArgumentException("Secondary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7878c36b-6a1e-470d-b48d-6fdc1fc73809")
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
        Secondary other = (Secondary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("4c584544-58e3-425e-a84e-b03059d5ce6c")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("827a1111-bff5-4cfa-8dca-2f29d4ca8ce4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("971de870-5467-4890-b2b9-b0aa02b7ac1d")
    protected Secondary(Actor elt) {
        this.elt = elt;
    }

    @objid ("8e474651-cd0e-4098-985c-82cba46299e9")
    public static final class MdaTypes {
        @objid ("8ece1753-6fcd-4d78-964a-27a771047ac8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6e50af9c-a5ea-4dc1-897e-7cfbf1621c93")
        private static Stereotype MDAASSOCDEP;

        @objid ("ea56c976-f9f5-4f35-92e0-bd4c9fac730a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8f34880f-b4b0-4033-9dc4-fc932e0cda98")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2f04-0000-000000000000");
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
