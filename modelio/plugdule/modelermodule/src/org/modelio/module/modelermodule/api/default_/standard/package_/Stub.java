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
 * Proxy class to handle a {@link Package} with << stub >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1dc07035-e0be-493c-8a41-81d1f3e2bbfe")
public class Stub {
    @objid ("4521cc52-bfd1-4587-aa9b-41b99529d674")
    public static final String STEREOTYPE_NAME = "stub";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("bd14cc0e-0f38-49a4-9145-bea5c5f8a49e")
    protected final Package elt;

    /**
     * Tells whether a {@link Stub proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << stub >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9f8979f3-e7f0-4e50-8002-d61a585013e2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << stub >> then instantiate a {@link Stub} proxy.
     * 
     * @return a {@link Stub} proxy on the created {@link Package}.
     */
    @objid ("1567c60a-8d4a-49bf-af31-eec0b9563693")
    public static Stub create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME);
        return Stub.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Stub} proxy or <i>null</i>.
     */
    @objid ("0729fd5a-e0d1-43cd-a366-df508aa51f3c")
    public static Stub instantiate(Package obj) {
        return Stub.canInstantiate(obj) ? new Stub(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Stub} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8febbf19-36dc-411b-b7a6-73985180656f")
    public static Stub safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Stub.canInstantiate(obj))
        	return new Stub(obj);
        else
        	throw new IllegalArgumentException("Stub: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("51954a0f-6792-459a-8f85-d6768926397f")
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
        Stub other = (Stub) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("074d5cc3-12a6-48cd-9d00-aae1910f72c4")
    public Package getElement() {
        return this.elt;
    }

    @objid ("ea1ce1e5-1585-4ad5-86c6-cc32553db8b3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c1e4cf24-1388-49ca-a992-65a56f5ff010")
    protected Stub(Package elt) {
        this.elt = elt;
    }

    @objid ("cbe40972-a0dc-4f70-a0d4-7a45c10fbcc0")
    public static final class MdaTypes {
        @objid ("15a779cf-c0ea-4345-8d0b-d348ae5a4c51")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1e253144-ba52-4e6d-a7e0-27312efae6e5")
        private static Stereotype MDAASSOCDEP;

        @objid ("40209e20-632f-4824-bbca-1a3676350b03")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8c13e62a-9972-410c-a37c-d3fd65c673ad")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d7-0000-000000000000");
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
