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
 * Proxy class to handle a {@link InputPin} with << UML2InputValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1c34bb71-2360-4ab4-a7be-dc80c0436183")
public class UML2InputValue {
    @objid ("6c931a9a-0d50-4db2-ba9e-75cdd73f6f8c")
    public static final String STEREOTYPE_NAME = "UML2InputValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("5b192638-acec-4ce1-bc5a-5a06956aeda8")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InputValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InputValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d0d3ed75-b661-492d-a7bb-37ce1ff7fd32")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InputValue >> then instantiate a {@link UML2InputValue} proxy.
     * 
     * @return a {@link UML2InputValue} proxy on the created {@link InputPin}.
     */
    @objid ("04db0b3a-3c27-47b7-9e19-b6509942e7fa")
    public static UML2InputValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME);
        return UML2InputValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InputValue} proxy or <i>null</i>.
     */
    @objid ("19a14f65-ba18-439d-b4c8-4b75a471b794")
    public static UML2InputValue instantiate(InputPin obj) {
        return UML2InputValue.canInstantiate(obj) ? new UML2InputValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InputValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("af0d7a89-de34-4b1f-860e-dace40902a22")
    public static UML2InputValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InputValue.canInstantiate(obj))
        	return new UML2InputValue(obj);
        else
        	throw new IllegalArgumentException("UML2InputValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bffc18e6-8ab1-4c93-8f11-e489256fa48e")
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
        UML2InputValue other = (UML2InputValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("63e701d6-658f-4342-958f-45fd15a446a9")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a3d8d587-a15a-4e30-9110-e3a2316adfd5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("da017c1e-2fa3-4ba1-bda0-0c22dfb5592a")
    protected UML2InputValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("01baa51d-5b0b-4b35-8446-fe87b63e0a81")
    public static final class MdaTypes {
        @objid ("e3c33f60-9d72-475f-8b56-5da27761357b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7d678336-7fef-437d-a97a-7507b4b1f422")
        private static Stereotype MDAASSOCDEP;

        @objid ("89255f80-3d0a-469d-9436-d23342e1e7ee")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ea4e9f56-ba09-4f4c-a159-026050219a42")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a81a2a04-07b3-4a26-8b1e-5b4ebaa67990");
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
