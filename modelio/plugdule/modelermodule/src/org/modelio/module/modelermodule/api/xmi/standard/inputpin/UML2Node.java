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
 * Proxy class to handle a {@link InputPin} with << UML2Node >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ea8b4221-c8cb-4251-aacd-1582dd0f6eed")
public class UML2Node {
    @objid ("01c0c7b9-d95a-406a-a888-c370331c91fd")
    public static final String STEREOTYPE_NAME = "UML2Node";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("b9289d9a-9f96-4fff-a637-68c23bcebe7c")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Node proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Node >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8581fbfe-6ab6-4f56-98f5-b25cf1ac429f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Node >> then instantiate a {@link UML2Node} proxy.
     * 
     * @return a {@link UML2Node} proxy on the created {@link InputPin}.
     */
    @objid ("0051359f-f722-460a-ae92-e08c88225260")
    public static UML2Node create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME);
        return UML2Node.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Node} proxy from a {@link InputPin} stereotyped << UML2Node >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Node} proxy or <i>null</i>.
     */
    @objid ("b8cbe635-cd2a-4c06-b327-361ccee3af6b")
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
    @objid ("b9fd29f8-1430-4f8a-8285-2e91b8fa0bb0")
    public static UML2Node safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Node.canInstantiate(obj))
        	return new UML2Node(obj);
        else
        	throw new IllegalArgumentException("UML2Node: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("066dc954-bc84-49ac-b51c-c75ac666c388")
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
    @objid ("70830c49-7a25-4e66-8129-98a13efc6b22")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("eab2a078-d349-41bd-97d3-f525b01e3803")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("317cc64d-7034-42b7-9dec-ad8abcc07bdd")
    protected UML2Node(InputPin elt) {
        this.elt = elt;
    }

    @objid ("0c3d3262-938a-44eb-ac4e-1a698f70a47a")
    public static final class MdaTypes {
        @objid ("2ed608ac-819b-44ed-9285-410b0fe4fba4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b59a2aea-c469-47ce-95f4-5af7eb612b9e")
        private static Stereotype MDAASSOCDEP;

        @objid ("a158a20b-cfcf-4ef7-bef9-0ec73aba69b5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4470a01c-f1dd-4c5a-ad82-15c8c1eff673")
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
