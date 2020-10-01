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
 * Proxy class to handle a {@link Dependency} with << -influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9e2b42e5-717f-4b46-98c8-1e34cddc5f83")
public class MinusInfluence {
    @objid ("4a8bf3fb-2e2c-4b70-a442-a63426758778")
    public static final String STEREOTYPE_NAME = "-influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fdbf3a7c-4ca4-446b-b536-7895cb416b73")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MinusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << -influence >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ea17432a-bd47-4ac3-9edd-f65fe9d90c1f")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << -influence >> then instantiate a {@link MinusInfluence} proxy.
     * 
     * @return a {@link MinusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("53a130c7-8ca4-405d-8830-e62870552762")
    public static MinusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
        return MinusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >> checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a Dependency
     * @return a {@link MinusInfluence} proxy or <i>null</i>.
     */
    @objid ("0f8f34f8-bf51-4a62-99ef-2d61666faee4")
    public static MinusInfluence instantiate(Dependency obj) {
        return MinusInfluence.canInstantiate(obj) ? new MinusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * 
     * @param obj a {@link Dependency}
     * @return a {@link MinusInfluence} proxy.
     * @throws java.lang.IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("54f199f2-941c-4c4b-b1d9-f7d28d8443de")
    public static MinusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MinusInfluence.canInstantiate(obj)) {
            return new MinusInfluence(obj);
        } else {
            throw new IllegalArgumentException("MinusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
        }
    }

    @objid ("6707ec2b-93bc-4a7e-be59-9ec002d97c4f")
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
        MinusInfluence other = (MinusInfluence) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}.
     * 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a9f428b2-d30c-4854-912b-910874961d8c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("464f60b6-7d12-4d02-a502-d0654caf5954")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("2f3f3a0a-26f8-4de2-a747-42b884948923")
    protected MinusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("4dd72b91-2fb2-4357-b513-96ce1e76264b")
    public static final class MdaTypes {
        @objid ("3ad87d2a-7952-4044-b8eb-64b5da844737")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("82110cea-6c5e-4834-b9f1-f1566e388b58")
        private static Stereotype MDAASSOCDEP;

        @objid ("64c701e1-fe12-4ed1-9a33-4a139929a386")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f37e383-c580-45f6-b03f-512cd69fd587")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-024c-0000-000000000000");
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
