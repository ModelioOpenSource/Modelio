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
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << catch >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("28e7a074-1463-4ea2-b62c-fcf073d01c3e")
public class Catch {
    @objid ("3bc58a38-a5d5-426c-8fb6-d96180f92f04")
    public static final String STEREOTYPE_NAME = "catch";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("3d696adf-1a89-4459-abf4-b8c17080c07d")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Catch proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << catch >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c1f67ec6-74d0-41aa-8069-f55b1820b308")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << catch >> then instantiate a {@link Catch} proxy.
     * 
     * @return a {@link Catch} proxy on the created {@link ElementImport}.
     */
    @objid ("3239b521-2988-4053-890a-3e7b78dda6de")
    public static Catch create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME);
        return Catch.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Catch} proxy from a {@link ElementImport} stereotyped << catch >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Catch} proxy or <i>null</i>.
     */
    @objid ("1370f4c8-4636-4091-9df6-55f9846c62a3")
    public static Catch instantiate(ElementImport obj) {
        return Catch.canInstantiate(obj) ? new Catch(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Catch} proxy from a {@link ElementImport} stereotyped << catch >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Catch} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d70abfe6-03e0-4687-9fba-81994fd964a5")
    public static Catch safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Catch.canInstantiate(obj))
        	return new Catch(obj);
        else
        	throw new IllegalArgumentException("Catch: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("44ab5621-fac6-4b49-aaa0-23546b49398d")
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
        Catch other = (Catch) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("8b9df761-806f-4821-a97d-b4b7f6d8d825")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("e6f9358c-82f4-414d-b808-839682b2cdd8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a7b7d31d-7750-471a-808d-11b23a697fd1")
    protected Catch(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("fa9f3ff0-e1c4-4c57-be5e-a56fb2fb5e4f")
    public static final class MdaTypes {
        @objid ("7b7cf536-1971-47c0-bed3-b09c3e40605b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("338a0f04-38a5-4757-a34f-28f5da8154a8")
        private static Stereotype MDAASSOCDEP;

        @objid ("3a88c11e-4ed0-40a7-8940-4fead6d909ac")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f6657a33-974f-49d9-b0fa-b971ec76c76e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0054070c-0000-005f-0000-000000000000");
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
