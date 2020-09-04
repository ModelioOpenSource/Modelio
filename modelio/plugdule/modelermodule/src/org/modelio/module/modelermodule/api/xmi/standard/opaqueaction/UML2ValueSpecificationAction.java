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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ValueSpecificationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dd53d800-09cd-4ca8-af69-5dce03bc0d85")
public class UML2ValueSpecificationAction {
    @objid ("16058d89-f008-4e8e-b242-d88bd0ba6852")
    public static final String STEREOTYPE_NAME = "UML2ValueSpecificationAction";

    @objid ("47de8211-73af-4150-8e02-ec5354e68f3d")
    public static final String VALUE_TAGTYPE = "Value";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("a49fff74-2d48-46b1-ac88-4229b0a8ae38")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ValueSpecificationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fa01e210-06fe-4079-a57f-86c1ba9a1ab6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> then instantiate a {@link UML2ValueSpecificationAction} proxy.
     * 
     * @return a {@link UML2ValueSpecificationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("279f9891-9d0d-4b16-92c5-2e3cba816ac0")
    public static UML2ValueSpecificationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME);
        return UML2ValueSpecificationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ValueSpecificationAction} proxy or <i>null</i>.
     */
    @objid ("6ef80b23-5d22-4935-9054-eca711a82adb")
    public static UML2ValueSpecificationAction instantiate(OpaqueAction obj) {
        return UML2ValueSpecificationAction.canInstantiate(obj) ? new UML2ValueSpecificationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ValueSpecificationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("75adf842-55ba-41b4-a756-b0724ff567f5")
    public static UML2ValueSpecificationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ValueSpecificationAction.canInstantiate(obj))
        	return new UML2ValueSpecificationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ValueSpecificationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("230d2c9c-918a-4dc7-95e1-90e789fc608a")
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
        UML2ValueSpecificationAction other = (UML2ValueSpecificationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b578941a-5642-4591-85f3-c5d8b2c0d2ad")
    public OpaqueAction getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("678dc82d-2282-4c3a-93fd-33a4470ee7f6")
    public String getValue() {
        return this.elt.getTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT);
    }

    @objid ("f4952aaa-e6a2-4955-a1dd-4d8aaad37616")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("397b1fa0-9c4c-41e1-982a-b9d431c53ebf")
    public void setValue(String value) {
        this.elt.putTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT, value);
    }

    @objid ("30861800-cc7c-48b9-9b72-a8a88936ae26")
    protected UML2ValueSpecificationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3e1fa1c4-a0c2-4590-b736-9bac327ca525")
    public static final class MdaTypes {
        @objid ("e2674d00-2430-40ed-a6d1-d252f1fc8ae2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("95ed24c2-ace9-4324-abfe-8fb9d3a9d65f")
        public static TagType VALUE_TAGTYPE_ELT;

        @objid ("fdf535eb-e594-408f-ae71-e544036f7439")
        private static Stereotype MDAASSOCDEP;

        @objid ("3e14eb35-55b3-4a20-91ed-350db62fe01a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d90146ec-e0a7-464b-a335-72b289e0cab1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1c0bf642-a90f-11de-8613-001302895b2b");
            VALUE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "b1935f69-ac36-11de-aefc-001302895b2b");
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
