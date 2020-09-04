/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link Constraint} with << postcondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0cfb1227-3745-4c76-8e1a-0c23d0f7a8f4")
public class Postcondition {
    @objid ("8319a273-c87d-4d43-8c13-6c1468921c6b")
    public static final String STEREOTYPE_NAME = "postcondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("791d6814-9b62-41df-bea0-c2b58527bbaa")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Postcondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << postcondition >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6995cec8-775f-4cfb-8fa1-0f817c3ab70a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << postcondition >> then instantiate a {@link Postcondition} proxy.
     * 
     * @return a {@link Postcondition} proxy on the created {@link Constraint}.
     */
    @objid ("7eed8a03-a556-4a49-bee4-37134a5a70fb")
    public static Postcondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME);
        return Postcondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Postcondition} proxy or <i>null</i>.
     */
    @objid ("05e4f065-9ebf-48e0-8225-7503936d11de")
    public static Postcondition instantiate(Constraint obj) {
        return Postcondition.canInstantiate(obj) ? new Postcondition(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Postcondition} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e1b87b5a-ba4c-4cee-8c73-7ac9ea26f66d")
    public static Postcondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Postcondition.canInstantiate(obj))
        	return new Postcondition(obj);
        else
        	throw new IllegalArgumentException("Postcondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d2a65b53-014a-4b08-b81c-49ef93c0d821")
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
        Postcondition other = (Postcondition) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("03cb32cd-ff43-4724-924d-706c2f2bb133")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("ae0881a9-9fbe-4b69-91ee-9c3605691d13")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("97d6b013-5edc-4fc7-aa43-2e284e5b6757")
    protected Postcondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("889980ab-e2c4-4755-bd2a-5c65867bd2d2")
    public static final class MdaTypes {
        @objid ("506c34a1-832c-4355-816f-309575d82fdc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9436edfc-61ed-428d-ab89-20d02c149855")
        private static Stereotype MDAASSOCDEP;

        @objid ("b8df32b7-ddc7-4b7d-ae2b-df74d07801fb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5d9ae138-b288-45f4-8d25-64b79e28ef29")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c46-0000-000000000000");
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
