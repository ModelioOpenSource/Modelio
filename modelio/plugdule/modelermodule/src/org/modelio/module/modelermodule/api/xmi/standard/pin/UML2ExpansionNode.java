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
 * Proxy class to handle a {@link Pin} with << UML2ExpansionNode >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c1c157dd-3d9f-46e4-ae7e-b086613744bc")
public class UML2ExpansionNode {
    @objid ("355f1025-434c-4a27-becc-c0e8759afe73")
    public static final String STEREOTYPE_NAME = "UML2ExpansionNode";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("56d3aa3f-1559-42ec-b2a8-0d18970ef27d")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ExpansionNode proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ExpansionNode >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a5defdfd-f032-4f8d-a964-744045740d6f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ExpansionNode >> then instantiate a {@link UML2ExpansionNode} proxy.
     * 
     * @return a {@link UML2ExpansionNode} proxy on the created {@link Pin}.
     */
    @objid ("562d4579-29c0-47a1-9e70-095fcd89c51c")
    public static UML2ExpansionNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME);
        return UML2ExpansionNode.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ExpansionNode} proxy or <i>null</i>.
     */
    @objid ("140c648f-b717-4908-a820-4d372b031ab7")
    public static UML2ExpansionNode instantiate(Pin obj) {
        return UML2ExpansionNode.canInstantiate(obj) ? new UML2ExpansionNode(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ExpansionNode} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6b50e9a2-4a56-4e14-80f3-42c25fb3728c")
    public static UML2ExpansionNode safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ExpansionNode.canInstantiate(obj))
        	return new UML2ExpansionNode(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0adb3dfc-c576-4ae1-9020-23605fb49c12")
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
        UML2ExpansionNode other = (UML2ExpansionNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("e805f36a-6594-4c62-8477-5011cf3acda8")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("f01485e1-de04-4293-b35e-d5a1212941bd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("711b9c07-4e3b-4930-b8f8-12c5a703db14")
    protected  UML2ExpansionNode(Pin elt) {
        this.elt = elt;
    }

    @objid ("6697fd3d-89c7-4e82-8ddc-c23cc8607673")
    public static final class MdaTypes {
        @objid ("177ce343-78a5-4ae3-9970-340baf1fccec")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4877effa-9983-4af9-810a-f89fa9b87150")
        private static Stereotype MDAASSOCDEP;

        @objid ("b31b034e-fc50-4a03-97e4-5f10c3930d03")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3e49a39d-d8a4-478d-9fae-f5a30e9867f9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1b1ba62d-205e-11df-948e-001302895b2b");
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
