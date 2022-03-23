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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a9af42c-042a-4e5c-b598-80ac68b8af16")
public class UML2DestroyLinkAction {
    @objid ("7be2c2aa-15af-4c47-89c6-63788aee234b")
    public static final String STEREOTYPE_NAME = "UML2DestroyLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("22c47d8e-a52b-44e5-bc66-ec85b345d221")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("008f5e01-d209-4053-9d95-5f91e36719c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> then instantiate a {@link UML2DestroyLinkAction} proxy.
     * 
     * @return a {@link UML2DestroyLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("adc014b6-f516-474d-8f55-6ef63d29b247")
    public static UML2DestroyLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME);
        return UML2DestroyLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyLinkAction} proxy or <i>null</i>.
     */
    @objid ("6b733b63-3f22-4879-8aa1-78ab9b368c0d")
    public static UML2DestroyLinkAction instantiate(OpaqueAction obj) {
        return UML2DestroyLinkAction.canInstantiate(obj) ? new UML2DestroyLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("febc6832-378e-4b32-bb72-d27570e69925")
    public static UML2DestroyLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyLinkAction.canInstantiate(obj))
        	return new UML2DestroyLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("51df8394-6131-4764-8de8-f7d82b2f0636")
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
        UML2DestroyLinkAction other = (UML2DestroyLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("358ea697-931b-40a8-96a2-666abb569e40")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("da976945-6c3c-45a8-bf8c-0a2480e73aa4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2e30c7c7-6ff2-4ec2-9b08-470f814a0b14")
    protected  UML2DestroyLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("32304d94-dbf4-4c93-af43-6a718691d283")
    public static final class MdaTypes {
        @objid ("3fb267a0-7bbe-4899-b83e-82f080322d43")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("53f35f3d-b16e-456b-bd79-23f639f99160")
        private static Stereotype MDAASSOCDEP;

        @objid ("ef9205f5-7e7c-42b2-8f1a-28f4c157a63f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cc502abb-ec65-43fd-8cf8-165a53f18e20")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "744f6321-c2f9-11de-8ac8-001302895b2b");
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
