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
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << goal_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("712e332b-fc86-4b4e-822a-eee5103016f9")
public class GoalDiagram {
    @objid ("d3661cb1-b7e9-4e2c-8113-7658efac46e4")
    public static final String STEREOTYPE_NAME = "goal_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("c0bba2a8-8758-44f3-bc79-17559bd34423")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link GoalDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << goal_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9a32abc2-7a32-4e8a-9c34-5e50b279f848")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << goal_diagram >> then instantiate a {@link GoalDiagram} proxy.
     * 
     * @return a {@link GoalDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("ba141ccc-fbc3-4b08-b4fb-1b15dca4ceaa")
    public static GoalDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME);
        return GoalDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link GoalDiagram} proxy from a {@link StaticDiagram} stereotyped << goal_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link GoalDiagram} proxy or <i>null</i>.
     */
    @objid ("18f5a02d-5dd1-4619-ab6d-c27663286efc")
    public static GoalDiagram instantiate(StaticDiagram obj) {
        return GoalDiagram.canInstantiate(obj) ? new GoalDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link GoalDiagram} proxy from a {@link StaticDiagram} stereotyped << goal_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link GoalDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2ef42828-8e7e-4d49-8cf2-f395f7960679")
    public static GoalDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (GoalDiagram.canInstantiate(obj))
        	return new GoalDiagram(obj);
        else
        	throw new IllegalArgumentException("GoalDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5a26d900-6341-4851-812e-7bdd4c0cef7e")
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
        GoalDiagram other = (GoalDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("4cc1abdd-8fe4-434b-8ca3-7da9e11c46b5")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("153be5b1-04fd-4015-8688-536c98b4656f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d40568ed-30c3-450b-ace2-8983624dcaee")
    protected GoalDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("450e7de6-a2a6-41bf-9708-da4861532339")
    public static final class MdaTypes {
        @objid ("be108c54-9540-4abf-8e15-00cb10cefe36")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("75f77656-9088-49ac-9d98-295da8df2b6e")
        private static Stereotype MDAASSOCDEP;

        @objid ("b45ccbd9-b088-4cb6-9b51-be7a120adfd6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("522bf893-b435-4133-b6fc-9a6d75b4eecf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0a37-0000-000000000000");
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
