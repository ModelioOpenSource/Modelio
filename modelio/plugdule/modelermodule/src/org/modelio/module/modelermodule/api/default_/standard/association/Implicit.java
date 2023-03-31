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
package org.modelio.module.modelermodule.api.default_.standard.association;

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
 * Proxy class to handle a {@link Association} with << implicit >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a2291f1c-9e40-4062-b1f6-c4ef88ee02eb")
public class Implicit {
    @objid ("65651209-1c5a-42bd-8d33-cf917f9bf225")
    public static final String STEREOTYPE_NAME = "implicit";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("7c5621bf-2cbf-491f-b704-b0d7cc846ed3")
    protected final Association elt;

    /**
     * Tells whether a {@link Implicit proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << implicit >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("288c1444-e79a-4e40-9287-b8ab79cd3342")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << implicit >> then instantiate a {@link Implicit} proxy.
     * 
     * @return a {@link Implicit} proxy on the created {@link Association}.
     */
    @objid ("c21b8f05-83df-4621-b5cf-ad1ab55750dd")
    public static Implicit create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME);
        return Implicit.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link Implicit} proxy or <i>null</i>.
     */
    @objid ("8e822d98-6169-44e0-827a-e6e4fecfdb49")
    public static Implicit instantiate(Association obj) {
        return Implicit.canInstantiate(obj) ? new Implicit(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link Implicit} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("63e218bb-c8a5-4baa-82d4-dfca2b936c4c")
    public static Implicit safeInstantiate(Association obj) throws IllegalArgumentException {
        if (Implicit.canInstantiate(obj))
        	return new Implicit(obj);
        else
        	throw new IllegalArgumentException("Implicit: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e1544cf1-2e44-4b9b-95f7-b864509f21c4")
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
        Implicit other = (Implicit) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("a91828b5-abb3-40d0-be4f-12426c7dcc45")
    public Association getElement() {
        return this.elt;
    }

    @objid ("3e2ed014-353c-484b-9193-040ae7c69601")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("4a62adfe-0288-490e-9c80-3b49a963972e")
    protected  Implicit(Association elt) {
        this.elt = elt;
    }

    @objid ("6e6fdf9c-fd6f-4767-bf30-7a823cf4cd32")
    public static final class MdaTypes {
        @objid ("535b9210-b686-4c11-abb5-89b1b2e4e8c6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ea4cff97-6f4d-4c65-9575-ed08cb665c25")
        private static Stereotype MDAASSOCDEP;

        @objid ("6342ac39-dd08-45d3-b744-0fbe29c9ea9f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7140911f-dd26-4534-8330-ce11fb7683a4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01b8-0000-000000000000");
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
