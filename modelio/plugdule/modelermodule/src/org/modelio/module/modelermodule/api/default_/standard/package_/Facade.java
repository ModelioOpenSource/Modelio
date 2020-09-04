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
 * Proxy class to handle a {@link Package} with << facade >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de0809b5-09b8-4993-822d-cd90c93c207d")
public class Facade {
    @objid ("7086ff77-ca7a-43dc-b1a5-4a532bb4e243")
    public static final String STEREOTYPE_NAME = "facade";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("a848d1e3-def3-4986-b144-fdd4aa70c42e")
    protected final Package elt;

    /**
     * Tells whether a {@link Facade proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << facade >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0978b68b-2daf-4e6a-8187-5b2f4134e964")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << facade >> then instantiate a {@link Facade} proxy.
     * 
     * @return a {@link Facade} proxy on the created {@link Package}.
     */
    @objid ("4e774a44-04e4-4097-bb56-efa83103f596")
    public static Facade create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME);
        return Facade.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Facade} proxy or <i>null</i>.
     */
    @objid ("ad6c2e93-acb7-499e-b701-f4cb3341de37")
    public static Facade instantiate(Package obj) {
        return Facade.canInstantiate(obj) ? new Facade(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Facade} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d2b14f94-ffa9-4f93-8d79-8f8157a439c8")
    public static Facade safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Facade.canInstantiate(obj))
        	return new Facade(obj);
        else
        	throw new IllegalArgumentException("Facade: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("009af206-34e5-4afb-a538-16a8fd6cef80")
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
        Facade other = (Facade) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("12ddaf74-b4da-4154-8b1f-62a30f0e4e59")
    public Package getElement() {
        return this.elt;
    }

    @objid ("c664fd71-346e-4fbd-9bf5-06a3eb59c464")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa797a5c-29bf-481d-8455-4fb8e616c4b5")
    protected Facade(Package elt) {
        this.elt = elt;
    }

    @objid ("5a57592c-acc8-457a-899c-a8d0cc2b60df")
    public static final class MdaTypes {
        @objid ("2b60c3ca-750d-45f2-b1bf-50be9ed2f9b6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bd68ca03-e0b4-4679-b5ce-8490191e7c26")
        private static Stereotype MDAASSOCDEP;

        @objid ("ca8321c9-6244-463c-a7b6-5467f828bffb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("00ac48da-0f47-4244-9973-de3da552cdf9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d5-0000-000000000000");
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
