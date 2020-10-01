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
package org.modelio.module.modelermodule.api.xmi.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << UML2InteractionOverviewDiagram  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("025fc4e6-2df9-46ba-9efe-c6ce06c9b716")
public class UML2InteractionOverviewDiagram {
    @objid ("bae6e212-0627-45b0-9a4c-fc0b3abaee8a")
    public static final String STEREOTYPE_NAME = "UML2InteractionOverviewDiagram ";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("4a4e13da-d5a2-4a2c-8988-9adb102ac581")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link UML2InteractionOverviewDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2ae27f53-393b-4735-9506-ffc2ee700f73")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> then instantiate a {@link UML2InteractionOverviewDiagram} proxy.
     * 
     * @return a {@link UML2InteractionOverviewDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("fb5a48e5-2863-4e71-9f47-4682b49c1f2e")
    public static UML2InteractionOverviewDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME);
        return UML2InteractionOverviewDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link UML2InteractionOverviewDiagram} proxy or <i>null</i>.
     */
    @objid ("7628537f-2aa0-406b-99ad-5e5c5a559ad2")
    public static UML2InteractionOverviewDiagram instantiate(StaticDiagram obj) {
        return UML2InteractionOverviewDiagram.canInstantiate(obj) ? new UML2InteractionOverviewDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link UML2InteractionOverviewDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2a3bfb4e-9172-43f7-b44c-d43df9a4bea5")
    public static UML2InteractionOverviewDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (UML2InteractionOverviewDiagram.canInstantiate(obj))
        	return new UML2InteractionOverviewDiagram(obj);
        else
        	throw new IllegalArgumentException("UML2InteractionOverviewDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e51be646-f780-40c8-8ea5-38a49f2fd2ae")
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
        UML2InteractionOverviewDiagram other = (UML2InteractionOverviewDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("4a82f9b9-6219-47df-9ab2-22b84f69bcf9")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("84b8062f-8b6a-4bd0-9b95-12db8f696cc4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9303ab12-7fe3-4100-9824-49839ba70530")
    protected UML2InteractionOverviewDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("be457845-c207-4134-ac6d-84fc582830cd")
    public static final class MdaTypes {
        @objid ("9ffd4e25-3257-434c-9ab3-2766255c604f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b05b3537-f105-4cef-a1a3-4a6d233c9873")
        private static Stereotype MDAASSOCDEP;

        @objid ("859c5e0e-020b-47bd-acac-4d5e67d991da")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aba08a3f-4cb5-4d5d-999b-5184e624fd25")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4358003-f3da-11df-8ada-0027103f347c");
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
