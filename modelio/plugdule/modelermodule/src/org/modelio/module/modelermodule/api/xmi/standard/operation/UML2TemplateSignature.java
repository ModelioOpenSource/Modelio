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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2TemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c64395f8-85c2-42fe-9a80-f23505af725e")
public class UML2TemplateSignature {
    @objid ("09228e69-269c-4f5f-9307-a7bd625830c7")
    public static final String STEREOTYPE_NAME = "UML2TemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("3634094f-0c6c-4cba-81ca-517037dd3bf3")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2TemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2TemplateSignature >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7558c28c-22eb-4184-8c69-22f0b954ded4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2TemplateSignature >> then instantiate a {@link UML2TemplateSignature} proxy.
     * 
     * @return a {@link UML2TemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("1621d450-f861-4a92-b433-29ad5abaab13")
    public static UML2TemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME);
        return UML2TemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2TemplateSignature} proxy or <i>null</i>.
     */
    @objid ("168fc902-8fcf-4201-996c-1b70815b8ed3")
    public static UML2TemplateSignature instantiate(Operation obj) {
        return UML2TemplateSignature.canInstantiate(obj) ? new UML2TemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2TemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c9eec4a1-65da-4a83-a375-2994b9f4e7c9")
    public static UML2TemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2TemplateSignature.canInstantiate(obj))
        	return new UML2TemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2TemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fcb67990-5db4-4741-9a49-e28164c44b04")
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
        UML2TemplateSignature other = (UML2TemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("3aada9b1-3cac-47dd-ae9d-350f491731ee")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("dca3493a-6107-42ce-a475-d6b11e9e4543")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2a6c6dda-7387-4e2c-b286-474997d4d697")
    protected UML2TemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("bd5513f3-a00c-4eb5-a954-b968c48149ed")
    public static final class MdaTypes {
        @objid ("48fe98ed-3f79-4645-b659-4587d525d50d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d1906539-8583-46ba-a3d8-660433dd79b1")
        private static Stereotype MDAASSOCDEP;

        @objid ("eafb113f-ad7b-428f-8d6e-623c939a2ae3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e9cae42d-4401-439e-a21c-590f7f40de27")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "554cb8bb-5d0e-11df-a996-001302895b2b");
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
