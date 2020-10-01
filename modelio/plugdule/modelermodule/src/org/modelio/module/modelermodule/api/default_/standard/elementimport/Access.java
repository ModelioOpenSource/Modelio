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
 * Proxy class to handle a {@link ElementImport} with << access >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b374025a-7e25-4fab-adc2-20468c9497a7")
public class Access {
    @objid ("ebcc6cc9-267e-4203-a095-2f99ed0d5d34")
    public static final String STEREOTYPE_NAME = "access";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("25ca0132-6521-4e35-b0a7-02ff5ab2cac0")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Access proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << access >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b56caafa-45aa-4b71-85e6-e7c18c011900")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << access >> then instantiate a {@link Access} proxy.
     * 
     * @return a {@link Access} proxy on the created {@link ElementImport}.
     */
    @objid ("1cd789e9-f511-4d95-9022-9a5d49fcfb12")
    public static Access create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME);
        return Access.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Access} proxy or <i>null</i>.
     */
    @objid ("786b382b-b79b-4196-bbfa-eba5fa24ed09")
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
    @objid ("31b76d82-23f1-4e86-96e8-dac4e67347be")
    public static Access safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Access.canInstantiate(obj))
        	return new Access(obj);
        else
        	throw new IllegalArgumentException("Access: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6fedfc7c-acbe-4a63-adb9-7a5786163ba5")
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
    @objid ("c6ec3967-30c3-49c7-925c-73c5fbd27063")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("f0ec93e9-3481-4faa-9cbf-403f6c8a6b68")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1b664204-a1f7-4b27-bb52-f5404fd16316")
    protected Access(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("f1f67b2c-6db9-48c7-8cfe-811ff7873676")
    public static final class MdaTypes {
        @objid ("b8bf376e-7283-4d1a-be54-6c0daa3c84f1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("56f6dacd-2bba-4cf8-a99b-36b0c705e4a9")
        private static Stereotype MDAASSOCDEP;

        @objid ("a70430d5-1a5d-4279-9782-4f6a84299ffe")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f0612b1-8a73-4d07-9131-2da14ddadf9e")
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
