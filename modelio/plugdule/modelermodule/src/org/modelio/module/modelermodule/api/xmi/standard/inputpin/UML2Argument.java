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
 * Proxy class to handle a {@link InputPin} with << UML2Argument >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f227bc75-d200-4586-8356-abed568e953a")
public class UML2Argument {
    @objid ("914045e3-48f6-4a2c-806f-f1f0778c4824")
    public static final String STEREOTYPE_NAME = "UML2Argument";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("880a4c20-9894-418d-8f7a-842c2d22a0c9")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Argument proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Argument >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("071f10b1-bf6e-40c3-b40c-4b81e6d6fb56")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Argument >> then instantiate a {@link UML2Argument} proxy.
     * 
     * @return a {@link UML2Argument} proxy on the created {@link InputPin}.
     */
    @objid ("be83d68f-98f5-45e1-9008-d9d005085029")
    public static UML2Argument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME);
        return UML2Argument.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Argument} proxy or <i>null</i>.
     */
    @objid ("dba5cdb0-5960-40b3-9a7d-c1d26e41e17b")
    public static UML2Argument instantiate(InputPin obj) {
        return UML2Argument.canInstantiate(obj) ? new UML2Argument(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Argument} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fa8292aa-6d38-45ea-8181-17d7887ba558")
    public static UML2Argument safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Argument.canInstantiate(obj))
        	return new UML2Argument(obj);
        else
        	throw new IllegalArgumentException("UML2Argument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1d714a9c-5e0e-47c7-ae3e-66af595efc50")
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
        UML2Argument other = (UML2Argument) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("44ff4ab2-277b-426d-a4ae-e4e419a82bcb")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("4420d3b0-5aca-4d60-8d31-95de8f9c7aa8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6fb9b14a-24e0-42fc-adad-10cbcf8fdb05")
    protected  UML2Argument(InputPin elt) {
        this.elt = elt;
    }

    @objid ("35371a23-7a89-4c20-ae40-4ea2e5ecc371")
    public static final class MdaTypes {
        @objid ("138cc020-aed4-41ec-a104-3e843b3cd973")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6dc4ab05-fe9a-4956-9a9a-31afa057dcf8")
        private static Stereotype MDAASSOCDEP;

        @objid ("bd48eb98-591f-4eda-aed1-5ee93ef279a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0d9c32d4-3a3b-4511-8538-851ddea8dd78")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86630901-a400-4353-8b38-6db0846d1e38");
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
