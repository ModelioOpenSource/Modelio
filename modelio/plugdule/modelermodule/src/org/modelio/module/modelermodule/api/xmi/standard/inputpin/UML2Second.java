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
 * Proxy class to handle a {@link InputPin} with << UML2Second >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b741933-97e4-4335-8b95-a8f3c5ea9155")
public class UML2Second {
    @objid ("2eee660d-4025-43c4-bce6-3141fa879267")
    public static final String STEREOTYPE_NAME = "UML2Second";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("ce022f1c-d827-482b-9683-f21ead824c0a")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Second proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Second >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5e8df47d-e402-4438-86dd-432f9f95028b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Second >> then instantiate a {@link UML2Second} proxy.
     * 
     * @return a {@link UML2Second} proxy on the created {@link InputPin}.
     */
    @objid ("576fa604-66be-47fe-8bb9-3809def8019f")
    public static UML2Second create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME);
        return UML2Second.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Second} proxy or <i>null</i>.
     */
    @objid ("48f85c5f-4b57-4e34-8e7b-0b47246363ba")
    public static UML2Second instantiate(InputPin obj) {
        return UML2Second.canInstantiate(obj) ? new UML2Second(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Second} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8a366ae2-76cf-4212-b780-de6c865a5496")
    public static UML2Second safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Second.canInstantiate(obj))
        	return new UML2Second(obj);
        else
        	throw new IllegalArgumentException("UML2Second: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0aac4307-c580-43ed-844f-8ae85eb1c502")
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
        UML2Second other = (UML2Second) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("76359689-a983-4c1f-8ffb-3a4ab5f794c3")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("ec737ade-74c0-451e-b964-09aa83ad3055")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("29fa8609-f2ae-4d3b-b29c-85cc8beafed4")
    protected UML2Second(InputPin elt) {
        this.elt = elt;
    }

    @objid ("12a78492-d0ac-49ef-8c8e-e162f97b0e1f")
    public static final class MdaTypes {
        @objid ("b7b50332-caf9-4041-9f4a-a4ef1df41645")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("410da6aa-1c28-4de0-8ab2-ef25e073fdb0")
        private static Stereotype MDAASSOCDEP;

        @objid ("ca185faa-4484-4adc-b5c9-3850f7ddf059")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8edd51eb-04a7-4ac7-b6ba-05c089f999ae")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3f03c5f1-c308-11de-8ac8-001302895b2b");
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
