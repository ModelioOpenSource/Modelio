/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RaiseExceptionAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59fa1fa5-bbf1-4055-b693-6d921c22cd94")
public class UML2RaiseExceptionAction {
    @objid ("6cc91468-72db-4d55-9ddc-0d4ea43891ec")
    public static final String STEREOTYPE_NAME = "UML2RaiseExceptionAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ae7e8ba8-073a-4b22-a5e1-fdd21bbfac5a")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RaiseExceptionAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("26061c8a-ece0-4361-9f89-f41ed950e1d9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> then instantiate a {@link UML2RaiseExceptionAction} proxy.
     * 
     * @return a {@link UML2RaiseExceptionAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b3c2cb75-7fd3-4946-a791-0c142e1319cf")
    public static UML2RaiseExceptionAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME);
        return UML2RaiseExceptionAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RaiseExceptionAction} proxy from a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RaiseExceptionAction} proxy or <i>null</i>.
     */
    @objid ("956464e5-72b7-4f7c-85c6-46327cf079db")
    public static UML2RaiseExceptionAction instantiate(OpaqueAction obj) {
        return UML2RaiseExceptionAction.canInstantiate(obj) ? new UML2RaiseExceptionAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RaiseExceptionAction} proxy from a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RaiseExceptionAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b52cbde0-c883-45e9-b0cb-a2e93a22168e")
    public static UML2RaiseExceptionAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RaiseExceptionAction.canInstantiate(obj))
        	return new UML2RaiseExceptionAction(obj);
        else
        	throw new IllegalArgumentException("UML2RaiseExceptionAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8cdeb298-6700-49b0-8dfa-418e94d246ba")
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
        UML2RaiseExceptionAction other = (UML2RaiseExceptionAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("ffb24970-54b7-410f-b855-d1ea6b2ba431")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("09a193d4-df7b-493c-a373-9d65c45c134e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("da0dcc23-e440-4e44-89e5-8d35d0972857")
    protected UML2RaiseExceptionAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("756bcc2b-8b40-459d-9679-09445f1d90d8")
    public static final class MdaTypes {
        @objid ("51bcfb2a-e082-4de0-a494-5e163ed46342")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("67eff640-4ab3-4fe0-a458-f24854c32e93")
        private static Stereotype MDAASSOCDEP;

        @objid ("3c093031-c46b-4cf5-9214-87530f9262fc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5a4b7613-6a24-4e1d-b0e5-3895b148db15")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d09356f1-c2fc-11de-8ac8-001302895b2b");
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
