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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << trace >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("deb1d430-370d-47a6-83f9-89eae36b4475")
public class Trace {
    @objid ("516c2c33-c86b-4b49-b50e-243691811e8a")
    public static final String STEREOTYPE_NAME = "trace";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a87cbb09-2384-44a3-926e-5baf82f66d1f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Trace proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << trace >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1f928f64-dcab-4c6b-8ac5-d1e29c261ff2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << trace >> then instantiate a {@link Trace} proxy.
     * 
     * @return a {@link Trace} proxy on the created {@link Dependency}.
     */
    @objid ("efdacb13-8958-4980-bae4-d9f61edf4b82")
    public static Trace create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME);
        return Trace.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Trace} proxy or <i>null</i>.
     */
    @objid ("db6e512c-a3be-4338-a4d0-51698d01eda2")
    public static Trace instantiate(Dependency obj) {
        return Trace.canInstantiate(obj) ? new Trace(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Trace} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("98707736-1ed1-416e-b8ae-8f6c39355b8c")
    public static Trace safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Trace.canInstantiate(obj))
        	return new Trace(obj);
        else
        	throw new IllegalArgumentException("Trace: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f1a9d77d-f3a2-4fc2-b548-3844d9f6483b")
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
        Trace other = (Trace) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("5791df1e-d282-48f4-b5e5-39952aa7d184")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("5487ef98-10c0-49d3-8f0f-08f767ee7c53")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d522ca5a-3900-4967-98f0-51eb62002e47")
    protected Trace(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1335f40a-5185-493c-ac4b-6745cea1855f")
    public static final class MdaTypes {
        @objid ("5ea32054-927f-4cc5-9b86-b3d480735888")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e6ed91d6-63ab-43d7-b6db-0a1f27328363")
        private static Stereotype MDAASSOCDEP;

        @objid ("aea9dad7-6c1a-40a4-9027-2be343f5c371")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d945169a-768f-41b5-9c94-1c6bad958378")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01280500-0000-0b37-0000-000000000000");
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
