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
package org.modelio.module.modelermodule.api.mda.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << MDAAssocDep >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Used to mark Dependencies representing an emulated association in a MDA profiled model.</i></p>
 */
@objid ("ff4c64b9-ac0c-4cbb-b591-dd6886604c51")
public class MDAAssocDep {
    @objid ("ebb42f9c-924e-4d32-8334-3ff6b3f54f92")
    public static final String STEREOTYPE_NAME = "MDAAssocDep";

    @objid ("a9631f70-3b6d-48d8-a114-7a8ff9c79ed9")
    public static final String ROLE_TAGTYPE = "role";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e0893b3a-5ede-4222-8a4b-adc64760404c")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MDAAssocDep proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << MDAAssocDep >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("77b0cb35-a901-474f-ac64-e587b1d9c63e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << MDAAssocDep >> then instantiate a {@link MDAAssocDep} proxy.
     * 
     * @return a {@link MDAAssocDep} proxy on the created {@link Dependency}.
     */
    @objid ("d8bbd6d8-ff22-4210-81db-d4fd7719a10c")
    public static MDAAssocDep create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME);
        return MDAAssocDep.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MDAAssocDep} proxy or <i>null</i>.
     */
    @objid ("38fcd0e8-130c-4825-bacf-139eaaae789d")
    public static MDAAssocDep instantiate(Dependency obj) {
        return MDAAssocDep.canInstantiate(obj) ? new MDAAssocDep(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link MDAAssocDep} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c111867e-ffac-4911-b5fb-9eecc43e5f93")
    public static MDAAssocDep safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MDAAssocDep.canInstantiate(obj))
        	return new MDAAssocDep(obj);
        else
        	throw new IllegalArgumentException("MDAAssocDep: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f781c761-45d5-4a00-af96-6bf7ecdeb775")
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
        MDAAssocDep other = (MDAAssocDep) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("dbcb7b30-f6ba-48a5-bd9c-be2d8833e188")
    public Dependency getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("803ddbe8-bac6-43c1-af42-d9b72a11005d")
    public String getRole() {
        return this.elt.getTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT);
    }

    @objid ("eb43cd40-9f45-4744-bb2b-05a0976328ca")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("65673ae4-1ee9-4b23-9752-22a7504c9a02")
    public void setRole(String value) {
        this.elt.putTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT, value);
    }

    @objid ("bbce3f2e-7f94-4e0e-ab80-9a669ab92c3d")
    protected MDAAssocDep(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d7dd74b-8894-4e3b-9fbc-53ed8d225760")
    public static final class MdaTypes {
        @objid ("f339c88e-e9b2-40b0-bb42-b67cd468c988")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e9d40dd-0648-436b-83f6-53c6fe541203")
        public static TagType ROLE_TAGTYPE_ELT;

        @objid ("9d69a222-414a-4160-b177-ec747b6084d6")
        private static Stereotype MDAASSOCDEP;

        @objid ("c6552487-aa12-4060-9680-04d25a5566c1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("67c5a7e2-a1cc-4bc7-9468-efc4218acb1c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            ROLE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
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
