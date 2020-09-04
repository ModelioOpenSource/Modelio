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
package org.modelio.module.modelermodule.api.default_.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << ModelComponentElementAlias >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f02a222-93eb-462f-b374-436e42fd6818")
public class ModelComponentElementAlias {
    @objid ("2ee27a56-b9ff-4559-a593-005a55049bb9")
    public static final String STEREOTYPE_NAME = "ModelComponentElementAlias";

    @objid ("6e855f21-ab18-4b01-8eca-c5cdf23bedc4")
    public static final String UUID_TAGTYPE = "uuid";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("edc1a711-fd52-417d-98de-bed7b1735603")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link ModelComponentElementAlias proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("eb98e133-3220-4cd1-a3e1-5caaf1c99ce9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> then instantiate a {@link ModelComponentElementAlias} proxy.
     * 
     * @return a {@link ModelComponentElementAlias} proxy on the created {@link UmlModelElement}.
     */
    @objid ("13972a2e-9ec9-4f0b-8898-d01805f62314")
    public static ModelComponentElementAlias create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME);
        return ModelComponentElementAlias.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link ModelComponentElementAlias} proxy or <i>null</i>.
     */
    @objid ("79197382-7d13-4e51-9f52-da6889f18360")
    public static ModelComponentElementAlias instantiate(UmlModelElement obj) {
        return ModelComponentElementAlias.canInstantiate(obj) ? new ModelComponentElementAlias(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link ModelComponentElementAlias} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9b605d0c-37ce-42a7-b78e-ecc130fb11d1")
    public static ModelComponentElementAlias safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (ModelComponentElementAlias.canInstantiate(obj))
        	return new ModelComponentElementAlias(obj);
        else
        	throw new IllegalArgumentException("ModelComponentElementAlias: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4cfdc537-383a-4b91-82f8-279d9da5c349")
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
        ModelComponentElementAlias other = (ModelComponentElementAlias) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("54fe79da-57d4-4ed1-9ed0-7c10f0829884")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ede4063b-a308-42cf-a328-f7b376987040")
    public String getUuid() {
        return this.elt.getTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT);
    }

    @objid ("65fef9b9-267e-47f7-b697-c03e83cd7712")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("01f8a94a-b98e-485d-8912-fe48b0f289f6")
    public void setUuid(String value) {
        this.elt.putTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT, value);
    }

    @objid ("0ba3ec18-756b-4a5a-8bf9-d1ec147ddeb3")
    protected ModelComponentElementAlias(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("5d98506b-4aa3-4855-9a1e-78d997c2836c")
    public static final class MdaTypes {
        @objid ("920a9217-edee-479a-a4a0-7f63b2ad0ce5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6270730a-6c06-4d15-8cca-e435212dd434")
        public static TagType UUID_TAGTYPE_ELT;

        @objid ("92cf6116-72f9-4fbd-88c2-0cd5f6977529")
        private static Stereotype MDAASSOCDEP;

        @objid ("cf8288a3-25c4-43d1-a68f-b7eec9560d04")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("690f2305-a20b-41a9-b8a5-6960c0b98ca4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3254be34-c7d8-4018-8a68-5de65c30b773");
            UUID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7ebbd53f-a5f3-433b-872c-4024615af229");
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
