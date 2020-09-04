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
    @objid ("fa377daf-d87b-488f-a53c-21e0007f1d35")
    public static final String STEREOTYPE_NAME = "metaclass";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("331e2af0-bc3b-43b7-80f5-e70dcffa9199")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Metaclass proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << metaclass >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2865a393-ea0b-441d-b4a8-082c2ac12f59")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << metaclass >> then instantiate a {@link Metaclass} proxy.
     * 
     * @return a {@link Metaclass} proxy on the created {@link Classifier}.
     */
    @objid ("8025cd04-ab7d-4ed1-8727-00c4b8aec499")
    public static Metaclass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME);
        return Metaclass.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Metaclass} proxy or <i>null</i>.
     */
    @objid ("2e72ee5d-efed-4381-97f9-e70fa2b5c12b")
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
    @objid ("596e754f-fe51-4b3f-8378-5832087d02f0")
    public static Metaclass safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Metaclass.canInstantiate(obj))
        	return new Metaclass(obj);
        else
        	throw new IllegalArgumentException("Metaclass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("594f0c66-9b6c-4d8e-9fb0-89cd4bc4735a")
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
    @objid ("10683a8d-7544-4b55-a665-40cc1697662e")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("2a03481e-c7c8-47eb-9f45-79f727eeecac")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("16a04783-ce15-457d-bfbe-35d6d7dc3fe6")
    protected Metaclass(Classifier elt) {
        this.elt = elt;
    }

    @objid ("849c2bbc-c1c1-4711-a8fa-6b1c9f242228")
    public static final class MdaTypes {
        @objid ("f9c82a55-914d-4d2b-b3c6-1eef218785e4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0d60f0da-1c56-4613-8e3a-63e97b615477")
        private static Stereotype MDAASSOCDEP;

        @objid ("34b9ba53-6e52-4a82-b3f9-0d658b569d77")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f2647201-c7a9-4405-8a3c-b71d57d0d0be")
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
