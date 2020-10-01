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
 * Proxy class to handle a {@link OutputPin} with << UML2BodyOutput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dbc1e7ca-f761-4429-b0c7-68a4c218ca5d")
public class UML2BodyOutput {
    @objid ("edb8e625-2211-49b4-90c6-db911d3b2eb5")
    public static final String STEREOTYPE_NAME = "UML2BodyOutput";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("a746f219-cd20-4af6-b906-e6f6253376cf")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2BodyOutput proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2BodyOutput >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8a1ff725-c3ea-41e0-8c9f-f2a9a853efc8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2BodyOutput >> then instantiate a {@link UML2BodyOutput} proxy.
     * 
     * @return a {@link UML2BodyOutput} proxy on the created {@link OutputPin}.
     */
    @objid ("4a9e0783-a0fd-4546-b3e7-0b5d5f28897e")
    public static UML2BodyOutput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME);
        return UML2BodyOutput.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2BodyOutput} proxy or <i>null</i>.
     */
    @objid ("d5c318f1-2416-4d8b-9a48-49e215c004b4")
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
    @objid ("1e032fc2-3c84-43d0-a7b4-19ab0897f9c3")
    public static UML2BodyOutput safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2BodyOutput.canInstantiate(obj))
        	return new UML2BodyOutput(obj);
        else
        	throw new IllegalArgumentException("UML2BodyOutput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ebecac65-808a-4a1d-846d-778e4b3942d0")
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
    @objid ("a7db7496-bf3b-46ed-9bca-95118eaa2261")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("57199ca6-ab7e-444b-bbda-7e61a5e59398")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9b6aa169-04b7-4183-93ad-ed4abb1ad26a")
    protected UML2BodyOutput(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("f8fb3049-bf2a-4fe1-9c58-ce020fedf5f3")
    public static final class MdaTypes {
        @objid ("c6c750b7-14fe-46b0-b08a-9cf30b2650c0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("604b503e-65e3-4b42-a617-6cdf637ac6bf")
        private static Stereotype MDAASSOCDEP;

        @objid ("85417d25-39b0-4b20-ae02-99367f2903df")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8ef76122-01c3-4a0e-9de6-e913adee24d6")
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
