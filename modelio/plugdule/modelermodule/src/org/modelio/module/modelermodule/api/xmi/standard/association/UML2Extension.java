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
    @objid ("4701d77a-bca3-4769-9f9c-ace02381bc32")
    public static final String STEREOTYPE_NAME = "UML2Extension";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("8e534191-29af-47d8-b0e6-8e2b80e14a63")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2Extension proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2Extension >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0e3eb537-d529-456c-b4c0-66dbbe3b838e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2Extension >> then instantiate a {@link UML2Extension} proxy.
     * 
     * @return a {@link UML2Extension} proxy on the created {@link Association}.
     */
    @objid ("2be3d41e-a6b2-46e9-9a67-354a0390ebe3")
    public static UML2Extension create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Association");
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
    @objid ("0eab4237-e7b7-49e9-841d-30f4a3492a6b")
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
    @objid ("7ad191e5-5cb1-4ec3-94ab-192ee044bc40")
    public static UML2Extension safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2Extension.canInstantiate(obj))
        	return new UML2Extension(obj);
        else
        	throw new IllegalArgumentException("UML2Extension: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("79fe5258-616a-449f-89a2-b879dda050c7")
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
    @objid ("662f91f5-71e6-4375-bafc-643be6955cf1")
    public Association getElement() {
        return this.elt;
    }

    @objid ("d03cc686-f774-4dd2-8fb9-ad7d2b6fb940")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c27a3381-8f2f-4cd9-8d27-b1db2d67151e")
    protected  UML2Extension(Association elt) {
        this.elt = elt;
    }

    @objid ("23d7d6ba-d0db-4e7a-adcd-190ee07503b0")
    public static final class MdaTypes {
        @objid ("4688e4c0-50e9-4f1f-a8b9-55a4501fd7bb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5a5ea28b-59e0-4f5c-918f-102ed2fb1f40")
        private static Stereotype MDAASSOCDEP;

        @objid ("2b42d5e1-6a4c-46c7-b329-a30c09aedc32")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("74fc1cf4-c045-4670-93b8-6f3217a0bcee")
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
