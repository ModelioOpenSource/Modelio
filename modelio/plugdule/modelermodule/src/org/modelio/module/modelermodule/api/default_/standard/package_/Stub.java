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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("58566acc-b4f7-4c50-9b9b-4158cc8a067f")
    public static final String STEREOTYPE_NAME = "stub";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("6a84db71-7081-442b-b233-c34e9e1773eb")
    protected final Package elt;

    /**
     * Tells whether a {@link Stub proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << stub >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("85518fd3-490a-4982-911e-1dc83f89ffff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << stub >> then instantiate a {@link Stub} proxy.
     * 
     * @return a {@link Stub} proxy on the created {@link Package}.
     */
    @objid ("8752a2be-7ec9-4e1d-b620-1c3f2f44670e")
    public static Stub create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME);
        return Stub.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Stub} proxy or <i>null</i>.
     */
    @objid ("66b2d616-ad06-4491-aec6-6c201ca2849c")
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
    @objid ("4be7ed81-79c1-46de-bbad-52577d3512a8")
    public static Stub safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Stub.canInstantiate(obj))
        	return new Stub(obj);
        else
        	throw new IllegalArgumentException("Stub: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("51edd0f5-71d2-4c07-a3db-71d80cf5b2f4")
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
    @objid ("da9e364b-3d70-4dc3-9ed9-9a0789adc0ab")
    public Package getElement() {
        return this.elt;
    }

    @objid ("78eaddd4-38bf-4e40-9521-e00573d9eb1d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("be2867e4-2bb1-4b0b-a9e6-7ab383752980")
    protected  Stub(Package elt) {
        this.elt = elt;
    }

    @objid ("cbe40972-a0dc-4f70-a0d4-7a45c10fbcc0")
    public static final class MdaTypes {
        @objid ("7d987242-e14c-45c7-a4bc-7aa8768dbd92")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6c74b206-56b2-45fa-a1fb-94b2b11f2824")
        private static Stereotype MDAASSOCDEP;

        @objid ("de4fb25a-97db-4218-a3e5-af7fc77dccc6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("571d846e-4491-42b0-8427-c71054af9e79")
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
