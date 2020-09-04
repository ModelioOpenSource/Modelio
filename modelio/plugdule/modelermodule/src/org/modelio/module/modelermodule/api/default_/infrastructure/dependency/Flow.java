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
    @objid ("880de7a9-b35f-4656-8519-2a96d93fef65")
    public static final String STEREOTYPE_NAME = "flow";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c77f28a3-976f-4762-9a0c-c3db148d5cd6")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Flow proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << flow >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0d42ce7e-ca2c-4ced-abca-80c5df7a02aa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << flow >> then instantiate a {@link Flow} proxy.
     * 
     * @return a {@link Flow} proxy on the created {@link Dependency}.
     */
    @objid ("7193a2d5-de5f-426c-9ad2-f1585e25f542")
    public static Flow create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME);
        return Flow.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Flow} proxy or <i>null</i>.
     */
    @objid ("f02122df-a790-4bd5-9d21-39a613ca198d")
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
    @objid ("8af03154-bacf-4ffe-9d4e-e9ea960ae9f8")
    public static Flow safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Flow.canInstantiate(obj))
        	return new Flow(obj);
        else
        	throw new IllegalArgumentException("Flow: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c2612d3d-f319-497e-b289-f79dd0c066c1")
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
    @objid ("136f953a-ed14-4887-97c7-3c4279426435")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("98e899bf-227c-45b2-84c1-1aa17f487fe9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d0b0f245-f1de-4ad7-b17b-35b2b9e3ae04")
    protected Flow(Dependency elt) {
        this.elt = elt;
    }

    @objid ("82b9b25a-72f2-40a9-9d30-45b5eb452746")
    public static final class MdaTypes {
        @objid ("43c515dd-7476-4ec3-9768-5d31bfe30e99")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("515fd7c2-746a-488f-82ef-cbb647916a67")
        private static Stereotype MDAASSOCDEP;

        @objid ("b30af16e-2ffe-475d-b27d-997d09919bd0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7307067a-2b80-4ace-b270-b46d7141c18b")
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
