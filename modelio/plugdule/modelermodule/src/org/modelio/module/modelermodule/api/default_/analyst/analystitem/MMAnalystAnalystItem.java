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
 * Proxy class to handle a Analyst.AnalystItem metaclass.
 * <p>Description:
 * <br/><i>MMAnalystAnalystItem</i></p>
 */
@objid ("0b76deb1-83c1-4c46-84b3-4a6731dda2cf")
public class MMAnalystAnalystItem {
    @objid ("5d701036-d088-42b0-8e53-26e07b727dd2")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link AnalystItem} represented by this proxy, never null.
     */
    @objid ("6cb4427a-8a4b-489e-b5f2-3f638cffc357")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMAnalystAnalystItem proxy} can be instantiated from a {@link MObject} checking it is a Analyst.AnalystItem.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5b4fd72f-2dc1-41da-9376-f96bfebf6bec")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Analyst.AnalystItem");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMAnalystAnalystItem} proxy from a Analyst.AnalystItem checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AnalystItem
     * @return a {@link MMAnalystAnalystItem} proxy or <i>null</i>.
     */
    @objid ("b27bd67c-88b1-4956-a903-2ad0ddc50b4b")
    public static MMAnalystAnalystItem instantiate(ModelElement obj) {
        return MMAnalystAnalystItem.canInstantiate(obj) ? new MMAnalystAnalystItem(obj) : null;
    }

    @objid ("b352358f-4166-40ca-bf41-183cda35cbd4")
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
     * Get the underlying Analyst.AnalystItem.
     * @return the AnalystItem represented by this proxy, never null.
     */
    @objid ("d76f7a38-106a-4ed3-8957-b567ffa9c2ca")
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
    @objid ("9d399fbd-c9b7-4e3f-9397-4ff30ff53dce")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMAnalystAnalystItem.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("d6858d7e-40f8-4a45-8ced-89c7e91bb49e")
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
    @objid ("c9c5429c-00b8-489d-b220-acd371dff1ff")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMAnalystAnalystItem.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("f9a20316-cc58-420d-a00e-1a3e95b9a00c")
    protected  MMAnalystAnalystItem(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("efbe94c7-e1fa-40fc-a932-c359d92f1e65")
    public static final class MdaTypes {
        @objid ("9f2b1e2f-a469-4c21-bbf5-63d46cf02304")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("a4ebbc08-8b9d-4bbc-82ca-aeec28697d3c")
        private static Stereotype MDAASSOCDEP;

        @objid ("d86db7b0-e085-47c0-9d3e-ade08a91d440")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c0ac8f00-7f3f-489e-90d3-215f0f969126")
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
