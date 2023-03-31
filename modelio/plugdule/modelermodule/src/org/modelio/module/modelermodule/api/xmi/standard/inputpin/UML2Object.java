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
 * Proxy class to handle a {@link InputPin} with << UML2Object >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4d9f0770-42a1-41ca-b31a-a5051fa170da")
public class UML2Object {
    @objid ("ba354846-c00a-44d0-81fd-70fbbc46c7e6")
    public static final String STEREOTYPE_NAME = "UML2Object";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("dbc8b42b-7b39-4032-a3b8-257dce6775d5")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Object proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Object >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0384318d-e7bf-453b-b22b-d2f99c894596")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Object >> then instantiate a {@link UML2Object} proxy.
     * 
     * @return a {@link UML2Object} proxy on the created {@link InputPin}.
     */
    @objid ("e708dd28-8498-418f-b335-5b87c1754e89")
    public static UML2Object create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME);
        return UML2Object.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Object} proxy or <i>null</i>.
     */
    @objid ("e32ef50e-5b00-4821-a454-25b54064bc62")
    public static UML2Object instantiate(InputPin obj) {
        return UML2Object.canInstantiate(obj) ? new UML2Object(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Object} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("350fa9c3-82db-4691-8afc-6acddde9bf96")
    public static UML2Object safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Object.canInstantiate(obj))
        	return new UML2Object(obj);
        else
        	throw new IllegalArgumentException("UML2Object: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e5ca9725-3099-4b6b-94a7-36870d85e31c")
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
        UML2Object other = (UML2Object) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("f71e4fd0-738d-4fd8-8338-36327589481a")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("c3b51893-600f-46f9-94e9-fe9a29be57e9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8bd3bad6-5c6a-46de-b8af-d73edd21804f")
    protected  UML2Object(InputPin elt) {
        this.elt = elt;
    }

    @objid ("a5f36406-a8bc-4203-87a4-7ada95b3bbc6")
    public static final class MdaTypes {
        @objid ("a97ac458-0591-4b9d-8ac4-1415c64efebd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d7a0ad64-837c-4f35-bab3-ceb19bfb7191")
        private static Stereotype MDAASSOCDEP;

        @objid ("e52663a8-e121-4520-98a1-bd05857fd60f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("af70aae5-4c42-4bcc-af2c-90eb34403aa7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f82bed81-afcc-41fc-b014-b9ce92bb5377");
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
