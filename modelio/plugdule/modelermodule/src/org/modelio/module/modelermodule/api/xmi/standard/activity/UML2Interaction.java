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
package org.modelio.module.modelermodule.api.xmi.standard.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
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
 * Proxy class to handle a {@link Activity} with << UML2Interaction  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fe0a2eb7-94ca-4d4c-b8b4-0755c72af361")
public class UML2Interaction {
    @objid ("cd52b495-1a03-4fbb-83b8-5e706d18cb8d")
    public static final String STEREOTYPE_NAME = "UML2Interaction ";

    /**
     * The underlying {@link Activity} represented by this proxy, never null.
     */
    @objid ("8dda66e9-0777-41ba-bed8-fb17eadf9e9d")
    protected final Activity elt;

    /**
     * Tells whether a {@link UML2Interaction proxy} can be instantiated from a {@link MObject} checking it is a {@link Activity} stereotyped << UML2Interaction  >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1589b440-7333-4fd0-bff8-5ce711527cdb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Activity) && ((Activity) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Activity} stereotyped << UML2Interaction  >> then instantiate a {@link UML2Interaction} proxy.
     * 
     * @return a {@link UML2Interaction} proxy on the created {@link Activity}.
     */
    @objid ("306c46b5-bbe2-4e5d-bb7e-a9d3ec22141d")
    public static UML2Interaction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Activity");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME);
        return UML2Interaction.instantiate((Activity)e);
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Activity
     * @return a {@link UML2Interaction} proxy or <i>null</i>.
     */
    @objid ("1b530a23-6bd8-4008-81ea-22b8e8273105")
    public static UML2Interaction instantiate(Activity obj) {
        return UML2Interaction.canInstantiate(obj) ? new UML2Interaction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Activity}
     * @return a {@link UML2Interaction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("af541d99-38c0-4d22-b63b-e44a502a1770")
    public static UML2Interaction safeInstantiate(Activity obj) throws IllegalArgumentException {
        if (UML2Interaction.canInstantiate(obj))
        	return new UML2Interaction(obj);
        else
        	throw new IllegalArgumentException("UML2Interaction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("07afb0a7-5de1-44d8-b32a-70bd4fc5cd92")
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
        UML2Interaction other = (UML2Interaction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Activity}. 
     * @return the Activity represented by this proxy, never null.
     */
    @objid ("2267e922-f8f1-462f-807a-e4729bb8bd19")
    public Activity getElement() {
        return this.elt;
    }

    @objid ("1fee45a1-5816-4457-acd1-719753619e70")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("175e0c2e-5497-4c3c-a8aa-72561a6333cc")
    protected UML2Interaction(Activity elt) {
        this.elt = elt;
    }

    @objid ("2d8755d9-18eb-44df-b006-43b75366bab0")
    public static final class MdaTypes {
        @objid ("12bc098e-ebef-409c-99d6-125c5d31b441")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4651a14d-ae70-4b40-be72-97a2d4c3e9ba")
        private static Stereotype MDAASSOCDEP;

        @objid ("98daf053-9880-45ab-b2d3-bfca7b6cde61")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9ce28d27-3930-4ca1-b693-a6ebc1c08daa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "021863a9-f3db-11df-8ada-0027103f347c");
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
