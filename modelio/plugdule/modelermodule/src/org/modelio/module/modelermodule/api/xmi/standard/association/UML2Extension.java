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
    @objid ("e17f6ee6-f271-4ee9-ab34-b0e41f9c2d2b")
    public static final String STEREOTYPE_NAME = "UML2Extension";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("1ecf2e04-507a-4ef9-9cab-4fa56d583fac")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2Extension proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2Extension >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("91de8cad-5f04-43da-a9b1-904eda4d6231")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2Extension >> then instantiate a {@link UML2Extension} proxy.
     * 
     * @return a {@link UML2Extension} proxy on the created {@link Association}.
     */
    @objid ("f15a5b24-dd5e-417f-b95e-4b433cf5c5ee")
    public static UML2Extension create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME);
        return UML2Extension.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2Extension} proxy from a {@link Association} stereotyped << UML2Extension >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2Extension} proxy or <i>null</i>.
     */
    @objid ("f433ae69-a4f4-46de-9145-c8f6c766adbe")
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
    @objid ("96546dae-5303-4ae1-a905-93928bfed153")
    public static UML2Extension safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2Extension.canInstantiate(obj))
        	return new UML2Extension(obj);
        else
        	throw new IllegalArgumentException("UML2Extension: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2fbe35f6-8641-49b2-8514-23908f50c0ae")
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
    @objid ("09dfdc55-cc46-4e12-ae28-23090b3254f4")
    public Association getElement() {
        return this.elt;
    }

    @objid ("fe0b8704-237f-4fac-8aae-cabc02bad84d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("812e8a8c-18b5-4685-9785-10bdabf6ec28")
    protected UML2Extension(Association elt) {
        this.elt = elt;
    }

    @objid ("23d7d6ba-d0db-4e7a-adcd-190ee07503b0")
    public static final class MdaTypes {
        @objid ("71aadbf2-69e6-40e4-8cde-97ae0606575a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("57a7a025-cf9c-4020-a9e8-4828703b15f8")
        private static Stereotype MDAASSOCDEP;

        @objid ("d3a99d83-bd02-418d-a499-d94cfa64492b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ec155e4c-d598-4e1b-bfd1-8b4650f5a18a")
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
