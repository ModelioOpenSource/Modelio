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
package org.modelio.module.modelermodule.api.xmi.standard.objectflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
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
 * Proxy class to handle a {@link ObjectFlow} with << UML2ExceptionHandler >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("99f3e547-a9ff-492d-8072-1124b305e010")
public class UML2ExceptionHandler {
    @objid ("c7dda10b-0b3f-451e-80cf-399bc88ddefe")
    public static final String STEREOTYPE_NAME = "UML2ExceptionHandler";

    /**
     * The underlying {@link ObjectFlow} represented by this proxy, never null.
     */
    @objid ("b2f1c14c-ea7c-4d76-af4d-f77ecb83edf5")
    protected final ObjectFlow elt;

    /**
     * Tells whether a {@link UML2ExceptionHandler proxy} can be instantiated from a {@link MObject} checking it is a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f474c34c-e211-4e04-9a5f-8459ea5f1520")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ObjectFlow) && ((ObjectFlow) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> then instantiate a {@link UML2ExceptionHandler} proxy.
     * 
     * @return a {@link UML2ExceptionHandler} proxy on the created {@link ObjectFlow}.
     */
    @objid ("878de68e-d1d6-43c3-ab47-5743fac156c0")
    public static UML2ExceptionHandler create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ObjectFlow");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME);
        return UML2ExceptionHandler.instantiate((ObjectFlow)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionHandler} proxy from a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ObjectFlow
     * @return a {@link UML2ExceptionHandler} proxy or <i>null</i>.
     */
    @objid ("0de9a64f-02ed-4d80-b1b8-5edbf64fc7ac")
    public static UML2ExceptionHandler instantiate(ObjectFlow obj) {
        return UML2ExceptionHandler.canInstantiate(obj) ? new UML2ExceptionHandler(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionHandler} proxy from a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ObjectFlow}
     * @return a {@link UML2ExceptionHandler} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("395a10ce-ba92-41ba-bb24-84b672a436ac")
    public static UML2ExceptionHandler safeInstantiate(ObjectFlow obj) throws IllegalArgumentException {
        if (UML2ExceptionHandler.canInstantiate(obj))
        	return new UML2ExceptionHandler(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionHandler: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2d6b4d98-81e0-428d-a8e9-c3310f674b6e")
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
        UML2ExceptionHandler other = (UML2ExceptionHandler) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ObjectFlow}. 
     * @return the ObjectFlow represented by this proxy, never null.
     */
    @objid ("1090a2c5-9972-4277-8816-a1064f18b073")
    public ObjectFlow getElement() {
        return this.elt;
    }

    @objid ("e619ca48-436b-4541-9c74-a7b71222b561")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("09fefd96-81ff-4801-a68c-7ac20a080a08")
    protected UML2ExceptionHandler(ObjectFlow elt) {
        this.elt = elt;
    }

    @objid ("ed214f2b-6a8e-4515-9c8d-379b6f62a571")
    public static final class MdaTypes {
        @objid ("f0d7a37a-7aa6-4e80-9402-95bf92650931")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e55d1073-abb1-41e0-ba3f-820baa409641")
        private static Stereotype MDAASSOCDEP;

        @objid ("69d4e134-3069-4ed9-904b-f53c9e83fd4a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9022aa72-2cf8-4c7f-843a-0c40b1d52453")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1b7fd53f-205e-11df-948e-001302895b2b");
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
