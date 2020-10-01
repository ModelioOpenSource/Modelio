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
 * Proxy class to handle a {@link Dependency} with << measure >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a5520de-c3f4-49fe-82ab-dd4a9414a61c")
public class Measure {
    @objid ("a0b28844-f973-4a9a-85bd-eefee607d2fb")
    public static final String STEREOTYPE_NAME = "measure";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2e847ed0-85ae-4dc2-abfe-7184ba1905da")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Measure proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << measure >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fb5cc6d4-5b41-4192-acbf-9b4d7da553d6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << measure >> then instantiate a {@link Measure} proxy.
     * 
     * @return a {@link Measure} proxy on the created {@link Dependency}.
     */
    @objid ("4c36dbdb-b8da-462e-80eb-305ea7246432")
    public static Measure create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME);
        return Measure.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Measure} proxy or <i>null</i>.
     */
    @objid ("43c359b7-80a0-4f95-b71e-8c4ce2f67544")
    public static Measure instantiate(Dependency obj) {
        return Measure.canInstantiate(obj) ? new Measure(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Measure} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("22747df2-591c-4886-a3ff-f62e7855483d")
    public static Measure safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Measure.canInstantiate(obj))
        	return new Measure(obj);
        else
        	throw new IllegalArgumentException("Measure: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("65489383-f117-4677-ab73-72265b0ca6de")
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
        Measure other = (Measure) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a503c4d0-c6b4-4cc9-9338-3fdb349afbbc")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("10d01d7f-317e-447c-bccd-635e817e0bd2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a6a9577b-c905-4f49-bf2d-87e519b10324")
    protected Measure(Dependency elt) {
        this.elt = elt;
    }

    @objid ("46bb357b-ca38-48c0-b010-8be5578cd473")
    public static final class MdaTypes {
        @objid ("c7fcbc7d-2e72-4321-bb9b-ae3ad77b668a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bf35bc4a-eba2-47ac-a4d1-e42fdb1a4535")
        private static Stereotype MDAASSOCDEP;

        @objid ("a659d080-5216-4966-bc43-843ff6d715fb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0f0d30b0-f79e-44f1-a80f-44d5141814a5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0256-0000-000000000000");
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
