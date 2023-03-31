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
    @objid ("dd2f20c2-cb2f-4960-93d8-1c1cb45e472b")
    public static final String STEREOTYPE_NAME = "goal_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("78ba8b9d-170c-4ef9-b832-c6df7ef04f73")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link GoalDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << goal_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e4277396-c63a-4be9-a175-84b95c210277")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << goal_diagram >> then instantiate a {@link GoalDiagram} proxy.
     * 
     * @return a {@link GoalDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("caaaf2d0-028f-422a-bb10-3bde74dbca26")
    public static GoalDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
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
    @objid ("1c85b3b9-12e3-4535-bb8c-20b67e09b644")
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
    @objid ("e500f794-cfd8-4e0a-b229-c025ba904b86")
    public static GoalDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (GoalDiagram.canInstantiate(obj))
        	return new GoalDiagram(obj);
        else
        	throw new IllegalArgumentException("GoalDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("12576a7f-6e32-4d29-bffb-9dacdc276e36")
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
    @objid ("1ac133df-13bd-4468-babd-24452ffda747")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("9d010706-2a31-49a1-9211-a380fcfd30b3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c52631e5-d4a5-4048-a873-36b8cf7f7775")
    protected  GoalDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("450e7de6-a2a6-41bf-9708-da4861532339")
    public static final class MdaTypes {
        @objid ("6ff83716-f560-4a90-9d3f-1bbc4eaa70d3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("86cf6132-869f-40ca-aa47-8eb899465d6f")
        private static Stereotype MDAASSOCDEP;

        @objid ("e6f40d27-db2f-4bec-ac35-1bd3f75b1fb9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8d81d535-a775-47ae-80d1-de246d81299b")
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
