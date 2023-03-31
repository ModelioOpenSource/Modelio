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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << topLevel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18369814-6a88-4ede-86be-c5df9aba56d3")
public class TopLevel {
    @objid ("28dddad8-5162-4ba8-8c80-b56512adf7a7")
    public static final String STEREOTYPE_NAME = "topLevel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("6df96d2b-bf81-4cdc-954b-ebb63ad4f02e")
    protected final Package elt;

    /**
     * Tells whether a {@link TopLevel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << topLevel >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ea2052e0-7ed6-4d04-8a32-18fe1b2dbb9e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << topLevel >> then instantiate a {@link TopLevel} proxy.
     * 
     * @return a {@link TopLevel} proxy on the created {@link Package}.
     */
    @objid ("72d6b4db-bd2b-4b60-900e-0c891b3a3990")
    public static TopLevel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME);
        return TopLevel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link TopLevel} proxy from a {@link Package} stereotyped << topLevel >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link TopLevel} proxy or <i>null</i>.
     */
    @objid ("07e0b2d1-ec98-4f22-a410-e680cc7a6e8c")
    public static TopLevel instantiate(Package obj) {
        return TopLevel.canInstantiate(obj) ? new TopLevel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TopLevel} proxy from a {@link Package} stereotyped << topLevel >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link TopLevel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c6d42123-d492-4f05-badc-bc63b24e7ea1")
    public static TopLevel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (TopLevel.canInstantiate(obj))
        	return new TopLevel(obj);
        else
        	throw new IllegalArgumentException("TopLevel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5ba51406-53d8-4fbe-bf32-877857469585")
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
        TopLevel other = (TopLevel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("f616f542-6bc6-4371-9fdf-de7d572e17e9")
    public Package getElement() {
        return this.elt;
    }

    @objid ("0c247611-1b08-4459-8120-11e74cf9a075")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8f006924-fbc6-45cf-a69a-39849d2bdf04")
    protected  TopLevel(Package elt) {
        this.elt = elt;
    }

    @objid ("8002304f-2a7d-47cc-b5a1-1cda6ca608d8")
    public static final class MdaTypes {
        @objid ("264078f5-3951-43d7-a5a6-ed75b38744f5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("adcc5db4-5482-4915-af32-d91d4b6803ca")
        private static Stereotype MDAASSOCDEP;

        @objid ("161417dd-3700-4f8e-a62b-9c7586e28baa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("557e8d00-c444-4017-9d51-d333fd776131")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d9-0000-000000000000");
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
