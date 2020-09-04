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
    @objid ("d941b36f-adf2-45f1-98a6-06b44b9d311b")
    public static final String STEREOTYPE_NAME = "goal_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("10a3652e-2a40-46ad-9226-d24a9148b194")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link GoalDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << goal_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9608d764-b333-4571-8d69-1b0cd936517f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << goal_diagram >> then instantiate a {@link GoalDiagram} proxy.
     * 
     * @return a {@link GoalDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("57c587b8-4224-4ab1-a30e-3a4046cc89ef")
    public static GoalDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME);
        return GoalDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link GoalDiagram} proxy from a {@link StaticDiagram} stereotyped << goal_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link GoalDiagram} proxy or <i>null</i>.
     */
    @objid ("e3cfd901-a6df-416b-8b3d-306dd2c361f2")
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
    @objid ("33ddb287-5002-4445-b69f-5d1ca7cfbf79")
    public static GoalDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (GoalDiagram.canInstantiate(obj))
        	return new GoalDiagram(obj);
        else
        	throw new IllegalArgumentException("GoalDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("30c8110a-f6ce-4005-bae0-0771976a102a")
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
    @objid ("a6d9ea97-5eaf-48ae-84b0-ab45d19fb854")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("500b25fa-d910-4d4a-a9fe-e87ee1ac602b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa93a357-620c-4b21-bbc4-844a6bc57d7f")
    protected GoalDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("450e7de6-a2a6-41bf-9708-da4861532339")
    public static final class MdaTypes {
        @objid ("68daf74b-84df-4db6-a478-a49fbc657797")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3fc7dcf9-64b4-4d80-ab4f-b1652783a261")
        private static Stereotype MDAASSOCDEP;

        @objid ("0bf20448-bbc8-4a09-8052-f25a7361110e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3de2ccf4-17af-4024-a2e0-65cbff10c41a")
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
