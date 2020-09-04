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
    @objid ("9d5522bb-03c8-4c52-9094-1bedd30220aa")
    public static final String STEREOTYPE_NAME = "UML2Variable";

    @objid ("36f6dfd6-3718-49ce-be24-3a3bfeeb5325")
    public static final String LOWER_TAGTYPE = "Lower";

    @objid ("a1be3bee-ec62-4dfd-a3a2-7810ca907a29")
    public static final String ORDERED_TAGTYPE = "Ordered";

    @objid ("e8fa7b8c-c00f-4047-9a39-9ff8f798a390")
    public static final String UNIQUE_TAGTYPE = "Unique";

    /**
     * The underlying {@link InstanceNode} represented by this proxy, never null.
     */
    @objid ("4f78df76-be1e-41d7-aae1-efcc6fb50a7a")
    protected final InstanceNode elt;

    /**
     * Tells whether a {@link UML2Variable proxy} can be instantiated from a {@link MObject} checking it is a {@link InstanceNode} stereotyped << UML2Variable >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1204a995-cabd-4915-83e7-22db34c03dec")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InstanceNode) && ((InstanceNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InstanceNode} stereotyped << UML2Variable >> then instantiate a {@link UML2Variable} proxy.
     * 
     * @return a {@link UML2Variable} proxy on the created {@link InstanceNode}.
     */
    @objid ("21685224-d6a4-42ff-af42-4e1a88a2f84e")
    public static UML2Variable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InstanceNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME);
        return UML2Variable.instantiate((InstanceNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Variable} proxy from a {@link InstanceNode} stereotyped << UML2Variable >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InstanceNode
     * @return a {@link UML2Variable} proxy or <i>null</i>.
     */
    @objid ("34e1f16c-6911-4f75-a4b3-0654f43b88b8")
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
    @objid ("81171ac4-912b-4998-aace-ad50190e26ee")
    public static UML2Variable safeInstantiate(InstanceNode obj) throws IllegalArgumentException {
        if (UML2Variable.canInstantiate(obj))
        	return new UML2Variable(obj);
        else
        	throw new IllegalArgumentException("UML2Variable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ffd234f1-59ca-4472-8ae1-84c4790e5ca3")
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
    @objid ("6e5e1962-0041-494c-b8e3-171c94a84d1c")
    public InstanceNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ef97574a-d0ca-4b91-bea4-537b62fc2b00")
    public String getLower() {
        return this.elt.getTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT);
    }

    @objid ("5c3e635a-328b-4091-b20d-003f766a9976")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7611dbd6-5d80-4cf2-9c5c-49c4e8026b72")
    public boolean isOrdered() {
        return this.elt.isTagged(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("387d4ea7-0ce7-4d5a-812e-6a63f1dd4770")
    public boolean isUnique() {
        return this.elt.isTagged(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6ec2f466-a0ab-47ba-b8ac-7d4a6878ea39")
    public void setLower(String value) {
        this.elt.putTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("696759e1-f65f-4c55-9536-2f3accd48ff3")
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
    @objid ("7a98f2e8-1d32-40a9-bd6a-3f89e840422c")
    public void setUnique(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    @objid ("de30c680-3980-486c-a2b0-b241b4c90451")
    protected UML2Variable(InstanceNode elt) {
        this.elt = elt;
    }

    @objid ("cde1a32f-53c8-43d5-bcef-de80f475ded2")
    public static final class MdaTypes {
        @objid ("05ee5a1b-77aa-4541-baea-c653993314e4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("08643b48-e4a9-4988-8b64-77ea9299e5d0")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("55472d46-0763-44a4-bfe6-30e8cc1edeb0")
        public static TagType UNIQUE_TAGTYPE_ELT;

        @objid ("98f3568f-b071-4e86-9923-3e022e7703af")
        public static TagType LOWER_TAGTYPE_ELT;

        @objid ("051a3d82-576e-42a7-914e-834d64074847")
        private static Stereotype MDAASSOCDEP;

        @objid ("d2a2f48e-1f10-458d-8795-c53f0399e92b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2860c745-8e75-4137-9b5d-1eabe062b86f")
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
