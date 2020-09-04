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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2Signal >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("429f7033-665a-4704-999e-c1f22086cd88")
public class UML2Signal {
    @objid ("2f1b2724-b71c-449f-af47-c543361c6a9f")
    public static final String STEREOTYPE_NAME = "UML2Signal";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("0132a8bd-c31f-4822-ba13-37df8235b737")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Signal proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Signal >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3617ac1c-fd67-481b-94da-5bf6791697f2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Signal >> then instantiate a {@link UML2Signal} proxy.
     * 
     * @return a {@link UML2Signal} proxy on the created {@link Dependency}.
     */
    @objid ("2576ffd5-c3ea-4c98-a8c2-a5dd4927ea33")
    public static UML2Signal create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME);
        return UML2Signal.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Signal} proxy or <i>null</i>.
     */
    @objid ("6723ac9f-99ac-47cc-a2ee-2fd7aa56169f")
    public static UML2Signal instantiate(Dependency obj) {
        return UML2Signal.canInstantiate(obj) ? new UML2Signal(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Signal} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("19cd65f3-52f7-42aa-a9c8-7f6b4b45144b")
    public static UML2Signal safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Signal.canInstantiate(obj))
        	return new UML2Signal(obj);
        else
        	throw new IllegalArgumentException("UML2Signal: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ccd309ee-c2f0-4a60-a588-829b7201325a")
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
        UML2Signal other = (UML2Signal) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("e0e45551-75b7-412b-81dd-dac44368269b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b1fd56dc-7348-4968-8570-9cd7dd3c2c0f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f2418454-bb56-43df-9c69-24c46a0b80ab")
    protected UML2Signal(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1ae6c789-5ffa-46c0-b0ad-50c587da8536")
    public static final class MdaTypes {
        @objid ("52cc53e5-67e4-40e2-b3b8-722fb9b89f76")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dbaa74cc-7c97-4c77-a8da-a190566b2601")
        private static Stereotype MDAASSOCDEP;

        @objid ("14d3061d-b572-4d84-92c7-687b6e2a13ac")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2afda586-4a5e-43e3-ac0a-7032955e5af7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "33ea7558-fb93-11df-8b5e-0027103f347c");
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
