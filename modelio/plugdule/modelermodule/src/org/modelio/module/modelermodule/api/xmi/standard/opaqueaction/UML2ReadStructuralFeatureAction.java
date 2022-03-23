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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2b0053d4-f916-41fb-ae12-2a439d6dce56")
public class UML2ReadStructuralFeatureAction {
    @objid ("3730fac1-a312-4548-8728-7f68828f7088")
    public static final String STEREOTYPE_NAME = "UML2ReadStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("1f007ffe-128b-4d83-9e41-bfb7a744233b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dcf98b1a-7db3-43d7-8282-27558315c2d8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> then instantiate a {@link UML2ReadStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ReadStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("d6d74cc2-3deb-426b-8468-9dc866291c44")
    public static UML2ReadStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ReadStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("3e2f94cb-35b0-46e7-9829-dce3344728d5")
    public static UML2ReadStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2ReadStructuralFeatureAction.canInstantiate(obj) ? new UML2ReadStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9e0d1a64-7ce2-4d06-9d6c-1a7d439b344f")
    public static UML2ReadStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ReadStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d22b7aa9-2e31-4a01-a694-821202443167")
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
        UML2ReadStructuralFeatureAction other = (UML2ReadStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("ac10b23d-818d-417d-8efa-c709d8daf6d5")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("9a270860-f826-4223-bc31-edb24ccc71ba")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e7210dda-3047-47a6-9757-371a452c8500")
    protected  UML2ReadStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e8682448-1fad-44d6-88fd-6b31e1005623")
    public static final class MdaTypes {
        @objid ("ec630a12-0acf-4330-9446-06a08080e8a9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("99189964-a625-4ea1-96c6-bcb0af90cbfb")
        private static Stereotype MDAASSOCDEP;

        @objid ("740a77c9-04cb-4538-a722-b6c17664a41d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6834dd67-476e-45b7-a2ad-44493ae3e9fc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b9654705-c2f9-11de-8ac8-001302895b2b");
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
