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
package org.modelio.module.modelermodule.api.default_.standard.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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
 * Proxy class to handle a {@link Signal} with << exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d36d02b-ca62-4e9d-ac9f-5616c922fdd2")
public class Exception {
    @objid ("6f2a7ebb-0952-4a13-b532-a4a6d2b7b22b")
    public static final String STEREOTYPE_NAME = "exception";

    /**
     * The underlying {@link Signal} represented by this proxy, never null.
     */
    @objid ("6d1b09d2-3348-409a-843e-ed5ac1f1f30f")
    protected final Signal elt;

    /**
     * Tells whether a {@link Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link Signal} stereotyped << exception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0df7de60-8d5d-41db-9f4a-50b2486bc0ee")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Signal) && ((Signal) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Signal} stereotyped << exception >> then instantiate a {@link Exception} proxy.
     * 
     * @return a {@link Exception} proxy on the created {@link Signal}.
     */
    @objid ("f03ba7eb-1238-498b-8a42-ba7d363ff7e4")
    public static Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Signal");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME);
        return Exception.instantiate((Signal)e);
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Signal
     * @return a {@link Exception} proxy or <i>null</i>.
     */
    @objid ("685a7e12-bd17-4a7a-a4ff-35145f07500c")
    public static Exception instantiate(Signal obj) {
        return Exception.canInstantiate(obj) ? new Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Signal}
     * @return a {@link Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8094d93e-c867-4220-8d7e-b72ea56d8209")
    public static Exception safeInstantiate(Signal obj) throws IllegalArgumentException {
        if (Exception.canInstantiate(obj))
        	return new Exception(obj);
        else
        	throw new IllegalArgumentException("Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("917e5d53-c279-46a0-bd50-56374f30f46c")
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
        Exception other = (Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Signal}. 
     * @return the Signal represented by this proxy, never null.
     */
    @objid ("780c7c26-517c-4640-a4b3-0ae7af515ba4")
    public Signal getElement() {
        return this.elt;
    }

    @objid ("c38578f5-f4d0-4a26-959a-d76a5e777c59")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("74bab799-409e-4173-b7ac-79474e2cdc28")
    protected  Exception(Signal elt) {
        this.elt = elt;
    }

    @objid ("9d616c88-ee7e-4e3e-a81d-c779a5fefc14")
    public static final class MdaTypes {
        @objid ("92e2cb05-4bc6-4cd6-b3bc-394529ff0eea")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b56648a6-905f-4b94-b789-883d030945f8")
        private static Stereotype MDAASSOCDEP;

        @objid ("2b2c3d7b-b8e4-4e8b-957d-8487f10f8047")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e6a46960-f49f-4717-b74a-a8821ddb0c17")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d1-0000-000000000000");
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
