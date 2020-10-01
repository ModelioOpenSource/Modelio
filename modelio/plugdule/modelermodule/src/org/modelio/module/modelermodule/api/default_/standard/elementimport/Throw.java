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
 * Proxy class to handle a {@link ElementImport} with << throw >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18da7ea1-47b8-4eef-93d1-8ff145826e37")
public class Throw {
    @objid ("c9bb80b0-d81f-418e-8b6d-3922bb449502")
    public static final String STEREOTYPE_NAME = "throw";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("63e2b227-a7dc-4330-bcd8-75e70a6b184e")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Throw proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << throw >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ef3a8146-4972-4ecf-91ec-c526c3e343c5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << throw >> then instantiate a {@link Throw} proxy.
     * 
     * @return a {@link Throw} proxy on the created {@link ElementImport}.
     */
    @objid ("2df10fbc-0c0d-4a48-9793-4814a644a03d")
    public static Throw create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
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
    @objid ("dc7da546-3ae1-4dde-a450-15d3104e4fb1")
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
    @objid ("e09a57b5-7bc6-45fd-8a0d-1cd0a781a431")
    public static Throw safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Throw.canInstantiate(obj))
        	return new Throw(obj);
        else
        	throw new IllegalArgumentException("Throw: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("84d23756-5ac6-4e1f-903e-1fa6094cd2d3")
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
    @objid ("a095a4cf-a9d9-459d-86de-636f66794439")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("00b561f0-cdb8-4742-b590-840d81c5fa89")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("51377ac5-9cce-4034-8d2e-e2824271306f")
    protected Throw(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("1f8f9f45-0963-4c94-b96b-4765073e15e0")
    public static final class MdaTypes {
        @objid ("de26df36-0577-487a-9ec4-9b92dd18c393")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9fa50254-eb9e-4199-91c2-33f0426cc4a8")
        private static Stereotype MDAASSOCDEP;

        @objid ("c69a2ef5-eebd-406d-9a64-73c868c09ad3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cc99bf49-e901-48b4-aeaf-8afea9a06a9b")
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
