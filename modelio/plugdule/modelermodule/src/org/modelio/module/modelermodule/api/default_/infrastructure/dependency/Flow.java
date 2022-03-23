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
 * Proxy class to handle a {@link Dependency} with << flow >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("059d7606-63e7-4b15-b47f-42f41353ce1e")
public class Flow {
    @objid ("efe75ef5-ee95-4787-a77c-1cf0281df9eb")
    public static final String STEREOTYPE_NAME = "flow";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("bd21a248-19cf-4354-91c3-ec7ef9b25a7f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Flow proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << flow >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f1d2a93b-658d-49e8-9100-671d495cf282")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << flow >> then instantiate a {@link Flow} proxy.
     * 
     * @return a {@link Flow} proxy on the created {@link Dependency}.
     */
    @objid ("e12ed6d3-fd30-491b-8a7e-eb021f6e70d2")
    public static Flow create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME);
        return Flow.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Flow} proxy or <i>null</i>.
     */
    @objid ("b6d49deb-e624-4fa0-b88c-754e24caa8b9")
    public static Flow instantiate(Dependency obj) {
        return Flow.canInstantiate(obj) ? new Flow(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Flow} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6ad62129-353f-4a40-b8e2-a5cb8611ac11")
    public static Flow safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Flow.canInstantiate(obj))
        	return new Flow(obj);
        else
        	throw new IllegalArgumentException("Flow: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("64e62e58-4d51-4c79-89e7-9c1ae292db22")
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
        Flow other = (Flow) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("46947202-b69b-4d6f-ad8e-9191038ca891")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8115ed7a-7404-473c-83c1-e9278265b336")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8b72966a-7e43-4dea-9909-739d7dd94d56")
    protected  Flow(Dependency elt) {
        this.elt = elt;
    }

    @objid ("82b9b25a-72f2-40a9-9d30-45b5eb452746")
    public static final class MdaTypes {
        @objid ("8089072d-cf14-4eb6-a19d-99f07c4bbe1e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e61f874-aa8f-43fb-9b93-59a79e4a4cf6")
        private static Stereotype MDAASSOCDEP;

        @objid ("ac571b16-cf57-4e38-ab1e-9eb8007ebe5c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c7bdf140-eaff-46d6-9b14-5a62488a99f5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-0964-0000-000000000000");
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
