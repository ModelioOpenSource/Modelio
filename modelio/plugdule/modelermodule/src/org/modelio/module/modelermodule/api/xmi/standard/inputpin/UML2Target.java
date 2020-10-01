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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Target >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c9ef7950-aef3-455f-bd22-02a260ca445a")
public class UML2Target {
    @objid ("8bbd3e8f-6829-49fe-bc7a-ada127c2fccb")
    public static final String STEREOTYPE_NAME = "UML2Target";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("77e52539-2415-4239-85bf-e16d5fffa6d2")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Target proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Target >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("61ab3e7d-05c8-48e8-a9b6-c0a3c1b463fc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Target >> then instantiate a {@link UML2Target} proxy.
     * 
     * @return a {@link UML2Target} proxy on the created {@link InputPin}.
     */
    @objid ("2325dcb1-e8b1-4ecb-90f7-53d3629f8d98")
    public static UML2Target create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME);
        return UML2Target.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Target} proxy or <i>null</i>.
     */
    @objid ("faca1890-7ec7-40ed-9363-2904300ff7fe")
    public static UML2Target instantiate(InputPin obj) {
        return UML2Target.canInstantiate(obj) ? new UML2Target(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Target} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9eea36bf-c752-42be-944a-5ad90a5e3feb")
    public static UML2Target safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Target.canInstantiate(obj))
        	return new UML2Target(obj);
        else
        	throw new IllegalArgumentException("UML2Target: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e19c964f-9c88-41f6-a19a-c8e866296c6b")
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
        UML2Target other = (UML2Target) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("2d46da4e-1429-4c97-a196-77dcca45d890")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a783f178-a6c8-4186-98d7-c394d56c3f43")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dc3cc50f-8651-4789-a0e2-b2a6a7b27af9")
    protected UML2Target(InputPin elt) {
        this.elt = elt;
    }

    @objid ("f7e8a899-0f5c-4177-848d-e2e7b037113a")
    public static final class MdaTypes {
        @objid ("fd3e61f7-b15c-47e5-babd-b9a409b1a73e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("823e4700-16db-40f3-bd29-68c364cc6241")
        private static Stereotype MDAASSOCDEP;

        @objid ("852d5657-4324-4ae6-9551-4ece19a01cbd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("26c5f1ca-f202-4a0b-b207-7342362247c5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "aa99ee06-c495-11de-ada1-001302895b2b");
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
