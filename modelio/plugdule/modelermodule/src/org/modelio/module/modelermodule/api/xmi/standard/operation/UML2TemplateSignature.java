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
package org.modelio.module.modelermodule.api.xmi.standard.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2TemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c64395f8-85c2-42fe-9a80-f23505af725e")
public class UML2TemplateSignature {
    @objid ("8e702a75-11b1-44ef-8fad-dabf8841c456")
    public static final String STEREOTYPE_NAME = "UML2TemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("1da30b5a-c629-4215-aa1c-8df5371285bb")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2TemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2TemplateSignature >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6c2c5776-d971-4f7c-8d87-6b07df741805")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2TemplateSignature >> then instantiate a {@link UML2TemplateSignature} proxy.
     * 
     * @return a {@link UML2TemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("1585fd55-35b7-4d21-9c52-197a1aea715d")
    public static UML2TemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME);
        return UML2TemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2TemplateSignature} proxy or <i>null</i>.
     */
    @objid ("82a1a3d8-749d-447c-81e5-d35542ae8405")
    public static UML2TemplateSignature instantiate(Operation obj) {
        return UML2TemplateSignature.canInstantiate(obj) ? new UML2TemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2TemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("28151be5-9683-4dd2-b504-99bf360153d6")
    public static UML2TemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2TemplateSignature.canInstantiate(obj))
        	return new UML2TemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2TemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ecbd4f6f-aeef-48d7-9cef-e2def773d6a6")
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
        UML2TemplateSignature other = (UML2TemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("1d32cb47-4e9a-4675-8d29-6f12808bf425")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("dc7d87e0-de20-49e2-9329-63711f8bcbee")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("463acf63-1422-4fb4-82a1-457c0c3d25a9")
    protected  UML2TemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("bd5513f3-a00c-4eb5-a954-b968c48149ed")
    public static final class MdaTypes {
        @objid ("778fbd48-b064-4450-a537-ce47ffeea2aa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c752db60-9060-4a6f-ac3e-dd69589e4bc5")
        private static Stereotype MDAASSOCDEP;

        @objid ("58883bd1-452b-49d2-9a0e-1d262537fd8d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("60ecd17e-bd22-4183-88eb-5062e585ceb3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "554cb8bb-5d0e-11df-a996-001302895b2b");
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
