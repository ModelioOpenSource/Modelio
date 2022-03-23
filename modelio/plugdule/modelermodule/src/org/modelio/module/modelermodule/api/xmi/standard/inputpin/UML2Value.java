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
    @objid ("368a9faf-3b9a-4989-ad9b-f5e1733ac470")
    public static final String STEREOTYPE_NAME = "UML2Value";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("8f53e515-8427-4625-9d4d-329d31337d9a")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Value proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Value >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b2d16d6c-b239-4bd8-bdd2-712c9d53966a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Value >> then instantiate a {@link UML2Value} proxy.
     * 
     * @return a {@link UML2Value} proxy on the created {@link InputPin}.
     */
    @objid ("e732fa5e-5a01-4efc-8484-876787ca4ff4")
    public static UML2Value create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME);
        return UML2Value.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Value} proxy or <i>null</i>.
     */
    @objid ("a0fbb04b-ba65-4aef-90ce-6482f620d869")
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
    @objid ("41fa6874-6447-47a1-b5d4-59260d020cb0")
    public static UML2Value safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Value.canInstantiate(obj))
        	return new UML2Value(obj);
        else
        	throw new IllegalArgumentException("UML2Value: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4ce65865-139e-4a03-9bf1-89eda1eec39b")
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
    @objid ("2729841d-8f57-4749-a9ad-29ef5455fab6")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("683b93dc-482b-4e82-b809-0f90ce954be4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("38805cdb-2356-4332-831b-e5f854d0f3bb")
    protected  UML2Value(InputPin elt) {
        this.elt = elt;
    }

    @objid ("5619aea8-dfda-4511-8227-6948387e1280")
    public static final class MdaTypes {
        @objid ("3b690968-2bde-4761-998b-61d804c489fd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9680c8d9-ea65-4375-8a8b-322d6a9f1bae")
        private static Stereotype MDAASSOCDEP;

        @objid ("12f2ef3b-78ff-498d-ab75-1a71f0ed9680")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4e47066a-882e-4d5b-a002-1259fc29fb18")
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
