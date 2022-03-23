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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << refers >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("263b586a-bce8-4894-8256-ff821796e9e9")
public class Refers {
    @objid ("d3ca0ad3-b386-43f0-a559-da75e3cce89e")
    public static final String STEREOTYPE_NAME = "refers";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f0fa6cfb-5ed8-4c98-99db-ab729f152148")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refers proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refers >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("79b048dd-a86e-4a6f-a4a3-7a5d843370bf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refers >> then instantiate a {@link Refers} proxy.
     * 
     * @return a {@link Refers} proxy on the created {@link Dependency}.
     */
    @objid ("66141269-9a1f-40b4-adc4-b04a344b2588")
    public static Refers create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME);
        return Refers.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refers} proxy from a {@link Dependency} stereotyped << refers >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refers} proxy or <i>null</i>.
     */
    @objid ("69900cc8-a89d-4085-b986-40015a64f798")
    public static Refers instantiate(Dependency obj) {
        return Refers.canInstantiate(obj) ? new Refers(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Refers} proxy from a {@link Dependency} stereotyped << refers >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Refers} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ba766a47-abb0-4b9a-830d-290f72e62f8e")
    public static Refers safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refers.canInstantiate(obj))
        	return new Refers(obj);
        else
        	throw new IllegalArgumentException("Refers: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4d250238-4ebb-4413-a129-10e23592e0d8")
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
        Refers other = (Refers) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("faac944f-e233-426a-a0e5-baeed3989d58")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("449fe23e-1123-4f4e-927f-99da70172784")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d97cd120-518d-4d97-97f0-4730b54e638b")
    protected  Refers(Dependency elt) {
        this.elt = elt;
    }

    @objid ("36fe2657-f445-48fc-a9c1-922300a50d98")
    public static final class MdaTypes {
        @objid ("a02afc7d-581e-4167-9e67-3a7c528bb184")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("384de22b-62f1-4452-b1ed-a74bf3ea1672")
        private static Stereotype MDAASSOCDEP;

        @objid ("3e322ea7-3a08-4950-b5f7-26e0da0f5397")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bbdb7cdf-29e9-43a7-b13c-de1f7a53517e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0265-0000-000000000000");
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
