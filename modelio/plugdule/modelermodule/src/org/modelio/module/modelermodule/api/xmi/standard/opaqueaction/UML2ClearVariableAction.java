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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a3faa58b-c1d9-477f-956a-bf64179c20dc")
public class UML2ClearVariableAction {
    @objid ("77afcda5-c4b6-4ec8-8281-45f6fade4ad2")
    public static final String STEREOTYPE_NAME = "UML2ClearVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("c3123024-fc37-4e8c-93a5-3c00ca099d0f")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ce09b935-f23a-49cf-9c54-a5d1a3eceaeb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> then instantiate a {@link UML2ClearVariableAction} proxy.
     * 
     * @return a {@link UML2ClearVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b16dcb7f-554a-4558-8164-698e262d48bc")
    public static UML2ClearVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME);
        return UML2ClearVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearVariableAction} proxy or <i>null</i>.
     */
    @objid ("d86808b7-d798-4acc-9bc2-3bd40245a0b2")
    public static UML2ClearVariableAction instantiate(OpaqueAction obj) {
        return UML2ClearVariableAction.canInstantiate(obj) ? new UML2ClearVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fce402e7-bab4-415e-886d-dcb879b093a2")
    public static UML2ClearVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearVariableAction.canInstantiate(obj))
        	return new UML2ClearVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("679bd04d-d76c-421f-a523-df1f1b045cd5")
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
        UML2ClearVariableAction other = (UML2ClearVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("41177d9c-0567-4ce3-a5d5-1a45d1d8ae5d")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ec28a13e-4950-490d-8449-2aed08efa0ff")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2701281a-e2f4-4831-af27-a8f1979fa9b8")
    protected UML2ClearVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f5cadea9-90dd-4d0b-a894-0d94886a75c3")
    public static final class MdaTypes {
        @objid ("f2a80cd5-cf9e-45df-89b5-62259e368c4d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("351c4a75-879c-4574-aa4d-7a104ac820d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b04b832-93b5-4993-9122-456e635ae659")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ba7b05c-a023-4b4a-b5ed-976bc447f60c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b071b025-c2fc-11de-8ac8-001302895b2b");
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
