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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("6f2c12f4-14d9-4043-8eab-599625e213eb")
    public static final String STEREOTYPE_NAME = "catch";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("77e97a4d-d5b7-4d1a-96b1-04a80e6a765d")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Catch proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << catch >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("252c22ac-ed7e-477e-b0d5-fb2de0f388e9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << catch >> then instantiate a {@link Catch} proxy.
     * 
     * @return a {@link Catch} proxy on the created {@link ElementImport}.
     */
    @objid ("15a6423a-fffe-4eea-aaeb-db7d965b24a6")
    public static Catch create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
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
    @objid ("d79a5fe5-7ef4-40b1-8dbd-09ed3921edb8")
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
    @objid ("a9ba01a2-915b-4368-ad34-84c855f8ed40")
    public static Catch safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Catch.canInstantiate(obj))
        	return new Catch(obj);
        else
        	throw new IllegalArgumentException("Catch: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5b36ccb4-6650-4c6e-b4ed-7b3af88da706")
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
    @objid ("7c784693-6a05-41b6-bc86-2d8ea6c9148b")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("2f15a48a-241b-4284-b2a4-e6d58d83184b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("02c2ed14-7bb2-4a96-95b6-a2c6a5328218")
    protected  Catch(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("fa9f3ff0-e1c4-4c57-be5e-a56fb2fb5e4f")
    public static final class MdaTypes {
        @objid ("379014b7-fc4a-40c7-a0d4-9cca26ac3a01")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2b7acf15-517e-4cf8-af2a-af8ddc1b8df1")
        private static Stereotype MDAASSOCDEP;

        @objid ("ba63b534-2a3a-4809-a889-b6f9dcc586dc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("99f875d3-77c8-485e-82ed-8d4a1137150e")
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
