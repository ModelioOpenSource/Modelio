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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2InsertAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42686ded-7b6b-453d-ab60-76a6e2b6d99a")
public class UML2InsertAt {
    @objid ("a682a186-5335-4bbe-90ed-95e6cd021148")
    public static final String STEREOTYPE_NAME = "UML2InsertAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("c9a8581a-cf55-4b63-8d4f-f3915a488bfe")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InsertAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InsertAt >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ec161ca2-2d74-4b6c-b334-aa5142f07e28")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InsertAt >> then instantiate a {@link UML2InsertAt} proxy.
     * 
     * @return a {@link UML2InsertAt} proxy on the created {@link InputPin}.
     */
    @objid ("9588dd3a-fa5d-4b69-9bce-bc44192cdd1c")
    public static UML2InsertAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME);
        return UML2InsertAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InsertAt} proxy or <i>null</i>.
     */
    @objid ("789f642b-6f53-4734-ba21-f82afba2257c")
    public static UML2InsertAt instantiate(InputPin obj) {
        return UML2InsertAt.canInstantiate(obj) ? new UML2InsertAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InsertAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("756ab7fc-c934-4615-b1f4-febd51eb581f")
    public static UML2InsertAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InsertAt.canInstantiate(obj))
        	return new UML2InsertAt(obj);
        else
        	throw new IllegalArgumentException("UML2InsertAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2e31dab7-1cdf-43cd-878f-f47decfb053c")
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
        UML2InsertAt other = (UML2InsertAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("e8949c1e-9778-474e-8d3d-e994989faaa7")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("fc670d91-c752-4661-95a7-c1d660931358")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("81342d89-786b-48b8-b551-a2e420c15483")
    protected UML2InsertAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3488919b-4d36-47de-bef3-5b5290d81e53")
    public static final class MdaTypes {
        @objid ("184ca3b1-958c-4522-8d34-7bfce682a963")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4c8c002e-6169-42ab-aae4-1ab1d676783f")
        private static Stereotype MDAASSOCDEP;

        @objid ("6d0b318f-34c1-462d-b216-ba7a76adde8f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5491dfbd-4be3-4475-99d7-46c99cf9d4dd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ec22d8ff-de86-11de-905b-001302895b2b");
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
