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
 * Proxy class to handle a {@link Dependency} with << UML2MethodReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("747f7e0d-5c8c-413d-bdbb-641dfe37801b")
public class UML2MethodReference {
    @objid ("4388da9b-eea5-4476-adfa-dd117ef1e9ab")
    public static final String STEREOTYPE_NAME = "UML2MethodReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("aa9bd433-4065-4ab0-ad88-f63c5cdc17b2")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2MethodReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2MethodReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3732595b-c3ba-4bf5-a860-7cf12da518c6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2MethodReference >> then instantiate a {@link UML2MethodReference} proxy.
     * 
     * @return a {@link UML2MethodReference} proxy on the created {@link Dependency}.
     */
    @objid ("a34a082c-bc2b-45c7-848e-c35ffee1723e")
    public static UML2MethodReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME);
        return UML2MethodReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2MethodReference} proxy or <i>null</i>.
     */
    @objid ("e3e2bd76-755a-45ae-8c1d-84ddf882211c")
    public static UML2MethodReference instantiate(Dependency obj) {
        return UML2MethodReference.canInstantiate(obj) ? new UML2MethodReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2MethodReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1365fe57-6da3-40d8-95c3-10a4c22bdb0e")
    public static UML2MethodReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2MethodReference.canInstantiate(obj))
        	return new UML2MethodReference(obj);
        else
        	throw new IllegalArgumentException("UML2MethodReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("14c24fd5-c366-47a4-a06e-e4a4de2d33cb")
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
        UML2MethodReference other = (UML2MethodReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("8700580a-4a04-40c3-8fac-d5b167ec3559")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("d9564ec2-3e07-4cc2-a925-ff8a9ba8c333")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2081a137-fb51-41be-8d1c-de399c70be40")
    protected UML2MethodReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("de8df399-f6e6-443c-be21-019af7e50895")
    public static final class MdaTypes {
        @objid ("a298fbb9-89a0-4329-a946-92e86a3963ba")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b14bba8d-b737-4349-baaf-ee0d04178201")
        private static Stereotype MDAASSOCDEP;

        @objid ("508c617c-b911-413e-9ee0-b6b4424b7717")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("86f6dce9-2981-47a9-afa3-837f067bade9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e445c33b-de99-11de-905b-001302895b2b");
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
