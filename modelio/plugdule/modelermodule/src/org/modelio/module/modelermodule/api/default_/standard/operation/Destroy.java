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
    @objid ("3069d99b-e3bd-43b3-b9e8-c2b50fd5567b")
    public static final String STEREOTYPE_NAME = "destroy";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("88b076c4-17d8-4158-b9dc-98ad19aeac44")
    protected final Operation elt;

    /**
     * Tells whether a {@link Destroy proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << destroy >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("45fbbad8-64d5-4ce1-acd4-061da5c86e0d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << destroy >> then instantiate a {@link Destroy} proxy.
     * 
     * @return a {@link Destroy} proxy on the created {@link Operation}.
     */
    @objid ("21654b9e-a1e8-4550-bcf1-bbde12d16488")
    public static Destroy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME);
        return Destroy.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Destroy} proxy or <i>null</i>.
     */
    @objid ("5c8f3bc9-3d78-46dd-ae4d-f06a1792f9ec")
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
    @objid ("2346d11a-c743-4357-9359-3575be16879b")
    public static Destroy safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Destroy.canInstantiate(obj))
        	return new Destroy(obj);
        else
        	throw new IllegalArgumentException("Destroy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f1ae67a2-4f10-41d7-b750-6690e66fe05b")
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
    @objid ("ef1f7773-e6ec-4668-9e9f-8eb0e36ae413")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("f6c399f8-7544-41b4-b162-3e6619bc808f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bfcefefb-4698-4d1c-939d-167983b60cea")
    protected Destroy(Operation elt) {
        this.elt = elt;
    }

    @objid ("452f4dff-b119-47dc-9dd6-c88f4d500896")
    public static final class MdaTypes {
        @objid ("d37d05f3-e9e3-4593-88bf-55d6eef374c5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d410cdbe-9194-4701-8f84-9cf8e0356dbe")
        private static Stereotype MDAASSOCDEP;

        @objid ("cd8ea31c-7b8b-4c86-94b8-9b77112aafd7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7cb3d76e-0d15-4e19-a230-62e9b331f41b")
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
