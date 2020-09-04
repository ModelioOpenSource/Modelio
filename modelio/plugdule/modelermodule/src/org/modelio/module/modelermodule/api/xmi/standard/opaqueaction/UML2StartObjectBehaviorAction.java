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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartObjectBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("25009208-0fa9-4e7f-90cf-c52ca583397c")
public class UML2StartObjectBehaviorAction {
    @objid ("c4f6e791-e70c-4325-92be-7584c07eeab2")
    public static final String STEREOTYPE_NAME = "UML2StartObjectBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("3a0292fa-c918-4482-9fcf-8858d6d2124f")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartObjectBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1d371433-3564-4ce8-9758-7b2fef2fef17")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> then instantiate a {@link UML2StartObjectBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartObjectBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("148f3ed9-66cf-4c59-b578-6f9e7cf7e6a0")
    public static UML2StartObjectBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME);
        return UML2StartObjectBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartObjectBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartObjectBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("ee2876cf-af55-4505-944b-68e29696aef8")
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
    @objid ("9d8e300d-66fa-4fb2-aa55-149df79a1b5b")
    public static UML2StartObjectBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartObjectBehaviorAction.canInstantiate(obj))
        	return new UML2StartObjectBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartObjectBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("68df088e-1ba7-4be2-bd9a-9092610d8675")
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
    @objid ("ec5fac14-f0af-4c3e-aeb0-61145fa26230")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c60e9ab2-11ff-43ce-92fc-5a43b57c1b02")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dd2e90f1-489d-4327-8bc8-0cf7f548b0a3")
    protected UML2StartObjectBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("fd5f46e7-53d3-4b11-a35d-6fce01797d11")
    public static final class MdaTypes {
        @objid ("11aa69cf-feb6-4b30-bc0f-fb4fffdd1aab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8587af5c-ac41-422c-bc76-7e4625056bb5")
        private static Stereotype MDAASSOCDEP;

        @objid ("9b5e675a-195e-475f-985f-e195a3980522")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e122d448-1fb8-4b2b-8dce-9bc3f45dea70")
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
