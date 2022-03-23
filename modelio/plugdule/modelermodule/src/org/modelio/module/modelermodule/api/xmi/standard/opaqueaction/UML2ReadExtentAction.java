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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadExtentAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f44efdd0-3ec1-476f-899a-8869358862ec")
public class UML2ReadExtentAction {
    @objid ("8a653ea2-c0a1-49a6-8c6a-8f4d5867df74")
    public static final String STEREOTYPE_NAME = "UML2ReadExtentAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("a0c7d254-ec43-45fb-9fe3-7002de418d00")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadExtentAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("42fe676f-3875-4ed9-b9ba-0aa24bb09a49")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> then instantiate a {@link UML2ReadExtentAction} proxy.
     * 
     * @return a {@link UML2ReadExtentAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("adb446d5-e862-4434-9628-92a33506303d")
    public static UML2ReadExtentAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME);
        return UML2ReadExtentAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadExtentAction} proxy or <i>null</i>.
     */
    @objid ("0bcd150e-9c67-4664-a2dc-3175b959bccf")
    public static UML2ReadExtentAction instantiate(OpaqueAction obj) {
        return UML2ReadExtentAction.canInstantiate(obj) ? new UML2ReadExtentAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadExtentAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d1113d89-2f0f-45be-8001-dc6e579dfcb3")
    public static UML2ReadExtentAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadExtentAction.canInstantiate(obj))
        	return new UML2ReadExtentAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadExtentAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4783eba8-dd2d-4d12-87dd-89ce6b23840f")
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
        UML2ReadExtentAction other = (UML2ReadExtentAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e39df9e9-985f-4c34-b3f5-3c943ae84283")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("8ee757ef-d288-41a8-80c1-22124e7c63bb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("300506a1-e9c1-4bf8-84f9-9e81e70126c8")
    protected  UML2ReadExtentAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5b0571b6-d970-4ab3-9d56-4b8aba8bc9d6")
    public static final class MdaTypes {
        @objid ("6ccb0140-4061-4f8e-925b-c69a0f87c67e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("08f5ac97-82b2-4877-afa3-7184c766640b")
        private static Stereotype MDAASSOCDEP;

        @objid ("e7fd07e3-0c85-4407-b13e-2aeab08681c9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3fdfc93f-9ea6-47c1-ae8c-a4255022347d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c4b3add1-c2f9-11de-8ac8-001302895b2b");
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
