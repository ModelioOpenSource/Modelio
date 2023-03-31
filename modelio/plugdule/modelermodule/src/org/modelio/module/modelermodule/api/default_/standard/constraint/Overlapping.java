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
 * Proxy class to handle a {@link Constraint} with << overlapping >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("56431f9b-f67b-4509-9a09-2227ea5aef29")
public class Overlapping {
    @objid ("ed0b4a6d-bda6-4c05-854e-8c686170fda8")
    public static final String STEREOTYPE_NAME = "overlapping";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("8fe51d2e-541c-4ac1-b396-500f3bb50a27")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Overlapping proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << overlapping >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("005fe14f-1e22-435c-93f2-556063b6fb60")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << overlapping >> then instantiate a {@link Overlapping} proxy.
     * 
     * @return a {@link Overlapping} proxy on the created {@link Constraint}.
     */
    @objid ("97184fca-55f6-4004-bd4d-9329816314ba")
    public static Overlapping create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME);
        return Overlapping.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Overlapping} proxy or <i>null</i>.
     */
    @objid ("5a8fb3c4-0a01-4a75-986d-716333913ee5")
    public static Overlapping instantiate(Constraint obj) {
        return Overlapping.canInstantiate(obj) ? new Overlapping(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Overlapping} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7a7ddba9-f897-4858-a0cc-cd9e9ab8b07b")
    public static Overlapping safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Overlapping.canInstantiate(obj))
        	return new Overlapping(obj);
        else
        	throw new IllegalArgumentException("Overlapping: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("29bbd9fc-f00e-4d67-b41c-1ef8a83db1b1")
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
        Overlapping other = (Overlapping) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("764a798b-6acc-41cb-a3fc-927b10f99338")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("432c6003-1a8c-46cf-a6d6-d8825683d9aa")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d8b6b7eb-6f76-4313-a147-6bddddb9988d")
    protected  Overlapping(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ae8f7c08-3dd0-4e2e-9abe-678cf39942a8")
    public static final class MdaTypes {
        @objid ("efeddc5c-f504-4fad-9175-1ae880aa2395")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d84c7c56-d818-4c8d-a82b-9ed12b844210")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b0ac036-3cd5-4e07-b8b6-8924a2fa3168")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("197b8a8b-8db8-4c2d-8717-1e47b39c8e6e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fb-0000-000000000000");
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
