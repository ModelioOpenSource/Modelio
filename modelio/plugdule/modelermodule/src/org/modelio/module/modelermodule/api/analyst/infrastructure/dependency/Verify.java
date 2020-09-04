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
 * Proxy class to handle a {@link Dependency} with << verify >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("844b6b14-0353-4b18-9447-12baf0537b75")
public class Verify {
    @objid ("756b7d43-ec55-4744-bfdd-cb57a1b05627")
    public static final String STEREOTYPE_NAME = "verify";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("9a85fe3e-fdd7-4f4c-95ea-b05b331d6721")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Verify proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << verify >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9d678318-e341-4947-963f-f90faef6f790")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << verify >> then instantiate a {@link Verify} proxy.
     * 
     * @return a {@link Verify} proxy on the created {@link Dependency}.
     */
    @objid ("7e9ac703-7e3e-4a2c-9029-9f836342820b")
    public static Verify create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME);
        return Verify.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Verify} proxy or <i>null</i>.
     */
    @objid ("6fb636ea-a38b-453a-840a-badf72220696")
    public static Verify instantiate(Dependency obj) {
        return Verify.canInstantiate(obj) ? new Verify(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Verify} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b6d4e612-22e7-49e2-8743-14de240a2ba3")
    public static Verify safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Verify.canInstantiate(obj))
        	return new Verify(obj);
        else
        	throw new IllegalArgumentException("Verify: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9a827a68-5de9-439e-9051-4d66735c1b55")
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
        Verify other = (Verify) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("990390d6-7970-47af-bf95-bdb86506aed2")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b572bc3b-3326-48b9-b6e1-c65de195019b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dfa20be9-527e-4486-99df-e072b1f8a728")
    protected Verify(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e2805429-a662-4e03-8705-adeea09fdcc7")
    public static final class MdaTypes {
        @objid ("b8d4e6e7-744f-4e07-9a11-4b2d999edcfb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1d86bd63-74a8-4af8-a55e-651a1c754046")
        private static Stereotype MDAASSOCDEP;

        @objid ("008c0c41-7224-4aad-882b-2df2f80ff8dc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dcd204d8-daf6-450b-9073-75fb7d91468b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0229-0000-000000000000");
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
