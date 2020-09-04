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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("369efe96-0462-4c40-b76c-8ace8f356825")
public class UML2ReadVariableAction {
    @objid ("e157b072-579e-4ee1-a3b7-f5a2b3b35f65")
    public static final String STEREOTYPE_NAME = "UML2ReadVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("10c93bd6-4455-42b6-bcbe-180003e44768")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0d1c431c-4633-477e-99ef-248debfd9b35")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> then instantiate a {@link UML2ReadVariableAction} proxy.
     * 
     * @return a {@link UML2ReadVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("c82b787c-fdd9-4796-a0ca-31574a51b691")
    public static UML2ReadVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME);
        return UML2ReadVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadVariableAction} proxy or <i>null</i>.
     */
    @objid ("21dd8745-54fe-4c0b-b656-9289d2f87d93")
    public static UML2ReadVariableAction instantiate(OpaqueAction obj) {
        return UML2ReadVariableAction.canInstantiate(obj) ? new UML2ReadVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0232e448-6581-49b7-aef8-1b16705ad687")
    public static UML2ReadVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadVariableAction.canInstantiate(obj))
        	return new UML2ReadVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("45daf337-06da-4306-a833-362dedbece75")
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
        UML2ReadVariableAction other = (UML2ReadVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("5577af74-a4ca-40e6-95de-94c6229aa848")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("16a8046c-6e5b-4273-bf5f-fb34bc7bd2ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7c52b689-a9dc-462d-95ec-f2ef05ba2a6b")
    protected UML2ReadVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("d499b2f6-8b56-4a50-a4f2-9b0acefb27f3")
    public static final class MdaTypes {
        @objid ("548c3156-1497-42e8-a99a-8ae347097ac0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a9c9a381-a793-415d-8595-bd472fbc8df7")
        private static Stereotype MDAASSOCDEP;

        @objid ("2356e851-9307-4c53-b38c-2265b8007a3a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("30193c42-e2db-45bf-84e3-56600e8f9d6f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1abd18db-c2fd-11de-8ac8-001302895b2b");
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
