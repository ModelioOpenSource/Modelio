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
    @objid ("6a9edf6b-9b44-436a-be06-504cf891850e")
    public static final String STEREOTYPE_NAME = "UML2Variable";

    @objid ("93790c5e-a9bf-44f6-857d-c4e9bff88672")
    public static final String LOWER_TAGTYPE = "Lower";

    @objid ("94f213bf-b1b5-4645-8946-4c74ec6df22c")
    public static final String ORDERED_TAGTYPE = "Ordered";

    @objid ("c7ef4bc5-f582-442d-b1db-0e5c675de4d8")
    public static final String UNIQUE_TAGTYPE = "Unique";

    /**
     * The underlying {@link InstanceNode} represented by this proxy, never null.
     */
    @objid ("f8f0a6dd-22be-492e-a9ab-a3da592e6d23")
    protected final InstanceNode elt;

    /**
     * Tells whether a {@link UML2Variable proxy} can be instantiated from a {@link MObject} checking it is a {@link InstanceNode} stereotyped << UML2Variable >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0c7b260d-c73a-42ab-b282-6631fd8d2d1b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InstanceNode) && ((InstanceNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InstanceNode} stereotyped << UML2Variable >> then instantiate a {@link UML2Variable} proxy.
     * 
     * @return a {@link UML2Variable} proxy on the created {@link InstanceNode}.
     */
    @objid ("d03d364c-cac7-45ee-a33e-55e47c5653e1")
    public static UML2Variable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InstanceNode");
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
    @objid ("cf7bb899-9847-4773-95a7-eb71ac14932e")
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
    @objid ("3736c82b-cf3d-493f-9c71-a9bd687e3b63")
    public static UML2Variable safeInstantiate(InstanceNode obj) throws IllegalArgumentException {
        if (UML2Variable.canInstantiate(obj))
        	return new UML2Variable(obj);
        else
        	throw new IllegalArgumentException("UML2Variable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8b6def6c-3e98-4f1d-b720-74ba8b659020")
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
    @objid ("5d3d15f7-e78a-4d4c-b2ab-da11be092ac5")
    public InstanceNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("136a7911-db93-4b96-9f3c-959acd295ba7")
    public String getLower() {
        return this.elt.getTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT);
    }

    @objid ("c6606c2d-fe44-402d-825a-27e0093ed3c1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f199708e-f8d5-446c-a0ea-a3cd293190ce")
    public boolean isOrdered() {
        return this.elt.isTagged(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8fbf0b95-84a0-4663-8a47-1b7d6f588f43")
    public boolean isUnique() {
        return this.elt.isTagged(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9cfefc38-8cdf-4dcd-92b4-1fc519f118ef")
    public void setLower(String value) {
        this.elt.putTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d1f21035-d47e-4fb4-9c77-d7f05226e190")
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
    @objid ("ff16ac21-8966-46ca-bf71-4b72de5e0327")
    public void setUnique(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    @objid ("094a3434-e862-452b-b7e7-e5d7bddef9ad")
    protected UML2Variable(InstanceNode elt) {
        this.elt = elt;
    }

    @objid ("cde1a32f-53c8-43d5-bcef-de80f475ded2")
    public static final class MdaTypes {
        @objid ("b32c0b98-f67a-417d-8909-656d2c41cc67")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7ab18fb9-7395-49a0-a7ec-dbef68975777")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("fc36032d-9e7b-4228-9ed8-9b5e829b121b")
        public static TagType UNIQUE_TAGTYPE_ELT;

        @objid ("03901688-39a2-49fd-9e8a-1b014b3aa1db")
        public static TagType LOWER_TAGTYPE_ELT;

        @objid ("af04ec32-a3a6-4d7e-bcff-0912de03f758")
        private static Stereotype MDAASSOCDEP;

        @objid ("be30bf3e-8634-4142-919f-b642bf26e635")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e3f2259b-a57d-45f3-a6e0-386e36e84567")
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
