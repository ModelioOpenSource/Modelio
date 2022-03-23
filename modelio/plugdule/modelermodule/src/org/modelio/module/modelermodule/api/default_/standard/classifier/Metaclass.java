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
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << metaclass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0317e1ea-1cf1-4651-8c3e-910f2777fdd0")
public class Metaclass {
    @objid ("b56e9683-3d7a-4b54-98fc-2d3a49ed8b3b")
    public static final String STEREOTYPE_NAME = "metaclass";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("17750b58-77ec-44ef-803e-45109628df66")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Metaclass proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << metaclass >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("402ecde3-ee5a-4fb0-9e4d-a20da32f3843")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << metaclass >> then instantiate a {@link Metaclass} proxy.
     * 
     * @return a {@link Metaclass} proxy on the created {@link Classifier}.
     */
    @objid ("d79751d2-1fa0-4d46-9f8a-e5089d34581e")
    public static Metaclass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME);
        return Metaclass.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Metaclass} proxy or <i>null</i>.
     */
    @objid ("d5577480-df24-453a-a6c1-3a83f05f4a19")
    public static Metaclass instantiate(Classifier obj) {
        return Metaclass.canInstantiate(obj) ? new Metaclass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Metaclass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ff10751f-03f4-4fca-b7ae-739f2c144a2e")
    public static Metaclass safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Metaclass.canInstantiate(obj))
        	return new Metaclass(obj);
        else
        	throw new IllegalArgumentException("Metaclass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d7d60dda-d5b8-4c5c-a582-1672160cad24")
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
        Metaclass other = (Metaclass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("b9a526a5-8802-4b24-aada-5c53d82bcb04")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("1ede2c62-2622-4f06-9b15-65f0a882ee9d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("bcb2dfb0-4e30-4adf-b51f-a6bbc79a3f82")
    protected  Metaclass(Classifier elt) {
        this.elt = elt;
    }

    @objid ("849c2bbc-c1c1-4711-a8fa-6b1c9f242228")
    public static final class MdaTypes {
        @objid ("aa4ca104-5051-4615-be65-1aa5606154b7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1e111c16-a053-4b0c-9cf1-08a3e14a73cb")
        private static Stereotype MDAASSOCDEP;

        @objid ("57f40349-ee4a-4016-ad63-e517f54940c7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1c6f3c9c-a31a-433c-8b8c-42d5951fdb78")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bd-0000-000000000000");
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
