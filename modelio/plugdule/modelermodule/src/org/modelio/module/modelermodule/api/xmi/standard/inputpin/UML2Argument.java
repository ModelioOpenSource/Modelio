/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("15d6749e-9ba2-4e4a-8ae0-7b654a907f92")
    public static final String STEREOTYPE_NAME = "UML2Argument";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("93186468-c263-4e70-9fcd-609c7ba31a05")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Argument proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Argument >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f4f342c8-6140-40bc-bfe5-98489c37a621")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Argument >> then instantiate a {@link UML2Argument} proxy.
     * 
     * @return a {@link UML2Argument} proxy on the created {@link InputPin}.
     */
    @objid ("813de8b6-0afd-4297-9d61-ef8df51ffe30")
    public static UML2Argument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME);
        return UML2Argument.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Argument} proxy or <i>null</i>.
     */
    @objid ("0c641fe9-1422-4c3d-b910-44b614ce4168")
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
    @objid ("a3913433-7192-4bbd-9769-e256ff85e28f")
    public static UML2Argument safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Argument.canInstantiate(obj))
        	return new UML2Argument(obj);
        else
        	throw new IllegalArgumentException("UML2Argument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("77caf00e-aafb-4cba-9208-164b88c6f6a0")
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
    @objid ("24fa8c0b-3a76-46f5-a937-ee86b87050fc")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("04c9f08e-167b-4d13-a894-68eb616235e2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("02c96247-d8c7-4cb2-8be1-503c5f455d34")
    protected UML2Argument(InputPin elt) {
        this.elt = elt;
    }

    @objid ("35371a23-7a89-4c20-ae40-4ea2e5ecc371")
    public static final class MdaTypes {
        @objid ("697b9705-2a76-4b2a-b5f6-01cb9718aeef")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ac4ade2b-4677-4865-a39c-128a923aa895")
        private static Stereotype MDAASSOCDEP;

        @objid ("08fb66ba-0d00-418a-8917-1b346b75fe9b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("558eeb59-da31-4f90-9f2e-01a1b2967248")
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
