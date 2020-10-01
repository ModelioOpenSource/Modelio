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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReplyAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1072f154-5793-424b-9dbf-2eaa07f6a142")
public class UML2ReplyAction {
    @objid ("b1b55b0c-5890-41c2-9d7f-e0776121021a")
    public static final String STEREOTYPE_NAME = "UML2ReplyAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("23a7ffa6-b8aa-4863-a352-c12250d755b8")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReplyAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReplyAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("852ae60a-ca7c-4663-91af-89f66a1fb87f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReplyAction >> then instantiate a {@link UML2ReplyAction} proxy.
     * 
     * @return a {@link UML2ReplyAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("7dda509a-26d4-4dad-8a9e-bfa19dd9e5c7")
    public static UML2ReplyAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME);
        return UML2ReplyAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReplyAction} proxy or <i>null</i>.
     */
    @objid ("da9c528b-6502-4f1a-8846-b481ea856b1e")
    public static UML2ReplyAction instantiate(OpaqueAction obj) {
        return UML2ReplyAction.canInstantiate(obj) ? new UML2ReplyAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReplyAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bba5ecdb-dd4c-4ce2-8bd7-31d4657b92d3")
    public static UML2ReplyAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReplyAction.canInstantiate(obj))
        	return new UML2ReplyAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bf382871-66c7-463b-8626-1c6fd7f38260")
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
        UML2ReplyAction other = (UML2ReplyAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("fdc0954c-5944-40cc-8a49-ac71642b94d7")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("89087385-8dd1-4140-b846-d1dffd580b78")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4ab1b99f-8ec4-476b-9608-dbcb75215a5d")
    protected UML2ReplyAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e3df1d05-1797-48da-8ef8-fa4d80b22d66")
    public static final class MdaTypes {
        @objid ("b1ea75da-ac57-49e1-9656-e637dfe402cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d2b42008-23bc-41f0-b71d-d27718978922")
        private static Stereotype MDAASSOCDEP;

        @objid ("f8c6bb2f-f416-447d-a3cb-ade2f7ecd9d0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("14a04578-d47a-485a-b762-7bc40e05b7b2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c6a579b6-5d0d-11df-a996-001302895b2b");
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
