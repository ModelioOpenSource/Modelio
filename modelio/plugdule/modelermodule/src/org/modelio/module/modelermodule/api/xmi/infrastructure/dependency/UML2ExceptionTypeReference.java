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
    @objid ("f75123df-7014-4385-92c5-048938974e02")
    public static final String STEREOTYPE_NAME = "UML2ExceptionTypeReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("1b112dd0-cf69-48f1-b090-fa9becfa62a7")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ExceptionTypeReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("16128f3b-05a1-4617-bb4f-713ead33e372")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ExceptionTypeReference >> then instantiate a {@link UML2ExceptionTypeReference} proxy.
     * 
     * @return a {@link UML2ExceptionTypeReference} proxy on the created {@link Dependency}.
     */
    @objid ("fc7f4c07-1c7b-491e-aced-c853baad21f7")
    public static UML2ExceptionTypeReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME);
        return UML2ExceptionTypeReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ExceptionTypeReference} proxy or <i>null</i>.
     */
    @objid ("b58f4e61-af05-420b-a00b-210be1a9f3c0")
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
    @objid ("af941d6d-9581-42d7-984e-b5eb906d51b4")
    public static UML2ExceptionTypeReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ExceptionTypeReference.canInstantiate(obj))
        	return new UML2ExceptionTypeReference(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionTypeReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cc2a87c8-3f70-46fe-b902-b2f9928313b2")
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
    @objid ("4122a211-891e-4203-98ec-9db5b7e2914e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("fbc4bda7-7e79-451c-b75f-5b1b5f83cd0e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1c55b16b-6238-4f5a-bc34-06ea60aa5937")
    protected UML2ExceptionTypeReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("209cf20e-a9e7-4dbf-a4ff-221a53f12e81")
    public static final class MdaTypes {
        @objid ("7ce2b157-80bb-44fb-95a0-32250401fe7a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b7ba8df9-dcd2-4f8c-9c7c-bf1a18e4cc4a")
        private static Stereotype MDAASSOCDEP;

        @objid ("38c063e9-4a3a-43be-a165-3f507d6064bc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("eeb39186-d370-4b54-8ac5-2da584cc35dd")
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
