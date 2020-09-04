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
package org.modelio.module.modelermodule.api.default_.standard.usecasedependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
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
 * Proxy class to handle a {@link UseCaseDependency} with << include >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("09cedb92-5f5a-40aa-a318-488613b0c1ef")
public class Include {
    @objid ("0fa47ad7-65bf-4a5b-b240-64631e5b8f3b")
    public static final String STEREOTYPE_NAME = "include";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("e8acb3cc-22ed-4a2a-ba1b-394a4557edf6")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Include proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << include >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6d695828-bc7a-4897-b14b-151e779c6be5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << include >> then instantiate a {@link Include} proxy.
     * 
     * @return a {@link Include} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("e6253776-3f7e-4690-a9bc-1fb73a59247a")
    public static Include create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME);
        return Include.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Include} proxy or <i>null</i>.
     */
    @objid ("948ba7a9-5cf3-47f9-9d29-93c19c1ec742")
    public static Include instantiate(UseCaseDependency obj) {
        return Include.canInstantiate(obj) ? new Include(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Include} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1d92e5cd-6cd3-48d2-93ab-3a5dee49c44b")
    public static Include safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Include.canInstantiate(obj))
        	return new Include(obj);
        else
        	throw new IllegalArgumentException("Include: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("347ecebd-26f5-4f27-982f-08d0c346cb17")
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
        Include other = (Include) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("bfcb1f4e-1b03-4bb1-a354-efe93501b881")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("e05fabad-1fc4-46c6-94bd-e57a8f0b8784")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a2de6300-a0f5-40b5-910a-97218fa96d60")
    protected Include(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("9d68d730-b7bb-41e7-a89a-ad9aad3c697c")
    public static final class MdaTypes {
        @objid ("167fdf16-072a-422d-83f7-de44afa9e82e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c93a0229-add1-401a-927d-82b8a9b6dcfc")
        private static Stereotype MDAASSOCDEP;

        @objid ("8ff7fb66-e191-49e8-b599-0afbe06262fd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f21d2aee-0132-4061-9562-54de21233c7e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c49-0000-000000000000");
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
