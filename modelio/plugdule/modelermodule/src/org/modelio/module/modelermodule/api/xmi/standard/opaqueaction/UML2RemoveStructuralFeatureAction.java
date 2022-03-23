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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("483e6c61-31d1-4ff3-912d-56394bd2ed10")
public class UML2RemoveStructuralFeatureAction {
    @objid ("989e84c8-a04d-4755-8241-63722d3a0b95")
    public static final String STEREOTYPE_NAME = "UML2RemoveStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("4c9f36c6-fb1f-4011-b4de-449148e26176")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0a37570f-1abf-4408-a539-ecbb80c28edc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> then instantiate a {@link UML2RemoveStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("1ea82e38-8ccd-4f5f-973d-37634ce088ed")
    public static UML2RemoveStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2RemoveStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("e61ef1f6-77a4-49fb-b5c4-25ffbefe8a0f")
    public static UML2RemoveStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2RemoveStructuralFeatureAction.canInstantiate(obj) ? new UML2RemoveStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("18dabe66-df49-45d7-9da7-3b12dc01415f")
    public static UML2RemoveStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveStructuralFeatureAction.canInstantiate(obj))
        	return new UML2RemoveStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1c34ed0f-657e-4a3f-aa59-335b5e70fb30")
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
        UML2RemoveStructuralFeatureAction other = (UML2RemoveStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("6d54675d-a851-4219-b107-3d49dcb98802")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("1d74c6f5-a30d-4299-965d-39b17c88fe11")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("1501dbf8-bc8d-43dc-89fc-e8c6757232eb")
    protected  UML2RemoveStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5059f30e-33d1-46b2-a966-8c981d0db622")
    public static final class MdaTypes {
        @objid ("be4d265a-bde1-4e73-9336-50a63811a537")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6f25b160-1b70-4e83-81d9-f1c2d68218dd")
        private static Stereotype MDAASSOCDEP;

        @objid ("a654fcf6-2caf-46e9-8f32-e22ecfb98219")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("01809aaa-ccfb-47d6-8d4d-062d2dadaff0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "35b84299-c2fd-11de-8ac8-001302895b2b");
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
