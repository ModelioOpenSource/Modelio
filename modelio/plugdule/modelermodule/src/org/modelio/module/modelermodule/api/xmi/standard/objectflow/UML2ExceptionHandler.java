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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("2b841b94-1ab4-4ab4-bd89-dee196f08765")
    public static final String STEREOTYPE_NAME = "UML2ExceptionHandler";

    /**
     * The underlying {@link ObjectFlow} represented by this proxy, never null.
     */
    @objid ("b251ae41-b70d-41f8-95c6-04d2f0a3b862")
    protected final ObjectFlow elt;

    /**
     * Tells whether a {@link UML2ExceptionHandler proxy} can be instantiated from a {@link MObject} checking it is a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2e7f2c5b-2044-4da2-b308-8287db68f35f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ObjectFlow) && ((ObjectFlow) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> then instantiate a {@link UML2ExceptionHandler} proxy.
     * 
     * @return a {@link UML2ExceptionHandler} proxy on the created {@link ObjectFlow}.
     */
    @objid ("43aa2cbb-691c-411e-830a-5f0e7d658aad")
    public static UML2ExceptionHandler create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ObjectFlow");
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
    @objid ("cbfde7f0-5eab-438a-85ae-1d9c908777a2")
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
    @objid ("356b57eb-7d5f-403e-aa48-a425710f1cfa")
    public static UML2ExceptionHandler safeInstantiate(ObjectFlow obj) throws IllegalArgumentException {
        if (UML2ExceptionHandler.canInstantiate(obj))
        	return new UML2ExceptionHandler(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionHandler: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("76e1f85e-ea30-4867-ac47-ad27dc86ea71")
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
    @objid ("cdabee1e-d47e-4519-82ad-9e1b8d1f084d")
    public ObjectFlow getElement() {
        return this.elt;
    }

    @objid ("f5d81fc6-adb0-4d89-a324-9590f5c39af7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b398a389-eb98-43ee-94f9-5dc62ab247c8")
    protected  UML2ExceptionHandler(ObjectFlow elt) {
        this.elt = elt;
    }

    @objid ("ed214f2b-6a8e-4515-9c8d-379b6f62a571")
    public static final class MdaTypes {
        @objid ("e47d0f50-9640-4886-909c-b5b139f36d68")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f2f0424b-97b6-432d-b171-00cee670b69d")
        private static Stereotype MDAASSOCDEP;

        @objid ("bd8e3aba-f47a-40fc-8e38-a18eb57f179b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c43a9b20-8a54-42eb-9c11-476680656497")
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
