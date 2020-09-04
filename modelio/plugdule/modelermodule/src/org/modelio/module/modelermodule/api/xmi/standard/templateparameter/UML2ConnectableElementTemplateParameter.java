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
 * Proxy class to handle a {@link TemplateParameter} with << UML2ConnectableElementTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac45a46f-1893-4689-9905-62e6e9724c4f")
public class UML2ConnectableElementTemplateParameter {
    @objid ("e2992b81-9eae-403e-bfe5-10c9b25b5f61")
    public static final String STEREOTYPE_NAME = "UML2ConnectableElementTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("ce9ff9e4-ddd2-4670-a092-a044ebfa3aa8")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ConnectableElementTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("701194a7-c493-4ceb-8c8d-692cd366de69")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> then instantiate a {@link UML2ConnectableElementTemplateParameter} proxy.
     * 
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("c709f997-bf08-43d7-854a-8b16d0f59fe2")
    public static UML2ConnectableElementTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME);
        return UML2ConnectableElementTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("cca12531-aa18-400d-93a5-13a1f19c7f05")
    public static UML2ConnectableElementTemplateParameter instantiate(TemplateParameter obj) {
        return UML2ConnectableElementTemplateParameter.canInstantiate(obj) ? new UML2ConnectableElementTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link TemplateParameter}
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4a794c28-bd6d-47a4-9db7-534220fc209f")
    public static UML2ConnectableElementTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ConnectableElementTemplateParameter.canInstantiate(obj))
        	return new UML2ConnectableElementTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ConnectableElementTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dca37d58-06ee-44cc-9bb5-c20ee3f96b32")
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
        UML2ConnectableElementTemplateParameter other = (UML2ConnectableElementTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link TemplateParameter}. 
     * @return the TemplateParameter represented by this proxy, never null.
     */
    @objid ("b36f80b2-ad69-4fa0-b159-87777c058ec2")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("97d1b204-38f0-4e78-8114-86107f5de077")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9fb55794-8a54-40d2-87df-0430e29eb269")
    protected UML2ConnectableElementTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("938d54a8-5da0-4ddb-804b-e7b573a5d935")
    public static final class MdaTypes {
        @objid ("35d89856-4bed-4256-9d98-a4782c7f65c2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e9f4d150-8a80-4d28-bcdc-8758ed52001f")
        private static Stereotype MDAASSOCDEP;

        @objid ("2aaa9760-20d5-4531-8783-5488aa6b4ef2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f8f8b7d8-a545-4c93-b6ae-8538cca42b71")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ff6e0375-5d09-11df-a996-001302895b2b");
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
