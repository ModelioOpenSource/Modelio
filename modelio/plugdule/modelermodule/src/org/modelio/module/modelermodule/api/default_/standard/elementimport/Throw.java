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
 * Proxy class to handle a {@link ElementImport} with << throw >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18da7ea1-47b8-4eef-93d1-8ff145826e37")
public class Throw {
    @objid ("906d4a58-6e79-4129-bff0-03dfe381b55e")
    public static final String STEREOTYPE_NAME = "throw";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("75c8e889-f1d3-465c-b3bd-0baac230bbfd")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Throw proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << throw >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b184a722-5a61-4eb5-b50b-83fd6558be1a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << throw >> then instantiate a {@link Throw} proxy.
     * 
     * @return a {@link Throw} proxy on the created {@link ElementImport}.
     */
    @objid ("85f769a9-d1f7-48c5-b31b-1412544e0361")
    public static Throw create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME);
        return Throw.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Throw} proxy or <i>null</i>.
     */
    @objid ("c472c4d4-8386-46f0-b5ea-e287697ace36")
    public static Throw instantiate(ElementImport obj) {
        return Throw.canInstantiate(obj) ? new Throw(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Throw} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a3306353-5895-40d2-9248-e4e62bf46187")
    public static Throw safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Throw.canInstantiate(obj))
        	return new Throw(obj);
        else
        	throw new IllegalArgumentException("Throw: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("db7b94fd-247a-4709-8ada-b2b140856147")
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
        Throw other = (Throw) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("44940bb1-b585-4d9c-8e3d-7050fec18709")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("82b214a7-7fce-4958-9083-8c947e6ec0a5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("094b15fd-35a9-466e-8d5e-3edcdceb1499")
    protected  Throw(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("1f8f9f45-0963-4c94-b96b-4765073e15e0")
    public static final class MdaTypes {
        @objid ("f1ef490a-7265-4ac9-b33b-0f6efef36881")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7fb6fc19-c9c2-4512-b79c-b4f118ef68a5")
        private static Stereotype MDAASSOCDEP;

        @objid ("059931e1-e3b4-4f98-a710-dc802f71ad9c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("15403442-cb4f-4c13-9cc0-4a6dc810dd14")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0054070c-0000-005d-0000-000000000000");
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
