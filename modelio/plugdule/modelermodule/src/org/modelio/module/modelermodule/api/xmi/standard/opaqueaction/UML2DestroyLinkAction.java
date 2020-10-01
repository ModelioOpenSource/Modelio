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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a9af42c-042a-4e5c-b598-80ac68b8af16")
public class UML2DestroyLinkAction {
    @objid ("222a711e-aa58-431d-8435-4e472e125d99")
    public static final String STEREOTYPE_NAME = "UML2DestroyLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("9d05428e-caa6-44c2-8982-5e4449b7da8c")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fdea3fec-5b89-440b-ad0c-dab086d1f253")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> then instantiate a {@link UML2DestroyLinkAction} proxy.
     * 
     * @return a {@link UML2DestroyLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("9edcf6c1-a4ff-4c88-ac90-d5b45e0f7be7")
    public static UML2DestroyLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
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
    @objid ("734990d1-1ea7-4b1f-a1b9-97dcd8955220")
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
    @objid ("3c08f5eb-37a4-4d58-8f3f-4e58a80f433d")
    public static UML2DestroyLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyLinkAction.canInstantiate(obj))
        	return new UML2DestroyLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d94d6937-0d02-4ad7-a29f-bb528b9dc795")
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
    @objid ("7cedc841-ff5d-4c9a-925a-47385522c62a")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("8dcf2788-8de2-4a27-86b7-deee3bd78e58")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b4edc9fe-fa2e-4111-9a6e-a1c2251fc808")
    protected UML2DestroyLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("32304d94-dbf4-4c93-af43-6a718691d283")
    public static final class MdaTypes {
        @objid ("2d9d2fa3-fce9-45cc-a6ed-5d4769c8ad48")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7465ce30-c889-45d9-a2f6-f11d1491a5bd")
        private static Stereotype MDAASSOCDEP;

        @objid ("b4338f4d-9352-41ac-973c-08f9b8145f56")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6fdc9c40-5321-4fba-80fd-22154465c01a")
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
