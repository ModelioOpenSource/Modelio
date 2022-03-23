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
package org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
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
 * Proxy class to handle a {@link SendSignalAction} with << UML2BroadcastSignalAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("912daf5b-02b1-4804-a635-9b0de80c403f")
public class UML2BroadcastSignalAction {
    @objid ("e603bb4a-b42e-46a5-8fa6-7d25c1578284")
    public static final String STEREOTYPE_NAME = "UML2BroadcastSignalAction";

    /**
     * The underlying {@link SendSignalAction} represented by this proxy, never null.
     */
    @objid ("39eedab8-5763-4568-a7a8-68ab4788b867")
    protected final SendSignalAction elt;

    /**
     * Tells whether a {@link UML2BroadcastSignalAction proxy} can be instantiated from a {@link MObject} checking it is a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5855ebea-2919-4d47-922c-746c50f4c7c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof SendSignalAction) && ((SendSignalAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> then instantiate a {@link UML2BroadcastSignalAction} proxy.
     * 
     * @return a {@link UML2BroadcastSignalAction} proxy on the created {@link SendSignalAction}.
     */
    @objid ("f9f13343-b422-438c-8221-bc2edb6a47e2")
    public static UML2BroadcastSignalAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.SendSignalAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME);
        return UML2BroadcastSignalAction.instantiate((SendSignalAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a SendSignalAction
     * @return a {@link UML2BroadcastSignalAction} proxy or <i>null</i>.
     */
    @objid ("9aa5935a-3ea4-45ab-b51f-8f1529ec99d4")
    public static UML2BroadcastSignalAction instantiate(SendSignalAction obj) {
        return UML2BroadcastSignalAction.canInstantiate(obj) ? new UML2BroadcastSignalAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link SendSignalAction}
     * @return a {@link UML2BroadcastSignalAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3da6e6e1-d4f8-441a-aaff-321fd87da788")
    public static UML2BroadcastSignalAction safeInstantiate(SendSignalAction obj) throws IllegalArgumentException {
        if (UML2BroadcastSignalAction.canInstantiate(obj))
        	return new UML2BroadcastSignalAction(obj);
        else
        	throw new IllegalArgumentException("UML2BroadcastSignalAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("35c32f32-db0f-4863-b6c6-0e97ba8cdf8f")
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
        UML2BroadcastSignalAction other = (UML2BroadcastSignalAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link SendSignalAction}. 
     * @return the SendSignalAction represented by this proxy, never null.
     */
    @objid ("6fb8e3ac-3cb3-4d20-86e6-4f85f6d9cdcd")
    public SendSignalAction getElement() {
        return this.elt;
    }

    @objid ("8c9d34ec-cf2c-44b6-918c-da53f69e64bb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e2252180-565c-4d74-99b4-5c8e21d49663")
    protected  UML2BroadcastSignalAction(SendSignalAction elt) {
        this.elt = elt;
    }

    @objid ("e18ef05a-6696-4bc4-a862-c1d144cb1230")
    public static final class MdaTypes {
        @objid ("96ee6a89-dd42-4034-b724-766f026c2846")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d3c2d16d-404b-4604-9490-63328b3b2941")
        private static Stereotype MDAASSOCDEP;

        @objid ("21a7d739-709a-4588-863d-b4cff16cdac8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("447d27ca-7b74-42c5-aaf8-9f015d87f032")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "edc05471-5d08-11df-a996-001302895b2b");
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
