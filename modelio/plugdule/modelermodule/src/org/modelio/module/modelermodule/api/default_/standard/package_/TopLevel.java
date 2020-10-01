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
    @objid ("6beb43a9-45ae-4d7e-aa51-aae370195f70")
    public static final String STEREOTYPE_NAME = "topLevel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("23a73ab7-3cc4-4f38-82ad-925339a57267")
    protected final Package elt;

    /**
     * Tells whether a {@link TopLevel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << topLevel >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2977ddc9-f06e-4760-8467-8ff8f841394c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << topLevel >> then instantiate a {@link TopLevel} proxy.
     * 
     * @return a {@link TopLevel} proxy on the created {@link Package}.
     */
    @objid ("be7c08bb-9c09-4b7c-af48-f711be8b3a44")
    public static TopLevel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
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
    @objid ("3d1eaca2-e21d-4018-ab9d-97afe49751d5")
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
    @objid ("c6375eb0-4182-44d8-b24e-4f1b6be0c213")
    public static TopLevel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (TopLevel.canInstantiate(obj))
        	return new TopLevel(obj);
        else
        	throw new IllegalArgumentException("TopLevel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3a8a0c73-c2e4-4500-8a9d-bf958d9b5d45")
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
    @objid ("5c7b8d4d-7630-4e90-9c89-6cd9d7a76926")
    public Package getElement() {
        return this.elt;
    }

    @objid ("604d7e8a-1473-43c1-aef3-212040626296")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d64d663e-d1bf-450e-85b0-090f32831984")
    protected TopLevel(Package elt) {
        this.elt = elt;
    }

    @objid ("8002304f-2a7d-47cc-b5a1-1cda6ca608d8")
    public static final class MdaTypes {
        @objid ("572db9bb-a9e0-4e49-a1b5-cf23eaf26c6f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a1458a85-d005-4de7-98cc-42e6f35a91b1")
        private static Stereotype MDAASSOCDEP;

        @objid ("e306ce90-076c-402a-aca2-578728713756")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c699648b-0c60-459f-afa3-fa17cfeb7cfd")
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
