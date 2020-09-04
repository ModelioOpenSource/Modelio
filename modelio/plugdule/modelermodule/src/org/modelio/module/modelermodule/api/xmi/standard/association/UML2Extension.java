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
package org.modelio.module.modelermodule.api.xmi.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << UML2Extension >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("91e907b3-23a7-4ab0-a65c-d0a6ef574552")
public class UML2Extension {
    @objid ("e43154ce-9a10-4013-8713-3d1ba4aad057")
    public static final String STEREOTYPE_NAME = "UML2Extension";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("98b2a506-e0c9-4fd4-9425-13325b8ccfcf")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2Extension proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2Extension >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0531b56b-896e-4dd7-85a2-e176a2b4a823")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2Extension >> then instantiate a {@link UML2Extension} proxy.
     * 
     * @return a {@link UML2Extension} proxy on the created {@link Association}.
     */
    @objid ("9531b694-477c-4500-9875-987dc9622714")
    public static UML2Extension create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME);
        return UML2Extension.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2Extension} proxy from a {@link Association} stereotyped << UML2Extension >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2Extension} proxy or <i>null</i>.
     */
    @objid ("1a76a649-53f6-48e2-8139-f3028a99006f")
    public static UML2Extension instantiate(Association obj) {
        return UML2Extension.canInstantiate(obj) ? new UML2Extension(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Extension} proxy from a {@link Association} stereotyped << UML2Extension >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2Extension} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("097d4e91-8950-4bbf-873b-bd86d5e4c355")
    public static UML2Extension safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2Extension.canInstantiate(obj))
        	return new UML2Extension(obj);
        else
        	throw new IllegalArgumentException("UML2Extension: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8e82f405-e750-4408-8d38-669dde10d55e")
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
        UML2Extension other = (UML2Extension) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("21c127e9-2b73-4558-a777-a1cd64e67d86")
    public Association getElement() {
        return this.elt;
    }

    @objid ("c87fef70-5ddc-4d30-9fc7-93807474045d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e395db8c-208d-4444-8dd0-7ef6f5d8d553")
    protected UML2Extension(Association elt) {
        this.elt = elt;
    }

    @objid ("23d7d6ba-d0db-4e7a-adcd-190ee07503b0")
    public static final class MdaTypes {
        @objid ("b8c0d2eb-4db1-4027-8e61-fb580c8326d5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("166f67c4-7eef-41ea-b6fb-8dd666780a19")
        private static Stereotype MDAASSOCDEP;

        @objid ("e57b1619-0ca0-424b-b18d-eb4a6374f3f6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("918996e8-9596-42bd-b6a7-75c294d3ee5c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4b4745a9-5d0c-11df-a996-001302895b2b");
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
