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
    @objid ("47c6c9f9-b1dd-43bf-82cc-74ccd93f283d")
    public static final String STEREOTYPE_NAME = "UML2Result";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("00af1d94-add2-46d4-9fd0-ea2b336cfab0")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Result proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Result >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c8b5122f-4fc4-4df8-948d-10119376f10b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Result >> then instantiate a {@link UML2Result} proxy.
     * 
     * @return a {@link UML2Result} proxy on the created {@link OutputPin}.
     */
    @objid ("ceac183d-5c66-42dc-9cd5-93a950bcb2e5")
    public static UML2Result create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
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
    @objid ("4e6fc1fe-9589-4d98-9b84-b958280302a7")
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
    @objid ("c15bc7dc-1e3f-4b96-aec8-187cd35bda88")
    public static UML2Result safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Result.canInstantiate(obj))
        	return new UML2Result(obj);
        else
        	throw new IllegalArgumentException("UML2Result: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dd529a2c-50b5-4ab6-b095-b1778a68fbb2")
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
    @objid ("53d3eb81-43d4-4b6d-aabc-a0957e341b11")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("d3c7dfae-64c4-4dba-b187-86905df727d0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4f19d0fe-08e8-4ac2-af26-75c5b9465dd5")
    protected UML2Result(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("ad42c1ae-a610-4d17-8bc4-7ff3c78480be")
    public static final class MdaTypes {
        @objid ("f14f574e-1ad4-42da-8086-30a28b7f1726")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4d3dd6fb-d6bf-4df2-86cb-339ded73f560")
        private static Stereotype MDAASSOCDEP;

        @objid ("cb9c3718-2b8e-4247-911d-b2482e2aa9be")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3e588338-df17-4d65-974f-c1514b447884")
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
