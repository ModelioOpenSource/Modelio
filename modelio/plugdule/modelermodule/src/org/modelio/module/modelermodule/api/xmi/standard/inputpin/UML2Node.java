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
 * Proxy class to handle a {@link InputPin} with << UML2Node >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ea8b4221-c8cb-4251-aacd-1582dd0f6eed")
public class UML2Node {
    @objid ("ca0ef32d-24c6-4a9f-8369-71677f074546")
    public static final String STEREOTYPE_NAME = "UML2Node";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("4de85c4d-eecf-4924-b752-1f6285156f50")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Node proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Node >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4529cdc2-8b5a-4208-8f4e-6e4a9b60b2e1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Node >> then instantiate a {@link UML2Node} proxy.
     * 
     * @return a {@link UML2Node} proxy on the created {@link InputPin}.
     */
    @objid ("8a29e1e1-7984-423b-bce7-3929c7967494")
    public static UML2Node create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME);
        return UML2Node.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Node} proxy from a {@link InputPin} stereotyped << UML2Node >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Node} proxy or <i>null</i>.
     */
    @objid ("df2ee161-c61c-45f3-a2fa-302665d6e6a3")
    public static UML2Node instantiate(InputPin obj) {
        return UML2Node.canInstantiate(obj) ? new UML2Node(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Node} proxy from a {@link InputPin} stereotyped << UML2Node >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Node} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f0f15b5a-ec0c-4441-9855-9d7f93175323")
    public static UML2Node safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Node.canInstantiate(obj))
        	return new UML2Node(obj);
        else
        	throw new IllegalArgumentException("UML2Node: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b40e10cd-3a38-4f5e-8dd2-73b25cff818f")
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
        UML2Node other = (UML2Node) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("60596b54-5476-434a-ad45-5f501c1a5b49")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("82a1bc54-e674-461c-a7d2-c4aab2fc81a9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bf60d2c9-d623-49e7-9235-1ba72bdb83ed")
    protected UML2Node(InputPin elt) {
        this.elt = elt;
    }

    @objid ("0c3d3262-938a-44eb-ac4e-1a698f70a47a")
    public static final class MdaTypes {
        @objid ("c55b8530-d17a-498b-bb84-58668e6e06e2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5a159fcf-953d-4a00-aa5f-f1cec9b90865")
        private static Stereotype MDAASSOCDEP;

        @objid ("badd635a-604c-4f77-9e9a-3371e0693638")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e596be6c-2d71-4b64-b4c8-3d9118f8a58e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1a4b1794-3302-4d19-9d02-077990211db2");
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
