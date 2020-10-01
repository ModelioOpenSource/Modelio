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
 * Proxy class to handle a {@link Artifact} with << url >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("74934370-1154-4850-a9ee-5d3c634680ac")
public class Url {
    @objid ("3b8f4d01-9a2f-41b3-a909-c8ec20a2268d")
    public static final String STEREOTYPE_NAME = "url";

    @objid ("e05aa7ba-11f5-4cd9-ae5f-7b3a9338e2a4")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("f7e0f726-7788-434d-830a-850bb86b9b34")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("ac61882f-0475-4a81-ba70-4b54c4ee11cd")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Url proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << url >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d274f983-ebd8-460c-8653-9e818eb0cca9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << url >> then instantiate a {@link Url} proxy.
     * 
     * @return a {@link Url} proxy on the created {@link Artifact}.
     */
    @objid ("8bfca282-cf34-47a4-834d-55833edd63a3")
    public static Url create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME);
        return Url.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Url} proxy from a {@link Artifact} stereotyped << url >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Url} proxy or <i>null</i>.
     */
    @objid ("f233c9c6-6c38-4a10-88c9-c2bc88a0c388")
    public static Url instantiate(Artifact obj) {
        return Url.canInstantiate(obj) ? new Url(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Url} proxy from a {@link Artifact} stereotyped << url >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Url} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c06f8e1f-f467-4e14-a174-f6d54f25d59c")
    public static Url safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Url.canInstantiate(obj))
        	return new Url(obj);
        else
        	throw new IllegalArgumentException("Url: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f85d08f8-c46a-4144-885d-a8d49c9cfff6")
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
        Url other = (Url) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("300a2a23-5d1d-4424-a4f7-5269d2a11577")
    public String getAuthor() {
        return this.elt.getTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("01d9c263-a54a-4964-bfae-e981ac419e85")
    public String getDate() {
        return this.elt.getTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("3c107958-90ca-430f-a7fa-d3ed97692217")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("e2594fec-27b6-4c5e-b390-f92d5599d6b7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f0339961-cb2d-430c-a395-d2c32374f9b5")
    public void setAuthor(String value) {
        this.elt.putTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("12058340-f400-406f-b7f1-8eaf4c7652c6")
    public void setDate(String value) {
        this.elt.putTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("d073ceb4-2ff0-414d-a119-d82987ebb0ec")
    protected Url(Artifact elt) {
        this.elt = elt;
    }

    @objid ("1612b182-f258-483a-a67a-a48cfaece602")
    public static final class MdaTypes {
        @objid ("8d77f33d-190a-44c0-9205-381523ba581a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8e415a4c-710e-44c7-8630-a85d9ba3d502")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("b66954ca-6cf3-4a37-9700-4cd8b3383189")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("5191cc94-450b-40bb-b88a-9508399b3416")
        private static Stereotype MDAASSOCDEP;

        @objid ("41040571-e262-4267-8e74-03133120cf8c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("619c4d31-790f-43b9-87e7-acb7be07fe7c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c7ba7024-eff5-4039-a4d4-c9ddcd0a3aed");
            AUTHOR_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7269505c-f5f2-4330-926b-19049c8f3c92");
            DATE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "2a483a85-be6f-4684-8ece-b6cec8ba7620");
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
