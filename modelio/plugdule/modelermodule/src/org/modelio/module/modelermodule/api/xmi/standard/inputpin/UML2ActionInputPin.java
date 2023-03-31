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
 * Proxy class to handle a {@link InputPin} with << UML2ActionInputPin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3bedd0c8-c338-4af8-95d9-6c58807f3ac3")
public class UML2ActionInputPin {
    @objid ("8f322552-74af-4382-a2e5-e9f9af312f6e")
    public static final String STEREOTYPE_NAME = "UML2ActionInputPin";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("546781c4-2be8-4b5d-b7b4-69ad664fb658")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ActionInputPin proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ActionInputPin >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4cad9afc-21e6-4b7e-990b-8aa990a6dfcb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ActionInputPin >> then instantiate a {@link UML2ActionInputPin} proxy.
     * 
     * @return a {@link UML2ActionInputPin} proxy on the created {@link InputPin}.
     */
    @objid ("94dd03a8-b468-4166-a725-0891a5583e9c")
    public static UML2ActionInputPin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME);
        return UML2ActionInputPin.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ActionInputPin} proxy or <i>null</i>.
     */
    @objid ("e3e85a3e-85d4-4c59-8582-66ea2690b2cf")
    public static UML2ActionInputPin instantiate(InputPin obj) {
        return UML2ActionInputPin.canInstantiate(obj) ? new UML2ActionInputPin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ActionInputPin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3d40bc15-6633-402d-80ea-4375da204969")
    public static UML2ActionInputPin safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ActionInputPin.canInstantiate(obj))
        	return new UML2ActionInputPin(obj);
        else
        	throw new IllegalArgumentException("UML2ActionInputPin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("923f3609-18b9-4b72-8077-d61ef9bc5a15")
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
        UML2ActionInputPin other = (UML2ActionInputPin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("bd9a77df-83d8-4b15-96a2-d482f58c05fc")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("c6abb6b3-0f83-4e32-bb6d-3ad91f8c44bf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8e48ffdb-eb2f-4d7d-b7cd-3e2c173b00b1")
    protected  UML2ActionInputPin(InputPin elt) {
        this.elt = elt;
    }

    @objid ("dc5d92d9-0b36-408d-b559-7f7dd32d402b")
    public static final class MdaTypes {
        @objid ("a032d07c-1045-49f5-95b6-0a1138db1207")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fa7360dc-2bec-4119-84a4-f2215ae82532")
        private static Stereotype MDAASSOCDEP;

        @objid ("923b0367-a0f5-4ec9-bf02-b01d32cfa78b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3719a6a0-3ffa-4ead-9bbe-dd684ee00bf9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0bd72298-5d08-11df-a996-001302895b2b");
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
