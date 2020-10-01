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
    @objid ("846fc2ad-e342-44ce-84b6-e49168ba75c0")
    public static final String STEREOTYPE_NAME = "Reference";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("532f38dd-5f60-4ff7-828c-277e478c921a")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Reference proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Reference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f6a6142d-7622-4b91-9d1a-14c171216cf8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Reference >> then instantiate a {@link Reference} proxy.
     * 
     * @return a {@link Reference} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("2de890c5-9092-42df-863c-c3133fabf53f")
    public static Reference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
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
    @objid ("bb324f59-5fe8-4b1b-8a15-0fba031ed463")
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
    @objid ("a4ae940b-4800-4d5c-a412-12f1eeee0ed4")
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

    @objid ("c41fa486-b87e-4f39-a940-00bf44c3e0f7")
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
    @objid ("95d3312c-3119-4d61-a92c-9a6fe9489791")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("29e0469e-53c0-4bde-b8cd-834c0751ca4c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("16779c2b-2a8c-47b7-b7e0-0b4a23a52367")
    protected Reference(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("a27b86e8-95fd-41e3-ac1c-1744e5c17bea")
    public static final class MdaTypes {
        @objid ("d43a9101-5ff7-4df3-ac05-d40d4721a042")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a8130818-bacb-4137-a066-da55b88e4bc5")
        private static Stereotype MDAASSOCDEP;

        @objid ("da877319-21a8-4d9f-881f-2e5bf2d87881")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("86eb843f-4b68-474b-95e8-ab60c4d65f29")
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
