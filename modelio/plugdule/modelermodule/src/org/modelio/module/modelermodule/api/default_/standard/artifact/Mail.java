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
 * Proxy class to handle a {@link Artifact} with << mail >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3a3ea94-499f-4c05-bb6a-3566db79f132")
public class Mail {
    @objid ("3cde969b-a227-472b-8837-38e03bd1cf44")
    public static final String STEREOTYPE_NAME = "mail";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("77472bf2-a786-4c6b-8a0b-01b68634a01f")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Mail proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << mail >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3d105c2c-0524-4c66-966a-f793fda075dc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << mail >> then instantiate a {@link Mail} proxy.
     * 
     * @return a {@link Mail} proxy on the created {@link Artifact}.
     */
    @objid ("293cb9b9-d3ac-4df3-87e9-d825c704bb65")
    public static Mail create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME);
        return Mail.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Mail} proxy or <i>null</i>.
     */
    @objid ("b467ede0-87c1-4252-92e9-780eecbe2709")
    public static Mail instantiate(Artifact obj) {
        return Mail.canInstantiate(obj) ? new Mail(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Mail} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("819950d4-a42b-4a8d-9d44-eda62b69a3a8")
    public static Mail safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Mail.canInstantiate(obj))
        	return new Mail(obj);
        else
        	throw new IllegalArgumentException("Mail: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3111d780-35b2-4468-a63c-4dbdd2d17ac4")
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
        Mail other = (Mail) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("8eb720a5-4093-4ea5-bc2f-ebd9785f216e")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("c2491156-9a13-4b53-a6b0-dbbd562b5a07")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("433ec77e-1434-472d-9762-ba4711744bd0")
    protected Mail(Artifact elt) {
        this.elt = elt;
    }

    @objid ("d81ebae2-b027-4736-b68a-a9c31925809d")
    public static final class MdaTypes {
        @objid ("92609a14-0ce4-440e-8755-b21c3510ad69")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("53f662a8-b71f-43c6-8750-5351b3a0bede")
        private static Stereotype MDAASSOCDEP;

        @objid ("3a70e5b7-7b13-4e2f-8d42-d10cbad77e90")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("70ecadeb-d67a-4092-b805-cb5b28944f45")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "74d7cf69-58eb-48e4-b71a-e5bb0f7570f7");
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
