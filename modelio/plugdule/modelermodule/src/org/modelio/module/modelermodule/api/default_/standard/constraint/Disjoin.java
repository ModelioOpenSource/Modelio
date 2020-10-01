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
 * Proxy class to handle a {@link Constraint} with << disjoin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("df267913-e3d3-4867-9a81-4c7c6893fae1")
public class Disjoin {
    @objid ("bf1bb3e0-bd2d-4b2a-bcc3-77b246e9ce90")
    public static final String STEREOTYPE_NAME = "disjoin";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("9047dd30-1d4c-4589-9eb7-d4172814c15f")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Disjoin proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << disjoin >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("13cb279b-1e34-45e0-bda6-31604ed2bf99")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << disjoin >> then instantiate a {@link Disjoin} proxy.
     * 
     * @return a {@link Disjoin} proxy on the created {@link Constraint}.
     */
    @objid ("4590e56b-60a2-45c1-b4e4-39486325314a")
    public static Disjoin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME);
        return Disjoin.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Disjoin} proxy or <i>null</i>.
     */
    @objid ("b7c99e15-4d2d-4a94-b9df-98b6e7dacd34")
    public static Disjoin instantiate(Constraint obj) {
        return Disjoin.canInstantiate(obj) ? new Disjoin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Disjoin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0797a48d-9d73-4681-af89-90f28de07370")
    public static Disjoin safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Disjoin.canInstantiate(obj))
        	return new Disjoin(obj);
        else
        	throw new IllegalArgumentException("Disjoin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cbdb7afb-7811-42c2-ab0b-86d036d8a90d")
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
        Disjoin other = (Disjoin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("6f3b82b8-381b-478e-9634-d7cf0878d5b3")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("5314e0b0-72bc-4ac4-aa9f-627507db2d83")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bc40db4b-3c39-413c-bddd-6190a0bef082")
    protected Disjoin(Constraint elt) {
        this.elt = elt;
    }

    @objid ("27c0c9fd-b109-4914-a1a6-01ed7023ca30")
    public static final class MdaTypes {
        @objid ("7183a471-6889-4e31-9f11-90811e2afe77")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e9ec8639-fd7b-4df0-8812-fd4132502bc8")
        private static Stereotype MDAASSOCDEP;

        @objid ("290f5146-18c9-40e1-a21c-d4e0544dd140")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d70e6fda-70ab-4e67-8ad4-1b452b2b5f27")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f5-0000-000000000000");
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
