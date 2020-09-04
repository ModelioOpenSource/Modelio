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
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << secondary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5214e11f-81b6-4d62-9a5c-3032b96258c1")
public class Secondary {
    @objid ("febf75c2-8346-4f27-bcb6-2dd48f54c865")
    public static final String STEREOTYPE_NAME = "secondary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("10bd0c41-32ca-49e8-8089-36c211b05a9e")
    protected final Actor elt;

    /**
     * Tells whether a {@link Secondary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << secondary >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c57a8d3f-aa51-4263-b0c4-5540ccc088dc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << secondary >> then instantiate a {@link Secondary} proxy.
     * 
     * @return a {@link Secondary} proxy on the created {@link Actor}.
     */
    @objid ("dcf4a7c5-4bfd-4978-8092-8767eafc6cae")
    public static Secondary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME);
        return Secondary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Secondary} proxy or <i>null</i>.
     */
    @objid ("9d54d7e5-b6f6-46c5-acaa-b21c2938cb00")
    public static Secondary instantiate(Actor obj) {
        return Secondary.canInstantiate(obj) ? new Secondary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Secondary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ea1049dd-003b-42e9-9738-a04112a37caa")
    public static Secondary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Secondary.canInstantiate(obj))
        	return new Secondary(obj);
        else
        	throw new IllegalArgumentException("Secondary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("24bcb687-04a2-4d5e-a24d-87995fcfdfe4")
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
        Secondary other = (Secondary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("1080fa0b-343c-437f-aa05-e4881e761b99")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("b31b9ea8-e602-41f1-a5de-14148c8f6631")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7f4c8e14-b39a-4ccb-9eed-72b5e29d9115")
    protected Secondary(Actor elt) {
        this.elt = elt;
    }

    @objid ("8e474651-cd0e-4098-985c-82cba46299e9")
    public static final class MdaTypes {
        @objid ("82693b81-9498-49cb-a97a-540249de695a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("05eb6025-5041-47b1-bc11-e9b1edb2a867")
        private static Stereotype MDAASSOCDEP;

        @objid ("961b29bb-9689-41c0-9e49-4d93b552ba25")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("59859f90-e5a1-43a1-998b-1fce4178a6ec")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2f04-0000-000000000000");
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
