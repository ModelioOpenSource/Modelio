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
package org.modelio.module.modelermodule.api.default_.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << create >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("73b5dac7-a5b3-4178-b056-93aaa0e2ed41")
public class Create {
    @objid ("0a0e72b8-eb93-4813-80ed-2732e3443d26")
    public static final String STEREOTYPE_NAME = "create";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("2839d5a3-32f6-443d-b816-02a76bb42112")
    protected final Operation elt;

    /**
     * Tells whether a {@link Create proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << create >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("45a228c0-7676-41bd-a8bd-6476465a6ba3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << create >> then instantiate a {@link Create} proxy.
     * 
     * @return a {@link Create} proxy on the created {@link Operation}.
     */
    @objid ("d81e12a8-ccb5-4989-8980-717be29755f2")
    public static Create create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME);
        return Create.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link Operation} stereotyped << create >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Create} proxy or <i>null</i>.
     */
    @objid ("8aa5fb18-32f7-4545-97b4-98e36e6381c6")
    public static Create instantiate(Operation obj) {
        return Create.canInstantiate(obj) ? new Create(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link Operation} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link Create} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ee2908fd-bd8b-41cb-aa77-d85e899589c8")
    public static Create safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Create.canInstantiate(obj))
        	return new Create(obj);
        else
        	throw new IllegalArgumentException("Create: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("115f7966-3eea-4d3c-99a4-42218e7ba8bb")
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
        Create other = (Create) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("57fd7121-8e3a-476c-a362-d318a324c0a0")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("b0acac46-cd63-4607-8e79-88dba8d97a17")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("3f4f1a13-e9a4-4f1c-8512-fd943e8c576b")
    protected Create(Operation elt) {
        this.elt = elt;
    }

    @objid ("98b0bd6f-4676-4bac-aa4f-c8a2015d89b0")
    public static final class MdaTypes {
        @objid ("a17bed35-ab05-4301-ac94-5ce836d7269e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("28304c7e-93f7-4e01-b728-8403fcb107cb")
        private static Stereotype MDAASSOCDEP;

        @objid ("75f4ce5b-9fdd-4ab4-b83e-d683756c715d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c7af00b-8a26-4a9d-91de-fce9062ff558")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0204-0000-000000000000");
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
