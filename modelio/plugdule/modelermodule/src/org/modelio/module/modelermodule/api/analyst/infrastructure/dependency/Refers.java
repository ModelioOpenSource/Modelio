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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("beec23ee-e7db-44f7-8f12-9702a304383d")
    public static final String STEREOTYPE_NAME = "refers";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a86da3d2-7338-4e0e-8a8f-8fc13a952c92")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refers proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refers >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4dda8538-a346-49f3-b712-229eaddf804b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refers >> then instantiate a {@link Refers} proxy.
     * 
     * @return a {@link Refers} proxy on the created {@link Dependency}.
     */
    @objid ("6dd50355-c7a5-43d5-acfc-903cc36ec3ce")
    public static Refers create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
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
    @objid ("4304c9d4-294e-44ee-a353-9786fb431a8b")
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
    @objid ("e14e8785-8d15-470c-8ff5-c2f52b15cb74")
    public static Refers safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refers.canInstantiate(obj))
        	return new Refers(obj);
        else
        	throw new IllegalArgumentException("Refers: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c41e34e7-0c8b-4091-93c8-b515ff4b71fb")
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
    @objid ("aa70fb86-ab58-427b-a4e3-165a0c955477")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("d574b115-d377-416f-9456-f64a85138a9b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ee34933a-19f7-4607-ac30-332529aa73d9")
    protected Refers(Dependency elt) {
        this.elt = elt;
    }

    @objid ("36fe2657-f445-48fc-a9c1-922300a50d98")
    public static final class MdaTypes {
        @objid ("dcccc2a0-c869-4cfb-888d-a011f0a55c82")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4a596b7f-5c88-4feb-97b1-2024509e08bf")
        private static Stereotype MDAASSOCDEP;

        @objid ("737ba9c4-a189-4273-8dfc-5383266baca9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("34cd8c92-a25e-4637-bd39-521047324021")
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
