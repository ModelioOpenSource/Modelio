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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadSelfAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("797d5347-a197-414c-84b4-44f2f877b47e")
public class UML2ReadSelfAction {
    @objid ("58888792-409a-4daa-9103-a2b9f4e4192a")
    public static final String STEREOTYPE_NAME = "UML2ReadSelfAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("24db0d43-519b-4ba3-b1cf-9e61959e9228")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadSelfAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("98fb7dc1-f2ef-4197-b487-853168b278a4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> then instantiate a {@link UML2ReadSelfAction} proxy.
     * 
     * @return a {@link UML2ReadSelfAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("699e9cc1-40ec-4e2e-97c6-1005fbae5792")
    public static UML2ReadSelfAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME);
        return UML2ReadSelfAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadSelfAction} proxy or <i>null</i>.
     */
    @objid ("ffbf6eae-ac58-4004-b2ed-f1a5154f5337")
    public static UML2ReadSelfAction instantiate(OpaqueAction obj) {
        return UML2ReadSelfAction.canInstantiate(obj) ? new UML2ReadSelfAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadSelfAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0f57096f-8244-46e3-a2b8-2c18df1d2967")
    public static UML2ReadSelfAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadSelfAction.canInstantiate(obj))
        	return new UML2ReadSelfAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadSelfAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("faba5893-5453-465e-918b-d22492e83f63")
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
        UML2ReadSelfAction other = (UML2ReadSelfAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("a139d685-88e1-4cfd-aff3-ec58bc292f26")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e2a6f52e-db28-4660-87e8-aafd912d8777")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2b5478af-0811-472f-aee2-c03932c45e93")
    protected  UML2ReadSelfAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("9b695724-53ea-403f-aee0-9d75de248162")
    public static final class MdaTypes {
        @objid ("0cc930d5-a604-4516-9678-babca7b17ed9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ca853885-cf0a-40a9-9fcb-adac455e6a71")
        private static Stereotype MDAASSOCDEP;

        @objid ("a4c8ab37-106a-4729-b5d5-a1f9bcff3b60")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("62af6af0-0431-48be-9a0a-b446de68a9c3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "120a35e7-c2fd-11de-8ac8-001302895b2b");
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
