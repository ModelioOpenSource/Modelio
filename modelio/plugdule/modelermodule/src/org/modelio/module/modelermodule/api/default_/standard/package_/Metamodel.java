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
 * Proxy class to handle a {@link Package} with << metamodel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a997496b-c74c-4bae-8064-327d1bfe98c0")
public class Metamodel {
    @objid ("9426f7e7-74b0-4083-b6e9-fd7f85fbb2f3")
    public static final String STEREOTYPE_NAME = "metamodel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("69e60e46-30f3-4d2e-baa6-c2a742a59edc")
    protected final Package elt;

    /**
     * Tells whether a {@link Metamodel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << metamodel >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4a68d1a7-8b07-4ad0-bec7-96b64c19ae30")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << metamodel >> then instantiate a {@link Metamodel} proxy.
     * 
     * @return a {@link Metamodel} proxy on the created {@link Package}.
     */
    @objid ("1d281025-50e1-4d8c-9c05-51ed1e32aaf8")
    public static Metamodel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME);
        return Metamodel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Metamodel} proxy or <i>null</i>.
     */
    @objid ("16447c4d-ec7e-43e9-bff8-a55d83e70410")
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
    @objid ("df2cc40c-827a-4ffe-b46f-ccfff8ee904e")
    public static Metamodel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Metamodel.canInstantiate(obj))
        	return new Metamodel(obj);
        else
        	throw new IllegalArgumentException("Metamodel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a05f05dc-4982-4505-9751-9edafd50adb0")
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
    @objid ("214dd444-ab42-48fb-abc7-11b410057f18")
    public Package getElement() {
        return this.elt;
    }

    @objid ("4986d7dd-6866-4e49-b0c9-16da0fa5cc74")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c093a9bd-3247-4ec8-b0f3-beddb9db38ca")
    protected Metamodel(Package elt) {
        this.elt = elt;
    }

    @objid ("272fd8c2-9080-44cd-9066-fd064321d028")
    public static final class MdaTypes {
        @objid ("019178cc-56c7-440a-99cd-d7e5e7883b49")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("65c8f6e7-7b4e-49c3-8e2b-3c7639650d96")
        private static Stereotype MDAASSOCDEP;

        @objid ("93b0c478-5cff-4b00-91af-931591750898")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c0a7c778-ed3a-4534-978c-faeb5c99f557")
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
