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
 * Proxy class to handle a {@link InputPin} with << UML2RemoveAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e9bdfdee-c4b7-4b08-8bf3-1518a048df86")
public class UML2RemoveAt {
    @objid ("6666665e-c648-4d2e-a9cc-bf3280ac02a2")
    public static final String STEREOTYPE_NAME = "UML2RemoveAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("283c0be9-b2ba-486c-abd5-694821038d4e")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2RemoveAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2RemoveAt >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("697196a3-d9c4-404f-8111-f5486ba04115")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2RemoveAt >> then instantiate a {@link UML2RemoveAt} proxy.
     * 
     * @return a {@link UML2RemoveAt} proxy on the created {@link InputPin}.
     */
    @objid ("b4525f5a-4b85-42a7-a2ff-c37afb3b0747")
    public static UML2RemoveAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME);
        return UML2RemoveAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2RemoveAt} proxy or <i>null</i>.
     */
    @objid ("8f300ded-05a2-4170-ac59-3326b679cc4d")
    public static UML2RemoveAt instantiate(InputPin obj) {
        return UML2RemoveAt.canInstantiate(obj) ? new UML2RemoveAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2RemoveAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6efa898f-0fa2-4cd4-b74b-64e94d095ba1")
    public static UML2RemoveAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2RemoveAt.canInstantiate(obj))
        	return new UML2RemoveAt(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e5953057-35b4-46e6-b6f6-8feb2b86b1bf")
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
        UML2RemoveAt other = (UML2RemoveAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("6fba0192-0f31-43ca-997f-753f023d916b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("9869db8c-e86a-43be-812d-c8d0e959de37")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9163592f-ef56-4c4f-918a-eeb7139ef52f")
    protected  UML2RemoveAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("140353cd-0401-48a1-a01c-d3f021559f83")
    public static final class MdaTypes {
        @objid ("d765d3bf-ea09-4177-bf6e-efedaf89bef5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("55a45a54-9655-4202-a466-8a4d6c1b339a")
        private static Stereotype MDAASSOCDEP;

        @objid ("6f2bf427-ae0d-4344-826e-4237a8432694")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dc1c9f14-b3d5-4ac6-b6a4-b81c4ec6a3cd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "407d1bab-d29f-4f92-b12f-01283c1cc7eb");
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
