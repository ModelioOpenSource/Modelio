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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartClassifierBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac4197cc-0a67-4a64-8eb4-caa91ba5815e")
public class UML2StartClassifierBehaviorAction {
    @objid ("01c74a11-d337-4f6b-aa5e-a1d7e1e01457")
    public static final String STEREOTYPE_NAME = "UML2StartClassifierBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("eafb56d5-1622-4715-9ec6-5833489975c7")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartClassifierBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("107c3a17-bff7-4a56-afae-47bf522029b9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> then instantiate a {@link UML2StartClassifierBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartClassifierBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4ab89669-eeb7-40cc-a012-927e809bf157")
    public static UML2StartClassifierBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME);
        return UML2StartClassifierBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartClassifierBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("e3201b90-48c9-4dd5-8ad5-e84d8818fdaf")
    public static UML2StartClassifierBehaviorAction instantiate(OpaqueAction obj) {
        return UML2StartClassifierBehaviorAction.canInstantiate(obj) ? new UML2StartClassifierBehaviorAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2StartClassifierBehaviorAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("75da06e6-62a9-4f32-8b15-7cb7ae8736b4")
    public static UML2StartClassifierBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartClassifierBehaviorAction.canInstantiate(obj))
        	return new UML2StartClassifierBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartClassifierBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3152a58d-4522-4bbb-9522-5018d609975d")
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
        UML2StartClassifierBehaviorAction other = (UML2StartClassifierBehaviorAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("33f32fa4-cfe1-45de-85a0-03a8142d9968")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6f714f00-2a9f-4fd4-a751-65a49b9d0a2e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d60bc91f-de05-4837-bb1c-fe78a0621b68")
    protected UML2StartClassifierBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("aecc60a1-6721-410a-8867-25ffb2dfa838")
    public static final class MdaTypes {
        @objid ("7a49acd7-830f-473e-9b1f-f672e5c0f564")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("df60e01d-8e63-4fcd-b146-672f3858a16c")
        private static Stereotype MDAASSOCDEP;

        @objid ("15d629cd-4d05-421b-b9a6-debda172001b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("44983dc9-aa0f-45b9-acae-e1d6fcbe7f22")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d357779-c2fd-11de-8ac8-001302895b2b");
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
