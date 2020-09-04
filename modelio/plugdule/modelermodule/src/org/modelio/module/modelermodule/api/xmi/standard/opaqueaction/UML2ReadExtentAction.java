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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadExtentAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f44efdd0-3ec1-476f-899a-8869358862ec")
public class UML2ReadExtentAction {
    @objid ("31ef8c65-3c5f-4d4a-a613-bfefcba6ade7")
    public static final String STEREOTYPE_NAME = "UML2ReadExtentAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("cbd15565-0e5c-4de4-94f1-1603a9152a4f")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadExtentAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4e3cccf1-7819-469e-a7d6-a4895493326e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> then instantiate a {@link UML2ReadExtentAction} proxy.
     * 
     * @return a {@link UML2ReadExtentAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("5240c5d9-5992-4a98-b211-7ecc8b9afcb3")
    public static UML2ReadExtentAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME);
        return UML2ReadExtentAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadExtentAction} proxy or <i>null</i>.
     */
    @objid ("8c594e1f-8f66-42e7-890b-8d78ad8cfdfa")
    public static UML2ReadExtentAction instantiate(OpaqueAction obj) {
        return UML2ReadExtentAction.canInstantiate(obj) ? new UML2ReadExtentAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadExtentAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ee6cac97-f664-459b-8dd9-4202bda6db98")
    public static UML2ReadExtentAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadExtentAction.canInstantiate(obj))
        	return new UML2ReadExtentAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadExtentAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d7178542-6275-4141-9782-d60ef5983e5a")
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
        UML2ReadExtentAction other = (UML2ReadExtentAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("5637e69f-a214-4eb6-8da8-8397f6c73048")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("f50c6aa8-7454-4c88-9900-fc83bb4d8ef7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c7e782fb-15ab-4f5d-924d-ac8134f3033d")
    protected UML2ReadExtentAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5b0571b6-d970-4ab3-9d56-4b8aba8bc9d6")
    public static final class MdaTypes {
        @objid ("97dbf20a-acb8-4c5e-908e-cc344a5d0899")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ded165d8-9589-4291-84e2-9e55adde66ed")
        private static Stereotype MDAASSOCDEP;

        @objid ("b83805b0-869b-452b-b357-9665ff013c82")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bfd26bab-05a8-425f-a537-be5a479715bb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c4b3add1-c2f9-11de-8ac8-001302895b2b");
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
