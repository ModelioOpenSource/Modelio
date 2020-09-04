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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndQualifierAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f2df080d-78a1-4a10-bc63-92b34862282d")
public class UML2ReadLinkObjectEndQualifierAction {
    @objid ("537a8aff-d27b-4ba3-891b-90b172d16b4d")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndQualifierAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("7e08ed35-1acd-48eb-a332-7ae05699b005")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndQualifierAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2c3f4fa8-5710-44f9-a5c6-ea8e19914086")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> then instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("355ab17a-aaff-4eda-be4e-6c0cc115961c")
    public static UML2ReadLinkObjectEndQualifierAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndQualifierAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy or <i>null</i>.
     */
    @objid ("57c342e8-ae9a-4022-a48a-e06e4eaec000")
    public static UML2ReadLinkObjectEndQualifierAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndQualifierAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a63876a7-a5ed-4bd8-be83-75adef7a1afc")
    public static UML2ReadLinkObjectEndQualifierAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndQualifierAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndQualifierAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3984caa1-61d0-4d82-b2df-47c62177da26")
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
        UML2ReadLinkObjectEndQualifierAction other = (UML2ReadLinkObjectEndQualifierAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("c2e4cd68-5a80-4a38-80ca-a6a1598ea8d1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("58de58a0-b5c0-4010-b15a-c8464d7c975c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("88a823c8-a628-4899-ace8-f1709880c4d5")
    protected UML2ReadLinkObjectEndQualifierAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5227cd16-da95-4b40-a5e0-22322a3d69eb")
    public static final class MdaTypes {
        @objid ("457a7861-c210-44e4-b23a-f7eeefac1356")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9faf8795-2590-41b5-b75b-5e04f2c7a105")
        private static Stereotype MDAASSOCDEP;

        @objid ("5c0ceb1e-b792-4953-b33e-8c94eb49a6ae")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5960df40-b9ce-40b5-9d25-bbfcb26b14a4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "06edcdd9-c2fd-11de-8ac8-001302895b2b");
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
