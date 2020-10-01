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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca7cc715-a18b-49dd-b91e-187dccae7ec1")
public class UML2DestroyObjectAction {
    @objid ("d4771d46-1a58-46e1-9a8b-e696ad7660d9")
    public static final String STEREOTYPE_NAME = "UML2DestroyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("092625d4-91dd-43bd-9e42-12250f33d030")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7b05edd1-f453-4799-940d-aea94e504fa3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> then instantiate a {@link UML2DestroyObjectAction} proxy.
     * 
     * @return a {@link UML2DestroyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("89f74ea7-3e56-4d15-94e1-d0ff63dd7f89")
    public static UML2DestroyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME);
        return UML2DestroyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyObjectAction} proxy or <i>null</i>.
     */
    @objid ("3b2f3afb-04ae-4a3c-8b09-d64ee7a55573")
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
    @objid ("aa588177-6f15-48f4-ac8c-842bd68766d7")
    public static UML2DestroyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyObjectAction.canInstantiate(obj))
        	return new UML2DestroyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("93fb487a-635f-433a-a59b-4f1e4dc06a69")
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
    @objid ("44060ffb-d85b-4dcf-b98e-c2f8e49dda08")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("d61c875d-0a95-485d-b308-519ccf64410c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4982ae5e-4ede-4c30-bd45-a0a8131ccdae")
    protected UML2DestroyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("0b742e7e-97c9-4b51-8f98-806bd4659614")
    public static final class MdaTypes {
        @objid ("23e9a3fd-364e-47fd-94cf-50079d38b05c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("76d3d529-8fc6-40d8-b9fc-c341e4f691d8")
        private static Stereotype MDAASSOCDEP;

        @objid ("6ef7d6b8-f787-4043-82a5-0810358f0325")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3873c8ed-5c33-4b14-b70b-f740171c85df")
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
