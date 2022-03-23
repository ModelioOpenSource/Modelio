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
 * Proxy class to handle a {@link InputPin} with << UML2Second >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b741933-97e4-4335-8b95-a8f3c5ea9155")
public class UML2Second {
    @objid ("dfcf45ee-169b-47e2-90c6-cc2435fd723b")
    public static final String STEREOTYPE_NAME = "UML2Second";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("db68e082-aa7b-466d-9074-276521495e44")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Second proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Second >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1a270c58-2f9e-4500-b591-37d6f41b4c09")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Second >> then instantiate a {@link UML2Second} proxy.
     * 
     * @return a {@link UML2Second} proxy on the created {@link InputPin}.
     */
    @objid ("b5038207-9424-455e-8377-e18b321638c0")
    public static UML2Second create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME);
        return UML2Second.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Second} proxy or <i>null</i>.
     */
    @objid ("2003aaa3-f03e-4fba-8be4-5b40573f4a2e")
    public static UML2Second instantiate(InputPin obj) {
        return UML2Second.canInstantiate(obj) ? new UML2Second(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Second} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("94c26668-67c7-4ea0-b784-56631c649cab")
    public static UML2Second safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Second.canInstantiate(obj))
        	return new UML2Second(obj);
        else
        	throw new IllegalArgumentException("UML2Second: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("44b44b3e-c1dc-485b-9678-6070ceeabeef")
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
        UML2Second other = (UML2Second) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("abf3e376-c3e4-4f21-9d6a-d162311b897a")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("8fb2b627-c035-4fea-bed7-ba70e4f6b771")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("5d126106-e358-4fa0-b6d5-96a9e9b2a7ca")
    protected  UML2Second(InputPin elt) {
        this.elt = elt;
    }

    @objid ("12a78492-d0ac-49ef-8c8e-e162f97b0e1f")
    public static final class MdaTypes {
        @objid ("f06d2a07-ec18-4a44-a03c-c554cc8219c2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("25e5b7e5-4a5a-4a7c-9497-91041e23dd6c")
        private static Stereotype MDAASSOCDEP;

        @objid ("74729e5d-5c03-416b-aa32-7ee0792e307f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("500dd064-ca52-4790-8a84-005a2ee3352e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3f03c5f1-c308-11de-8ac8-001302895b2b");
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
