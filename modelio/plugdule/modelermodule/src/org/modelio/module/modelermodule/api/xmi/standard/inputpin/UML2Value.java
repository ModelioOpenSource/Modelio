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
 * Proxy class to handle a {@link InputPin} with << UML2Value >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("520071db-260b-4339-a5b5-5acd89638735")
public class UML2Value {
    @objid ("da37019b-c059-4fe4-9063-3a42b353160e")
    public static final String STEREOTYPE_NAME = "UML2Value";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("9e0c16f8-55c8-49c5-9844-601a7180a872")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Value proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Value >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("96040189-488b-4887-9656-f0409c9f4237")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Value >> then instantiate a {@link UML2Value} proxy.
     * 
     * @return a {@link UML2Value} proxy on the created {@link InputPin}.
     */
    @objid ("140f8ee2-6926-49e0-8369-e915cb8b79d7")
    public static UML2Value create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME);
        return UML2Value.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Value} proxy or <i>null</i>.
     */
    @objid ("b726b472-256d-4855-bed1-4ffb3da5da04")
    public static UML2Value instantiate(InputPin obj) {
        return UML2Value.canInstantiate(obj) ? new UML2Value(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Value} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d212be3b-96ef-4189-9ae8-23925e28f10f")
    public static UML2Value safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Value.canInstantiate(obj))
        	return new UML2Value(obj);
        else
        	throw new IllegalArgumentException("UML2Value: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a9d7bf14-4442-4d73-ad7b-8ff398d1deab")
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
        UML2Value other = (UML2Value) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("4227c6d6-8339-47bb-8821-3044304eb27b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d1824afc-2e17-4119-a34d-d22d40b02b85")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d34f69ea-483a-4a40-a050-4a76812469d5")
    protected UML2Value(InputPin elt) {
        this.elt = elt;
    }

    @objid ("5619aea8-dfda-4511-8227-6948387e1280")
    public static final class MdaTypes {
        @objid ("2402d79b-5afb-4258-b945-40d9c9dcdbc5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e29aa5c3-64ca-4652-b4a5-a0c3cfc8c049")
        private static Stereotype MDAASSOCDEP;

        @objid ("d6d34f5f-64fb-4bb5-90b6-6e7f0344cba9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8835b1e7-4fa7-438b-8f0d-c8c54272fcff")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "eb8f06b7-de86-11de-905b-001302895b2b");
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
