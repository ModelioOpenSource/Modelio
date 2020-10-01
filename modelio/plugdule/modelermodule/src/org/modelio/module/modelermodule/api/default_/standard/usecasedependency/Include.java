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
    @objid ("9d3a69dd-1a05-47ec-aafc-e3184a4f13d0")
    public static final String STEREOTYPE_NAME = "include";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("493e7223-14ab-47d8-b855-ce71e69a13ad")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Include proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << include >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c0b07b15-2184-4ac3-adc5-14304034b2c2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << include >> then instantiate a {@link Include} proxy.
     * 
     * @return a {@link Include} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("fdd27f7c-909d-44fe-9711-e56ee4405ff1")
    public static Include create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME);
        return Include.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Include} proxy or <i>null</i>.
     */
    @objid ("efd66229-23bb-4f88-92a7-96f8ed5319ae")
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
    @objid ("e8ea8f2c-11f0-482e-85a2-73069bfdfce4")
    public static Include safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Include.canInstantiate(obj))
        	return new Include(obj);
        else
        	throw new IllegalArgumentException("Include: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ffdd897b-013b-41b8-a891-c240e95fb5ba")
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
    @objid ("75b69552-ab89-4ce2-8be1-5c6832f854b9")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("7bdd7894-a676-4016-bfdd-fde1170706cd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa17c56d-222a-449f-90a8-7628540c638a")
    protected Include(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("9d68d730-b7bb-41e7-a89a-ad9aad3c697c")
    public static final class MdaTypes {
        @objid ("57f2cf1f-a020-4aec-b1a2-49ba80ffe5a9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b518fc6c-26db-4706-bc60-cafa3df0839b")
        private static Stereotype MDAASSOCDEP;

        @objid ("dfd8c3c4-e33f-47dc-aded-844d82654b3b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("40b20916-3151-4235-a19a-10543aa49038")
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
