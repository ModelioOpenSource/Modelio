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
 * Proxy class to handle a {@link InputPin} with << UML2First >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1d8bbfa9-4d67-49bd-9cf2-8020cc4981d7")
public class UML2First {
    @objid ("6c75320c-f040-4061-80d5-010bb9b6c65b")
    public static final String STEREOTYPE_NAME = "UML2First";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("bec344f1-5b66-46fd-be11-2234baa10729")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2First proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2First >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e53bcd5c-738f-4791-82ef-bc410d146d0d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2First >> then instantiate a {@link UML2First} proxy.
     * 
     * @return a {@link UML2First} proxy on the created {@link InputPin}.
     */
    @objid ("c8c648fe-b0a9-408c-a505-e12caec5654e")
    public static UML2First create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME);
        return UML2First.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2First} proxy or <i>null</i>.
     */
    @objid ("a605634b-4d36-4b51-a4e4-0e87a79be8b9")
    public static UML2First instantiate(InputPin obj) {
        return UML2First.canInstantiate(obj) ? new UML2First(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2First} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ee054c2e-c9a4-4c29-a582-65a4b4dd4baf")
    public static UML2First safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2First.canInstantiate(obj))
        	return new UML2First(obj);
        else
        	throw new IllegalArgumentException("UML2First: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f704740e-a088-48b8-bae1-c6341380482c")
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
        UML2First other = (UML2First) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("0d9c9e84-e1f9-4c2a-9efa-204d2c7c0dbe")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("8bf5b4a0-baa7-4bda-8af1-f5ac002c88fb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("910148c0-a8b0-477a-a9ac-77d8cefb4c9b")
    protected  UML2First(InputPin elt) {
        this.elt = elt;
    }

    @objid ("231fcc4f-1387-4364-b0db-960af406ba29")
    public static final class MdaTypes {
        @objid ("8993c205-22d0-486d-a409-2995202a5f47")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("606e42aa-2474-4f8b-8b06-8afba78f5510")
        private static Stereotype MDAASSOCDEP;

        @objid ("6ddd9d45-5ae2-42d9-8c7e-1382be7de4a1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c642ec41-d3df-4239-89f2-9676f1393b4c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "37d0688a-c308-11de-8ac8-001302895b2b");
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
