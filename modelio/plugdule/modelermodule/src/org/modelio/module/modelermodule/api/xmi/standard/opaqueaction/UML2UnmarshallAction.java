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
 * Proxy class to handle a {@link OpaqueAction} with << UML2UnmarshallAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("79f49b91-19a8-4376-b6f1-84c1d30a8534")
public class UML2UnmarshallAction {
    @objid ("6c162c5f-dcdb-4cc8-9854-240e86388d8a")
    public static final String STEREOTYPE_NAME = "UML2UnmarshallAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("03f02c54-03ef-4f6a-baad-5ff681228100")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2UnmarshallAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a42c0374-d339-4ada-93c9-5d8e3cc584a2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> then instantiate a {@link UML2UnmarshallAction} proxy.
     * 
     * @return a {@link UML2UnmarshallAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a931db51-00e8-4890-bb37-846665b35c76")
    public static UML2UnmarshallAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME);
        return UML2UnmarshallAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2UnmarshallAction} proxy or <i>null</i>.
     */
    @objid ("5a6db16c-3e71-4322-842a-f251c1766336")
    public static UML2UnmarshallAction instantiate(OpaqueAction obj) {
        return UML2UnmarshallAction.canInstantiate(obj) ? new UML2UnmarshallAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2UnmarshallAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("14fc3ff3-e4cf-4d57-b68e-cdb1cb8cf5a8")
    public static UML2UnmarshallAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2UnmarshallAction.canInstantiate(obj))
        	return new UML2UnmarshallAction(obj);
        else
        	throw new IllegalArgumentException("UML2UnmarshallAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("342f5f11-65f3-4e6a-8ebe-47f22d018f53")
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
        UML2UnmarshallAction other = (UML2UnmarshallAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("17ea6541-efcb-4488-b057-8c8ff1fb43a9")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("359c5ad6-0633-4131-92f8-609664568769")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b2fa57af-85f5-4f84-8b92-3e06a00d541b")
    protected  UML2UnmarshallAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3dccb5eb-58b9-4a9d-ae64-d0336e7eb538")
    public static final class MdaTypes {
        @objid ("e994547e-24a4-4517-8918-f4a7aa2bfeab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0a21cb8d-006d-4e3e-aced-10e5e4247ac7")
        private static Stereotype MDAASSOCDEP;

        @objid ("2d862f5c-71a3-4af6-9a4d-dc7c9e7a7349")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6d734c51-e4ef-44c5-b974-7739f183643f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76c174ab-c2fd-11de-8ac8-001302895b2b");
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
