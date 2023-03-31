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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << homonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ee86437c-78c7-4033-9a77-f40e04f46719")
public class Homonym {
    @objid ("8caeccdc-3ea3-403d-af05-93d06759dc50")
    public static final String STEREOTYPE_NAME = "homonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("0402c20a-1fef-4f5b-86d1-baedd719674a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Homonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << homonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f336459d-d88d-4c08-9dc8-dd6cc9d5347e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << homonym >> then instantiate a {@link Homonym} proxy.
     * 
     * @return a {@link Homonym} proxy on the created {@link Dependency}.
     */
    @objid ("b8235531-6f31-4f20-a50c-5dee3047819c")
    public static Homonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME);
        return Homonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Homonym} proxy or <i>null</i>.
     */
    @objid ("8981f061-cb73-4a1a-b325-54b9d41db7e8")
    public static Homonym instantiate(Dependency obj) {
        return Homonym.canInstantiate(obj) ? new Homonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Homonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("27c7aa6a-502e-49e2-8538-de220a0567f3")
    public static Homonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Homonym.canInstantiate(obj))
        	return new Homonym(obj);
        else
        	throw new IllegalArgumentException("Homonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2b24fbc5-8c95-4927-b73e-d62c1e45a416")
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
        Homonym other = (Homonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("d205e01e-bfa0-4fa9-acdf-2118eafb517b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("9fe59940-0f91-4517-ab80-c3071becde92")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e34a1c07-1cc0-4817-8e4a-99aa661fb2c3")
    protected  Homonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8225eb66-4057-4d15-8d1d-414b4382bf6b")
    public static final class MdaTypes {
        @objid ("4867bc3b-19a8-4ae3-b24b-1afe7a639497")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("810ab48d-ae42-4b8e-861d-cd676f17487c")
        private static Stereotype MDAASSOCDEP;

        @objid ("451dec7a-f9e0-444f-a401-9d009aeeab45")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dd640daf-bc12-473b-b830-2fa888315f74")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0238-0000-000000000000");
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
