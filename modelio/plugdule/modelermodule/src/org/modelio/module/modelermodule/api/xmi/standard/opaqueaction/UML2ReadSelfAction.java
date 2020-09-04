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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadSelfAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("797d5347-a197-414c-84b4-44f2f877b47e")
public class UML2ReadSelfAction {
    @objid ("038a0606-c07c-4e5c-b708-042f12de8008")
    public static final String STEREOTYPE_NAME = "UML2ReadSelfAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("a4393402-69af-4908-915a-38d8188e21c4")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadSelfAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6aeeb75c-4381-40d1-a9b1-898882977eb3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> then instantiate a {@link UML2ReadSelfAction} proxy.
     * 
     * @return a {@link UML2ReadSelfAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("15ac086f-8e88-490d-ba4f-f76ebbd54e1b")
    public static UML2ReadSelfAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME);
        return UML2ReadSelfAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadSelfAction} proxy or <i>null</i>.
     */
    @objid ("45a2e12a-624a-4824-a8c9-b79ced16f4fc")
    public static UML2ReadSelfAction instantiate(OpaqueAction obj) {
        return UML2ReadSelfAction.canInstantiate(obj) ? new UML2ReadSelfAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadSelfAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("57d02610-2105-4624-98e9-0ae22f8452dd")
    public static UML2ReadSelfAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadSelfAction.canInstantiate(obj))
        	return new UML2ReadSelfAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadSelfAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("40fd3c43-c378-464f-ac80-63cbd96dab3a")
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
        UML2ReadSelfAction other = (UML2ReadSelfAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("ff11c4a6-b5ec-4b9c-a56b-61fc6c0fa325")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("d886cc1f-c021-47e8-860e-7ef4981aab62")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("77a0af76-15aa-4794-8a50-cea564c9505d")
    protected UML2ReadSelfAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("9b695724-53ea-403f-aee0-9d75de248162")
    public static final class MdaTypes {
        @objid ("150fd72c-4ff9-4a7f-bcec-37b9f66954e1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ad49732a-d579-4c8a-9e34-709fca766455")
        private static Stereotype MDAASSOCDEP;

        @objid ("2bbc199c-6a9c-486d-b73b-7785ea409ee7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7f5fafdc-b1ac-4f33-b46a-ca6f8facdfba")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "120a35e7-c2fd-11de-8ac8-001302895b2b");
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
