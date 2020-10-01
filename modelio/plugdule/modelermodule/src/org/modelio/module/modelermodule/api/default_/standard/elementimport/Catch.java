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
    @objid ("69107ebc-e1b4-4ae3-bbf3-9b3c4524cbe3")
    public static final String STEREOTYPE_NAME = "catch";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("f597aecb-413b-407e-8800-530a4a4e738c")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Catch proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << catch >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("97024a0f-73f7-482e-bc7a-96ac5d3bcb68")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << catch >> then instantiate a {@link Catch} proxy.
     * 
     * @return a {@link Catch} proxy on the created {@link ElementImport}.
     */
    @objid ("c7acc121-1b30-47b1-9e1c-42fc4ca10fcd")
    public static Catch create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME);
        return Catch.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Catch} proxy from a {@link ElementImport} stereotyped << catch >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Catch} proxy or <i>null</i>.
     */
    @objid ("697f54b7-7838-4793-ac74-b47d01820d61")
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
    @objid ("d483b44f-910d-47d7-b9d2-50472a0e4ad1")
    public static Catch safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Catch.canInstantiate(obj))
        	return new Catch(obj);
        else
        	throw new IllegalArgumentException("Catch: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("27e8d82b-f7b9-4886-868f-e3890c1c9846")
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
    @objid ("b79cd627-856d-4374-b3d1-5d329c68a536")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("ffd38e4b-75a9-4ef3-894d-5f9c5ef09d51")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1b80e7e5-2307-4d07-918e-b12232594fc3")
    protected Catch(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("fa9f3ff0-e1c4-4c57-be5e-a56fb2fb5e4f")
    public static final class MdaTypes {
        @objid ("acf313ce-a0ea-40d8-9bbd-13c85e5b9cd5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("adb89a80-f95d-4a45-9394-ac4b597c1f62")
        private static Stereotype MDAASSOCDEP;

        @objid ("e061aeb4-15e0-4898-9a09-cf7fff8a78ba")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ee42185-63fb-4725-8bfa-fa811cc8a8da")
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
