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
    @objid ("e4805747-0ae2-4e58-955c-5c53203feb37")
    public static final String STEREOTYPE_NAME = "impact_model";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("07a4512a-d8d7-4084-a435-31796d678f2f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactModel proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_model >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("adfd7bd5-2529-4508-b701-f44211a9386c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_model >> then instantiate a {@link ImpactModel} proxy.
     * 
     * @return a {@link ImpactModel} proxy on the created {@link Dependency}.
     */
    @objid ("ee0a9488-847c-4328-ac6a-2488257116b0")
    public static ImpactModel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME);
        return ImpactModel.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactModel} proxy or <i>null</i>.
     */
    @objid ("2c0c46d0-f059-49e8-b297-18437d99b0dd")
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
    @objid ("2229e36c-704b-47ce-b3e2-6f62f2eb7dcc")
    public static ImpactModel safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactModel.canInstantiate(obj))
        	return new ImpactModel(obj);
        else
        	throw new IllegalArgumentException("ImpactModel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("032627a7-3f74-4813-8951-c3bdd51e9e0a")
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
    @objid ("1e0c5639-0eb4-4a00-b404-733a1822cbd3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("d88162aa-9685-4863-9dc8-4c6257e34528")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0cb4e804-a450-4cd1-bdec-bdc7784368bd")
    protected ImpactModel(Dependency elt) {
        this.elt = elt;
    }

    @objid ("2f2f1126-cb55-4153-a6f5-b83673c9034b")
    public static final class MdaTypes {
        @objid ("23622391-57b8-4232-877b-6131ff4ef23c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("582dc8b6-4a0f-4081-8186-84cbc4db8281")
        private static Stereotype MDAASSOCDEP;

        @objid ("43b1e150-765b-4376-9b58-7f781c8c0baa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2fbe2ed6-d106-486a-b9c7-5058d41e7444")
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
