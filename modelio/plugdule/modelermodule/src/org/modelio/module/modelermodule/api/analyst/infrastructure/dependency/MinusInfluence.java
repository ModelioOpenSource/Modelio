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
 * <p>
 * Stereotype description:
 * <br/>
 * <i></i>
 * </p>
 */
@objid ("9e2b42e5-717f-4b46-98c8-1e34cddc5f83")
public class MinusInfluence {
    @objid ("39221507-ee74-4351-a9ee-48c597b5aa48")
    public static final String STEREOTYPE_NAME = "-influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a23dcef4-84f8-49b5-84f0-c37efff94948")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MinusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << -influence >>.
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5b47bc4d-3919-4341-9fd2-dd98ac6b3c2e")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << -influence >> then instantiate a {@link MinusInfluence} proxy.
     * 
     * @return a {@link MinusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("94c17a41-e79f-4ff8-a962-2aadc324c16d")
    public static MinusInfluence create() {
        ModelElement e = (ModelElement) ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
        return MinusInfluence.instantiate((Dependency) e);
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >>checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a Dependency
     * @return a {@link MinusInfluence} proxy or <i>null</i>.
     */
    @objid ("fe8bd7e2-32a7-43f1-8f25-02d06c7d5c82")
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
    @objid ("db15991d-7234-4b26-991c-c4ddc5c2bfff")
    public static MinusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MinusInfluence.canInstantiate(obj)) {
            return new MinusInfluence(obj);
        } else {
            throw new IllegalArgumentException("MinusInfluence: Cannot instantiate " + obj + ": wrong element type or stereotype");
        }
    }

    @objid ("90bb0bbb-84b3-469f-b402-2d35f058d7a6")
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
    @objid ("d3b3b48f-0044-479f-9c18-3a485a4589c5")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3d4b11d8-a845-44a7-8a09-04f0957b2f1d")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("6fe82f3d-3bfe-42ef-ad43-607b6de78bb8")
    protected MinusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("4dd72b91-2fb2-4357-b513-96ce1e76264b")
    public static final class MdaTypes {
        @objid ("27f17973-2384-4835-808a-83f7b8af70ea")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3bc61b91-96c3-46af-8ad1-0081d4a2a5b9")
        private static Stereotype MDAASSOCDEP;

        @objid ("5658bced-874c-4caa-ad67-cab9125a1044")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("84f3229b-2f60-4676-b055-a573e9527140")
        public static void init(IModuleContext ctx) {
            MdaTypes.STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-024c-0000-000000000000");
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
