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
 * Proxy class to handle a {@link Dependency} with << part >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5b672df6-1d49-4ad4-890a-08c3ae1afcb3")
public class Part {
    @objid ("74449789-fa5e-4bdb-9ad3-f065cbb7bd2f")
    public static final String STEREOTYPE_NAME = "part";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("21f49aa2-0684-403a-851c-e58836547b2c")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Part proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << part >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fd195a46-2b4c-4b0b-8639-2b62ef28c389")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << part >> then instantiate a {@link Part} proxy.
     * 
     * @return a {@link Part} proxy on the created {@link Dependency}.
     */
    @objid ("d8441722-23da-40e3-b9e5-dd29dd0def76")
    public static Part create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME);
        return Part.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Part} proxy or <i>null</i>.
     */
    @objid ("755b8348-9c97-49db-a3f0-3ed7da5eacb5")
    public static Part instantiate(Dependency obj) {
        return Part.canInstantiate(obj) ? new Part(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Part} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("12202ed6-ea12-4140-8f65-0881eddd9562")
    public static Part safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Part.canInstantiate(obj))
        	return new Part(obj);
        else
        	throw new IllegalArgumentException("Part: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5352ec8e-86c0-4ee3-9db7-3d8b42b6d810")
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
        Part other = (Part) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c8b1f77e-8d39-4909-ae3d-bb375a405ff2")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("fb0422c7-f211-4677-8d38-1aabbba6c7ee")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5c943692-b062-4dd3-a196-365201fedb7f")
    protected Part(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7ea483f7-ea8b-4bc8-8c23-87eb77766490")
    public static final class MdaTypes {
        @objid ("9a210d40-386a-4787-927a-6a6c58eabc43")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("565ab358-ec73-416a-8f5f-3b2cb6761407")
        private static Stereotype MDAASSOCDEP;

        @objid ("d7fe987d-4139-41d0-9046-e5a47a4ecb07")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4aa9990e-f500-46c2-a8a4-94e5ab17da21")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-00b7-0000-000000000000");
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
