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
    @objid ("887bc458-2fff-4444-90ba-d8c0769b6a84")
    public static final String STEREOTYPE_NAME = "ModelComponent";

    /**
     * The underlying {@link Component} represented by this proxy, never null.
     */
    @objid ("ee79ddca-8f9e-4763-b867-44976d0fb0ed")
    protected final Component elt;

    /**
     * Tells whether a {@link ModelComponent proxy} can be instantiated from a {@link MObject} checking it is a {@link Component} stereotyped << ModelComponent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b08d1b7e-a478-4947-93eb-02efeb8e2afa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Component) && ((Component) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Component} stereotyped << ModelComponent >> then instantiate a {@link ModelComponent} proxy.
     * 
     * @return a {@link ModelComponent} proxy on the created {@link Component}.
     */
    @objid ("5e648c2f-526e-4ab8-bc5b-8b65a444a622")
    public static ModelComponent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Component");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME);
        return ModelComponent.instantiate((Component)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponent} proxy from a {@link Component} stereotyped << ModelComponent >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Component
     * @return a {@link ModelComponent} proxy or <i>null</i>.
     */
    @objid ("24ba19ab-a42f-4d32-ada9-823d62a748bc")
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
    @objid ("c973b233-415d-43c8-ba59-75a5d05f06f7")
    public static ModelComponent safeInstantiate(Component obj) throws IllegalArgumentException {
        if (ModelComponent.canInstantiate(obj))
        	return new ModelComponent(obj);
        else
        	throw new IllegalArgumentException("ModelComponent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("981cb419-494c-4896-9512-a1ef6e152904")
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
    @objid ("58fba160-6d7c-4df1-83a2-1c97b330e2fb")
    public Component getElement() {
        return this.elt;
    }

    @objid ("35e800cc-3784-4f3b-bcc1-f746e6a5f257")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f13e2813-714f-4d77-84fb-cbd4e66d48ee")
    protected ModelComponent(Component elt) {
        this.elt = elt;
    }

    @objid ("9685b84a-47f2-4a11-b7a6-d392fda9c49b")
    public static final class MdaTypes {
        @objid ("34a923f5-ea83-4212-9370-8047136b8f8e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("38b3753b-9605-45cc-86e3-0a5bc1145cff")
        private static Stereotype MDAASSOCDEP;

        @objid ("44b38f0d-6a14-4af6-9074-7b6ae7b1661e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("14d2caf2-fded-4237-b57d-4e3cdff51919")
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
