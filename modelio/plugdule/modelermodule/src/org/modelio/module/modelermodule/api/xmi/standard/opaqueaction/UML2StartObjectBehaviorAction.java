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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartObjectBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("25009208-0fa9-4e7f-90cf-c52ca583397c")
public class UML2StartObjectBehaviorAction {
    @objid ("5884e605-fa76-4a2f-a873-e25dc03b38ec")
    public static final String STEREOTYPE_NAME = "UML2StartObjectBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2befc7b8-2d2c-44d5-97b7-9ce9259ec725")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartObjectBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("595aa940-f825-43c9-acb5-f215bc43e380")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> then instantiate a {@link UML2StartObjectBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartObjectBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4d65c499-7d70-4019-8701-6a05de1d6f99")
    public static UML2StartObjectBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME);
        return UML2StartObjectBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartObjectBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartObjectBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("b1ffe399-89aa-4931-bfb4-14ebb2270728")
    public static UML2StartObjectBehaviorAction instantiate(OpaqueAction obj) {
        return UML2StartObjectBehaviorAction.canInstantiate(obj) ? new UML2StartObjectBehaviorAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StartObjectBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2StartObjectBehaviorAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("23d2ddab-cfdd-4591-8d7f-2a41f32f9e19")
    public static UML2StartObjectBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartObjectBehaviorAction.canInstantiate(obj))
        	return new UML2StartObjectBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartObjectBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d89e2b30-5e31-4d7f-b14e-a74ade5471ee")
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
        UML2StartObjectBehaviorAction other = (UML2StartObjectBehaviorAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("2151c7f4-3db3-49e8-8bb1-fdcdec8e3131")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("f0ef43e3-851c-4df4-911c-329b89fb1504")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("21cbfedd-7109-4001-9f01-cb7aa17df6ca")
    protected UML2StartObjectBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("fd5f46e7-53d3-4b11-a35d-6fce01797d11")
    public static final class MdaTypes {
        @objid ("44de2fe9-d855-4d36-9c17-8a13f0fa97ad")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e244e412-7b54-4a6e-87ae-28168facad74")
        private static Stereotype MDAASSOCDEP;

        @objid ("5473222d-f1dc-4e35-bc76-ab932db5a871")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0f749915-c6cc-4a83-9e95-60a6731e2ff7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d4d4a0b8-fb19-4b78-bc9e-e04ad77087f8");
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
