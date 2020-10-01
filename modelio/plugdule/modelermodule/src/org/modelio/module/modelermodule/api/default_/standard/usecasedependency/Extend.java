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
package org.modelio.module.modelermodule.api.default_.standard.usecasedependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
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
 * Proxy class to handle a {@link UseCaseDependency} with << extend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5babb04d-9057-43e4-9e92-b0706d209ff8")
public class Extend {
    @objid ("b5d66019-cafb-4ca2-9bda-0d59d6115e92")
    public static final String STEREOTYPE_NAME = "extend";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("b250f0bb-fa2f-4718-8666-2097196d390b")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Extend proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << extend >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a6da6e67-66f8-4ded-a783-6215bef021fb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << extend >> then instantiate a {@link Extend} proxy.
     * 
     * @return a {@link Extend} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("b9f9cac5-1aec-4eeb-81f5-24fafb01d072")
    public static Extend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME);
        return Extend.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Extend} proxy or <i>null</i>.
     */
    @objid ("2aad0ff9-e911-4e45-9109-1c07a339ab51")
    public static Extend instantiate(UseCaseDependency obj) {
        return Extend.canInstantiate(obj) ? new Extend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Extend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3606cd54-ab95-4128-8237-dfe061671001")
    public static Extend safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Extend.canInstantiate(obj))
        	return new Extend(obj);
        else
        	throw new IllegalArgumentException("Extend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("abdc7981-25e6-4d0b-bdfe-4b3d396e2495")
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
        Extend other = (Extend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("9f8eb0cd-e56d-48bf-a060-f3c7364b13de")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("c2e193fd-bf23-45a1-96a1-dd66d7ecaf5d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("08757918-ee03-4435-ba9f-93829188f441")
    protected Extend(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("c8564055-bb69-4426-9956-d7b941a73e96")
    public static final class MdaTypes {
        @objid ("7486cb85-5ae4-4e2d-b5d8-9c14c6165c46")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("75ac38f2-33db-4022-be8e-321d514662b0")
        private static Stereotype MDAASSOCDEP;

        @objid ("ee58f8bf-02d1-468e-9add-997c1c54aaea")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("437c0b85-219c-4861-b82b-404744bd1b6a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c48-0000-000000000000");
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
