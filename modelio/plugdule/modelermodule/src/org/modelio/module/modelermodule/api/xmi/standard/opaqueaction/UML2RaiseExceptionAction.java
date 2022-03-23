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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RaiseExceptionAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59fa1fa5-bbf1-4055-b693-6d921c22cd94")
public class UML2RaiseExceptionAction {
    @objid ("ea28cc7b-6e83-44c0-95e2-5bbfdba4001c")
    public static final String STEREOTYPE_NAME = "UML2RaiseExceptionAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("1dc4f978-b45c-4c63-83f8-c8d7e56a0258")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RaiseExceptionAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6a3f486a-f416-4128-a4d1-8b756d12d172")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> then instantiate a {@link UML2RaiseExceptionAction} proxy.
     * 
     * @return a {@link UML2RaiseExceptionAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("61e89cf7-293d-4308-bc29-b24ba1057db8")
    public static UML2RaiseExceptionAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME);
        return UML2RaiseExceptionAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RaiseExceptionAction} proxy from a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RaiseExceptionAction} proxy or <i>null</i>.
     */
    @objid ("eaa6de15-6046-48fc-9958-173e4cdd6690")
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
    @objid ("05ae0f09-ce1e-4c92-a7d3-b19dc7d1e7b7")
    public static UML2RaiseExceptionAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RaiseExceptionAction.canInstantiate(obj))
        	return new UML2RaiseExceptionAction(obj);
        else
        	throw new IllegalArgumentException("UML2RaiseExceptionAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8f95c6cf-cf0c-44c0-ae10-5ab2f5c0e228")
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
    @objid ("51cb2c3d-b9a1-4ee5-b5e9-c9739840479a")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e6fead08-10f4-496e-9292-d0b26ea28d8c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c59d7f76-7d0d-4ca5-a80f-cef71866e704")
    protected  UML2RaiseExceptionAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("756bcc2b-8b40-459d-9679-09445f1d90d8")
    public static final class MdaTypes {
        @objid ("5d18375e-6513-4305-b173-dc07aeb2ef9e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b5259746-0e26-4a6b-afe5-312d470039d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("a561d987-8d39-489c-8d36-febf35b68e38")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5c9ba039-e9bc-4aca-987e-727368bd6150")
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
