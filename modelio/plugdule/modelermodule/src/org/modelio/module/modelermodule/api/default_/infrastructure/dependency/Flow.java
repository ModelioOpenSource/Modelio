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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << flow >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("059d7606-63e7-4b15-b47f-42f41353ce1e")
public class Flow {
    @objid ("b1993e5f-9ba4-4338-ba5d-a3d1fa7ecc70")
    public static final String STEREOTYPE_NAME = "flow";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("47402223-b895-43b5-a948-7d185f5f53b8")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Flow proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << flow >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2f06eace-61c1-466a-bdc6-552bbf0f7519")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << flow >> then instantiate a {@link Flow} proxy.
     * 
     * @return a {@link Flow} proxy on the created {@link Dependency}.
     */
    @objid ("088ca63b-d378-43c7-a6ff-4a20f2588117")
    public static Flow create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME);
        return Flow.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Flow} proxy or <i>null</i>.
     */
    @objid ("ec2092a7-de3d-41ee-b46c-bf9bae8813cb")
    public static Flow instantiate(Dependency obj) {
        return Flow.canInstantiate(obj) ? new Flow(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Flow} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("53c9b625-c15f-493f-816a-4002cb6617b2")
    public static Flow safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Flow.canInstantiate(obj))
        	return new Flow(obj);
        else
        	throw new IllegalArgumentException("Flow: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cef8d57f-5136-49f5-ac38-d6465026ffe0")
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
        Flow other = (Flow) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("08f1baa3-898a-4162-b686-3f3ac6674c69")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("13012092-1b2e-466b-90dd-e300e9738b23")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("476aaef4-b64b-4269-9811-4780c548653a")
    protected Flow(Dependency elt) {
        this.elt = elt;
    }

    @objid ("82b9b25a-72f2-40a9-9d30-45b5eb452746")
    public static final class MdaTypes {
        @objid ("0a864ddd-fec5-4a53-8e53-bb7c56fd57bc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0772f7b6-0243-458d-a893-27ebac9e6c79")
        private static Stereotype MDAASSOCDEP;

        @objid ("7650143e-8156-41a8-bf44-be2b3b324c94")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c3096d7e-577c-4acc-b06a-eb47aac3087c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-0964-0000-000000000000");
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
