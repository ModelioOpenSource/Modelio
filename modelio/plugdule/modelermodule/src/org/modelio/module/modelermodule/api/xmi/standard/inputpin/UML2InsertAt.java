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
 * Proxy class to handle a {@link InputPin} with << UML2InsertAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42686ded-7b6b-453d-ab60-76a6e2b6d99a")
public class UML2InsertAt {
    @objid ("c4f3f24d-9ee9-4b85-a7eb-0dc05c3cddad")
    public static final String STEREOTYPE_NAME = "UML2InsertAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("914e0790-127a-4089-8e63-ee0678bce780")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InsertAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InsertAt >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3f38d1d1-98a9-4141-9857-e9fd0fa3db50")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InsertAt >> then instantiate a {@link UML2InsertAt} proxy.
     * 
     * @return a {@link UML2InsertAt} proxy on the created {@link InputPin}.
     */
    @objid ("e820ffe8-9398-45a7-b5f5-9749040a4dac")
    public static UML2InsertAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME);
        return UML2InsertAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InsertAt} proxy or <i>null</i>.
     */
    @objid ("f01f74a0-bdbf-4a32-8003-2e09163fde9b")
    public static UML2InsertAt instantiate(InputPin obj) {
        return UML2InsertAt.canInstantiate(obj) ? new UML2InsertAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InsertAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b5a73a8a-16a4-4e29-bbf3-2bec5b09925d")
    public static UML2InsertAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InsertAt.canInstantiate(obj))
        	return new UML2InsertAt(obj);
        else
        	throw new IllegalArgumentException("UML2InsertAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("45233044-a283-403b-99da-096135c6fd41")
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
        UML2InsertAt other = (UML2InsertAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("d37f26cd-59ab-4816-994f-9a6b0daaa864")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("792b6150-854e-4223-85bf-e6f2aa6cbd47")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("723d64d6-2267-438f-8520-7d6898d251a3")
    protected  UML2InsertAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3488919b-4d36-47de-bef3-5b5290d81e53")
    public static final class MdaTypes {
        @objid ("3e9930df-41bc-4ead-b2a1-50bcc4c1b65a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("65ffaf39-ea49-41aa-af08-5d1d16d4c690")
        private static Stereotype MDAASSOCDEP;

        @objid ("626d5ecb-261b-42b2-a26f-45d4e26927db")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("392edae5-0f9e-4dcf-8956-8fbca2d39b90")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ec22d8ff-de86-11de-905b-001302895b2b");
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
