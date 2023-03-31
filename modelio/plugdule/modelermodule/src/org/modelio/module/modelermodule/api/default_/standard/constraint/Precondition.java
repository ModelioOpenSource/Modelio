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
 * Proxy class to handle a {@link Constraint} with << precondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("193679c9-0aaf-479a-b4f9-10dfca5f464d")
public class Precondition {
    @objid ("9f0e6d2b-2cce-4610-a73c-92ef288ff66f")
    public static final String STEREOTYPE_NAME = "precondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("4cd77de3-a2a0-4e53-83a6-56217f0a7575")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Precondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << precondition >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("08d3ef73-37f5-47e0-af6c-c9db9b23d1f4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << precondition >> then instantiate a {@link Precondition} proxy.
     * 
     * @return a {@link Precondition} proxy on the created {@link Constraint}.
     */
    @objid ("aa998092-9a72-4ebd-a87f-864e0830222c")
    public static Precondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME);
        return Precondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Precondition} proxy from a {@link Constraint} stereotyped << precondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Precondition} proxy or <i>null</i>.
     */
    @objid ("fefbd319-34c5-441f-a3db-80f7f5d6a093")
    public static Precondition instantiate(Constraint obj) {
        return Precondition.canInstantiate(obj) ? new Precondition(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Precondition} proxy from a {@link Constraint} stereotyped << precondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Precondition} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d2914ca9-7621-4452-aceb-109fe14a728d")
    public static Precondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Precondition.canInstantiate(obj))
        	return new Precondition(obj);
        else
        	throw new IllegalArgumentException("Precondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("81611130-a75b-4540-891e-707997df11ec")
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
        Precondition other = (Precondition) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("3caad31d-dd85-41c1-b783-ff9b38220dd5")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("aed5da35-a382-4e65-b0c2-6522495f2b56")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6b2c574e-76e1-4f86-b753-425b4871f8dc")
    protected  Precondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("7643ddc4-75b0-4f0c-88a7-b7292095f7e2")
    public static final class MdaTypes {
        @objid ("a05ddcb5-1dbf-4c67-abb6-cc4aa8070ec0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fa841dec-d897-4010-9601-bc0e193d7728")
        private static Stereotype MDAASSOCDEP;

        @objid ("0db8a6a6-0eb1-41b0-8bc1-73a6999cdd2e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6c3a097c-9215-41a3-8981-9b319e18568f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c45-0000-000000000000");
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
