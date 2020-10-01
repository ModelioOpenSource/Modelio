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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2EndDestructionDataReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("13fa7cda-1af5-453f-b5a9-6961ffafa991")
public class UML2EndDestructionDataReference {
    @objid ("03b6955d-21a3-4b14-932a-da01335f4985")
    public static final String STEREOTYPE_NAME = "UML2EndDestructionDataReference";

    @objid ("f223435f-9f8e-48ff-93eb-04b2746063b0")
    public static final String ISDESTROYDUPLICATES_TAGTYPE = "IsDestroyDuplicates";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("9e554011-b6e4-4ea7-980b-3ac0a7f27cad")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDestructionDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("30df7df6-34c3-45bb-bbe9-712b3056a8cd")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndDestructionDataReference >> then instantiate a {@link UML2EndDestructionDataReference} proxy.
     * 
     * @return a {@link UML2EndDestructionDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("3ab65823-9882-42df-944e-ccb38817e7ad")
    public static UML2EndDestructionDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME);
        return UML2EndDestructionDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDestructionDataReference} proxy from a {@link Dependency} stereotyped << UML2EndDestructionDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDestructionDataReference} proxy or <i>null</i>.
     */
    @objid ("2ae6410f-f2b8-49e4-8899-3fbd9c98c335")
    public static UML2EndDestructionDataReference instantiate(Dependency obj) {
        return UML2EndDestructionDataReference.canInstantiate(obj) ? new UML2EndDestructionDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndDestructionDataReference} proxy from a {@link Dependency} stereotyped << UML2EndDestructionDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndDestructionDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0285efed-bf62-49f8-b67f-193765851d58")
    public static UML2EndDestructionDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDestructionDataReference.canInstantiate(obj))
        	return new UML2EndDestructionDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDestructionDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("399bb5e2-c3ff-4fec-ae14-aac7c76ea4ff")
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
        UML2EndDestructionDataReference other = (UML2EndDestructionDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("897f4904-67cd-4e40-a02b-cdbd664c8b1d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("fbd97a10-928f-44de-8b9b-c531b3b4c67d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6ba0844a-7a7c-44eb-b2c1-77338dd3849f")
    public boolean isIsDestroyDuplicates() {
        return this.elt.isTagged(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("945968a2-f7cb-4e5b-a1a9-3b33422adc80")
    public void setIsDestroyDuplicates(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    @objid ("90959f2c-7e5e-48d6-9a87-a3595d8b9502")
    protected UML2EndDestructionDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8989eec9-3340-47a9-97c9-74399b71b10e")
    public static final class MdaTypes {
        @objid ("706660a3-118b-44b4-ba4e-19b209b36d68")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2d97c513-412e-40ab-ad2c-47891dc064d1")
        public static TagType ISDESTROYDUPLICATES_TAGTYPE_ELT;

        @objid ("81e2d97d-0279-4549-846b-5627b49a61e7")
        private static Stereotype MDAASSOCDEP;

        @objid ("4eec3a43-157f-4103-abf6-15080cac9a1f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aac918d6-f639-4eef-80fb-f1095addd22f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a74178fb-df2b-11de-905b-001302895b2b");
            ISDESTROYDUPLICATES_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "6f99afff-df2d-11de-905b-001302895b2b");
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
