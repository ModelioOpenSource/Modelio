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
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Result >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("44487931-3470-4987-9272-bb45a6d23c7e")
public class UML2Result {
    @objid ("cf351446-5efb-4651-b2d2-a22121b65ef9")
    public static final String STEREOTYPE_NAME = "UML2Result";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("fc2b77bc-9c78-4f1f-a3df-0eb75cc250b4")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Result proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Result >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("259cbe46-d881-4f2c-96ee-ddcd069c3bab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Result >> then instantiate a {@link UML2Result} proxy.
     * 
     * @return a {@link UML2Result} proxy on the created {@link OutputPin}.
     */
    @objid ("2cf606da-b0c1-4148-87c9-cdc0c7ffd43d")
    public static UML2Result create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME);
        return UML2Result.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Result} proxy or <i>null</i>.
     */
    @objid ("685959ad-4a19-45a0-8552-c7c926c210e1")
    public static UML2Result instantiate(OutputPin obj) {
        return UML2Result.canInstantiate(obj) ? new UML2Result(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Result} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b185ac1a-24f2-43a5-944d-c8b5bfa5a8e7")
    public static UML2Result safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Result.canInstantiate(obj))
        	return new UML2Result(obj);
        else
        	throw new IllegalArgumentException("UML2Result: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ea7fee30-ff53-4d12-b013-b639bb741c73")
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
        UML2Result other = (UML2Result) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("4d1a30a5-9562-4410-a1c6-0675ede49b84")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("2c6c9ffd-3168-490b-9da7-c6a6c0566596")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e6b61b1d-7503-45cf-aadd-94b665a279bd")
    protected UML2Result(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("ad42c1ae-a610-4d17-8bc4-7ff3c78480be")
    public static final class MdaTypes {
        @objid ("b6ff638f-326a-40d6-84e8-b0fb58071418")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a97f17bf-8d32-4cc4-bf0a-96fa6ba10369")
        private static Stereotype MDAASSOCDEP;

        @objid ("5700f368-077b-464c-a03e-8d5754deb685")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("79d159be-73fc-44a5-a9cb-fabc2e17f3dd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8914ba10-818c-4439-8e2b-89671c2288bc");
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
