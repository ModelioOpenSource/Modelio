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
 * Proxy class to handle a {@link ElementImport} with << create >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1ace0953-e65f-4688-9309-8b3b6ed39c7c")
public class Create {
    @objid ("9d064b2a-66a4-41e2-9122-52a5194ffd02")
    public static final String STEREOTYPE_NAME = "create";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("428749d6-c966-450a-9fe9-678d2275a605")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Create proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << create >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9753dcbf-e5ae-4e7f-b0f3-dd1c2b4432be")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << create >> then instantiate a {@link Create} proxy.
     * 
     * @return a {@link Create} proxy on the created {@link ElementImport}.
     */
    @objid ("1c17d644-37c8-4aa8-9ebc-743f9e0db2c6")
    public static Create create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME);
        return Create.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Create} proxy or <i>null</i>.
     */
    @objid ("dc1012c3-b5f7-4900-960f-594ab3e36264")
    public static Create instantiate(ElementImport obj) {
        return Create.canInstantiate(obj) ? new Create(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Create} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7b601aab-f62e-44d2-9159-69ccebe837e2")
    public static Create safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Create.canInstantiate(obj))
        	return new Create(obj);
        else
        	throw new IllegalArgumentException("Create: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("be35ea2c-3856-4aff-9279-11eda61a55c3")
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
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("30c89988-b523-460d-bdfc-c314c39cd130")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("b520e054-ad48-49fd-9a3b-3615a8a5fe41")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("512c435e-7dd5-4bf5-b4f4-dea8e39d8188")
    protected Create(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("97dedb37-d9c0-46d3-8bc3-639e2cfc8c0e")
    public static final class MdaTypes {
        @objid ("8e846891-d9c3-474c-af8f-a80a6a88cd4a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ed196161-dc11-4280-af82-a72c68843d06")
        private static Stereotype MDAASSOCDEP;

        @objid ("dc3a8030-135a-4932-a4c3-4d38fee95d7b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("169d0c5b-bfe2-42c5-8dc0-b547ee6ff5b5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0202-0000-000000000000");
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
