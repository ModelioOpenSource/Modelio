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
 * Proxy class to handle a {@link Constraint} with << destroyed >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5f98eedb-1fd0-40e9-8dd9-638944831a91")
public class Destroyed {
    @objid ("8944b14f-46e4-47c2-ab96-a7a52e491326")
    public static final String STEREOTYPE_NAME = "destroyed";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("8cd81c59-c6c8-4460-b66e-aed9c58d9b51")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Destroyed proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << destroyed >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a64f5310-39b3-49ae-9d5b-55a2c09c358c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << destroyed >> then instantiate a {@link Destroyed} proxy.
     * 
     * @return a {@link Destroyed} proxy on the created {@link Constraint}.
     */
    @objid ("d2ce909b-66d2-43ba-80cb-0d2e25aa93dc")
    public static Destroyed create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME);
        return Destroyed.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Destroyed} proxy or <i>null</i>.
     */
    @objid ("9dc686ad-f488-4bd0-8375-232b74b9334a")
    public static Destroyed instantiate(Constraint obj) {
        return Destroyed.canInstantiate(obj) ? new Destroyed(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Destroyed} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e5d06323-133c-43e7-8820-4756c1df38fd")
    public static Destroyed safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Destroyed.canInstantiate(obj))
        	return new Destroyed(obj);
        else
        	throw new IllegalArgumentException("Destroyed: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c4708c70-554f-4712-a6e2-6bac87eac256")
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
        Destroyed other = (Destroyed) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("c7b38486-be3d-422f-a21b-bfd2d7034ffe")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("2494641c-5c71-45a5-89b1-3ef48f3be52a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("fb0b4573-e3ac-4890-bbed-49526f8f0162")
    protected  Destroyed(Constraint elt) {
        this.elt = elt;
    }

    @objid ("74ba3d8d-a1db-491d-89b9-617b42eb8014")
    public static final class MdaTypes {
        @objid ("8cb70684-2fcf-477a-8945-cfbc55b6c5dd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d914b968-f05f-4eab-874b-86fc5275ae88")
        private static Stereotype MDAASSOCDEP;

        @objid ("d49bfd76-7d70-43e6-b19d-22c96c875d16")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d1d723cf-63b1-425f-ba3d-e671603ae7b9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f3-0000-000000000000");
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
