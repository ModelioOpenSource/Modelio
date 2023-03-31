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
    @objid ("8f941286-ba92-4817-a195-54154f040f35")
    public static final String STEREOTYPE_NAME = "secondary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("d359a636-0050-46e8-ae95-75f5b3ea14ae")
    protected final Actor elt;

    /**
     * Tells whether a {@link Secondary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << secondary >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9484a2c9-7bf9-421d-a511-67ad1f5ecd28")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << secondary >> then instantiate a {@link Secondary} proxy.
     * 
     * @return a {@link Secondary} proxy on the created {@link Actor}.
     */
    @objid ("b79b2bfb-1b54-4bb8-a7a9-4296e63707e4")
    public static Secondary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME);
        return Secondary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Secondary} proxy or <i>null</i>.
     */
    @objid ("51cfdfad-146b-45dd-b5e5-6f5bbb048bbf")
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
    @objid ("54a6c02d-91d6-49c7-ad24-3c20b5a27339")
    public static Secondary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Secondary.canInstantiate(obj))
        	return new Secondary(obj);
        else
        	throw new IllegalArgumentException("Secondary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fd78456e-6aca-4abb-a4f5-1ed6331e0375")
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
    @objid ("c98d17a1-c295-45a4-ad41-c17e90be2f9a")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("ca113636-3a1b-45f7-a107-624ca252af85")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("a88e927b-dc0d-4c3b-8529-0bba7fbc1835")
    protected  Secondary(Actor elt) {
        this.elt = elt;
    }

    @objid ("8e474651-cd0e-4098-985c-82cba46299e9")
    public static final class MdaTypes {
        @objid ("0a055ee4-1dd5-48f5-93a4-0be73af7629e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("93fe5758-6b2e-4b12-a0aa-86a574ce6f4e")
        private static Stereotype MDAASSOCDEP;

        @objid ("ebe6c311-dd15-4057-a2c5-4420cf1b9e23")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5dc19766-221e-4276-8ac4-192fdcaa3165")
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
