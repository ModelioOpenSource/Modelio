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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << complete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("949d7ce8-5626-43fd-86c7-a67be11c28f1")
public class Complete {
    @objid ("09cec22c-3633-4f2f-a131-efb11cc4f2bb")
    public static final String STEREOTYPE_NAME = "complete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("1d9bb229-488f-45d5-aa32-b5dabdf03e30")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Complete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << complete >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b778afe5-8348-4fda-afcd-169f4f6176e6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << complete >> then instantiate a {@link Complete} proxy.
     * 
     * @return a {@link Complete} proxy on the created {@link Constraint}.
     */
    @objid ("80be79f0-40db-4c4f-8035-872fe9e2ee71")
    public static Complete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME);
        return Complete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Complete} proxy or <i>null</i>.
     */
    @objid ("03d32ae0-7291-4577-90b6-8a2eef26c259")
    public static Complete instantiate(Constraint obj) {
        return Complete.canInstantiate(obj) ? new Complete(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Complete} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("49e0aa78-c74b-4a1c-9e5f-d7770032e074")
    public static Complete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Complete.canInstantiate(obj))
        	return new Complete(obj);
        else
        	throw new IllegalArgumentException("Complete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1e69b89b-64cd-4603-a100-b74c855d782e")
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
        Complete other = (Complete) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("fb7619e1-d3b2-44fa-9a3b-de1c19487f93")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("362a5137-357b-4059-b8e6-e29aeb5a1687")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("15d33a86-1ed2-44fd-979f-93e9a2f1d6b5")
    protected  Complete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("6ee7387e-71e0-4229-bfbd-e7a7e66d6948")
    public static final class MdaTypes {
        @objid ("a9e7f0e1-968f-4684-a5a4-bd3f70f7d13f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f8d5b930-21e0-4f5e-8093-150e721dc4df")
        private static Stereotype MDAASSOCDEP;

        @objid ("dbf142fe-5790-4d52-baeb-09467b543947")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3dbeb04f-808c-4753-85f0-a116a05ccd2c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f1-0000-000000000000");
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
