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
    @objid ("89b0b85b-0eaa-4020-ba08-851d1e988e5f")
    public static final String STEREOTYPE_NAME = "UML2BroadcastSignalAction";

    /**
     * The underlying {@link SendSignalAction} represented by this proxy, never null.
     */
    @objid ("61efb907-b50c-4cf7-9ef3-16c74918c51a")
    protected final SendSignalAction elt;

    /**
     * Tells whether a {@link UML2BroadcastSignalAction proxy} can be instantiated from a {@link MObject} checking it is a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("82f6da7e-2045-4939-afa7-1e7283658a9d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof SendSignalAction) && ((SendSignalAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> then instantiate a {@link UML2BroadcastSignalAction} proxy.
     * 
     * @return a {@link UML2BroadcastSignalAction} proxy on the created {@link SendSignalAction}.
     */
    @objid ("34f0184c-f1d8-4575-9090-2309beeb7ce9")
    public static UML2BroadcastSignalAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("SendSignalAction");
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
    @objid ("d55df14a-97a8-4be0-8a9e-cc0830a05895")
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
    @objid ("df61f6fc-3a98-4743-a460-c55489749cb1")
    public static UML2BroadcastSignalAction safeInstantiate(SendSignalAction obj) throws IllegalArgumentException {
        if (UML2BroadcastSignalAction.canInstantiate(obj))
        	return new UML2BroadcastSignalAction(obj);
        else
        	throw new IllegalArgumentException("UML2BroadcastSignalAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("932972c6-e5f6-48e6-ab2e-32617e3634af")
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
    @objid ("7754c796-1d00-48a5-97ec-01a1cc657e76")
    public SendSignalAction getElement() {
        return this.elt;
    }

    @objid ("3304fd20-4693-44f6-b348-971489dedc92")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("db20fafc-ae2f-4ab2-b490-c506183d76ec")
    protected UML2BroadcastSignalAction(SendSignalAction elt) {
        this.elt = elt;
    }

    @objid ("e18ef05a-6696-4bc4-a862-c1d144cb1230")
    public static final class MdaTypes {
        @objid ("c085b2f2-0d07-45b3-9cc7-817983153f00")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9df3dc93-63bf-4179-90c6-47dcf5396ec1")
        private static Stereotype MDAASSOCDEP;

        @objid ("63165be4-8a40-4c2b-8420-cf44b5a1118c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ac26623d-e47f-41f8-93e9-23140b562fae")
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
