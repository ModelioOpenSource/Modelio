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
package org.modelio.module.modelermodule.api.archimate.archimate.element;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a Archimate.Element metaclass.
 * <p>Description:
 * <br/><i>MMArchimateElement</i></p>
 */
@objid ("f08f1ee5-dc12-493b-aefe-06163fcd33d9")
public class MMArchimateElement {
    @objid ("201c97cf-d290-45d7-ba18-d4c75f8c4037")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Element} represented by this proxy, never null.
     */
    @objid ("bcf63b06-307e-4350-9f23-4dc2bfecaea3")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMArchimateElement proxy} can be instantiated from a {@link MObject} checking it is a Archimate.Element.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1ffad4a6-1368-45d7-a56b-4d80a58a8fae")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Archimate.Element");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMArchimateElement} proxy from a Archimate.Element checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Element
     * @return a {@link MMArchimateElement} proxy or <i>null</i>.
     */
    @objid ("d2972821-3866-42e8-b7e9-0f228421e644")
    public static MMArchimateElement instantiate(ModelElement obj) {
        return MMArchimateElement.canInstantiate(obj) ? new MMArchimateElement(obj) : null;
    }

    @objid ("508fbb8f-f939-4b34-a60a-9f9d66c6a31f")
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
        MMArchimateElement other = (MMArchimateElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying Archimate.Element.
     * @return the Element represented by this proxy, never null.
     */
    @objid ("eef4b46d-0fe7-46ae-938e-1aa0a7e04ef7")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("47e89d23-18b7-4570-a968-9b1b5ff39214")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMArchimateElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("859538c5-57b8-4e72-8288-382ae14e7d90")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("b3c80936-4ab5-4612-a68c-088fcdac6e7b")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMArchimateElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("7c7a22c3-8365-461c-877c-52f4469d26d3")
    protected  MMArchimateElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("5f5d64b1-9d12-4192-9d23-938c76e9d8f5")
    public static final class MdaTypes {
        @objid ("41b8fe9c-54ea-40fb-bb04-4f5dd494781e")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("cc7a5319-201d-4231-bc6f-a7d0cd0f5e7e")
        private static Stereotype MDAASSOCDEP;

        @objid ("29d9a00e-f91c-4ec9-a38c-a928636bb1a0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a89c8834-c2d2-49f4-b6ee-dcc8e0ac8f75")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "4e937f83-ad13-4869-8ba0-ac698e1c0762");
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
