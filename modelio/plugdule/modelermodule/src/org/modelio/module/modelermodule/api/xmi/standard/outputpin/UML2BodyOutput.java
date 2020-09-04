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
 * Proxy class to handle a {@link OutputPin} with << UML2BodyOutput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dbc1e7ca-f761-4429-b0c7-68a4c218ca5d")
public class UML2BodyOutput {
    @objid ("078f4937-d59b-4fb9-a5d1-05c89dde03bf")
    public static final String STEREOTYPE_NAME = "UML2BodyOutput";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("9d424c37-3f83-4985-9394-d11640b04dce")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2BodyOutput proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2BodyOutput >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("03e78958-7905-4dac-9920-dfde562ea4d2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2BodyOutput >> then instantiate a {@link UML2BodyOutput} proxy.
     * 
     * @return a {@link UML2BodyOutput} proxy on the created {@link OutputPin}.
     */
    @objid ("c580947e-de16-473d-9470-e41f2d6d6e91")
    public static UML2BodyOutput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME);
        return UML2BodyOutput.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2BodyOutput} proxy or <i>null</i>.
     */
    @objid ("cf2a6750-6bfc-458f-8984-769be5510d4c")
    public static UML2BodyOutput instantiate(OutputPin obj) {
        return UML2BodyOutput.canInstantiate(obj) ? new UML2BodyOutput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2BodyOutput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0f610c3f-b39f-4979-96ae-a6b64a60e6d6")
    public static UML2BodyOutput safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2BodyOutput.canInstantiate(obj))
        	return new UML2BodyOutput(obj);
        else
        	throw new IllegalArgumentException("UML2BodyOutput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("42dd94cf-1ada-4877-a662-908cdd503be1")
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
        UML2BodyOutput other = (UML2BodyOutput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("1ca9ba6b-8dc7-4e8b-9c28-53720b04f945")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("ac3aad65-5505-40cd-b41d-5793e0b479af")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d5c69a4d-145e-45c5-8dcf-5ac998a172d1")
    protected UML2BodyOutput(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("f8fb3049-bf2a-4fe1-9c58-ce020fedf5f3")
    public static final class MdaTypes {
        @objid ("f6eb064b-e013-4e12-b6ab-b681927b3e7d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("118f8bda-b2af-4940-9054-bd120db8144d")
        private static Stereotype MDAASSOCDEP;

        @objid ("e1e19777-9e5f-4294-b9ea-4b3dc0f384df")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e1a5441a-79b6-4b42-8924-6632da4a5a54")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "fdbc7d78-32c7-11e0-91f3-0027103f347c");
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
