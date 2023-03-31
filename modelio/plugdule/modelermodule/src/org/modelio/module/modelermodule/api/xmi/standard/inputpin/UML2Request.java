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
 * Proxy class to handle a {@link InputPin} with << UML2Request >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1e13d871-b6e7-425b-b323-3853d5ba793a")
public class UML2Request {
    @objid ("faf57e18-8662-454b-b5f9-7c095b836896")
    public static final String STEREOTYPE_NAME = "UML2Request";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("22c491f3-b1bf-4077-9b0d-00ace5c19b84")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Request proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Request >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("683a5749-0b97-45d5-930a-8c831d817ed6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Request >> then instantiate a {@link UML2Request} proxy.
     * 
     * @return a {@link UML2Request} proxy on the created {@link InputPin}.
     */
    @objid ("4599584f-e1e3-494a-89b1-c50907313225")
    public static UML2Request create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME);
        return UML2Request.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Request} proxy or <i>null</i>.
     */
    @objid ("6ecf7454-a446-4121-9524-acb29b72c7b2")
    public static UML2Request instantiate(InputPin obj) {
        return UML2Request.canInstantiate(obj) ? new UML2Request(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Request} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bda45f3d-a39f-4087-8dd1-f3c5f9a7aeab")
    public static UML2Request safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Request.canInstantiate(obj))
        	return new UML2Request(obj);
        else
        	throw new IllegalArgumentException("UML2Request: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6c95a320-2d11-44b8-9be4-c93d2ca708b7")
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
        UML2Request other = (UML2Request) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("c60008d1-0a78-48b8-91e5-e2b4ae70d726")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("fd2e8b0b-003f-403b-8d39-5ec676ca57b6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("35a4251d-f276-4b86-afae-c4ba2bcefe2f")
    protected  UML2Request(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3e4f51a5-49eb-42d4-9cf5-f8ecc89b07e5")
    public static final class MdaTypes {
        @objid ("06434411-b9a3-4a4e-8e56-94c77517d70e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7cfe4567-946c-495c-8e60-01c56054f59a")
        private static Stereotype MDAASSOCDEP;

        @objid ("0f8f434d-e817-4519-aace-f816fd153745")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a416d10f-c32c-4f87-867c-62b670345024")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e0ff915d-8d3f-4da8-84be-5aed471a16c5");
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
