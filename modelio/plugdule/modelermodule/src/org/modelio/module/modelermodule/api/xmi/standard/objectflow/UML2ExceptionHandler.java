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
    @objid ("045b0361-2646-4054-b111-7acc0cad1c56")
    public static final String STEREOTYPE_NAME = "UML2ExceptionHandler";

    /**
     * The underlying {@link ObjectFlow} represented by this proxy, never null.
     */
    @objid ("b61ad08e-2da0-4bbf-9b04-c341cd7b72b3")
    protected final ObjectFlow elt;

    /**
     * Tells whether a {@link UML2ExceptionHandler proxy} can be instantiated from a {@link MObject} checking it is a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7176d2f3-758a-4097-8e9d-3d53637a42cf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ObjectFlow) && ((ObjectFlow) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> then instantiate a {@link UML2ExceptionHandler} proxy.
     * 
     * @return a {@link UML2ExceptionHandler} proxy on the created {@link ObjectFlow}.
     */
    @objid ("4ce81d1e-13d0-4ecb-a283-20ecf9d46a79")
    public static UML2ExceptionHandler create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ObjectFlow");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME);
        return UML2ExceptionHandler.instantiate((ObjectFlow)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionHandler} proxy from a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ObjectFlow
     * @return a {@link UML2ExceptionHandler} proxy or <i>null</i>.
     */
    @objid ("dcdd3339-d89d-4737-aceb-d9708132a350")
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
    @objid ("104b712c-305e-4ee5-907e-aad685bae902")
    public static UML2ExceptionHandler safeInstantiate(ObjectFlow obj) throws IllegalArgumentException {
        if (UML2ExceptionHandler.canInstantiate(obj))
        	return new UML2ExceptionHandler(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionHandler: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f353fd20-1adc-4bd5-8f2a-1d7389236b54")
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
    @objid ("7ce763f1-87b2-4559-a111-1b39127aec20")
    public ObjectFlow getElement() {
        return this.elt;
    }

    @objid ("adcc6ef9-318e-4981-bd82-1cfe2a011871")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("468d925b-11c2-4c91-9a98-ab0315203d1b")
    protected UML2ExceptionHandler(ObjectFlow elt) {
        this.elt = elt;
    }

    @objid ("ed214f2b-6a8e-4515-9c8d-379b6f62a571")
    public static final class MdaTypes {
        @objid ("972e6df7-42f7-4d44-8347-c8570ed95e69")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8ab9bd78-bcd6-4e44-9644-b91b9abde115")
        private static Stereotype MDAASSOCDEP;

        @objid ("f8a008af-028b-4b38-91d8-d10be058703b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1acb78cd-76cd-48e0-b2e8-1949dd8ff2f1")
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
