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
 * Proxy class to handle a {@link InputPin} with << UML2Exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("280f1bea-297f-47f4-b6d6-5ab646b57289")
public class UML2Exception {
    @objid ("2dc074c8-2d2c-4bc5-bd2a-b3a3504d9e04")
    public static final String STEREOTYPE_NAME = "UML2Exception";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7d34b9d7-04de-4ede-a405-361a112cc862")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Exception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d3655597-6c76-4a1d-80fa-597faf78f408")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Exception >> then instantiate a {@link UML2Exception} proxy.
     * 
     * @return a {@link UML2Exception} proxy on the created {@link InputPin}.
     */
    @objid ("3f2156e4-2895-495b-8882-1160e604690b")
    public static UML2Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME);
        return UML2Exception.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Exception} proxy or <i>null</i>.
     */
    @objid ("f453b246-19a0-4ab6-8dd7-5b8536778a8a")
    public static UML2Exception instantiate(InputPin obj) {
        return UML2Exception.canInstantiate(obj) ? new UML2Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6245666c-ff37-4bbb-8340-b6f301b6b636")
    public static UML2Exception safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Exception.canInstantiate(obj))
        	return new UML2Exception(obj);
        else
        	throw new IllegalArgumentException("UML2Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1059b900-0f45-4271-a402-6f55330cb47d")
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
        UML2Exception other = (UML2Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("7238ac7c-1f35-4661-a343-26c5dec0c7af")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("391ebb69-3109-48a4-a463-f7e487b6cb4c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f873a642-e495-4493-95e5-a8b8a2804d4e")
    protected UML2Exception(InputPin elt) {
        this.elt = elt;
    }

    @objid ("07543495-a7b1-4345-accd-b02a81db07a5")
    public static final class MdaTypes {
        @objid ("a7575885-d1b3-41ef-ac08-82a984f0d25b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b79b72f1-532d-45a2-9a5b-4e92d60e661e")
        private static Stereotype MDAASSOCDEP;

        @objid ("83553ade-091d-4348-9108-7ee6b89c99a1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fa5f3e85-b3ff-4503-b756-a09967eb10ed")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b966e108-5fbe-4990-b7cf-94d258a5c3ff");
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
