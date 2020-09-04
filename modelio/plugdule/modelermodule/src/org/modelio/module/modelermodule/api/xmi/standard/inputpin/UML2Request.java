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
 * Proxy class to handle a {@link InputPin} with << UML2Request >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1e13d871-b6e7-425b-b323-3853d5ba793a")
public class UML2Request {
    @objid ("48503871-9658-46e4-9934-79dcce1c7123")
    public static final String STEREOTYPE_NAME = "UML2Request";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7e140bd8-9201-4a66-bd89-939b55b7a4a1")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Request proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Request >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c20dceb6-2e75-49d4-b219-827987da1e76")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Request >> then instantiate a {@link UML2Request} proxy.
     * 
     * @return a {@link UML2Request} proxy on the created {@link InputPin}.
     */
    @objid ("374bb780-bdc0-4b9b-93ea-a1c2f5e0f298")
    public static UML2Request create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME);
        return UML2Request.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Request} proxy or <i>null</i>.
     */
    @objid ("1589e4d2-c646-4ee9-a20f-4664aafa9d3c")
    public static UML2Request instantiate(InputPin obj) {
        return UML2Request.canInstantiate(obj) ? new UML2Request(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Request} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ee7d24ca-69ee-4e44-8927-f2752458acb4")
    public static UML2Request safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Request.canInstantiate(obj))
        	return new UML2Request(obj);
        else
        	throw new IllegalArgumentException("UML2Request: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b5913de7-979e-47c2-9940-9e72873bee15")
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
        UML2Request other = (UML2Request) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("dd0b2884-5cc1-4ab1-b757-b24c1d8c6d70")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("66bfc89c-f941-4de9-a880-6f094b971bd7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("016f5eba-f416-4bca-b147-100c2ad8bfda")
    protected UML2Request(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3e4f51a5-49eb-42d4-9cf5-f8ecc89b07e5")
    public static final class MdaTypes {
        @objid ("3cf159cb-a056-4f0d-aafa-37324c18a0e2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e588a43-0194-47a8-b990-888832cf7346")
        private static Stereotype MDAASSOCDEP;

        @objid ("79e7399f-b523-4bf4-adc0-00aff927f8f6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("08ea9de5-6a3e-4cc2-bde1-112051eea7d0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e0ff915d-8d3f-4da8-84be-5aed471a16c5");
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
