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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("369efe96-0462-4c40-b76c-8ace8f356825")
public class UML2ReadVariableAction {
    @objid ("0c5e374f-42dd-4a46-8cc8-e2a35c6a79fc")
    public static final String STEREOTYPE_NAME = "UML2ReadVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("cee9ad99-5807-4c02-bf8f-7bfdc122b336")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("923383eb-c073-447d-94b4-56e1b0b0cd96")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> then instantiate a {@link UML2ReadVariableAction} proxy.
     * 
     * @return a {@link UML2ReadVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8581427b-b269-4b6b-9cde-66e1f509486f")
    public static UML2ReadVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME);
        return UML2ReadVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadVariableAction} proxy or <i>null</i>.
     */
    @objid ("c2e86cf1-e97d-44cb-94ee-9619049c73bd")
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
    @objid ("9001a676-9bb1-4848-9678-5c5ab202e865")
    public static UML2ReadVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadVariableAction.canInstantiate(obj))
        	return new UML2ReadVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b682512e-e261-4b2c-91b5-1932d206f25f")
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
    @objid ("5645d760-d1c0-45fc-a3f3-568baecaaa20")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c13fe55a-f8de-48ce-828c-5f2b9a47190e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("90894f27-53d9-46eb-94b6-d335b6739dd5")
    protected  UML2ReadVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("d499b2f6-8b56-4a50-a4f2-9b0acefb27f3")
    public static final class MdaTypes {
        @objid ("58aa240b-908f-48a5-8f42-00b2cbfcbb52")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("cd0497b1-bdd1-4556-88d3-0b3c16f305a5")
        private static Stereotype MDAASSOCDEP;

        @objid ("03b183da-fb91-4010-9326-18dc1ff54211")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f9c6131a-0ea7-4a7e-8dd4-490970c5e798")
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
