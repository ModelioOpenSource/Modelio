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
package org.modelio.module.modelermodule.api.xmi.standard.attribute;

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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Attribute} with << UML2PropertyType >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("97813a21-b7b0-4858-b207-98c2879e16e9")
public class UML2PropertyType {
    @objid ("f7ecf3e1-36a5-45f5-bc0c-ccdc78bad6a2")
    public static final String STEREOTYPE_NAME = "UML2PropertyType";

    @objid ("3e51c17b-6288-46f2-8272-aad652d817e1")
    public static final String PROPERTYTYPE_TAGTYPE = "PropertyType";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("b64c9785-450f-4e67-8fa0-570041b720a1")
    protected final Attribute elt;

    /**
     * Tells whether a {@link UML2PropertyType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << UML2PropertyType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a2198235-1279-40e8-ad6b-2105b0c9c88c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Attribute) && ((Attribute) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Attribute} stereotyped << UML2PropertyType >> then instantiate a {@link UML2PropertyType} proxy.
     * 
     * @return a {@link UML2PropertyType} proxy on the created {@link Attribute}.
     */
    @objid ("2de136bc-410a-4980-8536-292a2d131d33")
    public static UML2PropertyType create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Attribute");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME);
        return UML2PropertyType.instantiate((Attribute)e);
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link UML2PropertyType} proxy or <i>null</i>.
     */
    @objid ("d211f356-0260-484a-8819-710ce8b4b256")
    public static UML2PropertyType instantiate(Attribute obj) {
        return UML2PropertyType.canInstantiate(obj) ? new UML2PropertyType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Attribute}
     * @return a {@link UML2PropertyType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("962a6969-5caa-462c-a2d5-4715301e564f")
    public static UML2PropertyType safeInstantiate(Attribute obj) throws IllegalArgumentException {
        if (UML2PropertyType.canInstantiate(obj))
        	return new UML2PropertyType(obj);
        else
        	throw new IllegalArgumentException("UML2PropertyType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5ddbbc61-a45c-4831-8df0-85c33489745c")
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
        UML2PropertyType other = (UML2PropertyType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("4fc0bbd6-2155-441c-aa4a-0243fc42c88f")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1b222e0b-868e-42b1-bbb0-5bc5148932e5")
    public String getPropertyType() {
        return this.elt.getTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT);
    }

    @objid ("3c3f193e-17af-4cbf-b517-3f361e1ddfdb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6197627a-cd54-4b21-9fba-4c8afe459853")
    public void setPropertyType(String value) {
        this.elt.putTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT, value);
    }

    @objid ("81fd2537-fecf-4efe-8760-5b51fc902e4d")
    protected UML2PropertyType(Attribute elt) {
        this.elt = elt;
    }

    @objid ("52f01be1-c820-4bae-a974-82d910c67b9a")
    public static final class MdaTypes {
        @objid ("62bc4041-2a23-4a71-af54-812cee9691fc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7798a7bc-7d4c-45fe-a08e-bde45c27675f")
        public static TagType PROPERTYTYPE_TAGTYPE_ELT;

        @objid ("a7a02155-fcc8-4ee7-bc97-934f6d57ba53")
        private static Stereotype MDAASSOCDEP;

        @objid ("caf28f5a-9705-461f-bde8-2cb3e1633ce0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d4e86b3e-6bbc-4e9e-89e8-b150cd68c837")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "68c63e60-70d6-11e0-872f-0027103f347c");
            PROPERTYTYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "68c63e61-70d6-11e0-872f-0027103f347c");
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
