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
 * Proxy class to handle a {@link Constraint} with << postcondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0cfb1227-3745-4c76-8e1a-0c23d0f7a8f4")
public class Postcondition {
    @objid ("7af79d9b-48db-4bbf-80a1-bd868b8a331e")
    public static final String STEREOTYPE_NAME = "postcondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("f204e87d-8031-4b35-878b-169dd14e1f4d")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Postcondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << postcondition >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5a579138-5308-49d9-b085-2bc7399cfb0b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << postcondition >> then instantiate a {@link Postcondition} proxy.
     * 
     * @return a {@link Postcondition} proxy on the created {@link Constraint}.
     */
    @objid ("19f7b4c8-2186-4ec7-95a8-44e81085bdfd")
    public static Postcondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME);
        return Postcondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Postcondition} proxy or <i>null</i>.
     */
    @objid ("61a56f88-9f92-48f2-b757-33c59f4d6b09")
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
    @objid ("87a8423e-c553-48ed-bf04-ff0bb068e584")
    public static Postcondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Postcondition.canInstantiate(obj))
        	return new Postcondition(obj);
        else
        	throw new IllegalArgumentException("Postcondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("346a46c6-ea5f-4d55-9c6c-4126d32854ff")
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
    @objid ("c6e5cd9a-5f4b-49d3-a40f-22a2e2dbb240")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("74a916e4-e542-4890-8e06-c0ca237d616c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("dc485b1d-5737-4e65-bd39-d0c0b27a7931")
    protected  Postcondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("889980ab-e2c4-4755-bd2a-5c65867bd2d2")
    public static final class MdaTypes {
        @objid ("9d270cb0-a56e-4e49-9310-cddb07b06e75")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d10f2c73-e931-445f-813f-f2f4be605f68")
        private static Stereotype MDAASSOCDEP;

        @objid ("aadac79b-dfa5-4022-bffe-bd44da4becb2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("eacdecae-318a-4672-bd7a-bad557165480")
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
