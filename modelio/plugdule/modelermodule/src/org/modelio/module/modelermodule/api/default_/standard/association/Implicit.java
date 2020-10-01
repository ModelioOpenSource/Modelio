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
package org.modelio.module.modelermodule.api.default_.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << implicit >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a2291f1c-9e40-4062-b1f6-c4ef88ee02eb")
public class Implicit {
    @objid ("90809629-e681-464b-89c5-0decacd5f26d")
    public static final String STEREOTYPE_NAME = "implicit";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("0db6c80b-393e-491f-8d29-b78d00e3a3c5")
    protected final Association elt;

    /**
     * Tells whether a {@link Implicit proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << implicit >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("01b6c29a-f9b8-46a7-b2ff-1b939301ba96")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << implicit >> then instantiate a {@link Implicit} proxy.
     * 
     * @return a {@link Implicit} proxy on the created {@link Association}.
     */
    @objid ("13ed194a-d08e-4c6e-8caf-5d2a12c6ef40")
    public static Implicit create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME);
        return Implicit.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link Implicit} proxy or <i>null</i>.
     */
    @objid ("400852ba-183b-4360-916f-37889e0bb19c")
    public static Implicit instantiate(Association obj) {
        return Implicit.canInstantiate(obj) ? new Implicit(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link Implicit} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0f10c8e8-a81d-461d-bb88-aa1390654211")
    public static Implicit safeInstantiate(Association obj) throws IllegalArgumentException {
        if (Implicit.canInstantiate(obj))
        	return new Implicit(obj);
        else
        	throw new IllegalArgumentException("Implicit: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa501c8f-b62b-4d27-bb8b-5177e75c0635")
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
        Implicit other = (Implicit) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("0d9a50de-5918-47ab-bdd8-87237b5ebac2")
    public Association getElement() {
        return this.elt;
    }

    @objid ("c4877405-87b9-4c42-bb12-75132300fdb1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("90d50349-bfac-4b3f-95e0-a719e7ba6645")
    protected Implicit(Association elt) {
        this.elt = elt;
    }

    @objid ("6e6fdf9c-fd6f-4767-bf30-7a823cf4cd32")
    public static final class MdaTypes {
        @objid ("05478203-c964-4084-9e2b-fa2d277345ec")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ad5b78f2-df06-4c6a-ba5e-632fa023a2f6")
        private static Stereotype MDAASSOCDEP;

        @objid ("9fcb1493-2df2-4a77-b691-a14dcac2d0b5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6c230c60-9e76-4c7e-8bf5-8403a87d4cb9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01b8-0000-000000000000");
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
