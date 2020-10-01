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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("2d4e270b-2e88-4e00-8847-96cc22fa9f5e")
    public static final String STEREOTYPE_NAME = "UML2ClassifierTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("e910e46e-99ff-418f-8857-db2e89d7abef")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ClassifierTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("af45aa40-9b89-4f66-8592-7e5d0cc9b45c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> then instantiate a {@link UML2ClassifierTemplateParameter} proxy.
     * 
     * @return a {@link UML2ClassifierTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("a1c97839-93bd-4995-a1f7-79da6cef2979")
    public static UML2ClassifierTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
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
    @objid ("87f3e76c-7b97-456d-80f7-7f059344dd84")
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
    @objid ("5db3f415-76e8-4b70-8c1c-671d2770e7c6")
    public static UML2ClassifierTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ClassifierTemplateParameter.canInstantiate(obj))
        	return new UML2ClassifierTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4a32165c-c06a-4215-9054-540de7bfffc6")
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
    @objid ("83a5210a-d571-4ab7-847c-1c44ece40b85")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("181b80f6-fd76-4e55-bb57-eff5f9a55338")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0cd14226-346b-4bb4-8ad8-430dc79ac5ec")
    protected UML2ClassifierTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("7d56bd81-07e4-4088-ae68-252f60dbb3fb")
    public static final class MdaTypes {
        @objid ("bdf90dc6-d8aa-491c-b5b3-fc37112ebd5d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("13c489cd-668e-48bd-978c-b3f853c9c821")
        private static Stereotype MDAASSOCDEP;

        @objid ("e549fd36-9934-42c8-9844-eefd3ba89566")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9324f3d2-1746-49af-9408-f0114188daef")
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
