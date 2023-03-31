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
 * Proxy class to handle a {@link Constraint} with << transient >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("be1ed99b-3e3c-4bf1-87aa-3fdc95e547fe")
public class Transient {
    @objid ("d55cf998-e2a3-4d39-b2cc-0bb3a3eeab0c")
    public static final String STEREOTYPE_NAME = "transient";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("4a4436ee-1860-4baa-8623-4c7ec8204283")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Transient proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << transient >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("941751c8-73b1-498d-acc9-3ff587e64f64")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << transient >> then instantiate a {@link Transient} proxy.
     * 
     * @return a {@link Transient} proxy on the created {@link Constraint}.
     */
    @objid ("89f52db1-35b2-42e7-8a37-0b8346beeebb")
    public static Transient create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME);
        return Transient.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Transient} proxy or <i>null</i>.
     */
    @objid ("62611c23-f3e6-4b72-b253-24bd07d4a245")
    public static Transient instantiate(Constraint obj) {
        return Transient.canInstantiate(obj) ? new Transient(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Transient} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("53b0840e-ad54-48a5-a87e-87b36f45d463")
    public static Transient safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Transient.canInstantiate(obj))
        	return new Transient(obj);
        else
        	throw new IllegalArgumentException("Transient: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3a4d8626-270a-4c31-a883-0997d24c7467")
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
        Transient other = (Transient) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("0300c4b0-981e-4dda-a01d-0918f0e49a4c")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("874191d6-e85e-4681-93f7-f158484d25fc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("37454ff2-6965-4e7e-a2f6-e35069e17dd8")
    protected  Transient(Constraint elt) {
        this.elt = elt;
    }

    @objid ("dd8d10f4-9b21-4aa7-810c-526be96a91dd")
    public static final class MdaTypes {
        @objid ("7bf73171-ce38-4d77-b559-142027717970")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("15d49a51-fc9f-4204-90ac-d711e09efd28")
        private static Stereotype MDAASSOCDEP;

        @objid ("991e1c15-1807-44bb-88af-f2d02c4efb13")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c4f30764-cb8c-40f7-8d15-f65bc79fb20f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fe-0000-000000000000");
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
