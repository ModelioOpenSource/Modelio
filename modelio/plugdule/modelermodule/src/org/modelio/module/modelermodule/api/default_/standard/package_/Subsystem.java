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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << subsystem >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f47f0f0-13fa-439b-be6a-0d13d837f14c")
public class Subsystem {
    @objid ("6f52d499-1bbb-41a5-81dc-d43251f4b2ef")
    public static final String STEREOTYPE_NAME = "subsystem";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("fca13b54-ea29-4416-9617-47d8bdb389b3")
    protected final Package elt;

    /**
     * Tells whether a {@link Subsystem proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << subsystem >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("99333b6d-891b-4d80-9a89-ba71058beaf2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << subsystem >> then instantiate a {@link Subsystem} proxy.
     * 
     * @return a {@link Subsystem} proxy on the created {@link Package}.
     */
    @objid ("b9202b82-af32-4481-b694-c770fcea1104")
    public static Subsystem create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME);
        return Subsystem.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Subsystem} proxy or <i>null</i>.
     */
    @objid ("16246b9e-10c7-433a-90ad-3f024bcc0868")
    public static Subsystem instantiate(Package obj) {
        return Subsystem.canInstantiate(obj) ? new Subsystem(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Subsystem} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3a377a06-73d3-4d52-b4d6-4d733040d02f")
    public static Subsystem safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Subsystem.canInstantiate(obj))
        	return new Subsystem(obj);
        else
        	throw new IllegalArgumentException("Subsystem: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3e9d7d35-ce23-450d-bb19-781e761fcca9")
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
        Subsystem other = (Subsystem) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("8811987b-4c1c-4181-bebb-b14accbdf54b")
    public Package getElement() {
        return this.elt;
    }

    @objid ("051d521e-f0fb-40ab-9bed-937fde76ba42")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("99c358ac-6d01-4418-9ba6-b71c507a4b53")
    protected Subsystem(Package elt) {
        this.elt = elt;
    }

    @objid ("ce7bd25b-0a68-466f-8b35-0426dcef43a4")
    public static final class MdaTypes {
        @objid ("a7c2bbc7-e671-4ea6-b184-f5f8e57b422c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b9fa7fda-8ab8-4f89-bcc9-fdd72b4ebcf3")
        private static Stereotype MDAASSOCDEP;

        @objid ("9a099348-6f11-477d-bc68-8a65c6f6ffd1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ff1eb207-8f8d-4e5d-8b25-1d47ddd0dcc3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d3-0000-000000000000");
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
