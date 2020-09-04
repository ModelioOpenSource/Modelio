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
    @objid ("fa474f26-8f15-4648-8512-18bce6c1dfe2")
    public static final String STEREOTYPE_NAME = "subsystem";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("1dc95120-e9c4-4c8c-a8b6-119e9ed43f72")
    protected final Package elt;

    /**
     * Tells whether a {@link Subsystem proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << subsystem >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0668af18-4e4d-43de-a5ae-d87db5720c6c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << subsystem >> then instantiate a {@link Subsystem} proxy.
     * 
     * @return a {@link Subsystem} proxy on the created {@link Package}.
     */
    @objid ("dceb0f87-ef96-4e70-8471-6a59bc2603ff")
    public static Subsystem create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME);
        return Subsystem.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Subsystem} proxy or <i>null</i>.
     */
    @objid ("c75f4fa8-6307-434f-8ef8-375713d0e3ff")
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
    @objid ("a5ce9ada-6ac8-4309-86a4-b53fe6f9479d")
    public static Subsystem safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Subsystem.canInstantiate(obj))
        	return new Subsystem(obj);
        else
        	throw new IllegalArgumentException("Subsystem: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7fd5fc9e-2c37-4902-991f-929833395a21")
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
    @objid ("d8475cdd-0983-420b-ad2f-32a6c916761b")
    public Package getElement() {
        return this.elt;
    }

    @objid ("edd1fc69-8323-4346-ae76-c5062ee83c5a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("11e255ca-e773-4162-b4ff-e23b23f70f15")
    protected Subsystem(Package elt) {
        this.elt = elt;
    }

    @objid ("ce7bd25b-0a68-466f-8b35-0426dcef43a4")
    public static final class MdaTypes {
        @objid ("15c7f2c1-db8b-4e8b-a95f-b55e28147899")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0785edfc-0de7-451c-83f9-3803f7a1bafe")
        private static Stereotype MDAASSOCDEP;

        @objid ("286ee76e-dd26-4c98-85f9-c53db0e1242c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("80833426-9c38-4eea-af2d-ce5423a274bc")
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
