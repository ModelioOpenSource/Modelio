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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReplyAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1072f154-5793-424b-9dbf-2eaa07f6a142")
public class UML2ReplyAction {
    @objid ("50f4d307-6df0-421e-89bd-c30911ebbdb8")
    public static final String STEREOTYPE_NAME = "UML2ReplyAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("518df155-52ad-4387-becf-0c5c9be6dbd5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReplyAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReplyAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1976d9f0-7656-4326-9ac4-714ea4887af7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReplyAction >> then instantiate a {@link UML2ReplyAction} proxy.
     * 
     * @return a {@link UML2ReplyAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("36e4d722-800b-4ef7-aa30-86e44fac40aa")
    public static UML2ReplyAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME);
        return UML2ReplyAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReplyAction} proxy or <i>null</i>.
     */
    @objid ("137a39b4-faaf-4ab2-b721-d6489fb6384c")
    public static UML2ReplyAction instantiate(OpaqueAction obj) {
        return UML2ReplyAction.canInstantiate(obj) ? new UML2ReplyAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReplyAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e8a864fc-46f9-4cc1-bc5b-49aabac50c35")
    public static UML2ReplyAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReplyAction.canInstantiate(obj))
        	return new UML2ReplyAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6f9a417d-d891-428b-8add-ddeaa869da27")
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
        UML2ReplyAction other = (UML2ReplyAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("ef1823de-c489-435b-a796-fc1a7986e468")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("5042d5e4-6bf9-451d-838c-eb660855f9d3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a7789934-63f5-415d-a71a-67dee5247616")
    protected UML2ReplyAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e3df1d05-1797-48da-8ef8-fa4d80b22d66")
    public static final class MdaTypes {
        @objid ("cbb8b704-100a-4706-8fe0-09c4113f6a09")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4b07aa4a-0d22-4c6a-b86e-9ec0f35801ab")
        private static Stereotype MDAASSOCDEP;

        @objid ("2146ce8a-189a-43be-9760-784f11585192")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("000dfe51-f471-41cb-b6a0-2c5a8401ab82")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c6a579b6-5d0d-11df-a996-001302895b2b");
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
