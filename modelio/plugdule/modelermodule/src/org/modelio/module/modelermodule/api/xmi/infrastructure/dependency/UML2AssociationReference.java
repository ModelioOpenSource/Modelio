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
 * Proxy class to handle a {@link Dependency} with << UML2AssociationReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("77a332fe-d12f-47f1-abad-e85c4227ad5a")
public class UML2AssociationReference {
    @objid ("5cfb77ff-97e8-4733-a85b-766a28e99653")
    public static final String STEREOTYPE_NAME = "UML2AssociationReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7e0f2ffc-0acb-4798-b009-699309dc499a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2AssociationReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2AssociationReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8258ed77-ba26-4d9a-a1cd-5d65a4c4dcc0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2AssociationReference >> then instantiate a {@link UML2AssociationReference} proxy.
     * 
     * @return a {@link UML2AssociationReference} proxy on the created {@link Dependency}.
     */
    @objid ("7fa95355-1f7e-46cd-b9c5-996f9dc6e560")
    public static UML2AssociationReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME);
        return UML2AssociationReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2AssociationReference} proxy or <i>null</i>.
     */
    @objid ("09301302-8ab1-4f2e-996d-3817487ac802")
    public static UML2AssociationReference instantiate(Dependency obj) {
        return UML2AssociationReference.canInstantiate(obj) ? new UML2AssociationReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2AssociationReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ede88557-41e4-461a-8b84-5775ffb22e8d")
    public static UML2AssociationReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2AssociationReference.canInstantiate(obj))
        	return new UML2AssociationReference(obj);
        else
        	throw new IllegalArgumentException("UML2AssociationReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("43b2cb0f-7908-4bef-acf0-511ed9b1d47d")
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
        UML2AssociationReference other = (UML2AssociationReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("0f7fdffd-4c02-44b9-828a-b4b1f57252f2")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0d59d927-9fd7-4f95-aabc-5ee8a38f1526")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fc58cfee-12b2-464c-b89f-a4eff60e3987")
    protected UML2AssociationReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b489f045-60a4-467e-9539-1f3d00766293")
    public static final class MdaTypes {
        @objid ("269a14ee-3af1-4b5a-9a44-e20cf1a99870")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3a5043eb-ae80-4351-a611-0d8de32738ec")
        private static Stereotype MDAASSOCDEP;

        @objid ("0e279020-ad0c-4166-85e1-8af94319c0c7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d30cf6d1-5c49-4ecb-88d3-4178dd5df06b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee97796b-de99-11de-905b-001302895b2b");
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
