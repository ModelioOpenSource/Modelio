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
package org.modelio.module.modelermodule.api.xmi.standard.templateparameter;

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
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link TemplateParameter} with << UML2ClassifierTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7d06e516-f1b3-4177-aff3-431439159472")
public class UML2ClassifierTemplateParameter {
    @objid ("9486e7e8-1052-40de-bade-913abf083745")
    public static final String STEREOTYPE_NAME = "UML2ClassifierTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("a5d44abf-b9e2-458c-8953-d9cf9a022f17")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ClassifierTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4ff42603-2571-4599-b581-0e8ecb409b9d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> then instantiate a {@link UML2ClassifierTemplateParameter} proxy.
     * 
     * @return a {@link UML2ClassifierTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("61d52c31-be77-46c3-b85f-1df933331073")
    public static UML2ClassifierTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME);
        return UML2ClassifierTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ClassifierTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("c9e902de-acde-4894-a794-552b9b91a380")
    public static UML2ClassifierTemplateParameter instantiate(TemplateParameter obj) {
        return UML2ClassifierTemplateParameter.canInstantiate(obj) ? new UML2ClassifierTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link TemplateParameter}
     * @return a {@link UML2ClassifierTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("043d51bf-de4b-4d8b-ab34-ecc08ea75e7a")
    public static UML2ClassifierTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ClassifierTemplateParameter.canInstantiate(obj))
        	return new UML2ClassifierTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("240113c8-4e97-42e2-8f66-e8257c9d7499")
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
        UML2ClassifierTemplateParameter other = (UML2ClassifierTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link TemplateParameter}. 
     * @return the TemplateParameter represented by this proxy, never null.
     */
    @objid ("6033bccb-595f-4f92-a0b1-9f92ee5917fd")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("bb647d95-3fcf-410c-aa4a-813d35761eba")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("37d8b419-459e-45ed-9b09-41ec5a7a65c2")
    protected  UML2ClassifierTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("7d56bd81-07e4-4088-ae68-252f60dbb3fb")
    public static final class MdaTypes {
        @objid ("3f125c5f-8564-4ee1-9012-968b0cc2e027")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1e702f32-288b-4373-82f4-15ceb80245c0")
        private static Stereotype MDAASSOCDEP;

        @objid ("80fbe6f5-ed8c-425a-ad3d-8b52ee773471")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ea6ddf2-f296-4b6e-839a-eb9391a6e2f1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4a427283-5d09-11df-a996-001302895b2b");
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
