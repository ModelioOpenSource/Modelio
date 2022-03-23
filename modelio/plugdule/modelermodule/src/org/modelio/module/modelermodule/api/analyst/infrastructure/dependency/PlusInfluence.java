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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link Dependency} with << +influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42cd298d-9d7d-4787-9645-c8747b86583f")
public class PlusInfluence {
    @objid ("99008f14-70f8-4302-b1c7-8d912455dfe6")
    public static final String STEREOTYPE_NAME = "+influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5c0bc459-c529-4864-9ab9-ab3c2424956f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link PlusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << +influence >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("59ae8f92-94f4-40f1-a3b8-03760f456f6c")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << +influence >> then instantiate a {@link PlusInfluence} proxy.
     * @return a {@link PlusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("866b06ee-5fa8-4a1f-9ba0-a4d98fa423c5")
    public static PlusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
        return PlusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link PlusInfluence} proxy or <i>null</i>.
     */
    @objid ("a504093e-ff15-4be6-a478-096deb407bec")
    public static PlusInfluence instantiate(Dependency obj) {
        return PlusInfluence.canInstantiate(obj) ? new PlusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link PlusInfluence} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5f7b4fcf-e38e-4b72-9b65-f0e55940241e")
    public static PlusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (PlusInfluence.canInstantiate(obj)) {
            return new PlusInfluence(obj);
        } else {
            throw new IllegalArgumentException("PlusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
        }
        
    }

    @objid ("43bc2240-6a92-4977-8e82-364888c5961a")
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
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("e6241a15-8913-4532-b830-91512fd03cf1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("97cd459a-6f12-498d-80ff-c389cfe2ef9c")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("7ba630a1-85b2-4371-8e9c-45c0d9716f23")
    protected  PlusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5e2e10fd-0acf-4a71-9302-363201fdbdef")
    public static final class MdaTypes {
        @objid ("92bd57a7-88f5-48c4-afc9-0a398f87b184")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9db12791-4f2f-4b97-a250-50c3bc7538ec")
        private static Stereotype MDAASSOCDEP;

        @objid ("a96eafb4-d3bf-404d-a541-9da3f55782fd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("39500967-f9e1-4147-a187-be7567fb511b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0247-0000-000000000000");
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
