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
    @objid ("ba633e8c-c095-47ca-b97f-91fa6c35db04")
    public static final String STEREOTYPE_NAME = "UML2EndDestructionDataReference";

    @objid ("73812fd0-9d3d-47e1-afed-3f589bd7ddf4")
    public static final String ISDESTROYDUPLICATES_TAGTYPE = "IsDestroyDuplicates";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7028f6eb-4706-41a5-b6c8-30af330957bc")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDestructionDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("87ea3693-a0db-4dfc-a8a7-2b0ef94fcc90")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndDestructionDataReference >> then instantiate a {@link UML2EndDestructionDataReference} proxy.
     * 
     * @return a {@link UML2EndDestructionDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("39d85bb2-14d0-46dc-903b-67ce164d524c")
    public static UML2EndDestructionDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME);
        return UML2EndDestructionDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDestructionDataReference} proxy from a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDestructionDataReference} proxy or <i>null</i>.
     */
    @objid ("88aaec3e-e018-4004-88a4-727542efb495")
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
    @objid ("d2628781-4d29-4a25-8898-5d0410c85bf8")
    public static UML2EndDestructionDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDestructionDataReference.canInstantiate(obj))
        	return new UML2EndDestructionDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDestructionDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2fced016-bbd9-4028-aec2-cb13ba3f6ce1")
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
    @objid ("de6448c0-4f0a-463d-952a-1d8d17eb4f81")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4de5ebad-3ff1-482c-924c-e556a4745508")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7d5b4273-fcc4-49c0-9366-00b811431716")
    public boolean isIsDestroyDuplicates() {
        return this.elt.isTagged(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("749a8a7a-8a0c-4798-aab7-87104c138587")
    public void setIsDestroyDuplicates(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    @objid ("ffaab448-7f86-4cd1-ae29-c1e6c8c462fb")
    protected UML2EndDestructionDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8989eec9-3340-47a9-97c9-74399b71b10e")
    public static final class MdaTypes {
        @objid ("a7d975f0-3abc-4730-a9b4-ae913055ebb7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d4733102-2fec-40d9-99e5-07489775fe64")
        public static TagType ISDESTROYDUPLICATES_TAGTYPE_ELT;

        @objid ("7d80c5ef-fd4b-40b0-ad90-6be65617fa38")
        private static Stereotype MDAASSOCDEP;

        @objid ("850bfa54-101a-4eb5-aa6b-b92d86e9797c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b051f3b8-67d7-4f5c-a0c2-41dabe4d8cbd")
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
