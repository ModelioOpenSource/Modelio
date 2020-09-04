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
 * Proxy class to handle a {@link Artifact} with << mail >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3a3ea94-499f-4c05-bb6a-3566db79f132")
public class Mail {
    @objid ("53a13384-46d6-4ac1-b1d4-652d212ccdb1")
    public static final String STEREOTYPE_NAME = "mail";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("6c568b46-9974-4655-a6ab-90223b03d670")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Mail proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << mail >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f808c236-c731-45cd-894d-de8c5a00cd51")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << mail >> then instantiate a {@link Mail} proxy.
     * 
     * @return a {@link Mail} proxy on the created {@link Artifact}.
     */
    @objid ("4b5cbbad-6d74-4e98-ba54-f4b625c7b3b9")
    public static Mail create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME);
        return Mail.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Mail} proxy or <i>null</i>.
     */
    @objid ("d904cd30-db9f-4725-8188-d2de3ee7d0e3")
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
    @objid ("ae4ac1ad-7b6f-4093-a166-87336db70b1c")
    public static Mail safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Mail.canInstantiate(obj))
        	return new Mail(obj);
        else
        	throw new IllegalArgumentException("Mail: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d71c6adb-ae91-4171-bbe5-e9a99cab02a7")
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
    @objid ("0bbb78c8-6661-414f-8764-5a66530c41e0")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("7157047d-fe0c-4765-9140-4ce1f1d3ab8b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("93a07990-bb7b-4f5b-b696-06d76204d407")
    protected Mail(Artifact elt) {
        this.elt = elt;
    }

    @objid ("d81ebae2-b027-4736-b68a-a9c31925809d")
    public static final class MdaTypes {
        @objid ("0e8709f8-a03b-4785-add1-0cae93dca936")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b5a9b72d-9958-4629-ace7-23c6917a6cd6")
        private static Stereotype MDAASSOCDEP;

        @objid ("b3d54d5a-604f-4680-930e-c8a8793f2c4a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("27437be6-3e53-4ae1-83bd-377c005396ad")
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
