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
    @objid ("22ff9319-a7dc-480e-8913-b100a4eb00b1")
    public static final String STEREOTYPE_NAME = "ExternalDocument";

    @objid ("fc7ce47f-d5a9-4e84-a48c-8ddcd63ef8e9")
    public static final String LINKLABEL_TAGTYPE = "LinkLabel";

    @objid ("d0d24a88-05ad-48d7-ae7a-f2eaf5285f6a")
    public static final String ISLINK_TAGTYPE = "isLink";

    /**
     * The underlying {@link Note} represented by this proxy, never null.
     */
    @objid ("63b556e7-6ebd-42f0-ab84-f2d7340efbd9")
    protected final Note elt;

    /**
     * Tells whether a {@link ExternalDocument proxy} can be instantiated from a {@link MObject} checking it is a {@link Note} stereotyped << ExternalDocument >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bb03ba8a-941e-41df-8d1b-ce93f76032f3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Note) && ((Note) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Note} stereotyped << ExternalDocument >> then instantiate a {@link ExternalDocument} proxy.
     * 
     * @return a {@link ExternalDocument} proxy on the created {@link Note}.
     */
    @objid ("942984ed-f39f-47f8-a353-168780b74a75")
    public static ExternalDocument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Note");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME);
        return ExternalDocument.instantiate((Note)e);
    }

    /**
     * Tries to instantiate a {@link ExternalDocument} proxy from a {@link Note} stereotyped << ExternalDocument >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Note
     * @return a {@link ExternalDocument} proxy or <i>null</i>.
     */
    @objid ("4545d246-2c2f-412d-a467-14bc8506b05d")
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
    @objid ("fcdd7327-3952-4f87-b9d4-897e90825dbc")
    public static ExternalDocument safeInstantiate(Note obj) throws IllegalArgumentException {
        if (ExternalDocument.canInstantiate(obj))
        	return new ExternalDocument(obj);
        else
        	throw new IllegalArgumentException("ExternalDocument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c39e3c69-051b-4025-82e9-02e531280603")
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
    @objid ("b8bc8ff7-910e-4496-8573-01d7fb9a2fc2")
    public Note getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7cde3635-4000-41b0-9f29-11fe092dd33a")
    public String getLinkLabel() {
        return this.elt.getTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT);
    }

    @objid ("8cc3681f-795a-425a-8262-831168cfbbc5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7ab88f27-cff3-49d6-9c4a-f5736a81c410")
    public boolean isIsLink() {
        return this.elt.isTagged(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8ed4bf8c-c4db-4017-94c7-b345d438e712")
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
    @objid ("0490e8b7-83dd-4daf-9a8e-3123b0ff0061")
    public void setLinkLabel(String value) {
        this.elt.putTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT, value);
    }

    @objid ("f47a8c60-942f-4918-aa52-b3de88b84ce0")
    protected ExternalDocument(Note elt) {
        this.elt = elt;
    }

    @objid ("8daed5a0-e2f1-4a7f-accc-c1b2e6b7509c")
    public static final class MdaTypes {
        @objid ("6b9cc304-516c-4c11-b93c-fd88ce18a4b9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5f155e5a-327e-4d87-90b6-4129099378b0")
        public static TagType ISLINK_TAGTYPE_ELT;

        @objid ("8eb9d4d5-73db-4a24-ab69-ccdd2ea7e470")
        public static TagType LINKLABEL_TAGTYPE_ELT;

        @objid ("d85634c4-53b8-460c-b745-92829226ccb4")
        private static Stereotype MDAASSOCDEP;

        @objid ("52671dc0-f938-4b48-969f-e9133b19ab35")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d0c6b295-fe20-4f47-834f-cd3dbd632136")
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
