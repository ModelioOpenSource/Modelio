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
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << metaclass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0317e1ea-1cf1-4651-8c3e-910f2777fdd0")
public class Metaclass {
    @objid ("62a66fca-d87e-4c87-8e6e-1ea3172a6ad8")
    public static final String STEREOTYPE_NAME = "metaclass";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("07fc8326-29c8-4213-acb6-f0b7ce5d476b")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Metaclass proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << metaclass >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("209d0067-4424-47f1-ab23-556c07e9f799")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << metaclass >> then instantiate a {@link Metaclass} proxy.
     * 
     * @return a {@link Metaclass} proxy on the created {@link Classifier}.
     */
    @objid ("5dbcb30b-ce38-40f9-9699-828dc78e5143")
    public static Metaclass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME);
        return Metaclass.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Metaclass} proxy or <i>null</i>.
     */
    @objid ("3f401114-7c31-40ba-bf82-24475bf8de0b")
    public static Metaclass instantiate(Classifier obj) {
        return Metaclass.canInstantiate(obj) ? new Metaclass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Metaclass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2f67ac63-ad52-4904-93d6-c872c53048f4")
    public static Metaclass safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Metaclass.canInstantiate(obj))
        	return new Metaclass(obj);
        else
        	throw new IllegalArgumentException("Metaclass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5f1301d7-0324-46b0-8048-bc2514554e90")
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
        Metaclass other = (Metaclass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("f0ff3fd3-7093-4862-84ce-fa9bba1bbaa5")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("46dd1078-b927-4e21-be39-29ad3b89bdfe")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("99c8de99-e326-4a5d-8265-505d2afa2965")
    protected Metaclass(Classifier elt) {
        this.elt = elt;
    }

    @objid ("849c2bbc-c1c1-4711-a8fa-6b1c9f242228")
    public static final class MdaTypes {
        @objid ("f9f2412c-762c-466d-ac02-a409238387dc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3c6d30f0-6bab-4354-bbc8-9efc86a74839")
        private static Stereotype MDAASSOCDEP;

        @objid ("855b0ebf-cb73-424d-a16b-e7fcacf52f15")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ec3624ed-7a84-48bc-a4ed-c8cc37562fd5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bd-0000-000000000000");
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
