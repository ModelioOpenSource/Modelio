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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("354d8db8-ee66-44cd-b3ef-7c744afd0684")
    public static final String STEREOTYPE_NAME = "disjoin";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("c4ce5cd3-9f55-46fa-a4d3-f697d23f6f2b")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Disjoin proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << disjoin >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("991c06ef-1628-4c2c-915a-986ac9a0e14c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << disjoin >> then instantiate a {@link Disjoin} proxy.
     * 
     * @return a {@link Disjoin} proxy on the created {@link Constraint}.
     */
    @objid ("55bae725-f459-4678-914d-1232af2c90a5")
    public static Disjoin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME);
        return Disjoin.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Disjoin} proxy or <i>null</i>.
     */
    @objid ("01d79232-e862-44b2-a2c4-1572790122c1")
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
    @objid ("1f806207-c9d2-4c5f-ad06-c565dde4511a")
    public static Disjoin safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Disjoin.canInstantiate(obj))
        	return new Disjoin(obj);
        else
        	throw new IllegalArgumentException("Disjoin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d734757b-210d-4923-bbfd-082b776d1d20")
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
    @objid ("0544d7a9-6cb2-489b-baca-edde4d404ace")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("15b767c7-363f-43f1-a276-ea87d94777e4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("380c173e-d0d2-4968-9d9c-e8cf9a4ef574")
    protected Disjoin(Constraint elt) {
        this.elt = elt;
    }

    @objid ("27c0c9fd-b109-4914-a1a6-01ed7023ca30")
    public static final class MdaTypes {
        @objid ("87b42991-2825-434b-8865-13a45b8ff373")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("19744480-4151-433d-9f59-9877790d255c")
        private static Stereotype MDAASSOCDEP;

        @objid ("a06929c3-edd8-48ce-8e47-886b240377d2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1d2cfca3-2f01-4b40-8461-2e5fab951a2e")
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
