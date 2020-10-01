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
 * Proxy class to handle a {@link ElementImport} with << friend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("46045e22-e2ca-4093-b989-b9b9bfb21781")
public class Friend {
    @objid ("3848c6fd-f78b-445f-9e2e-5ed74a5ec4cf")
    public static final String STEREOTYPE_NAME = "friend";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("274c50cf-0258-4086-a12e-f1e1533c007d")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Friend proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << friend >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("04eae222-2f4f-4262-a7b8-71223d88b3c6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << friend >> then instantiate a {@link Friend} proxy.
     * 
     * @return a {@link Friend} proxy on the created {@link ElementImport}.
     */
    @objid ("222ca442-a6f0-4c71-814e-825c16a5aa2b")
    public static Friend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME);
        return Friend.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Friend} proxy or <i>null</i>.
     */
    @objid ("80dce694-70d1-4c3d-9d54-22970a507942")
    public static Friend instantiate(ElementImport obj) {
        return Friend.canInstantiate(obj) ? new Friend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Friend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f944b33b-8d29-4b61-8ded-e9741e3e9d55")
    public static Friend safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Friend.canInstantiate(obj))
        	return new Friend(obj);
        else
        	throw new IllegalArgumentException("Friend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("61cd74fa-79a6-4445-ad7b-413f4e7440d2")
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
        Friend other = (Friend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("f933ada3-87a0-49c9-8fd8-d4e8e6afad05")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("3ae0375b-5ff6-4cfe-a386-07cfebbed431")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("96a669fe-fa24-4e87-a4fd-daff27652d2f")
    protected Friend(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("e4135690-595b-43d4-b1e7-11c556195a6c")
    public static final class MdaTypes {
        @objid ("96680288-30c9-4683-8d4a-a801d6aa0246")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5a41ccae-c987-4ea6-851b-3acc0347bbc3")
        private static Stereotype MDAASSOCDEP;

        @objid ("6cb486c4-b495-4f5a-a585-e4e48350ee1d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("59f8f3ea-b6cb-4baf-bb4c-def373e2e8d8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ca-0000-000000000000");
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
