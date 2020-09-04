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
 * Proxy class to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business process ou application Process doivent faire apparaitre un activity call.</i></p>
 */
@objid ("f89804bf-ce37-4ee3-9391-4817cb8a0de9")
public class Called {
    @objid ("142ab071-c2f8-4079-b063-bd0cbc9ccd53")
    public static final String STEREOTYPE_NAME = "Called";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("a431359f-001d-4c2e-8356-503d506f1d38")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Called proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Called >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cb798b9b-a906-4650-b2d0-e4d77132b2cb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Called >> then instantiate a {@link Called} proxy.
     * 
     * @return a {@link Called} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("929adc48-5fc1-4a29-a7b0-b5f27e1b441f")
    public static Called create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME);
        return Called.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Called} proxy or <i>null</i>.
     */
    @objid ("8b1bdc12-f37f-4c15-8bbb-5e279f277d01")
    public static Called instantiate(MethodologicalLink obj) {
        return Called.canInstantiate(obj) ? new Called(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Called} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("66e75f8d-056c-4251-8a4d-f60c561f1fcf")
    public static Called safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Called.canInstantiate(obj))
        	return new Called(obj);
        else
        	throw new IllegalArgumentException("Called: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bff86b6d-45f8-4f89-9bbe-ccce4ed5c363")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("7de0a3b2-2dae-493d-b2e1-b4d05f76b608")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("d7865a39-f68a-4d56-b4e5-80e267490345")
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
        Called other = (Called) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("d2cb4265-4a79-4377-8c5b-0578744694cb")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("f519b6c3-3215-4399-b27b-5fbfccbcd453")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("88e11862-e855-4d73-982c-3fa3e5437df7")
    protected Called(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("3a055766-68e6-4266-a9ac-43448b367a1b")
    public static final class MdaTypes {
        @objid ("d483fb73-dfbd-43dd-900d-bd601cd32b70")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a82eb7e3-c0a9-49c9-bf4f-7085c4f47277")
        private static Stereotype MDAASSOCDEP;

        @objid ("109a581d-3041-4d38-8053-522a0a218e2d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5664de50-e1a1-4c05-bfa3-b0f3d9a7b69d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c3862c6c-5983-4d1a-b0e2-58dd2685eda0");
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
