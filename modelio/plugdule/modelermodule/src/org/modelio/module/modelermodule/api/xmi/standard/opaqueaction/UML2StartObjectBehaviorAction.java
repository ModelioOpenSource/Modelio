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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartObjectBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("25009208-0fa9-4e7f-90cf-c52ca583397c")
public class UML2StartObjectBehaviorAction {
    @objid ("bf7d8c41-9bb1-4025-b6cf-a01afea86bb6")
    public static final String STEREOTYPE_NAME = "UML2StartObjectBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("02465c2e-87d3-46c4-a12b-2bb48abee218")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartObjectBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0ac5d0f1-4af6-4107-90b0-2024797ba83a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> then instantiate a {@link UML2StartObjectBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartObjectBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("ffac6012-d157-4de8-8e2b-be6ee5d0f258")
    public static UML2StartObjectBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
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
    @objid ("bca43a11-b92c-4a67-9a7d-855d81ae6d6c")
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
    @objid ("f00c9795-d17f-4fb6-9955-29dc8d73c636")
    public static UML2StartObjectBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartObjectBehaviorAction.canInstantiate(obj))
        	return new UML2StartObjectBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartObjectBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("901476ed-5126-4117-9f89-d4eb2bfee324")
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
    @objid ("6f3193a4-7d1d-4bf7-bc75-a49de7f52813")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("a90c5250-c607-4adc-b77b-229499ebbdc0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e469efd2-2f56-4978-a147-f36922f0989c")
    protected  UML2StartObjectBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("fd5f46e7-53d3-4b11-a35d-6fce01797d11")
    public static final class MdaTypes {
        @objid ("f09c18da-031f-4a73-a585-d79b44bd88bd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f599570e-74d0-44ba-8f32-b2544392423e")
        private static Stereotype MDAASSOCDEP;

        @objid ("37f5734e-b7ff-4d0a-9636-2c3fb0c436d4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d125a93b-cf18-440a-87f1-786ceca006a6")
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
