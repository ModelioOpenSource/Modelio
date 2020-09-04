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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("5625a15e-a483-40be-9503-41e3bfbef578")
    public static final String STEREOTYPE_NAME = "subset";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("e1447e01-0960-440b-90c3-9413ef39fb8f")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Subset proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << subset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cb8ce3fe-43b4-4cb5-9e01-900019452a22")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << subset >> then instantiate a {@link Subset} proxy.
     * 
     * @return a {@link Subset} proxy on the created {@link Constraint}.
     */
    @objid ("a73a09c8-e37f-4ca6-9118-c41026bcdc71")
    public static Subset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME);
        return Subset.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Subset} proxy or <i>null</i>.
     */
    @objid ("d7d95808-d12c-4e60-8c49-cac110c05464")
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
    @objid ("b080b6b9-a51a-48df-bd71-ebf7af1f915c")
    public static Subset safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Subset.canInstantiate(obj))
        	return new Subset(obj);
        else
        	throw new IllegalArgumentException("Subset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bb2d0902-6382-41a2-8f67-72f3ca997c14")
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
    @objid ("5eaaeded-d6f3-4e60-addd-73c5a070c2fd")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("4f027e48-2e59-43a4-9cea-44f9de016f84")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2e7870bd-b897-407b-a52e-b84e2ca02fbf")
    protected Subset(Constraint elt) {
        this.elt = elt;
    }

    @objid ("b023fad9-6eb0-417d-83a0-1258b9a55847")
    public static final class MdaTypes {
        @objid ("48441ded-39e7-49e9-98ef-70a8d27f151c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ac8afaad-abf7-4969-a482-bbf1067b6f3a")
        private static Stereotype MDAASSOCDEP;

        @objid ("6d57aa00-9a77-4db8-b8e8-31ed2e32fb57")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c5daa9ca-f7c8-4547-a7ae-996edf099925")
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
