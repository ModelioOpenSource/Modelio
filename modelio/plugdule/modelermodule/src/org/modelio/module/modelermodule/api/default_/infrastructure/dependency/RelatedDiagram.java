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
 * Proxy class to handle a {@link Dependency} with << related_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fca5b424-314d-4acd-b390-d416296df62e")
public class RelatedDiagram {
    @objid ("4e95aa93-c8f4-4f91-9fdd-ecb1ed706229")
    public static final String STEREOTYPE_NAME = "related_diagram";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ed156b5f-c407-45f1-86b3-deb078731808")
    protected final Dependency elt;

    /**
     * Tells whether a {@link RelatedDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4958e5a8-f536-49a9-8584-624e56b14c0e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related_diagram >> then instantiate a {@link RelatedDiagram} proxy.
     * 
     * @return a {@link RelatedDiagram} proxy on the created {@link Dependency}.
     */
    @objid ("14646e9c-d31e-47af-82d3-52a5f301e2cd")
    public static RelatedDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME);
        return RelatedDiagram.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link RelatedDiagram} proxy or <i>null</i>.
     */
    @objid ("56e9c84f-06ff-43cd-bec5-65e277e758c9")
    public static RelatedDiagram instantiate(Dependency obj) {
        return RelatedDiagram.canInstantiate(obj) ? new RelatedDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link RelatedDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0aec2956-56d3-4910-89df-42436c6580dd")
    public static RelatedDiagram safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (RelatedDiagram.canInstantiate(obj))
        	return new RelatedDiagram(obj);
        else
        	throw new IllegalArgumentException("RelatedDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e295a38c-6963-4c28-96f4-fa892fa1c6e1")
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
        RelatedDiagram other = (RelatedDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("17d35f80-6d51-4540-b42d-2c108400eb5c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("6eaafb8a-3792-404c-8814-817ae0adad86")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("96b16fe8-ff96-41bc-96f0-8dd1ee8690b9")
    protected RelatedDiagram(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7c15b3f3-14bd-419f-b399-40c4a720d7a2")
    public static final class MdaTypes {
        @objid ("fc2efc50-5106-482c-aeb4-f07ea46badf8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8688e4d4-80ef-4913-ad5c-6b1e18f373bf")
        private static Stereotype MDAASSOCDEP;

        @objid ("4661802c-9199-4611-993c-70330682b132")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("88d1b312-d447-48c6-9244-edb3b0705d0f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2961d57b-5120-11de-bbaf-00218648fa3d");
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
