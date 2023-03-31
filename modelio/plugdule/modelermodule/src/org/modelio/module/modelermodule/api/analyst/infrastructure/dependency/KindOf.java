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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << kind-of >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("48e6ef94-b235-4f1d-8b9b-a5b2b6b40e53")
public class KindOf {
    @objid ("97a6d319-a426-44f2-9c26-e786c8a3ae1b")
    public static final String STEREOTYPE_NAME = "kind-of";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("71932239-2a1b-4c08-9928-c008761b2688")
    protected final Dependency elt;

    /**
     * Tells whether a {@link KindOf proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << kind-of >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("19bce6fe-db56-4779-9c6e-bd9e678c5a7d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << kind-of >> then instantiate a {@link KindOf} proxy.
     * 
     * @return a {@link KindOf} proxy on the created {@link Dependency}.
     */
    @objid ("5582d846-d68b-4cb8-8650-c71f4a1362a5")
    public static KindOf create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME);
        return KindOf.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link KindOf} proxy or <i>null</i>.
     */
    @objid ("ac9ed505-ae6d-4b47-adab-704031722a4c")
    public static KindOf instantiate(Dependency obj) {
        return KindOf.canInstantiate(obj) ? new KindOf(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link KindOf} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fdee2db8-d651-4e05-9c17-9c566f608ebb")
    public static KindOf safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (KindOf.canInstantiate(obj))
        	return new KindOf(obj);
        else
        	throw new IllegalArgumentException("KindOf: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0b9afa1-5941-4684-af14-de06d705c4b1")
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
        KindOf other = (KindOf) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("efffe57c-81fd-4173-95e6-4c8be7a46425")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8b60f74c-0ab7-4238-90a7-701253ac5a82")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("4d48dea3-2456-46ac-bc19-a532e12dd18f")
    protected  KindOf(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5d095a89-ce1f-419e-b944-0225c5b7e954")
    public static final class MdaTypes {
        @objid ("360680cc-52ed-4da2-b50c-83e4cbcfd0bb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9bed6f61-3b28-4043-9b5e-0126ee9ecd19")
        private static Stereotype MDAASSOCDEP;

        @objid ("845ab792-90de-4379-b316-830aff6de1bc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("adca448b-00ba-4753-9b23-890c5b24d3ac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-12f8-0000-000000000000");
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
