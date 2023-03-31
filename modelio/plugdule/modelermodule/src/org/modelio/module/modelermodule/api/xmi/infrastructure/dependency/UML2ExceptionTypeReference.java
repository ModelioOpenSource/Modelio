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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2ExceptionTypeReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("87372d24-b077-4b6a-a76e-518b2d785b07")
public class UML2ExceptionTypeReference {
    @objid ("29d1c122-f872-407f-879f-5223237539ee")
    public static final String STEREOTYPE_NAME = "UML2ExceptionTypeReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f2c3de21-d859-4214-abf3-13fbb2541203")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ExceptionTypeReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1a561244-3c70-4f38-b1fc-5da725842dbc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ExceptionTypeReference >> then instantiate a {@link UML2ExceptionTypeReference} proxy.
     * 
     * @return a {@link UML2ExceptionTypeReference} proxy on the created {@link Dependency}.
     */
    @objid ("4666dec5-2198-47a6-a965-3e1f0fd37588")
    public static UML2ExceptionTypeReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME);
        return UML2ExceptionTypeReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ExceptionTypeReference} proxy or <i>null</i>.
     */
    @objid ("88f04114-3193-4399-84ed-27342071c37a")
    public static UML2ExceptionTypeReference instantiate(Dependency obj) {
        return UML2ExceptionTypeReference.canInstantiate(obj) ? new UML2ExceptionTypeReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ExceptionTypeReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ee372e43-65c1-4f0b-805a-9dbca5f5a3d5")
    public static UML2ExceptionTypeReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ExceptionTypeReference.canInstantiate(obj))
        	return new UML2ExceptionTypeReference(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionTypeReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("096159de-731a-4e8b-8c7b-56c5b569fb58")
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
        UML2ExceptionTypeReference other = (UML2ExceptionTypeReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("4cdce2c8-f2be-460d-9afb-d1e4ecb1d298")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4a066b4b-fd1a-46fd-b000-82aeb70c07a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("1f01bdad-f694-4705-953e-65c49f74ba15")
    protected  UML2ExceptionTypeReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("209cf20e-a9e7-4dbf-a4ff-221a53f12e81")
    public static final class MdaTypes {
        @objid ("775476f5-57d1-43bc-bb53-38a5b7d48cd9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6f2f6113-20a6-4c6f-8461-007128c90910")
        private static Stereotype MDAASSOCDEP;

        @objid ("8f17b9a6-b2b5-4749-96ea-ca3dd1a84ff0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6fc65bf7-6aaf-4b47-8484-16c5db90aa01")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4e477e48-35b4-11df-9280-001302895b2b");
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
