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
package org.modelio.module.modelermodule.api.xmi.standard.activitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
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
 * Proxy class to handle a {@link ActivityNode} with << UML2Body >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("83b25ebe-46ce-430e-986a-831f705f3318")
public class UML2Body {
    @objid ("730fbe3c-2612-4e74-9c3b-7cd93b4dcad3")
    public static final String STEREOTYPE_NAME = "UML2Body";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("338827ce-0d49-4e77-92d9-d5ac84d64208")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2Body proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2Body >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("62df24c2-8e5f-4084-9b78-727e2d79202b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2Body >> then instantiate a {@link UML2Body} proxy.
     * 
     * @return a {@link UML2Body} proxy on the created {@link ActivityNode}.
     */
    @objid ("33259405-6e96-4419-8281-3ecbba7aabf7")
    public static UML2Body create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME);
        return UML2Body.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Body} proxy from a {@link ActivityNode} stereotyped << UML2Body >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2Body} proxy or <i>null</i>.
     */
    @objid ("fc6168b8-2857-4c8e-aada-1d0a962090da")
    public static UML2Body instantiate(ActivityNode obj) {
        return UML2Body.canInstantiate(obj) ? new UML2Body(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Body} proxy from a {@link ActivityNode} stereotyped << UML2Body >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ActivityNode}
     * @return a {@link UML2Body} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d04ecae9-b04b-4ff2-bb65-b79565d668f3")
    public static UML2Body safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2Body.canInstantiate(obj))
        	return new UML2Body(obj);
        else
        	throw new IllegalArgumentException("UML2Body: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("17119fec-4c68-45da-bc71-b8cf6afae5c6")
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
        UML2Body other = (UML2Body) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("1464abfa-034a-4e29-ae04-18c8d7a150d6")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("213a6235-8fb0-4c9d-83ff-0b56d4c24ded")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ec6c008d-f6b9-47f1-a0bc-49cc4a79fbf3")
    protected UML2Body(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("8331b437-2365-45fa-b380-c464635804d8")
    public static final class MdaTypes {
        @objid ("8dd688d2-62e2-461a-a941-6ac20383927f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9e536dfe-226f-4f17-a1ce-dc0a7a085ea3")
        private static Stereotype MDAASSOCDEP;

        @objid ("140440d8-3516-406d-93e8-4e6f42d33679")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b6320848-bb7a-42d0-bdcd-682d1b00655c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76f275f9-32d9-11e0-91f3-0027103f347c");
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
