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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << +influence >> stereotype.
 * <p>
 * Stereotype description:
 * <br/>
 * <i></i>
 * </p>
 */
@objid ("42cd298d-9d7d-4787-9645-c8747b86583f")
public class PlusInfluence {
    @objid ("f6a1e081-5a3f-46b8-a942-a577b785b9ef")
    public static final String STEREOTYPE_NAME = "+influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("29a6417b-fc8f-405a-8a6b-e1719f5cc160")
    protected final Dependency elt;

    /**
     * Tells whether a {@link PlusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << +influence >>.
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c12a3e49-a53d-4b3f-a3bb-5f13c7e39d0d")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << +influence >> then instantiate a {@link PlusInfluence} proxy.
     * 
     * @return a {@link PlusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("1eef94c6-a981-4a6e-a921-5a0159c278b2")
    public static PlusInfluence create() {
        ModelElement e = (ModelElement) ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
        return PlusInfluence.instantiate((Dependency) e);
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >>checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a Dependency
     * @return a {@link PlusInfluence} proxy or <i>null</i>.
     */
    @objid ("15778364-a1c6-4e00-9eb2-1e8ba2f2f4d8")
    public static PlusInfluence instantiate(Dependency obj) {
        return PlusInfluence.canInstantiate(obj) ? new PlusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * 
     * @param obj a {@link Dependency}
     * @return a {@link PlusInfluence} proxy.
     * @throws java.lang.IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3864bfe1-9148-40a9-95bb-fd33e2c6f4c1")
    public static PlusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (PlusInfluence.canInstantiate(obj)) {
            return new PlusInfluence(obj);
        } else {
            throw new IllegalArgumentException("PlusInfluence: Cannot instantiate " + obj + ": wrong element type or stereotype");
        }
    }

    @objid ("5b1414fe-88e7-486f-bb32-5ae43a81efcc")
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
        PlusInfluence other = (PlusInfluence) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}.
     * 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("93cb15dd-e220-480f-b85b-7d5c1ab1d316")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("5682ebda-8cb2-47b8-93dc-ddaf4a8fe688")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("e6003009-8970-487f-b4c1-a38c36544acd")
    protected PlusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5e2e10fd-0acf-4a71-9302-363201fdbdef")
    public static final class MdaTypes {
        @objid ("6e8745d7-7083-42a0-b425-65b780a761a2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1027beaf-4531-42f6-8721-9be8cfba2779")
        private static Stereotype MDAASSOCDEP;

        @objid ("15c7d4b5-730f-4401-a81b-abde2e80444f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ae76ab80-54fa-4d46-814d-185f6951907f")
        public static void init(IModuleContext ctx) {
            MdaTypes.STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0247-0000-000000000000");
            MdaTypes.MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MdaTypes.MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


static {
            if (ModelerModuleModule.getInstance() != null) {
                init(ModelerModuleModule.getInstance().getModuleContext());
            }
        }
    }

}
