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
 * Proxy class to handle a {@link Dependency} with << UML2Deployment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fc3acabf-4f25-4ad1-b9d8-51446901052b")
public class UML2Deployment {
    @objid ("9b29c00f-0ffe-4b13-8207-cc5c9199c363")
    public static final String STEREOTYPE_NAME = "UML2Deployment";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("37eb5cd7-113b-4d75-8558-d50e65573382")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Deployment proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Deployment >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("63bcd8f3-554c-4154-9088-d43d30c0c42e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Deployment >> then instantiate a {@link UML2Deployment} proxy.
     * 
     * @return a {@link UML2Deployment} proxy on the created {@link Dependency}.
     */
    @objid ("0be05bbf-9b22-4707-94b1-472aaa434bdf")
    public static UML2Deployment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME);
        return UML2Deployment.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Deployment} proxy or <i>null</i>.
     */
    @objid ("c247748d-c3f3-4198-ad31-8aa461e41360")
    public static UML2Deployment instantiate(Dependency obj) {
        return UML2Deployment.canInstantiate(obj) ? new UML2Deployment(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Deployment} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4c665fba-c3f6-43c3-9de2-c0a8677d8694")
    public static UML2Deployment safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Deployment.canInstantiate(obj))
        	return new UML2Deployment(obj);
        else
        	throw new IllegalArgumentException("UML2Deployment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ac7e787c-4492-4d1d-91a0-894373a32484")
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
        UML2Deployment other = (UML2Deployment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("2b264dd8-ed95-458f-ac76-f4d069c2f8f5")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4fcacb78-04cd-4273-bdea-27052c98d192")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2fe88aa9-ec63-4811-8f3c-1174a5e40d22")
    protected UML2Deployment(Dependency elt) {
        this.elt = elt;
    }

    @objid ("3c7b3b03-b7b6-4b5e-b616-ef29bb173f1b")
    public static final class MdaTypes {
        @objid ("4d88c731-eea6-480e-9ca5-d899c3189570")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("170205c1-8567-4b4f-81b1-a23d111f0a20")
        private static Stereotype MDAASSOCDEP;

        @objid ("e77541fc-43bd-40c2-bd60-a16f53a88ff2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d42ad118-f27d-4e20-a997-051bc09d35bd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6faa55e3-5d0b-11df-a996-001302895b2b");
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
