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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("b21df0ab-4a5a-41bb-8a00-f0e6c985e18f")
    public static final String STEREOTYPE_NAME = "ExternalDocument";

    @objid ("bbf5b8b3-e14b-40c6-803a-824509c9c1db")
    public static final String LINKLABEL_TAGTYPE = "LinkLabel";

    @objid ("b8a64f71-c3a7-4b96-85d3-090602f4f00b")
    public static final String ISLINK_TAGTYPE = "isLink";

    /**
     * The underlying {@link Note} represented by this proxy, never null.
     */
    @objid ("6a616c4a-0f70-43a0-8f9c-67cf8914e6e7")
    protected final Note elt;

    /**
     * Tells whether a {@link ExternalDocument proxy} can be instantiated from a {@link MObject} checking it is a {@link Note} stereotyped << ExternalDocument >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("988201f1-4b76-4917-8226-196e42ecfa54")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Note) && ((Note) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Note} stereotyped << ExternalDocument >> then instantiate a {@link ExternalDocument} proxy.
     * 
     * @return a {@link ExternalDocument} proxy on the created {@link Note}.
     */
    @objid ("fe789440-ce84-48b3-a36a-3e9714287ca9")
    public static ExternalDocument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Note");
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
    @objid ("958bbd28-c55b-4466-b5c8-45fa7b7e969f")
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
    @objid ("fabc773b-3f93-4a36-97d7-820468798dbc")
    public static ExternalDocument safeInstantiate(Note obj) throws IllegalArgumentException {
        if (ExternalDocument.canInstantiate(obj))
        	return new ExternalDocument(obj);
        else
        	throw new IllegalArgumentException("ExternalDocument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9fb1077c-302e-4dd7-b581-bf1e2db9781f")
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
    @objid ("757b4f5b-0f0d-4e1b-9b48-a35564d81d31")
    public Note getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c1a7e94e-3783-43a6-bedf-a4eda25c1349")
    public String getLinkLabel() {
        return this.elt.getTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT);
    }

    @objid ("d2e7a71a-9e19-4dfc-8c67-0b0631ccbf9f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("61b96be8-da89-4c99-8d22-9f7eacd585bd")
    public boolean isIsLink() {
        return this.elt.isTagged(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9b084d61-5eeb-4e40-9f72-62bebc8a311d")
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
    @objid ("dd9e2663-1a62-4627-a3c9-ba9fd5d7083c")
    public void setLinkLabel(String value) {
        this.elt.putTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT, value);
    }

    @objid ("26fb636b-e7f3-4f9b-9b22-cbb151109a2c")
    protected  ExternalDocument(Note elt) {
        this.elt = elt;
    }

    @objid ("8daed5a0-e2f1-4a7f-accc-c1b2e6b7509c")
    public static final class MdaTypes {
        @objid ("002f40df-4269-4d8d-b7dd-5499b789af7d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("67da92f6-755a-4b97-badb-918ed5d2a2fb")
        public static TagType ISLINK_TAGTYPE_ELT;

        @objid ("f4bcbff9-c43d-4952-b021-48a02457649b")
        public static TagType LINKLABEL_TAGTYPE_ELT;

        @objid ("8a285e9f-27f8-4b13-ba39-7e7312451420")
        private static Stereotype MDAASSOCDEP;

        @objid ("96d659ac-740e-4feb-99af-5772727f790b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("31540842-e8e6-4a31-acec-c38cae6da6e0")
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
