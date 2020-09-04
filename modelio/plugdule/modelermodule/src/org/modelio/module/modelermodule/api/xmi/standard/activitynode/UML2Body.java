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
    @objid ("8df3be65-b64d-4f07-9ad3-43e3c49753c4")
    public static final String STEREOTYPE_NAME = "UML2Body";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("07925f76-8b78-4f8e-ac54-0a0adcab14b3")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2Body proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2Body >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dbe781f1-7e78-43d8-9a97-f597bb4e0a84")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2Body >> then instantiate a {@link UML2Body} proxy.
     * 
     * @return a {@link UML2Body} proxy on the created {@link ActivityNode}.
     */
    @objid ("5a2573d8-4e45-48c4-8a59-cf12ef2f509a")
    public static UML2Body create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME);
        return UML2Body.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Body} proxy from a {@link ActivityNode} stereotyped << UML2Body >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2Body} proxy or <i>null</i>.
     */
    @objid ("91361380-e362-4f98-bd7b-3fcc46f9e907")
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
    @objid ("fdb1007b-749c-4796-894b-6517e9cc31a5")
    public static UML2Body safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2Body.canInstantiate(obj))
        	return new UML2Body(obj);
        else
        	throw new IllegalArgumentException("UML2Body: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e9804de6-b13c-4c41-ad2c-b3b0c041da8a")
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
    @objid ("728e8b36-74de-4663-9058-d028103a11f5")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("42147af6-eb1b-4762-901d-325e492887b4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4854bb19-23d0-48a3-a185-5394ce0d73cf")
    protected UML2Body(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("8331b437-2365-45fa-b380-c464635804d8")
    public static final class MdaTypes {
        @objid ("aa607495-7308-4c18-90bd-d855707dc0a6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0a603042-94be-489b-8ed0-a1b89653da1b")
        private static Stereotype MDAASSOCDEP;

        @objid ("dedc8ddb-a19e-46fd-ab67-a6ca2416c8c7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4fdc427a-0d7f-4701-9341-3fbd4feda95f")
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
