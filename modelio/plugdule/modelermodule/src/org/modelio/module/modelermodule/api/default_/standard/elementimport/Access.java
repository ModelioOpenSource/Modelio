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
 * Proxy class to handle a {@link ElementImport} with << access >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b374025a-7e25-4fab-adc2-20468c9497a7")
public class Access {
    @objid ("db12c012-05b4-4a93-9602-e6b0778ec7da")
    public static final String STEREOTYPE_NAME = "access";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("784bd9cf-58ad-4af9-8900-f01eb4a1db88")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Access proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << access >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fbf7fa10-2166-4463-9e2b-7baebf670bb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << access >> then instantiate a {@link Access} proxy.
     * 
     * @return a {@link Access} proxy on the created {@link ElementImport}.
     */
    @objid ("79143665-eaa8-4656-befb-8f3f97357973")
    public static Access create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME);
        return Access.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Access} proxy or <i>null</i>.
     */
    @objid ("038586d4-e21e-4d32-9e93-7e4dfb387fbb")
    public static Access instantiate(ElementImport obj) {
        return Access.canInstantiate(obj) ? new Access(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Access} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fc4b25cb-de3b-49cb-9ada-0b72bb5f3673")
    public static Access safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Access.canInstantiate(obj))
        	return new Access(obj);
        else
        	throw new IllegalArgumentException("Access: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0eb849c-ac2a-4670-9b49-59d5b5574f8c")
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
        Access other = (Access) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("e0171176-39fd-4683-a360-6b5bf016a862")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("3883c628-93e2-4f20-a63c-7c9623a3816a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fda2bac5-fea7-420e-8bd0-ff621efc8f17")
    protected Access(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("f1f67b2c-6db9-48c7-8cfe-811ff7873676")
    public static final class MdaTypes {
        @objid ("653273a2-a2dc-4151-86f0-6e227873b156")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6c7e8a60-d7b8-476c-bd37-01480c5da19d")
        private static Stereotype MDAASSOCDEP;

        @objid ("7dd8249b-6a47-4169-be6b-6f60fbfd3c61")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dbf7a57d-0670-4d54-a4b2-8cd37ff17e1c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01cc-0000-000000000000");
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
