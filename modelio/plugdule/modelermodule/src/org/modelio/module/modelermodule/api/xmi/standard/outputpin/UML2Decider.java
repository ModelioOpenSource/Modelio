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
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Decider >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3b0b1cd2-7362-4d64-8798-cb185dd04589")
public class UML2Decider {
    @objid ("fa47a9c1-0f71-45f1-9fd8-e698ef6fb598")
    public static final String STEREOTYPE_NAME = "UML2Decider";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("4f1fb4b2-8577-41eb-b1b0-fac51a85baac")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Decider proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Decider >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f568685b-7509-4166-8ca2-c241dfdc8fb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Decider >> then instantiate a {@link UML2Decider} proxy.
     * 
     * @return a {@link UML2Decider} proxy on the created {@link OutputPin}.
     */
    @objid ("8dca7a51-8450-430a-851e-25a9a7850a3a")
    public static UML2Decider create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME);
        return UML2Decider.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Decider} proxy or <i>null</i>.
     */
    @objid ("88fcbced-b4ad-4721-ae11-1123865b39ad")
    public static UML2Decider instantiate(OutputPin obj) {
        return UML2Decider.canInstantiate(obj) ? new UML2Decider(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Decider} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f865480a-4bfa-4c0b-b605-fcaf2adb3c1c")
    public static UML2Decider safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Decider.canInstantiate(obj))
        	return new UML2Decider(obj);
        else
        	throw new IllegalArgumentException("UML2Decider: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("df83b45c-d7bc-40ec-b018-599e006379ff")
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
        UML2Decider other = (UML2Decider) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("efecb4dd-b8e6-4cb0-adea-32842f982842")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("6819e050-5809-424f-bc74-2625f3a00580")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("315605ff-3e2c-41c0-b26f-c3f4da71a8a7")
    protected UML2Decider(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("31eec6ec-04ba-451c-a15c-cacea4d48a26")
    public static final class MdaTypes {
        @objid ("745df3f0-7d6d-4fd6-a955-9a510fd45ba9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f467f7f9-0ddb-4624-a443-a01ea6945b01")
        private static Stereotype MDAASSOCDEP;

        @objid ("693bcf09-cea3-4b97-9616-5174247d17f9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("13c4678b-5302-489f-847a-a7bdb36d42b7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "27ac6d48-32c8-11e0-91f3-0027103f347c");
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
