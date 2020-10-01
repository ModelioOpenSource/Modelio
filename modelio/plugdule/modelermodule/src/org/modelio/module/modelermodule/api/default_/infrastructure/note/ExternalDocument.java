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
package org.modelio.module.modelermodule.api.default_.infrastructure.note;

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
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Note} with << ExternalDocument >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7b670613-5bc2-4326-b6e3-1f8c5e4fe52e")
public class ExternalDocument {
    @objid ("c7166406-df32-47fd-85cf-cf57a49875ce")
    public static final String STEREOTYPE_NAME = "ExternalDocument";

    @objid ("440ccbb8-f943-4930-aa44-314a9e6ef66a")
    public static final String LINKLABEL_TAGTYPE = "LinkLabel";

    @objid ("f6363587-33e0-49d7-b39e-281c778dd6a4")
    public static final String ISLINK_TAGTYPE = "isLink";

    /**
     * The underlying {@link Note} represented by this proxy, never null.
     */
    @objid ("6292058d-2129-49d1-b42f-ced175ec82c6")
    protected final Note elt;

    /**
     * Tells whether a {@link ExternalDocument proxy} can be instantiated from a {@link MObject} checking it is a {@link Note} stereotyped << ExternalDocument >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5496a72f-f549-4b50-a8c2-3e2262cd9ac5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Note) && ((Note) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Note} stereotyped << ExternalDocument >> then instantiate a {@link ExternalDocument} proxy.
     * 
     * @return a {@link ExternalDocument} proxy on the created {@link Note}.
     */
    @objid ("ba09bf3b-0489-42ac-ba02-6efdedfe2009")
    public static ExternalDocument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Note");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME);
        return ExternalDocument.instantiate((Note)e);
    }

    /**
     * Tries to instantiate a {@link ExternalDocument} proxy from a {@link Note} stereotyped << ExternalDocument >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Note
     * @return a {@link ExternalDocument} proxy or <i>null</i>.
     */
    @objid ("bf9e07a0-1f52-4dd0-8656-08a6f7c59f48")
    public static ExternalDocument instantiate(Note obj) {
        return ExternalDocument.canInstantiate(obj) ? new ExternalDocument(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ExternalDocument} proxy from a {@link Note} stereotyped << ExternalDocument >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Note}
     * @return a {@link ExternalDocument} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("24688547-6f9d-43ad-9135-08b1cde7ade5")
    public static ExternalDocument safeInstantiate(Note obj) throws IllegalArgumentException {
        if (ExternalDocument.canInstantiate(obj))
        	return new ExternalDocument(obj);
        else
        	throw new IllegalArgumentException("ExternalDocument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d6c27838-2394-4c66-916d-f007c8e70e47")
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
        ExternalDocument other = (ExternalDocument) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Note}. 
     * @return the Note represented by this proxy, never null.
     */
    @objid ("0d10c849-3991-44e4-98d4-46e04f5aab0c")
    public Note getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e5eb11e9-b4ae-47a5-b0cb-b5494f427a36")
    public String getLinkLabel() {
        return this.elt.getTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT);
    }

    @objid ("50555489-8607-4fae-b265-15cab1875a62")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("748b6a5d-e29b-4ee5-adcf-bbd91f121c5e")
    public boolean isIsLink() {
        return this.elt.isTagged(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("716a64cf-85e0-4d73-8500-2a9d2972a5f7")
    public void setIsLink(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("90d7dbac-db7e-4a18-96f7-b7cbfdf43215")
    public void setLinkLabel(String value) {
        this.elt.putTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT, value);
    }

    @objid ("2942fbfb-3053-4a8a-9e51-f285dab4ee7e")
    protected ExternalDocument(Note elt) {
        this.elt = elt;
    }

    @objid ("8daed5a0-e2f1-4a7f-accc-c1b2e6b7509c")
    public static final class MdaTypes {
        @objid ("3a5d6dc1-4ecc-4705-87a9-2463b875a8fa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9d86d7bc-6d06-479f-a979-9541c2020c6e")
        public static TagType ISLINK_TAGTYPE_ELT;

        @objid ("900bb8c4-4d8e-408f-a2f7-bdb5f6da15e1")
        public static TagType LINKLABEL_TAGTYPE_ELT;

        @objid ("b3cf6817-5333-4cc7-9338-5e50ab423f3a")
        private static Stereotype MDAASSOCDEP;

        @objid ("8c564b48-5f8d-4e38-9606-624c776d3429")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a120dd4c-e523-4d50-bc1c-83c3be7d8d66")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c2e23680-96f0-11de-a322-001fe2c988b8");
            ISLINK_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "0da68860-96f1-11de-a322-001fe2c988b8");
            LINKLABEL_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "4027c488-2b5c-11df-9426-00137279a5d1");
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
