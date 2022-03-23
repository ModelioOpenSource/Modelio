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
 * Proxy class to handle a {@link InputPin} with << UML2Target >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c9ef7950-aef3-455f-bd22-02a260ca445a")
public class UML2Target {
    @objid ("8f41de94-eece-4783-a0e5-01a10b30ca2a")
    public static final String STEREOTYPE_NAME = "UML2Target";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7c3068dc-9853-4f3b-b568-d53fb5739e97")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Target proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Target >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fa1cdfd6-7a8b-4d34-8289-210a63b3340e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Target >> then instantiate a {@link UML2Target} proxy.
     * 
     * @return a {@link UML2Target} proxy on the created {@link InputPin}.
     */
    @objid ("6ef1ba8d-c274-4902-8430-4a4d1a276b5d")
    public static UML2Target create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME);
        return UML2Target.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Target} proxy or <i>null</i>.
     */
    @objid ("558b1278-b508-4e26-8bce-1023d616b45f")
    public static UML2Target instantiate(InputPin obj) {
        return UML2Target.canInstantiate(obj) ? new UML2Target(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Target} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("18775652-d54c-40ae-8c6d-c01e72f12918")
    public static UML2Target safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Target.canInstantiate(obj))
        	return new UML2Target(obj);
        else
        	throw new IllegalArgumentException("UML2Target: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("37e6de6d-3a72-4bf2-bf38-2a8abddbb130")
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
        UML2Target other = (UML2Target) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("77bb62a4-cc0e-455a-bc0a-b05050f2c282")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d1d63c5e-fe05-4b2c-a9aa-023abcb2c836")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("a2744335-fecb-4584-9f79-b5cf714e6b76")
    protected  UML2Target(InputPin elt) {
        this.elt = elt;
    }

    @objid ("f7e8a899-0f5c-4177-848d-e2e7b037113a")
    public static final class MdaTypes {
        @objid ("361af955-be95-4aa5-9b75-121fc1d1477f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6d016281-5a16-4982-9582-de803d79fb78")
        private static Stereotype MDAASSOCDEP;

        @objid ("aead3804-64b0-45c5-92d3-1b8838930084")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d64559af-b9fd-4235-b67c-a61067c2727e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "aa99ee06-c495-11de-ada1-001302895b2b");
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
