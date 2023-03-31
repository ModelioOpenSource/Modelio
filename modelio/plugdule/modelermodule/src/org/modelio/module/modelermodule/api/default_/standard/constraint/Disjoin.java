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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << disjoin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("df267913-e3d3-4867-9a81-4c7c6893fae1")
public class Disjoin {
    @objid ("6fb47432-6a11-44aa-93a5-c6b87c7d5b72")
    public static final String STEREOTYPE_NAME = "disjoin";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("db8e1977-f3d4-4733-b2ca-84923ba4f7c2")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Disjoin proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << disjoin >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("73348070-bdc3-42e3-a435-40c60b0733a1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << disjoin >> then instantiate a {@link Disjoin} proxy.
     * 
     * @return a {@link Disjoin} proxy on the created {@link Constraint}.
     */
    @objid ("732b88f6-88e0-4ae9-af91-aee9191ab923")
    public static Disjoin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME);
        return Disjoin.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Disjoin} proxy or <i>null</i>.
     */
    @objid ("c5cc870b-bdb4-4742-ba2a-bb533c66491a")
    public static Disjoin instantiate(Constraint obj) {
        return Disjoin.canInstantiate(obj) ? new Disjoin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Disjoin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("739c1e6e-4be9-4ec8-9542-70772c568370")
    public static Disjoin safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Disjoin.canInstantiate(obj))
        	return new Disjoin(obj);
        else
        	throw new IllegalArgumentException("Disjoin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f080731f-d511-4516-ad9b-112bc5514aad")
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
        Disjoin other = (Disjoin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("ca0c2afa-9f98-43f5-b0d8-9e4b2046a22a")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("f0419c20-8e89-4df6-82cd-fda7947457b2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c48b5544-19e6-47d1-94bb-8c870bba92ce")
    protected  Disjoin(Constraint elt) {
        this.elt = elt;
    }

    @objid ("27c0c9fd-b109-4914-a1a6-01ed7023ca30")
    public static final class MdaTypes {
        @objid ("0778348f-fc67-45bd-8a96-e2b9f7dc05f5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("cfbb724c-c57a-4fe5-9997-14da14b79f1b")
        private static Stereotype MDAASSOCDEP;

        @objid ("a227078a-d289-46a1-ab0a-dadd587aa035")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("601a1ea3-6475-48a1-87f3-4122d38fa8c7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f5-0000-000000000000");
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
