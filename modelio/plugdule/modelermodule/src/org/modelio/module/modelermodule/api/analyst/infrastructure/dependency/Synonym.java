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
 * Proxy class to handle a {@link Dependency} with << synonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("85cec5dc-8795-48b4-b7c1-726492848407")
public class Synonym {
    @objid ("fb86ccaf-d6c4-4281-b173-54a8dedcd11d")
    public static final String STEREOTYPE_NAME = "synonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e9701283-9f3f-4d38-b31c-534b3526b5a0")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Synonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << synonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("16af7e9f-1beb-49e2-89c7-2cee021a69e9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << synonym >> then instantiate a {@link Synonym} proxy.
     * 
     * @return a {@link Synonym} proxy on the created {@link Dependency}.
     */
    @objid ("a65de683-3006-44db-9a16-3299910c2ba0")
    public static Synonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME);
        return Synonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Synonym} proxy or <i>null</i>.
     */
    @objid ("b9456521-649b-48cf-978c-182a28eaf31f")
    public static Synonym instantiate(Dependency obj) {
        return Synonym.canInstantiate(obj) ? new Synonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Synonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("513540c4-76ce-4144-95d8-671f5b6c5e6d")
    public static Synonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Synonym.canInstantiate(obj))
        	return new Synonym(obj);
        else
        	throw new IllegalArgumentException("Synonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("67ec8a43-4dc8-4d96-bce6-87ac0a1e03ae")
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
        Synonym other = (Synonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("43c02eba-8223-48dc-a219-4fec46ad3cbb")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e8a262dc-711e-4ffa-9d0a-ffbeef683566")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9306077b-30db-4458-88c7-bdf6ac412ad4")
    protected  Synonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b89f217e-7c42-4e09-b7aa-7f322e65a884")
    public static final class MdaTypes {
        @objid ("963fab1d-7163-4cdb-b931-d979cda2269f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("879954a5-d7fb-4e6b-8444-e3efb6fe54d5")
        private static Stereotype MDAASSOCDEP;

        @objid ("9bd1fc0f-3048-4461-a57c-225b5e0ae72b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f7b5ab29-5973-4da3-a5c5-b3bcec422cef")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-022e-0000-000000000000");
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
