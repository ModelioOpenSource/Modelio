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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << impact_model >> stereotype.
 * <p>Stereotype description:
 * <br/><i>impact_model dependencies are used to associate one or several ImpactModel instances to an ImpactDiagram. The associated models are used to filter the impact links to be displayed in the diagram.</i></p>
 */
@objid ("570c43d0-4d3e-4aa8-89b0-108c1cd48ed9")
public class ImpactModel {
    @objid ("bbd3575b-2ffd-4b5c-ab6b-12ecc151dcb5")
    public static final String STEREOTYPE_NAME = "impact_model";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("13b8dc63-dd96-4952-978c-b56fc7abcc13")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactModel proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_model >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b3b64be4-784f-486f-a7cc-6c13915a7f96")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_model >> then instantiate a {@link ImpactModel} proxy.
     * 
     * @return a {@link ImpactModel} proxy on the created {@link Dependency}.
     */
    @objid ("ba23d57d-d061-44c9-9282-892b6d457ad6")
    public static ImpactModel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME);
        return ImpactModel.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactModel} proxy or <i>null</i>.
     */
    @objid ("24e07bf9-5830-49d4-a14c-346e5454d11b")
    public static ImpactModel instantiate(Dependency obj) {
        return ImpactModel.canInstantiate(obj) ? new ImpactModel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactModel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b27375b1-a5c5-432d-8b5f-759b8dee2462")
    public static ImpactModel safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactModel.canInstantiate(obj))
        	return new ImpactModel(obj);
        else
        	throw new IllegalArgumentException("ImpactModel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("82ba3e82-a00b-424e-8434-dd37ee04e85d")
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
        ImpactModel other = (ImpactModel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c93af7b1-dca7-4d21-a7fd-10f11028acad")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("68a43507-ae83-477e-aeca-3d98f5f9ed57")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4115b006-5f66-4cd5-aa55-bc2ecb1ea0f9")
    protected ImpactModel(Dependency elt) {
        this.elt = elt;
    }

    @objid ("2f2f1126-cb55-4153-a6f5-b83673c9034b")
    public static final class MdaTypes {
        @objid ("06f2bdca-a9d9-4275-802f-057ef36fddeb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("eae10364-62d2-4c51-9288-d8764bff4969")
        private static Stereotype MDAASSOCDEP;

        @objid ("9f87a2c3-5343-4385-9c71-5e32d43d462a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dee4c1ef-d3fc-48a9-a3c7-20c563e1a69b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "946458b2-daf1-44b8-887b-12a0d9e5c2f6");
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
