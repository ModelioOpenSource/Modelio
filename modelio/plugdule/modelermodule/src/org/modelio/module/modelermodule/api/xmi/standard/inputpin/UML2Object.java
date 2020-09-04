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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
 * Proxy class to handle a {@link InputPin} with << UML2Object >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4d9f0770-42a1-41ca-b31a-a5051fa170da")
public class UML2Object {
    @objid ("77165a90-1686-428f-9f93-6cea49dd7087")
    public static final String STEREOTYPE_NAME = "UML2Object";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("2242b3ee-6854-4deb-94ef-35b03d9e8c58")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Object proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Object >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("06e4fa41-858f-45bd-94a0-a34f27d36b30")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Object >> then instantiate a {@link UML2Object} proxy.
     * 
     * @return a {@link UML2Object} proxy on the created {@link InputPin}.
     */
    @objid ("9d507b97-0e0a-4287-b213-24b8371d58ee")
    public static UML2Object create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME);
        return UML2Object.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Object} proxy or <i>null</i>.
     */
    @objid ("2b9b21ff-8eca-4e34-9b62-2a7dd4875e4e")
    public static UML2Object instantiate(InputPin obj) {
        return UML2Object.canInstantiate(obj) ? new UML2Object(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Object} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c88a6f11-2a36-4078-9377-fc4818926bf2")
    public static UML2Object safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Object.canInstantiate(obj))
        	return new UML2Object(obj);
        else
        	throw new IllegalArgumentException("UML2Object: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2ddb8557-89d4-425f-a4e8-0fffb517a26f")
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
        UML2Object other = (UML2Object) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("e5d893db-851c-4b0e-a68d-e449a6094db6")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("ce0c94e2-6068-4f3d-bff3-a003c57a5359")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ba0831e3-d7c8-4daf-8840-ef35c16c85fd")
    protected UML2Object(InputPin elt) {
        this.elt = elt;
    }

    @objid ("a5f36406-a8bc-4203-87a4-7ada95b3bbc6")
    public static final class MdaTypes {
        @objid ("e40d49d2-d6e0-4b67-8d3e-25d588801e71")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7c544ec8-435b-4737-bfa1-11d1ada8e05a")
        private static Stereotype MDAASSOCDEP;

        @objid ("5ef99bb5-acde-4655-b359-7596e90c4ace")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2999c5a1-21e4-48fb-b11c-4cd22f7e2d6e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f82bed81-afcc-41fc-b014-b9ce92bb5377");
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
