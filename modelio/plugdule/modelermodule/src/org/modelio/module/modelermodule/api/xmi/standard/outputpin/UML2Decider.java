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
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Decider >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3b0b1cd2-7362-4d64-8798-cb185dd04589")
public class UML2Decider {
    @objid ("97d40f10-de00-4430-b3f7-56c3b775d6af")
    public static final String STEREOTYPE_NAME = "UML2Decider";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("9c823626-23d2-4546-9044-896e84ea532e")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Decider proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Decider >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9040e122-7e6c-44ff-ac5b-4be18df699bc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Decider >> then instantiate a {@link UML2Decider} proxy.
     * 
     * @return a {@link UML2Decider} proxy on the created {@link OutputPin}.
     */
    @objid ("179c8574-5a4b-4c4e-9cfc-8fa5c35e9b6f")
    public static UML2Decider create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME);
        return UML2Decider.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Decider} proxy or <i>null</i>.
     */
    @objid ("e79c1fcd-3a23-43ff-8df4-57b5f3444871")
    public static UML2Decider instantiate(OutputPin obj) {
        return UML2Decider.canInstantiate(obj) ? new UML2Decider(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Decider} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cc2794ee-43fa-4d0f-a1e6-b302542778a0")
    public static UML2Decider safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Decider.canInstantiate(obj))
        	return new UML2Decider(obj);
        else
        	throw new IllegalArgumentException("UML2Decider: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c40dec23-e981-41e2-a409-ddc58a903ebb")
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
        UML2Decider other = (UML2Decider) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("1efe69d8-10e4-49d1-9d74-b7b6a87db00c")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("094f8808-dfdc-45e8-b63d-a4ccdfeabf0c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("38b07437-7212-48ad-94e4-9a8133a0eb11")
    protected  UML2Decider(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("31eec6ec-04ba-451c-a15c-cacea4d48a26")
    public static final class MdaTypes {
        @objid ("354830d4-9539-4941-b0ca-55241ce4a106")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d93112f6-4898-4a7d-a611-8ad23c16f01f")
        private static Stereotype MDAASSOCDEP;

        @objid ("ca430375-95ba-4b04-b6c1-38bc399f729b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5ab217e0-6886-42a5-82fa-2fb6a00580c2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "27ac6d48-32c8-11e0-91f3-0027103f347c");
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
