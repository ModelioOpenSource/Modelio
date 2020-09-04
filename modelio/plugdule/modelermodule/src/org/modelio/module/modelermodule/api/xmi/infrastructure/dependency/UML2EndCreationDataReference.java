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
 * Proxy class to handle a {@link Dependency} with << UML2EndCreationDataReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2bb9e3da-9089-4006-a6a4-4b50ae303353")
public class UML2EndCreationDataReference {
    @objid ("4c404261-8fca-429f-a2ff-59aedaeded28")
    public static final String STEREOTYPE_NAME = "UML2EndCreationDataReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("bf283e07-b7ec-4293-90ef-ea922c5e49df")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndCreationDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndCreationDataReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("36140dc3-b3d9-4083-bbc5-7f4984a0f4d9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndCreationDataReference >> then instantiate a {@link UML2EndCreationDataReference} proxy.
     * 
     * @return a {@link UML2EndCreationDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("dff5ff21-446f-49ca-9673-e6a63fcf50a3")
    public static UML2EndCreationDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME);
        return UML2EndCreationDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndCreationDataReference} proxy or <i>null</i>.
     */
    @objid ("b92ee0ce-ffe8-4cce-8b37-8878f0a47ecc")
    public static UML2EndCreationDataReference instantiate(Dependency obj) {
        return UML2EndCreationDataReference.canInstantiate(obj) ? new UML2EndCreationDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndCreationDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c940039c-6b02-45d0-ae96-93f2f582b04d")
    public static UML2EndCreationDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndCreationDataReference.canInstantiate(obj))
        	return new UML2EndCreationDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndCreationDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4586e33b-95a2-4daf-ba83-b8436113e289")
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
        UML2EndCreationDataReference other = (UML2EndCreationDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7d8fe508-a814-4f3d-b3f5-e3be404e3b63")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4d779147-f760-43c1-9f67-f0ffb162a145")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f0125453-2b34-451e-b64d-8b9ffff9e16d")
    protected UML2EndCreationDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("ce8685fd-e624-48a1-a3ab-352efe352774")
    public static final class MdaTypes {
        @objid ("0175b92c-67aa-4370-a585-94adc03611e3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3cb39772-489e-405b-9432-e603f926bb68")
        private static Stereotype MDAASSOCDEP;

        @objid ("fdb10c0a-c0d9-4361-99e9-93f8e0e15034")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("167ca1c0-63df-4cfa-8f63-f3cfa388ed69")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee06097f-de99-11de-905b-001302895b2b");
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
