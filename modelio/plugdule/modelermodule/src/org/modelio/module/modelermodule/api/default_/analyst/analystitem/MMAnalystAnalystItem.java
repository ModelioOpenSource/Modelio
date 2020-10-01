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
package org.modelio.module.modelermodule.api.default_.analyst.analystitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a AnalystItem metaclass.
 * <p>Description:
 * <br/><i>MMAnalystAnalystItem</i></p>
 */
@objid ("0b76deb1-83c1-4c46-84b3-4a6731dda2cf")
public class MMAnalystAnalystItem {
    @objid ("2697f643-8cb6-4236-9b3b-318a31a9eec0")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link AnalystItem} represented by this proxy, never null.
     */
    @objid ("3b5c9354-0c49-41c4-b77f-d357f65b746c")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMAnalystAnalystItem proxy} can be instantiated from a {@link MObject} checking it is a AnalystItem.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0889ee89-8504-40cb-a9a5-76d5274ac0a5")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Analyst.AnalystItem");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMAnalystAnalystItem} proxy from a AnalystItem checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a AnalystItem
     * @return a {@link MMAnalystAnalystItem} proxy or <i>null</i>.
     */
    @objid ("2e8c768a-f412-4d19-89a5-9a7dab650283")
    public static MMAnalystAnalystItem instantiate(ModelElement obj) {
        return MMAnalystAnalystItem.canInstantiate(obj) ? new MMAnalystAnalystItem(obj) : null;
    }

    @objid ("dee567d9-25c8-48dc-bb81-903c516b4475")
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
        MMAnalystAnalystItem other = (MMAnalystAnalystItem) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying AnalystItem.
     * 
     * @return the AnalystItem represented by this proxy, never null.
     */
    @objid ("fc2b00e9-2e3d-43cc-8995-ff607d8d021b")
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
    @objid ("ce8d2e15-3ac2-441b-afa6-58a2e77138b8")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMAnalystAnalystItem.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("1cfc07a4-9ef6-4db1-ac8d-26995b0f61fc")
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
    @objid ("58bab081-3a93-4442-ac60-c67287e23158")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMAnalystAnalystItem.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("657690d3-e818-450f-b3c9-7dc826d377ee")
    protected MMAnalystAnalystItem(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("efbe94c7-e1fa-40fc-a932-c359d92f1e65")
    public static final class MdaTypes {
        @objid ("531e4204-f918-459f-aac0-1001347efcdf")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("a97684b5-7d3b-47a2-8568-2c6bbe8cd6e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("9421ccff-a4f9-4949-bd12-4b547705ac65")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("66461329-6cda-4328-87f4-271aaee55efd")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "3139ea84-fdb9-45ef-9b08-ea65d7408105");
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
