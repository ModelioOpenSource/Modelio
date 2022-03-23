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
package org.modelio.module.modelermodule.api.xmi.standard.instancenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link InstanceNode} with << UML2Variable >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a009a5d3-00b8-46b7-ad7a-a22b170ab56e")
public class UML2Variable {
    @objid ("36fd3d63-81cb-4457-9823-a104112146c5")
    public static final String STEREOTYPE_NAME = "UML2Variable";

    @objid ("1c6dac00-1f03-443b-995e-8b46b2ae741f")
    public static final String LOWER_TAGTYPE = "Lower";

    @objid ("0d624225-8bd7-4532-ba40-40e43d608aa9")
    public static final String ORDERED_TAGTYPE = "Ordered";

    @objid ("e2ddc974-91fc-411b-b057-2542c781985a")
    public static final String UNIQUE_TAGTYPE = "Unique";

    /**
     * The underlying {@link InstanceNode} represented by this proxy, never null.
     */
    @objid ("b6fc197d-063d-45c4-8f9f-cc2a7c9a62b6")
    protected final InstanceNode elt;

    /**
     * Tells whether a {@link UML2Variable proxy} can be instantiated from a {@link MObject} checking it is a {@link InstanceNode} stereotyped << UML2Variable >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("76b08973-474f-46ed-942e-e637a2e407a2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InstanceNode) && ((InstanceNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InstanceNode} stereotyped << UML2Variable >> then instantiate a {@link UML2Variable} proxy.
     * 
     * @return a {@link UML2Variable} proxy on the created {@link InstanceNode}.
     */
    @objid ("ef178bdf-21b0-4a28-bbec-cda7868aff95")
    public static UML2Variable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InstanceNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME);
        return UML2Variable.instantiate((InstanceNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Variable} proxy from a {@link InstanceNode} stereotyped << UML2Variable >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InstanceNode
     * @return a {@link UML2Variable} proxy or <i>null</i>.
     */
    @objid ("c7e2a0d8-d943-4122-854c-bd33b6005d68")
    public static UML2Variable instantiate(InstanceNode obj) {
        return UML2Variable.canInstantiate(obj) ? new UML2Variable(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Variable} proxy from a {@link InstanceNode} stereotyped << UML2Variable >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InstanceNode}
     * @return a {@link UML2Variable} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("03ec2ffa-abc3-414e-9290-455bdbce63f1")
    public static UML2Variable safeInstantiate(InstanceNode obj) throws IllegalArgumentException {
        if (UML2Variable.canInstantiate(obj))
        	return new UML2Variable(obj);
        else
        	throw new IllegalArgumentException("UML2Variable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("de8ae003-ee12-44a1-9614-59c641fea560")
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
        UML2Variable other = (UML2Variable) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InstanceNode}. 
     * @return the InstanceNode represented by this proxy, never null.
     */
    @objid ("9ade344a-1143-4af1-8efd-2247d4e60c2d")
    public InstanceNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("155ad389-80dd-46df-b274-a2feea47d2e0")
    public String getLower() {
        return this.elt.getTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT);
    }

    @objid ("81b887ae-e947-46d5-a0e4-fcfa03ce5f74")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("17496807-4560-4679-aa4c-e39d8601ddb7")
    public boolean isOrdered() {
        return this.elt.isTagged(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("12d787af-efd0-4bde-bc38-aa248f2aca21")
    public boolean isUnique() {
        return this.elt.isTagged(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("edc4e4e9-0d0a-49b4-9b45-c334fde3ff5c")
    public void setLower(String value) {
        this.elt.putTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("96f63903-95f6-41c1-bc81-f312a0430704")
    public void setOrdered(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0eb46318-4613-4679-a153-2bdea846912b")
    public void setUnique(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    @objid ("147deef5-69b4-4143-b376-b09f8fb430ef")
    protected  UML2Variable(InstanceNode elt) {
        this.elt = elt;
    }

    @objid ("cde1a32f-53c8-43d5-bcef-de80f475ded2")
    public static final class MdaTypes {
        @objid ("2b9c73c8-7517-41c9-b1bf-849634bb7026")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d7181679-fa87-4e7e-8335-dd3cde3636ff")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("e9252ff2-df04-416a-97cf-6983a5ca2e01")
        public static TagType UNIQUE_TAGTYPE_ELT;

        @objid ("c82291e6-edf1-479f-9d4a-787ed5ce710c")
        public static TagType LOWER_TAGTYPE_ELT;

        @objid ("22f27b86-1bbf-40d8-be57-db750c50679b")
        private static Stereotype MDAASSOCDEP;

        @objid ("95baafb1-eabe-445b-9908-3a91b215457a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c49eac11-f961-4c70-8ecb-26a0c709e7f5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "de38398e-c746-11e1-8624-0027103f347d");
            ORDERED_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "03791167-cf3b-11e1-928c-0027103f347d");
            UNIQUE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "16933d11-cf40-11e1-b123-0027103f347d");
            LOWER_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "269fc5ec-cf40-11e1-b123-0027103f347d");
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
