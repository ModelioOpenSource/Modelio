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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Argument >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f227bc75-d200-4586-8356-abed568e953a")
public class UML2Argument {
    @objid ("2ef5014c-ff59-41d4-bbe9-d3fc33909d82")
    public static final String STEREOTYPE_NAME = "UML2Argument";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("6cd5b5f7-49de-4d51-aebe-db9630ddae60")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Argument proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Argument >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("491a3cc8-1199-4999-a318-3a7aa1f91eca")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Argument >> then instantiate a {@link UML2Argument} proxy.
     * 
     * @return a {@link UML2Argument} proxy on the created {@link InputPin}.
     */
    @objid ("ee811991-1bd5-4f38-831d-d2ca3e212843")
    public static UML2Argument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME);
        return UML2Argument.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Argument} proxy or <i>null</i>.
     */
    @objid ("398b3b81-f5a4-4938-911d-c144af4aa48e")
    public static UML2Argument instantiate(InputPin obj) {
        return UML2Argument.canInstantiate(obj) ? new UML2Argument(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Argument} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4faec10b-fafa-4d68-a720-f7a52e1ec2b6")
    public static UML2Argument safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Argument.canInstantiate(obj))
        	return new UML2Argument(obj);
        else
        	throw new IllegalArgumentException("UML2Argument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e83880ba-199b-4f48-9cb1-ab88ac1532e8")
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
        UML2Argument other = (UML2Argument) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("db191b2f-b5c7-4656-9ebe-0f749a13ca0b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("f7a5aea7-9cdf-41f8-8b6a-cbecc7cf01e2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a314ee16-fcf9-4f24-b7ec-fcbfeb845286")
    protected UML2Argument(InputPin elt) {
        this.elt = elt;
    }

    @objid ("35371a23-7a89-4c20-ae40-4ea2e5ecc371")
    public static final class MdaTypes {
        @objid ("eeb754d4-b83d-4a04-a053-cb0953c503d9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5949a702-5672-4fb5-928b-aa48d1e97424")
        private static Stereotype MDAASSOCDEP;

        @objid ("15c5c85f-936d-49a4-b664-4ff92c34daa5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("302b577d-d04d-4875-9fb5-563c27f8b7d4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86630901-a400-4353-8b38-6db0846d1e38");
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
