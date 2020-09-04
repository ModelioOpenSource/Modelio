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
package org.modelio.module.modelermodule.api.default_.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << implementationClass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a8d3476-0e47-4713-8073-82df91f999f5")
public class ImplementationClass {
    @objid ("837029d3-ba04-4312-b5eb-6e484feb989f")
    public static final String STEREOTYPE_NAME = "implementationClass";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("e865391c-e519-41cb-88d1-b03cb5a5b813")
    protected final Class elt;

    /**
     * Tells whether a {@link ImplementationClass proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << implementationClass >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9f846371-1e75-457a-9328-abaed7a5a4ce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << implementationClass >> then instantiate a {@link ImplementationClass} proxy.
     * 
     * @return a {@link ImplementationClass} proxy on the created {@link Class}.
     */
    @objid ("036ec924-74b6-427b-8f39-0468488a57ce")
    public static ImplementationClass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME);
        return ImplementationClass.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link ImplementationClass} proxy or <i>null</i>.
     */
    @objid ("d7ff176c-4975-4e0b-b99d-70d0f00db8a0")
    public static ImplementationClass instantiate(Class obj) {
        return ImplementationClass.canInstantiate(obj) ? new ImplementationClass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link ImplementationClass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a278ef6e-8252-49fd-bbdc-3a41e76f633f")
    public static ImplementationClass safeInstantiate(Class obj) throws IllegalArgumentException {
        if (ImplementationClass.canInstantiate(obj))
        	return new ImplementationClass(obj);
        else
        	throw new IllegalArgumentException("ImplementationClass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("abc7eaaf-5022-4c89-a10b-834c8977d5df")
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
        ImplementationClass other = (ImplementationClass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("86d0b033-052b-4c85-9f4e-fa633ba323e9")
    public Class getElement() {
        return this.elt;
    }

    @objid ("bad09897-7e80-4284-bb0f-4d0684ddc3e6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e7af1dc0-5164-40bc-a8ac-451e2d8a3442")
    protected ImplementationClass(Class elt) {
        this.elt = elt;
    }

    @objid ("1b7811d3-b98b-4855-a8c5-b056228fbf8f")
    public static final class MdaTypes {
        @objid ("c489cab6-7b6e-4331-ad4e-cc95279969a5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c00cf295-452e-4336-ae4c-0ff3241353d9")
        private static Stereotype MDAASSOCDEP;

        @objid ("da6219aa-6729-4d85-87f9-a6847538bfdf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c102a134-2fb4-4eb4-a0c2-054c38790a50")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00bc0050-0000-006b-0000-000000000000");
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
