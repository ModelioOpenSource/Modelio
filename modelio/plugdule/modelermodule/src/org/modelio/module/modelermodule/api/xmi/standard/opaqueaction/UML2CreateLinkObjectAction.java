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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8f1f797-b904-47b7-a13f-1c46cc7fc8b7")
public class UML2CreateLinkObjectAction {
    @objid ("29e15e3b-5fb6-46eb-bcc3-390d26de1302")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ee9b4690-964a-471a-adf5-2566950e88d9")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5be585a7-2f50-4ffa-8d11-26d27ff1d2d8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> then instantiate a {@link UML2CreateLinkObjectAction} proxy.
     * 
     * @return a {@link UML2CreateLinkObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("3abaa58a-9e9a-400b-bde4-4f8bc3fc3c09")
    public static UML2CreateLinkObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME);
        return UML2CreateLinkObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkObjectAction} proxy or <i>null</i>.
     */
    @objid ("73b212e5-e17d-4aff-b671-511a3404bf1f")
    public static UML2CreateLinkObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkObjectAction.canInstantiate(obj) ? new UML2CreateLinkObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3fe58a88-7b21-4532-a81d-8f1316579cba")
    public static UML2CreateLinkObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkObjectAction.canInstantiate(obj))
        	return new UML2CreateLinkObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("85fc9feb-bb36-447f-aad7-a7aa84080bdc")
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
        UML2CreateLinkObjectAction other = (UML2CreateLinkObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("4872dbbc-bfa1-4930-9c26-8ed437d182d7")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("147adde3-b341-403d-88d7-eae01ac2b63c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("f74c465f-c436-4b2e-8275-39936118a85c")
    protected  UML2CreateLinkObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("310f7147-2f6b-4605-9068-06aabbeaaf88")
    public static final class MdaTypes {
        @objid ("a8373681-ea9b-4a35-813e-468373ec2995")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("46f3ae07-13ae-4d16-b6a9-3a981eaf2e30")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b11d568-49ff-42c1-b58c-4c951bc1bd78")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ad945fe7-ecf4-4546-8d9c-a2dbaed77c32")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f8e58a85-c2fa-11de-8ac8-001302895b2b");
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
