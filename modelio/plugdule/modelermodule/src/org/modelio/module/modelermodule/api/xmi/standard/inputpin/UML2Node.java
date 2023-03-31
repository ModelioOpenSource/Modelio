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
 * Proxy class to handle a {@link InputPin} with << UML2Node >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ea8b4221-c8cb-4251-aacd-1582dd0f6eed")
public class UML2Node {
    @objid ("54e59ccf-2b83-4149-ab15-1c93d39fcb62")
    public static final String STEREOTYPE_NAME = "UML2Node";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("f5e5ee52-5155-4fc4-952e-aa7e0ead2f65")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Node proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Node >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("84abacfe-e27c-4d42-bb8c-ff8fc97b4701")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Node >> then instantiate a {@link UML2Node} proxy.
     * 
     * @return a {@link UML2Node} proxy on the created {@link InputPin}.
     */
    @objid ("6dd9f8a4-4711-4708-9d8d-59aa4eacf7d9")
    public static UML2Node create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
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
    @objid ("2c820e16-5884-4c40-a5ab-31cafd2d0473")
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
    @objid ("65a75551-91eb-498d-a865-6db20e0ecd7a")
    public static UML2Node safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Node.canInstantiate(obj))
        	return new UML2Node(obj);
        else
        	throw new IllegalArgumentException("UML2Node: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9700faf4-e474-4469-b613-0375c704e361")
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
    @objid ("6cfc4b31-eddc-460b-bdcc-1630960f459c")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("8f03a3d9-84ed-408b-b598-247a5c28deb8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3a46c29d-14ac-4954-a3ed-d1d8132401a3")
    protected  UML2Node(InputPin elt) {
        this.elt = elt;
    }

    @objid ("0c3d3262-938a-44eb-ac4e-1a698f70a47a")
    public static final class MdaTypes {
        @objid ("728061ab-21a8-4f39-aa25-378fa0aa1dad")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("80db2973-5e35-4429-a0f6-1342db2ab73c")
        private static Stereotype MDAASSOCDEP;

        @objid ("839e056b-0591-4a4d-bbc1-cf02a7b80c1a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4fe403a7-c6fa-4849-8985-e78c8eee8634")
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
