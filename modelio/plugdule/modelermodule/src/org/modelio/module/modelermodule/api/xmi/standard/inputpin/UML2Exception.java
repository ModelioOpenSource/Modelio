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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("280f1bea-297f-47f4-b6d6-5ab646b57289")
public class UML2Exception {
    @objid ("8cce03f4-187c-4d61-bc26-d7129d0d67de")
    public static final String STEREOTYPE_NAME = "UML2Exception";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("f1b1f5cf-fc25-42d0-a898-fd9dc2ab80a8")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Exception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("58016f52-e1fc-45c0-8bc8-601d927f7c60")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Exception >> then instantiate a {@link UML2Exception} proxy.
     * 
     * @return a {@link UML2Exception} proxy on the created {@link InputPin}.
     */
    @objid ("565d6a01-0093-48e6-9597-1c62ca4a77e1")
    public static UML2Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME);
        return UML2Exception.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Exception} proxy or <i>null</i>.
     */
    @objid ("1ae87bc8-9023-48e7-9a7a-ba1316d64cc1")
    public static UML2Exception instantiate(InputPin obj) {
        return UML2Exception.canInstantiate(obj) ? new UML2Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e3aeec18-3ca6-402e-a15a-97c1c56c0450")
    public static UML2Exception safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Exception.canInstantiate(obj))
        	return new UML2Exception(obj);
        else
        	throw new IllegalArgumentException("UML2Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1d90829c-8d54-474c-878e-5a282764a2ba")
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
        UML2Exception other = (UML2Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("ef985dd3-734c-469c-99e6-f1c9a4e1ea43")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("34f0fa77-4fd6-4380-96b9-c06a9149f87e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("188c27f7-f738-4f38-90aa-bed539baef30")
    protected  UML2Exception(InputPin elt) {
        this.elt = elt;
    }

    @objid ("07543495-a7b1-4345-accd-b02a81db07a5")
    public static final class MdaTypes {
        @objid ("c9b115c6-f255-4d76-b7a6-047544b21b95")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5c7a362e-efc6-4cfd-a8ca-5ed849b0256c")
        private static Stereotype MDAASSOCDEP;

        @objid ("dbc19f6f-0127-4470-961b-f8dc105347d6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ecf52e9-ffd1-4e9b-8e15-b8eeefe00f75")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b966e108-5fbe-4990-b7cf-94d258a5c3ff");
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
