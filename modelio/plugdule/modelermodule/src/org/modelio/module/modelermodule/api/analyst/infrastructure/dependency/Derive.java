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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << derive >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0a2b7f32-1cd2-44cd-b61d-f4464bbf91de")
public class Derive {
    @objid ("1d3514d7-6246-415b-ba7f-f4a300fd9d45")
    public static final String STEREOTYPE_NAME = "derive";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2fb3cf08-ad45-4771-abc6-208a35cce63a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Derive proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << derive >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ad237fb9-abce-4404-9d36-e38ca321f9e6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << derive >> then instantiate a {@link Derive} proxy.
     * 
     * @return a {@link Derive} proxy on the created {@link Dependency}.
     */
    @objid ("668f9993-e499-4535-9704-8560fe702290")
    public static Derive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME);
        return Derive.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Derive} proxy or <i>null</i>.
     */
    @objid ("9fbdfe54-7001-4eaa-9d80-eb511df4933e")
    public static Derive instantiate(Dependency obj) {
        return Derive.canInstantiate(obj) ? new Derive(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Derive} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a5a5c17a-b14e-4645-9121-7e946932c246")
    public static Derive safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Derive.canInstantiate(obj))
        	return new Derive(obj);
        else
        	throw new IllegalArgumentException("Derive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ca69150b-f8c8-4571-8521-9f1e3f5f328f")
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
        Derive other = (Derive) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("0bb1a27b-8472-4900-b9f7-e2c4a892df98")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("9cdc897e-cdf0-4304-b763-9980cbde4e16")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4774022c-4bb8-4ad0-8a87-19dcfdae6195")
    protected Derive(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7dea825e-1f89-4805-9f37-7ff8a5c37024")
    public static final class MdaTypes {
        @objid ("b0ca3f24-f090-4dc1-b672-e697794d1286")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2088cbfa-fd92-498c-a856-edd2f956c80f")
        private static Stereotype MDAASSOCDEP;

        @objid ("42a96aa6-1890-4c2f-84f9-7ff3c22afb6a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4872e769-65c0-4271-b162-ae71ce773f7d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021a-0000-000000000000");
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
