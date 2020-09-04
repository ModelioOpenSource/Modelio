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
    @objid ("922a4d69-d85a-46ee-9139-e351f9c78055")
    public static final String STEREOTYPE_NAME = "directory";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("f465c05d-b50d-490b-9dba-b5dc37df7544")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Directory proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << directory >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3c82edc2-b30a-4f49-a99b-a60c0fc7f865")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << directory >> then instantiate a {@link Directory} proxy.
     * 
     * @return a {@link Directory} proxy on the created {@link Artifact}.
     */
    @objid ("6f67c403-fd8d-4004-b421-6904bcf4ab1b")
    public static Directory create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME);
        return Directory.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Directory} proxy or <i>null</i>.
     */
    @objid ("7731b203-365e-433b-a9e2-471659d09fb5")
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
    @objid ("19d81c2f-c201-478b-8773-c268c35a70db")
    public static Directory safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Directory.canInstantiate(obj))
        	return new Directory(obj);
        else
        	throw new IllegalArgumentException("Directory: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6e1e3a85-be5a-43ea-bb60-6367742a9f4b")
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
    @objid ("12e5f2ac-3e7d-4cfa-8d70-e1e4e9a18cbd")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("19064b90-e6f4-4fba-a0b2-39871bc4fc97")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("78eaba0a-02e5-4b5a-967c-d99632ab9d98")
    protected Directory(Artifact elt) {
        this.elt = elt;
    }

    @objid ("c8a5a0d9-e215-4019-9561-d0323c58164f")
    public static final class MdaTypes {
        @objid ("e0349714-3c16-4825-9da5-52acd4259800")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a651181e-96ff-4bc0-942d-37859e189b84")
        private static Stereotype MDAASSOCDEP;

        @objid ("95149811-6c8a-4a9c-bf57-407c6bdebf10")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dc18026c-5cad-4ba0-98ff-fd755b7b1ff4")
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
