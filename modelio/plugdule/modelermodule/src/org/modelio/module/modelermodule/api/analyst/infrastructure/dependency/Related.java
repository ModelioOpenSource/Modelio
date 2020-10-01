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
 * Proxy class to handle a {@link Dependency} with << related >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5ac2e06b-dda4-459c-acdb-e71b88f113b8")
public class Related {
    @objid ("73e80d7a-24eb-4caa-9292-c93ef83b395d")
    public static final String STEREOTYPE_NAME = "related";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c532d720-50dc-4706-8bd9-fef0bbe69a33")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Related proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ef1e3a40-06a7-4269-85c4-97fe58c643a4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related >> then instantiate a {@link Related} proxy.
     * 
     * @return a {@link Related} proxy on the created {@link Dependency}.
     */
    @objid ("86159d34-3b06-4d35-97c6-319a54792dd5")
    public static Related create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME);
        return Related.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Related} proxy or <i>null</i>.
     */
    @objid ("ea445bc9-1061-4f60-bbfb-bdb3e7b0c5f9")
    public static Related instantiate(Dependency obj) {
        return Related.canInstantiate(obj) ? new Related(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Related} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7f577881-4b7c-4660-b79b-976aba8fa46c")
    public static Related safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Related.canInstantiate(obj))
        	return new Related(obj);
        else
        	throw new IllegalArgumentException("Related: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa2e8451-0498-4cc4-8e01-1f8c90185d40")
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
        Related other = (Related) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("f6b74e8f-f5e1-440e-a6ad-ac0efb5b644d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("cc4efc8b-6ece-4193-94c9-941cbeb31733")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1757b197-5887-48f7-a522-69749f86c60e")
    protected Related(Dependency elt) {
        this.elt = elt;
    }

    @objid ("20b74997-f3b4-4b98-8673-17a6d2577e56")
    public static final class MdaTypes {
        @objid ("096a759d-4a06-44a0-b02f-c0995648d5ff")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b4487c41-0343-4b59-a586-7591a88b965c")
        private static Stereotype MDAASSOCDEP;

        @objid ("95be6de9-2856-406d-a1f3-eb5676b1af6d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("03b959c5-260f-459a-acdc-cd9df85a7a6a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-023d-0000-000000000000");
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
