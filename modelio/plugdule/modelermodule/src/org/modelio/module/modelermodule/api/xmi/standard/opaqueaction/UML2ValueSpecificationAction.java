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
    @objid ("d466ba0d-d601-41bc-90da-a8efcf5d0e45")
    public static final String STEREOTYPE_NAME = "UML2ValueSpecificationAction";

    @objid ("1a05bf0e-7b9e-4746-8d57-bdbc0a2ada85")
    public static final String VALUE_TAGTYPE = "Value";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("8c9f9613-5808-4975-b61c-4755c03e6057")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ValueSpecificationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b60a4a07-bc4a-4496-ade6-014d6e42297f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> then instantiate a {@link UML2ValueSpecificationAction} proxy.
     * 
     * @return a {@link UML2ValueSpecificationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("95879f73-800b-4b7d-a766-81ae2aeafdb5")
    public static UML2ValueSpecificationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME);
        return UML2ValueSpecificationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ValueSpecificationAction} proxy or <i>null</i>.
     */
    @objid ("5639adbb-018b-481c-b374-c38951fe4da2")
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
    @objid ("5025fa41-7daf-44e6-ad43-2fbe702f2713")
    public static UML2ValueSpecificationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ValueSpecificationAction.canInstantiate(obj))
        	return new UML2ValueSpecificationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ValueSpecificationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5957eb0c-fa1d-44b2-8420-e36c482044a8")
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
    @objid ("db4f691d-edd9-4a61-8ade-8c2a5e3d2ff5")
    public OpaqueAction getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a68ce7ac-fab8-407b-aced-eea6909439c4")
    public String getValue() {
        return this.elt.getTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT);
    }

    @objid ("0acb4fda-3bdc-491a-b0bf-1660c223701a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9db6abd4-4a7f-4e21-a1b9-43ca6fdc33b7")
    public void setValue(String value) {
        this.elt.putTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT, value);
    }

    @objid ("20d19032-30bf-4360-a0c3-e6b0f3c5f0a2")
    protected UML2ValueSpecificationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3e1fa1c4-a0c2-4590-b736-9bac327ca525")
    public static final class MdaTypes {
        @objid ("d7dd084f-867b-41dd-926a-3fe9deb02e31")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e00a228a-ad13-4389-b14f-f982f427a2a2")
        public static TagType VALUE_TAGTYPE_ELT;

        @objid ("d331a88f-2a76-4005-84e4-601760a2c873")
        private static Stereotype MDAASSOCDEP;

        @objid ("841d4bae-546b-4ed2-a4ea-e79f0e616ad0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("532e3462-1374-4bdc-8492-46a08dd66cf6")
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
