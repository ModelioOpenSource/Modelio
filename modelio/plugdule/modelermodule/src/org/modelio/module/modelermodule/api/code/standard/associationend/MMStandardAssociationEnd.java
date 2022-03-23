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
package org.modelio.module.modelermodule.api.code.standard.associationend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link AssociationEnd} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociationEnd</i></p>
 */
@objid ("7fddda36-ab0b-4d49-9ae9-50a38347f9ea")
public class MMStandardAssociationEnd {
    @objid ("76e9c65e-8430-4d20-80e1-b20cc24acf39")
    public static final String NOCODE_TAGTYPE = "nocode";

    @objid ("b5856267-d84f-4090-b11d-a522594c8682")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("3a76ee59-5f59-4428-ba87-eeefcb8499de")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ddcd2748-a10f-4dfb-82ef-cfb442fbae13")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof AssociationEnd);
    }

    /**
     * Tries to instantiate a {@link MMStandardAssociationEnd} proxy from a {@link AssociationEnd} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AssociationEnd
     * @return a {@link MMStandardAssociationEnd} proxy or <i>null</i>.
     */
    @objid ("e60968c4-4774-4cb6-8926-22d673f4f3e4")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("691f2fa4-9c5e-4c62-b34f-b0cab98c2be5")
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
        MMStandardAssociationEnd other = (MMStandardAssociationEnd) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AssociationEnd}. 
     * @return the AssociationEnd represented by this proxy, never null.
     */
    @objid ("1116b823-2ebb-424e-b640-d330965ad397")
    public AssociationEnd getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("ab2b1f81-b281-439c-a0d8-6cdb705ce360")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardAssociationEnd.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("68ba4f04-4fa9-4835-b69b-eff405b2a810")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f30775fc-452f-4b25-8470-b718483c7658")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3acdd18d-b125-4f29-93f7-bd4319d23a60")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for List<String> property 'type'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7d6e4d19-d987-463d-8658-a449d91fc827")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardAssociationEnd.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("bae32679-1deb-4fb9-a282-8938302315fe")
    protected  MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("57627f85-c429-4d46-81c5-edf958d8ff53")
    public static final class MdaTypes {
        @objid ("96199579-8dfc-44e9-adf8-d1b6a8af0d83")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("213c1cf9-90c4-4c5a-b6f5-b6b50e04377c")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("3c8bd3d6-3097-4b9a-8eed-01dc1a5a4ca7")
        private static Stereotype MDAASSOCDEP;

        @objid ("6bbbb470-1d6a-424e-bf2f-55956fb50feb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6f840379-0db6-415a-b6ba-7eba198b616f")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36be-0000-000000000000");
            TYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-3766-0000-000000000000");
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
