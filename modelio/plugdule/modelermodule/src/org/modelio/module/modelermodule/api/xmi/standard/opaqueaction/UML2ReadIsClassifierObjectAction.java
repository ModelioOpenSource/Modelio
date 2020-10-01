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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadIsClassifierObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2863745e-922c-4eff-ae51-cb047027a4c0")
public class UML2ReadIsClassifierObjectAction {
    @objid ("19690970-6ba2-4510-bb42-09a1990f94c8")
    public static final String STEREOTYPE_NAME = "UML2ReadIsClassifierObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("76f00fe8-5b1b-4632-9021-41e6a7797ecf")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadIsClassifierObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0a92abc5-12a8-4699-8a2e-30bec4b43a4f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> then instantiate a {@link UML2ReadIsClassifierObjectAction} proxy.
     * 
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("94ac90f2-83f1-4c28-ba9a-3f87afcf11be")
    public static UML2ReadIsClassifierObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME);
        return UML2ReadIsClassifierObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy or <i>null</i>.
     */
    @objid ("4a581bca-b9f7-4eb9-b9f5-608e8bfecbd8")
    public static UML2ReadIsClassifierObjectAction instantiate(OpaqueAction obj) {
        return UML2ReadIsClassifierObjectAction.canInstantiate(obj) ? new UML2ReadIsClassifierObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("be0ff09b-d465-474f-b007-7fa6b3371d49")
    public static UML2ReadIsClassifierObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadIsClassifierObjectAction.canInstantiate(obj))
        	return new UML2ReadIsClassifierObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadIsClassifierObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3f2b9c90-dbb7-4eff-9eeb-989f730b6b76")
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
        UML2ReadIsClassifierObjectAction other = (UML2ReadIsClassifierObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("3cb0b9e3-d947-42ff-9b2e-242ae04f3f21")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("4613573a-c1c0-4242-92df-c32c1bb94052")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ea9d7132-5ee6-44a8-a782-2ecf5a24fd6d")
    protected UML2ReadIsClassifierObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("56ed5dde-97b3-43a7-80e9-7b0b898107d8")
    public static final class MdaTypes {
        @objid ("532e530a-6919-4404-9c17-75224a7e17cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("30702b4f-4852-4ac1-8206-1130342fc770")
        private static Stereotype MDAASSOCDEP;

        @objid ("3b7249c3-c053-4e17-8da0-9bdafbd8bf0f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("58c78a45-02ba-410a-991c-0c29f9903f28")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4c6c55f-c2fc-11de-8ac8-001302895b2b");
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
