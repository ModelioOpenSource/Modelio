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
    @objid ("93ae1def-46d1-4692-b07f-5a16c86f1feb")
    public static final String STEREOTYPE_NAME = "ModelComponent";

    /**
     * The underlying {@link Component} represented by this proxy, never null.
     */
    @objid ("206ca87e-e25a-4a70-a95a-7198a2347ccd")
    protected final Component elt;

    /**
     * Tells whether a {@link ModelComponent proxy} can be instantiated from a {@link MObject} checking it is a {@link Component} stereotyped << ModelComponent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1819f3ad-dd2e-40c0-93fd-a39bfeeffa0a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Component) && ((Component) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Component} stereotyped << ModelComponent >> then instantiate a {@link ModelComponent} proxy.
     * 
     * @return a {@link ModelComponent} proxy on the created {@link Component}.
     */
    @objid ("2806d8f9-ea6c-47de-956a-126970729fb9")
    public static ModelComponent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Component");
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
    @objid ("7289ebdb-87cf-409e-88c3-4181683e4769")
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
    @objid ("72a0ac5e-003a-4d8d-af6b-60cded4e31fe")
    public static ModelComponent safeInstantiate(Component obj) throws IllegalArgumentException {
        if (ModelComponent.canInstantiate(obj))
        	return new ModelComponent(obj);
        else
        	throw new IllegalArgumentException("ModelComponent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9a78b000-8235-4c3a-a81b-2884f02f6f2f")
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
    @objid ("8d052604-f444-4836-a2db-390c9fbfd055")
    public Component getElement() {
        return this.elt;
    }

    @objid ("86024f23-4aa8-4741-bb91-5e3ea92a2fa7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("010e3c2d-a980-450d-ab54-bce91aea4a36")
    protected  ModelComponent(Component elt) {
        this.elt = elt;
    }

    @objid ("9685b84a-47f2-4a11-b7a6-d392fda9c49b")
    public static final class MdaTypes {
        @objid ("ee8de9db-00c1-426a-ac28-fab68a7bbe8a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3073d9cb-4c0e-4133-8cce-7ef793b44450")
        private static Stereotype MDAASSOCDEP;

        @objid ("f47fc02c-cf42-4741-bd36-7b52391574ff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c11754f8-0006-4440-8d07-4402662c2116")
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
