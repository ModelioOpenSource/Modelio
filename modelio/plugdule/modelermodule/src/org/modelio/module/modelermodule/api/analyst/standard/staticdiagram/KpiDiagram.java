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
 * Proxy class to handle a {@link StaticDiagram} with << kpi_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca735868-68ed-46f2-b748-17695c64b56f")
public class KpiDiagram {
    @objid ("20a0a9aa-b489-44de-a47c-9f2953149712")
    public static final String STEREOTYPE_NAME = "kpi_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("a3bf244a-588b-4e73-9406-4f62e6a24059")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link KpiDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << kpi_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("575927cb-9fce-40ea-9af9-e80cfbfaa00c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << kpi_diagram >> then instantiate a {@link KpiDiagram} proxy.
     * 
     * @return a {@link KpiDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("7d8e4b99-1235-441e-aeb8-b8539b681853")
    public static KpiDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME);
        return KpiDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link KpiDiagram} proxy or <i>null</i>.
     */
    @objid ("9dbb305d-cbc3-40a4-8dda-8e437bc4b84e")
    public static KpiDiagram instantiate(StaticDiagram obj) {
        return KpiDiagram.canInstantiate(obj) ? new KpiDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link KpiDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3e238df4-022b-494f-98d1-78816536647a")
    public static KpiDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (KpiDiagram.canInstantiate(obj))
        	return new KpiDiagram(obj);
        else
        	throw new IllegalArgumentException("KpiDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c5437084-551d-4df5-adfd-229e5c7a39d9")
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
        KpiDiagram other = (KpiDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("ab28e51c-c878-4043-9564-79d7da49aadc")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("c9d4f641-579a-4f2f-b9f1-4f069fa65544")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4dbd60e8-ba7f-47ef-b476-4533285cc769")
    protected KpiDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("14383e01-4c20-4ad8-b7c9-1c85c97c4c2e")
    public static final class MdaTypes {
        @objid ("98f7029c-004a-4cc9-aeb0-c9e90c5ddc54")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("29554848-45f2-4ef0-895c-9174fd1ef9b3")
        private static Stereotype MDAASSOCDEP;

        @objid ("56565061-776a-4ae0-8a62-3e5a13ccdb1c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ba71751-30ab-494a-8287-ea86cd3985d3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "19d2deba-a7b7-4818-8d19-aa8b2d63dba1");
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
