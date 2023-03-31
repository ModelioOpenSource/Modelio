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
 * Proxy class to handle a {@link Dependency} with << part >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5b672df6-1d49-4ad4-890a-08c3ae1afcb3")
public class Part {
    @objid ("f2d1067b-fdef-4623-9164-0dbbe29cd38d")
    public static final String STEREOTYPE_NAME = "part";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("4c19dfae-544f-4d25-87ca-fa788e2a7f82")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Part proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << part >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ba57ccc0-df29-49f3-bb99-7537ac82e17b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << part >> then instantiate a {@link Part} proxy.
     * 
     * @return a {@link Part} proxy on the created {@link Dependency}.
     */
    @objid ("a527ce0a-781b-4ed2-ab38-095a7653019e")
    public static Part create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME);
        return Part.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Part} proxy or <i>null</i>.
     */
    @objid ("5f9d9484-bbe2-493c-96f2-46dd9fc9fa57")
    public static Part instantiate(Dependency obj) {
        return Part.canInstantiate(obj) ? new Part(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Part} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("32826f07-1544-4ada-955d-18472384bae4")
    public static Part safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Part.canInstantiate(obj))
        	return new Part(obj);
        else
        	throw new IllegalArgumentException("Part: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fe63aa61-c81b-4e82-9bca-6be4298aba46")
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
        Part other = (Part) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("504b44ed-84e4-4464-9c47-50ee84f63fa3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("70fccd74-f59f-4124-8a63-e44e9e982999")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3654f737-b9e2-4ff6-a6bb-42f96fa4d1c4")
    protected  Part(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7ea483f7-ea8b-4bc8-8c23-87eb77766490")
    public static final class MdaTypes {
        @objid ("02c0592e-f597-4409-9554-a296aeceab68")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dc5e4b28-e606-4c29-8db6-62076b45df50")
        private static Stereotype MDAASSOCDEP;

        @objid ("525a10f1-21db-4e46-9b33-2d37feb14d1b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6f476617-b0b0-4c45-9a1c-a2aea76d6767")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-00b7-0000-000000000000");
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
