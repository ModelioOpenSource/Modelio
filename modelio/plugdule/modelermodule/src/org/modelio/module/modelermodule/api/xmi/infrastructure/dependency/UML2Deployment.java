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
 * Proxy class to handle a {@link Dependency} with << UML2Deployment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fc3acabf-4f25-4ad1-b9d8-51446901052b")
public class UML2Deployment {
    @objid ("2ceffa46-dd08-4897-aeba-4733443c896e")
    public static final String STEREOTYPE_NAME = "UML2Deployment";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c18b8f97-a531-4b11-b741-c71221157482")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Deployment proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Deployment >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bfd5f359-5957-4b29-a74c-c3cc5d3b7bc2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Deployment >> then instantiate a {@link UML2Deployment} proxy.
     * 
     * @return a {@link UML2Deployment} proxy on the created {@link Dependency}.
     */
    @objid ("c154adaa-158a-4206-ad4a-0ac44392b6ea")
    public static UML2Deployment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME);
        return UML2Deployment.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Deployment} proxy or <i>null</i>.
     */
    @objid ("0702099e-c0f1-4dd2-8051-fccddd46e437")
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
    @objid ("836abe17-bf69-42b7-a662-7ba30c061da8")
    public static UML2Deployment safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Deployment.canInstantiate(obj))
        	return new UML2Deployment(obj);
        else
        	throw new IllegalArgumentException("UML2Deployment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4b85c291-9853-4867-8c31-31e1818b7caa")
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
    @objid ("d6c2fa2e-7dcc-411a-a18c-8065a695244d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("5e1c093a-d4ca-4fa3-aa70-0cb1a90b0f58")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9c951bd6-b948-45a3-979d-6f362e9473cb")
    protected UML2Deployment(Dependency elt) {
        this.elt = elt;
    }

    @objid ("3c7b3b03-b7b6-4b5e-b616-ef29bb173f1b")
    public static final class MdaTypes {
        @objid ("9306317d-4b0a-465f-8e48-eece09d40083")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("79e089b8-656f-4d0f-a14e-8e5fa316c4b9")
        private static Stereotype MDAASSOCDEP;

        @objid ("8fdd0349-05b6-41db-8305-a2683d6f0e0e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e286bc31-09a8-4db8-ab81-a0a5ef919d4d")
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
