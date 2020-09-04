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
    @objid ("625e86e3-d7a8-4262-ba2c-60bdfce88fa7")
    public static final String STEREOTYPE_NAME = "refers";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("3d8f8a43-0606-4feb-b08b-70e5d3b1df08")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refers proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refers >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c398341d-4c04-4bac-9ef3-86e398376a63")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refers >> then instantiate a {@link Refers} proxy.
     * 
     * @return a {@link Refers} proxy on the created {@link Dependency}.
     */
    @objid ("3361b9dc-24aa-484b-84d0-0992c33f0e4d")
    public static Refers create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME);
        return Refers.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refers} proxy from a {@link Dependency} stereotyped << refers >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refers} proxy or <i>null</i>.
     */
    @objid ("760a7bcb-6cef-470d-9471-7e848749c2fe")
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
    @objid ("978f7968-8e25-417e-b29e-d69df747dc37")
    public static Refers safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refers.canInstantiate(obj))
        	return new Refers(obj);
        else
        	throw new IllegalArgumentException("Refers: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0fa0d849-8152-4000-bf5c-3939308ed8db")
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
    @objid ("874884b5-5199-44c1-ad2a-b411a20ba98b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f27cd35f-96a5-404e-9573-004dff9e1cdd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bb0c868c-2394-4b89-9162-8c1c404f073d")
    protected Refers(Dependency elt) {
        this.elt = elt;
    }

    @objid ("36fe2657-f445-48fc-a9c1-922300a50d98")
    public static final class MdaTypes {
        @objid ("1c83d582-4faf-4fe6-b890-8dd700a5f4ff")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("09d177c2-8c09-41f5-aed7-d03b0be7ea4d")
        private static Stereotype MDAASSOCDEP;

        @objid ("c8b553b7-cb8c-4102-9aa4-6e0eff694511")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("80bdaf50-c53b-4cf0-9e20-07b622e6554c")
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
