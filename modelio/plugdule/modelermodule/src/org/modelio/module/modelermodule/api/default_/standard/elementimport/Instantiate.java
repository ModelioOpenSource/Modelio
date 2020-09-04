/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link ElementImport} with << instantiate >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f52a2d6-89b9-4630-b892-b28a159590b9")
public class Instantiate {
    @objid ("f8cd2a75-d924-4aa3-b3dd-04a1d4e138ce")
    public static final String STEREOTYPE_NAME = "instantiate";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("8fc3aa94-72a1-4273-acf7-f790479d2b3e")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Instantiate proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << instantiate >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4d38bccd-e24c-4efa-a622-336714e36380")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << instantiate >> then instantiate a {@link Instantiate} proxy.
     * 
     * @return a {@link Instantiate} proxy on the created {@link ElementImport}.
     */
    @objid ("bb427d90-9fa6-4c15-aa96-11775bcef808")
    public static Instantiate create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME);
        return Instantiate.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Instantiate} proxy or <i>null</i>.
     */
    @objid ("bbecbc19-95f2-4668-988d-3ea45c4ea891")
    public static Instantiate instantiate(ElementImport obj) {
        return Instantiate.canInstantiate(obj) ? new Instantiate(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Instantiate} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("18259939-9111-4e24-9289-fbeee3e9a8f7")
    public static Instantiate safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Instantiate.canInstantiate(obj))
        	return new Instantiate(obj);
        else
        	throw new IllegalArgumentException("Instantiate: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("323f94a9-ea48-49d5-8f76-afb135c39f51")
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
        Instantiate other = (Instantiate) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("9b3c35a0-ab61-439f-be9a-1bc75d92bf00")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("39cfe2be-5652-4d79-b7e7-ba03db061708")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6744035d-32a1-4393-90e9-57244fb9dc33")
    protected Instantiate(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("5005c1af-f43a-4d74-9d9a-355357fdf406")
    public static final class MdaTypes {
        @objid ("7f26b6b5-45f9-492b-bc11-ed976f0c89fe")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("78a73c60-8dc7-40c6-8a85-dcf95dc19f73")
        private static Stereotype MDAASSOCDEP;

        @objid ("2d444786-773e-4bc4-b871-25b77b01e25a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9933144a-bdb3-4b80-9c6c-fbf2ca42464a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e5-0000-000000000000");
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
