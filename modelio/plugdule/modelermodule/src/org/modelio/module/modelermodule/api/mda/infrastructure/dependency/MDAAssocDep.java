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
    @objid ("ddfa7104-6967-4e8a-b7d7-775c27d293a3")
    public static final String STEREOTYPE_NAME = "MDAAssocDep";

    @objid ("fc948bfb-1521-433c-bf28-b27a0beeeb13")
    public static final String ROLE_TAGTYPE = "role";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("8391bf79-32d1-496b-b9b4-73b345e5f80e")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MDAAssocDep proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << MDAAssocDep >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("25a74c37-876b-489f-b328-213e6466b378")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << MDAAssocDep >> then instantiate a {@link MDAAssocDep} proxy.
     * 
     * @return a {@link MDAAssocDep} proxy on the created {@link Dependency}.
     */
    @objid ("b326ecef-eb22-4f3e-b4ea-9e71fc45dad4")
    public static MDAAssocDep create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME);
        return MDAAssocDep.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MDAAssocDep} proxy or <i>null</i>.
     */
    @objid ("9310fb9f-0719-4f92-bd2e-3b230b25238d")
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
    @objid ("e25bcd27-33ea-4459-a104-cc2f4918a672")
    public static MDAAssocDep safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MDAAssocDep.canInstantiate(obj))
        	return new MDAAssocDep(obj);
        else
        	throw new IllegalArgumentException("MDAAssocDep: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9bad70ac-6d00-47cb-a3a0-e09265d2fdd2")
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
    @objid ("de5fa34f-315b-4877-9aa0-31b0030e245c")
    public Dependency getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("d8261eac-7768-4411-82cd-61427e1aad87")
    public String getRole() {
        return this.elt.getTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT);
    }

    @objid ("7aa03d1b-f656-431c-b6f7-fd2103c6f74e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("b967e772-6111-44d5-98e5-f371c686a15b")
    public void setRole(String value) {
        this.elt.putTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT, value);
    }

    @objid ("7a330612-d5ad-4a51-8a66-13fd332ffb9b")
    protected MDAAssocDep(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d7dd74b-8894-4e3b-9fbc-53ed8d225760")
    public static final class MdaTypes {
        @objid ("f49d1285-e553-45ec-8d16-a1ae7e7f8564")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b3d7a5ef-b1dc-4223-aa2d-f83835909f9c")
        public static TagType ROLE_TAGTYPE_ELT;

        @objid ("0c8e2e64-c8f5-4ad5-a67c-6be709906a99")
        private static Stereotype MDAASSOCDEP;

        @objid ("eadedb31-add7-4a81-938e-60b859f17c9e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("22e99355-dd01-4b66-8407-29e25c32a22b")
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
