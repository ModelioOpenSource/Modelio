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
 * Proxy class to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business process ou application Process doivent faire apparaitre un activity call.</i></p>
 */
@objid ("f89804bf-ce37-4ee3-9391-4817cb8a0de9")
public class Called {
    @objid ("ed72fb53-cdc2-4dbb-a338-f1735e4add6e")
    public static final String STEREOTYPE_NAME = "Called";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("ea177b43-cc5c-480c-9133-f16ea561f650")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Called proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Called >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a92cee00-3d4c-4b93-a637-43aa9a482d10")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Called >> then instantiate a {@link Called} proxy.
     * 
     * @return a {@link Called} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("0a0e6b7d-3d43-4078-b658-745d9efb2c79")
    public static Called create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME);
        return Called.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Called} proxy or <i>null</i>.
     */
    @objid ("739375bd-b0ff-46cd-a90f-fe4240c88672")
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
    @objid ("ae1a4e65-1fca-49e3-8562-544fa533691d")
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

    @objid ("89a0a547-5640-4841-8b30-7bdd6472fcf4")
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
    @objid ("e37dad08-ca30-4a51-8c68-84c9f425bc10")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("ac05db5e-f118-43db-b917-0671f904222d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("31663257-1324-4099-85b0-3ba0be83cc85")
    protected  Called(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("3a055766-68e6-4266-a9ac-43448b367a1b")
    public static final class MdaTypes {
        @objid ("6e181195-9e34-4e5c-971d-f7e29ec2d6bf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8e4b070e-1231-4693-97c3-617f23152b02")
        private static Stereotype MDAASSOCDEP;

        @objid ("b1eb7d95-2524-4853-ac36-8d806494d757")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("abdec8b8-9157-4400-836e-13a2902c0212")
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
