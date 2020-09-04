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
package org.modelio.module.modelermodule.api.default_.standard.component;

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
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Component} with << ModelComponent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c387e5c6-b04b-4bbe-a77e-ab653b1d5ee1")
public class ModelComponent {
    @objid ("b8f76778-99b8-4770-809f-14fbcc0dfc27")
    public static final String STEREOTYPE_NAME = "ModelComponent";

    /**
     * The underlying {@link Component} represented by this proxy, never null.
     */
    @objid ("4b20170e-3fd1-4e6e-aac1-efbc94c9f6c6")
    protected final Component elt;

    /**
     * Tells whether a {@link ModelComponent proxy} can be instantiated from a {@link MObject} checking it is a {@link Component} stereotyped << ModelComponent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d790172d-f302-44cf-b460-b4c941a1c00a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Component) && ((Component) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Component} stereotyped << ModelComponent >> then instantiate a {@link ModelComponent} proxy.
     * 
     * @return a {@link ModelComponent} proxy on the created {@link Component}.
     */
    @objid ("5e8fb05b-b50b-4680-a648-e71689df6b6c")
    public static ModelComponent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Component");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME);
        return ModelComponent.instantiate((Component)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponent} proxy from a {@link Component} stereotyped << ModelComponent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Component
     * @return a {@link ModelComponent} proxy or <i>null</i>.
     */
    @objid ("275a2161-7f28-41ba-a47f-80479e44396a")
    public static ModelComponent instantiate(Component obj) {
        return ModelComponent.canInstantiate(obj) ? new ModelComponent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponent} proxy from a {@link Component} stereotyped << ModelComponent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Component}
     * @return a {@link ModelComponent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("95a72ba8-b1e0-40ac-808e-51af5e9a355c")
    public static ModelComponent safeInstantiate(Component obj) throws IllegalArgumentException {
        if (ModelComponent.canInstantiate(obj))
        	return new ModelComponent(obj);
        else
        	throw new IllegalArgumentException("ModelComponent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ac6cec75-6573-4ebe-a58c-c26b39479213")
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
        ModelComponent other = (ModelComponent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Component}. 
     * @return the Component represented by this proxy, never null.
     */
    @objid ("cfc0916e-8e94-404a-ae52-b48be7e034ed")
    public Component getElement() {
        return this.elt;
    }

    @objid ("22aefe24-ff8a-447a-9e5d-1faeea7e7dfd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a2529347-dec0-41f5-bd8e-9d5710b241e1")
    protected ModelComponent(Component elt) {
        this.elt = elt;
    }

    @objid ("9685b84a-47f2-4a11-b7a6-d392fda9c49b")
    public static final class MdaTypes {
        @objid ("d60e1f9c-6320-4746-a419-ebade9cf18e1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("da5440de-9b54-4791-8472-8bf60b919213")
        private static Stereotype MDAASSOCDEP;

        @objid ("65acb728-23a6-4cd4-a330-8fff0ebdaa1b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d4a649fc-9408-4b80-84fe-5be9b88056fb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00d0052c-0000-0143-0000-000000000000");
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
