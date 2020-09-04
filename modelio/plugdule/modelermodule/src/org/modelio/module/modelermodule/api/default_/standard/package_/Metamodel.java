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
 * Proxy class to handle a {@link Package} with << metamodel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a997496b-c74c-4bae-8064-327d1bfe98c0")
public class Metamodel {
    @objid ("b12a13e8-30e8-4b03-a29f-3e39d8f6af1d")
    public static final String STEREOTYPE_NAME = "metamodel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("d6db2050-12a7-4b65-a86a-fddc600ce4f6")
    protected final Package elt;

    /**
     * Tells whether a {@link Metamodel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << metamodel >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4aec3868-a28b-45ea-805d-f8870993005f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << metamodel >> then instantiate a {@link Metamodel} proxy.
     * 
     * @return a {@link Metamodel} proxy on the created {@link Package}.
     */
    @objid ("94190eb0-7675-4c8b-bce5-19b42f0a0852")
    public static Metamodel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME);
        return Metamodel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Metamodel} proxy or <i>null</i>.
     */
    @objid ("2a949da8-5e1f-4569-aa55-a2c1150d83a7")
    public static Metamodel instantiate(Package obj) {
        return Metamodel.canInstantiate(obj) ? new Metamodel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Metamodel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("09ab1541-2f57-44d4-85e5-6c5aee530cee")
    public static Metamodel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Metamodel.canInstantiate(obj))
        	return new Metamodel(obj);
        else
        	throw new IllegalArgumentException("Metamodel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d0a58e02-3e77-4b12-b321-b8c726d8989a")
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
        Metamodel other = (Metamodel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("ca590d2c-0e59-45e7-a587-3eb000c1e5b4")
    public Package getElement() {
        return this.elt;
    }

    @objid ("4b7105ca-0fdf-4b66-b443-1ad97bc557a5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("14922a90-ed2a-410d-b7f3-654ca29b85f1")
    protected Metamodel(Package elt) {
        this.elt = elt;
    }

    @objid ("272fd8c2-9080-44cd-9066-fd064321d028")
    public static final class MdaTypes {
        @objid ("1f2a0234-99cd-4bc3-b678-45367f86c99c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0c6f10a5-f730-4767-9b6a-527a2cb0f609")
        private static Stereotype MDAASSOCDEP;

        @objid ("48958429-a769-4805-9c3c-07be417eaf32")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b0170c78-d080-42dd-96ac-61c4ff5fb73d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e7-0000-000000000000");
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
