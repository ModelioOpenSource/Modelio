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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("0b1a5642-50de-46f9-bf4a-2fc939d70bf0")
public class Reference {
    @objid ("615bea68-4da1-434f-9fcc-c5f63bb11862")
    public static final String STEREOTYPE_NAME = "Reference";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("f1efc917-8efb-4208-821e-2900460b412d")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Reference proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Reference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("437728fc-00cb-4b50-bcac-16769d01243c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Reference >> then instantiate a {@link Reference} proxy.
     * 
     * @return a {@link Reference} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("ad2c54ab-c549-46ad-b7df-fa832c493674")
    public static Reference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME);
        return Reference.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Reference} proxy from a {@link MethodologicalLink} stereotyped << Reference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Reference} proxy or <i>null</i>.
     */
    @objid ("4164cc88-a85e-4c22-8c9a-d773cd1eb349")
    public static Reference instantiate(MethodologicalLink obj) {
        return Reference.canInstantiate(obj) ? new Reference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Reference} proxy from a {@link MethodologicalLink} stereotyped << Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Reference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("29d89b63-426f-4aff-b54c-4051bff59b9c")
    public static Reference safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Reference.canInstantiate(obj))
        	return new Reference(obj);
        else
        	throw new IllegalArgumentException("Reference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2e3656a4-2638-4010-b48d-c91fcb045f5e")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("d63c878d-80c3-48e0-be1f-5f7a98a81a88")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("42baa33b-5e36-481e-9df8-b343e1007c73")
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
        Reference other = (Reference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("23f3a013-819a-4a1f-bbcc-8777671a10c8")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("0530e12d-a65f-4ebe-a030-f3e5365556ec")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0fe26a18-7696-4260-8f59-0b09d1e5bc13")
    protected Reference(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("a27b86e8-95fd-41e3-ac1c-1744e5c17bea")
    public static final class MdaTypes {
        @objid ("5a747669-f300-4bc5-94d4-97aad6a93830")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e9eb529d-904b-4163-9b73-bfc0968a4d17")
        private static Stereotype MDAASSOCDEP;

        @objid ("486bbaa1-33d1-4d0a-9821-f776ddd774c1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b05e6b9f-6bb3-487a-96a1-4a553dbadf05")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3b4dc351-ccaa-47b8-af57-8434f8e0e5f5");
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
