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
 * Proxy class to handle a {@link Constraint} with << subset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("64070ac9-b416-414f-8cd6-63da9a9124aa")
public class Subset {
    @objid ("859d81be-676b-430a-97a7-34071d99daed")
    public static final String STEREOTYPE_NAME = "subset";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("503246e0-3667-473f-afd9-bd6ac13fd5c9")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Subset proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << subset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c837d43f-89f3-4dc8-80d0-41f47f96f02c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << subset >> then instantiate a {@link Subset} proxy.
     * 
     * @return a {@link Subset} proxy on the created {@link Constraint}.
     */
    @objid ("161624a4-12b5-4935-ace9-d9678f8c9ce2")
    public static Subset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME);
        return Subset.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Subset} proxy or <i>null</i>.
     */
    @objid ("7b0387d7-ab1e-46b7-85d0-dbfadaa0ea6f")
    public static Subset instantiate(Constraint obj) {
        return Subset.canInstantiate(obj) ? new Subset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Subset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("25f29e47-c8b0-4f9c-b721-f10d44de7cbd")
    public static Subset safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Subset.canInstantiate(obj))
        	return new Subset(obj);
        else
        	throw new IllegalArgumentException("Subset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("16f0e662-14e8-41c6-b55e-dfd61facca46")
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
        Subset other = (Subset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("944a5546-256c-4081-9892-26c299ac98e3")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("d49d5b4f-fab0-4e59-b6a9-fbd3ad41080b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2edd6d1e-4012-4fc5-ae64-13e821bb04d3")
    protected  Subset(Constraint elt) {
        this.elt = elt;
    }

    @objid ("b023fad9-6eb0-417d-83a0-1258b9a55847")
    public static final class MdaTypes {
        @objid ("0bc80d5c-5d7f-4545-a424-2183979dfd21")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1d77b622-18b1-4521-916c-17ebf15ab355")
        private static Stereotype MDAASSOCDEP;

        @objid ("26a590dd-1b7d-4c58-a2da-0cc0d01e4845")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7c599483-ae53-4406-a906-401561b2dee6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0005-0000-000000000000");
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
