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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2EndData_Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21ba6909-eafd-4ef8-9976-b21672b3d76a")
public class UML2EndDataReference {
    @objid ("1854a9fb-705f-412d-ba7c-e32273c63198")
    public static final String STEREOTYPE_NAME = "UML2EndData_Reference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5cad9aac-7b49-41af-9685-045281a3fc91")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndData_Reference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7e299471-dbfa-4045-8852-d587e5f998c3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndData_Reference >> then instantiate a {@link UML2EndDataReference} proxy.
     * 
     * @return a {@link UML2EndDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("295bf561-eb15-4f1b-b1d4-394ba9e5df4f")
    public static UML2EndDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME);
        return UML2EndDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDataReference} proxy or <i>null</i>.
     */
    @objid ("e32f9e99-a845-40f5-a6cf-2052bb48d4f9")
    public static UML2EndDataReference instantiate(Dependency obj) {
        return UML2EndDataReference.canInstantiate(obj) ? new UML2EndDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("82c952b0-f574-4e4b-bfaa-ec0183525d27")
    public static UML2EndDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDataReference.canInstantiate(obj))
        	return new UML2EndDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("321daa21-e032-4831-ab41-180ffa65e3a3")
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
        UML2EndDataReference other = (UML2EndDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a018473a-0e3b-47fb-94e3-7d9fa9893c66")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("92c16374-cd91-41b9-93f7-2a1da8e94bbd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0b6e7262-4053-47e9-8b76-923a7a1ac125")
    protected UML2EndDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fbf92a3f-3305-4158-b8a7-11cae3ac1b3b")
    public static final class MdaTypes {
        @objid ("0b6e1899-d006-4caa-a242-fc24790aa817")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f752561e-3491-4f39-8137-779c38d689b7")
        private static Stereotype MDAASSOCDEP;

        @objid ("c68aa7d2-e57e-421d-88d6-f51a2173cb2c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dc3ae543-a760-486a-b0ed-3a286f8956a8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d167c0f-df53-11de-b2b1-001302895b2b");
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
