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
 * Proxy class to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f6b5b83-851b-4d23-b980-2de82874fa5b")
public class Assigned {
    @objid ("afe1af1a-6253-47f8-8dec-6e64eb62f186")
    public static final String STEREOTYPE_NAME = "assigned";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6b1e62d6-0b47-482f-a0b4-953a57a82032")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Assigned proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << assigned >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0e476146-2463-4f76-b733-cab70638c033")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << assigned >> then instantiate a {@link Assigned} proxy.
     * 
     * @return a {@link Assigned} proxy on the created {@link Dependency}.
     */
    @objid ("63645bc5-78f6-4424-b322-4a48ef710185")
    public static Assigned create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME);
        return Assigned.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Assigned} proxy or <i>null</i>.
     */
    @objid ("03d576c3-071e-4e06-b0be-f748ff27338d")
    public static Assigned instantiate(Dependency obj) {
        return Assigned.canInstantiate(obj) ? new Assigned(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Assigned} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ad17dd2f-3ed8-4588-a4c6-b8e3d646d7c8")
    public static Assigned safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Assigned.canInstantiate(obj))
        	return new Assigned(obj);
        else
        	throw new IllegalArgumentException("Assigned: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("54de3baa-6ef1-4f6c-b749-b8eec30a8057")
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
        Assigned other = (Assigned) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("fc7e6792-a6e9-4aa0-8c4d-b78e0b982268")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("63177ebe-d2b6-41b6-8f90-39ecf803700d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6aa90364-f78c-4a9c-899c-bd915a3d9f58")
    protected Assigned(Dependency elt) {
        this.elt = elt;
    }

    @objid ("52e42aa6-442a-4a00-a707-08c1820fc9cd")
    public static final class MdaTypes {
        @objid ("39179605-e5bd-4b73-b24a-a86fe2e5697e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f5e36499-9bc8-4224-b2cb-e1aa1411bc82")
        private static Stereotype MDAASSOCDEP;

        @objid ("aedb6fbf-83f6-4985-91cd-3348fd93a31b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("062affd8-e06b-4e33-9afd-42acb994aa02")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-025b-0000-000000000000");
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
