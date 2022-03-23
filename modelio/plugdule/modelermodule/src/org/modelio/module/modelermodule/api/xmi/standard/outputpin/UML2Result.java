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
 * Proxy class to handle a {@link OutputPin} with << UML2Result >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("44487931-3470-4987-9272-bb45a6d23c7e")
public class UML2Result {
    @objid ("e4322b00-cde3-4ba8-b04c-8d8b5505b1f9")
    public static final String STEREOTYPE_NAME = "UML2Result";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("bc8f8479-9f29-45dd-bca3-962f4cde2ac4")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Result proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Result >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7023ef7c-8228-46f0-bfa3-375d2e08a742")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Result >> then instantiate a {@link UML2Result} proxy.
     * 
     * @return a {@link UML2Result} proxy on the created {@link OutputPin}.
     */
    @objid ("85a94359-7175-43ff-9864-7687fa6d3d86")
    public static UML2Result create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME);
        return UML2Result.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Result} proxy or <i>null</i>.
     */
    @objid ("92766028-9050-4e25-b039-91c4ad0d9074")
    public static UML2Result instantiate(OutputPin obj) {
        return UML2Result.canInstantiate(obj) ? new UML2Result(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Result} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8eb35604-0837-42cb-909f-19163f918ef8")
    public static UML2Result safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Result.canInstantiate(obj))
        	return new UML2Result(obj);
        else
        	throw new IllegalArgumentException("UML2Result: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("034299fc-50db-45b3-bbd6-717adb820ca4")
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
        UML2Result other = (UML2Result) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("588b76e9-5968-4ca5-a737-7c75b1766246")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("2268d87a-fd23-4799-b51f-18daeb2899c5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("78037b10-0f63-42e1-9c6a-7a8275e4a5b2")
    protected  UML2Result(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("ad42c1ae-a610-4d17-8bc4-7ff3c78480be")
    public static final class MdaTypes {
        @objid ("8738b449-b34c-49d4-8506-a26b4568d5e1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("50e2f8a6-c5b0-4200-97ab-439ba88d846f")
        private static Stereotype MDAASSOCDEP;

        @objid ("12355571-ba17-4694-863f-bbc2fe9a1524")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("84c27a0a-0dad-4fa3-824b-36728fd919e8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8914ba10-818c-4439-8e2b-89671c2288bc");
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
