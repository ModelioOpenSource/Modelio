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
 * Proxy class to handle a {@link Constraint} with << precondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("193679c9-0aaf-479a-b4f9-10dfca5f464d")
public class Precondition {
    @objid ("fb15bd53-ba8a-40b3-8676-28c3359325bf")
    public static final String STEREOTYPE_NAME = "precondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("9bb8396a-516b-4c7c-9c64-4b15f77168f9")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Precondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << precondition >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b1f7ee63-2a05-40fa-9dc0-80b2ea673de2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << precondition >> then instantiate a {@link Precondition} proxy.
     * 
     * @return a {@link Precondition} proxy on the created {@link Constraint}.
     */
    @objid ("754d01e7-92e8-4bbe-b26b-55f70d026257")
    public static Precondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
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
    @objid ("58ade0e1-3ede-4528-8030-f2e67c3b6921")
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
    @objid ("a71bb735-88ea-4c56-a914-4f8f76507bba")
    public static Precondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Precondition.canInstantiate(obj))
        	return new Precondition(obj);
        else
        	throw new IllegalArgumentException("Precondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("518909a9-c2b7-4254-90c1-dc32c66a548c")
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
    @objid ("3649f9af-6749-4696-aad5-b9016db22bfd")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("3d0fd36a-cb3f-491d-aa36-ab02426423e5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f484eea9-7eaa-410d-899f-de0b8b2aa9ee")
    protected Precondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("7643ddc4-75b0-4f0c-88a7-b7292095f7e2")
    public static final class MdaTypes {
        @objid ("f00b6ea9-a8d7-49b6-bef1-0a76a3558703")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b93482f3-7df9-461e-81cd-b01fd654cb82")
        private static Stereotype MDAASSOCDEP;

        @objid ("34cae902-30a3-41a1-9dfc-5ca834a28951")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("40668632-bd24-4982-9a12-f21c56c81d9e")
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
