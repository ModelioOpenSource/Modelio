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
 * Proxy class to handle a {@link Dependency} with << guarantee >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59095dca-f0dc-4be2-a58b-5035f5929642")
public class Guarantee {
    @objid ("8700b5ce-1902-48af-b2d1-5d4d69f8f100")
    public static final String STEREOTYPE_NAME = "guarantee";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("19dbaad1-cb5a-4fc9-aa2f-01edc102b6af")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Guarantee proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << guarantee >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cc251ace-40ad-4085-8a14-5de8af7bd47d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << guarantee >> then instantiate a {@link Guarantee} proxy.
     * 
     * @return a {@link Guarantee} proxy on the created {@link Dependency}.
     */
    @objid ("18fb1d24-58ed-49c8-a4b5-e2fef69911b2")
    public static Guarantee create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME);
        return Guarantee.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Guarantee} proxy or <i>null</i>.
     */
    @objid ("ebaa59af-65ee-4278-b62b-03ff9ebb6464")
    public static Guarantee instantiate(Dependency obj) {
        return Guarantee.canInstantiate(obj) ? new Guarantee(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Guarantee} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f9ee2c24-d054-4e62-8359-6258a47f5bf5")
    public static Guarantee safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Guarantee.canInstantiate(obj))
        	return new Guarantee(obj);
        else
        	throw new IllegalArgumentException("Guarantee: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ab70277f-af5a-4767-94f1-114886a8e60c")
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
        Guarantee other = (Guarantee) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("905ebe36-d5b0-4794-aa26-2963c0918b50")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("28c18940-ff07-4e67-bce6-c984546e3fe9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2c126584-6b37-4a2c-a4e2-6076ac05e1fc")
    protected Guarantee(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5bd302d-6ad5-4ad1-bfa4-bca43f79b810")
    public static final class MdaTypes {
        @objid ("524a9d0f-c37d-4d82-9439-fcfbbee44e01")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("be3e913f-4efe-4434-adf2-3fb8a7e4bc74")
        private static Stereotype MDAASSOCDEP;

        @objid ("7c14910c-2391-4ad4-a6fc-93f8dc8b77e0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("63a1249d-e50f-4f74-a825-ce9534d03836")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0251-0000-000000000000");
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
