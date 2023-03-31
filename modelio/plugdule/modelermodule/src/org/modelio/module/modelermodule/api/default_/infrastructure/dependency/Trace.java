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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << trace >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("deb1d430-370d-47a6-83f9-89eae36b4475")
public class Trace {
    @objid ("23d79614-6ef2-4808-8247-f6e5be9b8025")
    public static final String STEREOTYPE_NAME = "trace";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("517f46e0-40be-451a-8013-d93f6b726562")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Trace proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << trace >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0210e92e-66b6-4f9a-9845-0d8bc841ef5c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << trace >> then instantiate a {@link Trace} proxy.
     * 
     * @return a {@link Trace} proxy on the created {@link Dependency}.
     */
    @objid ("d17577cd-cec6-444d-9812-49137517aa4a")
    public static Trace create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME);
        return Trace.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Trace} proxy or <i>null</i>.
     */
    @objid ("cb8fc96c-f00b-4847-bfca-abfa9abd4fb2")
    public static Trace instantiate(Dependency obj) {
        return Trace.canInstantiate(obj) ? new Trace(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Trace} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("11ec7d4c-0908-4c61-bf37-89804c2e40c0")
    public static Trace safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Trace.canInstantiate(obj))
        	return new Trace(obj);
        else
        	throw new IllegalArgumentException("Trace: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("97bdeb19-7b21-48d9-9473-2d70235c80a7")
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
        Trace other = (Trace) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("cfbeaf38-0cbf-4e40-9ef3-a61248649072")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4f587ef7-e692-4607-9c63-18ed6299e092")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("df4891a6-48c8-4ae3-9bba-3467acd497a0")
    protected  Trace(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1335f40a-5185-493c-ac4b-6745cea1855f")
    public static final class MdaTypes {
        @objid ("f06768c9-dded-4903-8032-e31d48a24c0b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9df69f75-a3ab-47d2-9384-7e13b6fb22f7")
        private static Stereotype MDAASSOCDEP;

        @objid ("6119a6f6-74c8-4823-b338-672e0dea1afc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("64d6750f-5201-4c56-ae08-f453580dd448")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01280500-0000-0b37-0000-000000000000");
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
