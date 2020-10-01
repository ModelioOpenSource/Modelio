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
package org.modelio.module.modelermodule.api.default_.standard.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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
 * Proxy class to handle a {@link Signal} with << exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d36d02b-ca62-4e9d-ac9f-5616c922fdd2")
public class Exception {
    @objid ("8bb33fb3-88e5-4644-809f-93402f05e2ba")
    public static final String STEREOTYPE_NAME = "exception";

    /**
     * The underlying {@link Signal} represented by this proxy, never null.
     */
    @objid ("cd6d1407-97c8-42f5-8767-ffd0d8633ca0")
    protected final Signal elt;

    /**
     * Tells whether a {@link Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link Signal} stereotyped << exception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("116ebe16-9b13-4f82-973d-fad7fc2b48c3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Signal) && ((Signal) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Signal} stereotyped << exception >> then instantiate a {@link Exception} proxy.
     * 
     * @return a {@link Exception} proxy on the created {@link Signal}.
     */
    @objid ("fd1c64a1-0bf5-4007-b4ae-693fa880898c")
    public static Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Signal");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME);
        return Exception.instantiate((Signal)e);
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Signal
     * @return a {@link Exception} proxy or <i>null</i>.
     */
    @objid ("265fb914-b552-4c5a-99c1-7c05ef5ee908")
    public static Exception instantiate(Signal obj) {
        return Exception.canInstantiate(obj) ? new Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Signal}
     * @return a {@link Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f89c371f-f3f8-4e1f-8a95-d5b196049a16")
    public static Exception safeInstantiate(Signal obj) throws IllegalArgumentException {
        if (Exception.canInstantiate(obj))
        	return new Exception(obj);
        else
        	throw new IllegalArgumentException("Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5eb2294a-4f75-40bd-a0b6-e727a353b461")
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
        Exception other = (Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Signal}. 
     * @return the Signal represented by this proxy, never null.
     */
    @objid ("532ea5eb-c2fb-473c-8674-4c1df7bd932d")
    public Signal getElement() {
        return this.elt;
    }

    @objid ("02ea00b7-24dc-4b2f-93ac-3c476e1cdb93")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("045a143d-3ebb-42e4-962a-704032a04ae8")
    protected Exception(Signal elt) {
        this.elt = elt;
    }

    @objid ("9d616c88-ee7e-4e3e-a81d-c779a5fefc14")
    public static final class MdaTypes {
        @objid ("86f3b9c4-c3a2-40d2-bb01-3a38752a0e3f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("eb0d8353-8380-482e-9dd3-122e2b832768")
        private static Stereotype MDAASSOCDEP;

        @objid ("a38181bd-2bef-48d4-9cd7-06ab60c420a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("00a4ad35-e781-4966-b663-50ebd130f5d2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d1-0000-000000000000");
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
