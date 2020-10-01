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
 * Proxy class to handle a {@link InputPin} with << UML2RemoveAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e9bdfdee-c4b7-4b08-8bf3-1518a048df86")
public class UML2RemoveAt {
    @objid ("ce87f883-e183-4cbf-828a-e148c45646aa")
    public static final String STEREOTYPE_NAME = "UML2RemoveAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("899e80c0-57d5-4b0d-9229-21be0197ef77")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2RemoveAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2RemoveAt >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2fd691ca-6846-46c9-8e67-7e7113324916")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2RemoveAt >> then instantiate a {@link UML2RemoveAt} proxy.
     * 
     * @return a {@link UML2RemoveAt} proxy on the created {@link InputPin}.
     */
    @objid ("73f054c9-4aee-4de0-b7f8-c53af719559f")
    public static UML2RemoveAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
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
    @objid ("0cf08775-c98b-4111-9e26-86fbf0f04db5")
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
    @objid ("70149118-2105-42f5-b3c5-79311852f0d2")
    public static UML2RemoveAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2RemoveAt.canInstantiate(obj))
        	return new UML2RemoveAt(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ae708881-a177-47f7-82da-4b64beaf7b91")
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
    @objid ("f3d6815c-7e48-4e4b-95b6-4d5211857816")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("73d35b0f-e9a2-4bcb-adca-1ef804b69488")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d699db02-15b4-4cdf-a342-fe07ace111cc")
    protected UML2RemoveAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("140353cd-0401-48a1-a01c-d3f021559f83")
    public static final class MdaTypes {
        @objid ("4e79b247-2de4-4a95-842f-d89691a58ce9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("18918e8a-1591-4bbf-b89a-5de84265ab10")
        private static Stereotype MDAASSOCDEP;

        @objid ("9dfbdd7f-b44e-48f5-a50d-975a6aad9cf6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee293e1a-1c08-472e-af8a-b79f14e28fda")
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
