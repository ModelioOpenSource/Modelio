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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Second >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b741933-97e4-4335-8b95-a8f3c5ea9155")
public class UML2Second {
    @objid ("afc3223f-c63e-476f-ac35-99b94d7dcca3")
    public static final String STEREOTYPE_NAME = "UML2Second";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("b02e2a4b-4a18-4a07-9904-5a5c621b0d33")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Second proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Second >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f13e2c2f-cc81-47c3-b644-35b21f6ac0e1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Second >> then instantiate a {@link UML2Second} proxy.
     * 
     * @return a {@link UML2Second} proxy on the created {@link InputPin}.
     */
    @objid ("de849f6d-a06d-41a3-90a0-4d3e449c747f")
    public static UML2Second create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME);
        return UML2Second.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Second} proxy or <i>null</i>.
     */
    @objid ("4e9cb812-dc4c-4689-9bb4-8b58214179cd")
    public static UML2Second instantiate(InputPin obj) {
        return UML2Second.canInstantiate(obj) ? new UML2Second(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Second} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a10ce6c8-8a13-401b-9390-9979a20b7dc2")
    public static UML2Second safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Second.canInstantiate(obj))
        	return new UML2Second(obj);
        else
        	throw new IllegalArgumentException("UML2Second: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5299e5c7-1e4f-4c4b-a933-4ecb146b270f")
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
        UML2Second other = (UML2Second) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("b44ddf41-b7f3-4696-90d8-152b21bffe79")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("79264a0b-16bf-4eff-8035-087f00e8c954")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bd28a8ae-2f51-4ff1-a85d-93664a0ac411")
    protected UML2Second(InputPin elt) {
        this.elt = elt;
    }

    @objid ("12a78492-d0ac-49ef-8c8e-e162f97b0e1f")
    public static final class MdaTypes {
        @objid ("c390742e-3a97-4d2f-b931-dfc661acf1bd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("260a7b0e-3d8c-4c29-abb0-30951f5419be")
        private static Stereotype MDAASSOCDEP;

        @objid ("abe05ee1-6128-40b0-9919-4a936dfcd595")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("adf0d413-6d9b-45f3-8ac9-1607a04c885e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3f03c5f1-c308-11de-8ac8-001302895b2b");
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
