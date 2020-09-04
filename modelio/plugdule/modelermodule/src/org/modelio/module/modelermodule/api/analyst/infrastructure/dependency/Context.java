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
 * Proxy class to handle a {@link Dependency} with << context >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9adbf168-2b19-4e82-afe0-a6337867d437")
public class Context {
    @objid ("f140fa3c-1925-4ed7-8d3e-5daf016f65da")
    public static final String STEREOTYPE_NAME = "context";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e5c73a1f-57a0-48ae-824a-007756570dad")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Context proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << context >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c263cb9d-4541-4f6b-8260-ea0a643cd8b6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << context >> then instantiate a {@link Context} proxy.
     * 
     * @return a {@link Context} proxy on the created {@link Dependency}.
     */
    @objid ("356539ec-b08f-42bf-bda4-f7f053ff19f2")
    public static Context create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME);
        return Context.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Context} proxy or <i>null</i>.
     */
    @objid ("af170644-caf5-44b3-bf67-71f268f45964")
    public static Context instantiate(Dependency obj) {
        return Context.canInstantiate(obj) ? new Context(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Context} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2671f082-f399-4cf0-8224-f2ac34bf1ff5")
    public static Context safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Context.canInstantiate(obj))
        	return new Context(obj);
        else
        	throw new IllegalArgumentException("Context: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("12370be6-0876-4e84-84da-1ca92eb88421")
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
        Context other = (Context) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("41882d2d-52f1-4878-8d14-4578087a026c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b342d57a-474d-4176-b15d-f2346a4da58f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("66629b7a-8eb3-445d-882d-16c30e85cc6c")
    protected Context(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b5192799-2b87-4173-932c-3e67df3991ab")
    public static final class MdaTypes {
        @objid ("ad96f7ef-768a-4c01-8c3b-7c4aee0b9e1c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8a51e571-a402-4d7f-9ab1-9c218a56d47c")
        private static Stereotype MDAASSOCDEP;

        @objid ("3daafe9b-5e15-408d-bbb6-79d63a0342bc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("68f9956f-b0f1-4500-afcb-67b1acdfffd0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0242-0000-000000000000");
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
