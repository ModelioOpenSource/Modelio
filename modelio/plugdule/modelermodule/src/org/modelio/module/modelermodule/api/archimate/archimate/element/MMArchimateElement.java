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
 * Proxy class to handle a Element metaclass.
 * <p>Description:
 * <br/><i>MMArchimateElement</i></p>
 */
@objid ("f08f1ee5-dc12-493b-aefe-06163fcd33d9")
public class MMArchimateElement {
    @objid ("dd78bf13-7714-4784-b2aa-8827cc8d1cd6")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Element} represented by this proxy, never null.
     */
    @objid ("a0b638ae-82c1-409c-b006-f9c57c366a3c")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMArchimateElement proxy} can be instantiated from a {@link MObject} checking it is a Element.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fce00869-c133-4471-b1a3-cd5e64b2215b")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Archimate.Element");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMArchimateElement} proxy from a Element checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a Element
     * @return a {@link MMArchimateElement} proxy or <i>null</i>.
     */
    @objid ("42b313dc-dd41-4cb0-88dd-8c3494276516")
    public static MMArchimateElement instantiate(ModelElement obj) {
        return MMArchimateElement.canInstantiate(obj) ? new MMArchimateElement(obj) : null;
    }

    @objid ("eea3e5de-ba26-49de-b147-4520cb2b3281")
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
     * Get the underlying Element.
     * 
     * @return the Element represented by this proxy, never null.
     */
    @objid ("121cc320-fd55-4a79-91c2-438593f61143")
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
    @objid ("09bd3b5e-2482-4d1c-b849-e602dfe5f83a")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMArchimateElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("b970c19d-aa6f-4201-845b-11bdd3f72be0")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("647c3eae-5d1f-48d9-bbd8-859a047b94a4")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMArchimateElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("296b9c5e-2f45-4342-8086-015c5cc5d817")
    protected MMArchimateElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("5f5d64b1-9d12-4192-9d23-938c76e9d8f5")
    public static final class MdaTypes {
        @objid ("8fbff9ce-6640-4ad9-9dad-1376fb08013d")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("435617ed-514a-44dd-b5d0-a65c4a9148e0")
        private static Stereotype MDAASSOCDEP;

        @objid ("e7409566-3443-4ae6-8fb4-2bda1c88149e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f2cb7e5e-2d0f-413b-b286-ad4c9787399c")
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
