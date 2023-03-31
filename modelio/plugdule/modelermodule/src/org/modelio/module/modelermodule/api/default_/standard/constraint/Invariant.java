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
 * Proxy class to handle a {@link Constraint} with << invariant >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e56d6561-c974-4569-834a-dbe37b352acd")
public class Invariant {
    @objid ("f20c4ca4-c51b-4f7b-b2fe-61765748b2d0")
    public static final String STEREOTYPE_NAME = "invariant";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("dc06ce6f-5325-4f9d-b392-07f3c3c07efb")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Invariant proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << invariant >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ac1cbd43-68b3-4ec9-8780-3f55fa20d33e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << invariant >> then instantiate a {@link Invariant} proxy.
     * 
     * @return a {@link Invariant} proxy on the created {@link Constraint}.
     */
    @objid ("94c70b4c-1c6a-4aad-a73d-db2c7e330fa2")
    public static Invariant create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME);
        return Invariant.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Invariant} proxy or <i>null</i>.
     */
    @objid ("2d688cd4-97fd-463c-bb3b-1b8ad5c276a2")
    public static Invariant instantiate(Constraint obj) {
        return Invariant.canInstantiate(obj) ? new Invariant(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Invariant} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c122cfa6-6ca0-4792-bd5e-da98c5e36d7f")
    public static Invariant safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Invariant.canInstantiate(obj))
        	return new Invariant(obj);
        else
        	throw new IllegalArgumentException("Invariant: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("52223bb7-6353-4ad9-b59c-0e45f37ca4de")
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
        Invariant other = (Invariant) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("6e4274d3-e0db-40bc-aa4a-148465dbe1bf")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("217a947a-72df-40d9-9e9c-a536bda6fdf9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("eaa3a659-5a89-4ad4-a172-a9380595f1ab")
    protected  Invariant(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2ffe4bc5-f27a-4f95-9bdc-ffea03ee62b0")
    public static final class MdaTypes {
        @objid ("3ba370f7-bfa7-48e8-9be5-7ff76d1d334b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3cbe20a8-38c5-4c95-a9e3-d6570ac3a2a7")
        private static Stereotype MDAASSOCDEP;

        @objid ("e5edbf0a-f65e-4a2d-8b78-b9e8014695c0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("243a43e4-93a9-42a2-bc32-fe3f6fc96794")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c44-0000-000000000000");
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
