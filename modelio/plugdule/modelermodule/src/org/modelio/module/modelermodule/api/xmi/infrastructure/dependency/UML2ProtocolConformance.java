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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2ProtocolConformance >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fd03066d-0a51-4fb7-9404-e4fdea747c28")
public class UML2ProtocolConformance {
    @objid ("ee7fe327-29c3-4712-897c-154b10a1e393")
    public static final String STEREOTYPE_NAME = "UML2ProtocolConformance";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fd1b5399-07ad-4b6c-880c-6f4e0331f222")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ProtocolConformance proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ProtocolConformance >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a48b531a-6904-4aab-9316-8088a2b04193")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ProtocolConformance >> then instantiate a {@link UML2ProtocolConformance} proxy.
     * 
     * @return a {@link UML2ProtocolConformance} proxy on the created {@link Dependency}.
     */
    @objid ("dc0e5dcb-77b0-466f-8f5b-f0e8c423479c")
    public static UML2ProtocolConformance create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME);
        return UML2ProtocolConformance.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ProtocolConformance} proxy or <i>null</i>.
     */
    @objid ("c67e7030-3249-4b1c-9614-7f50b0929e67")
    public static UML2ProtocolConformance instantiate(Dependency obj) {
        return UML2ProtocolConformance.canInstantiate(obj) ? new UML2ProtocolConformance(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ProtocolConformance} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("33cfa795-2770-4452-87e5-db334aad45cb")
    public static UML2ProtocolConformance safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ProtocolConformance.canInstantiate(obj))
        	return new UML2ProtocolConformance(obj);
        else
        	throw new IllegalArgumentException("UML2ProtocolConformance: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("13c11c47-1f2b-46d8-a200-06f957b787f6")
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
        UML2ProtocolConformance other = (UML2ProtocolConformance) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("96cd873e-1cf4-4693-8593-b90a68d57b22")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("81341ef1-19f8-45d8-836d-b85a22c6b75a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("64c796b6-7a3b-4545-831e-9a534f63ad79")
    protected  UML2ProtocolConformance(Dependency elt) {
        this.elt = elt;
    }

    @objid ("04468c8e-8b2c-41f7-86f3-27c6af364806")
    public static final class MdaTypes {
        @objid ("dbee5c35-21ba-4068-9bbd-5591077aa736")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a7b30b92-f221-41a1-a52e-1be99fc33918")
        private static Stereotype MDAASSOCDEP;

        @objid ("d45cd6ce-ca86-4607-b214-bc531e271548")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3debba77-1773-4118-a243-27e1393af709")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3edfb381-5d0d-11df-a996-001302895b2b");
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
