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
    @objid ("f0a04a9a-a525-4d65-a807-9c1f8ac91481")
    public static final String STEREOTYPE_NAME = "topLevel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("cde142f8-6247-4eba-8835-f4b9e36a7277")
    protected final Package elt;

    /**
     * Tells whether a {@link TopLevel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << topLevel >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("83acba9b-fb87-438a-bf7d-6d120a916cad")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << topLevel >> then instantiate a {@link TopLevel} proxy.
     * 
     * @return a {@link TopLevel} proxy on the created {@link Package}.
     */
    @objid ("5e184081-382f-4bbe-9668-76cadb62b32b")
    public static TopLevel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME);
        return TopLevel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link TopLevel} proxy from a {@link Package} stereotyped << topLevel >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link TopLevel} proxy or <i>null</i>.
     */
    @objid ("a3940b95-feb9-4523-80e9-cd3e5d71f355")
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
    @objid ("06784c20-0c17-44bc-8734-570cb5dc20a0")
    public static TopLevel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (TopLevel.canInstantiate(obj))
        	return new TopLevel(obj);
        else
        	throw new IllegalArgumentException("TopLevel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("20508d54-0fb2-4443-bff0-90b57861948b")
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
    @objid ("dcbb7c5c-a8a1-48b1-a501-6f3994b02da6")
    public Package getElement() {
        return this.elt;
    }

    @objid ("d79274e1-2a9c-4920-816a-5b29e19e93da")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("68ad71c8-3ef4-476c-b1d8-03bae481bd82")
    protected TopLevel(Package elt) {
        this.elt = elt;
    }

    @objid ("8002304f-2a7d-47cc-b5a1-1cda6ca608d8")
    public static final class MdaTypes {
        @objid ("0c9257eb-fb7b-4f02-9380-692e888a03a2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("df73fd29-b6a5-4c98-bdb6-6d101ded2388")
        private static Stereotype MDAASSOCDEP;

        @objid ("d54606d2-5b3b-4bf4-873d-5d853dd292a3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("26290e06-aaba-480f-9bcd-6d614b3a603a")
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
