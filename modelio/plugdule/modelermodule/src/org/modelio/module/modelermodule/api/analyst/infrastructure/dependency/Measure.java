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
 * Proxy class to handle a {@link Dependency} with << measure >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a5520de-c3f4-49fe-82ab-dd4a9414a61c")
public class Measure {
    @objid ("79d692b9-0ec5-47d4-9cc9-b4875bb780c3")
    public static final String STEREOTYPE_NAME = "measure";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("60091fcd-532e-47eb-a59d-50921b4823ce")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Measure proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << measure >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cae98b56-6b63-4ade-9b0b-d262bbd01219")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << measure >> then instantiate a {@link Measure} proxy.
     * 
     * @return a {@link Measure} proxy on the created {@link Dependency}.
     */
    @objid ("0ddc2f63-0f7b-4755-8085-d3ec65f4f4ef")
    public static Measure create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME);
        return Measure.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Measure} proxy or <i>null</i>.
     */
    @objid ("8304d3cd-56c8-411d-b3de-85cfe18c9175")
    public static Measure instantiate(Dependency obj) {
        return Measure.canInstantiate(obj) ? new Measure(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Measure} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("aa2a2e9d-440c-4c4c-bca0-d59ab37fabed")
    public static Measure safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Measure.canInstantiate(obj))
        	return new Measure(obj);
        else
        	throw new IllegalArgumentException("Measure: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0350d55-64b9-4598-85fd-2dbe88b59628")
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
        Measure other = (Measure) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("9105af4a-b89d-4921-88c4-f6c3d8be3a9d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("cb7014c1-97b7-4294-a9d1-d401ad9015c1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("07599252-44b6-411e-8cd4-496084536671")
    protected Measure(Dependency elt) {
        this.elt = elt;
    }

    @objid ("46bb357b-ca38-48c0-b010-8be5578cd473")
    public static final class MdaTypes {
        @objid ("a17d983f-12d3-4fa6-87d4-d5d314cc05f3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2346dce4-159d-40e1-8e30-971e1e36df67")
        private static Stereotype MDAASSOCDEP;

        @objid ("77e73c4b-ed41-4f88-8be7-cf25d39e8fb8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee24ea59-8b07-4480-808c-b91553aa97ce")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0256-0000-000000000000");
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
