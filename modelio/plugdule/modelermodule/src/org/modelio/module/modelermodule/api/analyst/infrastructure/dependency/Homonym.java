/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link Dependency} with << homonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ee86437c-78c7-4033-9a77-f40e04f46719")
public class Homonym {
    @objid ("57b9dcb9-6a73-4956-9d14-929081301a25")
    public static final String STEREOTYPE_NAME = "homonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f605fb78-a630-40e3-9d2b-3d6f4b92f2ca")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Homonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << homonym >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7e954f1c-cb6a-4591-8759-5d02491e0b4c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << homonym >> then instantiate a {@link Homonym} proxy.
     * 
     * @return a {@link Homonym} proxy on the created {@link Dependency}.
     */
    @objid ("fbb27ae3-8768-4bce-aa83-c60c6ae0acf9")
    public static Homonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME);
        return Homonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Homonym} proxy or <i>null</i>.
     */
    @objid ("75cec6e2-bfd7-40bb-8be3-9533bbba19d3")
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
    @objid ("419331e4-5ec9-4561-a69f-3bff19ed0c58")
    public static Homonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Homonym.canInstantiate(obj))
        	return new Homonym(obj);
        else
        	throw new IllegalArgumentException("Homonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("97cc0677-e1ae-41b3-be7a-62907eedbdae")
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
    @objid ("5c2f84f6-c51a-4e23-96b1-a29ac80890b0")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("123ce064-26ac-4a06-8507-bbe003f26525")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("646c1226-b056-411c-bf3a-eaba83e51f0f")
    protected Homonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8225eb66-4057-4d15-8d1d-414b4382bf6b")
    public static final class MdaTypes {
        @objid ("77167d0d-d3f6-470c-9caa-4ba94bfe3f21")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("061c4add-c74f-4245-827c-cd453edf9c29")
        private static Stereotype MDAASSOCDEP;

        @objid ("4e281e31-d0dc-4bb4-9c4c-ef278236b2b4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb24ae70-a9b5-4ee8-82b3-b11491004b52")
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
