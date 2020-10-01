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
 * Proxy class to handle a {@link Classifier} with << utility >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("361f955a-46bb-4174-ae99-420074e6c928")
public class Utility {
    @objid ("b0ff22f0-ec2a-4ba1-8ab8-3c5e2622ccac")
    public static final String STEREOTYPE_NAME = "utility";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("7d1abd71-f1e6-4385-acca-641efe1e3a08")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Utility proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << utility >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("61cd9f77-aa47-4d38-b73a-4f85e6f90285")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << utility >> then instantiate a {@link Utility} proxy.
     * 
     * @return a {@link Utility} proxy on the created {@link Classifier}.
     */
    @objid ("54238078-22dc-4a11-959d-744693d5ff8e")
    public static Utility create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME);
        return Utility.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Utility} proxy or <i>null</i>.
     */
    @objid ("0afb136a-edc5-40cf-a38f-771974994938")
    public static Utility instantiate(Classifier obj) {
        return Utility.canInstantiate(obj) ? new Utility(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Utility} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7e38b715-da54-4289-ab61-6a4fbd02df1a")
    public static Utility safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Utility.canInstantiate(obj))
        	return new Utility(obj);
        else
        	throw new IllegalArgumentException("Utility: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("61627309-398c-4b7b-b228-5ea9d2c3ec68")
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
        Utility other = (Utility) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("be75fdb4-d846-45c4-aff6-e176f83778a3")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("d044e848-a1eb-49a0-bb41-7f9572f5acc6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("16a6255f-f315-44c0-908a-9db7e079d3f5")
    protected Utility(Classifier elt) {
        this.elt = elt;
    }

    @objid ("0a6fc122-40b5-4691-a722-7d1756955c7a")
    public static final class MdaTypes {
        @objid ("9029c1ce-8e41-4f1a-ad6d-4dadf09516fb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("80066240-ee75-41c8-8c82-27a4266bf788")
        private static Stereotype MDAASSOCDEP;

        @objid ("fdd56f10-09ed-4644-8a26-6dbc5aded207")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4d060b45-90b7-484a-add8-97cd45222f90")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bf-0000-000000000000");
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
