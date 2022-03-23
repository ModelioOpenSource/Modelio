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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8be3f15-859f-4f75-bcf6-706f60bc54c9")
public class UML2RemoveVariableValueAction {
    @objid ("35ffe85a-c266-40ef-b3cb-02bf8509be16")
    public static final String STEREOTYPE_NAME = "UML2RemoveVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("0013c7f5-c8ba-4e8a-8352-42f4cc93b4f5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("78edae6d-fc63-4f6c-964b-2f526d0dccc5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> then instantiate a {@link UML2RemoveVariableValueAction} proxy.
     * 
     * @return a {@link UML2RemoveVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0ab0ead5-3f33-45b0-9212-5bc5aec2275b")
    public static UML2RemoveVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME);
        return UML2RemoveVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("5dbcd1ff-eb14-4351-b733-44cc07b92243")
    public static UML2RemoveVariableValueAction instantiate(OpaqueAction obj) {
        return UML2RemoveVariableValueAction.canInstantiate(obj) ? new UML2RemoveVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1507421a-7818-43e6-b30c-90587fb44420")
    public static UML2RemoveVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveVariableValueAction.canInstantiate(obj))
        	return new UML2RemoveVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cb53dbdd-2220-47d1-8a06-bd9c0feaac20")
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
        UML2RemoveVariableValueAction other = (UML2RemoveVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("92ed0a55-21ed-4c41-8b87-5a87395ee4a5")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("d3bced96-949c-458f-84fe-bad736895622")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("05cc9174-26e6-4ab3-a72a-774230619494")
    protected  UML2RemoveVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("4788dd43-ab73-4535-a12f-facdd8ad949a")
    public static final class MdaTypes {
        @objid ("18ed7244-509f-4c29-8c82-1ce3a7c81e12")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("34182ade-1e17-41d7-a3e4-17955eff88e7")
        private static Stereotype MDAASSOCDEP;

        @objid ("f70ac471-8ed6-43b9-a4b8-a3fdf6de3d58")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c0b19c4b-6b9c-4950-a918-73abe76cbc95")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "435869cb-c2fd-11de-8ac8-001302895b2b");
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
