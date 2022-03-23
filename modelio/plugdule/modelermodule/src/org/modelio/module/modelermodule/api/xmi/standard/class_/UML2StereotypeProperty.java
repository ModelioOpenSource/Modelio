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
package org.modelio.module.modelermodule.api.xmi.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << UML2StereotypeProperty >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16847bf6-c61c-4c56-9ab6-52148bf70507")
public class UML2StereotypeProperty {
    @objid ("c1fc7d54-65c6-4e81-9d1d-49fe9c5614db")
    public static final String STEREOTYPE_NAME = "UML2StereotypeProperty";

    @objid ("7ba1864b-be0e-44ce-9188-afb2aaaa9b7a")
    public static final String PROPERTY_TAGTYPE = "Property";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("a51892a7-0a06-47c6-aac0-f7a1ff65b737")
    protected final Class elt;

    /**
     * Tells whether a {@link UML2StereotypeProperty proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << UML2StereotypeProperty >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7f4e863f-a2be-478e-bcfb-2a84d804cf76")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << UML2StereotypeProperty >> then instantiate a {@link UML2StereotypeProperty} proxy.
     * 
     * @return a {@link UML2StereotypeProperty} proxy on the created {@link Class}.
     */
    @objid ("be4db78c-1316-4501-9097-46cfb6b3b9a1")
    public static UML2StereotypeProperty create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME);
        return UML2StereotypeProperty.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link UML2StereotypeProperty} proxy or <i>null</i>.
     */
    @objid ("87ca8620-e75d-4375-9c7e-50ed35faa2fd")
    public static UML2StereotypeProperty instantiate(Class obj) {
        return UML2StereotypeProperty.canInstantiate(obj) ? new UML2StereotypeProperty(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link UML2StereotypeProperty} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("72d2fa7e-0baa-4687-b2f7-d8fb8652af73")
    public static UML2StereotypeProperty safeInstantiate(Class obj) throws IllegalArgumentException {
        if (UML2StereotypeProperty.canInstantiate(obj))
        	return new UML2StereotypeProperty(obj);
        else
        	throw new IllegalArgumentException("UML2StereotypeProperty: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("eb88ee8c-3675-47de-942b-3a79b45a86a5")
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
        UML2StereotypeProperty other = (UML2StereotypeProperty) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("e44963c3-d256-45da-8594-3afb81046fc2")
    public Class getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("daad757e-e2d9-43eb-9ac3-c88c1a20f37b")
    public String getProperty() {
        return this.elt.getTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT);
    }

    @objid ("da7fd3e8-a070-4d98-8bde-217613633409")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6693848c-2b13-4eee-a201-59f23b13a186")
    public void setProperty(String value) {
        this.elt.putTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT, value);
    }

    @objid ("8b32de16-4a9e-4aff-bea0-f5ad35b1f34f")
    protected  UML2StereotypeProperty(Class elt) {
        this.elt = elt;
    }

    @objid ("3e2c97cc-5fc7-4642-8c2c-e5591f0c6d83")
    public static final class MdaTypes {
        @objid ("0373e895-d91f-4ca9-b742-40465621af90")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c836ef7e-5246-45cd-96d6-06bac3b4d32f")
        public static TagType PROPERTY_TAGTYPE_ELT;

        @objid ("f5a71434-f5ac-430a-8c0c-3db5c880b99c")
        private static Stereotype MDAASSOCDEP;

        @objid ("e6330f9e-d737-4070-aeb7-b9108590a8db")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c8a6e2fd-8f96-4aa2-a163-2751fbe536af")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "bae91a3b-7009-11e0-a462-0027103f347c");
            PROPERTY_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c28e8b06-7009-11e0-a462-0027103f347c");
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
