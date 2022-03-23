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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartClassifierBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac4197cc-0a67-4a64-8eb4-caa91ba5815e")
public class UML2StartClassifierBehaviorAction {
    @objid ("8d9aa5c3-f363-4d0d-ab93-41c856a021d7")
    public static final String STEREOTYPE_NAME = "UML2StartClassifierBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("124e5091-f081-4dc0-80f5-be8a64847042")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartClassifierBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("acbfe080-1576-46b3-8489-76bbcaa7debb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> then instantiate a {@link UML2StartClassifierBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartClassifierBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("ba4f84e2-9595-4b16-8392-d6fdaf464358")
    public static UML2StartClassifierBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME);
        return UML2StartClassifierBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartClassifierBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("cc7f7857-ff81-4028-954c-9ff0fc06c72c")
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
    @objid ("ccb4f1c9-29b8-4111-a30e-f53330ae2ea0")
    public static UML2StartClassifierBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartClassifierBehaviorAction.canInstantiate(obj))
        	return new UML2StartClassifierBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartClassifierBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5ff7f6e2-b30e-44fb-a145-805a515c396a")
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
    @objid ("94d56a16-433b-4633-96a9-7f6efa66dddc")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e81671ed-a93c-4fad-8c7a-c4790722ba9f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3093b5e6-f4ff-4b7e-a13c-360d0b3de968")
    protected  UML2StartClassifierBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("aecc60a1-6721-410a-8867-25ffb2dfa838")
    public static final class MdaTypes {
        @objid ("e98536f3-3740-4863-8bbd-ac154cc4ec47")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0bb1e408-f906-412f-a702-70fc03cf76e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("827df17a-a94c-4e18-bcdd-11b215ff29c1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("104beceb-fde7-481d-9319-f7de31fe56ab")
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
