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
 * Proxy class to handle a {@link Dependency} with << satisfy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de664031-5286-4b77-8e34-8ad41e46aa5d")
public class Satisfy {
    @objid ("5e9230d2-f6b5-4326-b81c-f99ad7d48a3c")
    public static final String STEREOTYPE_NAME = "satisfy";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e67e366b-cb58-403d-9316-42b6a60da02d")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Satisfy proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << satisfy >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b97cf242-a247-4f76-947b-c3044631310c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << satisfy >> then instantiate a {@link Satisfy} proxy.
     * 
     * @return a {@link Satisfy} proxy on the created {@link Dependency}.
     */
    @objid ("c119714a-0ab5-4aff-bd90-8ec6464f7194")
    public static Satisfy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME);
        return Satisfy.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Satisfy} proxy or <i>null</i>.
     */
    @objid ("23fda7dc-a23a-49d0-b247-37ac24d9cccc")
    public static Satisfy instantiate(Dependency obj) {
        return Satisfy.canInstantiate(obj) ? new Satisfy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Satisfy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ef5647c4-9424-4759-85dd-fb28ad51b3fc")
    public static Satisfy safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Satisfy.canInstantiate(obj))
        	return new Satisfy(obj);
        else
        	throw new IllegalArgumentException("Satisfy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bfab6dcf-9896-4468-96f2-cbdb177491c3")
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
        Satisfy other = (Satisfy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("1ac4ceba-d339-47b5-b774-d0a2d4b8c631")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("bcf63342-8f5c-4e98-a68a-5ebed89d9382")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("edd6204a-7b06-4976-8272-e2111e618a32")
    protected Satisfy(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b06d31b4-20d4-42e2-b341-3bd59450e084")
    public static final class MdaTypes {
        @objid ("3db1f34b-9fd6-48e5-81db-3db7b18bedef")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9b06e299-f146-423b-b554-525e48d58845")
        private static Stereotype MDAASSOCDEP;

        @objid ("f8b78789-214e-48c4-ab9c-51dac16a0098")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3659bcde-185b-4cc0-8dd1-e4894b5d748b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0224-0000-000000000000");
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
