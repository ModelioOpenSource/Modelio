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
 * Proxy class to handle a {@link TemplateParameter} with << UML2ConnectableElementTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac45a46f-1893-4689-9905-62e6e9724c4f")
public class UML2ConnectableElementTemplateParameter {
    @objid ("ad8cc439-df66-4c6f-b42a-4b1265c87052")
    public static final String STEREOTYPE_NAME = "UML2ConnectableElementTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("be6bb9ba-7991-4a9c-86c3-b7a5092cc9e8")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ConnectableElementTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d9b7f3aa-d79a-43a3-92d9-ebcb7c89a5d6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> then instantiate a {@link UML2ConnectableElementTemplateParameter} proxy.
     * 
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("f690e084-b781-4bcb-a5b0-de640098bbe9")
    public static UML2ConnectableElementTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME);
        return UML2ConnectableElementTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("5a8f637d-de72-4ab3-9f6b-3d55d2b1ecf1")
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
    @objid ("cd075c5a-8bd7-4cc6-af8d-4bbf4c3f2420")
    public static UML2ConnectableElementTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ConnectableElementTemplateParameter.canInstantiate(obj))
        	return new UML2ConnectableElementTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ConnectableElementTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0eac0aa-733e-4e16-b128-937f1c642a18")
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
    @objid ("e262a3b2-7ed4-4e99-8112-42c9876f26af")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("c8897dfd-ca00-4615-bf58-85034be9b626")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e1efb158-66bb-4016-993b-7952c19dff8d")
    protected UML2ConnectableElementTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("938d54a8-5da0-4ddb-804b-e7b573a5d935")
    public static final class MdaTypes {
        @objid ("59bf7584-c0b2-4286-859b-bf85241bc054")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fa839803-80ca-4e73-9993-4599e54d0c34")
        private static Stereotype MDAASSOCDEP;

        @objid ("499cf5e2-ad05-40d7-8b4f-392b4f758914")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("72768370-469a-47be-8e03-8a414c69ad01")
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
