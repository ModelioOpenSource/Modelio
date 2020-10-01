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
 * Proxy class to handle a {@link Association} with << UML2CommunicationPath >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81678c9e-2f43-40bc-ad65-f1a578bb0dee")
public class UML2CommunicationPath {
    @objid ("4e7f4970-ad16-462e-8a97-98c72897c8b9")
    public static final String STEREOTYPE_NAME = "UML2CommunicationPath";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("ad9c55ab-a8fd-4e91-8b6d-a4eb5a70516f")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2CommunicationPath proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2CommunicationPath >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dc1d09aa-0855-49c6-80f8-7ab963d589ed")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2CommunicationPath >> then instantiate a {@link UML2CommunicationPath} proxy.
     * 
     * @return a {@link UML2CommunicationPath} proxy on the created {@link Association}.
     */
    @objid ("4ea8494a-92ab-4f92-bf29-57aae3ea8c20")
    public static UML2CommunicationPath create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME);
        return UML2CommunicationPath.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2CommunicationPath} proxy or <i>null</i>.
     */
    @objid ("c436ead0-6a5d-4daf-b872-2596c99d8bf5")
    public static UML2CommunicationPath instantiate(Association obj) {
        return UML2CommunicationPath.canInstantiate(obj) ? new UML2CommunicationPath(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2CommunicationPath} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d0ada198-633e-4b39-95c7-40f33be775bf")
    public static UML2CommunicationPath safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2CommunicationPath.canInstantiate(obj))
        	return new UML2CommunicationPath(obj);
        else
        	throw new IllegalArgumentException("UML2CommunicationPath: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dcf2a5d3-7bfe-455c-89fe-32b9c84f68ac")
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
        UML2CommunicationPath other = (UML2CommunicationPath) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("466dec24-c022-4d45-8a91-6c2898e64691")
    public Association getElement() {
        return this.elt;
    }

    @objid ("2ff7c0e8-5e72-4875-8c6d-388b41a6dfe3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a4b43b45-8f22-4ee3-9f55-5cfb8b442847")
    protected UML2CommunicationPath(Association elt) {
        this.elt = elt;
    }

    @objid ("d8b8c58e-ba0a-4cce-937a-c14207a1cafc")
    public static final class MdaTypes {
        @objid ("6577f8e6-b4a2-44ac-986a-149fc5c96d16")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f2f287ef-8ed4-441b-ab35-99cd28b86373")
        private static Stereotype MDAASSOCDEP;

        @objid ("55f7944f-6d73-4d65-9b60-a97ab9f4f5be")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e42a9814-ee25-4388-be45-db28b8fcd9a0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8ed6276e-5821-11df-be59-001302895b2b");
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
