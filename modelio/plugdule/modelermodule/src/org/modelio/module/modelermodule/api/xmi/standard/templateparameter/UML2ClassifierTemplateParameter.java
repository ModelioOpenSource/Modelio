/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("caad71a4-eb21-4ac0-b5b7-73b79e47e67c")
    public static final String STEREOTYPE_NAME = "UML2ClassifierTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("f113cda4-4505-4d58-a3a5-beeb4891a6d5")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ClassifierTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f7cb2f21-16bf-4981-aa38-4aaf4b5249c4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> then instantiate a {@link UML2ClassifierTemplateParameter} proxy.
     * 
     * @return a {@link UML2ClassifierTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("0d27f308-ff58-484e-ae1c-21fd3544547b")
    public static UML2ClassifierTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME);
        return UML2ClassifierTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ClassifierTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("beda9482-aa68-4e5d-993a-00278aef7e48")
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
    @objid ("872d1216-ad07-45b5-95e4-65cffefc3c4d")
    public static UML2ClassifierTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ClassifierTemplateParameter.canInstantiate(obj))
        	return new UML2ClassifierTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0aac17ca-8c89-402f-9137-9ff62c6f8b00")
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
    @objid ("438c43c3-fee1-4179-a690-66216e2d3df7")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("53f04a74-98bb-4a01-adb1-bdb125391554")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0b2267d3-a01c-4afd-8ba0-4f7b6c565db4")
    protected UML2ClassifierTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("7d56bd81-07e4-4088-ae68-252f60dbb3fb")
    public static final class MdaTypes {
        @objid ("6b2cb97b-c3f6-4df2-a0a8-93f186a01771")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5e039a70-c737-45df-9e44-ebe8a5d5e4f7")
        private static Stereotype MDAASSOCDEP;

        @objid ("ec6ea17e-05f2-4f83-9047-83e7dc96dd1d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b0924f9e-6e20-4003-b03c-df06d6f52226")
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
