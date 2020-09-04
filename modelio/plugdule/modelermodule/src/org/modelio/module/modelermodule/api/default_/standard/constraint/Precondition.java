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
 * Proxy class to handle a {@link Constraint} with << precondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("193679c9-0aaf-479a-b4f9-10dfca5f464d")
public class Precondition {
    @objid ("ebc0ce12-3862-4e6c-82a2-fb92e716c623")
    public static final String STEREOTYPE_NAME = "precondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("9276d86e-8765-4d8f-a1bb-a3536201dd18")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Precondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << precondition >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("58b0e156-3b79-4725-b00b-12167448bcd8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << precondition >> then instantiate a {@link Precondition} proxy.
     * 
     * @return a {@link Precondition} proxy on the created {@link Constraint}.
     */
    @objid ("e8233c9a-a73b-46bc-9e96-381a836431e0")
    public static Precondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME);
        return Precondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Precondition} proxy from a {@link Constraint} stereotyped << precondition >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Precondition} proxy or <i>null</i>.
     */
    @objid ("201e191c-7086-4add-9957-92c3c119bb31")
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
    @objid ("9f8752e3-ba48-4756-9292-5d185801b1dd")
    public static Precondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Precondition.canInstantiate(obj))
        	return new Precondition(obj);
        else
        	throw new IllegalArgumentException("Precondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("770686b8-f542-4829-aefe-fed72e48711b")
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
    @objid ("561bb9ee-701b-407f-96f9-fa8fb3f8aecd")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("090d7838-57d0-4ed7-92c3-9db0d615862d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4c70bf34-c373-4488-9f29-78e7db470d4c")
    protected Precondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("7643ddc4-75b0-4f0c-88a7-b7292095f7e2")
    public static final class MdaTypes {
        @objid ("728d4641-a1e5-439a-9986-84bb7722e273")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e459182b-161c-4308-8652-4ef4c89b423e")
        private static Stereotype MDAASSOCDEP;

        @objid ("f5d665bb-a78e-46f6-aeaa-069379a02260")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8e4d1e05-dfc1-4358-b7e0-1646c587ca59")
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
