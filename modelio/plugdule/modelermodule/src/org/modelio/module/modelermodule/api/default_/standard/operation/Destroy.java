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
package org.modelio.module.modelermodule.api.default_.standard.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << destroy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("699eb4cb-af8a-484f-af24-91a599fada81")
public class Destroy {
    @objid ("9a875a41-fbf9-42b2-9088-8919860eb830")
    public static final String STEREOTYPE_NAME = "destroy";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("bd8830cf-76a0-43bb-b160-6242aa4005be")
    protected final Operation elt;

    /**
     * Tells whether a {@link Destroy proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << destroy >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("795b8dcc-c965-452a-8efc-d1db37a23211")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << destroy >> then instantiate a {@link Destroy} proxy.
     * 
     * @return a {@link Destroy} proxy on the created {@link Operation}.
     */
    @objid ("8fc865b7-c99d-410d-9bbe-0d920b866731")
    public static Destroy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME);
        return Destroy.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Destroy} proxy or <i>null</i>.
     */
    @objid ("9e76b427-33fb-482a-9af3-78f0245d2ad4")
    public static Destroy instantiate(Operation obj) {
        return Destroy.canInstantiate(obj) ? new Destroy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link Destroy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bd2b3e33-701b-4e56-8528-3c0cd5ecdf74")
    public static Destroy safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Destroy.canInstantiate(obj))
        	return new Destroy(obj);
        else
        	throw new IllegalArgumentException("Destroy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("94123dff-78c6-428b-b5cf-aacec3d05e4f")
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
        Destroy other = (Destroy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("088d51e1-5c2d-4d12-9efd-af9d9e95f624")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("66678d15-61f0-4a39-87b0-ceeab10ab66d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("033650bc-fa06-44aa-87b6-e2fadbf4749a")
    protected Destroy(Operation elt) {
        this.elt = elt;
    }

    @objid ("452f4dff-b119-47dc-9dd6-c88f4d500896")
    public static final class MdaTypes {
        @objid ("4525f235-bb1f-4158-9dd9-4b5c34ac6abd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fec70ba7-bfda-4071-bdfd-3e9218a629fc")
        private static Stereotype MDAASSOCDEP;

        @objid ("252a17f3-0342-47a7-8422-be25936814ef")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b2e0f4bf-2644-4aab-9264-8f29e011f68e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0206-0000-000000000000");
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
