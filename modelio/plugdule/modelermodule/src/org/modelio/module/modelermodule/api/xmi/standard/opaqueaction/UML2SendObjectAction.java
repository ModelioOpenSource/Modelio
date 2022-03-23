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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2SendObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1de174fc-1ed7-4452-9f8b-57656fbeb200")
public class UML2SendObjectAction {
    @objid ("e2c530bb-ddbb-473c-ad38-22ab95982da6")
    public static final String STEREOTYPE_NAME = "UML2SendObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("08d9cb3c-659e-48ed-895e-5aeaed218f57")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2SendObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b7184c05-a0cd-457e-b62f-95d2bf5d5300")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2SendObjectAction >> then instantiate a {@link UML2SendObjectAction} proxy.
     * 
     * @return a {@link UML2SendObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0be607fd-41c4-46d0-aaac-ac2fc2ebe296")
    public static UML2SendObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME);
        return UML2SendObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2SendObjectAction} proxy or <i>null</i>.
     */
    @objid ("15e1eb52-972e-4dd6-9463-1a2fdf051130")
    public static UML2SendObjectAction instantiate(OpaqueAction obj) {
        return UML2SendObjectAction.canInstantiate(obj) ? new UML2SendObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2SendObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("567e0485-ea69-4809-a46f-381febf82efa")
    public static UML2SendObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2SendObjectAction.canInstantiate(obj))
        	return new UML2SendObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2SendObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("14e5ef6e-ab96-4dd7-8ceb-0b7df8aa733b")
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
        UML2SendObjectAction other = (UML2SendObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("dc070e1d-298f-44d4-914f-2f7b2024223e")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("86113836-55b1-4eb4-944f-9bbd931e27b7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("bcc9ec7b-0ce9-4741-adba-aaea6410bedd")
    protected  UML2SendObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("66231600-a2af-46ef-9aea-d8405fd2e16e")
    public static final class MdaTypes {
        @objid ("ad8da3d0-eecc-4a3a-befe-51648ffb3b13")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f795a823-3730-49cd-9a54-1be455c28083")
        private static Stereotype MDAASSOCDEP;

        @objid ("5291b1d1-ea8f-40be-8e6b-a8c806a3f350")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fb419d76-926f-426f-b8c2-979b2f4f8f9c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "52d7cccb-c2fd-11de-8ac8-001302895b2b");
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
