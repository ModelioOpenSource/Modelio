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
 * Proxy class to handle a {@link OpaqueAction} with << UML2SendObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1de174fc-1ed7-4452-9f8b-57656fbeb200")
public class UML2SendObjectAction {
    @objid ("aaae1ea5-0d8f-46b6-8212-de651f3c54e9")
    public static final String STEREOTYPE_NAME = "UML2SendObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("38f5678a-a03c-4ffe-b734-5b31e1974dce")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2SendObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c1dc97f3-c283-4b13-9b87-2a5ea286ed3c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2SendObjectAction >> then instantiate a {@link UML2SendObjectAction} proxy.
     * 
     * @return a {@link UML2SendObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b57b7d30-5354-41f5-a6d6-03cb12a8102b")
    public static UML2SendObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME);
        return UML2SendObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2SendObjectAction} proxy or <i>null</i>.
     */
    @objid ("c28783c1-cd98-43cb-a2e6-29a77140c31f")
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
    @objid ("6342cdb3-fc28-42c7-a495-b0f1b31158a5")
    public static UML2SendObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2SendObjectAction.canInstantiate(obj))
        	return new UML2SendObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2SendObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a13c9470-87f2-4863-9ef8-70da028aaf49")
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
    @objid ("53de12f1-7e8c-454a-9999-d1896d201cb2")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e1f2ae0e-11e9-4fdd-a44d-4a3252389ecc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("049a0444-930e-4abf-9ae8-5cb0d92304c5")
    protected UML2SendObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("66231600-a2af-46ef-9aea-d8405fd2e16e")
    public static final class MdaTypes {
        @objid ("d95c6555-53b3-430e-8495-0327d7903262")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("479745d0-2ebc-4534-95b1-140dfcdbf24e")
        private static Stereotype MDAASSOCDEP;

        @objid ("2b0db69e-5b8d-4450-a4ec-473148c1d2e1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("21fc1316-77ff-4685-a668-5d13818fd070")
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
