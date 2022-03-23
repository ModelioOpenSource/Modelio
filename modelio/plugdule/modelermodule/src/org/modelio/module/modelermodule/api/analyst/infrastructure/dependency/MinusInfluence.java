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
 * Proxy class to handle a {@link Dependency} with << -influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9e2b42e5-717f-4b46-98c8-1e34cddc5f83")
public class MinusInfluence {
    @objid ("067154dd-fd04-4dea-9e12-4b40a053fe45")
    public static final String STEREOTYPE_NAME = "-influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("17c06352-128f-4437-9213-c3bcc7be0d62")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MinusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << -influence >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("df6d4840-8713-49bb-bf2c-18525dbd3773")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << -influence >> then instantiate a {@link MinusInfluence} proxy.
     * @return a {@link MinusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("5455148f-b0b3-4a94-99d8-c0c86a0305e1")
    public static MinusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
        return MinusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >> checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MinusInfluence} proxy or <i>null</i>.
     */
    @objid ("eff094f0-bcc9-4fcd-83b8-411d6581ea0a")
    public static MinusInfluence instantiate(Dependency obj) {
        return MinusInfluence.canInstantiate(obj) ? new MinusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link MinusInfluence} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ba5c2c3d-c69c-4a61-a555-8f5c5d9fec78")
    public static MinusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MinusInfluence.canInstantiate(obj)) {
            return new MinusInfluence(obj);
        } else {
            throw new IllegalArgumentException("MinusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
        }
        
    }

    @objid ("9c79ac8d-dd8d-4fdb-a2aa-0615f690f240")
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
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("932c7f72-f367-445f-8989-51eefe4c1836")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ce0d2cfb-b444-42ca-88a1-ab5777bb81e2")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("cdd3d0ec-e82d-48cd-b878-69ad023348ce")
    protected  MinusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("4dd72b91-2fb2-4357-b513-96ce1e76264b")
    public static final class MdaTypes {
        @objid ("7fb3bce8-2b65-49a0-9ec2-9b66575e9818")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7c737252-8462-4f5a-9be4-67732734482e")
        private static Stereotype MDAASSOCDEP;

        @objid ("7474d5d5-d13a-472a-8aaa-49efd98356ec")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dcc65ad1-5be8-4ae7-9acf-f23669456b26")
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
