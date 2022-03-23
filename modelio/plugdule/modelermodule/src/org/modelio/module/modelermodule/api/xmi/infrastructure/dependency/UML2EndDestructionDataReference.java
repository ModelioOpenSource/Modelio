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
    @objid ("fc45ab5d-3d49-4f7d-a0de-7410acfe4d6a")
    public static final String STEREOTYPE_NAME = "UML2EndDestructionDataReference";

    @objid ("bb0904d9-7d8c-4988-812b-2df1cdfe9e7a")
    public static final String ISDESTROYDUPLICATES_TAGTYPE = "IsDestroyDuplicates";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d4d6c38c-69c1-440e-bf11-83cb7052b9cd")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDestructionDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3da74f83-4b44-4baf-827b-4bd71e562e3b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndDestructionDataReference >> then instantiate a {@link UML2EndDestructionDataReference} proxy.
     * 
     * @return a {@link UML2EndDestructionDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("093d4b3a-7d19-46a8-894f-4d2cf3b36759")
    public static UML2EndDestructionDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
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
    @objid ("b301742b-b005-48d7-ac3d-0210c071755a")
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
    @objid ("37ddb0dc-f451-47a5-9ab7-c0d6c4256003")
    public static UML2EndDestructionDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDestructionDataReference.canInstantiate(obj))
        	return new UML2EndDestructionDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDestructionDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d71c2e37-8f94-4d4d-a223-043052945ad2")
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
    @objid ("6386e147-3e6d-4ef1-95c4-34f75a7afce3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("eea1f7ba-b1a0-4d1c-9663-49980001533d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("120aa0a9-e6d5-4268-8cbf-718b5b53f126")
    public boolean isIsDestroyDuplicates() {
        return this.elt.isTagged(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("95070ab4-fb5e-482d-95f7-03a92e737445")
    public void setIsDestroyDuplicates(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    @objid ("16a9a864-3005-4023-9732-23e432908662")
    protected  UML2EndDestructionDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8989eec9-3340-47a9-97c9-74399b71b10e")
    public static final class MdaTypes {
        @objid ("60c112c9-4de2-4798-9577-73c2fef56cd4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("db48c203-631a-46d7-930d-215108fd7dcc")
        public static TagType ISDESTROYDUPLICATES_TAGTYPE_ELT;

        @objid ("324eed58-8913-428d-b4e5-8df513b9fe69")
        private static Stereotype MDAASSOCDEP;

        @objid ("a7c94102-ba01-4035-9159-eaad0ff23614")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("11f58b67-ac2b-4971-9076-69f892feff52")
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
