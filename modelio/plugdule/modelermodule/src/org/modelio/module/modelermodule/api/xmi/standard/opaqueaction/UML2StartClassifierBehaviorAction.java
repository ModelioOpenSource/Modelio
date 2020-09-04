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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartClassifierBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac4197cc-0a67-4a64-8eb4-caa91ba5815e")
public class UML2StartClassifierBehaviorAction {
    @objid ("5ccba804-9eb0-48e1-818a-1cfa13cc6a8b")
    public static final String STEREOTYPE_NAME = "UML2StartClassifierBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("bb79b1e3-0e3f-44ba-baaf-6da4f2b3a756")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartClassifierBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("244878b2-3d12-4da6-935b-1b9c2851329c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> then instantiate a {@link UML2StartClassifierBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartClassifierBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("9c43ee1e-dd9a-4112-9764-c9770a3f2013")
    public static UML2StartClassifierBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME);
        return UML2StartClassifierBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartClassifierBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("c4a63991-df5b-4479-aabe-0ea1c96efdd6")
    public static UML2StartClassifierBehaviorAction instantiate(OpaqueAction obj) {
        return UML2StartClassifierBehaviorAction.canInstantiate(obj) ? new UML2StartClassifierBehaviorAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2StartClassifierBehaviorAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("810ff780-85c8-4f06-8994-75f2761487b1")
    public static UML2StartClassifierBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartClassifierBehaviorAction.canInstantiate(obj))
        	return new UML2StartClassifierBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartClassifierBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ec5be8db-d5ab-4eb1-bb1e-ec8caa7be1d5")
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
        UML2StartClassifierBehaviorAction other = (UML2StartClassifierBehaviorAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0457b3ad-2155-41e0-9db1-d98a4ebde1f4")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("3223f26b-5985-4dd0-a5f5-d8abffd0ba80")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2aaa6d17-69e7-4657-b5c7-1705d4cf649e")
    protected UML2StartClassifierBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("aecc60a1-6721-410a-8867-25ffb2dfa838")
    public static final class MdaTypes {
        @objid ("a2f0bc0a-ab3d-4792-8238-02ce606ce429")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4ff07707-afb0-4d20-b0d9-321cc58a1633")
        private static Stereotype MDAASSOCDEP;

        @objid ("48915e3b-ac99-4140-9d8c-120b03bda286")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("05e4a2fe-c29f-4096-bde6-bb90d107f762")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d357779-c2fd-11de-8ac8-001302895b2b");
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
