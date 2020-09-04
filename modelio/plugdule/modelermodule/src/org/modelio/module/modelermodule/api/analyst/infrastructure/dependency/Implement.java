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
    @objid ("9602ec82-b20d-4367-8f73-6ebe24a2bf09")
    public static final String STEREOTYPE_NAME = "implement";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e3622a86-a921-4996-8ec0-aea6dc38b275")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Implement proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << implement >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("055a03c9-bafd-46b3-87a4-005e81d16600")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << implement >> then instantiate a {@link Implement} proxy.
     * 
     * @return a {@link Implement} proxy on the created {@link Dependency}.
     */
    @objid ("a92f580f-951d-44ea-b249-6bb7c72aa807")
    public static Implement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME);
        return Implement.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Implement} proxy or <i>null</i>.
     */
    @objid ("8e299313-08c2-4d03-894b-f8636756fdd5")
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
    @objid ("47e1dfa8-e71b-4606-ac53-63673bf2165a")
    public static Implement safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Implement.canInstantiate(obj))
        	return new Implement(obj);
        else
        	throw new IllegalArgumentException("Implement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("79e1ad97-9ab5-4895-a845-236cca9b1cca")
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
    @objid ("3b7d20c3-b013-48a4-98ca-a4f306ce13d6")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("92ca7bbf-9640-46ec-a6b9-c6ccdfafc1f6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6c4e7037-d748-42db-9b44-68f43e1649b2")
    protected Implement(Dependency elt) {
        this.elt = elt;
    }

    @objid ("062103b4-cf74-41a4-8db5-49367f2eaa96")
    public static final class MdaTypes {
        @objid ("17b27c2d-5d67-4165-86bd-65fe3a7df719")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0d848f8f-07aa-48d1-9943-a7924458086f")
        private static Stereotype MDAASSOCDEP;

        @objid ("0ffbc2d2-9461-4a0c-adc9-bf53f1d2fc8e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6fce0b84-4709-433f-a472-15cc23e82b20")
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
