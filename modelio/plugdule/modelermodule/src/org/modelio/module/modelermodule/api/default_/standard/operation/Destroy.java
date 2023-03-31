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
package org.modelio.module.modelermodule.api.default_.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << destroy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("699eb4cb-af8a-484f-af24-91a599fada81")
public class Destroy {
    @objid ("4b402a3a-34cb-4367-868d-86b6b47bf5b5")
    public static final String STEREOTYPE_NAME = "destroy";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("309d92a5-ad29-4307-985b-396fd5462c92")
    protected final Operation elt;

    /**
     * Tells whether a {@link Destroy proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << destroy >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("47e159d3-1385-4894-a210-f107e213c508")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << destroy >> then instantiate a {@link Destroy} proxy.
     * 
     * @return a {@link Destroy} proxy on the created {@link Operation}.
     */
    @objid ("60b6cc66-8cba-4892-951d-f26b4a3d9164")
    public static Destroy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME);
        return Destroy.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Destroy} proxy or <i>null</i>.
     */
    @objid ("a8069b72-04fe-412f-a15c-2650225e261e")
    public static Destroy instantiate(Operation obj) {
        return Destroy.canInstantiate(obj) ? new Destroy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link Destroy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("95d3e765-6f76-4ce9-a5c3-e981527e3e39")
    public static Destroy safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Destroy.canInstantiate(obj))
        	return new Destroy(obj);
        else
        	throw new IllegalArgumentException("Destroy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9b43a113-88b2-4d4a-995a-a2531b2b4d5a")
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
        Destroy other = (Destroy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("a61e99b5-c0ac-42b8-a29b-35c7364f26ef")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("8231ccb5-b1a7-469c-bc3e-4cc0c047b08f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("f1297261-426d-445a-ad5b-1fbdaef911f4")
    protected  Destroy(Operation elt) {
        this.elt = elt;
    }

    @objid ("452f4dff-b119-47dc-9dd6-c88f4d500896")
    public static final class MdaTypes {
        @objid ("83419803-5dbb-4348-86d4-ad8e809c3b0a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("587903d0-4ef0-483a-a4c5-b1be715e9e9f")
        private static Stereotype MDAASSOCDEP;

        @objid ("4ce9fc5b-31a1-447f-8ea2-f819aab4ff27")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("89b87baa-2e73-4e3d-b992-406bc79c1ef8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0206-0000-000000000000");
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
