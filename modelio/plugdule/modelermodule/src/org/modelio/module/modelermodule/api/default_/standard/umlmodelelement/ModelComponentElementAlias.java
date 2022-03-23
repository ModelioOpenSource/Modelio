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
    @objid ("824bfe82-0f62-418d-9fa4-b7e88a4722f1")
    public static final String STEREOTYPE_NAME = "ModelComponentElementAlias";

    @objid ("be32248a-c748-469e-8c7c-0dce5d205ebc")
    public static final String UUID_TAGTYPE = "uuid";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("055afa5a-d794-45c6-acc2-fcc4e29ecb92")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link ModelComponentElementAlias proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("930ccdab-6306-4452-b81c-c8206f03c60f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> then instantiate a {@link ModelComponentElementAlias} proxy.
     * 
     * @return a {@link ModelComponentElementAlias} proxy on the created {@link UmlModelElement}.
     */
    @objid ("5ed6a77a-8de4-4cff-98b3-744b8e4f52f0")
    public static ModelComponentElementAlias create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME);
        return ModelComponentElementAlias.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link ModelComponentElementAlias} proxy or <i>null</i>.
     */
    @objid ("30b1cd5d-2f89-4494-8485-df3001e4ddfc")
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
    @objid ("f235b108-25db-426f-b385-32d5b31d1d26")
    public static ModelComponentElementAlias safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (ModelComponentElementAlias.canInstantiate(obj))
        	return new ModelComponentElementAlias(obj);
        else
        	throw new IllegalArgumentException("ModelComponentElementAlias: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("145d1147-e4f4-4452-9ea4-6705d152e3eb")
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
    @objid ("fc2f0c82-8ba7-4a72-9766-37ba9f9478f0")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ce53722a-eef9-437b-84ea-c28d2431b6ca")
    public String getUuid() {
        return this.elt.getTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT);
    }

    @objid ("a5b3bdbc-29b0-4811-b8b3-8a7b155faadd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("15bb8479-3864-48e9-80a2-a8379e07a974")
    public void setUuid(String value) {
        this.elt.putTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT, value);
    }

    @objid ("f0de6980-9eb1-43f9-911a-9e98c0d51b2b")
    protected  ModelComponentElementAlias(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("5d98506b-4aa3-4855-9a1e-78d997c2836c")
    public static final class MdaTypes {
        @objid ("eb501877-3f87-4b66-9fe1-d981aa5d2bb1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5d6e8b30-9880-4226-8522-78cf6cab98a9")
        public static TagType UUID_TAGTYPE_ELT;

        @objid ("f43e2d88-16d0-4d72-b381-e461771128e9")
        private static Stereotype MDAASSOCDEP;

        @objid ("29131a59-a806-4046-be72-d568cc32e7b6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("41434c48-a0d3-4c1f-9315-6dffc309ef59")
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
