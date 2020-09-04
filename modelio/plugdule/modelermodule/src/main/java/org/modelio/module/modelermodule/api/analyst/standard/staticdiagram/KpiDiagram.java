/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
    @objid ("45d8fbbf-7ccd-4b37-bee9-db42098005c3")
    public static final String STEREOTYPE_NAME = "kpi_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("37d4060c-d74f-4dc1-a552-11ac737c7d18")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link KpiDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << kpi_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5796d855-59ea-4f70-bb24-57308341a90f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << kpi_diagram >> then instantiate a {@link KpiDiagram} proxy.
     * 
     * @return a {@link KpiDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("7626751b-3fd0-4883-b20e-f6c2da3dbb05")
    public static KpiDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME);
        return KpiDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link KpiDiagram} proxy or <i>null</i>.
     */
    @objid ("acd474ec-05bb-4bf3-908c-ff6d6dacf06a")
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
    @objid ("f8530dce-5984-4d21-914e-8866a2ccd726")
    public static KpiDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (KpiDiagram.canInstantiate(obj))
        	return new KpiDiagram(obj);
        else
        	throw new IllegalArgumentException("KpiDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a42b2050-ace1-4387-aaa5-c40a06545130")
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
    @objid ("e1f7bf7a-7206-4a8c-9b0c-f9930de52c51")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("81ce4357-c065-4599-8de4-0c9d456700b8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("238a1dee-dc9d-4d60-95fd-28c3bb61739e")
    protected KpiDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("14383e01-4c20-4ad8-b7c9-1c85c97c4c2e")
    public static final class MdaTypes {
        @objid ("c9a42cbf-a82d-4e96-87a3-12823538a63a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("81e98809-1ca4-4d31-832a-3c8280f13ec4")
        private static Stereotype MDAASSOCDEP;

        @objid ("8b040438-9082-45ac-b66f-7adb78055366")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("700613de-f096-4b13-8d37-244597290940")
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
