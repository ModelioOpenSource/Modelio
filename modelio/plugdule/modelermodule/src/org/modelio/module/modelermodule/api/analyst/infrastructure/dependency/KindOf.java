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
 * Proxy class to handle a {@link Dependency} with << kind-of >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("48e6ef94-b235-4f1d-8b9b-a5b2b6b40e53")
public class KindOf {
    @objid ("bcb54ddd-cfdd-4d9b-af2b-cf0a64d326fc")
    public static final String STEREOTYPE_NAME = "kind-of";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f5935213-d7d3-401b-998b-ff9231ab17cb")
    protected final Dependency elt;

    /**
     * Tells whether a {@link KindOf proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << kind-of >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("52e22833-92a2-4f3e-be05-c3a26e09d62a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << kind-of >> then instantiate a {@link KindOf} proxy.
     * 
     * @return a {@link KindOf} proxy on the created {@link Dependency}.
     */
    @objid ("b640268e-54f1-45f9-a208-6b8f8ce3877c")
    public static KindOf create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME);
        return KindOf.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link KindOf} proxy or <i>null</i>.
     */
    @objid ("220fe8bc-f8e9-4e89-81a1-8fbc3a478fb9")
    public static KindOf instantiate(Dependency obj) {
        return KindOf.canInstantiate(obj) ? new KindOf(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link KindOf} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b575242b-a026-4f5d-b0c1-e1174d162ea9")
    public static KindOf safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (KindOf.canInstantiate(obj))
        	return new KindOf(obj);
        else
        	throw new IllegalArgumentException("KindOf: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a1646bab-bfda-434e-a3d1-33ff44656f1e")
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
        KindOf other = (KindOf) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("d45b5b82-0c78-40af-96d1-ca3e7ab85d69")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b86bf40a-e696-4e8e-b234-5d4cbf32c41f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("36939b75-d9bf-4e2a-a973-1b2f3158e603")
    protected KindOf(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5d095a89-ce1f-419e-b944-0225c5b7e954")
    public static final class MdaTypes {
        @objid ("5bab5580-bdca-4e57-9eea-b11e84a7fc97")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c9ba8f7e-dfe7-4d54-8b83-845850860310")
        private static Stereotype MDAASSOCDEP;

        @objid ("099e9d1d-01bb-4bf4-9029-343c6be3f454")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("26b2bca5-2b06-46cb-8c7b-521306593b45")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-12f8-0000-000000000000");
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
