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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca7cc715-a18b-49dd-b91e-187dccae7ec1")
public class UML2DestroyObjectAction {
    @objid ("4adefdbb-2394-41f8-8c02-ee43a1ae8187")
    public static final String STEREOTYPE_NAME = "UML2DestroyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("11386f69-1631-46a4-9af3-5ba7e18e78bd")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("377297fa-a56d-4c0a-8b1f-aa550b9c170d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> then instantiate a {@link UML2DestroyObjectAction} proxy.
     * 
     * @return a {@link UML2DestroyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("3c5fd18c-5dcd-4035-9c41-eb5f77b9908c")
    public static UML2DestroyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME);
        return UML2DestroyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyObjectAction} proxy or <i>null</i>.
     */
    @objid ("3070af72-faf0-4fcf-b9b9-3fac50a2bd83")
    public static UML2DestroyObjectAction instantiate(OpaqueAction obj) {
        return UML2DestroyObjectAction.canInstantiate(obj) ? new UML2DestroyObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c98a37b0-46bd-4780-9113-b2a4505a0e5c")
    public static UML2DestroyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyObjectAction.canInstantiate(obj))
        	return new UML2DestroyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d515b928-4266-4ae0-aad4-3e132719c8ef")
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
        UML2DestroyObjectAction other = (UML2DestroyObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("fd1dd5da-6685-4068-9f45-870508b36c85")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c68911de-8ecd-4b92-9caa-ee1f84141c10")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("324ecc54-e819-4eb4-84cf-9e9f36e5b34a")
    protected UML2DestroyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("0b742e7e-97c9-4b51-8f98-806bd4659614")
    public static final class MdaTypes {
        @objid ("3a65f06a-931c-4efd-aeb3-deac7cd75ddc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a4db4869-c60f-4d1f-9830-5cc7144e5b6f")
        private static Stereotype MDAASSOCDEP;

        @objid ("68dbfed4-0e70-4e99-b329-58bcafbe9016")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5d2fa048-1348-4ce0-ab7e-0d8fa2c9572f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "cf671bc3-c2f9-11de-8ac8-001302895b2b");
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
