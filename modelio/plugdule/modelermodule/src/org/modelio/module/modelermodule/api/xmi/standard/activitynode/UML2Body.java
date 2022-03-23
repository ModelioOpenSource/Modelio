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
    @objid ("9bc257c1-de4a-452c-9814-26f4452dc4d5")
    public static final String STEREOTYPE_NAME = "UML2Body";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("213f72df-bafc-42cb-b4d2-2fcbba209366")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2Body proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2Body >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fdcc7635-b84d-4830-a85c-2bcdd0b66f41")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2Body >> then instantiate a {@link UML2Body} proxy.
     * 
     * @return a {@link UML2Body} proxy on the created {@link ActivityNode}.
     */
    @objid ("6321d5e5-8e9b-49dc-b426-bcc595cacdf9")
    public static UML2Body create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ActivityNode");
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
    @objid ("b1298db4-1113-49ef-8500-60d0ead676ed")
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
    @objid ("7c0021dc-0289-4607-92cc-d01da89dd35c")
    public static UML2Body safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2Body.canInstantiate(obj))
        	return new UML2Body(obj);
        else
        	throw new IllegalArgumentException("UML2Body: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("327df9eb-f961-4f2b-894a-2501dbe64471")
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
    @objid ("c544d167-53dd-46f3-b702-1992732a81fe")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("581afa81-ac23-4a5f-9321-da7eb3d5e7d0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("486a2822-a132-470b-be1b-86efb8386cb8")
    protected  UML2Body(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("8331b437-2365-45fa-b380-c464635804d8")
    public static final class MdaTypes {
        @objid ("49f57888-92d0-4808-a3ee-23d8fb185d17")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d827d451-e5e7-4932-a9f9-6636f2d7b147")
        private static Stereotype MDAASSOCDEP;

        @objid ("246ff140-9106-4f75-8788-94cbbadec9ec")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0fae10af-4243-4847-beff-2020ea1eeded")
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
