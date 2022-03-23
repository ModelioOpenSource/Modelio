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
    @objid ("205d4317-1a62-477b-b813-eb1d4811299d")
    public static final String STEREOTYPE_NAME = "UML2Signal";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f1f0c08d-b97e-4f3d-94ff-ed1df0b03a60")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Signal proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Signal >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0e66b0fb-6101-4380-931b-0a0425108e52")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Signal >> then instantiate a {@link UML2Signal} proxy.
     * 
     * @return a {@link UML2Signal} proxy on the created {@link Dependency}.
     */
    @objid ("d152d535-1a32-4ad4-9a6d-be842715e46c")
    public static UML2Signal create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME);
        return UML2Signal.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Signal} proxy or <i>null</i>.
     */
    @objid ("216f1f70-f881-43c8-b9f0-a2161ddadd0e")
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
    @objid ("16e36f2b-5434-4b54-b802-0baa6bdcdd1d")
    public static UML2Signal safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Signal.canInstantiate(obj))
        	return new UML2Signal(obj);
        else
        	throw new IllegalArgumentException("UML2Signal: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("39bfa20b-3cc3-4b53-9d83-f7edcc46109a")
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
    @objid ("3ae9c2f1-57d8-4258-ad7b-f49cd18fb3eb")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("2da01cf3-de6a-4cde-8776-76273e02d665")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7c4ece4d-e1ce-44d8-aafc-69a6d9e2a589")
    protected  UML2Signal(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1ae6c789-5ffa-46c0-b0ad-50c587da8536")
    public static final class MdaTypes {
        @objid ("403dd79c-d4ff-4f2a-bc10-e2e47bfbafa8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c2007314-26fa-449a-942e-6e0b56d34a91")
        private static Stereotype MDAASSOCDEP;

        @objid ("7f7fcd4b-d0bd-4766-b0fa-956c28d120a2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f00ff6b2-bdf4-4a2b-8571-fe0c186ae7ab")
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
