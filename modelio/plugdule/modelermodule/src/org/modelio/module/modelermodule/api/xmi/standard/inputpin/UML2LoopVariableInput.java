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
 * Proxy class to handle a {@link InputPin} with << UML2LoopVariableInput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6a8233a5-9eb3-40a5-83aa-e84ebe4d59b4")
public class UML2LoopVariableInput {
    @objid ("32649d31-8b3a-4cb2-9658-e9ab25d328be")
    public static final String STEREOTYPE_NAME = "UML2LoopVariableInput";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7fbc74ac-3b1d-442b-90e6-f47a2b6fb86a")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2LoopVariableInput proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2LoopVariableInput >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a0d4dec1-cdbe-480e-9516-e653ec9981fb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2LoopVariableInput >> then instantiate a {@link UML2LoopVariableInput} proxy.
     * 
     * @return a {@link UML2LoopVariableInput} proxy on the created {@link InputPin}.
     */
    @objid ("e354cacb-936f-4095-add2-2a415a1fdf31")
    public static UML2LoopVariableInput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME);
        return UML2LoopVariableInput.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2LoopVariableInput} proxy or <i>null</i>.
     */
    @objid ("67653730-587a-4c73-90e3-62c27c3ab31c")
    public static UML2LoopVariableInput instantiate(InputPin obj) {
        return UML2LoopVariableInput.canInstantiate(obj) ? new UML2LoopVariableInput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2LoopVariableInput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8f0d5108-71c2-4950-a70c-85d5f022fc02")
    public static UML2LoopVariableInput safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2LoopVariableInput.canInstantiate(obj))
        	return new UML2LoopVariableInput(obj);
        else
        	throw new IllegalArgumentException("UML2LoopVariableInput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("359915fd-0866-4383-8fd2-f22088c4382d")
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
        UML2LoopVariableInput other = (UML2LoopVariableInput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("a457748c-47f4-4f25-8076-291ac1f16620")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d95ce2eb-40ef-4abf-b384-dc62953ccbf5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("ef2df497-6f15-49ba-962c-d4705d8b199d")
    protected  UML2LoopVariableInput(InputPin elt) {
        this.elt = elt;
    }

    @objid ("2bccab18-b4c5-42a8-a3d6-068429925e3a")
    public static final class MdaTypes {
        @objid ("c91a64a9-12e7-4054-8cc7-8b22f19de6a1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1b58792d-9de7-427e-b2c6-acd51ad98dd8")
        private static Stereotype MDAASSOCDEP;

        @objid ("eacfbf77-7b2c-416f-983e-33fb4f638941")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f8d1e5ce-2242-4d0e-ab5c-38adac19ec26")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "7a7f049a-6b5f-4db9-9f79-8e327ca90297");
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
