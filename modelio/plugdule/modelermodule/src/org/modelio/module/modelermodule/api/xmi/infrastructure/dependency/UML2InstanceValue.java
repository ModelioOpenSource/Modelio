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
 * Proxy class to handle a {@link Dependency} with << UML2InstanceValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57682dea-36fc-489f-a20b-7834ce24109f")
public class UML2InstanceValue {
    @objid ("6bc58bf7-6013-4d1f-b8c7-c321d30a4d26")
    public static final String STEREOTYPE_NAME = "UML2InstanceValue";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ace9867e-73d1-4ce1-9ca5-4ec1c730d52b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2InstanceValue proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2InstanceValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("281e130b-26af-4cd1-b26e-e8c94d561e0c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2InstanceValue >> then instantiate a {@link UML2InstanceValue} proxy.
     * 
     * @return a {@link UML2InstanceValue} proxy on the created {@link Dependency}.
     */
    @objid ("c9b9a5e5-75e7-4849-8cb0-62d6fbe8c56c")
    public static UML2InstanceValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME);
        return UML2InstanceValue.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2InstanceValue} proxy or <i>null</i>.
     */
    @objid ("c1c930c9-72c5-4f84-ac51-e5463b9a7807")
    public static UML2InstanceValue instantiate(Dependency obj) {
        return UML2InstanceValue.canInstantiate(obj) ? new UML2InstanceValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2InstanceValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8e967e8a-d7c4-43b0-a88c-cde938bb59fd")
    public static UML2InstanceValue safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2InstanceValue.canInstantiate(obj))
        	return new UML2InstanceValue(obj);
        else
        	throw new IllegalArgumentException("UML2InstanceValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("15b349ec-78af-4fdb-8336-e8bec3ad8f46")
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
        UML2InstanceValue other = (UML2InstanceValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6c3cae1b-4d7b-4a32-8935-34ce1080188f")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b9bf96c2-ca50-4f17-b762-1d4418433513")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("fd8ce67e-2378-4d3f-9b5e-1a552273d890")
    protected  UML2InstanceValue(Dependency elt) {
        this.elt = elt;
    }

    @objid ("212a0d8e-5958-407c-8c93-a9014079a3cb")
    public static final class MdaTypes {
        @objid ("380fb4ba-3978-42ac-abfd-4189321ce0e4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("064d2d07-6217-42b0-a058-a05560f0b206")
        private static Stereotype MDAASSOCDEP;

        @objid ("c8c6e215-a57b-4367-97b0-98b4f93f35ca")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a7dd4b77-b618-401e-9747-4f04f6033953")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5791cd76-03ec-11e2-9c63-0027103f347d");
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
