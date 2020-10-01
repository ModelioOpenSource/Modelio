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
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << directory >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d378822b-d3d3-4f51-9c86-6bd5d820f76d")
public class Directory {
    @objid ("4b0cd5ee-889a-4068-91bf-258cb42323e2")
    public static final String STEREOTYPE_NAME = "directory";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("8757e9f2-4d36-4b0a-9083-e87f5b249f30")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Directory proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << directory >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("cf0af56f-685f-4c53-a807-493253c56b32")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << directory >> then instantiate a {@link Directory} proxy.
     * 
     * @return a {@link Directory} proxy on the created {@link Artifact}.
     */
    @objid ("0c55aaa7-1572-4a20-ba56-a0fbbff95e5d")
    public static Directory create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME);
        return Directory.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Directory} proxy or <i>null</i>.
     */
    @objid ("e4803663-e2bc-48eb-b7cd-58928e54bb1e")
    public static Directory instantiate(Artifact obj) {
        return Directory.canInstantiate(obj) ? new Directory(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Directory} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("72300205-81b7-403a-b85d-dc0a572c750a")
    public static Directory safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Directory.canInstantiate(obj))
        	return new Directory(obj);
        else
        	throw new IllegalArgumentException("Directory: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c8e1a879-86c2-44a9-b8bd-817a19f89154")
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
        Directory other = (Directory) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("cdd34b03-c9be-4538-880c-881b4dc2cc4c")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("29e1a1b5-8beb-4683-b2b8-d8cbec4099a3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ad14a70b-7f75-47fa-88ae-0028c1e7915e")
    protected Directory(Artifact elt) {
        this.elt = elt;
    }

    @objid ("c8a5a0d9-e215-4019-9561-d0323c58164f")
    public static final class MdaTypes {
        @objid ("9fcb80e3-2b91-4a3b-aa86-337a5030f520")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f9156091-3074-444d-96ee-984ba2b5dbbf")
        private static Stereotype MDAASSOCDEP;

        @objid ("0836edda-0cd7-41c6-8425-d8092d51f6a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bab65ef8-dbfd-409a-b9cc-7a4705a4707b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6432b987-256f-4121-9428-a89d364c2cef");
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
