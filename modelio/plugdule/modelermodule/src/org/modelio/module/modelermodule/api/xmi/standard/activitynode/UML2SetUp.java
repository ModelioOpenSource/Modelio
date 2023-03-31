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
 * Proxy class to handle a {@link ActivityNode} with << UML2SetUp >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("eb6de59a-6ea2-4168-8059-82456e9bf47e")
public class UML2SetUp {
    @objid ("a59b3fd1-1ece-4d33-a638-940873829249")
    public static final String STEREOTYPE_NAME = "UML2SetUp";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("7d161c47-323c-47a1-a3c2-8e58bba4b667")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2SetUp proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2SetUp >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f53871f5-74bd-4548-9a4b-1f1efedace68")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2SetUp >> then instantiate a {@link UML2SetUp} proxy.
     * 
     * @return a {@link UML2SetUp} proxy on the created {@link ActivityNode}.
     */
    @objid ("63d3464b-e10d-45ca-b2b9-f75c21179864")
    public static UML2SetUp create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME);
        return UML2SetUp.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2SetUp} proxy or <i>null</i>.
     */
    @objid ("e6818259-b1a5-4ae5-a65e-3d062fb0297d")
    public static UML2SetUp instantiate(ActivityNode obj) {
        return UML2SetUp.canInstantiate(obj) ? new UML2SetUp(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ActivityNode}
     * @return a {@link UML2SetUp} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2e6a6cfc-b54a-44e3-a154-7ea2dc45b5e3")
    public static UML2SetUp safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2SetUp.canInstantiate(obj))
        	return new UML2SetUp(obj);
        else
        	throw new IllegalArgumentException("UML2SetUp: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("113d6db0-e420-4f39-8850-09a200beebbf")
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
        UML2SetUp other = (UML2SetUp) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("7a085e11-b9eb-46cf-a605-9b6e3f35c713")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("6606ed35-045a-4033-9410-16be0f293262")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("56b56ef2-2a55-4bb1-b7f7-2e5fb030e015")
    protected  UML2SetUp(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("f4de3ad0-deac-44e6-8597-cd122260702a")
    public static final class MdaTypes {
        @objid ("5caa8192-94fd-4e07-a597-4b22b1970d44")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e0f182f6-5722-41a8-9771-064a0308e6e3")
        private static Stereotype MDAASSOCDEP;

        @objid ("46314aff-b990-47ed-9cae-1dcdb6d4b280")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("47b0495a-f721-4781-92c9-283b28fa67f2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86eada10-32d9-11e0-91f3-0027103f347c");
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
