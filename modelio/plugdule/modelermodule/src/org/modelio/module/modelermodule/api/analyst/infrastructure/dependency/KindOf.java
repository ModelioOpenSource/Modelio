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
 * Proxy class to handle a {@link Dependency} with << kind-of >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("48e6ef94-b235-4f1d-8b9b-a5b2b6b40e53")
public class KindOf {
    @objid ("44821d06-4c06-4f77-86d7-73982990ecd8")
    public static final String STEREOTYPE_NAME = "kind-of";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("772b9ac6-208f-411b-8192-a0bcea356ad8")
    protected final Dependency elt;

    /**
     * Tells whether a {@link KindOf proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << kind-of >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bf43fa66-2e34-434a-926b-b4ac6171424b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << kind-of >> then instantiate a {@link KindOf} proxy.
     * 
     * @return a {@link KindOf} proxy on the created {@link Dependency}.
     */
    @objid ("b44ca65c-8844-4997-8006-5b28411d7708")
    public static KindOf create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME);
        return KindOf.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link KindOf} proxy or <i>null</i>.
     */
    @objid ("ef7a79ef-7bc2-4192-a9b4-d2d79cb7a063")
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
    @objid ("c8e5a408-cb3b-447c-ac1b-18dfe4ed43ca")
    public static KindOf safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (KindOf.canInstantiate(obj))
        	return new KindOf(obj);
        else
        	throw new IllegalArgumentException("KindOf: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8d0a2fe5-35c2-4564-a304-65c5bf1490c7")
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
    @objid ("71698035-25b7-47fd-9a0c-e25830048ab3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("dda5fd4d-26d9-44f3-ab66-0f42fbceebee")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9c8b6bf9-91a4-4b67-b912-55e3496bb02a")
    protected KindOf(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5d095a89-ce1f-419e-b944-0225c5b7e954")
    public static final class MdaTypes {
        @objid ("2e3a8b51-6e18-44ae-9bef-efc53db86ab1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3299c1a2-79de-4886-b747-45234e0496ab")
        private static Stereotype MDAASSOCDEP;

        @objid ("11eea252-a12d-442a-8c95-3bb211102a00")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("01daa28b-61e4-4405-b035-25af222fc014")
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
