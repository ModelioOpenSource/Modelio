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
 * Proxy class to handle a {@link InputPin} with << UML2First >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1d8bbfa9-4d67-49bd-9cf2-8020cc4981d7")
public class UML2First {
    @objid ("a98b7ab7-dcda-496f-805f-f05f98762a8c")
    public static final String STEREOTYPE_NAME = "UML2First";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("0ec3b6a6-1485-46aa-990d-cff0d5d09433")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2First proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2First >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3437e084-f322-4251-a827-3bac1bc35b50")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2First >> then instantiate a {@link UML2First} proxy.
     * 
     * @return a {@link UML2First} proxy on the created {@link InputPin}.
     */
    @objid ("97f7b85c-b937-44d3-a129-0133604b0312")
    public static UML2First create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME);
        return UML2First.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2First} proxy or <i>null</i>.
     */
    @objid ("9cd10570-fb12-4af1-9fa6-d1d3d16a08d7")
    public static UML2First instantiate(InputPin obj) {
        return UML2First.canInstantiate(obj) ? new UML2First(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2First} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f507f96f-21fa-4e38-bf4e-d900ff029e05")
    public static UML2First safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2First.canInstantiate(obj))
        	return new UML2First(obj);
        else
        	throw new IllegalArgumentException("UML2First: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("33c70e35-27d1-445b-ac58-907051b7b437")
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
        UML2First other = (UML2First) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("4665f284-4074-4f44-a2d2-71365c4310ee")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a826c4e1-ccd7-4d6f-91c4-1b15bf60f549")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("809007ce-b240-476d-bd21-d507800b17df")
    protected UML2First(InputPin elt) {
        this.elt = elt;
    }

    @objid ("231fcc4f-1387-4364-b0db-960af406ba29")
    public static final class MdaTypes {
        @objid ("a13a7f67-6cab-4381-90cf-2276e2b6044b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("896e6c2d-4520-4b15-a443-f29cbe9ca7f2")
        private static Stereotype MDAASSOCDEP;

        @objid ("ce634055-9dde-477e-80c9-cd50b8d34e85")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6b3ff478-a7a5-4f95-9340-89e42bca5efd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "37d0688a-c308-11de-8ac8-001302895b2b");
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
