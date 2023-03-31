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
    @objid ("fea5985e-6ab1-4df6-8cd8-f7d357a302d7")
    public static final String STEREOTYPE_NAME = "Reference";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("97081749-c523-455a-83cb-853ace0424d0")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Reference proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Reference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("97e4d9d8-58c5-4f2a-bcc4-ef9bd0106776")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Reference >> then instantiate a {@link Reference} proxy.
     * 
     * @return a {@link Reference} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("a06e59b9-dd17-4311-8e0c-007923996e20")
    public static Reference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME);
        return Reference.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Reference} proxy from a {@link MethodologicalLink} stereotyped << Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Reference} proxy or <i>null</i>.
     */
    @objid ("21904457-1c8c-4609-9b6b-a8656ac412ae")
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
    @objid ("933d0df7-6920-4e8d-866e-7f69fbc984a1")
    public static Reference safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Reference.canInstantiate(obj))
        	return new Reference(obj);
        else
        	throw new IllegalArgumentException("Reference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("2e3656a4-2638-4010-b48d-c91fcb045f5e")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("d63c878d-80c3-48e0-be1f-5f7a98a81a88")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("175e40f6-3d3b-4065-9689-4446ce996ec9")
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
    @objid ("4d8e1e70-79d6-42a0-b0b1-ca3a7ce603dc")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("0b1806b5-ec4f-4ada-b656-19f39750db52")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("28ea4d3d-7cfd-4c71-af0f-5159e3379075")
    protected  Reference(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("a27b86e8-95fd-41e3-ac1c-1744e5c17bea")
    public static final class MdaTypes {
        @objid ("b3f6254c-8535-4633-b532-a00623d1edd8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("23e04465-73ea-47f0-982c-7ec9edc6c7ae")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6458bc6-6956-4c78-876d-50c17bb415cc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b9243098-1ac0-49c1-8b7b-2452a44c1433")
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
