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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << new >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b720f13-4154-4751-a90f-aadaef5fa9dd")
public class New {
    @objid ("3034a47e-af7e-47b7-8343-bcf54bb45437")
    public static final String STEREOTYPE_NAME = "new";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("24dbbc89-9a4c-42f7-99c6-71290e682346")
    protected final Constraint elt;

    /**
     * Tells whether a {@link New proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << new >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2866b752-2a0d-4cce-b332-85734b5da12e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << new >> then instantiate a {@link New} proxy.
     * 
     * @return a {@link New} proxy on the created {@link Constraint}.
     */
    @objid ("f92daa67-85f9-4df6-8b7c-11511e9f1d75")
    public static New create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME);
        return New.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link New} proxy or <i>null</i>.
     */
    @objid ("ddb59ba8-978e-4833-8733-5a3cd3e9d320")
    public static New instantiate(Constraint obj) {
        return New.canInstantiate(obj) ? new New(obj) : null;
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link New} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bab2ee3f-4ddc-438a-8650-1142d657c084")
    public static New safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (New.canInstantiate(obj))
        	return new New(obj);
        else
        	throw new IllegalArgumentException("New: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d0df3034-a9a9-4c30-bb17-a1fc224e0e19")
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
        New other = (New) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("cf13d6ac-d535-4563-87fa-99a4a7b9ced6")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("c5054230-fd8b-447d-833a-7cfaf0e4fce7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("096fface-806e-41a1-9054-f36717c64353")
    protected New(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2f9de4ba-2666-4917-a258-dbdafc9f0143")
    public static final class MdaTypes {
        @objid ("f784d57d-286b-477b-9519-e74cd8ab937c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("63c5de5b-4be5-4ae8-b50d-51aeb5344cb7")
        private static Stereotype MDAASSOCDEP;

        @objid ("bea8bf71-11f0-4a4e-8002-943941861be7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2fc7f89c-1750-4bee-8f57-bca05b74bcc7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f9-0000-000000000000");
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
