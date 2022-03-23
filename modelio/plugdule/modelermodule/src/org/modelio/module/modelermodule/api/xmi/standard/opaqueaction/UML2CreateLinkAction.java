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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33dada2d-31ec-4a9d-9046-df7e11c23765")
public class UML2CreateLinkAction {
    @objid ("9d911510-0926-4b96-a5a9-3b4b29a478af")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("d5e99f8d-0899-4dce-908b-d8fd217f9cf9")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("931dd8b4-2670-400e-a595-cf44488bda88")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> then instantiate a {@link UML2CreateLinkAction} proxy.
     * 
     * @return a {@link UML2CreateLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("da26aa7b-ddfd-4909-9ab4-e3b4ee7e31cc")
    public static UML2CreateLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME);
        return UML2CreateLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkAction} proxy or <i>null</i>.
     */
    @objid ("426b097e-fd9b-412e-8dfb-d822decea678")
    public static UML2CreateLinkAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkAction.canInstantiate(obj) ? new UML2CreateLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a6b7c443-c335-460e-a6c0-778499aaf703")
    public static UML2CreateLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkAction.canInstantiate(obj))
        	return new UML2CreateLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1e66f979-63d2-4ee2-aa89-17ab7a608600")
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
        UML2CreateLinkAction other = (UML2CreateLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("155200c9-37eb-49ff-978c-ba5127baec53")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("83279a85-869e-4d0b-a26f-cfcd99853a4f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3599ee1a-2f69-4818-bd10-36d5aeb4027c")
    protected  UML2CreateLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("ff41ce0e-1a7a-41e4-b25f-3b3c0f13b830")
    public static final class MdaTypes {
        @objid ("09993bd8-3ef8-4993-afa3-3112a8781038")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c228df51-9f0c-44b7-a21f-c9668c355822")
        private static Stereotype MDAASSOCDEP;

        @objid ("b1482bd8-6d6a-4fe3-93f1-f6246a281a16")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1f8bd229-e41e-4dc1-8021-29a535a37fdd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "67694a37-c2f9-11de-8ac8-001302895b2b");
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
