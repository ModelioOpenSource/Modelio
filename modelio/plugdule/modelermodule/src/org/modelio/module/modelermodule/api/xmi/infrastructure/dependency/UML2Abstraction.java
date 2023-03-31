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
 * Proxy class to handle a {@link Dependency} with << UML2Abstraction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d1e11d0-fed8-4531-a0ff-914ea423a867")
public class UML2Abstraction {
    @objid ("6374a60e-7852-4b12-81cc-4aa036975307")
    public static final String STEREOTYPE_NAME = "UML2Abstraction";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a5a7dcc1-2a53-4eda-8039-9c57c5f1e53a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Abstraction proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Abstraction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("37a50f32-f76d-47d9-ba3f-d76229360018")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Abstraction >> then instantiate a {@link UML2Abstraction} proxy.
     * 
     * @return a {@link UML2Abstraction} proxy on the created {@link Dependency}.
     */
    @objid ("b45c1546-f852-4918-9df2-60d0e05f65f6")
    public static UML2Abstraction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME);
        return UML2Abstraction.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Abstraction} proxy or <i>null</i>.
     */
    @objid ("97b364ec-37a4-4e61-b38d-ed174b6db85f")
    public static UML2Abstraction instantiate(Dependency obj) {
        return UML2Abstraction.canInstantiate(obj) ? new UML2Abstraction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Abstraction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3e70b4ce-be3d-426a-a4f0-de1622d32ae7")
    public static UML2Abstraction safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Abstraction.canInstantiate(obj))
        	return new UML2Abstraction(obj);
        else
        	throw new IllegalArgumentException("UML2Abstraction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6fc611aa-61d4-4193-af59-22c23c7a3c9d")
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
        UML2Abstraction other = (UML2Abstraction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("9ce431f0-4482-4918-8788-3a7f3e51a94b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("2cd50fe2-35a3-4108-87d0-5c49f10b7a5a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("20b4a545-69fe-4b08-98fd-fc1804fce4a1")
    protected  UML2Abstraction(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d9d3b85-b56b-481e-8799-f55b8683359f")
    public static final class MdaTypes {
        @objid ("b6d23e63-f7c3-4f7d-be2c-c3ffe8771a55")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ff8a5471-a568-4efc-aac0-22487508e229")
        private static Stereotype MDAASSOCDEP;

        @objid ("8665f960-9daa-4cf3-8af2-859974143d5e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2d4d5436-a6c8-4d43-a2e6-b9629463b708")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b355cc6c-c4aa-11df-b100-001302895b2b");
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
