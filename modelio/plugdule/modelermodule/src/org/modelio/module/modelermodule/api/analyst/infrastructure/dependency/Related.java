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
 * Proxy class to handle a {@link Dependency} with << related >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5ac2e06b-dda4-459c-acdb-e71b88f113b8")
public class Related {
    @objid ("52e4d726-b87e-49b5-a6bf-b3697c03f324")
    public static final String STEREOTYPE_NAME = "related";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6d4c5cb5-6765-49e3-8718-6f2de04e3470")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Related proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9f25fd35-ecf8-4a0d-b79d-a79e0172976c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related >> then instantiate a {@link Related} proxy.
     * 
     * @return a {@link Related} proxy on the created {@link Dependency}.
     */
    @objid ("acf9db3a-9a40-49b5-b4cf-b9e2f6c9eafa")
    public static Related create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME);
        return Related.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Related} proxy or <i>null</i>.
     */
    @objid ("8545271c-ef43-4b8d-ade2-84955e362053")
    public static Related instantiate(Dependency obj) {
        return Related.canInstantiate(obj) ? new Related(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Related} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("854e5079-7857-42f7-8389-1f3fc35aaf88")
    public static Related safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Related.canInstantiate(obj))
        	return new Related(obj);
        else
        	throw new IllegalArgumentException("Related: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b25aaf50-22d2-4b1e-8877-ea3d9443b7db")
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
        Related other = (Related) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6d3c92d3-66d7-4dd5-9693-3b721db5b11d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3c8e8802-683f-400f-8cf1-6d5f95c86699")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("feea313c-104c-431c-b133-c12cdb907e1c")
    protected Related(Dependency elt) {
        this.elt = elt;
    }

    @objid ("20b74997-f3b4-4b98-8673-17a6d2577e56")
    public static final class MdaTypes {
        @objid ("a7815d7c-522e-46f6-950a-ca6f49e00c4f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("695129b5-dcf5-45f1-9173-a27c34ab1df8")
        private static Stereotype MDAASSOCDEP;

        @objid ("48c375d4-c55e-4fb0-9858-e3642ddd6b1f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5630c3b6-86c6-456b-95e4-eb1dc55c0542")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-023d-0000-000000000000");
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
