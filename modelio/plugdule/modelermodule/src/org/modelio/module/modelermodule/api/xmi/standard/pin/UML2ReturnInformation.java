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
package org.modelio.module.modelermodule.api.xmi.standard.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
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
 * Proxy class to handle a {@link Pin} with << UML2ReturnInformation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a592276c-6700-4520-af3c-8bb54ab17733")
public class UML2ReturnInformation {
    @objid ("66e01874-e194-464d-ae20-89b4fd3d3062")
    public static final String STEREOTYPE_NAME = "UML2ReturnInformation";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("35d52282-22c6-4619-b110-9ecab2502c1f")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ReturnInformation proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ReturnInformation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("97643dd8-111a-4ba0-9de0-f6b03b9189ff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ReturnInformation >> then instantiate a {@link UML2ReturnInformation} proxy.
     * 
     * @return a {@link UML2ReturnInformation} proxy on the created {@link Pin}.
     */
    @objid ("40f8abab-636a-4f66-a247-029ce08f92ff")
    public static UML2ReturnInformation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME);
        return UML2ReturnInformation.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ReturnInformation} proxy or <i>null</i>.
     */
    @objid ("714b105b-9b88-4d12-af6b-e099eb9fd40b")
    public static UML2ReturnInformation instantiate(Pin obj) {
        return UML2ReturnInformation.canInstantiate(obj) ? new UML2ReturnInformation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ReturnInformation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fec23293-e47d-45e0-88c0-5641e9611081")
    public static UML2ReturnInformation safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ReturnInformation.canInstantiate(obj))
        	return new UML2ReturnInformation(obj);
        else
        	throw new IllegalArgumentException("UML2ReturnInformation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("df3bcdfe-c91e-4c6e-bb49-ba881d97a9e2")
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
        UML2ReturnInformation other = (UML2ReturnInformation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("0ffb9f7e-7400-464c-955d-e53efd6ed38a")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("1ad3511b-b38b-482f-b468-6ab981836c95")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("38ba69ca-488e-4970-894a-ac203f5ef5c7")
    protected UML2ReturnInformation(Pin elt) {
        this.elt = elt;
    }

    @objid ("4350d302-8b4a-4b36-9f3e-09cc98ca12e6")
    public static final class MdaTypes {
        @objid ("d018af8f-9323-482f-a480-369790d455d5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("48e397ec-2841-4ebc-b426-5b71b7f92b7d")
        private static Stereotype MDAASSOCDEP;

        @objid ("ab0c3226-e1c3-4eea-bd40-249227c4c115")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e219668a-7872-4003-bbf8-4aa2e51f9693")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0936024c-9d51-4bc0-99b5-e8f72ae60007");
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
