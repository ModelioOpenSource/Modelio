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
 * Proxy class to handle a {@link Dependency} with << satisfy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de664031-5286-4b77-8e34-8ad41e46aa5d")
public class Satisfy {
    @objid ("6845fa6c-de85-45c5-9d29-e0e02cd290e9")
    public static final String STEREOTYPE_NAME = "satisfy";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5bdb34c8-d5dc-474f-b3b1-75d119bdf03f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Satisfy proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << satisfy >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2375695c-4506-4628-b320-2d6694b08ca2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << satisfy >> then instantiate a {@link Satisfy} proxy.
     * 
     * @return a {@link Satisfy} proxy on the created {@link Dependency}.
     */
    @objid ("0611e01f-9827-4b4e-8ca8-ed4ce789cd98")
    public static Satisfy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME);
        return Satisfy.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Satisfy} proxy or <i>null</i>.
     */
    @objid ("790caa15-9168-4b97-a384-a5df09c99818")
    public static Satisfy instantiate(Dependency obj) {
        return Satisfy.canInstantiate(obj) ? new Satisfy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Satisfy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("abb450a3-8094-4fed-80cb-456aecae05a2")
    public static Satisfy safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Satisfy.canInstantiate(obj))
        	return new Satisfy(obj);
        else
        	throw new IllegalArgumentException("Satisfy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("408ec29f-efce-4c61-b404-8a4e57cb17e3")
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
        Satisfy other = (Satisfy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("221f89fa-d892-4e7a-a031-8f83483a0f04")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4d0dfcf0-e231-420c-bb13-e9c2037770a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("479119bf-b64f-4d87-9771-b9320a28c3cd")
    protected  Satisfy(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b06d31b4-20d4-42e2-b341-3bd59450e084")
    public static final class MdaTypes {
        @objid ("7e3b2e56-16f1-4ddf-b911-7ae4d7ccd8ff")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7256137c-7915-409e-989f-71f77b2bc5d2")
        private static Stereotype MDAASSOCDEP;

        @objid ("f628206c-36f7-40ab-836f-839eba51a00f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d99a6314-eff9-4764-8275-2e01f47c17de")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0224-0000-000000000000");
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
